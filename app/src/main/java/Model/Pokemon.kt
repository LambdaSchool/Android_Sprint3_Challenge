package Model

import com.google.gson.annotations.SerializedName

data class Pokemon(val name: String,
                   @SerializedName("front_default")
                   val spriteUrl: String,
                   val ID: Int,
                   @SerializedName("abilities")
                   val abilities: Array<String>)