package com.example.myapp_test6_syytest.ch11_Test.viewPageandrecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp_test6_syytest.databinding.ItemRecycler2Binding


class MyViewHolderTest(val binding : ItemRecycler2Binding) : RecyclerView.ViewHolder(binding.root)

// 나중에는 모델로
class RecyclerViewTest (val datas : MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolderTest(ItemRecycler2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val binding = (holder as MyViewHolderTest).binding
    binding.testText.text=datas[position]
    }

}