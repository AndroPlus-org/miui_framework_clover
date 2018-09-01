// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.ddm;

import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.*;

public class DdmHandleExit extends ChunkHandler
{

    private DdmHandleExit()
    {
    }

    public static void register()
    {
        DdmServer.registerHandler(CHUNK_EXIT, mInstance);
    }

    public void connected()
    {
    }

    public void disconnected()
    {
    }

    public Chunk handleChunk(Chunk chunk)
    {
        int i = wrapChunk(chunk).getInt();
        Runtime.getRuntime().halt(i);
        return null;
    }

    public static final int CHUNK_EXIT = type("EXIT");
    private static DdmHandleExit mInstance = new DdmHandleExit();

}
