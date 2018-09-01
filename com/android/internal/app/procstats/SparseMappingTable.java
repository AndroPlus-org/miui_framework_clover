// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app.procstats;

import android.os.Build;
import android.os.Parcel;
import android.util.EventLog;
import android.util.Slog;
import com.android.internal.util.GrowingArrayUtils;
import java.util.ArrayList;
import libcore.util.EmptyArray;

public class SparseMappingTable
{
    public static class Table
    {

        private void assertConsistency()
        {
        }

        private int binarySearch(byte byte0)
        {
            int i = 0;
            for(int j = mSize - 1; i <= j;)
            {
                int k = i + j >>> 1;
                byte byte1 = (byte)(mTable[k] >> 0 & 0xff);
                if(byte1 < byte0)
                    i = k + 1;
                else
                if(byte1 > byte0)
                    j = k - 1;
                else
                    return k;
            }

            return i;
        }

        private boolean validateKeys(boolean flag)
        {
            ArrayList arraylist = SparseMappingTable._2D_get0(mParent);
            int i = arraylist.size();
            int j = mSize;
            for(int k = 0; k < j; k++)
            {
                int l = mTable[k];
                int i1 = SparseMappingTable.getArrayFromKey(l);
                l = SparseMappingTable.getIndexFromKey(l);
                if(i1 >= i || l >= ((long[])arraylist.get(i1)).length)
                {
                    if(flag)
                        Slog.w("SparseMappingTable", (new StringBuilder()).append("Invalid stats at index ").append(k).append(" -- ").append(dumpInternalState()).toString());
                    return false;
                }
            }

            return true;
        }

        public void copyFrom(Table table, int i)
        {
            mTable = null;
            mSize = 0;
            int j = table.getKeyCount();
            for(int k = 0; k < j; k++)
            {
                int l = table.getKeyAt(k);
                long al[] = (long[])SparseMappingTable._2D_get0(table.mParent).get(SparseMappingTable.getArrayFromKey(l));
                int i1 = getOrAddKey(SparseMappingTable.getIdFromKey(l), i);
                long al1[] = (long[])SparseMappingTable._2D_get0(mParent).get(SparseMappingTable.getArrayFromKey(i1));
                System.arraycopy(al, SparseMappingTable.getIndexFromKey(l), al1, SparseMappingTable.getIndexFromKey(i1), i);
            }

        }

        public String dumpInternalState()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("SparseMappingTable.Table{mSequence=");
            stringbuilder.append(mSequence);
            stringbuilder.append(" mParent.mSequence=");
            stringbuilder.append(SparseMappingTable._2D_get2(mParent));
            stringbuilder.append(" mParent.mLongs.size()=");
            stringbuilder.append(SparseMappingTable._2D_get0(mParent).size());
            stringbuilder.append(" mSize=");
            stringbuilder.append(mSize);
            stringbuilder.append(" mTable=");
            if(mTable == null)
            {
                stringbuilder.append("null");
            } else
            {
                int i = mTable.length;
                stringbuilder.append('[');
                for(int j = 0; j < i; j++)
                {
                    int k = mTable[j];
                    stringbuilder.append("0x");
                    stringbuilder.append(Integer.toHexString(k >> 0 & 0xff));
                    stringbuilder.append("/0x");
                    stringbuilder.append(Integer.toHexString(k >> 8 & 0xff));
                    stringbuilder.append("/0x");
                    stringbuilder.append(Integer.toHexString(k >> 16 & 0xffff));
                    if(j != i - 1)
                        stringbuilder.append(", ");
                }

                stringbuilder.append(']');
            }
            stringbuilder.append(" clazz=");
            stringbuilder.append(getClass().getName());
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        public long[] getArrayForKey(int i)
        {
            assertConsistency();
            return (long[])SparseMappingTable._2D_get0(mParent).get(SparseMappingTable.getArrayFromKey(i));
        }

        public int getKey(byte byte0)
        {
            assertConsistency();
            int i = binarySearch(byte0);
            if(i >= 0)
                return mTable[i];
            else
                return -1;
        }

        public int getKeyAt(int i)
        {
            return mTable[i];
        }

        public int getKeyCount()
        {
            return mSize;
        }

        public int getOrAddKey(byte byte0, int i)
        {
            assertConsistency();
            int j = binarySearch(byte0);
            if(j >= 0)
                return mTable[j];
            Object obj = SparseMappingTable._2D_get0(mParent);
            int k = ((ArrayList) (obj)).size() - 1;
            long al[] = (long[])((ArrayList) (obj)).get(k);
            int l = k;
            if(SparseMappingTable._2D_get1(mParent) + i > al.length)
            {
                ((ArrayList) (obj)).add(new long[4096]);
                l = k + 1;
                SparseMappingTable._2D_set0(mParent, 0);
            }
            l = l << 8 | SparseMappingTable._2D_get1(mParent) << 16 | byte0 << 0;
            obj = mParent;
            SparseMappingTable._2D_set0(((SparseMappingTable) (obj)), SparseMappingTable._2D_get1(((SparseMappingTable) (obj))) + i);
            int ai[];
            if(mTable != null)
                ai = mTable;
            else
                ai = EmptyArray.INT;
            mTable = GrowingArrayUtils.insert(ai, mSize, j, l);
            mSize = mSize + 1;
            return l;
        }

