package com.rodrigoguerrero.mynotes.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.models.uimodels.Note
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    onCardSelected: (Int) -> Unit,
    onLongPress: (Int) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = modifier
            .combinedClickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onCardSelected(note.id) },
                onLongClick = { onLongPress(note.id) }
            ),
        shape = MyNotesTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.card_elevation)
        ),
        colors = CardDefaults.cardColors(
            containerColor = note.color?.let { Color(it) }
                ?: MyNotesTheme.color.surfaceVariant
        ),
        border = if (note.isSelected) {
            BorderStroke(
                width = dimensionResource(id = R.dimen.card_border_width),
                color = MyNotesTheme.color.onSurfaceVariant
            )
        } else {
            null
        }
    ) {
        Column(
            modifier = Modifier.padding(MyNotesTheme.padding.m),
            verticalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.m)
        ) {
            if (!note.title.isNullOrEmpty()) {
                Text(
                    text = note.title,
                    style = MyNotesTheme.typography.headlineSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (!note.content.isNullOrEmpty()) {
                Text(
                    text = note.content,
                    style = MyNotesTheme.typography.bodyLarge,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (note.isPinned) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Icon(
                        imageVector = Icons.Filled.PushPin,
                        contentDescription = null,
                        modifier = Modifier.size(dimensionResource(id = R.dimen.card_pinned_icon_size)).rotate(45f)
                    )
                }
            }
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewNoteCard() {
    MyNotesTheme {
        NoteCard(
            note = Note(
                title = "Note title",
                content = "Note content",
                id = 1,
                color = Color.Blue.value,
                isPinned = true
            ),
            onCardSelected = { },
            onLongPress = { }
        )
    }
}
