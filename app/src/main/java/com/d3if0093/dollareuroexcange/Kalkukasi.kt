package com.d3if0093.dollareuroexcange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.d3if0093.dollareuroexcange.databinding.FragmentKalkukasiBinding

/**
 * A simple [Fragment] subclass.
 */
class Kalkukasi : Fragment() {
    private lateinit var binding: FragmentKalkukasiBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kalkukasi, container, false)

        return binding.root

    }
}
