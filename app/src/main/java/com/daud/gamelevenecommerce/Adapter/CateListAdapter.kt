package com.daud.gamelevenecommerce.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.R

class CateListAdapter(private val context: Context, private val list: Array<String>):
    RecyclerView.Adapter<CateListAdapter.CateListVH>() {

    inner class CateListVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val listItemTv: TextView = itemView.findViewById(R.id.listItemTv)
        val expandBtn: ImageButton = itemView.findViewById(R.id.expandBtn)
        val listItemLay: LinearLayout = itemView.findViewById(R.id.listItemLay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateListVH {
        val view = LayoutInflater.from(context).inflate(R.layout.vh_cate_list,parent,false)
        return CateListVH(view)
    }

    override fun onBindViewHolder(holder: CateListVH, position: Int) {
        holder.listItemTv.text = list.get(position)

        holder.expandBtn.setOnClickListener(View.OnClickListener { view: View? ->
            expandBtnClickHandler(holder)
        })
    }

    private fun expandBtnClickHandler(holder: CateListVH) {
        if (holder.listItemLay.visibility==View.GONE){
            holder.listItemLay.visibility = View.VISIBLE
            holder.expandBtn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
        }else{
            holder.listItemLay.visibility = View.GONE
            holder.expandBtn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}