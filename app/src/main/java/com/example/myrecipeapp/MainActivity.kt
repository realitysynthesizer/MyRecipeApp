package com.example.myrecipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myrecipeapp.ui.theme.MyRecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyRecipeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    //RecipeScreen()
                    Myapp(it)
                    //CategoryDetailScreen(category = Category(idCategory = 1.toString(), strCategory = "beef", strCategoryDescription = "Vegetarianism is the practice of abstaining from the consumption of meat (red meat, poultry, seafood, and the flesh of any other animal), and may also include abstention from by-products of animal slaughter.\\r\\n\\r\\nVegetarianism may be adopted for various reasons. Many people object to eating meat out of respect for sentient life. Such ethical motivations have been codified under various religious beliefs, as well as animal rights advocacy. Other motivations for vegetarianism are health-related, political, environmental, cultural, aesthetic, economic, or personal preference. There are variations of the diet as well: an ovo-lacto vegetarian diet includes both eggs and dairy products, an ovo-vegetarian diet includes eggs but not dairy products, and a lacto-vegetarian diet includes dairy products but not eggs. A vegan diet excludes all animal products, including eggs and dairy. Some vegans also avoid other animal products such as beeswax, leather or silk clothing, and goose-fat shoe polish.", strCategoryThumb = "https://www.themealdb.com/images/category/beef.png"))

                }
            }
        }
    }
}

@Composable
fun Myapp(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "recipescreen") {
        composable("recipescreen") {
            RecipeScreen(modifier =Modifier.padding(paddingValues)) { category ->
                // Serialize the category object to a JSON string
                //val categoryJson = Json.encodeToString(category)
                // URL-encode the JSON string to handle special characters
                //val encodedCategoryJson = java.net.URLEncoder.encode(categoryJson, "UTF-8")
                // Navigate with the serialized string as a route argument
                navController.currentBackStackEntry?.savedStateHandle?.set("category",category)
                navController.navigate("categoryscreen")
            }
        }
        composable("categoryscreen") {
            // Retrieve and URL-decode the JSON string
            /*val categoryJson = backStackEntry.arguments?.getString("categoryJson")?.let {
                java.net.URLDecoder.decode(it, "UTF-8")
            }
            // Deserialize the JSON string back to a Category object
            val category = categoryJson?.let { Json.decodeFromString<Category>(it) }
            category?.let {
                CategoryDetailScreen(category = it)
            }*/
            val cat=navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")
            cat?.let {
                CategoryDetailScreen(category = it)
            }
        }

    }
}
