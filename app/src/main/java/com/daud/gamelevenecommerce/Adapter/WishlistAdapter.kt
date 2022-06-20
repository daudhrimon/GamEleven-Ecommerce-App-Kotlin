package com.daud.gamelevenecommerce.Adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Fragment.FragProductDetails
import com.daud.gamelevenecommerce.Model.WishlistModel
import com.daud.gamelevenecommerce.R

class WishlistAdapter(private val context: Context, private var list: MutableList<WishlistModel>):
    RecyclerView.Adapter<WishlistAdapter.WishlistVH>() {
    class WishlistVH(itemView: View):RecyclerView.ViewHolder(itemView) {
        val wishItem: LinearLayout = itemView.findViewById(R.id.wishItem)
        val wishDlt: LinearLayout = itemView.findViewById(R.id.wishDlt)
        val wishImage: ImageView = itemView.findViewById(R.id.wishImage)
        val wishName: TextView = itemView.findViewById(R.id.wishName)
        val wishCate: TextView = itemView.findViewById(R.id.wishCate)
        val wishPrice: TextView = itemView.findViewById(R.id.wishPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistVH {
        val view = LayoutInflater.from(context).inflate(R.layout.vh_wishlist,parent,false)
        return WishlistVH(view)
    }

    override fun onBindViewHolder(holder: WishlistVH, position: Int) {
        holder.wishImage.setImageResource(list.get(position).image)
        holder.wishName.text = list.get(position).name
        holder.wishCate.text = list.get(position).type
        holder.wishPrice.text = list.get(position).price

        holder.wishItem.setOnClickListener(View.OnClickListener { view: View? ->
            wishItemClickHandler()
        })

        holder.wishDlt.setOnClickListener(View.OnClickListener { view: View? ->
            wishDeleteClickHandler(holder)
        })
    }

    private fun wishItemClickHandler() {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragProductDetails()).addToBackStack(null).commit()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun wishDeleteClickHandler(holder: WishlistVH) {
        holder.wishDlt.setBackgroundResource(R.drawable.wd_select)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.aleartdialog_delete, null)
        val alertTv: TextView = view.findViewById(R.id.alertTv)

        alertTv.setText(R.string.alertDlt_wishlist)
        builder.setView(view)
        val dialog: Dialog = builder.create()
        dialog.show()

        val cancelBtn: Button = view.findViewById(R.id.cancelBtn)
        cancelBtn.setOnClickListener { view1: View? ->
            holder.wishDlt.setBackgroundResource(R.drawable.wd_unselect)
            dialog.dismiss()
        }

        val okBtn: Button = view.findViewById(R.id.okBtn)
        okBtn.setOnClickListener { view1: View? ->
            holder.wishDlt.setBackgroundResource(R.drawable.wd_unselect)
            dialog.dismiss()
        }
    }
}