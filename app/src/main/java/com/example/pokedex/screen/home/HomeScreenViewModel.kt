package com.example.pokedex.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.PokemonResult
import com.example.pokedex.model.PokemonTypeEnum
import com.example.pokedex.service.PokemonRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PokemonUiState(
    val id: String,
    val name: String,
    val imageUrl: String,
    val typeEnum: PokemonTypeEnum
)
class HomeScreenViewModel : ViewModel() {

    private val repository = PokemonRepository()


    private val _pokemonList = MutableStateFlow<List<PokemonUiState>>(emptyList())
    val pokemonList: StateFlow<List<PokemonUiState>> = _pokemonList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var currentOffset = 0
    private val limit = 20

    init {
        loadMorePokemons()
    }

    fun loadMorePokemons() {
        if (_isLoading.value) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getPokemonList(limit = limit, offset  = currentOffset)

                val detailedPokemons = response.results.map { result ->
                    async {
                        val detail = repository.getPokemonDetail(result.name)

                        val firstTypeName = detail.types.firstOrNull()?.type?.name ?: "unknown"
                        val typeEnum = PokemonTypeEnum.fromString(firstTypeName)

                        PokemonUiState(
                            id = "#${detail.id.toString().padStart(3, '0')}",
                            name = detail.name,
                            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${detail.id}.png",
                            typeEnum = typeEnum
                        )
                    }
                }.awaitAll()

                _pokemonList.value = _pokemonList.value + detailedPokemons

                currentOffset += limit

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}