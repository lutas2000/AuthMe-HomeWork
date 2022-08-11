package com.lutas.authme.presentation.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lutas.authme.R
import com.lutas.authme.databinding.ItemUserBinding
import com.lutas.authme.domain.model.User

class UsersAdapter(private val navController: NavController)
    : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    private var users: List<User> = emptyList()

    class ViewHolder(
        private val binding: ItemUserBinding,
        private val navController: NavController
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                root.setOnClickListener {
                    val action = UsersFragmentDirections
                        .actionUsersFragmentToUserProfileFragment(user.login)
                    navController.navigate(action)
                }
                this.user = user
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemUserBinding.inflate(layoutInflater, viewGroup, false)
        return ViewHolder(binding, navController)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val user = users[position]
        viewHolder.bind(user)
    }

    override fun getItemCount() = users.size

    fun updateData(newData: List<User>) {
        users = newData
        notifyDataSetChanged()
    }
}