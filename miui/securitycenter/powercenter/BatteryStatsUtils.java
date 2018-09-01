// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.powercenter;

import android.os.*;
import android.util.Log;
import com.android.internal.app.IBatteryStats;
import com.android.internal.os.BatteryStatsImpl;
import java.io.FileInputStream;
import java.io.IOException;

class BatteryStatsUtils
{

    BatteryStatsUtils()
    {
    }

    static BatteryStatsImpl getBatteryStats()
    {
        IOException ioexception;
        Object obj;
        Object obj3;
        android.os.ParcelFileDescriptor.AutoCloseInputStream autocloseinputstream;
        Parcel parcel;
        ioexception = null;
        obj = null;
        Object obj1 = com.android.internal.app.IBatteryStats.Stub.asInterface(ServiceManager.getService("batterystats"));
        obj3 = ioexception;
        try
        {
            obj1 = ((IBatteryStats) (obj1)).getStatisticsStream();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BatteryStatsHelper", "remote exception:", remoteexception);
            continue; /* Loop/switch isn't completed */
        }
        obj3 = obj;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_108;
        obj3 = ioexception;
        autocloseinputstream = JVM INSTR new #41  <Class android.os.ParcelFileDescriptor$AutoCloseInputStream>;
        obj3 = ioexception;
        autocloseinputstream.android.os.ParcelFileDescriptor.AutoCloseInputStream(((ParcelFileDescriptor) (obj1)));
        obj3 = ioexception;
        parcel = Parcel.obtain();
        obj3 = readFully(autocloseinputstream, MemoryFile.getSize(((ParcelFileDescriptor) (obj1)).getFileDescriptor()));
        parcel.unmarshall(((byte []) (obj3)), 0, obj3.length);
        parcel.setDataPosition(0);
        obj1 = (BatteryStatsImpl)BatteryStatsImpl.CREATOR.createFromParcel(parcel);
        obj3 = obj1;
        parcel.recycle();
        obj3 = obj1;
        autocloseinputstream.close();
        obj3 = obj1;
_L2:
        return ((BatteryStatsImpl) (obj3));
        ioexception;
        obj3 = obj1;
        Log.e("", "fis close", ioexception);
        obj3 = obj1;
        continue; /* Loop/switch isn't completed */
        IOException ioexception2;
        ioexception2;
        Log.w("BatteryStatsHelper", "Unable to read statistics stream", ioexception2);
        ioexception2 = ioexception;
        parcel.recycle();
        ioexception2 = ioexception;
        autocloseinputstream.close();
        ioexception2 = obj;
        continue; /* Loop/switch isn't completed */
        Object obj2;
        obj2;
        ioexception2 = ioexception;
        Log.e("", "fis close", ((Throwable) (obj2)));
        ioexception2 = obj;
        if(true) goto _L2; else goto _L1
_L1:
        obj2;
        ioexception2 = ioexception;
        parcel.recycle();
        ioexception2 = ioexception;
        autocloseinputstream.close();
_L3:
        ioexception2 = ioexception;
        throw obj2;
        IOException ioexception1;
        ioexception1;
        ioexception2 = ioexception;
        Log.e("", "fis close", ioexception1);
          goto _L3
    }

    private static byte[] readFully(FileInputStream fileinputstream, int i)
        throws IOException
    {
        boolean flag = false;
        byte abyte0[] = new byte[i];
        i = ((flag) ? 1 : 0);
        do
        {
            int j;
            int k;
            do
            {
                j = fileinputstream.read(abyte0, i, abyte0.length - i);
                if(j <= 0)
                    return abyte0;
                j = i + j;
                k = fileinputstream.available();
                i = j;
            } while(k <= abyte0.length - j);
            byte abyte1[] = new byte[j + k];
            System.arraycopy(abyte0, 0, abyte1, 0, j);
            abyte0 = abyte1;
            i = j;
        } while(true);
    }

    private static final String TAG = "BatteryStatsHelper";
}
