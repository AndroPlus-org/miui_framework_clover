// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.MathUtils;
import android.util.proto.ProtoOutputStream;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.IndentingPrintWriter;
import java.io.*;
import java.net.ProtocolException;
import java.util.Arrays;
import java.util.Random;

// Referenced classes of package android.net:
//            NetworkStats

public class NetworkStatsHistory
    implements Parcelable
{
    public static class DataStreamUtils
    {

        public static long[] readFullLongArray(DataInputStream datainputstream)
            throws IOException
        {
            int i = datainputstream.readInt();
            if(i < 0)
                throw new ProtocolException("negative array size");
            long al[] = new long[i];
            for(int j = 0; j < al.length; j++)
                al[j] = datainputstream.readLong();

            return al;
        }

        public static long readVarLong(DataInputStream datainputstream)
            throws IOException
        {
            int i = 0;
            long l = 0L;
            for(; i < 64; i += 7)
            {
                byte byte0 = datainputstream.readByte();
                l |= (long)(byte0 & 0x7f) << i;
                if((byte0 & 0x80) == 0)
                    return l;
            }

            throw new ProtocolException("malformed long");
        }

        public static long[] readVarLongArray(DataInputStream datainputstream)
            throws IOException
        {
            int i = datainputstream.readInt();
            if(i == -1)
                return null;
            if(i < 0)
                throw new ProtocolException("negative array size");
            long al[] = new long[i];
            for(int j = 0; j < al.length; j++)
                al[j] = readVarLong(datainputstream);

            return al;
        }

        public static void writeVarLong(DataOutputStream dataoutputstream, long l)
            throws IOException
        {
            do
            {
                if((-128L & l) == 0L)
                {
                    dataoutputstream.writeByte((int)l);
                    return;
                }
                dataoutputstream.writeByte((int)l & 0x7f | 0x80);
                l >>>= 7;
            } while(true);
        }

        public static void writeVarLongArray(DataOutputStream dataoutputstream, long al[], int i)
            throws IOException
        {
            if(al == null)
            {
                dataoutputstream.writeInt(-1);
                return;
            }
            if(i > al.length)
                throw new IllegalArgumentException("size larger than length");
            dataoutputstream.writeInt(i);
            for(int j = 0; j < i; j++)
                writeVarLong(dataoutputstream, al[j]);

        }

        public DataStreamUtils()
        {
        }
    }

    public static class Entry
    {

        public static final long UNKNOWN = -1L;
        public long activeTime;
        public long bucketDuration;
        public long bucketStart;
        public long operations;
        public long rxBytes;
        public long rxPackets;
        public long txBytes;
        public long txPackets;

        public Entry()
        {
        }
    }

    public static class ParcelUtils
    {

        public static long[] readLongArray(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == -1)
                return null;
            long al[] = new long[i];
            for(int j = 0; j < al.length; j++)
                al[j] = parcel.readLong();

            return al;
        }

        public static void writeLongArray(Parcel parcel, long al[], int i)
        {
            if(al == null)
            {
                parcel.writeInt(-1);
                return;
            }
            if(i > al.length)
                throw new IllegalArgumentException("size larger than length");
            parcel.writeInt(i);
            for(int j = 0; j < i; j++)
                parcel.writeLong(al[j]);

        }

        public ParcelUtils()
        {
        }
    }


    public NetworkStatsHistory(long l)
    {
        this(l, 10, -1);
    }

    public NetworkStatsHistory(long l, int i)
    {
        this(l, i, -1);
    }

    public NetworkStatsHistory(long l, int i, int j)
    {
        bucketDuration = l;
        bucketStart = new long[i];
        if((j & 1) != 0)
            activeTime = new long[i];
        if((j & 2) != 0)
            rxBytes = new long[i];
        if((j & 4) != 0)
            rxPackets = new long[i];
        if((j & 8) != 0)
            txBytes = new long[i];
        if((j & 0x10) != 0)
            txPackets = new long[i];
        if((j & 0x20) != 0)
            operations = new long[i];
        bucketCount = 0;
        totalBytes = 0L;
    }

    public NetworkStatsHistory(NetworkStatsHistory networkstatshistory, long l)
    {
        this(l, networkstatshistory.estimateResizeBuckets(l));
        recordEntireHistory(networkstatshistory);
    }

    public NetworkStatsHistory(Parcel parcel)
    {
        bucketDuration = parcel.readLong();
        bucketStart = ParcelUtils.readLongArray(parcel);
        activeTime = ParcelUtils.readLongArray(parcel);
        rxBytes = ParcelUtils.readLongArray(parcel);
        rxPackets = ParcelUtils.readLongArray(parcel);
        txBytes = ParcelUtils.readLongArray(parcel);
        txPackets = ParcelUtils.readLongArray(parcel);
        operations = ParcelUtils.readLongArray(parcel);
        bucketCount = bucketStart.length;
        totalBytes = parcel.readLong();
    }

    public NetworkStatsHistory(DataInputStream datainputstream)
        throws IOException
    {
        int i;
        i = datainputstream.readInt();
        switch(i)
        {
        default:
            throw new ProtocolException((new StringBuilder()).append("unexpected version: ").append(i).toString());

        case 2: // '\002'
        case 3: // '\003'
            break MISSING_BLOCK_LABEL_190;

        case 1: // '\001'
            bucketDuration = datainputstream.readLong();
            bucketStart = DataStreamUtils.readFullLongArray(datainputstream);
            rxBytes = DataStreamUtils.readFullLongArray(datainputstream);
            rxPackets = new long[bucketStart.length];
            txBytes = DataStreamUtils.readFullLongArray(datainputstream);
            txPackets = new long[bucketStart.length];
            operations = new long[bucketStart.length];
            bucketCount = bucketStart.length;
            totalBytes = ArrayUtils.total(rxBytes) + ArrayUtils.total(txBytes);
            break;
        }
          goto _L1
        bucketDuration = datainputstream.readLong();
        bucketStart = DataStreamUtils.readVarLongArray(datainputstream);
        long al[];
        if(i >= 3)
            al = DataStreamUtils.readVarLongArray(datainputstream);
        else
            al = new long[bucketStart.length];
        activeTime = al;
        rxBytes = DataStreamUtils.readVarLongArray(datainputstream);
        rxPackets = DataStreamUtils.readVarLongArray(datainputstream);
        txBytes = DataStreamUtils.readVarLongArray(datainputstream);
        txPackets = DataStreamUtils.readVarLongArray(datainputstream);
        operations = DataStreamUtils.readVarLongArray(datainputstream);
        bucketCount = bucketStart.length;
        totalBytes = ArrayUtils.total(rxBytes) + ArrayUtils.total(txBytes);
_L1:
        if(bucketStart.length != bucketCount || rxBytes.length != bucketCount || rxPackets.length != bucketCount || txBytes.length != bucketCount || txPackets.length != bucketCount || operations.length != bucketCount)
            throw new ProtocolException("Mismatched history lengths");
        else
            return;
    }

    private static void addLong(long al[], int i, long l)
    {
        if(al != null)
            al[i] = al[i] + l;
    }

    private void ensureBuckets(long l, long l1)
    {
        long l2 = bucketDuration;
        long l3 = bucketDuration;
        long l4 = bucketDuration;
        long l5 = bucketDuration;
        for(l -= l % l2; l < l1 + (l3 - l1 % l4) % l5; l += bucketDuration)
        {
            int i = Arrays.binarySearch(bucketStart, 0, bucketCount, l);
            if(i < 0)
                insertBucket(i, l);
        }

    }

    private static long getLong(long al[], int i, long l)
    {
        if(al != null)
            l = al[i];
        return l;
    }

    private void insertBucket(int i, long l)
    {
        if(bucketCount >= bucketStart.length)
        {
            int j = (Math.max(bucketStart.length, 10) * 3) / 2;
            bucketStart = Arrays.copyOf(bucketStart, j);
            if(activeTime != null)
                activeTime = Arrays.copyOf(activeTime, j);
            if(rxBytes != null)
                rxBytes = Arrays.copyOf(rxBytes, j);
            if(rxPackets != null)
                rxPackets = Arrays.copyOf(rxPackets, j);
            if(txBytes != null)
                txBytes = Arrays.copyOf(txBytes, j);
            if(txPackets != null)
                txPackets = Arrays.copyOf(txPackets, j);
            if(operations != null)
                operations = Arrays.copyOf(operations, j);
        }
        if(i < bucketCount)
        {
            int i1 = i + 1;
            int k = bucketCount - i;
            System.arraycopy(bucketStart, i, bucketStart, i1, k);
            if(activeTime != null)
                System.arraycopy(activeTime, i, activeTime, i1, k);
            if(rxBytes != null)
                System.arraycopy(rxBytes, i, rxBytes, i1, k);
            if(rxPackets != null)
                System.arraycopy(rxPackets, i, rxPackets, i1, k);
            if(txBytes != null)
                System.arraycopy(txBytes, i, txBytes, i1, k);
            if(txPackets != null)
                System.arraycopy(txPackets, i, txPackets, i1, k);
            if(operations != null)
                System.arraycopy(operations, i, operations, i1, k);
        }
        bucketStart[i] = l;
        setLong(activeTime, i, 0L);
        setLong(rxBytes, i, 0L);
        setLong(rxPackets, i, 0L);
        setLong(txBytes, i, 0L);
        setLong(txPackets, i, 0L);
        setLong(operations, i, 0L);
        bucketCount = bucketCount + 1;
    }

    public static long randomLong(Random random, long l, long l1)
    {
        return (long)((float)l + random.nextFloat() * (float)(l1 - l));
    }

    private static void setLong(long al[], int i, long l)
    {
        if(al != null)
            al[i] = l;
    }

    private static void writeToProto(ProtoOutputStream protooutputstream, long l, long al[], int i)
    {
        if(al != null)
            protooutputstream.write(l, al[i]);
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(IndentingPrintWriter indentingprintwriter, boolean flag)
    {
        indentingprintwriter.print("NetworkStatsHistory: bucketDuration=");
        indentingprintwriter.println(bucketDuration / 1000L);
        indentingprintwriter.increaseIndent();
        int i;
        if(flag)
            i = 0;
        else
            i = Math.max(0, bucketCount - 32);
        if(i > 0)
        {
            indentingprintwriter.print("(omitting ");
            indentingprintwriter.print(i);
            indentingprintwriter.println(" buckets)");
        }
        for(; i < bucketCount; i++)
        {
            indentingprintwriter.print("st=");
            indentingprintwriter.print(bucketStart[i] / 1000L);
            if(rxBytes != null)
            {
                indentingprintwriter.print(" rb=");
                indentingprintwriter.print(rxBytes[i]);
            }
            if(rxPackets != null)
            {
                indentingprintwriter.print(" rp=");
                indentingprintwriter.print(rxPackets[i]);
            }
            if(txBytes != null)
            {
                indentingprintwriter.print(" tb=");
                indentingprintwriter.print(txBytes[i]);
            }
            if(txPackets != null)
            {
                indentingprintwriter.print(" tp=");
                indentingprintwriter.print(txPackets[i]);
            }
            if(operations != null)
            {
                indentingprintwriter.print(" op=");
                indentingprintwriter.print(operations[i]);
            }
            indentingprintwriter.println();
        }

        indentingprintwriter.decreaseIndent();
    }

    public void dumpCheckin(PrintWriter printwriter)
    {
        printwriter.print("d,");
        printwriter.print(bucketDuration / 1000L);
        printwriter.println();
        int i = 0;
        while(i < bucketCount) 
        {
            printwriter.print("b,");
            printwriter.print(bucketStart[i] / 1000L);
            printwriter.print(',');
            if(rxBytes != null)
                printwriter.print(rxBytes[i]);
            else
                printwriter.print("*");
            printwriter.print(',');
            if(rxPackets != null)
                printwriter.print(rxPackets[i]);
            else
                printwriter.print("*");
            printwriter.print(',');
            if(txBytes != null)
                printwriter.print(txBytes[i]);
            else
                printwriter.print("*");
            printwriter.print(',');
            if(txPackets != null)
                printwriter.print(txPackets[i]);
            else
                printwriter.print("*");
            printwriter.print(',');
            if(operations != null)
                printwriter.print(operations[i]);
            else
                printwriter.print("*");
            printwriter.println();
            i++;
        }
    }

    public int estimateResizeBuckets(long l)
    {
        return (int)(((long)size() * getBucketDuration()) / l);
    }

    public void generateRandom(long l, long l1, long l2)
    {
        Random random = new Random();
        float f = random.nextFloat();
        long l3 = (long)((float)l2 * f);
        l2 = (long)((float)l2 * (1.0F - f));
        generateRandom(l, l1, l3, l3 / 1024L, l2, l2 / 1024L, l3 / 2048L, random);
    }

    public void generateRandom(long l, long l1, long l2, long l3, long l4, long l5, long l6, 
            Random random)
    {
        ensureBuckets(l, l1);
        NetworkStats.Entry entry = new NetworkStats.Entry(NetworkStats.IFACE_ALL, -1, 0, 0, 0L, 0L, 0L, 0L, 0L);
        while(l2 > 1024L || l3 > 128L || l4 > 1024L || l5 > 128L || l6 > 32L) 
        {
            long l7 = randomLong(random, l, l1);
            long l8 = randomLong(random, 0L, (l1 - l7) / 2L);
            entry.rxBytes = randomLong(random, 0L, l2);
            entry.rxPackets = randomLong(random, 0L, l3);
            entry.txBytes = randomLong(random, 0L, l4);
            entry.txPackets = randomLong(random, 0L, l5);
            entry.operations = randomLong(random, 0L, l6);
            l2 -= entry.rxBytes;
            l3 -= entry.rxPackets;
            l4 -= entry.txBytes;
            l5 -= entry.txPackets;
            l6 -= entry.operations;
            recordData(l7, l7 + l8, entry);
        }
    }

    public long getBucketDuration()
    {
        return bucketDuration;
    }

    public long getEnd()
    {
        if(bucketCount > 0)
            return bucketStart[bucketCount - 1] + bucketDuration;
        else
            return 0x8000000000000000L;
    }

    public int getIndexAfter(long l)
    {
        int i = Arrays.binarySearch(bucketStart, 0, bucketCount, l);
        if(i < 0)
            i = i;
        else
            i++;
        return MathUtils.constrain(i, 0, bucketCount - 1);
    }

    public int getIndexBefore(long l)
    {
        int i = Arrays.binarySearch(bucketStart, 0, bucketCount, l);
        if(i < 0)
            i--;
        else
            i--;
        return MathUtils.constrain(i, 0, bucketCount - 1);
    }

    public long getStart()
    {
        if(bucketCount > 0)
            return bucketStart[0];
        else
            return 0x7fffffffffffffffL;
    }

    public long getTotalBytes()
    {
        return totalBytes;
    }

    public Entry getValues(int i, Entry entry)
    {
        if(entry == null)
            entry = new Entry();
        entry.bucketStart = bucketStart[i];
        entry.bucketDuration = bucketDuration;
        entry.activeTime = getLong(activeTime, i, -1L);
        entry.rxBytes = getLong(rxBytes, i, -1L);
        entry.rxPackets = getLong(rxPackets, i, -1L);
        entry.txBytes = getLong(txBytes, i, -1L);
        entry.txPackets = getLong(txPackets, i, -1L);
        entry.operations = getLong(operations, i, -1L);
        return entry;
    }

    public Entry getValues(long l, long l1, long l2, Entry entry)
    {
        long l3;
        int i;
        long l4;
        if(entry == null)
            entry = new Entry();
        entry.bucketDuration = l1 - l;
        entry.bucketStart = l;
        if(activeTime != null)
            l3 = 0L;
        else
            l3 = -1L;
        entry.activeTime = l3;
        if(rxBytes != null)
            l3 = 0L;
        else
            l3 = -1L;
        entry.rxBytes = l3;
        if(rxPackets != null)
            l3 = 0L;
        else
            l3 = -1L;
        entry.rxPackets = l3;
        if(txBytes != null)
            l3 = 0L;
        else
            l3 = -1L;
        entry.txBytes = l3;
        if(txPackets != null)
            l3 = 0L;
        else
            l3 = -1L;
        entry.txPackets = l3;
        if(operations != null)
            l3 = 0L;
        else
            l3 = -1L;
        entry.operations = l3;
        i = getIndexAfter(l1);
_L2:
label0:
        {
            if(i >= 0)
            {
                l4 = bucketStart[i];
                l3 = l4 + bucketDuration;
                if(l3 > l)
                    break label0;
            }
            return entry;
        }
        if(l4 < l1)
            break; /* Loop/switch isn't completed */
_L3:
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        boolean flag;
        if(l4 < l2 && l3 > l2)
            flag = true;
        else
            flag = false;
        if(flag)
        {
            l3 = bucketDuration;
        } else
        {
            if(l3 >= l1)
                l3 = l1;
            if(l4 <= l)
                l4 = l;
            l3 -= l4;
        }
        if(l3 > 0L)
        {
            if(activeTime != null)
                entry.activeTime = entry.activeTime + (activeTime[i] * l3) / bucketDuration;
            if(rxBytes != null)
                entry.rxBytes = entry.rxBytes + (rxBytes[i] * l3) / bucketDuration;
            if(rxPackets != null)
                entry.rxPackets = entry.rxPackets + (rxPackets[i] * l3) / bucketDuration;
            if(txBytes != null)
                entry.txBytes = entry.txBytes + (txBytes[i] * l3) / bucketDuration;
            if(txPackets != null)
                entry.txPackets = entry.txPackets + (txPackets[i] * l3) / bucketDuration;
            if(operations != null)
                entry.operations = entry.operations + (operations[i] * l3) / bucketDuration;
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    public Entry getValues(long l, long l1, Entry entry)
    {
        return getValues(l, l1, 0x7fffffffffffffffL, entry);
    }

    public boolean intersects(long l, long l1)
    {
        long l2 = getStart();
        long l3 = getEnd();
        if(l >= l2 && l <= l3)
            return true;
        if(l1 >= l2 && l1 <= l3)
            return true;
        if(l2 >= l && l2 <= l1)
            return true;
        return l3 >= l && l3 <= l1;
    }

    public void recordData(long l, long l1, long l2, long l3)
    {
        recordData(l, l1, new NetworkStats.Entry(NetworkStats.IFACE_ALL, -1, 0, 0, l2, 0L, l3, 0L, 0L));
    }

    public void recordData(long l, long l1, NetworkStats.Entry entry)
    {
        long l2;
        long l3;
        long l4;
        long l5;
        long l6;
        long l7;
        int i;
        l2 = entry.rxBytes;
        l3 = entry.rxPackets;
        l4 = entry.txBytes;
        l5 = entry.txPackets;
        l6 = entry.operations;
        if(entry.isNegative())
            throw new IllegalArgumentException("tried recording negative data");
        if(entry.isEmpty())
            return;
        ensureBuckets(l, l1);
        l7 = l1 - l;
        i = getIndexAfter(l1);
_L2:
        long l8;
        long l9;
        long l10;
        long l11;
        long l12;
        long l13;
label0:
        {
            if(i >= 0)
            {
                l8 = bucketStart[i];
                l9 = l8 + bucketDuration;
                if(l9 >= l)
                    break label0;
            }
            totalBytes = totalBytes + (entry.rxBytes + entry.txBytes);
            return;
        }
        if(l8 <= l1)
            break; /* Loop/switch isn't completed */
        l10 = l5;
        l11 = l4;
        l12 = l3;
        l13 = l2;
        l9 = l6;
        l8 = l7;
_L3:
        i--;
        l7 = l8;
        l6 = l9;
        l2 = l13;
        l3 = l12;
        l4 = l11;
        l5 = l10;
        if(true) goto _L2; else goto _L1
_L1:
        long l14 = Math.min(l9, l1) - Math.max(l8, l);
        l8 = l7;
        l9 = l6;
        l13 = l2;
        l12 = l3;
        l11 = l4;
        l10 = l5;
        if(l14 > 0L)
        {
            l13 = (l2 * l14) / l7;
            l12 = (l3 * l14) / l7;
            l11 = (l4 * l14) / l7;
            l9 = (l5 * l14) / l7;
            l8 = (l6 * l14) / l7;
            addLong(activeTime, i, l14);
            addLong(rxBytes, i, l13);
            l13 = l2 - l13;
            addLong(rxPackets, i, l12);
            l12 = l3 - l12;
            addLong(txBytes, i, l11);
            l11 = l4 - l11;
            addLong(txPackets, i, l9);
            l10 = l5 - l9;
            addLong(operations, i, l8);
            l9 = l6 - l8;
            l8 = l7 - l14;
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    public void recordEntireHistory(NetworkStatsHistory networkstatshistory)
    {
        recordHistory(networkstatshistory, 0x8000000000000000L, 0x7fffffffffffffffL);
    }

    public void recordHistory(NetworkStatsHistory networkstatshistory, long l, long l1)
    {
        NetworkStats.Entry entry = new NetworkStats.Entry(NetworkStats.IFACE_ALL, -1, 0, 0, 0L, 0L, 0L, 0L, 0L);
        int i = 0;
        while(i < networkstatshistory.bucketCount) 
        {
            long l2 = networkstatshistory.bucketStart[i];
            long l3 = l2 + networkstatshistory.bucketDuration;
            if(l2 >= l && l3 <= l1)
            {
                entry.rxBytes = getLong(networkstatshistory.rxBytes, i, 0L);
                entry.rxPackets = getLong(networkstatshistory.rxPackets, i, 0L);
                entry.txBytes = getLong(networkstatshistory.txBytes, i, 0L);
                entry.txPackets = getLong(networkstatshistory.txPackets, i, 0L);
                entry.operations = getLong(networkstatshistory.operations, i, 0L);
                recordData(l2, l3, entry);
            }
            i++;
        }
    }

    public void removeBucketsBefore(long l)
    {
        int i = 0;
        do
        {
            if(i >= bucketCount || bucketStart[i] + bucketDuration > l)
            {
                if(i > 0)
                {
                    int j = bucketStart.length;
                    bucketStart = Arrays.copyOfRange(bucketStart, i, j);
                    if(activeTime != null)
                        activeTime = Arrays.copyOfRange(activeTime, i, j);
                    if(rxBytes != null)
                        rxBytes = Arrays.copyOfRange(rxBytes, i, j);
                    if(rxPackets != null)
                        rxPackets = Arrays.copyOfRange(rxPackets, i, j);
                    if(txBytes != null)
                        txBytes = Arrays.copyOfRange(txBytes, i, j);
                    if(txPackets != null)
                        txPackets = Arrays.copyOfRange(txPackets, i, j);
                    if(operations != null)
                        operations = Arrays.copyOfRange(operations, i, j);
                    bucketCount = bucketCount - i;
                }
                return;
            }
            i++;
        } while(true);
    }

    public void setValues(int i, Entry entry)
    {
        if(rxBytes != null)
            totalBytes = totalBytes - rxBytes[i];
        if(txBytes != null)
            totalBytes = totalBytes - txBytes[i];
        bucketStart[i] = entry.bucketStart;
        setLong(activeTime, i, entry.activeTime);
        setLong(rxBytes, i, entry.rxBytes);
        setLong(rxPackets, i, entry.rxPackets);
        setLong(txBytes, i, entry.txBytes);
        setLong(txPackets, i, entry.txPackets);
        setLong(operations, i, entry.operations);
        if(rxBytes != null)
            totalBytes = totalBytes + rxBytes[i];
        if(txBytes != null)
            totalBytes = totalBytes + txBytes[i];
    }

    public int size()
    {
        return bucketCount;
    }

    public String toString()
    {
        CharArrayWriter chararraywriter = new CharArrayWriter();
        dump(new IndentingPrintWriter(chararraywriter, "  "), false);
        return chararraywriter.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(bucketDuration);
        ParcelUtils.writeLongArray(parcel, bucketStart, bucketCount);
        ParcelUtils.writeLongArray(parcel, activeTime, bucketCount);
        ParcelUtils.writeLongArray(parcel, rxBytes, bucketCount);
        ParcelUtils.writeLongArray(parcel, rxPackets, bucketCount);
        ParcelUtils.writeLongArray(parcel, txBytes, bucketCount);
        ParcelUtils.writeLongArray(parcel, txPackets, bucketCount);
        ParcelUtils.writeLongArray(parcel, operations, bucketCount);
        parcel.writeLong(totalBytes);
    }

    public void writeToProto(ProtoOutputStream protooutputstream, long l)
    {
        l = protooutputstream.start(l);
        protooutputstream.write(0x10400000001L, bucketDuration);
        for(int i = 0; i < bucketCount; i++)
        {
            long l1 = protooutputstream.start(0x21100000002L);
            protooutputstream.write(0x10400000001L, bucketStart[i]);
            writeToProto(protooutputstream, 0x10400000002L, rxBytes, i);
            writeToProto(protooutputstream, 0x10400000003L, rxPackets, i);
            writeToProto(protooutputstream, 0x10400000004L, txBytes, i);
            writeToProto(protooutputstream, 0x10400000005L, txPackets, i);
            writeToProto(protooutputstream, 0x10400000006L, operations, i);
            protooutputstream.end(l1);
        }

        protooutputstream.end(l);
    }

    public void writeToStream(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(3);
        dataoutputstream.writeLong(bucketDuration);
        DataStreamUtils.writeVarLongArray(dataoutputstream, bucketStart, bucketCount);
        DataStreamUtils.writeVarLongArray(dataoutputstream, activeTime, bucketCount);
        DataStreamUtils.writeVarLongArray(dataoutputstream, rxBytes, bucketCount);
        DataStreamUtils.writeVarLongArray(dataoutputstream, rxPackets, bucketCount);
        DataStreamUtils.writeVarLongArray(dataoutputstream, txBytes, bucketCount);
        DataStreamUtils.writeVarLongArray(dataoutputstream, txPackets, bucketCount);
        DataStreamUtils.writeVarLongArray(dataoutputstream, operations, bucketCount);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkStatsHistory createFromParcel(Parcel parcel)
        {
            return new NetworkStatsHistory(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkStatsHistory[] newArray(int i)
        {
            return new NetworkStatsHistory[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FIELD_ACTIVE_TIME = 1;
    public static final int FIELD_ALL = -1;
    public static final int FIELD_OPERATIONS = 32;
    public static final int FIELD_RX_BYTES = 2;
    public static final int FIELD_RX_PACKETS = 4;
    public static final int FIELD_TX_BYTES = 8;
    public static final int FIELD_TX_PACKETS = 16;
    private static final int VERSION_ADD_ACTIVE = 3;
    private static final int VERSION_ADD_PACKETS = 2;
    private static final int VERSION_INIT = 1;
    private long activeTime[];
    private int bucketCount;
    private long bucketDuration;
    private long bucketStart[];
    private long operations[];
    private long rxBytes[];
    private long rxPackets[];
    private long totalBytes;
    private long txBytes[];
    private long txPackets[];

}
