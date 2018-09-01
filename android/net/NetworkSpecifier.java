// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;


public abstract class NetworkSpecifier
{

    public NetworkSpecifier()
    {
    }

    public void assertValidFromUid(int i)
    {
    }

    public abstract boolean satisfiedBy(NetworkSpecifier networkspecifier);
}
