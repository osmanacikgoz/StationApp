package com.osmanacikgoz.stationsapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.osmanacikgoz.stationsapp.R
import com.osmanacikgoz.stationsapp.databinding.ActivityStationDetailBinding
import com.osmanacikgoz.stationsapp.model.PositionList
import com.osmanacikgoz.stationsapp.model.Positions
import com.osmanacikgoz.stationsapp.model.SatelliteDetail
import com.osmanacikgoz.stationsapp.model.SatellitePositionList
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

class StationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStationDetailBinding

    private val stationDetailModel: ArrayList<SatelliteDetail> = ArrayList()
    private val satellitePositionList: ArrayList<SatellitePositionList> = ArrayList()
    private val positionsModel: ArrayList<Positions> = ArrayList()
    private val positionList: ArrayList<PositionList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_station_detail)

        initUI()
    }

    private fun initUI() {
        showStationDetail()
        //showSatellitePosition()
        setDetailView()
    }

    private fun showStationDetail() {
        val satelliteId = intent.getIntExtra("id", 1)
        val stationDetailObject = JSONArray(loadFromSatelliteDetailAsset())
        val stationDetail = stationDetailObject.getJSONObject(satelliteId)
        val costPerLaunch = stationDetail.getInt("cost_per_launch")
        val firstFlight = stationDetail.getString("first_flight")
        val height = stationDetail.getInt("height")
        val mass = stationDetail.getInt("mass")
        val stationDetailList =
            SatelliteDetail(satelliteId, costPerLaunch, firstFlight, height, mass)
        stationDetailModel.add(stationDetailList)

    }

    private fun showSatellitePosition() {
        val positionXY = JSONObject(loadFromSatellitePosition())
        val positionX = positionXY.getDouble("posX")
        val positionY = positionXY.getDouble("posY")
        val positionsArray = Positions(positionX, positionY)
        positionsModel.add(positionsArray)
        val satelliteId = intent.getIntExtra("id", 1)
        positionXY.getJSONArray("positions")
        val positionIdList = SatellitePositionList(satelliteId.toString(), positionsModel)
        satellitePositionList.add(positionIdList)
        positionXY.getJSONArray("list")
        val positionsList = PositionList(satellitePositionList)
        positionList.add(positionsList)
    }

    private fun loadFromSatellitePosition(): String {
        val json: String?
        val charset: Charset = Charsets.UTF_8
        val positionAsset = assets.open("positions.json")
        val size = positionAsset.available()
        val buffer = ByteArray(size)
        positionAsset.read(buffer)
        positionAsset.close()
        json = String(buffer, charset)

        return json
    }

    private fun loadFromSatelliteDetailAsset(): String {
        val json: String?
        val charset: Charset = Charsets.UTF_8
        val openDetailAsset = assets.open("satellite-detail.json")
        val size = openDetailAsset.available()
        val buffer = ByteArray(size)
        openDetailAsset.read(buffer)
        openDetailAsset.close()
        json = String(buffer, charset)

        return json
    }

    @SuppressLint("SetTextI18n")
    private fun setDetailView() {
        stationDetailModel[0].let {
            with(binding) {
                val intentStationName = intent.getStringExtra("name")
                this.stationName.text = intentStationName
                this.tvDate.text = it.firstFlight
                this.tvHeightMassValue.text = it.height.toString() + "/" + it.mass
                this.costValue.text = it.costPerLaunch.toString()

            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setPosition(position:Int) {
        positionList[position].list[position].positions[position].let {
            with(binding) {
                this.positionValue.text = it.posX.toString() + "," + it.posY
            }
        }
    }
}