        public long getValue(int i)
        {
            return getValue(i, 0);
        }

        public long getValue(int i, int j)
        {
            assertConsistency();
            long l;
            try
            {
                l = ((long[])SparseMappingTable._2D_get0(mParent).get(SparseMappingTable.getArrayFromKey(i)))[SparseMappingTable.getIndexFromKey(i) + j];
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception)
            {
                SparseMappingTable._2D_wrap1((new StringBuilder()).append("key=0x").append(Integer.toHexString(i)).append(" index=").append(j).append(" -- ").append(dumpInternalState()).toString(), indexoutofboundsexception);
                return 0L;
            }
            return l;
        }

        public long getValueForId(byte byte0)
        {
            return getValueForId(byte0, 0);
        }

        public long getValueForId(byte byte0, int i)
        {
            assertConsistency();
            int j = binarySearch(byte0);
            if(j >= 0)
            {
                int k = mTable[j];
                long l;
                try
                {
                    l = ((long[])SparseMappingTable._2D_get0(mParent).get(SparseMappingTable.getArrayFromKey(k)))[SparseMappingTable.getIndexFromKey(k) + i];
                }
                catch(IndexOutOfBoundsException indexoutofboundsexception)
                {
                    SparseMappingTable._2D_wrap1((new StringBuilder()).append("id=0x").append(Integer.toHexString(byte0)).append(" idx=").append(j).append(" key=0x").append(Integer.toHexString(k)).append(" index=").append(i).append(" -- ").append(dumpInternalState()).toString(), indexoutofboundsexception);
                    return 0L;
                }
                return l;
            } else
            {
                return 0L;
            }
        }

        public boolean readFromParcel(Parcel parcel)
        {
            mSequence = parcel.readInt();
            mSize = parcel.readInt();
            if(mSize != 0)
            {
                mTable = new int[mSize];
                for(int i = 0; i < mSize; i++)
                    mTable[i] = parcel.readInt();

            } else
            {
                mTable = null;
            }
            if(validateKeys(true))
            {
                return true;
            } else
            {
                mSize = 0;
                mTable = null;
                return false;
            }
        }

        public void resetTable()
        {
            mTable = null;
            mSize = 0;
            mSequence = SparseMappingTable._2D_get2(mParent);
        }

        public void setValue(int i, int j, long l)
        {
            assertConsistency();
            if(l < 0L)
            {
                SparseMappingTable._2D_wrap0((new StringBuilder()).append("can't store negative values key=0x").append(Integer.toHexString(i)).append(" index=").append(j).append(" value=").append(l).append(" -- ").append(dumpInternalState()).toString());
                return;
            }
            try
            {
                ((long[])SparseMappingTable._2D_get0(mParent).get(SparseMappingTable.getArrayFromKey(i)))[SparseMappingTable.getIndexFromKey(i) + j] = l;
                return;
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception)
            {
                SparseMappingTable._2D_wrap1((new StringBuilder()).append("key=0x").append(Integer.toHexString(i)).append(" index=").append(j).append(" value=").append(l).append(" -- ").append(dumpInternalState()).toString(), indexoutofboundsexception);
            }
        }

        public void setValue(int i, long l)
        {
            setValue(i, 0, l);
        }

        public void writeToParcel(Parcel parcel)
        {
            parcel.writeInt(mSequence);
            parcel.writeInt(mSize);
            for(int i = 0; i < mSize; i++)
                parcel.writeInt(mTable[i]);

        }

        private SparseMappingTable mParent;
        private int mSequence;
        private int mSize;
        private int mTable[];

        public Table(SparseMappingTable sparsemappingtable)
        {
            mSequence = 1;
            mParent = sparsemappingtable;
            mSequence = SparseMappingTable._2D_get2(sparsemappingtable);
        }
    }


    static ArrayList _2D_get0(SparseMappingTable sparsemappingtable)
    {
        return sparsemappingtable.mLongs;
    }

    static int _2D_get1(SparseMappingTable sparsemappingtable)
    {
        return sparsemappingtable.mNextIndex;
    }

    static int _2D_get2(SparseMappingTable sparsemappingtable)
    {
        return sparsemappingtable.mSequence;
    }

    static int _2D_set0(SparseMappingTable sparsemappingtable, int i)
    {
        sparsemappingtable.mNextIndex = i;
        return i;
    }

    static void _2D_wrap0(String s)
    {
        logOrThrow(s);
    }

    static void _2D_wrap1(String s, Throwable throwable)
    {
        logOrThrow(s, throwable);
    }

    public SparseMappingTable()
    {
        mLongs.add(new long[4096]);
    }

    public static int getArrayFromKey(int i)
    {
        return i >> 8 & 0xff;
    }

    public static byte getIdFromKey(int i)
    {
        return (byte)(i >> 0 & 0xff);
    }

    public static int getIndexFromKey(int i)
    {
        return i >> 16 & 0xffff;
    }

