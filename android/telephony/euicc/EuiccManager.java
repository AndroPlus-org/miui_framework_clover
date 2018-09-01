// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.euicc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import com.android.internal.telephony.euicc.IEuiccController;

// Referenced classes of package android.telephony.euicc:
//            DownloadableSubscription, EuiccInfo

public class EuiccManager
{

    public EuiccManager(Context context)
    {
        mContext = context;
    }

    private static void sendUnavailableError(PendingIntent pendingintent)
    {
        pendingintent.send(2);
_L2:
        return;
        pendingintent;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void continueOperation(Intent intent, Bundle bundle)
    {
        if(!isEnabled())
        {
            intent = (PendingIntent)intent.getParcelableExtra("android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_RESOLUTION_CALLBACK_INTENT");
            if(intent != null)
                sendUnavailableError(intent);
            return;
        }
        try
        {
            mController.continueOperation(intent, bundle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void deleteSubscription(int i, PendingIntent pendingintent)
    {
        if(!isEnabled())
        {
            sendUnavailableError(pendingintent);
            return;
        }
        try
        {
            mController.deleteSubscription(i, mContext.getOpPackageName(), pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw pendingintent.rethrowFromSystemServer();
        }
    }

    public void downloadSubscription(DownloadableSubscription downloadablesubscription, boolean flag, PendingIntent pendingintent)
    {
        if(!isEnabled())
        {
            sendUnavailableError(pendingintent);
            return;
        }
        try
        {
            mController.downloadSubscription(downloadablesubscription, flag, mContext.getOpPackageName(), pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(DownloadableSubscription downloadablesubscription)
        {
            throw downloadablesubscription.rethrowFromSystemServer();
        }
    }

    public void eraseSubscriptions(PendingIntent pendingintent)
    {
        if(!isEnabled())
        {
            sendUnavailableError(pendingintent);
            return;
        }
        try
        {
            mController.eraseSubscriptions(pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw pendingintent.rethrowFromSystemServer();
        }
    }

    public void getDefaultDownloadableSubscriptionList(PendingIntent pendingintent)
    {
        if(!isEnabled())
        {
            sendUnavailableError(pendingintent);
            return;
        }
        try
        {
            mController.getDefaultDownloadableSubscriptionList(mContext.getOpPackageName(), pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw pendingintent.rethrowFromSystemServer();
        }
    }

    public void getDownloadableSubscriptionMetadata(DownloadableSubscription downloadablesubscription, PendingIntent pendingintent)
    {
        if(!isEnabled())
        {
            sendUnavailableError(pendingintent);
            return;
        }
        try
        {
            mController.getDownloadableSubscriptionMetadata(downloadablesubscription, mContext.getOpPackageName(), pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(DownloadableSubscription downloadablesubscription)
        {
            throw downloadablesubscription.rethrowFromSystemServer();
        }
    }

    public String getEid()
    {
        if(!isEnabled())
            return null;
        String s;
        try
        {
            s = mController.getEid();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public EuiccInfo getEuiccInfo()
    {
        if(!isEnabled())
            return null;
        EuiccInfo euiccinfo;
        try
        {
            euiccinfo = mController.getEuiccInfo();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return euiccinfo;
    }

    public boolean isEnabled()
    {
        boolean flag;
        if(mController != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void retainSubscriptionsForFactoryReset(PendingIntent pendingintent)
    {
        if(!isEnabled())
        {
            sendUnavailableError(pendingintent);
            return;
        }
        try
        {
            mController.retainSubscriptionsForFactoryReset(pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw pendingintent.rethrowFromSystemServer();
        }
    }

    public void startResolutionActivity(Activity activity, int i, Intent intent, PendingIntent pendingintent)
        throws android.content.IntentSender.SendIntentException
    {
        intent = (PendingIntent)intent.getParcelableExtra("android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_RESOLUTION_INTENT");
        if(intent == null)
        {
            throw new IllegalArgumentException("Invalid result intent");
        } else
        {
            Intent intent1 = new Intent();
            intent1.putExtra("android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_RESOLUTION_CALLBACK_INTENT", pendingintent);
            activity.startIntentSenderForResult(intent.getIntentSender(), i, intent1, 0, 0, 0);
            return;
        }
    }

    public void switchToSubscription(int i, PendingIntent pendingintent)
    {
        if(!isEnabled())
        {
            sendUnavailableError(pendingintent);
            return;
        }
        try
        {
            mController.switchToSubscription(i, mContext.getOpPackageName(), pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw pendingintent.rethrowFromSystemServer();
        }
    }

    public void updateSubscriptionNickname(int i, String s, PendingIntent pendingintent)
    {
        if(!isEnabled())
        {
            sendUnavailableError(pendingintent);
            return;
        }
        try
        {
            mController.updateSubscriptionNickname(i, s, pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public static final String ACTION_MANAGE_EMBEDDED_SUBSCRIPTIONS = "android.telephony.euicc.action.MANAGE_EMBEDDED_SUBSCRIPTIONS";
    public static final String ACTION_PROVISION_EMBEDDED_SUBSCRIPTION = "android.telephony.euicc.action.PROVISION_EMBEDDED_SUBSCRIPTION";
    public static final String ACTION_RESOLVE_ERROR = "android.telephony.euicc.action.RESOLVE_ERROR";
    public static final int EMBEDDED_SUBSCRIPTION_RESULT_ERROR = 2;
    public static final int EMBEDDED_SUBSCRIPTION_RESULT_OK = 0;
    public static final int EMBEDDED_SUBSCRIPTION_RESULT_RESOLVABLE_ERROR = 1;
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_DETAILED_CODE";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_DOWNLOADABLE_SUBSCRIPTION = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_DOWNLOADABLE_SUBSCRIPTION";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_DOWNLOADABLE_SUBSCRIPTIONS = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_DOWNLOADABLE_SUBSCRIPTIONS";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_RESOLUTION_ACTION = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_RESOLUTION_ACTION";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_RESOLUTION_CALLBACK_INTENT = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_RESOLUTION_CALLBACK_INTENT";
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_RESOLUTION_INTENT = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_RESOLUTION_INTENT";
    public static final String EXTRA_FORCE_PROVISION = "android.telephony.euicc.extra.FORCE_PROVISION";
    public static final String META_DATA_CARRIER_ICON = "android.telephony.euicc.carriericon";
    private final Context mContext;
    private final IEuiccController mController = com.android.internal.telephony.euicc.IEuiccController.Stub.asInterface(ServiceManager.getService("econtroller"));
}
