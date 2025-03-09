package com.example.unitconverter

import android.graphics.Color
import android.graphics.fonts.FontFamily
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        unitConverter()
                    }
                }
            }
        }
    }
}

@Composable
fun unitConverter() {
    var inputValue by remember { mutableStateOf("") } // Added state management for text input
    var outputValue by remember { mutableStateOf("") } // Added state management for text input
    var inputUnit by remember { mutableStateOf("Meter") } // Added state management for text input
    var outputUnit by remember { mutableStateOf("Meter") } // Added state management for text input
    var iExpanded by remember { mutableStateOf(false) } // Added state management for text input
    var oExpanded by remember { mutableStateOf(false) } // Added state management for text input
    var conversionFactor = remember { mutableStateOf(1.00) } // Added state management for text input
    var oconversionFactor = remember { mutableStateOf(1.00) }

//    val customTextStyle = androidx.compose.ui.text.TextStyle(
//        fontFamily = FontFamily.Default,
//        fontSize = 16.sp,
//        color = Color.Red
//    )




    fun convertUnits(){
        //?:-elvis operator kind of like ternary operator
        val inputValueDouble= inputValue.toDoubleOrNull() ?:0.0 //if value there use that or use 0.0
        val result =(inputValueDouble * conversionFactor.value*100.0/ oconversionFactor.value).roundToInt()/100.0
        outputValue=result.toString()
    }

    Column (
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Unit Converter",
            style=MaterialTheme.typography.headlineLarge)
        Spacer(modifier=Modifier.height(16.dp))//for spacing between elements dp means density pointer(basically relative height)
        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue=it //updates the state with the latest user input.
                convertUnits()
            //here will go what should happen when value will change
                 },
            label = { Text("Enter value") }
        )
        Spacer(modifier=Modifier.height(16.dp))

        Row {
            // Elements placed next to each other can be added here
            /*
            val context=LocalContext.current //provides the context of the current screen (Activity) where the composable function is running.
            //Toast is pop up message
            //.show is to display
            //Duration should also be given
            Button(onClick = { Toast.makeText(context,"Thanks for clicking",Toast.LENGTH_LONG).show()}) {
                Text("Click Me!")
            }*/ // this was to show how buttons work

            //input Box
            Box{//box arranges and stack composables on top of each other(like drop down menu)
                //input button
                Button(onClick = { /*To Do*/ iExpanded=true}){
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = " Arrow down")//icons for arrow etc.contextDescription is what it will show on hover
                }
                DropdownMenu(expanded = iExpanded , onDismissRequest = {iExpanded=false }) {
                    DropdownMenuItem(
                        text={Text("Meters")},
                        onClick={/*To Do*/
                            iExpanded=false
                            inputUnit="Meters"
                            conversionFactor.value = 1.00 //If You Use mutableStateOf Without by, Then You Need .value:
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text={Text("Centimeters")},
                        onClick={
                            iExpanded=false
                            inputUnit="Centimeters"
                            conversionFactor.value = 0.01 //If You Use mutableStateOf Without by, Then You Need .value:
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text={Text("Millimeters")},
                        onClick={/*To Do*/
                            iExpanded=false
                            inputUnit="Millimeters"
                            conversionFactor.value = 0.001 //If You Use mutableStateOf Without by, Then You Need .value:
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text={Text("Feet")},
                        onClick={/*To Do*/
                            iExpanded=false
                            inputUnit="Feet"
                            conversionFactor.value = 0.3048 //If You Use mutableStateOf Without by, Then You Need .value:
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier=Modifier.width(16.dp))
            //output box
            Box{
                //output button
                Button(onClick = { /*To Do*/ oExpanded=true}){
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = " Arrow down")//icons for arrow etc.contextDescription is what it will show on hover
                }
                DropdownMenu(expanded = oExpanded , onDismissRequest = {oExpanded=false }) {
                    DropdownMenuItem(
                        text={Text("Meters")},
                        onClick={/*To Do*/
                            iExpanded=false
                            outputUnit="Meters"
                            oconversionFactor.value = 1.00 //If You Use mutableStateOf Without by, Then You Need .value:
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text={Text("Centimeters")},
                        onClick={/*To Do*/
                            iExpanded=false
                            outputUnit="Centimeters"
                            oconversionFactor.value = 0.01 //If You Use mutableStateOf Without by, Then You Need .value:
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text={Text("Millimeters")},
                        onClick={/*To Do*/
                            iExpanded=false
                            outputUnit="Millimeters"
                            oconversionFactor.value = 0.001 //If You Use mutableStateOf Without by, Then You Need .value:
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text={Text("Feet")},
                        onClick={/*To Do*/
                            iExpanded=false
                            outputUnit="Feet"
                            oconversionFactor.value = 0.3048 //If You Use mutableStateOf Without by, Then You Need .value:
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier=Modifier.height(16.dp))

        Text("Result: $outputValue $outputUnit ",
            style=MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun unitConverterPreview() {
    UnitConverterTheme {
        unitConverter()
    }
}
