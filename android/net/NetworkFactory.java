// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Context;
import android.os.*;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.util.IndentingPrintWriter;
import java.io.FileDescriptor;
import java.io.PrintWriter;

// Referenced classes of package android.net:
//            NetworkRequest, NetworkCapabilities, ConnectivityManager

public class NetworkFactory extends Handler
{
    private class NetworkRequestInfo
    {

        public String toString()
        {
            return (new StringBuilder()).append("{").append(request).append(", score=").append(score).append(", requested=").append(requested).append("}").toString();
        }

        public final NetworkRequest request;
        public boolean requested;
        public int score;
        final NetworkFactory this$0;

        public NetworkRequestInfo(NetworkRequest networkrequest, int i)
        {
            this$0 = NetworkFactory.this;
            super();
            request = networkrequest;
            score = i;
            requested = false;
        }
    }


    public NetworkFactory(Looper looper, Context context, String s, NetworkCapabilities networkcapabilities)
    {
        super(looper);
        mRefCount = 0;
        mMessenger = null;
        LOG_TAG = s;
        mContext = context;
        mCapabilityFilter = networkcapabilities;
    }

    private void evalRequest(NetworkRequestInfo networkrequestinfo)
    {
        if(!networkrequestinfo.requested && networkrequestinfo.score < mScore && networkrequestinfo.request.networkCapabilities.satisfiedByNetworkCapabilities(mCapabilityFilter) && acceptRequest(networkrequestinfo.request, networkrequestinfo.score))
        {
            needNetworkFor(networkrequestinfo.request, networkrequestinfo.score);
            networkrequestinfo.requested = true;
        } else
        {
            while(false) 
                if(networkrequestinfo.requested && (networkrequestinfo.score > mScore || !networkrequestinfo.request.networkCapabilities.satisfiedByNetworkCapabilities(mCapabilityFilter) || !acceptRequest(networkrequestinfo.request, networkrequestinfo.score)))
                {
                    releaseNetworkFor(networkrequestinfo.request);
                    networkrequestinfo.requested = false;
                }
        }
    }

    private void evalRequests()
    {
        for(int i = 0; i < mNetworkRequests.size(); i++)
            evalRequest((NetworkRequestInfo)mNetworkRequests.valueAt(i));

    }

    private void handleSetFilter(NetworkCapabilities networkcapabilities)
    {
        mCapabilityFilter = networkcapabilities;
        evalRequests();
    }

    private void handleSetScore(int i)
    {
        mScore = i;
        evalRequests();
    }

    public boolean acceptRequest(NetworkRequest networkrequest, int i)
    {
        return true;
    }

    public void addNetworkRequest(NetworkRequest networkrequest, int i)
    {
        sendMessage(obtainMessage(0x83000, new NetworkRequestInfo(networkrequest, i)));
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        filedescriptor = new IndentingPrintWriter(printwriter, "  ");
        filedescriptor.println(toString());
        filedescriptor.increaseIndent();
        for(int i = 0; i < mNetworkRequests.size(); i++)
            filedescriptor.println(mNetworkRequests.valueAt(i));

        filedescriptor.decreaseIndent();
    }

    protected int getRequestCount()
    {
        return mNetworkRequests.size();
    }

    protected void handleAddRequest(NetworkRequest networkrequest, int i)
    {
        NetworkRequestInfo networkrequestinfo = (NetworkRequestInfo)mNetworkRequests.get(networkrequest.requestId);
        if(networkrequestinfo == null)
        {
            log((new StringBuilder()).append("got request ").append(networkrequest).append(" with score ").append(i).toString());
            networkrequest = new NetworkRequestInfo(networkrequest, i);
            mNetworkRequests.put(((NetworkRequestInfo) (networkrequest)).request.requestId, networkrequest);
        } else
        {
            networkrequestinfo.score = i;
            networkrequest = networkrequestinfo;
        }
        evalRequest(networkrequest);
    }

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 536576 536579: default 36
    //                   536576 37
    //                   536577 55
    //                   536578 69
    //                   536579 80;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return;
_L2:
        handleAddRequest((NetworkRequest)message.obj, message.arg1);
        continue; /* Loop/switch isn't completed */
_L3:
        handleRemoveRequest((NetworkRequest)message.obj);
        continue; /* Loop/switch isn't completed */
_L4:
        handleSetScore(message.arg1);
        continue; /* Loop/switch isn't completed */
_L5:
        handleSetFilter((NetworkCapabilities)message.obj);
        if(true) goto _L1; else goto _L6
_L6:
    }

    protected void handleRemoveRequest(NetworkRequest networkrequest)
    {
        NetworkRequestInfo networkrequestinfo = (NetworkRequestInfo)mNetworkRequests.get(networkrequest.requestId);
        if(networkrequestinfo != null)
        {
            mNetworkRequests.remove(networkrequest.requestId);
            if(networkrequestinfo.requested)
                releaseNetworkFor(networkrequestinfo.request);
        }
    }

    void lambda$_2D_android_net_NetworkFactory_9783()
    {
        evalRequests();
    }

    protected void log(String s)
    {
        Log.d(LOG_TAG, s);
    }

    protected void needNetworkFor(NetworkRequest networkrequest, int i)
    {
        i = mRefCount + 1;
        mRefCount = i;
        if(i == 1)
            startNetwork();
    }

    protected void reevaluateAllRequests()
    {
        post(new _.Lambda._cls1viEGAj7AUZvOpShNdsx1w19_Xc(this));
    }

    public void register()
    {
        log("Registering NetworkFactory");
        if(mMessenger == null)
        {
            mMessenger = new Messenger(this);
            ConnectivityManager.from(mContext).registerNetworkFactory(mMessenger, LOG_TAG);
        }
    }

    protected void releaseNetworkFor(NetworkRequest networkrequest)
    {
        int i = mRefCount - 1;
        mRefCount = i;
        if(i == 0)
            stopNetwork();
    }

    public void removeNetworkRequest(NetworkRequest networkrequest)
    {
        sendMessage(obtainMessage(0x83001, networkrequest));
    }

    public void setCapabilityFilter(NetworkCapabilities networkcapabilities)
    {
        sendMessage(obtainMessage(0x83003, new NetworkCapabilities(networkcapabilities)));
    }

    public void setScoreFilter(int i)
    {
        sendMessage(obtainMessage(0x83002, i, 0));
    }

    protected void startNetwork()
    {
    }

    protected void stopNetwork()
    {
    }

    public String toString()
    {
        return (new StringBuilder("{")).append(LOG_TAG).append(" - ScoreFilter=").append(mScore).append(", Filter=").append(mCapabilityFilter).append(", requests=").append(mNetworkRequests.size()).append(", refCount=").append(mRefCount).append("}").toString();
    }

    public void unregister()
    {
        log("Unregistering NetworkFactory");
        if(mMessenger != null)
        {
            ConnectivityManager.from(mContext).unregisterNetworkFactory(mMessenger);
            mMessenger = null;
        }
    }

    private static final int BASE = 0x83000;
    public static final int CMD_CANCEL_REQUEST = 0x83001;
    public static final int CMD_REQUEST_NETWORK = 0x83000;
    private static final int CMD_SET_FILTER = 0x83003;
    private static final int CMD_SET_SCORE = 0x83002;
    private static final boolean DBG = true;
    private static final boolean VDBG = false;
    private final String LOG_TAG;
    private NetworkCapabilities mCapabilityFilter;
    private final Context mContext;
    private Messenger mMessenger;
    private final SparseArray mNetworkRequests = new SparseArray();
    private int mRefCount;
    private int mScore;
}
