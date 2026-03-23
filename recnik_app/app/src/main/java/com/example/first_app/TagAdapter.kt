package com.example.first_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class TagAdapter(private val tags: List<String>) : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

    // ViewHolder класата ги „држи“ референците до елементите во секој ред од листата
    class TagViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Го користиме ID-то од твојот item_tag.xml
        val btnTagName: TextView = view.findViewById(R.id.btnTagName)

        // Овие ги чуваме ако постојат во XML-от, ако не ги користиш моментално, не пречат
        val btnEdit: MaterialButton? = view.findViewById(R.id.btnEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        // Го „надувуваме“ изгледот за еден ред (item_tag.xml)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tag, parent, false)
        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        // Тука го поставуваме текстот (пр. "morning -> утро") во картичката
        holder.btnTagName.text = tags[position]

        // Тука можеш да додадеш логика за копчето Edit ако ти треба во иднина
        holder.btnEdit?.setOnClickListener {
            // Логика за измена
        }
    }

    override fun getItemCount() = tags.size
}