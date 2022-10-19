package com.example.ppa

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class StatsFragment : Fragment() {

    lateinit var sp:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sp = requireActivity().getSharedPreferences("my_sp",AppCompatActivity.MODE_PRIVATE)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats, container, false)
        val prog_rez = sp.getFloat("Rezistor",0f)
        val prog_ind = sp.getFloat("Inductor",0f)
        val prog_cond = sp.getFloat("Condensator",0f)
        val prog_tnb = sp.getFloat("TNB",0f)
        val prog_mos = sp.getFloat("MOS",0f)
        val prog_tec = sp.getFloat("TEC",0f)

        val params : FrameLayout.LayoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(0,0,0,0)
        params.gravity= Gravity.CENTER

        view.findViewById<ProgressBar>(R.id.progress_rez).progress=prog_rez.toInt()
        view.findViewById<ProgressBar>(R.id.progress_ind).progress=prog_ind.toInt()
        view.findViewById<ProgressBar>(R.id.progress_cond).progress=prog_cond.toInt()
        if(prog_rez<60)
        {
            view.findViewById<ImageView>(R.id.ind_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.img_cond).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.tnb_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.mos_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.tec_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<CardView>(R.id.C1).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.C2).setCardBackgroundColor(resources.getColor(R.color.grey,null))

            view.findViewById<ProgressBar>(R.id.progressBar2).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar3).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar4).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar5).progress=0




        }

        if(prog_cond<60f || prog_cond<60f)
        {
            view.findViewById<ProgressBar>(R.id.progressBar).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar6).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar7).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar8).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar9).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar10).progress=0
            view.findViewById<ImageView>(R.id.tnb_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.tnb_img).layoutParams=params

            view.findViewById<ImageView>(R.id.mos_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.mos_img).layoutParams=params
            view.findViewById<ImageView>(R.id.tec_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<CardView>(R.id.C5).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.C3).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.C4).setCardBackgroundColor(resources.getColor(R.color.grey,null))

        }

        if(prog_tnb<60f || prog_mos<60f || prog_tec<60f)
        {
            view.findViewById<ProgressBar>(R.id.progressBar11).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar12).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar13).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar14).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar15).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar16).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar17).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar18).progress=0
            view.findViewById<ProgressBar>(R.id.progressBar19).progress=0
            view.findViewById<ImageView>(R.id.amp1_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.amp1_img).layoutParams=params
            view.findViewById<ImageView>(R.id.amp2_img).setImageResource(R.drawable.ic_baseline_lock_24)


            view.findViewById<ImageView>(R.id.amp2_img).layoutParams=params

            view.findViewById<ImageView>(R.id.amp3_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.amp3_img).layoutParams=params
            view.findViewById<ImageView>(R.id.amp4_img).layoutParams=params
            view.findViewById<ImageView>(R.id.amp4_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.amp5_img).setImageResource(R.drawable.ic_baseline_lock_24)
            view.findViewById<ImageView>(R.id.amp5_img).layoutParams=params
            view.findViewById<CardView>(R.id.C6).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.C7).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.C8).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.C9).setCardBackgroundColor(resources.getColor(R.color.grey,null))
            view.findViewById<CardView>(R.id.C10).setCardBackgroundColor(resources.getColor(R.color.grey,null))
        }




        



        return view


    }


}
