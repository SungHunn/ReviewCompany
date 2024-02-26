package com.example.reviewcompany.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reviewcompany.presentation.screen.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
   navController: NavController,
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.requiredHeight(60.dp),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF8FDD4D),
                    //titleContentColor = Color.White
                ),
                title = {
                    Text(
                        "Review Company",
                        color = Color.White,
                        modifier = Modifier.padding(
                            top = 15.dp
                        )
                    )


                }
            )

        }
    ) {
        Column(Modifier.padding(it)) {

            Text(text = "MAIN SCREEN")

            Button(onClick = {navController.navigate("login_screen")}) {
                Text(text = "LoginScreen")
            }

            Button(onClick = {navController.navigate("signup_screen")}) {
                Text(text = "SignUpScreen")
            }
        }

    }
}

