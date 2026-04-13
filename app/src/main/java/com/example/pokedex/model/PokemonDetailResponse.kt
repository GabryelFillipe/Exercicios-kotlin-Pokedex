package com.example.pokedex.model

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val types: List<TypeSlot>,
    val stats: List<StatSlot>
)


data class TypeSlot(
    val slot: Int,
    val type: TypeDetail
)

data class TypeDetail(
    val name: String
)

data class StatSlot(
    val base_stat: Int,
    val stat: StatDetail
)

data class StatDetail(
    val name: String
)