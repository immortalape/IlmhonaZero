package com.example.ilmhonazero

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter() :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    private var contactsList = emptyList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contacts_view_holder, parent, false)
        return ContactsViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val currentItem = contactsList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = contactsList.size

    class ContactsViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.name_text_view)
        private val number = view.findViewById<TextView>(R.id.number_text_view)
        private val age = view.findViewById<TextView>(R.id.age_text_view)

        fun bind(contact: Contact) {
            name.text = contact.name
            number.text = contact.number.toString()
            age.text = context.getString(R.string.age, contact.age)
        }
    }

    fun setData(contactsList: List<Contact>) {
        this.contactsList = contactsList
        notifyDataSetChanged()
    }
}
