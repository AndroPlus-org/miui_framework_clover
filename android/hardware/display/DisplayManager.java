// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.display;

import android.content.Context;
import android.graphics.Point;
import android.media.projection.MediaProjection;
import android.os.Handler;
import android.util.SparseArray;
import android.view.Display;
import android.view.Surface;
import java.util.ArrayList;

// Referenced classes of package android.hardware.display:
//            DisplayManagerGlobal, VirtualDisplay, WifiDisplayStatus

public final class DisplayManager
{
    public static interface DisplayListener
    {

        public abstract void onDisplayAdded(int i);

        public abstract void onDisplayChanged(int i);

        public abstract void onDisplayRemoved(int i);
    }


    public DisplayManager(Context context)
    {
        mContext = context;
    }

    private void addAllDisplaysLocked(ArrayList arraylist, int ai[])
    {
        for(int i = 0; i < ai.length; i++)
        {
            Display display = getOrCreateDisplayLocked(ai[i], true);
            if(display != null)
                arraylist.add(display);
        }

    }

    private void addPresentationDisplaysLocked(ArrayList arraylist, int ai[], int i)
    {
        for(int j = 0; j < ai.length; j++)
        {
            Display display = getOrCreateDisplayLocked(ai[j], true);
            if(display != null && (display.getFlags() & 8) != 0 && display.getType() == i)
                arraylist.add(display);
        }

    }

    private Display getOrCreateDisplayLocked(int i, boolean flag)
    {
        Display display = (Display)mDisplays.get(i);
        if(display != null) goto _L2; else goto _L1
_L1:
        Object obj;
        if(mContext.getDisplay().getDisplayId() == i)
            obj = mContext;
        else
            obj = mContext.getApplicationContext();
        display = mGlobal.getCompatibleDisplay(i, ((Context) (obj)).getResources());
        obj = display;
        if(display != null)
        {
            mDisplays.put(i, display);
            obj = display;
        }
_L4:
        return ((Display) (obj));
_L2:
        obj = display;
        if(!flag)
        {
            obj = display;
            if(display.isValid() ^ true)
                obj = null;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void connectWifiDisplay(String s)
    {
        mGlobal.connectWifiDisplay(s);
    }

    public VirtualDisplay createVirtualDisplay(MediaProjection mediaprojection, String s, int i, int j, int k, Surface surface, int l, 
            VirtualDisplay.Callback callback, Handler handler, String s1)
    {
        return mGlobal.createVirtualDisplay(mContext, mediaprojection, s, i, j, k, surface, l, callback, handler, s1);
    }

    public VirtualDisplay createVirtualDisplay(String s, int i, int j, int k, Surface surface, int l)
    {
        return createVirtualDisplay(s, i, j, k, surface, l, null, null);
    }

    public VirtualDisplay createVirtualDisplay(String s, int i, int j, int k, Surface surface, int l, VirtualDisplay.Callback callback, 
            Handler handler)
    {
        return createVirtualDisplay(null, s, i, j, k, surface, l, callback, handler, null);
    }

    public void disconnectWifiDisplay()
    {
        mGlobal.disconnectWifiDisplay();
    }

    public void forgetWifiDisplay(String s)
    {
        mGlobal.forgetWifiDisplay(s);
    }

    public Display getDisplay(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Display display = getOrCreateDisplayLocked(i, false);
        obj;
        JVM INSTR monitorexit ;
        return display;
        Exception exception;
        exception;
        throw exception;
    }

    public Display[] getDisplays()
    {
        return getDisplays(null);
    }

    public Display[] getDisplays(String s)
    {
        int ai[] = mGlobal.getDisplayIds();
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(s != null) goto _L2; else goto _L1
_L1:
        addAllDisplaysLocked(mTempDisplays, ai);
_L4:
        s = (Display[])mTempDisplays.toArray(new Display[mTempDisplays.size()]);
        mTempDisplays.clear();
        obj;
        JVM INSTR monitorexit ;
        return s;
_L2:
        if(!s.equals("android.hardware.display.category.PRESENTATION")) goto _L4; else goto _L3
_L3:
        addPresentationDisplaysLocked(mTempDisplays, ai, 3);
        addPresentationDisplaysLocked(mTempDisplays, ai, 2);
        addPresentationDisplaysLocked(mTempDisplays, ai, 4);
        addPresentationDisplaysLocked(mTempDisplays, ai, 5);
          goto _L4
        s;
        mTempDisplays.clear();
        throw s;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
    }

    public Point getStableDisplaySize()
    {
        return mGlobal.getStableDisplaySize();
    }

    public WifiDisplayStatus getWifiDisplayStatus()
    {
        return mGlobal.getWifiDisplayStatus();
    }

    public void pauseWifiDisplay()
    {
        mGlobal.pauseWifiDisplay();
    }

    public void registerDisplayListener(DisplayListener displaylistener, Handler handler)
    {
        mGlobal.registerDisplayListener(displaylistener, handler);
    }

    public void renameWifiDisplay(String s, String s1)
    {
        mGlobal.renameWifiDisplay(s, s1);
    }

    public void resumeWifiDisplay()
    {
        mGlobal.resumeWifiDisplay();
    }

    public void startWifiDisplayScan()
    {
        mGlobal.startWifiDisplayScan();
    }

    public void stopWifiDisplayScan()
    {
        mGlobal.stopWifiDisplayScan();
    }

    public void unregisterDisplayListener(DisplayListener displaylistener)
    {
        mGlobal.unregisterDisplayListener(displaylistener);
    }

    public static final String ACTION_WIFI_DISPLAY_STATUS_CHANGED = "android.hardware.display.action.WIFI_DISPLAY_STATUS_CHANGED";
    private static final boolean DEBUG = false;
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    public static final String EXTRA_WIFI_DISPLAY_STATUS = "android.hardware.display.extra.WIFI_DISPLAY_STATUS";
    private static final String TAG = "DisplayManager";
    public static final int VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR = 16;
    public static final int VIRTUAL_DISPLAY_FLAG_CAN_SHOW_WITH_INSECURE_KEYGUARD = 32;
    public static final int VIRTUAL_DISPLAY_FLAG_DESTROY_CONTENT_ON_REMOVAL = 256;
    public static final int VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY = 8;
    public static final int VIRTUAL_DISPLAY_FLAG_PRESENTATION = 2;
    public static final int VIRTUAL_DISPLAY_FLAG_PUBLIC = 1;
    public static final int VIRTUAL_DISPLAY_FLAG_ROTATES_WITH_CONTENT = 128;
    public static final int VIRTUAL_DISPLAY_FLAG_SECURE = 4;
    public static final int VIRTUAL_DISPLAY_FLAG_SUPPORTS_TOUCH = 64;
    private final Context mContext;
    private final SparseArray mDisplays = new SparseArray();
    private final DisplayManagerGlobal mGlobal = DisplayManagerGlobal.getInstance();
    private final Object mLock = new Object();
    private final ArrayList mTempDisplays = new ArrayList();
}
