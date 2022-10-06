package com.trongson.sjf

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class sjfAdapter (var context: Context, var manglist: ArrayList<sjfdata>): BaseAdapter() {
    class ViewHolder(row: View){
        var textviewtimein: TextView
        var textviewtimeout: TextView
        var textviewname: TextView
        init {
            textviewtimein = row.findViewById(R.id.timein) as TextView
            textviewtimeout = row.findViewById(R.id.timeout) as TextView
            textviewname = row.findViewById(R.id.tientrinh) as TextView

        }
    }

    override fun getCount(): Int {
        return manglist.size;
    }

    override fun getItem(position: Int): Any {
        return manglist.get(position)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view : View?
        var viewHolder : ViewHolder
        if (convertView==null)
        {
            var layoutinInflater : LayoutInflater = LayoutInflater.from(context)
            view = layoutinInflater.inflate(R.layout.listsjf,null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }
        else
        {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }
        var firstdata : sjfdata = getItem(position) as sjfdata
        viewHolder.textviewname.text= firstdata.name
        viewHolder.textviewtimein.text= firstdata.timein.toString()
        viewHolder.textviewtimeout.text= firstdata.timeout.toString()
        return view as View
    }
}