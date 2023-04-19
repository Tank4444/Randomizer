package ru.chuikov.program.common.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.Modifier

@Composable
fun NumberInputField(int: Int, modifier: Modifier = Modifier, onValueChange:(Int)->Unit){
    var text = remember { mutableStateOf(int) }
    TextField(
        value = text.value.toString(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = modifier,
        onValueChange = {
            text.value = when(it){
                "" -> 0
                else -> try {
                        it.toInt()
                        }catch (e:NumberFormatException){
                            text.value
                        }


            }
            onValueChange(text.value)
        },

    )
}
