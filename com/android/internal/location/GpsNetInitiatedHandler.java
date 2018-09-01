// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.location;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.*;
import android.location.INetInitiatedListener;
import android.location.LocationManager;
import android.os.RemoteException;
import android.os.UserHandle;
import android.telephony.*;
import android.util.Log;
import com.android.internal.app.NetInitiatedActivity;
import com.android.internal.notification.SystemNotificationChannels;
import com.android.internal.telephony.GsmAlphabet;
import java.io.UnsupportedEncodingException;

public class GpsNetInitiatedHandler
{
    public static class GpsNiNotification
    {

        public int defaultResponse;
        public boolean needNotify;
        public boolean needVerify;
        public int niType;
        public int notificationId;
        public boolean privacyOverride;
        public String requestorId;
        public int requestorIdEncoding;
        public String text;
        public int textEncoding;
        public int timeout;

        public GpsNiNotification()
        {
        }
    }

    public static class GpsNiResponse
    {

        int userResponse;

        public GpsNiResponse()
        {
        }
    }


    public GpsNetInitiatedHandler(Context context, INetInitiatedListener inetinitiatedlistener, boolean flag)
    {
        mPlaySounds = false;
        mPopupImmediately = true;
        mIsLocationEnabled = false;
        mContext = context;
        if(inetinitiatedlistener == null)
        {
            throw new IllegalArgumentException("netInitiatedListener is null");
        } else
        {
            mNetInitiatedListener = inetinitiatedlistener;
            setSuplEsEnabled(flag);
            mLocationManager = (LocationManager)context.getSystemService("location");
            updateLocationMode();
            mTelephonyManager = (TelephonyManager)context.getSystemService("phone");
            mPhoneStateListener = new PhoneStateListener() {

                public void onCallStateChanged(int i, String s)
                {
                    Log.d("GpsNetInitiatedHandler", (new StringBuilder()).append("onCallStateChanged(): state is ").append(i).toString());
                    if(i == 0)
                        setInEmergency(false);
                }

                final GpsNetInitiatedHandler this$0;

            
            {
                this$0 = GpsNetInitiatedHandler.this;
                super();
            }
            }
;
            mTelephonyManager.listen(mPhoneStateListener, 32);
            context = new IntentFilter();
            context.addAction("android.intent.action.NEW_OUTGOING_CALL");
            context.addAction("android.location.MODE_CHANGED");
            mContext.registerReceiver(mBroadcastReciever, context);
            return;
        }
    }

    static String decodeGSMPackedString(byte abyte0[])
    {
        int i = abyte0.length;
        int j = (i * 8) / 7;
        int k = j;
        if(i % 7 == 0)
        {
            k = j;
            if(i > 0)
            {
                k = j;
                if(abyte0[i - 1] >> 1 == 0)
                    k = j - 1;
            }
        }
        String s = GsmAlphabet.gsm7BitPackedToString(abyte0, 0, k);
        abyte0 = s;
        if(s == null)
        {
            Log.e("GpsNetInitiatedHandler", "Decoding of GSM packed string failed");
            abyte0 = "";
        }
        return abyte0;
    }

    private static String decodeString(String s, boolean flag, int i)
    {
        String s1;
        byte abyte0[];
        s1 = s;
        abyte0 = stringToByteArray(s, flag);
        i;
        JVM INSTR tableswitch -1 3: default 44
    //                   -1 112
    //                   0 82
    //                   1 85
    //                   2 94
    //                   3 103;
           goto _L1 _L2 _L2 _L3 _L4 _L5
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        Log.e("GpsNetInitiatedHandler", (new StringBuilder()).append("Unknown encoding ").append(i).append(" for NI text ").append(s).toString());
        s = s1;
_L7:
        return s;
_L3:
        s = decodeGSMPackedString(abyte0);
        continue; /* Loop/switch isn't completed */
_L4:
        s = decodeUTF8String(abyte0);
        continue; /* Loop/switch isn't completed */
_L5:
        s = decodeUCS2String(abyte0);
        if(true) goto _L7; else goto _L6
_L6:
    }

    static String decodeUCS2String(byte abyte0[])
    {
        try
        {
            abyte0 = new String(abyte0, "UTF-16");
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new AssertionError();
        }
        return abyte0;
    }

