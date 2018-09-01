// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Context;
import android.os.*;
import android.util.Log;
import com.android.internal.util.AsyncChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package android.net:
//            ConnectivityManager, NetworkInfo, LinkProperties, NetworkCapabilities, 
//            NetworkMisc, UidRange

public abstract class NetworkAgent extends Handler
{

    public NetworkAgent(Looper looper, Context context, String s, NetworkInfo networkinfo, NetworkCapabilities networkcapabilities, LinkProperties linkproperties, int i)
    {
        this(looper, context, s, networkinfo, networkcapabilities, linkproperties, i, null);
    }

    public NetworkAgent(Looper looper, Context context, String s, NetworkInfo networkinfo, NetworkCapabilities networkcapabilities, LinkProperties linkproperties, int i, 
            NetworkMisc networkmisc)
    {
        super(looper);
        mPreConnectedQueue = new ArrayList();
        mLastBwRefreshTime = 0L;
        mPollLceScheduled = false;
        mPollLcePending = new AtomicBoolean(false);
        LOG_TAG = s;
        for(mContext = context; networkinfo == null || networkcapabilities == null || linkproperties == null;)
            throw new IllegalArgumentException();

        netId = ((ConnectivityManager)mContext.getSystemService("connectivity")).registerNetworkAgent(new Messenger(this), new NetworkInfo(networkinfo), new LinkProperties(linkproperties), new NetworkCapabilities(networkcapabilities), i, networkmisc);
    }

    private void queueOrSendMessage(int i, int j, int k)
    {
        queueOrSendMessage(i, j, k, null);
    }

    private void queueOrSendMessage(int i, int j, int k, Object obj)
    {
        Message message = Message.obtain();
        message.what = i;
        message.arg1 = j;
        message.arg2 = k;
        message.obj = obj;
        queueOrSendMessage(message);
    }

    private void queueOrSendMessage(int i, Object obj)
    {
        queueOrSendMessage(i, 0, 0, obj);
    }

    private void queueOrSendMessage(Message message)
    {
        ArrayList arraylist = mPreConnectedQueue;
        arraylist;
        JVM INSTR monitorenter ;
        if(mAsyncChannel == null)
            break MISSING_BLOCK_LABEL_25;
        mAsyncChannel.sendMessage(message);
_L1:
        arraylist;
        JVM INSTR monitorexit ;
        return;
        mPreConnectedQueue.add(message);
          goto _L1
        message;
        throw message;
    }

    public void addUidRanges(UidRange auidrange[])
    {
        queueOrSendMessage(0x81005, auidrange);
    }

    public void explicitlySelected(boolean flag)
    {
        queueOrSendMessage(0x81008, Boolean.valueOf(flag));
    }

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR lookupswitch 11: default 104
    //                   69633: 105
    //                   69635: 211
    //                   69636: 228
    //                   528384: 260
    //                   528391: 361
    //                   528393: 387
    //                   528394: 286
    //                   528395: 412
    //                   528396: 420
    //                   528398: 428
    //                   528399: 503;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
        return;
_L2:
        Object obj;
        if(mAsyncChannel != null)
        {
            log("Received new connection while already connected!");
            continue; /* Loop/switch isn't completed */
        }
        obj = new AsyncChannel();
        ((AsyncChannel) (obj)).connected(null, this, message.replyTo);
        ((AsyncChannel) (obj)).replyToMessage(message, 0x11002, 0);
        message = mPreConnectedQueue;
        message;
        JVM INSTR monitorenter ;
        mAsyncChannel = ((AsyncChannel) (obj));
        for(Iterator iterator = mPreConnectedQueue.iterator(); iterator.hasNext(); ((AsyncChannel) (obj)).sendMessage((Message)iterator.next()));
        break MISSING_BLOCK_LABEL_199;
        Exception exception;
        exception;
        throw exception;
        mPreConnectedQueue.clear();
        message;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
_L3:
        if(mAsyncChannel != null)
            mAsyncChannel.disconnect();
        continue; /* Loop/switch isn't completed */
_L4:
        log("NetworkAgent channel lost");
        unwanted();
        exception = mPreConnectedQueue;
        exception;
        JVM INSTR monitorenter ;
        mAsyncChannel = null;
        exception;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        message;
        throw message;
_L5:
        log((new StringBuilder()).append("Unhandled Message ").append(message).toString());
        continue; /* Loop/switch isn't completed */
_L8:
        long l = System.currentTimeMillis();
        if(l >= mLastBwRefreshTime + 500L)
        {
            mPollLceScheduled = false;
            if(!mPollLcePending.getAndSet(true))
                pollLceData();
        } else
        if(!mPollLceScheduled)
            mPollLceScheduled = sendEmptyMessageDelayed(0x8100a, ((mLastBwRefreshTime + 500L) - l) + 1L);
        continue; /* Loop/switch isn't completed */
_L6:
        String s = ((Bundle)message.obj).getString(REDIRECT_URL_KEY);
        networkStatus(message.arg1, s);
        continue; /* Loop/switch isn't completed */
_L7:
        boolean flag;
        if(message.arg1 != 0)
            flag = true;
        else
            flag = false;
        saveAcceptUnvalidated(flag);
        continue; /* Loop/switch isn't completed */
_L9:
        startPacketKeepalive(message);
        continue; /* Loop/switch isn't completed */
_L10:
        stopPacketKeepalive(message);
        continue; /* Loop/switch isn't completed */
_L11:
        message = ((Bundle)message.obj).getIntegerArrayList("thresholds");
        int ai[];
        int i;
        if(message != null)
            i = message.size();
        else
            i = 0;
        ai = new int[i];
        for(i = 0; i < ai.length; i++)
            ai[i] = ((Integer)message.get(i)).intValue();

