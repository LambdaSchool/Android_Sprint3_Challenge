package com.ali.pokemonapp.model



data class Pokemon(val name:String,val id:Int,val sprites: Sprites,val abilities: List<AbilityList>,val types: List<TypesList>)

data class Ability(

    val name: String

)
data class Type(

    val name: String

)
data class AbilityList(

    val ability: Ability

)
data class TypesList(

    val type: Type

)

data class Sprites (

    val front_default: String

)

