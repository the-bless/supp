package com.example.mysupp.log

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mysupp.R

@Composable
fun RegisterScreen() {
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
                    if (it.length <= 1) {
                        val newOtp = otp.toMutableList()
                        if (it.isNotEmpty()) {
                            newOtp[i] = it[0]
                        } else {
                            newOtp[i] = ' '
                        }
                        onOtpChanged(newOtp.joinToString(""))
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (i == 4) ImeAction.Done else ImeAction.Next
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
