// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.ims;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.telephony.ims.feature.ImsFeature;
import android.telephony.ims.feature.MMTelFeature;
import android.telephony.ims.feature.RcsFeature;
import android.util.Log;
import android.util.SparseArray;
import com.android.ims.ImsCallProfile;
import com.android.ims.internal.*;

public class ImsService extends Service
{

    static SparseArray _2D_get0(ImsService imsservice)
    {
        return imsservice.mFeatures;
    }

    static MMTelFeature _2D_wrap0(ImsService imsservice, int i, int j)
    {
        return imsservice.resolveMMTelFeature(i, j);
    }

    static void _2D_wrap1(ImsService imsservice, String s)
    {
        imsservice.enforceReadPhoneStatePermission(s);
    }

    static void _2D_wrap2(ImsService imsservice, int i, int j, IImsFeatureStatusCallback iimsfeaturestatuscallback)
    {
        imsservice.onCreateImsFeatureInternal(i, j, iimsfeaturestatuscallback);
    }

    static void _2D_wrap3(ImsService imsservice, int i, int j, IImsFeatureStatusCallback iimsfeaturestatuscallback)
    {
        imsservice.onRemoveImsFeatureInternal(i, j, iimsfeaturestatuscallback);
    }

    public ImsService()
    {
    }

    private void enforceReadPhoneStatePermission(String s)
    {
        if(checkCallingOrSelfPermission("android.permission.READ_PRIVILEGED_PHONE_STATE") != 0)
            enforceCallingOrSelfPermission("android.permission.READ_PHONE_STATE", s);
    }

    private ImsFeature makeImsFeature(int i, int j)
    {
        switch(j)
        {
        default:
            return null;

        case 0: // '\0'
            return onCreateEmergencyMMTelImsFeature(i);

        case 1: // '\001'
            return onCreateMMTelImsFeature(i);

        case 2: // '\002'
            return onCreateRcsFeature(i);
        }
    }

    private void onCreateImsFeatureInternal(int i, int j, IImsFeatureStatusCallback iimsfeaturestatuscallback)
    {
        Object obj = (SparseArray)mFeatures.get(i);
        SparseArray sparsearray = ((SparseArray) (obj));
        if(obj == null)
        {
            sparsearray = new SparseArray();
            mFeatures.put(i, sparsearray);
        }
        obj = makeImsFeature(i, j);
        if(obj != null)
        {
            ((ImsFeature) (obj)).setContext(this);
            ((ImsFeature) (obj)).setSlotId(i);
            ((ImsFeature) (obj)).addImsFeatureStatusCallback(iimsfeaturestatuscallback);
            sparsearray.put(j, obj);
        }
    }

    private void onRemoveImsFeatureInternal(int i, int j, IImsFeatureStatusCallback iimsfeaturestatuscallback)
    {
        SparseArray sparsearray = (SparseArray)mFeatures.get(i);
        if(sparsearray == null)
            return;
        ImsFeature imsfeature = getImsFeatureFromType(sparsearray, j);
        if(imsfeature != null)
        {
            sparsearray.remove(j);
            imsfeature.notifyFeatureRemoved(i);
            imsfeature.removeImsFeatureStatusCallback(iimsfeaturestatuscallback);
        }
    }

    private ImsFeature resolveImsFeature(SparseArray sparsearray, int i, Class class1)
    {
        sparsearray = getImsFeatureFromType(sparsearray, i);
        if(sparsearray == null)
            return null;
        try
        {
            sparsearray = (ImsFeature)class1.cast(sparsearray);
        }
        // Misplaced declaration of an exception variable
        catch(SparseArray sparsearray)
        {
            Log.e("ImsService", (new StringBuilder()).append("Can not cast ImsFeature! Exception: ").append(sparsearray.getMessage()).toString());
            return null;
        }
        return sparsearray;
    }

