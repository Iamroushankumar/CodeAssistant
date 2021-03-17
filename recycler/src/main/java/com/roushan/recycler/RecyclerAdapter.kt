package com.roushan.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class RecyclerAdapter(val list: List<Any>, val resid: Int, val callback: RecyclerAction) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {

    var dlist: MutableList<Any> = mutableListOf()

    init {
        dlist = list as MutableList<Any>;
    }


    public fun update(ulist: List<Any>) {
        dlist.clear()
        dlist.addAll(ulist)
        notifyDataSetChanged()
    }

    inner class RecyclerHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(position: Int) {
            callback.onAction(view, dlist.get(position),position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val view = LayoutInflater.from(parent.context).inflate(resid, parent, false)
        return RecyclerHolder(view)
    }

    override fun getItemCount(): Int {
        return dlist.size
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.onBind(position)
    }

}

public interface RecyclerAction {
    fun onAction(
        view: View, any: Any,position:Int
    )
}
