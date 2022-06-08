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
import com.daud.gamelevenecommerce.Model.FlashDModel
import com.daud.gamelevenecommerce.R

class FlashDealsAdapter(private val context: Context, private val list: MutableList<FlashDModel>):
    RecyclerView.Adapter<FlashDealsAdapter.FlashDealsVH>() {

    inner class FlashDealsVH(itemView:View):RecyclerView.ViewHolder(itemView) {
        val fdImage: ImageView = itemView.findViewById(R.id.fdImage)
        val fdPrice: TextView = itemView.findViewById(R.id.fdPrice)
        val fdCard: LinearLayout = itemView.findViewById(R.id.fdCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashDealsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vh_flash_deals,parent,false)
        return FlashDealsVH(view)
    }

    override fun onBindViewHolder(holder: FlashDealsVH, position: Int) {
        holder.fdImage.setImageResource(list.get(position).image)
        holder.fdPrice.text = (list.get(position).price)
        holder.fdCard.setBackgroundColor(Color.parseColor(list.get(position).color))

        holder.fdImage.setOnClickListener(View.OnClickListener { view: View? ->
            fdImageClickHandler()
        })

    }

    private fun fdImageClickHandler() {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragProductDetails()).addToBackStack(null).commit()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}