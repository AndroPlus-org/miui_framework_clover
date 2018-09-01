// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import libcore.net.NetworkSecurityPolicy;

public class FrameworkNetworkSecurityPolicy extends NetworkSecurityPolicy
{

    public FrameworkNetworkSecurityPolicy(boolean flag)
    {
        mCleartextTrafficPermitted = flag;
    }

    public boolean isCertificateTransparencyVerificationRequired(String s)
    {
        return false;
    }

    public boolean isCleartextTrafficPermitted()
    {
        return mCleartextTrafficPermitted;
    }

    public boolean isCleartextTrafficPermitted(String s)
    {
        return isCleartextTrafficPermitted();
    }

    private final boolean mCleartextTrafficPermitted;
}
