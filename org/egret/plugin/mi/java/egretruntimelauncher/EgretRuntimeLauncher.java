// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.egret.plugin.mi.java.egretruntimelauncher;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.egret.plugin.mi.android.util.launcher.*;

// Referenced classes of package org.egret.plugin.mi.java.egretruntimelauncher:
//            EgretRuntimeVersion, Library, EgretRuntimeLoader, EgretRuntimeLibrary

public class EgretRuntimeLauncher
{
    public static interface EgretRuntimeDownloadListener
    {

        public abstract void onError(String s);

        public abstract void onProgress(String s, int i, int j);

        public abstract void onProgressTotal(int i, int j);

        public abstract void onSuccess(Class class1);
    }

    public class GameEngineMethod
    {

        public static final String CALL_EGRET_INTERFACE = "callEgretInterface";
        public static final String ENABLE_EGRET_RUNTIME_INTERFACE = "enableEgretRuntimeInterface";
        public static final String GAME_ENGINE_GET_VIEW = "game_engine_get_view";
        public static final String GAME_ENGINE_INIT = "game_engine_init";
        public static final String GAME_ENGINE_ON_PAUSE = "game_engine_onPause";
        public static final String GAME_ENGINE_ON_RESUME = "game_engine_onResume";
        public static final String GAME_ENGINE_ON_STOP = "game_engine_onStop";
        public static final String GAME_ENGINE_SET_LOADING_VIEW = "game_engine_set_loading_view";
        public static final String GAME_ENGINE_SET_OPTIONS = "game_engine_set_options";
        public static final String SET_RUNTIME_INTERFACE_SET = "setRuntimeInterfaceSet";
        final EgretRuntimeLauncher this$0;

        public GameEngineMethod()
        {
            this$0 = EgretRuntimeLauncher.this;
            super();
        }
    }


    static int _2D_get0(EgretRuntimeLauncher egretruntimelauncher)
    {
        return egretruntimelauncher.downLoadSum;
    }

    static EgretRuntimeDownloadListener _2D_get1(EgretRuntimeLauncher egretruntimelauncher)
    {
        return egretruntimelauncher.downloadListener;
    }

    static int _2D_get2(EgretRuntimeLauncher egretruntimelauncher)
    {
        return egretruntimelauncher.fileSizeSum;
    }

    static ConcurrentHashMap _2D_get3(EgretRuntimeLauncher egretruntimelauncher)
    {
        return egretruntimelauncher.mapFileSize;
    }

    static String _2D_get4(EgretRuntimeLauncher egretruntimelauncher)
    {
        return egretruntimelauncher.runtimeVersionUrl;
    }

    static void _2D_wrap0(EgretRuntimeLauncher egretruntimelauncher, String s)
    {
        egretruntimelauncher.handleError(s);
    }

    static void _2D_wrap1(EgretRuntimeLauncher egretruntimelauncher, String s)
    {
        egretruntimelauncher.parseRuntimeVersion(s);
    }

    static void _2D_wrap2(EgretRuntimeLauncher egretruntimelauncher)
    {
        egretruntimelauncher.updateDownLoadSum();
    }

    static void _2D_wrap3(EgretRuntimeLauncher egretruntimelauncher)
    {
        egretruntimelauncher.updated();
    }

    public EgretRuntimeLauncher(Context context, String s)
    {
        Object obj = null;
        super();
        mapFileSize = new ConcurrentHashMap();
        runtimeVersion = new EgretRuntimeVersion();
        downloaderList = new ArrayList();
        mainHandler = new Handler(context.getMainLooper());
        runtimeVersionUrl = "http://runtime.egret-labs.org/runtime.php";
        context = obj;
        if(s != null)
            context = new File(s);
        libraryRoot = context;
        cacheRoot = new File(s, "update");
        sdRoot = getSdRoot();
        cacheRoot.mkdirs();
    }

    public EgretRuntimeLauncher(Context context, String s, String s1, String s2, int i)
    {
        this(context, s);
        urlData = (new StringBuilder()).append("?appId=").append(s1).append("&appKey=").append(s2).toString();
        if(i > 0)
            urlData = (new StringBuilder()).append(urlData).append("&dev=").append(i).toString();
        runtimeVersionUrl = (new StringBuilder()).append(runtimeVersionUrl).append(urlData).toString();
    }

