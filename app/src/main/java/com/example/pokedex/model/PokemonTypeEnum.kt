package com.example.pokedex.model

enum class PokemonTypeEnum(val colorHex: Long) {
    GRASS(0xFF78C850),
    POISON(0xFFA040A0),
    FIRE(0xFFF08030),
    WATER(0xFF6890F0),
    BUG(0xFFA8B820),
    NORMAL(0xFFA8A878),
    ELECTRIC(0xFFF8D030),
    GROUND(0xFFE0C068),
    UNKNOWN(0xFF68A090);

    companion object {
        fun fromString(type: String): PokemonTypeEnum {
            return entries.find { it.name.equals(type, ignoreCase = true) } ?: UNKNOWN
        }
    }
}