package com.practice.practiceproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.practice.practiceproject.databinding.OtherUserSampleBinding
import com.practice.practiceproject.databinding.UserSampleBinding

//class UserAdapter : ListAdapter<UserData, UserAdapter.ViewHolder>(UserDiffCallback()) {
class UserAdapter : ListAdapter<UserData, RecyclerView.ViewHolder>(UserDiffCallback()) {

    private val MALE_VIEW = 1
    private val FEMALE_VIEW = 2

    inner class BoysViewHolder(val binding: UserSampleBinding) : RecyclerView.ViewHolder(binding.root)
    inner class GirlsViewHolder(val binding: OtherUserSampleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType==MALE_VIEW){
            val binding = UserSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return BoysViewHolder(binding)
        }else{
            val binding = OtherUserSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return GirlsViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position).gender){
            "male"-> MALE_VIEW
            "female" -> FEMALE_VIEW
            else -> throw IllegalArgumentException("Not matching type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position) ?: return // Get item from ListAdapter
        when(holder){
            is BoysViewHolder -> {
                holder.binding.tvName.text = "${data.name} BOY"
                holder.binding.tvAge.text = data.age.toString()
                holder.binding.tvEducation.text = data.education
            }
            is GirlsViewHolder -> {
                holder.binding.tvName.text = "${data.name} GIRL"
                holder.binding.tvAge.text = data.age.toString()
                holder.binding.tvEducation.text = data.education
            }
        }

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