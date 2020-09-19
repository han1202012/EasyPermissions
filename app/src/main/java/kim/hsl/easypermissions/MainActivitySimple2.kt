package kim.hsl.easypermissions

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class MainActivitySimple2 : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCLick(view : View){
        doSomethingWithPermissions()
    }

    @AfterPermissionGranted( 100 )
    fun doSomethingWithPermissions(){
        if(EasyPermissions.hasPermissions(this,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_SMS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)){

            // 如果有上述权限, 执行该操作
            Toast.makeText(this, "权限申请通过", Toast.LENGTH_LONG).show()
        }else{
            // 如果没有上述权限 , 那么申请权限
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
}