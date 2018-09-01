// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.cardemulation;

import android.app.Activity;
import android.app.ActivityThread;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.IPackageManager;
import android.nfc.INfcCardEmulation;
import android.nfc.NfcAdapter;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import java.util.HashMap;
import java.util.List;

// Referenced classes of package android.nfc.cardemulation:
//            AidGroup

public final class CardEmulation
{

    private CardEmulation(Context context, INfcCardEmulation infccardemulation)
    {
        mContext = context.getApplicationContext();
        sService = infccardemulation;
    }

    public static CardEmulation getInstance(NfcAdapter nfcadapter)
    {
        android/nfc/cardemulation/CardEmulation;
        JVM INSTR monitorenter ;
        if(nfcadapter != null)
            break MISSING_BLOCK_LABEL_25;
        nfcadapter = JVM INSTR new #69  <Class NullPointerException>;
        nfcadapter.NullPointerException("NfcAdapter is null");
        throw nfcadapter;
        nfcadapter;
        android/nfc/cardemulation/CardEmulation;
        JVM INSTR monitorexit ;
        throw nfcadapter;
        Context context = nfcadapter.getContext();
        if(context != null)
            break MISSING_BLOCK_LABEL_52;
        Log.e("CardEmulation", "NfcAdapter context is null.");
        nfcadapter = JVM INSTR new #89  <Class UnsupportedOperationException>;
        nfcadapter.UnsupportedOperationException();
        throw nfcadapter;
        Object obj;
        if(sIsInitialized)
            break MISSING_BLOCK_LABEL_137;
        obj = ActivityThread.getPackageManager();
        if(obj != null)
            break MISSING_BLOCK_LABEL_84;
        Log.e("CardEmulation", "Cannot get PackageManager");
        nfcadapter = JVM INSTR new #89  <Class UnsupportedOperationException>;
        nfcadapter.UnsupportedOperationException();
        throw nfcadapter;
        try
        {
            if(!((IPackageManager) (obj)).hasSystemFeature("android.hardware.nfc.hce", 0))
            {
                Log.e("CardEmulation", "This device does not support card emulation");
                nfcadapter = JVM INSTR new #89  <Class UnsupportedOperationException>;
                nfcadapter.UnsupportedOperationException();
                throw nfcadapter;
            }
            break MISSING_BLOCK_LABEL_133;
        }
        // Misplaced declaration of an exception variable
        catch(NfcAdapter nfcadapter) { }
        Log.e("CardEmulation", "PackageManager query failed.");
        nfcadapter = JVM INSTR new #89  <Class UnsupportedOperationException>;
        nfcadapter.UnsupportedOperationException();
        throw nfcadapter;
        sIsInitialized = true;
        CardEmulation cardemulation = (CardEmulation)sCardEmus.get(context);
        obj = cardemulation;
        if(cardemulation != null)
            break MISSING_BLOCK_LABEL_200;
        nfcadapter = nfcadapter.getCardEmulationService();
        if(nfcadapter != null)
            break MISSING_BLOCK_LABEL_181;
        Log.e("CardEmulation", "This device does not implement the INfcCardEmulation interface.");
        nfcadapter = JVM INSTR new #89  <Class UnsupportedOperationException>;
        nfcadapter.UnsupportedOperationException();
        throw nfcadapter;
        obj = JVM INSTR new #2   <Class CardEmulation>;
        ((CardEmulation) (obj)).CardEmulation(context, nfcadapter);
        sCardEmus.put(context, obj);
        android/nfc/cardemulation/CardEmulation;
        JVM INSTR monitorexit ;
        return ((CardEmulation) (obj));
    }

