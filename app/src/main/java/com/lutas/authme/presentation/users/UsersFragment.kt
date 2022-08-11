package com.lutas.authme.presentation.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lutas.authme.databinding.FragmentUsersBinding
import com.lutas.authme.presentation.users.viewmodel.UsersViewModel
import com.lutas.authme.util.observe
import com.lutas.authme.util.observeVisibleEvent
import org.koin.androidx.viewmodel.ext.android.viewModel


class UsersFragment : Fragment() {
    private val usersViewModel: UsersViewModel by viewModel()
    private lateinit var binding: FragmentUsersBinding
    private lateinit var usersAdapter: UsersPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater)
        val navController = findNavController()
        usersAdapter = UsersPagingAdapter(navController)
        initView(binding)
        return binding.root
    }

    private fun initView(binding: FragmentUsersBinding) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usersAdapter
        }
        usersAdapter.addLoadStateListener {
            binding.count = usersAdapter.itemCount
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersViewModel.apply {
            getUsersLiveData().observe(viewLifecycleOwner) {
                it?.let {
                    usersAdapter.submitData(lifecycle, it)
                }
            }
        }
    }
}