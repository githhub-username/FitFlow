package eu.tutorials.a7_minutesworkoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityLoginBinding
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding.button.setOnClickListener {

            val email = binding.email.text.toString()
            val password = binding.pass.text.toString()

            signupWithFirebase(email, password)
        }

        binding.loginButton.setOnClickListener {

            val intent = Intent(this,ActivityLogin::class.java)
            startActivity(intent)
        }
    }

    private fun signupWithFirebase(email: String, password: String) {

        binding.button.isClickable = false

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

            if(task.isSuccessful){
                Toast.makeText(applicationContext,"Your account has been created", Toast.LENGTH_SHORT).show()
                finish()
                binding.button.isClickable = true
            }
            else{
                Toast.makeText(applicationContext,task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }


}