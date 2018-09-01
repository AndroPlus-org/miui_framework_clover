// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.ddm;

import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.*;

public class DdmHandleAppName extends ChunkHandler
{

    private DdmHandleAppName()
    {
    }

    public static String getAppName()
    {
        return mAppName;
    }

    public static void register()
    {
    }

    private static void sendAPNM(String s, int i)
    {
        ByteBuffer bytebuffer = ByteBuffer.allocate(s.length() * 2 + 4 + 4);
        bytebuffer.order(ChunkHandler.CHUNK_ORDER);
        bytebuffer.putInt(s.length());
        putString(bytebuffer, s);
        bytebuffer.putInt(i);
        DdmServer.sendChunk(new Chunk(CHUNK_APNM, bytebuffer));
    }

    public static void setAppName(String s, int i)
    {
        if(s == null || s.length() == 0)
        {
            return;
        } else
        {
            mAppName = s;
            sendAPNM(s, i);
            return;
        }
    }

    public void connected()
    {
    }

    public void disconnected()
    {
    }

    public Chunk handleChunk(Chunk chunk)
    {
        return null;
    }

    public static final int CHUNK_APNM = type("APNM");
    private static volatile String mAppName = "";
    private static DdmHandleAppName mInstance = new DdmHandleAppName();

}
