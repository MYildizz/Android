package com.example.kotlinlandmarkbook

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var landmarkNames =ArrayList<String>()
        landmarkNames.add("Pisa")
        landmarkNames.add("Colosseum")
        landmarkNames.add("Eiffel")
        landmarkNames.add("London Brige")
        landmarkNames.add("Anitkabir")

        val pisa =BitmapFactory.decodeResource(applicationContext.resources,R.drawable.pisa)
        val colosseum=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.colosseum)
        val eiffel =BitmapFactory.decodeResource(applicationContext.resources,R.drawable.eiffel)
        val londonBridge=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.london)
        val anitkabir=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.anitkabir)


        val landMarkImages=ArrayList<Bitmap>()
        landMarkImages.add(pisa)
        landMarkImages.add(colosseum)
        landMarkImages.add(eiffel)
        landMarkImages.add(londonBridge)
        landMarkImages.add(anitkabir)




        val arrayAdapter =ArrayAdapter(this,android.R.layout.simple_list_item_1,landmarkNames)
        listView.adapter=arrayAdapter

        listView.onItemClickListener=AdapterView.OnItemClickListener { parent, view, a, id ->
            val intent= Intent(applicationContext,DetailActivity::class.java)
            intent.putExtra("name",landmarkNames[a])
            val global = Globals()

            val bitmap =landMarkImages[a]

            val chosen = Globals.Chosen
            chosen.chosenImage=bitmap

            startActivity(intent)





        }


    }
}
