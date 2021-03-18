package com.hashaan.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),INotesAdapter {
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: NoteViewModel
    lateinit var EditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter = NotesAdapter(this,this)
        recyclerView.adapter =adapter

         EditText = findViewById<EditText>(R.id.input)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list-> list?.let {
            adapter.updateList(it)
        }


        })


    }

    override fun onItemClicked(note: Note) {
         viewModel.deleteNote(note)
         Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText = EditText.text.toString()
        if (noteText.isNotEmpty())
            viewModel.insertNote(Note(noteText))
        Toast.makeText(this,"$noteText inserted",Toast.LENGTH_LONG).show()

    }

}