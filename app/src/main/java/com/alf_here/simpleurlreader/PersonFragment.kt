package com.alf_here.simpleurlreader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PersonFragment : Fragment() {

    private lateinit var viewModel: PersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.person_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.people_recyclerview)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        val peopleAdapter = PeopleAdapter()
        recycler.adapter = peopleAdapter
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PersonViewModel::class.java)

        viewModel.person.observe(requireActivity()) {
            peopleAdapter.submitList(listOf(it))
        }
    }
}