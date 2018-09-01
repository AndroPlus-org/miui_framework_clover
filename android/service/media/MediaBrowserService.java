// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.media;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ParceledListSlice;
import android.media.browse.MediaBrowserUtils;
import android.os.*;
import android.util.*;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;

// Referenced classes of package android.service.media:
//            IMediaBrowserServiceCallbacks

public abstract class MediaBrowserService extends Service
{
    public static final class BrowserRoot
    {

        public Bundle getExtras()
        {
            return mExtras;
        }

        public String getRootId()
        {
            return mRootId;
        }

        public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
        public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
        public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
        private final Bundle mExtras;
        private final String mRootId;

        public BrowserRoot(String s, Bundle bundle)
        {
            if(s == null)
            {
                throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
            } else
            {
                mRootId = s;
                mExtras = bundle;
                return;
            }
        }
    }

    private class ConnectionRecord
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            MediaBrowserService._2D_get1(MediaBrowserService.this).post(new Runnable() {

                public void run()
                {
                    MediaBrowserService._2D_get0(_fld0).remove(callbacks.asBinder());
                }

                final ConnectionRecord this$1;

            
            {
                this$1 = ConnectionRecord.this;
                super();
            }
            }
);
        }

        IMediaBrowserServiceCallbacks callbacks;
        String pkg;
        BrowserRoot root;
        Bundle rootHints;
        HashMap subscriptions;
        final MediaBrowserService this$0;

        private ConnectionRecord()
        {
            this$0 = MediaBrowserService.this;
            super();
            subscriptions = new HashMap();
        }

        ConnectionRecord(ConnectionRecord connectionrecord)
        {
            this();
        }
    }

    public class Result
    {

        public void detach()
        {
            if(mDetachCalled)
                throw new IllegalStateException((new StringBuilder()).append("detach() called when detach() had already been called for: ").append(mDebug).toString());
            if(mSendResultCalled)
            {
                throw new IllegalStateException((new StringBuilder()).append("detach() called when sendResult() had already been called for: ").append(mDebug).toString());
            } else
            {
                mDetachCalled = true;
                return;
            }
        }

        boolean isDone()
        {
            boolean flag;
            if(!mDetachCalled)
                flag = mSendResultCalled;
            else
                flag = true;
            return flag;
        }

        void onResultSent(Object obj, int i)
        {
        }

        public void sendResult(Object obj)
        {
            if(mSendResultCalled)
            {
                throw new IllegalStateException((new StringBuilder()).append("sendResult() called twice for: ").append(mDebug).toString());
            } else
            {
                mSendResultCalled = true;
                onResultSent(obj, mFlags);
                return;
            }
        }

        void setFlags(int i)
        {
            mFlags = i;
        }

        private Object mDebug;
        private boolean mDetachCalled;
        private int mFlags;
        private boolean mSendResultCalled;
        final MediaBrowserService this$0;

        Result(Object obj)
        {
            this$0 = MediaBrowserService.this;
            super();
            mDebug = obj;
        }
    }

    private class ServiceBinder extends IMediaBrowserService.Stub
    {

        public void addSubscription(final String id, final IBinder token, Bundle bundle, final IMediaBrowserServiceCallbacks callbacks)
        {
            MediaBrowserService._2D_get1(MediaBrowserService.this).post(bundle. new Runnable() {

                public void run()
                {
                    Object obj = callbacks.asBinder();
                    obj = (ConnectionRecord)MediaBrowserService._2D_get0(_fld0).get(obj);
                    if(obj == null)
                    {
                        Log.w("MediaBrowserService", (new StringBuilder()).append("addSubscription for callback that isn't registered id=").append(id).toString());
                        return;
                    } else
                    {
                        MediaBrowserService._2D_wrap3(_fld0, id, ((ConnectionRecord) (obj)), token, options);
                        return;
                    }
                }

                final ServiceBinder this$1;
                final IMediaBrowserServiceCallbacks val$callbacks;
                final String val$id;
                final Bundle val$options;
                final IBinder val$token;

            
            {
                this$1 = final_servicebinder;
                callbacks = imediabrowserservicecallbacks;
                id = s;
                token = ibinder;
                options = Bundle.this;
                super();
            }
            }
);
        }

        public void addSubscriptionDeprecated(String s, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
        {
        }

        public void connect(final String pkg, final Bundle rootHints, final IMediaBrowserServiceCallbacks callbacks)
        {
            int i = Binder.getCallingUid();
            if(!MediaBrowserService._2D_wrap0(MediaBrowserService.this, pkg, i))
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Package/uid mismatch: uid=").append(i).append(" package=").append(pkg).toString());
            } else
            {
                MediaBrowserService._2D_get1(MediaBrowserService.this).post(i. new Runnable() {

                    public void run()
                    {
                        Object obj;
                        ConnectionRecord connectionrecord;
                        obj = callbacks.asBinder();
                        MediaBrowserService._2D_get0(_fld0).remove(obj);
                        connectionrecord = new ConnectionRecord(null);
                        connectionrecord.pkg = pkg;
                        connectionrecord.rootHints = rootHints;
                        connectionrecord.callbacks = callbacks;
                        connectionrecord.root = onGetRoot(pkg, uid, rootHints);
                        if(connectionrecord.root != null)
                            break MISSING_BLOCK_LABEL_183;
                        Log.i("MediaBrowserService", (new StringBuilder()).append("No root for client ").append(pkg).append(" from service ").append(getClass().getName()).toString());
                        callbacks.onConnectFailed();
_L1:
                        return;
                        obj;
                        Log.w("MediaBrowserService", (new StringBuilder()).append("Calling onConnectFailed() failed. Ignoring. pkg=").append(pkg).toString());
                          goto _L1
                        try
                        {
                            MediaBrowserService._2D_get0(_fld0).put(obj, connectionrecord);
                            ((IBinder) (obj)).linkToDeath(connectionrecord, 0);
                            if(mSession != null)
                                callbacks.onConnect(connectionrecord.root.getRootId(), mSession, connectionrecord.root.getExtras());
                        }
                        catch(RemoteException remoteexception)
                        {
                            Log.w("MediaBrowserService", (new StringBuilder()).append("Calling onConnect() failed. Dropping client. pkg=").append(pkg).toString());
                            MediaBrowserService._2D_get0(_fld0).remove(obj);
                        }
                          goto _L1
                    }

                    final ServiceBinder this$1;
                    final IMediaBrowserServiceCallbacks val$callbacks;
                    final String val$pkg;
                    final Bundle val$rootHints;
                    final int val$uid;

            
            {
                this$1 = final_servicebinder;
                callbacks = imediabrowserservicecallbacks;
                pkg = s;
                rootHints = bundle;
                uid = I.this;
                super();
            }
                }
);
                return;
            }
        }

        public void disconnect(IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
        {
            MediaBrowserService._2D_get1(MediaBrowserService.this).post(imediabrowserservicecallbacks. new Runnable() {

                public void run()
                {
                    Object obj = callbacks.asBinder();
                    obj = (ConnectionRecord)MediaBrowserService._2D_get0(_fld0).remove(obj);
                    if(obj != null)
                        ((ConnectionRecord) (obj)).callbacks.asBinder().unlinkToDeath(((android.os.IBinder.DeathRecipient) (obj)), 0);
                }

                final ServiceBinder this$1;
                final IMediaBrowserServiceCallbacks val$callbacks;

            
            {
                this$1 = final_servicebinder;
                callbacks = IMediaBrowserServiceCallbacks.this;
                super();
            }
            }
);
        }

        public void getMediaItem(final String mediaId, ResultReceiver resultreceiver, final IMediaBrowserServiceCallbacks callbacks)
        {
            MediaBrowserService._2D_get1(MediaBrowserService.this).post(resultreceiver. new Runnable() {

                public void run()
                {
                    Object obj = callbacks.asBinder();
                    obj = (ConnectionRecord)MediaBrowserService._2D_get0(_fld0).get(obj);
                    if(obj == null)
                    {
                        Log.w("MediaBrowserService", (new StringBuilder()).append("getMediaItem for callback that isn't registered id=").append(mediaId).toString());
                        return;
                    } else
                    {
                        MediaBrowserService._2D_wrap5(_fld0, mediaId, ((ConnectionRecord) (obj)), receiver);
                        return;
                    }
                }

                final ServiceBinder this$1;
                final IMediaBrowserServiceCallbacks val$callbacks;
                final String val$mediaId;
                final ResultReceiver val$receiver;

            
            {
                this$1 = final_servicebinder;
                callbacks = imediabrowserservicecallbacks;
                mediaId = s;
                receiver = ResultReceiver.this;
                super();
            }
            }
);
        }

        public void removeSubscription(final String id, IBinder ibinder, final IMediaBrowserServiceCallbacks callbacks)
        {
            MediaBrowserService._2D_get1(MediaBrowserService.this).post(ibinder. new Runnable() {

                public void run()
                {
                    Object obj = callbacks.asBinder();
                    obj = (ConnectionRecord)MediaBrowserService._2D_get0(_fld0).get(obj);
                    if(obj == null)
                    {
                        Log.w("MediaBrowserService", (new StringBuilder()).append("removeSubscription for callback that isn't registered id=").append(id).toString());
                        return;
                    }
                    if(!MediaBrowserService._2D_wrap1(_fld0, id, ((ConnectionRecord) (obj)), token))
                        Log.w("MediaBrowserService", (new StringBuilder()).append("removeSubscription called for ").append(id).append(" which is not subscribed").toString());
                }

                final ServiceBinder this$1;
                final IMediaBrowserServiceCallbacks val$callbacks;
                final String val$id;
                final IBinder val$token;

            
            {
                this$1 = final_servicebinder;
                callbacks = imediabrowserservicecallbacks;
                id = s;
                token = IBinder.this;
                super();
            }
            }
);
        }

        public void removeSubscriptionDeprecated(String s, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
        {
        }

        final MediaBrowserService this$0;

        private ServiceBinder()
        {
            this$0 = MediaBrowserService.this;
            super();
        }

        ServiceBinder(ServiceBinder servicebinder)
        {
            this();
        }
    }


    static ArrayMap _2D_get0(MediaBrowserService mediabrowserservice)
    {
        return mediabrowserservice.mConnections;
    }

    static Handler _2D_get1(MediaBrowserService mediabrowserservice)
    {
        return mediabrowserservice.mHandler;
    }

    static boolean _2D_wrap0(MediaBrowserService mediabrowserservice, String s, int i)
    {
        return mediabrowserservice.isValidPackage(s, i);
    }

    static boolean _2D_wrap1(MediaBrowserService mediabrowserservice, String s, ConnectionRecord connectionrecord, IBinder ibinder)
    {
        return mediabrowserservice.removeSubscription(s, connectionrecord, ibinder);
    }

    static List _2D_wrap2(MediaBrowserService mediabrowserservice, List list, Bundle bundle)
    {
        return mediabrowserservice.applyOptions(list, bundle);
    }

    static void _2D_wrap3(MediaBrowserService mediabrowserservice, String s, ConnectionRecord connectionrecord, IBinder ibinder, Bundle bundle)
    {
        mediabrowserservice.addSubscription(s, connectionrecord, ibinder, bundle);
    }

    static void _2D_wrap4(MediaBrowserService mediabrowserservice, String s, ConnectionRecord connectionrecord, Bundle bundle)
    {
        mediabrowserservice.performLoadChildren(s, connectionrecord, bundle);
    }

    static void _2D_wrap5(MediaBrowserService mediabrowserservice, String s, ConnectionRecord connectionrecord, ResultReceiver resultreceiver)
    {
        mediabrowserservice.performLoadItem(s, connectionrecord, resultreceiver);
    }

    public MediaBrowserService()
    {
    }

    private void addSubscription(String s, ConnectionRecord connectionrecord, IBinder ibinder, Bundle bundle)
    {
        List list = (List)connectionrecord.subscriptions.get(s);
        Object obj = list;
        if(list == null)
            obj = new ArrayList();
        for(Iterator iterator = ((Iterable) (obj)).iterator(); iterator.hasNext();)
        {
            Pair pair = (Pair)iterator.next();
            if(ibinder == pair.first && MediaBrowserUtils.areSameOptions(bundle, (Bundle)pair.second))
                return;
        }

        ((List) (obj)).add(new Pair(ibinder, bundle));
        connectionrecord.subscriptions.put(s, obj);
        performLoadChildren(s, connectionrecord, bundle);
    }

    private List applyOptions(List list, Bundle bundle)
    {
        if(list == null)
            return null;
        int i = bundle.getInt("android.media.browse.extra.PAGE", -1);
        int j = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if(i == -1 && j == -1)
            return list;
        int k = j * i;
        int l;
        for(l = k + j; i < 0 || j < 1 || k >= list.size();)
            return Collections.EMPTY_LIST;

        j = l;
        if(l > list.size())
            j = list.size();
        return list.subList(k, j);
    }

    private boolean isValidPackage(String s, int i)
    {
        if(s == null)
            return false;
        String as[] = getPackageManager().getPackagesForUid(i);
        int j = as.length;
        for(i = 0; i < j; i++)
            if(as[i].equals(s))
                return true;

        return false;
    }

    private void notifyChildrenChangedInternal(final String parentId, final Bundle options)
    {
        if(parentId == null)
        {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        } else
        {
            mHandler.post(new Runnable() {

                public void run()
                {
                    for(Iterator iterator = MediaBrowserService._2D_get0(MediaBrowserService.this).keySet().iterator(); iterator.hasNext();)
                    {
                        Object obj = (IBinder)iterator.next();
                        obj = (ConnectionRecord)MediaBrowserService._2D_get0(MediaBrowserService.this).get(obj);
                        Object obj1 = (List)((ConnectionRecord) (obj)).subscriptions.get(parentId);
                        if(obj1 != null)
                        {
                            obj1 = ((Iterable) (obj1)).iterator();
                            while(((Iterator) (obj1)).hasNext()) 
                            {
                                Pair pair = (Pair)((Iterator) (obj1)).next();
                                if(MediaBrowserUtils.hasDuplicatedItems(options, (Bundle)pair.second))
                                    MediaBrowserService._2D_wrap4(MediaBrowserService.this, parentId, ((ConnectionRecord) (obj)), (Bundle)pair.second);
                            }
                        }
                    }

                }

                final MediaBrowserService this$0;
                final Bundle val$options;
                final String val$parentId;

            
            {
                this$0 = MediaBrowserService.this;
                parentId = s;
                options = bundle;
                super();
            }
            }
);
            return;
        }
    }

    private void performLoadChildren(final String final_obj, final ConnectionRecord connection, Bundle bundle)
    {
        Result result = new Result(final_obj, bundle) {

            volatile void onResultSent(Object obj, int i)
            {
                onResultSent((List)obj, i);
            }

            void onResultSent(List list, int i)
            {
                if(MediaBrowserService._2D_get0(MediaBrowserService.this).get(connection.callbacks.asBinder()) != connection)
                    return;
                if((i & 1) != 0)
                    list = MediaBrowserService._2D_wrap2(MediaBrowserService.this, list, options);
                if(list == null)
                    list = null;
                else
                    list = new ParceledListSlice(list);
                connection.callbacks.onLoadChildrenWithOptions(parentId, list, options);
_L1:
                return;
                list;
                Log.w("MediaBrowserService", (new StringBuilder()).append("Calling onLoadChildren() failed for id=").append(parentId).append(" package=").append(connection.pkg).toString());
                  goto _L1
            }

            final MediaBrowserService this$0;
            final ConnectionRecord val$connection;
            final Bundle val$options;
            final String val$parentId;

            
            {
                this$0 = final_mediabrowserservice1;
                connection = connectionrecord;
                parentId = s;
                options = bundle;
                super(final_obj);
            }
        }
;
        mCurConnection = connection;
        if(bundle == null)
            onLoadChildren(final_obj, result);
        else
            onLoadChildren(final_obj, result, bundle);
        mCurConnection = null;
        if(!result.isDone())
            throw new IllegalStateException((new StringBuilder()).append("onLoadChildren must call detach() or sendResult() before returning for package=").append(connection.pkg).append(" id=").append(final_obj).toString());
        else
            return;
    }

    private void performLoadItem(final String final_obj, final ConnectionRecord connection, ResultReceiver resultreceiver)
    {
        resultreceiver = new Result(final_obj, resultreceiver) {

            void onResultSent(android.media.browse.MediaBrowser.MediaItem mediaitem, int i)
            {
                if(MediaBrowserService._2D_get0(MediaBrowserService.this).get(connection.callbacks.asBinder()) != connection)
                    return;
                if((i & 2) != 0)
                {
                    receiver.send(-1, null);
                    return;
                } else
                {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("media_item", mediaitem);
                    receiver.send(0, bundle);
                    return;
                }
            }

            volatile void onResultSent(Object obj, int i)
            {
                onResultSent((android.media.browse.MediaBrowser.MediaItem)obj, i);
            }

            final MediaBrowserService this$0;
            final ConnectionRecord val$connection;
            final String val$itemId;
            final ResultReceiver val$receiver;

            
            {
                this$0 = final_mediabrowserservice1;
                connection = connectionrecord;
                itemId = s;
                receiver = resultreceiver;
                super(final_obj);
            }
        }
;
        mCurConnection = connection;
        onLoadItem(final_obj, resultreceiver);
        mCurConnection = null;
        if(!resultreceiver.isDone())
            throw new IllegalStateException((new StringBuilder()).append("onLoadItem must call detach() or sendResult() before returning for id=").append(final_obj).toString());
        else
            return;
    }

    private boolean removeSubscription(String s, ConnectionRecord connectionrecord, IBinder ibinder)
    {
        boolean flag = false;
        if(ibinder == null)
        {
            if(connectionrecord.subscriptions.remove(s) != null)
                flag = true;
            return flag;
        }
        boolean flag1 = false;
        flag = false;
        List list = (List)connectionrecord.subscriptions.get(s);
        if(list != null)
        {
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                if(ibinder == ((Pair)iterator.next()).first)
                {
                    flag = true;
                    iterator.remove();
                }
            } while(true);
            flag1 = flag;
            if(list.size() == 0)
            {
                connectionrecord.subscriptions.remove(s);
                flag1 = flag;
            }
        }
        return flag1;
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
    }

    public final Bundle getBrowserRootHints()
    {
        Bundle bundle = null;
        if(mCurConnection == null)
            throw new IllegalStateException("This should be called inside of onLoadChildren or onLoadItem methods");
        if(mCurConnection.rootHints != null)
            bundle = new Bundle(mCurConnection.rootHints);
        return bundle;
    }

    public android.media.session.MediaSession.Token getSessionToken()
    {
        return mSession;
    }

    public void notifyChildrenChanged(String s)
    {
        notifyChildrenChangedInternal(s, null);
    }

    public void notifyChildrenChanged(String s, Bundle bundle)
    {
        if(bundle == null)
        {
            throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
        } else
        {
            notifyChildrenChangedInternal(s, bundle);
            return;
        }
    }

    public IBinder onBind(Intent intent)
    {
        if("android.media.browse.MediaBrowserService".equals(intent.getAction()))
            return mBinder;
        else
            return null;
    }

    public void onCreate()
    {
        super.onCreate();
        mBinder = new ServiceBinder(null);
    }

    public abstract BrowserRoot onGetRoot(String s, int i, Bundle bundle);

    public abstract void onLoadChildren(String s, Result result);

    public void onLoadChildren(String s, Result result, Bundle bundle)
    {
        result.setFlags(1);
        onLoadChildren(s, result);
    }

    public void onLoadItem(String s, Result result)
    {
        result.setFlags(2);
        result.sendResult(null);
    }

    public void setSessionToken(final android.media.session.MediaSession.Token token)
    {
        if(token == null)
            throw new IllegalArgumentException("Session token may not be null.");
        if(mSession != null)
        {
            throw new IllegalStateException("The session token has already been set.");
        } else
        {
            mSession = token;
            mHandler.post(new Runnable() {

                public void run()
                {
                    for(Iterator iterator = MediaBrowserService._2D_get0(MediaBrowserService.this).values().iterator(); iterator.hasNext();)
                    {
                        ConnectionRecord connectionrecord = (ConnectionRecord)iterator.next();
                        try
                        {
                            connectionrecord.callbacks.onConnect(connectionrecord.root.getRootId(), token, connectionrecord.root.getExtras());
                        }
                        catch(RemoteException remoteexception)
                        {
                            Log.w("MediaBrowserService", (new StringBuilder()).append("Connection for ").append(connectionrecord.pkg).append(" is no longer valid.").toString());
                            iterator.remove();
                        }
                    }

                }

                final MediaBrowserService this$0;
                final android.media.session.MediaSession.Token val$token;

            
            {
                this$0 = MediaBrowserService.this;
                token = token1;
                super();
            }
            }
);
            return;
        }
    }

    private static final boolean DBG = false;
    public static final String KEY_MEDIA_ITEM = "media_item";
    private static final int RESULT_ERROR = -1;
    private static final int RESULT_FLAG_ON_LOAD_ITEM_NOT_IMPLEMENTED = 2;
    private static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;
    private static final int RESULT_OK = 0;
    public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
    private static final String TAG = "MediaBrowserService";
    private ServiceBinder mBinder;
    private final ArrayMap mConnections = new ArrayMap();
    private ConnectionRecord mCurConnection;
    private final Handler mHandler = new Handler();
    android.media.session.MediaSession.Token mSession;
}
