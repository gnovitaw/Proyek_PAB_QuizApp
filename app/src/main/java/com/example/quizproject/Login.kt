package com.example.quizproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener {
            loginuser()

        }

        tvCreateAccount.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
        }

    }

    private fun loginuser() {
        val email: String = etEmailLogin.text.toString()
        val password:String = etPasswordLogin.text.toString()


        if(email == "")
        {
            Toast.makeText(this, "Please Input Email", Toast.LENGTH_SHORT).show()
        }
        else if(password == "")
        {
            Toast.makeText(this, "Please Input Password", Toast.LENGTH_SHORT).show()
        }
        else{
            mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task->
                    if(task.isSuccessful)
                    {
                        val intent = Intent(this@Login,NavDrawer::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this@Login, "Error Message:" + task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}


