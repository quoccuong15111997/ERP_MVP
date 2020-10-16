package com.firstems.erp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.balysv.materialripple.MaterialRippleLayout
import com.firstems.erp.R
import com.firstems.erp.network.model.response.user.Company

class AdapterCompany(list: List<Company>) : RecyclerView.Adapter<AdapterCompany.ViewHolder>() {
    private lateinit var onClickCallback: (Company) -> Unit
    private var list = list
    fun setOnClickListentner(onClickCallback: (Company) -> Unit) {
        this.onClickCallback = onClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_company_select, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtCompName.setText(list[position].cOMPNAME)
        holder.materialRippleLayout.setOnClickListener {
            onClickCallback.invoke(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtCompName = itemView.findViewById<TextView?>(R.id.name)
        var cardViewParent = itemView.findViewById<CardView?>(R.id.cardViewParent)
        var materialRippleLayout = itemView.findViewById<MaterialRippleLayout?>(R.id.materialRippleLayout)
    }

}