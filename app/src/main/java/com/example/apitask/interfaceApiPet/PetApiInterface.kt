package com.example.apitask.interfaceApiPet

import com.example.apitask.model.Pet
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PetApiInterface {
    @GET("pets")
    suspend fun getAllPets(): List<Pet>

    @POST("pets")
    suspend fun addPet(@Body pet: Pet): Response<Pet>

    @DELETE("pets/{petId}")
    suspend fun deletePet(@Path("petId") petId : Int): Response<Unit>
}