package com.roushan.recycler


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatSpinner


class SpinnerAdapter(val list: List<Any>, val resid: Int, val callback: SpinnerAction) :
    BaseAdapter() {

    var dlist: MutableList<Any> = mutableListOf()

    init {
        dlist = list as MutableList<Any>;
    }


    public fun update(ulist: List<Any>) {
        dlist.clear()
        dlist.addAll(ulist)
        notifyDataSetChanged()
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val v = LayoutInflater.from(p2?.getContext()).inflate(resid, p2, false)
        callback.onSpinnerAction(v, list[p0], p0)
        return v
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

}

public interface SpinnerAction {
    fun onSpinnerAction(
        view: View, any: Any, position: Int
    )
}

public interface ChooseAction {
    fun onChooseAction(any: Any?, position: Int)
}

public fun AppCompatSpinner.choosenItem(
    isNeglateFirstPosition: Boolean,
    spinnerlist: List<Any>,

    spinneraction: ChooseAction
) {

    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            if (isNeglateFirstPosition) {
                if (p2 != 0) {
                    if (spinnerlist.size > p2) {
                        spinneraction.onChooseAction(spinnerlist[p2], p2)
                    } else {
                        spinneraction.onChooseAction(null, p2)

                    }
                }
            } else {
                if (spinnerlist.size > p2) {
                    spinneraction.onChooseAction(spinnerlist[p2], p2)
                }
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

    }
}