package ru.chuikov.program.common.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow

//medium.com/mobile-app-development-publication/setup-a-self-modifiable-list-of-data-in-jetpack-compose-2057c1ae6109
@Composable
fun inputListOfVariants(snapshotStateList: SnapshotStateList<Audience>,
                        modifierColumn: Modifier = Modifier,
){
    //val audiences = mutableStateListOf<Audience>()
    val _itemsListFlow = MutableStateFlow(snapshotStateList)

    Column(
        modifier = Modifier ,
        //verticalArrangement = Arrangement.SpaceBetween
        ) {

        Row(modifier = Modifier
        ) {
            Text("Название аудитории", modifier = Modifier.weight(1f).padding(10.dp))
            Text("Количество мест", modifier = Modifier.weight(1f).padding(10.dp))
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            itemsIndexed(items= _itemsListFlow.value,){
                    index, item ->
                Row {
                    TextField(
                        value = item.cabinet,
                        onValueChange = {
                                text ->
                            snapshotStateList[index] = snapshotStateList[index].copy(cabinet = text)

                        },
                        modifier = Modifier.weight(1f).padding(5.dp,2.dp),
                    )
                    NumberInputField(item.station,Modifier.weight(1f).padding(5.dp,2.dp)){
                        snapshotStateList[index] = snapshotStateList[index].copy(station = it)
                    }
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                modifier = Modifier,
                onClick = {
                    snapshotStateList.add(Audience("A",1))
                }){
                Text("Add")
            }
            Button(
                    modifier = Modifier,
            onClick = {
                snapshotStateList.removeLast()
            }){
            Text("Remove last")
        }
        }

    }

}

data class Audience(
    var cabinet:String,
    var station:Int
)

@Composable
@Preview
fun previewInputListOfVariants(){
    Column {

    }

}