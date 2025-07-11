package com.sahilm.wtpdf_android.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.features.auth.util.UiText
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String = "",
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
    isError: Boolean = false,
    leadingIcon: Int?,
    trailingIconResource: @Composable (() -> Unit)? = null,
    errorMessage: UiText?,
    visualTransformation: VisualTransformation
    ) {
    WTpdFandroidTheme {
        var showPassword by remember { mutableStateOf(false) }
        val interactionSource = remember { MutableInteractionSource() }
        val isFocused by interactionSource.collectIsFocusedAsState()
        val colorBorder = if (isError) MaterialTheme.colorScheme.error else if (isFocused)
            MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        val focusRequester = remember {
            FocusRequester()
        }

        Column {
            BasicTextField(
                value = value,
                onValueChange = {onValueChange(it)},
                textStyle = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface),
                maxLines = 1,
                singleLine = true,
                interactionSource = interactionSource,
                visualTransformation = visualTransformation,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = imeAction,
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = colorBorder
                            )
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .focusRequester(focusRequester)
                            .padding(4.dp)
                    ) {
                        if (leadingIcon != null) {
                            Spacer(modifier = Modifier.padding(4.dp))
                            Icon(painter = painterResource(leadingIcon), contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.padding(6.dp))
                        } else {
                            Spacer(modifier = Modifier.padding(8.dp))
                        }
                        Box(
                            modifier = Modifier
                                .weight(1.0f)
                                .padding(vertical = 16.dp)
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                                )
                            }
                            Box(modifier = Modifier.fillMaxWidth()) {
                                innerTextField()
                            }
                        }
                        if (trailingIconResource != null) {
                            trailingIconResource()
                        } else {
                            Spacer(modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            )
        }
        Text(
            text = if (isError) errorMessage!!.asString() else "",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview(modifier: Modifier = Modifier) {
    WTpdFandroidTheme {
        var text by remember { mutableStateOf("") }
//        mutableStateOfPasswordTextField(
//            value = text,
//            onValueChange = { text = it },
//            label = { Text("email") },
//            textColor = MaterialTheme.colorScheme.onSurface,
//            leadingIconResource = R.drawable.icon_lock
//        )
    }
}