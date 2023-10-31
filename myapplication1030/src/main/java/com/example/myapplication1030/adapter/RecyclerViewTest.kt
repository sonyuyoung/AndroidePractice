package com.example.myapplication1030.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1030.databinding.ItemRecyclerBinding


class MyViewHolderTest(val binding : ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root)
class RecyclerViewTest (val datas : MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolderTest(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolderTest).binding
        binding.testText.text=datas[position]
    }

}
