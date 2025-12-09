package com.turingalan.pokemon.ui.creation


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turingalan.pokemon.data.model.Pokemon
import  com.turingalan.pokemon.data.repository.PokemonRepository
import  com.turingalan.pokemon.ui.detail.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonCreationViewModel@Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterRepository : PokemonRepository
): ViewModel() {
    private val _uiState : MutableStateFlow<DetailUiState> =
        MutableStateFlow(DetailUiState())
    val uiState : StateFlow<DetailUiState>
        get()= _uiState.asStateFlow()
    init {

    }
    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
    }
    fun create(){
        viewModelScope.launch {
            val newId = getLastId()
            val pokemon = Pokemon(
                id = newId,
                name = name,
                sprite = "sprite_1.png",
                artwork = "artwork_1.png",
            )
            characterRepository.insert(pokemon)
        }
    }
    private suspend fun getLastId(): Long {
        val characters = characterRepository.observe().first().getOrNull()
        val maxId = characters?.maxOfOrNull { it.id } ?: 0L
        return maxId + 1
    }
    var isError =false
    var name by mutableStateOf("")
}

