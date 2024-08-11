package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier=Modifier, onitemclicked: (Category) -> Unit){
    val recipeviewModel:MainViewModel= viewModel()
    val viewState by recipeviewModel.categoriesState
    Box(modifier = modifier.fillMaxSize()){
        when{
            viewState.loading ->{
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            viewState.error != null ->{
                Text(text = "Error loading categories ${viewState.error}")
            }
            else ->{
                //display recipe categories
                CategoryScreen(categories = viewState.list, onitemclicked)
            }

        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>, onitemclicked: (Category) -> Unit){
    LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.fillMaxSize()) {
        items(categories){
            category ->
            CategoryItem(category = category, onitemclicked)
        }


    }
}

@Composable
fun CategoryItem(category: Category,onitemclicked: (Category) -> Unit){
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable { onitemclicked(category) }, horizontalAlignment = Alignment.CenterHorizontally

    )
    {

        Image(painter = rememberAsyncImagePainter(model = category.strCategoryThumb) , contentDescription = null, Modifier.fillMaxSize().aspectRatio(1f))
        Text(text = category.strCategory, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Cursive, fontSize = 28.sp)


    
    }

}