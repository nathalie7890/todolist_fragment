package com.nathalie.todolistfragments.fragments.ui

import android.os.Bundle
import android.os.VibrationEffect.Composition
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.nathalie.todolistfragments.data.Model.Image
import com.nathalie.todolistfragments.R
import com.nathalie.todolistfragments.adapters.ImageSliderAdapter
import com.nathalie.todolistfragments.databinding.FragmentImageGalleryBinding
import kotlinx.coroutines.delay

class ImageGalleryFragment : Fragment() {
    private lateinit var binding: FragmentImageGalleryBinding
    private lateinit var adapter: ImageSliderAdapter

    private val items: List<Image> = listOf(
        Image(R.drawable.image1),
        Image(R.drawable.image2),
        Image(R.drawable.image3),
        Image(R.drawable.image4),
        Image(R.drawable.image5),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageGalleryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("greeting", "Hello from Image Gallery")
            setFragmentResult("from_image_gallery", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }


        adapter = ImageSliderAdapter(items.toMutableList())

        binding.vpImages.let { viewPager ->
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 3
            viewPager.getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val composePageTransformer = CompositePageTransformer()
            composePageTransformer.addTransformer { page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = 0.90f + r * 0.10f
            }

            viewPager.setPageTransformer(composePageTransformer)
            lifecycleScope.launchWhenResumed {
                var position = 0
                while (true) {
                    delay(3000)
                    position = (position + 1) % 7
                    viewPager.setCurrentItem(position, true)
                }
            }
        }
    }
}