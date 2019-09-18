package `in`.ravidsrk.sample

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    internal lateinit var editText: EditText
    internal lateinit var button: Button
    internal lateinit var buttonFetchNetwork: Button
    internal lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        initUI()
    }

    private fun initUI() {
        editText = findViewById<EditText>(R.id.editText)
        button = findViewById<Button>(R.id.button)
        buttonFetchNetwork = findViewById<Button>(R.id.button_fetch_network)
        buttonFetchNetwork.setOnClickListener {
            FetchWebsiteAsyncTask(object : Listener {
                override fun onSuccess(result: String?) {
                    editText.setText(result)
                    recyclerView.adapter = MyAdapter()
                }
            }).execute()
        }
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onResume() {
        super.onResume()
        editText.setText("Budi")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    fun clickMe(view: View) {
        val previousText = editText.text.toString()
        if (previousText == "You clicked me after 5 seconds") {
            editText.setText(R.string.you_clicked_me_2)
        } else {
            editText.setText(R.string.you_clicked_me)
        }
        Handler().postDelayed({ editText.setText("You clicked me after 5 seconds") }, 10000)
    }

    fun buttonClicked(view: View) {
        if (view.id == R.id.buttonAdd) {
            // do some add work
            Log.d("ButtonClicked", "-------------------------------------------" + "adding")
        } else {
            // do the remove work
            Log.d("ButtonClicked", "-------------------------------------------" + "removing")
        }
    }
}

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view);
    }

    override fun getItemCount(): Int {
        return 5;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = position.toString()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}

interface Listener {
    fun onSuccess(result: String?)
}

class FetchWebsiteAsyncTask(listener: Listener) : AsyncTask<String, Void, String>() {
    var weakReference: WeakReference<Listener> = WeakReference(listener)

    override fun doInBackground(vararg params: String?): String {
        val urlConnection: HttpURLConnection
        val url: URL

        try {
            url = URL("https://google.com")
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.setRequestMethod("GET")

            val responseCode = urlConnection.getResponseCode()

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readStream(urlConnection.getInputStream())
            }

        } catch (e: Throwable) {
            e.printStackTrace()
        }

        return ""
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        val listener = weakReference.get()
        if (listener != null) {
            listener.onSuccess(result)
        }
    }

    private fun readStream(`in`: InputStream): String {
        var reader: BufferedReader? = null
        val response = StringBuilder()
        try {
            reader = BufferedReader(InputStreamReader(`in`))
            var line = reader.readLine()
            while (line != null) {
                response.append(line)
                line = reader.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            return response.toString()
        }
    }
}
