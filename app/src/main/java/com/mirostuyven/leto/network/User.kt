package com.mirostuyven.leto.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (val id: String, val name: String) : Parcelable {

}