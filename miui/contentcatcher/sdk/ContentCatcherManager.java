// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk;

import android.os.*;
import android.util.Slog;
import miui.contentcatcher.IContentCatcherService;
import miui.contentcatcher.sdk.data.PageConfig;
import miui.contentcatcher.sdk.injector.IContentDecorateCallback;
import miui.contentcatcher.sdk.listener.IContentListenerCallback;

// Referenced classes of package miui.contentcatcher.sdk:
//            ClientToken, Token, DecorateContentParam, Content

public class ContentCatcherManager
{

    static String _2D_get0()
    {
        return TAG;
    }

    static IContentCatcherService _2D_get1(ContentCatcherManager contentcatchermanager)
    {
        return contentcatchermanager.mService;
    }

    static IContentCatcherService _2D_set0(ContentCatcherManager contentcatchermanager, IContentCatcherService icontentcatcherservice)
    {
        contentcatchermanager.mService = icontentcatcherservice;
        return icontentcatcherservice;
    }

    private ContentCatcherManager()
    {
        mDeathHandler = new android.os.IBinder.DeathRecipient() {

            public void binderDied()
            {
                Slog.w(ContentCatcherManager._2D_get0(), "ContentCatcher binderDied!");
                if(ContentCatcherManager._2D_get1(ContentCatcherManager.this) != null)
                {
                    ContentCatcherManager._2D_get1(ContentCatcherManager.this).asBinder().unlinkToDeath(mDeathHandler, 0);
                    ContentCatcherManager._2D_set0(ContentCatcherManager.this, null);
                }
            }

            final ContentCatcherManager this$0;

            
            {
                this$0 = ContentCatcherManager.this;
                super();
            }
        }
;
    }

    public static ContentCatcherManager getInstance()
    {
        if(sInstance != null) goto _L2; else goto _L1
_L1:
        miui/contentcatcher/sdk/ContentCatcherManager;
        JVM INSTR monitorenter ;
        if(sInstance == null)
        {
            ContentCatcherManager contentcatchermanager = JVM INSTR new #2   <Class ContentCatcherManager>;
            contentcatchermanager.ContentCatcherManager();
            sInstance = contentcatchermanager;
        }
        miui/contentcatcher/sdk/ContentCatcherManager;
        JVM INSTR monitorexit ;
_L2:
        return sInstance;
        Exception exception;
        exception;
        throw exception;
    }

    public void decorateContent(ClientToken clienttoken, Token token, DecorateContentParam decoratecontentparam)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("decorateContent listenerToken ").append(clienttoken).append(", targetInjectorToken ").append(token).toString());
        IContentCatcherService icontentcatcherservice = getContentCatcherService();
        if(icontentcatcherservice == null)
            break MISSING_BLOCK_LABEL_62;
        icontentcatcherservice.decorateContent(clienttoken, token, decoratecontentparam);
_L1:
        return;
        clienttoken;
        Slog.e(TAG, (new StringBuilder()).append("decorateContent error: ").append(clienttoken.toString()).toString());
          goto _L1
    }

    protected IContentCatcherService getContentCatcherService()
    {
        if(mService != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        IContentCatcherService icontentcatcherservice;
        if(mService != null)
            break MISSING_BLOCK_LABEL_56;
        mService = miui.contentcatcher.IContentCatcherService.Stub.asInterface(ServiceManager.getService("miui.contentcatcher.ContentCatcherService"));
        icontentcatcherservice = mService;
        if(icontentcatcherservice == null)
            break MISSING_BLOCK_LABEL_76;
        mService.asBinder().linkToDeath(mDeathHandler, 0);
_L3:
        this;
        JVM INSTR monitorexit ;
_L2:
        return mService;
        Object obj;
        obj;
        ((Exception) (obj)).printStackTrace();
          goto _L3
        obj;
        throw obj;
        Slog.e(TAG, "failed to get ContentCatcherService.");
          goto _L3
    }

    public PageConfig getPageConfig(Token token)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("getPageInjectorInfo ").append(token).toString());
        IContentCatcherService icontentcatcherservice = getContentCatcherService();
        if(icontentcatcherservice == null)
            break MISSING_BLOCK_LABEL_81;
        token = icontentcatcherservice.getPageConfig(token);
        return token;
        token;
        Slog.e(TAG, (new StringBuilder()).append("isPageInterested error: ").append(token.toString()).toString());
        return null;
    }

    public void onContentCatched(Content content)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("onContentCatched: ").append(content).toString());
        IContentCatcherService icontentcatcherservice = getContentCatcherService();
        if(icontentcatcherservice == null)
            break MISSING_BLOCK_LABEL_48;
        icontentcatcherservice.onContentCatched(content);
