package com.trongson.sjf

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class docquyenAdapter (var context: Context, var docquyenmang: ArrayList<mangdocquyen>): BaseAdapter() {
    class ViewHolder(row: View){
        var docquyentimein: TextView
        var docquyentimeout: TextView
        var docquyenname: TextView
        init {
            docquyentimein = row.findViewById(R.id.timeindocquyen) as TextView
            docquyentimeout = row.findViewById(R.id.timeoutdocquyen) as TextView
            docquyenname = row.findViewById(R.id.ttdocquyen) as TextView

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
        viewHolder.docquyenname.text= firstdata.tentt.toString()
        viewHolder.docquyentimein.text= firstdata.ttin.toString()
        viewHolder.docquyentimeout.text= firstdata.ttout.toString()
        return view as View
    }
}