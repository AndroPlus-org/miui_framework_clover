// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.app.*;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.*;
import android.util.SparseArray;
import android.view.*;
import com.android.internal.util.ArrayUtils;

// Referenced classes of package android.webkit:
//            WebViewFactory, LegacyErrorStrings, IWebViewUpdateService

public final class WebViewDelegate
{
    public static interface OnTraceEnabledChangeListener
    {

        public abstract void onTraceEnabledChange(boolean flag);
    }


    WebViewDelegate()
    {
    }

    public void addWebViewAssetPath(Context context)
    {
        String s = WebViewFactory.getLoadedPackageInfo().applicationInfo.sourceDir;
        context = context.getApplicationInfo();
        String as[] = ((ApplicationInfo) (context)).sharedLibraryFiles;
        if(!ArrayUtils.contains(as, s))
        {
            int i;
            String as1[];
            if(as != null)
                i = as.length;
            else
                i = 0;
            as1 = new String[++i];
            if(as != null)
                System.arraycopy(as, 0, as1, 0, as.length);
            as1[i - 1] = s;
            context.sharedLibraryFiles = as1;
            ResourcesManager.getInstance().appendLibAssetForMainAssetPath(context.getBaseResourcePath(), s);
        }
    }

    public void callDrawGlFunction(Canvas canvas, long l)
    {
        if(!(canvas instanceof DisplayListCanvas))
        {
            throw new IllegalArgumentException((new StringBuilder()).append(canvas.getClass().getName()).append(" is not a DisplayList canvas").toString());
        } else
        {
            ((DisplayListCanvas)canvas).drawGLFunctor2(l, null);
            return;
        }
    }

    public void callDrawGlFunction(Canvas canvas, long l, Runnable runnable)
    {
        if(!(canvas instanceof DisplayListCanvas))
        {
            throw new IllegalArgumentException((new StringBuilder()).append(canvas.getClass().getName()).append(" is not a DisplayList canvas").toString());
        } else
        {
            ((DisplayListCanvas)canvas).drawGLFunctor2(l, runnable);
            return;
        }
    }

    public boolean canInvokeDrawGlFunctor(View view)
    {
        return true;
    }

    public void detachDrawGlFunctor(View view, long l)
    {
        view = view.getViewRootImpl();
        if(l != 0L && view != null)
            view.detachFunctor(l);
    }

    public Application getApplication()
    {
        return ActivityThread.currentApplication();
    }

    public String getErrorString(Context context, int i)
    {
        return LegacyErrorStrings.getString(i, context);
    }

    public int getPackageId(Resources resources, String s)
    {
        resources = resources.getAssets().getAssignedPackageIdentifiers();
        for(int i = 0; i < resources.size(); i++)
            if(s.equals((String)resources.valueAt(i)))
                return resources.keyAt(i);

        throw new RuntimeException((new StringBuilder()).append("Package not found: ").append(s).toString());
    }

    public void invokeDrawGlFunctor(View view, long l, boolean flag)
    {
        ViewRootImpl.invokeFunctor(l, flag);
    }

    public boolean isMultiProcessEnabled()
    {
        boolean flag;
        try
        {
            flag = WebViewFactory.getUpdateService().isMultiProcessEnabled();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isTraceTagEnabled()
    {
        return Trace.isTagEnabled(16L);
    }

    public void setOnTraceEnabledChangeListener(final OnTraceEnabledChangeListener listener)
    {
        SystemProperties.addChangeCallback(new Runnable() {

            public void run()
            {
                listener.onTraceEnabledChange(isTraceTagEnabled());
            }

            final WebViewDelegate this$0;
            final OnTraceEnabledChangeListener val$listener;

            
            {
                this$0 = WebViewDelegate.this;
                listener = ontraceenabledchangelistener;
                super();
            }
        }
);
    }
}
