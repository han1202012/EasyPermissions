package kim.hsl.easypermissions

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pub.devrel.easypermissions.EasyPermissions

class MainActivitySimple : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCLick(view : View){
        EasyPermissions.requestPermissions(
            this,
            "权限申请原理对话框 : 描述申请权限的原理",
            100,

            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

}