package com.cccisi.privacycollector.xsy;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.cccisi.privacycollector.PrivacyApplication;

public class EasyPermissions {
    public static boolean hasPermissions(String... perms) {
        return hasPermissions(PrivacyApplication.getContext(), perms);
    }

    public static boolean hasPermissions(Context context, String... perms) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String perm : perms) {
            boolean hasPerm = (ContextCompat.checkSelfPermission(context, perm) == PackageManager.PERMISSION_GRANTED);
            if (!hasPerm) {
                return false;
            }
        }
        return true;
    }
}
