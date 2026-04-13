package com.example.pokedex.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pokedex.R
import com.example.pokedex.model.PokemonDetailResponse

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    pokemonId: String
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(116, 190, 78, 255)) // O fundo verde vem para cá
    ) {

        Image(
            painter = painterResource(R.drawable.pokeball),
            contentDescription = "",
            modifier = Modifier
                .size(240.dp)
                .align(Alignment.TopEnd),
            colorFilter = ColorFilter.tint(Color.White),
            alpha = 0.15f
        )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp),
                            tint = Color.White
                        )
                        Text(
                            "Bulbasaur",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            color = Color.White
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "#001", fontWeight = FontWeight.Bold, color = Color.White
                        )
                    }
                }
               // PokemonDetailResponse()
            }

        }
    }
