package com.example.wwnewsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*

class MyAdapter (val arrayList: ArrayList<Model>, val context: Context ) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(model: Model) {

            itemView.titleTv.text = model.title
            itemView.descriptionTv.text = model.des
            itemView.imageIv.setImageResource(model.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener {

            // create intent in kotlin
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra("Afghanistan", arrayList)
                putExtra("Albania", arrayList)
            }
            // start another activity
            context.startActivity(intent)
        }
    }
}