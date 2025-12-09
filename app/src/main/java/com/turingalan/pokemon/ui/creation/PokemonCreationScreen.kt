package com.turingalan.pokemon.ui.creation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun PokemonCreationScreen(
    modifier : Modifier = Modifier,
    viewModel : PokemonCreationViewModel = hiltViewModel(),
    onNavegationBack:()->Unit
){
    Card(
        modifier = Modifier.padding(top = 80.dp, start = 16.dp, end = 16.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                value = viewModel.name,
                singleLine = true,
                isError = viewModel.isError,
                label = { Text("Nombre") },
                onValueChange = { viewModel.name = it }

            )

        }
        Row(modifier = Modifier.padding(8.dp)){
            Button(
                onClick={
                    viewModel.create()
                    onNavegationBack()
                },
            ){
                Text("Crear")
            }
            Button(
                onClick={
                    onNavegationBack()
                },
            ){
                Text("Cancelar")
            }
        }
    }
}