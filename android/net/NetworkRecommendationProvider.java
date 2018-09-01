// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Context;
import android.os.*;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.concurrent.Executor;

// Referenced classes of package android.net:
//            NetworkKey

public abstract class NetworkRecommendationProvider
{
    private final class ServiceWrapper extends INetworkRecommendationProvider.Stub
    {

        private void enforceCallingPermission()
        {
            if(mContext != null)
                mContext.enforceCallingOrSelfPermission("android.permission.REQUEST_NETWORK_SCORES", "Permission denied.");
        }

        private void execute(Runnable runnable)
        {
            if(mExecutor != null)
                mExecutor.execute(runnable);
            else
                mHandler.post(runnable);
        }

        public void requestScores(NetworkKey anetworkkey[])
            throws RemoteException
        {
            enforceCallingPermission();
            if(anetworkkey != null && anetworkkey.length > 0)
                execute(anetworkkey. new Runnable() {

                    public void run()
                    {
                        onRequestScores(networks);
                    }

                    final ServiceWrapper this$1;
                    final NetworkKey val$networks[];

            
            {
                this$1 = final_servicewrapper;
                networks = _5B_Landroid.net.NetworkKey_3B_.this;
                super();
            }
                }
);
        }

        private final Context mContext;
        private final Executor mExecutor;
        private final Handler mHandler = null;
        final NetworkRecommendationProvider this$0;

        ServiceWrapper(Context context, Executor executor)
        {
            this$0 = NetworkRecommendationProvider.this;
            super();
            mContext = context;
            mExecutor = executor;
        }
    }


    public NetworkRecommendationProvider(Context context, Executor executor)
    {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(executor);
        mService = new ServiceWrapper(context, executor);
    }

    public final IBinder getBinder()
    {
        return mService;
    }

    public abstract void onRequestScores(NetworkKey anetworkkey[]);

    private static final String TAG = "NetworkRecProvider";
    private static final boolean VERBOSE;
    private final IBinder mService;

    static 
    {
        boolean flag;
        if(Build.IS_DEBUGGABLE)
            flag = Log.isLoggable("NetworkRecProvider", 2);
        else
            flag = false;
        VERBOSE = flag;
    }
}
