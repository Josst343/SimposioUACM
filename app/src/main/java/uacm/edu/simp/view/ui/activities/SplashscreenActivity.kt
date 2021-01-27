package uacm.edu.simp.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splashscreen.*
import uacm.edu.simp.R

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val annotation = AnimationUtils.loadAnimation(this,R.anim.animacion_inicio)
        ivLogoUacmSimposio.startAnimation(annotation)
        val intent = Intent(this, MainActivity::class.java)


        annotation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
             startActivity(intent)
                finish()

            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })
    }
}