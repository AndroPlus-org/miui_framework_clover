// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.ddm;

import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.*;

public class DdmHandleThread extends ChunkHandler
{

    private DdmHandleThread()
    {
    }

    private Chunk createStackChunk(StackTraceElement astacktraceelement[], int i)
    {
        int j = 4 + 4 + 4;
        int k = astacktraceelement.length;
        for(int l = 0; l < k; l++)
        {
            StackTraceElement stacktraceelement = astacktraceelement[l];
            int j1 = j + (stacktraceelement.getClassName().length() * 2 + 4) + (stacktraceelement.getMethodName().length() * 2 + 4) + 4;
            j = j1;
            if(stacktraceelement.getFileName() != null)
                j = j1 + stacktraceelement.getFileName().length() * 2;
            j += 4;
        }

        ByteBuffer bytebuffer = ByteBuffer.allocate(j);
        bytebuffer.putInt(0);
        bytebuffer.putInt(i);
        bytebuffer.putInt(astacktraceelement.length);
        int i1 = astacktraceelement.length;
        i = 0;
        while(i < i1) 
        {
            StackTraceElement stacktraceelement1 = astacktraceelement[i];
            bytebuffer.putInt(stacktraceelement1.getClassName().length());
            putString(bytebuffer, stacktraceelement1.getClassName());
            bytebuffer.putInt(stacktraceelement1.getMethodName().length());
            putString(bytebuffer, stacktraceelement1.getMethodName());
            if(stacktraceelement1.getFileName() != null)
            {
                bytebuffer.putInt(stacktraceelement1.getFileName().length());
                putString(bytebuffer, stacktraceelement1.getFileName());
            } else
            {
                bytebuffer.putInt(0);
            }
            bytebuffer.putInt(stacktraceelement1.getLineNumber());
            i++;
        }
        return new Chunk(CHUNK_STKL, bytebuffer);
    }

    private Chunk handleSTKL(Chunk chunk)
    {
        int i = wrapChunk(chunk).getInt();
        chunk = DdmVmInternal.getStackTraceById(i);
        if(chunk == null)
            return createFailChunk(1, "Stack trace unavailable");
        else
            return createStackChunk(chunk, i);
    }

    private Chunk handleTHEN(Chunk chunk)
    {
        boolean flag;
        if(wrapChunk(chunk).get() != 0)
            flag = true;
        else
            flag = false;
        DdmVmInternal.threadNotify(flag);
        return null;
    }

    private Chunk handleTHST(Chunk chunk)
    {
        wrapChunk(chunk);
        chunk = DdmVmInternal.getThreadStats();
        if(chunk != null)
            return new Chunk(CHUNK_THST, chunk, 0, chunk.length);
        else
            return createFailChunk(1, "Can't build THST chunk");
    }

    public static void register()
    {
        DdmServer.registerHandler(CHUNK_THEN, mInstance);
        DdmServer.registerHandler(CHUNK_THST, mInstance);
        DdmServer.registerHandler(CHUNK_STKL, mInstance);
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
        if(i == CHUNK_THEN)
            return handleTHEN(chunk);
        if(i == CHUNK_THST)
            return handleTHST(chunk);
        if(i == CHUNK_STKL)
            return handleSTKL(chunk);
        else
            throw new RuntimeException((new StringBuilder()).append("Unknown packet ").append(ChunkHandler.name(i)).toString());
    }

    public static final int CHUNK_STKL = type("STKL");
    public static final int CHUNK_THCR = type("THCR");
    public static final int CHUNK_THDE = type("THDE");
    public static final int CHUNK_THEN = type("THEN");
    public static final int CHUNK_THST = type("THST");
    private static DdmHandleThread mInstance = new DdmHandleThread();

}
