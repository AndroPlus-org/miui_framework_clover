// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.Map;

public class VintfObject
{

    public VintfObject()
    {
    }

    public static native String[] getHalNamesAndVersions();

    public static native String getSepolicyVersion();

    public static native Map getVndkSnapshots();

    public static native String[] report();

    public static native int verify(String as[]);
}
