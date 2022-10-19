package com.example.ppa

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.ByteArrayOutputStream

class TheoryActivity : AppCompatActivity() {

    private val Th1 = TH1Fragment()
    private val Th2 = TH2Fragment()
    private val Th3 = TH3Fragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_teorie)

        val actBar=this.supportActionBar!!
        actBar.setDisplayHomeAsUpEnabled(true)
        findViewById<TextView>(R.id.NUME).text = getSharedPreferences("my_sp", MODE_PRIVATE).getString("Nume Comp","null")



        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.container_th,Th1,"TH1")
            add(R.id.container_th,Th2,"TH2").hide(Th2)
            add(R.id.container_th,Th3,"TH3").hide(Th3)
        }.commit()

        var currentfrag : Fragment = Th1

        bottom_nav.setOnItemSelectedListener {

            when(it.itemId)
            {
                R.id.frag1->
                {
                  supportFragmentManager.beginTransaction().hide(currentfrag).show(Th1).commit()
                  currentfrag= Th1

                }

                R.id.frag2->
                {
                    supportFragmentManager.beginTransaction().hide(currentfrag).show(Th2).commit()
                    currentfrag=Th2
                }

                R.id.frag3->
                {
                    supportFragmentManager.beginTransaction().hide(currentfrag).show(Th3).commit()
                    currentfrag=Th3
                }

            }
            true
        }



    }



}