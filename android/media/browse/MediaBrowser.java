// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.browse;

import android.content.*;
import android.content.pm.ParceledListSlice;
import android.media.MediaDescription;
import android.os.*;
import android.service.media.IMediaBrowserService;
import android.service.media.IMediaBrowserServiceCallbacks;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package android.media.browse:
//            MediaBrowserUtils

public final class MediaBrowser
{
    public static class ConnectionCallback
    {

        public void onConnected()
        {
        }

        public void onConnectionFailed()
        {
        }

        public void onConnectionSuspended()
        {
        }

        public ConnectionCallback()
        {
        }
    }

    public static abstract class ItemCallback
    {

        public void onError(String s)
        {
        }

        public void onItemLoaded(MediaItem mediaitem)
        {
        }

        public ItemCallback()
        {
        }
    }

    public static class MediaItem
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public MediaDescription getDescription()
        {
            return mDescription;
        }

        public int getFlags()
        {
            return mFlags;
        }

        public String getMediaId()
        {
            return mDescription.getMediaId();
        }

        public boolean isBrowsable()
        {
            boolean flag = false;
            if((mFlags & 1) != 0)
                flag = true;
            return flag;
        }

        public boolean isPlayable()
        {
            boolean flag = false;
            if((mFlags & 2) != 0)
                flag = true;
            return flag;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder("MediaItem{");
            stringbuilder.append("mFlags=").append(mFlags);
            stringbuilder.append(", mDescription=").append(mDescription);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mFlags);
            mDescription.writeToParcel(parcel, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public MediaItem createFromParcel(Parcel parcel)
            {
                return new MediaItem(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public MediaItem[] newArray(int i)
            {
                return new MediaItem[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescription mDescription;
        private final int mFlags;


        public MediaItem(MediaDescription mediadescription, int i)
        {
            if(mediadescription == null)
                throw new IllegalArgumentException("description cannot be null");
            if(TextUtils.isEmpty(mediadescription.getMediaId()))
            {
                throw new IllegalArgumentException("description must have a non-empty media id");
            } else
            {
                mFlags = i;
                mDescription = mediadescription;
                return;
            }
        }

        private MediaItem(Parcel parcel)
        {
            mFlags = parcel.readInt();
            mDescription = (MediaDescription)MediaDescription.CREATOR.createFromParcel(parcel);
        }

        MediaItem(Parcel parcel, MediaItem mediaitem)
        {
            this(parcel);
        }
    }

    private class MediaServiceConnection
        implements ServiceConnection
    {

        static boolean _2D_wrap0(MediaServiceConnection mediaserviceconnection, String s)
        {
            return mediaserviceconnection.isCurrent(s);
        }

        private boolean isCurrent(String s)
        {
            while(MediaBrowser._2D_get7(MediaBrowser.this) != this || MediaBrowser._2D_get8(MediaBrowser.this) == 0 || MediaBrowser._2D_get8(MediaBrowser.this) == 1) 
            {
                if(MediaBrowser._2D_get8(MediaBrowser.this) != 0 && MediaBrowser._2D_get8(MediaBrowser.this) != 1)
                    Log.i("MediaBrowser", (new StringBuilder()).append(s).append(" for ").append(MediaBrowser._2D_get6(MediaBrowser.this)).append(" with mServiceConnection=").append(MediaBrowser._2D_get7(MediaBrowser.this)).append(" this=").append(this).toString());
                return false;
            }
            return true;
        }

        private void postOrRun(Runnable runnable)
        {
            if(Thread.currentThread() == MediaBrowser._2D_get2(MediaBrowser.this).getLooper().getThread())
                runnable.run();
            else
                MediaBrowser._2D_get2(MediaBrowser.this).post(runnable);
        }

        public void onServiceConnected(final ComponentName name, IBinder ibinder)
        {
            postOrRun(ibinder. new Runnable() {

                public void run()
                {
                    if(!MediaServiceConnection._2D_wrap0(MediaServiceConnection.this, "onServiceConnected"))
                        return;
                    MediaBrowser._2D_set3(_fld0, android.service.media.IMediaBrowserService.Stub.asInterface(binder));
                    MediaBrowser._2D_set4(_fld0, MediaBrowser._2D_wrap0(_fld0));
                    MediaBrowser._2D_set6(_fld0, 2);
                    MediaBrowser._2D_get4(_fld0).connect(MediaBrowser._2D_get1(_fld0).getPackageName(), MediaBrowser._2D_get3(_fld0), MediaBrowser._2D_get5(_fld0));
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("MediaBrowser", (new StringBuilder()).append("RemoteException during connect for ").append(MediaBrowser._2D_get6(_fld0)).toString());
                      goto _L1
                }

                final MediaServiceConnection this$1;
                final IBinder val$binder;
                final ComponentName val$name;

            
            {
                this$1 = final_mediaserviceconnection;
                name = componentname;
                binder = IBinder.this;
                super();
            }
            }
);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            postOrRun(componentname. new Runnable() {

                public void run()
                {
                    if(!MediaServiceConnection._2D_wrap0(MediaServiceConnection.this, "onServiceDisconnected"))
                    {
                        return;
                    } else
                    {
                        MediaBrowser._2D_set3(_fld0, null);
                        MediaBrowser._2D_set4(_fld0, null);
                        MediaBrowser._2D_set6(_fld0, 4);
                        MediaBrowser._2D_get0(_fld0).onConnectionSuspended();
                        return;
                    }
                }

                final MediaServiceConnection this$1;
                final ComponentName val$name;

            
            {
                this$1 = final_mediaserviceconnection;
                name = ComponentName.this;
                super();
            }
            }
);
        }

        final MediaBrowser this$0;

        private MediaServiceConnection()
        {
            this$0 = MediaBrowser.this;
            super();
        }

        MediaServiceConnection(MediaServiceConnection mediaserviceconnection)
        {
            this();
        }
    }

    private static class ServiceCallbacks extends android.service.media.IMediaBrowserServiceCallbacks.Stub
    {

        public void onConnect(String s, android.media.session.MediaSession.Token token, Bundle bundle)
        {
            MediaBrowser mediabrowser = (MediaBrowser)mMediaBrowser.get();
            if(mediabrowser != null)
                MediaBrowser._2D_wrap6(mediabrowser, this, s, token, bundle);
        }

        public void onConnectFailed()
        {
            MediaBrowser mediabrowser = (MediaBrowser)mMediaBrowser.get();
            if(mediabrowser != null)
                MediaBrowser._2D_wrap4(mediabrowser, this);
        }

        public void onLoadChildren(String s, ParceledListSlice parceledlistslice)
        {
            onLoadChildrenWithOptions(s, parceledlistslice, null);
        }

        public void onLoadChildrenWithOptions(String s, ParceledListSlice parceledlistslice, Bundle bundle)
        {
            MediaBrowser mediabrowser = (MediaBrowser)mMediaBrowser.get();
            if(mediabrowser != null)
                MediaBrowser._2D_wrap5(mediabrowser, this, s, parceledlistslice, bundle);
        }

        private WeakReference mMediaBrowser;

        public ServiceCallbacks(MediaBrowser mediabrowser)
        {
            mMediaBrowser = new WeakReference(mediabrowser);
        }
    }

    private static class Subscription
    {

        public SubscriptionCallback getCallback(Bundle bundle)
        {
            for(int i = 0; i < mOptionsList.size(); i++)
                if(MediaBrowserUtils.areSameOptions((Bundle)mOptionsList.get(i), bundle))
                    return (SubscriptionCallback)mCallbacks.get(i);

            return null;
        }

        public List getCallbacks()
        {
            return mCallbacks;
        }

        public List getOptionsList()
        {
            return mOptionsList;
        }

        public boolean isEmpty()
        {
            return mCallbacks.isEmpty();
        }

        public void putCallback(Bundle bundle, SubscriptionCallback subscriptioncallback)
        {
            for(int i = 0; i < mOptionsList.size(); i++)
                if(MediaBrowserUtils.areSameOptions((Bundle)mOptionsList.get(i), bundle))
                {
                    mCallbacks.set(i, subscriptioncallback);
                    return;
                }

            mCallbacks.add(subscriptioncallback);
            mOptionsList.add(bundle);
        }

        private final List mCallbacks = new ArrayList();
        private final List mOptionsList = new ArrayList();

        public Subscription()
        {
        }
    }

    public static abstract class SubscriptionCallback
    {

        public void onChildrenLoaded(String s, List list)
        {
        }

        public void onChildrenLoaded(String s, List list, Bundle bundle)
        {
        }

        public void onError(String s)
        {
        }

        public void onError(String s, Bundle bundle)
        {
        }

        Binder mToken;

        public SubscriptionCallback()
        {
            mToken = new Binder();
        }
    }


    static ConnectionCallback _2D_get0(MediaBrowser mediabrowser)
    {
        return mediabrowser.mCallback;
    }

    static Context _2D_get1(MediaBrowser mediabrowser)
    {
        return mediabrowser.mContext;
    }

    static Handler _2D_get2(MediaBrowser mediabrowser)
    {
        return mediabrowser.mHandler;
    }

    static Bundle _2D_get3(MediaBrowser mediabrowser)
    {
        return mediabrowser.mRootHints;
    }

    static IMediaBrowserService _2D_get4(MediaBrowser mediabrowser)
    {
        return mediabrowser.mServiceBinder;
    }

    static IMediaBrowserServiceCallbacks _2D_get5(MediaBrowser mediabrowser)
    {
        return mediabrowser.mServiceCallbacks;
    }

    static ComponentName _2D_get6(MediaBrowser mediabrowser)
    {
        return mediabrowser.mServiceComponent;
    }

    static MediaServiceConnection _2D_get7(MediaBrowser mediabrowser)
    {
        return mediabrowser.mServiceConnection;
    }

    static int _2D_get8(MediaBrowser mediabrowser)
    {
        return mediabrowser.mState;
    }

    static ArrayMap _2D_get9(MediaBrowser mediabrowser)
    {
        return mediabrowser.mSubscriptions;
    }

    static Bundle _2D_set0(MediaBrowser mediabrowser, Bundle bundle)
    {
        mediabrowser.mExtras = bundle;
        return bundle;
    }

    static android.media.session.MediaSession.Token _2D_set1(MediaBrowser mediabrowser, android.media.session.MediaSession.Token token)
    {
        mediabrowser.mMediaSessionToken = token;
        return token;
    }

    static String _2D_set2(MediaBrowser mediabrowser, String s)
    {
        mediabrowser.mRootId = s;
        return s;
    }

    static IMediaBrowserService _2D_set3(MediaBrowser mediabrowser, IMediaBrowserService imediabrowserservice)
    {
        mediabrowser.mServiceBinder = imediabrowserservice;
        return imediabrowserservice;
    }

    static IMediaBrowserServiceCallbacks _2D_set4(MediaBrowser mediabrowser, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
    {
        mediabrowser.mServiceCallbacks = imediabrowserservicecallbacks;
        return imediabrowserservicecallbacks;
    }

    static MediaServiceConnection _2D_set5(MediaBrowser mediabrowser, MediaServiceConnection mediaserviceconnection)
    {
        mediabrowser.mServiceConnection = mediaserviceconnection;
        return mediaserviceconnection;
    }

    static int _2D_set6(MediaBrowser mediabrowser, int i)
    {
        mediabrowser.mState = i;
        return i;
    }

    static ServiceCallbacks _2D_wrap0(MediaBrowser mediabrowser)
    {
        return mediabrowser.getNewServiceCallbacks();
    }

    static boolean _2D_wrap1(MediaBrowser mediabrowser, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks, String s)
    {
        return mediabrowser.isCurrent(imediabrowserservicecallbacks, s);
    }

    static String _2D_wrap2(int i)
    {
        return getStateLabel(i);
    }

    static void _2D_wrap3(MediaBrowser mediabrowser)
    {
        mediabrowser.forceCloseConnection();
    }

    static void _2D_wrap4(MediaBrowser mediabrowser, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
    {
        mediabrowser.onConnectionFailed(imediabrowserservicecallbacks);
    }

    static void _2D_wrap5(MediaBrowser mediabrowser, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks, String s, ParceledListSlice parceledlistslice, Bundle bundle)
    {
        mediabrowser.onLoadChildren(imediabrowserservicecallbacks, s, parceledlistslice, bundle);
    }

    static void _2D_wrap6(MediaBrowser mediabrowser, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks, String s, android.media.session.MediaSession.Token token, Bundle bundle)
    {
        mediabrowser.onServiceConnected(imediabrowserservicecallbacks, s, token, bundle);
    }

    public MediaBrowser(Context context, ComponentName componentname, ConnectionCallback connectioncallback, Bundle bundle)
    {
        Object obj = null;
        super();
        mState = 1;
        if(context == null)
            throw new IllegalArgumentException("context must not be null");
        if(componentname == null)
            throw new IllegalArgumentException("service component must not be null");
        if(connectioncallback == null)
            throw new IllegalArgumentException("connection callback must not be null");
        mContext = context;
        mServiceComponent = componentname;
        mCallback = connectioncallback;
        if(bundle == null)
            context = obj;
        else
            context = new Bundle(bundle);
        mRootHints = context;
    }

    private void forceCloseConnection()
    {
        if(mServiceConnection != null)
            try
            {
                mContext.unbindService(mServiceConnection);
            }
            catch(IllegalArgumentException illegalargumentexception) { }
        mState = 1;
        mServiceConnection = null;
        mServiceBinder = null;
        mServiceCallbacks = null;
        mRootId = null;
        mMediaSessionToken = null;
    }

    private ServiceCallbacks getNewServiceCallbacks()
    {
        return new ServiceCallbacks(this);
    }

    private static String getStateLabel(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("UNKNOWN/").append(i).toString();

        case 0: // '\0'
            return "CONNECT_STATE_DISCONNECTING";

        case 1: // '\001'
            return "CONNECT_STATE_DISCONNECTED";

        case 2: // '\002'
            return "CONNECT_STATE_CONNECTING";

        case 3: // '\003'
            return "CONNECT_STATE_CONNECTED";

        case 4: // '\004'
            return "CONNECT_STATE_SUSPENDED";
        }
    }

    private boolean isCurrent(IMediaBrowserServiceCallbacks imediabrowserservicecallbacks, String s)
    {
        while(mServiceCallbacks != imediabrowserservicecallbacks || mState == 0 || mState == 1) 
        {
            if(mState != 0 && mState != 1)
                Log.i("MediaBrowser", (new StringBuilder()).append(s).append(" for ").append(mServiceComponent).append(" with mServiceConnection=").append(mServiceCallbacks).append(" this=").append(this).toString());
            return false;
        }
        return true;
    }

    private final void onConnectionFailed(final IMediaBrowserServiceCallbacks callback)
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                Log.e("MediaBrowser", (new StringBuilder()).append("onConnectFailed for ").append(MediaBrowser._2D_get6(MediaBrowser.this)).toString());
                if(!MediaBrowser._2D_wrap1(MediaBrowser.this, callback, "onConnectFailed"))
                    return;
                if(MediaBrowser._2D_get8(MediaBrowser.this) != 2)
                {
                    Log.w("MediaBrowser", (new StringBuilder()).append("onConnect from service while mState=").append(MediaBrowser._2D_wrap2(MediaBrowser._2D_get8(MediaBrowser.this))).append("... ignoring").toString());
                    return;
                } else
                {
                    MediaBrowser._2D_wrap3(MediaBrowser.this);
                    MediaBrowser._2D_get0(MediaBrowser.this).onConnectionFailed();
                    return;
                }
            }

            final MediaBrowser this$0;
            final IMediaBrowserServiceCallbacks val$callback;

            
            {
                this$0 = MediaBrowser.this;
                callback = imediabrowserservicecallbacks;
                super();
            }
        }
);
    }

    private final void onLoadChildren(final IMediaBrowserServiceCallbacks callback, final String parentId, final ParceledListSlice list, final Bundle options)
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                if(!MediaBrowser._2D_wrap1(MediaBrowser.this, callback, "onLoadChildren"))
                    return;
                Subscription subscription = (Subscription)MediaBrowser._2D_get9(MediaBrowser.this).get(parentId);
                if(subscription != null)
                {
                    SubscriptionCallback subscriptioncallback = subscription.getCallback(options);
                    if(subscriptioncallback != null)
                    {
                        List list1;
                        if(list == null)
                            list1 = null;
                        else
                            list1 = list.getList();
                        if(options == null)
                        {
                            if(list1 == null)
                                subscriptioncallback.onError(parentId);
                            else
                                subscriptioncallback.onChildrenLoaded(parentId, list1);
                        } else
                        if(list1 == null)
                            subscriptioncallback.onError(parentId, options);
                        else
                            subscriptioncallback.onChildrenLoaded(parentId, list1, options);
                        return;
                    }
                }
            }

