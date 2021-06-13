package com.example.projet_tdm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Doctor
import com.example.projet_tdm.retrofit.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getDoctors()
    }

    private fun getDoctors() {
        val call = RetrofitService.endpoint.getDoctors()
        call.enqueue(object : Callback<List<Doctor>> {
            override fun onResponse(call: Call<List<Doctor>>, response: Response<List<Doctor>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        specRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        specRecyclerView.adapter = MyAdapter(this@MainActivity, data)
                        Toast.makeText(this@MainActivity, "success", Toast.LENGTH_LONG ).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "erreur1", Toast.LENGTH_LONG ).show()
                }
            }

            override fun onFailure(call: Call<List<Doctor>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_LONG ).show()
            }

        })
    }

}
