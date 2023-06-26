package com.example.myapplication1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication1.ui.theme.MyApplication1Theme
//定义数据集合
val itemsList = (0..20).toList()
val itemsIndexedList = listOf("A", "B", "C")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(arrayOf("Android","android2"))
                }
            }
        }
    }
}




@Composable
fun Greeting(names: Array<String>) {
    Row {
        Text(text = "kotlin")
        Column {
            LazyColumn {
                items(names) { name ->
                    Text(text = name)
                }
            }
            LazyColumn {
                items(names) { name ->
                    Text(text = name)
                }
            }

            LazyColumn{
                items(2){
                    Text(text = "$it 个")
                }
            }

            LazyColumn {
                //添加多个item，回调中没有数据的下标
                items(itemsList) { it ->
                    Text("第$it 个Item")
                }
                //添加单个item
                item {
                    Text("单个 item")
                }
                //添加多个Item，可以获取到数据的下标
                itemsIndexed(itemsIndexedList) { index, item ->
                    Text("第$index 个Item，value = $item")
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplication1Theme {
        Greeting(arrayOf("Android","android2"))
    }
}