    private MMTelFeature resolveMMTelFeature(int i, int j)
    {
        SparseArray sparsearray = getImsFeatureMap(i);
        MMTelFeature mmtelfeature = null;
        if(sparsearray != null)
            mmtelfeature = (MMTelFeature)resolveImsFeature(sparsearray, j, android/telephony/ims/feature/MMTelFeature);
        return mmtelfeature;
    }

    public ImsFeature getImsFeatureFromType(SparseArray sparsearray, int i)
    {
        return (ImsFeature)sparsearray.get(i);
    }

    public SparseArray getImsFeatureMap(int i)
    {
        return (SparseArray)mFeatures.get(i);
    }

    public IBinder onBind(Intent intent)
    {
        if("android.telephony.ims.ImsService".equals(intent.getAction()))
            return mImsServiceController;
        else
            return null;
    }

    public MMTelFeature onCreateEmergencyMMTelImsFeature(int i)
    {
        return null;
    }

    public MMTelFeature onCreateMMTelImsFeature(int i)
    {
        return null;
    }

    public RcsFeature onCreateRcsFeature(int i)
    {
        return null;
    }

    private static final String LOG_TAG = "ImsService";
    public static final String SERVICE_INTERFACE = "android.telephony.ims.ImsService";
    private final SparseArray mFeatures = new SparseArray();
    protected final IBinder mImsServiceController = new com.android.ims.internal.IImsServiceController.Stub() {

        public void addRegistrationListener(int i, int j, IImsRegistrationListener iimsregistrationlistener)
            throws RemoteException
        {
            ImsService._2D_wrap1(ImsService.this, "addRegistrationListener");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            MMTelFeature mmtelfeature = ImsService._2D_wrap0(ImsService.this, i, j);
            if(mmtelfeature == null)
                break MISSING_BLOCK_LABEL_43;
            mmtelfeature.addRegistrationListener(iimsregistrationlistener);
            sparsearray;
            JVM INSTR monitorexit ;
            return;
            iimsregistrationlistener;
            throw iimsregistrationlistener;
        }

        public ImsCallProfile createCallProfile(int i, int j, int k, int l, int i1)
            throws RemoteException
        {
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "createCallProfile");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            Object obj = ImsService._2D_wrap0(ImsService.this, i, j);
            if(obj == null)
                break MISSING_BLOCK_LABEL_57;
            obj = ((MMTelFeature) (obj)).createCallProfile(k, l, i1);
            sparsearray;
            JVM INSTR monitorexit ;
            return ((ImsCallProfile) (obj));
            sparsearray;
            JVM INSTR monitorexit ;
            return null;
            Exception exception;
            exception;
            throw exception;
        }

