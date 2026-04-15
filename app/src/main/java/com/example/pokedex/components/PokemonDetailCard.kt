import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokedex.R
import com.example.pokedex.components.InfoColumn
import com.example.pokedex.components.PokemonStatRow
import com.example.pokedex.components.PokemonTypeTag
import com.example.pokedex.screen.details.PokemonStat

@Composable
fun PokemonDetailCard(
    pokemonImg: String,
    types: List<String>,
    weight: String,
    height: String,
    description: String,
    typeColor: Color,
    modifier: Modifier = Modifier,
    stats: List<PokemonStat>,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = Color.White
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),

                contentPadding = PaddingValues(
                    start = 24.dp,
                    end = 24.dp,
                    top = 60.dp,
                    bottom = 32.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        types.forEach { typeName ->
                            PokemonTypeTag(name = typeName, color = typeColor)
                        }
                    }

                    Text(
                        text = "About",
                        modifier = Modifier.padding(vertical = 10.dp),
                        color = typeColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        InfoColumn(label = "Weight", value = weight, icon = R.drawable.ic_weight)
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(40.dp)
                                .background(Color.LightGray)
                        )
                        InfoColumn(label = "Height", value = height, icon = R.drawable.ic_height)
                    }

                    Text(
                        text = description,
                        modifier = Modifier.padding(top = 10.dp, bottom = 8.dp),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = Color.Black
                    )

                    Text(
                        text = "Base Stats",
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = typeColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                item {
                    stats.forEach { stat ->
                        PokemonStatRow(
                            statName = stat.name,
                            statValue = stat.value,
                            statColor = typeColor
                        )
                    }
                }
            }
        }

            AsyncImage(
                model = pokemonImg,
                contentDescription = "Imagem do Pokémon",
                modifier = Modifier
                    .size(200.dp)
                    .offset(y = (-65).dp)
            )
        }
    }
