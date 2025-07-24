package com.sahilm.wtpdf_android.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme
import com.sahilm.wtpdf_android.ui.theme.displayFontFamily

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonText: String,
    buttonTextSize: TextUnit = 16.sp,
    buttonTextFontFamily: FontFamily = displayFontFamily,
    buttonIcon: Int,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.primaryContainer,
    borderShape: Shape = RoundedCornerShape(8.dp),
    spacerPadding: Dp = 6.dp,
    contentColor: Color = MaterialTheme.colorScheme.primary
) {
    WTpdFandroidTheme {
        OutlinedButton(
            modifier = modifier
                .fillMaxWidth()
                .border(borderWidth, borderColor, borderShape),
            onClick = onClick,
            shape = borderShape
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = modifier
                        .size(28.dp),
                    painter = painterResource(buttonIcon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    )
                Spacer(
                    modifier = Modifier.padding(spacerPadding)
                )
                Text(
                    text = buttonText,
                    fontFamily = buttonTextFontFamily,
                    fontSize = buttonTextSize,
                    color = contentColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondaryButtonPreview(modifier: Modifier = Modifier) {
    SecondaryButton(
        modifier = modifier,
        onClick = {},
        buttonText = "Get started with Google",
        buttonIcon = R.drawable.icon_google_logo
    )
}