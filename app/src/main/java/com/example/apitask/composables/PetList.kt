package com.example.apitask.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.apitask.model.Pet
import com.example.apitask.petViewModel.PetViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun PetListComposableScreen(
    viewModel: PetViewModel,
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val pets = viewModel.pets
    LazyColumn(
        modifier = modifier
    ) {
        items(pets) { pet ->
            PetItems(pet,viewModel)
        }
    }

}


@Composable
fun PetItems(
    pet: Pet,
    viewModel: PetViewModel = viewModel()
) {
    Card(
        modifier = Modifier
            .background(Color(color = 0xFF720E30))
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {

        Row(
            Modifier
                .padding(10.dp)
                .height(150.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            AsyncImage(
                model = pet.image, contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .width(100.dp)
            )
            Column {

                Text("\tPet's ID: " + pet.id.toString())

                Text("\tPet's Name: " + pet.name)

                Text("\tPet's Gender: " + pet.gender)

                Text("\tPet's Age: " + pet.age.toString())

                Text("\tIs adopted?: " + pet.adopted.toString())
            }
        }
        Button(onClick = {
            viewModel.deletePet(pet.id)
            viewModel.fetchPets()
        }, modifier = Modifier) {
            Text(text = "Delete Pet")
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddPetScreen(viewModel: PetViewModel = viewModel(),
                 back:()->Unit = {}){


    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var adopted by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier
            .padding(15.dp)
            .background(Color(color = 0xFF720E30))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add a New Pet",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            InputField(
                value = id,
                onValueChange = { id = it },
                label = "Id",
                keyboardType = KeyboardType.Number
            )
            InputField(
                value = name,
                onValueChange = { name = it },
                label = "Name"
            )
            InputField(
                value = gender,
                onValueChange = { gender = it },
                label = "Gender",
            )
            InputField(
                value = image,
                onValueChange = { image = it },
                label = "Image URL"
            )

            InputField(
                value = adopted,
                onValueChange = { adopted = it },
                label = "Adopted"
            )

            InputField(
                value = age,
                onValueChange = { age = it },
                label = "Age",
                keyboardType = KeyboardType.Number

            )


            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val newPet = Pet(
                        id = 0,
                        name = name,
                        adopted = true,
                        image = image,
                        age = age.toInt(),
                        gender = gender
                    )
                    viewModel.addPet(newPet)
                    viewModel.fetchPets()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Pet")
            }
        }
    }
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}