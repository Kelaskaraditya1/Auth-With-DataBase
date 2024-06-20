package com.starkindustries.authentication_with_sqlitedatabase.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.starkindustries.authentication_with_sqlitedatabase.Database.DatabaseHandler
import com.starkindustries.authentication_with_sqlitedatabase.Keys.Keys
import com.starkindustries.authentication_with_sqlitedatabase.R
import com.starkindustries.authentication_with_sqlitedatabase.databinding.ActivityLoginBinding
class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    lateinit var dbHandler:DatabaseHandler
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor:SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        dbHandler=DatabaseHandler(applicationContext, Keys.DATABASE_NAME,Keys.VERSION)
        sharedPreferences=getSharedPreferences(Keys.SHARED_PREFRENCES_NAME, MODE_PRIVATE)
        editor=sharedPreferences.edit()
        binding.LoginButton.setOnClickListener()
        {
            val view = this.currentFocus
            if(view!=null)
            {
                var manager:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(view.windowToken,0)
            }
            var status = dbHandler.loginFunction(binding.loginUsername.text.toString().trim(),binding.loginPassword.text.toString().trim())
            if(status)
            {
                var inext = Intent(this,DashBoardScreen::class.java)
                editor.putBoolean(Keys.STATUS,true)
                editor.putString(Keys.USERNAME,binding.loginUsername.text.toString().trim())
                editor.apply()
                startActivity(inext)
            }
            else
                Toast.makeText(applicationContext, "Username or Password is incorrect", Toast.LENGTH_SHORT).show()
        }
        binding.registerButton.setOnClickListener()
        {
            val inext:Intent = Intent(this,RegisterActivity::class.java)
            startActivity(inext)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}