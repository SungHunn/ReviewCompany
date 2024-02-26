package com.example.reviewcompany.presentation.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewcompany.presentation.screen.navigation.Screen
import com.example.reviewcompany.presentation.viewmodel.SignUpViewModel
import com.example.reviewcompany.ui.theme.ReviewCompanyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel,
) {
    val emailAddress = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,


        ) {
        Text(
            text = "회원가입",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = emailAddress.value,
            onValueChange = {
                emailAddress.value = it
            },
            shape = RoundedCornerShape(8.dp),
            label = {
                Text(text = "email")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                Color.Green
            )
        )
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            shape = RoundedCornerShape(8.dp),
            label = {
                Text(text = "password")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                Color.Green
            )

        )
        Spacer(modifier = Modifier.padding(10.dp))


        Button(onClick = {
            viewModel.createAccount(emailAddress.value, password.value)
            Log.d("state", "${viewModel.registerState.value}")
            if (viewModel.registerState.value){
                navController.navigate(Screen.Main.route)
            }

        }) {
            Text(text = "회원가입")
        }

    }

}


