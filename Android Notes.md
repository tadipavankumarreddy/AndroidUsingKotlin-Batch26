#### Communication
- fill out this form to keep in touch [link](https://forms.gle/9p8hom1m2eQ4PK669)

- Feel free to explore android app development using Java by referring to this exclusive e-book written by me [Link](https://android-app-development-documentation.readthedocs.io/en/latest/)


### Jetpack UI Compose

Jetpack compose is android's modern toolkit for building native UI. It simplifies and accelerates UI development on android by using declarative approach - you describe what the UI should look like, and compose takes care of the rest. Instead of Modifying the UI elements imperatively (like with XML + `findViewById()`), you simply declare the UI using kotlin code. 

1. Jetpack Compose Basics
- @Composable : An Annotation used on a function that defines UI in a declarative way.
- Recomposition: When state changes, Composables automatically update the UI. 
- Modifier: Used to decorate or add behavior (e.g., padding, size, click listeners)

2. Layouts:
- Column, Row, Box : Used to arrange the UI components. 
- Arragement & Alignment control positioning. 

#### Assignment
Just Go through compose basics and design the app by understanding the Column, Row, Box, Text, Button.

***mutableStateOf()***  is a function used to create a state holder that tracks changes to a variable. When the variable changes, it automatically triggers UI updates in Jetpack Compose.

**Specifically:**
- It is often created using mutableStateOf()
- When you change a mutable state, any composables that read the state will automatically recompose (refresh)

**remember{}** -> keep the state alive across recompositions.

#### ViewModel
ViewModel in android is a calss that is used to store and manage UI releted Data in a life cycle aware way.

***Why is it important ?***
- In android, things like Activity and Fragment can be destroyed and recreated (for Example, when you rotate the phone. )
- Normally, if you store data inside an activity (say, a list of names), it gets lost when the activity is recreated. 
- ViewModel helps by holding the data seperately  so that even if your activity/fragment is recreated, the data survives. 
- View Model is part of Android Jetpack libraries. 
- Combine ViewModel + mutableStateOf() to make jetpack compose apps powerful.

### Register me App Notes + UI Composables Explained

**What we Designed**  
![What we Designed](/registermephase1.png)

**1. Project Setup**
- MainActivity inherits from ComponentActivity, a standard class in Jetpack Compose for activities.

- enableEdgeToEdge() is called to render content under the system bars (status & navigation bars).

- setContent {} block sets the screen's UI using Compose.

🔗 Reference: [ComponentActivity - Android Docs](https://developer.android.com/reference/androidx/activity/ComponentActivity)  
🔗 Reference: [Edge-to-Edge Design - Android Docs](https://developer.android.com/develop/ui/views/layout/edge-to-edge)  

**2. Registration Screen UI**  
We define a Composable function `RegistrationScreen()` that builds the UI.

**a. State Management**
```kotlin
var name by remember { mutableStateOf("") }
var age by remember { mutableStateOf("") }
var gender by remember { mutableStateOf("") }
```
- Using `remember` + `mutableStateOf` to manage **local state** for user inputs like Name, Age, and Gender.

- These states will auto-trigger recomposition when changed.

🔗 Reference: [State in Compose - Official Docs](https://developer.android.com/jetpack/compose/state)

**b. Column Layout**
```kotlin
Column(
    modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
        .background(Color.White)
        .padding(WindowInsets.statusBars.asPaddingValues())
        .padding(WindowInsets.navigationBars.asPaddingValues())
)
```
- We use a Column to arrange components vertically.

- We apply:

  - Padding: To create spaces inside.

  - FillMaxSize(): To take full screen.

  - Background(Color.White): White background.

  - WindowInsets: Adjust padding dynamically for status/navigation bars to avoid UI overlaps.

🔗 Reference: [Column Layout - Android Docs](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/Column)  
🔗 Reference: [Handling Insets - Compose](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/WindowInsets)

**c. Heading (Title Text)**
```kotlin
Text(
    text = "Register Me!",
    fontFamily = FontFamily.Cursive,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    fontSize = 30.sp,
    modifier = Modifier
        .fillMaxWidth()
        .rotate(-10f)
        .padding(10.dp)
)
```

- Display a stylish heading:

  - **Font:** Cursive & Bold

  - **Text Size:** 30 sp

  - **Alignment:** Center

  - **Rotation:** Tilted (-10 degrees) for fun visual appeal.

🔗 Reference: [Text Composable - Compose Docs](https://developer.android.com/jetpack/compose/text/text)

**d. Name Input Field**
```kotlin
TextField(
    value = name,
    onValueChange = { name = it },
    label = { Text("Enter your name") },
    modifier = Modifier.fillMaxWidth()
)
```

- Basic `TextField` to capture name input.

- Expands to full width

🔗 Reference: [TextField - Compose Docs](https://developer.android.com/reference/kotlin/androidx/compose/material/TextField)

**e. Age Input Field (Outlined)**
```kotlin
OutlinedTextField(
    value = age,
    onValueChange = { age = it },
    label = { Text("Enter your Age") },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    modifier = Modifier.fillMaxWidth()
)
```
- **OutlinedTextField** gives a bordered style.

- Forces **numeric keyboard** using **KeyboardType.Number** for entering Age.

🔗 Reference: [OutlinedTextField - Compose Docs](https://developer.android.com/reference/kotlin/androidx/compose/material/OutlinedTextField)

**f. Gender Selection (Radio Buttons)**
```kotlin
Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround
) {
    Text(text = "Gender")

    RadioButton(selected = gender == "Male", onClick = { gender = "Male" })
    Text("Male")

    RadioButton(selected = gender == "Female", onClick = { gender = "Female" })
    Text("Female")
}
```
- Row layout used to arrange Gender selection options horizontally.

- Two RadioButton options:

  - Male

  - Female

- On click, gender value gets updated accordingly.
- 
🔗 Reference: [RadioButton - Compose Docs](https://developer.android.com/reference/kotlin/androidx/compose/material/RadioButton)

**3. Previewing in Android Studio**
```kotlin
@Preview(showBackground = true)
@Composable
private fun RegistrationScreen()
```
- `@Preview` annotation is used to see the layout without running the app.

🔗 Reference: [@Preview - Compose Docs](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/Preview)

### Intents in Android
Intents are messaging objects that are sent to the android OS, the OS then takes a call to address these requests. 

**Intents**  
- Explicit Intents
  - An Explicit intnet directly specifies the target component (Activity, Service, etc.,) it wants to start. You can use this when you know exactly where you want to go in your own app.
  - Eg: Navigating from one activity to another activity

- Implicit Intents
  - An Implicit intent does not name a specific component. Instead, it declares a general action to perform, and the android system find the best app to handle the action.
  - Eg: Opening a webpage in a browser

### What is a context in Android ?
Context is a handle to the system. It gives your app access to everything android provides - like resources, launching activities, accessing databases, systems and services,etc.,

**Types Of Context**  
1. Activity Context (This is inside an Activity)
   - Tied to the Activity's lifecycle
   - Use when dealing with UI or starting other activities.
2. ApplicationContext (getApplicationContext())
   -  Tied to the app's lifecycle.
   -  Use when you don't need the UI or Activity (e.g., from a background service)

**Activity Result Launcher**  
***What is it ?***
ActivityResultLauncher is a modren way to Start an **Activity and get a result back** from it, replacing the older ***startActivityForResult()*** and ***onActivityResult()*** methods. 

***Why use it ?***
- Simpler and lifecycle-aware
- Prevents memory leaks
- Automatically handles configuration changes (like Screen rotations)
- Clean Separation of Logic

***Code Steps to Follow***

**Step 1:**  
```kotlin
val launcher  = registerForActivityResult{(ActivityResultContracts.StartActivityForResult())
{result:ActivityResult->
val data = result.data?.getStringExtra("key")
Toast.makeText(applicationContext,data,Toast.LENGTH_LONG).show()
}
```

**Step 2**
```kotlin
 val i = Intent(applicationContext,SecondActivity::class.java)
i.putExtra("KEY",name)
/*startActivity(i)*/
launcher.launch(i)
```
**Step 3**
```kotlin
val resultIntent = Intent()
resultIntent.putExtra("key","Thank you!")
setResult(RESULT_OK,resultIntent)
finish()
```

### Common Intents (Implicit Intents present in most of the devices)

[Follow this link to use more of them](https://developer.android.com/guide/components/intents-common)

***Assignment:***
Please try another two or three common intents. Drop me an email once you are done. pavankreddy.t@gmail.com

---

## Jetpack Compose Navigation – **Elaborated Notes**

### Introduction to Navigation in Jetpack Compose

Navigation is a fundamental part of Android app development, allowing users to move between different screens (composables). Jetpack Compose provides a robust and modern way to handle navigation using the **Navigation Component for Compose**.

Jetpack Compose uses a **declarative approach** to UI, and its navigation system follows the same principle, using a **NavHost**, **NavController**, and **NavGraph** to manage destinations.

---

### Key Components

#### 1. **NavController**

* Acts as the central API for navigation.
* Keeps track of the back stack of composables.
* Created using:

  ```kotlin
  val navController = rememberNavController()
  ```

#### 2. **NavHost**

* Hosts all the destinations.
* Needs:

  * A `navController`
  * A `startDestination`
* Example:

  ```kotlin
  NavHost(navController = navController, startDestination = "home") {
      composable("home") { HomeScreen() }
      composable("details") { DetailsScreen() }
  }
  ```

#### 3. **composable()**

* Defines a destination in the navigation graph.
* The route string identifies each screen.
* You can pass arguments via route strings or using `navArguments`.

---

### Basic Navigation Example

```kotlin
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
    }
}
```

In your `HomeScreen`, you can navigate like this:

```kotlin
Button(onClick = { navController.navigate("profile") }) {
    Text("Go to Profile")
}
```

---

### Passing Data Between Screens

#### 1. **Using Route Parameters**

```kotlin
NavHost(navController, startDestination = "home") {
    composable("details/{userId}") { backStackEntry ->
        val userId = backStackEntry.arguments?.getString("userId")
        DetailsScreen(userId)
    }
}
```

Navigate using:

```kotlin
navController.navigate("details/123")
```

#### 2. **Using `navArgument()` for type safety**

```kotlin
composable(
    route = "details/{userId}",
    arguments = listOf(navArgument("userId") { type = NavType.StringType })
) { backStackEntry ->
    val userId = backStackEntry.arguments?.getString("userId")
    DetailsScreen(userId)
}
```

---

### Navigating Back

Use `navController.popBackStack()` to go back to the previous screen:

```kotlin
Button(onClick = { navController.popBackStack() }) {
    Text("Back")
}
```

---

### Deep Dive: Navigation with ViewModel

Compose encourages a unidirectional data flow and clean architecture. For passing ViewModels:

* Use `hiltViewModel()` or `viewModel()` in each screen.
* Avoid sharing ViewModels between unrelated composables via navigation.

---

### Navigation with Bottom Navigation Bar

You can integrate navigation with UI elements like bottom navigation:

```kotlin
val navController = rememberNavController()
Scaffold(
    bottomBar = {
        BottomNavigation {
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Home, null) },
                selected = currentRoute == "home",
                onClick = { navController.navigate("home") }
            )
            // Add more items...
        }
    }
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("settings") { SettingsScreen() }
    }
}
```

---

### Advanced Topics to explore

* **Nested Navigation Graphs**: Managing large apps with modular screen groups.
* **PopUpTo & Inclusive**: Fine-tuning backstack behavior.
* **Safe Args**: Not yet available in Compose. Handle with manual argument parsing.
* **Animated Navigation**: Using `accompanist-navigation-animation` for transitions.
* **Navigation with State Preservation**: Using `rememberSaveable`.

---

### Libraries and Tools

* `androidx.navigation:navigation-compose`
* Optional: `com.google.accompanist:accompanist-navigation-animation`

---

### Summary

| Concept                   | Description                                             |
| ------------------------- | ------------------------------------------------------- |
| `NavController`           | Manages backstack and navigation actions                |
| `NavHost`                 | Declares and hosts all destinations                     |
| `composable`              | Defines a destination/screen                            |
| Passing Arguments         | Via route strings and `navArguments`                    |
| Navigation with ViewModel | Use scoped ViewModels to avoid tight coupling           |
| Bottom Navigation         | Can be integrated with Compose navigation for tabbed UI |

---

### App Flow
1. HomeScreen -> Selecting the brand (Oppo, samsung, Nothing, etc.,)
2. VariantScreen -> Select Variant (8GB|128GB, 8GB|256GB)
3. Summary Screen -> Display Brand + Variant Selected.

