// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.service.voice.IVoiceInteractionSession;
import android.util.SparseIntArray;
import com.android.internal.app.IVoiceInteractor;
import java.util.List;

public abstract class ActivityManagerInternal
{
    public static abstract class SleepToken
    {

        public abstract void release();

        public SleepToken()
        {
        }
    }


    public ActivityManagerInternal()
    {
    }

    public abstract SleepToken acquireSleepToken(String s, int i);

    public abstract String checkContentProviderAccess(String s, int i);

    public abstract void clearSavedANRState();

    public abstract ComponentName getHomeActivityForUser(int i);

    public abstract List getTopVisibleActivities();

    public abstract int getUidProcessState(int i);

    public abstract void grantUriPermissionFromIntent(int i, String s, Intent intent, int j);

    public abstract boolean hasRunningActivity(int i, String s);

    public abstract boolean isProcessWaitingToUse(int i);

    public abstract boolean isSystemReady();

    public abstract void killForegroundAppsForUser(int i);

    public abstract void notifyAppTransitionCancelled();

    public abstract void notifyAppTransitionFinished();

    public abstract void notifyAppTransitionStarting(SparseIntArray sparseintarray, long l);

    public abstract void notifyDockedStackMinimizedChanged(boolean flag);

    public abstract void notifyKeyguardFlagsChanged(Runnable runnable);

    public abstract void notifyKeyguardTrustedChanged();

    public abstract void notifyNetworkPolicyRulesUpdated(int i, long l);

    public abstract void onLocalVoiceInteractionStarted(IBinder ibinder, IVoiceInteractionSession ivoiceinteractionsession, IVoiceInteractor ivoiceinteractor);

    public abstract void onUserRemoved(int i);

    public abstract void onWakefulnessChanged(int i);

    public abstract void saveANRState(String s);

    public abstract void setDeviceIdleWhitelist(int ai[]);

    public abstract void setFocusedActivity(IBinder ibinder);

    public abstract void setHasOverlayUi(int i, boolean flag);

    public abstract void setPendingIntentWhitelistDuration(IIntentSender iintentsender, IBinder ibinder, long l);

    public abstract void setVr2dDisplayId(int i);

    public abstract int startActivitiesAsPackage(String s, int i, Intent aintent[], Bundle bundle);

    public abstract int startIsolatedProcess(String s, String as[], String s1, String s2, int i, Runnable runnable);

    public abstract void updateDeviceIdleTempWhitelist(int ai[], int i, boolean flag);

    public abstract void updatePersistentConfigurationForUser(Configuration configuration, int i);

    public static final int APP_TRANSITION_SAVED_SURFACE = 0;
    public static final int APP_TRANSITION_SNAPSHOT = 4;
    public static final int APP_TRANSITION_SPLASH_SCREEN = 1;
    public static final int APP_TRANSITION_TIMEOUT = 3;
    public static final int APP_TRANSITION_WINDOWS_DRAWN = 2;
}