    private boolean checkCache(Library library)
    {
        if(!checkZipInRoot(library, cacheRoot))
            return false;
        library = new File(cacheRoot, library.getZipName());
        if(!(new ZipClass()).unzip(library, libraryRoot))
        {
            Log.e("EgretRuntimeLauncher", (new StringBuilder()).append("fail to unzip ").append(library.getAbsolutePath()).toString());
            return false;
        }
        if(!library.delete())
        {
            Log.e("EgretRuntimeLauncher", (new StringBuilder()).append("fail to delete ").append(library.getAbsolutePath()).toString());
            return false;
        } else
        {
            return true;
        }
    }

    private boolean checkLocal(Library library)
    {
        return isLatest(new File(libraryRoot, library.getLibraryName()), library.getLibraryCheckSum());
    }

    private boolean checkSd(Library library)
    {
        if(!checkZipInRoot(library, sdRoot))
            return false;
        File file = new File(cacheRoot, library.getZipName());
        if(!FileUtil.Copy(new File(sdRoot, library.getZipName()), file))
            return false;
        else
            return checkCache(library);
    }

    private boolean checkZipInRoot(Library library, File file)
    {
        return isLatest(new File(file, library.getZipName()), library.getZipCheckSum());
    }

    private void fetchRemoteVersion()
    {
        ExecutorLab.getInstance().addTask(new Thread(new Runnable() {

            public void run()
            {
                (new NetClass()).getRequest(EgretRuntimeLauncher._2D_get4(EgretRuntimeLauncher.this), new org.egret.plugin.mi.android.util.launcher.NetClass.OnNetListener() {

                    public void onError(String s)
                    {
                        EgretRuntimeLauncher._2D_wrap0(_fld0, s);
                    }

                    public void onProgress(int i, int j)
                    {
                    }

                    public void onSuccess(String s)
                    {
                        if(s == null)
                        {
                            EgretRuntimeLauncher._2D_wrap0(_fld0, "response content is null");
                            return;
                        } else
                        {
                            EgretRuntimeLauncher._2D_wrap1(_fld0, s);
                            return;
                        }
                    }

                    final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                }
);
            }

            final EgretRuntimeLauncher this$0;

            
            {
                this$0 = EgretRuntimeLauncher.this;
                super();
            }
        }
));
    }

