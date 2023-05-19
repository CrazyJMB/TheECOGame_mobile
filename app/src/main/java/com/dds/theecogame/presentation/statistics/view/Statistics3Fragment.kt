package com.dds.theecogame.presentation.statistics.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.R
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.StatisticsRepositoryImpl
import androidx.fragment.app.viewModels
import com.dds.theecogame.databinding.FragmentStatistics2Binding

import com.dds.theecogame.databinding.FragmentStatistics3Binding
import com.dds.theecogame.domain.Application
import com.dds.theecogame.domain.repository.StatisticsRepository
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.statistics.viewModel.StatisticsViewModel
import kotlinx.coroutines.launch

class Statistics3Fragment : Fragment() {

    private lateinit var binding: FragmentStatistics3Binding
    private val viewModel: StatisticsViewModel by viewModels()
    private val statisticsRepository: StatisticsRepository = StatisticsRepositoryImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatistics3Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeStats()

        binding.scrollView.overScrollMode = View.OVER_SCROLL_NEVER

        binding.btnBack.setOnClickListener {
            goToMainScreen()
        }

        binding.ivLeft.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, Statistics2Fragment())
                .commit()
        }
    }

    private fun initializeStats() {

        lifecycleScope.launch {
            statisticsRepository.getStatistics(Application.getUser()!!.id).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val stats = it.data

//                        val odsKnowledgeLevel1 = stats!!.ods_knowledge1.toString()
//                        binding.tvPercentageODS1.text =
//                            "${binding.tvPercentageODS1.text} $odsKnowledgeLevel1%"
//
//                        val odsKnowledgeLevel2 = stats!!.ods_knowledge2.toString()
//                        binding.tvPercentageODS2.text =
//                            "${binding.tvPercentageODS2.text} $odsKnowledgeLevel2%"
//
//                        val odsKnowledgeLevel3 = stats!!.ods_knowledge3.toString()
//                        binding.tvPercentageODS3.text =
//                            "${binding.tvPercentageODS3.text} $odsKnowledgeLevel3%"
//
//                        val odsKnowledgeLevel4 = stats!!.ods_knowledge4.toString()
//                        binding.tvPercentageODS4.text =
//                            "${binding.tvPercentageODS4.text} $odsKnowledgeLevel4%"
//
//                        val odsKnowledgeLevel5 = stats!!.ods_knowledge5.toString()
//                        binding.tvPercentageODS5.text =
//                            "${binding.tvPercentageODS5.text} $odsKnowledgeLevel5%"
//
//                        val odsKnowledgeLevel6 = stats!!.ods_knowledge6.toString()
//                        binding.tvPercentageODS6.text =
//                            "${binding.tvPercentageODS6.text} $odsKnowledgeLevel6%"
//
//                        val odsKnowledgeLevel7 = stats!!.ods_knowledge7.toString()
//                        binding.tvPercentageODS7.text =
//                            "${binding.tvPercentageODS7.text} $odsKnowledgeLevel7%"
//
//                        val odsKnowledgeLevel8 = stats!!.ods_knowledge8.toString()
//                        binding.tvPercentageODS8.text =
//                            "${binding.tvPercentageODS8.text} $odsKnowledgeLevel8%"
//
//                        val odsKnowledgeLevel9 = stats!!.ods_knowledge9.toString()
//                        binding.tvPercentageODS9.text =
//                            "${binding.tvPercentageODS9.text} $odsKnowledgeLevel9%"
//
//                        val odsKnowledgeLevel10 = stats!!.ods_knowledge10.toString()
//                        binding.tvPercentageODS10.text =
//                            "${binding.tvPercentageODS10.text} $odsKnowledgeLevel10%"
//
//                        val odsKnowledgeLevel11 = stats!!.ods_knowledge11.toString()
//                        binding.tvPercentageODS11.text =
//                            "${binding.tvPercentageODS11.text} $odsKnowledgeLevel11%"
//
//                        val odsKnowledgeLevel12 = stats!!.ods_knowledge12.toString()
//                        binding.tvPercentageODS12.text =
//                            "${binding.tvPercentageODS12.text} $odsKnowledgeLevel12%"
//
//                        val odsKnowledgeLevel13 = stats!!.ods_knowledge13.toString()
//                        binding.tvPercentageODS13.text =
//                            "${binding.tvPercentageODS13.text} $odsKnowledgeLevel13%"
//
//                        val odsKnowledgeLevel14 = stats!!.ods_knowledge14.toString()
//                        binding.tvPercentageODS14.text =
//                            "${binding.tvPercentageODS14.text} $odsKnowledgeLevel14%"
//
//                        val odsKnowledgeLevel15 = stats!!.ods_knowledge15.toString()
//                        binding.tvPercentageODS15.text =
//                            "${binding.tvPercentageODS15.text} $odsKnowledgeLevel15%"
//
//                        val odsKnowledgeLevel16 = stats!!.ods_knowledge16.toString()
//                        binding.tvPercentageODS16.text =
//                            "${binding.tvPercentageODS16.text} $odsKnowledgeLevel16%"
//
//                        val odsKnowledgeLevel17 = stats!!.ods_knowledge17.toString()
//                        binding.tvPercentageODS17.text =
//                            "${binding.tvPercentageODS17.text} $odsKnowledgeLevel17%"

                    }
                    is Resource.Error -> {}
                }
            }

        }

    }

    private fun goToMainScreen() {
        val mainScreen = Intent(activity, MainScreenActivity::class.java)
        startActivity(mainScreen)
    }

}
