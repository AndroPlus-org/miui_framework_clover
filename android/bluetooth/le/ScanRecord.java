// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.bluetooth.BluetoothUuid;
import android.os.ParcelUuid;
import android.util.*;
import java.util.*;

// Referenced classes of package android.bluetooth.le:
//            BluetoothLeUtils

public final class ScanRecord
{

    private ScanRecord(List list, SparseArray sparsearray, Map map, int i, int j, String s, byte abyte0[])
    {
        mServiceUuids = list;
        mManufacturerSpecificData = sparsearray;
        mServiceData = map;
        mDeviceName = s;
        mAdvertiseFlags = i;
        mTxPowerLevel = j;
        mBytes = abyte0;
    }

    private static byte[] extractBytes(byte abyte0[], int i, int j)
    {
        byte abyte1[] = new byte[j];
        System.arraycopy(abyte0, i, abyte1, 0, j);
        return abyte1;
    }

    public static ScanRecord parseFromBytes(byte abyte0[])
    {
        int i;
        ArrayList arraylist;
        Object obj;
        int j;
        SparseArray sparsearray;
        ArrayMap arraymap;
        int k;
        if(abyte0 == null)
            return null;
        i = -1;
        arraylist = new ArrayList();
        obj = null;
        j = 0x80000000;
        sparsearray = new SparseArray();
        arraymap = new ArrayMap();
        k = 0;
_L11:
        int l = k;
        l = abyte0.length;
        ArrayList arraylist1;
        if(k < l)
        {
            l = k + 1;
            k = abyte0[k] & 0xff;
            if(k != 0)
                break MISSING_BLOCK_LABEL_115;
        }
        arraylist1 = arraylist;
        if(arraylist.isEmpty())
            arraylist1 = null;
        obj = new ScanRecord(arraylist1, sparsearray, arraymap, i, j, ((String) (obj)), abyte0);
        return ((ScanRecord) (obj));
        int i1;
        int j1;
        i1 = k - 1;
        j1 = l + 1;
        l = abyte0[l] & 0xff;
        l;
        JVM INSTR lookupswitch 14: default 260
    //                   1: 270
    //                   2: 282
    //                   3: 282
    //                   4: 345
    //                   5: 345
    //                   6: 363
    //                   7: 363
    //                   8: 382
    //                   9: 382
    //                   10: 405
    //                   22: 414
    //                   32: 414
    //                   33: 414
    //                   255: 481;
           goto _L1 _L2 _L3 _L3 _L4 _L4 _L5 _L5 _L6 _L6 _L7 _L8 _L8 _L8 _L9
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L12:
        k = j1 + i1;
        if(true) goto _L11; else goto _L10
_L10:
        i = abyte0[j1] & 0xff;
          goto _L12
_L3:
        l = j1;
        parseServiceUuid(abyte0, j1, i1, 2, arraylist);
          goto _L12
        obj;
_L13:
        Log.e("ScanRecord", (new StringBuilder()).append("unable to parse scan record: ").append(Arrays.toString(abyte0)).toString());
        return new ScanRecord(null, null, null, -1, 0x80000000, null, abyte0);
_L4:
        l = j1;
        parseServiceUuid(abyte0, j1, i1, 4, arraylist);
          goto _L12
_L5:
        l = j1;
        parseServiceUuid(abyte0, j1, i1, 16, arraylist);
          goto _L12
_L6:
        l = j1;
        obj = new String(extractBytes(abyte0, j1, i1));
          goto _L12
_L7:
        j = abyte0[j1];
          goto _L12
_L8:
        byte byte0 = 2;
        if(l == 32)
            byte0 = 4;
        else
        if(l == 33)
            byte0 = 16;
        l = j1;
        arraymap.put(BluetoothUuid.parseUuidFrom(extractBytes(abyte0, j1, byte0)), extractBytes(abyte0, j1 + byte0, i1 - byte0));
          goto _L12
_L9:
        l = j1;
        sparsearray.put(((abyte0[j1 + 1] & 0xff) << 8) + (abyte0[j1] & 0xff), extractBytes(abyte0, j1 + 2, i1 - 2));
          goto _L12
        Exception exception;
        exception;
          goto _L13
    }

    private static int parseServiceUuid(byte abyte0[], int i, int j, int k, List list)
    {
        while(j > 0) 
        {
            list.add(BluetoothUuid.parseUuidFrom(extractBytes(abyte0, i, k)));
            j -= k;
            i += k;
        }
        return i;
    }

    public int getAdvertiseFlags()
    {
        return mAdvertiseFlags;
    }

    public byte[] getBytes()
    {
        return mBytes;
    }

    public String getDeviceName()
    {
        return mDeviceName;
    }

    public SparseArray getManufacturerSpecificData()
    {
        return mManufacturerSpecificData;
    }

    public byte[] getManufacturerSpecificData(int i)
    {
        return (byte[])mManufacturerSpecificData.get(i);
    }

    public Map getServiceData()
    {
        return mServiceData;
    }

    public byte[] getServiceData(ParcelUuid parceluuid)
    {
        if(parceluuid == null)
            return null;
        else
            return (byte[])mServiceData.get(parceluuid);
    }

    public List getServiceUuids()
    {
        return mServiceUuids;
    }

    public int getTxPowerLevel()
    {
        return mTxPowerLevel;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ScanRecord [mAdvertiseFlags=").append(mAdvertiseFlags).append(", mServiceUuids=").append(mServiceUuids).append(", mManufacturerSpecificData=").append(BluetoothLeUtils.toString(mManufacturerSpecificData)).append(", mServiceData=").append(BluetoothLeUtils.toString(mServiceData)).append(", mTxPowerLevel=").append(mTxPowerLevel).append(", mDeviceName=").append(mDeviceName).append("]").toString();
    }

    private static final int DATA_TYPE_FLAGS = 1;
    private static final int DATA_TYPE_LOCAL_NAME_COMPLETE = 9;
    private static final int DATA_TYPE_LOCAL_NAME_SHORT = 8;
    private static final int DATA_TYPE_MANUFACTURER_SPECIFIC_DATA = 255;
    private static final int DATA_TYPE_SERVICE_DATA_128_BIT = 33;
    private static final int DATA_TYPE_SERVICE_DATA_16_BIT = 22;
    private static final int DATA_TYPE_SERVICE_DATA_32_BIT = 32;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_COMPLETE = 7;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_PARTIAL = 6;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_COMPLETE = 3;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_PARTIAL = 2;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_COMPLETE = 5;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_PARTIAL = 4;
    private static final int DATA_TYPE_TX_POWER_LEVEL = 10;
    private static final String TAG = "ScanRecord";
    private final int mAdvertiseFlags;
    private final byte mBytes[];
    private final String mDeviceName;
    private final SparseArray mManufacturerSpecificData;
    private final Map mServiceData;
    private final List mServiceUuids;
    private final int mTxPowerLevel;
}
