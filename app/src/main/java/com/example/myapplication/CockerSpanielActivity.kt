package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

class CockerSpanielActivity : AppCompatActivity() {

    var itemModel: ItemModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocker_spaniel)

        itemModel = intent.getSerializableExtra("data") as ItemModel

        val cockerSpanielView: ImageView = findViewById(R.id.cockerSpanielView)

        cockerSpanielView.setImageResource(itemModel!!.image)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.share){

            val shareIntent = Intent().apply{
                this.action = Intent.ACTION_SEND
                val textView : TextView = findViewById(R.id.cockerSpanielText)
                val text = textView.getText().toString()
                this.putExtra(Intent.EXTRA_TEXT, text)
                this.type = "text/plain"
            }
            startActivity(shareIntent)

        }
        else {
            return super.onOptionsItemSelected(item)
        }
        return true
    }
}