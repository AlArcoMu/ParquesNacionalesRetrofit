package net.azarquiel.parquesnacionalesretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.parquesnacionalesretrofit.api.MainRepository
import net.azarquiel.parquesnacionalesretrofit.model.Comunidad
import net.azarquiel.parquesnacionalesretrofit.model.Imagen
import net.azarquiel.parquesnacionalesretrofit.model.Parque


// ……

/**
 * Created by Paco Pulido.
 */
class MainViewModel : ViewModel() {

    private var repository: MainRepository = MainRepository()

    fun getComunidades(): MutableLiveData<List<Comunidad>> {
        val comunidades = MutableLiveData<List<Comunidad>>()
        GlobalScope.launch(Main) {
            comunidades.value = repository.getComunidades()
        }
        return comunidades
    }
    fun getParquesByIdComunidad(idcomunidad:Int): MutableLiveData<List<Parque>> {
        val parques = MutableLiveData<List<Parque>>()
        GlobalScope.launch(Main) {
            parques.value = repository.getParquesByIdComunidad(idcomunidad)
        }
        return parques
    }
    fun getParques(): MutableLiveData<List<Parque>> {
        val parques = MutableLiveData<List<Parque>>()
        GlobalScope.launch(Main) {
            parques.value = repository.getParques()
        }
        return parques
    }
    fun getImagenesByIdParque(idparque:Int): MutableLiveData<List<Imagen>> {
        val imagenes = MutableLiveData<List<Imagen>>()
        GlobalScope.launch(Main) {
            imagenes.value = repository.getImagenesByIdParque(idparque)
        }
        return imagenes
    }
    fun likeParque(idparque: Int): MutableLiveData<String> {
        val mensaje = MutableLiveData<String>()
        GlobalScope.launch(Main) {
            mensaje.value=repository.likeParque(idparque)
        }
        return mensaje
    }
}
