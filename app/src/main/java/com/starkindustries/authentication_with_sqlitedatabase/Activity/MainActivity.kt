package com.starkindustries.authentication_with_sqlitedatabase.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.starkindustries.authentication_with_sqlitedatabase.Keys.Keys
import com.starkindustries.authentication_with_sqlitedatabase.R
import com.starkindustries.authentication_with_sqlitedatabase.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var sharedPrefrences:SharedPreferences
    lateinit var editor:SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
//        SplashController().execute()
        sharedPrefrences=getSharedPreferences(Keys.SHARED_PREFRENCES_NAME, MODE_PRIVATE)
        editor=sharedPrefrences.edit()
        if(sharedPrefrences.getBoolean(Keys.STATUS,false))
        {
            Handler(Looper.getMainLooper()).postDelayed({
                val inext = Intent(this,DashBoardScreen::class.java)
                startActivity(inext)
            },2000)
        }
        else
        {
            Handler(Looper.getMainLooper()).postDelayed({
                val inext = Intent(this,LoginActivity::class.java)
                startActivity(inext)
            },2000)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
//    open inner class SplashController:AsyncTask<String?,Void?,String?>()
//    {
//        override fun doInBackground(vararg params: String?): String? {
//            for(i in 0..3)
//            {
//                try{
//                    Thread.sleep(1000)
//                }
//                catch(e:Exception)
//                {
//                    Thread.interrupted()
//                }
//            }
//           return "result"
//        }
//
//        override fun onPostExecute(result: String?) {
//            var inext: Intent = Intent(this@MainActivity,LoginActivity::class.java)
//            startActivity(inext)
//            finish()
//        }
//    }
}