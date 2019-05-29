package com.example.sqlitekotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        try
        {
            val myDatabase = this.openOrCreateDatabase("Musicians",Context.MODE_PRIVATE,null)

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR,age INT(2))")

           // myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('James',50)")
           // myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('murat',22)")
           // myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('kübra',23)")

          //  myDatabase.execSQL("UPDATE musicians SET age = 55 WHERE name = 'murat'")

          //  myDatabase.execSQL("DELETE FROM musicians WHERE name = 'kübra'")

            val cursor = myDatabase.rawQuery("SELECT * FROM musicians ",null)

            val nameIndex = cursor.getColumnIndex("name")
            val ageIndex  = cursor.getColumnIndex("age")

            cursor.moveToFirst()

            while(cursor != null)
            {
                println("Name: " + cursor.getString(nameIndex))
                println("Age:"+cursor.getInt(ageIndex))
                cursor.moveToNext()

            }
            if(cursor !=null)
            {
                cursor!!.close()

            }



        }
        catch(e : Exception)
        {
            e.printStackTrace()

        }


    }
}
