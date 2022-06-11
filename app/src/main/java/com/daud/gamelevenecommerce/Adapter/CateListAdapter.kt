package com.daud.gamelevenecommerce.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Fragment.FragProductDetails
import com.daud.gamelevenecommerce.Fragment.FragFilterSearch
import com.daud.gamelevenecommerce.R

class CateListAdapter(private val context: Context, private val list: Array<String>):
    RecyclerView.Adapter<CateListAdapter.CateListVH>() {

    private var index: Int = -1

    inner class CateListVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val listItemTv: TextView = itemView.findViewById(R.id.listItemTv)
        val expandBtn: ImageButton = itemView.findViewById(R.id.expandBtn)
        val listItem: LinearLayout = itemView.findViewById(R.id.listItem)
        val listItemLay: LinearLayout = itemView.findViewById(R.id.listItemLay)
        ///////////////
        val cate1: LinearLayout = itemView.findViewById(R.id.cate1)
        val cate2: LinearLayout = itemView.findViewById(R.id.cate2)
        val cate3: LinearLayout = itemView.findViewById(R.id.cate3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateListVH {
        val view = LayoutInflater.from(context).inflate(R.layout.vh_cate_list,parent,false)
        return CateListVH(view)
    }

    override fun onBindViewHolder(holder: CateListVH, position: Int) {

        var adapterPos: Int = position

        if (index == adapterPos){
            holder.listItemLay.visibility = View.VISIBLE
            holder.expandBtn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
        } else {
            holder.listItemLay.visibility = View.GONE
            holder.expandBtn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
        }

        holder.listItemTv.text = list.get(position)

        holder.expandBtn.setOnClickListener(View.OnClickListener { view: View? ->
            expandBtnClickHandler(holder,adapterPos)
        })

        holder.listItem.setOnClickListener(View.OnClickListener{ view: View? ->
            listItemClickHandler()
        })

        /////////////////////////////////////////////////////////////////////////
        holder.cate1.setOnClickListener(View.OnClickListener { view: View? ->
            listItemsDemoClick()
        })

        holder.cate2.setOnClickListener(View.OnClickListener { view: View? ->
            listItemsDemoClick()
        })

        holder.cate3.setOnClickListener(View.OnClickListener { view: View? ->
            listItemsDemoClick()
        })
    }

    private fun listItemsDemoClick() {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setTransition(TRANSIT_FRAGMENT_OPEN).replace(R.id.mainFrame, FragProductDetails())
            .addToBackStack(null).commit()
    }

    private fun listItemClickHandler() {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setTransition(TRANSIT_FRAGMENT_OPEN).replace(R.id.mainFrame, FragFilterSearch())
            .addToBackStack(null).commit()
    }

    private fun expandBtnClickHandler(holder: CateListVH, adapterPos: Int) {
        if (holder.listItemLay.visibility==View.GONE){
            index = adapterPos
        }else{
            index = -1
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}