package ltd.kaizo.moodtracker.controller.Adapter;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import ltd.kaizo.moodtracker.controller.Activities.MainActivity;

/**
 * The type Swipe detector.
 * Class to detect swipe gesture
 */
public class SwipeDetector extends GestureDetector {


    /**
     * Instantiates a new Swipe detector.
     *
     * @param context the context
     */
    public SwipeDetector(final MainActivity context) {
        super(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Boolean result;
                //start point
                float deltaX = e2.getX() - e1.getX();
                // finish point
                float deltaY = e2.getY() - e1.getY();
                if (Math.abs(deltaX) > Math.abs(deltaY)) {

                    if (deltaX < 0) {
                        //if we swipe to the left
                        result = true;
                    } else {
                        //we swipe on right
                        result = true;
                    }
                } else if (deltaY < 0) {
                    //swipe up
                    context.onSwipeUp();
                    result = true;
                } else {
                    //swipe down
                    context.onSwipeDown();
                    result = true;
                }
                return result;
            }
        });

    }


}
