package com.nareshtech.datastore

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nareshtech.datastore.ui.theme.DataStoreTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

// TODO 2: Create the data store instance (usually done in a singleton or context extension)
val Context.dataStore by preferencesDataStore(name = "user_prefs")

// TODO 3: Define the keys
object PreferenceKeys {
    val USERNAME = stringPreferencesKey("username")
    val AGE = stringPreferencesKey("age")
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataStoreTheme {
                DataStoreScreen(context = this)
            }
        }
    }
}

// TODO 4: To save and retrieve data, create functions and they should work asynchronously
suspend fun saveUserData(context: Context, username: String, age: String) {
    context.dataStore.edit { preferences ->
        preferences[PreferenceKeys.USERNAME] = username
        preferences[PreferenceKeys.AGE] = age
    }
}

fun readUserData(context: Context): Flow<Pair<String, String>> {
    return context.dataStore.data.map { preferences ->
        val username = preferences[PreferenceKeys.USERNAME] ?: ""
        val age = preferences[PreferenceKeys.AGE] ?: ""
        (username to age) as Pair<String, String>
    }
}

@Composable
private fun DataStoreScreen(context: MainActivity) {
    // States for user Input
    var username by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    // states for retrieved data
    var savedUsername by remember { mutableStateOf("") }
    var savedAge by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()
        .padding(WindowInsets.statusBars.asPaddingValues())
        .padding(WindowInsets.navigationBars.asPaddingValues())) {

        TextField(value = username,
            onValueChange = {username = it},
            label = {Text("Enter the username")},
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(8.dp))

        TextField(value = age,
            onValueChange = {age = it},
            label = {Text("Enter the Age")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                // Storing values into data store
                scope.launch {
                    saveUserData(context,username,age)
                }
            },
                modifier = Modifier.weight(1f)) {
                Text("Save Data")
            }

            Button(onClick = {
                // Retrieve data
                scope.launch {
                    readUserData(context).collect { (u,a)->
                        savedUsername = u
                        savedAge = a
                    }
                }
            },
                modifier = Modifier.weight(1f)) {
                Text("Retrieve Data")
            }
        }

        Text(
            text = "$savedUsername $savedAge",
            fontWeight = FontWeight.Bold
        )
    }
}
