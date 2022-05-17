package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {
    var itemList = arrayOf(
        ItemModel(R.drawable.beagle, "Beagle", "Despre Beagle") ,
        ItemModel(R.drawable.chow_chow_rough, "Chow Chow", "Despre Chow Chow"),
        ItemModel(R.drawable.cocker_spaniel, "Cocker Spaniel", "Despre Cocker Spaniel"),
        ItemModel(R.drawable.corgi, "Corgi", "Despre Corgi"),
        ItemModel(R.drawable.dobermann, "Dobermann", "Despre Dobermann"),
        ItemModel(R.drawable.golden_retriever, "Golden Retriever", "Despre Golden Retriever"),
        ItemModel(R.drawable.great_dane, "Great Dane", "Despre Great Dane"),
        ItemModel(R.drawable.labrador_retriever, "Labrador", "Despre Labrador")
    )

    var itemsList = ArrayList<ItemModel>()
    var itemAdapter: ItemAdapter? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        for(item in itemList){
            itemsList.add(item)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        itemAdapter = ItemAdapter(this)
        itemAdapter!!.setData(itemsList)
        recyclerView.adapter = itemAdapter

    }

    fun clickedItem(itemModel1: ItemModel) {
        var itemsModel1 = itemsList
        var name = itemModel1.name

        when(name){
            "Beagle" -> startActivity(Intent(this@RecyclerViewActivity, BeagleActivity::class.java).putExtra("data", itemModel1))
            "Chow Chow" -> startActivity(Intent(this@RecyclerViewActivity, ChowChowActivity::class.java).putExtra("data", itemModel1))
            "Cocker Spaniel" -> startActivity(Intent(this@RecyclerViewActivity, CockerSpanielActivity::class.java).putExtra("data", itemModel1))
            "Corgi" -> startActivity(Intent(this@RecyclerViewActivity, CorgiActivity::class.java).putExtra("data", itemModel1))
            "Dobermann" -> startActivity(Intent(this@RecyclerViewActivity, DobermannActivity::class.java).putExtra("data", itemModel1))
            "Golden Retriever" -> startActivity(Intent(this@RecyclerViewActivity, GoldenRetrieverActivity::class.java).putExtra("data", itemModel1))
            "Great Dane" -> startActivity(Intent(this@RecyclerViewActivity, GreatDaneActivity::class.java).putExtra("data", itemModel1))
            "Labrador" -> startActivity(Intent(this@RecyclerViewActivity, LabradorActivity::class.java).putExtra("data", itemModel1))

            else -> {
                Toast.makeText(this@RecyclerViewActivity, "No action", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        var menuItem = menu!!.findItem(R.id.searchDog)
        var searchDog = menuItem.actionView as SearchView

        searchDog.maxWidth = Int.MAX_VALUE

        searchDog.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                itemAdapter!!.filter.filter(newText)
                return true
            }

        })

        return true
    }

}