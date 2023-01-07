package com.rodrigoguerrero.mynotes.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.mynotes.R
import com.rodrigoguerrero.mynotes.models.uimodels.DrawerNavigationItem
import com.rodrigoguerrero.mynotes.navigation.MainScreenDestinations
import com.rodrigoguerrero.mynotes.theme.MyNotesTheme

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    item: DrawerNavigationItem,
    isSelected: Boolean,
    onClicked: (MainScreenDestinations) -> Unit
) {
    val (backgroundColor, shape) = if (isSelected) {
        MyNotesTheme.color.primary.copy(alpha = 0.5f) to RoundedCornerShape(
            topEnd = dimensionResource(id = R.dimen.drawer_item_radius),
            bottomEnd = dimensionResource(id = R.dimen.drawer_item_radius)
        )
    } else {
        Color.Transparent to RectangleShape
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = shape)
            .clickable { onClicked(item.destination) }
            .padding(vertical = MyNotesTheme.padding.m, horizontal = MyNotesTheme.padding.ml),
        horizontalArrangement = Arrangement.spacedBy(MyNotesTheme.padding.m)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            tint = MyNotesTheme.color.onSurface
        )
        Text(
            text = stringResource(id = item.title),
            color = MyNotesTheme.color.onSurface
        )
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewDrawerItem() {
    MyNotesTheme {
        DrawerItem(item = DrawerNavigationItem.Notes, isSelected = false) { }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
private fun PreviewSelectedDrawerItem() {
    MyNotesTheme {
        DrawerItem(item = DrawerNavigationItem.Notes, isSelected = true) { }
    }
}
