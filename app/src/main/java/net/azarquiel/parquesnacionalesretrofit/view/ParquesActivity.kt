package net.azarquiel.parquesnacionalesretrofit.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import net.azarquiel.parquesnacionalesretrofit.R
import net.azarquiel.parquesnacionalesretrofit.databinding.ActivityParquesBinding
import net.azarquiel.parquesnacionalesretrofit.model.Comunidad
import net.azarquiel.parquesnacionalesretrofit.model.Parque
import net.azarquiel.parquesnacionalesretrofit.viewmodel.MainViewModel
import net.azarquiel.recyclerviewpajaros.adapter.AdapterComunidad
import net.azarquiel.recyclerviewpajaros.adapter.AdapterParques

class ParquesActivity : AppCompatActivity() {

    private lateinit var adapter: AdapterParques
    private lateinit var comunidad: Comunidad
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityParquesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParquesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        if (intent.hasExtra("comunidad")) {
            comunidad = intent.getSerializableExtra("comunidad") as Comunidad
            title = "Parques de ${comunidad.nombre}"
            setSupportActionBar(binding.toolbar)
            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            viewModel.getParquesByIdComunidad(comunidad.id).observe(this, Observer {
                it?.let{
                    adapter.setParques(it)
                }
            })
        } else {
            title = "Parques"
            setSupportActionBar(binding.toolbar)
            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            viewModel.getParques().observe(this, Observer {
                it?.let{
                    adapter.setParques(it)
                }
            })
        }
    }
    private fun initRV() {
        adapter = AdapterParques(this, R.layout.rowparque)
        binding.cp.rvparques.adapter = adapter
        binding.cp.rvparques.layoutManager = LinearLayoutManager(this)
    }
    fun onClickParque(v: View) {
        val parque = v.tag as Parque
        val intent = Intent(this, DetailParque::class.java)
        intent.putExtra("parque", parque)
        startActivity(intent)
    }
    override fun onResume() {
        super.onResume()
        if (intent.hasExtra("comunidad")) {
            val comunidad = intent.getSerializableExtra("comunidad") as Comunidad
            viewModel.getParquesByIdComunidad(comunidad.id).observe(this, Observer {
                it?.let{
                    adapter.setParques(it)
                }
            })
        } else {
            viewModel.getParques().observe(this, Observer {
                it?.let{
                    adapter.setParques(it)
                }
            })
        }
    }

}