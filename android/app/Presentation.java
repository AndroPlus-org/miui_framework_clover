// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.os.*;
import android.util.*;
import android.view.*;

// Referenced classes of package android.app:
//            Dialog

public class Presentation extends Dialog
{

    static Display _2D_get0(Presentation presentation)
    {
        return presentation.mDisplay;
    }

    static void _2D_wrap0(Presentation presentation)
    {
        presentation.handleDisplayChanged();
    }

    static void _2D_wrap1(Presentation presentation)
    {
        presentation.handleDisplayRemoved();
    }

    public Presentation(Context context, Display display)
    {
        this(context, display, 0);
    }

    public Presentation(Context context, Display display, int i)
    {
        super(createPresentationContext(context, display, i), i, false);
        mToken = new Binder();
        mDisplayListener = new android.hardware.display.DisplayManager.DisplayListener() {

            public void onDisplayAdded(int j)
            {
            }

            public void onDisplayChanged(int j)
            {
                if(j == Presentation._2D_get0(Presentation.this).getDisplayId())
                    Presentation._2D_wrap0(Presentation.this);
            }

            public void onDisplayRemoved(int j)
            {
                if(j == Presentation._2D_get0(Presentation.this).getDisplayId())
                    Presentation._2D_wrap1(Presentation.this);
            }

            final Presentation this$0;

            
            {
                this$0 = Presentation.this;
                super();
            }
        }
;
        mHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 1 1: default 24
            //                           1 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                cancel();
                if(true) goto _L1; else goto _L3
_L3:
            }

            final Presentation this$0;

            
            {
                this$0 = Presentation.this;
                super();
            }
        }
;
        mDisplay = display;
        mDisplayManager = (DisplayManager)getContext().getSystemService("display");
        display = getWindow();
        context = display.getAttributes();
        context.token = mToken;
        display.setAttributes(context);
        display.setGravity(119);
        display.setType(2037);
        setCanceledOnTouchOutside(false);
    }

    private static Context createPresentationContext(Context context, Display display, int i)
    {
        if(context == null)
            throw new IllegalArgumentException("outerContext must not be null");
        if(display == null)
            throw new IllegalArgumentException("display must not be null");
        Context context1 = context.createDisplayContext(display);
        int j = i;
        if(i == 0)
        {
            display = new TypedValue();
            context1.getTheme().resolveAttribute(0x10103c0, display, true);
            j = ((TypedValue) (display)).resourceId;
        }
        return new ContextThemeWrapper(context1, j, ((WindowManagerImpl)context.getSystemService("window")).createPresentationWindowManager(context1)) {

            public Object getSystemService(String s)
            {
                if("window".equals(s))
                    return displayWindowManager;
                else
                    return super.getSystemService(s);
            }

            final WindowManagerImpl val$displayWindowManager;

            
            {
                displayWindowManager = windowmanagerimpl;
                super(context, i);
            }
        }
;
    }

    private void handleDisplayChanged()
    {
        onDisplayChanged();
        if(!isConfigurationStillValid())
        {
            Log.i("Presentation", "Presentation is being dismissed because the display metrics have changed since it was created.");
            cancel();
        }
    }

    private void handleDisplayRemoved()
    {
        onDisplayRemoved();
        cancel();
    }

    private boolean isConfigurationStillValid()
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        mDisplay.getMetrics(displaymetrics);
        return displaymetrics.equalsPhysical(getResources().getDisplayMetrics());
    }

    public Display getDisplay()
    {
        return mDisplay;
    }

    public Resources getResources()
    {
        return getContext().getResources();
    }

    public void onDisplayChanged()
    {
    }

    public void onDisplayRemoved()
    {
    }

    protected void onStart()
    {
        super.onStart();
        mDisplayManager.registerDisplayListener(mDisplayListener, mHandler);
        if(!isConfigurationStillValid())
        {
            Log.i("Presentation", "Presentation is being dismissed because the display metrics have changed since it was created.");
            mHandler.sendEmptyMessage(1);
        }
    }

    protected void onStop()
    {
        mDisplayManager.unregisterDisplayListener(mDisplayListener);
        super.onStop();
    }

    public void show()
    {
        super.show();
    }

    private static final int MSG_CANCEL = 1;
    private static final String TAG = "Presentation";
    private final Display mDisplay;
    private final android.hardware.display.DisplayManager.DisplayListener mDisplayListener;
    private final DisplayManager mDisplayManager;
    private final Handler mHandler;
    private final IBinder mToken;
}
