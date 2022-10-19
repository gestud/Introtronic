package com.example.ppa

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import java.io.ByteArrayOutputStream
import java.util.function.Supplier

class MainActivity : AppCompatActivity() {


    lateinit var toggle : ActionBarDrawerToggle

    val learnFragment = LearnFragment()
    val quizzFragment = QuizzFragment()
    val statsFragment = StatsFragment()

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val drawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        val navView:NavigationView=findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.container,learnFragment,"learn")
            add(R.id.container,quizzFragment,"quizz").hide(quizzFragment)
            add(R.id.container,statsFragment,"stats").hide(statsFragment)
        }.commit()
        var currentFragment : Fragment = learnFragment

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.learn -> {
                    supportFragmentManager.beginTransaction().hide(currentFragment).show(learnFragment).commit()
                    currentFragment=learnFragment
                    drawerLayout.close()
                }
                R.id.quizz -> {
                    supportFragmentManager.beginTransaction().hide(currentFragment).show(quizzFragment).commit()
                    currentFragment = quizzFragment
                    drawerLayout.close()
                }

                R.id.progress -> {
                    supportFragmentManager.beginTransaction().hide(currentFragment).show(statsFragment).commit()
                    currentFragment=statsFragment
                    drawerLayout.close()
                }
            }
            true
        }



        /*val layoutManager =
            LinearLayoutManager(this)
        layoutManager.orientation=
            LinearLayoutManager.VERTICAL
        val recyclerView : RecyclerView = this.findViewById(R.id.list)
        val itemDec = DividerItemDecoration(
            this,
            DividerItemDecoration.VERTICAL
        )

        adapter= ItemAdapter(this,list)

        recyclerView.layoutManager=layoutManager
        recyclerView.addItemDecoration(itemDec)

        recyclerView.adapter=adapter*/


    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item)
    }
}