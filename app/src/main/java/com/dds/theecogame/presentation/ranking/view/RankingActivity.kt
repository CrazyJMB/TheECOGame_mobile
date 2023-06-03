package com.dds.theecogame.presentation.ranking.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
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

        // Obtener la información del top 10
        lifecycleScope.launch {
            rankingRepository.getRanking().collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val ranking = it.data!!

                        for (i in 0 until minOf(ranking.size, 10)) {
                            val user = ranking[i]
                            val userNameField =
                                binding.javaClass.getDeclaredField("tvUserUsername${i + 1}")
                            val userPointsField =
                                binding.javaClass.getDeclaredField("tvUserPoints${i + 1}")
                            val userAvatarField =
                                binding.javaClass.getDeclaredField("ivUserAvatar${i + 1}")

                            val userNameView = userNameField.get(binding) as TextView
                            val userPointsView = userPointsField.get(binding) as TextView
                            val userAvatarView = userAvatarField.get(binding) as ImageView

                            if (user.avatar.isNullOrEmpty()) {
                                // Set default avatar
                                userAvatarView.setImageResource(R.drawable.empty_avatar)
                            } else {
                                MainScope().launch {
                                    val bitmap = viewModel.loadImageFromUrl(user.avatar!!)
                                    if (bitmap != null) {
                                        // Mostrar la imagen en un ImageView
                                        userAvatarView.setImageBitmap(bitmap)
                                    } else {
                                        userAvatarView.setImageResource(R.drawable.empty_avatar)
                                    }
                                }
                            }

                            userNameView.text = user.username
                            userPointsView.text = user.score.toString()
                        }
                    }

                    is Resource.Error -> {}
                }
            }
        }
    }
}