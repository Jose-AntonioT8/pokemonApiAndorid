package com.turingalan.pokemon.ui.update


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.turingalan.pokemon.data.model.Pokemon
import com.turingalan.pokemon.data.repository.PokemonRepository
import com.turingalan.pokemon.ui.detail.DetailUiState
import com.turingalan.pokemon.ui.detail.toDetailUiState
import com.turingalan.pokemon.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


data class DetailUiState(
    val id : Long = 0,
    val name : String = "",
    val sprite:String = "sprite_1.png",
    val artwork:String = "artwork_1.png",
)

@HiltViewModel
class CharacterUpdateViewModel@Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterRepository : PokemonRepository
): ViewModel() {
    var isError =false
    var characterId = 0L
    var name by mutableStateOf("")
    private  var sprite:String = "sprite_1.png"
    private var artwork:String = "artwork_1.png"
    private val _uiState : MutableStateFlow<DetailUiState> =
        MutableStateFlow(DetailUiState())
    val uiState : StateFlow<DetailUiState> = _uiState.asStateFlow()
    init {
        viewModelScope.launch {
            val route = savedStateHandle.toRoute<Route.Detail>()
            characterId = route.id
            val character = characterRepository.readOne(characterId)
            character.let{character ->
                name = character.getOrNull()!!.name
                sprite = character.getOrNull()!!.sprite
                artwork = character.getOrNull()!!.artwork

                _uiState.value = character.getOrNull()!!.toDetailUiState()
            }


        }
    }
    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        isError = true

    }
    fun update(){
        viewModelScope.launch {
            val character = Pokemon(
                id = characterId,
                name = name,
                sprite = sprite,
                artwork = artwork,
            )
            characterRepository.insert(character)
        }
    }



}

