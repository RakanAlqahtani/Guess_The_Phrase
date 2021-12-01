package com.example.guessthephrase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var messages: ArrayList<String>
    var phrase = "This is the secret phrase"
    private val myAnswerDirectionary = mutableMapOf<Int, Char>()
    private var myAnswer = ""
    private var guessLetters = ""
    private var count = 10
    private var guessPhrase = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messages = arrayListOf()
        val adapter = MainAdapter(messages)


        replaceAnswerToStar()

        btGuess.setOnClickListener { addMessages() }

        RVmain.adapter = adapter
        RVmain.layoutManager = LinearLayoutManager(this)


        updateText()

    }

    private fun addMessages() {

        val mes = edText.text.toString()

        if (guessPhrase) {

            if (mes == phrase) {
                disapleEntry()
                showAlertDialong("You win!\n\nPlay Again?")
            } else {
                messages.add("Wrong guess  $mes")
                guessPhrase = false
                updateText()
            }
        } else {
            if (mes.isNotEmpty() && mes.length == 1) {
                myAnswer = ""
                guessPhrase = true
                checkLetter(mes[0])
            } else {
                Snackbar.make(clMain, "Plesse Enter one latter only ", Snackbar.LENGTH_LONG).show()

            }
        }

        edText.text.clear()
        edText.clearFocus()
        RVmain.adapter?.notifyDataSetChanged()
    }

    private fun checkLetter(guessLetter: Char) {
var found = 0
        for (i in phrase.indices){
            if (phrase[i] == guessLetter){
                myAnswerDirectionary[i] = guessLetter
                found++
            }
        }
        for (i in myAnswerDirectionary){myAnswer += myAnswerDirectionary[i.key]}
if(myAnswer == phrase){
    disapleEntry()
    showAlertDialong("You win!\n\nPlay Again?")
}
        if(guessLetters.isEmpty()){guessLetters+=guessLetter}else{guessLetters+=", "+guessLetter}
        if (found>0){
            messages.add("Found $found ${guessLetter.toUpperCase()}(s)")
        }else {
            messages.add("No ${guessLetter.toUpperCase()}s found")
        }
count--
        if(count>0){messages.add("$count  guesses remaining")}
        updateText()

        RVmain.scrollToPosition(messages.size-1)
    }

    private fun showAlertDialong(message: String) {
        val addAlert = AlertDialog.Builder(this)
            .setTitle("Game Over")
            .setMessage(message)

            .setPositiveButton("Yes") { _, _ ->
                this.recreate()
            }.setNegativeButton("No") { _, _ ->
                print("Work!!!")
            }.create().show()
    }

    private fun disapleEntry() {
        btGuess.isEnabled = false
        btGuess.isClickable = false
        edText.isEnabled = false
        edText.isClickable = false
    }

    private fun updateText() {

        tvPhase.text = "Phases:  " + myAnswer.toUpperCase()
        tvLitter.text = "Guessed Letters:  " + guessLetters
        if (guessPhrase) {
            edText.hint = "Guess the full Phases"
        } else {
            edText.hint = "Guess the Letters"


        }

    }

    private fun replaceAnswerToStar() {

        for (i in phrase.indices) {
            if (phrase[i] == ' ') {
                myAnswerDirectionary[i] = ' '
                myAnswer += " "
            } else {
                myAnswerDirectionary[i] = '*'
                myAnswer += "*"

            }

        }

    }




}








