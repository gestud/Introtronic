package com.example.ppa

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class TestActivity : AppCompatActivity() {

    lateinit var db : DBHelper
    lateinit var question : String
    lateinit var indexes : ArrayList<Int>
    lateinit var indexes_answers : ArrayList<Int>
    private lateinit var right_answer:String
    lateinit var sp :SharedPreferences
    lateinit var name : String
    var current_score : Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val actBar=this.supportActionBar!!
        actBar.setDisplayHomeAsUpEnabled(true)

        sp = getSharedPreferences("my_sp", MODE_PRIVATE)
        name = sp.getString("Nume Comp","null").toString()
        db=DBHelper(this,null,1)
        question= " "
        indexes= ArrayList()
        indexes_answers= ArrayList()

        var points=0

        var Q_num=0
        var from=0
        var to=0

        current_score = sp.getFloat(name,0f)

       /* for(i in 1..5)
        {

            while(indexes.contains(num))
            {
                num = (1..9).random()
            }
            indexes.add(num)
            */


        if(sp.getString("Nume Comp"," ")=="Rezistor")
        {
            from =1
            to =10
        }


        if(sp.getString("Nume Comp"," ")=="Inductor")
        {
            from =11
            to =20
        }

        if(sp.getString("Nume Comp"," ")=="Condensator")
        {
            from =21
            to =30
        }


        GenerateRandoms(indexes,from,to,5)
        GenerateRandoms(indexes_answers,1,4,4)
        LoadQA(indexes[Q_num])
        var parts = question.split(";")
        findViewById<TextView>(R.id.textIntrebare).text=parts[0]
        findViewById<RadioButton>(R.id.rA).text=parts[indexes_answers[0]]
        findViewById<RadioButton>(R.id.rB).text=parts[indexes_answers[1]]
        findViewById<RadioButton>(R.id.rC).text=parts[indexes_answers[2]]
        findViewById<RadioButton>(R.id.rD).text=parts[indexes_answers[3]]
        right_answer=parts[5]


        findViewById<Button>(R.id.verify).setOnClickListener {

             val selectedAnswer = findViewById<RadioGroup>(R.id.Answers).checkedRadioButtonId

            if(Q_num < 4) {
                if (selectedAnswer != -1) {
                    if (findViewById<RadioButton>(selectedAnswer).text == right_answer) {
                        points++
                    }
                    findViewById<RadioGroup>(R.id.Answers).clearCheck()
                    Q_num ++
                    LoadQA(indexes[Q_num])
                    GenerateRandoms(indexes_answers,1,4,4)
                    parts = question.split(";")
                    findViewById<TextView>(R.id.textIntrebare).text = parts[0]
                    findViewById<RadioButton>(R.id.rA).text=parts[indexes_answers[0]]
                    findViewById<RadioButton>(R.id.rB).text=parts[indexes_answers[1]]
                    findViewById<RadioButton>(R.id.rC).text=parts[indexes_answers[2]]
                    findViewById<RadioButton>(R.id.rD).text=parts[indexes_answers[3]]
                    right_answer = parts[5]



                } else
                    Toast.makeText(this, "Nu ai selectat niciun raspuns!", Toast.LENGTH_LONG).show()
            }
            else
            {
                    if (selectedAnswer != -1) {
                        if (findViewById<RadioButton>(selectedAnswer).text == right_answer) {
                            points++
                        }
                    }
                    else
                        Toast.makeText(this, "Nu ai selectat niciun raspuns!", Toast.LENGTH_LONG).show()
                val score = (points.toFloat()/5)*100

                if(score>=current_score)
                sp.edit().putFloat(name,score).apply()

                Toast.makeText(this,"Scor obtinut : "+score.toInt().toString()+ " %",Toast.LENGTH_LONG).show()
                val home = Intent(this,MeniuComp::class.java)
                startActivity(home)
            }


        }


    }

    private fun LoadQA(index:Int)
    {
        val cursor = db.getQuestion(name.toString(),index)

        if(cursor!!.count>0)
        {
            cursor.moveToFirst()
            question = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.Q_COL))

        }
    }

    private fun GenerateRandoms(list : ArrayList<Int>,from:Int,to:Int,count:Int)
    {
    list.clear()
        for(i in 1..count) {
            var j = 0
            var num = (from..to).random()
            while (j < list.size) {
                if (num == list[j]) {
                 num = (from..to).random()
                 j = 0
                } else
                    j++
            }
            list.add(num)
    }

    }

}