package com.example.taskapi.petViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapi.interfaceApiPet.PetApiInterface
import com.example.taskapi.model.Pet
import com.example.taskapi.petSingleton.RetrofitHelperPet
import com.example.taskapi.repo.PetRepo
import kotlinx.coroutines.launch

class PetViewModel : ViewModel() {
    private val petApiService = RetrofitHelperPet
        .getInstance()
        .create(PetApiInterface::class.java)
    private val repository = PetRepo(petApiService)

    var pets by mutableStateOf(listOf<Pet>())

    init {
        fetchPets()
    }

    fun fetchPets() {
        viewModelScope.launch {
            try {
                pets = repository.getAllPets()
            } catch (e: Exception) {
                println("HELLO ${e}")
            }
        }
    }
}