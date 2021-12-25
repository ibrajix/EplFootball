package com.ibrajix.eplfootball.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.SnackbarUtils
import com.ibrajix.eplfootball.R
import com.ibrajix.eplfootball.databinding.FragmentTeamsBinding
import com.ibrajix.eplfootball.network.Resource
import com.ibrajix.eplfootball.ui.adapters.PremierLeagueTeamAdapter
import com.ibrajix.eplfootball.ui.viewmodel.PremierLeagueTeamViewModel
import com.ibrajix.eplfootball.utils.Utility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TeamsFragment : Fragment() {

    private  var _binding: FragmentTeamsBinding? = null
    private val binding get() = _binding!!

    //initialize view model
    private val viewModel: PremierLeagueTeamViewModel by viewModels()

    //initialize adapter
    private lateinit var premierLeagueTeamAdapter: PremierLeagueTeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTeamsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //start initializing views and codes
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews(){
        setUpSwipeToRefresh()
        setUpAdapter()
        startCollectingValues()
    }


    private fun setUpSwipeToRefresh(){

        binding.root.apply {
            setOnRefreshListener {
                startCollectingValues()
                isRefreshing = false
            }
        }

    }


    private fun setUpAdapter(){

        premierLeagueTeamAdapter = PremierLeagueTeamAdapter(onClickListener = PremierLeagueTeamAdapter.OnTeamItemClickListener{it, view ->
            val extras = FragmentNavigatorExtras(view to it.crestUrl)
            val action = TeamsFragmentDirections.actionClubsFragmentToTeamDetailsFragment(it)
            findNavController().navigate(action, extras)
        })

        //set recycler view to adapter
        binding.rcvPremiership.apply {
            adapter = premierLeagueTeamAdapter
            layoutManager = GridLayoutManager(context, 3)

            //return transition
            postponeEnterTransition()
            viewTreeObserver
                .addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
        }

    }

    private fun startCollectingValues(){

        //check if network is available
        if (NetworkUtils.isConnected()){
            getPremierLeagueTeams()
        }
        else{
            Utility.displaySnackBar(binding.root, getString(R.string.not_connected), requireContext())
        }
    }

    private fun getPremierLeagueTeams(){

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.doGetPremierLeagueTeams()

                viewModel.premierLeagueTeams.collect {

                    when (it.status) {

                        Resource.Status.SUCCESS -> {
                            premierLeagueTeamAdapter.submitList(it.data?.teams)
                            binding.loadingCompetitions.visibility = View.GONE
                            binding.rcvPremiership.visibility = View.VISIBLE
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
        }
    }

    private fun showError(error: String){

        //stop loading, show recycler view and show error
        binding.loadingCompetitions.visibility = View.GONE
        binding.rcvPremiership.visibility = View.VISIBLE
        Utility.displaySnackBar(binding.root, error, requireContext())

    }

}