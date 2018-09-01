// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.cardemulation;

import android.app.Activity;
import android.app.ActivityThread;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.IPackageManager;
import android.nfc.INfcFCardEmulation;
import android.nfc.NfcAdapter;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import java.util.HashMap;
import java.util.List;

public final class NfcFCardEmulation
{

    private NfcFCardEmulation(Context context, INfcFCardEmulation infcfcardemulation)
    {
        mContext = context.getApplicationContext();
        sService = infcfcardemulation;
    }

    public static NfcFCardEmulation getInstance(NfcAdapter nfcadapter)
    {
        android/nfc/cardemulation/NfcFCardEmulation;
        JVM INSTR monitorenter ;
        if(nfcadapter != null)
            break MISSING_BLOCK_LABEL_25;
        nfcadapter = JVM INSTR new #47  <Class NullPointerException>;
        nfcadapter.NullPointerException("NfcAdapter is null");
        throw nfcadapter;
        nfcadapter;
        android/nfc/cardemulation/NfcFCardEmulation;
        JVM INSTR monitorexit ;
        throw nfcadapter;
        Context context = nfcadapter.getContext();
        if(context != null)
            break MISSING_BLOCK_LABEL_52;
        Log.e("NfcFCardEmulation", "NfcAdapter context is null.");
        nfcadapter = JVM INSTR new #67  <Class UnsupportedOperationException>;
        nfcadapter.UnsupportedOperationException();
        throw nfcadapter;
        Object obj;
        if(sIsInitialized)
            break MISSING_BLOCK_LABEL_137;
        obj = ActivityThread.getPackageManager();
        if(obj != null)
            break MISSING_BLOCK_LABEL_84;
        Log.e("NfcFCardEmulation", "Cannot get PackageManager");
        nfcadapter = JVM INSTR new #67  <Class UnsupportedOperationException>;
        nfcadapter.UnsupportedOperationException();
        throw nfcadapter;
        try
        {
            if(!((IPackageManager) (obj)).hasSystemFeature("android.hardware.nfc.hcef", 0))
            {
                Log.e("NfcFCardEmulation", "This device does not support NFC-F card emulation");
                nfcadapter = JVM INSTR new #67  <Class UnsupportedOperationException>;
                nfcadapter.UnsupportedOperationException();
                throw nfcadapter;
            }
            break MISSING_BLOCK_LABEL_133;
        }
        // Misplaced declaration of an exception variable
        catch(NfcAdapter nfcadapter) { }
        Log.e("NfcFCardEmulation", "PackageManager query failed.");
        nfcadapter = JVM INSTR new #67  <Class UnsupportedOperationException>;
        nfcadapter.UnsupportedOperationException();
        throw nfcadapter;
        sIsInitialized = true;
        NfcFCardEmulation nfcfcardemulation = (NfcFCardEmulation)sCardEmus.get(context);
        obj = nfcfcardemulation;
        if(nfcfcardemulation != null)
            break MISSING_BLOCK_LABEL_200;
        nfcadapter = nfcadapter.getNfcFCardEmulationService();
        if(nfcadapter != null)
            break MISSING_BLOCK_LABEL_181;
        Log.e("NfcFCardEmulation", "This device does not implement the INfcFCardEmulation interface.");
        nfcadapter = JVM INSTR new #67  <Class UnsupportedOperationException>;
        nfcadapter.UnsupportedOperationException();
        throw nfcadapter;
        obj = JVM INSTR new #2   <Class NfcFCardEmulation>;
        ((NfcFCardEmulation) (obj)).NfcFCardEmulation(context, nfcadapter);
        sCardEmus.put(context, obj);
        android/nfc/cardemulation/NfcFCardEmulation;
        JVM INSTR monitorexit ;
        return ((NfcFCardEmulation) (obj));
    }

    public static boolean isValidNfcid2(String s)
    {
        if(s == null)
            return false;
        if(s.length() != 16)
        {
            Log.e("NfcFCardEmulation", (new StringBuilder()).append("NFCID2 ").append(s).append(" is not a valid NFCID2.").toString());
            return false;
        }
        if(!s.toUpperCase().startsWith("02FE"))
        {
            Log.e("NfcFCardEmulation", (new StringBuilder()).append("NFCID2 ").append(s).append(" is not a valid NFCID2.").toString());
            return false;
        }
        try
        {
            Long.parseLong(s, 16);
        }
        catch(NumberFormatException numberformatexception)
        {
            Log.e("NfcFCardEmulation", (new StringBuilder()).append("NFCID2 ").append(s).append(" is not a valid NFCID2.").toString());
            return false;
        }
        return true;
    }

    public static boolean isValidSystemCode(String s)
    {
        if(s == null)
            return false;
        if(s.length() != 4)
        {
            Log.e("NfcFCardEmulation", (new StringBuilder()).append("System Code ").append(s).append(" is not a valid System Code.").toString());
            return false;
        }
        if(!s.startsWith("4") || s.toUpperCase().endsWith("FF"))
        {
            Log.e("NfcFCardEmulation", (new StringBuilder()).append("System Code ").append(s).append(" is not a valid System Code.").toString());
            return false;
        }
        try
        {
            Integer.parseInt(s, 16);
        }
        catch(NumberFormatException numberformatexception)
        {
            Log.e("NfcFCardEmulation", (new StringBuilder()).append("System Code ").append(s).append(" is not a valid System Code.").toString());
            return false;
        }
        return true;
    }

