// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.util.Size;
import java.nio.ByteBuffer;
import libcore.io.Memory;

// Referenced classes of package android.media:
//            Image, ImageWriter

class ImageUtils
{

    ImageUtils()
    {
    }

    private static void directByteBufferCopy(ByteBuffer bytebuffer, int i, ByteBuffer bytebuffer1, int j, int k)
    {
        Memory.memmove(bytebuffer1, j, bytebuffer, i, k);
    }

    private static Size getEffectivePlaneSizeForImage(Image image, int i)
    {
        switch(image.getFormat())
        {
        default:
            throw new UnsupportedOperationException(String.format("Invalid image format %d", new Object[] {
                Integer.valueOf(image.getFormat())
            }));

        case 17: // '\021'
        case 35: // '#'
        case 842094169: 
            if(i == 0)
                return new Size(image.getWidth(), image.getHeight());
            else
                return new Size(image.getWidth() / 2, image.getHeight() / 2);

        case 16: // '\020'
            if(i == 0)
                return new Size(image.getWidth(), image.getHeight());
            else
                return new Size(image.getWidth(), image.getHeight() / 2);

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 20: // '\024'
        case 32: // ' '
        case 37: // '%'
        case 38: // '&'
        case 256: 
        case 4098: 
        case 538982489: 
        case 540422489: 
            return new Size(image.getWidth(), image.getHeight());

        case 34: // '"'
            return new Size(0, 0);
        }
    }

    public static int getEstimatedNativeAllocBytes(int i, int j, int k, int l)
    {
        k;
        JVM INSTR lookupswitch 20: default 172
    //                   1: 251
    //                   2: 251
    //                   3: 243
    //                   4: 235
    //                   16: 235
    //                   17: 227
    //                   20: 235
    //                   32: 235
    //                   34: 227
    //                   35: 227
    //                   36: 235
    //                   37: 219
    //                   38: 227
    //                   256: 196
    //                   257: 196
    //                   4098: 235
    //                   538982489: 213
    //                   540422489: 235
    //                   842094169: 227
    //                   1144402265: 235;
           goto _L1 _L2 _L2 _L3 _L4 _L4 _L5 _L4 _L4 _L5 _L5 _L4 _L6 _L5 _L7 _L7 _L4 _L8 _L4 _L5 _L4
_L1:
        throw new UnsupportedOperationException(String.format("Invalid format specified %d", new Object[] {
            Integer.valueOf(k)
        }));
_L7:
        double d = 0.29999999999999999D;
_L10:
        return (int)((double)(i * j) * d * (double)l);
_L8:
        d = 1.0D;
        continue; /* Loop/switch isn't completed */
_L6:
        d = 1.25D;
        continue; /* Loop/switch isn't completed */
_L5:
        d = 1.5D;
        continue; /* Loop/switch isn't completed */
_L4:
        d = 2D;
        continue; /* Loop/switch isn't completed */
_L3:
        d = 3D;
        continue; /* Loop/switch isn't completed */
_L2:
        d = 4D;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public static int getNumPlanesForFormat(int i)
    {
        switch(i)
        {
        default:
            throw new UnsupportedOperationException(String.format("Invalid format specified %d", new Object[] {
                Integer.valueOf(i)
            }));

        case 17: // '\021'
        case 35: // '#'
        case 842094169: 
            return 3;

        case 16: // '\020'
            return 2;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 20: // '\024'
        case 32: // ' '
        case 36: // '$'
        case 37: // '%'
        case 38: // '&'
        case 256: 
        case 257: 
        case 4098: 
        case 538982489: 
        case 540422489: 
        case 1144402265: 
            return 1;

        case 34: // '"'
            return 0;
        }
    }

    public static void imageCopy(Image image, Image image1)
    {
        if(image == null || image1 == null)
            throw new IllegalArgumentException("Images should be non-null");
        if(image.getFormat() != image1.getFormat())
            throw new IllegalArgumentException("Src and dst images should have the same format");
        if(image.getFormat() == 34 || image1.getFormat() == 34)
            throw new IllegalArgumentException("PRIVATE format images are not copyable");
        if(image.getFormat() == 36)
            throw new IllegalArgumentException("Copy of RAW_OPAQUE format has not been implemented");
        if(image.getFormat() == 4098)
            throw new IllegalArgumentException("Copy of RAW_DEPTH format has not been implemented");
        if(!(image1.getOwner() instanceof ImageWriter))
            throw new IllegalArgumentException("Destination image is not from ImageWriter. Only the images from ImageWriter are writable");
        Size size = new Size(image.getWidth(), image.getHeight());
        Size size1 = new Size(image1.getWidth(), image1.getHeight());
        if(!size.equals(size1))
            throw new IllegalArgumentException((new StringBuilder()).append("source image size ").append(size).append(" is different").append(" with ").append("destination image size ").append(size1).toString());
        Image.Plane aplane[] = image.getPlanes();
        Image.Plane aplane1[] = image1.getPlanes();
        int i = 0;
        while(i < aplane.length) 
        {
            int j = aplane[i].getRowStride();
            int k = aplane1[i].getRowStride();
            ByteBuffer bytebuffer = aplane[i].getBuffer();
            ByteBuffer bytebuffer1 = aplane1[i].getBuffer();
            boolean flag;
            if(bytebuffer.isDirect())
                flag = bytebuffer1.isDirect();
            else
                flag = false;
            if(!flag)
                throw new IllegalArgumentException("Source and destination ByteBuffers must be direct byteBuffer!");
            if(aplane[i].getPixelStride() != aplane1[i].getPixelStride())
                throw new IllegalArgumentException((new StringBuilder()).append("Source plane image pixel stride ").append(aplane[i].getPixelStride()).append(" must be same as destination image pixel stride ").append(aplane1[i].getPixelStride()).toString());
            int l = bytebuffer.position();
            bytebuffer.rewind();
            bytebuffer1.rewind();
            if(j == k)
            {
                bytebuffer1.put(bytebuffer);
            } else
            {
                int i1 = bytebuffer.position();
                int j1 = bytebuffer1.position();
                image1 = getEffectivePlaneSizeForImage(image, i);
                int k1 = image1.getWidth() * aplane[i].getPixelStride();
                int l1 = 0;
                while(l1 < image1.getHeight()) 
                {
                    int i2 = k1;
                    if(l1 == image1.getHeight() - 1)
                    {
                        int j2 = bytebuffer.remaining() - i1;
                        i2 = k1;
                        if(k1 > j2)
                            i2 = j2;
                    }
                    directByteBufferCopy(bytebuffer, i1, bytebuffer1, j1, i2);
                    i1 += j;
                    j1 += k;
                    l1++;
                    k1 = i2;
                }
            }
            bytebuffer.position(l);
            bytebuffer1.rewind();
            i++;
        }
    }
}
