package com.mirostuyven.leto.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class User(
    val id: String,
    val name: String
) : Parcelable {}

@Parcelize
data class Quiz(
    val id: String,
    val title: String,
    val creator: User,
    val questions: @RawValue List<Question>?,
) : Parcelable {}

@Parcelize
data class Question(
    val id: String,
    val title: String,
    val answers: @RawValue List<Answer>?,
) : Parcelable {}

@Parcelize
data class Answer(
    val id: String,
    val title: String,
    val correct: Boolean?,
) : Parcelable {}
