package com.d3if0093.dollareuroexcange.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.d3if0093.dollareuroexcange.R
import com.d3if0093.dollareuroexcange.`object`.Kurs
import com.d3if0093.dollareuroexcange.database.ListNegara

class MyAdapter(
private var kurs:Kurs
):RecyclerView.Adapter<MyAdapter.ViewHolder>(){







    var data = listOf<ListNegara>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }




    override fun getItemCount()= data.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.excange_list,parent,false)



        return ViewHolder(view)

          }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


       val item = data[position]
        holder.bendera.setImageResource(item.pict)
        holder.negara.text = item.negara
      val valueNya=when(item.mataUang){
        "SGD" -> kurs.rates?.SGD.toString()
        "EUR" -> kurs.rates?.EUR.toString()
        "USD" -> kurs.rates?.USD.toString()
        "JPY" -> kurs.rates?.JPY.toString()
        "AUD" -> kurs.rates?.AUD.toString()
        "CNY" -> kurs.rates?.CNY.toString()
        "GBP" -> kurs.rates?.GBP.toString()
     else -> kurs.rates?.IDR.toString() }
        holder.mataUang.text =valueNya+" "+item.mataUang
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val mataUang:TextView=itemView.findViewById(R.id.mataUang)
        val negara:TextView=itemView.findViewById(R.id.negara)
        val bendera: ImageView =itemView.findViewById(R.id.bendera)

    }
}











class DiffUtilCallback:DiffUtil.ItemCallback<ListNegara>(){
    override fun areContentsTheSame(oldItem: ListNegara, newItem: ListNegara): Boolean {
         return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: ListNegara, newItem: ListNegara): Boolean {
        return oldItem.id == newItem.id

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}







class Listener(val listener:(id:Long)->Unit){
    fun onClick(listNegara: ListNegara)=(listNegara.id)
}