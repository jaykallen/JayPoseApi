package com.example.jayposeapi2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jayposeapi2.Screen.DetailScreen.withArgs

@Composable
fun MainScreen(navController: NavController) {
    val mainViewModel: MainViewModel = viewModel()
    val bookList by mainViewModel.bookList.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { mainViewModel.callApi() },
            modifier = Modifier.padding(8.dp),
        ) {
            Text(
                text = "Run Api"
            )
        }
        Text(
            text = "Code = ${mainViewModel.apiCode}",
            modifier = Modifier.padding(8.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(bookList.size) { book ->
                BookItem(navController, book = bookList[book])
            }
        }
    }
}

@Composable
fun BookItem(navController: NavController, book: Book) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(
                        withArgs(
                            book.id.toString(), book.title, book.author, book.status, book.fee.toString(), book.lastEdited
                        )
                    )
                },
        ) {
            Text(
                text = "${book.id}",
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = book.title,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = book.author,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}
