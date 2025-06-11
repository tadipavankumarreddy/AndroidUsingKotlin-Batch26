package com.nareshtech.hiltinandroid

import javax.inject.Inject

// TODO 3: Constructor injection
// The @Inject constructor() tells hilt how to create an instance of `UserRepository`

class UserRepository @Inject constructor(){
    fun getUserName():String{
        return "Pavan Kumar Reddy"
    }
}