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
import com.squareup.picasso.Picasso
import java.lang.Exception

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
                        binding.tvUserRank.text = it.data!!.position.toString()
                        if (actualUser.avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar.setImageResource(R.drawable.empty_avatar)
                        } else {
                            try {
                                Picasso.get()
                                    .load(actualUser.avatar)
                                    .into(binding.ivUserAvatar)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        binding.tvUserUsername.text = actualUser.username
                        binding.tvUserPoints.text = it.data!!.score.toString()
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
                        if (ranking[0].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar1.setImageResource(R.drawable.empty_avatar)
                        } else {
                            try {
                                Picasso.get()
                                    .load(ranking[0].avatar)
                                    .into(binding.ivUserAvatar1)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        binding.tvUserUsername1.text = ranking[0].username
                        binding.tvUserPoints1.text = ranking[0].score.toString()

                        //User 2
                        if (ranking[1].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar2.setImageResource(R.drawable.empty_avatar)
                        } else {
                            try {
                                Picasso.get()
                                    .load(ranking[1].avatar)
                                    .into(binding.ivUserAvatar2)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        binding.tvUserUsername2.text = ranking[1].username
                        binding.tvUserPoints2.text = ranking[1].score.toString()

                        //User 3
                        if (ranking[2].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar3.setImageResource(R.drawable.empty_avatar)
                        } else {
                            try {
                                Picasso.get()
                                    .load(ranking[2].avatar)
                                    .into(binding.ivUserAvatar3)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        binding.tvUserUsername3.text = ranking[2].username
                        binding.tvUserPoints3.text = ranking[2].score.toString()

                        //User 4
                        if (ranking[3].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar4.setImageResource(R.drawable.empty_avatar)
                        } else {
                            try {
                                Picasso.get()
                                    .load(ranking[3].avatar)
                                    .into(binding.ivUserAvatar4)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        binding.tvUserUsername4.text = ranking[3].username
                        binding.tvUserPoints4.text = ranking[3].score.toString()

                        //User 5
                        if (ranking[4].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar5.setImageResource(R.drawable.empty_avatar)
                        } else {
                            try {
                                Picasso.get()
                                    .load(ranking[4].avatar)
                                    .into(binding.ivUserAvatar5)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        binding.tvUserUsername5.text = ranking[4].username
                        binding.tvUserPoints5.text = ranking[4].score.toString()

                        //User 6
                        if (ranking[5].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar6.setImageResource(R.drawable.empty_avatar)
                        } else {
                            try {
                                Picasso.get()
                                    .load(ranking[5].avatar)
                                    .into(binding.ivUserAvatar6)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        binding.tvUserUsername6.text = ranking[5].username
                        binding.tvUserPoints6.text = ranking[5].score.toString()

                        //User 7
                        if (ranking[6].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar7.setImageResource(R.drawable.empty_avatar)
                        } else {
                            try {
                                Picasso.get()
                                    .load(ranking[6].avatar)
                                    .into(binding.ivUserAvatar7)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        binding.tvUserUsername7.text = ranking[6].username
                        binding.tvUserPoints7.text = ranking[6].score.toString()

                        //User 8
                        if (ranking[7].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar8.setImageResource(R.drawable.empty_avatar)
                        } else {
                            try {
                                Picasso.get()
                                    .load(ranking[7].avatar)
                                    .into(binding.ivUserAvatar8)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        binding.tvUserUsername8.text = ranking[7].username
                        binding.tvUserPoints8.text = ranking[7].score.toString()

                        //User 9
                        if (ranking[8].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar9.setImageResource(R.drawable.empty_avatar)
                        } else {
                            try {
                                Picasso.get()
                                    .load(ranking[8].avatar)
                                    .into(binding.ivUserAvatar9)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        binding.tvUserUsername9.text = ranking[8].username
                        binding.tvUserPoints9.text = ranking[8].score.toString()

                        //User 10
                        if (ranking[9].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar10.setImageResource(R.drawable.empty_avatar)
                        } else {
                            try {
                                Picasso.get()
                                    .load(ranking[9].avatar)
                                    .into(binding.ivUserAvatar10)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
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