            final MediaBrowser this$0;
            final IMediaBrowserServiceCallbacks val$callback;
            final ParceledListSlice val$list;
            final Bundle val$options;
            final String val$parentId;

            
            {
                this$0 = MediaBrowser.this;
                callback = imediabrowserservicecallbacks;
                parentId = s;
                options = bundle;
                list = parceledlistslice;
                super();
            }
        }
);
    }

    private final void onServiceConnected(final IMediaBrowserServiceCallbacks callback, final String root, final android.media.session.MediaSession.Token session, final Bundle extra)
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                if(!MediaBrowser._2D_wrap1(MediaBrowser.this, callback, "onConnect"))
                    return;
                if(MediaBrowser._2D_get8(MediaBrowser.this) != 2)
                {
                    Log.w("MediaBrowser", (new StringBuilder()).append("onConnect from service while mState=").append(MediaBrowser._2D_wrap2(MediaBrowser._2D_get8(MediaBrowser.this))).append("... ignoring").toString());
                    return;
                }
                MediaBrowser._2D_set2(MediaBrowser.this, root);
                MediaBrowser._2D_set1(MediaBrowser.this, session);
                MediaBrowser._2D_set0(MediaBrowser.this, extra);
                MediaBrowser._2D_set6(MediaBrowser.this, 3);
                MediaBrowser._2D_get0(MediaBrowser.this).onConnected();
                for(Iterator iterator = MediaBrowser._2D_get9(MediaBrowser.this).entrySet().iterator(); iterator.hasNext();)
                {
                    Object obj = (java.util.Map.Entry)iterator.next();
                    String s = (String)((java.util.Map.Entry) (obj)).getKey();
                    Subscription subscription = (Subscription)((java.util.Map.Entry) (obj)).getValue();
                    obj = subscription.getCallbacks();
                    List list = subscription.getOptionsList();
                    int i = 0;
                    while(i < ((List) (obj)).size()) 
                    {
                        try
                        {
                            MediaBrowser._2D_get4(MediaBrowser.this).addSubscription(s, ((SubscriptionCallback)((List) (obj)).get(i)).mToken, (Bundle)list.get(i), MediaBrowser._2D_get5(MediaBrowser.this));
                        }
                        catch(RemoteException remoteexception)
                        {
                            Log.d("MediaBrowser", (new StringBuilder()).append("addSubscription failed with RemoteException parentId=").append(s).toString());
                        }
                        i++;
                    }
                }

            }

            final MediaBrowser this$0;
            final IMediaBrowserServiceCallbacks val$callback;
            final Bundle val$extra;
            final String val$root;
            final android.media.session.MediaSession.Token val$session;

            
            {
                this$0 = MediaBrowser.this;
                callback = imediabrowserservicecallbacks;
                root = s;
                session = token;
                extra = bundle;
                super();
            }
        }
);
    }

    private void subscribeInternal(String s, Bundle bundle, SubscriptionCallback subscriptioncallback)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("parentId cannot be empty.");
        if(subscriptioncallback == null)
            throw new IllegalArgumentException("callback cannot be null");
        Subscription subscription = (Subscription)mSubscriptions.get(s);
        Subscription subscription1 = subscription;
        if(subscription == null)
        {
            subscription1 = new Subscription();
            mSubscriptions.put(s, subscription1);
        }
        subscription1.putCallback(bundle, subscriptioncallback);
        if(!isConnected())
            break MISSING_BLOCK_LABEL_126;
        if(bundle != null)
            break MISSING_BLOCK_LABEL_107;
        mServiceBinder.addSubscriptionDeprecated(s, mServiceCallbacks);
        mServiceBinder.addSubscription(s, subscriptioncallback.mToken, bundle, mServiceCallbacks);
