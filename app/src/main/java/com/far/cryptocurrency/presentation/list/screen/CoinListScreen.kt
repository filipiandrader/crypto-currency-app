package com.far.cryptocurrency.presentation.list.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.far.cryptocurrency.presentation.route.RouteScreen
import com.far.cryptocurrency.presentation.list.components.CoinListItem
import com.far.cryptocurrency.presentation.list.viewmodel.CoinListViewModel
import com.far.cryptocurrency.presentation.theme.DarkGray900

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val getCoinsState = viewModel.getCoinsState.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                TopAppBar(backgroundColor = DarkGray900, title = { Text(text = "Crypto Currency") })
            }
            items(getCoinsState.coins) { coin ->
                CoinListItem(
                    coin = coin,
                    onItemClick = {
                        navController.navigate(
                            RouteScreen.CoinDetailScreen.route.plus("/${coin.id}")
                        )
                    }
                )
                Divider(
                    modifier = Modifier.absolutePadding(
                        left = 8.dp,
                        right = 8.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    )
                )
            }
        }
        if (getCoinsState.error.isNotBlank()) {
            Text(
                text = getCoinsState.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center)
            )
        }
        if (getCoinsState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}