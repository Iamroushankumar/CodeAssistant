package com.roushan.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class RecyclerAdapter(val list: MutableList<Any>, val resid: Int, val callback: RecyclerAction) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {

    inner class RecyclerHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(any: Any) {
            callback.onAction(view, any)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val view = LayoutInflater.from(parent.context).inflate(resid, parent, false)
        return RecyclerHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.onBind(list.get(position))
    }

}

public interface RecyclerAction {
    fun onAction(
        view: View, any: Any
    )
}
