package com.example.pokedex

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.screen.details.DetailScreen
import com.example.pokedex.screen.details.DetailScreenViewModel
import com.example.pokedex.screen.home.HomeScreen
import com.example.pokedex.screen.home.HomeScreenViewModel
import com.example.pokedex.ui.theme.PokedexTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()


                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(1000)
                            ) + fadeOut(animationSpec = tween(1000))
                        },
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                animationSpec = tween(1000)
                            ) + fadeIn(animationSpec = tween(500))
                        }
                    ) {
                        composable(
                            route = "home",
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                                    animationSpec = tween(1000)
                                )
                            }
                        ) {
                            val homeViewModel: HomeScreenViewModel = viewModel()
                            HomeScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                homeScreenViewModel = homeViewModel,
                                onPokemonClick = {}
                            )
                        }
                        composable(
                            route = "detail/{name}/{color}",
                            arguments = listOf(
                                navArgument("name") { type = NavType.StringType },
                                navArgument("color") {type = NavType.StringType}
                            )
                        ) { backStackEntry ->
                            val name = backStackEntry.arguments?.getString("name")
                            val color = backStackEntry.arguments?.getString("color")
                            println("LOG_POKEDEX: Navegando para o ID $name")

                            val detailViewModel: DetailScreenViewModel = viewModel()
                            DetailScreen(
                                navController = navController,
                                pokemonName = name ?: "",
                                pokemonColor = color?: "",
                                detailScreenViewModel = detailViewModel
                            )
                        }

                    }
                }
            }
        }
    }
}

