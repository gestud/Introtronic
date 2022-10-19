package com.example.ppa

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream

class MeniuComp : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meniu_comp)


        val actBar=this.supportActionBar!!
        actBar.setDisplayHomeAsUpEnabled(true)





        val int_T = Intent(this, TheoryActivity::class.java)
        val int_test = Intent(this, TestActivity::class.java)
        findViewById<CardView>(R.id.Teorie).setOnClickListener {


            ContextCompat.startActivity(this, int_T, null)
        }

        findViewById<CardView>(R.id.Test).setOnClickListener {

            ContextCompat.startActivity(this, int_test, null)
        }


/*
        findViewById<ImageView>(R.id.POZA1).setImageBitmap(BitmapFactory.decodeByteArray(images[0], 0, images[0].size))
*/

    }





}