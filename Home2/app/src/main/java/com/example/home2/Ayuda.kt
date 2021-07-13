/*<!--
    EASYCHAMBA Ayuda.kt
    Desarrollado en Kotlin
    Desarollado por Young Developers:
        - Alosilla Sánchez Moreno Guillermo
        - Delgado Rivera Ricardo Mauricio
        - Lazo Acuña Franco Alfredo
        - Mamani Paccori Ray Jose Enrique
        - Rodriguez Lopez Paolo
-->*/
package com.example.home2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.mlkit.nl.smartreply.SmartReply
import com.google.mlkit.nl.smartreply.SmartReplySuggestionResult
import com.google.mlkit.nl.smartreply.TextMessage
import kotlinx.android.synthetic.main.activity_ayuda.*

class Ayuda : AppCompatActivity() {
    private var conversation = ArrayList<TextMessage>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda)
        sendButton.setOnClickListener{
            addMesage(messageText.text.toString())
        }
        hintsButton.setOnClickListener{
            getHints()
        }
        clearButton.setOnClickListener{
            conversation = ArrayList()
            hint0Button.visibility = View.GONE
            hint1Button.visibility = View.GONE
            hint2Button.visibility = View.GONE
        }
        hint0Button.setOnClickListener{
            addMesage(hint0Button.text.toString())
        }
        hint1Button.setOnClickListener{
            addMesage(hint1Button.text.toString())
        }
        hint2Button.setOnClickListener{
            addMesage(hint2Button.text.toString())
        }
    }
    private fun addMesage(text: String){
        conversation.add(
            TextMessage.createForRemoteUser(
                messageText.text.toString(), System.currentTimeMillis(), nameText.text.toString()))
    }
    private fun getHints(){
        val smartReply = SmartReply.getClient()
        smartReply.suggestReplies(conversation)
            .addOnSuccessListener { result ->
                if (result.getStatus() ==
                    SmartReplySuggestionResult.STATUS_NOT_SUPPORTED_LANGUAGE) {
                    Toast.makeText(applicationContext,"Lenguaje no soportado", Toast.LENGTH_SHORT).show()
                } else if (result.getStatus() == SmartReplySuggestionResult.STATUS_SUCCESS) {
                    hint0Button.text = result.suggestions[0].text
                    hint1Button.text = result.suggestions[1].text
                    hint2Button.text = result.suggestions[2].text
                    hint0Button.visibility = View.VISIBLE
                    hint1Button.visibility = View.VISIBLE
                    hint2Button.visibility = View.VISIBLE
                }}
            .addOnFailureListener {
                errorText.text = it.toString()
            }
    }}

