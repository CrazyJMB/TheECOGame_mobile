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
        //initializeStats()

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

    /*
        private fun initializeStats() {

            lifecycleScope.launch {
                statisticsRepository.getStatistics(Application.getUser()!!.id).collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            val stats = it.data

                            val odsKnowledgeLevel1 = stats!!.ods_knowledge1.toString()
                            binding.tvODS1.text =
                                "${binding.tvODS1.text} $odsKnowledgeLevel1%"

                            val odsKnowledgeLevel2 = stats!!.ods_knowledge2.toString()
                            binding.tvODS2.text =
                                "${binding.tvODS2.text} $odsKnowledgeLevel2%"

                            val odsKnowledgeLevel3 = stats!!.ods_knowledge3.toString()
                            binding.tvODS3.text =
                                "${binding.tvODS3.text} $odsKnowledgeLevel3%"

                            val odsKnowledgeLevel4 = stats!!.ods_knowledge4.toString()
                            binding.tvODS4.text =
                                "${binding.tvODS4.text} $odsKnowledgeLevel4%"

                            val odsKnowledgeLevel5 = stats!!.ods_knowledge5.toString()
                            binding.tvODS5.text =
                                "${binding.tvODS5.text} $odsKnowledgeLevel5%"

                            val odsKnowledgeLevel6 = stats!!.ods_knowledge6.toString()
                            binding.tvODS6.text =
                                "${binding.tvODS6.text} $odsKnowledgeLevel6%"

                            val odsKnowledgeLevel7 = stats!!.ods_knowledge7.toString()
                            binding.tvODS7.text =
                                "${binding.tvODS7.text} $odsKnowledgeLevel7%"

                            val odsKnowledgeLevel8 = stats!!.ods_knowledge8.toString()
                            binding.tvODS8.text =
                                "${binding.tvODS8.text} $odsKnowledgeLevel8%"

                            val odsKnowledgeLevel9 = stats!!.ods_knowledge9.toString()
                            binding.tvODS9.text =
                                "${binding.tvODS9.text} $odsKnowledgeLevel9%"

                            val odsKnowledgeLevel10 = stats!!.ods_knowledge10.toString()
                            binding.tvODS10.text =
                                "${binding.tvODS10.text} $odsKnowledgeLevel10%"

                            val odsKnowledgeLevel11 = stats!!.ods_knowledge11.toString()
                            binding.tvODS11.text =
                                "${binding.tvODS11.text} $odsKnowledgeLevel11%"

                            val odsKnowledgeLevel12 = stats!!.ods_knowledge12.toString()
                            binding.tvODS12.text =
                                "${binding.tvODS12.text} $odsKnowledgeLevel12%"

                            val odsKnowledgeLevel13 = stats!!.ods_knowledge13.toString()
                            binding.tvODS13.text =
                                "${binding.tvODS13.text} $odsKnowledgeLevel13%"

                            val odsKnowledgeLevel14 = stats!!.ods_knowledge14.toString()
                            binding.tvODS14.text =
                                "${binding.tvODS14.text} $odsKnowledgeLevel14%"

                            val odsKnowledgeLevel15 = stats!!.ods_knowledge15.toString()
                            binding.tvODS15.text =
                                "${binding.tvODS15.text} $odsKnowledgeLevel15%"

                            val odsKnowledgeLevel16 = stats!!.ods_knowledge16.toString()
                            binding.tvODS16.text =
                                "${binding.tvODS16.text} $odsKnowledgeLevel16%"

                            val odsKnowledgeLevel17 = stats!!.ods_knowledge17.toString()
                            binding.tvODS17.text =
                                "${binding.tvODS17.text} $odsKnowledgeLevel17%"

                        }
                        is Resource.Error -> {}
                    }
                }

            }

        }
    */
    private fun goToMainScreen() {
        val mainScreen = Intent(activity, MainScreenActivity::class.java)
        startActivity(mainScreen)
    }

}
