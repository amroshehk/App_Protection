package com.applefish.protectingapps;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Tamperprotection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamperprotection);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>---------------Tamper protection by detecting the installer,    emulator, and debug flag
    // >>>>>>>>>>>>>>>>>>>>>>>---------------Tamper protection by detecting the installer,    emulator, and debug flag
    public static boolean isDebuggable(Context context){
        return (context.getApplicationInfo().flags & ApplicationInfo.
                FLAG_DEBUGGABLE) != 0;
    }
    public static boolean checkGooglePlayStore(Context context) {
        String installerPackageName = context.getPackageManager()
                .getInstallerPackageName(context.getPackageName());
        return installerPackageName != null
                && installerPackageName.startsWith("com.google.android");
    }
    public static boolean isEmulator() {
        try {
            Class systemPropertyClazz = Class
                    .forName("android.os.SystemProperties");
            boolean kernelQemu = getProperty(systemPropertyClazz,
                    "ro.kernel.qemu").length() > 0;
            boolean hardwareGoldfish = getProperty(systemPropertyClazz,
                    "ro.hardware").equals("goldfish");
            boolean modelSdk = getProperty(systemPropertyClazz,
                    "ro.product.model").equals("sdk");
            if (kernelQemu || hardwareGoldfish || modelSdk) {
                return true;
            }
        } catch (Exception e) {
// error assumes emulator
        }
        return false;
    }
    private static String getProperty(Class clazz, String
            propertyName)
            throws Exception {
        return (String) clazz.getMethod("get", new Class[] { String.
                class })
                .invoke(clazz, new Object[] { propertyName });
    }

    public  void checkInstaller(View view)
    {
        if(checkGooglePlayStore(getBaseContext()))
        {
            Toast.makeText(this,
                    "Installer Name Is Google Play Store",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,
                    "Installer Name does not  Google Play Store",
                    Toast.LENGTH_SHORT).show();

        }

    }
    public  void checkemulator(View view)
    {
        if(!isEmulator())
        {
            Toast.makeText(this,
                    "App runs on an emulator",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,
                    "App does not runs on an emulator",
                    Toast.LENGTH_SHORT).show();

        }

    }
    public  void checkDebugable(View view)
    {
        if(isDebuggable(getBaseContext()))
        {
            Toast.makeText(this,
                    "App has the debuggable flag Enabled",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,
                    "App has the debuggable flag Disable",
                    Toast.LENGTH_SHORT).show();

        }

    }
}
