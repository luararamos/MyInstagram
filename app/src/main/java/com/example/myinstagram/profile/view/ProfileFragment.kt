package com.example.myinstagram.profile.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myinstagram.R
import com.example.myinstagram.common.base.BaseFragment
import com.example.myinstagram.common.base.DependencyInjector
import com.example.myinstagram.common.model.Post
import com.example.myinstagram.common.model.UserAuth
import com.example.myinstagram.databinding.FragmentProfileBinding
import com.example.myinstagram.profile.Profile
import com.example.myinstagram.profile.presenter.ProfilePresenter

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
    FragmentProfileBinding::bind
), Profile.View {

    override lateinit var presenter: Profile.Presenter

    private val adapter = PostAdapter()
    override fun setupViews() {
        binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.profileRv?.adapter = adapter

        presenter.fetchUserProfile()
    }

    override fun setupPresenter() {
        val repository = DependencyInjector.profileRepository()
        presenter = ProfilePresenter(this, repository)
    }

    override fun showProgress(enebled: Boolean) {
        binding?.profileProgress?.visibility = if (enebled) View.VISIBLE else View.GONE
    }

    override fun displayUserProfile(userAuth: UserAuth) {
        binding?.txtProfilePostCount?.text = userAuth.postCount.toString()
        binding?.txtProfileFollowingCount?.text = userAuth.followingCount.toString()
        binding?.txtProfileFollowersCount?.text = userAuth.followersCount.toString()
        binding?.txtProfileUsername?.text = userAuth.name
        binding?.txtProfileBio?.text = "TODO"
        presenter.fetchUserPost()
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayEmptyPost() {
        binding?.txtProfileEmpty?.visibility = View.VISIBLE
        binding?.profileRv?.visibility = View.GONE

    }

    override fun displayFullPosts(posts: List<Post>) {
        binding?.txtProfileEmpty?.visibility = View.GONE
        binding?.profileRv?.visibility = View.VISIBLE
        adapter.items = posts
        adapter.notifyDataSetChanged()
    }

    override fun getMenu(): Int {
        return R.menu.menu_profile
    }

}