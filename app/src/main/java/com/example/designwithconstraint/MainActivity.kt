package com.example.designwithconstraint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.designwithconstraint.ui.theme.DesignWithConstraintTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesignWithConstraintTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
   ConstraintLayout(modifier = Modifier
       .fillMaxWidth()
       .fillMaxHeight()
       .background(color = Color.White)

       ){
        val(headerRow,contentRow,bottomRow)= createRefs()

       Row(modifier = Modifier
           .constrainAs(headerRow) {
               top.linkTo(parent.top)
               start.linkTo(parent.start)
               end.linkTo(parent.end)
               width = Dimension.fillToConstraints
           }
           .background(color = Color.Gray),
           horizontalArrangement = Arrangement.Center
       ) {
            Text(text = "Select Your Product", fontSize = 16.sp, color = Color.White)
       }
       
       LazyColumn(modifier = Modifier.constrainAs(contentRow) {
           top.linkTo(headerRow.bottom, 5.dp)
           start.linkTo(parent.start)
           end.linkTo(parent.end)
           width=Dimension.fillToConstraints
       }) {
            items(5){
                MyListProducts()
                Spacer(modifier = Modifier.height(3.dp))
            }
       }

       Row(modifier = Modifier.constrainAs(bottomRow){
           top.linkTo(contentRow.bottom,5.dp)
           start.linkTo(parent.start)
           end.linkTo(parent.end)
           width=Dimension.fillToConstraints
       }, horizontalArrangement = Arrangement.Center){
           Text(text = "Enjoy Your Shopping", fontSize = 16.sp, color = Color.Black)
       }
   }
}
@Composable
fun MyListProducts(){
    val imageModifier= Modifier
        .size(150.dp)
        .border(BorderStroke(1.dp, color = Color.Black))
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
         horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
        Spacer(modifier = Modifier.height(10.dp))
        //1
        Image(
            painter = painterResource(id = R.drawable.argan),
            contentDescription = "argan",
            modifier= imageModifier
            )
        //2
        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
            ) {
            Text(text = "Add to my list ", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DesignWithConstraintTheme {
        HomeScreen()
    }
}