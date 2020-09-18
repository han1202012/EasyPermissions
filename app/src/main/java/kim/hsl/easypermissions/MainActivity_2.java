package kim.hsl.easypermissions;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity_2 extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    /**
     * 权限申请码, 作为权限申请的标识
     */
    public static final int PERMISSION_REQUEST_CODE = 100;

    /**
     * 要申请的权限
     */
    private String[] PERMMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCLick(View view){
        EasyPermissions.requestPermissions(
                this,
                "申请五种权限",
                PERMISSION_REQUEST_CODE,
                PERMMISSIONS);
    }


}
