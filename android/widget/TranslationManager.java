// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.*;
import android.os.*;
import com.miui.translationservice.ITranslation;
import com.miui.translationservice.ITranslationRemoteCallback;
import com.miui.translationservice.provider.TranslationResult;
import miui.os.Build;

class TranslationManager
{
    private class TranslationRemoteCallback extends com.miui.translationservice.ITranslationRemoteCallback.Stub
    {

        public void cancel()
        {
            mCancelled = true;
        }

        public void onTranslationFinished(TranslationResult translationresult)
        {
            if(mCancelled)
                return;
            TranslationManager._2D_get1(TranslationManager.this).removeCallbacksAndMessages(null);
            if(translationresult == null)
                translationresult = TranslationManager._2D_get1(TranslationManager.this).obtainMessage(1);
            else
                translationresult = TranslationManager._2D_get1(TranslationManager.this).obtainMessage(0, translationresult);
            TranslationManager._2D_get1(TranslationManager.this).sendMessageDelayed(translationresult, 200L);
        }

        private boolean mCancelled;
        final TranslationManager this$0;

        private TranslationRemoteCallback()
        {
            this$0 = TranslationManager.this;
            super();
            mCancelled = false;
        }

        TranslationRemoteCallback(TranslationRemoteCallback translationremotecallback)
        {
            this();
        }
    }


    static Context _2D_get0(TranslationManager translationmanager)
    {
        return translationmanager.mContext;
    }

    static Handler _2D_get1(TranslationManager translationmanager)
    {
        return translationmanager.mHandler;
    }

    static TranslationRemoteCallback _2D_get2(TranslationManager translationmanager)
    {
        return translationmanager.mRemoteCallback;
    }

    static ITranslation _2D_set0(TranslationManager translationmanager, ITranslation itranslation)
    {
        translationmanager.mService = itranslation;
        return itranslation;
    }

    static void _2D_wrap0(TranslationManager translationmanager)
    {
        translationmanager.onTranslationFailed();
    }

    static void _2D_wrap1(TranslationManager translationmanager, String s, String s1, String s2, ITranslationRemoteCallback itranslationremotecallback)
    {
        translationmanager.translate(s, s1, s2, itranslationremotecallback);
    }

    TranslationManager(Context context, Handler handler)
    {
        mService = null;
        mRemoteCallback = null;
        mBindServiceTask = null;
        mContext = context;
        mHandler = handler;
    }

    private void onTranslationFailed()
    {
        mHandler.removeCallbacksAndMessages(null);
        android.os.Message message = mHandler.obtainMessage(1);
        mHandler.sendMessageDelayed(message, 200L);
    }

    private void translate(String s, String s1, String s2, ITranslationRemoteCallback itranslationremotecallback)
    {
        mService.translate(s, s1, s2, mRemoteCallback);
_L1:
        return;
        s;
        s.printStackTrace();
        onTranslationFailed();
          goto _L1
    }

    boolean isAvailable()
    {
        return Build.IS_INTERNATIONAL_BUILD ^ true;
    }

    void translate(String s, String s1, String s2)
    {
        if(mRemoteCallback != null)
            mRemoteCallback.cancel();
        mRemoteCallback = new TranslationRemoteCallback(null);
        if(mService == null) goto _L2; else goto _L1
_L1:
        translate(s, s1, s2, ((ITranslationRemoteCallback) (mRemoteCallback)));
_L4:
        return;
_L2:
        if(mBindServiceTask == null || mBindServiceTask.getStatus() != android.os.AsyncTask.Status.RUNNING)
        {
            mBindServiceTask = new AsyncTask() {

                protected volatile Object doInBackground(Object aobj[])
                {
                    return doInBackground((String[])aobj);
                }

                protected transient Void doInBackground(String as[])
                {
                    Intent intent = (new Intent()).setClassName("com.miui.translationservice", "com.miui.translationservice.TranslationService");
                    if(!TranslationManager._2D_get0(TranslationManager.this).bindService(intent, as. new ServiceConnection() {

                public void onServiceConnected(ComponentName componentname, IBinder ibinder)
                {
                    TranslationManager._2D_set0(_fld0, com.miui.translationservice.ITranslation.Stub.asInterface(ibinder));
                    TranslationManager._2D_wrap1(_fld0, params[0], params[1], params[2], TranslationManager._2D_get2(_fld0));
                }

                public void onServiceDisconnected(ComponentName componentname)
                {
                    TranslationManager._2D_set0(_fld0, null);
                }

                final _cls1 this$1;
                final String val$params[];

            
            {
                this$1 = final__pcls1;
                params = _5B_Ljava.lang.String_3B_.this;
                super();
            }
            }
, 1))
                        TranslationManager._2D_wrap0(TranslationManager.this);
                    return null;
                }

                final TranslationManager this$0;

            
            {
                this$0 = TranslationManager.this;
                super();
            }
            }
;
            mBindServiceTask.execute(new String[] {
                s, s1, s2
            });
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    static final int MSG_QUERY_FAIL = 1;
    static final int MSG_QUERY_SUCCESS = 0;
    private static final String TRANSLATION_SERVICE_CLASS = "com.miui.translationservice.TranslationService";
    private static final String TRANSLATION_SERVICE_PACKAGE = "com.miui.translationservice";
    private AsyncTask mBindServiceTask;
    private Context mContext;
    private Handler mHandler;
    private TranslationRemoteCallback mRemoteCallback;
    private ITranslation mService;
}
