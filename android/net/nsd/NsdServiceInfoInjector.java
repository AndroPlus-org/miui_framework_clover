// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.nsd;

import android.util.Log;

// Referenced classes of package android.net.nsd:
//            NsdServiceInfo

public class NsdServiceInfoInjector
{

    public NsdServiceInfoInjector()
    {
    }

    public static void setTxtRecord(int i, byte abyte0[], NsdServiceInfo nsdserviceinfo)
    {
        int j;
        if(i < 2)
        {
            Log.w("NsdServiceInfo", "txtRecord < 2");
            return;
        }
        if(abyte0 == null)
        {
            Log.w("NsdServiceInfo", "txtRecord is null");
            return;
        }
        if(abyte0.length != i)
            Log.w("NsdServiceInfo", "txtRecord.length != txtLen");
        j = 0;
_L5:
        if(j >= i) goto _L2; else goto _L1
_L1:
        byte byte0;
        byte0 = abyte0[j];
        j++;
        if(byte0 <= i - j) goto _L4; else goto _L3
_L3:
        Log.w("NsdServiceInfo", String.format("invalid length: %d", new Object[] {
            Byte.valueOf(byte0)
        }));
_L2:
        return;
_L4:
        Object aobj[] = new byte[byte0];
        System.arraycopy(abyte0, j, ((byte []) (aobj)), 0, byte0);
        aobj = (new String(((byte []) (aobj)))).split("=");
        if(aobj.length == 2)
            nsdserviceinfo.setAttribute(aobj[0], aobj[1].getBytes());
        j += byte0;
          goto _L5
        abyte0;
        abyte0.printStackTrace();
          goto _L2
    }

    private static final String TAG = "NsdServiceInfo";
}