    static String decodeUTF8String(byte abyte0[])
    {
        try
        {
            abyte0 = new String(abyte0, "UTF-8");
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new AssertionError();
        }
        return abyte0;
    }

    private static String getDialogMessage(GpsNiNotification gpsninotification, Context context)
    {
        return getNotifMessage(gpsninotification, context);
    }

    public static String getDialogTitle(GpsNiNotification gpsninotification, Context context)
    {
        return getNotifTitle(gpsninotification, context);
    }

    private Intent getDlgIntent(GpsNiNotification gpsninotification)
    {
        Intent intent = new Intent();
        String s = getDialogTitle(gpsninotification, mContext);
        String s1 = getDialogMessage(gpsninotification, mContext);
        intent.setFlags(0x10008000);
        intent.setClass(mContext, com/android/internal/app/NetInitiatedActivity);
        intent.putExtra("notif_id", gpsninotification.notificationId);
        intent.putExtra("title", s);
        intent.putExtra("message", s1);
        intent.putExtra("timeout", gpsninotification.timeout);
        intent.putExtra("default_resp", gpsninotification.defaultResponse);
        Log.d("GpsNetInitiatedHandler", (new StringBuilder()).append("generateIntent, title: ").append(s).append(", message: ").append(s1).append(", timeout: ").append(gpsninotification.timeout).toString());
        return intent;
    }

    private static String getNotifMessage(GpsNiNotification gpsninotification, Context context)
    {
        return String.format(context.getString(0x1040256), new Object[] {
            decodeString(gpsninotification.requestorId, mIsHexInput, gpsninotification.requestorIdEncoding), decodeString(gpsninotification.text, mIsHexInput, gpsninotification.textEncoding)
        });
    }

    private static String getNotifTicker(GpsNiNotification gpsninotification, Context context)
    {
        return String.format(context.getString(0x1040257), new Object[] {
            decodeString(gpsninotification.requestorId, mIsHexInput, gpsninotification.requestorIdEncoding), decodeString(gpsninotification.text, mIsHexInput, gpsninotification.textEncoding)
        });
    }

    private static String getNotifTitle(GpsNiNotification gpsninotification, Context context)
    {
        return String.format(context.getString(0x1040258), new Object[0]);
    }

    private void handleNi(GpsNiNotification gpsninotification)
    {
        Log.d("GpsNetInitiatedHandler", (new StringBuilder()).append("in handleNi () : needNotify: ").append(gpsninotification.needNotify).append(" needVerify: ").append(gpsninotification.needVerify).append(" privacyOverride: ").append(gpsninotification.privacyOverride).append(" mPopupImmediately: ").append(mPopupImmediately).append(" mInEmergency: ").append(getInEmergency()).toString());
        if(!getLocationEnabled() && getInEmergency() ^ true)
            try
            {
                mNetInitiatedListener.sendNiResponse(gpsninotification.notificationId, 4);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("GpsNetInitiatedHandler", "RemoteException in sendNiResponse");
            }
        if(gpsninotification.needNotify)
            if(gpsninotification.needVerify && mPopupImmediately)
                openNiDialog(gpsninotification);
            else
                setNiNotification(gpsninotification);
        if(gpsninotification.needVerify && !gpsninotification.privacyOverride)
            break MISSING_BLOCK_LABEL_167;
        mNetInitiatedListener.sendNiResponse(gpsninotification.notificationId, 1);
_L1:
        return;
        gpsninotification;
        Log.e("GpsNetInitiatedHandler", "RemoteException in sendNiResponse");
          goto _L1
    }

    private void handleNiInEs(GpsNiNotification gpsninotification)
    {
        Log.d("GpsNetInitiatedHandler", (new StringBuilder()).append("in handleNiInEs () : niType: ").append(gpsninotification.niType).append(" notificationId: ").append(gpsninotification.notificationId).toString());
        boolean flag;
        if(gpsninotification.niType == 4)
            flag = true;
        else
            flag = false;
        if(flag == getInEmergency())
            break MISSING_BLOCK_LABEL_94;
        mNetInitiatedListener.sendNiResponse(gpsninotification.notificationId, 4);
_L1:
        return;
        gpsninotification;
        Log.e("GpsNetInitiatedHandler", "RemoteException in sendNiResponse");
          goto _L1
        handleNi(gpsninotification);
          goto _L1
    }

