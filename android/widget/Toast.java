// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.app.INotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

// Referenced classes of package android.widget:
//            TextView, ToastInjector

public class Toast
{
    private static class TN extends android.app.ITransientNotification.Stub
    {

        private void trySendAccessibilityEvent()
        {
            AccessibilityManager accessibilitymanager = AccessibilityManager.getInstance(mView.getContext());
            if(!accessibilitymanager.isEnabled())
            {
                return;
            } else
            {
                AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(64);
                accessibilityevent.setClassName(getClass().getName());
                accessibilityevent.setPackageName(mView.getContext().getPackageName());
                mView.dispatchPopulateAccessibilityEvent(accessibilityevent);
                accessibilitymanager.sendAccessibilityEvent(accessibilityevent);
                return;
            }
        }

        public void cancel()
        {
            mHandler.obtainMessage(2).sendToTarget();
        }

        public void handleHide()
        {
            if(mView != null)
            {
                if(mView.getParent() != null)
                    mWM.removeViewImmediate(mView);
                mView = null;
            }
        }

        public void handleShow(IBinder ibinder)
        {
            if(mHandler.hasMessages(2) || mHandler.hasMessages(1))
                return;
            if(mView != mNextView)
            {
                handleHide();
                mView = mNextView;
                Object obj = mView.getContext().getApplicationContext();
                String s = mView.getContext().getOpPackageName();
                Context context = ((Context) (obj));
                if(obj == null)
                    context = mView.getContext();
                mWM = (WindowManager)context.getSystemService("window");
                obj = mView.getContext().getResources().getConfiguration();
                int i = Gravity.getAbsoluteGravity(mGravity, ((Configuration) (obj)).getLayoutDirection());
                mParams.gravity = i;
                if((i & 7) == 7)
                    mParams.horizontalWeight = 1.0F;
                if((i & 0x70) == 112)
                    mParams.verticalWeight = 1.0F;
                mParams.x = mX;
                mParams.y = mY;
                mParams.verticalMargin = mVerticalMargin;
                mParams.horizontalMargin = mHorizontalMargin;
                mParams.packageName = s;
                obj = mParams;
                long l;
                if(mDuration == 1)
                    l = 7000L;
                else
                    l = 4000L;
                obj.hideTimeoutMilliseconds = l;
                mParams.token = ibinder;
                if(mView.getParent() != null)
                    mWM.removeView(mView);
                try
                {
                    mWM.addView(mView, mParams);
                    trySendAccessibilityEvent();
                }
                // Misplaced declaration of an exception variable
                catch(IBinder ibinder) { }
                Log.i("Toast", (new StringBuilder()).append("Show toast from OpPackageName:").append(s).append(", PackageName:").append(context.getPackageName()).toString());
            }
        }

        public void hide()
        {
            mHandler.obtainMessage(1).sendToTarget();
        }

        public void show(IBinder ibinder)
        {
            mHandler.obtainMessage(0, ibinder).sendToTarget();
        }

        private static final int CANCEL = 2;
        private static final int HIDE = 1;
        static final long LONG_DURATION_TIMEOUT = 7000L;
        static final long SHORT_DURATION_TIMEOUT = 4000L;
        private static final int SHOW = 0;
        int mDuration;
        int mGravity;
        final Handler mHandler;
        float mHorizontalMargin;
        View mNextView;
        String mPackageName;
        final android.view.WindowManager.LayoutParams mParams = new android.view.WindowManager.LayoutParams();
        float mVerticalMargin;
        View mView;
        WindowManager mWM;
        int mX;
        int mY;

        TN(String s, Looper looper)
        {
            android.view.WindowManager.LayoutParams layoutparams = mParams;
            layoutparams.height = -2;
            layoutparams.width = -2;
            layoutparams.format = -3;
            layoutparams.windowAnimations = 0x1030004;
            layoutparams.type = 2005;
            layoutparams.setTitle("Toast");
            layoutparams.flags = 152;
            mPackageName = s;
            s = looper;
            if(looper == null)
            {
                looper = Looper.myLooper();
                s = looper;
                if(looper == null)
                    throw new RuntimeException("Can't toast on a thread that has not called Looper.prepare()");
            }
            mHandler = new _cls1(s, null);
        }
    }


