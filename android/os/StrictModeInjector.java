// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.*;

public class StrictModeInjector
{

    public StrictModeInjector()
    {
    }

    public static boolean isSkipStrictModeCheck(Throwable throwable)
    {
        throwable = parseStack(throwable);
        for(Iterator iterator = mWhiteList.iterator(); iterator.hasNext();)
            if(throwable.contains((String)iterator.next()))
                return true;

        return false;
    }

    private static List parseStack(Throwable throwable)
    {
        ArrayList arraylist = new ArrayList();
        throwable = throwable.getStackTrace();
        for(int i = 0; i < throwable.length; i++)
            arraylist.add(throwable[i].getClassName());

        return arraylist;
    }

    private static ArrayList mWhiteList;

    static 
    {
        mWhiteList = new ArrayList();
        mWhiteList.add("org.apache.http.impl.client.AbstractHttpClient");
        mWhiteList.add("miui.content.res.ThemeZipFile");
        mWhiteList.add("miui.text.ChinesePinyinConverter");
        mWhiteList.add("miui.telephony.phonenumber.ChineseTelocationConverter.java");
        mWhiteList.add("com.android.okhttp.internal.http.HttpURLConnectionImpl");
    }
}
