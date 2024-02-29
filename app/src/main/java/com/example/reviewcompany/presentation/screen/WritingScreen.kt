package com.example.reviewcompany.presentation.screen

import android.widget.Space
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.reviewcompany.data.ArticleEntity
import com.example.reviewcompany.presentation.screen.navigation.Screen
import com.example.reviewcompany.presentation.viewmodel.WritingViewModel
import com.example.reviewcompany.ui.theme.ReviewCompanyTheme
import com.google.firebase.auth.FirebaseAuth


val options =
    listOf("기획 / 전략", "법무 / 사무", "회계 / 세무", "개발 / 데이터", "디자인", "교육", "미디어 / 문화 / 스포츠", "건축 / 시설")


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WritingScreen(
    navController: NavController,
    auth: FirebaseAuth,
    viewModel: WritingViewModel,
) {
    val nickName = rememberSaveable { mutableStateOf("") }
    var selectedText by remember { mutableStateOf(options[0]) }
    val companyName = rememberSaveable { mutableStateOf("") }
    val content = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "글 쓰기",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Row(

        ) {

            OutlinedTextField(
                value = nickName.value,
                onValueChange = {
                    nickName.value = it
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
            Spacer(modifier = Modifier.padding(8.dp))

            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(1f)
            ) {

                var expanded by remember { mutableStateOf(false) }

                var textfieldSize by remember { mutableStateOf(Size.Zero) }

                val icon = Icons.Filled.ArrowDropDown


                Box() {
                    OutlinedTextField(
                        readOnly = true,
                        value = selectedText,
                        onValueChange = { selectedText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                //This value is used to assign to the DropDown the same width
                                textfieldSize = coordinates.size.toSize()
                            },
                        label = { Text("Label") },
                        trailingIcon = {
                            Icon(icon, "contentDescription",
                                Modifier.clickable { expanded = !expanded })
                        }
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                    ) {
                        options.forEach { label ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedText = label
                                    expanded = false
                                },
                                text = { Text(text = label) }
                            )

                        }
                    }
                }
            }

        }

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            value = companyName.value,
            onValueChange = {
                companyName.value = it
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

        Spacer(modifier = Modifier.padding(20.dp))

        OutlinedTextField(
            value = content.value,
            onValueChange = {
                content.value = it
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

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
            onClick = {
                val articleEntity = ArticleEntity(
                    uid = auth.uid!!,
                    nickName = nickName.value,
                    category = selectedText,
                    companyName = companyName.value,
                    content = content.value

                )
                viewModel.writeArticle(articleEntity)
                if (viewModel.writingState.value) {
                    navController.navigate(Screen.Main.route)
                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF81BE4E)
            )
        ) {
            Text("작성 완료")
        }


    }
}

