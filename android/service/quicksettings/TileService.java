// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.quicksettings;

import android.app.Dialog;
import android.app.Service;
import android.content.*;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.*;
import android.view.View;
import android.view.Window;

// Referenced classes of package android.service.quicksettings:
//            IQSService, Tile

public class TileService extends Service
{
    private class H extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 7: default 48
        //                       1 124
        //                       2 95
        //                       3 49
        //                       4 59
        //                       5 153
        //                       6 178
        //                       7 203;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            return;
_L4:
            onTileAdded();
            continue; /* Loop/switch isn't completed */
_L5:
            if(TileService._2D_get1(TileService.this))
            {
                TileService._2D_set0(TileService.this, false);
                onStopListening();
            }
            onTileRemoved();
            continue; /* Loop/switch isn't completed */
_L3:
            if(TileService._2D_get1(TileService.this))
            {
                TileService._2D_set0(TileService.this, false);
                onStopListening();
            }
            continue; /* Loop/switch isn't completed */
_L2:
            if(!TileService._2D_get1(TileService.this))
            {
                TileService._2D_set0(TileService.this, true);
                onStartListening();
            }
            continue; /* Loop/switch isn't completed */
_L6:
            TileService._2D_set1(TileService.this, (IBinder)message.obj);
            onClick();
            continue; /* Loop/switch isn't completed */
_L7:
            if(TileService._2D_get4(TileService.this) != null)
                TileService._2D_get4(TileService.this).run();
            continue; /* Loop/switch isn't completed */
_L8:
            try
            {
                TileService._2D_get2(TileService.this).onStartSuccessful(TileService._2D_get3(TileService.this));
            }
            // Misplaced declaration of an exception variable
            catch(Message message) { }
            if(true) goto _L1; else goto _L9
_L9:
        }

        private static final int MSG_START_LISTENING = 1;
        private static final int MSG_START_SUCCESS = 7;
        private static final int MSG_STOP_LISTENING = 2;
        private static final int MSG_TILE_ADDED = 3;
        private static final int MSG_TILE_CLICKED = 5;
        private static final int MSG_TILE_REMOVED = 4;
        private static final int MSG_UNLOCK_COMPLETE = 6;
        final TileService this$0;

        public H(Looper looper)
        {
            this$0 = TileService.this;
            super(looper);
        }
    }


    static H _2D_get0(TileService tileservice)
    {
        return tileservice.mHandler;
    }

    static boolean _2D_get1(TileService tileservice)
    {
        return tileservice.mListening;
    }

    static IQSService _2D_get2(TileService tileservice)
    {
        return tileservice.mService;
    }

    static IBinder _2D_get3(TileService tileservice)
    {
        return tileservice.mTileToken;
    }

    static Runnable _2D_get4(TileService tileservice)
    {
        return tileservice.mUnlockRunnable;
    }

    static boolean _2D_set0(TileService tileservice, boolean flag)
    {
        tileservice.mListening = flag;
        return flag;
    }

    static IBinder _2D_set1(TileService tileservice, IBinder ibinder)
    {
        tileservice.mToken = ibinder;
        return ibinder;
    }

    public TileService()
    {
        mListening = false;
    }

    public static boolean isQuickSettingsSupported()
    {
        return Resources.getSystem().getBoolean(0x112008b);
    }

    public static final void requestListeningState(Context context, ComponentName componentname)
    {
        Intent intent = new Intent("android.service.quicksettings.action.REQUEST_LISTENING");
        intent.putExtra("android.intent.extra.COMPONENT_NAME", componentname);
        intent.setPackage("com.android.systemui");
        context.sendBroadcast(intent, "android.permission.BIND_QUICK_SETTINGS_TILE");
    }

    public final Tile getQsTile()
    {
        return mTile;
    }

    public final boolean isLocked()
    {
        boolean flag;
        try
        {
            flag = mService.isLocked();
        }
        catch(RemoteException remoteexception)
        {
            return true;
        }
        return flag;
    }

    public final boolean isSecure()
    {
        boolean flag;
        try
        {
            flag = mService.isSecure();
        }
        catch(RemoteException remoteexception)
        {
            return true;
        }
        return flag;
    }

    public IBinder onBind(Intent intent)
    {
        mService = IQSService.Stub.asInterface(intent.getIBinderExtra("service"));
        mTileToken = intent.getIBinderExtra("token");
        try
        {
            mTile = mService.getTile(mTileToken);
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw new RuntimeException("Unable to reach IQSService", intent);
        }
        if(mTile != null)
        {
            mTile.setService(mService, mTileToken);
            mHandler.sendEmptyMessage(7);
        }
        return new IQSTileService.Stub() {

            public void onClick(IBinder ibinder)
                throws RemoteException
            {
                TileService._2D_get0(TileService.this).obtainMessage(5, ibinder).sendToTarget();
            }

            public void onStartListening()
                throws RemoteException
            {
                TileService._2D_get0(TileService.this).sendEmptyMessage(1);
            }

            public void onStopListening()
                throws RemoteException
            {
                TileService._2D_get0(TileService.this).sendEmptyMessage(2);
            }

            public void onTileAdded()
                throws RemoteException
            {
                TileService._2D_get0(TileService.this).sendEmptyMessage(3);
            }

            public void onTileRemoved()
                throws RemoteException
            {
                TileService._2D_get0(TileService.this).sendEmptyMessage(4);
            }

            public void onUnlockComplete()
                throws RemoteException
            {
                TileService._2D_get0(TileService.this).sendEmptyMessage(6);
            }

            final TileService this$0;

            
            {
                this$0 = TileService.this;
                super();
            }
        }
;
    }

    public void onClick()
    {
    }

    public void onDestroy()
    {
        if(mListening)
        {
            onStopListening();
            mListening = false;
        }
        super.onDestroy();
    }

    public void onStartListening()
    {
    }

    public void onStopListening()
    {
    }

    public void onTileAdded()
    {
    }

    public void onTileRemoved()
    {
    }

    public final void setStatusIcon(Icon icon, String s)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.updateStatusIcon(mTileToken, icon, s);
