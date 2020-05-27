package com.example.wwnewsapp

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class Albania : AppCompatActivity() {

    val arrayList = ArrayList<Model>()
    val displayList = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrayList.add(Model("Afghanistan", "This is Afghanistan description", R.drawable.afghanistan))
        arrayList.add(Model("Albania", "This is Albania description", R.drawable.albania))
        arrayList.add(Model("Algeria", "This is Algeria description", R.drawable.algeria))
        displayList.addAll(arrayList)

        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)
    }
}