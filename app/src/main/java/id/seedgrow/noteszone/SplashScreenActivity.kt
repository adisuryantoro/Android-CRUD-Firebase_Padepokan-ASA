package id.seedgrow.noteszone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager

/**
 * @author Adi Suryantoro (adisuryantoro.mail@gmail.com)
 * @version SplashScreen Activity, v 0.1 08/12/2019 by Adi Suryantoro
 */

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hiding name bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        //Three Second splash time
        Handler().postDelayed({
            //start MainActivity
            startActivity(Intent(this, LoginActivity::class.java))
            //finish this activity
            finish()
        }, 3000)
    }
}
