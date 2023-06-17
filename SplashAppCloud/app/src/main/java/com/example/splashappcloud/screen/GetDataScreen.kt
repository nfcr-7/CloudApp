package com.example.splashappcloud.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.splashappcloud.ui.theme.colorTextField
import com.example.splashappcloud.ui.theme.primaryColor
import com.example.splashappcloud.ui.theme.whiteBackground
import com.example.splashappcloud.util.SharedViewModel
import com.example.splashappcloud.util.UserData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var userID: String by remember { mutableStateOf("") }

    var projectName: String by remember { mutableStateOf("") }

    var projectDescription: String by remember { mutableStateOf("") }

    var project: String by remember { mutableStateOf("") }

    var projectLanguage: String by remember { mutableStateOf("") }

    val context = LocalContext.current

    // main Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(primaryColor)
    ) {
        // back button
        Row(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
            }
        }
        // get project data Layout
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // UserID
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.6f),
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
                    )
                )
                // get project data Button
                Button(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(100.dp),
                    colors = ButtonDefaults.buttonColors(colorTextField),
                    onClick = {
                        sharedViewModel.retrieveData(
                            userID = userID,
                            context = context
                        ) { data ->
                            projectName = data.ProjectName
                            projectDescription = data.ProjectDescription
                            project = data.Project
                            projectLanguage = data.ProjectLanguage
                        }
                    }
                ) {
                    Text(text = "Get Project")
                }
            }
            // Project Name
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
//                    .border(0.dp, Color.White),
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
                )
            )

            // Project
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = project,
                onValueChange = {
                    project = it
                },
                label = {
                    Text(text = "Project")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorTextField,
                    cursorColor = colorTextField,
                    focusedLabelColor = colorTextField
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
                        Project = project,
                        ProjectLanguage = projectLanguage
                    )


                    sharedViewModel.saveData(userData = userData, context = context)
                }
            ) {
                Text(text = "Save Project")
            }
            // delete button
            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(colorTextField),
                onClick = {
                    sharedViewModel.deleteData(
                        userID = userID,
                        context = context,
                        navController = navController
                    )
                }
            ) {
                Text(text = "Delete Project")
            }
        }
    }
}