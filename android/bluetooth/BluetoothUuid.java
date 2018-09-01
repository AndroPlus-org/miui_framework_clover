// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.ParcelUuid;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public final class BluetoothUuid
{

    public BluetoothUuid()
    {
    }

    public static boolean containsAllUuids(ParcelUuid aparceluuid[], ParcelUuid aparceluuid1[])
    {
        boolean flag = true;
        if(aparceluuid == null && aparceluuid1 == null)
            return true;
        if(aparceluuid == null)
        {
            if(aparceluuid1.length != 0)
                flag = false;
            return flag;
        }
        if(aparceluuid1 == null)
            return true;
        aparceluuid = new HashSet(Arrays.asList(aparceluuid));
        int i = aparceluuid1.length;
        for(int j = 0; j < i; j++)
            if(!aparceluuid.contains(aparceluuid1[j]))
                return false;

        return true;
    }

    public static boolean containsAnyUuid(ParcelUuid aparceluuid[], ParcelUuid aparceluuid1[])
    {
        boolean flag = true;
        boolean flag1 = true;
        if(aparceluuid == null && aparceluuid1 == null)
            return true;
        if(aparceluuid == null)
        {
            if(aparceluuid1.length != 0)
                flag1 = false;
            return flag1;
        }
        if(aparceluuid1 == null)
        {
            boolean flag2;
            if(aparceluuid.length == 0)
                flag2 = flag;
            else
                flag2 = false;
            return flag2;
        }
        aparceluuid = new HashSet(Arrays.asList(aparceluuid));
        int i = aparceluuid1.length;
        for(int j = 0; j < i; j++)
            if(aparceluuid.contains(aparceluuid1[j]))
                return true;

        return false;
    }

    public static int getServiceIdentifierFromParcelUuid(ParcelUuid parceluuid)
    {
        return (int)((parceluuid.getUuid().getMostSignificantBits() & 0xffffffff00000000L) >>> 32);
    }

    public static boolean is16BitUuid(ParcelUuid parceluuid)
    {
        boolean flag = false;
        parceluuid = parceluuid.getUuid();
        if(parceluuid.getLeastSignificantBits() != BASE_UUID.getUuid().getLeastSignificantBits())
            return false;
        if((parceluuid.getMostSignificantBits() & 0xffff0000ffffffffL) == 4096L)
            flag = true;
        return flag;
    }

    public static boolean is32BitUuid(ParcelUuid parceluuid)
    {
        boolean flag = false;
        UUID uuid = parceluuid.getUuid();
        if(uuid.getLeastSignificantBits() != BASE_UUID.getUuid().getLeastSignificantBits())
            return false;
        if(is16BitUuid(parceluuid))
            return false;
        if((uuid.getMostSignificantBits() & 0xffffffffL) == 4096L)
            flag = true;
        return flag;
    }

    public static boolean isAdvAudioDist(ParcelUuid parceluuid)
    {
        return parceluuid.equals(AdvAudioDist);
    }

    public static boolean isAudioSink(ParcelUuid parceluuid)
    {
        return parceluuid.equals(AudioSink);
    }

    public static boolean isAudioSource(ParcelUuid parceluuid)
    {
        return parceluuid.equals(AudioSource);
    }

    public static boolean isAvrcpController(ParcelUuid parceluuid)
    {
        return parceluuid.equals(AvrcpController);
    }

    public static boolean isAvrcpTarget(ParcelUuid parceluuid)
    {
        return parceluuid.equals(AvrcpTarget);
    }

    public static boolean isBnep(ParcelUuid parceluuid)
    {
        return parceluuid.equals(BNEP);
    }

    public static boolean isHandsfree(ParcelUuid parceluuid)
    {
        return parceluuid.equals(Handsfree);
    }

    public static boolean isHeadset(ParcelUuid parceluuid)
    {
        return parceluuid.equals(HSP);
    }

    public static boolean isInputDevice(ParcelUuid parceluuid)
    {
        return parceluuid.equals(Hid);
    }

    public static boolean isMap(ParcelUuid parceluuid)
    {
        return parceluuid.equals(MAP);
    }

    public static boolean isMas(ParcelUuid parceluuid)
    {
        return parceluuid.equals(MAS);
    }

    public static boolean isMns(ParcelUuid parceluuid)
    {
        return parceluuid.equals(MNS);
    }

    public static boolean isNap(ParcelUuid parceluuid)
    {
        return parceluuid.equals(NAP);
    }

    public static boolean isPanu(ParcelUuid parceluuid)
    {
        return parceluuid.equals(PANU);
    }

    public static boolean isSap(ParcelUuid parceluuid)
    {
        return parceluuid.equals(SAP);
    }

    public static boolean isUuidPresent(ParcelUuid aparceluuid[], ParcelUuid parceluuid)
    {
        if((aparceluuid == null || aparceluuid.length == 0) && parceluuid == null)
            return true;
        if(aparceluuid == null)
            return false;
        int i = aparceluuid.length;
        for(int j = 0; j < i; j++)
            if(aparceluuid[j].equals(parceluuid))
                return true;

        return false;
    }

    public static ParcelUuid parseUuidFrom(byte abyte0[])
    {
        if(abyte0 == null)
            throw new IllegalArgumentException("uuidBytes cannot be null");
        int i = abyte0.length;
        if(i != 2 && i != 4 && i != 16)
            throw new IllegalArgumentException((new StringBuilder()).append("uuidBytes length invalid - ").append(i).toString());
        if(i == 16)
        {
            abyte0 = ByteBuffer.wrap(abyte0).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(abyte0.getLong(8), abyte0.getLong(0)));
        }
        long l;
        if(i == 2)
            l = (long)(abyte0[0] & 0xff) + (long)((abyte0[1] & 0xff) << 8);
        else
            l = (long)(abyte0[0] & 0xff) + (long)((abyte0[1] & 0xff) << 8) + (long)((abyte0[2] & 0xff) << 16) + (long)((abyte0[3] & 0xff) << 24);
        return new ParcelUuid(new UUID(BASE_UUID.getUuid().getMostSignificantBits() + (l << 32), BASE_UUID.getUuid().getLeastSignificantBits()));
    }

    public static byte[] uuidToBytes(ParcelUuid parceluuid)
    {
        if(parceluuid == null)
            throw new IllegalArgumentException("uuid cannot be null");
        if(is16BitUuid(parceluuid))
        {
            int i = getServiceIdentifierFromParcelUuid(parceluuid);
            return (new byte[] {
                (byte)(i & 0xff), (byte)((i & 0xff00) >> 8)
            });
        }
        if(is32BitUuid(parceluuid))
        {
            int j = getServiceIdentifierFromParcelUuid(parceluuid);
            return (new byte[] {
                (byte)(j & 0xff), (byte)((j & 0xff00) >> 8), (byte)((0xff0000 & j) >> 16), (byte)((0xff000000 & j) >> 24)
            });
        } else
        {
            long l = parceluuid.getUuid().getMostSignificantBits();
            long l1 = parceluuid.getUuid().getLeastSignificantBits();
            parceluuid = new byte[16];
            ByteBuffer bytebuffer = ByteBuffer.wrap(parceluuid).order(ByteOrder.LITTLE_ENDIAN);
            bytebuffer.putLong(8, l);
            bytebuffer.putLong(0, l1);
            return parceluuid;
        }
    }

    public static final ParcelUuid AdvAudioDist;
    public static final ParcelUuid AudioSink;
    public static final ParcelUuid AudioSource;
    public static final ParcelUuid AvrcpController;
    public static final ParcelUuid AvrcpTarget;
    public static final ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid BNEP = ParcelUuid.fromString("0000000f-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid HSP;
    public static final ParcelUuid HSP_AG = ParcelUuid.fromString("00001112-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid Handsfree;
    public static final ParcelUuid Handsfree_AG = ParcelUuid.fromString("0000111F-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid Hid = ParcelUuid.fromString("00001124-0000-1000-8000-00805f9b34fb");
    public static final ParcelUuid Hogp = ParcelUuid.fromString("00001812-0000-1000-8000-00805f9b34fb");
    public static final ParcelUuid MAP;
    public static final ParcelUuid MAS;
    public static final ParcelUuid MNS;
    public static final ParcelUuid NAP;
    public static final ParcelUuid ObexObjectPush;
    public static final ParcelUuid PANU;
    public static final ParcelUuid PBAP_PCE = ParcelUuid.fromString("0000112e-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid PBAP_PSE = ParcelUuid.fromString("0000112f-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid RESERVED_UUIDS[];
    public static final ParcelUuid SAP;
    public static final int UUID_BYTES_128_BIT = 16;
    public static final int UUID_BYTES_16_BIT = 2;
    public static final int UUID_BYTES_32_BIT = 4;

    static 
    {
        AudioSink = ParcelUuid.fromString("0000110B-0000-1000-8000-00805F9B34FB");
        AudioSource = ParcelUuid.fromString("0000110A-0000-1000-8000-00805F9B34FB");
        AdvAudioDist = ParcelUuid.fromString("0000110D-0000-1000-8000-00805F9B34FB");
        HSP = ParcelUuid.fromString("00001108-0000-1000-8000-00805F9B34FB");
        Handsfree = ParcelUuid.fromString("0000111E-0000-1000-8000-00805F9B34FB");
        AvrcpController = ParcelUuid.fromString("0000110E-0000-1000-8000-00805F9B34FB");
        AvrcpTarget = ParcelUuid.fromString("0000110C-0000-1000-8000-00805F9B34FB");
        ObexObjectPush = ParcelUuid.fromString("00001105-0000-1000-8000-00805f9b34fb");
        PANU = ParcelUuid.fromString("00001115-0000-1000-8000-00805F9B34FB");
        NAP = ParcelUuid.fromString("00001116-0000-1000-8000-00805F9B34FB");
        MAP = ParcelUuid.fromString("00001134-0000-1000-8000-00805F9B34FB");
        MNS = ParcelUuid.fromString("00001133-0000-1000-8000-00805F9B34FB");
        MAS = ParcelUuid.fromString("00001132-0000-1000-8000-00805F9B34FB");
        SAP = ParcelUuid.fromString("0000112D-0000-1000-8000-00805F9B34FB");
        RESERVED_UUIDS = (new ParcelUuid[] {
            AudioSink, AudioSource, AdvAudioDist, HSP, Handsfree, AvrcpController, AvrcpTarget, ObexObjectPush, PANU, NAP, 
            MAP, MNS, MAS, SAP
        });
    }
}
