package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.AppEntwicklungTIF18A.databinding.FragmentGameBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_game.*
import org.json.JSONException
import kotlin.random.Random
import kotlin.random.nextInt

class GameFragment : Fragment() {
   private var mistakes = 0
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      val inputManager =
         context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      val binding = FragmentGameBinding.inflate(layoutInflater)
      val tempKeywordList = arguments?.getStringArrayList("selectedCategory")
      mistakes = arguments?.getInt("Mistakes") as Int

      //region search
      if (tempKeywordList?.size != null) {
         tempKeywordList.shuffle()
         parseSearchJSON(binding, tempKeywordList[0])

         //region display Hint/Mistakes
         val sharedPrefHint =
            activity?.getSharedPreferences(getString(R.string.wordHint), Context.MODE_PRIVATE)
         if (sharedPrefHint != null && sharedPrefHint.getString(R.string.wordHint.toString(), "")
               .equals("1")
         ) {
            binding.charCountTextView.text =
               "Hint: " + tempKeywordList[0].toCharArray().size + " Letter"
         }
         //DisplayMistakes
         binding.MistakeCountTextView.text = "Mistakes: $mistakes"
         //endregion
      }
      //endregion

      // region Buttons and EnterKey (UserAnswerAction)
      binding.answerTextView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
         if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
            checkAnswer(tempKeywordList as ArrayList<String>, v, inputManager)
            return@OnKeyListener true
         }
         false
      })
      binding.btnAnswer.setOnClickListener { view: View ->
         if (tempKeywordList?.size != 0) {
            checkAnswer(tempKeywordList as ArrayList<String>, view, inputManager)
         }
      }

      binding.btnReroll.setOnClickListener { view: View ->
         if (tempKeywordList != null) {
            parseSearchJSON(binding, tempKeywordList[0])
         }
      }
      //endregion

      (activity as AppCompatActivity?)!!.supportActionBar!!.show()
      return binding.root
   }

   /**
    * Sucht vier zufällige Bilder zum Suchbegriff über die PixaBayAPI und stellt diese dar
    */
   private fun parseSearchJSON(binding: FragmentGameBinding, keyword: String) {
      val url =
         "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=$keyword&image_type=photo&pretty=true"
      val imageUrls = mutableListOf("1", "2", "3", "4")
      val requestQueue: RequestQueue? = Volley.newRequestQueue(context)

      val request =
         JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
               val jsonArray = response.getJSONArray("hits")
               val randArray = mutableListOf<Int>()
               var done = 0

               //get Random Unique Images from API
               while (true) {
                  if (jsonArray.length() != 0) {
                     val tempRand = Random.nextInt(0 until jsonArray.length())
                     if (isUnique(randArray, tempRand)) {
                        randArray.add(done, tempRand)
                        done++
                        if (done == 4) {
                           break
                        }
                     }

                  } else {
                     Thread.sleep(1_000)
                     Toast.makeText(context, "No images found!", Toast.LENGTH_LONG).show()
                     view?.findNavController()?.navigateUp()
                     break
                  }
               }

               //displays Images
               if(!randArray.isEmpty()) {
                  for (i in 0 until 4) {
                     val employee = jsonArray.getJSONObject(randArray[i])
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
               }
            } catch (e: JSONException) {
               e.printStackTrace()
            }
         }, { error -> error.printStackTrace() })
      requestQueue?.add(request)
   }

   /**
    * Überprüft die vom Nutzer gegebene Antwort
    */
   private fun checkAnswer(
      tempKeywordList: ArrayList<String>,
      v: View,
      inputManager: InputMethodManager
   ) {
      val userAnswer = answerTextView.text.toString()
      if (tempKeywordList.size != 0) {
         // Richtige Antwort
         if (userAnswer.trim().equals(tempKeywordList[0], true)) {

            val bundle = bundleOf(
               "selectedCategory" to tempKeywordList,
               "categoryName" to arguments?.getString("categoryName"),
               "Mistakes" to mistakes
            )
            tempKeywordList.removeAt(0)
            view?.findNavController()?.navigate(R.id.action_gameFragment_to_successFragment, bundle)

            //region soundCheck
            val sharedPrefHint =
               activity?.getSharedPreferences(getString(R.string.wordHint), Context.MODE_PRIVATE)
            if (sharedPrefHint != null && sharedPrefHint.getString(R.string.sounds.toString(), "")
                  .equals("1")
            ) {
               val soundBox = MediaPlayer.create(this.context, R.raw.ding)
               soundBox.start()
            }
            //endregion

            //SetMistakes
            MistakeCountTextView.text = "Fehler: $mistakes"

         } else {
            mistakes++
            answerTextView.hint = "Wrong!"
            answerTextView.setText("")
            //SetMistakes
            this.MistakeCountTextView.text = "Fehler: $mistakes"
         }
      }
      inputManager.hideSoftInputFromWindow(v.windowToken, 0)
   }

   /**
    * Überprüft die Einzigartigkeit eines Bildes in einer gegebenen Liste von Bildern
    */
   private fun isUnique(list: MutableList<Int>, toBeChecked: Int): Boolean {
      for (i in 0 until list.size) {
         if (list[i] == toBeChecked) {
            return false
         }
      }
      return true
   }
}

