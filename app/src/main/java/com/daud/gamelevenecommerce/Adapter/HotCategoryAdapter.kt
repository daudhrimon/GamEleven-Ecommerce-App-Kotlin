package com.daud.gamelevenecommerce.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Fragment.FragProductDetails
import com.daud.gamelevenecommerce.Model.HotCModel
import com.daud.gamelevenecommerce.R

class HotCategoryAdapter(private val context: Context, private val list: MutableList<HotCModel>):
    RecyclerView.Adapter<HotCategoryAdapter.HotCategoryVH>() {

    inner class HotCategoryVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val hcImage: ImageView = itemView.findViewById(R.id.hcImage)
        val hcPrice: TextView = itemView.findViewById(R.id.hcPrice)
        val hcCard: LinearLayout = itemView.findViewById(R.id.hcCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotCategoryVH {
        val view = LayoutInflater.from(context).inflate(R.layout.vh_hot_categories,parent,false)
        return HotCategoryVH(view)
    }

    override fun onBindViewHolder(holder: HotCategoryVH, position: Int) {
        holder.hcImage.setImageResource(list.get(position).image)
        holder.hcPrice.text = (list.get(position).price)
        holder.hcCard.setBackgroundColor(Color.parseColor(list.get(position).color))

        holder.hcImage.setOnClickListener(View.OnClickListener { view: View? ->
            hcImageClickHandler()
        })
    }

    private fun hcImageClickHandler() {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragProductDetails()).addToBackStack(null).commit()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}