        public IImsCallSession createCallSession(int i, int j, int k, ImsCallProfile imscallprofile, IImsCallSessionListener iimscallsessionlistener)
            throws RemoteException
        {
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "createCallSession");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            MMTelFeature mmtelfeature = ImsService._2D_wrap0(ImsService.this, i, j);
            if(mmtelfeature == null)
                break MISSING_BLOCK_LABEL_57;
            imscallprofile = mmtelfeature.createCallSession(k, imscallprofile, iimscallsessionlistener);
            sparsearray;
            JVM INSTR monitorexit ;
            return imscallprofile;
            sparsearray;
            JVM INSTR monitorexit ;
            return null;
            imscallprofile;
            throw imscallprofile;
        }

        public void createImsFeature(int i, int j, IImsFeatureStatusCallback iimsfeaturestatuscallback)
            throws RemoteException
        {
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "createImsFeature");
            ImsService._2D_wrap2(ImsService.this, i, j, iimsfeaturestatuscallback);
            sparsearray;
            JVM INSTR monitorexit ;
            return;
            iimsfeaturestatuscallback;
            throw iimsfeaturestatuscallback;
        }

        public void endSession(int i, int j, int k)
            throws RemoteException
        {
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            MMTelFeature mmtelfeature;
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "endSession");
            mmtelfeature = ImsService._2D_wrap0(ImsService.this, i, j);
            if(mmtelfeature == null)
                break MISSING_BLOCK_LABEL_45;
            mmtelfeature.endSession(k);
            sparsearray;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public IImsConfig getConfigInterface(int i, int j)
            throws RemoteException
        {
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "getConfigInterface");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            Object obj = ImsService._2D_wrap0(ImsService.this, i, j);
            if(obj == null)
                break MISSING_BLOCK_LABEL_49;
            obj = ((MMTelFeature) (obj)).getConfigInterface();
            sparsearray;
            JVM INSTR monitorexit ;
            return ((IImsConfig) (obj));
            sparsearray;
            JVM INSTR monitorexit ;
            return null;
            Exception exception;
            exception;
            throw exception;
        }

        public IImsEcbm getEcbmInterface(int i, int j)
            throws RemoteException
        {
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "getEcbmInterface");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            Object obj = ImsService._2D_wrap0(ImsService.this, i, j);
            if(obj == null)
                break MISSING_BLOCK_LABEL_49;
            obj = ((MMTelFeature) (obj)).getEcbmInterface();
            sparsearray;
            JVM INSTR monitorexit ;
            return ((IImsEcbm) (obj));
            sparsearray;
            JVM INSTR monitorexit ;
            return null;
            Exception exception;
            exception;
            throw exception;
        }

        public int getFeatureStatus(int i, int j)
            throws RemoteException
        {
            boolean flag;
            ImsService._2D_wrap1(ImsService.this, "getFeatureStatus");
            flag = false;
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            Object obj = (SparseArray)ImsService._2D_get0(ImsService.this).get(i);
            i = ((flag) ? 1 : 0);
            if(obj == null)
                break MISSING_BLOCK_LABEL_71;
            obj = getImsFeatureFromType(((SparseArray) (obj)), j);
            i = ((flag) ? 1 : 0);
            if(obj == null)
                break MISSING_BLOCK_LABEL_71;
            i = ((ImsFeature) (obj)).getFeatureState();
            sparsearray;
            JVM INSTR monitorexit ;
            return i;
            Exception exception;
            exception;
            throw exception;
        }

        public IImsMultiEndpoint getMultiEndpointInterface(int i, int j)
            throws RemoteException
        {
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "getMultiEndpointInterface");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            Object obj = ImsService._2D_wrap0(ImsService.this, i, j);
            if(obj == null)
                break MISSING_BLOCK_LABEL_49;
            obj = ((MMTelFeature) (obj)).getMultiEndpointInterface();
            sparsearray;
            JVM INSTR monitorexit ;
            return ((IImsMultiEndpoint) (obj));
            sparsearray;
            JVM INSTR monitorexit ;
            return null;
            Exception exception;
            exception;
            throw exception;
        }

        public IImsCallSession getPendingCallSession(int i, int j, int k, String s)
            throws RemoteException
        {
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "getPendingCallSession");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            MMTelFeature mmtelfeature = ImsService._2D_wrap0(ImsService.this, i, j);
            if(mmtelfeature == null)
                break MISSING_BLOCK_LABEL_55;
            s = mmtelfeature.getPendingCallSession(k, s);
            sparsearray;
            JVM INSTR monitorexit ;
            return s;
            sparsearray;
            JVM INSTR monitorexit ;
            return null;
            s;
            throw s;
        }

        public IImsUt getUtInterface(int i, int j)
            throws RemoteException
        {
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "getUtInterface");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            Object obj = ImsService._2D_wrap0(ImsService.this, i, j);
            if(obj == null)
                break MISSING_BLOCK_LABEL_49;
            obj = ((MMTelFeature) (obj)).getUtInterface();
            sparsearray;
            JVM INSTR monitorexit ;
            return ((IImsUt) (obj));
            sparsearray;
            JVM INSTR monitorexit ;
            return null;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean isConnected(int i, int j, int k, int l)
            throws RemoteException
        {
            ImsService._2D_wrap1(ImsService.this, "isConnected");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            MMTelFeature mmtelfeature = ImsService._2D_wrap0(ImsService.this, i, j);
            if(mmtelfeature == null)
                break MISSING_BLOCK_LABEL_53;
            boolean flag = mmtelfeature.isConnected(k, l);
            sparsearray;
            JVM INSTR monitorexit ;
            return flag;
            sparsearray;
            JVM INSTR monitorexit ;
            return false;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean isOpened(int i, int j)
            throws RemoteException
        {
            ImsService._2D_wrap1(ImsService.this, "isOpened");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            MMTelFeature mmtelfeature = ImsService._2D_wrap0(ImsService.this, i, j);
            if(mmtelfeature == null)
                break MISSING_BLOCK_LABEL_47;
            boolean flag = mmtelfeature.isOpened();
            sparsearray;
            JVM INSTR monitorexit ;
            return flag;
            sparsearray;
            JVM INSTR monitorexit ;
            return false;
            Exception exception;
            exception;
            throw exception;
        }

        public void removeImsFeature(int i, int j, IImsFeatureStatusCallback iimsfeaturestatuscallback)
            throws RemoteException
        {
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "removeImsFeature");
            ImsService._2D_wrap3(ImsService.this, i, j, iimsfeaturestatuscallback);
            sparsearray;
            JVM INSTR monitorexit ;
            return;
            iimsfeaturestatuscallback;
            throw iimsfeaturestatuscallback;
        }

        public void removeRegistrationListener(int i, int j, IImsRegistrationListener iimsregistrationlistener)
            throws RemoteException
        {
            ImsService._2D_wrap1(ImsService.this, "removeRegistrationListener");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            MMTelFeature mmtelfeature = ImsService._2D_wrap0(ImsService.this, i, j);
            if(mmtelfeature == null)
                break MISSING_BLOCK_LABEL_43;
            mmtelfeature.removeRegistrationListener(iimsregistrationlistener);
            sparsearray;
            JVM INSTR monitorexit ;
            return;
            iimsregistrationlistener;
            throw iimsregistrationlistener;
        }

        public void setUiTTYMode(int i, int j, int k, Message message)
            throws RemoteException
        {
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "setUiTTYMode");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            MMTelFeature mmtelfeature = ImsService._2D_wrap0(ImsService.this, i, j);
            if(mmtelfeature == null)
                break MISSING_BLOCK_LABEL_47;
            mmtelfeature.setUiTTYMode(k, message);
            sparsearray;
            JVM INSTR monitorexit ;
            return;
            message;
            throw message;
        }

        public int startSession(int i, int j, PendingIntent pendingintent, IImsRegistrationListener iimsregistrationlistener)
            throws RemoteException
        {
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "startSession");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            MMTelFeature mmtelfeature = ImsService._2D_wrap0(ImsService.this, i, j);
            if(mmtelfeature == null)
                break MISSING_BLOCK_LABEL_53;
            i = mmtelfeature.startSession(pendingintent, iimsregistrationlistener);
            sparsearray;
            JVM INSTR monitorexit ;
            return i;
            sparsearray;
            JVM INSTR monitorexit ;
            return 0;
            pendingintent;
            throw pendingintent;
        }

        public void turnOffIms(int i, int j)
            throws RemoteException
        {
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "turnOffIms");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            MMTelFeature mmtelfeature = ImsService._2D_wrap0(ImsService.this, i, j);
            if(mmtelfeature == null)
                break MISSING_BLOCK_LABEL_42;
            mmtelfeature.turnOffIms();
            sparsearray;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void turnOnIms(int i, int j)
            throws RemoteException
        {
            enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "turnOnIms");
            SparseArray sparsearray = ImsService._2D_get0(ImsService.this);
            sparsearray;
            JVM INSTR monitorenter ;
            MMTelFeature mmtelfeature = ImsService._2D_wrap0(ImsService.this, i, j);
            if(mmtelfeature == null)
                break MISSING_BLOCK_LABEL_42;
            mmtelfeature.turnOnIms();
            sparsearray;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final ImsService this$0;

            
            {
                this$0 = ImsService.this;
                super();
            }
    }
;
}
