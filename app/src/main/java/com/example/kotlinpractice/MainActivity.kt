package com.example.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() , RecyclerViewAdapter.OnItemClickListener {

    private val exampleList = generateDummyList(500)
    private val adapter = RecyclerViewAdapter(exampleList , this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val recycler_view : RecyclerView = findViewById(R.id.recycler_view)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    fun insertItem(view : View) {

        val index = Random.nextInt(5)

        val newItem = ExampleItem(
            R.drawable.ic_baseline_android_24,
            "New Item at position $index",
            "Line 2"
        )

        exampleList.add(index , newItem)
        adapter.notifyItemInserted(index)


    }
    fun removeItem(view : View) {
        val index = Random.nextInt(8)
        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = exampleList[position]
        clickedItem.text1 = "clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_baseline_android_24
                1 -> R.drawable.ic_audio
                else -> R.drawable.ic_sun
            }
            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
}