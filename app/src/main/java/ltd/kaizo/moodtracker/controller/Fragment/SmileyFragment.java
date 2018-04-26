package ltd.kaizo.moodtracker.controller.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import ltd.kaizo.moodtracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SmileyFragment extends Fragment {
    public static final String KEY_POSITION="position";
    public static final String KEY_COLOR="color";
    public static final String KEY_PICTURE_RESSOURCE="pictureRessource";

    public SmileyFragment() {
        // Required empty public constructor
    }

    // Method that will create a new instance of PageFragment, and add data to its bundle.
    public static SmileyFragment newInstance(int color, int pictureRessource) {
        //create new fragment
        SmileyFragment frag = new SmileyFragment();
        //create bundle and add it some data
        Bundle args = new Bundle();

        args.putInt("KEY_COLOR", color);
        args.putInt("KEY_PICTURE_RESSOURCE", pictureRessource);
        frag.setArguments(args);
        return (frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_main, container, false);
        //get widget from layout and serialise
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.activity_main_layout);
        ImageButton smiley = (ImageButton) view.findViewById(R.id.activity_main_btn_smiley);

        //get data from bundle
        int color = getArguments().getInt("KEY_COLOR", -1);
        int pictureressource = getArguments().getInt("KEY_PICTURE_RESSOURCE", -1);

        //assign picture and background color
        layout.setBackgroundResource(color);
        smiley.setImageResource(pictureressource);


        return (view);
    }

}
