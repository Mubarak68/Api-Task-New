package com.example.apitask.repo

import com.example.apitask.interfaceApiPet.PetApiInterface

class PetRepo(private val api: PetApiInterface) {

    suspend fun getAllPets() = api.getAllPets()
}