        setSignalStrengthThresholds(ai);
        continue; /* Loop/switch isn't completed */
_L12:
        preventAutomaticReconnect();
        if(true) goto _L1; else goto _L13
_L13:
    }

    protected void log(String s)
    {
        Log.d(LOG_TAG, (new StringBuilder()).append("NetworkAgent: ").append(s).toString());
    }

    protected void networkStatus(int i, String s)
    {
    }

    public void onPacketKeepaliveEvent(int i, int j)
    {
        queueOrSendMessage(0x8100d, i, j);
    }

    protected void pollLceData()
    {
    }

    protected void preventAutomaticReconnect()
    {
    }

    public void removeUidRanges(UidRange auidrange[])
    {
        queueOrSendMessage(0x81006, auidrange);
    }

    protected void saveAcceptUnvalidated(boolean flag)
    {
    }

    public void sendLinkProperties(LinkProperties linkproperties)
    {
        queueOrSendMessage(0x81003, new LinkProperties(linkproperties));
    }

    public void sendNetworkCapabilities(NetworkCapabilities networkcapabilities)
    {
        mPollLcePending.set(false);
        mLastBwRefreshTime = System.currentTimeMillis();
        queueOrSendMessage(0x81002, new NetworkCapabilities(networkcapabilities));
    }

    public void sendNetworkInfo(NetworkInfo networkinfo)
    {
        queueOrSendMessage(0x81001, new NetworkInfo(networkinfo));
    }

    public void sendNetworkScore(int i)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException("Score must be >= 0");
        } else
        {
            queueOrSendMessage(0x81004, i, 0);
            return;
        }
    }

    protected void setSignalStrengthThresholds(int ai[])
    {
    }

    protected void startPacketKeepalive(Message message)
    {
        onPacketKeepaliveEvent(message.arg1, -30);
    }

    protected void stopPacketKeepalive(Message message)
    {
        onPacketKeepaliveEvent(message.arg1, -30);
    }

    protected abstract void unwanted();

    private static final int BASE = 0x81000;
    private static final long BW_REFRESH_MIN_WIN_MS = 500L;
    public static final int CMD_PREVENT_AUTOMATIC_RECONNECT = 0x8100f;
    public static final int CMD_REPORT_NETWORK_STATUS = 0x81007;
    public static final int CMD_REQUEST_BANDWIDTH_UPDATE = 0x8100a;
    public static final int CMD_SAVE_ACCEPT_UNVALIDATED = 0x81009;
    public static final int CMD_SET_SIGNAL_STRENGTH_THRESHOLDS = 0x8100e;
    public static final int CMD_START_PACKET_KEEPALIVE = 0x8100b;
    public static final int CMD_STOP_PACKET_KEEPALIVE = 0x8100c;
    public static final int CMD_SUSPECT_BAD = 0x81000;
    private static final boolean DBG = true;
    public static final int EVENT_NETWORK_CAPABILITIES_CHANGED = 0x81002;
    public static final int EVENT_NETWORK_INFO_CHANGED = 0x81001;
    public static final int EVENT_NETWORK_PROPERTIES_CHANGED = 0x81003;
    public static final int EVENT_NETWORK_SCORE_CHANGED = 0x81004;
    public static final int EVENT_PACKET_KEEPALIVE = 0x8100d;
    public static final int EVENT_SET_EXPLICITLY_SELECTED = 0x81008;
    public static final int EVENT_UID_RANGES_ADDED = 0x81005;
    public static final int EVENT_UID_RANGES_REMOVED = 0x81006;
    public static final int INVALID_NETWORK = 2;
    public static String REDIRECT_URL_KEY = "redirect URL";
    public static final int VALID_NETWORK = 1;
    private static final boolean VDBG = false;
    public static final int WIFI_BASE_SCORE = 60;
    private final String LOG_TAG;
    private volatile AsyncChannel mAsyncChannel;
    private final Context mContext;
    private volatile long mLastBwRefreshTime;
    private AtomicBoolean mPollLcePending;
    private boolean mPollLceScheduled;
    private final ArrayList mPreConnectedQueue;
    public final int netId;

}
