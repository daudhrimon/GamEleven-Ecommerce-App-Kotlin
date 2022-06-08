package com.daud.gamelevenecommerce.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.R

class OnBoardingAdapter(private var images: IntArray, private val headers: IntArray):
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingVH>() {

    inner class OnBoardingVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val onImage: ImageView = itemView.findViewById(R.id.onImage)
        val onHeader: TextView = itemView.findViewById(R.id.onHeader)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lay_onboarding,parent,false);
        return OnBoardingVH(view)
    }

    override fun onBindViewHolder(holder: OnBoardingVH, position: Int) {
        holder.onImage.setImageResource(images.get(position))
        holder.onHeader.setText(headers.get(position))
    }

    override fun getItemCount(): Int {
        return headers.size
    }
}