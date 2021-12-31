package com.osmanacikgoz.stationsapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.osmanacikgoz.stationsapp.R
import com.osmanacikgoz.stationsapp.base.dateConverter
import com.osmanacikgoz.stationsapp.base.readJson
import com.osmanacikgoz.stationsapp.databinding.ActivityStationDetailBinding
import com.osmanacikgoz.stationsapp.model.SatelliteDetailResponse
import com.osmanacikgoz.stationsapp.model.SatellitePositionResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStationDetailBinding

    private var detailData: SatelliteDetailResponse.SatelliteDetail? = null

    private val viewModel: StationDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_station_detail)

        getIntentData()

        initUI()

        detailData?.id?.let { mId ->
            val satellitePosition = getPositionById(mId.toString())

            satellitePosition?.positions?.let {
                lifecycleScope.launch {
                    viewModel.getPositionFlow(it).collect { position ->
                        val positionsSatellite = position.posX.toString()+","+position.posY
                        binding.positionValue.text = positionsSatellite
                        binding.progress.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun loadFromPositionAsset(): String {
        return assets.readJson("positions.json")
    }

    private fun getPositionById(id: String): SatellitePositionResponse.SatellitePosition? {
        val data = loadFromPositionAsset()
        val response = Gson().fromJson(data, SatellitePositionResponse::class.java)
        return response.list.find { it.id == id }
    }

    private fun getIntentData() {
        detailData = intent.getParcelableExtra("satelliteDetail")
    }

    private fun initUI() {
        setView()
    }


    private fun setView() {
        with(binding) {
            stationName.text = detailData?.stationName

            val heightMass = detailData?.height.toString() + "/" + detailData?.mass
            tvHeightMassValue.text = heightMass

            tvCostValue.text = detailData?.costPerLaunch?.toString()

            tvDate.text = detailData?.firstFlight?.dateConverter()
        }
    }
}