// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.ddm;

import android.os.*;
import dalvik.system.VMRuntime;
import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.*;

// Referenced classes of package android.ddm:
//            DdmHandleAppName

public class DdmHandleHello extends ChunkHandler
{

    private DdmHandleHello()
    {
    }

    private Chunk handleFEAT(Chunk chunk)
    {
        String as[] = Debug.getVmFeatureList();
        int i = (as.length + FRAMEWORK_FEATURES.length) * 4 + 4;
        for(int i1 = as.length - 1; i1 >= 0; i1--)
            i += as[i1].length() * 2;

        int k1 = FRAMEWORK_FEATURES.length - 1;
        int j1 = i;
        for(int j = k1; j >= 0; j--)
            j1 += FRAMEWORK_FEATURES[j].length() * 2;

        chunk = ByteBuffer.allocate(j1);
        chunk.order(ChunkHandler.CHUNK_ORDER);
        chunk.putInt(as.length + FRAMEWORK_FEATURES.length);
        for(int k = as.length - 1; k >= 0; k--)
        {
            chunk.putInt(as[k].length());
            putString(chunk, as[k]);
        }

        for(int l = FRAMEWORK_FEATURES.length - 1; l >= 0; l--)
        {
            chunk.putInt(FRAMEWORK_FEATURES[l].length());
            putString(chunk, FRAMEWORK_FEATURES[l]);
        }

        return new Chunk(CHUNK_FEAT, chunk);
    }

    private Chunk handleHELO(Chunk chunk)
    {
        wrapChunk(chunk).getInt();
        Object obj = System.getProperty("java.vm.name", "?");
        chunk = System.getProperty("java.vm.version", "?");
        String s = (new StringBuilder()).append(((String) (obj))).append(" v").append(chunk).toString();
        String s1 = DdmHandleAppName.getAppName();
        Object obj1 = VMRuntime.getRuntime();
        Object obj2;
        boolean flag;
        int i;
        if(((VMRuntime) (obj1)).is64Bit())
            chunk = "64-bit";
        else
            chunk = "32-bit";
        obj2 = ((VMRuntime) (obj1)).vmInstructionSet();
        obj = chunk;
        if(obj2 != null)
        {
            obj = chunk;
            if(((String) (obj2)).length() > 0)
                obj = (new StringBuilder()).append(chunk).append(" (").append(((String) (obj2))).append(")").toString();
        }
        obj2 = (new StringBuilder()).append("CheckJNI=");
        if(((VMRuntime) (obj1)).isCheckJniEnabled())
            chunk = "true";
        else
            chunk = "false";
        chunk = ((StringBuilder) (obj2)).append(chunk).toString();
        flag = ((VMRuntime) (obj1)).isNativeDebuggable();
        obj1 = ByteBuffer.allocate(s.length() * 2 + 28 + s1.length() * 2 + ((String) (obj)).length() * 2 + chunk.length() * 2 + 1);
        ((ByteBuffer) (obj1)).order(ChunkHandler.CHUNK_ORDER);
        ((ByteBuffer) (obj1)).putInt(1);
        ((ByteBuffer) (obj1)).putInt(Process.myPid());
        ((ByteBuffer) (obj1)).putInt(s.length());
        ((ByteBuffer) (obj1)).putInt(s1.length());
        putString(((ByteBuffer) (obj1)), s);
        putString(((ByteBuffer) (obj1)), s1);
        ((ByteBuffer) (obj1)).putInt(UserHandle.myUserId());
        ((ByteBuffer) (obj1)).putInt(((String) (obj)).length());
        putString(((ByteBuffer) (obj1)), ((String) (obj)));
        ((ByteBuffer) (obj1)).putInt(chunk.length());
        putString(((ByteBuffer) (obj1)), chunk);
        if(flag)
            i = 1;
        else
            i = 0;
        ((ByteBuffer) (obj1)).put((byte)i);
        chunk = new Chunk(CHUNK_HELO, ((ByteBuffer) (obj1)));
        if(Debug.waitingForDebugger())
            sendWAIT(0);
        return chunk;
    }

    public static void register()
    {
        DdmServer.registerHandler(CHUNK_HELO, mInstance);
        DdmServer.registerHandler(CHUNK_FEAT, mInstance);
    }

    public static void sendWAIT(int i)
    {
        byte byte0 = (byte)i;
        DdmServer.sendChunk(new Chunk(CHUNK_WAIT, new byte[] {
            byte0
        }, 0, 1));
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
        if(i == CHUNK_HELO)
            return handleHELO(chunk);
        if(i == CHUNK_FEAT)
            return handleFEAT(chunk);
        else
            throw new RuntimeException((new StringBuilder()).append("Unknown packet ").append(ChunkHandler.name(i)).toString());
    }

    public static final int CHUNK_FEAT = type("FEAT");
    public static final int CHUNK_HELO = type("HELO");
    public static final int CHUNK_WAIT = type("WAIT");
    private static final String FRAMEWORK_FEATURES[] = {
        "opengl-tracing", "view-hierarchy"
    };
    private static DdmHandleHello mInstance = new DdmHandleHello();

}
