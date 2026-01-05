package com.rgb.samplejetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = "Home")
//                }, colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color.Yellow
//                ),
//                actions = {
//                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
//                }
//            )
//        },
//        bottomBar = { BottomAppBar(containerColor = Color.Green) { } }
//
//    ) { paddingValues ->
//        Column(modifier = Modifier.padding(paddingValues = paddingValues).fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally) {
//            Text("Marcos", fontSize = 20.sp)
//            Text("Maria", fontSize = 20.sp)
//            Text("João", fontSize = 20.sp)
//        }
//    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Green),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Box(
//            modifier = Modifier
//                .size(200.dp)
//                .background(Color.Red),
//            contentAlignment = Alignment.Center
//        ) {
//            Box(Modifier.size(100.dp).background(Color.Blue),
//                contentAlignment = Alignment.Center){
//
//            }
//        }
//    }

//    var name by remember { mutableStateOf("Rachel Lima") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
//
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Button(onClick = {
//            name = "Usuária Rachel Lima autenticado com sucesso"
//        }
//        ) {
//            Text("Login")
//        }
//        Text(name)

        Text(text = "Login Page", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            modifier = Modifier.padding(30.dp),
            value = email,
            onValueChange = {
                email = it
            },
            placeholder = {
                Text("E-mail")
            },
            maxLines = 1
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text("Password")
            },
            maxLines = 1
        )
        Button(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(context, "Login efetuado com sucesso", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text("Login")
        }
    }

}

@Preview(name = "HomePreview")
@Composable
fun HomePreview() {
    Home()
}
