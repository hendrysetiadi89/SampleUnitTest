package in.ravidsrk.sample;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import org.apache.tools.ant.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowLooper;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class MainActivityRoboelectricTest {

    @Test //@Ignore
    public void clickButtonFetch() {
        ActivityController<MainActivity> activityController = Robolectric.buildActivity(MainActivity.class);
        activityController.create().start().visible();
        MainActivity activity = activityController.get();
        Button buttonfetch = activity.findViewById(R.id.button_fetch_network);

        buttonfetch.performClick();
        Robolectric.flushForegroundThreadScheduler();
        Robolectric.flushBackgroundThreadScheduler();
        int count = 0;
        ShadowLooper.idleMainLooper(5, TimeUnit.SECONDS);
        MyAdapter myAdapter = (MyAdapter) ((RecyclerView) activity.findViewById(R.id.recycler_view)).getAdapter();
        if (myAdapter!=null) {
            count = myAdapter.getItemCount();
            System.out.println(String.valueOf(count));
        } else {
            System.out.println("adapter is null");
        }
        assertEquals("Count is not 5", 5, count);
    }

    @Test //@Ignore
    public void clickButton() {
        ActivityController<MainActivity> activityController = Robolectric.buildActivity(MainActivity.class);
        activityController.create().start().visible();
        MainActivity activity = activityController.get();
        Button button = activity.findViewById(R.id.button);
        EditText editText = activity.findViewById(R.id.editText);
        String text = editText.getText().toString();
        System.out.println("Before click: " + text);
        button.performClick();

        String text2 = editText.getText().toString();
        System.out.println("After click 1: " + text2);

        Robolectric.flushForegroundThreadScheduler();
        Robolectric.flushBackgroundThreadScheduler();
        String text3 = editText.getText().toString();
        System.out.println("After click 1 and wait: " + text3);

        button.performClick();

        String text4 = editText.getText().toString();
        System.out.println("After click 2: " + text4);

        Robolectric.flushForegroundThreadScheduler();
        Robolectric.flushBackgroundThreadScheduler();
        String text5 = editText.getText().toString();
        System.out.println("After click 2 and wait: " + text5);

    }


    static class MyAsyncTask extends android.os.AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
