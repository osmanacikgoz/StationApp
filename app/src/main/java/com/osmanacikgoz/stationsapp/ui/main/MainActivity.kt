package com.osmanacikgoz.stationsapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.osmanacikgoz.stationsapp.R
import com.osmanacikgoz.stationsapp.base.readJson
import com.osmanacikgoz.stationsapp.databinding.ActivityMainBinding
import com.osmanacikgoz.stationsapp.model.Satellite
import com.osmanacikgoz.stationsapp.model.SatelliteDetailResponse
import com.osmanacikgoz.stationsapp.ui.detail.StationDetailActivity
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val stationListModel: ArrayList<Satellite> = ArrayList()

    private var stationAdapter: StationAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        getLoadJsonData()
        searchStation()
        setAdapter()

    }

    private fun setAdapter() {
        stationAdapter = StationAdapter { satellite, _ ->
            val stationDetailData = getDetailById(satellite.id)
            stationDetailData?.apply {
                stationName = satellite.name
            }

            val intent = Intent(this@MainActivity, StationDetailActivity::class.java)
            intent.putExtra("satelliteDetail", stationDetailData)
            startActivity(intent)
        }

        stationAdapter?.setData(stationListModel)

        binding.rvStationList.adapter = stationAdapter
    }

    private fun loadFromSatelliteDetailAsset(): String {
        return assets.readJson("satellite-detail.json")
    }

    private fun loadFromAsset(): String {
        return assets.readJson("satellite-list.json")
    }

    private fun getDetailById(id: Int): SatelliteDetailResponse.SatelliteDetail? {
        val data = loadFromSatelliteDetailAsset()
        val response = Gson().fromJson(data, SatelliteDetailResponse::class.java)
        return response.find { satelliteDetail -> satelliteDetail.id == id }
    }

    private fun searchStation() {
        binding.etStationSearch.addTextChangedListener {
            it?.let {
                setupSearch(it.toString())
            }
        }
    }

    private fun setupSearch(search: String) {
        val filteredStations = stationListModel.filter { it.name.contains(search) }
        stationAdapter?.setData(filteredStations)
    }

    private fun getLoadJsonData() {
        val obj = JSONArray(loadFromAsset())
        for (i in 0 until obj.length()) {
            val stations = obj.getJSONObject(i)
            val stationName = stations.getString("name")
            val active = stations.getBoolean("active")
            val id = stations.getInt("id")
            val stationsList = Satellite(stationName, active, id)
            stationListModel.add(stationsList)
        }

    }


}