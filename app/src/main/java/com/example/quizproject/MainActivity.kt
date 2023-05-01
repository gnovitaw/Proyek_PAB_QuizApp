package com.example.quizproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserId:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        btnRegister.setOnClickListener{
            registerUser()




        }

        tvHaveAccount.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent);
        }

    }

    private fun registerUser(){
        val username: String = etUsernameRegister.text.toString()
        val email: String = etEmailRegister.text.toString()
        val password:String = etPasswordRegister.text.toString()



        if (username == "")
        {
            Toast.makeText(this, "Please Input User Name", Toast.LENGTH_SHORT).show()
        }
        else if(email == "")
        {
            Toast.makeText(this, "Please Input Email", Toast.LENGTH_SHORT).show()
        }
        else if(password == "")
        {
            Toast.makeText(this, "Please Input Password", Toast.LENGTH_SHORT).show()
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                    task ->
                if (task.isSuccessful)
                {
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserId)

                    val userHashMap = HashMap<String,Any>()
                    userHashMap["uid"] = firebaseUserId
                    userHashMap["username"] = username



                    refUsers.updateChildren(userHashMap)
                        .addOnCompleteListener { task ->
                            if(task.isSuccessful)
                            {
                                val intent = Intent(this@MainActivity,NavDrawer::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                finish()



                            }
                        }
                }
                else
                {
                    Toast.makeText(this@MainActivity, "Error Message:" + task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

}