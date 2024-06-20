package com.starkindustries.authentication_with_sqlitedatabase.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.starkindustries.authentication_with_sqlitedatabase.Keys.Keys
import com.starkindustries.authentication_with_sqlitedatabase.Model.User

class DatabaseHandler(var context_: Context,var dbName:String,var version:Int):SQLiteOpenHelper(context_,dbName,null,version) {
    lateinit var context:Context
    init {
        this.context=context_
    }
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("Create table "+ Keys.TABLE_NAME+" ( "+Keys.USERNAME+" text Primary key, "+Keys.NAME+" Text, "+Keys.EMAIL+" Text, "+Keys.PASSWORD+" Text )")
        }
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("Drop is exist"+Keys.TABLE_NAME)
            onCreate(db)
        }
    }
    fun addUser(user:User)
    {
        var db:SQLiteDatabase = this.writableDatabase
        var contentValues:ContentValues = contentValuesOf()
        contentValues.put(Keys.NAME,user.name)
        contentValues.put(Keys.EMAIL,user.email)
        contentValues.put(Keys.USERNAME,user.username)
        contentValues.put(Keys.PASSWORD,user.password)
        db.insert(Keys.TABLE_NAME,null,contentValues)
        Toast.makeText(context, "User inserted Successfully", Toast.LENGTH_SHORT).show()
    }
    fun loginFunction(username:String,password:String):Boolean
    {
        val db:SQLiteDatabase = this.writableDatabase
        var cursor: Cursor = db.query(Keys.TABLE_NAME, arrayOf(Keys.USERNAME,Keys.NAME,Keys.EMAIL,Keys.PASSWORD),Keys.USERNAME+"=?"+" and "+Keys.PASSWORD+"=?",
            arrayOf(username,password),null,null,null)
        if((cursor!=null)&&(cursor.count!=0)&&(cursor.moveToFirst()))
            return true
        else
            return false

    }
    fun getCount():Int
    {
        val db:SQLiteDatabase = this.writableDatabase
        var cursor:Cursor = db.rawQuery("Select * from "+Keys.TABLE_NAME,null)
        if(cursor!=null)
            cursor.moveToFirst()
        return cursor.count
    }
    fun removeUser(username:String)
    {
        val db:SQLiteDatabase = this.writableDatabase
        db.delete(Keys.TABLE_NAME,Keys.USERNAME+"=?", arrayOf(username))
        Toast.makeText(context, "User Deleted successfully", Toast.LENGTH_SHORT).show()
    }
}
