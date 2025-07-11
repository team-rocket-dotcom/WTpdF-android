package com.sahilm.wtpdf_android.features.auth.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.sahilm.wtpdf_android.features.auth.util.UiText
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme

@Composable
fun SignInScreen(
   signInViewModel: SignInViewModel = hiltViewModel()
) {
    WTpdFandroidTheme {
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
                    onClick = {},
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
                    value = "",
                    onValueChange = {
                        signInViewModel.onEvent(SignInValidationEvent.EmailChanged(it))
                    },
                    leadingIconResource = R.drawable.ic_mail,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    singleLine = true,
                    trailingIconResource = {
                    }
                )

                Spacer(modifier = Modifier.padding(8.dp))

                PasswordTextField(
                    modifier = Modifier.fillMaxWidth(0.95f),
                    placeholder = stringResource(id = R.string.strConfirmPassword),
                    value = "",
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
                    isError = signInViewModel.formState.passwordError != null,
                    errorMessage = signInViewModel.formState.passwordError,
                    visualTransformation =
                        if (signInViewModel.formState.isVisiblePassword) VisualTransformation.None else PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.padding(18.dp))
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(0.95f),
                    onClick = {},
                    buttonContentText = "Sign In",
                    enabled = if (signInViewModel.formState.passwordError != null && signInViewModel.formState.emailError != null) {
                        false
                    } else {
                        true
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(modifier: Modifier = Modifier) {
    WTpdFandroidTheme { SignInScreen() }
}