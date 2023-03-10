package com.mirostuyven.leto.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mirostuyven.leto.network.Quiz
import com.mirostuyven.leto.network.User


@Entity
data class DatabaseQuiz constructor(
    @PrimaryKey
    val id: String,
    val title: String,
    val creator: String
)

fun List<Quiz>.asDatabaseModel(): List<DatabaseQuiz> {
    return map {
        DatabaseQuiz(
            id = it.id,
            title = it.title,
            creator = it.creator.name
        )
    }
}

fun List<DatabaseQuiz>.asDomainModel(): List<Quiz> {
    return map {
        Quiz(
            id = it.id,
            title = it.title,
            creator = User("", it.creator),
            questions = listOf()
        )
    }
}