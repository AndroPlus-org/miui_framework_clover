// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.content.Context;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import java.util.*;

// Referenced classes of package android.media.tv:
//            TvInputManager

public class TvRecordingClient
{
    private class MySessionCallback extends TvInputManager.SessionCallback
    {

        public void onError(TvInputManager.Session session, int i)
        {
            if(this != TvRecordingClient._2D_get3(TvRecordingClient.this))
            {
                Log.w("TvRecordingClient", "onError - session not created");
                return;
            } else
            {
                TvRecordingClient._2D_get0(TvRecordingClient.this).onError(i);
                return;
            }
        }

        public void onRecordingStopped(TvInputManager.Session session, Uri uri)
        {
            if(this != TvRecordingClient._2D_get3(TvRecordingClient.this))
            {
                Log.w("TvRecordingClient", "onRecordingStopped - session not created");
                return;
            } else
            {
                TvRecordingClient._2D_set0(TvRecordingClient.this, false);
                TvRecordingClient._2D_get0(TvRecordingClient.this).onRecordingStopped(uri);
                return;
            }
        }

        public void onSessionCreated(TvInputManager.Session session)
        {
            if(this != TvRecordingClient._2D_get3(TvRecordingClient.this))
            {
                Log.w("TvRecordingClient", "onSessionCreated - session already created");
                if(session != null)
                    session.release();
                return;
            }
            TvRecordingClient._2D_set2(TvRecordingClient.this, session);
            if(session == null) goto _L2; else goto _L1
_L1:
            Pair pair;
            for(session = TvRecordingClient._2D_get1(TvRecordingClient.this).iterator(); session.hasNext(); TvRecordingClient._2D_get2(TvRecordingClient.this).sendAppPrivateCommand((String)pair.first, (Bundle)pair.second))
                pair = (Pair)session.next();

            TvRecordingClient._2D_get1(TvRecordingClient.this).clear();
            TvRecordingClient._2D_get2(TvRecordingClient.this).tune(mChannelUri, mConnectionParams);
_L4:
            return;
_L2:
            TvRecordingClient._2D_set3(TvRecordingClient.this, null);
            if(TvRecordingClient._2D_get0(TvRecordingClient.this) != null)
                TvRecordingClient._2D_get0(TvRecordingClient.this).onConnectionFailed(mInputId);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void onSessionEvent(TvInputManager.Session session, String s, Bundle bundle)
        {
            if(this != TvRecordingClient._2D_get3(TvRecordingClient.this))
            {
                Log.w("TvRecordingClient", "onSessionEvent - session not created");
                return;
            }
            if(TvRecordingClient._2D_get0(TvRecordingClient.this) != null)
                TvRecordingClient._2D_get0(TvRecordingClient.this).onEvent(mInputId, s, bundle);
        }

        public void onSessionReleased(TvInputManager.Session session)
        {
            if(this != TvRecordingClient._2D_get3(TvRecordingClient.this))
            {
                Log.w("TvRecordingClient", "onSessionReleased - session not created");
                return;
            }
            TvRecordingClient._2D_set1(TvRecordingClient.this, false);
            TvRecordingClient._2D_set0(TvRecordingClient.this, false);
            TvRecordingClient._2D_set3(TvRecordingClient.this, null);
            TvRecordingClient._2D_set2(TvRecordingClient.this, null);
            if(TvRecordingClient._2D_get0(TvRecordingClient.this) != null)
                TvRecordingClient._2D_get0(TvRecordingClient.this).onDisconnected(mInputId);
        }

        void onTuned(TvInputManager.Session session, Uri uri)
        {
            if(this != TvRecordingClient._2D_get3(TvRecordingClient.this))
            {
                Log.w("TvRecordingClient", "onTuned - session not created");
                return;
            } else
            {
                TvRecordingClient._2D_set1(TvRecordingClient.this, true);
                TvRecordingClient._2D_get0(TvRecordingClient.this).onTuned(uri);
                return;
            }
        }

        Uri mChannelUri;
        Bundle mConnectionParams;
        final String mInputId;
        final TvRecordingClient this$0;

        MySessionCallback(String s, Uri uri, Bundle bundle)
        {
            this$0 = TvRecordingClient.this;
            super();
            mInputId = s;
            mChannelUri = uri;
            mConnectionParams = bundle;
        }
    }

    public static abstract class RecordingCallback
    {

        public void onConnectionFailed(String s)
        {
        }

        public void onDisconnected(String s)
        {
        }

