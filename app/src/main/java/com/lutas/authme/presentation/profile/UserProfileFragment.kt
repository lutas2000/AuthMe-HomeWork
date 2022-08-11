package com.lutas.authme.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs
import com.lutas.authme.databinding.FragmentUserProfileBinding
import com.lutas.authme.presentation.profile.viewmodel.UserProfileViewModel
import com.lutas.authme.util.observe
import com.lutas.authme.util.observeVisibleEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserProfileFragment : Fragment() {
    private val args: UserProfileFragmentArgs by navArgs()
    private val profileViewModel: UserProfileViewModel by viewModel()
    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewModel.getProfile(args.userName)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.apply {
            observe(profile) { profile ->
                profile?.let {
                    binding.profile = it
                }
            }
            observeVisibleEvent(isLoading, binding.progressBar)
            observe(error) {
                it?.getContentIfNotHandled()?.let{
                    showErrorAlert()
                }
            }
        }
    }

    private fun showErrorAlert() {
        AlertDialog.Builder(requireContext())
            .setTitle("Oops!")
            .setMessage("Something went wrong.")
            .setPositiveButton(android.R.string.ok, null)
            .setNegativeButton(android.R.string.cancel, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}