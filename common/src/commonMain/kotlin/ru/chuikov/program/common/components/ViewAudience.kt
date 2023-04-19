package ru.chuikov.program.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.MutableStateFlow
import ru.chuikov.program.common.AudienceGenerated

@Composable
fun ViewAudience(list: SnapshotStateList<AudienceGenerated>){
    val _itemsListFlow = MutableStateFlow(list)
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        itemsIndexed(
            items = _itemsListFlow.value
        ){index, item ->
            Row(

            ) {
                Column {
                    Text("Аудитория: ${item.name}")
                    Row {
                        item.station.forEachIndexed { index, b ->
                            Text("${b.number+1}")
                            Checkbox(b.free,onCheckedChange = null)
                        }
                    }

                }

            }
        }
    }
}