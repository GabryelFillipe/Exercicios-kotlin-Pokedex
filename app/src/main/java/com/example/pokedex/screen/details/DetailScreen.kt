package com.example.pokedex.screen.details

import PokemonDetailCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.pokedex.R

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    detailScreenViewModel: DetailScreenViewModel,
    pokemonName: String,
    pokemonColor: String
) {
    val pokemonInfo by detailScreenViewModel.pokemonInfo.collectAsStateWithLifecycle()

    LaunchedEffect(pokemonName) {
        detailScreenViewModel.loadPokemonInfo(pokemonName)
    }

    if (pokemonInfo != null) {
        val pokemon = pokemonInfo!!

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color(pokemon.typeEnum.colorHex))
        ) {
            Image(
                painter = painterResource(R.drawable.pokeball),
                contentDescription = "",
                modifier = Modifier
                    .size(240.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 40.dp, y = (-20).dp),
                colorFilter = ColorFilter.tint(Color.White),
                alpha = 0.15f
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 40.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { navController.navigate("home") },
                        tint = Color.White
                    )
                    Text(
                        text = pokemonName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        color = Color.White
                    )
                }
                Text(
                    text = pokemon.id.padStart(3, '0'),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            PokemonDetailCard(
                types = pokemon.types,
                height = pokemon.height,
                weight = pokemon.weight,
                description = "There is a plant seed on its back right from the day this Pokémon is born...",
                typeColor = Color(pokemon.typeEnum.colorHex),
                pokemonImg = pokemon.imageUrl,
                stats = pokemon.stats,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(0.80f)
            )

        }
    } else {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(116, 190, 78, 255))
        }
    }
}