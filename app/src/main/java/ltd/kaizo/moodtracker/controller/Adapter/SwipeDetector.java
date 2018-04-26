package ltd.kaizo.moodtracker.controller.Adapter;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class SwipeDetector extends GestureDetector {


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
                        Log.i("SWIPE", "Swipe LEFT");
                                result =  true;
                    } else {
                        //we swipe on right
                        Log.i("SWIPE", "Swipe RIGHT");
                                  result =  true;
                    }
                } else if (deltaY < 0) {
                    //swipe up
                    Log.i("SWIPE", "Swipe UP");
                       context.onSwipeUp();
                    result =  true;
                } else {
                    //swipe down
                    Log.i("SWIPE", "Swipe DOWN");
                         context.onSwipeDown();
                    result =  true;
                }
                return result;
            }
        });

    }


}
