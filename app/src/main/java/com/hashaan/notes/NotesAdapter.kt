package com.hashaan.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class NotesAdapter(private val context: Context, private val listener: INotesAdapter): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private val allNotes = ArrayList<Note>()

    inner class NotesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val textView:TextView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton:ImageView = itemView.findViewById<ImageView>(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked((allNotes[viewHolder.adapterPosition]))

        }

        return viewHolder


    }

    override fun getItemCount(): Int {
        return allNotes.size

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text


    }

    fun updateList(newList : List<Note>){

        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INotesAdapter {
    fun onItemClicked(note: Note)

}
