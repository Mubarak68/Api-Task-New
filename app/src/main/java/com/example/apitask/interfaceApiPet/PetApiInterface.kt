package com.example.taskapi.interfaceApiPet

import com.example.taskapi.model.Pet
import retrofit2.http.GET

interface PetApiInterface {
    @GET("pets")
    suspend fun getAllPets(): List<Pet>
}