package com.daud.gamelevenecommerce.Adapter

import android.content.Context
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
import com.daud.gamelevenecommerce.Model.AdsModel
import com.daud.gamelevenecommerce.R

class AdsAdapter(private val context: Context, private val list: MutableList<AdsModel>):
    RecyclerView.Adapter<AdsAdapter.AdsVH>() {

    inner class AdsVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val adsImage: ImageView = itemView.findViewById(R.id.adsImage)
        val adsName: TextView = itemView.findViewById(R.id.adsName)
        val adsType: TextView = itemView.findViewById(R.id.adsType)
        val adsPrice: TextView = itemView.findViewById(R.id.adsPrice)
        val adsItem: LinearLayout = itemView.findViewById(R.id.adsItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsVH {
        val view = LayoutInflater.from(context).inflate(R.layout.vh_ads,parent,false)
        return AdsVH(view)
    }

    override fun onBindViewHolder(holder: AdsVH, position: Int) {
        holder.adsImage.setImageResource(list.get(position).image)
        holder.adsName.text = list.get(position).name
        holder.adsType.text = (list.get(position).type)
        holder.adsPrice.text = (list.get(position).price)

        holder.adsItem.setOnClickListener(View.OnClickListener { view: View? ->
            adsItemClickHandler()
        })
    }

    private fun adsItemClickHandler() {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragProductDetails()).addToBackStack(null).commit()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}