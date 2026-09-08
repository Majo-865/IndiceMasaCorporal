package com.example.indicemasacorporal


import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.res.painterResource

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.indicemasacorporal.ui.theme.IndiceMasaCorporalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf("") }
    var focusRequester by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
            painter = painterResource(id = R.drawable.fondo),
            contentScale = ContentScale.Crop,
            alpha = 0.4f,
        )
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo"
        )
        Text(
            text = ("Aplicativo de Masa Corporal"),
            fontSize = 20.sp,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Ingrese su peso en Kilogramos") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )

        OutlinedTextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("Ingrese su estatura en metros") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                val a = peso.toDoubleOrNull()
                val b = altura.toDoubleOrNull()

                if (a != null && b != null) {
                    val imc = a / (b * b)

                    estado = when {
                        imc < 18.5 -> "Bajo peso"
                        imc < 30 -> "Obesidad"
                        else -> "Obesidad morbidad"
                    }

                    peso = ""
                    altura = ""
                }


            }
        ) {
            Text(
                text = "Calcular",
                fontSize = 15.sp,
                color = Color.LightGray,
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Su IMC es $imc Lo que significa $estado "
        )


    }
}

@Composable
@Preview(showBackground = true)
fun Vista(){
    App()
}