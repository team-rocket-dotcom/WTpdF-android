package com.sahilm.wtpdf_android.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun HorizontalOrDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(0.95f).padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(modifier = Modifier.weight(0.5f),color = MaterialTheme.colorScheme.outlineVariant)
        Text(text = "Or", color = MaterialTheme.colorScheme.outlineVariant)
        HorizontalDivider(modifier = Modifier.weight(0.5f),color = MaterialTheme.colorScheme.outlineVariant)
    }
}