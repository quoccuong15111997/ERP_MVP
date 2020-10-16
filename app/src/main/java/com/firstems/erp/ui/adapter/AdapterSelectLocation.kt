package com.firstems.erp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firstems.erp.R
import com.firstems.erp.network.model.response.user.Location

class AdapterSelectLocation(list: List<Location>) :
    RecyclerView.Adapter<AdapterSelectLocation.ViewHolder>() {
    private var list = list
    private lateinit var onClickCallback: (Location) -> Unit
    fun setOnClickListener(onClickCallback: (Location) -> Unit) {
        this.onClickCallback = onClickCallback
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var linearLayout = itemView.findViewById<LinearLayout?>(R.id.lAssets)
        var txtName = itemView.findViewById<TextView?>(R.id.txtLocatioName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location_select, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.setText(list[position].lCTNNAME)
        holder.linearLayout.setOnClickListener {
            onClickCallback.invoke(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}