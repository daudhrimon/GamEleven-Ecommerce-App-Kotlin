package com.daud.gamelevenecommerce.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Fragment.FragProductDetails
import com.daud.gamelevenecommerce.Model.BrandsModel
import com.daud.gamelevenecommerce.R

class BrandsAdapter(private val context: Context, private val list: MutableList<BrandsModel>):
    RecyclerView.Adapter<BrandsAdapter.BrandsVH>() {

    inner class BrandsVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val bImage: ImageView = itemView.findViewById(R.id.bImage)
        val brand: ImageView = itemView.findViewById(R.id.brand)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsVH {
        val view = LayoutInflater.from(context).inflate(R.layout.vh_brands,parent,false)
        return BrandsVH(view)
    }

    override fun onBindViewHolder(holder: BrandsVH, position: Int) {
        holder.bImage.setImageResource(list.get(position).image)
        holder.brand.setImageResource(list.get(position).brand)

        holder.bImage.setOnClickListener(View.OnClickListener { view: View? ->
            bImageClickhandler()
        })
    }

    private fun bImageClickhandler() {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragProductDetails()).addToBackStack(null).commit()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}