    private int getFileSize(String s)
    {
        boolean flag;
        int i;
        int j;
        int k;
        flag = false;
        i = 0;
        j = i;
        k = ((flag) ? 1 : 0);
        URL url = JVM INSTR new #264 <Class URL>;
        j = i;
        k = ((flag) ? 1 : 0);
        url.URL(s);
        j = i;
        k = ((flag) ? 1 : 0);
        s = (HttpURLConnection)url.openConnection();
        j = i;
        k = ((flag) ? 1 : 0);
        i = s.getContentLength();
        j = i;
        k = i;
        s.disconnect();
        k = i;
_L2:
        return k;
        s;
        s.printStackTrace();
        k = j;
        continue; /* Loop/switch isn't completed */
        s;
        s.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    private ArrayList getNeedUpdateLibraryList()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = runtimeVersion.getLibraryList().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Library library = (Library)iterator.next();
            if(!checkLocal(library) && !checkCache(library) && !checkSd(library))
                arraylist.add(library);
        } while(true);
        return arraylist;
    }

    private File getSdRoot()
    {
        if("mounted".equals(Environment.getExternalStorageState()))
        {
            File file = new File(Environment.getExternalStorageDirectory(), "egret/runtime");
            if(file.exists() || file.mkdirs())
                return file;
        }
        return null;
    }

    private void handleError(String s)
    {
        Object obj = FileUtil.readFile(new File(libraryRoot, "egret.json"));
        if(obj == null)
        {
            downloadListener.onError(s);
            ExecutorLab.releaseInstance();
            return;
        }
        runtimeVersion.fromString(((String) (obj)));
        obj = runtimeVersion.getLibraryList();
        if(obj == null)
            return;
        obj = ((Iterable) (obj)).iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            Library library = (Library)((Iterator) (obj)).next();
            if(!checkLocal(library))
            {
                downloadListener.onError(s);
                ExecutorLab.releaseInstance();
                return;
            }
            if(!EgretRuntimeLoader.get().isLoaded())
                EgretRuntimeLoader.get().load((new File(libraryRoot, library.getLibraryName())).getAbsolutePath());
        } while(true);
        notifyLoadHandler();
    }

    private boolean isLatest(File file, String s)
    {
        if(DEBUG_RUNTIME_DOWNLOAD > 0)
            return false;
        if(!file.exists())
            return false;
        if(Md5Util.checkMd5(file, s))
            return true;
        if(!file.delete())
        {
            handleError((new StringBuilder()).append("Fail to delete file: ").append(file.getAbsolutePath()).toString());
            ExecutorLab.releaseInstance();
        }
        return false;
    }

    private void loadLibrary()
    {
        if(!EgretRuntimeLoader.get().isLoaded())
        {
            Library library;
            for(Iterator iterator = runtimeVersion.getLibraryList().iterator(); iterator.hasNext(); EgretRuntimeLoader.get().load((new File(libraryRoot, library.getLibraryName())).getAbsolutePath()))
                library = (Library)iterator.next();

        }
        notifyLoadHandler();
    }

    private void notifyLoadHandler()
    {
        Runnable runnable = new Runnable() {

            public void run()
            {
                Class class1 = EgretRuntimeLoader.get().getEgretGameEngineClass();
                if(class1 == null)
                {
                    EgretRuntimeLauncher._2D_get1(EgretRuntimeLauncher.this).onError("fails to new game engine");
                    ExecutorLab.releaseInstance();
                    return;
                } else
                {
                    EgretRuntimeLauncher._2D_get1(EgretRuntimeLauncher.this).onSuccess(class1);
                    return;
                }
            }

            final EgretRuntimeLauncher this$0;

            
            {
                this$0 = EgretRuntimeLauncher.this;
                super();
            }
        }
;
        mainHandler.post(runnable);
    }

    private void parseRuntimeVersion(String s)
    {
        runtimeVersion.fromString(s);
        FileUtil.writeFile(new File(libraryRoot, "egret.json"), s);
        updateLibrary();
    }

    private void updateDownLoadSum()
    {
        this;
        JVM INSTR monitorenter ;
        downLoadSum = 0;
        for(Iterator iterator = mapFileSize.entrySet().iterator(); iterator.hasNext();)
        {
            Object obj = (java.util.Map.Entry)iterator.next();
            String s = (String)((java.util.Map.Entry) (obj)).getKey();
            obj = (Integer)((java.util.Map.Entry) (obj)).getValue();
            StringBuilder stringbuilder1 = JVM INSTR new #160 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            Log.d("", stringbuilder1.append("rt zipUrl progress key = ").append(s).append(" value = ").append(obj).toString());
            downLoadSum = downLoadSum + ((Integer) (obj)).intValue();
        }

        break MISSING_BLOCK_LABEL_122;
        Exception exception;
        exception;
        throw exception;
        StringBuilder stringbuilder = JVM INSTR new #160 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.d("", stringbuilder.append("rt zipUrl progress downLoadSum = ").append(downLoadSum).toString());
        this;
        JVM INSTR monitorexit ;
    }

    private void updateLibrary()
    {
        updatedNumber = 0;
        Object obj = getNeedUpdateLibraryList();
        if(((ArrayList) (obj)).size() == 0)
            updated();
        Log.d("EgretRuntimeLauncher", (new StringBuilder()).append("rt libraryList size: ").append(String.valueOf(((ArrayList) (obj)).size())).toString());
        int i = 0;
        for(Iterator iterator = ((Iterable) (obj)).iterator(); iterator.hasNext();)
            i += getFileSize(((Library)iterator.next()).getUrl());

        fileSizeSum = i;
        for(Iterator iterator1 = ((Iterable) (obj)).iterator(); iterator1.hasNext(); ExecutorLab.getInstance().addTask(((Runnable) (obj))))
        {
            final Library library = (Library)iterator1.next();
            obj = new EgretRuntimeLibrary(library, libraryRoot, cacheRoot, sdRoot);
            ((EgretRuntimeLibrary) (obj)).download(new EgretRuntimeLibrary.OnDownloadListener() {

                public void onError(String s)
                {
                    EgretRuntimeLauncher._2D_wrap0(EgretRuntimeLauncher.this, (new StringBuilder()).append("Fail to download file: ").append(library.getZipName()).append(" detail: ").append(s).toString());
                    ExecutorLab.releaseInstance();
                }

                public void onProgress(int j, int k)
                {
                    EgretRuntimeLauncher._2D_get3(EgretRuntimeLauncher.this).put(library.getZipName(), Integer.valueOf(j));
                    EgretRuntimeLauncher._2D_wrap2(EgretRuntimeLauncher.this);
                    EgretRuntimeLauncher._2D_get1(EgretRuntimeLauncher.this).onProgressTotal(EgretRuntimeLauncher._2D_get0(EgretRuntimeLauncher.this), EgretRuntimeLauncher._2D_get2(EgretRuntimeLauncher.this));
                }

                public void onSuccess()
                {
                    EgretRuntimeLauncher egretruntimelauncher = EgretRuntimeLauncher.this;
                    egretruntimelauncher.updatedNumber = egretruntimelauncher.updatedNumber + 1;
                    EgretRuntimeLauncher._2D_wrap3(EgretRuntimeLauncher.this);
                }

                final EgretRuntimeLauncher this$0;
                final Library val$library;

            
            {
                this$0 = EgretRuntimeLauncher.this;
                library = library1;
                super();
            }
            }
);
            downloaderList.add(obj);
            Log.d("EgretRuntimeLauncher", (new StringBuilder()).append("addTask: ").append(library.getZipName()).toString());
        }

    }

    private void updated()
    {
        if(downloaderList.size() > 0 && updatedNumber != downloaderList.size())
        {
            return;
        } else
        {
            loadLibrary();
            return;
        }
    }

    public void run(EgretRuntimeDownloadListener egretruntimedownloadlistener)
    {
        while(runtimeVersionUrl == null || libraryRoot == null || egretruntimedownloadlistener == null) 
        {
            Log.e("EgretRuntimeLauncher", "library root, url or listener may be null");
            egretruntimedownloadlistener.onError("library root, url or listener may be null");
            ExecutorLab.releaseInstance();
            return;
        }
        Log.d("EgretRuntimeLauncher", "run");
        downloadListener = egretruntimedownloadlistener;
        fetchRemoteVersion();
    }

    public void setRuntimeVersionUrl(String s)
    {
        if(urlData == null)
            runtimeVersionUrl = s;
        else
            runtimeVersionUrl = (new StringBuilder()).append(s).append(urlData).toString();
    }

    public void stop()
    {
        for(int i = 0; i < downloaderList.size(); i++)
            ((EgretRuntimeLibrary)downloaderList.get(i)).stop();

        ExecutorLab.releaseInstance();
    }

    public static int DEBUG_RUNTIME_DOWNLOAD = 0;
    public static final String EGRET_JSON = "egret.json";
    public static final String EGRET_ROOT = "egret";
    private static final String EGRET_RUNTIME_CACHE_ROOT = "update";
    public static final String EGRET_RUNTIME_SD_ROOT = "egret/runtime";
    private static final String EGRET_RUNTIME_VERSION_URL = "http://runtime.egret-labs.org/runtime.php";
    private static final String TAG = "EgretRuntimeLauncher";
    private File cacheRoot;
    private int downLoadSum;
    private EgretRuntimeDownloadListener downloadListener;
    private ArrayList downloaderList;
    private int fileSizeSum;
    private File libraryRoot;
    private Handler mainHandler;
    private ConcurrentHashMap mapFileSize;
    private EgretRuntimeVersion runtimeVersion;
    private String runtimeVersionUrl;
    private File sdRoot;
    protected int updatedNumber;
    private String urlData;

    static 
    {
        DEBUG_RUNTIME_DOWNLOAD = 0;
    }
}
