package com.example.apitask.petViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitask.interfaceApiPet.PetApiInterface
import com.example.apitask.model.Pet
import com.example.apitask.petSingleton.RetrofitHelperPet
import com.example.apitask.repo.PetRepo
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

    fun addPet(pet: Pet) {
        viewModelScope.launch {
            try {
                var response = petApiService.addPet(pet)

                if (response.isSuccessful && response.body() != null) {
                    println("The pet added")
                } else {
                    println("Error")
                }
            } catch (e: Exception) {
                println("HELLO ${e}")
            }
        }
    }

    fun deletePet(petId : Int){
        viewModelScope.launch {
            try {
                var response = petApiService.deletePet(petId)

            }catch (e:Exception){

            }
        }
    }
}