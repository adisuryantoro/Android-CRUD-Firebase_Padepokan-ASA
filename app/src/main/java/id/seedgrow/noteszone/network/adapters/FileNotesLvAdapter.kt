package id.seedgrow.noteszone.network.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import id.seedgrow.noteszone.R
import id.seedgrow.noteszone.model.FileNoteModel

/**
 * @author Adi Suryantoro (adisuryantoro.mail@gmail.com)
 * @version FileNotesLvAdapter, v 0.1 08/12/2019 by Adi Suryantoro
 */

//Example Adapter Listview
class FileNotesLvAdapter(val mCtx: Context, val layoutResId: Int, val list: List<FileNoteModel>) :
    ArrayAdapter<FileNoteModel>(mCtx, layoutResId, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textNama = view.findViewById<TextView>(R.id.tv_item_main_title)
        val textStatus = view.findViewById<TextView>(R.id.tv_item_main_desc)

        val notes = list[position]

        textNama.text = notes.title
        textStatus.text = notes.desc

        return view

    }
}