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


class TH3Fragment : Fragment() {

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

        val view = inflater.inflate(R.layout.fragment_th3, container, false)
        view.findViewById<TextView>(R.id.TH5).text=theories[0]
        view.findViewById<ImageView>(R.id.POZA4).setImageBitmap(BitmapFactory.decodeByteArray(images[0], 0, images[0].size))

        view.findViewById<ImageView>(R.id.POZA4).setOnClickListener {
            var inflater = LayoutInflater.from(requireContext())
            var popup = inflater.inflate(R.layout.image_show,null,false)
            var image_show = popup.findViewById<ImageView>(R.id.img)
            image_show.setImageDrawable( view.findViewById<ImageView>(R.id.POZA4).drawable)
            var close = popup.findViewById<ImageView>(R.id.close)
            var builder = PopupWindow(popup,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,true)
            builder.showAtLocation(view.findViewById<ImageView>(R.id.POZA4), Gravity.CENTER,0,0)
            close.setOnClickListener {
                builder.dismiss()
            }
        }

        return view
    }

    private fun LoadList(p : Int)
    {
        var cursor: Cursor?

        var ind=0
        if(sp.getString("Nume Comp"," ")=="Rezistor")
        {
           ind = 5
        }


        if(sp.getString("Nume Comp"," ")=="Inductor")
        {
            ind = 10
        }

        if(sp.getString("Nume Comp"," ")=="Condensator")
        {
            ind = 15
        }

        if(p==0) {
            theories.clear()

                cursor = db.getCompTheory(requireActivity().getSharedPreferences("my_sp", AppCompatActivity.MODE_PRIVATE).getString("Nume Comp","null").toString(),ind)
                if (cursor!!.count > 0) {
                    cursor!!.moveToFirst()
                    theories.add(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.THEORY_COL)))
                }

        }



        if(p==1) {
            images.clear()

                cursor = db.getCompImg(requireActivity().getSharedPreferences("my_sp", AppCompatActivity.MODE_PRIVATE).getString("Nume Comp","null").toString(),ind-1)
                if (cursor!!.count > 0) {
                    cursor!!.moveToFirst()
                    images.add(cursor.getBlob(cursor.getColumnIndexOrThrow(DBHelper.IMG_COL)))
                }


        }
    }

}