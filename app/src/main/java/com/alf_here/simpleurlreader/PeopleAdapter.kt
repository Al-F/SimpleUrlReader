package com.alf_here.simpleurlreader

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class PeopleAdapter : ListAdapter<Person, PersonViewHolder>(PeopleDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.person_name_textview)
    private val lastname: TextView = view.findViewById(R.id.person_lastname_textview)
    private val age: TextView = view.findViewById(R.id.person_age_textview)
    private val phone: TextView = view.findViewById(R.id.person_phone_number_textview)
    private val address: TextView = view.findViewById(R.id.person_address_textview)
    private val gender: ImageView = view.findViewById(R.id.person_gender_imageview)
    fun bind(person: Person) {
        name.text = person.firstName
        lastname.text = person.lastName
        age.text = person.age.toString()
        phone.text = person.phoneNumbers.firstOrNull()?.number
        address.text = itemView.resources.getString(
            R.string.person_address_placeholder,
            person.address.postalCode,
            person.address.city,
            person.address.streetAddress
        )
        val genderDrawable =
            if (person.gender == "Male"){
                R.drawable.ic_baseline_male_24
            } else {
                R.drawable.ic_baseline_female_24
            }
        gender.setImageResource(genderDrawable)
    }
}

object PeopleDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.firstName == newItem.firstName && oldItem.lastName == oldItem.lastName
    }
}
