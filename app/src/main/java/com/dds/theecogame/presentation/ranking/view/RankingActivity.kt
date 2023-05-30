package com.dds.theecogame.presentation.ranking.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.R
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.StatisticsRepositoryImpl
import com.dds.theecogame.databinding.ActivityRankingBinding
import com.dds.theecogame.domain.Application
import com.dds.theecogame.domain.repository.StatisticsRepository
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.ranking.viewModel.RankingViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.MainScope

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
                            MainScope().launch {
                                val bitmap = viewModel.loadImageFromUrl(actualUser.avatar)
                                if (bitmap != null) {
                                    // Mostrar la imagen en un ImageView
                                    binding.ivUserAvatar.setImageBitmap(bitmap)
                                } else {
                                    binding.ivUserAvatar.setImageResource(R.drawable.empty_avatar)
                                }
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
                            MainScope().launch {
                                val bitmap = viewModel.loadImageFromUrl(ranking[0].avatar!!)
                                if (bitmap != null) {
                                    // Mostrar la imagen en un ImageView
                                    binding.ivUserAvatar1.setImageBitmap(bitmap)
                                } else {
                                    binding.ivUserAvatar1.setImageResource(R.drawable.empty_avatar)
                                }
                            }
                        }
                        binding.tvUserUsername1.text = ranking[0].username
                        binding.tvUserPoints1.text = ranking[0].score.toString()

                        //User 2
                        if (ranking[1].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar2.setImageResource(R.drawable.empty_avatar)
                        } else {
                            MainScope().launch {
                                val bitmap = viewModel.loadImageFromUrl(ranking[1].avatar!!)
                                if (bitmap != null) {
                                    // Mostrar la imagen en un ImageView
                                    binding.ivUserAvatar2.setImageBitmap(bitmap)
                                } else {
                                    binding.ivUserAvatar2.setImageResource(R.drawable.empty_avatar)
                                }
                            }
                        }
                        binding.tvUserUsername2.text = ranking[1].username
                        binding.tvUserPoints2.text = ranking[1].score.toString()

                        //User 3
                        if (ranking[2].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar3.setImageResource(R.drawable.empty_avatar)
                        } else {
                            MainScope().launch {
                                val bitmap = viewModel.loadImageFromUrl(ranking[2].avatar!!)
                                if (bitmap != null) {
                                    // Mostrar la imagen en un ImageView
                                    binding.ivUserAvatar3.setImageBitmap(bitmap)
                                } else {
                                    binding.ivUserAvatar3.setImageResource(R.drawable.empty_avatar)
                                }
                            }
                        }
                        binding.tvUserUsername3.text = ranking[2].username
                        binding.tvUserPoints3.text = ranking[2].score.toString()

                        //User 4
                        if (ranking[3].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar4.setImageResource(R.drawable.empty_avatar)
                        } else {
                            MainScope().launch {
                                val bitmap = viewModel.loadImageFromUrl(ranking[3].avatar!!)
                                if (bitmap != null) {
                                    // Mostrar la imagen en un ImageView
                                    binding.ivUserAvatar4.setImageBitmap(bitmap)
                                } else {
                                    binding.ivUserAvatar4.setImageResource(R.drawable.empty_avatar)
                                }
                            }
                        }
                        binding.tvUserUsername4.text = ranking[3].username
                        binding.tvUserPoints4.text = ranking[3].score.toString()

                        //User 5
                        if (ranking[4].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar5.setImageResource(R.drawable.empty_avatar)
                        } else {
                            MainScope().launch {
                                val bitmap = viewModel.loadImageFromUrl(ranking[4].avatar!!)
                                if (bitmap != null) {
                                    // Mostrar la imagen en un ImageView
                                    binding.ivUserAvatar5.setImageBitmap(bitmap)
                                } else {
                                    binding.ivUserAvatar5.setImageResource(R.drawable.empty_avatar)
                                }
                            }
                        }
                        binding.tvUserUsername5.text = ranking[4].username
                        binding.tvUserPoints5.text = ranking[4].score.toString()

                        //User 6
                        if (ranking[5].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar6.setImageResource(R.drawable.empty_avatar)
                        } else {
                            MainScope().launch {
                                val bitmap = viewModel.loadImageFromUrl(ranking[5].avatar!!)
                                if (bitmap != null) {
                                    // Mostrar la imagen en un ImageView
                                    binding.ivUserAvatar6.setImageBitmap(bitmap)
                                } else {
                                    binding.ivUserAvatar6.setImageResource(R.drawable.empty_avatar)
                                }
                            }
                        }
                        binding.tvUserUsername6.text = ranking[5].username
                        binding.tvUserPoints6.text = ranking[5].score.toString()

                        //User 7
                        if (ranking[6].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar7.setImageResource(R.drawable.empty_avatar)
                        } else {
                            MainScope().launch {
                                val bitmap = viewModel.loadImageFromUrl(ranking[6].avatar!!)
                                if (bitmap != null) {
                                    // Mostrar la imagen en un ImageView
                                    binding.ivUserAvatar7.setImageBitmap(bitmap)
                                } else {
                                    binding.ivUserAvatar7.setImageResource(R.drawable.empty_avatar)
                                }
                            }
                        }
                        binding.tvUserUsername7.text = ranking[6].username
                        binding.tvUserPoints7.text = ranking[6].score.toString()

                        //User 8
                        if (ranking[7].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar8.setImageResource(R.drawable.empty_avatar)
                        } else {
                            MainScope().launch {
                                val bitmap = viewModel.loadImageFromUrl(ranking[7].avatar!!)
                                if (bitmap != null) {
                                    // Mostrar la imagen en un ImageView
                                    binding.ivUserAvatar8.setImageBitmap(bitmap)
                                } else {
                                    binding.ivUserAvatar8.setImageResource(R.drawable.empty_avatar)
                                }
                            }
                        }
                        binding.tvUserUsername8.text = ranking[7].username
                        binding.tvUserPoints8.text = ranking[7].score.toString()

                        //User 9
                        if (ranking[8].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar9.setImageResource(R.drawable.empty_avatar)
                        } else {
                            MainScope().launch {
                                val bitmap = viewModel.loadImageFromUrl(ranking[8].avatar!!)
                                if (bitmap != null) {
                                    // Mostrar la imagen en un ImageView
                                    binding.ivUserAvatar9.setImageBitmap(bitmap)
                                } else {
                                    binding.ivUserAvatar9.setImageResource(R.drawable.empty_avatar)
                                }
                            }
                        }
                        binding.tvUserUsername9.text = ranking[8].username
                        binding.tvUserPoints9.text = ranking[8].score.toString()

                        //User 10
                        if (ranking[9].avatar.isNullOrEmpty()) {
                            //Set default avatar
                            binding.ivUserAvatar10.setImageResource(R.drawable.empty_avatar)
                        } else {
                            MainScope().launch {
                                val bitmap = viewModel.loadImageFromUrl(ranking[9].avatar!!)
                                if (bitmap != null) {
                                    // Mostrar la imagen en un ImageView
                                    binding.ivUserAvatar10.setImageBitmap(bitmap)
                                } else {
                                    binding.ivUserAvatar10.setImageResource(R.drawable.empty_avatar)
                                }
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