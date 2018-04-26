package ltd.kaizo.moodtracker.controller.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class VerticalViewPager extends ViewPager {
    public VerticalViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        // use PageTransformer to implement vertical viewpager

        setPageTransformer(true, new VerticalViewPager.PageTransformer());

        // disable over scroll shadow

        setOverScrollMode(OVER_SCROLL_NEVER);

    }
    public class PageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {

            if (position < -1) {
                // [-infinity, -1], view page is off-screen to the left


                // hide the page.

                page.setVisibility(View.INVISIBLE);

            } else if (position <= 1) {
                // [-1, 1], page is on screen


                // show the page

                page.setVisibility(View.VISIBLE);

                // get page back to the center of screen since it will get swipe horizontally by default.

                page.setTranslationX(page.getWidth() * -position);

                // set Y position to swipe in vertical direction.

                float y = position * page.getHeight();
                page.setTranslationY(y);

            } else {
                // [1, +infinity], page is off-screen to the right


                // hide the page.

                page.setVisibility(View.INVISIBLE);
            }
        }


    }
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean interceped = super.onInterceptTouchEvent(swapXY(ev));
        swapXY(ev); // swap x,y back for other touch events.

        return interceped;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapXY(ev));
    }






}

