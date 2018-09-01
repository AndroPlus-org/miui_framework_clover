// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.Log;
import com.android.internal.util.FastPrintWriter;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

// Referenced classes of package android.os:
//            ParcelFileDescriptor

public class TransactionTracker
{

    TransactionTracker()
    {
        resetTraces();
    }

    private void resetTraces()
    {
        this;
        JVM INSTR monitorenter ;
        HashMap hashmap = JVM INSTR new #17  <Class HashMap>;
        hashmap.HashMap();
        mTraces = hashmap;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void addTrace(Throwable throwable)
    {
        throwable = Log.getStackTraceString(throwable);
        this;
        JVM INSTR monitorenter ;
        if(!mTraces.containsKey(throwable))
            break MISSING_BLOCK_LABEL_55;
        mTraces.put(throwable, Long.valueOf(((Long)mTraces.get(throwable)).longValue() + 1L));
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        mTraces.put(throwable, Long.valueOf(1L));
          goto _L1
        throwable;
        throw throwable;
    }

    public void clearTraces()
    {
        resetTraces();
    }

    public void writeTracesToFile(ParcelFileDescriptor parcelfiledescriptor)
    {
        if(mTraces.isEmpty())
            return;
        FastPrintWriter fastprintwriter = new FastPrintWriter(new FileOutputStream(parcelfiledescriptor.getFileDescriptor()));
        this;
        JVM INSTR monitorenter ;
        for(parcelfiledescriptor = mTraces.keySet().iterator(); parcelfiledescriptor.hasNext(); fastprintwriter.println())
        {
            String s = (String)parcelfiledescriptor.next();
            StringBuilder stringbuilder = JVM INSTR new #98  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            fastprintwriter.println(stringbuilder.append("Count: ").append(mTraces.get(s)).toString());
            stringbuilder = JVM INSTR new #98  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            fastprintwriter.println(stringbuilder.append("Trace: ").append(s).toString());
        }

        break MISSING_BLOCK_LABEL_145;
        parcelfiledescriptor;
        throw parcelfiledescriptor;
        this;
        JVM INSTR monitorexit ;
        fastprintwriter.flush();
        return;
    }

    private Map mTraces;
}
