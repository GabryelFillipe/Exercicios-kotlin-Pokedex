package com.example.pokedex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PokemonStatRow(
    statName: String,
    statValue: Int,
    statColor: Color,
    maxValue: Int = 100
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = statName,
            color = statColor,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.width(40.dp)
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .width(1.dp)
                .height(16.dp)
                .background(Color.LightGray)
        )

        Text(
            text = statValue.toString().padStart(3, '0'),
            color = Color.DarkGray,
            fontSize = 12.sp,
            modifier = Modifier.width(28.dp)
        )

        LinearProgressIndicator(
            progress = { (statValue / maxValue.toFloat()).coerceIn(0f, 1f) },
            modifier = Modifier
                .weight(1f)
                .height(6.dp)
                .padding(start = 12.dp)
                .clip(RoundedCornerShape(50)),
            color = statColor,
            trackColor = statColor.copy(alpha = 0.2f)
        )
    }
}