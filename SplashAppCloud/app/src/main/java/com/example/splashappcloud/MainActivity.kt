package com.example.splashappcloud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.splashappcloud.screen.AddDataScreen
import com.example.splashappcloud.screen.GetDataScreen
import com.example.splashappcloud.screen.ListDataScreen
import com.example.splashappcloud.screen.MainScreens
import com.example.splashappcloud.ui.theme.colorTextField
import com.example.splashappcloud.ui.theme.primaryColor
import com.example.splashappcloud.ui.theme.whiteBackground
import com.example.splashappcloud.util.SharedViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

//    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navigation(sharedViewModel)
        }
    }
}

@Composable
fun navigation( sharedViewModel: SharedViewModel ){
    
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ){
        // splash screen app
        composable("splash_screen"){ SplashScreen(navController = navController) }

        // main screen project app
        composable("main_screen"){ MainScreen(navController = navController) }

        // register page
        composable("register_page"){ RegisterPage(navController = navController) }

        // main screen page project
        composable("main_screens") { MainScreens(navController = navController) }

        // get project data screen
        composable("get_data_screen") { GetDataScreen(navController = navController, sharedViewModel = sharedViewModel) }

        // add project data screen
        composable("add_data_screen") { AddDataScreen(navController = navController, sharedViewModel = sharedViewModel) }

        composable("list_data") { ListDataScreen(navController = navController ) }
        // list project data screen
//        composable(
//            route = "list_all_screen/{userID}",
//            arguments = listOf(navArgument("userID") { type = NavType.StringType })
//        ) {  backStackEntry ->
//
//            ListDataScreen(
//            userID = backStackEntry.arguments?.getString("userID").toString(),
//            navController = navController,
//            sharedViewModel = sharedViewModel
//        )}

//        composable("list_all_data") { ListAllData(navController = navController, sharedViewModel = sharedViewModel)}
    }
}

@Composable
fun SplashScreen(navController: NavController){

    LaunchedEffect(key1 = true){
        delay(3000L)
        navController.navigate("main_screen")
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.l),
            "",
            modifier = Modifier
                .width(200.dp)
                .height(500.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {

    val image = painterResource(id = R.drawable.n)

    var emailValue by remember { mutableStateOf("") }

    var passwordValue by remember { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(primaryColor),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(image, contentDescription = null)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(whiteBackground)
                .padding(16.dp),
//                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Sign In", fontSize = 30.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                )

                Spacer(modifier = Modifier.padding(20.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = emailValue,
                        onValueChange = { textField ->
                            emailValue = textField
                        },
                        label = { Text(text = "Email Address") },
                        placeholder = { Text(text = "Email Address") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorTextField,
                            cursorColor = colorTextField,
                            focusedLabelColor = colorTextField
                        )
                    )

                    OutlinedTextField(
                        value = passwordValue,
                        label = { Text("Password") },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.password_eye),
                                    contentDescription = null,
                                    tint = if (passwordVisibility) colorTextField else Color.Black
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .focusRequester(focusRequester = focusRequester),
                        onValueChange = { passwordValue = it },
                        placeholder = { Text(text = "Password") },
                        singleLine = true,

                        visualTransformation = if (passwordVisibility) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorTextField,
                            cursorColor = colorTextField,
                            focusedLabelColor = colorTextField
                        )
                    )

                    Spacer(modifier = Modifier.padding(20.dp))

                    Button(
                        onClick = {navController.navigate("main_screens")},
                        colors = ButtonDefaults.buttonColors(colorTextField),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                    ) {
                        Text(text = "Sign In", fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.padding(20.dp))

                    Text(
                        text = "Create An Account",
                        modifier = Modifier.clickable(onClick = {
                            navController.navigate("register_page"){
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        })
                    )

                    Spacer(modifier = Modifier.padding(20.dp))

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(navController: NavController) {

    val image = painterResource(id = R.drawable.n)

    val nameValue = remember { mutableStateOf("") }

    val emailValue = remember { mutableStateOf("") }

    val phoneValue = remember { mutableStateOf("") }

    val passwordValue = remember { mutableStateOf("") }

    val confirmPasswordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }

    val confirmPasswordVisibility = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(primaryColor),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(image, contentDescription = null)

        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.70f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(whiteBackground)
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sign Up", fontSize = 30.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                )

                Spacer(modifier = Modifier.padding(20.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = nameValue.value,
                        onValueChange = { nameValue.value = it },
                        label = { Text(text = "Name") },
                        placeholder = { Text(text = "Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorTextField,
                            cursorColor = colorTextField,
                            focusedLabelColor = colorTextField
                        )
                    )

                    OutlinedTextField(
                        value = emailValue.value,
                        onValueChange = { emailValue.value = it },
                        label = { Text(text = "Email Address") },
                        placeholder = { Text(text = "Email Address") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorTextField,
                            cursorColor = colorTextField,
                            focusedLabelColor = colorTextField
                        )
                    )

                    OutlinedTextField(
                        value = phoneValue.value,
                        onValueChange = { phoneValue.value = it },
                        label = { Text(text = "Phone Number") },
                        placeholder = { Text(text = "Phone Number") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorTextField,
                            cursorColor = colorTextField,
                            focusedLabelColor = colorTextField
                        )
                    )

                    OutlinedTextField(
                        value = passwordValue.value,
                        onValueChange = { passwordValue.value = it },
                        label = { Text(text = "Password") },
                        placeholder = { Text(text = "Password") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.password_eye),
                                    contentDescription = null,
                                    tint = if (passwordVisibility.value) colorTextField else Color.Black
                                )
                            }
                        },
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorTextField,
                            cursorColor = colorTextField,
                            focusedLabelColor = colorTextField
                        )
                    )

                    OutlinedTextField(
                        value = confirmPasswordValue.value,
                        onValueChange = { confirmPasswordValue.value = it },
                        label = { Text(text = "Confirm Password") },
                        placeholder = { Text(text = "Confirm Password") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        trailingIcon = {
                            IconButton(onClick = {
                                confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.password_eye),
                                    contentDescription = null,
                                    tint = if (confirmPasswordVisibility.value) colorTextField else Color.Black
                                )
                            }
                        },
                        visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorTextField,
                            cursorColor = colorTextField,
                            focusedLabelColor = colorTextField
                        )
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    Button(
                        onClick = {navController.navigate("main_screens")},
                        colors = ButtonDefaults.buttonColors(colorTextField),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                    ) {
                        Text(text = "Sign Up", fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.padding(20.dp))

                    Text(
                        text = "Login Instead",
                        modifier = Modifier.clickable(onClick = {
                            navController.navigate("main_screen"){
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        })
                    )

                    Spacer(modifier = Modifier.padding(20.dp))



                }
            }
        }
    }
}



