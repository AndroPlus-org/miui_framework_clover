// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.strategy;

import android.content.*;
import android.os.Process;
import android.util.Log;
import com.miui.whetstone.server.WhetstoneActivityManagerService;
import java.util.Iterator;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;
import org.json.*;

public class WhetstoneSystemSetting extends Observable
{

    public WhetstoneSystemSetting(Context context, WhetstoneActivityManagerService whetstoneactivitymanagerservice)
    {
        mConfigMap = new ConcurrentHashMap();
        mContext = context;
        mWhetstoneActivityManagerService = whetstoneactivitymanagerservice;
    }

    private boolean finishCommonAllowed(int i, ComponentName componentname, Boolean boolean1)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(true)
        {
            flag1 = flag;
            if(componentname.getPackageName() != null)
            {
                boolean1 = mWhetstoneActivityManagerService.getPackageNamebyPid(i);
                flag1 = flag;
                if(boolean1 != null)
                {
                    flag1 = flag;
                    if(boolean1.equals(componentname.getPackageName()))
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public boolean finishIsStartPorcessAllowedByBroadcast(int i, Intent intent, ComponentName componentname, Boolean boolean1)
    {
        if("android.intent.action.BOOT_COMPLETED" == intent.getAction())
            return true;
        else
            return finishCommonAllowed(i, componentname, boolean1);
    }

    public boolean finishstartServiceAllowed(int i, ComponentName componentname, int j, boolean flag, boolean flag1)
    {
        flag = false;
        if(i == Process.myPid())
            return true;
        if(componentname != null)
            flag = finishCommonAllowed(i, componentname, Boolean.valueOf(flag1));
        return flag;
    }

    public boolean finishstartServiceAllowed(int i, Intent intent, Boolean boolean1)
    {
        return true;
    }

    public boolean getCommonConfigInBoolean(String s, boolean flag)
    {
        s = ((String) (mConfigMap.get(s)));
        if(s instanceof Boolean)
            flag = ((Boolean)s).booleanValue();
        return flag;
    }

    public int getCommonConfigInInt(String s, int i)
    {
        s = ((String) (mConfigMap.get(s)));
        if(s instanceof Integer)
            i = ((Integer)s).intValue();
        return i;
    }

    public JSONArray getCommonConfigInJSONArray(String s)
    {
        s = ((String) (mConfigMap.get(s)));
        if(s instanceof JSONArray)
            return (JSONArray)s;
        else
            return null;
    }

    public JSONObject getCommonConfigInJSONObject(String s)
    {
        s = ((String) (mConfigMap.get(s)));
        if(s instanceof JSONObject)
            return (JSONObject)s;
        else
            return null;
    }

    public String getCommonConfigInString(String s, String s1)
    {
        s = ((String) (mConfigMap.get(s)));
        if(s instanceof String)
            s1 = (String)s;
        return s1;
    }

    public void updateFrameworkCommonConfig(String s)
    {
        Log.d(TAG, (new StringBuilder()).append("updateFrameworkCommonConfig json:").append(s).toString());
        try
        {
            JSONObject jsonobject = JVM INSTR new #107 <Class JSONObject>;
            jsonobject.JSONObject(s);
            String s1;
            Object obj;
            for(s = jsonobject.keys(); s.hasNext(); mConfigMap.put(s1, obj))
            {
                s1 = (String)s.next();
                obj = jsonobject.get(s1);
            }

        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w(TAG, (new StringBuilder()).append("updateFrameworkCommonConfig JSONException e:").append(s.getMessage()).toString());
        }
        setChanged();
        notifyObservers();
    }

    private static final String TAG = com/miui/whetstone/strategy/WhetstoneSystemSetting.getSimpleName();
    private ConcurrentHashMap mConfigMap;
    private Context mContext;
    private WhetstoneActivityManagerService mWhetstoneActivityManagerService;

}
