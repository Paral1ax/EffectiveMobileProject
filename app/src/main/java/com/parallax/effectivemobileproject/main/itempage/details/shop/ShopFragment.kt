package com.parallax.effectivemobileproject.main.itempage.details.shop

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.itempage.ItemFragment
import com.parallax.effectivemobileproject.main.itempage.ItemViewModel

class ShopFragment : Fragment() {

    companion object {
        fun newInstance() = ShopFragment()
    }

    private var viewModel: ItemViewModel = ItemFragment.thisViewModel()

    private lateinit var processorTextView: TextView
    private lateinit var cameraTextView: TextView
    private lateinit var ramTextView: TextView
    private lateinit var memoryTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews(view)

        viewModel.responseData.observe(viewLifecycleOwner) {
            val item = viewModel.responseData.value
            if (item != null) {
                processorTextView.text = item.CPU
                cameraTextView.text = item.camera
                ramTextView.text = item.sd
                memoryTextView.text = item.ssd
            }
        }
    }

    private fun bindViews(view: View) {
        processorTextView = view.findViewById(R.id.processor_textview)
        cameraTextView = view.findViewById(R.id.camera_textview)
        ramTextView = view.findViewById(R.id.ram_textview)
        memoryTextView = view.findViewById(R.id.memory_textview)
    }
}