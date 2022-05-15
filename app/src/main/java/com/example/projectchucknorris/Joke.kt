package com.example.projectchucknorris

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Joke(
    val categories: List<String> = emptyList(),
    @SerialName("created_at") val createdAt: String = "2020-01-05 13:42:26.766831",
    @SerialName("icon_url") val iconUrl: String = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
    val id: String = "pyNXTV7WThiNLRykGsQmrg",
    @SerialName("updated_at") val updatedAt: String = "2020-01-05 13:42:26.766831",
    val url: String = "https://api.chucknorris.io/jokes/pyNXTV7WThiNLRykGsQmrg",
    val value: String
)
