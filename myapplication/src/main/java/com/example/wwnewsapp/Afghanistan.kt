package com.example.wwnewsapp

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class Afghanistan : AppCompatActivity() {

    val arrayList2 = ArrayList<Model>()
    val displayList = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)


//        arrayList2.add(Model("Afghanistan", "This is Afghanistan description", R.drawable.afghanistan))
//        arrayList2.add(Model("Albania", "This is Albania description", R.drawable.albania))
//        displayList.addAll(arrayList2)

    }
}