// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.Context;
import android.text.TextUtils;
import miui.securityspace.CrossUserUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class AutoDisableScreenButtonsHelper
{

    public AutoDisableScreenButtonsHelper()
    {
    }

    private static void checkJson(Context context)
    {
        if(context == null)
            return;
        if(mUserJson == null)
        {
            String s = android.provider.MiuiSettings.System.getStringForUser(context.getContentResolver(), "auto_disable_screen_button", CrossUserUtils.getCurrentUserId());
            if(s == null)
                mUserJson = new JSONObject();
            else
                updateUserJson(s);
        }
        if(mCloudJson == null)
            updateCloudJson(android.provider.Settings.System.getString(context.getContentResolver(), "auto_disable_screen_button_cloud_setting"));
    }

    public static int getAppFlag(Context context, String s)
    {
        context = ((Context) (getValue(context, s)));
        int i;
        if(context == null)
            i = 3;
        else
            i = ((Integer)context).intValue();
        return i;
    }

    public static Object getValue(Context context, String s)
    {
        checkJson(context);
        if(mUserJson != null && mUserJson.has(s))
            return mUserJson.get(s);
        if(mCloudJson == null || !mCloudJson.has(s))
            break MISSING_BLOCK_LABEL_59;
        context = ((Context) (mCloudJson.get(s)));
        return context;
        context;
        context.printStackTrace();
        return null;
    }

    public static void setFlag(Context context, String s, int i)
    {
        setValue(context, s, Integer.valueOf(i));
    }

    public static void setValue(Context context, String s, Object obj)
    {
        checkJson(context);
        if(mUserJson != null && context != null)
        {
            try
            {
                mUserJson.put(s, obj);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
            }
            android.provider.MiuiSettings.System.putStringForUser(context.getContentResolver(), "auto_disable_screen_button", mUserJson.toString(), CrossUserUtils.getCurrentUserId());
        }
    }

    public static void updateCloudJson(String s)
    {
        if(TextUtils.isEmpty(s) || mCloudJson != null && s.equals(mCloudJson.toString()))
            return;
        JSONObject jsonobject = JVM INSTR new #57  <Class JSONObject>;
        jsonobject.JSONObject(s);
        mCloudJson = jsonobject;
_L1:
        return;
        s;
        s.printStackTrace();
          goto _L1
    }

    public static void updateUserJson(String s)
    {
        if(TextUtils.isEmpty(s) || mUserJson != null && s.equals(mUserJson.toString()))
            return;
        JSONObject jsonobject = JVM INSTR new #57  <Class JSONObject>;
        jsonobject.JSONObject(s);
        mUserJson = jsonobject;
_L1:
        return;
        s;
        s.printStackTrace();
          goto _L1
    }

    public static final String CLOUD_SETTING = "auto_disable_screen_button_cloud_setting";
    public static final int ENABLE_ASK = 1;
    public static final int ENABLE_AUTO = 2;
    public static final String MODULE_AUTO_DIS_NAV_BTN = "AutoDisableNavigationButton1";
    public static final int NO = 3;
    public static final int NONE = 0;
    private static final String TAG = "AutoDisableHelper";
    private static JSONObject mCloudJson;
    private static JSONObject mUserJson;
}
