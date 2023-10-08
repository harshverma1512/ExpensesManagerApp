package com.example.expensesmanagerapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.expensesmanagerapp.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    lateinit var binding: FragmentNotificationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return FragmentNotificationBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }
}