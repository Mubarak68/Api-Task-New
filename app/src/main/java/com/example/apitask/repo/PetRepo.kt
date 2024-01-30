package com.example.taskapi.repo

import com.example.taskapi.interfaceApiPet.PetApiInterface

class PetRepo(private val api: PetApiInterface) {

    suspend fun getAllPets() = api.getAllPets()
}