package com.example.taskapi.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.taskapi.model.Pet
import com.example.taskapi.petViewModel.PetViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PetListComposableScreen(viewModel: PetViewModel) {
    val pets = viewModel.pets
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Magenta)) {
        items(pets) { pet ->
            PetItems(pet)
        }
    }
}

@Composable
fun PetItems(
    pet: Pet
) {
    Card(
        modifier = Modifier
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
            AsyncImage(model = pet.image, contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .width(100.dp))
            Column {

                Text("Pet's ID: "+pet.id.toString())

                Text("Pet's Name: "+pet.name)

                Text("Pet's Gender: "+pet.gender)

                Text("Pet's Age: "+pet.age.toString())

                Text("Is adopted?: "+pet.adopted.toString())
            }
        }
    }
}
