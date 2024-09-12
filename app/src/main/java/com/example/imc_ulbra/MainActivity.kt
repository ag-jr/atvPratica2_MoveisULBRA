package com.example.imc_ulbra

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc_ulbra.ui.theme.Imc_ulbraTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Imc_ulbraTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()

                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun App() {

    var peso  by remember {
        mutableStateOf("")
    }

    var altura by remember {
        mutableStateOf("")
    }

    var nome by remember {
        mutableStateOf("")
    }

    var mensagem by remember { mutableStateOf("Resultado aparecerá aqui.") }



    fun operador(peso: String, altura: String): String {
        val nPeso = peso.toDouble()

        val nAltura = altura.toDouble()/100

        val calculoIMC = nPeso / (nAltura * nAltura)

         return when (calculoIMC) {
             in 0.0..18.4 -> {
                 "Abaixo do peso."
             }
             in 18.5..24.9 -> {
                 "Peso normal."
             }
             in 25.0..29.9 -> {
                 "Acima do peso."
             }
             in 30.0..34.9 -> {
                 "Obesidade grau I."
             }
             in 35.0..39.9 -> {
                 "Obesidade grau II."
             }
             else -> {
                 "Obesidade grau III."
             }
         }
    }



    //outer
    Column(
        Modifier
            .background(color = Color(0xFF21B327))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(32.dp))
        Text(text = "Calculadora de IMC",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        //inner
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "Nome:", style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            )

            TextField(
                value = nome,
                onValueChange = { novoNome ->
                    nome = novoNome
                },
                label = {
                    Text(text = "Qual seu nome?")
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))


            Text(text = "Peso:", style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ))

            TextField(
                value = peso,
                onValueChange = { novoPeso ->
                    peso = novoPeso
                },
                label = {
                    Text(text = "Digite seu peso em KG")
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "Altura:", style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ))

            TextField(
                value = altura,
                onValueChange = { novaAltura ->
                    altura = novaAltura
                },
                label = {
                    Text(text = "Digite sua altura em Metros")
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))

            Button(onClick = {
                if(peso.isNotBlank() && altura.isNotBlank() && nome.isNotBlank()) {
                    val nPeso = peso.toDouble()
                    val nAltura = altura.toDouble()/100
                    val calculoIMC = nPeso / (nAltura * nAltura)
                    val fcalculoIMC = String.format("%.2f", calculoIMC)
                    val faixa = operador(peso, altura)
                    mensagem = "Olá $nome,\n o resultado do seu cálculo de Massa Corporal " +
                            "é: $fcalculoIMC,\n'$faixa'"
                }
            }) {
                Text(text = "Calcular")
            }


            Text(text = "Resultado:", style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ))

            Spacer(modifier = Modifier.size(16.dp))
            Text(text = mensagem, style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ))


        }

    }
}







@Preview
@Composable
fun AppPreview() {
    Imc_ulbraTheme {
        App()
    }
}

// formula IMC = peso/(altura*altura)
//peso em KG e altura em metros