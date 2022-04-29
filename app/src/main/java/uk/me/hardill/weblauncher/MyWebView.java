package uk.me.hardill.weblauncher;

import android.content.Context;

import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * TODO: document your custom view class.
 */
public class MyWebView extends WebView {

    Context context;
    GestureDetector gd;
    boolean fling = false;
    private static final int SWIPE_MIN_DISTANCE = 320;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private SwipeListerner swipeListerner;

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        gd = new GestureDetector(context, sogl);

    }

    public void setSwipeListener(SwipeListerner list) {
        swipeListerner = list;
    }

    GestureDetector.SimpleOnGestureListener sogl = new GestureDetector.SimpleOnGestureListener(){
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            Log.i("startX", "" + e1.getX());
//            Log.i("endX", "" + e2.getX());
//            Log.i("startY", "" + e1.getY());
//            Log.i("startY", "" + e2.getY());

            if (e1.getX() < 1200 && e1.getX() > 80) {
                return false;
            }
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Log.i("Swiped","swipe left");
                swipeListerner.onSwipe();
                fling = true;
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Log.i("Swiped","swipe right");
                swipeListerner.onSwipe();
                fling = true;
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("Long Press", "location " + e.getX()+ "," + e.getY());
            swipeListerner.onSwipe();
            super.onLongPress(e);
        }

        void show_toast(final String text) {
            Toast t = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            t.show();
        }
    };


    public boolean onTouchEvent(MotionEvent event) {
        gd.onTouchEvent(event);
        if (fling) {
            fling = false;
            return true;
        } else {
            return super.onTouchEvent(event);
        }
    }
}
