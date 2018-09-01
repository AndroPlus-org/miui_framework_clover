// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.net.SntpClient;
import java.net.InetAddress;

// Referenced classes of package android.util:
//            Log

class NtpTrustedTimeInjector
{

    NtpTrustedTimeInjector()
    {
    }

    static boolean requestTime(SntpClient sntpclient, String s, int i)
    {
        InetAddress ainetaddress[];
        int j;
        ainetaddress = InetAddress.getAllByName(s);
        j = ainetaddress.length;
        int k = 0;
_L2:
        if(k >= j)
            break; /* Loop/switch isn't completed */
        boolean flag;
        String s1 = ainetaddress[k].getHostAddress();
        StringBuilder stringbuilder = JVM INSTR new #32  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.d("NtpTrustedTimeInjector", stringbuilder.append("host ").append(s).append(", which address ").append(s1).toString());
        flag = sntpclient.requestTime(s1, i);
        if(flag)
            return true;
        k++;
        if(true) goto _L2; else goto _L1
        sntpclient;
        Log.d("NtpTrustedTimeInjector", (new StringBuilder()).append("requestTime: ").append(sntpclient).toString());
_L1:
        return false;
    }

    private static final boolean DBG = true;
    private static final String TAG = "NtpTrustedTimeInjector";
}
