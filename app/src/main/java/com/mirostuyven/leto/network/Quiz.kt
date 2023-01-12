package com.mirostuyven.leto.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quiz (
        val id: String,
        val title: String,
        val creator: User,
    ) : Parcelable {
}