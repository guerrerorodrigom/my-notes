package com.rodrigoguerrero.mynotes.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.FormatColorReset
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme

@Composable
fun ColorSelector(
    color: Color,
    isSelected: Boolean,
    isEmpty: Boolean,
    onColorClicked: (Color) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .padding(MyNotesTheme.padding.s)
    ) {
        var modifier = Modifier
            .size(dimensionResource(id = R.dimen.color_selector_size))
            .clip(CircleShape)
            .background(color)

        if (isSelected) {
            modifier = modifier
                .border(
                    border = BorderStroke(
                        width = dimensionResource(id = R.dimen.color_selector_border_width),
                        color = MyNotesTheme.color.primary
                    ),
                    shape = CircleShape
                )
        }

        IconButton(
            modifier = modifier,
            onClick = { onColorClicked(color) }
        ) {
            val icon = when {
                isEmpty -> Icons.Outlined.FormatColorReset
                isSelected -> Icons.Filled.Check
                else -> null
            }
            icon?.let {
                Icon(imageVector = it, contentDescription = null, tint = MyNotesTheme.color.primary)
            }
        }
    }
}
