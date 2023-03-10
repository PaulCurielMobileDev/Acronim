package com.mexicandeveloper.acronyminitialism.models

import com.google.gson.annotations.SerializedName

data class AbbreviationResponse(
    @SerializedName("sf"  ) var sf  : String?        = null,
    @SerializedName("lfs" ) var lfs : ArrayList<Lfs> = arrayListOf()
)
data class Lfs (

    @SerializedName("lf"    ) var lf    : String?         = null,
    @SerializedName("freq"  ) var freq  : Int?            = null,
    @SerializedName("since" ) var since : Int?            = null,
    @SerializedName("vars"  ) var vars  : ArrayList<Vars> = arrayListOf()

)
data class Vars (

    @SerializedName("lf"    ) var lf    : String? = null,
    @SerializedName("freq"  ) var freq  : Int?    = null,
    @SerializedName("since" ) var since : Int?    = null

)