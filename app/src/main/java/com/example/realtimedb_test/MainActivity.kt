package com.example.realtimedb_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.internal.Objects.ToStringHelper
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val database = Firebase.database.reference

    lateinit var ename : EditText
    lateinit var esurname : EditText
    lateinit var eID : EditText
    lateinit var epfp : EditText
    lateinit var eemail : EditText
    lateinit var submitBTN : Button

    lateinit var schoolname : EditText

    lateinit var view : TextView

    lateinit var dbref : DatabaseReference
    lateinit var dbrefS : DatabaseReference

    lateinit var showInfoBTN : Button

    val studentList = arrayListOf<String>()

    var userid = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ename = findViewById(R.id.name)
        esurname = findViewById(R.id.surname)
        eID = findViewById(R.id.ID)
        epfp = findViewById(R.id.PFP)
        eemail = findViewById(R.id.emial)
        submitBTN = findViewById(R.id.SubimtBTN)
        schoolname = findViewById(R.id.schoolname)


        dbref = FirebaseDatabase.getInstance().getReference("Students")
        dbrefS = FirebaseDatabase.getInstance().getReference("School")


        submitBTN.setOnClickListener {
            if ((ename.length() > 3) && esurname.length() > 3 && (eID.length() > 12 || eID.length() < 14) &&
                epfp.length() > 1 && eemail.length() > 5 && eemail.text.contains("@") && schoolname.length() > 2) {
                saveStudent()
            }
        }

        showInfoBTN = findViewById(R.id.InfoPageBTN)

        showInfoBTN.setOnClickListener {
            startActivity(Intent(this, InfoPageActivity::class.java))
        }



    }

    private fun saveStudent() {
        val Sname = ename.text.toString()
        val Ssurname = esurname.text.toString()
        val SID = eID.text.toString()
        val Spfp = epfp.text.toString()
        val Semail = eemail.text.toString()
        val Sschoolname = schoolname.text.toString()

        val students = Students(Sname, Ssurname, SID, Spfp, Semail, Sschoolname)

        dbref.child(Sname).setValue(students).addOnCompleteListener {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }


        studentList.add(Sname + " " + Ssurname+"\n")

        val school = School(studentList)

        dbrefS.setValue(school)

    }

}

data class School(val students: List<String>? = null) {

}

data class Students(val name: String? = null, val surname: String? = null,
                    val ID: String? = null, val ProfilePicture: String? = null,
                    val mail: String? = null, val schoolname: String? = null)


