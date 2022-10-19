package com.example.ppa

import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class TH2Fragment : Fragment() {

    private val theories : ArrayList<String> = ArrayList()
    private val images : ArrayList<ByteArray> = ArrayList()
    private lateinit var db : DBHelper
    lateinit var sp : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = DBHelper(requireContext(),null,1)
        sp = requireActivity().getSharedPreferences("my_sp", Context.MODE_PRIVATE)

        LoadList(0)
        LoadList(1)
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_th2, container, false)
        view.findViewById<TextView>(R.id.TH3).text=theories[0]
        view.findViewById<TextView>(R.id.TH4).text=theories[1]
        view.findViewById<ImageView>(R.id.POZA3).setImageBitmap(BitmapFactory.decodeByteArray(images[0], 0, images[0].size))


        view.findViewById<ImageView>(R.id.POZA3).setOnClickListener {
            var inflater = LayoutInflater.from(requireContext())
            var popup = inflater.inflate(R.layout.image_show,null,false)
            var image_show = popup.findViewById<ImageView>(R.id.img)
            image_show.setImageDrawable( view.findViewById<ImageView>(R.id.POZA3).drawable)
            var close = popup.findViewById<ImageView>(R.id.close)
            var builder = PopupWindow(popup,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,true)
            builder.showAtLocation(view.findViewById<ImageView>(R.id.POZA3), Gravity.CENTER,0,0)
            close.setOnClickListener {
                builder.dismiss()
            }
        }

        return view
    }

    private fun LoadList(p : Int)
    {
        var cursor: Cursor?

        var begin=0
        var end=0

        if(sp.getString("Nume Comp"," ")=="Rezistor")
        {
            begin=3
            end=4
        }


        if(sp.getString("Nume Comp"," ")=="Inductor")
        {
            begin=8
            end=9
        }

        if(sp.getString("Nume Comp"," ")=="Condensator")
        {
            begin=13
            end=14
        }

        if(p==0) {
            theories.clear()
            for(i in begin..end) {

                cursor = db.getCompTheory(requireActivity().getSharedPreferences("my_sp", AppCompatActivity.MODE_PRIVATE).getString("Nume Comp","null").toString(),i)
                if (cursor!!.count > 0) {
                    cursor!!.moveToFirst()
                    theories.add(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.THEORY_COL)))
                }
            }
        }



        if(p==1) {
            images.clear()

                cursor = db.getCompImg(requireActivity().getSharedPreferences("my_sp", AppCompatActivity.MODE_PRIVATE).getString("Nume Comp","null").toString(),begin)
                if (cursor!!.count > 0) {
                    cursor!!.moveToFirst()
                    images.add(cursor.getBlob(cursor.getColumnIndexOrThrow(DBHelper.IMG_COL)))
                }


        }
    }
}