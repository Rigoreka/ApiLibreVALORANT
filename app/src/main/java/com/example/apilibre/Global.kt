package com.example.apilibre

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import com.google.gson.annotations.SerializedName


data class agents(
  @SerializedName("displayName") val Name:String,
  @SerializedName("description") val Descrip:String,
  @SerializedName("displayIcon") val Icon:String

)
data class agentsResponse(
  @SerializedName("data") val data: List<agents>
)