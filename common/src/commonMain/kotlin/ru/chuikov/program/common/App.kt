package ru.chuikov.program.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ru.chuikov.program.common.components.Audience
import ru.chuikov.program.common.components.ViewAudience
import ru.chuikov.program.common.components.inputListOfVariants

@Composable
fun App() {
    val platformName = getPlatformName()
    val inputAudience = remember { mutableStateListOf<Audience>() }
    Scaffold(modifier = Modifier.fillMaxSize(),

        ) {
        Row(modifier = Modifier.fillMaxSize()) {
            //Column input Audience
            Column(modifier = Modifier.weight(1f).fillMaxHeight()) {
                inputListOfVariants(
                    inputAudience,
                )
            }
            val markedAudience = remember { mutableStateListOf<AudienceGenerated>() }
            //Column out list of audience and random
            Column(modifier = Modifier.weight(1f).fillMaxHeight()) {
                //Generate audience and station
                Row(modifier = Modifier.weight(1f)) {



                    Button(onClick = {
                        markedAudience.clear()
                        for (i in inputAudience){
                            markedAudience.add(AudienceGenerated(i.cabinet, MutableList(i.station){
                                Station(it,true)
                            }))
                        }
                    }){ Text("Gen") }

                    ViewAudience(markedAudience)
                }
                val choose = remember { mutableStateOf("Нажми для выбора случайного значения") }
                //Out random cab and station
                Row(modifier = Modifier.weight(1f)) {
                    Text("Нажми для рандома", modifier = Modifier.clickable {
                        //generate random set
                       var randomSet = markedAudience.filter {
                           !it.station.filter { it.free }.isEmpty()
                       }
                        val r = randomSet.random()
                        val r2 = r.station.random()
                        choose.value = "${r.name} - ${r2.number}"
                        //markedAudience.asdasdf
                        TODO()
                    }, textAlign = TextAlign.Center
                    )
                }
            }
            Column(modifier = Modifier.weight(1f).fillMaxHeight()) {

            }
        }

    }
}

data class AudienceGenerated(var name:String, var station:MutableList<Station>)

data class Station(var number:Int, var free:Boolean)