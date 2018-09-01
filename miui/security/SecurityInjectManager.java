// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.security;


public class SecurityInjectManager
{

    private SecurityInjectManager()
    {
    }

    public static native void blockSelfNetwork(boolean flag);

    public static native boolean hookFunctions(long l, int i);

    public static native boolean isNetworkBlocked();
}
