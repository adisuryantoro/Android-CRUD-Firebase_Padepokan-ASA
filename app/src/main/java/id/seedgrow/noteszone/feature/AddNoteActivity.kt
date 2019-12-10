package id.seedgrow.noteszone.feature

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.seedgrow.noteszone.model.FileNoteModel
import id.seedgrow.noteszone.R
import kotlinx.android.synthetic.main.activity_add_note.*
import java.util.*

/**
 * @author Adi Suryantoro (adisuryantoro.mail@gmail.com)
 * @version AddNoteActivity, v 0.1 08/12/2019 by Adi Suryantoro
 */

class AddNoteActivity : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    lateinit var calendarTime: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        databaseReference = FirebaseDatabase.getInstance().getReference("FILENOTE")
        calendarTime = Calendar.getInstance().time

        fb_add_note_send.setOnClickListener {
            sendNote()
        }

    }

    private fun sendNote() {
        val title = edt_add_note_title.text.toString()
        val desc = edt_add_note_desc.text.toString()
        val date = calendarTime.toString()

        val userId = databaseReference.push().key.toString()

        val fileNote = FileNoteModel(userId, title, desc, date)

        if (title.isEmpty()) {
            Toast.makeText(applicationContext, "Title cannot be empty", Toast.LENGTH_LONG).show()
        } else if (desc.isEmpty()) {
            Toast.makeText(applicationContext, "Description cannot be empty", Toast.LENGTH_LONG)
                .show()
        } else {
            databaseReference.child(userId).setValue(fileNote).addOnCompleteListener {
                Toast.makeText(applicationContext, "Add note successfully", Toast.LENGTH_LONG)
                    .show()
                startActivity(MainActivity.getLaunchIntent(this))
                finish()
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
