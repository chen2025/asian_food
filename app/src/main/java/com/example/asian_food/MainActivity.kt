package com.example.asian_food

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.asian_food.ui.theme.Asian_foodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Asian_foodTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AsianFoodTour(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsianFoodTour(name: String, modifier: Modifier = Modifier) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val imageSize = 300.dp
    val chineseDishes = listOf(
        "Char Siu", "Chow Mein", "Dim Sum", "Dumplings", "Fried Rice",
        "Hot Pot", "Kung Pao Chicken", "Ma Po Tofu", "Peking Duck", "Sweet and Sour Pork"
    )
    val chineseImages = listOf(
        R.drawable.char_siu,
        R.drawable.chow_mein,
        R.drawable.dim_sum,
        R.drawable.dumplings,
        R.drawable.fried_rice,
        R.drawable.hot_pot,
        R.drawable.kung_pao_chicken,
        R.drawable.ma_po_tofu,
        R.drawable.peking_duck,
        R.drawable.sweet_sour_pork
    )
    val koreanDishes = listOf(
        "Bibimbap", "Bulgogi", "Galbi", "Korean Pork Belly", "Korean Radish",
        "Korean Stir Fried Noodles", "Mandoo", "Oxtail Soup", "Short Rib", "Spicy Kimchi Stew"
    )
    val koreanImages = listOf(
        R.drawable.bibimbap,
        R.drawable.bulgogi,
        R.drawable.galbi,
        R.drawable.korean_pork_belly,
        R.drawable.korean_radish,
        R.drawable.korean_stir_fried_noodles,
        R.drawable.mandoo,
        R.drawable.oxtail_soup,
        R.drawable.short_rib_soup,
        R.drawable.spicy_kimchi_stew
    )
    val japaneseDishes = listOf(
        "Chilled Tofu", "Natto", "Oden", "Oyakodon", "Ramen",
        "Sashimi", "Sushi", "Tempura", "Udon", "Yakitori"
    )
    val japaneseImages = listOf(
        R.drawable.chilled_tofu,
        R.drawable.natto,
        R.drawable.oden,
        R.drawable.oyakodon,
        R.drawable.ramen,
        R.drawable.sashimi,
        R.drawable.sushi,
        R.drawable.tempura,
        R.drawable.udon,
        R.drawable.yakitori
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Asian Food Tour") })
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
                    listOf("Chinese", "Korean", "Japanese").forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text(title) }
                        )
                    }
                }
                when (selectedTabIndex) {
                    0 -> LazyColumn {
                        items(
                            count = chineseDishes.size,
                            itemContent = { index ->
                                Card(modifier = Modifier.padding(8.dp)) {
                                    Row(modifier = Modifier.padding(16.dp)) {
                                        Image(
                                            painter = painterResource(id = chineseImages[index]),
                                            contentDescription = null,
                                            modifier = Modifier.size(imageSize)
                                        )
                                        Text(
                                            chineseDishes[index],
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                    }
                                }
                            }
                        )
                    }

                    1 -> LazyColumn {
                        items(
                            count = koreanDishes.size,
                            itemContent = { index ->
                                Card(modifier = Modifier.padding(8.dp)) {
                                    Row(modifier = Modifier.padding(16.dp)) {
                                        Image(
                                            painter = painterResource(id = koreanImages[index]),
                                            contentDescription = null,
                                            modifier = Modifier.size(imageSize)
                                        )
                                        Text(
                                            koreanDishes[index],
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                    }
                                }
                            }
                        )
                    }

                    2 -> LazyColumn {
                        items(
                            count = japaneseDishes.size,
                            itemContent = { index ->
                                Card(modifier = Modifier.padding(8.dp)) {
                                    Row(modifier = Modifier.padding(16.dp)) {
                                        Image(
                                            painter = painterResource(id = japaneseImages[index]),
                                            contentDescription = null,
                                            modifier = Modifier.size(imageSize)
                                        )
                                        Text(
                                            japaneseDishes[index],
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun AsianFoodTourPreview() {
    Asian_foodTheme {
        AsianFoodTour("Android")
    }
}