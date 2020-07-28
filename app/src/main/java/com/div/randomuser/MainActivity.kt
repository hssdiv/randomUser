package com.div.randomuser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.div.randomuser.api.RandomUserAPI
import com.div.randomuser.api.UserResponse
import com.div.randomuser.api.UserResults
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), UserRecyclerViewAdapter.OnItemClickListener {
    val TAG = "RandomUserMainActivity"
    val userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userRecyclerView.adapter = UserRecyclerViewAdapter(userList, this)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        val retrofit =   Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RandomUserAPI::class.java)
        val call = service.getUser("20")


        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.code() == 200) {
                    Log.i(TAG, "retrofit call - ok")
                    val userResultsList = response.body()!!.results!!.listIterator()

                    for (user: UserResults in userResultsList){
                        val firstName = user.userName!!.first.toString()
                        val lastName = user.userName!!.last.toString()
                        val fullName = firstName.plus(" ".plus(lastName))
                        val pictureUrl = user.picture!!.large.toString()
                        val age = user.dob!!.age.toString()
                        val phone = user.phone.toString()
                        val email = user.email.toString()
                        val skype = user.login!!.username.toString()
                        val user = User(pictureUrl, fullName, age, phone, email, skype)

                        userList += user
                    }
                    (userRecyclerView.adapter as UserRecyclerViewAdapter).notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                //testTextView.text = t.message.toString()
                Log.e(TAG, t.message.toString())
            }
        })

    }

    override fun onItemClick(position: Int) {
        val clickedItem = userList[position]
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("imageUrl", clickedItem.imageUrl)
        intent.putExtra("name", clickedItem.name)
        intent.putExtra("age", clickedItem.age)
        intent.putExtra("phone", clickedItem.phone)
        intent.putExtra("email", clickedItem.email)
        intent.putExtra("skype", clickedItem.skype)
        startActivity(intent)
    }
}