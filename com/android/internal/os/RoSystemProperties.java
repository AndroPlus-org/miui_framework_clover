// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.SystemProperties;

public class RoSystemProperties
{

    public RoSystemProperties()
    {
    }

    public static final boolean CONFIG_LOW_RAM = SystemProperties.getBoolean("ro.config.low_ram", false);
    public static final boolean CONFIG_SMALL_BATTERY = SystemProperties.getBoolean("ro.config.small_battery", false);
    public static final String CONTROL_PRIVAPP_PERMISSIONS = SystemProperties.get("ro.control_privapp_permissions");
    public static final boolean CONTROL_PRIVAPP_PERMISSIONS_DISABLE;
    public static final boolean CONTROL_PRIVAPP_PERMISSIONS_ENFORCE;
    public static final boolean CONTROL_PRIVAPP_PERMISSIONS_LOG;
    public static final boolean CRYPTO_BLOCK_ENCRYPTED = "block".equalsIgnoreCase(CRYPTO_TYPE);
    public static final boolean CRYPTO_ENCRYPTABLE;
    public static final boolean CRYPTO_ENCRYPTED = "encrypted".equalsIgnoreCase(CRYPTO_STATE);
    public static final boolean CRYPTO_FILE_ENCRYPTED = "file".equalsIgnoreCase(CRYPTO_TYPE);
    public static final String CRYPTO_STATE = SystemProperties.get("ro.crypto.state");
    public static final String CRYPTO_TYPE = SystemProperties.get("ro.crypto.type");
    public static final boolean DEBUGGABLE;
    public static final int FACTORYTEST = SystemProperties.getInt("ro.factorytest", 0);
    public static final boolean FW_SYSTEM_USER_SPLIT = SystemProperties.getBoolean("ro.fw.system_user_split", false);

    static 
    {
        boolean flag = true;
        boolean flag1 = false;
        if(SystemProperties.getInt("ro.debuggable", 0) != 1)
            flag = false;
        DEBUGGABLE = flag;
        if(!CRYPTO_STATE.isEmpty())
            flag = "unsupported".equals(CRYPTO_STATE) ^ true;
        else
            flag = false;
        CRYPTO_ENCRYPTABLE = flag;
        CONTROL_PRIVAPP_PERMISSIONS_LOG = "log".equalsIgnoreCase(CONTROL_PRIVAPP_PERMISSIONS);
        CONTROL_PRIVAPP_PERMISSIONS_ENFORCE = "enforce".equalsIgnoreCase(CONTROL_PRIVAPP_PERMISSIONS);
        flag = flag1;
        if(!CONTROL_PRIVAPP_PERMISSIONS_LOG)
            flag = CONTROL_PRIVAPP_PERMISSIONS_ENFORCE ^ true;
        CONTROL_PRIVAPP_PERMISSIONS_DISABLE = flag;
    }
}
