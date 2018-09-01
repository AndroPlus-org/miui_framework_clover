// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.os.*;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

// Referenced classes of package android.app:
//            FragmentContainer, Activity, FragmentManagerImpl, LoaderManagerImpl, 
//            Fragment

public abstract class FragmentHostCallback extends FragmentContainer
{

    FragmentHostCallback(Activity activity)
    {
        this(activity, ((Context) (activity)), activity.mHandler, 0);
    }

    FragmentHostCallback(Activity activity, Context context, Handler handler, int i)
    {
        mFragmentManager = new FragmentManagerImpl();
        mActivity = activity;
        mContext = context;
        mHandler = handler;
        mWindowAnimations = i;
    }

    public FragmentHostCallback(Context context, Handler handler, int i)
    {
        Activity activity;
        if(context instanceof Activity)
            activity = (Activity)context;
        else
            activity = null;
        this(activity, context, chooseHandler(context, handler), i);
    }

    private static Handler chooseHandler(Context context, Handler handler)
    {
        if(handler == null && (context instanceof Activity))
            return ((Activity)context).mHandler;
        else
            return handler;
    }

    void doLoaderDestroy()
    {
        if(mLoaderManager == null)
        {
            return;
        } else
        {
            mLoaderManager.doDestroy();
            return;
        }
    }

    void doLoaderRetain()
    {
        if(mLoaderManager == null)
        {
            return;
        } else
        {
            mLoaderManager.doRetain();
            return;
        }
    }

    void doLoaderStart()
    {
        if(mLoadersStarted)
            return;
        mLoadersStarted = true;
        if(mLoaderManager == null) goto _L2; else goto _L1
_L1:
        mLoaderManager.doStart();
_L4:
        mCheckedForLoaderManager = true;
        return;
_L2:
        if(!mCheckedForLoaderManager)
            mLoaderManager = getLoaderManager("(root)", mLoadersStarted, false);
        if(true) goto _L4; else goto _L3
_L3:
    }

    void doLoaderStop(boolean flag)
    {
        mRetainLoaders = flag;
        if(mLoaderManager == null)
            return;
        if(!mLoadersStarted)
            return;
        mLoadersStarted = false;
        if(flag)
            mLoaderManager.doRetain();
        else
            mLoaderManager.doStop();
    }

    void dumpLoaders(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.print(s);
        printwriter.print("mLoadersStarted=");
        printwriter.println(mLoadersStarted);
        if(mLoaderManager != null)
        {
            printwriter.print(s);
            printwriter.print("Loader Manager ");
            printwriter.print(Integer.toHexString(System.identityHashCode(mLoaderManager)));
            printwriter.println(":");
            mLoaderManager.dump((new StringBuilder()).append(s).append("  ").toString(), filedescriptor, printwriter, as);
        }
    }

    Activity getActivity()
    {
        return mActivity;
    }

    Context getContext()
    {
        return mContext;
    }

    FragmentManagerImpl getFragmentManagerImpl()
    {
        return mFragmentManager;
    }

    Handler getHandler()
    {
        return mHandler;
    }

