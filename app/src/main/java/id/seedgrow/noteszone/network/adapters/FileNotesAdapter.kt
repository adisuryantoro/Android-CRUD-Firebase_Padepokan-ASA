package id.seedgrow.noteszone.network.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.seedgrow.noteszone.R
import id.seedgrow.noteszone.model.FileNoteModel

/**
 * @author Adi Suryantoro (adisuryantoro.mail@gmail.com)
 * @version FileNoteAdapter, v 0.1 08/12/2019 by Adi Suryantoro
 */

//Adapter Recyclerview
class FileNotesAdapter(internal var context: Context
                       , internal var listNoteObject: MutableList<ListNoteObject>
                       , internal var listListNoteListener: ListNoteListener) : RecyclerView.Adapter<FileNotesAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var lLinear: LinearLayout
        var mTitle: TextView
        var mDesc: TextView
        var mTime: TextView

        init {
            lLinear = view.findViewById(R.id.ll_item_main)
            mTitle = view.findViewById(R.id.tv_item_main_title)
            mDesc = view.findViewById(R.id.tv_item_main_desc)
            mTime = view.findViewById(R.id.tv_item_main_time)

        }

    }

    // this method for build view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLayout = R.layout.item_main_notes
        val itemView = LayoutInflater.from(parent.context)
            .inflate(itemLayout, parent, false)
        return MyViewHolder(itemView)
    }

    // this method for init item in every view item
    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listNotes = listNoteObject[position].listNote

        holder.mTitle.text = listNotes.title
        holder.mDesc.text = listNotes.desc
        holder.mTime.text = listNotes.time

        holder.lLinear.setOnClickListener {
            listListNoteListener.goToDetailListener(position, listNotes)
        }

    }

    // this method for get total item
    override fun getItemCount(): Int {
        var itemCount = 0

        try {
            val itemSize = listNoteObject.size
            itemCount = itemSize
        } catch (e: Exception) {
        }

        return itemCount
    }


    // this interface for handle more button pressed
    interface ListNoteListener {
        fun goToDetailListener(position: Int, detailNote: FileNoteModel)

    }

    // this class is object of item in recyclerview
    class ListNoteObject(var listNote: FileNoteModel)

}