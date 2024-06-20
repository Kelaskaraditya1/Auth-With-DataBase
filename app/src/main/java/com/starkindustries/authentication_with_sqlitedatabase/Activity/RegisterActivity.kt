package com.starkindustries.authentication_with_sqlitedatabase.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.starkindustries.authentication_with_sqlitedatabase.Database.DatabaseHandler
import com.starkindustries.authentication_with_sqlitedatabase.Keys.Keys
import com.starkindustries.authentication_with_sqlitedatabase.Model.User
import com.starkindustries.authentication_with_sqlitedatabase.R
import com.starkindustries.authentication_with_sqlitedatabase.databinding.ActivityRegisterBinding
class RegisterActivity : AppCompatActivity() {
    lateinit var binding:ActivityRegisterBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor:SharedPreferences.Editor
    lateinit var dbHandler:DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_register)
        dbHandler= DatabaseHandler(applicationContext, Keys.DATABASE_NAME,Keys.VERSION)
        sharedPreferences=getSharedPreferences(Keys.SHARED_PREFRENCES_NAME, MODE_PRIVATE)
        editor=sharedPreferences.edit()
        binding.regButton.setOnClickListener()
        {
            var view = this.currentFocus
            if(view!=null)
            {
                var manager:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(view.windowToken,0)
            }
            dbHandler.addUser(User(binding.name.text.toString().trim(),binding.email.text.toString().trim(),binding.username.text.toString().trim(),binding.password.text.toString().trim()))
            editor.putString(Keys.USERNAME,binding.username.text.toString().trim())
            editor.putBoolean(Keys.STATUS,true)
            editor.apply()
            var inext:Intent = Intent(this,DashBoardScreen::class.java)
            startActivity(inext)
        }
        binding.logButton.setOnClickListener()
        {
            val inext: Intent = Intent(this,LoginActivity::class.java)
            startActivity(inext)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}