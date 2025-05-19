**Note-Taking App using Room DB + Jetpack Compose + MVVM**
---

## 🧱 Project Setup (build.gradle.kts)

### ✅ Plugin Configuration

```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Enables Kotlin Symbol Processing (for Room Annotations)
    id("com.google.devtools.ksp")
}
```

### ✅ Android Configuration

```kotlin
android {
    namespace = "com.nareshtech.notetakingapp"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.nareshtech.notetakingapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    ...
    buildFeatures {
        compose = true // Enables Jetpack Compose
    }
}
```

### ✅ Dependencies

```kotlin
dependencies {
    implementation("androidx.room:room-runtime:2.7.1") // Room DB
    ksp("androidx.room:room-compiler:2.7.1")            // KSP Compiler for Room Annotations

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1") // ViewModel in Compose
    implementation(libs.androidx.material3)                                // Material 3 UI
    ...
}
```

---

## 🗃️ Model: `Note.kt`

This is your **Room entity** class.

```kotlin
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String
)
```

* `@Entity` tells Room this class represents a table.
* `@PrimaryKey(autoGenerate = true)` makes `id` an auto-incremented primary key.

---

## 💾 DAO (Data Access Object): `NoteDao.kt`

```kotlin
@Dao
interface NoteDao {

    @Query("select * from notes")
    fun getAllNotes(): Flow<List<Note>> // Reactive data with Flow

    @Insert
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}
```

* All DB operations are **suspend** functions for use with coroutines.
* `Flow<List<Note>>` ensures **reactive updates**.

---

## 🏛️ Room Database: `NoteDatabase.kt`

```kotlin
@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile private var INSTANCE: NoteDatabase? = null
        fun getDatabase(context: Context): NoteDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, NoteDatabase::class.java, "note_db")
                    .build().also { INSTANCE = it }
            }
    }
}
```

* Singleton pattern prevents multiple instances of the DB.
* `getDatabase(context)` provides access to `NoteDao`.

---

## 🧠 Repository: `NoteRepository.kt`

```kotlin
class NoteRepository(private val dao: NoteDao) {
    val notes = dao.getAllNotes()

    suspend fun addNote(note: Note) = dao.insertNote(note)
    suspend fun deleteNote(note: Note) = dao.deleteNote(note)
}
```

* Repository abstracts DB operations.
* It provides a **clean API** to the ViewModel.

---

## 👨‍🏫 ViewModel: `NoteViewModel.kt`

```kotlin
class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: NoteRepository
    val notes: StateFlow<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        repo = NoteRepository(dao)
        notes = repo.notes.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }

    fun addNote(note: Note) = viewModelScope.launch { repo.addNote(note) }
    fun deleteNote(note: Note) = viewModelScope.launch { repo.deleteNote(note) }
}
```

* Inherits from `AndroidViewModel` to get `applicationContext`.
* Uses `stateIn()` to convert **Flow to StateFlow** for Compose.

---

## 🎨 UI: `NoteListScreen.kt`

```kotlin
@Composable
fun NoteListScreen(viewModel: NoteViewModel = viewModel()) {
    val notes by viewModel.notes.collectAsState()
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(WindowInsets.statusBars.asPaddingValues())
        .padding(WindowInsets.navigationBars.asPaddingValues())) {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (title.isNotBlank() && content.isNotBlank()) {
                    viewModel.addNote(Note(title = title, content = content))
                    title = ""
                    content = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Note")
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(notes) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = note.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = note.content, style = MaterialTheme.typography.bodySmall)

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = { viewModel.deleteNote(note) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Completed")
                        }
                    }
                }
            }
        }
    }
}
```

* Uses `OutlinedTextField` for input.
* `LazyColumn` for listing notes.
* `Card` UI with Material 3 for aesthetics.
* Delete button says “Completed” — nice touch!

---

## 🏁 Main Entry: `MainActivity.kt`

```kotlin
class MainActivity : ComponentActivity() {

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Optional: Allows edge-to-edge rendering

        setContent {
            NoteTakingAppTheme {
                NoteListScreen(viewModel) // Launch the main UI
            }
        }
    }
}
```

* ViewModel is scoped to the activity using `by viewModels()`.
* `setContent` uses Compose.

---

## 🌟 Suggestions for Enhancement

1. **Input Validation Feedback**:

   * Show a `Snackbar` if title/content is empty.
2. **Empty State UI**:

   * Show a message like "No notes yet" if list is empty.
3. **Dark Mode Support**:

   * Add color theming with `MaterialTheme`.

---

## 📦 Summary

| Layer       | What it Does                          | File                |
| ----------- | ------------------------------------- | ------------------- |
| Entity      | Defines the schema                    | `Note.kt`           |
| DAO         | Contains DB queries                   | `NoteDao.kt`        |
| Database    | Creates Room DB instance              | `NoteDatabase.kt`   |
| Repository  | Abstracts data access                 | `NoteRepository.kt` |
| ViewModel   | Business logic + lifecycle awareness  | `NoteViewModel.kt`  |
| UI          | Jetpack Compose UI to view/add/delete | `NoteListScreen.kt` |
| Entry Point | Launches app with Compose             | `MainActivity.kt`   |

---
