package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(category: Category){
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally){
        CenterAlignedTopAppBar({ Text(text = category.strCategory) })
        //Text(text = category.strCategory, textAlign = TextAlign.Center, fontSize = 28.sp)
        Image(painter = rememberAsyncImagePainter(model = category.strCategoryThumb) , contentDescription = null,
            Modifier
                .wrapContentSize()
                .aspectRatio(1f))
        Text(text = category.strCategoryDescription, Modifier.verticalScroll(rememberScrollState()), textAlign = TextAlign.Justify)

    }
}