package eu.tutorials.a7_minutesworkoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityLoginBinding

class ActivityLogin : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        auth = Firebase.auth

        binding.loginButton.setOnClickListener {
            val userEmail = binding.email.text.toString()
            val userPassword = binding.pass.text.toString()

            signInUser(userEmail,userPassword)
        }

    }

    private fun updateUI(firebaseUser: FirebaseUser?) {

        if(firebaseUser != null) {
            val mainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
        }
    }


    private fun signInUser(userEmail: String, userPassword: String) {

        binding.loginButton.isClickable = false

        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->

            binding.loginButton.isClickable = true

            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "Welcome", Toast.LENGTH_SHORT).show()

                updateUI(auth.currentUser)

            } else {
                Toast.makeText(applicationContext, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}