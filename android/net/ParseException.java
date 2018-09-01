// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;


public class ParseException extends RuntimeException
{

    ParseException(String s)
    {
        response = s;
    }

    public String response;
}