    LoaderManagerImpl getLoaderManager(String s, boolean flag, boolean flag1)
    {
        LoaderManagerImpl loadermanagerimpl;
        if(mAllLoaderManagers == null)
            mAllLoaderManagers = new ArrayMap();
        loadermanagerimpl = (LoaderManagerImpl)mAllLoaderManagers.get(s);
        if(loadermanagerimpl != null || !flag1) goto _L2; else goto _L1
_L1:
        loadermanagerimpl = new LoaderManagerImpl(s, this, flag);
        mAllLoaderManagers.put(s, loadermanagerimpl);
        s = loadermanagerimpl;
_L4:
        return s;
_L2:
        s = loadermanagerimpl;
        if(flag)
        {
            s = loadermanagerimpl;
            if(loadermanagerimpl != null)
            {
                s = loadermanagerimpl;
                if(loadermanagerimpl.mStarted ^ true)
                {
                    loadermanagerimpl.doStart();
                    s = loadermanagerimpl;
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    LoaderManagerImpl getLoaderManagerImpl()
    {
        if(mLoaderManager != null)
        {
            return mLoaderManager;
        } else
        {
            mCheckedForLoaderManager = true;
            mLoaderManager = getLoaderManager("(root)", mLoadersStarted, true);
            return mLoaderManager;
        }
    }

    boolean getRetainLoaders()
    {
        return mRetainLoaders;
    }

    void inactivateFragment(String s)
    {
        if(mAllLoaderManagers != null)
        {
            LoaderManagerImpl loadermanagerimpl = (LoaderManagerImpl)mAllLoaderManagers.get(s);
            if(loadermanagerimpl != null && loadermanagerimpl.mRetaining ^ true)
            {
                loadermanagerimpl.doDestroy();
                mAllLoaderManagers.remove(s);
            }
        }
    }

    public void onAttachFragment(Fragment fragment)
    {
    }

    public void onDump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
    }

    public View onFindViewById(int i)
    {
        return null;
    }

    public abstract Object onGetHost();

    public LayoutInflater onGetLayoutInflater()
    {
        return (LayoutInflater)mContext.getSystemService("layout_inflater");
    }

    public int onGetWindowAnimations()
    {
        return mWindowAnimations;
    }

    public boolean onHasView()
    {
        return true;
    }

    public boolean onHasWindowAnimations()
    {
        return true;
    }

    public void onInvalidateOptionsMenu()
    {
    }

    public void onRequestPermissionsFromFragment(Fragment fragment, String as[], int i)
    {
    }

    public boolean onShouldSaveFragmentState(Fragment fragment)
    {
        return true;
    }

    public void onStartActivityAsUserFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle, UserHandle userhandle)
    {
        if(i != -1)
        {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        } else
        {
            mContext.startActivityAsUser(intent, userhandle);
            return;
        }
    }

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle)
    {
        if(i != -1)
        {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        } else
        {
            mContext.startActivity(intent);
            return;
        }
    }

    public void onStartIntentSenderFromFragment(Fragment fragment, IntentSender intentsender, int i, Intent intent, int j, int k, int l, 
            Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        if(i != -1)
        {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        } else
        {
            mContext.startIntentSender(intentsender, intent, j, k, l, bundle);
            return;
        }
    }

    public boolean onUseFragmentManagerInflaterFactory()
    {
        return false;
    }

    void reportLoaderStart()
    {
        if(mAllLoaderManagers != null)
        {
            int i = mAllLoaderManagers.size();
            LoaderManagerImpl aloadermanagerimpl[] = new LoaderManagerImpl[i];
            for(int j = i - 1; j >= 0; j--)
                aloadermanagerimpl[j] = (LoaderManagerImpl)mAllLoaderManagers.valueAt(j);

            for(int k = 0; k < i; k++)
            {
                LoaderManagerImpl loadermanagerimpl = aloadermanagerimpl[k];
                loadermanagerimpl.finishRetain();
                loadermanagerimpl.doReportStart();
            }

        }
    }

    void restoreLoaderNonConfig(ArrayMap arraymap)
    {
        if(arraymap != null)
        {
            int i = 0;
            for(int j = arraymap.size(); i < j; i++)
                ((LoaderManagerImpl)arraymap.valueAt(i)).updateHostController(this);

        }
        mAllLoaderManagers = arraymap;
    }

    ArrayMap retainLoaderNonConfig()
    {
        boolean flag = false;
        int i = 0;
        if(mAllLoaderManagers != null)
        {
            int j = mAllLoaderManagers.size();
            LoaderManagerImpl aloadermanagerimpl[] = new LoaderManagerImpl[j];
            for(int k = j - 1; k >= 0; k--)
                aloadermanagerimpl[k] = (LoaderManagerImpl)mAllLoaderManagers.valueAt(k);

            boolean flag2 = getRetainLoaders();
            flag = false;
            boolean flag1 = i;
            i = ((flag) ? 1 : 0);
            do
            {
                flag = flag1;
                if(i >= j)
                    break;
                LoaderManagerImpl loadermanagerimpl = aloadermanagerimpl[i];
                if(!loadermanagerimpl.mRetaining && flag2)
                {
                    if(!loadermanagerimpl.mStarted)
                        loadermanagerimpl.doStart();
                    loadermanagerimpl.doRetain();
                }
                if(loadermanagerimpl.mRetaining)
                {
                    flag1 = true;
                } else
                {
                    loadermanagerimpl.doDestroy();
                    mAllLoaderManagers.remove(loadermanagerimpl.mWho);
                }
                i++;
            } while(true);
        }
        if(flag)
            return mAllLoaderManagers;
        else
            return null;
    }

    private final Activity mActivity;
    private ArrayMap mAllLoaderManagers;
    private boolean mCheckedForLoaderManager;
    final Context mContext;
    final FragmentManagerImpl mFragmentManager;
    private final Handler mHandler;
    private LoaderManagerImpl mLoaderManager;
    private boolean mLoadersStarted;
    private boolean mRetainLoaders;
    final int mWindowAnimations;
}
