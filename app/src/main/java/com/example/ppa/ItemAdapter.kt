package com.example.ppa

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.persistableBundleOf

class ItemAdapter(private val context: Activity,private val itemsArray:List<Componenta>) : RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView:View):
        RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
       init{
           itemView.setOnClickListener(this)
       }

        override fun onClick(v:View?)
        {
            val position = adapterPosition
            if(position!= RecyclerView.NO_POSITION){


                    val int_c = Intent(context, MeniuComp::class.java)
                    startActivity(context, int_c, null)

            }
        }
    }

    override fun getItemCount(): Int {
        return itemsArray.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.list_view_custom,null)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {

        p0.itemView.findViewById<TextView>(R.id.item_name).text=itemsArray[p1].nume
        p0.itemView.findViewById<TextView>(R.id.item_prog).text=itemsArray[p1].progres.toString()+"%"
       // p0.itemView.findViewById<ImageView>(R.id.item_image).setImageBitmap(itemsArray[p1].imagine)
    }




    /*override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_view_custom, null)
        val adapter = this
        val itemImage: ImageView = view.findViewById(R.id.item_image)
        val itemProg: TextView = view.findViewById(R.id.item_prog)
        val itemName: TextView = view.findViewById(R.id.item_name)

        itemImage.setImageBitmap(itemsArray[position].imagine)
        itemName.text=itemsArray[position].nume
        itemProg.text=itemsArray[position].progres.toString()
        return view
    }*/

}