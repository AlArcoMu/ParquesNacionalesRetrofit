package net.azarquiel.parquesnacionalesretrofit.api


import net.azarquiel.parquesnacionalesretrofit.model.Comunidad
import net.azarquiel.parquesnacionalesretrofit.model.Imagen
import net.azarquiel.parquesnacionalesretrofit.model.Parque

class MainRepository() {
    val service = WebAccess.parquesNaturalesService

    suspend fun getComunidades(): List<Comunidad> {
        val webResponse = service.getComunidades().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comunidades
        }
        return emptyList()
    }
    suspend fun getParquesByIdComunidad(idcomunidad:Int): List<Parque> {
        val webResponse = service.getParquesByIdComunidad(idcomunidad).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.parques
        }
        return emptyList()
    }
    suspend fun getParques(): List<Parque> {
        val webResponse = service.getParques().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.parques
        }
        return emptyList()
    }
    suspend fun getImagenesByIdParque(idparque:Int): List<Imagen> {
        val webResponse = service.getImagenesByIdParque(idparque).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.imagenes
        }
        return emptyList()
    }
    suspend fun likeParque(idparque: Int): String {
        val webResponse = service.likeParque(idparque).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.msg
        }
        return ""
    }
}