    static INotificationManager _2D_wrap0()
    {
        return getService();
    }

    public Toast(Context context)
    {
        this(context, null);
    }

    public Toast(Context context, Looper looper)
    {
        mContext = context;
        mTN = new TN(context.getPackageName(), looper);
        mTN.mY = context.getResources().getDimensionPixelSize(0x10501aa);
        mTN.mGravity = context.getResources().getInteger(0x10e00a6);
    }

    private static INotificationManager getService()
    {
        if(sService != null)
        {
            return sService;
        } else
        {
            sService = android.app.INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
            return sService;
        }
    }

    public static Toast makeText(Context context, int i, int j)
        throws android.content.res.Resources.NotFoundException
    {
        return makeText(context, context.getResources().getText(i), j);
    }

    public static Toast makeText(Context context, Looper looper, CharSequence charsequence, int i)
    {
        Toast toast = new Toast(context, looper);
        looper = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(0x1090116, null);
        ((TextView)looper.findViewById(0x102000b)).setText(ToastInjector.addAppName(context, charsequence));
        toast.mNextView = looper;
        toast.mDuration = i;
        return toast;
    }

    public static Toast makeText(Context context, CharSequence charsequence, int i)
    {
        return makeText(context, null, charsequence, i);
    }

    public void cancel()
    {
        mTN.cancel();
    }

    public int getDuration()
    {
        return mDuration;
    }

    public int getGravity()
    {
        return mTN.mGravity;
    }

    public float getHorizontalMargin()
    {
        return mTN.mHorizontalMargin;
    }

    public float getVerticalMargin()
    {
        return mTN.mVerticalMargin;
    }

    public View getView()
    {
        return mNextView;
    }

    public android.view.WindowManager.LayoutParams getWindowParams()
    {
        return mTN.mParams;
    }

    public int getXOffset()
    {
        return mTN.mX;
    }

    public int getYOffset()
    {
        return mTN.mY;
    }

    public void setDuration(int i)
    {
        mDuration = i;
        mTN.mDuration = i;
    }

    public void setGravity(int i, int j, int k)
    {
        mTN.mGravity = i;
        mTN.mX = j;
        mTN.mY = k;
    }

    public void setMargin(float f, float f1)
    {
        mTN.mHorizontalMargin = f;
        mTN.mVerticalMargin = f1;
    }

    public void setText(int i)
    {
        setText(mContext.getText(i));
    }

    public void setText(CharSequence charsequence)
    {
        if(mNextView == null)
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        TextView textview = (TextView)mNextView.findViewById(0x102000b);
        if(textview == null)
        {
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        } else
        {
            textview.setText(charsequence);
            return;
        }
    }

    public void setType(int i)
    {
        mTN.mParams.type = i;
    }

    public void setView(View view)
    {
        mNextView = view;
    }

    public void show()
    {
        INotificationManager inotificationmanager;
        String s;
        TN tn;
        if(mNextView == null)
            throw new RuntimeException("setView must have been called");
        inotificationmanager = getService();
        s = mContext.getOpPackageName();
        tn = mTN;
        tn.mNextView = mNextView;
        inotificationmanager.enqueueToast(s, tn, mDuration);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final int LENGTH_LONG = 1;
    public static final int LENGTH_SHORT = 0;
    static final String TAG = "Toast";
    static final boolean localLOGV = false;
    private static INotificationManager sService;
    final Context mContext;
    int mDuration;
    View mNextView;
    final TN mTN;

    // Unreferenced inner class android/widget/Toast$TN$1

/* anonymous class */
    class TN._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 2: default 32
        //                       0 33
        //                       1 52
        //                       2 70;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            message = (IBinder)message.obj;
            handleShow(message);
            continue; /* Loop/switch isn't completed */
_L3:
            handleHide();
            mNextView = null;
            continue; /* Loop/switch isn't completed */
_L4:
            handleHide();
            mNextView = null;
            try
            {
                Toast._2D_wrap0().cancelToast(mPackageName, TN.this);
            }
            // Misplaced declaration of an exception variable
            catch(Message message) { }
            if(true) goto _L1; else goto _L5
_L5:
        }

        final TN this$1;

            
            {
                this$1 = TN.this;
                super(looper, callback);
            }
    }

}
