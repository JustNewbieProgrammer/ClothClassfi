package com.example.clothclassfi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import clothDB.BottomCloth
import clothDB.OuterCloth
import clothDB.TopCloth
import com.example.clothclassfi.databinding.BottomItemBinding
import com.example.clothclassfi.databinding.TopItemBinding
import com.example.clothclassfi.outer_dialog.OuterDialogDetailActivity
import com.example.clothclassfi.top_dialog.TopDialogDetailActivity

class AdapterOuter(private val itemList: MutableList<OuterCloth>):
        RecyclerView.Adapter<AdapterOuter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = TopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]

        holder.itemView.setOnClickListener{
            if(position == 0) {}//0번은 제품이 아닌 해당 열의 이름이 쓰여있음(이름, 총장, 등등)
            else {
                val intent = Intent(holder.itemView?.context, OuterDialogDetailActivity::class.java)
                intent.putExtra("name", item.name)
                intent.putExtra("total", item.total)
                intent.putExtra("shoulder", item.shoulder)
                intent.putExtra("chest", item.chest)
                intent.putExtra("sleeve", item.sleeve)
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }
        }

        holder.bind(item)
    }

    class Holder(private val itemBinding: TopItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind (item: OuterCloth) {
            itemBinding.tvName.text = item.name
            itemBinding.tvTotal.text = item.total
            itemBinding.tvShoulder.text = item.shoulder
            itemBinding.tvChest.text = item.chest
            itemBinding.tvSleeve.text = item.sleeve
        }
    }
}