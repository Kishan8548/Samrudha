package com.samrudha.app.model

data class LanguageLocationResponse(
    val status_code: Int,
    val status_message: String,
    val tasks: List<Task>
)

data class Task(
    val result: Result
)

data class Result(
    val languages: List<Language>,
    val locations: List<Location>
)

data class Language(
    val code: String,
    val name: String
)

data class Location(
    val code: String,
    val name: String
)
