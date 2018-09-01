// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.cas.V1_0;

import android.os.*;
import java.util.ArrayList;
import java.util.Objects;

public final class HidlCasPluginDescriptor
{

    public HidlCasPluginDescriptor()
    {
        name = new String();
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
            HidlCasPluginDescriptor hidlcasplugindescriptor = new HidlCasPluginDescriptor();
            hidlcasplugindescriptor.readEmbeddedFromParcel(hwparcel, hwblob, j * 24);
            arraylist.add(hidlcasplugindescriptor);
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
            ((HidlCasPluginDescriptor)arraylist.get(j)).writeEmbeddedToBlob(hwblob1, j * 24);

        hwblob.putBlob(0L, hwblob1);
        hwparcel.writeBuffer(hwblob);
    }

    public final boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(obj.getClass() != android/hardware/cas/V1_0/HidlCasPluginDescriptor)
            return false;
        obj = (HidlCasPluginDescriptor)obj;
        if(caSystemId != ((HidlCasPluginDescriptor) (obj)).caSystemId)
            return false;
        return HidlSupport.deepEquals(name, ((HidlCasPluginDescriptor) (obj)).name);
    }

    public final int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(caSystemId))), Integer.valueOf(HidlSupport.deepHashCode(name))
        });
    }

    public final void readEmbeddedFromParcel(HwParcel hwparcel, HwBlob hwblob, long l)
    {
        caSystemId = hwblob.getInt32(l + 0L);
        name = hwblob.getString(l + 8L);
        hwparcel.readEmbeddedBuffer(name.getBytes().length + 1, hwblob.handle(), 0L + (l + 8L), false);
    }

    public final void readFromParcel(HwParcel hwparcel)
    {
        readEmbeddedFromParcel(hwparcel, hwparcel.readBuffer(24L), 0L);
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{");
        stringbuilder.append(".caSystemId = ");
        stringbuilder.append(caSystemId);
        stringbuilder.append(", .name = ");
        stringbuilder.append(name);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public final void writeEmbeddedToBlob(HwBlob hwblob, long l)
    {
        hwblob.putInt32(0L + l, caSystemId);
        hwblob.putString(8L + l, name);
    }

    public final void writeToParcel(HwParcel hwparcel)
    {
        HwBlob hwblob = new HwBlob(24);
        writeEmbeddedToBlob(hwblob, 0L);
        hwparcel.writeBuffer(hwblob);
    }

    public int caSystemId;
    public String name;
}
