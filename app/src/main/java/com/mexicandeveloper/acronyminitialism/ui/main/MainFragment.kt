package com.mexicandeveloper.acronyminitialism.ui.main

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mexicandeveloper.acronyminitialism.databinding.FragmentMainBinding
import com.mexicandeveloper.acronyminitialism.models.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.res.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.pb.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.pb.visibility = View.GONE

                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                }
                Status.SUCCESS -> {
                    binding.pb.visibility = View.GONE
                    (binding.rvAbreviation.adapter as RvAdapter).updateInfo(it.data ?: emptyList())
                }

            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionListener = OnEditorActionListener() { textView, i, keyEvent ->
            updateList()
            return@OnEditorActionListener true
        }
        binding.abreviation.setOnEditorActionListener(actionListener)
        binding.fullForm.setOnEditorActionListener(actionListener)

        binding.checkResults.setOnClickListener {
            updateList()
        }
        binding.rvAbreviation.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvAbreviation.adapter = RvAdapter(emptyList())
    }

    private fun updateList() {
        hideKeyboard()
        (binding.rvAbreviation.adapter as RvAdapter).updateInfo(emptyList())
        viewModel.getAbreviationOrFullText(
            binding.abreviation.text.toString(),
            binding.fullForm.text.toString()
        )
    }

}

fun Fragment.hideKeyboard() {
    val inputMethodManager =
        requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
}
