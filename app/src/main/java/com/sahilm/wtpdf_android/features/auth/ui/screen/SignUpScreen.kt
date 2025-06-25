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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.components.InputTextField
import com.sahilm.wtpdf_android.components.PasswordTextField
import com.sahilm.wtpdf_android.components.PrimaryButton
import com.sahilm.wtpdf_android.features.auth.ui.viewmodel.ValidationEvent
import com.sahilm.wtpdf_android.features.auth.ui.viewmodel.ValidationViewModel
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@Preview(showBackground = true)
@Composable
fun SignUpScreen() {
    WTpdFandroidTheme {

        val validationViewModel: ValidationViewModel = viewModel()

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
                    modifier = Modifier.fillMaxWidth(0.95f),
                    placeholder = stringResource(id = R.string.strUsername),
                    value = validationViewModel.formState.userName,
                    onValueChange = {
                        validationViewModel.onEvent(ValidationEvent.UsernameChanged(it))
                    },
                    leadingIconResource = R.drawable.icon_user,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    singleLine = true,
                    trailingIconResource = {
                        if (validationViewModel.formState.userName.isNotBlank()) {
                            IconButton(
                                onClick = {
                                    validationViewModel.onEvent(ValidationEvent.UsernameChanged(""))
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
                    value = validationViewModel.formState.email,
                    onValueChange = {
                        validationViewModel.onEvent(ValidationEvent.EmailChanged(it))
                    },
                    leadingIconResource = R.drawable.ic_mail,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    singleLine = true,
                    isError = validationViewModel.formState.emailError != null,
                    errorMessage = validationViewModel.formState.emailError,
                    trailingIconResource = {
                        if (validationViewModel.formState.email.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    validationViewModel.onEvent(ValidationEvent.EmailChanged(""))
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
                    value = validationViewModel.formState.password,
                    onValueChange = {
                        validationViewModel.onEvent(ValidationEvent.PasswordChanged(it))
                    },
                    imeAction = ImeAction.Next,
                    leadingIcon = R.drawable.icon_lock,
                    trailingIconResource = {
                        IconButton(
                            onClick = {
                                validationViewModel.onEvent(ValidationEvent.VisiblePassword(!(validationViewModel.formState.isVisiblePassword)))
                            }
                        ) {
                            Icon(
                                painter = if (validationViewModel.formState.isVisiblePassword) painterResource(
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
                    isError = validationViewModel.formState.passwordError != null,
                    errorMessage = validationViewModel.formState.passwordError
                )
                PasswordTextField(
                    modifier = Modifier.fillMaxWidth(0.95f),
                    placeholder = stringResource(id = R.string.strConfirmPassword),
                    value = validationViewModel.formState.confirmPassword,
                    onValueChange = {
                        validationViewModel.onEvent(ValidationEvent.ConfirmPasswordChanged(it))
                    },
                    imeAction = ImeAction.Done,
                    leadingIcon = R.drawable.icon_lock_closed,
                    trailingIconResource = {
                        IconButton(
                            onClick = {
                                validationViewModel.onEvent(ValidationEvent.VisiblePassword((!validationViewModel.formState.isVisiblePassword)))
                            }
                        ) {
                            Icon(
                                painter = if (validationViewModel.formState.isVisiblePassword) painterResource(
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
                    isError = validationViewModel.formState.confirmPasswordError != null,
                    errorMessage = validationViewModel.formState.confirmPasswordError
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