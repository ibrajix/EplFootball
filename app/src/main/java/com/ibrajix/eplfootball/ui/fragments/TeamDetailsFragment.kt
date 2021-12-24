package com.ibrajix.eplfootball.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.ibrajix.eplfootball.R
import com.ibrajix.eplfootball.databinding.FragmentTeamDetailsBinding
import com.ibrajix.eplfootball.databinding.FragmentTeamsBinding
import com.ibrajix.eplfootball.utils.Constants.HEADER_URL
import com.ibrajix.eplfootball.utils.loadSvg

class TeamDetailsFragment : Fragment() {

    private  var _binding: FragmentTeamDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: TeamDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTeamDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        setViews()
        handleClicks()
    }

    private fun setViews(){

        //load header to view
        binding.header.load(HEADER_URL)

        //load club image to view
        binding.imgClub.apply {
            transitionName = args.details.crestUrl
            loadSvg(args.details.crestUrl)
        }

        binding.txtClubName.text = args.details.name

        binding.txtClubLocation.text = args.details.address

        binding.txtFounded.text = args.details.founded.toString()

        binding.txtShortName.text = args.details.tla

        binding.txtPhoneNumber.text = args.details.phone

        binding.txtWebsite.text = args.details.website

        binding.txtEmail.text = args.details.email

        binding.txtStadium.text = args.details.venue

    }

    private fun handleClicks(){

        //on click back
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}