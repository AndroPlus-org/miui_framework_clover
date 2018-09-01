// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.egret.plugin.mi.java.egretruntimelauncher;

import android.util.Log;
import java.io.File;
import org.egret.plugin.mi.android.util.launcher.*;

// Referenced classes of package org.egret.plugin.mi.java.egretruntimelauncher:
//            Library

public class EgretRuntimeLibrary
    implements Runnable
{
    public static interface OnDownloadListener
    {

        public abstract void onError(String s);

        public abstract void onProgress(int i, int j);

        public abstract void onSuccess();
    }


    static OnDownloadListener _2D_get0(EgretRuntimeLibrary egretruntimelibrary)
    {
        return egretruntimelibrary.downloadListener;
    }

    static boolean _2D_get1(EgretRuntimeLibrary egretruntimelibrary)
    {
        return egretruntimelibrary.isCancelling;
    }

    static Library _2D_get2(EgretRuntimeLibrary egretruntimelibrary)
    {
        return egretruntimelibrary.library;
    }

    static boolean _2D_wrap0(EgretRuntimeLibrary egretruntimelibrary)
    {
        return egretruntimelibrary.doMove();
    }

    static void _2D_wrap1(EgretRuntimeLibrary egretruntimelibrary)
    {
        egretruntimelibrary.doUnzip();
    }

    public EgretRuntimeLibrary(Library library1, File file, File file1, File file2)
    {
        library = library1;
        root = file;
        cacheRoot = file1;
        sdRoot = file2;
    }

    private void doDownload()
    {
        File file;
        if(sdRoot != null)
            file = sdRoot;
        else
            file = cacheRoot;
        file = new File(file, library.getZipName());
        (new NetClass()).writeResponseToFile(library.getUrl(), file, new org.egret.plugin.mi.android.util.launcher.NetClass.OnNetListener() {

            public void onError(String s)
            {
                EgretRuntimeLibrary._2D_get0(EgretRuntimeLibrary.this).onError(s);
            }

            public void onProgress(int i, int j)
            {
                EgretRuntimeLibrary._2D_get0(EgretRuntimeLibrary.this).onProgress(i, j);
            }

            public void onSuccess(String s)
            {
                if(EgretRuntimeLibrary._2D_get1(EgretRuntimeLibrary.this))
                    return;
                if(EgretRuntimeLibrary._2D_wrap0(EgretRuntimeLibrary.this))
                    EgretRuntimeLibrary._2D_wrap1(EgretRuntimeLibrary.this);
            }

            final EgretRuntimeLibrary this$0;

            
            {
                this$0 = EgretRuntimeLibrary.this;
                super();
            }
        }
);
    }

    private boolean doMove()
    {
        if(isCancelling)
        {
            downloadListener.onError("thread is cancelling");
            return false;
        }
        if(sdRoot != null && FileUtil.Copy(new File(sdRoot, library.getZipName()), new File(cacheRoot, library.getZipName())) ^ true)
        {
            downloadListener.onError("copy file error");
            return false;
        } else
        {
            return true;
        }
    }

    private void doUnzip()
    {
        if(isCancelling)
            downloadListener.onError("thread is cancelling");
        final File cache = new File(cacheRoot, library.getZipName());
        final File target = new File(root, library.getLibraryName());
        if(!Md5Util.checkMd5(cache, library.getZipCheckSum()))
            downloadListener.onError("cache file md5 error");
        (new ZipClass()).unzip(cache, root, new org.egret.plugin.mi.android.util.launcher.ZipClass.OnZipListener() {

            public void onError(String s)
            {
                EgretRuntimeLibrary._2D_get0(EgretRuntimeLibrary.this).onError((new StringBuilder()).append("fail to unzip file: ").append(cache.getAbsolutePath()).toString());
            }

            public void onFileProgress(int i, int j)
            {
            }

            public void onProgress(int i, int j)
            {
            }

            public void onSuccess()
            {
                Log.i("EgretRuntimeLibrary", (new StringBuilder()).append("Success to unzip file: ").append(cache.getAbsolutePath()).toString());
                if(!cache.delete())
                    Log.e("EgretRuntimeLibrary", (new StringBuilder()).append("Fail to delete file: ").append(cache.getAbsolutePath()).toString());
                if(!Md5Util.checkMd5(target, EgretRuntimeLibrary._2D_get2(EgretRuntimeLibrary.this).getLibraryCheckSum()))
                {
                    EgretRuntimeLibrary._2D_get0(EgretRuntimeLibrary.this).onError("target file md5 error");
                    return;
                } else
                {
                    EgretRuntimeLibrary._2D_get0(EgretRuntimeLibrary.this).onSuccess();
                    return;
                }
            }

            final EgretRuntimeLibrary this$0;
            final File val$cache;
            final File val$target;

            
            {
                this$0 = EgretRuntimeLibrary.this;
                cache = file;
                target = file1;
                super();
            }
        }
);
    }

    public void download(OnDownloadListener ondownloadlistener)
    {
        while(library == null || root == null || cacheRoot == null || ondownloadlistener == null) 
        {
            ondownloadlistener.onError("libray, root, cacheRoot, listener may be null");
            return;
        }
        downloadListener = ondownloadlistener;
    }

    public void run()
    {
        isCancelling = false;
        doDownload();
    }

    public void stop()
    {
        isCancelling = true;
    }

    protected static final String TAG = "EgretRuntimeLibrary";
    private File cacheRoot;
    private OnDownloadListener downloadListener;
    private volatile boolean isCancelling;
    private Library library;
    private File root;
    private File sdRoot;
}
