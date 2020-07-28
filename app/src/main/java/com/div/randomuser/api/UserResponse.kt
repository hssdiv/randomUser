package com.div.randomuser.api

import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("results")
    var results: List<UserResults>? = null

}