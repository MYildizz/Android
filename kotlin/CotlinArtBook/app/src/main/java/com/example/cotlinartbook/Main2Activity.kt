package com.example.cotlinartbook

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.ByteArrayOutputStream
import java.lang.Exception

class Main2Activity : AppCompatActivity() {

    var selectedImage : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val intent = intent

        val info =intent.getStringExtra("info")

        if(info.equals("new"))
        {

        button.visibility=View.VISIBLE
            editText.setText("")


        }
        else
        {
            val name =intent.getStringExtra("name")
            editText.setText(name)
            val chosen=Globals.Chosen
            val bitmap=chosen.returnImage()
            imageView.setImageBitmap(bitmap)

            button.visibility=View.INVISIBLE

        }


    }

    fun select (view: View)
    {
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 2)

        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)
        }
    }
    override  fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults:IntArray)
        {
            if(requestCode==2)
            {
                if (grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(intent,1)

                }

            }
            super.onRequestPermissionsResult(requestCode,permissions,grantResults)
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode==1 && resultCode == Activity.RESULT_OK && data != null)
        {
            val image=data.data


            try {

                selectedImage=MediaStore.Images.Media.getBitmap(this.contentResolver,image)
                imageView.setImageBitmap(selectedImage)

            }
            catch (e: Exception)
            {
                e.printStackTrace()

            }

        }

        super.onActivityResult(requestCode, resultCode, data)
    }



    fun save(view: View)
    {
        val  artName = editText.text.toString()

        val outputStream = ByteArrayOutputStream()
        selectedImage?.compress(Bitmap.CompressFormat.PNG,50,outputStream)
        val byteArray = outputStream.toByteArray()

        try {
            val database= this.openOrCreateDatabase("Arts", Context.MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS arts(name VARCHAR,image BLOB)")

            val sqString ="INSERT INTO arts (name,image) VALUES (?,?)"
            val statement=database.compileStatement(sqString)

            statement.bindString(1,artName)
            statement.bindBlob(2,byteArray)
            statement.execute()

        }
        catch (e: Exception)
        {
            e.printStackTrace()

        }

        val intent = Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)


    }

}
