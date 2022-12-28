package com.rodrigoguerrero.mynotes.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.outlined.Brush
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onCheckboxClicked: () -> Unit,
    onDrawingClicked: () -> Unit,
    onMicrophoneClicked: () -> Unit,
    onImageClicked: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MyNotesTheme.color.surfaceVariant)
            .padding(MyNotesTheme.padding.m),
        horizontalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.l)
    ) {
        Icon(
            imageVector = Icons.Outlined.CheckBox,
            contentDescription = null,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = interactionSource
            ) { onCheckboxClicked() }
        )
        Icon(
            imageVector = Icons.Outlined.Brush,
            contentDescription = null,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = interactionSource
            ) { onDrawingClicked() }
        )
        Icon(
            imageVector = Icons.Outlined.Mic,
            contentDescription = null,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = interactionSource
            ) { onMicrophoneClicked() }
        )
        Icon(
            imageVector = Icons.Outlined.Image,
            contentDescription = null,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = interactionSource
            ) { onImageClicked() }
        )
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewBottomBar() {
    MyNotesTheme {
        Surface(modifier = Modifier.padding(MyNotesTheme.padding.s)) {
            BottomBar(
                onCheckboxClicked = { },
                onDrawingClicked = { },
                onMicrophoneClicked = { },
                onImageClicked = { }
            )
        }
    }
}
