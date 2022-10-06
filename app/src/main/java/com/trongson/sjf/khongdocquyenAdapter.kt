package com.trongson.sjf

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class khongdocquyenAdapter (var context: Context, var docquyenmang: ArrayList<mangkhongdocquyen>): BaseAdapter() {
    class ViewHolder(row: View){
        var kdocquyentimein: TextView
        var kdocquyentimeout: TextView
        var kdocquyenname: TextView
        init {
            kdocquyentimein = row.findViewById(R.id.timeinKdocquyen) as TextView
            kdocquyentimeout = row.findViewById(R.id.timeoutKdocquyen) as TextView
            kdocquyenname = row.findViewById(R.id.ttKdocquyen) as TextView

        }
    }

    override fun getCount(): Int {
        return docquyenmang.size;
    }

    override fun getItem(position: Int): Any {
        return docquyenmang.get(position)
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
        var firstdata : mangdocquyen = getItem(position) as mangdocquyen
        viewHolder.kdocquyenname.text= firstdata.tentt.toString()
        viewHolder.kdocquyentimein.text= firstdata.ttin.toString()
        viewHolder.kdocquyentimeout.text= firstdata.ttout.toString()
        return view as View
    }
}
