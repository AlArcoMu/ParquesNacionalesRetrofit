package net.azarquiel.parquesnacionalesretrofit.api

import kotlinx.coroutines.Deferred
import net.azarquiel.parquesnacionalesretrofit.model.Respuesta
import retrofit2.Response
import retrofit2.http.*
/**
 * Created by Paco Pulido.
 */
interface ParquesNaturalesService {
    // No necesita nada para trabajar
    @GET("comunidades")
    fun getComunidades(): Deferred<Response<Respuesta>>

    @GET("parques")
    fun getParques(): Deferred<Response<Respuesta>>

    @GET("comunidad/{idcomunidad}/parques")
    fun getParquesByIdComunidad(@Path("idcomunidad") idcomunidad:Int): Deferred<Response<Respuesta>>

    @GET("parque/{idparque}/imagenes")
    fun getImagenesByIdParque(@Path("idparque") idparque:Int): Deferred<Response<Respuesta>>
    @PATCH("parque/{idparque}/like")
    fun likeParque(@Path("idparque") idparque: Int): Deferred<Response<Respuesta>>


// ……..   resto de métodos de la interfaz ………..
}
