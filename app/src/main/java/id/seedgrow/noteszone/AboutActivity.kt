package id.seedgrow.noteszone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * @author Adi Suryantoro (adisuryantoro.mail@gmail.com)
 * @version AboutActivity, v 0.1 08/12/2019 by Adi Suryantoro
 */

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