    private void openNiDialog(GpsNiNotification gpsninotification)
    {
        Intent intent = getDlgIntent(gpsninotification);
        Log.d("GpsNetInitiatedHandler", (new StringBuilder()).append("openNiDialog, notifyId: ").append(gpsninotification.notificationId).append(", requestorId: ").append(gpsninotification.requestorId).append(", text: ").append(gpsninotification.text).toString());
        mContext.startActivity(intent);
    }

    private void setNiNotification(GpsNiNotification gpsninotification)
    {
        this;
        JVM INSTR monitorenter ;
        NotificationManager notificationmanager = (NotificationManager)mContext.getSystemService("notification");
        if(notificationmanager != null)
            break MISSING_BLOCK_LABEL_23;
        this;
        JVM INSTR monitorexit ;
        return;
        String s;
        String s1;
        s = getNotifTitle(gpsninotification, mContext);
        s1 = getNotifMessage(gpsninotification, mContext);
        StringBuilder stringbuilder = JVM INSTR new #192 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.d("GpsNetInitiatedHandler", stringbuilder.append("setNiNotification, notifyId: ").append(gpsninotification.notificationId).append(", title: ").append(s).append(", message: ").append(s1).toString());
        if(mNiNotificationBuilder == null)
        {
            android.app.Notification.Builder builder = JVM INSTR new #390 <Class android.app.Notification$Builder>;
            builder.android.app.Notification.Builder(mContext, SystemNotificationChannels.NETWORK_ALERTS);
            mNiNotificationBuilder = builder.setSmallIcon(0x10807c3).setWhen(0L).setOngoing(true).setAutoCancel(true).setColor(mContext.getColor(0x1060155));
        }
        if(!mPlaySounds) goto _L2; else goto _L1
_L1:
        mNiNotificationBuilder.setDefaults(1);
_L3:
        Object obj;
        if(mPopupImmediately)
            break MISSING_BLOCK_LABEL_270;
        obj = getDlgIntent(gpsninotification);
_L4:
        obj = PendingIntent.getBroadcast(mContext, 0, ((Intent) (obj)), 0);
        mNiNotificationBuilder.setTicker(getNotifTicker(gpsninotification, mContext)).setContentTitle(s).setContentText(s1).setContentIntent(((PendingIntent) (obj)));
        notificationmanager.notifyAsUser(null, gpsninotification.notificationId, mNiNotificationBuilder.build(), UserHandle.ALL);
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        mNiNotificationBuilder.setDefaults(0);
          goto _L3
        gpsninotification;
        throw gpsninotification;
        obj = new Intent();
          goto _L4
    }

    static byte[] stringToByteArray(String s, boolean flag)
    {
        int i;
        byte abyte0[];
        if(flag)
            i = s.length() / 2;
        else
            i = s.length();
        abyte0 = new byte[i];
        if(flag)
        {
            for(int j = 0; j < i; j++)
                abyte0[j] = (byte)Integer.parseInt(s.substring(j * 2, j * 2 + 2), 16);

        } else
        {
            for(int k = 0; k < i; k++)
                abyte0[k] = (byte)s.charAt(k);

        }
        return abyte0;
    }

    public boolean getInEmergency()
    {
        boolean flag = mTelephonyManager.getEmergencyCallbackMode();
        if(mIsInEmergency)
            flag = true;
        return flag;
    }

    public boolean getLocationEnabled()
    {
        return mIsLocationEnabled;
    }

    public boolean getSuplEsEnabled()
    {
        return mIsSuplEsEnabled;
    }

    public void handleNiNotification(GpsNiNotification gpsninotification)
    {
        Log.d("GpsNetInitiatedHandler", (new StringBuilder()).append("in handleNiNotification () : notificationId: ").append(gpsninotification.notificationId).append(" requestorId: ").append(gpsninotification.requestorId).append(" text: ").append(gpsninotification.text).append(" mIsSuplEsEnabled").append(getSuplEsEnabled()).append(" mIsLocationEnabled").append(getLocationEnabled()).toString());
        if(getSuplEsEnabled())
            handleNiInEs(gpsninotification);
        else
            handleNi(gpsninotification);
    }

