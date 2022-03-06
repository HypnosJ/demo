package com.jacky.demohomework.ui.information

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jacky.demohomework.data.model.InformationRes
import com.jacky.demohomework.databinding.ItemInformationBinding

class InformationListAdapter(var informationList: InformationRes) :
    RecyclerView.Adapter<InformationListAdapter.InformationViewHolder>() {
    inner class InformationViewHolder(val binding: ItemInformationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InformationListAdapter.InformationViewHolder {
        val binding =
            ItemInformationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return InformationViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: InformationListAdapter.InformationViewHolder,
        position: Int
    ) {
        with(holder) {
            with(informationList) {
                binding.informationTitleTv.text = this.News[position].chtmessage
                binding.informationContentTv.text = this.News[position].content
            }
        }
    }

    override fun getItemCount(): Int {
        return informationList.News.size
    }
}