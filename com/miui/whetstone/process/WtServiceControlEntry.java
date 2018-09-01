// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.process;

import android.util.Slog;
import com.miui.whetstone.client.WhetstoneClientManager;
import com.miui.whetstone.strategy.WhetstoneSystemSetting;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;

public class WtServiceControlEntry
    implements Observer
{

    private WtServiceControlEntry()
    {
        mServiceControlWhitelist.add("com.android.cts");
    }

    public static void addAppToServiceControlWhitelist(String s)
    {
        mServiceControlWhitelist.add(s);
    }

    public static void addAppToServiceControlWhitelist(List list)
    {
        if(list != null)
            mServiceControlWhitelist.addAll(list);
    }

    public static WtServiceControlEntry getInstance()
    {
        if(mSCE == null)
            mSCE = new WtServiceControlEntry();
        return mSCE;
    }

    public static boolean isAppInServiceControlWhitelist(String s)
    {
        return mServiceControlWhitelist.contains(s);
    }

    public static boolean isServiceControlEnabled()
    {
        WhetstoneSystemSetting whetstonesystemsetting = WhetstoneClientManager.getWhetstoneSystemSetting();
        if(whetstonesystemsetting != null)
            return whetstonesystemsetting.getCommonConfigInBoolean("servicecontrol_enabled", false);
        else
            return false;
    }

    public static void removeAppFromServiceControlWhitelist(String s)
    {
        if(!mServiceControlWhitelist.contains(s))
        {
            Slog.d("ServiceControlEntry", "could not remove an nonexist package from whitelist");
            return;
        } else
        {
            mServiceControlWhitelist.remove(s);
            return;
        }
    }

    private void setServiceControlWhitelist()
    {
        Object obj;
        mServiceControlWhitelist.clear();
        mServiceControlWhitelist.add("com.android.cts");
        obj = WhetstoneClientManager.getWhetstoneSystemSetting();
        if(obj == null) goto _L2; else goto _L1
_L1:
        obj = ((WhetstoneSystemSetting) (obj)).getCommonConfigInJSONArray("servicecontrol_whitelist");
        if(obj == null) goto _L2; else goto _L3
_L3:
        int i = 0;
_L4:
        if(i >= ((JSONArray) (obj)).length())
            break; /* Loop/switch isn't completed */
        String s = ((JSONArray) (obj)).getString(i);
        mServiceControlWhitelist.add(s);
        i++;
        if(true) goto _L4; else goto _L2
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
_L2:
    }

    public void update(Observable observable, Object obj)
    {
        setServiceControlWhitelist();
    }

    private static WtServiceControlEntry mSCE = null;
    private static Set mServiceControlWhitelist = null;

    static 
    {
        mServiceControlWhitelist = Collections.newSetFromMap(new ConcurrentHashMap());
    }
}
