// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.content.*;
import android.os.*;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.bluetooth:
//            BluetoothProfile, BluetoothAdapter, IBluetoothManager, BluetoothDevice, 
//            IBluetoothHeadsetClient, IBluetoothStateChangeCallback, BluetoothHeadsetClientCall

public final class BluetoothHeadsetClient
    implements BluetoothProfile
{

    static ServiceConnection _2D_get0(BluetoothHeadsetClient bluetoothheadsetclient)
    {
        return bluetoothheadsetclient.mConnection;
    }

    static Context _2D_get1(BluetoothHeadsetClient bluetoothheadsetclient)
    {
        return bluetoothheadsetclient.mContext;
    }

    static IBluetoothHeadsetClient _2D_get2(BluetoothHeadsetClient bluetoothheadsetclient)
    {
        return bluetoothheadsetclient.mService;
    }

    static BluetoothProfile.ServiceListener _2D_get3(BluetoothHeadsetClient bluetoothheadsetclient)
    {
        return bluetoothheadsetclient.mServiceListener;
    }

    static IBluetoothHeadsetClient _2D_set0(BluetoothHeadsetClient bluetoothheadsetclient, IBluetoothHeadsetClient ibluetoothheadsetclient)
    {
        bluetoothheadsetclient.mService = ibluetoothheadsetclient;
        return ibluetoothheadsetclient;
    }

    BluetoothHeadsetClient(Context context, BluetoothProfile.ServiceListener servicelistener)
    {
        mContext = context;
        mServiceListener = servicelistener;
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        context = mAdapter.getBluetoothManager();
        if(context != null)
            try
            {
                context.registerStateChangeCallback(mBluetoothStateChangeCallback);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.e("BluetoothHeadsetClient", "", context);
            }
        doBind();
    }

    private boolean isEnabled()
    {
        boolean flag;
        if(mAdapter.getState() == 12)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isValidDevice(BluetoothDevice bluetoothdevice)
    {
        boolean flag;
        if(bluetoothdevice != null)
            flag = BluetoothAdapter.checkBluetoothAddress(bluetoothdevice.getAddress());
        else
            flag = false;
        return flag;
    }

    private static void log(String s)
    {
        Log.d("BluetoothHeadsetClient", s);
    }

    public boolean acceptCall(BluetoothDevice bluetoothdevice, int i)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("acceptCall()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_59;
        boolean flag = ibluetoothheadsetclient.acceptCall(bluetoothdevice, i);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    void close()
    {
        Object obj;
        obj = mAdapter.getBluetoothManager();
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        if(obj != null)
            try
            {
                ((IBluetoothManager) (obj)).unregisterStateChangeCallback(mBluetoothStateChangeCallback);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothHeadsetClient", "", ((Throwable) (obj)));
            }
        obj = mConnection;
        obj;
        JVM INSTR monitorenter ;
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null)
            break MISSING_BLOCK_LABEL_54;
        mService = null;
        mContext.unbindService(mConnection);
_L1:
        obj;
        JVM INSTR monitorexit ;
        mServiceListener = null;
        return;
        Object obj1;
        obj1;
        Log.e("BluetoothHeadsetClient", "", ((Throwable) (obj1)));
          goto _L1
        obj1;
        throw obj1;
    }

    public boolean connect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("connect(").append(bluetoothdevice).append(")").toString());
        IBluetoothHeadsetClient ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothheadsetclient.connect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    public boolean connectAudio(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled()) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothheadsetclient.connectAudio(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        Log.d("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public BluetoothHeadsetClientCall dial(BluetoothDevice bluetoothdevice, String s)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("dial()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_57;
        bluetoothdevice = ibluetoothheadsetclient.dial(bluetoothdevice, s);
        return bluetoothdevice;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return null;
    }

    public boolean disconnect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("disconnect(").append(bluetoothdevice).append(")").toString());
        IBluetoothHeadsetClient ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothheadsetclient.disconnect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    public boolean disconnectAudio(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled()) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothheadsetclient.disconnectAudio(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        Log.d("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothHeadsetClient.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothHeadsetClient", (new StringBuilder()).append("Could not bind to Bluetooth Headset Client Service with ").append(intent).toString());
            return false;
        } else
        {
            return true;
        }
    }

    public boolean enterPrivateMode(BluetoothDevice bluetoothdevice, int i)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("enterPrivateMode()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_59;
        boolean flag = ibluetoothheadsetclient.enterPrivateMode(bluetoothdevice, i);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    public boolean explicitCallTransfer(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("explicitCallTransfer()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_56;
        boolean flag = ibluetoothheadsetclient.explicitCallTransfer(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    public boolean getAudioRouteAllowed(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled()) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothheadsetclient.getAudioRouteAllowed(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        Log.d("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getAudioState(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled()) goto _L2; else goto _L1
_L1:
        int i = ibluetoothheadsetclient.getAudioState(bluetoothdevice);
        return i;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", bluetoothdevice.toString());
_L4:
        return 0;
_L2:
        Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        Log.d("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public List getConnectedDevices()
    {
        Object obj = mService;
        if(obj != null && isEnabled())
        {
            try
            {
                obj = ((IBluetoothHeadsetClient) (obj)).getConnectedDevices();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
            return ((List) (obj));
        }
        if(obj == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothheadsetclient.getConnectionState(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
                return 0;
            }
            return i;
        }
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return 0;
    }

    public Bundle getCurrentAgEvents(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("getCurrentCalls()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_56;
        bluetoothdevice = ibluetoothheadsetclient.getCurrentAgEvents(bluetoothdevice);
        return bluetoothdevice;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return null;
    }

    public Bundle getCurrentAgFeatures(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled()) goto _L2; else goto _L1
_L1:
        bluetoothdevice = ibluetoothheadsetclient.getCurrentAgFeatures(bluetoothdevice);
        return bluetoothdevice;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", bluetoothdevice.toString());
_L4:
        return null;
_L2:
        Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        Log.d("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public List getCurrentCalls(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("getCurrentCalls()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_56;
        bluetoothdevice = ibluetoothheadsetclient.getCurrentCalls(bluetoothdevice);
        return bluetoothdevice;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return null;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient != null && isEnabled())
        {
            try
            {
                ai = ibluetoothheadsetclient.getDevicesMatchingConnectionStates(ai);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
            return ai;
        }
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return new ArrayList();
    }

    public boolean getLastVoiceTagNumber(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("getLastVoiceTagNumber()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_56;
        boolean flag = ibluetoothheadsetclient.getLastVoiceTagNumber(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    public int getPriority(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothheadsetclient.getPriority(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
                return 0;
            }
            return i;
        }
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return 0;
    }

    public boolean holdCall(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("holdCall()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_56;
        boolean flag = ibluetoothheadsetclient.holdCall(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    public boolean rejectCall(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("rejectCall()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_56;
        boolean flag = ibluetoothheadsetclient.rejectCall(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    public boolean sendDTMF(BluetoothDevice bluetoothdevice, byte byte0)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("sendDTMF()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_59;
        boolean flag = ibluetoothheadsetclient.sendDTMF(bluetoothdevice, byte0);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    public void setAudioRouteAllowed(BluetoothDevice bluetoothdevice, boolean flag)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled())
            break MISSING_BLOCK_LABEL_39;
        ibluetoothheadsetclient.setAudioRouteAllowed(bluetoothdevice, flag);
_L1:
        return;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", bluetoothdevice.toString());
          goto _L1
        Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        Log.d("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
          goto _L1
    }

    public boolean setPriority(BluetoothDevice bluetoothdevice, int i)
    {
        log((new StringBuilder()).append("setPriority(").append(bluetoothdevice).append(", ").append(i).append(")").toString());
        IBluetoothHeadsetClient ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            if(i != 0 && i != 100)
                return false;
            boolean flag;
            try
            {
                flag = ibluetoothheadsetclient.setPriority(bluetoothdevice, i);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    public boolean startVoiceRecognition(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("startVoiceRecognition()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_56;
        boolean flag = ibluetoothheadsetclient.startVoiceRecognition(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    public boolean stopVoiceRecognition(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("stopVoiceRecognition()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_56;
        boolean flag = ibluetoothheadsetclient.stopVoiceRecognition(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    public boolean terminateCall(BluetoothDevice bluetoothdevice, BluetoothHeadsetClientCall bluetoothheadsetclientcall)
    {
        IBluetoothHeadsetClient ibluetoothheadsetclient;
        log("terminateCall()");
        ibluetoothheadsetclient = mService;
        if(ibluetoothheadsetclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_59;
        boolean flag = ibluetoothheadsetclient.terminateCall(bluetoothdevice, bluetoothheadsetclientcall);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadsetclient == null)
            Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
        return false;
    }

    public static final String ACTION_AG_EVENT = "android.bluetooth.headsetclient.profile.action.AG_EVENT";
    public static final String ACTION_AUDIO_STATE_CHANGED = "android.bluetooth.headsetclient.profile.action.AUDIO_STATE_CHANGED";
    public static final String ACTION_CALL_CHANGED = "android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED";
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_LAST_VTAG = "android.bluetooth.headsetclient.profile.action.LAST_VTAG";
    public static final String ACTION_RESULT = "android.bluetooth.headsetclient.profile.action.RESULT";
    public static final int ACTION_RESULT_ERROR = 1;
    public static final int ACTION_RESULT_ERROR_BLACKLISTED = 6;
    public static final int ACTION_RESULT_ERROR_BUSY = 3;
    public static final int ACTION_RESULT_ERROR_CME = 7;
    public static final int ACTION_RESULT_ERROR_DELAYED = 5;
    public static final int ACTION_RESULT_ERROR_NO_ANSWER = 4;
    public static final int ACTION_RESULT_ERROR_NO_CARRIER = 2;
    public static final int ACTION_RESULT_OK = 0;
    public static final int CALL_ACCEPT_HOLD = 1;
    public static final int CALL_ACCEPT_NONE = 0;
    public static final int CALL_ACCEPT_TERMINATE = 2;
    public static final int CME_CORPORATE_PERSONALIZATION_PIN_REQUIRED = 46;
    public static final int CME_CORPORATE_PERSONALIZATION_PUK_REQUIRED = 47;
    public static final int CME_DIAL_STRING_TOO_LONG = 26;
    public static final int CME_EAP_NOT_SUPPORTED = 49;
    public static final int CME_EMERGENCY_SERVICE_ONLY = 32;
    public static final int CME_HIDDEN_KEY_REQUIRED = 48;
    public static final int CME_INCORRECT_PARAMETERS = 50;
    public static final int CME_INCORRECT_PASSWORD = 16;
    public static final int CME_INVALID_CHARACTER_IN_DIAL_STRING = 27;
    public static final int CME_INVALID_CHARACTER_IN_TEXT_STRING = 25;
    public static final int CME_INVALID_INDEX = 21;
    public static final int CME_MEMORY_FAILURE = 23;
    public static final int CME_MEMORY_FULL = 20;
    public static final int CME_NETWORK_PERSONALIZATION_PIN_REQUIRED = 40;
    public static final int CME_NETWORK_PERSONALIZATION_PUK_REQUIRED = 41;
    public static final int CME_NETWORK_SUBSET_PERSONALIZATION_PIN_REQUIRED = 42;
    public static final int CME_NETWORK_SUBSET_PERSONALIZATION_PUK_REQUIRED = 43;
    public static final int CME_NETWORK_TIMEOUT = 31;
    public static final int CME_NOT_FOUND = 22;
    public static final int CME_NOT_SUPPORTED_FOR_VOIP = 34;
    public static final int CME_NO_CONNECTION_TO_PHONE = 1;
    public static final int CME_NO_NETWORK_SERVICE = 30;
    public static final int CME_NO_SIMULTANOUS_VOIP_CS_CALLS = 33;
    public static final int CME_OPERATION_NOT_ALLOWED = 3;
    public static final int CME_OPERATION_NOT_SUPPORTED = 4;
    public static final int CME_PHFSIM_PIN_REQUIRED = 6;
    public static final int CME_PHFSIM_PUK_REQUIRED = 7;
    public static final int CME_PHONE_FAILURE = 0;
    public static final int CME_PHSIM_PIN_REQUIRED = 5;
    public static final int CME_SERVICE_PROVIDER_PERSONALIZATION_PIN_REQUIRED = 44;
    public static final int CME_SERVICE_PROVIDER_PERSONALIZATION_PUK_REQUIRED = 45;
    public static final int CME_SIM_BUSY = 14;
    public static final int CME_SIM_FAILURE = 13;
    public static final int CME_SIM_NOT_INSERTED = 10;
    public static final int CME_SIM_PIN2_REQUIRED = 17;
    public static final int CME_SIM_PIN_REQUIRED = 11;
    public static final int CME_SIM_PUK2_REQUIRED = 18;
    public static final int CME_SIM_PUK_REQUIRED = 12;
    public static final int CME_SIM_WRONG = 15;
    public static final int CME_SIP_RESPONSE_CODE = 35;
    public static final int CME_TEXT_STRING_TOO_LONG = 24;
    private static final boolean DBG = true;
    public static final String EXTRA_AG_FEATURE_3WAY_CALLING = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_3WAY_CALLING";
    public static final String EXTRA_AG_FEATURE_ACCEPT_HELD_OR_WAITING_CALL = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ACCEPT_HELD_OR_WAITING_CALL";
    public static final String EXTRA_AG_FEATURE_ATTACH_NUMBER_TO_VT = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ATTACH_NUMBER_TO_VT";
    public static final String EXTRA_AG_FEATURE_ECC = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ECC";
    public static final String EXTRA_AG_FEATURE_MERGE = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_MERGE";
    public static final String EXTRA_AG_FEATURE_MERGE_AND_DETACH = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_MERGE_AND_DETACH";
    public static final String EXTRA_AG_FEATURE_REJECT_CALL = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_REJECT_CALL";
    public static final String EXTRA_AG_FEATURE_RELEASE_AND_ACCEPT = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RELEASE_AND_ACCEPT";
    public static final String EXTRA_AG_FEATURE_RELEASE_HELD_OR_WAITING_CALL = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RELEASE_HELD_OR_WAITING_CALL";
    public static final String EXTRA_AG_FEATURE_RESPONSE_AND_HOLD = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RESPONSE_AND_HOLD";
    public static final String EXTRA_AG_FEATURE_VOICE_RECOGNITION = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_VOICE_RECOGNITION";
    public static final String EXTRA_AUDIO_WBS = "android.bluetooth.headsetclient.extra.AUDIO_WBS";
    public static final String EXTRA_BATTERY_LEVEL = "android.bluetooth.headsetclient.extra.BATTERY_LEVEL";
    public static final String EXTRA_CALL = "android.bluetooth.headsetclient.extra.CALL";
    public static final String EXTRA_CME_CODE = "android.bluetooth.headsetclient.extra.CME_CODE";
    public static final String EXTRA_IN_BAND_RING = "android.bluetooth.headsetclient.extra.IN_BAND_RING";
    public static final String EXTRA_NETWORK_ROAMING = "android.bluetooth.headsetclient.extra.NETWORK_ROAMING";
    public static final String EXTRA_NETWORK_SIGNAL_STRENGTH = "android.bluetooth.headsetclient.extra.NETWORK_SIGNAL_STRENGTH";
    public static final String EXTRA_NETWORK_STATUS = "android.bluetooth.headsetclient.extra.NETWORK_STATUS";
    public static final String EXTRA_NUMBER = "android.bluetooth.headsetclient.extra.NUMBER";
    public static final String EXTRA_OPERATOR_NAME = "android.bluetooth.headsetclient.extra.OPERATOR_NAME";
    public static final String EXTRA_RESULT_CODE = "android.bluetooth.headsetclient.extra.RESULT_CODE";
    public static final String EXTRA_SUBSCRIBER_INFO = "android.bluetooth.headsetclient.extra.SUBSCRIBER_INFO";
    public static final String EXTRA_VOICE_RECOGNITION = "android.bluetooth.headsetclient.extra.VOICE_RECOGNITION";
    public static final int STATE_AUDIO_CONNECTED = 2;
    public static final int STATE_AUDIO_CONNECTING = 1;
    public static final int STATE_AUDIO_DISCONNECTED = 0;
    private static final String TAG = "BluetoothHeadsetClient";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            Log.d("BluetoothHeadsetClient", (new StringBuilder()).append("onBluetoothStateChange: up=").append(flag).toString());
            if(flag) goto _L2; else goto _L1
_L1:
            ServiceConnection serviceconnection = BluetoothHeadsetClient._2D_get0(BluetoothHeadsetClient.this);
            serviceconnection;
            JVM INSTR monitorenter ;
            BluetoothHeadsetClient._2D_set0(BluetoothHeadsetClient.this, null);
            BluetoothHeadsetClient._2D_get1(BluetoothHeadsetClient.this).unbindService(BluetoothHeadsetClient._2D_get0(BluetoothHeadsetClient.this));
_L3:
            serviceconnection;
            JVM INSTR monitorexit ;
            return;
            Object obj1;
            obj1;
            Log.e("BluetoothHeadsetClient", "", ((Throwable) (obj1)));
              goto _L3
            obj1;
            throw obj1;
_L2:
            ServiceConnection serviceconnection1 = BluetoothHeadsetClient._2D_get0(BluetoothHeadsetClient.this);
            serviceconnection1;
            JVM INSTR monitorenter ;
            serviceconnection = serviceconnection1;
            if(BluetoothHeadsetClient._2D_get2(BluetoothHeadsetClient.this) != null) goto _L3; else goto _L4
_L4:
            new Intent(android/bluetooth/IBluetoothHeadsetClient.getName());
            doBind();
            serviceconnection = serviceconnection1;
              goto _L3
            Object obj;
            obj;
            Log.e("BluetoothHeadsetClient", "", ((Throwable) (obj)));
            obj = serviceconnection1;
              goto _L3
            obj;
            throw obj;
        }

        final BluetoothHeadsetClient this$0;

            
            {
                this$0 = BluetoothHeadsetClient.this;
                super();
            }
    }
;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Log.d("BluetoothHeadsetClient", "Proxy object connected");
            BluetoothHeadsetClient._2D_set0(BluetoothHeadsetClient.this, IBluetoothHeadsetClient.Stub.asInterface(Binder.allowBlocking(ibinder)));
            if(BluetoothHeadsetClient._2D_get3(BluetoothHeadsetClient.this) != null)
                BluetoothHeadsetClient._2D_get3(BluetoothHeadsetClient.this).onServiceConnected(16, BluetoothHeadsetClient.this);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d("BluetoothHeadsetClient", "Proxy object disconnected");
            BluetoothHeadsetClient._2D_set0(BluetoothHeadsetClient.this, null);
            if(BluetoothHeadsetClient._2D_get3(BluetoothHeadsetClient.this) != null)
                BluetoothHeadsetClient._2D_get3(BluetoothHeadsetClient.this).onServiceDisconnected(16);
        }

        final BluetoothHeadsetClient this$0;

            
            {
                this$0 = BluetoothHeadsetClient.this;
                super();
            }
    }
;
    private Context mContext;
    private volatile IBluetoothHeadsetClient mService;
    private BluetoothProfile.ServiceListener mServiceListener;
}
