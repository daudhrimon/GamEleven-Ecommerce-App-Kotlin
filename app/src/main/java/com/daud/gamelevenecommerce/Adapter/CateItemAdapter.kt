package com.daud.gamelevenecommerce.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Fragment.FragCategory
import com.daud.gamelevenecommerce.Model.CateItemModel
import com.daud.gamelevenecommerce.R

class CateItemAdapter(private val context: Context, private var list: MutableList<CateItemModel>):
    RecyclerView.Adapter<CateItemAdapter.CateItemVH>() {

    private var index: Int = -1

    inner class CateItemVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ciIamge: ImageView = itemView.findViewById(R.id.ciIamge)
        val ciName: TextView = itemView.findViewById(R.id.ciName)
        val cateItem: LinearLayout = itemView.findViewById(R.id.cateItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateItemVH {
        val view = LayoutInflater.from(context).inflate(R.layout.vh_cate_item,parent,false)
        return CateItemVH(view)
    }

    override fun onBindViewHolder(holder: CateItemVH, position: Int) {

        val adapterPosition:Int = position

        //this line will set the color of the selected position.
        if (index==adapterPosition){
            holder.cateItem.setBackgroundColor(Color.parseColor("#E8E8E8"))
        }else{
            holder.cateItem.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        //this line will set the list items for the first time...
        if (index==-1){
            when (adapterPosition){
                0 -> holder.cateItem.setBackgroundColor(Color.parseColor("#E8E8E8"))
            }
            FragCategory.listItemRecycler.adapter = FragCategory.data.getCateList1()?.let { CateListAdapter(context, it) }
        }

        holder.ciIamge.setImageResource(list.get(position).image)
        holder.ciName.text = (list.get(position).name)

        holder.cateItem.setOnClickListener(View.OnClickListener { view: View? ->
            index = adapterPosition

            holder.cateItem.setBackgroundColor(Color.parseColor("#E8E8E8"))
            cateItemClickHandler(adapterPosition)
            notifyDataSetChanged()
        })
    }

    private fun cateItemClickHandler(adapterPosition: Int) {
        when (list.get(adapterPosition).state){
            1-> FragCategory.listItemRecycler.adapter = FragCategory.data.getCateList1()?.let { CateListAdapter(context, it) }

            2-> FragCategory.listItemRecycler.adapter = FragCategory.data.getCateList2()?.let { CateListAdapter(context, it) }
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }
}