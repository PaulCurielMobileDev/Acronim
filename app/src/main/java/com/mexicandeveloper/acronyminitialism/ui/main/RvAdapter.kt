package com.mexicandeveloper.acronyminitialism.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mexicandeveloper.acronyminitialism.R
import com.mexicandeveloper.acronyminitialism.databinding.RowAbreviationBinding
import com.mexicandeveloper.acronyminitialism.models.RowToShowModel

class RvAdapter(var rowInfoList: List<RowToShowModel>) : Adapter<RvAdapter.TheHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateInfo(newInfo: List<RowToShowModel>) {
        rowInfoList = newInfo
        notifyDataSetChanged()
    }

    inner class TheHolder(private val binding: RowAbreviationBinding) : ViewHolder(binding.root) {
        fun bind(rowInfo: RowToShowModel) {
            binding.textAcron = rowInfo
            if (rowInfo.lf == null) {
                binding.longForm.visibility = View.GONE
                binding.card.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.shortAcron.context,
                        R.color.red
                    )
                )
            } else binding.longForm.visibility = View.VISIBLE
            if (rowInfo.sf == null) {
                binding.shortAcron.visibility = View.GONE
                binding.card.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.shortAcron.context,
                        R.color.blue
                    )
                )
            } else binding.shortAcron.visibility = View.VISIBLE
        }
    }

    private lateinit var binding: RowAbreviationBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheHolder {
        binding = RowAbreviationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TheHolder(binding)
    }

    override fun getItemCount() = rowInfoList.size

    override fun onBindViewHolder(holder: TheHolder, position: Int) {
        holder.bind(rowInfoList[position])
    }
}