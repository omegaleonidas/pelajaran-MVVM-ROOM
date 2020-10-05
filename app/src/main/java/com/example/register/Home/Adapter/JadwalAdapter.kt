package com.example.register.Home.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.register.R
import com.example.register.local.model.Jadwal
import kotlinx.android.synthetic.main.item_jadwal.view.*

class JadwalAdapter(
    private val data: List<Jadwal>?,
    val itemClick: OnClickListener
) :
    RecyclerView.Adapter<JadwalAdapter.ViewHolder>() {


    class ViewHolder(
        val view: View,
        val itemClick: OnClickListener

    ) :
        RecyclerView.ViewHolder(view) {
        fun bind(item: Jadwal?) {
            view.tvDate.text = item?.date
            view.tvJudulJadwal.text = item?.pelajaran
            view.tvKeterangan.text = item?.keterangan
            view.imageButton.setOnClickListener {
                itemClick.update(item)

            }
            view.imageButton2.setOnClickListener {
                itemClick.delete(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_jadwal, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = data?.size ?: 0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Jadwal? = data?.get(position)
        holder.bind(item)
    }

    interface OnClickListener {
        fun update(item: Jadwal?)
        fun delete(item: Jadwal?)
    }


}