

package com.app.note.components.viewTodoListScreenComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.note.components.globalComponents.millistoDate
import com.app.note.components.globalComponents.numtoTime


@Composable
fun todoDetailsCard(

    Id : Int,
    title: String,
    createdDate : Long,
    createdHour : Int,
    createdMinute : Int,
    deadlineDate: Long?,
    deadlineHour : Int?,
    deadlineMinute : Int?,
    note : String

){

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        item{
            Text("Note Details", modifier = Modifier.fillMaxWidth()
                , fontSize = 20.sp, fontWeight = FontWeight.W600,)
        }
    item(){
        Column(Modifier.border(
            width =2.dp,
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.primary,
        )){

            Column(modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)){
                Text("Title : ${title}", modifier = Modifier.fillMaxWidth()
                )

                Text("Created : ${millistoDate(createdDate)} " +
                        numtoTime(createdHour,createdMinute)
                )

                if(deadlineDate is Long && deadlineHour is Int && deadlineMinute is Int){
                    Text("Deadline : ${millistoDate(createdDate)} " +
                            numtoTime(createdHour,createdMinute)
                    )
                }
            }
        }
    }
        item{
            Text("Note", modifier = Modifier.fillMaxWidth()
                , fontSize = 20.sp, fontWeight = FontWeight.W600,)
        }

        item {
            Column(modifier = Modifier.fillMaxWidth().heightIn(min = 300.dp).border(2.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(16.dp)
            )){

                Text(note,
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                )

            }
        }
    }

}