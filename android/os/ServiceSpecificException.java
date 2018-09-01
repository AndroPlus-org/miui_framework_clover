// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


public class ServiceSpecificException extends RuntimeException
{

    public ServiceSpecificException(int i)
    {
        errorCode = i;
    }

    public ServiceSpecificException(int i, String s)
    {
        super(s);
        errorCode = i;
    }

    public String toString()
    {
        return (new StringBuilder()).append(super.toString()).append(" (code ").append(errorCode).append(")").toString();
    }

    public final int errorCode;
}
