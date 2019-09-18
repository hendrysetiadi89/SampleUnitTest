package in.ravidsrk.sample;

import android.content.Intent;
import android.os.IBinder;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ServiceTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class SampleServiceTest {

    @Rule
    public ServiceTestRule myServiceRule = new ServiceTestRule();
//    public SampleServiceTestRule myServiceRule = new SampleServiceTestRule();

    @Test
    public void testService() throws TimeoutException {
        myServiceRule.startService(new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), SampleService.class));
    }

    @Test
    public void testBoundService() throws TimeoutException {
//        IBinder binder = myServiceRule.bindService(
//                new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), SampleService.class));
//        SampleService service = ((SampleService.LocalBinder) binder).();
//        // Do work with the service
//        assertNotNull("Bound service is null", service);
    }
}
