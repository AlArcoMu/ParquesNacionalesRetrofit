package net.azarquiel.parquesnacionalesretrofit.model

import java.io.Serializable

data class Comunidad(var id:Int, var nombre:String):Serializable
data class Parque(var id:Int,var nombre: String,var comunidad: Int,var imagen:String,var mapa:String,var fondo:String,var descripcion:String,var likes:Int):Serializable
data class Imagen(var id:Int,var parque:Int,var foto: String):Serializable
data class Respuesta(
    var comunidades:List<Comunidad>,
    var parques:List<Parque>,
    var imagenes:List<Imagen>,
    var msg:String
)