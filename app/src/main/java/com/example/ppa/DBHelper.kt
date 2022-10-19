package com.example.ppa
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context,factory: SQLiteDatabase.CursorFactory?,val type:Int)
    :SQLiteOpenHelper(context,DATABASE_NAME,factory, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {



            val query1 = ("CREATE TABLE " + TABLE_TEORIE +
                    " (" +ID_COL + " INTEGER PRIMARY KEY, "
                    + NAME_COl + " TEXT, "
                    + THEORY_COL + " TEXT, "
                    + IMG_COL + " MEDIUMBLOB )")
            db.execSQL(query1)


            val query2 = ("CREATE TABLE " + TABLE_TEST +
                    " (" +ID_COL + " INTEGER PRIMARY KEY, "
                    + NAME_COl + " TEXT, "
                    + Q_COL + " TEXT)")
            db.execSQL(query2)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

           onCreate(db)
           db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEORIE)
           db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST)

    }

    fun addData(name : String,theory:String,img : ByteArray)
    {
        val values = ContentValues()

        values.put(NAME_COl,name)
        values.put(THEORY_COL,theory)
        values.put(IMG_COL,img)
        val db = this.writableDatabase
        db.insert(TABLE_TEORIE,null,values)

        db.close()
    }

    fun addQUESTION(name : String,q:String)
    {
        val values = ContentValues()

        values.put(NAME_COl,name)
        values.put(Q_COL,q)

        val db = this.writableDatabase
        db.insert(TABLE_TEST,null,values)

        db.close()
    }




    fun getCompData(name : String):Cursor?
    {
        val db= this.readableDatabase

        return db.rawQuery("SELECT * FROM Teorie WHERE name = '" + name + "' " ,null)
    }

    fun getCompTheory(name : String, index:Int):Cursor?
    {
        val db= this.readableDatabase

        return db.rawQuery("SELECT theory FROM Teorie WHERE name = '" + name + "' AND id = "+ index ,null)
    }

    fun getCompImg(name : String, index:Int):Cursor?
    {
        val db= this.readableDatabase

        return db.rawQuery("SELECT image FROM Teorie WHERE name = '" + name + "' AND id = "+ index ,null)
    }

    fun getQuestion(name:String, index:Int):Cursor?
    {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM Test WHERE name = '"+name+ "' AND id = "+ index,null)
    }


   /* fun getItemImage(name: String,owner: String):Cursor?
    {
        val db= this.readableDatabase
        return db.rawQuery("SELECT "+ IMG_COL + " FROM items WHERE owner = '"+owner+"' AND "+ NAME_COl + " = '"+name+"'",null)
    }
    */

    companion object
    {

        private val DATABASE_NAME = "electrobase"

        private val DATABASE_VERSION = 1

        val TABLE_TEST = "Test"
        val TABLE_TEORIE = "Teorie"

//TAB TEORIE

        val ID_COL = "id"

        val NAME_COl = "name"

        val THEORY_COL = "theory"

        val IMG_COL = "image"
//TAB INTREBARI
        val Q_COL = "question"





    }
}