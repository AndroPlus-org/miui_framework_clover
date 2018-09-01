// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Loader;
import android.os.Bundle;
import android.util.*;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

// Referenced classes of package android.app:
//            LoaderManager, FragmentHostCallback, FragmentManagerImpl

class LoaderManagerImpl extends LoaderManager
{
    final class LoaderInfo
        implements android.content.Loader.OnLoadCompleteListener, android.content.Loader.OnLoadCanceledListener
    {

        void callOnLoadFinished(Loader loader, Object obj)
        {
            String s;
            if(mCallbacks == null)
                break MISSING_BLOCK_LABEL_137;
            s = null;
            if(LoaderManagerImpl._2D_get0(LoaderManagerImpl.this) != null)
            {
                s = LoaderManagerImpl._2D_get0(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause;
                LoaderManagerImpl._2D_get0(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause = "onLoadFinished";
            }
            if(LoaderManagerImpl.DEBUG)
            {
                StringBuilder stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.v("LoaderManager", stringbuilder.append("  onLoadFinished in ").append(loader).append(": ").append(loader.dataToString(obj)).toString());
            }
            mCallbacks.onLoadFinished(loader, obj);
            if(LoaderManagerImpl._2D_get0(LoaderManagerImpl.this) != null)
                LoaderManagerImpl._2D_get0(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause = s;
            mDeliveredData = true;
            return;
            loader;
            if(LoaderManagerImpl._2D_get0(LoaderManagerImpl.this) != null)
                LoaderManagerImpl._2D_get0(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause = s;
            throw loader;
        }

        boolean cancel()
        {
            if(LoaderManagerImpl.DEBUG)
                Log.v("LoaderManager", (new StringBuilder()).append("  Canceling: ").append(this).toString());
            if(mStarted && mLoader != null && mListenerRegistered)
            {
                boolean flag = mLoader.cancelLoad();
                if(!flag)
                    onLoadCanceled(mLoader);
                return flag;
            } else
            {
                return false;
            }
        }

        void destroy()
        {
            String s;
            if(LoaderManagerImpl.DEBUG)
                Log.v("LoaderManager", (new StringBuilder()).append("  Destroying: ").append(this).toString());
            mDestroyed = true;
            boolean flag = mDeliveredData;
            mDeliveredData = false;
            if(mCallbacks == null || mLoader == null || !mHaveData || !flag)
                break MISSING_BLOCK_LABEL_180;
            if(LoaderManagerImpl.DEBUG)
                Log.v("LoaderManager", (new StringBuilder()).append("  Reseting: ").append(this).toString());
            s = null;
            if(LoaderManagerImpl._2D_get0(LoaderManagerImpl.this) != null)
            {
                s = LoaderManagerImpl._2D_get0(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause;
                LoaderManagerImpl._2D_get0(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause = "onLoaderReset";
            }
            mCallbacks.onLoaderReset(mLoader);
            if(LoaderManagerImpl._2D_get0(LoaderManagerImpl.this) != null)
                LoaderManagerImpl._2D_get0(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause = s;
            mCallbacks = null;
            mData = null;
            mHaveData = false;
            if(mLoader != null)
            {
                if(mListenerRegistered)
                {
                    mListenerRegistered = false;
                    mLoader.unregisterListener(this);
                    mLoader.unregisterOnLoadCanceledListener(this);
                }
                mLoader.reset();
            }
            if(mPendingLoader != null)
                mPendingLoader.destroy();
            return;
            Exception exception;
            exception;
            if(LoaderManagerImpl._2D_get0(LoaderManagerImpl.this) != null)
                LoaderManagerImpl._2D_get0(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause = s;
            throw exception;
        }

        public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            printwriter.print(s);
            printwriter.print("mId=");
            printwriter.print(mId);
            printwriter.print(" mArgs=");
            printwriter.println(mArgs);
            printwriter.print(s);
            printwriter.print("mCallbacks=");
            printwriter.println(mCallbacks);
            printwriter.print(s);
            printwriter.print("mLoader=");
            printwriter.println(mLoader);
            if(mLoader != null)
                mLoader.dump((new StringBuilder()).append(s).append("  ").toString(), filedescriptor, printwriter, as);
            if(mHaveData || mDeliveredData)
            {
                printwriter.print(s);
                printwriter.print("mHaveData=");
                printwriter.print(mHaveData);
                printwriter.print("  mDeliveredData=");
                printwriter.println(mDeliveredData);
                printwriter.print(s);
                printwriter.print("mData=");
                printwriter.println(mData);
            }
            printwriter.print(s);
            printwriter.print("mStarted=");
            printwriter.print(mStarted);
            printwriter.print(" mReportNextStart=");
            printwriter.print(mReportNextStart);
            printwriter.print(" mDestroyed=");
            printwriter.println(mDestroyed);
            printwriter.print(s);
            printwriter.print("mRetaining=");
            printwriter.print(mRetaining);
            printwriter.print(" mRetainingStarted=");
            printwriter.print(mRetainingStarted);
            printwriter.print(" mListenerRegistered=");
            printwriter.println(mListenerRegistered);
            if(mPendingLoader != null)
            {
                printwriter.print(s);
                printwriter.println("Pending Loader ");
                printwriter.print(mPendingLoader);
                printwriter.println(":");
                mPendingLoader.dump((new StringBuilder()).append(s).append("  ").toString(), filedescriptor, printwriter, as);
            }
        }

        void finishRetain()
        {
            if(mRetaining)
            {
                if(LoaderManagerImpl.DEBUG)
                    Log.v("LoaderManager", (new StringBuilder()).append("  Finished Retaining: ").append(this).toString());
                mRetaining = false;
                if(mStarted != mRetainingStarted && !mStarted)
                    stop();
            }
            if(mStarted && mHaveData && mReportNextStart ^ true)
                callOnLoadFinished(mLoader, mData);
        }

        public void onLoadCanceled(Loader loader)
        {
            if(LoaderManagerImpl.DEBUG)
                Log.v("LoaderManager", (new StringBuilder()).append("onLoadCanceled: ").append(this).toString());
            if(mDestroyed)
            {
                if(LoaderManagerImpl.DEBUG)
                    Log.v("LoaderManager", "  Ignoring load canceled -- destroyed");
                return;
            }
            if(mLoaders.get(mId) != this)
            {
                if(LoaderManagerImpl.DEBUG)
                    Log.v("LoaderManager", "  Ignoring load canceled -- not active");
                return;
            }
            loader = mPendingLoader;
            if(loader != null)
            {
                if(LoaderManagerImpl.DEBUG)
                    Log.v("LoaderManager", (new StringBuilder()).append("  Switching to pending loader: ").append(loader).toString());
                mPendingLoader = null;
                mLoaders.put(mId, null);
                destroy();
                installLoader(loader);
            }
        }

        public void onLoadComplete(Loader loader, Object obj)
        {
            if(LoaderManagerImpl.DEBUG)
                Log.v("LoaderManager", (new StringBuilder()).append("onLoadComplete: ").append(this).toString());
            if(mDestroyed)
            {
                if(LoaderManagerImpl.DEBUG)
                    Log.v("LoaderManager", "  Ignoring load complete -- destroyed");
                return;
            }
            if(mLoaders.get(mId) != this)
            {
                if(LoaderManagerImpl.DEBUG)
                    Log.v("LoaderManager", "  Ignoring load complete -- not active");
                return;
            }
            LoaderInfo loaderinfo = mPendingLoader;
            if(loaderinfo != null)
            {
                if(LoaderManagerImpl.DEBUG)
                    Log.v("LoaderManager", (new StringBuilder()).append("  Switching to pending loader: ").append(loaderinfo).toString());
                mPendingLoader = null;
                mLoaders.put(mId, null);
                destroy();
                installLoader(loaderinfo);
                return;
            }
            if(mData != obj || mHaveData ^ true)
            {
                mData = obj;
                mHaveData = true;
                if(mStarted)
                    callOnLoadFinished(loader, obj);
            }
            loader = (LoaderInfo)mInactiveLoaders.get(mId);
            if(loader != null && loader != this)
            {
                loader.mDeliveredData = false;
                loader.destroy();
                mInactiveLoaders.remove(mId);
            }
            if(LoaderManagerImpl._2D_get0(LoaderManagerImpl.this) != null && hasRunningLoaders() ^ true)
                LoaderManagerImpl._2D_get0(LoaderManagerImpl.this).mFragmentManager.startPendingDeferredFragments();
        }

        void reportStart()
        {
            if(mStarted && mReportNextStart)
            {
                mReportNextStart = false;
                if(mHaveData && mRetaining ^ true)
                    callOnLoadFinished(mLoader, mData);
            }
        }

        void retain()
        {
            if(LoaderManagerImpl.DEBUG)
                Log.v("LoaderManager", (new StringBuilder()).append("  Retaining: ").append(this).toString());
            mRetaining = true;
            mRetainingStarted = mStarted;
            mStarted = false;
            mCallbacks = null;
        }

        void start()
        {
            if(mRetaining && mRetainingStarted)
            {
                mStarted = true;
                return;
            }
            if(mStarted)
                return;
            mStarted = true;
            if(LoaderManagerImpl.DEBUG)
                Log.v("LoaderManager", (new StringBuilder()).append("  Starting: ").append(this).toString());
            if(mLoader == null && mCallbacks != null)
                mLoader = mCallbacks.onCreateLoader(mId, mArgs);
            if(mLoader != null)
            {
                if(mLoader.getClass().isMemberClass() && Modifier.isStatic(mLoader.getClass().getModifiers()) ^ true)
                    throw new IllegalArgumentException((new StringBuilder()).append("Object returned from onCreateLoader must not be a non-static inner member class: ").append(mLoader).toString());
                if(!mListenerRegistered)
                {
                    mLoader.registerListener(mId, this);
                    mLoader.registerOnLoadCanceledListener(this);
                    mListenerRegistered = true;
                }
                mLoader.startLoading();
            }
        }

        void stop()
        {
            if(LoaderManagerImpl.DEBUG)
                Log.v("LoaderManager", (new StringBuilder()).append("  Stopping: ").append(this).toString());
            mStarted = false;
            if(!mRetaining && mLoader != null && mListenerRegistered)
            {
                mListenerRegistered = false;
                mLoader.unregisterListener(this);
                mLoader.unregisterOnLoadCanceledListener(this);
                mLoader.stopLoading();
            }
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(64);
            stringbuilder.append("LoaderInfo{");
            stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringbuilder.append(" #");
            stringbuilder.append(mId);
            stringbuilder.append(" : ");
            DebugUtils.buildShortClassTag(mLoader, stringbuilder);
            stringbuilder.append("}}");
            return stringbuilder.toString();
        }

        final Bundle mArgs;
        LoaderManager.LoaderCallbacks mCallbacks;
        Object mData;
        boolean mDeliveredData;
        boolean mDestroyed;
        boolean mHaveData;
        final int mId;
        boolean mListenerRegistered;
        Loader mLoader;
        LoaderInfo mPendingLoader;
        boolean mReportNextStart;
        boolean mRetaining;
        boolean mRetainingStarted;
        boolean mStarted;
        final LoaderManagerImpl this$0;

        public LoaderInfo(int i, Bundle bundle, LoaderManager.LoaderCallbacks loadercallbacks)
        {
            this$0 = LoaderManagerImpl.this;
            super();
            mId = i;
            mArgs = bundle;
            mCallbacks = loadercallbacks;
        }
    }


    static FragmentHostCallback _2D_get0(LoaderManagerImpl loadermanagerimpl)
    {
        return loadermanagerimpl.mHost;
    }

    LoaderManagerImpl(String s, FragmentHostCallback fragmenthostcallback, boolean flag)
    {
        mWho = s;
        mHost = fragmenthostcallback;
        mStarted = flag;
    }

    private LoaderInfo createAndInstallLoader(int i, Bundle bundle, LoaderManager.LoaderCallbacks loadercallbacks)
    {
        mCreatingLoader = true;
        bundle = createLoader(i, bundle, loadercallbacks);
        installLoader(bundle);
        mCreatingLoader = false;
        return bundle;
        bundle;
        mCreatingLoader = false;
        throw bundle;
    }

    private LoaderInfo createLoader(int i, Bundle bundle, LoaderManager.LoaderCallbacks loadercallbacks)
    {
        LoaderInfo loaderinfo = new LoaderInfo(i, bundle, loadercallbacks);
        loaderinfo.mLoader = loadercallbacks.onCreateLoader(i, bundle);
        return loaderinfo;
    }

    public void destroyLoader(int i)
    {
        if(mCreatingLoader)
            throw new IllegalStateException("Called while creating a loader");
        if(DEBUG)
            Log.v("LoaderManager", (new StringBuilder()).append("destroyLoader in ").append(this).append(" of ").append(i).toString());
        int j = mLoaders.indexOfKey(i);
        if(j >= 0)
        {
            LoaderInfo loaderinfo = (LoaderInfo)mLoaders.valueAt(j);
            mLoaders.removeAt(j);
            loaderinfo.destroy();
        }
        i = mInactiveLoaders.indexOfKey(i);
        if(i >= 0)
        {
            LoaderInfo loaderinfo1 = (LoaderInfo)mInactiveLoaders.valueAt(i);
            mInactiveLoaders.removeAt(i);
            loaderinfo1.destroy();
        }
        if(mHost != null && hasRunningLoaders() ^ true)
            mHost.mFragmentManager.startPendingDeferredFragments();
    }

    void doDestroy()
    {
        if(!mRetaining)
        {
            if(DEBUG)
                Log.v("LoaderManager", (new StringBuilder()).append("Destroying Active in ").append(this).toString());
            for(int i = mLoaders.size() - 1; i >= 0; i--)
                ((LoaderInfo)mLoaders.valueAt(i)).destroy();

            mLoaders.clear();
        }
        if(DEBUG)
            Log.v("LoaderManager", (new StringBuilder()).append("Destroying Inactive in ").append(this).toString());
        for(int j = mInactiveLoaders.size() - 1; j >= 0; j--)
            ((LoaderInfo)mInactiveLoaders.valueAt(j)).destroy();

        mInactiveLoaders.clear();
        mHost = null;
    }

    void doReportNextStart()
    {
        for(int i = mLoaders.size() - 1; i >= 0; i--)
            ((LoaderInfo)mLoaders.valueAt(i)).mReportNextStart = true;

    }

    void doReportStart()
    {
        for(int i = mLoaders.size() - 1; i >= 0; i--)
            ((LoaderInfo)mLoaders.valueAt(i)).reportStart();

    }

    void doRetain()
    {
        if(DEBUG)
            Log.v("LoaderManager", (new StringBuilder()).append("Retaining in ").append(this).toString());
        if(!mStarted)
        {
            RuntimeException runtimeexception = new RuntimeException("here");
            runtimeexception.fillInStackTrace();
            Log.w("LoaderManager", (new StringBuilder()).append("Called doRetain when not started: ").append(this).toString(), runtimeexception);
            return;
        }
        mRetaining = true;
        mStarted = false;
        for(int i = mLoaders.size() - 1; i >= 0; i--)
            ((LoaderInfo)mLoaders.valueAt(i)).retain();

    }

    void doStart()
    {
        if(DEBUG)
            Log.v("LoaderManager", (new StringBuilder()).append("Starting in ").append(this).toString());
        if(mStarted)
        {
            RuntimeException runtimeexception = new RuntimeException("here");
            runtimeexception.fillInStackTrace();
            Log.w("LoaderManager", (new StringBuilder()).append("Called doStart when already started: ").append(this).toString(), runtimeexception);
            return;
        }
        mStarted = true;
        for(int i = mLoaders.size() - 1; i >= 0; i--)
            ((LoaderInfo)mLoaders.valueAt(i)).start();

    }

    void doStop()
    {
        if(DEBUG)
            Log.v("LoaderManager", (new StringBuilder()).append("Stopping in ").append(this).toString());
        if(!mStarted)
        {
            RuntimeException runtimeexception = new RuntimeException("here");
            runtimeexception.fillInStackTrace();
            Log.w("LoaderManager", (new StringBuilder()).append("Called doStop when not started: ").append(this).toString(), runtimeexception);
            return;
        }
        for(int i = mLoaders.size() - 1; i >= 0; i--)
            ((LoaderInfo)mLoaders.valueAt(i)).stop();

        mStarted = false;
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        if(mLoaders.size() > 0)
        {
            printwriter.print(s);
            printwriter.println("Active Loaders:");
            String s1 = (new StringBuilder()).append(s).append("    ").toString();
            for(int i = 0; i < mLoaders.size(); i++)
            {
                LoaderInfo loaderinfo = (LoaderInfo)mLoaders.valueAt(i);
                printwriter.print(s);
                printwriter.print("  #");
                printwriter.print(mLoaders.keyAt(i));
                printwriter.print(": ");
                printwriter.println(loaderinfo.toString());
                loaderinfo.dump(s1, filedescriptor, printwriter, as);
            }

        }
        if(mInactiveLoaders.size() > 0)
        {
            printwriter.print(s);
            printwriter.println("Inactive Loaders:");
            String s2 = (new StringBuilder()).append(s).append("    ").toString();
            for(int j = 0; j < mInactiveLoaders.size(); j++)
            {
                LoaderInfo loaderinfo1 = (LoaderInfo)mInactiveLoaders.valueAt(j);
                printwriter.print(s);
                printwriter.print("  #");
                printwriter.print(mInactiveLoaders.keyAt(j));
                printwriter.print(": ");
                printwriter.println(loaderinfo1.toString());
                loaderinfo1.dump(s2, filedescriptor, printwriter, as);
            }

        }
    }

    void finishRetain()
    {
        if(mRetaining)
        {
            if(DEBUG)
                Log.v("LoaderManager", (new StringBuilder()).append("Finished Retaining in ").append(this).toString());
            mRetaining = false;
            for(int i = mLoaders.size() - 1; i >= 0; i--)
                ((LoaderInfo)mLoaders.valueAt(i)).finishRetain();

        }
    }

    public FragmentHostCallback getFragmentHostCallback()
    {
        return mHost;
    }

    public Loader getLoader(int i)
    {
        if(mCreatingLoader)
            throw new IllegalStateException("Called while creating a loader");
        LoaderInfo loaderinfo = (LoaderInfo)mLoaders.get(i);
        if(loaderinfo != null)
        {
            if(loaderinfo.mPendingLoader != null)
                return loaderinfo.mPendingLoader.mLoader;
            else
                return loaderinfo.mLoader;
        } else
        {
            return null;
        }
    }

    public boolean hasRunningLoaders()
    {
        boolean flag = false;
        int i = mLoaders.size();
        int j = 0;
        while(j < i) 
        {
            LoaderInfo loaderinfo = (LoaderInfo)mLoaders.valueAt(j);
            boolean flag1;
            if(loaderinfo.mStarted)
                flag1 = loaderinfo.mDeliveredData ^ true;
            else
                flag1 = false;
            flag |= flag1;
            j++;
        }
        return flag;
    }

    public Loader initLoader(int i, Bundle bundle, LoaderManager.LoaderCallbacks loadercallbacks)
    {
        if(mCreatingLoader)
            throw new IllegalStateException("Called while creating a loader");
        LoaderInfo loaderinfo = (LoaderInfo)mLoaders.get(i);
        if(DEBUG)
            Log.v("LoaderManager", (new StringBuilder()).append("initLoader in ").append(this).append(": args=").append(bundle).toString());
        if(loaderinfo == null)
        {
            loadercallbacks = createAndInstallLoader(i, bundle, loadercallbacks);
            bundle = loadercallbacks;
            if(DEBUG)
            {
                Log.v("LoaderManager", (new StringBuilder()).append("  Created new loader ").append(loadercallbacks).toString());
                bundle = loadercallbacks;
            }
        } else
        {
            if(DEBUG)
                Log.v("LoaderManager", (new StringBuilder()).append("  Re-using existing loader ").append(loaderinfo).toString());
            loaderinfo.mCallbacks = loadercallbacks;
            bundle = loaderinfo;
        }
        if(((LoaderInfo) (bundle)).mHaveData && mStarted)
            bundle.callOnLoadFinished(((LoaderInfo) (bundle)).mLoader, ((LoaderInfo) (bundle)).mData);
        return ((LoaderInfo) (bundle)).mLoader;
    }

    void installLoader(LoaderInfo loaderinfo)
    {
        mLoaders.put(loaderinfo.mId, loaderinfo);
        if(mStarted)
            loaderinfo.start();
    }

    public Loader restartLoader(int i, Bundle bundle, LoaderManager.LoaderCallbacks loadercallbacks)
    {
        if(mCreatingLoader)
            throw new IllegalStateException("Called while creating a loader");
        LoaderInfo loaderinfo = (LoaderInfo)mLoaders.get(i);
        if(DEBUG)
            Log.v("LoaderManager", (new StringBuilder()).append("restartLoader in ").append(this).append(": args=").append(bundle).toString());
        if(loaderinfo != null)
        {
            LoaderInfo loaderinfo1 = (LoaderInfo)mInactiveLoaders.get(i);
            if(loaderinfo1 != null)
            {
                if(loaderinfo.mHaveData)
                {
                    if(DEBUG)
                        Log.v("LoaderManager", (new StringBuilder()).append("  Removing last inactive loader: ").append(loaderinfo).toString());
                    loaderinfo1.mDeliveredData = false;
                    loaderinfo1.destroy();
                    loaderinfo.mLoader.abandon();
                    mInactiveLoaders.put(i, loaderinfo);
                } else
                if(!loaderinfo.cancel())
                {
                    if(DEBUG)
                        Log.v("LoaderManager", "  Current loader is stopped; replacing");
                    mLoaders.put(i, null);
                    loaderinfo.destroy();
                } else
                {
                    if(DEBUG)
                        Log.v("LoaderManager", "  Current loader is running; configuring pending loader");
                    if(loaderinfo.mPendingLoader != null)
                    {
                        if(DEBUG)
                            Log.v("LoaderManager", (new StringBuilder()).append("  Removing pending loader: ").append(loaderinfo.mPendingLoader).toString());
                        loaderinfo.mPendingLoader.destroy();
                        loaderinfo.mPendingLoader = null;
                    }
                    if(DEBUG)
                        Log.v("LoaderManager", "  Enqueuing as new pending loader");
                    loaderinfo.mPendingLoader = createLoader(i, bundle, loadercallbacks);
                    return loaderinfo.mPendingLoader.mLoader;
                }
            } else
            {
                if(DEBUG)
                    Log.v("LoaderManager", (new StringBuilder()).append("  Making last loader inactive: ").append(loaderinfo).toString());
                loaderinfo.mLoader.abandon();
                mInactiveLoaders.put(i, loaderinfo);
            }
        }
        return createAndInstallLoader(i, bundle, loadercallbacks).mLoader;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("LoaderManager{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(" in ");
        DebugUtils.buildShortClassTag(mHost, stringbuilder);
        stringbuilder.append("}}");
        return stringbuilder.toString();
    }

    void updateHostController(FragmentHostCallback fragmenthostcallback)
    {
        mHost = fragmenthostcallback;
    }

    static boolean DEBUG = false;
    static final String TAG = "LoaderManager";
    boolean mCreatingLoader;
    private FragmentHostCallback mHost;
    final SparseArray mInactiveLoaders = new SparseArray(0);
    final SparseArray mLoaders = new SparseArray(0);
    boolean mRetaining;
    boolean mRetainingStarted;
    boolean mStarted;
    final String mWho;

    static 
    {
        DEBUG = false;
    }
}
