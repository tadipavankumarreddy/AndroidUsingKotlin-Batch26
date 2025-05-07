package com.nareshtech.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController


@Composable
fun BottomNavigationBar(navController: NavController){
    val selectedNavigationIndex = rememberSaveable { mutableIntStateOf(0) }

    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            icon = Icons.Default.Home,
            route = Screen.Home.route
        ),
        NavigationItem(
            title = "Profile",
            icon = Icons.Default.Person,
            route = Screen.Profile.route
        ),
        NavigationItem(
            title = "Cart",
            icon = Icons.Default.ShoppingCart,
            route = Screen.Cart.route
        ),
        NavigationItem(
            title = "Setting",
            icon = Icons.Default.Settings,
            route = Screen.Setting.route
        )
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = { Text(item.title, color = if(index==selectedNavigationIndex.intValue) Color.Black else Color.Gray) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}


// Inorder to keep all the items related to each Option given in the bottom navigation bar, we used the following data class.
data class NavigationItem(
    val title:String,
    val icon:ImageVector,
    val route:String
)

// Let us rep all fourscreens
sealed class Screen(val route:String){
    object Home:Screen("home_screen")
    object Profile:Screen("profile_screen")
    object Cart:Screen("cart_screen")
    object Setting:Screen("setting_screen")
}

