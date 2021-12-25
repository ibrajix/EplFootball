package com.ibrajix.eplfootball.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.blankj.utilcode.util.NetworkUtils
import com.ibrajix.eplfootball.R
import com.ibrajix.eplfootball.databinding.FragmentTeamDetailsBinding
import com.ibrajix.eplfootball.network.Resource
import com.ibrajix.eplfootball.ui.adapters.PlayersAdapter
import com.ibrajix.eplfootball.ui.viewmodel.PlayersViewModel
import com.ibrajix.eplfootball.utils.Constants.HEADER_URL
import com.ibrajix.eplfootball.utils.Utility
import com.ibrajix.eplfootball.utils.loadSvg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailsFragment : Fragment() {

    private lateinit var playersAdapter: PlayersAdapter
    private  var _binding: FragmentTeamDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: TeamDetailsFragmentArgs by navArgs()
    private val playersViewModel: PlayersViewModel by viewModels()

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView(){
        startObservingValues()
        setUpAdapter()
        setViews()
        handleClicks()
    }

    private fun startObservingValues(){

        //check if network is available
        if (NetworkUtils.isConnected()){
            getPlayers()
        }
        else{
            Utility.displaySnackBar(binding.root, getString(R.string.not_connected), requireContext())
        }

    }

    private fun getPlayers(){

        playersViewModel.doGetPremierLeagueTeams(args.details.id.toString())
        playersViewModel.premierLeagueTeamPlayers.observe(viewLifecycleOwner){
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    playersAdapter.submitList(it.data?.squad)
                }

                Resource.Status.LOADING -> {
                    showError(it.message?:getString(R.string.error_message))
                }

                Resource.Status.ERROR -> {
                    showError(it.message?:getString(R.string.error_message))
                }

                Resource.Status.FAILURE -> {
                    showError(it.message?:getString(R.string.error_message))
                }
            }
        }


    }

    private fun setUpAdapter(){

        playersAdapter = PlayersAdapter(onClickListener = PlayersAdapter.OnPlayersClickListener{
           //do something when a player is clicked
        })

        binding.rcvPlayers.adapter = playersAdapter

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

    private fun showError(error: String){
        //stop loading, show recycler view and show error
        Utility.displaySnackBar(binding.root, error, requireContext())
    }

}