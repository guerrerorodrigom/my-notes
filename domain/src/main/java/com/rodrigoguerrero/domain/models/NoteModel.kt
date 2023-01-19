package com.rodrigoguerrero.domain.models

data class NoteModel(
    val id: Int,
    val title: String? = null,
    val content: String? = null,
    val created: String,
    val modified: String,
    val isPinned: Boolean = false
) {
    fun isEmpty() = title.isNullOrEmpty() && content.isNullOrEmpty()

    override fun equals(other: Any?): Boolean {
        return other is NoteModel &&
                other.isPinned == this.isPinned &&
                other.content == this.content &&
                other.title == this.title
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + isPinned.hashCode()
        return result
    }
}
