package kim.hsl.easypermissions

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
import pub.devrel.easypermissions.EasyPermissions.RationaleCallbacks

class MainActivity_2020_09_18 : AppCompatActivity(), PermissionCallbacks, RationaleCallbacks{

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

        Log.i(TAG, "onRequestPermissionsResult")

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
        Log.i(TAG, "onPermissionsDenied 用户拒绝权限申请 , 请求码 $requestCode , 拒绝的权限 : $perms")


        // 如果申请的权限中有任何一个权限存在 永久拒绝 的情况
        /*if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            // 设置 引导用户前往设置界面 自行设置权限的引导对话框
            AppSettingsDialog.Builder(this)
                .setTitle("需要手动设置权限")
                .setRationale("存在永久拒绝的权限 , 需要手动前往设置界面为应用进行授权")
                .setPositiveButton("前往设置界面")
                .setNegativeButton("不使用该功能")
                .build().show()
        }*/
    }

    /**
     * EasyPermissions.PermissionCallbacks 接口中实现的方法
     * 调用 EasyPermissions.requestPermissions() 方法申请权限 , 用户点击同意授权后会回调该方法
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.i(TAG, "onPermissionsGranted 用户同意权限申请 , 请求码 $requestCode , 拒绝的权限 : $perms")
    }




    /*
        三 、 实现 EasyPermissions.RationaleCallbacks 接口中的方法
     */

    /**
     * EasyPermissions.RationaleCallbacks 接口中的方法
     *
     */
    override fun onRationaleDenied(requestCode: Int) {
        Log.i(TAG, "权限申请原理对话框中选择 取消 , 请求码 $requestCode")
    }

    /**
     * EasyPermissions.RationaleCallbacks 接口中的方法
     *
     */
    override fun onRationaleAccepted(requestCode: Int) {
        Log.i(TAG, "权限申请原理对话框中选择 确定 , 请求码 $requestCode")
    }




    /*
        四 、 用户点击按钮开始申请权限
     */

    fun onCLick(view : View){
        // 申请权限, 并在权限申请通过后 , 在执行一次该方法
        //doSomethingWithPermissions();

        EasyPermissions.requestPermissions(
            this,
            "申请五种权限",
            PERMISSION_REQUEST_CODE,

            // 数组前加上 * 符号 , 可以将数组展开 , 转为可变数组
            *PERMMISSIONS
        )

    }

    /**
     * AfterPermissionGranted 注解的作用是 , 当 请求吗 666 对应的权限申请全部通过后
     * 再次回调一次该方法 . ( 相当于调用了两次该方法 )
     */
    @AfterPermissionGranted( 100 )
    fun doSomethingWithPermissions(){

        Log.i(TAG, "doSomethingWithPermissions")

        // 数组前加上 * 符号 , 可以将数组展开 , 转为可变数组
        // 调用 EasyPermissions.hasPermissions 方法判定是否已经申请该权限
        if(EasyPermissions.hasPermissions(this,
                *PERMMISSIONS)){
            // 如果有上述权限, 执行该操作
            Toast.makeText(this, "权限申请通过", Toast.LENGTH_LONG).show()
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


    /**
     * 从 AppSettingsDialog 界面中返回, 回调该方法
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE){
            // 判断五种权限是否申请成功
            var hasPermissions =
                EasyPermissions.hasPermissions(this, *PERMMISSIONS)
            // 界面中显示权限申请结果
            Toast.makeText(this, "设置界面用户手动申请权限结果 $hasPermissions",
                Toast.LENGTH_LONG).show()
        }
    }

}