_L2:
        return;
        icon;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final void showDialog(Dialog dialog)
    {
        dialog.getWindow().getAttributes().token = mToken;
        dialog.getWindow().setType(2035);
        dialog.getWindow().getDecorView().addOnAttachStateChangeListener(new android.view.View.OnAttachStateChangeListener() {

            public void onViewAttachedToWindow(View view)
            {
            }

            public void onViewDetachedFromWindow(View view)
            {
                TileService._2D_get2(TileService.this).onDialogHidden(TileService._2D_get3(TileService.this));
_L2:
                return;
                view;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final TileService this$0;

            
            {
                this$0 = TileService.this;
                super();
            }
        }
);
        dialog.show();
        mService.onShowDialog(mTileToken);
_L2:
        return;
        dialog;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final void startActivityAndCollapse(Intent intent)
    {
        startActivity(intent);
        mService.onStartActivity(mTileToken);
_L2:
        return;
        intent;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final void unlockAndRun(Runnable runnable)
    {
        mUnlockRunnable = runnable;
        mService.startUnlockAndRun(mTileToken);
_L2:
        return;
        runnable;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final String ACTION_QS_TILE = "android.service.quicksettings.action.QS_TILE";
    public static final String ACTION_QS_TILE_PREFERENCES = "android.service.quicksettings.action.QS_TILE_PREFERENCES";
    public static final String ACTION_REQUEST_LISTENING = "android.service.quicksettings.action.REQUEST_LISTENING";
    public static final String EXTRA_SERVICE = "service";
    public static final String EXTRA_STATE = "state";
    public static final String EXTRA_TOKEN = "token";
    public static final String META_DATA_ACTIVE_TILE = "android.service.quicksettings.ACTIVE_TILE";
    private final H mHandler = new H(Looper.getMainLooper());
    private boolean mListening;
    private IQSService mService;
    private Tile mTile;
    private IBinder mTileToken;
    private IBinder mToken;
    private Runnable mUnlockRunnable;
}