_L1:
        return;
        bundle;
        Log.d("MediaBrowser", (new StringBuilder()).append("addSubscription failed with RemoteException parentId=").append(s).toString());
          goto _L1
    }

    private void unsubscribeInternal(String s, SubscriptionCallback subscriptioncallback)
    {
label0:
        {
            {
                if(TextUtils.isEmpty(s))
                    throw new IllegalArgumentException("parentId cannot be empty.");
                Subscription subscription = (Subscription)mSubscriptions.get(s);
                if(subscription == null)
                    return;
                if(subscriptioncallback != null)
                    break label0;
                List list;
                List list1;
                int i;
                try
                {
                    if(isConnected())
                    {
                        mServiceBinder.removeSubscriptionDeprecated(s, mServiceCallbacks);
                        mServiceBinder.removeSubscription(s, null, mServiceCallbacks);
                    }
                }
                catch(RemoteException remoteexception)
                {
                    Log.d("MediaBrowser", (new StringBuilder()).append("removeSubscription failed with RemoteException parentId=").append(s).toString());
                }
            }
            if(subscription.isEmpty() || subscriptioncallback == null)
                mSubscriptions.remove(s);
            return;
        }
        list = subscription.getCallbacks();
        list1 = subscription.getOptionsList();
        i = list.size() - 1;
_L2:
        if(i < 0)
            continue; /* Loop/switch isn't completed */
        if(list.get(i) == subscriptioncallback)
        {
            if(isConnected())
                mServiceBinder.removeSubscription(s, subscriptioncallback.mToken, mServiceCallbacks);
            list.remove(i);
            list1.remove(i);
        }
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        if(true) goto _L4; else goto _L3
_L4:
        break MISSING_BLOCK_LABEL_75;
_L3:
    }

    public void connect()
    {
        if(mState != 0 && mState != 1)
        {
            throw new IllegalStateException((new StringBuilder()).append("connect() called while neither disconnecting nor disconnected (state=").append(getStateLabel(mState)).append(")").toString());
        } else
        {
            mState = 2;
            mHandler.post(new Runnable() {

                public void run()
                {
                    if(MediaBrowser._2D_get8(MediaBrowser.this) == 0)
                        return;
                    MediaBrowser._2D_set6(MediaBrowser.this, 2);
                    if(MediaBrowser._2D_get4(MediaBrowser.this) != null)
                        throw new RuntimeException((new StringBuilder()).append("mServiceBinder should be null. Instead it is ").append(MediaBrowser._2D_get4(MediaBrowser.this)).toString());
                    if(MediaBrowser._2D_get5(MediaBrowser.this) != null)
                        throw new RuntimeException((new StringBuilder()).append("mServiceCallbacks should be null. Instead it is ").append(MediaBrowser._2D_get5(MediaBrowser.this)).toString());
                    Intent intent = new Intent("android.media.browse.MediaBrowserService");
                    intent.setComponent(MediaBrowser._2D_get6(MediaBrowser.this));
                    MediaBrowser._2D_set5(MediaBrowser.this, new MediaServiceConnection(null));
                    boolean flag = false;
                    boolean flag1;
                    try
                    {
                        flag1 = MediaBrowser._2D_get1(MediaBrowser.this).bindService(intent, MediaBrowser._2D_get7(MediaBrowser.this), 1);
                    }
                    catch(Exception exception)
                    {
                        Log.e("MediaBrowser", (new StringBuilder()).append("Failed binding to service ").append(MediaBrowser._2D_get6(MediaBrowser.this)).toString());
                        flag1 = flag;
                    }
                    if(!flag1)
                    {
                        MediaBrowser._2D_wrap3(MediaBrowser.this);
                        MediaBrowser._2D_get0(MediaBrowser.this).onConnectionFailed();
                    }
                }

                final MediaBrowser this$0;

            
            {
                this$0 = MediaBrowser.this;
                super();
            }
            }
);
            return;
        }
    }

    public void disconnect()
    {
        mState = 0;
        mHandler.post(new Runnable() {

            public void run()
            {
                int i;
                if(MediaBrowser._2D_get5(MediaBrowser.this) != null)
                    try
                    {
                        MediaBrowser._2D_get4(MediaBrowser.this).disconnect(MediaBrowser._2D_get5(MediaBrowser.this));
                    }
                    catch(RemoteException remoteexception)
                    {
                        Log.w("MediaBrowser", (new StringBuilder()).append("RemoteException during connect for ").append(MediaBrowser._2D_get6(MediaBrowser.this)).toString());
                    }
                i = MediaBrowser._2D_get8(MediaBrowser.this);
                MediaBrowser._2D_wrap3(MediaBrowser.this);
                if(i != 0)
                    MediaBrowser._2D_set6(MediaBrowser.this, i);
            }

            final MediaBrowser this$0;

            
            {
                this$0 = MediaBrowser.this;
                super();
            }
        }
);
    }

    void dump()
    {
        Log.d("MediaBrowser", "MediaBrowser...");
        Log.d("MediaBrowser", (new StringBuilder()).append("  mServiceComponent=").append(mServiceComponent).toString());
        Log.d("MediaBrowser", (new StringBuilder()).append("  mCallback=").append(mCallback).toString());
        Log.d("MediaBrowser", (new StringBuilder()).append("  mRootHints=").append(mRootHints).toString());
        Log.d("MediaBrowser", (new StringBuilder()).append("  mState=").append(getStateLabel(mState)).toString());
        Log.d("MediaBrowser", (new StringBuilder()).append("  mServiceConnection=").append(mServiceConnection).toString());
        Log.d("MediaBrowser", (new StringBuilder()).append("  mServiceBinder=").append(mServiceBinder).toString());
        Log.d("MediaBrowser", (new StringBuilder()).append("  mServiceCallbacks=").append(mServiceCallbacks).toString());
        Log.d("MediaBrowser", (new StringBuilder()).append("  mRootId=").append(mRootId).toString());
        Log.d("MediaBrowser", (new StringBuilder()).append("  mMediaSessionToken=").append(mMediaSessionToken).toString());
    }

    public Bundle getExtras()
    {
        if(!isConnected())
            throw new IllegalStateException((new StringBuilder()).append("getExtras() called while not connected (state=").append(getStateLabel(mState)).append(")").toString());
        else
            return mExtras;
    }

    public void getItem(final String mediaId, final ItemCallback cb)
    {
        ResultReceiver resultreceiver;
        if(TextUtils.isEmpty(mediaId))
            throw new IllegalArgumentException("mediaId cannot be empty.");
        if(cb == null)
            throw new IllegalArgumentException("cb cannot be null.");
        if(mState != 3)
        {
            Log.i("MediaBrowser", "Not connected, unable to retrieve the MediaItem.");
            mHandler.post(new Runnable() {

                public void run()
                {
                    cb.onError(mediaId);
                }

                final MediaBrowser this$0;
                final ItemCallback val$cb;
                final String val$mediaId;

            
            {
                this$0 = MediaBrowser.this;
                cb = itemcallback;
                mediaId = s;
                super();
            }
            }
);
            return;
        }
        resultreceiver = new ResultReceiver(mediaId) {

            protected void onReceiveResult(int i, Bundle bundle)
            {
                if(!isConnected())
                    return;
                while(i != 0 || bundle == null || bundle.containsKey("media_item") ^ true) 
                {
                    cb.onError(mediaId);
                    return;
                }
                bundle = bundle.getParcelable("media_item");
                if(bundle != null && (bundle instanceof MediaItem) ^ true)
                {
                    cb.onError(mediaId);
                    return;
                } else
                {
                    cb.onItemLoaded((MediaItem)bundle);
                    return;
                }
            }

            final MediaBrowser this$0;
            final ItemCallback val$cb;
            final String val$mediaId;

            
            {
                this$0 = MediaBrowser.this;
                cb = itemcallback;
                mediaId = s;
                super(final_handler);
            }
        }
;
        mServiceBinder.getMediaItem(mediaId, resultreceiver, mServiceCallbacks);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.i("MediaBrowser", "Remote error getting media item.");
        mHandler.post(new Runnable() {

            public void run()
            {
                cb.onError(mediaId);
            }

            final MediaBrowser this$0;
            final ItemCallback val$cb;
            final String val$mediaId;

            
            {
                this$0 = MediaBrowser.this;
                cb = itemcallback;
                mediaId = s;
                super();
            }
        }
);
          goto _L1
    }

    public String getRoot()
    {
        if(!isConnected())
            throw new IllegalStateException((new StringBuilder()).append("getRoot() called while not connected (state=").append(getStateLabel(mState)).append(")").toString());
        else
            return mRootId;
    }

    public ComponentName getServiceComponent()
    {
        if(!isConnected())
            throw new IllegalStateException((new StringBuilder()).append("getServiceComponent() called while not connected (state=").append(mState).append(")").toString());
        else
            return mServiceComponent;
    }

    public android.media.session.MediaSession.Token getSessionToken()
    {
        if(!isConnected())
            throw new IllegalStateException((new StringBuilder()).append("getSessionToken() called while not connected (state=").append(mState).append(")").toString());
        else
            return mMediaSessionToken;
    }

    public boolean isConnected()
    {
        boolean flag;
        if(mState == 3)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void subscribe(String s, SubscriptionCallback subscriptioncallback)
    {
        subscribeInternal(s, null, subscriptioncallback);
    }

    public void subscribe(String s, Bundle bundle, SubscriptionCallback subscriptioncallback)
    {
        if(bundle == null)
        {
            throw new IllegalArgumentException("options cannot be null");
        } else
        {
            subscribeInternal(s, new Bundle(bundle), subscriptioncallback);
            return;
        }
    }

    public void unsubscribe(String s)
    {
        unsubscribeInternal(s, null);
    }

    public void unsubscribe(String s, SubscriptionCallback subscriptioncallback)
    {
        if(subscriptioncallback == null)
        {
            throw new IllegalArgumentException("callback cannot be null");
        } else
        {
            unsubscribeInternal(s, subscriptioncallback);
            return;
        }
    }

    private static final int CONNECT_STATE_CONNECTED = 3;
    private static final int CONNECT_STATE_CONNECTING = 2;
    private static final int CONNECT_STATE_DISCONNECTED = 1;
    private static final int CONNECT_STATE_DISCONNECTING = 0;
    private static final int CONNECT_STATE_SUSPENDED = 4;
    private static final boolean DBG = false;
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    private static final String TAG = "MediaBrowser";
    private final ConnectionCallback mCallback;
    private final Context mContext;
    private volatile Bundle mExtras;
    private final Handler mHandler = new Handler();
    private volatile android.media.session.MediaSession.Token mMediaSessionToken;
    private final Bundle mRootHints;
    private volatile String mRootId;
    private IMediaBrowserService mServiceBinder;
    private IMediaBrowserServiceCallbacks mServiceCallbacks;
    private final ComponentName mServiceComponent;
    private MediaServiceConnection mServiceConnection;
    private volatile int mState;
    private final ArrayMap mSubscriptions = new ArrayMap();
}
