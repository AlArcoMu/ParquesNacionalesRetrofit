package net.azarquiel.parquesnacionalesretrofit.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Paco Pulido.
 */

object WebAccess {

    val parquesNaturalesService : ParquesNaturalesService by lazy {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://www.ies-azarquiel.es/paco/apiparques/")
            .build()

        return@lazy retrofit.create(ParquesNaturalesService::class.java)
    }
}