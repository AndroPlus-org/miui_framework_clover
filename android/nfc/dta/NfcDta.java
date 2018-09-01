// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.dta;

import android.content.Context;
import android.nfc.INfcDta;
import android.nfc.NfcAdapter;
import android.os.RemoteException;
import android.util.Log;
import java.util.HashMap;

public final class NfcDta
{

    private NfcDta(Context context, INfcDta infcdta)
    {
        mContext = context.getApplicationContext();
        sService = infcdta;
    }

    public static NfcDta getInstance(NfcAdapter nfcadapter)
    {
        android/nfc/dta/NfcDta;
        JVM INSTR monitorenter ;
        if(nfcadapter != null)
            break MISSING_BLOCK_LABEL_25;
        nfcadapter = JVM INSTR new #41  <Class NullPointerException>;
        nfcadapter.NullPointerException("NfcAdapter is null");
        throw nfcadapter;
        nfcadapter;
        android/nfc/dta/NfcDta;
        JVM INSTR monitorexit ;
        throw nfcadapter;
        Context context = nfcadapter.getContext();
        if(context != null)
            break MISSING_BLOCK_LABEL_52;
        Log.e("NfcDta", "NfcAdapter context is null.");
        nfcadapter = JVM INSTR new #61  <Class UnsupportedOperationException>;
        nfcadapter.UnsupportedOperationException();
        throw nfcadapter;
        NfcDta nfcdta = (NfcDta)sNfcDtas.get(context);
        NfcDta nfcdta1;
        nfcdta1 = nfcdta;
        if(nfcdta != null)
            break MISSING_BLOCK_LABEL_115;
        nfcadapter = nfcadapter.getNfcDtaInterface();
        if(nfcadapter != null)
            break MISSING_BLOCK_LABEL_96;
        Log.e("NfcDta", "This device does not implement the INfcDta interface.");
        nfcadapter = JVM INSTR new #61  <Class UnsupportedOperationException>;
        nfcadapter.UnsupportedOperationException();
        throw nfcadapter;
        nfcdta1 = JVM INSTR new #2   <Class NfcDta>;
        nfcdta1.NfcDta(context, nfcadapter);
        sNfcDtas.put(context, nfcdta1);
        android/nfc/dta/NfcDta;
        JVM INSTR monitorexit ;
        return nfcdta1;
    }

    public boolean disableClient()
    {
        try
        {
            sService.disableClient();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public boolean disableDta()
    {
        try
        {
            sService.disableDta();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public boolean disableServer()
    {
        try
        {
            sService.disableServer();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public boolean enableClient(String s, int i, int j, int k)
    {
        boolean flag;
        try
        {
            flag = sService.enableClient(s, i, j, k);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return flag;
    }

    public boolean enableDta()
    {
        try
        {
            sService.enableDta();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public boolean enableServer(String s, int i, int j, int k, int l)
    {
        boolean flag;
        try
        {
            flag = sService.enableServer(s, i, j, k, l);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return flag;
    }

    public boolean registerMessageService(String s)
    {
        boolean flag;
        try
        {
            flag = sService.registerMessageService(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return flag;
    }

    private static final String TAG = "NfcDta";
    private static HashMap sNfcDtas = new HashMap();
    private static INfcDta sService;
    private final Context mContext;

}
