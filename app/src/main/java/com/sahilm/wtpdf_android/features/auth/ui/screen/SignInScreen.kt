package com.sahilm.wtpdf_android.features.auth.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.core.components.InputTextField
import com.sahilm.wtpdf_android.core.components.PasswordTextField
import com.sahilm.wtpdf_android.core.components.PrimaryButton
import com.sahilm.wtpdf_android.core.components.TertiaryButton
import com.sahilm.wtpdf_android.features.auth.ui.viewmodel.SignInValidationEvent
import com.sahilm.wtpdf_android.features.auth.ui.viewmodel.SignInViewModel
import com.sahilm.wtpdf_android.features.auth.ui.viewmodel.SignUpValidationEvent
import com.sahilm.wtpdf_android.features.auth.util.ResultState
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme
import kotlin.math.sign

@Composable
fun SignInScreen(
   signInViewModel: SignInViewModel = hiltViewModel(),
   navigateAuthScreen: () -> Unit,
   navigateSignUpScreen: () -> Unit
) {

    val uiState by signInViewModel.uiState.collectAsState()

    WTpdFandroidTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .padding(4.dp, top = 26.dp)
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AuthTopBar(
                        onClick = { navigateAuthScreen() },
                        topText = "Sign In"
                    )

                    Text(
                        text = "Please enter your email & password to sign in",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.padding(16.dp))


                    InputTextField(
                        modifier = Modifier.fillMaxWidth(0.95f),
                        placeholder = stringResource(id = R.string.strEmail),
                        value = signInViewModel.formState.email,
                        onValueChange = {
                            signInViewModel.onEvent(SignInValidationEvent.EmailChanged(it))
                        },
                        leadingIconResource = R.drawable.ic_mail,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        singleLine = true,
                        trailingIconResource = {
                            if (signInViewModel.formState.email.isNotBlank()) {
                                IconButton(
                                    onClick = {
                                        signInViewModel.onEvent(
                                            SignInValidationEvent.EmailChanged(
                                                ""
                                            )
                                        )
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_cross),
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        },
                    )

                    Spacer(modifier = Modifier.padding(8.dp))

                    PasswordTextField(
                        modifier = Modifier.fillMaxWidth(0.95f),
                        placeholder = stringResource(id = R.string.strPassword),
                        value = signInViewModel.formState.password,
                        onValueChange = {
                            signInViewModel.onEvent(SignInValidationEvent.PasswordChanged(it))
                        },
                        imeAction = ImeAction.Done,
                        leadingIcon = R.drawable.icon_lock_closed,
                        trailingIconResource = {
                            IconButton(
                                onClick = {
                                    signInViewModel.onEvent(SignInValidationEvent.VisiblePassword(!(signInViewModel.formState.isVisiblePassword)))
                                }
                            ) {
                                Icon(
                                    painter = if (signInViewModel.formState.isVisiblePassword) painterResource(
                                        id = R.drawable.icon_visibility
                                    ) else painterResource(
                                        id = R.drawable.icon_visibility_off
                                    ),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        },
                        visualTransformation =
                            if (signInViewModel.formState.isVisiblePassword) VisualTransformation.None else PasswordVisualTransformation(),
                        errorMessage = signInViewModel.formState.passwordError
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = if (uiState.isSignInSuccessful == false && uiState.isError == true) "Either of the credentials are invalid" else "",
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(0.95f),
                        onClick = {
                            Log.d("SignInScreen", "SignInScreen: SignIn Button clicked")
                            signInViewModel.onSignInClicked()
                        },
                        buttonContentText = "Sign In",
                        enabled = if (signInViewModel.formState.password.isBlank()  ||
                            signInViewModel.formState.email.isBlank() ) {
                            false
                        } else {
                            true
                        }
                    )
                    Spacer(modifier = Modifier.padding(24.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Don't have an account?")
                        TertiaryButton(
                            onClick = { navigateSignUpScreen() },
                            buttonContentText = "Sign Up",
                            buttonContentTextSize = 24.sp
                        )
                    }
                }
            }
            if (uiState.isLoading) {
                SignInLoader()
            }
        }
    }
}

@Composable
fun SignInLoader() {
    Log.d("SignUpScreen", "SignUpLoader: this is called")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 8.dp,
            modifier = Modifier.padding(32.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(78.dp)
                        .rotate(0.85f),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 6.dp
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Signing you in...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


