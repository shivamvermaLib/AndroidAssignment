package com.shivam.androidassignment.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shivam.androidassignment.SubItem

@Composable
fun ListItem(subItem: SubItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 6.dp)
    ) {
        AsyncImage(
            model = subItem.imageUrl,
            contentDescription = subItem.title,
            modifier = Modifier.size(60.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = subItem.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = subItem.desc, style = MaterialTheme.typography.bodyMedium)
        }
    }
}