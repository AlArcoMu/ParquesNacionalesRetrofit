package net.azarquiel.recyclerviewpajaros.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.parquesnacionalesretrofit.R
import net.azarquiel.parquesnacionalesretrofit.model.Parque

/**
 * Created by pacopulido
 */
class AdapterParques(val context: Context,
                     val layout: Int
                    ) : RecyclerView.Adapter<AdapterParques.ViewHolder>() {

    private var dataList: List<Parque> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setParques(parques: List<Parque>) {
        this.dataList = parques
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Parque){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvRowParqueName = itemView.findViewById(R.id.tvRowParqueName) as TextView
            tvRowParqueName.text = dataItem.nombre
            val ivRowParque = itemView.findViewById(R.id.ivRowParque) as ImageView
            Picasso.get().load("${dataItem.imagen}").into(ivRowParque)
            itemView.tag = dataItem

        }

    }
}