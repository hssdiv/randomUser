package com.div.randomuser.api

import com.google.gson.annotations.SerializedName

class UserName {
    @SerializedName("first")
    var first: String? = null

    @SerializedName("last")
    var last: String? = null
}
