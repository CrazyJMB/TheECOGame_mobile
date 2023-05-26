package com.dds.theecogame.presentation.ranking.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.R
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.StatisticsRepositoryImpl
import com.dds.theecogame.databinding.ActivityMainScreenBinding
import com.dds.theecogame.databinding.ActivityRankingBinding
import com.dds.theecogame.domain.Application
import com.dds.theecogame.domain.repository.StatisticsRepository
import com.dds.theecogame.domain.repository.UserRepository
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.mainScreen.viewModel.MainScreenViewModel
import com.dds.theecogame.presentation.ranking.viewModel.RankingViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RankingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRankingBinding
    private val viewModel: RankingViewModel by viewModels()
    private val rankingRepository: StatisticsRepository = StatisticsRepositoryImpl()

    //Variables para ranking
    private var actualUserRanking: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actualUser = Application.getUser()!!

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainScreenActivity::class.java))
        }

        //Establecer datos usuario actual

        //1: Obtener ranking del usuario actual
        lifecycleScope.launch {
            rankingRepository.getUserRanking(Application.getUser()!!.id).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        binding.tvUserRank.text = it.data!!.toString()
                        binding.tvUserUsername.text = actualUser.username
                        binding.tvUserPoints.text = "0"
                        //FIXME: No puedo obtener los puntos totales del usuario actual
                    }
                    is Resource.Error -> {
                    }
                }
            }
        }

        //Obtener la información del top 10
        lifecycleScope.launch {
            rankingRepository.getRanking().collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val ranking = it.data!!

                        //User 1
                        //binding.ivUserAvatar1 //FIXME: Obtener imagen desde URL
                        binding.tvUserUsername1.text = ranking[0].username
                        binding.tvUserPoints1.text = ranking[0].score.toString()

                        //User 2
                        //binding.ivUserAvatar2 //FIXME: Obtener imagen desde URL
                        binding.tvUserUsername2.text = ranking[1].username
                        binding.tvUserPoints2.text = ranking[1].score.toString()

                        //User 3
                        //binding.ivUserAvatar3 //FIXME: Obtener imagen desde URL
                        binding.tvUserUsername3.text = ranking[2].username
                        binding.tvUserPoints3.text = ranking[2].score.toString()

                        //User 4
                        //binding.ivUserAvatar4 //FIXME: Obtener imagen desde URL
                        binding.tvUserUsername4.text = ranking[3].username
                        binding.tvUserPoints4.text = ranking[3].score.toString()

                        //User 5
                        //binding.ivUserAvatar5 //FIXME: Obtener imagen desde URL
                        binding.tvUserUsername5.text = ranking[4].username
                        binding.tvUserPoints5.text = ranking[4].score.toString()

                        //User 6
                        //binding.ivUserAvatar6 //FIXME: Obtener imagen desde URL
                        binding.tvUserUsername6.text = ranking[5].username
                        binding.tvUserPoints6.text = ranking[5].score.toString()

                        //User 7
                        //binding.ivUserAvatar7 //FIXME: Obtener imagen desde URL
                        binding.tvUserUsername7.text = ranking[6].username
                        binding.tvUserPoints7.text = ranking[6].score.toString()

                        //User 8
                        //binding.ivUserAvatar8 //FIXME: Obtener imagen desde URL
                        binding.tvUserUsername8.text = ranking[7].username
                        binding.tvUserPoints8.text = ranking[7].score.toString()

                        //User 9
                        //binding.ivUserAvatar9 //FIXME: Obtener imagen desde URL
                        binding.tvUserUsername9.text = ranking[8].username
                        binding.tvUserPoints9.text = ranking[8].score.toString()

                        //User 10
                        //binding.ivUserAvatar10 //FIXME: Obtener imagen desde URL
                        binding.tvUserUsername10.text = ranking[9].username
                        binding.tvUserPoints10.text = ranking[9].score.toString()

                    }
                    is Resource.Error -> {
                    }
                }
            }
        }

    }
}