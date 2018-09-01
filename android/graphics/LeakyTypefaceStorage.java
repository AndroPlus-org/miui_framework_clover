// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.os.Parcel;
import android.os.Process;
import android.util.ArrayMap;
import java.util.ArrayList;

// Referenced classes of package android.graphics:
//            Typeface

public class LeakyTypefaceStorage
{

    public LeakyTypefaceStorage()
    {
    }

    public static Typeface readTypefaceFromParcel(Parcel parcel)
    {
        int j;
        int i = parcel.readInt();
        j = parcel.readInt();
        if(i != Process.myPid())
            return null;
        parcel = ((Parcel) (sLock));
        parcel;
        JVM INSTR monitorenter ;
        Typeface typeface = (Typeface)sStorage.get(j);
        parcel;
        JVM INSTR monitorexit ;
        return typeface;
        Exception exception;
        exception;
        throw exception;
    }

    public static void writeTypefaceToParcel(Typeface typeface, Parcel parcel)
    {
        parcel.writeInt(Process.myPid());
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        Integer integer = (Integer)sTypefaceMap.get(typeface);
        if(integer == null)
            break MISSING_BLOCK_LABEL_43;
        int i = integer.intValue();
_L1:
        parcel.writeInt(i);
        obj;
        JVM INSTR monitorexit ;
        return;
        i = sStorage.size();
        sStorage.add(typeface);
        sTypefaceMap.put(typeface, Integer.valueOf(i));
          goto _L1
        typeface;
        throw typeface;
    }

    private static final Object sLock = new Object();
    private static final ArrayList sStorage = new ArrayList();
    private static final ArrayMap sTypefaceMap = new ArrayMap();

}
