package com.far.cryptocurrency.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.far.cryptocurrency.common.Constants
import com.far.cryptocurrency.presentation.route.RouteScreen
import com.far.cryptocurrency.presentation.detail.screen.CoinDetailScreen
import com.far.cryptocurrency.presentation.list.screen.CoinListScreen
import com.far.cryptocurrency.presentation.theme.CryptocurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = RouteScreen.CoinListScreen.route
                    ) {
                        composable(
                            route = RouteScreen.CoinListScreen.route
                        ) {
                            CoinListScreen(navController)
                        }
                        composable(
                            route = RouteScreen.CoinDetailScreen.route + "/{${Constants.PARAM_COIN_ID}}"
                        ) {
                            CoinDetailScreen(navController)
                        }
                    }
                }
            }
        }
    }
}