package com.practice.practiceproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.practice.practiceproject.databinding.UserSampleBinding

class UserAdapter : ListAdapter<UserData, UserAdapter.ViewHolder>(UserDiffCallback()) {

    inner class ViewHolder(val binding: UserSampleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position) ?: return // Get item from ListAdapter
        holder.binding.tvName.text = data.name
        holder.binding.tvAge.text = data.age.toString()
        holder.binding.tvEducation.text = data.education
    }
    class UserDiffCallback : DiffUtil.ItemCallback<UserData>(){
        override fun areItemsTheSame(
            oldItem: UserData,
            newItem: UserData
        ): Boolean {
            return oldItem.name==newItem.name
        }

        override fun areContentsTheSame(
            oldItem: UserData,
            newItem: UserData
        ): Boolean {
            return oldItem==newItem
        }

    }
}