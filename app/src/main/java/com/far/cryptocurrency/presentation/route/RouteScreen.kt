package com.far.cryptocurrency.presentation.route

sealed class RouteScreen(val route: String) {
    object CoinListScreen: RouteScreen("coin_list_screen")
    object CoinDetailScreen: RouteScreen("coin_detail_screen")
}