    public void setInEmergency(boolean flag)
    {
        mIsInEmergency = flag;
    }

    public void setSuplEsEnabled(boolean flag)
    {
        mIsSuplEsEnabled = flag;
    }

    public void updateLocationMode()
    {
        mIsLocationEnabled = mLocationManager.isProviderEnabled("gps");
    }

    public static final String ACTION_NI_VERIFY = "android.intent.action.NETWORK_INITIATED_VERIFY";
    private static final boolean DEBUG = true;
    public static final int GPS_ENC_NONE = 0;
    public static final int GPS_ENC_SUPL_GSM_DEFAULT = 1;
    public static final int GPS_ENC_SUPL_UCS2 = 3;
    public static final int GPS_ENC_SUPL_UTF8 = 2;
    public static final int GPS_ENC_UNKNOWN = -1;
    public static final int GPS_NI_NEED_NOTIFY = 1;
    public static final int GPS_NI_NEED_VERIFY = 2;
    public static final int GPS_NI_PRIVACY_OVERRIDE = 4;
    public static final int GPS_NI_RESPONSE_ACCEPT = 1;
    public static final int GPS_NI_RESPONSE_DENY = 2;
    public static final int GPS_NI_RESPONSE_IGNORE = 4;
    public static final int GPS_NI_RESPONSE_NORESP = 3;
    public static final int GPS_NI_TYPE_EMERGENCY_SUPL = 4;
    public static final int GPS_NI_TYPE_UMTS_CTRL_PLANE = 3;
    public static final int GPS_NI_TYPE_UMTS_SUPL = 2;
    public static final int GPS_NI_TYPE_VOICE = 1;
    public static final String NI_EXTRA_CMD_NOTIF_ID = "notif_id";
    public static final String NI_EXTRA_CMD_RESPONSE = "response";
    public static final String NI_INTENT_KEY_DEFAULT_RESPONSE = "default_resp";
    public static final String NI_INTENT_KEY_MESSAGE = "message";
    public static final String NI_INTENT_KEY_NOTIF_ID = "notif_id";
    public static final String NI_INTENT_KEY_TIMEOUT = "timeout";
    public static final String NI_INTENT_KEY_TITLE = "title";
    public static final String NI_RESPONSE_EXTRA_CMD = "send_ni_response";
    private static final String TAG = "GpsNetInitiatedHandler";
    private static final boolean VERBOSE = false;
    private static boolean mIsHexInput = true;
    private final BroadcastReceiver mBroadcastReciever = new BroadcastReceiver() {

        public void onReceive(Context context1, Intent intent)
        {
            context1 = intent.getAction();
            if(!context1.equals("android.intent.action.NEW_OUTGOING_CALL")) goto _L2; else goto _L1
_L1:
            context1 = intent.getStringExtra("android.intent.extra.PHONE_NUMBER");
            setInEmergency(PhoneNumberUtils.isEmergencyNumber(context1));
            Log.v("GpsNetInitiatedHandler", (new StringBuilder()).append("ACTION_NEW_OUTGOING_CALL - ").append(getInEmergency()).toString());
_L4:
            return;
_L2:
            if(context1.equals("android.location.MODE_CHANGED"))
            {
                updateLocationMode();
                Log.d("GpsNetInitiatedHandler", (new StringBuilder()).append("location enabled :").append(getLocationEnabled()).toString());
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        final GpsNetInitiatedHandler this$0;

            
            {
                this$0 = GpsNetInitiatedHandler.this;
                super();
            }
    }
;
    private final Context mContext;
    private volatile boolean mIsInEmergency;
    private volatile boolean mIsLocationEnabled;
    private volatile boolean mIsSuplEsEnabled;
    private final LocationManager mLocationManager;
    private final INetInitiatedListener mNetInitiatedListener;
    private android.app.Notification.Builder mNiNotificationBuilder;
    private final PhoneStateListener mPhoneStateListener;
    private boolean mPlaySounds;
    private boolean mPopupImmediately;
    private final TelephonyManager mTelephonyManager;

}
