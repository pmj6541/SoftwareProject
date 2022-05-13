package com.example.mainactivity

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource


class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private var LOCATION_PERMISSION = 1004
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private val marker = Marker()
    private val marker1 = Marker()
    private val marker2 = Marker()
    private val marker3 = Marker()
    private val marker4 = Marker()
    //private var mBinding: ActivityMapBinding? = null
    //private val binding get() = mBinding!!


    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val PERMISSION = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION ,android.Manifest.permission.ACCESS_COARSE_LOCATION )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        //mBinding = ActivityMapBinding.inflate(layoutInflater)

       /* supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()*/


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationSource = FusedLocationSource(this@MapActivity , LOCATION_PERMISSION)
        val fragmentManager: FragmentManager = supportFragmentManager
        var mapFragment: MapFragment? = fragmentManager.findFragmentById(R.id.map) as MapFragment?
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance()
            fragmentManager.beginTransaction().add(R.id.map, mapFragment).commit()
        }

        mapFragment!!.getMapAsync(this)

    }

    @UiThread
    override fun onMapReady(map: NaverMap) {

        naverMap = map
//        naverMap.maxZoom =18.0
//        naverMap.minZoom =5.0
        marker1.position = LatLng(37.4950, 126.9605)
        marker1.map = naverMap
        marker1.captionText = "기숙사 앞"
        marker2.position = LatLng(37.4949, 126.9565)
        marker2.map = naverMap
        marker2.captionText = "고민사거리"
        marker3.position = LatLng(37.4958, 126.9542)
        marker3.map = naverMap
        marker3.captionText = "숭실대입구역 3번 출구"
        marker4.position = LatLng(37.4967, 126.9580)
        marker4.map = naverMap
        marker4.captionText = "숭실대 나무계단"

        marker1.setOnClickListener {
            Toast.makeText(this@MapActivity, "기숙사 앞", Toast.LENGTH_SHORT).show()
            // 이벤트 전파
            false
        }

        marker2.setOnClickListener {
            Toast.makeText(this@MapActivity, "고민사거리", Toast.LENGTH_SHORT).show()
            // 이벤트 소비
            true
        }

        marker3.setOnClickListener {
            Toast.makeText(this@MapActivity, "숭실대입구역 3번 출구", Toast.LENGTH_SHORT).show()
            // 이벤트 소비
            true
        }

        marker4.setOnClickListener {
            Toast.makeText(this@MapActivity, "숭실대 나무계단", Toast.LENGTH_SHORT).show()
            // 이벤트 소비
            true
        }


//        카메라 설정
        val cameraPosition = CameraPosition(
            LatLng(37.4963, 126.9783881), // 대상 지점
            16.0, // 줌 레벨
            20.0, // 기울임 각도
            0.0 // 베어링 각도
        )
        naverMap.cameraPosition = cameraPosition

        naverMap.addOnCameraChangeListener { reason, animated ->
            // 마커 포지션
            marker.position = LatLng(naverMap.cameraPosition.target.latitude, naverMap.cameraPosition.target.longitude) }

        /*naverMap.addOnCameraIdleListener {
            // 현재 보이는 네이버맵의 정중앙 가운데로 마커
            marker.map = naverMap
            marker.icon = MarkerIcons.BLACK
            marker.iconTintColor = Color.BLUE

        }*/

        naverMap.locationSource = locationSource
        ActivityCompat.requestPermissions(this, PERMISSION, LOCATION_PERMISSION)
    }



    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when {
            requestCode != LOCATION_PERMISSION -> {
                return
            }
            else -> {
                when {
                    locationSource.onRequestPermissionsResult(requestCode,permissions,grantResults) -> {
                        if (!locationSource.isActivated){
                            naverMap.locationTrackingMode = LocationTrackingMode.None
                        }else{
                            naverMap.locationTrackingMode = LocationTrackingMode.Follow
                        }
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



}
