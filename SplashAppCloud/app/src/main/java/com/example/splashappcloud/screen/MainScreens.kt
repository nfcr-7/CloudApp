package com.example.splashappcloud.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splashappcloud.nav.Screens
import com.example.splashappcloud.ui.theme.colorTextField
import com.example.splashappcloud.ui.theme.primaryColor
import com.example.splashappcloud.ui.theme.whiteBackground

@Composable
fun MainScreens(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
// back button
        Row(
            modifier = Modifier
                .padding(start = 15.dp, top = 35.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Welcome to Cloud Data",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = whiteBackground

                )
        }

        // get user data Button
        Button(
//            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(colorTextField),
            onClick = {
                navController.navigate(route = Screens.GetDataScreen.route)
            }
        ) {
            Text(text = "Get Project Name")
        }

        // add project data Button
        Button(
//            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(colorTextField),
            onClick = {
                navController.navigate(route = Screens.AddDataScreen.route)
            }
        ) {
            Text(text = "Add Project Name")
        }

        Button(
//            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(colorTextField),
            onClick = {
                navController.navigate("list_data")
            }
        ) {
            Text(text = "List Project Name")
        }
    }
}