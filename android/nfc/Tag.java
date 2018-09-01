// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcBarcode;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

// Referenced classes of package android.nfc:
//            INfcTag

public final class Tag
    implements Parcelable
{

    public Tag(byte abyte0[], int ai[], Bundle abundle[], int i, INfcTag infctag)
    {
        if(ai == null)
        {
            throw new IllegalArgumentException("rawTargets cannot be null");
        } else
        {
            mId = abyte0;
            mTechList = Arrays.copyOf(ai, ai.length);
            mTechStringList = generateTechStringList(ai);
            mTechExtras = (Bundle[])Arrays.copyOf(abundle, ai.length);
            mServiceHandle = i;
            mTagService = infctag;
            mConnectedTechnology = -1;
            return;
        }
    }

    public static Tag createMockTag(byte abyte0[], int ai[], Bundle abundle[])
    {
        return new Tag(abyte0, ai, abundle, 0, null);
    }

    private String[] generateTechStringList(int ai[])
    {
        int i;
        String as[];
        int j;
        i = ai.length;
        as = new String[i];
        j = 0;
_L13:
        if(j >= i)
            break MISSING_BLOCK_LABEL_229;
        ai[j];
        JVM INSTR tableswitch 1 10: default 76
    //                   1 169
    //                   2 181
    //                   3 106
    //                   4 193
    //                   5 205
    //                   6 145
    //                   7 157
    //                   8 121
    //                   9 133
    //                   10 217;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L11:
        break MISSING_BLOCK_LABEL_217;
_L9:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown tech type ").append(ai[j]).toString());
_L4:
        as[j] = android/nfc/tech/IsoDep.getName();
_L14:
        j++;
        if(true) goto _L13; else goto _L12
_L12:
        as[j] = android/nfc/tech/MifareClassic.getName();
          goto _L14
_L10:
        as[j] = android/nfc/tech/MifareUltralight.getName();
          goto _L14
_L7:
        as[j] = android/nfc/tech/Ndef.getName();
          goto _L14
_L8:
        as[j] = android/nfc/tech/NdefFormatable.getName();
          goto _L14
_L2:
        as[j] = android/nfc/tech/NfcA.getName();
          goto _L14
_L3:
        as[j] = android/nfc/tech/NfcB.getName();
          goto _L14
_L5:
        as[j] = android/nfc/tech/NfcF.getName();
          goto _L14
_L6:
        as[j] = android/nfc/tech/NfcV.getName();
          goto _L14
        as[j] = android/nfc/tech/NfcBarcode.getName();
          goto _L14
        return as;
    }

    static int[] getTechCodesFromStrings(String as[])
        throws IllegalArgumentException
    {
        if(as == null)
            throw new IllegalArgumentException("List cannot be null");
        int ai[] = new int[as.length];
        HashMap hashmap = getTechStringToCodeMap();
        for(int i = 0; i < as.length; i++)
        {
            Integer integer = (Integer)hashmap.get(as[i]);
            if(integer == null)
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown tech type ").append(as[i]).toString());
            ai[i] = integer.intValue();
        }

        return ai;
    }

    private static HashMap getTechStringToCodeMap()
    {
        HashMap hashmap = new HashMap();
        hashmap.put(android/nfc/tech/IsoDep.getName(), Integer.valueOf(3));
        hashmap.put(android/nfc/tech/MifareClassic.getName(), Integer.valueOf(8));
        hashmap.put(android/nfc/tech/MifareUltralight.getName(), Integer.valueOf(9));
        hashmap.put(android/nfc/tech/Ndef.getName(), Integer.valueOf(6));
        hashmap.put(android/nfc/tech/NdefFormatable.getName(), Integer.valueOf(7));
        hashmap.put(android/nfc/tech/NfcA.getName(), Integer.valueOf(1));
        hashmap.put(android/nfc/tech/NfcB.getName(), Integer.valueOf(2));
        hashmap.put(android/nfc/tech/NfcF.getName(), Integer.valueOf(4));
        hashmap.put(android/nfc/tech/NfcV.getName(), Integer.valueOf(5));
        hashmap.put(android/nfc/tech/NfcBarcode.getName(), Integer.valueOf(10));
        return hashmap;
    }

    static byte[] readBytesWithNull(Parcel parcel)
    {
        int i = parcel.readInt();
        byte abyte0[] = null;
        if(i >= 0)
        {
            abyte0 = new byte[i];
            parcel.readByteArray(abyte0);
        }
        return abyte0;
    }

    static void writeBytesWithNull(Parcel parcel, byte abyte0[])
    {
        if(abyte0 == null)
        {
            parcel.writeInt(-1);
            return;
        } else
        {
            parcel.writeInt(abyte0.length);
            parcel.writeByteArray(abyte0);
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public int getConnectedTechnology()
    {
        return mConnectedTechnology;
    }

    public byte[] getId()
    {
        return mId;
    }

    public int getServiceHandle()
    {
        return mServiceHandle;
    }

    public INfcTag getTagService()
    {
        return mTagService;
    }

    public int[] getTechCodeList()
    {
        return mTechList;
    }

    public Bundle getTechExtras(int i)
    {
        byte byte0 = -1;
        int j = 0;
        do
        {
label0:
            {
                int k = byte0;
                if(j < mTechList.length)
                {
                    if(mTechList[j] != i)
                        break label0;
                    k = j;
                }
                if(k < 0)
                    return null;
                else
                    return mTechExtras[k];
            }
            j++;
        } while(true);
    }

    public String[] getTechList()
    {
        return mTechStringList;
    }

    public boolean hasTech(int i)
    {
        int ai[] = mTechList;
        int j = ai.length;
        for(int k = 0; k < j; k++)
            if(ai[k] == i)
                return true;

        return false;
    }

    public Tag rediscover()
        throws IOException
    {
        if(getConnectedTechnology() != -1)
            throw new IllegalStateException("Close connection to the technology first!");
        if(mTagService == null)
            throw new IOException("Mock tags don't support this operation.");
        Object obj;
        try
        {
            obj = mTagService.rediscover(getServiceHandle());
        }
        catch(RemoteException remoteexception)
        {
            throw new IOException("NFC service dead");
        }
        if(obj != null)
            return ((Tag) (obj));
        obj = JVM INSTR new #187 <Class IOException>;
        ((IOException) (obj)).IOException("Failed to rediscover tag");
        throw obj;
    }

    public void setConnectedTechnology(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if(mConnectedTechnology != -1)
            break MISSING_BLOCK_LABEL_18;
        mConnectedTechnology = i;
        this;
        JVM INSTR monitorexit ;
        return;
        IllegalStateException illegalstateexception = JVM INSTR new #193 <Class IllegalStateException>;
        illegalstateexception.IllegalStateException("Close other technology first!");
        throw illegalstateexception;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setTechnologyDisconnected()
    {
        mConnectedTechnology = -1;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("TAG: Tech [");
        String as[] = getTechList();
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            stringbuilder.append(as[j]);
            if(j < i - 1)
                stringbuilder.append(", ");
        }

        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mTagService == null)
            i = 1;
        else
            i = 0;
        writeBytesWithNull(parcel, mId);
        parcel.writeInt(mTechList.length);
        parcel.writeIntArray(mTechList);
        parcel.writeTypedArray(mTechExtras, 0);
        parcel.writeInt(mServiceHandle);
        parcel.writeInt(i);
        if(i == 0)
            parcel.writeStrongBinder(mTagService.asBinder());
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Tag createFromParcel(Parcel parcel)
        {
            byte abyte0[] = Tag.readBytesWithNull(parcel);
            int ai[] = new int[parcel.readInt()];
            parcel.readIntArray(ai);
            Bundle abundle[] = (Bundle[])parcel.createTypedArray(Bundle.CREATOR);
            int i = parcel.readInt();
            if(parcel.readInt() == 0)
                parcel = INfcTag.Stub.asInterface(parcel.readStrongBinder());
            else
                parcel = null;
            return new Tag(abyte0, ai, abundle, i, parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Tag[] newArray(int i)
        {
            return new Tag[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    int mConnectedTechnology;
    final byte mId[];
    final int mServiceHandle;
    final INfcTag mTagService;
    final Bundle mTechExtras[];
    final int mTechList[];
    final String mTechStringList[];

}
