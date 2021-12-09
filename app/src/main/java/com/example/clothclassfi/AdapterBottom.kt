package com.example.clothclassfi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import clothDB.BottomCloth
import clothDB.TopCloth
import com.example.clothclassfi.bottom_dialog.BottomDialogDetailActivity
import com.example.clothclassfi.databinding.BottomItemBinding
import com.example.clothclassfi.databinding.TopItemBinding
import com.example.clothclassfi.top_dialog.TopDialogDetailActivity

class AdapterBottom(private val itemList: MutableList<BottomCloth>):
        RecyclerView.Adapter<AdapterBottom.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = BottomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
                val intent = Intent(holder.itemView?.context, BottomDialogDetailActivity::class.java)//바텀 액티비티 만들고 바꿔!
                intent.putExtra("name", item.name)
                intent.putExtra("total", item.total)
                intent.putExtra("waist", item.waist)
                intent.putExtra("thigh", item.thigh)
                intent.putExtra("crotch", item.crotch)
                intent.putExtra("hem", item.hem)
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }
        }

        holder.bind(item)
    }

    class Holder(private val itemBinding: BottomItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind (item: BottomCloth) {
            itemBinding.tvName.text = item.name
            itemBinding.tvTotal.text = item.total
            itemBinding.tvWaist.text = item.waist
            itemBinding.tvThigh.text = item.thigh
            itemBinding.tvCrotch.text = item.crotch
            itemBinding.tvHem.text = item.hem
        }
    }
}