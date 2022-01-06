package com.far.cryptocurrency.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.far.cryptocurrency.presentation.detail.components.CoinTag
import com.far.cryptocurrency.presentation.detail.components.TeamMemberListItem
import com.far.cryptocurrency.presentation.detail.viewmodel.CoinDetailViewModel
import com.far.cryptocurrency.presentation.theme.DarkGray900
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(
    navController: NavController,
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val getCoinState = viewModel.getCoinState.value
    Box(modifier = Modifier.fillMaxSize()) {
        getCoinState.coins?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    TopAppBar(
                        backgroundColor = DarkGray900,
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.Filled.ArrowBack, "back-button")
                            }
                        },
                        title = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                                    style = MaterialTheme.typography.h2,
                                    modifier = Modifier.weight(8f)
                                )
                                Text(
                                    text = if (coin.isActive) "active" else "inactive",
                                    color = if (coin.isActive) Color.Green else Color.Red,
                                    fontStyle = FontStyle.Italic,
                                    textAlign = TextAlign.End,
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .weight(2f)
                                        .absolutePadding(right = 8.dp)
                                )
                            }
                        })
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = coin.description,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(modifier = Modifier.padding(horizontal = 8.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        coin.tags.forEach { tag -> CoinTag(tag = tag) }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(modifier = Modifier.padding(horizontal = 8.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Team Members",
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                items(coin.team) { teamMember ->
                    TeamMemberListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .absolutePadding(left = 16.dp, top = 4.dp, bottom = 4.dp, right = 16.dp)
                    )
                    Divider(modifier = Modifier.padding(horizontal = 12.dp))
                }
            }
        }
        if (getCoinState.error.isNotBlank()) {
            Text(
                text = getCoinState.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center)
            )
        }
        if (getCoinState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}