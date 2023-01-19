package com.rodrigoguerrero.mynotes.models.statemodels

import android.content.Context
import com.rodrigoguerrero.domain.models.NoteModel
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.utils.DateUtils.formatTimeUnitWithTwoDigits
import com.rodrigoguerrero.mynotes.utils.DateUtils.isBeforeThisYear
import com.rodrigoguerrero.mynotes.utils.DateUtils.isToday
import com.rodrigoguerrero.mynotes.utils.DateUtils.isYesterday
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import kotlinx.datetime.toLocalDateTime

sealed class EditNoteState {
    data class ContentState(
        val title: String,
        val content: String,
        val editedDate: String,
        val createdDate: String,
        val id: Int
    ) : EditNoteState()

    object LoadingState : EditNoteState()
    object ErrorState : EditNoteState()
}

fun EditNoteState.ContentState.toDomainModel() = NoteModel(
    id = id,
    title = title,
    content = content,
    modified = editedDate,
    created = createdDate
)

fun MutableStateFlow<EditNoteState>.updateWithNote(note: NoteModel) {
    update {
        with(note) {
            EditNoteState.ContentState(
                title = title.orEmpty(),
                content = content.orEmpty(),
                editedDate = modified,
                createdDate = created,
                id = id
            )
        }
    }
}

fun formatDate(date: String, context: Context): String {
    return try {
        val instant = Instant.parse(date)
        with(instant.toLocalDateTime(currentSystemDefault())) {
            return when {
                isBeforeThisYear(date) -> context.getString(
                    R.string.edited_more_than_a_year_ago,
                    month.name.lowercase().replaceFirstChar { it.uppercase() }.substring(0, 3),
                    dayOfMonth.toString(),
                    year.toString()
                )
                isToday(date) -> context.getString(
                    R.string.edited_today,
                    time.hour.toString(),
                    formatTimeUnitWithTwoDigits(time.minute)
                )
                isYesterday(date) -> context.getString(
                    R.string.edited_yesterday,
                    time.hour.toString(),
                    formatTimeUnitWithTwoDigits(time.minute)
                )
                else -> context.getString(
                    R.string.edited_month_day,
                    month.name.lowercase().replaceFirstChar { it.uppercase() }.substring(0, 3),
                    dayOfMonth.toString()
                )
            }
        }
    } catch (exception: Exception) {
        ""
    }
}

fun MutableStateFlow<EditNoteState>.updateTitle(title: String) {
    update {
        if (it is EditNoteState.ContentState) {
            it.copy(title = title)
        } else {
            it
        }
    }
}

fun MutableStateFlow<EditNoteState>.updateContent(value: String) {
    update {
        if (it is EditNoteState.ContentState) {
            it.copy(content = value)
        } else {
            it
        }
    }
}
