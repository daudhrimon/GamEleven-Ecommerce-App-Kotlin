package com.daud.gamelevenecommerce.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Fragment.FragProductDetails
import com.daud.gamelevenecommerce.Model.DailyFlashModel
import com.daud.gamelevenecommerce.R

class DailyFeatureAdapter(private val context: Context, private val list: MutableList<DailyFlashModel>):
    RecyclerView.Adapter<DailyFeatureAdapter.DailyFeatureVH>(){

    inner class DailyFeatureVH(itemView: View):RecyclerView.ViewHolder(itemView) {
        val dfImage: ImageView = itemView.findViewById(R.id.dfImage)
        val dfPrice: TextView = itemView.findViewById(R.id.dfPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyFeatureVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vh_daily_features,parent,false)
        return DailyFeatureVH(view)
    }

    override fun onBindViewHolder(holder: DailyFeatureVH, position: Int) {
        holder.dfImage.setImageResource(list.get(position).image)
        holder.dfPrice.text = (list.get(position).price)

        holder.dfImage.setOnClickListener(View.OnClickListener { view: View? ->
            dfImageClickHandler()
        })
    }

    private fun dfImageClickHandler() {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragProductDetails()).addToBackStack(null).commit()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}