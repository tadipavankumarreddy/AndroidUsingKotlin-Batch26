package com.nareshtech.scorekeeper

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    fun incrementScore(view: View) {
        count++
        result.setText(count.toString())
    }

    fun decrementScore(view: View) {
        count--
        result.setText(count.toString())
    }

    var count = 0
    lateinit var result:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        result = findViewById(R.id.result)
        Log.v("MAIN", "onCreate")
        if(savedInstanceState!=null && savedInstanceState.containsKey("COUNT")){
            count = savedInstanceState.getInt("COUNT")
            result.setText(count.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("COUNT", count)
    }

    override fun onStart() {
        super.onStart()
        Log.v("MAIN", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v("MAIN", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v("MAIN", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v("MAIN", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("MAIN", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("MAIN", "onRestart")
    }
}