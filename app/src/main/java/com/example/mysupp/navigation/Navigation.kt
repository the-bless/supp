package com.example.mysupp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mysupp.R
import com.example.mysupp.log.LoginScreen
import com.example.mysupp.log.RegisterScreen
import com.example.mysupp.log.VerifyScreen

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object VerifyScreen : Screen("verify_screen")
    object RegisterScreen : Screen("register_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEachIndexed { index, arg ->
                if (index == 0) {
                    append("/$arg")
                } else {
                    append("&$arg")
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(
            route = "${Screen.VerifyScreen.route}/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString("name") ?: ""
            VerifyScreen(navController = navController, name = name)
        }
        composable(
            route = "${Screen.RegisterScreen.route}/{name}", // Unique route for RegisterScreen
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString("name") ?: ""
            RegisterScreen(navController = navController, name = name)
        }
    }
}


@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .background(Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.add_list),
                contentDescription = "Logo",
                modifier = Modifier.size(45.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(0.8.dp)
                    .weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Log in to your Account")

        Text(text = "Welcome back! Please enter your details")

        Spacer(modifier = Modifier.height(32.dp))

        // Email field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.envelope),
                        contentDescription = "Email Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Enter your email")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = "Lock Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Password")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login button
        Button(
            onClick = {
                navController.navigate(Screen.VerifyScreen.withArgs(email))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log in")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Google login button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Google", Modifier.padding(start = 4.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Facebook login button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Facebook", Modifier.padding(start = 4.dp))
            }
        }

        Spacer(modifier = Modifier.height(240.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Sign up option
            Text(
                text = "Don't have an account? ",
                modifier = Modifier.padding(top = 8.dp)
            )


            Text(
                text = "Sign up",
                style = TextStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .clickable { /*TODO: Navigate to sign up screen*/ }
                    .padding(start = 4.dp, top = 8.dp)
            )

            Spacer(modifier = Modifier.width(70.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "To VerifyScreen",
                    style = TextStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .clickable { navController.navigate(Screen.VerifyScreen.withArgs(email)) }
                        .padding(start = 4.dp, top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun VerifyScreen(navController: NavController, name: String) {
    var text by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .background(Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.add_list),
                contentDescription = "Logo",
                modifier = Modifier.size(45.dp)

            )

            Spacer(modifier = Modifier.width(16.dp))

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(0.8.dp)
                    .weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Log in to your Account")

        Text(text = "Welcome back! Please enter your details")

        Spacer(modifier = Modifier.height(32.dp))

        // Name field
        OutlinedTextField(
            value = name,
            onValueChange = { },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "user Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Enter your Name")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.envelope),
                        contentDescription = "Email Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Enter your email")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = "lock",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Password")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.hexagon_check),
                contentDescription = "Tick Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Remember for 30 days")

            Spacer(modifier = Modifier.width(28.dp))

            Text(text = "Forgot my Password")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Log in")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Google", Modifier.padding(start = 4.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Facebook", Modifier.padding(start = 4.dp))
            }
        }

        Spacer(modifier = Modifier.height(120.dp))


            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "To RegisterScreen",
                    style = TextStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .clickable { navController.navigate(Screen.VerifyScreen.withArgs(password)) }
                        .padding(start = 4.dp, top = 8.dp)
                )
            }
        }
    }





@Composable
fun RegisterScreen(navController: NavController, name: String) {
    var text by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .background(androidx.compose.ui.graphics.Color.Black),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add_list),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(45.dp)

                )

                Spacer(modifier = Modifier.width(16.dp))

                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .height(0.8.dp)
                        .weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Log in to your Account")

            Text(text = "Welcome back!Please enter your details")

            Spacer(modifier = Modifier.height(32.dp))

            val (otp, setOtp) = remember { mutableStateOf("") }

            OTPInput(
                otp = otp,
                onOtpChanged = { newOtp ->
                    setOtp(newOtp)
                }
            )

            Button(
                onClick = {

                    val context = LocalContext
                    if (otp.length == 4) {

                    } else {

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Verify Email")

            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Didn't receive the OTP? Click to resend",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {


                    }
                )


                Spacer(modifier = Modifier.height(16.dp))


            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Back to login",

                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {


                    }
                )
            }
        }
    }
}

@Composable
fun OTPInput(otp: String, onOtpChanged: (String) -> Unit) {
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until 4) {
            val value = if (otp.length > i) otp[i] else ' '
            TextField(
                value = value.toString(),
                onValueChange = {
                        newChar ->
                    val newOtp = otp.toMutableList()
                    newOtp[i] = newChar.singleOrNull() ?: ' '
                    onOtpChanged(newOtp.joinToString(""))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (i == 4) androidx.compose.ui.text.input.ImeAction . Done else androidx.compose.ui.text.input.ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                singleLine = true,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
