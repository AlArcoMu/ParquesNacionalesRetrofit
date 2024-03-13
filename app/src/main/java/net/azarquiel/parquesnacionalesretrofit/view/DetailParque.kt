package net.azarquiel.parquesnacionalesretrofit.view

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.squareup.picasso.Picasso
import net.azarquiel.parquesnacionalesretrofit.R
import net.azarquiel.parquesnacionalesretrofit.databinding.ActivityDetailParqueBinding
import net.azarquiel.parquesnacionalesretrofit.model.Comunidad
import net.azarquiel.parquesnacionalesretrofit.model.Imagen
import net.azarquiel.parquesnacionalesretrofit.model.Parque
import net.azarquiel.parquesnacionalesretrofit.viewmodel.MainViewModel

class DetailParque : AppCompatActivity() {

    private lateinit var tvDetailParqueNumeroLikes: TextView
    private lateinit var imagenes: List<Imagen>
    private lateinit var viewModel: MainViewModel
    private lateinit var parque: Parque
    private lateinit var binding: ActivityDetailParqueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailParqueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        parque = intent.getSerializableExtra("parque") as Parque

        binding.fab.setOnClickListener {
            postLike()
        }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getImagenesByIdParque(parque.id).observe(this, Observer {
            it?.let{imagenes ->
                this.imagenes = imagenes
                showDatos()
            }
        })
    }

    private fun postLike() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.likeParque(parque.id).observe(this, Observer {
            it?.let{
                parque.likes += 1
                refreshLikes()
            }
        })
    }

    private fun refreshLikes() {
        tvDetailParqueNumeroLikes.text=parque.likes.toString()
    }

    private fun showDatos() {
        Picasso.get().load("${parque.mapa}").into(binding.cdp.ivDetailParqueMapa)
        Picasso.get().load("${parque.fondo}").into(binding.cdp.ivDetailParquueMapaFondo)
        binding.cdp.tvDescripcionDetailParque.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(parque.descripcion , Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(parque.descripcion , Html.FROM_HTML_MODE_LEGACY)
        }
        binding.cdp.tvDetailParqueTitulo.text=parque.nombre
        tvDetailParqueNumeroLikes=binding.cdp.tvDetailParqueNumeroLikes
        tvDetailParqueNumeroLikes.text=parque.likes.toString()
        imagenes.forEach {
            val iv = ImageView(this)
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 700)
            lp.setMargins(0,0, 10, 0)
            iv.layoutParams = lp
            Picasso.get().load(it.foto).into(iv)
            binding.cdp.linearfotos.addView(iv)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}