package com.dicoding.footballplayer

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.footballplayer.databinding.ItemRowHeroBinding

class ListHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
//        return ListViewHolder(view)
//    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i:Int) : ListViewHolder {
        val binding = ItemRowHeroBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listHero[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
//        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHero[holder.adapterPosition]) }
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_hero", listHero[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listHero.size

//    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
//        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
//        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
//    }

    class ListViewHolder(var binding: ItemRowHeroBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }
}