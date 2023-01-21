package com.rodrigoguerrero.mynotes.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.FormatColorReset
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.models.uimodels.NoteColor
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme

@Composable
fun BottomSheetColorSelector(
    modifier: Modifier = Modifier,
    selectedColor: Color?,
    onColorSelected: (Color?) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(selectedColor ?: MyNotesTheme.color.surface)
            .padding(vertical = MyNotesTheme.padding.m)
    ) {
        Text(
            text = stringResource(R.string.bottom_sheet_color),
            modifier = Modifier.padding(
                bottom = MyNotesTheme.padding.m,
                start = MyNotesTheme.padding.m
            ),
            color = MyNotesTheme.color.onSurface
        )
        ColorSelectorRow(selectedColor, onColorSelected)
    }
}

@Composable
private fun ColorSelectorRow(
    selectedColor: Color?,
    onColorSelected: (Color?) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = MyNotesTheme.padding.m),
        horizontalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.m)
    ) {
        item {
            ColorSelector(
                color = Color.Transparent,
                isSelected = Color.Transparent == selectedColor,
                onColorClicked = { onColorSelected(null) },
                isEmpty = Color.Transparent != selectedColor
            )
        }

        items(NoteColor.values()) { noteColor ->
            ColorSelector(
                color = noteColor.color,
                isSelected = noteColor.color == selectedColor,
                onColorClicked = onColorSelected,
                isEmpty = false
            )
        }
    }
}

@Composable
private fun ColorSelector(
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

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewColorSelector() {
    ColorSelector(Color.Blue, isSelected = false, isEmpty = false) {}
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewColorSelectorSelected() {
    ColorSelector(Color.Green, isSelected = true, isEmpty = false) {}
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewColorRow() {
    ColorSelectorRow(onColorSelected = {}, selectedColor = Color.Transparent)
}
