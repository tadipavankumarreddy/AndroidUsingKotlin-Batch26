package com.nareshtech.loginscreentesting

import android.graphics.Paint
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import org.junit.Assert.*
import org.junit.Test

class LoginFuncTest {

    fun isValidCredentials(userName:String, password:String): Boolean{
        if(userName.isBlank()||password.isBlank())
            return false
        return userName == "admin" && password == "password123"
    }

    //Positive case
    @Test
    fun validLogin_returnTrue(){
        assertTrue(isValidCredentials("admin","password123"))
    }

    // Negative Case
    @Test
    fun invalidLogin_returnFalse(){
        assertFalse(isValidCredentials("Admin","Password123"))
        assertFalse(isValidCredentials("pavan","Kumar"))
        assertFalse(isValidCredentials("","password123"))
    }

}