    private static void logOrThrow(String s)
    {
        logOrThrow(s, ((Throwable) (new RuntimeException("Stack trace"))));
    }

    private static void logOrThrow(String s, Throwable throwable)
    {
        Slog.e("SparseMappingTable", s, throwable);
        if(Build.IS_ENG)
            throw new RuntimeException(s, throwable);
        else
            return;
    }

    private static void readCompactedLongArray(Parcel parcel, long al[], int i)
    {
        int j = al.length;
        if(i > j)
        {
            logOrThrow((new StringBuilder()).append("bad array lengths: got ").append(i).append(" array is ").append(j).toString());
            return;
        }
        int k = 0;
        int l;
        do
        {
            l = k;
            if(k >= i)
                break;
            l = parcel.readInt();
            if(l >= 0)
            {
                al[k] = l;
            } else
            {
                int i1 = parcel.readInt();
                al[k] = (long)l << 32 | (long)i1;
            }
            k++;
        } while(true);
        for(; l < j; l++)
            al[l] = 0L;

    }

    private static void writeCompactedLongArray(Parcel parcel, long al[], int i)
    {
        int j = 0;
        while(j < i) 
        {
            long l = al[j];
            long l1 = l;
            if(l < 0L)
            {
                Slog.w("SparseMappingTable", (new StringBuilder()).append("Time val negative: ").append(l).toString());
                l1 = 0L;
            }
            if(l1 <= 0x7fffffffL)
            {
                parcel.writeInt((int)l1);
            } else
            {
                int k = (int)(l1 >> 32 & 0x7fffffffL);
                int i1 = (int)(0xffffffffL & l1);
                parcel.writeInt(k);
                parcel.writeInt(i1);
            }
            j++;
        }
    }

    public String dumpInternalState(boolean flag)
    {
        StringBuilder stringbuilder;
        int i;
        stringbuilder = new StringBuilder();
        stringbuilder.append("SparseMappingTable{");
        stringbuilder.append("mSequence=");
        stringbuilder.append(mSequence);
        stringbuilder.append(" mNextIndex=");
        stringbuilder.append(mNextIndex);
        stringbuilder.append(" mLongs.size=");
        i = mLongs.size();
        stringbuilder.append(i);
        stringbuilder.append("\n");
        if(!flag) goto _L2; else goto _L1
_L1:
        int j = 0;
_L4:
        if(j < i)
        {
            long al[] = (long[])mLongs.get(j);
            int k = 0;
            do
            {
                if(k >= al.length || j == i - 1 && k == mNextIndex)
                {
                    j++;
                    continue; /* Loop/switch isn't completed */
                }
                stringbuilder.append(String.format(" %4d %d 0x%016x %-19d\n", new Object[] {
                    Integer.valueOf(j), Integer.valueOf(k), Long.valueOf(al[k]), Long.valueOf(al[k])
                }));
                k++;
            } while(true);
        }
_L2:
        stringbuilder.append("}");
        return stringbuilder.toString();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void readFromParcel(Parcel parcel)
    {
        mSequence = parcel.readInt();
        mNextIndex = parcel.readInt();
        mLongs.clear();
        int i = parcel.readInt();
        for(int j = 0; j < i; j++)
        {
            int k = parcel.readInt();
            long al[] = new long[k];
            readCompactedLongArray(parcel, al, k);
            mLongs.add(al);
        }

        if(i > 0 && ((long[])mLongs.get(i - 1)).length != mNextIndex)
        {
            EventLog.writeEvent(0x534e4554, new Object[] {
                "73252178", Integer.valueOf(-1), ""
            });
            throw new IllegalStateException((new StringBuilder()).append("Expected array of length ").append(mNextIndex).append(" but was ").append(((long[])mLongs.get(i - 1)).length).toString());
        } else
        {
            return;
        }
    }

    public void reset()
    {
        mLongs.clear();
        mLongs.add(new long[4096]);
        mNextIndex = 0;
        mSequence = mSequence + 1;
    }

    public void writeToParcel(Parcel parcel)
    {
        parcel.writeInt(mSequence);
        parcel.writeInt(mNextIndex);
        int i = mLongs.size();
        parcel.writeInt(i);
        for(int j = 0; j < i - 1; j++)
        {
            long al[] = (long[])mLongs.get(j);
            parcel.writeInt(al.length);
            writeCompactedLongArray(parcel, al, al.length);
        }

        long al1[] = (long[])mLongs.get(i - 1);
        parcel.writeInt(mNextIndex);
        writeCompactedLongArray(parcel, al1, mNextIndex);
    }

    private static final int ARRAY_MASK = 255;
    private static final int ARRAY_SHIFT = 8;
    public static final int ARRAY_SIZE = 4096;
    private static final int ID_MASK = 255;
    private static final int ID_SHIFT = 0;
    private static final int INDEX_MASK = 65535;
    private static final int INDEX_SHIFT = 16;
    public static final int INVALID_KEY = -1;
    private static final String TAG = "SparseMappingTable";
    private final ArrayList mLongs = new ArrayList();
    private int mNextIndex;
    private int mSequence;
}
