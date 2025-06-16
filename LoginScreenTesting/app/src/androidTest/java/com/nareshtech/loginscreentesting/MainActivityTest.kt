package com.nareshtech.loginscreentesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    // Enable the testing
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    // Two cases
    @Test
    fun testValidLoginCredentials(){
        // Enter a valid username
        composeTestRule.onNodeWithText("Enter User name")
            .performTextInput("admin")
        // Enter a valid password
        composeTestRule.onNodeWithText("Enter password")
            .performTextInput("password123")
        // Click on the button
        composeTestRule.onNodeWithText("Validate Sign in")
            .performClick()

        // Assert login success is true
        composeTestRule.onNodeWithText("Login Validation:true")
            .assertIsDisplayed()

    }

    @Test
    fun testInValidLoginCredentials(){
// Enter a valid username
        composeTestRule.onNodeWithText("Enter User name")
            .performTextInput("wrong")
        // Enter a valid password
        composeTestRule.onNodeWithText("Enter password")
            .performTextInput("guessme")
        // Click on the button
        composeTestRule.onNodeWithText("Validate Sign in")
            .performClick()

        // Assert login success is true
        composeTestRule.onNodeWithText("Login Validation:false")
            .assertIsDisplayed()
    }
}