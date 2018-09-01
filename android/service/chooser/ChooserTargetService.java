// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.chooser;

import android.app.Service;
import android.content.*;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

// Referenced classes of package android.service.chooser:
//            IChooserTargetResult

public abstract class ChooserTargetService extends Service
{
    private class IChooserTargetServiceWrapper extends IChooserTargetService.Stub
    {

        public void getChooserTargets(ComponentName componentname, IntentFilter intentfilter, IChooserTargetResult ichoosertargetresult)
            throws RemoteException
        {
            long l = clearCallingIdentity();
            componentname = onGetChooserTargets(componentname, intentfilter);
            restoreCallingIdentity(l);
            ichoosertargetresult.sendResult(componentname);
            return;
            componentname;
            restoreCallingIdentity(l);
            ichoosertargetresult.sendResult(null);
            throw componentname;
        }

        final ChooserTargetService this$0;

        private IChooserTargetServiceWrapper()
        {
            this$0 = ChooserTargetService.this;
            super();
        }

        IChooserTargetServiceWrapper(IChooserTargetServiceWrapper ichoosertargetservicewrapper)
        {
            this();
        }
    }


    public ChooserTargetService()
    {
        mWrapper = null;
    }

    public IBinder onBind(Intent intent)
    {
        if(!"android.service.chooser.ChooserTargetService".equals(intent.getAction()))
            return null;
        if(mWrapper == null)
            mWrapper = new IChooserTargetServiceWrapper(null);
        return mWrapper;
    }

    public abstract List onGetChooserTargets(ComponentName componentname, IntentFilter intentfilter);

    public static final String BIND_PERMISSION = "android.permission.BIND_CHOOSER_TARGET_SERVICE";
    private static final boolean DEBUG = false;
    public static final String META_DATA_NAME = "android.service.chooser.chooser_target_service";
    public static final String SERVICE_INTERFACE = "android.service.chooser.ChooserTargetService";
    private final String TAG = (new StringBuilder()).append(android/service/chooser/ChooserTargetService.getSimpleName()).append('[').append(getClass().getSimpleName()).append(']').toString();
    private IChooserTargetServiceWrapper mWrapper;
}
