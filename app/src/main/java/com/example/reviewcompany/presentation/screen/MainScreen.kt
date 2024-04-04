package com.example.reviewcompany.presentation.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reviewcompany.R
import com.example.reviewcompany.domain.model.DomainArticle
import com.example.reviewcompany.presentation.screen.navigation.Screen
import com.example.reviewcompany.presentation.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.StateFlow


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    auth: FirebaseAuth,
    viewModel: MainViewModel,
    idList: StateFlow<List<String>>,
) {
    LaunchedEffect(key1 = 1){
        viewModel.getArticle()
    }


    val list = viewModel.firebaseList.collectAsState().value
    val idList = idList.collectAsState().value
    Log.e("test", "${list.size}")

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
                    articleId = idList[it],
                    uid = list[it].uid,
                    nickName = list[it].nickName,
                    category = list[it].category,
                    companyName = list[it].companyName,
                    content = list[it].content,
                    navController
                )


            }
        }

    }
}

@Composable
fun Article(
    articleId: String,
    uid: String,
    nickName: String,
    category: String,
    companyName: String,
    content: String,
    navController: NavController,

    ) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(10.dp)
                .border(BorderStroke(1.dp, Color(0xFF86C73A)),shape = RoundedCornerShape(15.dp))
                .clickable {

                    val article = DomainArticle(
                        articleId = articleId,
                        uid = uid,
                        nickName = nickName,
                        category = category,
                        companyName = companyName,
                        content = content

                    )

                    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
                    navController.navigate(Screen.Article.route)
                }
        ) {
            Column(
                modifier = Modifier.padding(start = 5.dp, end = 5.dp,top = 5.dp)
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

        Spacer(modifier = Modifier.padding(10.dp).background(Color.White))
    }


}
