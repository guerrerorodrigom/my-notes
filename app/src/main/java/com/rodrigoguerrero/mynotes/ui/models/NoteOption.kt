package com.rodrigoguerrero.mynotes.ui.models

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Brush
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.outlined.Share
import androidx.compose.ui.graphics.vector.ImageVector
import com.rodrigoguerrero.mynotes.R

sealed class NoteOption(val icon: ImageVector, @StringRes val text: Int) {
    object TakePhoto : NoteOption(icon = Icons.Outlined.PhotoCamera, text = R.string.option_photo)
    object AddImage : NoteOption(icon = Icons.Outlined.Image, text = R.string.option_image)
    object Drawing : NoteOption(icon = Icons.Outlined.Brush, text = R.string.option_drawing)
    object Recording : NoteOption(icon = Icons.Outlined.Mic, text = R.string.option_recording)
    object Checkboxes : NoteOption(
        icon = Icons.Outlined.CheckBox,
        text = R.string.option_checkboxes
    )

    object Delete : NoteOption(icon = Icons.Outlined.Delete, text = R.string.option_delete)
    object Copy : NoteOption(icon = Icons.Outlined.ContentCopy, text = R.string.option_copy)
    object Send : NoteOption(icon = Icons.Outlined.Share, text = R.string.option_send)
    object Collaborator : NoteOption(
        icon = Icons.Outlined.PersonAdd,
        text = R.string.option_collaborator
    )

    object Labels : NoteOption(icon = Icons.Outlined.Label, text = R.string.option_labels)
}

val noteMenuOptions = listOf(
    NoteOption.TakePhoto,
    NoteOption.AddImage,
    NoteOption.Drawing,
    NoteOption.Recording,
    NoteOption.Checkboxes
)

val moreMenuOptions = listOf(
    NoteOption.Delete,
    NoteOption.Copy,
    NoteOption.Send,
    NoteOption.Collaborator,
    NoteOption.Labels,
)
