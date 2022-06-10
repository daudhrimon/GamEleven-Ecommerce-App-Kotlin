package com.daud.gamelevenecommerce.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Fragment.FragSearchFilter
import com.daud.gamelevenecommerce.R

class TextAdapter(private val context: Context, private var list: Array<String>):
    RecyclerView.Adapter<TextAdapter.TextVH>() {

    inner class TextVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tv: TextView = itemView.findViewById(R.id.tv)
        var textBtn: LinearLayout = itemView.findViewById(R.id.textBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vh_text_list,parent,false)
        return TextVH(view)
    }

    override fun onBindViewHolder(holder: TextVH, position: Int) {
        holder.tv.text = (list.get(position))

        holder.textBtn.setOnClickListener(View.OnClickListener { view: View? ->
            textBtnClickHandler()
        })

    }

    private fun textBtnClickHandler() {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragSearchFilter()).addToBackStack(null).commit()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}