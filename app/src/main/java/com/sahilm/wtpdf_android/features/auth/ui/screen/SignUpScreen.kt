package com.sahilm.wtpdf_android.features.auth.ui.screen

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.sahilm.wtpdf_android.features.auth.ui.viewmodel.SignUpUiEvent
import com.sahilm.wtpdf_android.features.auth.ui.viewmodel.SignUpViewModel
import com.sahilm.wtpdf_android.features.auth.ui.viewmodel.SignUpValidationEvent
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel()
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
                    onClick = {signUpViewModel.onSignUpEvent(SignUpUiEvent.NavigateToLogin)},
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
                    modifier = Modifier.fillMaxWidth(0.95f),
                    placeholder = stringResource(id = R.string.strUsername),
                    value = signUpViewModel.formState.userName,
                    onValueChange = {
                        signUpViewModel.onEvent(SignUpValidationEvent.UsernameChanged(it))
                    },
                    leadingIconResource = R.drawable.icon_user,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    singleLine = true,
                    trailingIconResource = {
                        if (signUpViewModel.formState.userName.isNotBlank()) {
                            IconButton(
                                onClick = {
                                    signUpViewModel.onEvent(SignUpValidationEvent.UsernameChanged(""))
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_cross),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                )
                InputTextField(
                    modifier = Modifier.fillMaxWidth(0.95f),
                    placeholder = stringResource(id = R.string.strEmail),
                    value = signUpViewModel.formState.email,
                    onValueChange = {
                        signUpViewModel.onEvent(SignUpValidationEvent.EmailChanged(it))
                    },
                    leadingIconResource = R.drawable.ic_mail,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    singleLine = true,
                    isError = signUpViewModel.formState.emailError != null,
                    errorMessage = signUpViewModel.formState.emailError,
                    trailingIconResource = {
                        if (signUpViewModel.formState.email.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    signUpViewModel.onEvent(SignUpValidationEvent.EmailChanged(""))
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_cross),
                                    contentDescription = "Clear email",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                )
                PasswordTextField(
                    modifier = Modifier.fillMaxWidth(0.95f),
                    placeholder = stringResource(id = R.string.strPassword),
                    value = signUpViewModel.formState.password,
                    onValueChange = {
                        signUpViewModel.onEvent(SignUpValidationEvent.PasswordChanged(it))
                    },
                    imeAction = ImeAction.Next,
                    leadingIcon = R.drawable.icon_lock,
                    trailingIconResource = {
                        IconButton(
                            onClick = {
                                signUpViewModel.onEvent(SignUpValidationEvent.VisiblePassword(!(signUpViewModel.formState.isVisiblePassword)))
                            }
                        ) {
                            Icon(
                                painter = if (signUpViewModel.formState.isVisiblePassword) painterResource(
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
                    isError = signUpViewModel.formState.passwordError != null,
                    errorMessage = signUpViewModel.formState.passwordError,
                    visualTransformation =
                        if (signUpViewModel.formState.isVisiblePassword) VisualTransformation.None else PasswordVisualTransformation()
                )
                PasswordTextField(
                    modifier = Modifier.fillMaxWidth(0.95f),
                    placeholder = stringResource(id = R.string.strConfirmPassword),
                    value = signUpViewModel.formState.confirmPassword,
                    onValueChange = {
                        signUpViewModel.onEvent(SignUpValidationEvent.ConfirmPasswordChanged(it))
                    },
                    imeAction = ImeAction.Done,
                    leadingIcon = R.drawable.icon_lock_closed,
                    trailingIconResource = {
                        IconButton(
                            onClick = {
                                signUpViewModel.onEvent(SignUpValidationEvent.VisiblePassword((!signUpViewModel.formState.isVisiblePassword)))
                            }
                        ) {
                            Icon(
                                painter = if (signUpViewModel.formState.isVisiblePassword) painterResource(
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
                    isError = signUpViewModel.formState.confirmPasswordError != null,
                    errorMessage = signUpViewModel.formState.confirmPasswordError,
                    visualTransformation =
                        if (signUpViewModel.formState.isVisiblePassword) VisualTransformation.None else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.padding(18.dp))
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(0.95f),
                    onClick = {},
                    buttonContentText = "Sign Up",
                    enabled = if (
                        signUpViewModel.formState.passwordError != null &&
                        signUpViewModel.formState.emailError != null &&
                        signUpViewModel.formState.confirmPasswordError != null
                        ) {
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
                    Text(text = "Already have an account?")
                    TertiaryButton(
                        onClick = {signUpViewModel.onSignUpClicked()},
                        buttonContentText = "Sign In",
                        buttonContentTextSize = 24.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    WTpdFandroidTheme { SignUpScreen() }
}