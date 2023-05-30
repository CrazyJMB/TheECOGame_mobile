package com.dds.theecogame.presentation.statistics.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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
                        val stats = it.data!!

                        val odsKnowledgeLevel1 = stats.ods_knowledge1.toString()
                        if (binding.pbODS1.progress < 10 && odsKnowledgeLevel1.toInt() < 10) {
                            binding.pbODS1.progress = odsKnowledgeLevel1.toInt() * 10
                            binding.tvPercentageODS1.text =
                                "${binding.tvPercentageODS1.text} ${odsKnowledgeLevel1.toInt() * 10}%"
                        } else {
                            binding.pbODS1.progress = 100
                            binding.tvPercentageODS1.text = "100%"
                        }

                        val odsKnowledgeLevel2 = stats.ods_knowledge2.toString()
                        if (binding.pbODS2.progress < 10 && odsKnowledgeLevel2.toInt() < 10) {
                            binding.pbODS2.progress = odsKnowledgeLevel2.toInt() * 10
                            binding.tvPercentageODS2.text =
                                "${binding.tvPercentageODS2.text} ${odsKnowledgeLevel2.toInt() * 10}%"
                        } else {
                            binding.pbODS2.progress = 100
                            binding.tvPercentageODS2.text = "100%"
                        }

                        val odsKnowledgeLevel3 = stats.ods_knowledge3.toString()
                        if (binding.pbODS3.progress < 10 && odsKnowledgeLevel3.toInt() < 10) {
                            binding.pbODS3.progress = odsKnowledgeLevel3.toInt() * 10
                            binding.tvPercentageODS3.text =
                                "${binding.tvPercentageODS3.text} ${odsKnowledgeLevel3.toInt() * 10}%"
                        } else {
                            binding.pbODS3.progress = 100
                            binding.tvPercentageODS3.text = "100%"
                        }

                        val odsKnowledgeLevel4 = stats.ods_knowledge4.toString()
                        if (binding.pbODS4.progress < 10 && odsKnowledgeLevel4.toInt() < 10) {
                            binding.pbODS4.progress = odsKnowledgeLevel4.toInt() * 10
                            binding.tvPercentageODS4.text =
                                "${binding.tvPercentageODS4.text} ${odsKnowledgeLevel4.toInt() * 10}%"
                        } else {
                            binding.pbODS4.progress = 100
                            binding.tvPercentageODS4.text = "100%"
                        }

                        val odsKnowledgeLevel5 = stats.ods_knowledge5.toString()
                        if (binding.pbODS5.progress < 10 && odsKnowledgeLevel5.toInt() < 10) {
                            binding.pbODS5.progress = odsKnowledgeLevel5.toInt() * 10
                            binding.tvPercentageODS5.text =
                                "${binding.tvPercentageODS5.text} ${odsKnowledgeLevel5.toInt() * 10}%"
                        } else {
                            binding.pbODS5.progress = 100
                            binding.tvPercentageODS5.text = "100%"
                        }

                        val odsKnowledgeLevel6 = stats.ods_knowledge6.toString()
                        if (binding.pbODS6.progress < 10 && odsKnowledgeLevel6.toInt() < 10) {
                            binding.pbODS6.progress = odsKnowledgeLevel6.toInt() * 10
                            binding.tvPercentageODS6.text =
                                "${binding.tvPercentageODS6.text} ${odsKnowledgeLevel6.toInt() * 10}%"
                        } else {
                            binding.pbODS6.progress = 100
                            binding.tvPercentageODS6.text = "100%"
                        }

                        val odsKnowledgeLevel7 = stats.ods_knowledge7.toString()
                        if (binding.pbODS7.progress < 10 && odsKnowledgeLevel7.toInt() < 10) {
                            binding.pbODS7.progress = odsKnowledgeLevel7.toInt() * 10
                            binding.tvPercentageODS7.text =
                                "${binding.tvPercentageODS7.text} ${odsKnowledgeLevel7.toInt() * 10}%"
                        } else {
                            binding.pbODS7.progress = 100
                            binding.tvPercentageODS7.text = "100%"
                        }

                        val odsKnowledgeLevel8 = stats.ods_knowledge8.toString()
                        if (binding.pbODS8.progress < 10 && odsKnowledgeLevel8.toInt() < 10) {
                            binding.pbODS8.progress = odsKnowledgeLevel8.toInt() * 10
                            binding.tvPercentageODS8.text =
                                "${binding.tvPercentageODS8.text} ${odsKnowledgeLevel8.toInt() * 10}%"
                        } else {
                            binding.pbODS8.progress = 100
                            binding.tvPercentageODS8.text = "100%"
                        }

                        val odsKnowledgeLevel9 = stats.ods_knowledge9.toString()
                        if (binding.pbODS9.progress < 10 && odsKnowledgeLevel9.toInt() < 10) {
                            binding.pbODS9.progress = odsKnowledgeLevel9.toInt() * 10
                            binding.tvPercentageODS9.text =
                                "${binding.tvPercentageODS9.text} ${odsKnowledgeLevel9.toInt() * 10}%"
                        } else {
                            binding.pbODS9.progress = 100
                            binding.tvPercentageODS9.text = "100%"
                        }

                        val odsKnowledgeLevel10 = stats.ods_knowledge10.toString()
                        if (binding.pbODS10.progress < 10 && odsKnowledgeLevel10.toInt() < 10) {
                            binding.pbODS10.progress = odsKnowledgeLevel10.toInt() * 10
                            binding.tvPercentageODS10.text =
                                "${binding.tvPercentageODS10.text} ${odsKnowledgeLevel10.toInt() * 10}%"
                        } else {
                            binding.pbODS10.progress = 100
                            binding.tvPercentageODS10.text = "100%"
                        }

                        val odsKnowledgeLevel11 = stats.ods_knowledge11.toString()
                        if (binding.pbODS11.progress < 10 && odsKnowledgeLevel11.toInt() < 10) {
                            binding.pbODS11.progress = odsKnowledgeLevel11.toInt() * 10
                            binding.tvPercentageODS11.text =
                                "${binding.tvPercentageODS11.text} ${odsKnowledgeLevel11.toInt() * 10}%"
                        } else {
                            binding.pbODS11.progress = 100
                            binding.tvPercentageODS11.text = "100%"
                        }

                        val odsKnowledgeLevel12 = stats.ods_knowledge12.toString()
                        if (binding.pbODS12.progress < 10 && odsKnowledgeLevel12.toInt() < 10) {
                            binding.pbODS12.progress = odsKnowledgeLevel12.toInt() * 10
                            binding.tvPercentageODS12.text =
                                "${binding.tvPercentageODS12.text} ${odsKnowledgeLevel12.toInt() * 10}%"
                        } else {
                            binding.pbODS12.progress = 100
                            binding.tvPercentageODS12.text = "100%"
                        }

                        val odsKnowledgeLevel13 = stats.ods_knowledge13.toString()
                        if (binding.pbODS13.progress < 10 && odsKnowledgeLevel13.toInt() < 10) {
                            binding.pbODS13.progress = odsKnowledgeLevel13.toInt() * 10
                            binding.tvPercentageODS13.text =
                                "${binding.tvPercentageODS13.text} ${odsKnowledgeLevel13.toInt() * 10}%"
                        } else {
                            binding.pbODS13.progress = 100
                            binding.tvPercentageODS13.text = "100%"
                        }

                        val odsKnowledgeLevel14 = stats.ods_knowledge14.toString()
                        if (binding.pbODS14.progress < 10 && odsKnowledgeLevel14.toInt() < 10) {
                            binding.pbODS14.progress = odsKnowledgeLevel14.toInt() * 10
                            binding.tvPercentageODS14.text =
                                "${binding.tvPercentageODS14.text} ${odsKnowledgeLevel14.toInt() * 10}%"
                        } else {
                            binding.pbODS14.progress = 100
                            binding.tvPercentageODS14.text = "100%"
                        }

                        val odsKnowledgeLevel15 = stats.ods_knowledge15.toString()
                        if (binding.pbODS15.progress < 10 && odsKnowledgeLevel15.toInt() < 10) {
                            binding.pbODS15.progress = odsKnowledgeLevel15.toInt() * 10
                            binding.tvPercentageODS15.text =
                                "${binding.tvPercentageODS15.text} ${odsKnowledgeLevel15.toInt() * 10}%"
                        } else {
                            binding.pbODS15.progress = 100
                            binding.tvPercentageODS15.text = "100%"
                        }

                        val odsKnowledgeLevel16 = stats.ods_knowledge16.toString()
                        if (binding.pbODS16.progress < 10 && odsKnowledgeLevel16.toInt() < 10) {
                            binding.pbODS16.progress = odsKnowledgeLevel16.toInt() * 10
                            binding.tvPercentageODS16.text =
                                "${binding.tvPercentageODS16.text} ${odsKnowledgeLevel16.toInt() * 10}%"
                        } else {
                            binding.pbODS16.progress = 100
                            binding.tvPercentageODS16.text = "100%"
                        }

                        val odsKnowledgeLevel17 = stats.ods_knowledge17.toString()
                        if (binding.pbODS17.progress < 10 && odsKnowledgeLevel17.toInt() < 10) {
                            binding.pbODS17.progress = odsKnowledgeLevel17.toInt() * 10
                            binding.tvPercentageODS17.text =
                                "${binding.tvPercentageODS17.text} ${odsKnowledgeLevel17.toInt() * 10}%"
                        } else {
                            binding.pbODS17.progress = 100
                            binding.tvPercentageODS17.text = "100%"
                        }

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
