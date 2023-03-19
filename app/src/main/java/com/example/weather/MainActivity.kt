package com.example.weather

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.api.*
import com.example.weather.database.weather_databse
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.days_adapter.DaysAdapter
import com.example.weather.days_adapter.adapter
import com.example.weather.model_classes.days_model
import com.example.weather.model_classes.hours_model
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class MainActivity : AppCompatActivity(), adapter.click, DaysAdapter.click {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var mAdapter: adapter
    private lateinit var mDaysAdapter: DaysAdapter
    private lateinit var mviewmodelclass: viewmodelclass

    @SuppressLint(
        "MissingInflatedId", "CutPasteId", "MissingPermission", "SetTextI18n",
        "NotifyDataSetChanged"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        val recyclerview_hours: RecyclerView = findViewById(R.id.recyclerview_hours)
        val recyclerview_days: RecyclerView = findViewById(R.id.recyclerview_days)
        val apiinterface = apiIntegration.ApiInterface
        val repository = Repository(apiinterface, weather_databse.getinstanse(this))
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        mAdapter = adapter(gethours(), this)
        mDaysAdapter = DaysAdapter(getdays(), this)
        recyclerview_hours.layoutManager = GridLayoutManager(
            this, 1, LinearLayoutManager.HORIZONTAL, false
        )// LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        //recyclerview_hours.layoutManager = GridLayoutManager(this, 1)
        recyclerview_days.layoutManager = GridLayoutManager(
            this,
            1, LinearLayoutManager.HORIZONTAL, false
        )
        recyclerview_hours.adapter = mAdapter
        recyclerview_days.adapter = mDaysAdapter

        mAdapter.notifyDataSetChanged()



        mviewmodelclass =
            ViewModelProvider(this, viewmodelfactory(repository))[viewmodelclass::class.java]

        binding.myLocation.setOnClickListener {
            binding.progressbar.visibility = View.VISIBLE
            if (accessLocation()) {
                if (islocationEnable()) {
                    mFusedLocationClient.lastLocation.addOnSuccessListener {
                        // binding.progressbar.visibility = View.VISIBLE
                        if (it != null) {
                            val geocoder = Geocoder(this, Locale.getDefault())
                            val list: List<Address> =
                                geocoder.getFromLocation(
                                    it.latitude,
                                    it.longitude,
                                    1
                                ) as List<Address>

                            val address = list[0].locality
                            mviewmodelclass.modelCitydata(address)
                        }
                    }

                } else {
                    Toast.makeText(this, "please enable your location", Toast.LENGTH_SHORT).show()
                    val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                }
            } else {
                requestPermission()
            }
        }




        binding.dialogShow.setOnClickListener {
            dialog()
        }
        // mviewmodelclass.modelCitydata(newaddress!!)

        mviewmodelclass.modelgetdata.observe(this@MainActivity) {
            // binding.progressbar.visibility = View.VISIBLE
            val rightnow = Calendar.getInstance()
            val hourofday = rightnow.get(Calendar.HOUR_OF_DAY)
            val minutesofday = rightnow.get(Calendar.MINUTE)
            val secondofday = rightnow.get(Calendar.SECOND)
            val hour = rightnow.get(Calendar.HOUR_OF_DAY) - 1

            if (hour < 12) {
                binding.timeUnit.text = "AM"
            } else {
                binding.timeUnit.text = "PM"
            }
            if (minutesofday < 10) {
                binding.timeinminutes.text = "0$minutesofday"
            } else {
                binding.timeinminutes.text = minutesofday.toString()
            }
            if (secondofday < 10) {
                binding.timeinseconds.text = "0$secondofday"
            } else {
                binding.timeinseconds.text = secondofday.toString()
            }
            binding.apply {


                address.text = it.address
                updatedAt.text = it.days[0].datetime
                temp.text = it.days[0].hours[hour].temp.toString()
                status.text = it.days[0].hours[hour].icon
                tempMin.text = it.days[0].tempmin.toString()
                time.text = hourofday.toString()
                tempMax.text = it.days[0].tempmax.toString()
                sunrise.text = it.days[0].sunrise
                sunset.text = it.days[0].sunset
                pressureValue.text = it.days[0].hours[hour].pressure.toString()
                windSpeed.text = it.days[0].hours[hour].windspeed.toString()
                humidityValue.text = it.days[0].hours[hour].humidity.toString()
                binding.progressbar.visibility = View.GONE
            }

        }
    }


    private fun gethours(): ArrayList<hours_model> {
        val list = ArrayList<hours_model>()

        list.add(hours_model("hour_01"))
        list.add(hours_model("hour_02"))
        list.add(hours_model("hour_03"))
        list.add(hours_model("hour_04"))
        list.add(hours_model("hour_05"))
        list.add(hours_model("hour_06"))
        list.add(hours_model("hour_07"))
        list.add(hours_model("hour_08"))
        list.add(hours_model("hour_09"))
        list.add(hours_model("hour_10"))
        list.add(hours_model("hour_11"))
        list.add(hours_model("hour_12"))
        list.add(hours_model("hour_13"))
        list.add(hours_model("hour_14"))
        list.add(hours_model("hour_15"))
        list.add(hours_model("hour_16"))
        list.add(hours_model("hour_17"))
        list.add(hours_model("hour_18"))
        list.add(hours_model("hour_19"))
        list.add(hours_model("hour_20"))
        list.add(hours_model("hour_21"))
        list.add(hours_model("hour_22"))
        list.add(hours_model("hour_23"))
        list.add(hours_model("hour_24"))

        return list
    }

    private fun getdays(): ArrayList<days_model> {
        val list = ArrayList<days_model>()

        list.add(days_model("days_01"))
        list.add(days_model("days_02"))
        list.add(days_model("days_03"))
        list.add(days_model("days_04"))
        list.add(days_model("days_05"))
        list.add(days_model("days_06"))
        list.add(days_model("days_07"))
        list.add(days_model("days_08"))
        list.add(days_model("days_09"))
        list.add(days_model("days_10"))
        list.add(days_model("days_11"))
        list.add(days_model("days_12"))
        list.add(days_model("days_13"))
        list.add(days_model("days_14"))
        list.add(days_model("days_15"))
        list.add(days_model("days_16"))
        list.add(days_model("days_17"))
        list.add(days_model("days_18"))
        list.add(days_model("days_19"))
        list.add(days_model("days_20"))
        list.add(days_model("days_21"))
        list.add(days_model("days_22"))
        list.add(days_model("days_23"))
        list.add(days_model("days_24"))
        list.add(days_model("days_25"))
        list.add(days_model("days_26"))
        list.add(days_model("days_27"))
        list.add(days_model("days_28"))
        list.add(days_model("days_29"))
        list.add(days_model("days_30"))
        list.add(days_model("days_31"))

        return list
    }



    private fun islocationEnable(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ), 123
        )
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        if (requestCode == 123) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    private fun accessLocation(): Boolean {
        return (ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
                == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)


    }

    @SuppressLint("SetTextI18n")
    override fun onclick(hoursModel: hours_model, position: Int) {
        binding.progressbar.visibility = View.VISIBLE
        mviewmodelclass.modelgetdata.observe(this@MainActivity) {

            val rightnow = Calendar.getInstance()
            val hourofday = rightnow.get(Calendar.HOUR_OF_DAY)
            val minutesofday = rightnow.get(Calendar.MINUTE)
            val secondofday = rightnow.get(Calendar.SECOND)
            val hour = rightnow.get(Calendar.HOUR_OF_DAY) - 1

            if (hour < 12) {
                binding.timeUnit.text = "AM"
            } else {
                binding.timeUnit.text = "PM"
            }
            if (minutesofday < 10) {
                binding.timeinminutes.text = "0$minutesofday"
            } else {
                binding.timeinminutes.text = minutesofday.toString()
            }
            if (secondofday < 10) {
                binding.timeinseconds.text = "0$secondofday"
            } else {
                binding.timeinseconds.text = secondofday.toString()
            }
            binding.apply {
                address.text = it.address
                updatedAt.text = it.days[0].datetime
                temp.text = it.days[0].hours[position + 1].temp.toString()
                status.text = it.days[0].hours[position + 1].icon
                tempMin.text = it.days[0].tempmin.toString()
                time.text = hourofday.toString()
                tempMax.text = it.days[0].tempmax.toString()
                sunrise.text = it.days[0].sunrise
                sunset.text = it.days[0].sunset
                pressureValue.text = it.days[0].hours[position + 1].pressure.toString()
                windSpeed.text = it.days[0].hours[position + 1].windspeed.toString()
                humidityValue.text = it.days[0].hours[position + 1].humidity.toString()
                binding.progressbar.visibility = View.GONE
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onclick(daysModel: days_model, position: Int) {
        mviewmodelclass.modelgetdata.observe(this@MainActivity) {

            val rightnow = Calendar.getInstance()
            val hourofday = rightnow.get(Calendar.HOUR_OF_DAY)
            val minutesofday = rightnow.get(Calendar.MINUTE)
            val secondofday = rightnow.get(Calendar.SECOND)
            val hour = rightnow.get(Calendar.HOUR_OF_DAY) - 1

            if (hour < 12) {
                binding.timeUnit.text = "AM"
            } else {
                binding.timeUnit.text = "PM"
            }
            if (minutesofday < 10) {
                binding.timeinminutes.text = "0$minutesofday"
            } else {
                binding.timeinminutes.text = minutesofday.toString()
            }
            if (secondofday < 10) {
                binding.timeinseconds.text = "0$secondofday"
            } else {
                binding.timeinseconds.text = secondofday.toString()
            }
            binding.apply {
                address.text = it.address
                updatedAt.text = it.days[position + 1].datetime
                temp.text = it.days[position + 1].hours[hour].temp.toString()
                status.text = it.days[position + 1].hours[hour].icon
                tempMin.text = it.days[position + 1].tempmin.toString()
                time.text = hourofday.toString()
                tempMax.text = it.days[position + 1].tempmax.toString()
                sunrise.text = it.days[position + 1].sunrise
                sunset.text = it.days[position + 1].sunset
                pressureValue.text = it.days[position + 1].hours[hour].pressure.toString()
                windSpeed.text = it.days[position + 1].hours[hour].windspeed.toString()
                humidityValue.text = it.days[position + 1].hours[hour].humidity.toString()
                // binding.progressbar.visibility = View.VISIBLE

            }

        }
    }

    private fun dialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_layout)
        val textcity: EditText = dialog.findViewById(R.id.city_Input)

        val cancel: Button = dialog.findViewById(R.id.cancel_Button)
        val enter: Button = dialog.findViewById(R.id.enter_Button)

        cancel.setOnClickListener {
            dialog.dismiss()
        }
        enter.setOnClickListener {
            binding.progressbar.visibility = View.VISIBLE
            val city_text = textcity.text.toString()
            if (city_text.isNotBlank()) {
                mviewmodelclass.modelCitydata(city_text)
                dialog.dismiss()
                binding.mainContainer.visibility = View.VISIBLE

                // binding.outerRL.visibility = View.GONE
            } else {
                Toast.makeText(this, "Please Enter City Name", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
}