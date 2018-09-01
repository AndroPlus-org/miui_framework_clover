// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.ddm;

import android.util.Log;
import org.apache.harmony.dalvik.ddmc.*;

public class DdmHandleNativeHeap extends ChunkHandler
{

    private DdmHandleNativeHeap()
    {
    }

    private native byte[] getLeakInfo();

    private Chunk handleNHGT(Chunk chunk)
    {
        chunk = getLeakInfo();
        if(chunk != null)
        {
            Log.i("ddm-nativeheap", (new StringBuilder()).append("Sending ").append(chunk.length).append(" bytes").toString());
            return new Chunk(ChunkHandler.type("NHGT"), chunk, 0, chunk.length);
        } else
        {
            return createFailChunk(1, "Something went wrong");
        }
    }

    public static void register()
    {
        DdmServer.registerHandler(CHUNK_NHGT, mInstance);
    }

    public void connected()
    {
    }

    public void disconnected()
    {
    }

    public Chunk handleChunk(Chunk chunk)
    {
        Log.i("ddm-nativeheap", (new StringBuilder()).append("Handling ").append(name(chunk.type)).append(" chunk").toString());
        int i = chunk.type;
        if(i == CHUNK_NHGT)
            return handleNHGT(chunk);
        else
            throw new RuntimeException((new StringBuilder()).append("Unknown packet ").append(ChunkHandler.name(i)).toString());
    }

    public static final int CHUNK_NHGT = type("NHGT");
    private static DdmHandleNativeHeap mInstance = new DdmHandleNativeHeap();

}
