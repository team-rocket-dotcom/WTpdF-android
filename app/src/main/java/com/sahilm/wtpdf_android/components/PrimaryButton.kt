package com.sahilm.wtpdf_android.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme
import com.sahilm.wtpdf_android.ui.theme.bodyFontFamily

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    disabledContainerColor: Color = MaterialTheme.colorScheme.surface,
    disabledContentColor: Color = MaterialTheme.colorScheme.onSurface,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(8.dp),
    elevation: Dp = 8.dp,
    buttonContentText: String,
    buttonContentSize: TextUnit = 18.sp,
    buttonContentFontFamily: FontFamily = bodyFontFamily,
    buttonContentPadding: Dp = 12.dp
    ) {
    WTpdFandroidTheme {
        Button(
            modifier = modifier,
            onClick = onClick,
            colors = ButtonColors(
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContentColor = disabledContentColor,
                disabledContainerColor = disabledContainerColor
            ),
            enabled = enabled,
            shape = shape,
           elevation = ButtonDefaults.elevatedButtonElevation(elevation),
            contentPadding = PaddingValues(buttonContentPadding)
        ) {
            Text(
                text = buttonContentText,
                fontSize = buttonContentSize,
                fontFamily = buttonContentFontFamily
            )
        }
    }
}