package net.azarquiel.parquesnacionalesretrofit.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.azarquiel.parquesnacionalesretrofit.R
import net.azarquiel.parquesnacionalesretrofit.databinding.ActivityMainBinding
import net.azarquiel.parquesnacionalesretrofit.model.Comunidad
import net.azarquiel.parquesnacionalesretrofit.model.Parque
import net.azarquiel.parquesnacionalesretrofit.viewmodel.MainViewModel
import net.azarquiel.recyclerviewpajaros.adapter.AdapterComunidad

class MainActivity : AppCompatActivity() {

    private var vaEspaña: String?=null
    private lateinit var parques: List<Parque>
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: AdapterComunidad
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title="Parques Nacionales De España"
        setSupportActionBar(binding.toolbar)
        initRV()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getComunidades().observe(this, Observer {
            it?.let{
                adapter.setComunidades(it)
            }
        })
    }

    private fun initRV() {
        adapter = AdapterComunidad(this, R.layout.rowcomunidad)
        binding.cm.rvcomunidades.adapter = adapter
        binding.cm.rvcomunidades.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    fun onClickComunidad(v: View) {
        val comunidad = v.tag as Comunidad
        val intent = Intent(this, ParquesActivity::class.java)
        intent.putExtra("comunidad", comunidad)
        startActivity(intent)
    }
    fun onClickEspaña(v: View) {
        val intent = Intent(this, ParquesActivity::class.java)
        startActivity(intent)
    }
}