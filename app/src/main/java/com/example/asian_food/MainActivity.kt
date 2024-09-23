package com.example.asian_food

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.asian_food.ui.theme.Asian_foodTheme


/*
This app highlights the most popular dishes from three East Asian countries: China, Korea, and Japan.
User switches between three countries by clicking on the tab inside ScrollableTabRow.
All dishes are displayed inside a Card with an image and the name of the dish.
Cards are organized in a LazyColumn.
We opted for a simplistic design because we don't want to distract the user from the delicious food.
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Asian_foodTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AsianFoodTour(
                        name = "East Asian Food Tour",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsianFoodTour(name: String, modifier: Modifier) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val imageSize = 200.dp

    // Define color scheme
    val backgroundColor = Color(0xFFF2F2F2)
    val cardBackgroundColor = Color(0xFFFFFFFF)
    val appBarColor = Color(0xFFC5A8ED)
    val tabColor = Color(0xFF7E57C2) // Slightly lighter than appBarColor
    val textColor = Color(0xFF333333)
    val borderColor = Color(0xFF333333) // Border color for images

    // Dishes and images for each category
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
            TopAppBar(
                title = {
                    Text(
                        name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.White,
                        modifier = modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = appBarColor)
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(padding)
            ) {
                ScrollableTabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = tabColor, // Slightly lighter tab color
                    contentColor = Color.White
                ) {
                    listOf("Chinese", "Korean", "Japanese").forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text(title, color = Color.White) }
                        )
                    }
                }

                val dishes = when (selectedTabIndex) {
                    0 -> chineseDishes
                    1 -> koreanDishes
                    else -> japaneseDishes
                }
                val images = when (selectedTabIndex) {
                    0 -> chineseImages
                    1 -> koreanImages
                    else -> japaneseImages
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(count = dishes.size) { index ->
                        Card(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            shape = MaterialTheme.shapes.medium,
                            elevation = CardDefaults.cardElevation(8.dp),
                            colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth() // Fill width to center content
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally
                                verticalArrangement = Arrangement.Center // Center content vertically
                            ) {
                                Image(
                                    painter = painterResource(id = images[index]),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(imageSize)
                                        .border(2.dp, borderColor) // Border around image
                                        .align(Alignment.CenterHorizontally) // Ensure the image is horizontally centered
                                )
                                Text(
                                    text = dishes[index],
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = textColor,
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .align(Alignment.CenterHorizontally) // Center the text horizontally
                                )
                            }
                        }
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
        AsianFoodTour("Android", Modifier.fillMaxSize())
    }
}
