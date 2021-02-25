package com.example.androiddevchallenge.model

import com.thedeanda.lorem.LoremIpsum
import java.util.UUID
import kotlin.random.Random

object PupRepository {

    private const val PUP_COUNT = 172

    private val pups by lazy { generatePupList() }
    private val lorem = LoremIpsum(1234567890L)
    private val random = Random(987654321L)

    private fun generatePupList(): List<Pup> {
        return List(PUP_COUNT) {
            Pup(
                UUID.randomUUID().toString(),
                lorem.firstName,
                lorem.getParagraphs(1, 1) + "\n\n" + lorem.getParagraphs(1, 1) + "\n\n" + lorem.getParagraphs(1, 1),
                "https://placedog.net/500?id=$it"
            )
        }.shuffled(random)
    }

    fun getPupList(): List<Pup> {
        return pups
    }

    fun getPup(id: String): Pup? {
        return pups.firstOrNull { it.id == id }
    }
}

data class Pup(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String
)