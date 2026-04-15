package com.example.pokedex.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.PokemonTypeEnum
import com.example.pokedex.service.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PokemonStat(
    val name: String,
    val value: Int
)
data class PokemonDetailUiState(
    val id: String,
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val weight: String,
    val height: String,
    val typeEnum: PokemonTypeEnum,
    val stats: List<PokemonStat>
)

class DetailScreenViewModel : ViewModel() {

    private val _pokemonInfos = MutableStateFlow<PokemonDetailUiState?>(null)
    val pokemonInfo: StateFlow<PokemonDetailUiState?> = _pokemonInfos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val repository = PokemonRepository()

    private fun formatStatName(apiName: String): String {
        return when(apiName.lowercase()) {
            "hp" -> "HP"
            "attack" -> "ATK"
            "defense" -> "DEF"
            "special-attack" -> "SATK"
            "special-defense" -> "SDEF"
            "speed" -> "SPD"
            else -> apiName.uppercase()
        }
    }

    fun loadPokemonInfo(pokemonName: String) {

        viewModelScope.launch {
            try {

                val pokemonDetail = repository.getPokemonDetail(pokemonName.lowercase().trim().replace("#", ""))

                val firstTypeName = pokemonDetail.types.firstOrNull()?.type?.name ?: "unknown"
                val typeEnum = PokemonTypeEnum.fromString(firstTypeName)

                _pokemonInfos.value = PokemonDetailUiState(
                    id = pokemonDetail.id.toString(),
                    name = pokemonDetail.name,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonDetail.id}.png",
                    types = pokemonDetail.types.map { it.type.name.replaceFirstChar { char -> char.uppercase() } },
                    weight = "${pokemonDetail.weight / 10f} kg",
                    height = "${pokemonDetail.height /10f} m",
                    typeEnum = typeEnum,
                    stats = pokemonDetail.stats.map { stat ->
                        PokemonStat(
                            name = formatStatName(stat.stat.name),
                            value = stat.base_stat
                        )
                    }
                )


            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
