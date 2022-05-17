package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(var clickedItem: RecyclerViewActivity): RecyclerView.Adapter<ItemAdapter.ItemAdapterVH>(), Filterable {

    var itemList = ArrayList<ItemModel>()
    var itemListFilter = ArrayList<ItemModel>()

    fun setData(itemList: ArrayList<ItemModel>){
        this.itemList = itemList
        this.itemListFilter = itemList
        notifyDataSetChanged()
    }

    interface ClickedItem{
        fun clickedItem(itemModel: ItemModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterVH {
        return ItemAdapterVH(
            LayoutInflater.from(parent.context).inflate(R.layout.row_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemAdapterVH, position: Int) {
        var itemsList = itemList[position]
        holder.imageView.setImageResource(itemsList.image)
        holder.name.text = itemsList.name
        holder.desc.text = itemsList.desc

        holder.imageView.setOnClickListener{
            clickedItem.clickedItem(itemsList)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.findViewById<ImageView>(R.id.r_imageView)
        var name = itemView.findViewById<TextView>(R.id.text_name)
        var desc = itemView.findViewById<TextView>(R.id.text_desc)
    }

    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var filterResults= FilterResults()
                if(constraint == null || constraint.isEmpty()){
                    filterResults.count = itemListFilter.size
                    filterResults.values = itemListFilter
                } else {
                    var searchChar : String = constraint.toString().lowercase()
                    var itemsList = ArrayList<ItemModel>()

                    for(item in itemListFilter){
                        if(item.name.lowercase().contains(searchChar)){
                            itemsList.add(item)
                        }
                    }
                    filterResults.count = itemsList.size
                    filterResults.values = itemsList
                }

                return  filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                    itemList = results!!.values as ArrayList<ItemModel>
                    notifyDataSetChanged()
            }

        }
    }
}