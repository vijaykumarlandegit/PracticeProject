package com.practice.practiceproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practice.practiceproject.databinding.UserSampleBinding

class SampleAdapter(private var list : MutableList<UserData> ):  RecyclerView.Adapter<SampleAdapter.ViewHolder>() {

    fun updateList(newList: MutableList<UserData>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
    fun addUser(user: UserData) {
        list.add(user)
        notifyItemInserted(list.size - 1) // notify adapter of the new item
    }
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding= UserSampleBinding.bind(view)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_sample, parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=list[position]
        holder.binding.tvName.text=data.name
        holder.binding.tvAge.text=data.age.toString()
        holder.binding.tvEducation.text=data.education

    }

    override fun getItemCount(): Int {
        return list.size
    }


}