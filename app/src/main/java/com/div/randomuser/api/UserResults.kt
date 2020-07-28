package com.div.randomuser.api

import com.div.randomuser.api.UserDateOfBirth
import com.div.randomuser.api.UserName
import com.google.gson.annotations.SerializedName

class UserResults {
    @SerializedName("name")
    var userName: UserName? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("dob")
    var dob: UserDateOfBirth? = null

    @SerializedName("picture")
    var picture: UserPicture? = null

    @SerializedName("login")
    var login: UserLogin? = null

}