    public boolean disableService(Activity activity)
        throws RuntimeException
    {
        if(activity == null)
            throw new NullPointerException("activity is null");
        if(!activity.isResumed())
            throw new IllegalArgumentException("Activity must be resumed.");
        boolean flag;
        try
        {
            flag = sService.disableNfcFForegroundService();
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("NfcFCardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.disableNfcFForegroundService();
            }
            // Misplaced declaration of an exception variable
            catch(Activity activity)
            {
                Log.e("NfcFCardEmulation", "Failed to reach CardEmulationService.");
                activity.rethrowAsRuntimeException();
                return false;
            }
            return flag1;
        }
        return flag;
    }

    public boolean enableService(Activity activity, ComponentName componentname)
        throws RuntimeException
    {
        if(activity == null || componentname == null)
            throw new NullPointerException("activity or service is null");
        if(!activity.isResumed())
            throw new IllegalArgumentException("Activity must be resumed.");
        boolean flag;
        try
        {
            flag = sService.enableNfcFForegroundService(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("NfcFCardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.enableNfcFForegroundService(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(Activity activity)
            {
                Log.e("NfcFCardEmulation", "Failed to reach CardEmulationService.");
                activity.rethrowAsRuntimeException();
                return false;
            }
            return flag1;
        }
        return flag;
    }

    public int getMaxNumOfRegisterableSystemCodes()
    {
        int i;
        try
        {
            i = sService.getMaxNumOfRegisterableSystemCodes();
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("NfcFCardEmulation", "Failed to recover CardEmulationService.");
                return -1;
            }
            int j;
            try
            {
                j = sService.getMaxNumOfRegisterableSystemCodes();
            }
            catch(RemoteException remoteexception1)
            {
                Log.e("NfcFCardEmulation", "Failed to reach CardEmulationService.");
                return -1;
            }
            return j;
        }
        return i;
    }

    public List getNfcFServices()
    {
        List list;
        try
        {
            list = sService.getNfcFServices(UserHandle.myUserId());
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("NfcFCardEmulation", "Failed to recover CardEmulationService.");
                return null;
            }
            List list1;
            try
            {
                list1 = sService.getNfcFServices(UserHandle.myUserId());
            }
            catch(RemoteException remoteexception1)
            {
                Log.e("NfcFCardEmulation", "Failed to reach CardEmulationService.");
                return null;
            }
            return list1;
        }
        return list;
    }

    public String getNfcid2ForService(ComponentName componentname)
        throws RuntimeException
    {
        if(componentname == null)
            throw new NullPointerException("service is null");
        String s;
        try
        {
            s = sService.getNfcid2ForService(UserHandle.myUserId(), componentname);
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("NfcFCardEmulation", "Failed to recover CardEmulationService.");
                return null;
            }
            try
            {
                componentname = sService.getNfcid2ForService(UserHandle.myUserId(), componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                Log.e("NfcFCardEmulation", "Failed to reach CardEmulationService.");
                componentname.rethrowAsRuntimeException();
                return null;
            }
            return componentname;
        }
        return s;
    }

    public String getSystemCodeForService(ComponentName componentname)
        throws RuntimeException
    {
        if(componentname == null)
            throw new NullPointerException("service is null");
        String s;
        try
        {
            s = sService.getSystemCodeForService(UserHandle.myUserId(), componentname);
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("NfcFCardEmulation", "Failed to recover CardEmulationService.");
                return null;
            }
            try
            {
                componentname = sService.getSystemCodeForService(UserHandle.myUserId(), componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                Log.e("NfcFCardEmulation", "Failed to reach CardEmulationService.");
                componentname.rethrowAsRuntimeException();
                return null;
            }
            return componentname;
        }
        return s;
    }

    void recoverService()
    {
        sService = NfcAdapter.getDefaultAdapter(mContext).getNfcFCardEmulationService();
    }

    public boolean registerSystemCodeForService(ComponentName componentname, String s)
        throws RuntimeException
    {
        if(componentname == null || s == null)
            throw new NullPointerException("service or systemCode is null");
        boolean flag;
        try
        {
            flag = sService.registerSystemCodeForService(UserHandle.myUserId(), componentname, s);
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("NfcFCardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.registerSystemCodeForService(UserHandle.myUserId(), componentname, s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                Log.e("NfcFCardEmulation", "Failed to reach CardEmulationService.");
                componentname.rethrowAsRuntimeException();
                return false;
            }
            return flag1;
        }
        return flag;
    }

    public boolean setNfcid2ForService(ComponentName componentname, String s)
        throws RuntimeException
    {
        if(componentname == null || s == null)
            throw new NullPointerException("service or nfcid2 is null");
        boolean flag;
        try
        {
            flag = sService.setNfcid2ForService(UserHandle.myUserId(), componentname, s);
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("NfcFCardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.setNfcid2ForService(UserHandle.myUserId(), componentname, s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                Log.e("NfcFCardEmulation", "Failed to reach CardEmulationService.");
                componentname.rethrowAsRuntimeException();
                return false;
            }
            return flag1;
        }
        return flag;
    }

    public boolean unregisterSystemCodeForService(ComponentName componentname)
        throws RuntimeException
    {
        if(componentname == null)
            throw new NullPointerException("service is null");
        boolean flag;
        try
        {
            flag = sService.removeSystemCodeForService(UserHandle.myUserId(), componentname);
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("NfcFCardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.removeSystemCodeForService(UserHandle.myUserId(), componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                Log.e("NfcFCardEmulation", "Failed to reach CardEmulationService.");
                componentname.rethrowAsRuntimeException();
                return false;
            }
            return flag1;
        }
        return flag;
    }

    static final String TAG = "NfcFCardEmulation";
    static HashMap sCardEmus = new HashMap();
    static boolean sIsInitialized = false;
    static INfcFCardEmulation sService;
    final Context mContext;

}
