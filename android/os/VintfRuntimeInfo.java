// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


public class VintfRuntimeInfo
{

    private VintfRuntimeInfo()
    {
    }

    public static native String getBootAvbVersion();

    public static native String getBootVbmetaAvbVersion();

    public static native String getCpuInfo();

    public static native String getHardwareId();

    public static native long getKernelSepolicyVersion();

    public static native String getKernelVersion();

    public static native String getNodeName();

    public static native String getOsName();

    public static native String getOsRelease();

    public static native String getOsVersion();
}
