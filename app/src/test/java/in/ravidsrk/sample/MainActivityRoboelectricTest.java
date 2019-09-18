package in.ravidsrk.sample;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class MainActivityRoboelectricTest {

    private MainActivity activity;
    private ActivityController<MainActivity> activityController;

    @Before
    public void setup() {
        activityController = Robolectric.buildActivity(MainActivity.class);
    }

    @Test //@Ignore
    public void clickButton() {
        activityController.create().start().visible();
        activity = activityController.get();
        Button button = activity.findViewById(R.id.button);
        Button buttonfetch = activity.findViewById(R.id.button_fetch_network);
        RecyclerView recyclerView = activity.findViewById(R.id.recycler_view);
        EditText editText = activity.findViewById(R.id.editText);
        String text = editText.getText().toString();
        Log.i("TEST HENDRY", text);
        button.performClick();
        String text2 = editText.getText().toString();
        Log.i("TEST HENDRY", text2);
        Robolectric.flushForegroundThreadScheduler();
        Robolectric.flushBackgroundThreadScheduler();
        String text3 = editText.getText().toString();
        Log.i("TEST HENDRY", text3);

        String text4 = editText.getText().toString();
        Log.i("TEST HENDRY", text4);
        button.performClick();
        String text5 = editText.getText().toString();
        Log.i("TEST HENDRY", text5);
        Robolectric.flushForegroundThreadScheduler();
        Robolectric.flushBackgroundThreadScheduler();
        String text6 = editText.getText().toString();
        Log.i("TEST HENDRY", text6);

        MyAdapter myAdapter = (MyAdapter) recyclerView.getAdapter();
        if (myAdapter!=null) {
            Log.i("TEST HENDRY", String.valueOf(myAdapter.getItemCount()));
        }

        buttonfetch.performClick();
        Robolectric.flushForegroundThreadScheduler();
        Robolectric.flushBackgroundThreadScheduler();
        String text7 = editText.getText().toString();
        myAdapter = (MyAdapter) ((RecyclerView)activity.findViewById(R.id.recycler_view)).getAdapter();
        if (myAdapter!=null) {
            System.out.println( String.valueOf(myAdapter.getItemCount()));
            Log.i("TEST HENDRY", String.valueOf(myAdapter.getItemCount()));
            Log.i("TEST HENDRY", String.valueOf(myAdapter.getItemCount()));
        }

        assertNotNull("test button could not be found", button);
        assertEquals("button does not contain text 'Click Me!'", "Click Me", button.getText());
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
