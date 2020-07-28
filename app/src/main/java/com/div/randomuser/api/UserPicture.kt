package com.div.randomuser.api

import com.google.gson.annotations.SerializedName

class UserPicture {

    @SerializedName("large")
    var large: String? = null

    @SerializedName("thumbnail")
    var thumbnail: String? = null
}
