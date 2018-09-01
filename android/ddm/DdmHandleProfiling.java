// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.ddm;

import android.os.Debug;
import android.util.Log;
import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.*;

public class DdmHandleProfiling extends ChunkHandler
{

    private DdmHandleProfiling()
    {
    }

    private Chunk handleMPRE(Chunk chunk)
    {
        Debug.stopMethodTracing();
        int i = 0;
_L2:
        chunk = new byte[1];
        chunk[0] = (byte)i;
        return new Chunk(CHUNK_MPRE, chunk, 0, chunk.length);
        chunk;
        Log.w("ddm-heap", (new StringBuilder()).append("Method profiling end failed: ").append(chunk.getMessage()).toString());
        i = 1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private Chunk handleMPRQ(Chunk chunk)
    {
        int i = Debug.getMethodTracingMode();
        chunk = new byte[1];
        chunk[0] = (byte)i;
        return new Chunk(CHUNK_MPRQ, chunk, 0, chunk.length);
    }

    private Chunk handleMPRS(Chunk chunk)
    {
        chunk = wrapChunk(chunk);
        int i = chunk.getInt();
        int j = chunk.getInt();
        chunk = getString(chunk, chunk.getInt());
        try
        {
            Debug.startMethodTracing(chunk, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(Chunk chunk)
        {
            return createFailChunk(1, chunk.getMessage());
        }
        return null;
    }

    private Chunk handleMPSEOrSPSE(Chunk chunk, String s)
    {
        try
        {
            Debug.stopMethodTracing();
        }
        // Misplaced declaration of an exception variable
        catch(Chunk chunk)
        {
            Log.w("ddm-heap", (new StringBuilder()).append(s).append(" prof stream end failed: ").append(chunk.getMessage()).toString());
            return createFailChunk(1, chunk.getMessage());
        }
        return null;
    }

    private Chunk handleMPSS(Chunk chunk)
    {
        chunk = wrapChunk(chunk);
        int i = chunk.getInt();
        int j = chunk.getInt();
        try
        {
            Debug.startMethodTracingDdms(i, j, false, 0);
        }
        // Misplaced declaration of an exception variable
        catch(Chunk chunk)
        {
            return createFailChunk(1, chunk.getMessage());
        }
        return null;
    }

    private Chunk handleSPSS(Chunk chunk)
    {
        chunk = wrapChunk(chunk);
        int i = chunk.getInt();
        int j = chunk.getInt();
        int k = chunk.getInt();
        try
        {
            Debug.startMethodTracingDdms(i, j, true, k);
        }
        // Misplaced declaration of an exception variable
        catch(Chunk chunk)
        {
            return createFailChunk(1, chunk.getMessage());
        }
        return null;
    }

    public static void register()
    {
        DdmServer.registerHandler(CHUNK_MPRS, mInstance);
        DdmServer.registerHandler(CHUNK_MPRE, mInstance);
        DdmServer.registerHandler(CHUNK_MPSS, mInstance);
        DdmServer.registerHandler(CHUNK_MPSE, mInstance);
        DdmServer.registerHandler(CHUNK_MPRQ, mInstance);
        DdmServer.registerHandler(CHUNK_SPSS, mInstance);
        DdmServer.registerHandler(CHUNK_SPSE, mInstance);
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
        if(i == CHUNK_MPRS)
            return handleMPRS(chunk);
        if(i == CHUNK_MPRE)
            return handleMPRE(chunk);
        if(i == CHUNK_MPSS)
            return handleMPSS(chunk);
        if(i == CHUNK_MPSE)
            return handleMPSEOrSPSE(chunk, "Method");
        if(i == CHUNK_MPRQ)
            return handleMPRQ(chunk);
        if(i == CHUNK_SPSS)
            return handleSPSS(chunk);
        if(i == CHUNK_SPSE)
            return handleMPSEOrSPSE(chunk, "Sample");
        else
            throw new RuntimeException((new StringBuilder()).append("Unknown packet ").append(ChunkHandler.name(i)).toString());
    }

    public static final int CHUNK_MPRE = type("MPRE");
    public static final int CHUNK_MPRQ = type("MPRQ");
    public static final int CHUNK_MPRS = type("MPRS");
    public static final int CHUNK_MPSE = type("MPSE");
    public static final int CHUNK_MPSS = type("MPSS");
    public static final int CHUNK_SPSE = type("SPSE");
    public static final int CHUNK_SPSS = type("SPSS");
    private static final boolean DEBUG = false;
    private static DdmHandleProfiling mInstance = new DdmHandleProfiling();

}
