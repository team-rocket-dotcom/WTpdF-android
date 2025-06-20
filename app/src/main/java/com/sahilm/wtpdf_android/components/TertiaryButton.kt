package com.sahilm.wtpdf_android.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TertiaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonContentText: String,
    buttonContentTextSize: TextUnit = 24.sp,
    buttonContentTextColor: Color = MaterialTheme.colorScheme.primary,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = buttonContentTextColor,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        )
    ) {
        Text(
            text = buttonContentText,
            fontSize = buttonContentTextSize
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TertiaryButtonPreview() {
    TertiaryButton(
        onClick = {},
        buttonContentText = "Sign Up"
    )
}