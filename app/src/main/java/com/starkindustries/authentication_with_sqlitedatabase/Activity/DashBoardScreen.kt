package com.starkindustries.authentication_with_sqlitedatabase.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.starkindustries.authentication_with_sqlitedatabase.Database.DatabaseHandler
import com.starkindustries.authentication_with_sqlitedatabase.Keys.Keys
import com.starkindustries.authentication_with_sqlitedatabase.R
import com.starkindustries.authentication_with_sqlitedatabase.databinding.ActivityDashBoardScreenBinding

class DashBoardScreen : AppCompatActivity() {
    lateinit var binding:ActivityDashBoardScreenBinding
    lateinit var sharedPrefrences:SharedPreferences
    lateinit var editor:SharedPreferences.Editor
    lateinit var dbHandler:DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dash_board_screen)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_dash_board_screen)
        sharedPrefrences=getSharedPreferences(Keys.SHARED_PREFRENCES_NAME, MODE_PRIVATE)
        editor=sharedPrefrences.edit()
        dbHandler= DatabaseHandler(applicationContext,Keys.DATABASE_NAME,Keys.VERSION)
        binding.logoutButton.setOnClickListener()
        {
            var inext = Intent(this,LoginActivity::class.java)
            editor.putBoolean(Keys.STATUS,false)
            editor.apply()
//            var username: String? = sharedPrefrences.getString(Keys.USERNAME,"null")
//            if (username != null) {
////                dbHandler.removeUser(username)
//                editor.remove(Keys.USERNAME)
//                editor.apply()
//            }
            startActivity(inext)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}