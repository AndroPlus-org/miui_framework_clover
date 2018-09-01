// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.graphics.Region;
import android.os.*;
import android.util.SeempLog;

// Referenced classes of package android.view:
//            WindowManager, WindowManagerGlobal, IWindowManager, Window, 
//            View, Display

public final class WindowManagerImpl
    implements WindowManager
{

    public WindowManagerImpl(Context context)
    {
        this(context, null);
    }

    private WindowManagerImpl(Context context, Window window)
    {
        mGlobal = WindowManagerGlobal.getInstance();
        mContext = context;
        mParentWindow = window;
    }

    private void applyDefaultToken(ViewGroup.LayoutParams layoutparams)
    {
        if(mDefaultToken != null && mParentWindow == null)
        {
            if(!(layoutparams instanceof WindowManager.LayoutParams))
                throw new IllegalArgumentException("Params must be WindowManager.LayoutParams");
            layoutparams = (WindowManager.LayoutParams)layoutparams;
            if(((WindowManager.LayoutParams) (layoutparams)).token == null)
                layoutparams.token = mDefaultToken;
        }
    }

    public void addView(View view, ViewGroup.LayoutParams layoutparams)
    {
        SeempLog.record_vg_layout(383, layoutparams);
        applyDefaultToken(layoutparams);
        mGlobal.addView(view, layoutparams, mContext.getDisplay(), mParentWindow);
    }

    public WindowManagerImpl createLocalWindowManager(Window window)
    {
        return new WindowManagerImpl(mContext, window);
    }

    public WindowManagerImpl createPresentationWindowManager(Context context)
    {
        return new WindowManagerImpl(context, mParentWindow);
    }

    public Region getCurrentImeTouchRegion()
    {
        Region region;
        try
        {
            region = WindowManagerGlobal.getWindowManagerService().getCurrentImeTouchRegion();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return region;
    }

    public Display getDefaultDisplay()
    {
        return mContext.getDisplay();
    }

    public void removeView(View view)
    {
        mGlobal.removeView(view, false);
    }

    public void removeViewImmediate(View view)
    {
        mGlobal.removeView(view, true);
    }

    public void requestAppKeyboardShortcuts(final WindowManager.KeyboardShortcutsReceiver receiver, int i)
    {
        receiver = new com.android.internal.os.IResultReceiver.Stub() {

            public void send(int j, Bundle bundle)
                throws RemoteException
            {
                bundle = bundle.getParcelableArrayList("shortcuts_array");
                receiver.onKeyboardShortcutsReceived(bundle);
            }

            final WindowManagerImpl this$0;
            final WindowManager.KeyboardShortcutsReceiver val$receiver;

            
            {
                this$0 = WindowManagerImpl.this;
                receiver = keyboardshortcutsreceiver;
                super();
            }
        }
;
        WindowManagerGlobal.getWindowManagerService().requestAppKeyboardShortcuts(receiver, i);
_L2:
        return;
        receiver;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setDefaultToken(IBinder ibinder)
    {
        mDefaultToken = ibinder;
    }

    public void updateViewLayout(View view, ViewGroup.LayoutParams layoutparams)
    {
        SeempLog.record_vg_layout(384, layoutparams);
        applyDefaultToken(layoutparams);
        mGlobal.updateViewLayout(view, layoutparams);
    }

    private final Context mContext;
    private IBinder mDefaultToken;
    private final WindowManagerGlobal mGlobal;
    private final Window mParentWindow;
}
