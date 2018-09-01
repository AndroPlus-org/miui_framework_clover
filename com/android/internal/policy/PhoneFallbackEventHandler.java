// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.app.KeyguardManager;
import android.app.SearchManager;
import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.session.MediaSessionLegacyHelper;
import android.os.UserHandle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.*;

// Referenced classes of package com.android.internal.policy:
//            PhoneWindow

public class PhoneFallbackEventHandler
    implements FallbackEventHandler
{

    public PhoneFallbackEventHandler(Context context)
    {
        mContext = context;
    }

    private void handleMediaKeyEvent(KeyEvent keyevent)
    {
        MediaSessionLegacyHelper.getHelper(mContext).sendMediaButtonEvent(keyevent, false);
    }

    private boolean isUserSetupComplete()
    {
        boolean flag = false;
        if(android.provider.Settings.Secure.getInt(mContext.getContentResolver(), "user_setup_complete", 0) != 0)
            flag = true;
        return flag;
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        int i = keyevent.getAction();
        int j = keyevent.getKeyCode();
        if(i == 0)
            return onKeyDown(j, keyevent);
        else
            return onKeyUp(j, keyevent);
    }

    AudioManager getAudioManager()
    {
        if(mAudioManager == null)
            mAudioManager = (AudioManager)mContext.getSystemService("audio");
        return mAudioManager;
    }

    KeyguardManager getKeyguardManager()
    {
        if(mKeyguardManager == null)
            mKeyguardManager = (KeyguardManager)mContext.getSystemService("keyguard");
        return mKeyguardManager;
    }

    SearchManager getSearchManager()
    {
        if(mSearchManager == null)
            mSearchManager = (SearchManager)mContext.getSystemService("search");
        return mSearchManager;
    }

    TelephonyManager getTelephonyManager()
    {
        if(mTelephonyManager == null)
            mTelephonyManager = (TelephonyManager)mContext.getSystemService("phone");
        return mTelephonyManager;
    }

    boolean onKeyDown(int i, KeyEvent keyevent)
    {
        Object obj = mView.getKeyDispatcherState();
        i;
        JVM INSTR lookupswitch 18: default 164
    //                   5: 201
    //                   24: 166
    //                   25: 166
    //                   27: 318
    //                   79: 194
    //                   84: 445
    //                   85: 182
    //                   86: 194
    //                   87: 194
    //                   88: 194
    //                   89: 194
    //                   90: 194
    //                   91: 194
    //                   126: 182
    //                   127: 182
    //                   130: 194
    //                   164: 166
    //                   222: 194;
           goto _L1 _L2 _L3 _L3 _L4 _L5 _L6 _L7 _L5 _L5 _L5 _L5 _L5 _L5 _L7 _L7 _L5 _L3 _L5
_L1:
        return false;
_L3:
        MediaSessionLegacyHelper.getHelper(mContext).sendVolumeKeyEvent(keyevent, 0x80000000, false);
        return true;
_L7:
        if(getTelephonyManager().getCallState() != 0)
            return true;
_L5:
        handleMediaKeyEvent(keyevent);
        return true;
_L2:
        if(getKeyguardManager().inKeyguardRestrictedInputMode() || obj == null)
            continue; /* Loop/switch isn't completed */
        if(keyevent.getRepeatCount() == 0)
            ((android.view.KeyEvent.DispatcherState) (obj)).startTracking(keyevent, this);
        else
        if(keyevent.isLongPress() && ((android.view.KeyEvent.DispatcherState) (obj)).isTracking(keyevent))
        {
            ((android.view.KeyEvent.DispatcherState) (obj)).performedLongPress(keyevent);
            if(isUserSetupComplete())
            {
                mView.performHapticFeedback(0);
                keyevent = new Intent("android.intent.action.VOICE_COMMAND");
                keyevent.setFlags(0x10000000);
                try
                {
                    sendCloseSystemWindows();
                    mContext.startActivity(keyevent);
                }
                // Misplaced declaration of an exception variable
                catch(KeyEvent keyevent)
                {
                    startCallActivity();
                }
            } else
            {
                Log.i(TAG, "Not starting call activity because user setup is in progress.");
            }
        }
        return true;
_L4:
        if(getKeyguardManager().inKeyguardRestrictedInputMode() || obj == null)
            continue; /* Loop/switch isn't completed */
        if(keyevent.getRepeatCount() == 0)
            ((android.view.KeyEvent.DispatcherState) (obj)).startTracking(keyevent, this);
        else
        if(keyevent.isLongPress() && ((android.view.KeyEvent.DispatcherState) (obj)).isTracking(keyevent))
        {
            ((android.view.KeyEvent.DispatcherState) (obj)).performedLongPress(keyevent);
            if(isUserSetupComplete())
            {
                mView.performHapticFeedback(0);
                sendCloseSystemWindows();
                obj = new Intent("android.intent.action.CAMERA_BUTTON", null);
                ((Intent) (obj)).addFlags(0x10000000);
                ((Intent) (obj)).putExtra("android.intent.extra.KEY_EVENT", keyevent);
                mContext.sendOrderedBroadcastAsUser(((Intent) (obj)), UserHandle.CURRENT_OR_SELF, null, null, null, 0, null, null);
            } else
            {
                Log.i(TAG, "Not dispatching CAMERA long press because user setup is in progress.");
            }
        }
        return true;
_L6:
        if(getKeyguardManager().inKeyguardRestrictedInputMode() || obj == null)
            continue; /* Loop/switch isn't completed */
        if(keyevent.getRepeatCount() == 0)
        {
            ((android.view.KeyEvent.DispatcherState) (obj)).startTracking(keyevent, this);
            continue; /* Loop/switch isn't completed */
        }
        if(!keyevent.isLongPress() || !((android.view.KeyEvent.DispatcherState) (obj)).isTracking(keyevent))
            continue; /* Loop/switch isn't completed */
        Configuration configuration = mContext.getResources().getConfiguration();
        if(configuration.keyboard != 1 && configuration.hardKeyboardHidden != 2)
            continue; /* Loop/switch isn't completed */
        if(!isUserSetupComplete()) goto _L9; else goto _L8
_L8:
        Intent intent;
        intent = new Intent("android.intent.action.SEARCH_LONG_PRESS");
        intent.setFlags(0x10000000);
        mView.performHapticFeedback(0);
        sendCloseSystemWindows();
        getSearchManager().stopSearch();
        mContext.startActivity(intent);
        ((android.view.KeyEvent.DispatcherState) (obj)).performedLongPress(keyevent);
        return true;
_L9:
        Log.i(TAG, "Not dispatching SEARCH long press because user setup is in progress.");
        continue; /* Loop/switch isn't completed */
        keyevent;
        if(true) goto _L1; else goto _L10
_L10:
    }

    boolean onKeyUp(int i, KeyEvent keyevent)
    {
        android.view.KeyEvent.DispatcherState dispatcherstate = mView.getKeyDispatcherState();
        if(dispatcherstate != null)
            dispatcherstate.handleUpEvent(keyevent);
        i;
        JVM INSTR lookupswitch 17: default 164
    //                   5: 220
    //                   24: 166
    //                   25: 166
    //                   27: 196
    //                   79: 189
    //                   85: 189
    //                   86: 189
    //                   87: 189
    //                   88: 189
    //                   89: 189
    //                   90: 189
    //                   91: 189
    //                   126: 189
    //                   127: 189
    //                   130: 189
    //                   164: 166
    //                   222: 189;
           goto _L1 _L2 _L3 _L3 _L4 _L5 _L5 _L5 _L5 _L5 _L5 _L5 _L5 _L5 _L5 _L5 _L3 _L5
_L1:
        return false;
_L3:
        if(!keyevent.isCanceled())
            MediaSessionLegacyHelper.getHelper(mContext).sendVolumeKeyEvent(keyevent, 0x80000000, false);
        return true;
_L5:
        handleMediaKeyEvent(keyevent);
        return true;
_L4:
        if(!getKeyguardManager().inKeyguardRestrictedInputMode())
        {
            if(keyevent.isTracking())
                keyevent.isCanceled();
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(!getKeyguardManager().inKeyguardRestrictedInputMode())
        {
            if(keyevent.isTracking() && keyevent.isCanceled() ^ true)
                if(isUserSetupComplete())
                    startCallActivity();
                else
                    Log.i(TAG, "Not starting call activity because user setup is in progress.");
            return true;
        }
        if(true) goto _L1; else goto _L6
_L6:
    }

    public void preDispatchKeyEvent(KeyEvent keyevent)
    {
        getAudioManager().preDispatchKeyEvent(keyevent, 0x80000000);
    }

    void sendCloseSystemWindows()
    {
        PhoneWindow.sendCloseSystemWindows(mContext, null);
    }

    public void setView(View view)
    {
        mView = view;
    }

    void startCallActivity()
    {
        Intent intent;
        sendCloseSystemWindows();
        intent = new Intent("android.intent.action.CALL_BUTTON");
        intent.setFlags(0x10000000);
        mContext.startActivity(intent);
_L1:
        return;
        ActivityNotFoundException activitynotfoundexception;
        activitynotfoundexception;
        Log.w(TAG, "No activity found for android.intent.action.CALL_BUTTON.");
          goto _L1
    }

    private static final boolean DEBUG = false;
    private static String TAG = "PhoneFallbackEventHandler";
    AudioManager mAudioManager;
    Context mContext;
    KeyguardManager mKeyguardManager;
    SearchManager mSearchManager;
    TelephonyManager mTelephonyManager;
    View mView;

}
