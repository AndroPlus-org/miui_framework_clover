// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.bluetooth.BluetoothAdapter;
import android.util.SparseArray;
import java.util.*;

public class BluetoothLeUtils
{

    public BluetoothLeUtils()
    {
    }

    static void checkAdapterStateOn(BluetoothAdapter bluetoothadapter)
    {
        if(bluetoothadapter == null || bluetoothadapter.isLeEnabled() ^ true)
            throw new IllegalStateException("BT Adapter is not turned ON");
        else
            return;
    }

    static boolean equals(SparseArray sparsearray, SparseArray sparsearray1)
    {
        if(sparsearray == sparsearray1)
            return true;
        if(sparsearray == null || sparsearray1 == null)
            return false;
        if(sparsearray.size() != sparsearray1.size())
            return false;
        for(int i = 0; i < sparsearray.size(); i++)
            if(sparsearray.keyAt(i) != sparsearray1.keyAt(i) || Arrays.equals((byte[])sparsearray.valueAt(i), (byte[])sparsearray1.valueAt(i)) ^ true)
                return false;

        return true;
    }

    static boolean equals(Map map, Map map1)
    {
        if(map == map1)
            return true;
        if(map == null || map1 == null)
            return false;
        if(map.size() != map1.size())
            return false;
        Set set = map.keySet();
        if(!set.equals(map1.keySet()))
            return false;
        for(Iterator iterator = set.iterator(); iterator.hasNext();)
        {
            Object obj = iterator.next();
            if(!Objects.deepEquals(map.get(obj), map1.get(obj)))
                return false;
        }

        return true;
    }

    static String toString(SparseArray sparsearray)
    {
        if(sparsearray == null)
            return "null";
        if(sparsearray.size() == 0)
            return "{}";
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('{');
        for(int i = 0; i < sparsearray.size(); i++)
            stringbuilder.append(sparsearray.keyAt(i)).append("=").append(Arrays.toString((byte[])sparsearray.valueAt(i)));

        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    static String toString(Map map)
    {
        if(map == null)
            return "null";
        if(map.isEmpty())
            return "{}";
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('{');
        Iterator iterator = map.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Object obj = ((java.util.Map.Entry)iterator.next()).getKey();
            stringbuilder.append(obj).append("=").append(Arrays.toString((byte[])map.get(obj)));
            if(iterator.hasNext())
                stringbuilder.append(", ");
        } while(true);
        stringbuilder.append('}');
        return stringbuilder.toString();
    }
}
