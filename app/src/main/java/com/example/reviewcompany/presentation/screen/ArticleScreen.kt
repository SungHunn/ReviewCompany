package com.example.reviewcompany.presentation.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.reviewcompany.R
import com.example.reviewcompany.data.ArticleEntity
import com.example.reviewcompany.presentation.screen.navigation.Screen
import com.example.reviewcompany.presentation.viewmodel.ArticleViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.android.awaitFrame

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    navController: NavController,
    auth: FirebaseAuth,
    viewModel: ArticleViewModel,
    articleEntity: ArticleEntity?,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.requiredHeight(40.dp),
                title = {},
                actions = {},
                navigationIcon = {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "뒤로 가기",
                        modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                }

            )

        },
        floatingActionButton = {

        },
        floatingActionButtonPosition = FabPosition.End
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            //Text(text = "${articleEntity?.articleId}")
            val editable = rememberSaveable { mutableStateOf(true) }
            var isVisible by remember { mutableStateOf(false) }

            var editNickname by remember { mutableStateOf(articleEntity?.nickName) }
            var editCompanyName by remember { mutableStateOf(articleEntity?.companyName) }
            var editContent by remember { mutableStateOf(articleEntity?.content) }


            Spacer(modifier = Modifier.padding(10.dp))

            Row(

            ) {

                articleEntity?.nickName?.let {
                    OutlinedTextField(
                        readOnly = editable.value,
                        value = editNickname!!,
                        onValueChange = {
                            editNickname = it
                        },
                        shape = RoundedCornerShape(8.dp),
                        label = {
                            Text(text = "nickname")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            Color.Black
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))

                articleEntity?.category?.let {
                    OutlinedTextField(
                        readOnly = true,
                        value = it,
                        onValueChange = {},
                        shape = RoundedCornerShape(8.dp),
                        label = {
                            Text(text = "category")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            Color.Black
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp)
                    )
                }


            }

            Spacer(modifier = Modifier.padding(10.dp))

            articleEntity?.companyName?.let {
                OutlinedTextField(
                    readOnly = editable.value,
                    value = editCompanyName!!,
                    onValueChange = {
                        editCompanyName = it
                    },
                    shape = RoundedCornerShape(8.dp),
                    label = {
                        Text(text = "company Name")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                )
            }

            Spacer(modifier = Modifier.padding(20.dp))

            articleEntity?.content?.let {
                OutlinedTextField(
                    readOnly = editable.value,
                    value = editContent!!,
                    onValueChange = {
                        editContent = it
                    },
                    shape = RoundedCornerShape(8.dp),
                    label = {
                        Text(text = "content")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        Color.Black
                    ),
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(horizontalArrangement = Arrangement.Start) {
                AnimatedVisibility(
                    visible = isVisible,

                    ) {
                    Button(onClick = {
                        viewModel.editArticle(
                            ArticleEntity(
                                articleId = articleEntity?.articleId!!,
                                uid = auth.currentUser?.uid!!,
                                nickName = editNickname!!,
                                category = articleEntity?.category!!,
                                companyName = editCompanyName!!,
                                content = editContent!!
                            )
                        )
                        navController.navigate(Screen.Main.route)
                    }) {
                        Text(text = "수정 완료 ")
                    }

                }
            }

            Row() {
                Button(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
                    onClick = {
                        editable.value = false
                        isVisible = true

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF81BE4E)
                    )
                ) {
                    Text("수정")
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Button(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
                    onClick = {
                        viewModel.deleteArticle(articleEntity)
                        navController.navigate(Screen.Main.route)


                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF81BE4E)
                    )
                ) {
                    Text("삭제")
                }

            }


        }
    }

}