    public static boolean isValidAid(String s)
    {
        if(s == null)
            return false;
        if((s.endsWith("*") || s.endsWith("#")) && s.length() % 2 == 0)
        {
            Log.e("CardEmulation", (new StringBuilder()).append("AID ").append(s).append(" is not a valid AID.").toString());
            return false;
        }
        boolean flag;
        if(!s.endsWith("*"))
            flag = s.endsWith("#");
        else
            flag = true;
        if(!flag && s.length() % 2 != 0)
        {
            Log.e("CardEmulation", (new StringBuilder()).append("AID ").append(s).append(" is not a valid AID.").toString());
            return false;
        }
        if(!s.matches("[0-9A-Fa-f]{10,32}\\*?\\#?"))
        {
            Log.e("CardEmulation", (new StringBuilder()).append("AID ").append(s).append(" is not a valid AID.").toString());
            return false;
        } else
        {
            return true;
        }
    }

    public boolean categoryAllowsForegroundPreference(String s)
    {
        if(!"payment".equals(s)) goto _L2; else goto _L1
_L1:
        boolean flag = false;
        int i = android.provider.Settings.Secure.getInt(mContext.getContentResolver(), "nfc_payment_foreground");
        if(i != 0)
            flag = true;
        else
            flag = false;
_L4:
        return flag;
_L2:
        return true;
        s;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public List getAidsForService(ComponentName componentname, String s)
    {
        Object obj;
        List list;
        obj = null;
        list = null;
        AidGroup aidgroup = sService.getAidGroupForService(UserHandle.myUserId(), componentname, s);
        if(aidgroup == null)
            break MISSING_BLOCK_LABEL_32;
        list = aidgroup.getAids();
        return list;
        RemoteException remoteexception;
        remoteexception;
        recoverService();
        if(sService == null)
        {
            Log.e("CardEmulation", "Failed to recover CardEmulationService.");
            return null;
        }
        try
        {
            s = sService.getAidGroupForService(UserHandle.myUserId(), componentname, s);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            Log.e("CardEmulation", "Failed to recover CardEmulationService.");
            return null;
        }
        componentname = obj;
        if(s == null)
            break MISSING_BLOCK_LABEL_82;
        componentname = s.getAids();
        return componentname;
    }

    public int getSelectionModeForCategory(String s)
    {
        if("payment".equals(s))
            return android.provider.Settings.Secure.getString(mContext.getContentResolver(), "nfc_payment_default_component") == null ? 1 : 0;
        else
            return 2;
    }

    public List getServices(String s)
    {
        List list;
        try
        {
            list = sService.getServices(UserHandle.myUserId(), s);
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("CardEmulation", "Failed to recover CardEmulationService.");
                return null;
            }
            try
            {
                s = sService.getServices(UserHandle.myUserId(), s);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("CardEmulation", "Failed to reach CardEmulationService.");
                return null;
            }
            return s;
        }
        return list;
    }

    public boolean isDefaultServiceForAid(ComponentName componentname, String s)
    {
        boolean flag;
        try
        {
            flag = sService.isDefaultServiceForAid(UserHandle.myUserId(), componentname, s);
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("CardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.isDefaultServiceForAid(UserHandle.myUserId(), componentname, s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                Log.e("CardEmulation", "Failed to reach CardEmulationService.");
                return false;
            }
            return flag1;
        }
        return flag;
    }

