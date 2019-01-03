package com.treehouse.popultion

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PopAdapter()


        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager= layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this,layoutManager.orientation))
        recyclerView.adapter=  adapter
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("http://api.population.io:80/1.0/")
            .addConverterFactory(GsonConverterFactory
            .create())
        val retrofit = retrofitBuilder
            .client(okHttpClient)
            .build()

        val popClient = retrofit.create(PopServices::class.java)


        popClient.getPopulitionDetails(country = "Brazil", age = 18)
            .enqueue(object : Callback<PopResponse>
            {
            override fun onFailure(call: Call<PopResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<PopResponse>, response: Response<PopResponse>) {

                if(response.isSuccessful){
                  adapter.setData(
                      response.body()?.country
                    ?: emptyList())

                }
            }

        })
    }
}
