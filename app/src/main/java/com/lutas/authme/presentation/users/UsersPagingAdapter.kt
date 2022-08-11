package com.lutas.authme.presentation.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lutas.authme.databinding.ItemUserBinding
import com.lutas.authme.domain.model.User

class UsersPagingAdapter(
    private val navController: NavController
): PagingDataAdapter<User, UsersPagingAdapter.ViewHolder>(Comparator) {

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
        val user = getItem(position)!!
        viewHolder.bind(user)
    }

    object Comparator: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}