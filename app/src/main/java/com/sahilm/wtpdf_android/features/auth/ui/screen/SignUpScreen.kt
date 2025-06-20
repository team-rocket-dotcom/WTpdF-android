package com.sahilm.wtpdf_android.features.auth.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.components.InputTextField
import com.sahilm.wtpdf_android.components.PasswordTextField
import com.sahilm.wtpdf_android.components.PrimaryButton
import com.sahilm.wtpdf_android.features.auth.utils.FieldInput
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme

@Preview(showBackground = true)
@Composable
fun SignUpScreen() {
    WTpdFandroidTheme {

        var userName by remember { mutableStateOf("") }
        val userNameIsVisible by remember { derivedStateOf { userName.isNotBlank() } }
        var email by remember { mutableStateOf("") }
        val emailIsVisible by remember { derivedStateOf { email.isNotBlank() } }
        var password by remember { mutableStateOf("") }
        val passwordIsVisible by remember { derivedStateOf { password.isNotBlank() } }
        var confirmPassword by remember { mutableStateOf("") }
        val confirmPasswordIsVisible by remember { derivedStateOf { confirmPassword.isNotBlank() } }

        Surface(
            color = MaterialTheme.colorScheme.background
        ) {

            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AuthTopBar(
                    onClick = {},
                    topText = "Sign Up"
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Box {
                    Image(
                        painter = painterResource(R.drawable.icon_default_pfp),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(98.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.surfaceVariant,
                                RoundedCornerShape(12.dp)
                            ),
                        contentDescription = null
                    )
                    Box(
                        modifier = Modifier
                            .offset(x = 78.dp, y = 68.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer, CircleShape)
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    MaterialTheme.colorScheme.onPrimaryContainer
                                ), shape = RoundedCornerShape(12.dp)
                            )
                            .size(34.dp),
                        // need to add a click listener
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.Center),
                            painter = painterResource(R.drawable.icon_add_image),
                            contentDescription = null,
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))
                InputTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    label = { Text("User Name") },
                    leadingIconResource = R.drawable.icon_user,
                    isVisible = userNameIsVisible
                )
                InputTextField(
                    value = email,
                    onValueChange = { email = it },
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    label = "Email",
                    leadingIconResource = R.drawable.ic_mail,
                    isVisible = emailIsVisible,
                )
                PasswordTextField(
                    value = password,
                    onValueChange = {password = it},
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    label = { Text("Password") },
                    leadingIconResource = R.drawable.icon_lock,
                    isVisible = passwordIsVisible
                )
                PasswordTextField(
                    value = confirmPassword,
                    onValueChange = {confirmPassword = it},
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    label = { Text("Confirm Password") },
                    leadingIconResource = R.drawable.icon_lock_closed,
                    isVisible = confirmPasswordIsVisible
                )
                Spacer(modifier = Modifier.padding(18.dp))
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(0.95f),
                    onClick = {},
                    buttonContentText = "Sign Up"
                )
            }
        }
    }
}