        public void onError(int i)
        {
        }

        public void onEvent(String s, String s1, Bundle bundle)
        {
        }

        public void onRecordingStopped(Uri uri)
        {
        }

        public void onTuned(Uri uri)
        {
        }

        public RecordingCallback()
        {
        }
    }


    static RecordingCallback _2D_get0(TvRecordingClient tvrecordingclient)
    {
        return tvrecordingclient.mCallback;
    }

    static Queue _2D_get1(TvRecordingClient tvrecordingclient)
    {
        return tvrecordingclient.mPendingAppPrivateCommands;
    }

    static TvInputManager.Session _2D_get2(TvRecordingClient tvrecordingclient)
    {
        return tvrecordingclient.mSession;
    }

    static MySessionCallback _2D_get3(TvRecordingClient tvrecordingclient)
    {
        return tvrecordingclient.mSessionCallback;
    }

    static boolean _2D_set0(TvRecordingClient tvrecordingclient, boolean flag)
    {
        tvrecordingclient.mIsRecordingStarted = flag;
        return flag;
    }

    static boolean _2D_set1(TvRecordingClient tvrecordingclient, boolean flag)
    {
        tvrecordingclient.mIsTuned = flag;
        return flag;
    }

    static TvInputManager.Session _2D_set2(TvRecordingClient tvrecordingclient, TvInputManager.Session session)
    {
        tvrecordingclient.mSession = session;
        return session;
    }

    static MySessionCallback _2D_set3(TvRecordingClient tvrecordingclient, MySessionCallback mysessioncallback)
    {
        tvrecordingclient.mSessionCallback = mysessioncallback;
        return mysessioncallback;
    }

    public TvRecordingClient(Context context, String s, RecordingCallback recordingcallback, Handler handler)
    {
        mCallback = recordingcallback;
        s = handler;
        if(handler == null)
            s = new Handler(Looper.getMainLooper());
        mHandler = s;
        mTvInputManager = (TvInputManager)context.getSystemService("tv_input");
    }

    private void resetInternal()
    {
        mSessionCallback = null;
        mPendingAppPrivateCommands.clear();
        if(mSession != null)
        {
            mSession.release();
            mSession = null;
        }
    }

    public void release()
    {
        resetInternal();
    }

    public void sendAppPrivateCommand(String s, Bundle bundle)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("action cannot be null or an empty string");
        if(mSession != null)
        {
            mSession.sendAppPrivateCommand(s, bundle);
        } else
        {
            Log.w("TvRecordingClient", (new StringBuilder()).append("sendAppPrivateCommand - session not yet created (action \"").append(s).append("\" pending)").toString());
            mPendingAppPrivateCommands.add(Pair.create(s, bundle));
        }
    }

    public void startRecording(Uri uri)
    {
        if(!mIsTuned)
            throw new IllegalStateException("startRecording failed - not yet tuned");
        if(mSession != null)
        {
            mSession.startRecording(uri);
            mIsRecordingStarted = true;
        }
    }

    public void stopRecording()
    {
        if(!mIsRecordingStarted)
            Log.w("TvRecordingClient", "stopRecording failed - recording not yet started");
        if(mSession != null)
            mSession.stopRecording();
    }

    public void tune(String s, Uri uri)
    {
        tune(s, uri, null);
    }

    public void tune(String s, Uri uri, Bundle bundle)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("inputId cannot be null or an empty string");
        if(mIsRecordingStarted)
            throw new IllegalStateException("tune failed - recording already started");
        if(mSessionCallback == null || !TextUtils.equals(mSessionCallback.mInputId, s)) goto _L2; else goto _L1
_L1:
        if(mSession != null)
        {
            mSession.tune(uri, bundle);
        } else
        {
            mSessionCallback.mChannelUri = uri;
            mSessionCallback.mConnectionParams = bundle;
        }
_L4:
        return;
_L2:
        resetInternal();
        mSessionCallback = new MySessionCallback(s, uri, bundle);
        if(mTvInputManager != null)
            mTvInputManager.createRecordingSession(s, mSessionCallback, mHandler);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "TvRecordingClient";
    private final RecordingCallback mCallback;
    private final Handler mHandler;
    private boolean mIsRecordingStarted;
    private boolean mIsTuned;
    private final Queue mPendingAppPrivateCommands = new ArrayDeque();
    private TvInputManager.Session mSession;
    private MySessionCallback mSessionCallback;
    private final TvInputManager mTvInputManager;
}
