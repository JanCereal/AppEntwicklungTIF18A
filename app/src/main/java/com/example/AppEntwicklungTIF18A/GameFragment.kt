package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.AppEntwicklungTIF18A.databinding.FragmentGameBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_game.*
import org.json.JSONException

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inputManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val binding = FragmentGameBinding.inflate(layoutInflater)
        val tempKeywordList = arguments?.getStringArrayList("selectedCategory")

        //search
        if (tempKeywordList?.size != null) {
            tempKeywordList.shuffle()
            parseSearchJSON(binding, tempKeywordList[0])
            binding.charCountTextView.setText("Das gesuchte Wort hat " + tempKeywordList[0].toCharArray().size + " Buchstaben")
        } else {
            println("rKeywordList empty")
        }
        //

        //Buttons and EnterKey (UserAnswerAction)
        binding.answerTextView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                checkAnswer(tempKeywordList as ArrayList<String>, v, inputManager)
                return@OnKeyListener true
            }
            false
        })
        binding.btnAnswer.setOnClickListener { view: View ->
            var userAnswer = answerTextView.text.toString()
            if (tempKeywordList?.size != 0) {
                checkAnswer(tempKeywordList as ArrayList<String>, view, inputManager)
            }
        }
        //
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        return binding.root
    }

    private fun parseSearchJSON(binding: FragmentGameBinding, keyword: String) {
        val url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=" + keyword + "&image_type=photo&pretty=true"
        val imageUrls = mutableListOf("1", "2", "3", "4")
        val requestQueue: RequestQueue? = Volley.newRequestQueue(context)

        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
                try {
                    val jsonArray = response.getJSONArray("hits")
                    for (i in 0 until 4) {
                        val employee = jsonArray.getJSONObject(i)
                        val imageUrl = employee.getString("previewURL")
                        imageUrls.add(i, imageUrl)
                        when (i) {
                            0 -> Picasso.get().load(imageUrls[i]).into(imagePanel1)
                            1 -> Picasso.get().load(imageUrls[i]).into(imagePanel2)
                            2 -> Picasso.get().load(imageUrls[i]).into(imagePanel3)
                            3 -> Picasso.get().load(imageUrls[i]).into(imagePanel4)
                            else -> {
                                print("error")
                            }
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }

    private fun checkAnswer(tempKeywordList: ArrayList<String>, v: View, inputManager: InputMethodManager) {
        var userAnswer = answerTextView.text.toString()
        if (tempKeywordList?.size != 0) {
            //Answer check
            println(userAnswer + " - " + tempKeywordList?.get(0))
            if (userAnswer.trim().equals(tempKeywordList?.get(0),true)) {
                val bundle = bundleOf("selectedCategory" to tempKeywordList)
                tempKeywordList.removeAt(0)
                view?.findNavController()?.navigate(R.id.action_gameFragment_to_successFragment, bundle)
            } else {
                answerTextView.hint = "Wrong!"
                answerTextView.setText("")
            }
        }
        inputManager.hideSoftInputFromWindow(v.windowToken, 0)
    }
}

