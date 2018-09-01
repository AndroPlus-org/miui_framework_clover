// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.*;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.android.internal.telephony.ITelephony;

// Referenced classes of package android.telephony:
//            TelephonyManager

public abstract class CellLocation
{

    public CellLocation()
    {
    }

    public static CellLocation getEmpty()
    {
        switch(TelephonyManager.getDefault().getCurrentPhoneType())
        {
        default:
            return null;

        case 2: // '\002'
            return new CdmaCellLocation();

        case 1: // '\001'
            return new GsmCellLocation();
        }
    }

    public static CellLocation newFromBundle(Bundle bundle)
    {
        switch(TelephonyManager.getDefault().getCurrentPhoneType())
        {
        default:
            return null;

        case 2: // '\002'
            return new CdmaCellLocation(bundle);

        case 1: // '\001'
            return new GsmCellLocation(bundle);
        }
    }

    public static void requestLocationUpdate()
    {
        ITelephony itelephony = com.android.internal.telephony.ITelephony.Stub.asInterface(ServiceManager.getService("phone"));
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_19;
        itelephony.updateServiceLocation();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public abstract void fillInNotifierBundle(Bundle bundle);

    public abstract boolean isEmpty();

    public abstract void setStateInvalid();
}