    public boolean isDefaultServiceForCategory(ComponentName componentname, String s)
    {
        boolean flag;
        try
        {
            flag = sService.isDefaultServiceForCategory(UserHandle.myUserId(), componentname, s);
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("CardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.isDefaultServiceForCategory(UserHandle.myUserId(), componentname, s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                Log.e("CardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            return flag1;
        }
        return flag;
    }

    void recoverService()
    {
        sService = NfcAdapter.getDefaultAdapter(mContext).getCardEmulationService();
    }

    public boolean registerAidsForService(ComponentName componentname, String s, List list)
    {
        list = new AidGroup(list, s);
        boolean flag;
        try
        {
            flag = sService.registerAidGroupForService(UserHandle.myUserId(), componentname, list);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("CardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.registerAidGroupForService(UserHandle.myUserId(), componentname, list);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                Log.e("CardEmulation", "Failed to reach CardEmulationService.");
                return false;
            }
            return flag1;
        }
        return flag;
    }

    public boolean removeAidsForService(ComponentName componentname, String s)
    {
        boolean flag;
        try
        {
            flag = sService.removeAidGroupForService(UserHandle.myUserId(), componentname, s);
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("CardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.removeAidGroupForService(UserHandle.myUserId(), componentname, s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                Log.e("CardEmulation", "Failed to reach CardEmulationService.");
                return false;
            }
            return flag1;
        }
        return flag;
    }

    public boolean setDefaultForNextTap(ComponentName componentname)
    {
        boolean flag;
        try
        {
            flag = sService.setDefaultForNextTap(UserHandle.myUserId(), componentname);
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("CardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.setDefaultForNextTap(UserHandle.myUserId(), componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                Log.e("CardEmulation", "Failed to reach CardEmulationService.");
                return false;
            }
            return flag1;
        }
        return flag;
    }

    public boolean setDefaultServiceForCategory(ComponentName componentname, String s)
    {
        boolean flag;
        try
        {
            flag = sService.setDefaultServiceForCategory(UserHandle.myUserId(), componentname, s);
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("CardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.setDefaultServiceForCategory(UserHandle.myUserId(), componentname, s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                Log.e("CardEmulation", "Failed to reach CardEmulationService.");
                return false;
            }
            return flag1;
        }
        return flag;
    }

    public boolean setPreferredService(Activity activity, ComponentName componentname)
    {
        if(activity == null || componentname == null)
            throw new NullPointerException("activity or service or category is null");
        if(!activity.isResumed())
            throw new IllegalArgumentException("Activity must be resumed.");
        boolean flag;
        try
        {
            flag = sService.setPreferredService(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("CardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.setPreferredService(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(Activity activity)
            {
                Log.e("CardEmulation", "Failed to reach CardEmulationService.");
                return false;
            }
            return flag1;
        }
        return flag;
    }

    public boolean supportsAidPrefixRegistration()
    {
        boolean flag;
        try
        {
            flag = sService.supportsAidPrefixRegistration();
        }
        catch(RemoteException remoteexception)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("CardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.supportsAidPrefixRegistration();
            }
            catch(RemoteException remoteexception1)
            {
                Log.e("CardEmulation", "Failed to reach CardEmulationService.");
                return false;
            }
            return flag1;
        }
        return flag;
    }

    public boolean unsetPreferredService(Activity activity)
    {
        if(activity == null)
            throw new NullPointerException("activity is null");
        if(!activity.isResumed())
            throw new IllegalArgumentException("Activity must be resumed.");
        boolean flag;
        try
        {
            flag = sService.unsetPreferredService();
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            recoverService();
            if(sService == null)
            {
                Log.e("CardEmulation", "Failed to recover CardEmulationService.");
                return false;
            }
            boolean flag1;
            try
            {
                flag1 = sService.unsetPreferredService();
            }
            // Misplaced declaration of an exception variable
            catch(Activity activity)
            {
                Log.e("CardEmulation", "Failed to reach CardEmulationService.");
                return false;
            }
            return flag1;
        }
        return flag;
    }

    public static final String ACTION_CHANGE_DEFAULT = "android.nfc.cardemulation.action.ACTION_CHANGE_DEFAULT";
    public static final String CATEGORY_OTHER = "other";
    public static final String CATEGORY_PAYMENT = "payment";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_SERVICE_COMPONENT = "component";
    public static final int SELECTION_MODE_ALWAYS_ASK = 1;
    public static final int SELECTION_MODE_ASK_IF_CONFLICT = 2;
    public static final int SELECTION_MODE_PREFER_DEFAULT = 0;
    static final String TAG = "CardEmulation";
    static HashMap sCardEmus = new HashMap();
    static boolean sIsInitialized = false;
    static INfcCardEmulation sService;
    final Context mContext;

}
