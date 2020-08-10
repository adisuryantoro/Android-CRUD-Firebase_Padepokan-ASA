package id.seedgrow.noteszone.feature

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import com.google.firebase.database.*
import id.seedgrow.noteszone.AboutActivity
import id.seedgrow.noteszone.LoginActivity
import id.seedgrow.noteszone.R
import id.seedgrow.noteszone.model.FileNoteModel
import id.seedgrow.noteszone.network.adapters.FileNotesAdapter
import id.seedgrow.noteszone.utils.Constans
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Adi Suryantoro (adisuryantoro.mail@gmail.com)
 * @version MainActivity, v 0.1 08/12/2019 by Adi Suryantoro
 */

class MainActivity : AppCompatActivity() {

    lateinit var fileNotesAdapter: FileNotesAdapter
    lateinit var databaseReference: DatabaseReference

    lateinit var recyclerView: RecyclerView

    var listNoteObjects: MutableList<FileNotesAdapter.ListNoteObject> = ArrayList()
    lateinit var listNoteObject: FileNotesAdapter.ListNoteObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView =findViewById(R.id.rv_main_notes)
        recyclerView.setHasFixedSize(true)
        databaseReference = FirebaseDatabase.getInstance().getReference("FILENOTE")

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext, "Database Error", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()){
                    for (data in dataSnapshot.children){
                        val notes = data.getValue(FileNoteModel::class.java)
                        if (notes != null) {
                            listNoteObject = FileNotesAdapter.ListNoteObject(notes)
                            listNoteObjects.add(listNoteObject)
                        }
                        showList()
                    }
                    fileNotesAdapter.notifyDataSetChanged()
                }else{
                    tv_main_isempty.visibility = View.VISIBLE
                    rv_main_notes.visibility = View.GONE
                }
            }

        })

        fb_main_add_note.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showList() {
        fileNotesAdapter = FileNotesAdapter(applicationContext, listNoteObjects, obj)
        val mLayoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = fileNotesAdapter

    }

    private val obj = object : FileNotesAdapter.ListNoteListener {
        override fun goToDetailListener(position: Int, detailNote: FileNoteModel) {
            val intent = Intent(applicationContext, DetailActivity::class.java)

            intent.putExtra(Constans.ID, detailNote.id)
            intent.putExtra(Constans.TITLE, detailNote.title)
            intent.putExtra(Constans.DESC, detailNote.desc)

            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_logout, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }

            R.id.menu_log_out -> {
                signOut()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    //Sign out firebase and start login activity
    private fun signOut() {
        AuthUI.getInstance().signOut(this).addOnCompleteListener {

            startActivity(
                LoginActivity.getLaunchIntent(
                    this
                )
            )

        }

    }

    //Finish another activity
    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}
