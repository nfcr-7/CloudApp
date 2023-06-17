package com.example.splashappcloud.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.documentfile.provider.DocumentFile
import androidx.navigation.NavController
import com.example.splashappcloud.R
import com.example.splashappcloud.ui.theme.colorTextField
import com.example.splashappcloud.ui.theme.whiteBackground
import com.example.splashappcloud.util.SharedViewModel
import com.example.splashappcloud.util.UserData

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {

    var userID: String by remember { mutableStateOf("") }

    var projectName: String by remember { mutableStateOf("") }

    var projectDescription: String by remember { mutableStateOf("") }

    var project: String by remember { mutableStateOf("") }

    var projectLanguage: String by remember { mutableStateOf("") }

    val selectedFiles = mutableStateListOf<DocumentFile>()

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current

    // main Layout
    Column(modifier = Modifier.fillMaxSize()) {
        // back button
        Row(
            modifier = Modifier
                .padding(start = 15.dp, top = 35.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
            }
        }
        // add project data Layout
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // UserID
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = userID,
                onValueChange = {
                    userID = it
                },
                label = {
                    Text(text = "ProjectID")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorTextField,
                    cursorColor = colorTextField,
                    focusedLabelColor = colorTextField
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            // Project Name
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = projectName,
                onValueChange = {
                    projectName = it
                },
                label = {
                    Text(text = "Project Name")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorTextField,
                    cursorColor = colorTextField,
                    focusedLabelColor = colorTextField
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            // Project Description
            OutlinedTextField(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                value = projectDescription,
                onValueChange = {
                    projectDescription = it
                },
                label = {
                    Text(text = "Project Description")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorTextField,
                    cursorColor = colorTextField,
                    focusedLabelColor = colorTextField
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            // Project
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = imageUri.toString(),
                onValueChange = {
                    project = it
                },
                label = {
                    Text(text = "Project")
                },
                trailingIcon = {

//                    val fileSelector =
//                        rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
//
//                            val document = uri?.let { DocumentFile.fromSingleUri(context, it) }
//                            document?.let {
//                                selectedFiles.add(it)
//                            }
//                        }
                    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
                        // Récupérer l'URI de l'image sélectionnée
                        result.data?.data?.let { uri ->
                            imageUri = uri
                        }
                    }

                    IconButton(onClick = {
//                        fileSelector.launch(arrayOf("*/*"))
                        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                            type = "*/*"
                        }
                        launcher.launch(intent)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.folder),
                            contentDescription = null,
                            tint = colorTextField
//                            tint = if (passwordVisibility) colorTextField else Color.Black
                        )
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorTextField,
                    cursorColor = colorTextField,
                    focusedLabelColor = colorTextField
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            // Project Language
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = projectLanguage,
                onValueChange = {
                    projectLanguage = it
                },
                label = {
                    Text(text = "Project Language")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorTextField,
                    cursorColor = colorTextField,
                    focusedLabelColor = colorTextField
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            // Save Button
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(colorTextField),
                onClick = {
                    val userData = UserData(
                        UserID = userID,
                        ProjectName = projectName ,
                        ProjectDescription = projectDescription,
                        Project = imageUri.toString(),
                        ProjectLanguage = projectLanguage
                    )

                    sharedViewModel.saveData(userData = userData, context = context)
                    navController.navigate("list_data")
                }
            ) {
                Text(text = "Save Project")
            }
        }
    }
}