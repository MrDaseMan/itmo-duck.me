package ru.sample.duckapp.data

import android.widget.ImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import ru.sample.duckapp.infra.Api
import ru.sample.duckapp.domain.Duck

import okhttp3.ResponseBody
import android.graphics.BitmapFactory
import android.widget.ProgressBar
import android.util.Log

import android.widget.Toast
import android.view.View

class ApiHelpers {

    fun getRandomDuck(imageView: ImageView, progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE

        Api.ducksApi.getRandomDuck().enqueue(object : Callback<Duck> {
            override fun onResponse(call: Call<Duck>, response: Response<Duck>) {
                progressBar.visibility = View.INVISIBLE

                if (response.isSuccessful) {
                    val duckImageUrl = response.body()?.url
                    duckImageUrl?.let {
                        Picasso.get()
                            .load(it)
                            .into(imageView)
                    }
                } else {
                    val context = imageView.context
                    Toast.makeText(context, "Упс! Что-то пошло не так при получении рандомной утки!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Duck>, t: Throwable) {
                progressBar.visibility = View.INVISIBLE
                val context = imageView.context
                Toast.makeText(context, "Упс! Что-то пошло не так при получении рандомной утки!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getDuckByCode(imageView: ImageView, progressBar: ProgressBar, code: String) {
        progressBar.visibility = View.VISIBLE

        Api.ducksApi.getDuckByCode(code).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                progressBar.visibility = View.INVISIBLE

                if (response.isSuccessful) {
                    val imageByteArray = response.body()?.bytes()
                    imageByteArray?.let {
                        try {
                            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                            imageView.setImageBitmap(bitmap)
                        } catch (e: Exception) {
                            val context = imageView.context
                            Toast.makeText(context, "Упс! Что-то пошло не так при подгрузке изображения!", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    // TODO: show popup with error
                    val context = imageView.context
                    Toast.makeText(context, "Упс! Что-то пошло не так при получении утки по коду $code!", Toast.LENGTH_SHORT).show()
                }
            }
    
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                progressBar.visibility = View.INVISIBLE

                val context = imageView.context
                Toast.makeText(context, "Упс! Что-то пошло не так при получении утки по коду $code!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}