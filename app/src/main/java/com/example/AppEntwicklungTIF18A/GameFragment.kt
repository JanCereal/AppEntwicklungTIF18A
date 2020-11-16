package com.example.AppEntwicklungTIF18A

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.example.AppEntwicklungTIF18A.databinding.FragmentGameBinding
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class GameFragment : Fragment() {

    val imageUrls = arrayListOf<String>("10", "20", "3", "4")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGameBinding.inflate(layoutInflater)

        binding.btnNext.setOnClickListener { view: View ->
            parseJSON()
            Picasso.get().load(imageUrls[0]).into(binding.imagePanel1)
            Picasso.get().load(imageUrls[1]).into(binding.imagePanel2)

            //println(imageUrls[0])
            //println(imageUrls[1])
        }


        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        return binding.root
    }

    private fun parseJSON() {
        println("startenis")
        val url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true"
        println("secondenis")

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener<JSONObject?> {
                println("thirdenis")
                fun onResponse(response: JSONObject) {
                    println("fourthenis")
                    try {
                        println("2222222")
                        val jsonArray: JSONArray = response.getJSONArray("hits")
                        for (i in 0 until jsonArray.length()) {
                            val hit: JSONObject = jsonArray.getJSONObject(i)
                            imageUrls[i] = hit.getString("webformatURL")
                            //println(imageUrls[i])
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }, Response.ErrorListener {
                fun onErrorResponse(error: VolleyError) {
                    error.printStackTrace()
                }
            })
        //Queue ? mRequestQueue.add(request)
    }

}