_L1:
        return;
        content;
        Slog.e(TAG, (new StringBuilder()).append("onContentCatched error: ").append(content.toString()).toString());
          goto _L1
    }

    public void registerContentInjector(Token token, IContentDecorateCallback icontentdecoratecallback)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("registerContentInjector ").append(token).toString());
        IContentCatcherService icontentcatcherservice = getContentCatcherService();
        if(icontentcatcherservice == null)
            break MISSING_BLOCK_LABEL_49;
        icontentcatcherservice.registerContentInjector(token, icontentdecoratecallback);
_L1:
        return;
        token;
        Slog.e(TAG, (new StringBuilder()).append("registerContentInjector error: ").append(token.toString()).toString());
          goto _L1
    }

    public void registerContentListener(ClientToken clienttoken, IContentListenerCallback icontentlistenercallback)
    {
        if(DEBUG)
            Slog.i(TAG, "registerContentListener");
        IContentCatcherService icontentcatcherservice = getContentCatcherService();
        if(icontentcatcherservice == null)
            break MISSING_BLOCK_LABEL_32;
        icontentcatcherservice.registerContentListener(clienttoken, icontentlistenercallback);
_L1:
        return;
        clienttoken;
        Slog.e(TAG, (new StringBuilder()).append("registerContentListener error: ").append(clienttoken.toString()).toString());
          goto _L1
    }

    public void unregisterContentInjector(Token token)
    {
        if(DEBUG)
            Slog.i(TAG, "unregisterContentInjector");
        IContentCatcherService icontentcatcherservice = getContentCatcherService();
        if(icontentcatcherservice == null)
            break MISSING_BLOCK_LABEL_31;
        icontentcatcherservice.unregisterContentInjector(token);
_L1:
        return;
        token;
        Slog.e(TAG, (new StringBuilder()).append("unregisterContentInjector error: ").append(token.toString()).toString());
          goto _L1
    }

    public void unregisterContentListener(ClientToken clienttoken)
    {
        if(DEBUG)
            Slog.i(TAG, "unregisterContentListener");
        IContentCatcherService icontentcatcherservice = getContentCatcherService();
        if(icontentcatcherservice == null)
            break MISSING_BLOCK_LABEL_31;
        icontentcatcherservice.unregisterContentListener(clienttoken);
_L1:
        return;
        clienttoken;
        Slog.e(TAG, (new StringBuilder()).append("unregisterContentListener error: ").append(clienttoken.toString()).toString());
          goto _L1
    }

    public boolean updateClientConfig(String s, String s1, boolean flag)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("updateClientConfig target ").append(s).append(" jobTag ").append(s1).append(" enable ").append(flag).toString());
        IContentCatcherService icontentcatcherservice = getContentCatcherService();
        if(icontentcatcherservice != null)
            try
            {
                icontentcatcherservice.updateClientConfig(s, s1, flag);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Slog.e(TAG, (new StringBuilder()).append("updateClientConfig error: ").append(s.toString()).toString());
            }
        return true;
    }

    public boolean updateConfig(String as[])
    {
        if(DEBUG)
            Slog.i(TAG, "updateConfig");
        IContentCatcherService icontentcatcherservice = getContentCatcherService();
        if(icontentcatcherservice != null)
            try
            {
                icontentcatcherservice.updateConfig(as);
            }
            // Misplaced declaration of an exception variable
            catch(String as[])
            {
                Slog.e(TAG, (new StringBuilder()).append("updateConfig error: ").append(as.toString()).toString());
            }
        return true;
    }

    public void updateJobValidity(String s, String s1, boolean flag)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("updateJobValidity jobTag ").append(s).append(" packageName ").append(s1).append(" enable ").append(flag).toString());
        IContentCatcherService icontentcatcherservice = getContentCatcherService();
        if(icontentcatcherservice == null)
            break MISSING_BLOCK_LABEL_71;
        icontentcatcherservice.updateJobValidity(s, s1, flag);
_L1:
        return;
        s;
        Slog.e(TAG, (new StringBuilder()).append("updateJobValidity error: ").append(s.toString()).toString());
          goto _L1
    }

    private static final String CONTENTCAP_SERVICE_NAME = "miui.contentcatcher.ContentCatcherService";
    private static boolean DEBUG = false;
    private static final String TAG = miui/contentcatcher/sdk/ContentCatcherManager.getSimpleName();
    private static volatile ContentCatcherManager sInstance = null;
    android.os.IBinder.DeathRecipient mDeathHandler;
    private volatile IContentCatcherService mService;

}
