package com.example.mainactivity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource


class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private var LOCATION_PERMISSION = 1004
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private var boolTester = false
    private val marker = Marker()
    private val marker1 = Marker()
    private val marker2 = Marker()
    private val marker3 = Marker()
    private val marker4 = Marker()
    private val marker5 = Marker()
    private val marker6 = Marker()
    private val marker7 = Marker()

    private var firebaseAuth : FirebaseAuth? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val PERMISSION = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION ,android.Manifest.permission.ACCESS_COARSE_LOCATION )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        firebaseAuth = Firebase.auth

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationSource = FusedLocationSource(this@MapActivity , LOCATION_PERMISSION)
        val fragmentManager: FragmentManager = supportFragmentManager
        var mapFragment: MapFragment? = fragmentManager.findFragmentById(R.id.map) as MapFragment?
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance()
            fragmentManager.beginTransaction().add(R.id.map, mapFragment).commit()
        }
        mapFragment!!.getMapAsync(this)

        val searchPlace = arrayOf("gisuksa","고민사거리","숭실대입구역 3번 출구","숭실대 나무계단","상도역 1번 출구","봉현초 사거리","친절마트")

        val placeAdapter : ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,
            searchPlace
        )
        val searchListView = findViewById<ListView>(R.id.searchListView)
        val searchView = findViewById<SearchView>(R.id.searchView)

        searchListView.adapter = placeAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                //검색어 데이터를 가지고 다음 액티비티로 이동
                if(searchPlace.contains(query)) {
                    var curUser: User = makeUserInfo(firebaseAuth?.currentUser, "")
                    curUser = addLocationInfo(curUser, marker1.tag as String)
                    goNextActivity(curUser)
                }
                searchView.clearFocus()
                if(searchPlace.contains(query)){
                    placeAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                placeAdapter.filter.filter(newText)
                return false
            }

        })

        searchListView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            var curUser: User = makeUserInfo(firebaseAuth?.currentUser, "")
            curUser = addLocationInfo(curUser, searchPlace.get(position)  as String)
            goNextActivity(curUser)
        }



    }

    @UiThread
    override fun onMapReady(map: NaverMap) {

        naverMap = map
//        naverMap.maxZoom =18.0
//        naverMap.minZoom =5.0
        marker1.position = LatLng(37.4950, 126.9605)
        marker1.map = naverMap
        marker1.tag = "gisuksa"

        marker2.position = LatLng(37.4949, 126.9565)
        marker2.map = naverMap
        marker2.tag = "고민사거리"

        marker3.position = LatLng(37.4958, 126.9542)
        marker3.map = naverMap
        marker3.tag = "숭실대입구역 3번 출구"

        marker4.position = LatLng(37.4967, 126.9580)
        marker4.map = naverMap
        marker4.tag = "숭실대 나무계단"

        marker5.position = LatLng(37.5028, 126.9477)
        marker5.map = naverMap
        marker5.tag = "상도역 1번 출구"

        marker6.position = LatLng(37.4910, 126.9568)
        marker6.map = naverMap
        marker6.tag = "봉현초 사거리"

        marker7.position = LatLng(37.4899, 126.9637)
        marker7.map = naverMap
        marker7.tag = "친절마트"

        var btn: Button = findViewById(R.id.button2)
        var backbtn: ImageButton = findViewById(R.id.backbtn)

        val infoWindow = InfoWindow()

        infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(this) {
            override fun getText(infoWindow: InfoWindow): CharSequence {
                //열린 마커의 tag를 텍스트로
                return infoWindow.marker?.tag as CharSequence? ?: ""
            }
        }

        naverMap.setOnMapClickListener{ point, coord ->
            boolTester=false
            infoWindow.close()
            btn.setBackgroundColor(Color.parseColor("#FFA8A8A8"))
        }
        //허공 누르면 현재 떠있는 마커 정보 없애고 버튼 비활성화 시키려고 함

        var curUser : User = makeUserInfo(firebaseAuth?.currentUser, "")

        marker1.setOnClickListener {
            boolTester = true
            btn.setBackgroundColor(Color.parseColor("#fd9f0a"))
            infoWindow.open(marker1)
            curUser = addLocationInfo(curUser, marker1.tag as String)
            true
        }

        marker2.setOnClickListener {
            boolTester = true
            btn.setBackgroundColor(Color.parseColor("#fd9f0a"))
            infoWindow.open(marker2)
            curUser = addLocationInfo(curUser, marker2.tag as String)
            true
        }

        marker3.setOnClickListener {
            boolTester = true
            btn.setBackgroundColor(Color.parseColor("#fd9f0a"))
            infoWindow.open(marker3)
            curUser = addLocationInfo(curUser, marker3.tag as String)
            true
        }

        marker4.setOnClickListener {
            boolTester = true
            btn.setBackgroundColor(Color.parseColor("#fd9f0a"))
            infoWindow.open(marker4)
            curUser = addLocationInfo(curUser, marker4.tag as String)
            true
        }

        marker5.setOnClickListener {
            boolTester = true
            btn.setBackgroundColor(Color.parseColor("#fd9f0a"))
            infoWindow.open(marker5)
            curUser = addLocationInfo(curUser, marker5.tag as String)
            true
        }

        marker6.setOnClickListener {
            boolTester = true
            btn.setBackgroundColor(Color.parseColor("#fd9f0a"))
            infoWindow.open(marker6)
            curUser = addLocationInfo(curUser, marker6.tag as String)
            true
        }

        marker7.setOnClickListener {
            boolTester = true
            btn.setBackgroundColor(Color.parseColor("#fd9f0a"))
            infoWindow.open(marker7)
            curUser = addLocationInfo(curUser, marker7.tag as String)
            true
        }


        btn.setOnClickListener {
            if(boolTester){
                goNextActivity(curUser)
            }
        }

        backbtn.setOnClickListener {
            finish()
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


        naverMap.locationSource = locationSource
        ActivityCompat.requestPermissions(this, PERMISSION, LOCATION_PERMISSION)
    }

    private fun makeUserInfo(user: FirebaseUser?, location: String): User {
        val initId: String = user?.email.toString().split('@')[0]
        val initLocation: String = location
        val tempMenu: String = ""
        //Toast.makeText(this, "현재 사용자 = $initId\n현재 사용자 스팟 : $initLocation",Toast.LENGTH_SHORT).show()
        return User(
            initId,
            initLocation,
            tempMenu
        )
    }
    private fun addLocationInfo(curUser : User, location: String): User{
        return User(
            curUser.Id,
            location,
            ""
        )
    }

    private fun goNextActivity(curUser : User){
        val intent : Intent = Intent(this,MenuActivity::class.java)
        intent.putExtra("curUser",curUser)
        startActivity(intent)
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
