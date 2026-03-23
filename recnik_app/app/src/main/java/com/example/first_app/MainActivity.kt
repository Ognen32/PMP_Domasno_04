package com.example.first_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    // 1. Дефинирање на мапата за речникот и листата за историја (врвот на класата)
    private val dictionaryMap = HashMap<String, String>()
    private val searchHistory = mutableListOf<String>()
    private lateinit var adapter: TagAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_twitter_searches)

        // 2. Иницијализација на елементите од XML
        val etSearch = findViewById<TextInputEditText>(R.id.etInputWord) // Поле за внес
        val etResult = findViewById<TextInputEditText>(R.id.etTranslation)   // Поле за резултат
        val btnSearch = findViewById<MaterialButton>(R.id.btnTranslate)   // Копчето SEARCH
        val rvTags = findViewById<RecyclerView>(R.id.rvTags)         // Листата долу

        // 3. Поставување на RecyclerView и Адаптерот
        adapter = TagAdapter(searchHistory)
        rvTags.layoutManager = LinearLayoutManager(this)
        rvTags.adapter = adapter

        // 4. Вчитај го речникот од assets (src/main/assets/en_mk_recnik.txt)
        loadDictionary()

        // 5. Логика за пребарување на клик
        btnSearch.setOnClickListener {
            val input = etSearch.text.toString().lowercase(Locale.ROOT).trim()

            if (input.isNotEmpty()) {
                val translation = dictionaryMap[input]

                if (translation != null) {
                    // Го прикажуваме преводот во второто поле
                    etResult.setText(translation)

                    // Го додаваме во историјата (Tagged Searches)
                    val historyEntry = "$input -> $translation"

                    // Проверка за да не се дуплираат исти пребарувања едно под друго
                    if (searchHistory.isEmpty() || searchHistory[0] != historyEntry) {
                        searchHistory.add(0, historyEntry) // Најновото оди најгоре
                        adapter.notifyItemInserted(0)
                        rvTags.scrollToPosition(0) // Автоматски скролај до новиот збор
                    }
                } else {
                    etResult.setText("")
                    Toast.makeText(this, "Зборот не е пронајден во речникот!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ве молиме внесете збор за превод!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadDictionary() {
        try {
            // Отворање на фајлот од assets фолдерот
            val inputStream = assets.open("en_mk_recnik.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))

            reader.useLines { lines ->
                lines.forEach { line ->
                    // Сечење на линијата кај запирката (пр. morning, утро)
                    val parts = line.split(",")
                    if (parts.size == 2) {
                        val first = parts[0].trim().lowercase(Locale.ROOT)
                        val second = parts[1].trim().lowercase(Locale.ROOT)

                        // Овозможуваме пребарување во двата правци: EN->MK и MK->EN
                        dictionaryMap[first] = second
                        dictionaryMap[second] = first
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Грешка при вчитавање на речникот!", Toast.LENGTH_LONG).show()
        }
    }
}