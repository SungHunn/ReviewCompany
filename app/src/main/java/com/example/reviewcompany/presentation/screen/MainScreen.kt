package com.example.reviewcompany.presentation.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reviewcompany.R
import com.example.reviewcompany.presentation.screen.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    auth: FirebaseAuth,
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.requiredHeight(60.dp),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF75BF37),
                ),
                title = {
                    Text(
                        "Review Company",
                        color = Color.White,
                        modifier = Modifier.padding(
                            top = 15.dp
                        )
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "search",
                            tint = Color.White,
                            modifier = Modifier.padding(top = 15.dp)
                        )
                    }
                },
                navigationIcon = {
                    Column(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .clickable {
                                auth.signOut()
                                navController.navigate(Screen.Login.route)
                            }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_account_circle_24),
                            contentDescription = "account",
                            tint = Color.White,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                        Text(text = "로그 아웃", color = Color.White)
                    }
                }

            )

        }
    ) {
        Column(Modifier.padding(it)) {


        }

    }
}

//@Composable
//fun logoutDialog(
//    onDismissRequest: () -> Unit,
//    onConfirmation: () -> Unit,
//) {
//    AlertDialog(
//        icon = {
//            Icon(
//                imageVector = Icons.Filled.ExitToApp,
//                contentDescription = "Logout Icon"
//            )
//        },
//        title = {
//            Text(text = "Logout")
//        },
//        text = {
//            Text(text = "로그 아웃 하시겠습니까?")
//        },
//        onDismissRequest = {
//            onDismissRequest()
//        },
//        confirmButton = {
//            TextButton(
//                onClick = {
//                    onConfirmation()
//                }
//            ) {
//                Text("예")
//            }
//        },
//        dismissButton = {
//            TextButton(
//                onClick = {
//                    onDismissRequest()
//                }
//            ) {
//                Text("아니오")
//            }
//        }
//    )
//}
//
