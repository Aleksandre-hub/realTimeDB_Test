package com.example.realtimedb_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.internal.Objects.ToStringHelper
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class InfoPageActivity : AppCompatActivity() {

    //var firebaseAuth : FirebaseAuth ?= null

    lateinit var name : EditText
    lateinit var surname : TextView
    lateinit var ID : TextView
    lateinit var email : TextView
    lateinit var finduser : Button
    var firebaseDatabase : FirebaseDatabase ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_info)

        surname = findViewById(R.id.surname)
        ID = findViewById(R.id.carID)
        email = findViewById(R.id.email)
        name = findViewById(R.id.userName)
        finduser = findViewById(R.id.FindUser)


        val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Students")

        finduser.setOnClickListener {

            if (name.text.length > 0) {
                databaseReference.child(name.text.toString()).addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val students:Students? = snapshot.getValue(Students::class.java)
                        surname.text = students?.surname
                        ID.text = students?.ID
                        email.text = students?.mail

                    }

                    override fun onCancelled(error: DatabaseError) {
                        //Toast.makeText(this, error.code, Toast.LENGTH_SHORT).show()
                    }
                })
            }

        }




    }
}