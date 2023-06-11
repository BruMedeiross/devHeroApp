package com.brunadev.devheroapp.login.view

import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.DialogFragment
import com.brunadev.devheroapp.R
import com.brunadev.devheroapp.databinding.DialogCreateBinding
import com.brunadev.devheroapp.login.commom.NetworkChecker
import com.brunadev.devheroapp.login.data.model.AddRequest
import com.brunadev.devheroapp.login.viewmodel.DialogViewModel
import kotlinx.android.synthetic.main.dialog_create.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddDialog : DialogFragment() {

    private val viewModel: DialogViewModel by viewModel()
    private var _binding: DialogCreateBinding? = null
    private val binding get() = _binding!!
    private var addRequest: AddRequest? = null

    private val networkChecker: NetworkChecker by lazy {
        NetworkChecker(
            ContextCompat.getSystemService(
                requireContext(),
                ConnectivityManager::class.java
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCreateBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()


        binding.button.setOnClickListener {
            addRequest = AddRequest(
                title = binding.companyText.text.toString(),
                desc = binding.emailText.text.toString(),
                priority =  binding.spinner.selectedItemPosition,
            )
            networkChecker.performActionIfConnectet {
                addRequest?.let { viewModel.saveData(it) }
            }
        }
    }

    private fun setObservers() {
        with(viewModel) {
            formState.observe(this@AddDialog) { valid ->
                if (!valid) {
                    invalidateData()
                } else {
                    addRequest?.let { request(it) }
                }
            }

            newProjectState.observe(this@AddDialog) { user ->
                if (user != null) {
                    savedData()
                } else {
                    errorData()
                }
            }
        }
    }

    private fun errorData() {
        binding.button.setText(R.string.error_api)
        binding.button.setBackgroundColor(Color.RED)
    }

    private fun savedData() {
        binding.button.setText("Dados Salvos")
        binding.button.setBackgroundColor(Color.GREEN)
    }


    private fun invalidateData() {
        binding.companyText.requestFocus()
        binding.emailText.requestFocus()
        binding.hourText.requestFocus()
        binding.companyText.error = getString(R.string.error_add)
        binding.emailText.error = getString(R.string.error_add)
        binding.hourText.error = getString(R.string.error_add)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}