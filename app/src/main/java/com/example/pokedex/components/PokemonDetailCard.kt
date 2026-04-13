import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.R
import com.example.pokedex.components.InfoColumn
import com.example.pokedex.components.PokemonTypeTag

@Composable
fun PokemonInfoCard(
    types: List<String>,
    weight: String,
    height: String,
    description: String,
    typeColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 56.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                types.forEach { typeName ->
                    PokemonTypeTag(name = typeName, color = typeColor)
                }
            }

            Text(
                text = "About",
                modifier = Modifier.padding(vertical = 20.dp),
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

                Box(modifier = Modifier.width(1.dp).height(40.dp).background(Color.LightGray))

                InfoColumn(label = "Height", value = height, icon = R.drawable.ic_height)
            }

            Text(
                text = description,
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = Color.Black
            )

            Text(
                text = "Base Stats",
                modifier = Modifier.padding(bottom = 16.dp),
                color = typeColor,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

        }
    }
}




