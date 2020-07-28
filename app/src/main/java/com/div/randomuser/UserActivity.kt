package com.div.randomuser

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val imageUrl = intent.getStringExtra("imageUrl")
        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        val skype = intent.getStringExtra("skype")

        val userImage: ImageView = findViewById(R.id.userDetailImage)

        Glide.with(this)
            .load(imageUrl)
            .circleCrop()
            .into(userImage)

        val nameTextView: TextView = findViewById(R.id.userDetailName)
        val ageTextView: TextView = findViewById(R.id.userDetailAge)
        val phoneTextView: TextView = findViewById(R.id.userDetailPhone)
        val emailTextView: TextView = findViewById(R.id.userDetailEmail)
        val skypeTextView: TextView = findViewById(R.id.userDetailSkype)

        nameTextView.text = name
        ageTextView.text = age.plus(" years old")
        phoneTextView.text = phone
        emailTextView.text = email
        skypeTextView.text = skype

        phoneTextView.setOnClickListener(View.OnClickListener { view -> makeCall2(phone.toString()) })

    }

    private fun makeCall(phone: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$phone")
        //startActivity(intent)
        Toast.makeText(this, "making call to $phone", Toast.LENGTH_SHORT).show()
    }

    fun makeCall2(phone: String) {
        val MY_PERMISSIONS_REQUEST_CALL_PHONE = 123

        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phone")
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this as Activity,
                    Manifest.permission.CALL_PHONE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    MY_PERMISSIONS_REQUEST_CALL_PHONE)
            }
        }
        startActivity(callIntent)
    }


}