package com.example.reviewcompany.presentation.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reviewcompany.R
import com.example.reviewcompany.data.ArticleEntity
import com.example.reviewcompany.presentation.screen.navigation.Screen
import com.example.reviewcompany.presentation.viewmodel.MainViewModel
import com.example.reviewcompany.ui.theme.ReviewCompanyTheme
import com.google.android.play.integrity.internal.f
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    auth: FirebaseAuth,
    firebaseList: StateFlow<List<ArticleEntity>>
) {

    val list = firebaseList.collectAsState().value


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

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.Writing.route)
                }
            ) {
                Column(modifier = Modifier.padding(5.dp)) {

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_thumbs_up_down_24),
                        contentDescription = "글 작성",
                        tint = Color.Blue
                    )
                    Text(text = "글 쓰기", color = Color.Black)

                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = 70.dp)
        ) {

            items(list.size) {

                Article(
                    nickName = list[it].nickName,
                    category = list[it].category,
                    companyName = list[it].companyName,
                    content = list[it].content)
            }
        }

    }
}

@Composable
fun Article(
    nickName: String,
    category: String,
    companyName: String,
    content: String,
) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(start = 5.dp, end = 5.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 15.dp, end = 15.dp)
                    .background(Color(0xFFFFFFFF))
            ) {
                Column(
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                ) {
                    Text(text = "작성자 : ${nickName}")
                    Text(text = "직종 : ${category}")
                    Text(text = "회사 이름 : ${companyName}")
                    Text(text = "후기 : ${content}", maxLines = 1, overflow = TextOverflow.Ellipsis)
                }

            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
                    .background(Color.Black),
                thickness = 2.dp,

            )
        }


}

@Preview
@Composable
fun ArticlePreview() {
    ReviewCompanyTheme {
        Article(
            nickName = "park",
            category = "IT / 개발",
            companyName = "Naver",
            content = "매우 좋았습니다."
        )
    }
}