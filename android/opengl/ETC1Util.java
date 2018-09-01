// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;

import java.io.*;
import java.nio.*;

// Referenced classes of package android.opengl:
//            ETC1, GLES10

public class ETC1Util
{
    public static class ETC1Texture
    {

        public ByteBuffer getData()
        {
            return mData;
        }

        public int getHeight()
        {
            return mHeight;
        }

        public int getWidth()
        {
            return mWidth;
        }

        private ByteBuffer mData;
        private int mHeight;
        private int mWidth;

        public ETC1Texture(int i, int j, ByteBuffer bytebuffer)
        {
            mWidth = i;
            mHeight = j;
            mData = bytebuffer;
        }
    }


    public ETC1Util()
    {
    }

    public static ETC1Texture compressTexture(Buffer buffer, int i, int j, int k, int l)
    {
        ByteBuffer bytebuffer = ByteBuffer.allocateDirect(ETC1.getEncodedDataSize(i, j)).order(ByteOrder.nativeOrder());
        ETC1.encodeImage(buffer, i, j, k, l, bytebuffer);
        return new ETC1Texture(i, j, bytebuffer);
    }

    public static ETC1Texture createTexture(InputStream inputstream)
        throws IOException
    {
        byte abyte0[] = new byte[4096];
        if(inputstream.read(abyte0, 0, 16) != 16)
            throw new IOException("Unable to read PKM file header.");
        ByteBuffer bytebuffer = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder());
        bytebuffer.put(abyte0, 0, 16).position(0);
        if(!ETC1.isValid(bytebuffer))
            throw new IOException("Not a PKM file.");
        int i = ETC1.getWidth(bytebuffer);
        int j = ETC1.getHeight(bytebuffer);
        int k = ETC1.getEncodedDataSize(i, j);
        bytebuffer = ByteBuffer.allocateDirect(k).order(ByteOrder.nativeOrder());
        int i1;
        for(int l = 0; l < k; l += i1)
        {
            i1 = Math.min(abyte0.length, k - l);
            if(inputstream.read(abyte0, 0, i1) != i1)
                throw new IOException("Unable to read PKM file data.");
            bytebuffer.put(abyte0, 0, i1);
        }

        bytebuffer.position(0);
        return new ETC1Texture(i, j, bytebuffer);
    }

    public static boolean isETC1Supported()
    {
        int ai[] = new int[20];
        GLES10.glGetIntegerv(34466, ai, 0);
        int i = ai[0];
        int ai1[] = ai;
        if(i > ai.length)
            ai1 = new int[i];
        GLES10.glGetIntegerv(34467, ai1, 0);
        for(int j = 0; j < i; j++)
            if(ai1[j] == 36196)
                return true;

        return false;
    }

    public static void loadTexture(int i, int j, int k, int l, int i1, ETC1Texture etc1texture)
    {
        if(l != 6407)
            throw new IllegalArgumentException("fallbackFormat must be GL_RGB");
        if(i1 != 33635 && i1 != 5121)
            throw new IllegalArgumentException("Unsupported fallbackType");
        int j1 = etc1texture.getWidth();
        int k1 = etc1texture.getHeight();
        ByteBuffer bytebuffer = etc1texture.getData();
        if(isETC1Supported())
        {
            GLES10.glCompressedTexImage2D(i, j, 36196, j1, k1, k, bytebuffer.remaining(), bytebuffer);
        } else
        {
            byte byte0;
            int l1;
            if(i1 != 5121)
                byte0 = 1;
            else
                byte0 = 0;
            if(byte0 != 0)
                byte0 = 2;
            else
                byte0 = 3;
            l1 = byte0 * j1;
            etc1texture = ByteBuffer.allocateDirect(l1 * k1).order(ByteOrder.nativeOrder());
            ETC1.decodeImage(bytebuffer, etc1texture, j1, k1, byte0, l1);
            GLES10.glTexImage2D(i, j, l, j1, k1, k, l, i1, etc1texture);
        }
    }

    public static void loadTexture(int i, int j, int k, int l, int i1, InputStream inputstream)
        throws IOException
    {
        loadTexture(i, j, k, l, i1, createTexture(inputstream));
    }

    public static void writeTexture(ETC1Texture etc1texture, OutputStream outputstream)
        throws IOException
    {
        ByteBuffer bytebuffer;
        int i;
        bytebuffer = etc1texture.getData();
        i = bytebuffer.position();
        int l;
        byte abyte0[];
        int j = etc1texture.getWidth();
        l = etc1texture.getHeight();
        etc1texture = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder());
        ETC1.formatHeader(etc1texture, j, l);
        abyte0 = new byte[4096];
        etc1texture.get(abyte0, 0, 16);
        outputstream.write(abyte0, 0, 16);
        l = ETC1.getEncodedDataSize(j, l);
        int k = 0;
_L2:
        if(k >= l)
            break; /* Loop/switch isn't completed */
        int i1;
        i1 = Math.min(abyte0.length, l - k);
        bytebuffer.get(abyte0, 0, i1);
        outputstream.write(abyte0, 0, i1);
        k += i1;
        if(true) goto _L2; else goto _L1
_L1:
        bytebuffer.position(i);
        return;
        etc1texture;
        bytebuffer.position(i);
        throw etc1texture;
    }
}
