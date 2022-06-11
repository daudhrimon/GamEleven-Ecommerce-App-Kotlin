package com.daud.gamelevenecommerce.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Fragment.FragProductDetails
import com.daud.gamelevenecommerce.Model.CartModel
import com.daud.gamelevenecommerce.R

class CartAdapter(private val context: Context, private var list: MutableList<CartModel>):
    RecyclerView.Adapter<CartAdapter.CartVH>() {

    class CartVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cartItem: RelativeLayout = itemView.findViewById(R.id.cartItem)
        val cartImage: ImageView = itemView.findViewById(R.id.cartImage)
        val cartName: TextView = itemView.findViewById(R.id.cartName)
        val cartCate: TextView = itemView.findViewById(R.id.cartCate)
        val cartId: TextView = itemView.findViewById(R.id.cartId)
        val cartPrice: TextView = itemView.findViewById(R.id.cartPrice)
        val cartCount: TextView = itemView.findViewById(R.id.cartCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartVH {
        val view = LayoutInflater.from(context).inflate(R.layout.vh_cart,parent,false)
        return CartVH(view)
    }

    override fun onBindViewHolder(holder: CartVH, position: Int) {
        holder.cartImage.setImageResource(list.get(position).image)
        holder.cartName.text = (list.get(position).name)
        holder.cartCate.text = (list.get(position).type)
        holder.cartId.text = (list.get(position).id)
        holder.cartPrice.text = (list.get(position).price)
        holder.cartCount.text = (list.get(position).count.toString())

        holder.cartItem.setOnClickListener(View.OnClickListener { view: View? ->
            cartItemClickHandler()
        })
    }

    private fun cartItemClickHandler(){
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragProductDetails()).addToBackStack(null).commit()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}