package com.majd.techestisharatiassignment.ui.fragments.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.majd.techestisharatiassignment.R
import com.majd.techestisharatiassignment.ui.activities.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), ProfileView {
    lateinit var presenter: ProfilePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Initiate presenters
        initPresenters()
        // Initiate actions listeners
        initListeners()
    }

    // Initiate presenters
    private fun initPresenters() {
        presenter = ProfilePresenter(this)
    }

    // Initiate actions listeners
    private fun initListeners() {
        // Logout action listener
        logout.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }
    }
}