package com.example.tp1;

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tp1.databinding.FragmentAnimauxDetailsBinding

public class AnimauxDetails : Fragment() {

    private var binding: FragmentAnimauxDetailsBinding? = null

    private var animal: Animaux? = null
    private var position: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentAnimauxDetailsBinding.inflate(inflater, container, false)

        return binding?.root
    }

    private fun chooseImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        val IMAGE_CHOOSE = 1000
        startActivityForResult(intent, IMAGE_CHOOSE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding?.image?.setImageURI(data?.data)
        animal?.portrait = data?.data.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.image?.setOnClickListener() {
            chooseImageGallery()
        }

        animal = arguments?.getParcelable<Animaux>("animaux")
        val position = arguments?.getInt("position") ?: 0

        binding?.nom?.setText(animal?.nom)
        binding?.description?.setText(animal?.description)

        binding?.image?.let { image ->
            Glide.with(this)
                .load(animal?.portrait)
                .into(image)
        };
        binding?.buttonValider?.setOnClickListener {
           animal?.let {
               it.nom = binding?.nom?.text.toString()
               it.description = binding?.description?.text.toString()
               (requireActivity() as MainActivity).animauxlist[position] = it
            }
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, AnimauxList((requireActivity() as MainActivity).animauxlist)).commit()
        }
    }


    // get
}
