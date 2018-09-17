package get.gondai.demo.components.main

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import get.gondai.demo.App
import get.gondai.demo.R
import get.gondai.demo.models.DeliveryItem



class DeliveryListAdapter(var onClick:(i:DeliveryItem)->Unit): PagedListAdapter<DeliveryItem, DeliveryListAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<DeliveryItem>(){
            override fun areItemsTheSame(oldItem: DeliveryItem, newItem: DeliveryItem): Boolean
            = oldItem.id==newItem.id

            override fun areContentsTheSame(oldItem: DeliveryItem, newItem: DeliveryItem): Boolean
                    =oldItem==newItem


        }
) {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DeliveryListAdapter.ViewHolder
    = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_,parent,false))



    override fun onBindViewHolder(vh: DeliveryListAdapter.ViewHolder, position: Int) {
        getItem(position)?.let{
            vh.bindTo(it)
        }

    }

   inner class ViewHolder (v: View):RecyclerView.ViewHolder(v){
        var text1:TextView
        var image1:ImageView
        var buttonlocation:Button
        init {
            text1=itemView.findViewById(R.id.text1)
            image1=itemView.findViewById(R.id.image1)
            buttonlocation=itemView.findViewById(R.id.button1)
        }
        fun bindTo(item: DeliveryItem) {
           text1.setText(item.description)
            Glide.with(itemView.context)

                    .load(item.imageUrl).into(image1)



            buttonlocation.setOnClickListener{

                    this@DeliveryListAdapter.onClick(item)


            }
        }

    }
}