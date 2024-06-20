package com.starkindustries.authentication_with_sqlitedatabase.Model
class User
{
    lateinit var name:String
    lateinit var email:String
    lateinit var username:String
    lateinit var password:String
    constructor( name_:String, email_:String, username_:String,password_:String)
    {
        this.name=name_
        this.email=email_
        this.username=username_
        this.password=password_
    }
    constructor(name_:String, email_:String, username_:String)
    {
        this.name=name_
        this.email=email_
        this.username=username_
    }
    constructor(name_:String, email_:String,)
    {
        this.name=name_
        this.email=email_
    }
    constructor()
    {}
}