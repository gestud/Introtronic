package com.example.ppa

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizzFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizzFragment : Fragment() {


    lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sp = requireActivity().getSharedPreferences("my_sp", AppCompatActivity.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quizz, container, false)
        val unlocked_txt  = view.findViewById<TextView>(R.id.unlocked)
        if(sp.getFloat("Rezistor",0f)>=60f)
        {
            unlocked_txt.text = unlocked_txt.text.toString().plus("\n Inductor \n Condensator")
        }

        if(sp.getFloat("Inductor",0f)>=60f && sp.getFloat("Condensator",0f)>=60f)
        {
            unlocked_txt.text = unlocked_txt.text.toString().plus("\n Tranzistor Bipolar \n Tranzistor MOS \n Tranzistor TEC-J")
        }


        return view
    }


}