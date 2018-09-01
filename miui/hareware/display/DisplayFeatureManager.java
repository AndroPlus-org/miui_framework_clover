// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.hareware.display;

import android.os.*;
import android.util.Log;
import miui.util.FeatureParser;

// Referenced classes of package miui.hareware.display:
//            DisplayFeatureServiceProxy

public class DisplayFeatureManager
{

    private DisplayFeatureManager()
    {
        IBinder ibinder = ServiceManager.getService("DisplayFeatureControl");
        if(ibinder != null)
            mProxy = new DisplayFeatureServiceProxy(ibinder);
    }

    private static int getDefaultScreenSaturation()
    {
        byte byte0 = 10;
        if(FeatureParser.getBoolean("is_hongmi", false))
            byte0 = 11;
        return FeatureParser.getInteger("display_ce", byte0);
    }

    public static DisplayFeatureManager getInstance()
    {
        if(sInstance == null)
            sInstance = new DisplayFeatureManager();
        return sInstance;
    }

    private int setActiveMode(IBinder ibinder, int i, int j)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.qti.snapdragon.sdk.display.IColorService");
        parcel.writeInt(i);
        parcel.writeInt(j);
        ibinder.transact(6, parcel, parcel1, 0);
        parcel1.readException();
        i = parcel1.readInt();
        parcel1.recycle();
        parcel.recycle();
        return i;
        ibinder;
        parcel1.recycle();
        parcel.recycle();
        throw ibinder;
    }

    private int setDefaultMode(IBinder ibinder, int i, int j)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.qti.snapdragon.sdk.display.IColorService");
        parcel.writeInt(i);
        parcel.writeInt(j);
        ibinder.transact(12, parcel, parcel1, 0);
        parcel1.readException();
        i = parcel1.readInt();
        parcel1.recycle();
        parcel.recycle();
        return i;
        ibinder;
        parcel1.recycle();
        parcel.recycle();
        throw ibinder;
    }

    public int getColorPrefer()
    {
        return SystemProperties.getInt("persist.sys.display_prefer", 2);
    }

    public int getEyeCare()
    {
        return SystemProperties.getInt("persist.sys.display_eyecare", 0);
    }

    public int getScreenCabc()
    {
        return SystemProperties.getInt("persist.sys.display_cabc", 1);
    }

    public int getScreenGamut()
    {
        return SystemProperties.getInt("persist.sys.gamut_mode", 0);
    }

    public int getScreenSaturation()
    {
        return SystemProperties.getInt("persist.sys.display_ce", DEFAULT_SCREEN_SATURATION);
    }

    public boolean isAdEnable()
    {
        return SystemProperties.getBoolean("persist.sys.ltm_enable", true);
    }

    public void setAdEnable(boolean flag)
    {
        int i = 0;
        DisplayFeatureServiceProxy displayfeatureserviceproxy;
        if(mProxy == null)
            break MISSING_BLOCK_LABEL_30;
        displayfeatureserviceproxy = mProxy;
        if(flag)
            i = 1;
        try
        {
            displayfeatureserviceproxy.setAd(0, i, 255);
        }
        catch(Exception exception)
        {
            Log.e(TAG, "set assertive display error.");
        }
        SystemProperties.set("persist.sys.ltm_enable", String.valueOf(flag));
        return;
    }

    public void setColorPrefer(int i)
    {
        try
        {
            if(mProxy != null)
                mProxy.setColorPrefer(0, i);
        }
        catch(Exception exception)
        {
            Log.e(TAG, "set color prefer error.");
        }
        SystemProperties.set("persist.sys.display_prefer", String.valueOf(i));
    }

    public void setEyeCare(int i)
    {
        int j = -1;
        IBinder ibinder;
        if(mProxy != null)
            j = mProxy.setEyeCare(0, i);
        ibinder = ServiceManager.getService("com.qti.snapdragon.sdk.display.IColorService");
        if(j == -1 && ibinder != null)
            try
            {
                setActiveMode(ibinder, 0, i);
                setDefaultMode(ibinder, 0, i);
            }
            catch(Exception exception)
            {
                Log.e(TAG, "set eye care error.");
            }
        SystemProperties.set("persist.sys.display_eyecare", String.valueOf(i));
        return;
    }

    public void setScreenCabc(int i)
    {
        try
        {
            if(mProxy != null)
                mProxy.setCABC(0, i);
        }
        catch(Exception exception)
        {
            Log.e(TAG, "set screen cabc error.");
        }
        SystemProperties.set("persist.sys.display_cabc", String.valueOf(i));
    }

    public void setScreenGamut(int i)
    {
        try
        {
            if(mProxy != null)
                mProxy.setGamutMode(0, i);
        }
        catch(Exception exception)
        {
            Log.e(TAG, "set screen gamut error.");
        }
        SystemProperties.set("persist.sys.gamut_mode", String.valueOf(i));
    }

    public void setScreenSaturation(int i)
    {
        try
        {
            if(mProxy != null)
                mProxy.setCE(0, i);
        }
        catch(Exception exception)
        {
            Log.e(TAG, "set screen ce error.");
        }
        SystemProperties.set("persist.sys.display_ce", String.valueOf(i));
    }

    private static final String COLOR_SERVICE_NAME = "com.qti.snapdragon.sdk.display.IColorService";
    public static final int DEFALUT_GAMUT_MODE = 0;
    public static final int DEFALUT_SCREEN_CABC = 1;
    public static final int DEFALUT_SCREEN_COLOR = 2;
    public static final int DEFAULT_DISPLAY_EYECARE_LEVEL = 0;
    public static final int DEFAULT_SCREEN_SATURATION = getDefaultScreenSaturation();
    private static final String DISPLAY_FEATURE_SERVICE = "DisplayFeatureControl";
    public static final String PROPERTY_ASSERTIVE_DISPLAY = "persist.sys.ltm_enable";
    public static final String PROPERTY_DISPLAY_EYECARE = "persist.sys.display_eyecare";
    public static final String PROPERTY_GAMUT_MODE = "persist.sys.gamut_mode";
    public static final String PROPERTY_SCREEN_CABC = "persist.sys.display_cabc";
    public static final String PROPERTY_SCREEN_COLOR = "persist.sys.display_prefer";
    public static final String PROPERTY_SCREEN_SATURATION = "persist.sys.display_ce";
    public static final int SCREEN_COLOR_COOL = 3;
    public static final int SCREEN_COLOR_NATURE = 2;
    public static final int SCREEN_COLOR_WARM = 1;
    public static final int SCREEN_SATURATION_STANDARD = 10;
    public static final int SCREEN_SATURATION_VIVID = 11;
    private static String TAG = "DisplayFeatureManager";
    private static final int TRANSACTION_setActiveMode = 6;
    private static final int TRANSACTION_setDefaultMode = 12;
    private static DisplayFeatureManager sInstance;
    private DisplayFeatureServiceProxy mProxy;

}
