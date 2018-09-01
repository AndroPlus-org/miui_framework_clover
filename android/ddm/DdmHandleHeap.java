// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.ddm;

import android.os.Debug;
import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.*;

public class DdmHandleHeap extends ChunkHandler
{

    private DdmHandleHeap()
    {
    }

    private Chunk handleHPDS(Chunk chunk)
    {
        wrapChunk(chunk);
        chunk = null;
        try
        {
            Debug.dumpHprofDataDdms();
        }
        // Misplaced declaration of an exception variable
        catch(Chunk chunk)
        {
            chunk = "hprof dumps not supported in this VM";
        }
        // Misplaced declaration of an exception variable
        catch(Chunk chunk)
        {
            chunk = (new StringBuilder()).append("Exception: ").append(chunk.getMessage()).toString();
        }
        if(chunk != null)
        {
            Log.w("ddm-heap", chunk);
            return createFailChunk(1, chunk);
        } else
        {
            return null;
        }
    }

    private Chunk handleHPDU(Chunk chunk)
    {
        chunk = wrapChunk(chunk);
        chunk = getString(chunk, chunk.getInt());
        Debug.dumpHprofData(chunk);
        int i = 0;
_L2:
        chunk = new byte[1];
        chunk[0] = (byte)i;
        return new Chunk(CHUNK_HPDU, chunk, 0, chunk.length);
        chunk;
        i = -1;
        continue; /* Loop/switch isn't completed */
        chunk;
        i = -1;
        continue; /* Loop/switch isn't completed */
        chunk;
        Log.w("ddm-heap", "hprof dumps not supported in this VM");
        i = -1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private Chunk handleHPGC(Chunk chunk)
    {
        Runtime.getRuntime().gc();
        return null;
    }

    private Chunk handleHPIF(Chunk chunk)
    {
        if(!DdmVmInternal.heapInfoNotify(wrapChunk(chunk).get()))
            return createFailChunk(1, "Unsupported HPIF what");
        else
            return null;
    }

    private Chunk handleHPSGNHSG(Chunk chunk, boolean flag)
    {
        chunk = wrapChunk(chunk);
        if(!DdmVmInternal.heapSegmentNotify(chunk.get(), chunk.get(), flag))
            return createFailChunk(1, "Unsupported HPSG what/when");
        else
            return null;
    }

    private Chunk handleREAE(Chunk chunk)
    {
        boolean flag;
        if(wrapChunk(chunk).get() != 0)
            flag = true;
        else
            flag = false;
        DdmVmInternal.enableRecentAllocations(flag);
        return null;
    }

    private Chunk handleREAL(Chunk chunk)
    {
        chunk = DdmVmInternal.getRecentAllocations();
        return new Chunk(CHUNK_REAL, chunk, 0, chunk.length);
    }

    private Chunk handleREAQ(Chunk chunk)
    {
        int i = 1;
        chunk = new byte[1];
        if(!DdmVmInternal.getRecentAllocationStatus())
            i = 0;
        chunk[0] = (byte)i;
        return new Chunk(CHUNK_REAQ, chunk, 0, chunk.length);
    }

    public static void register()
    {
        DdmServer.registerHandler(CHUNK_HPIF, mInstance);
        DdmServer.registerHandler(CHUNK_HPSG, mInstance);
        DdmServer.registerHandler(CHUNK_HPDU, mInstance);
        DdmServer.registerHandler(CHUNK_HPDS, mInstance);
        DdmServer.registerHandler(CHUNK_NHSG, mInstance);
        DdmServer.registerHandler(CHUNK_HPGC, mInstance);
        DdmServer.registerHandler(CHUNK_REAE, mInstance);
        DdmServer.registerHandler(CHUNK_REAQ, mInstance);
        DdmServer.registerHandler(CHUNK_REAL, mInstance);
    }

    public void connected()
    {
    }

    public void disconnected()
    {
    }

    public Chunk handleChunk(Chunk chunk)
    {
        int i = chunk.type;
        if(i == CHUNK_HPIF)
            return handleHPIF(chunk);
        if(i == CHUNK_HPSG)
            return handleHPSGNHSG(chunk, false);
        if(i == CHUNK_HPDU)
            return handleHPDU(chunk);
        if(i == CHUNK_HPDS)
            return handleHPDS(chunk);
        if(i == CHUNK_NHSG)
            return handleHPSGNHSG(chunk, true);
        if(i == CHUNK_HPGC)
            return handleHPGC(chunk);
        if(i == CHUNK_REAE)
            return handleREAE(chunk);
        if(i == CHUNK_REAQ)
            return handleREAQ(chunk);
        if(i == CHUNK_REAL)
            return handleREAL(chunk);
        else
            throw new RuntimeException((new StringBuilder()).append("Unknown packet ").append(ChunkHandler.name(i)).toString());
    }

    public static final int CHUNK_HPDS = type("HPDS");
    public static final int CHUNK_HPDU = type("HPDU");
    public static final int CHUNK_HPGC = type("HPGC");
    public static final int CHUNK_HPIF = type("HPIF");
    public static final int CHUNK_HPSG = type("HPSG");
    public static final int CHUNK_NHSG = type("NHSG");
    public static final int CHUNK_REAE = type("REAE");
    public static final int CHUNK_REAL = type("REAL");
    public static final int CHUNK_REAQ = type("REAQ");
    private static DdmHandleHeap mInstance = new DdmHandleHeap();

}
