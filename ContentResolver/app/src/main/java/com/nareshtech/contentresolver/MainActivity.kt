package com.nareshtech.contentresolver

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.nareshtech.contentresolver.ui.theme.ContentResolverTheme

class MainActivity : ComponentActivity() {

    companion object{
        const val AUTHORITY = "com.nareshtech.notetakingapp.CONTENT_PROVIDER"
        val NOTES_URI: Uri = Uri.parse("content://$AUTHORITY")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val c = contentResolver.query(NOTES_URI, null,null,null,null)
            ContentResolverTheme {

                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    val strings = StringBuilder()
                    c?.moveToFirst()
                    do{
                        val id = c?.getInt(0)
                        val title = c?.getString(1)
                        val content = c?.getString(2)
                        strings.append("$id $title $content\n")
                    }while(c?.moveToNext() == true)

                    Text(strings.toString(), fontSize = 20.sp)

                }
            }
        }
    }
}

