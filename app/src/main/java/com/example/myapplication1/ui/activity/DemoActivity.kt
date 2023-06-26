package com.example.myapplication1.ui.activity

import android.os.Bundle
import android.widget.Toast

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.ui.foundation.AdapterList
import androidx.ui.layout.ConstraintLayout

import com.example.myapplication1.R
import com.example.myapplication1.itemsIndexedList
import com.example.myapplication1.itemsList
import com.example.myapplication1.ui.Message
import com.example.myapplication1.ui.list
import com.example.myapplication1.ui.theme.MyApplication1Theme

class DemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_demo)

        setContent {
            //            setContentView()
//            Greeting(arrayOf("Android", "android2"))
            MyApplication1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PreviewConversation()
                }
            }
        }

    }





    @Composable
    fun setContentView() {
        Text(text = "hello compose")
    }




    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        MyApplication1Theme {
            Conversation(/*SampleData.conversationSample*/list)
        }
    }


    @Composable
    fun MessageCard(msg: Message) {
        Row(modifier = Modifier.padding(all = 8.dp)) {

            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            // We keep track if the message is expanded or not in this
            // variable
//        var isExpanded by remember { mutableStateOf(false) }
            var isExpanded by remember {
                mutableStateOf(false)
            }

            // We toggle the isExpanded variable when we click on this Column
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier
                            .padding(all = 4.dp)
                            .clickable {
                                Toast
                                    .makeText(this@DemoActivity, "你好111", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }

                /*AdapterList(data = listOf("1111111","2222222")) {

                }*/
            }
        }
    }
}








