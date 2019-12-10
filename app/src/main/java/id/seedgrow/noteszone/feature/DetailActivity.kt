package id.seedgrow.noteszone.feature

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.seedgrow.noteszone.R
import id.seedgrow.noteszone.utils.Constans
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * @author Adi Suryantoro (adisuryantoro.mail@gmail.com)
 * @version DetailActivity, v 0.1 08/12/2019 by Adi Suryantoro
 */

class DetailActivity : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference

    var id: String? = null
    var title: String? = null
    var desc: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        databaseReference = FirebaseDatabase.getInstance().getReference("FILENOTE")

        id = intent.getStringExtra(Constans.ID)
        title = intent.getStringExtra(Constans.TITLE)
        desc = intent.getStringExtra(Constans.DESC)

        tv_detail_title_data.text = title
        tv_detail_desc_data.text = desc

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_edit_delete, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {

            R.id.menu_edit_note -> {
                Toast.makeText(applicationContext, "Coming soon.", Toast.LENGTH_SHORT).show()
            }

            R.id.menu_delete_note -> {
                deleteInfo(id!!)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun deleteInfo(id: String) {
        databaseReference.child(id).removeValue()
        Toast.makeText(applicationContext, "Successfully deleted.", Toast.LENGTH_SHORT).show()
        startActivity(MainActivity.getLaunchIntent(this))
        finish()

    }
}
