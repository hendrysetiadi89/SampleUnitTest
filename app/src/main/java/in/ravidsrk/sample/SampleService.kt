package `in`.ravidsrk.sample

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class SampleService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        Log.e("SampleService", "Binding SampleService")
        return LocalBinder()
    }

    override fun onCreate() {
        Log.e("SampleService", "Creating SampleService")
        super.onCreate()
    }

    override fun onDestroy() {
        Log.e("SampleService", "Destroying SampleService")
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.e("SampleService", "Executing some service work")
        return super.onStartCommand(intent, flags, startId)
    }

    inner class LocalBinder : Binder() {
        internal val service: SampleService
            get() = this@SampleService
    }

}
