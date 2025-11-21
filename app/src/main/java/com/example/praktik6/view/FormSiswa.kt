package com.example.praktik6.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults.Thickness
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.praktik6.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
//edit 1
fun FormIsian(
    pilihanJK: List<String>,
    OnSubmitBtnClick : (MutableList<String>) -> Unit
) {
    //edit 2
    var txtNama by rememberSaveable {mutableStateOf (value ="")}
    var txtAlamat by remember {mutableStateOf (value ="")}
    var txtGender by remember {mutableStateOf (value ="")}
    val listData: MutableList<String> = mutableListOf (txtNama, txtAlamat, txtGender)

    Scaffold(
        modifier = Modifier,
        {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.home),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    colorResource(id = R.color.teal_700)
                )
            )
        }
    ) { isiRuang ->
        Column(
            modifier = Modifier.padding(isiRuang),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = txtNama,
                singleLine = true,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(250.dp),
                label = { Text(text = "Nama Lengkap") },
                onValueChange = {
                    txtNama = it
                },

                )
            HorizontalDivider(
                modifier = Modifier
                    .padding(20.dp)
                    .width(250.dp), thickness = Thickness, color =
                    Color.Red
            )
            //edit 3
            Row {
                pilihanJK.forEach {
                        item->
                    Row (modifier = Modifier.selectable(
                        selected = txtGender == item,
                        onClick = { txtGender = item }
                    ),
                        verticalAlignment = Alignment.CenterVertically){
                        RadioButton(
                            selected = txtGender == item,
                            onClick = { txtGender = item }
                        )
                        Text(text = item)
                    }
                }

            }
            HorizontalDivider(modifier = Modifier
                .padding(20.dp)
                .width(250.dp),
                thickness = 1.dp,
                color = Color.Red
            )
            OutlinedTextField(
                value = txtAlamat,
                singleLine = true,
                modifier = Modifier
                    .width(250.dp),
                label = {Text(text = "Alamat")},
                onValueChange = {
                    txtAlamat = it
                },
            )
            //edit 4
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                modifier = Modifier.fillMaxWidth(1f)
                    .padding(all = 25.dp),
                enabled = txtAlamat.isNotEmpty(),
                onClick = {OnSubmitBtnClick(listData)}
            ){
                Text(stringResource(id = R.string.submit))
            }
        }
    }
}