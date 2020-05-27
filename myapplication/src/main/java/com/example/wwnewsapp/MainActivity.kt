package com.example.wwnewsapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    val arrayList = ArrayList<Model>()
    val displayList = ArrayList<Model>()
    lateinit var myAdapter: MyAdapter
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrayList.add(Model("Afghanistan", "This is Afghanistan description", R.drawable.afghanistan))
        arrayList.add(Model("Albania", "This is Albania description", R.drawable.albania))
        arrayList.add(Model("Algeria", "This is Algeria description", R.drawable.algeria))
        displayList.addAll(arrayList)

        myAdapter = MyAdapter(displayList, this)

        recyclerView.layoutManager = LinearLayoutManager (this )
        recyclerView.adapter = myAdapter

        preferences = getSharedPreferences("My_Pref", Context.MODE_PRIVATE)
        val mSortSetting = preferences.getString("Sort", "Ascending")

        if (mSortSetting == "Ascending"){
            sortAscending(myAdapter)

        } else if (mSortSetting == "Descending"){
            sortDescending(myAdapter)
        }
    }

    private fun sortDescending(myAdapter: MyAdapter) {
        displayList.sortWith(compareBy{ it.title })
        displayList.reverse()
        myAdapter.notifyDataSetChanged()

    }

    private fun sortAscending(myAdapter: MyAdapter) {
        displayList.sortWith(compareBy{ it.title })
        myAdapter.notifyDataSetChanged()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu!!.findItem(R.id.search)

        if(menuItem != null){

            val searchView = menuItem.actionView as SearchView

            val editText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            editText.hint = "Search..."

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText!!.isNotEmpty()){

                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        arrayList.forEach{

                            if(it.title.toLowerCase(Locale.getDefault()).contains(search)){
                                displayList.add(it)
                            }
                        }

                        recyclerView.adapter!!.notifyDataSetChanged()
                    }

                    else {

                        displayList.clear()
                        displayList.addAll(arrayList)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }

                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == R.id.sorting){
            sortDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortDialog() {
        val options = arrayOf("Ascending", "Descending")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sort By")
//        builder.setIcon(R.drawable.ic_action_sort)

        builder.setItems(options) {dialog, which ->

            if (which == 0){
                val editor : SharedPreferences.Editor = preferences.edit()
                editor.putString("Sort", "Ascending")
                editor.apply()
                sortAscending(myAdapter)
                Toast.makeText(this@MainActivity, "Ascending Order", Toast.LENGTH_LONG).show()

            }
            if (which == 1){
                val editor : SharedPreferences.Editor = preferences.edit()
                editor.putString("Sort", "Descending")
                editor.apply()
                sortDescending(myAdapter)
                Toast.makeText(this@MainActivity, "Descending Order", Toast.LENGTH_LONG).show()
            }
        }
        builder.create().show()
    }
}
