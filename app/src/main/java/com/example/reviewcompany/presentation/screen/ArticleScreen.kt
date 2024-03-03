package com.example.reviewcompany.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.reviewcompany.data.ArticleEntity
import com.example.reviewcompany.presentation.screen.navigation.Screen
import com.example.reviewcompany.presentation.viewmodel.ArticleViewModel
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    navController: NavController,
    auth: FirebaseAuth,
    viewModel: ArticleViewModel,
    articleEntity: ArticleEntity?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "${articleEntity?.articleId}")

        Spacer(modifier = Modifier.padding(10.dp))

        Row(

        ) {

            articleEntity?.nickName?.let {
                OutlinedTextField(
                    readOnly = true,
                    value = it,
                    onValueChange = {},
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
                readOnly = true,
                value = it,
                onValueChange = {},
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
                readOnly = true,
                value = it,
                onValueChange = {},
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
            Text("삭제하기")
        }


    }
}