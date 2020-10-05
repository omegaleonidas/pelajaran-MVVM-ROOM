package com.example.register.Home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.register.Home.Adapter.JadwalAdapter
import com.example.register.R
import com.example.register.ViewModel.JadwalViewModel
import com.example.register.local.DatabaseJadwal
import com.example.register.local.model.Jadwal
import kotlinx.android.synthetic.main.dialog_from_jadwal.*
import kotlinx.android.synthetic.main.dialog_from_jadwal.view.*
import kotlinx.android.synthetic.main.fragment_jadwal.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class JadwalFragment : Fragment() {

    private var jadwalDatabase: DatabaseJadwal? = null
    lateinit var jadwalViewModel: JadwalViewModel


    private var dialogView: Dialog? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jadwalViewModel.showJadwalView()
        attachObserve()
        floatingActionButton.setOnClickListener {
            getshowAddDialog()

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jadwalViewModel = ViewModelProvider(this).get(JadwalViewModel::class.java)
        jadwalDatabase = context?.let { DatabaseJadwal.getInstance(it) }
    }

    private fun getshowAddDialog() {

        val dialog = AlertDialog.Builder(context)
        val view: View = layoutInflater.inflate(R.layout.dialog_from_jadwal, null)
        dialog.setView(view)
        view.btnSave.setOnClickListener {
            if (view.editTextTextPersonName.text.isNotEmpty()) {

                var pelajaran = view.editTextTextPersonName.text.toString()
                var keterangan = view.tvDesckipsi.text.toString()
                var tanggal = getDate()


                jadwalViewModel.addJadwalView(Jadwal(null, pelajaran, keterangan, tanggal))


                Toast.makeText(context, "data telah tersimpan ", Toast.LENGTH_SHORT).show()

                dialogView?.dismiss()
            } else {
                view.editTextTextPersonName.error = "data harus di isi"
                view.tvDesckipsi.error = "data harus di isi"
            }
        }
        view.imageView.setOnClickListener {
            dialogView?.dismiss()
        }
        dialogView = dialog.create()
        dialogView?.show()

    }


    private fun getDate(): String {
        val date: Date = Calendar.getInstance().time
        val formatter: DateFormat = SimpleDateFormat.getDateInstance()
        val formatDate: String = formatter.format(date)

        return formatDate

    }


    private fun showJadwal(it: List<Jadwal>?) {
        recyclerView.adapter = JadwalAdapter(it, object : JadwalAdapter.OnClickListener {
            override fun delete(item: Jadwal?) {
                AlertDialog.Builder(context).apply {
                    setTitle("hapus")
                    setMessage("yakin hapus Jadwal ?")
                    setCancelable(false)
                    setPositiveButton("yakin ") { dialogInterface, i ->
                        jadwalViewModel.deleteJadwalView(item!!)
                    }
                    setNegativeButton("batal") { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }.show()
            }

            override fun update(item: Jadwal?) {
                getShowUpdateData(item)


            }

        })
    }


    private fun getShowUpdateData(item: Jadwal?) {

        val dialog = AlertDialog.Builder(context)
        val view: View = layoutInflater.inflate(R.layout.dialog_from_jadwal, null)
        dialog.setView(view)
        view.btnSave.text = "update"
        view.editTextTextPersonName.setText(item?.pelajaran)
        view.tvDesckipsi.setText(item?.keterangan)


        view.btnSave.setOnClickListener {
            val id = item?.id
            val matapelajran = view.editTextTextPersonName.text.toString()
            val keterangan = view.tvDesckipsi.text.toString()
            val date = getDate()

            if (view.editTextTextPersonName.text.isNotEmpty()) {


                jadwalViewModel.updateJadwalView(Jadwal(id, matapelajran, keterangan, date))
                dialogView?.dismiss()
            } else {
                view.editTextTextPersonName.error = "data harus di isi"
                view.tvDesckipsi.error = "data harus di isi"
            }
        }
        view.imageView.setOnClickListener {
            dialogView?.dismiss()
        }
        dialogView = dialog.create()
        dialogView?.show()

    }


    private fun attachObserve() {
        jadwalViewModel.ShowJadwal.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showJadwal(it) })
        jadwalViewModel.isError.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showError(it) })
        jadwalViewModel.AddJadwal.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showAddJadwal(it) })
        jadwalViewModel.UpdateJadwal.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showUpdateJadwal(it) })
        jadwalViewModel.DeleteJadwal.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showDeleteJadwal(it) })
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showAddJadwal(it: Unit?) {
        Toast.makeText(context, "Jadwal   pelajaran berhasil disimpan", Toast.LENGTH_SHORT).show()
        jadwalViewModel.showJadwalView()
    }

    private fun showUpdateJadwal(it: Unit?) {
        Toast.makeText(context, "Jadwal pelajran berhasil diupdate", Toast.LENGTH_SHORT).show()
        jadwalViewModel.showJadwalView()
    }

    private fun showDeleteJadwal(it: Unit?) {
        Toast.makeText(context, "jadwal pelajaran  berhasil dihapus", Toast.LENGTH_SHORT).show()
        jadwalViewModel.showJadwalView()
    }


}
