package com.rodrigoguerrero.mynotes.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.ViewAgenda
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.ui.theme.MyNotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    onMenuClicked: () -> Unit
) {
    var value by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    var listView by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = { value = it },
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) { onMenuClicked() }
            )
        },
        shape = MyNotesTheme.shapes.extraLarge,
        trailingIcon = {
            Icon(
                imageVector = if (listView) Icons.Default.GridView else Icons.Outlined.ViewAgenda,
                contentDescription = null,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) { listView = !listView }
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        placeholder = { Text(text = stringResource(id = R.string.search_hint)) }
    )
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun PreviewSearchField() {
    MyNotesTheme {
        Surface(modifier = Modifier.padding(MyNotesTheme.padding.s)) {
            SearchField { }
        }
    }
}
