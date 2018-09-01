// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.*;
import android.content.res.Resources;
import android.os.*;
import android.util.Log;

// Referenced classes of package com.android.internal.app:
//            IVoiceInteractionManagerService, IVoiceInteractionSessionListener, IVoiceInteractionSessionShowCallback

public class AssistUtils
{

    public AssistUtils(Context context)
    {
        mContext = context;
    }

    public static boolean allowDisablingAssistDisclosure(Context context)
    {
        return context.getResources().getBoolean(0x1120009);
    }

    private static boolean isDisclosureEnabled(Context context)
    {
        boolean flag = false;
        if(android.provider.Settings.Secure.getInt(context.getContentResolver(), "assist_disclosure_enabled", 0) != 0)
            flag = true;
        return flag;
    }

    public static boolean isPreinstalledAssistant(Context context, ComponentName componentname)
    {
        if(componentname == null)
            return false;
        boolean flag;
        try
        {
            context = context.getPackageManager().getApplicationInfo(componentname.getPackageName(), 0);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return false;
        }
        if(!context.isSystemApp())
            flag = context.isUpdatedSystemApp();
        else
            flag = true;
        return flag;
    }

    public static boolean shouldDisclose(Context context, ComponentName componentname)
    {
        boolean flag = true;
        if(!allowDisablingAssistDisclosure(context))
            return true;
        if(!isDisclosureEnabled(context))
            flag = isPreinstalledAssistant(context, componentname) ^ true;
        return flag;
    }

    public boolean activeServiceSupportsAssistGesture()
    {
        boolean flag = false;
        try
        {
            if(mVoiceInteractionManagerService != null)
                flag = mVoiceInteractionManagerService.activeServiceSupportsAssist();
        }
        catch(RemoteException remoteexception)
        {
            Log.w("AssistUtils", "Failed to call activeServiceSupportsAssistGesture", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean activeServiceSupportsLaunchFromKeyguard()
    {
        boolean flag = false;
        try
        {
            if(mVoiceInteractionManagerService != null)
                flag = mVoiceInteractionManagerService.activeServiceSupportsLaunchFromKeyguard();
        }
        catch(RemoteException remoteexception)
        {
            Log.w("AssistUtils", "Failed to call activeServiceSupportsLaunchFromKeyguard", remoteexception);
            return false;
        }
        return flag;
    }

    public ComponentName getActiveServiceComponentName()
    {
label0:
        {
            ComponentName componentname;
            try
            {
                if(mVoiceInteractionManagerService == null)
                    break label0;
                componentname = mVoiceInteractionManagerService.getActiveServiceComponentName();
            }
            catch(RemoteException remoteexception)
            {
                Log.w("AssistUtils", "Failed to call getActiveServiceComponentName", remoteexception);
                return null;
            }
            return componentname;
        }
        return null;
    }

    public ComponentName getAssistComponentForUser(int i)
    {
        Object obj = android.provider.Settings.Secure.getStringForUser(mContext.getContentResolver(), "assistant", i);
        if(obj != null)
            return ComponentName.unflattenFromString(((String) (obj)));
        if(activeServiceSupportsAssistGesture())
            return getActiveServiceComponentName();
        obj = ((SearchManager)mContext.getSystemService("search")).getAssistIntent(false);
        obj = mContext.getPackageManager().resolveActivityAsUser(((android.content.Intent) (obj)), 0x10000, i);
        if(obj != null)
            return new ComponentName(((ResolveInfo) (obj)).activityInfo.applicationInfo.packageName, ((ResolveInfo) (obj)).activityInfo.name);
        else
            return null;
    }

    public void hideCurrentSession()
    {
        if(mVoiceInteractionManagerService != null)
            mVoiceInteractionManagerService.hideCurrentSession();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.w("AssistUtils", "Failed to call hideCurrentSession", remoteexception);
          goto _L1
    }

    public boolean isSessionRunning()
    {
        boolean flag = false;
        try
        {
            if(mVoiceInteractionManagerService != null)
                flag = mVoiceInteractionManagerService.isSessionRunning();
        }
        catch(RemoteException remoteexception)
        {
            Log.w("AssistUtils", "Failed to call isSessionRunning", remoteexception);
            return false;
        }
        return flag;
    }

    public void launchVoiceAssistFromKeyguard()
    {
        if(mVoiceInteractionManagerService != null)
            mVoiceInteractionManagerService.launchVoiceAssistFromKeyguard();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.w("AssistUtils", "Failed to call launchVoiceAssistFromKeyguard", remoteexception);
          goto _L1
    }

    public void onLockscreenShown()
    {
        if(mVoiceInteractionManagerService != null)
            mVoiceInteractionManagerService.onLockscreenShown();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.w("AssistUtils", "Failed to call onLockscreenShown", remoteexception);
          goto _L1
    }

    public void registerVoiceInteractionSessionListener(IVoiceInteractionSessionListener ivoiceinteractionsessionlistener)
    {
        if(mVoiceInteractionManagerService != null)
            mVoiceInteractionManagerService.registerVoiceInteractionSessionListener(ivoiceinteractionsessionlistener);
_L1:
        return;
        ivoiceinteractionsessionlistener;
        Log.w("AssistUtils", "Failed to register voice interaction listener", ivoiceinteractionsessionlistener);
          goto _L1
    }

    public boolean showSessionForActiveService(Bundle bundle, int i, IVoiceInteractionSessionShowCallback ivoiceinteractionsessionshowcallback, IBinder ibinder)
    {
        boolean flag;
        if(mVoiceInteractionManagerService == null)
            break MISSING_BLOCK_LABEL_36;
        flag = mVoiceInteractionManagerService.showSessionForActiveService(bundle, i, ivoiceinteractionsessionshowcallback, ibinder);
        return flag;
        bundle;
        Log.w("AssistUtils", "Failed to call showSessionForActiveService", bundle);
        return false;
    }

    private static final String TAG = "AssistUtils";
    private final Context mContext;
    private final IVoiceInteractionManagerService mVoiceInteractionManagerService = IVoiceInteractionManagerService.Stub.asInterface(ServiceManager.getService("voiceinteraction"));
}
