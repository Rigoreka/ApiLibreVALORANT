package com.example.apilibre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AgenteAdapter(private val agentes:List<agents>, private val onItemClick:(agents)->Unit):
        RecyclerView.Adapter<AgenteAdapter.AgenteViewHolder>(){

            inner class AgenteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                //Definir campos
                private val textName=itemView.findViewById<TextView>(R.id.txtAgente)
                //private val textDescript=itemView.findViewById<TextView>(R.id.txtDescrip)
                private val imgP=itemView.findViewById<ImageView>(R.id.imgAgente)



                fun bind(agents: agents){
                    textName.text=agents.Name
                    //textDescript.text=agents.Descrip
                    Glide.with(itemView.context)
                        .load(agents.Icon)
                        .into(imgP)

                    itemView.setOnClickListener{
                        onItemClick(agents)
                    }
                }

            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgenteViewHolder {
                val view=LayoutInflater.from(parent.context)
                 .inflate(R.layout.listaagentes, parent,false)
                 return AgenteViewHolder(view)
                }

                 override fun getItemCount(): Int {
                    return agentes.size
                    }

                override fun onBindViewHolder(holder: AgenteViewHolder, position: Int) {
                   val agent=agentes[position]
                    holder.bind(agent)
                }
        }