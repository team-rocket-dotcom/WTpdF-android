package com.sahilm.wtpdf_android.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme
import com.sahilm.wtpdf_android.ui.theme.displayFontFamily

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    maxLines: Int = 1,
    textColor: Color,
    textFontWeight: FontWeight = FontWeight.SemiBold,
    textFontFamily: FontFamily = displayFontFamily,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    leadingIconResource: Int? = null,
    trailingIconResource: Int? = R.drawable.icon_cross,
    isVisible: Boolean = false,
) {
    WTpdFandroidTheme {
        TextField(
            modifier = modifier
                .fillMaxWidth(0.95f)
                .padding(4.dp),
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label, style = MaterialTheme.typography.bodyMedium) },
            maxLines = maxLines,
            textStyle = TextStyle(
                color = textColor,
                fontWeight = textFontWeight,
                fontFamily = textFontFamily,
                fontSize = 20.sp
            ),
            keyboardOptions = keyboardOptions,
            leadingIcon = {
                leadingIconResource?.let {
                    Icon(
                        modifier = modifier.size(24.dp),
                        painter = painterResource(
                            leadingIconResource
                        ),
                        contentDescription = null,
                    )
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            trailingIcon = {
                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    trailingIconResource?.let {
                        Icon(
                            modifier = modifier.size(24.dp),
                            painter = painterResource(trailingIconResource),
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PrimaryTextFieldPreview(modifier: Modifier = Modifier) {
    WTpdFandroidTheme {
        var text by remember { mutableStateOf("") }
        InputTextField(
            value = text,
            onValueChange = { text = it },
            label = "Email",
            textColor = MaterialTheme.colorScheme.onSurface,
            leadingIconResource = R.drawable.ic_mail
        )
    }
}