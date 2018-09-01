// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;
import android.util.Slog;
import android.util.SparseBooleanArray;
import com.android.internal.util.ArrayUtils;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.util.*;
import libcore.util.EmptyArray;

public class NetworkStats
    implements Parcelable
{
    public static class Entry
    {

        public void add(Entry entry)
        {
            rxBytes = rxBytes + entry.rxBytes;
            rxPackets = rxPackets + entry.rxPackets;
            txBytes = txBytes + entry.txBytes;
            txPackets = txPackets + entry.txPackets;
            operations = operations + entry.operations;
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(obj instanceof Entry)
            {
                obj = (Entry)obj;
                boolean flag1 = flag;
                if(uid == ((Entry) (obj)).uid)
                {
                    flag1 = flag;
                    if(set == ((Entry) (obj)).set)
                    {
                        flag1 = flag;
                        if(tag == ((Entry) (obj)).tag)
                        {
                            flag1 = flag;
                            if(metered == ((Entry) (obj)).metered)
                            {
                                flag1 = flag;
                                if(roaming == ((Entry) (obj)).roaming)
                                {
                                    flag1 = flag;
                                    if(rxBytes == ((Entry) (obj)).rxBytes)
                                    {
                                        flag1 = flag;
                                        if(rxPackets == ((Entry) (obj)).rxPackets)
                                        {
                                            flag1 = flag;
                                            if(txBytes == ((Entry) (obj)).txBytes)
                                            {
                                                flag1 = flag;
                                                if(txPackets == ((Entry) (obj)).txPackets)
                                                {
                                                    flag1 = flag;
                                                    if(operations == ((Entry) (obj)).operations)
                                                        flag1 = iface.equals(((Entry) (obj)).iface);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return flag1;
            } else
            {
                return false;
            }
        }

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                Integer.valueOf(uid), Integer.valueOf(set), Integer.valueOf(tag), Integer.valueOf(metered), Integer.valueOf(roaming), iface
            });
        }

        public boolean isEmpty()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(rxBytes == 0L)
            {
                flag1 = flag;
                if(rxPackets == 0L)
                {
                    flag1 = flag;
                    if(txBytes == 0L)
                    {
                        flag1 = flag;
                        if(txPackets == 0L)
                        {
                            flag1 = flag;
                            if(operations == 0L)
                                flag1 = true;
                        }
                    }
                }
            }
            return flag1;
        }

        public boolean isNegative()
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(rxBytes < 0L) goto _L2; else goto _L1
_L1:
            if(rxPackets >= 0L) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(txBytes >= 0L)
            {
                flag1 = flag;
                if(txPackets >= 0L)
                {
                    flag1 = flag;
                    if(operations >= 0L)
                        flag1 = false;
                }
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("iface=").append(iface);
            stringbuilder.append(" uid=").append(uid);
            stringbuilder.append(" set=").append(NetworkStats.setToString(set));
            stringbuilder.append(" tag=").append(NetworkStats.tagToString(tag));
            stringbuilder.append(" metered=").append(NetworkStats.meteredToString(metered));
            stringbuilder.append(" roaming=").append(NetworkStats.roamingToString(roaming));
            stringbuilder.append(" rxBytes=").append(rxBytes);
            stringbuilder.append(" rxPackets=").append(rxPackets);
            stringbuilder.append(" txBytes=").append(txBytes);
            stringbuilder.append(" txPackets=").append(txPackets);
            stringbuilder.append(" operations=").append(operations);
            return stringbuilder.toString();
        }

        public String iface;
        public int metered;
        public long operations;
        public int roaming;
        public long rxBytes;
        public long rxPackets;
        public int set;
        public int tag;
        public long txBytes;
        public long txPackets;
        public int uid;

        public Entry()
        {
            this(NetworkStats.IFACE_ALL, -1, 0, 0, 0L, 0L, 0L, 0L, 0L);
        }

        public Entry(long l, long l1, long l2, long l3, long l4)
        {
            this(NetworkStats.IFACE_ALL, -1, 0, 0, l, l1, l2, l3, l4);
        }

        public Entry(String s, int i, int j, int k, int l, int i1, long l1, long l2, long l3, long l4, 
                long l5)
        {
            iface = s;
            uid = i;
            set = j;
            tag = k;
            metered = l;
            roaming = i1;
            rxBytes = l1;
            rxPackets = l2;
            txBytes = l3;
            txPackets = l4;
            operations = l5;
        }

        public Entry(String s, int i, int j, int k, long l, long l1, long l2, long l3, long l4)
        {
            this(s, i, j, k, 0, 0, l, l1, l2, l3, l4);
        }
    }

    public static interface NonMonotonicObserver
    {

        public abstract void foundNonMonotonic(NetworkStats networkstats, int i, NetworkStats networkstats1, int j, Object obj);
    }


    public NetworkStats(long l, int i)
    {
        elapsedRealtime = l;
        size = 0;
        if(i >= 0)
        {
            capacity = i;
            iface = new String[i];
            uid = new int[i];
            set = new int[i];
            tag = new int[i];
            metered = new int[i];
            roaming = new int[i];
            rxBytes = new long[i];
            rxPackets = new long[i];
            txBytes = new long[i];
            txPackets = new long[i];
            operations = new long[i];
        } else
        {
            capacity = 0;
            iface = EmptyArray.STRING;
            uid = EmptyArray.INT;
            set = EmptyArray.INT;
            tag = EmptyArray.INT;
            metered = EmptyArray.INT;
            roaming = EmptyArray.INT;
            rxBytes = EmptyArray.LONG;
            rxPackets = EmptyArray.LONG;
            txBytes = EmptyArray.LONG;
            txPackets = EmptyArray.LONG;
            operations = EmptyArray.LONG;
        }
    }

    public NetworkStats(Parcel parcel)
    {
        elapsedRealtime = parcel.readLong();
        size = parcel.readInt();
        capacity = parcel.readInt();
        iface = parcel.createStringArray();
        uid = parcel.createIntArray();
        set = parcel.createIntArray();
        tag = parcel.createIntArray();
        metered = parcel.createIntArray();
        roaming = parcel.createIntArray();
        rxBytes = parcel.createLongArray();
        rxPackets = parcel.createLongArray();
        txBytes = parcel.createLongArray();
        txPackets = parcel.createLongArray();
        operations = parcel.createLongArray();
    }

    private Entry addTrafficToApplications(int i, String s, String s1, Entry entry, Entry entry1)
    {
        Entry entry2 = new Entry();
        Entry entry3 = new Entry();
        entry3.iface = s1;
        int j = 0;
        while(j < size) 
        {
            if(!Objects.equals(iface[j], s) || uid[j] == i)
                continue;
            if(entry.rxBytes > 0L)
                entry3.rxBytes = (entry1.rxBytes * rxBytes[j]) / entry.rxBytes;
            else
                entry3.rxBytes = 0L;
            if(entry.rxPackets > 0L)
                entry3.rxPackets = (entry1.rxPackets * rxPackets[j]) / entry.rxPackets;
            else
                entry3.rxPackets = 0L;
            if(entry.txBytes > 0L)
                entry3.txBytes = (entry1.txBytes * txBytes[j]) / entry.txBytes;
            else
                entry3.txBytes = 0L;
            if(entry.txPackets > 0L)
                entry3.txPackets = (entry1.txPackets * txPackets[j]) / entry.txPackets;
            else
                entry3.txPackets = 0L;
            if(entry.operations > 0L)
                entry3.operations = (entry1.operations * operations[j]) / entry.operations;
            else
                entry3.operations = 0L;
            entry3.uid = uid[j];
            entry3.tag = tag[j];
            entry3.set = set[j];
            entry3.metered = metered[j];
            entry3.roaming = roaming[j];
            combineValues(entry3);
            if(tag[j] == 0)
            {
                entry2.add(entry3);
                entry3.set = 1001;
                combineValues(entry3);
            }
            j++;
        }
        return entry2;
    }

    private void deductTrafficFromVpnApp(int i, String s, Entry entry)
    {
        entry.uid = i;
        entry.set = 1002;
        entry.tag = 0;
        entry.iface = s;
        entry.metered = -1;
        entry.roaming = -1;
        combineValues(entry);
        int j = findIndex(s, i, 0, 0, 0, 0);
        if(j != -1)
            tunSubtract(j, this, entry);
        i = findIndex(s, i, 1, 0, 0, 0);
        if(i != -1)
            tunSubtract(i, this, entry);
    }

    private Entry getTotal(Entry entry, HashSet hashset, int i, boolean flag)
    {
        int j;
        if(entry == null)
            entry = new Entry();
        entry.iface = IFACE_ALL;
        entry.uid = i;
        entry.set = -1;
        entry.tag = 0;
        entry.metered = -1;
        entry.roaming = -1;
        entry.rxBytes = 0L;
        entry.rxPackets = 0L;
        entry.txBytes = 0L;
        entry.txPackets = 0L;
        entry.operations = 0L;
        j = 0;
        while(j < size) 
        {
            boolean flag1;
            boolean flag2;
            if(i == -1 || i == uid[j])
                flag1 = true;
            else
                flag1 = false;
            if(hashset != null)
                flag2 = hashset.contains(iface[j]);
            else
                flag2 = true;
            if(flag1 && flag2 && (tag[j] == 0 || !(flag ^ true)))
            {
                entry.rxBytes = entry.rxBytes + rxBytes[j];
                entry.rxPackets = entry.rxPackets + rxPackets[j];
                entry.txBytes = entry.txBytes + txBytes[j];
                entry.txPackets = entry.txPackets + txPackets[j];
                entry.operations = entry.operations + operations[j];
            }
            j++;
        }
        return entry;
    }

    public static String meteredToString(int i)
    {
        switch(i)
        {
        default:
            return "UNKNOWN";

        case -1: 
            return "ALL";

        case 0: // '\0'
            return "NO";

        case 1: // '\001'
            return "YES";
        }
    }

    public static String roamingToString(int i)
    {
        switch(i)
        {
        default:
            return "UNKNOWN";

        case -1: 
            return "ALL";

        case 0: // '\0'
            return "NO";

        case 1: // '\001'
            return "YES";
        }
    }

    public static boolean setMatches(int i, int j)
    {
        boolean flag = true;
        if(i == j)
            return true;
        if(i != -1 || j >= 1000)
            flag = false;
        return flag;
    }

    public static String setToCheckinString(int i)
    {
        switch(i)
        {
        default:
            return "unk";

        case -1: 
            return "all";

        case 0: // '\0'
            return "def";

        case 1: // '\001'
            return "fg";

        case 1001: 
            return "vpnin";

        case 1002: 
            return "vpnout";
        }
    }

    public static String setToString(int i)
    {
        switch(i)
        {
        default:
            return "UNKNOWN";

        case -1: 
            return "ALL";

        case 0: // '\0'
            return "DEFAULT";

        case 1: // '\001'
            return "FOREGROUND";

        case 1001: 
            return "DBG_VPN_IN";

        case 1002: 
            return "DBG_VPN_OUT";
        }
    }

    public static NetworkStats subtract(NetworkStats networkstats, NetworkStats networkstats1, NonMonotonicObserver nonmonotonicobserver, Object obj)
    {
        return subtract(networkstats, networkstats1, nonmonotonicobserver, obj, null);
    }

    public static NetworkStats subtract(NetworkStats networkstats, NetworkStats networkstats1, NonMonotonicObserver nonmonotonicobserver, Object obj, NetworkStats networkstats2)
    {
        long l = networkstats.elapsedRealtime - networkstats1.elapsedRealtime;
        long l1 = l;
        if(l < 0L)
        {
            if(nonmonotonicobserver != null)
                nonmonotonicobserver.foundNonMonotonic(networkstats, -1, networkstats1, -1, obj);
            l1 = 0L;
        }
        Entry entry = new Entry();
        NetworkStats networkstats3;
        int i;
        if(networkstats2 != null && networkstats2.capacity >= networkstats.size)
        {
            networkstats3 = networkstats2;
            networkstats2.size = 0;
            networkstats2.elapsedRealtime = l1;
        } else
        {
            networkstats3 = new NetworkStats(l1, networkstats.size);
        }
        for(i = 0; i < networkstats.size; i++)
        {
            entry.iface = networkstats.iface[i];
            entry.uid = networkstats.uid[i];
            entry.set = networkstats.set[i];
            entry.tag = networkstats.tag[i];
            entry.metered = networkstats.metered[i];
            entry.roaming = networkstats.roaming[i];
            entry.rxBytes = networkstats.rxBytes[i];
            entry.rxPackets = networkstats.rxPackets[i];
            entry.txBytes = networkstats.txBytes[i];
            entry.txPackets = networkstats.txPackets[i];
            entry.operations = networkstats.operations[i];
            int j = networkstats1.findIndexHinted(entry.iface, entry.uid, entry.set, entry.tag, entry.metered, entry.roaming, i);
            if(j != -1)
            {
                entry.rxBytes = entry.rxBytes - networkstats1.rxBytes[j];
                entry.rxPackets = entry.rxPackets - networkstats1.rxPackets[j];
                entry.txBytes = entry.txBytes - networkstats1.txBytes[j];
                entry.txPackets = entry.txPackets - networkstats1.txPackets[j];
                entry.operations = entry.operations - networkstats1.operations[j];
            }
            if(entry.isNegative())
            {
                if(nonmonotonicobserver != null)
                    nonmonotonicobserver.foundNonMonotonic(networkstats, i, networkstats1, j, obj);
                entry.rxBytes = Math.max(entry.rxBytes, 0L);
                entry.rxPackets = Math.max(entry.rxPackets, 0L);
                entry.txBytes = Math.max(entry.txBytes, 0L);
                entry.txPackets = Math.max(entry.txPackets, 0L);
                entry.operations = Math.max(entry.operations, 0L);
            }
            networkstats3.addValues(entry);
        }

        return networkstats3;
    }

    public static String tagToString(int i)
    {
        return (new StringBuilder()).append("0x").append(Integer.toHexString(i)).toString();
    }

    private void tunAdjustmentInit(int i, String s, String s1, Entry entry, Entry entry1)
    {
        Entry entry2 = new Entry();
        for(int j = 0; j < size; j++)
        {
            getValues(j, entry2);
            if(entry2.uid == -1)
                throw new IllegalStateException("Cannot adjust VPN accounting on an iface aggregated NetworkStats.");
            if(entry2.set == 1001 || entry2.set == 1002)
                throw new IllegalStateException("Cannot adjust VPN accounting on a NetworkStats containing SET_DBG_VPN_*");
            if(entry2.uid == i && entry2.tag == 0 && Objects.equals(s1, entry2.iface))
                entry1.add(entry2);
            if(entry2.uid != i && entry2.tag == 0 && Objects.equals(s, entry2.iface))
                entry.add(entry2);
        }

    }

    private static Entry tunGetPool(Entry entry, Entry entry1)
    {
        Entry entry2 = new Entry();
        entry2.rxBytes = Math.min(entry.rxBytes, entry1.rxBytes);
        entry2.rxPackets = Math.min(entry.rxPackets, entry1.rxPackets);
        entry2.txBytes = Math.min(entry.txBytes, entry1.txBytes);
        entry2.txPackets = Math.min(entry.txPackets, entry1.txPackets);
        entry2.operations = Math.min(entry.operations, entry1.operations);
        return entry2;
    }

    private static void tunSubtract(int i, NetworkStats networkstats, Entry entry)
    {
        long l = Math.min(networkstats.rxBytes[i], entry.rxBytes);
        long al[] = networkstats.rxBytes;
        al[i] = al[i] - l;
        entry.rxBytes = entry.rxBytes - l;
        l = Math.min(networkstats.rxPackets[i], entry.rxPackets);
        al = networkstats.rxPackets;
        al[i] = al[i] - l;
        entry.rxPackets = entry.rxPackets - l;
        l = Math.min(networkstats.txBytes[i], entry.txBytes);
        al = networkstats.txBytes;
        al[i] = al[i] - l;
        entry.txBytes = entry.txBytes - l;
        l = Math.min(networkstats.txPackets[i], entry.txPackets);
        networkstats = networkstats.txPackets;
        networkstats[i] = networkstats[i] - l;
        entry.txPackets = entry.txPackets - l;
    }

    public NetworkStats addIfaceValues(String s, long l, long l1, long l2, 
            long l3)
    {
        return addValues(s, -1, 0, 0, l, l1, l2, l3, 0L);
    }

    public NetworkStats addValues(Entry entry)
    {
        if(size >= capacity)
        {
            int i = (Math.max(size, 10) * 3) / 2;
            iface = (String[])Arrays.copyOf(iface, i);
            uid = Arrays.copyOf(uid, i);
            set = Arrays.copyOf(set, i);
            tag = Arrays.copyOf(tag, i);
            metered = Arrays.copyOf(metered, i);
            roaming = Arrays.copyOf(roaming, i);
            rxBytes = Arrays.copyOf(rxBytes, i);
            rxPackets = Arrays.copyOf(rxPackets, i);
            txBytes = Arrays.copyOf(txBytes, i);
            txPackets = Arrays.copyOf(txPackets, i);
            operations = Arrays.copyOf(operations, i);
            capacity = i;
        }
        iface[size] = entry.iface;
        uid[size] = entry.uid;
        set[size] = entry.set;
        tag[size] = entry.tag;
        metered[size] = entry.metered;
        roaming[size] = entry.roaming;
        rxBytes[size] = entry.rxBytes;
        rxPackets[size] = entry.rxPackets;
        txBytes[size] = entry.txBytes;
        txPackets[size] = entry.txPackets;
        operations[size] = entry.operations;
        size = size + 1;
        return this;
    }

    public NetworkStats addValues(String s, int i, int j, int k, int l, int i1, long l1, long l2, long l3, long l4, 
            long l5)
    {
        return addValues(new Entry(s, i, j, k, l, i1, l1, l2, l3, l4, l5));
    }

    public NetworkStats addValues(String s, int i, int j, int k, long l, long l1, long l2, long l3, long l4)
    {
        return addValues(new Entry(s, i, j, k, l, l1, l2, l3, l4));
    }

    public NetworkStats clone()
    {
        NetworkStats networkstats = new NetworkStats(elapsedRealtime, size);
        Entry entry = null;
        for(int i = 0; i < size; i++)
        {
            entry = getValues(i, entry);
            networkstats.addValues(entry);
        }

        return networkstats;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public void combineAllValues(NetworkStats networkstats)
    {
        Entry entry = null;
        for(int i = 0; i < networkstats.size; i++)
        {
            entry = networkstats.getValues(i, entry);
            combineValues(entry);
        }

    }

    public NetworkStats combineValues(Entry entry)
    {
        int i = findIndex(entry.iface, entry.uid, entry.set, entry.tag, entry.metered, entry.roaming);
        if(i == -1)
        {
            addValues(entry);
        } else
        {
            long al[] = rxBytes;
            al[i] = al[i] + entry.rxBytes;
            al = rxPackets;
            al[i] = al[i] + entry.rxPackets;
            al = txBytes;
            al[i] = al[i] + entry.txBytes;
            al = txPackets;
            al[i] = al[i] + entry.txPackets;
            al = operations;
            al[i] = al[i] + entry.operations;
        }
        return this;
    }

    public NetworkStats combineValues(String s, int i, int j, int k, long l, long l1, long l2, long l3, long l4)
    {
        return combineValues(new Entry(s, i, j, k, l, l1, l2, l3, l4));
    }

    public NetworkStats combineValues(String s, int i, int j, long l, long l1, 
            long l2, long l3, long l4)
    {
        return combineValues(s, i, 0, j, l, l1, l2, l3, l4);
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(String s, PrintWriter printwriter)
    {
        printwriter.print(s);
        printwriter.print("NetworkStats: elapsedRealtime=");
        printwriter.println(elapsedRealtime);
        for(int i = 0; i < size; i++)
        {
            printwriter.print(s);
            printwriter.print("  [");
            printwriter.print(i);
            printwriter.print("]");
            printwriter.print(" iface=");
            printwriter.print(iface[i]);
            printwriter.print(" uid=");
            printwriter.print(uid[i]);
            printwriter.print(" set=");
            printwriter.print(setToString(set[i]));
            printwriter.print(" tag=");
            printwriter.print(tagToString(tag[i]));
            printwriter.print(" metered=");
            printwriter.print(meteredToString(metered[i]));
            printwriter.print(" roaming=");
            printwriter.print(roamingToString(roaming[i]));
            printwriter.print(" rxBytes=");
            printwriter.print(rxBytes[i]);
            printwriter.print(" rxPackets=");
            printwriter.print(rxPackets[i]);
            printwriter.print(" txBytes=");
            printwriter.print(txBytes[i]);
            printwriter.print(" txPackets=");
            printwriter.print(txPackets[i]);
            printwriter.print(" operations=");
            printwriter.println(operations[i]);
        }

    }

    public int findIndex(String s, int i, int j, int k, int l, int i1)
    {
        for(int j1 = 0; j1 < size; j1++)
            if(i == uid[j1] && j == set[j1] && k == tag[j1] && l == metered[j1] && i1 == roaming[j1] && Objects.equals(s, iface[j1]))
                return j1;

        return -1;
    }

    public int findIndexHinted(String s, int i, int j, int k, int l, int i1, int j1)
    {
        int l1;
        for(int k1 = 0; k1 < size; k1++)
        {
            l1 = k1 / 2;
            if(k1 % 2 == 0)
                l1 = (j1 + l1) % size;
            else
                l1 = ((size + j1) - l1 - 1) % size;
            if(i == uid[l1] && j == set[l1] && k == tag[l1] && l == metered[l1] && i1 == roaming[l1] && Objects.equals(s, iface[l1]))
                return l1;
        }

        return -1;
    }

    public long getElapsedRealtime()
    {
        return elapsedRealtime;
    }

    public long getElapsedRealtimeAge()
    {
        return SystemClock.elapsedRealtime() - elapsedRealtime;
    }

    public Entry getTotal(Entry entry)
    {
        return getTotal(entry, null, -1, false);
    }

    public Entry getTotal(Entry entry, int i)
    {
        return getTotal(entry, null, i, false);
    }

    public Entry getTotal(Entry entry, HashSet hashset)
    {
        return getTotal(entry, hashset, -1, false);
    }

    public long getTotalBytes()
    {
        Entry entry = getTotal(null);
        return entry.rxBytes + entry.txBytes;
    }

    public Entry getTotalIncludingTags(Entry entry)
    {
        return getTotal(entry, null, -1, true);
    }

    public long getTotalPackets()
    {
        long l = 0L;
        for(int i = size - 1; i >= 0; i--)
            l += rxPackets[i] + txPackets[i];

        return l;
    }

    public String[] getUniqueIfaces()
    {
        HashSet hashset = new HashSet();
        String as[] = iface;
        int i = 0;
        for(int j = as.length; i < j; i++)
        {
            String s = as[i];
            if(s != IFACE_ALL)
                hashset.add(s);
        }

        return (String[])hashset.toArray(new String[hashset.size()]);
    }

    public int[] getUniqueUids()
    {
        SparseBooleanArray sparsebooleanarray = new SparseBooleanArray();
        int ai[] = uid;
        int i = 0;
        for(int k = ai.length; i < k; i++)
            sparsebooleanarray.put(ai[i], true);

        int l = sparsebooleanarray.size();
        ai = new int[l];
        for(int j = 0; j < l; j++)
            ai[j] = sparsebooleanarray.keyAt(j);

        return ai;
    }

    public Entry getValues(int i, Entry entry)
    {
        if(entry == null)
            entry = new Entry();
        entry.iface = iface[i];
        entry.uid = uid[i];
        entry.set = set[i];
        entry.tag = tag[i];
        entry.metered = metered[i];
        entry.roaming = roaming[i];
        entry.rxBytes = rxBytes[i];
        entry.rxPackets = rxPackets[i];
        entry.txBytes = txBytes[i];
        entry.txPackets = txPackets[i];
        entry.operations = operations[i];
        return entry;
    }

    public NetworkStats groupedByIface()
    {
        NetworkStats networkstats = new NetworkStats(elapsedRealtime, 10);
        Entry entry = new Entry();
        entry.uid = -1;
        entry.set = -1;
        entry.tag = 0;
        entry.metered = -1;
        entry.roaming = -1;
        entry.operations = 0L;
        int i = 0;
        while(i < size) 
        {
            if(tag[i] == 0)
            {
                entry.iface = iface[i];
                entry.rxBytes = rxBytes[i];
                entry.rxPackets = rxPackets[i];
                entry.txBytes = txBytes[i];
                entry.txPackets = txPackets[i];
                networkstats.combineValues(entry);
            }
            i++;
        }
        return networkstats;
    }

    public NetworkStats groupedByUid()
    {
        NetworkStats networkstats = new NetworkStats(elapsedRealtime, 10);
        Entry entry = new Entry();
        entry.iface = IFACE_ALL;
        entry.set = -1;
        entry.tag = 0;
        entry.metered = -1;
        entry.roaming = -1;
        int i = 0;
        while(i < size) 
        {
            if(tag[i] == 0)
            {
                entry.uid = uid[i];
                entry.rxBytes = rxBytes[i];
                entry.rxPackets = rxPackets[i];
                entry.txBytes = txBytes[i];
                entry.txPackets = txPackets[i];
                entry.operations = operations[i];
                networkstats.combineValues(entry);
            }
            i++;
        }
        return networkstats;
    }

    public int internalSize()
    {
        return capacity;
    }

    public boolean migrateTun(int i, String s, String s1)
    {
        Entry entry = new Entry();
        Entry entry1 = new Entry();
        tunAdjustmentInit(i, s, s1, entry, entry1);
        entry1 = tunGetPool(entry, entry1);
        if(entry1.isEmpty())
            return true;
        s = addTrafficToApplications(i, s, s1, entry, entry1);
        deductTrafficFromVpnApp(i, s1, s);
        if(!s.isEmpty())
        {
            Slog.wtf("NetworkStats", (new StringBuilder()).append("Failed to deduct underlying network traffic from VPN package. Moved=").append(s).toString());
            return false;
        } else
        {
            return true;
        }
    }

    public void setElapsedRealtime(long l)
    {
        elapsedRealtime = l;
    }

    public int size()
    {
        return size;
    }

    public void spliceOperationsFrom(NetworkStats networkstats)
    {
        int i = 0;
        while(i < size) 
        {
            int j = networkstats.findIndex(iface[i], uid[i], set[i], tag[i], metered[i], roaming[i]);
            if(j == -1)
                operations[i] = 0L;
            else
                operations[i] = networkstats.operations[j];
            i++;
        }
    }

    public NetworkStats subtract(NetworkStats networkstats)
    {
        return subtract(this, networkstats, null, null);
    }

    public String toString()
    {
        CharArrayWriter chararraywriter = new CharArrayWriter();
        dump("", new PrintWriter(chararraywriter));
        return chararraywriter.toString();
    }

    public NetworkStats withoutUids(int ai[])
    {
        NetworkStats networkstats = new NetworkStats(elapsedRealtime, 10);
        Entry entry = new Entry();
        for(int i = 0; i < size; i++)
        {
            entry = getValues(i, entry);
            if(!ArrayUtils.contains(ai, entry.uid))
                networkstats.addValues(entry);
        }

        return networkstats;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(elapsedRealtime);
        parcel.writeInt(size);
        parcel.writeInt(capacity);
        parcel.writeStringArray(iface);
        parcel.writeIntArray(uid);
        parcel.writeIntArray(set);
        parcel.writeIntArray(tag);
        parcel.writeIntArray(metered);
        parcel.writeIntArray(roaming);
        parcel.writeLongArray(rxBytes);
        parcel.writeLongArray(rxPackets);
        parcel.writeLongArray(txBytes);
        parcel.writeLongArray(txPackets);
        parcel.writeLongArray(operations);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkStats createFromParcel(Parcel parcel)
        {
            return new NetworkStats(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkStats[] newArray(int i)
        {
            return new NetworkStats[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String IFACE_ALL;
    public static final int METERED_ALL = -1;
    public static final int METERED_NO = 0;
    public static final int METERED_YES = 1;
    public static final int ROAMING_ALL = -1;
    public static final int ROAMING_NO = 0;
    public static final int ROAMING_YES = 1;
    public static final int SET_ALL = -1;
    public static final int SET_DBG_VPN_IN = 1001;
    public static final int SET_DBG_VPN_OUT = 1002;
    public static final int SET_DEBUG_START = 1000;
    public static final int SET_DEFAULT = 0;
    public static final int SET_FOREGROUND = 1;
    public static final int STATS_PER_IFACE = 0;
    public static final int STATS_PER_UID = 1;
    private static final String TAG = "NetworkStats";
    public static final int TAG_ALL = -1;
    public static final int TAG_NONE = 0;
    public static final int UID_ALL = -1;
    private int capacity;
    private long elapsedRealtime;
    private String iface[];
    private int metered[];
    private long operations[];
    private int roaming[];
    private long rxBytes[];
    private long rxPackets[];
    private int set[];
    private int size;
    private int tag[];
    private long txBytes[];
    private long txPackets[];
    private int uid[];

}
