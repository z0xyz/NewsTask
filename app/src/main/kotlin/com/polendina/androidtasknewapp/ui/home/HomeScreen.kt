package com.polendina.androidtasknewapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polendina.androidtasknewapp.R
import com.polendina.androidtasknewapp.ui.BottomBar
import com.polendina.androidtasknewapp.ui.home.widgets.HorizontalPublication
import com.polendina.androidtasknewapp.ui.home.widgets.PublicationCard
import com.polendina.androidtasknewapp.ui.home.widgets.TopBarSection
import com.polendina.androidtasknewapp.ui.theme.AndroidTaskNewAppTheme
import kotlinx.coroutines.runBlocking

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel
) {
    Scaffold (
        topBar = {
            TopBarSection(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // TODO: Get dimensions in a separate dimensions resource file!
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
            }
        },
        floatingActionButton = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { BottomBar() },
        modifier = Modifier
            .padding(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(it)
        ) {
            LazyRow (
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                items(runBlocking { homeScreenViewModel.getNewsFeed() }) {
                    PublicationCard(
                        publication = it,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    )
                }
            }
            Text(
                text = stringResource(id = R.string.latest_news),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                ),
                modifier = Modifier
                    .padding(
                        top = 15.dp,
                        bottom = 10.dp
                    )
            )
            LazyColumn {
                items(runBlocking { homeScreenViewModel.getNewsFeed().reversed() }) {
                    HorizontalPublication(
                        publication = it,
                        modifier = Modifier
                            .height(90.dp)
                    )
                    Divider()
                }
            }
        }
    }
    
}

@Preview(name = "Home screen (Light mode)", showBackground = true)
@Composable
fun HomeScreenPreview() {
    AndroidTaskNewAppTheme {
        HomeScreen(
            homeScreenViewModel = NewsScreenViewModelMock()
        )
    }
}