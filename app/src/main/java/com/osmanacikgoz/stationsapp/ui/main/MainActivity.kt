package com.osmanacikgoz.stationsapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.osmanacikgoz.stationsapp.R
import com.osmanacikgoz.stationsapp.databinding.ActivityMainBinding
import com.osmanacikgoz.stationsapp.model.Satellite
import com.osmanacikgoz.stationsapp.ui.detail.StationDetailActivity
import org.json.JSONArray
import java.nio.charset.Charset
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val stationListModel: ArrayList<Satellite> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        getLoadJsonData()
        setAdapter()

    }

    private fun setAdapter() {
        val stationAdapter = StationAdapter(stationListModel)
        binding.rvStationList.adapter = stationAdapter

        stationAdapter.setOnClickListener(object : StationAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, StationDetailActivity::class.java)
                intent.putExtra("id", stationListModel[position].id)
                intent.putExtra("name",stationListModel[position].name)
                startActivity(intent)
            }

        })

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

    private fun loadFromAsset(): String {
        val json: String?
        val charSet: Charset = Charsets.UTF_8

        val openAsset = assets.open("satellite-list.json")
        val size = openAsset.available()
        val buffer = ByteArray(size)
        openAsset.read(buffer)
        openAsset.close()

        json = String(buffer, charSet)
        return json
    }

}