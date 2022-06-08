package com.example.tp1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp1.databinding.FragmentAnimauxListBinding

class AnimauxList(
    val  animaux : List<Animaux>
) : Fragment() {

    var binding : FragmentAnimauxListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentAnimauxListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var monRecycler = binding?.RecycleView
        monRecycler?.layoutManager = LinearLayoutManager(requireContext())
        monRecycler?.adapter = AnimauxAdapter(animaux.toTypedArray()) { animaux, position ->
            var animauxfragment = AnimauxDetails()
                animauxfragment.arguments = Bundle().apply {
                this.putParcelable("animaux", animaux)
                this.putInt("position", position)
            }

            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, animauxfragment).commit()
        }
    }
}