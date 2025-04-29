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

