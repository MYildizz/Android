package com.example.kotlintravelbook

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*





class MainActivity : AppCompatActivity() {

    var nameArray=ArrayList<String>()
    var locationArray=ArrayList<LatLng>()



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater =menuInflater
        menuInflater.inflate(R.menu.add_place,menu)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item!!.itemId==R.id.add_place)
        {
            val intent= Intent(applicationContext,MapsActivity::class.java)
            intent.putExtra("info","new")
            startActivity(intent)

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        try {

            val database=openOrCreateDatabase("Places", Context.MODE_PRIVATE,null)
            val cursor =database.rawQuery("SELECT * FROM places",null)

            val nameIndex=cursor.getColumnIndex("name")
            val latitudeIndex=cursor.getColumnIndex("latitude")
            val longitudeIndex=cursor.getColumnIndex("longitude")

            cursor.moveToFirst()

            nameArray.clear()
            locationArray.clear()

            while(cursor!= null)
            {
                val nameFromDatabase=cursor.getString(nameIndex)
                val latitudeFromDatabase=cursor.getString(latitudeIndex)
                val longitudeFromDatabase=cursor.getString(longitudeIndex)

                nameArray.add(nameFromDatabase)

                val latitudeCoordinate=latitudeFromDatabase.toDouble()
                val longitudeCoordinate=longitudeFromDatabase.toDouble()

                val location=LatLng(latitudeCoordinate,longitudeCoordinate)

                locationArray.add(location)


                cursor.moveToNext()

            }


        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }



        val arrayAdapter =ArrayAdapter(this,android.R.layout.simple_list_item_1,nameArray)
        listView.adapter=arrayAdapter



    }
}
