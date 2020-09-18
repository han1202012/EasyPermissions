package kim.hsl.easypermissions

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
import pub.devrel.easypermissions.EasyPermissions.RationaleCallbacks

class MainActivity : AppCompatActivity(), PermissionCallbacks, RationaleCallbacks{

    val TAG = "MainActivity"

    /**
     * 权限申请码, 作为权限申请的标识
     */
    val PERMISSION_REQUEST_CODE : Int = 100;
    var PERMMISSIONS: Array<String> = arrayOf(Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.READ_SMS,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    /**
     * 一 、 重写 Activity 的 onRequestPermissionsResult 方法
     *    主要是在该方法中使用 EasyPermissions 进一步处理权限申请后续结果
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // 进一步使用 EasyPermissions 处理后续结果
        EasyPermissions.onRequestPermissionsResult(
            requestCode, permissions, grantResults, this);
    }




    /*
        二 、 实现 EasyPermissions.PermissionCallbacks 接口中的方法
     */

    /**
     * EasyPermissions.PermissionCallbacks 接口中实现的方法
     * 调用 EasyPermissions.requestPermissions() 方法申请权限 , 用户点击拒绝授权后会回调该方法
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.i(TAG, "用户拒绝权限申请 , 请求码 $requestCode , 拒绝的权限 : $perms")
    }

    /**
     * EasyPermissions.PermissionCallbacks 接口中实现的方法
     * 调用 EasyPermissions.requestPermissions() 方法申请权限 , 用户点击同意授权后会回调该方法
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.i(TAG, "用户同意权限申请 , 请求码 $requestCode , 拒绝的权限 : $perms")
    }





    /*
        三 、 实现 EasyPermissions.RationaleCallbacks 接口中的方法
     */

    /**
     * EasyPermissions.RationaleCallbacks 接口中的方法
     *
     */
    override fun onRationaleDenied(requestCode: Int) {

    }

    /**
     * EasyPermissions.RationaleCallbacks 接口中的方法
     *
     */
    override fun onRationaleAccepted(requestCode: Int) {

    }




    /*
        四 、 用户点击按钮开始申请权限
     */

    fun onCLick(view : View){
        // 申请权限, 并在权限申请通过后 , 在执行一次该方法
        doSomethingWithPermissions();
    }

    /**
     * AfterPermissionGranted 注解的作用是 , 当 请求吗 666 对应的权限申请全部通过后
     * 再次回调一次该方法 . ( 相当于调用了两次该方法 )
     */
    @AfterPermissionGranted( 100 )
    fun doSomethingWithPermissions(){

        // 数组前加上 * 符号 , 可以将数组展开 , 转为可变数组
        if(EasyPermissions.hasPermissions(this, *PERMMISSIONS)){
            // 如果有上述权限, 执行该操作
        }else{
            // 如果没有上述权限 , 那么申请权限
            EasyPermissions.requestPermissions(
                this,
                "申请五种权限",
                PERMISSION_REQUEST_CODE,

                // 数组前加上 * 符号 , 可以将数组展开 , 转为可变数组
                *PERMMISSIONS
            )
        }
    }

}
