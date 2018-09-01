// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hidl.base.V1_0;

import android.os.*;
import java.util.ArrayList;
import java.util.Objects;

public final class DebugInfo
{
    public static final class Architecture
    {

        public static final String dumpBitfield(int i)
        {
            ArrayList arraylist = new ArrayList();
            int j = 0;
            arraylist.add("UNKNOWN");
            if((i & 1) == 1)
            {
                arraylist.add("IS_64BIT");
                j = 1;
            }
            int k = j;
            if((i & 2) == 2)
            {
                arraylist.add("IS_32BIT");
                k = j | 2;
            }
            if(i != k)
                arraylist.add((new StringBuilder()).append("0x").append(Integer.toHexString(k & i)).toString());
            return String.join(" | ", arraylist);
        }

        public static final String toString(int i)
        {
            if(i == 0)
                return "UNKNOWN";
            if(i == 1)
                return "IS_64BIT";
            if(i == 2)
                return "IS_32BIT";
            else
                return (new StringBuilder()).append("0x").append(Integer.toHexString(i)).toString();
        }

        public static final int IS_32BIT = 2;
        public static final int IS_64BIT = 1;
        public static final int UNKNOWN = 0;

        public Architecture()
        {
        }
    }


    public DebugInfo()
    {
    }

    public static final ArrayList readVectorFromParcel(HwParcel hwparcel)
    {
        ArrayList arraylist = new ArrayList();
        HwBlob hwblob = hwparcel.readBuffer(16L);
        int i = hwblob.getInt32(8L);
        hwblob = hwparcel.readEmbeddedBuffer(i * 24, hwblob.handle(), 0L, true);
        arraylist.clear();
        for(int j = 0; j < i; j++)
        {
            DebugInfo debuginfo = new DebugInfo();
            debuginfo.readEmbeddedFromParcel(hwparcel, hwblob, j * 24);
            arraylist.add(debuginfo);
        }

        return arraylist;
    }

    public static final void writeVectorToParcel(HwParcel hwparcel, ArrayList arraylist)
    {
        HwBlob hwblob = new HwBlob(16);
        int i = arraylist.size();
        hwblob.putInt32(8L, i);
        hwblob.putBool(12L, false);
        HwBlob hwblob1 = new HwBlob(i * 24);
        for(int j = 0; j < i; j++)
            ((DebugInfo)arraylist.get(j)).writeEmbeddedToBlob(hwblob1, j * 24);

        hwblob.putBlob(0L, hwblob1);
        hwparcel.writeBuffer(hwblob);
    }

    public final boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(obj.getClass() != android/hidl/base/V1_0/DebugInfo)
            return false;
        obj = (DebugInfo)obj;
        if(pid != ((DebugInfo) (obj)).pid)
            return false;
        if(ptr != ((DebugInfo) (obj)).ptr)
            return false;
        return arch == ((DebugInfo) (obj)).arch;
    }

    public final int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(pid))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(ptr))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(arch)))
        });
    }

    public final void readEmbeddedFromParcel(HwParcel hwparcel, HwBlob hwblob, long l)
    {
        pid = hwblob.getInt32(0L + l);
        ptr = hwblob.getInt64(8L + l);
        arch = hwblob.getInt32(16L + l);
    }

    public final void readFromParcel(HwParcel hwparcel)
    {
        readEmbeddedFromParcel(hwparcel, hwparcel.readBuffer(24L), 0L);
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{");
        stringbuilder.append(".pid = ");
        stringbuilder.append(pid);
        stringbuilder.append(", .ptr = ");
        stringbuilder.append(ptr);
        stringbuilder.append(", .arch = ");
        stringbuilder.append(Architecture.toString(arch));
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public final void writeEmbeddedToBlob(HwBlob hwblob, long l)
    {
        hwblob.putInt32(0L + l, pid);
        hwblob.putInt64(8L + l, ptr);
        hwblob.putInt32(16L + l, arch);
    }

    public final void writeToParcel(HwParcel hwparcel)
    {
        HwBlob hwblob = new HwBlob(24);
        writeEmbeddedToBlob(hwblob, 0L);
        hwparcel.writeBuffer(hwblob);
    }

    public int arch;
    public int pid;
    public long ptr;
}
