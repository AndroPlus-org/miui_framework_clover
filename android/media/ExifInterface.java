// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.system.*;
import android.util.Log;
import android.util.Pair;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;
import libcore.io.IoUtils;
import libcore.io.Streams;

// Referenced classes of package android.media:
//            MediaMetadataRetriever, MediaDataSource

public class ExifInterface
{
    private static class ByteOrderedDataInputStream extends InputStream
        implements DataInput
    {

        static int _2D_get0(ByteOrderedDataInputStream byteordereddatainputstream)
        {
            return byteordereddatainputstream.mLength;
        }

        static int _2D_get1(ByteOrderedDataInputStream byteordereddatainputstream)
        {
            return byteordereddatainputstream.mPosition;
        }

        public int available()
            throws IOException
        {
            return mDataInputStream.available();
        }

        public int peek()
        {
            return mPosition;
        }

        public int read()
            throws IOException
        {
            mPosition = mPosition + 1;
            return mDataInputStream.read();
        }

        public boolean readBoolean()
            throws IOException
        {
            mPosition = mPosition + 1;
            return mDataInputStream.readBoolean();
        }

        public byte readByte()
            throws IOException
        {
            mPosition = mPosition + 1;
            if(mPosition > mLength)
                throw new EOFException();
            int i = mDataInputStream.read();
            if(i < 0)
                throw new EOFException();
            else
                return (byte)i;
        }

        public char readChar()
            throws IOException
        {
            mPosition = mPosition + 2;
            return mDataInputStream.readChar();
        }

        public double readDouble()
            throws IOException
        {
            return Double.longBitsToDouble(readLong());
        }

        public float readFloat()
            throws IOException
        {
            return Float.intBitsToFloat(readInt());
        }

        public void readFully(byte abyte0[])
            throws IOException
        {
            mPosition = mPosition + abyte0.length;
            if(mPosition > mLength)
                throw new EOFException();
            if(mDataInputStream.read(abyte0, 0, abyte0.length) != abyte0.length)
                throw new IOException("Couldn't read up to the length of buffer");
            else
                return;
        }

        public void readFully(byte abyte0[], int i, int j)
            throws IOException
        {
            mPosition = mPosition + j;
            if(mPosition > mLength)
                throw new EOFException();
            if(mDataInputStream.read(abyte0, i, j) != j)
                throw new IOException("Couldn't read up to the length of buffer");
            else
                return;
        }

        public int readInt()
            throws IOException
        {
            mPosition = mPosition + 4;
            if(mPosition > mLength)
                throw new EOFException();
            int i = mDataInputStream.read();
            int j = mDataInputStream.read();
            int k = mDataInputStream.read();
            int l = mDataInputStream.read();
            if((i | j | k | l) < 0)
                throw new EOFException();
            if(mByteOrder == LITTLE_ENDIAN)
                return (l << 24) + (k << 16) + (j << 8) + i;
            if(mByteOrder == BIG_ENDIAN)
                return (i << 24) + (j << 16) + (k << 8) + l;
            else
                throw new IOException((new StringBuilder()).append("Invalid byte order: ").append(mByteOrder).toString());
        }

        public String readLine()
            throws IOException
        {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        public long readLong()
            throws IOException
        {
            mPosition = mPosition + 8;
            if(mPosition > mLength)
                throw new EOFException();
            int i = mDataInputStream.read();
            int j = mDataInputStream.read();
            int k = mDataInputStream.read();
            int l = mDataInputStream.read();
            int i1 = mDataInputStream.read();
            int j1 = mDataInputStream.read();
            int k1 = mDataInputStream.read();
            int l1 = mDataInputStream.read();
            if((i | j | k | l | i1 | j1 | k1 | l1) < 0)
                throw new EOFException();
            if(mByteOrder == LITTLE_ENDIAN)
                return ((long)l1 << 56) + ((long)k1 << 48) + ((long)j1 << 40) + ((long)i1 << 32) + ((long)l << 24) + ((long)k << 16) + ((long)j << 8) + (long)i;
            if(mByteOrder == BIG_ENDIAN)
                return ((long)i << 56) + ((long)j << 48) + ((long)k << 40) + ((long)l << 32) + ((long)i1 << 24) + ((long)j1 << 16) + ((long)k1 << 8) + (long)l1;
            else
                throw new IOException((new StringBuilder()).append("Invalid byte order: ").append(mByteOrder).toString());
        }

        public short readShort()
            throws IOException
        {
            mPosition = mPosition + 2;
            if(mPosition > mLength)
                throw new EOFException();
            int i = mDataInputStream.read();
            int j = mDataInputStream.read();
            if((i | j) < 0)
                throw new EOFException();
            if(mByteOrder == LITTLE_ENDIAN)
                return (short)((j << 8) + i);
            if(mByteOrder == BIG_ENDIAN)
                return (short)((i << 8) + j);
            else
                throw new IOException((new StringBuilder()).append("Invalid byte order: ").append(mByteOrder).toString());
        }

        public String readUTF()
            throws IOException
        {
            mPosition = mPosition + 2;
            return mDataInputStream.readUTF();
        }

        public int readUnsignedByte()
            throws IOException
        {
            mPosition = mPosition + 1;
            return mDataInputStream.readUnsignedByte();
        }

        public long readUnsignedInt()
            throws IOException
        {
            return (long)readInt() & 0xffffffffL;
        }

        public int readUnsignedShort()
            throws IOException
        {
            mPosition = mPosition + 2;
            if(mPosition > mLength)
                throw new EOFException();
            int i = mDataInputStream.read();
            int j = mDataInputStream.read();
            if((i | j) < 0)
                throw new EOFException();
            if(mByteOrder == LITTLE_ENDIAN)
                return (j << 8) + i;
            if(mByteOrder == BIG_ENDIAN)
                return (i << 8) + j;
            else
                throw new IOException((new StringBuilder()).append("Invalid byte order: ").append(mByteOrder).toString());
        }

        public void seek(long l)
            throws IOException
        {
            if((long)mPosition > l)
            {
                mPosition = 0;
                mDataInputStream.reset();
                mDataInputStream.mark(mLength);
            } else
            {
                l -= mPosition;
            }
            if(skipBytes((int)l) != (int)l)
                throw new IOException("Couldn't seek up to the byteCount");
            else
                return;
        }

        public void setByteOrder(ByteOrder byteorder)
        {
            mByteOrder = byteorder;
        }

        public int skipBytes(int i)
            throws IOException
        {
            int j = Math.min(i, mLength - mPosition);
            for(i = 0; i < j; i += mDataInputStream.skipBytes(j - i));
            mPosition = mPosition + i;
            return i;
        }

        private static final ByteOrder BIG_ENDIAN;
        private static final ByteOrder LITTLE_ENDIAN;
        private ByteOrder mByteOrder;
        private DataInputStream mDataInputStream;
        private InputStream mInputStream;
        private final int mLength;
        private int mPosition;

        static 
        {
            LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
            BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
        }

        public ByteOrderedDataInputStream(InputStream inputstream)
            throws IOException
        {
            mByteOrder = ByteOrder.BIG_ENDIAN;
            mInputStream = inputstream;
            mDataInputStream = new DataInputStream(inputstream);
            mLength = mDataInputStream.available();
            mPosition = 0;
            mDataInputStream.mark(mLength);
        }

        public ByteOrderedDataInputStream(byte abyte0[])
            throws IOException
        {
            this(((InputStream) (new ByteArrayInputStream(abyte0))));
        }
    }

    private static class ByteOrderedDataOutputStream extends FilterOutputStream
    {

        public void setByteOrder(ByteOrder byteorder)
        {
            mByteOrder = byteorder;
        }

        public void write(byte abyte0[])
            throws IOException
        {
            mOutputStream.write(abyte0);
        }

        public void write(byte abyte0[], int i, int j)
            throws IOException
        {
            mOutputStream.write(abyte0, i, j);
        }

        public void writeByte(int i)
            throws IOException
        {
            mOutputStream.write(i);
        }

        public void writeInt(int i)
            throws IOException
        {
            if(mByteOrder != ByteOrder.LITTLE_ENDIAN) goto _L2; else goto _L1
_L1:
            mOutputStream.write(i >>> 0 & 0xff);
            mOutputStream.write(i >>> 8 & 0xff);
            mOutputStream.write(i >>> 16 & 0xff);
            mOutputStream.write(i >>> 24 & 0xff);
_L4:
            return;
_L2:
            if(mByteOrder == ByteOrder.BIG_ENDIAN)
            {
                mOutputStream.write(i >>> 24 & 0xff);
                mOutputStream.write(i >>> 16 & 0xff);
                mOutputStream.write(i >>> 8 & 0xff);
                mOutputStream.write(i >>> 0 & 0xff);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void writeShort(short word0)
            throws IOException
        {
            if(mByteOrder != ByteOrder.LITTLE_ENDIAN) goto _L2; else goto _L1
_L1:
            mOutputStream.write(word0 >>> 0 & 0xff);
            mOutputStream.write(word0 >>> 8 & 0xff);
_L4:
            return;
_L2:
            if(mByteOrder == ByteOrder.BIG_ENDIAN)
            {
                mOutputStream.write(word0 >>> 8 & 0xff);
                mOutputStream.write(word0 >>> 0 & 0xff);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void writeUnsignedInt(long l)
            throws IOException
        {
            writeInt((int)l);
        }

        public void writeUnsignedShort(int i)
            throws IOException
        {
            writeShort((short)i);
        }

        private ByteOrder mByteOrder;
        private final OutputStream mOutputStream;

        public ByteOrderedDataOutputStream(OutputStream outputstream, ByteOrder byteorder)
        {
            super(outputstream);
            mOutputStream = outputstream;
            mByteOrder = byteorder;
        }
    }

    private static class ExifAttribute
    {

        static Object _2D_wrap0(ExifAttribute exifattribute, ByteOrder byteorder)
        {
            return exifattribute.getValue(byteorder);
        }

        public static ExifAttribute createByte(String s)
        {
            if(s.length() == 1 && s.charAt(0) >= '0' && s.charAt(0) <= '1')
            {
                byte abyte0[] = new byte[1];
                abyte0[0] = (byte)(s.charAt(0) - 48);
                return new ExifAttribute(1, abyte0.length, abyte0);
            } else
            {
                s = s.getBytes(ExifInterface._2D_get0());
                return new ExifAttribute(1, s.length, s);
            }
        }

        public static ExifAttribute createDouble(double d, ByteOrder byteorder)
        {
            return createDouble(new double[] {
                d
            }, byteorder);
        }

        public static ExifAttribute createDouble(double ad[], ByteOrder byteorder)
        {
            ByteBuffer bytebuffer = ByteBuffer.wrap(new byte[ExifInterface._2D_get2()[12] * ad.length]);
            bytebuffer.order(byteorder);
            int i = 0;
            for(int j = ad.length; i < j; i++)
                bytebuffer.putDouble(ad[i]);

            return new ExifAttribute(12, ad.length, bytebuffer.array());
        }

        public static ExifAttribute createSLong(int i, ByteOrder byteorder)
        {
            return createSLong(new int[] {
                i
            }, byteorder);
        }

        public static ExifAttribute createSLong(int ai[], ByteOrder byteorder)
        {
            ByteBuffer bytebuffer = ByteBuffer.wrap(new byte[ExifInterface._2D_get2()[9] * ai.length]);
            bytebuffer.order(byteorder);
            int i = 0;
            for(int j = ai.length; i < j; i++)
                bytebuffer.putInt(ai[i]);

            return new ExifAttribute(9, ai.length, bytebuffer.array());
        }

        public static ExifAttribute createSRational(Rational rational, ByteOrder byteorder)
        {
            return createSRational(new Rational[] {
                rational
            }, byteorder);
        }

        public static ExifAttribute createSRational(Rational arational[], ByteOrder byteorder)
        {
            ByteBuffer bytebuffer = ByteBuffer.wrap(new byte[ExifInterface._2D_get2()[10] * arational.length]);
            bytebuffer.order(byteorder);
            int i = 0;
            for(int j = arational.length; i < j; i++)
            {
                byteorder = arational[i];
                bytebuffer.putInt((int)((Rational) (byteorder)).numerator);
                bytebuffer.putInt((int)((Rational) (byteorder)).denominator);
            }

            return new ExifAttribute(10, arational.length, bytebuffer.array());
        }

        public static ExifAttribute createString(String s)
        {
            s = (new StringBuilder()).append(s).append('\0').toString().getBytes(ExifInterface._2D_get0());
            return new ExifAttribute(2, s.length, s);
        }

        public static ExifAttribute createULong(long l, ByteOrder byteorder)
        {
            return createULong(new long[] {
                l
            }, byteorder);
        }

        public static ExifAttribute createULong(long al[], ByteOrder byteorder)
        {
            ByteBuffer bytebuffer = ByteBuffer.wrap(new byte[ExifInterface._2D_get2()[4] * al.length]);
            bytebuffer.order(byteorder);
            int i = 0;
            for(int j = al.length; i < j; i++)
                bytebuffer.putInt((int)al[i]);

            return new ExifAttribute(4, al.length, bytebuffer.array());
        }

        public static ExifAttribute createURational(Rational rational, ByteOrder byteorder)
        {
            return createURational(new Rational[] {
                rational
            }, byteorder);
        }

        public static ExifAttribute createURational(Rational arational[], ByteOrder byteorder)
        {
            ByteBuffer bytebuffer = ByteBuffer.wrap(new byte[ExifInterface._2D_get2()[5] * arational.length]);
            bytebuffer.order(byteorder);
            int i = 0;
            for(int j = arational.length; i < j; i++)
            {
                byteorder = arational[i];
                bytebuffer.putInt((int)((Rational) (byteorder)).numerator);
                bytebuffer.putInt((int)((Rational) (byteorder)).denominator);
            }

            return new ExifAttribute(5, arational.length, bytebuffer.array());
        }

        public static ExifAttribute createUShort(int i, ByteOrder byteorder)
        {
            return createUShort(new int[] {
                i
            }, byteorder);
        }

        public static ExifAttribute createUShort(int ai[], ByteOrder byteorder)
        {
            ByteBuffer bytebuffer = ByteBuffer.wrap(new byte[ExifInterface._2D_get2()[3] * ai.length]);
            bytebuffer.order(byteorder);
            int i = 0;
            for(int j = ai.length; i < j; i++)
                bytebuffer.putShort((short)ai[i]);

            return new ExifAttribute(3, ai.length, bytebuffer.array());
        }

        private Object getValue(ByteOrder byteorder)
        {
            ByteOrderedDataInputStream byteordereddatainputstream;
            byteordereddatainputstream = JVM INSTR new #137 <Class ExifInterface$ByteOrderedDataInputStream>;
            byteordereddatainputstream.ByteOrderedDataInputStream(bytes);
            byteordereddatainputstream.setByteOrder(byteorder);
            format;
            JVM INSTR tableswitch 1 12: default 84
        //                       1 86
        //                       2 153
        //                       3 306
        //                       4 341
        //                       5 376
        //                       6 86
        //                       7 153
        //                       8 424
        //                       9 459
        //                       10 494
        //                       11 544
        //                       12 580;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L2 _L3 _L7 _L8 _L9 _L10 _L11
_L1:
            return null;
_L3:
            flag = false;
            i = ((flag) ? 1 : 0);
            if(numberOfComponents < ExifInterface._2D_get1().length) goto _L13; else goto _L12
_L12:
            flag1 = true;
            i = 0;
_L43:
            byte0 = flag1;
            if(i >= ExifInterface._2D_get1().length) goto _L15; else goto _L14
_L14:
            if(bytes[i] == ExifInterface._2D_get1()[i]) goto _L17; else goto _L16
_L16:
            byte0 = 0;
_L15:
            i = ((flag) ? 1 : 0);
            if(!byte0) goto _L13; else goto _L18
_L18:
            i = ExifInterface._2D_get1().length;
_L13:
            byteorder = JVM INSTR new #106 <Class StringBuilder>;
            byteorder.StringBuilder();
_L24:
            if(i >= numberOfComponents) goto _L20; else goto _L19
_L19:
            byte0 = bytes[i];
            if(byte0 != 0) goto _L21; else goto _L20
_L20:
            return byteorder.toString();
_L17:
            i++;
            continue; /* Loop/switch isn't completed */
_L21:
            if(byte0 < 32) goto _L23; else goto _L22
_L22:
            byteorder.append((char)byte0);
_L25:
            i++;
              goto _L24
_L23:
            byteorder.append('?');
              goto _L25
_L2:
            boolean flag;
            int i;
            boolean flag1;
            byte byte0;
            try
            {
                if(bytes.length == 1 && bytes[0] >= 0 && bytes[0] <= 1)
                    return new String(new char[] {
                        (char)(bytes[0] + 48)
                    });
                else
                    return new String(bytes, ExifInterface._2D_get0());
            }
            // Misplaced declaration of an exception variable
            catch(ByteOrder byteorder)
            {
                Log.w("ExifInterface", "IOException occurred during reading a value", byteorder);
            }
            return null;
              goto _L24
_L4:
            byteorder = new int[numberOfComponents];
            int j = 0;
_L27:
            if(j >= numberOfComponents)
                break; /* Loop/switch isn't completed */
            byteorder[j] = byteordereddatainputstream.readUnsignedShort();
            j++;
            if(true) goto _L27; else goto _L26
_L26:
            return byteorder;
_L5:
            byteorder = new long[numberOfComponents];
            j = 0;
_L29:
            if(j >= numberOfComponents)
                break; /* Loop/switch isn't completed */
            byteorder[j] = byteordereddatainputstream.readUnsignedInt();
            j++;
            if(true) goto _L29; else goto _L28
_L28:
            return byteorder;
_L6:
            byteorder = new Rational[numberOfComponents];
            j = 0;
_L31:
            if(j >= numberOfComponents)
                break; /* Loop/switch isn't completed */
            byteorder[j] = new Rational(byteordereddatainputstream.readUnsignedInt(), byteordereddatainputstream.readUnsignedInt(), null);
            j++;
            if(true) goto _L31; else goto _L30
_L30:
            return byteorder;
_L7:
            byteorder = new int[numberOfComponents];
            j = 0;
_L33:
            if(j >= numberOfComponents)
                break; /* Loop/switch isn't completed */
            byteorder[j] = byteordereddatainputstream.readShort();
            j++;
            if(true) goto _L33; else goto _L32
_L32:
            return byteorder;
_L8:
            byteorder = new int[numberOfComponents];
            j = 0;
_L35:
            if(j >= numberOfComponents)
                break; /* Loop/switch isn't completed */
            byteorder[j] = byteordereddatainputstream.readInt();
            j++;
            if(true) goto _L35; else goto _L34
_L34:
            return byteorder;
_L9:
            byteorder = new Rational[numberOfComponents];
            j = 0;
_L37:
            if(j >= numberOfComponents)
                break; /* Loop/switch isn't completed */
            byteorder[j] = new Rational(byteordereddatainputstream.readInt(), byteordereddatainputstream.readInt(), null);
            j++;
            if(true) goto _L37; else goto _L36
_L36:
            return byteorder;
_L10:
            byteorder = new double[numberOfComponents];
            j = 0;
_L39:
            if(j >= numberOfComponents)
                break; /* Loop/switch isn't completed */
            byteorder[j] = byteordereddatainputstream.readFloat();
            j++;
            if(true) goto _L39; else goto _L38
_L38:
            return byteorder;
_L11:
            byteorder = new double[numberOfComponents];
            j = 0;
_L41:
            if(j >= numberOfComponents)
                break; /* Loop/switch isn't completed */
            byteorder[j] = byteordereddatainputstream.readDouble();
            j++;
            if(true) goto _L41; else goto _L40
_L40:
            return byteorder;
            if(true) goto _L43; else goto _L42
_L42:
        }

        public double getDoubleValue(ByteOrder byteorder)
        {
            byteorder = ((ByteOrder) (getValue(byteorder)));
            if(byteorder == null)
                throw new NumberFormatException("NULL can't be converted to a double value");
            if(byteorder instanceof String)
                return Double.parseDouble((String)byteorder);
            if(byteorder instanceof long[])
            {
                byteorder = (long[])byteorder;
                if(byteorder.length == 1)
                    return (double)byteorder[0];
                else
                    throw new NumberFormatException("There are more than one component");
            }
            if(byteorder instanceof int[])
            {
                byteorder = (int[])byteorder;
                if(byteorder.length == 1)
                    return (double)byteorder[0];
                else
                    throw new NumberFormatException("There are more than one component");
            }
            if(byteorder instanceof double[])
            {
                byteorder = (double[])byteorder;
                if(byteorder.length == 1)
                    return byteorder[0];
                else
                    throw new NumberFormatException("There are more than one component");
            }
            if(byteorder instanceof Rational[])
            {
                byteorder = (Rational[])byteorder;
                if(byteorder.length == 1)
                    return byteorder[0].calculate();
                else
                    throw new NumberFormatException("There are more than one component");
            } else
            {
                throw new NumberFormatException("Couldn't find a double value");
            }
        }

        public int getIntValue(ByteOrder byteorder)
        {
            byteorder = ((ByteOrder) (getValue(byteorder)));
            if(byteorder == null)
                throw new NumberFormatException("NULL can't be converted to a integer value");
            if(byteorder instanceof String)
                return Integer.parseInt((String)byteorder);
            if(byteorder instanceof long[])
            {
                byteorder = (long[])byteorder;
                if(byteorder.length == 1)
                    return (int)byteorder[0];
                else
                    throw new NumberFormatException("There are more than one component");
            }
            if(byteorder instanceof int[])
            {
                byteorder = (int[])byteorder;
                if(byteorder.length == 1)
                    return byteorder[0];
                else
                    throw new NumberFormatException("There are more than one component");
            } else
            {
                throw new NumberFormatException("Couldn't find a integer value");
            }
        }

        public String getStringValue(ByteOrder byteorder)
        {
            Object obj = getValue(byteorder);
            if(obj == null)
                return null;
            if(obj instanceof String)
                return (String)obj;
            byteorder = new StringBuilder();
            if(obj instanceof long[])
            {
                obj = (long[])obj;
                for(int i = 0; i < obj.length; i++)
                {
                    byteorder.append(obj[i]);
                    if(i + 1 != obj.length)
                        byteorder.append(",");
                }

                return byteorder.toString();
            }
            if(obj instanceof int[])
            {
                obj = (int[])obj;
                for(int j = 0; j < obj.length; j++)
                {
                    byteorder.append(obj[j]);
                    if(j + 1 != obj.length)
                        byteorder.append(",");
                }

                return byteorder.toString();
            }
            if(obj instanceof double[])
            {
                obj = (double[])obj;
                for(int k = 0; k < obj.length; k++)
                {
                    byteorder.append(obj[k]);
                    if(k + 1 != obj.length)
                        byteorder.append(",");
                }

                return byteorder.toString();
            }
            if(obj instanceof Rational[])
            {
                Rational arational[] = (Rational[])obj;
                for(int l = 0; l < arational.length; l++)
                {
                    byteorder.append(arational[l].numerator);
                    byteorder.append('/');
                    byteorder.append(arational[l].denominator);
                    if(l + 1 != arational.length)
                        byteorder.append(",");
                }

                return byteorder.toString();
            } else
            {
                return null;
            }
        }

        public int size()
        {
            return ExifInterface._2D_get2()[format] * numberOfComponents;
        }

        public String toString()
        {
            return (new StringBuilder()).append("(").append(ExifInterface._2D_get3()[format]).append(", data length:").append(bytes.length).append(")").toString();
        }

        public final byte bytes[];
        public final int format;
        public final int numberOfComponents;

        private ExifAttribute(int i, int j, byte abyte0[])
        {
            format = i;
            numberOfComponents = j;
            bytes = abyte0;
        }

        ExifAttribute(int i, int j, byte abyte0[], ExifAttribute exifattribute)
        {
            this(i, j, abyte0);
        }
    }

    private static class ExifTag
    {

        public final String name;
        public final int number;
        public final int primaryFormat;
        public final int secondaryFormat;

        private ExifTag(String s, int i, int j)
        {
            name = s;
            number = i;
            primaryFormat = j;
            secondaryFormat = -1;
        }

        private ExifTag(String s, int i, int j, int k)
        {
            name = s;
            number = i;
            primaryFormat = j;
            secondaryFormat = k;
        }

        ExifTag(String s, int i, int j, int k, ExifTag exiftag)
        {
            this(s, i, j, k);
        }

        ExifTag(String s, int i, int j, ExifTag exiftag)
        {
            this(s, i, j);
        }
    }

    private static class Rational
    {

        public double calculate()
        {
            return (double)numerator / (double)denominator;
        }

        public String toString()
        {
            return (new StringBuilder()).append(numerator).append("/").append(denominator).toString();
        }

        public final long denominator;
        public final long numerator;

        private Rational(long l, long l1)
        {
            if(l1 == 0L)
            {
                numerator = 0L;
                denominator = 1L;
                return;
            } else
            {
                numerator = l;
                denominator = l1;
                return;
            }
        }

        Rational(long l, long l1, Rational rational)
        {
            this(l, l1);
        }
    }


    static Charset _2D_get0()
    {
        return ASCII;
    }

    static byte[] _2D_get1()
    {
        return EXIF_ASCII_PREFIX;
    }

    static int[] _2D_get2()
    {
        return IFD_FORMAT_BYTES_PER_FORMAT;
    }

    static String[] _2D_get3()
    {
        return IFD_FORMAT_NAMES;
    }

    public ExifInterface(FileDescriptor filedescriptor)
        throws IOException
    {
        Object obj;
        FileInputStream fileinputstream;
        mAttributes = new HashMap[EXIF_TAGS.length];
        mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if(filedescriptor == null)
            throw new IllegalArgumentException("fileDescriptor cannot be null");
        mAssetInputStream = null;
        mFilename = null;
        if(isSeekableFD(filedescriptor))
        {
            mSeekableFileDescriptor = filedescriptor;
            try
            {
                filedescriptor = Os.dup(filedescriptor);
            }
            // Misplaced declaration of an exception variable
            catch(FileDescriptor filedescriptor)
            {
                throw filedescriptor.rethrowAsIOException();
            }
        } else
        {
            mSeekableFileDescriptor = null;
        }
        mIsInputStream = false;
        obj = null;
        fileinputstream = JVM INSTR new #993 <Class FileInputStream>;
        fileinputstream.FileInputStream(filedescriptor);
        loadAttributes(fileinputstream);
        IoUtils.closeQuietly(fileinputstream);
        return;
        filedescriptor;
_L2:
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        throw filedescriptor;
        filedescriptor;
        obj = fileinputstream;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public ExifInterface(InputStream inputstream)
        throws IOException
    {
        mAttributes = new HashMap[EXIF_TAGS.length];
        mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if(inputstream == null)
            throw new IllegalArgumentException("inputStream cannot be null");
        mFilename = null;
        if(inputstream instanceof android.content.res.AssetManager.AssetInputStream)
        {
            mAssetInputStream = (android.content.res.AssetManager.AssetInputStream)inputstream;
            mSeekableFileDescriptor = null;
        } else
        if((inputstream instanceof FileInputStream) && isSeekableFD(((FileInputStream)inputstream).getFD()))
        {
            mAssetInputStream = null;
            mSeekableFileDescriptor = ((FileInputStream)inputstream).getFD();
        } else
        {
            mAssetInputStream = null;
            mSeekableFileDescriptor = null;
        }
        mIsInputStream = true;
        loadAttributes(inputstream);
    }

    public ExifInterface(String s)
        throws IOException
    {
        Object obj;
        mAttributes = new HashMap[EXIF_TAGS.length];
        mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if(s == null)
            throw new IllegalArgumentException("filename cannot be null");
        obj = null;
        mAssetInputStream = null;
        mFilename = s;
        mIsInputStream = false;
        FileInputStream fileinputstream;
        fileinputstream = JVM INSTR new #993 <Class FileInputStream>;
        fileinputstream.FileInputStream(s);
        if(!isSeekableFD(fileinputstream.getFD()))
            break MISSING_BLOCK_LABEL_91;
        mSeekableFileDescriptor = fileinputstream.getFD();
_L1:
        loadAttributes(fileinputstream);
        IoUtils.closeQuietly(fileinputstream);
        return;
        mSeekableFileDescriptor = null;
          goto _L1
        Exception exception;
        exception;
        s = fileinputstream;
_L3:
        IoUtils.closeQuietly(s);
        throw exception;
        exception;
        s = obj;
        if(true) goto _L3; else goto _L2
_L2:
    }

    private void addDefaultValuesForCompatibility()
    {
        String s = getAttribute("DateTimeOriginal");
        if(s != null && getAttribute("DateTime") == null)
            mAttributes[0].put("DateTime", ExifAttribute.createString(s));
        if(getAttribute("ImageWidth") == null)
            mAttributes[0].put("ImageWidth", ExifAttribute.createULong(0L, mExifByteOrder));
        if(getAttribute("ImageLength") == null)
            mAttributes[0].put("ImageLength", ExifAttribute.createULong(0L, mExifByteOrder));
        if(getAttribute("Orientation") == null)
            mAttributes[0].put("Orientation", ExifAttribute.createUShort(0, mExifByteOrder));
        if(getAttribute("LightSource") == null)
            mAttributes[1].put("LightSource", ExifAttribute.createULong(0L, mExifByteOrder));
    }

    private boolean containsMatch(byte abyte0[], byte abyte1[])
    {
        int i = 0;
label0:
        do
        {
            if(i < abyte0.length - abyte1.length)
            {
                int j = 0;
                do
                {
                    if(j >= abyte1.length || abyte0[i + j] != abyte1[j])
                    {
                        i++;
                        continue label0;
                    }
                    if(j == abyte1.length - 1)
                        return true;
                    j++;
                } while(true);
            }
            return false;
        } while(true);
    }

    private static float convertRationalLatLonToFloat(String s, String s1)
    {
        double d;
        double d1;
        double d2;
        boolean flag;
        try
        {
            s = s.split(",");
            String as[] = s[0].split("/");
            d = Double.parseDouble(as[0].trim()) / Double.parseDouble(as[1].trim());
            as = s[1].split("/");
            d1 = Double.parseDouble(as[0].trim()) / Double.parseDouble(as[1].trim());
            s = s[2].split("/");
            d2 = Double.parseDouble(s[0].trim()) / Double.parseDouble(s[1].trim());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new IllegalArgumentException();
        }
        d2 = d1 / 60D + d + d2 / 3600D;
        if(s1.equals("S"))
            break MISSING_BLOCK_LABEL_141;
        flag = s1.equals("W");
        if(!flag)
            break MISSING_BLOCK_LABEL_146;
        return (float)(-d2);
        return (float)d2;
    }

    private ExifAttribute getExifAttribute(String s)
    {
        for(int i = 0; i < EXIF_TAGS.length; i++)
        {
            Object obj = mAttributes[i].get(s);
            if(obj != null)
                return (ExifAttribute)obj;
        }

        return null;
    }

    private void getHeifAttributes(ByteOrderedDataInputStream byteordereddatainputstream)
        throws IOException
    {
        MediaMetadataRetriever mediametadataretriever = new MediaMetadataRetriever();
        if(mSeekableFileDescriptor == null) goto _L2; else goto _L1
_L1:
        mediametadataretriever.setDataSource(mSeekableFileDescriptor);
_L10:
        if(!"yes".equals(mediametadataretriever.extractMetadata(17))) goto _L4; else goto _L3
_L3:
        String s;
        byteordereddatainputstream = mediametadataretriever.extractMetadata(18);
        s = mediametadataretriever.extractMetadata(19);
        if(byteordereddatainputstream == null)
            break MISSING_BLOCK_LABEL_80;
        mAttributes[0].put("ImageWidth", ExifAttribute.createUShort(Integer.parseInt(byteordereddatainputstream), mExifByteOrder));
        if(s == null)
            break MISSING_BLOCK_LABEL_108;
        mAttributes[0].put("ImageLength", ExifAttribute.createUShort(Integer.parseInt(s), mExifByteOrder));
        byteordereddatainputstream = mediametadataretriever.extractMetadata(24);
        if(byteordereddatainputstream == null) goto _L4; else goto _L5
_L5:
        int i = 1;
        Integer.parseInt(byteordereddatainputstream);
        JVM INSTR lookupswitch 3: default 160
    //                   90: 212
    //                   180: 219
    //                   270: 225;
           goto _L6 _L7 _L8 _L9
_L6:
        break; /* Loop/switch isn't completed */
_L9:
        break MISSING_BLOCK_LABEL_225;
_L11:
        mAttributes[0].put("Orientation", ExifAttribute.createUShort(i, mExifByteOrder));
_L4:
        mediametadataretriever.release();
        return;
_L2:
        MediaDataSource mediadatasource = JVM INSTR new #6   <Class ExifInterface$1>;
        mediadatasource.this. _cls1();
        mediametadataretriever.setDataSource(mediadatasource);
          goto _L10
        byteordereddatainputstream;
        mediametadataretriever.release();
        throw byteordereddatainputstream;
_L7:
        i = 6;
          goto _L11
_L8:
        i = 3;
          goto _L11
        i = 8;
          goto _L11
    }

    private void getJpegAttributes(ByteOrderedDataInputStream byteordereddatainputstream, int i, int j)
        throws IOException
    {
        byteordereddatainputstream.setByteOrder(ByteOrder.BIG_ENDIAN);
        byteordereddatainputstream.seek(i);
        byte byte0 = byteordereddatainputstream.readByte();
        if(byte0 != -1)
            throw new IOException((new StringBuilder()).append("Invalid marker: ").append(Integer.toHexString(byte0 & 0xff)).toString());
        if(byteordereddatainputstream.readByte() != -40)
            throw new IOException((new StringBuilder()).append("Invalid marker: ").append(Integer.toHexString(byte0 & 0xff)).toString());
        i = i + 1 + 1;
_L8:
        int k;
        int l;
        int i1;
        k = byteordereddatainputstream.readByte();
        if(k != -1)
            throw new IOException((new StringBuilder()).append("Invalid marker:").append(Integer.toHexString(k & 0xff)).toString());
        k = byteordereddatainputstream.readByte();
        if(k == -39 || k == -38)
        {
            byteordereddatainputstream.setByteOrder(mExifByteOrder);
            return;
        }
        l = byteordereddatainputstream.readUnsignedShort() - 2;
        i1 = i + 1 + 1 + 2;
        if(l < 0)
            throw new IOException("Invalid length");
        k;
        JVM INSTR lookupswitch 15: default 356
    //                   -64: 599
    //                   -63: 599
    //                   -62: 599
    //                   -61: 599
    //                   -59: 599
    //                   -58: 599
    //                   -57: 599
    //                   -55: 599
    //                   -54: 599
    //                   -53: 599
    //                   -51: 599
    //                   -50: 599
    //                   -49: 599
    //                   -31: 378
    //                   -2: 513;
           goto _L1 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L3 _L4
_L1:
        i = l;
        k = i1;
_L6:
        if(i < 0)
            throw new IOException("Invalid length");
        break; /* Loop/switch isn't completed */
_L3:
        k = i1;
        i = l;
        if(l >= 6)
        {
            byte abyte0[] = new byte[6];
            if(byteordereddatainputstream.read(abyte0) != 6)
                throw new IOException("Invalid exif");
            i1 += 6;
            l -= 6;
            k = i1;
            i = l;
            if(Arrays.equals(abyte0, IDENTIFIER_EXIF_APP1))
            {
                if(l <= 0)
                    throw new IOException("Invalid exif");
                mExifOffset = i1;
                byte abyte1[] = new byte[l];
                if(byteordereddatainputstream.read(abyte1) != l)
                    throw new IOException("Invalid exif");
                k = i1 + l;
                i = 0;
                readExifSegment(abyte1, j);
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        byte abyte2[] = new byte[l];
        if(byteordereddatainputstream.read(abyte2) != l)
            throw new IOException("Invalid exif");
        l = 0;
        k = i1;
        i = l;
        if(getAttribute("UserComment") == null)
        {
            mAttributes[1].put("UserComment", ExifAttribute.createString(new String(abyte2, ASCII)));
            k = i1;
            i = l;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(byteordereddatainputstream.skipBytes(1) != 1)
            throw new IOException("Invalid SOFx");
        mAttributes[j].put("ImageLength", ExifAttribute.createULong(byteordereddatainputstream.readUnsignedShort(), mExifByteOrder));
        mAttributes[j].put("ImageWidth", ExifAttribute.createULong(byteordereddatainputstream.readUnsignedShort(), mExifByteOrder));
        i = l - 5;
        k = i1;
        if(true) goto _L6; else goto _L5
_L5:
        if(byteordereddatainputstream.skipBytes(i) != i)
            throw new IOException("Invalid JPEG segment");
        i = k + i;
        if(true) goto _L8; else goto _L7
_L7:
    }

    private int getMimeType(BufferedInputStream bufferedinputstream)
        throws IOException
    {
        bufferedinputstream.mark(5000);
        byte abyte0[] = new byte[5000];
        bufferedinputstream.read(abyte0);
        bufferedinputstream.reset();
        if(isJpegFormat(abyte0))
            return 4;
        if(isRafFormat(abyte0))
            return 9;
        if(isHeifFormat(abyte0))
            return 12;
        if(isOrfFormat(abyte0))
            return 7;
        return !isRw2Format(abyte0) ? 0 : 10;
    }

    private void getOrfAttributes(ByteOrderedDataInputStream byteordereddatainputstream)
        throws IOException
    {
        getRawAttributes(byteordereddatainputstream);
        byteordereddatainputstream = (ExifAttribute)mAttributes[1].get("MakerNote");
        if(byteordereddatainputstream == null) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        byte abyte1[];
        byteordereddatainputstream = new ByteOrderedDataInputStream(((ExifAttribute) (byteordereddatainputstream)).bytes);
        byteordereddatainputstream.setByteOrder(mExifByteOrder);
        abyte0 = new byte[ORF_MAKER_NOTE_HEADER_1.length];
        byteordereddatainputstream.readFully(abyte0);
        byteordereddatainputstream.seek(0L);
        abyte1 = new byte[ORF_MAKER_NOTE_HEADER_2.length];
        byteordereddatainputstream.readFully(abyte1);
        if(!Arrays.equals(abyte0, ORF_MAKER_NOTE_HEADER_1)) goto _L4; else goto _L3
_L3:
        byteordereddatainputstream.seek(8L);
_L6:
        readImageFileDirectory(byteordereddatainputstream, 6);
        ExifAttribute exifattribute = (ExifAttribute)mAttributes[7].get("PreviewImageStart");
        byteordereddatainputstream = (ExifAttribute)mAttributes[7].get("PreviewImageLength");
        if(exifattribute != null && byteordereddatainputstream != null)
        {
            mAttributes[5].put("JPEGInterchangeFormat", exifattribute);
            mAttributes[5].put("JPEGInterchangeFormatLength", byteordereddatainputstream);
        }
        byteordereddatainputstream = (ExifAttribute)mAttributes[8].get("AspectFrame");
        if(byteordereddatainputstream != null)
        {
            int ai[] = new int[4];
            byteordereddatainputstream = (int[])ExifAttribute._2D_wrap0(byteordereddatainputstream, mExifByteOrder);
            if(byteordereddatainputstream[2] > byteordereddatainputstream[0] && byteordereddatainputstream[3] > byteordereddatainputstream[1])
            {
                int i = (byteordereddatainputstream[2] - byteordereddatainputstream[0]) + 1;
                int j = (byteordereddatainputstream[3] - byteordereddatainputstream[1]) + 1;
                int k = j;
                int l = i;
                if(i < j)
                {
                    l = i + j;
                    k = l - j;
                    l -= k;
                }
                ExifAttribute exifattribute1 = ExifAttribute.createUShort(l, mExifByteOrder);
                byteordereddatainputstream = ExifAttribute.createUShort(k, mExifByteOrder);
                mAttributes[0].put("ImageWidth", exifattribute1);
                mAttributes[0].put("ImageLength", byteordereddatainputstream);
            }
        }
_L2:
        return;
_L4:
        if(Arrays.equals(abyte1, ORF_MAKER_NOTE_HEADER_2))
            byteordereddatainputstream.seek(12L);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void getRafAttributes(ByteOrderedDataInputStream byteordereddatainputstream)
        throws IOException
    {
        byteordereddatainputstream.skipBytes(84);
        byte abyte0[] = new byte[4];
        byte abyte1[] = new byte[4];
        byteordereddatainputstream.read(abyte0);
        byteordereddatainputstream.skipBytes(4);
        byteordereddatainputstream.read(abyte1);
        int i = ByteBuffer.wrap(abyte0).getInt();
        int k = ByteBuffer.wrap(abyte1).getInt();
        getJpegAttributes(byteordereddatainputstream, i, 5);
        byteordereddatainputstream.seek(k);
        byteordereddatainputstream.setByteOrder(ByteOrder.BIG_ENDIAN);
        k = byteordereddatainputstream.readInt();
        for(int j = 0; j < k; j++)
        {
            int l = byteordereddatainputstream.readUnsignedShort();
            int i1 = byteordereddatainputstream.readUnsignedShort();
            if(l == TAG_RAF_IMAGE_SIZE.number)
            {
                short word0 = byteordereddatainputstream.readShort();
                j = byteordereddatainputstream.readShort();
                ExifAttribute exifattribute = ExifAttribute.createUShort(word0, mExifByteOrder);
                byteordereddatainputstream = ExifAttribute.createUShort(j, mExifByteOrder);
                mAttributes[0].put("ImageLength", exifattribute);
                mAttributes[0].put("ImageWidth", byteordereddatainputstream);
                return;
            }
            byteordereddatainputstream.skipBytes(i1);
        }

    }

    private void getRawAttributes(ByteOrderedDataInputStream byteordereddatainputstream)
        throws IOException
    {
        parseTiffHeaders(byteordereddatainputstream, byteordereddatainputstream.available());
        readImageFileDirectory(byteordereddatainputstream, 0);
        updateImageSizeValues(byteordereddatainputstream, 0);
        updateImageSizeValues(byteordereddatainputstream, 5);
        updateImageSizeValues(byteordereddatainputstream, 4);
        validateImages(byteordereddatainputstream);
        if(mMimeType == 8)
        {
            byteordereddatainputstream = (ExifAttribute)mAttributes[1].get("MakerNote");
            if(byteordereddatainputstream != null)
            {
                byteordereddatainputstream = new ByteOrderedDataInputStream(((ExifAttribute) (byteordereddatainputstream)).bytes);
                byteordereddatainputstream.setByteOrder(mExifByteOrder);
                byteordereddatainputstream.seek(6L);
                readImageFileDirectory(byteordereddatainputstream, 9);
                byteordereddatainputstream = (ExifAttribute)mAttributes[9].get("ColorSpace");
                if(byteordereddatainputstream != null)
                    mAttributes[1].put("ColorSpace", byteordereddatainputstream);
            }
        }
    }

    private void getRw2Attributes(ByteOrderedDataInputStream byteordereddatainputstream)
        throws IOException
    {
        getRawAttributes(byteordereddatainputstream);
        if((ExifAttribute)mAttributes[0].get("JpgFromRaw") != null)
            getJpegAttributes(byteordereddatainputstream, mRw2JpgFromRawOffset, 5);
        ExifAttribute exifattribute = (ExifAttribute)mAttributes[0].get("ISO");
        byteordereddatainputstream = (ExifAttribute)mAttributes[1].get("ISOSpeedRatings");
        if(exifattribute != null && byteordereddatainputstream == null)
            mAttributes[1].put("ISOSpeedRatings", exifattribute);
    }

    private static Pair guessDataFormat(String s)
    {
label0:
        {
            if(!s.contains(","))
                break label0;
            String as[] = s.split(",");
            s = guessDataFormat(as[0]);
            if(((Integer)((Pair) (s)).first).intValue() == 2)
                return s;
            int i = 1;
            while(i < as.length) 
            {
                int j;
                int k;
label1:
                {
                    Pair pair = guessDataFormat(as[i]);
                    j = -1;
                    byte byte0 = -1;
                    if(pair.first == ((Pair) (s)).first || pair.second == ((Pair) (s)).first)
                        j = ((Integer)((Pair) (s)).first).intValue();
                    k = byte0;
                    if(((Integer)((Pair) (s)).second).intValue() == -1)
                        break label1;
                    if(pair.first != ((Pair) (s)).second)
                    {
                        k = byte0;
                        if(pair.second != ((Pair) (s)).second)
                            break label1;
                    }
                    k = ((Integer)((Pair) (s)).second).intValue();
                }
                if(j == -1 && k == -1)
                    return new Pair(Integer.valueOf(2), Integer.valueOf(-1));
                if(j == -1)
                    s = new Pair(Integer.valueOf(k), Integer.valueOf(-1));
                else
                if(k == -1)
                    s = new Pair(Integer.valueOf(j), Integer.valueOf(-1));
                i++;
            }
            return s;
        }
        if(!s.contains("/"))
            break MISSING_BLOCK_LABEL_380;
        s = s.split("/");
        if(s.length != 2)
            break MISSING_BLOCK_LABEL_364;
        long l;
        long l1;
        l = (long)Double.parseDouble(s[0]);
        l1 = (long)Double.parseDouble(s[1]);
        if(l < 0L || l1 < 0L)
        {
            try
            {
                return new Pair(Integer.valueOf(10), Integer.valueOf(-1));
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
            break MISSING_BLOCK_LABEL_364;
        }
        if(l <= 0x7fffffffL && l1 <= 0x7fffffffL)
            break MISSING_BLOCK_LABEL_344;
        return new Pair(Integer.valueOf(5), Integer.valueOf(-1));
        s = new Pair(Integer.valueOf(10), Integer.valueOf(5));
        return s;
        return new Pair(Integer.valueOf(2), Integer.valueOf(-1));
        Object obj;
        try
        {
            obj = Long.valueOf(Long.parseLong(s));
            if(((Long) (obj)).longValue() >= 0L && ((Long) (obj)).longValue() <= 65535L)
                return new Pair(Integer.valueOf(3), Integer.valueOf(4));
            if(((Long) (obj)).longValue() < 0L)
                return new Pair(Integer.valueOf(9), Integer.valueOf(-1));
            obj = new Pair(Integer.valueOf(4), Integer.valueOf(-1));
        }
        catch(NumberFormatException numberformatexception)
        {
            try
            {
                Double.parseDouble(s);
                s = new Pair(Integer.valueOf(12), Integer.valueOf(-1));
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return new Pair(Integer.valueOf(2), Integer.valueOf(-1));
            }
            return s;
        }
        return ((Pair) (obj));
    }

    private void handleThumbnailFromJfif(ByteOrderedDataInputStream byteordereddatainputstream, HashMap hashmap)
        throws IOException
    {
        ExifAttribute exifattribute = (ExifAttribute)hashmap.get("JPEGInterchangeFormat");
        hashmap = (ExifAttribute)hashmap.get("JPEGInterchangeFormatLength");
        if(exifattribute != null && hashmap != null)
        {
            int i = exifattribute.getIntValue(mExifByteOrder);
            int j = Math.min(hashmap.getIntValue(mExifByteOrder), byteordereddatainputstream.available() - i);
            int k;
            if(mMimeType == 4 || mMimeType == 9 || mMimeType == 10)
            {
                k = i + mExifOffset;
            } else
            {
                k = i;
                if(mMimeType == 7)
                    k = i + mOrfMakerNoteOffset;
            }
            if(k > 0 && j > 0)
            {
                mHasThumbnail = true;
                mThumbnailOffset = k;
                mThumbnailLength = j;
                mThumbnailCompression = 6;
                if(mFilename == null && mAssetInputStream == null && mSeekableFileDescriptor == null)
                {
                    hashmap = new byte[j];
                    byteordereddatainputstream.seek(k);
                    byteordereddatainputstream.readFully(hashmap);
                    mThumbnailBytes = hashmap;
                }
            }
        }
    }

    private void handleThumbnailFromStrips(ByteOrderedDataInputStream byteordereddatainputstream, HashMap hashmap)
        throws IOException
    {
        ExifAttribute exifattribute = (ExifAttribute)hashmap.get("StripOffsets");
        ExifAttribute exifattribute1 = (ExifAttribute)hashmap.get("StripByteCounts");
        if(exifattribute != null && exifattribute1 != null)
        {
            hashmap = (long[])ExifAttribute._2D_wrap0(exifattribute, mExifByteOrder);
            long al[] = (long[])ExifAttribute._2D_wrap0(exifattribute1, mExifByteOrder);
            byte abyte0[] = new byte[(int)Arrays.stream(al).sum()];
            int i = 0;
            int j = 0;
            for(int k = 0; k < hashmap.length; k++)
            {
                int l = (int)hashmap[k];
                int i1 = (int)al[k];
                l -= i;
                if(l < 0)
                    Log.d("ExifInterface", "Invalid strip offset value");
                byteordereddatainputstream.seek(l);
                byte abyte1[] = new byte[i1];
                byteordereddatainputstream.read(abyte1);
                i = i + l + i1;
                System.arraycopy(abyte1, 0, abyte0, j, abyte1.length);
                j += abyte1.length;
            }

            mHasThumbnail = true;
            mThumbnailBytes = abyte0;
            mThumbnailLength = abyte0.length;
        }
    }

    private boolean isHeifFormat(byte abyte0[])
        throws IOException
    {
        byte abyte1[];
        Object obj;
        abyte1 = null;
        obj = null;
        Object obj1;
        obj1 = JVM INSTR new #8   <Class ExifInterface$ByteOrderedDataInputStream>;
        ((ByteOrderedDataInputStream) (obj1)).ByteOrderedDataInputStream(abyte0);
        long l;
        boolean flag;
        ((ByteOrderedDataInputStream) (obj1)).setByteOrder(ByteOrder.BIG_ENDIAN);
        l = ((ByteOrderedDataInputStream) (obj1)).readInt();
        abyte1 = new byte[4];
        ((ByteOrderedDataInputStream) (obj1)).read(abyte1);
        flag = Arrays.equals(abyte1, HEIF_TYPE_FTYP);
        long l1;
        long l2;
        if(!flag)
        {
            if(obj1 != null)
                ((ByteOrderedDataInputStream) (obj1)).close();
            return false;
        }
        l1 = 8L;
        l2 = l;
        if(l != 1L)
            break MISSING_BLOCK_LABEL_117;
        l2 = ((ByteOrderedDataInputStream) (obj1)).readLong();
        if(l2 < 16L)
        {
            if(obj1 != null)
                ((ByteOrderedDataInputStream) (obj1)).close();
            return false;
        }
        l1 = 16L;
        l = l2;
        int i;
        if(l2 <= (long)abyte0.length)
            break MISSING_BLOCK_LABEL_139;
        i = abyte0.length;
        l = i;
        l1 = l - l1;
        if(l1 < 8L)
        {
            if(obj1 != null)
                ((ByteOrderedDataInputStream) (obj1)).close();
            return false;
        }
        abyte0 = new byte[4];
        boolean flag1;
        i = 0;
        flag1 = false;
        l2 = 0L;
_L2:
        int j;
        int k;
        if(l2 >= l1 / 4L)
            break MISSING_BLOCK_LABEL_330;
        j = ((ByteOrderedDataInputStream) (obj1)).read(abyte0);
        k = abyte0.length;
        if(j != k)
        {
            if(obj1 != null)
                ((ByteOrderedDataInputStream) (obj1)).close();
            return false;
        }
        if(l2 != 1L)
            break; /* Loop/switch isn't completed */
        j = ((flag1) ? 1 : 0);
_L6:
        l2++;
        flag1 = j;
        if(true) goto _L2; else goto _L1
_L1:
        flag = Arrays.equals(abyte0, HEIF_BRAND_MIF1);
        if(!flag) goto _L4; else goto _L3
_L3:
        k = 1;
_L8:
        j = ((flag1) ? 1 : 0);
        i = k;
        if(!k) goto _L6; else goto _L5
_L5:
        j = ((flag1) ? 1 : 0);
        i = k;
        if(!flag1) goto _L6; else goto _L7
_L7:
        if(obj1 != null)
            ((ByteOrderedDataInputStream) (obj1)).close();
        return true;
_L4:
        flag = Arrays.equals(abyte0, HEIF_BRAND_HEIC);
        k = i;
        if(flag)
        {
            flag1 = true;
            k = i;
        }
          goto _L8
        if(obj1 != null)
            ((ByteOrderedDataInputStream) (obj1)).close();
_L10:
        return false;
        abyte0;
        abyte0 = obj;
_L13:
        if(abyte0 != null)
            abyte0.close();
        if(true) goto _L10; else goto _L9
_L9:
        abyte0;
        obj1 = abyte1;
_L12:
        if(obj1 != null)
            ((ByteOrderedDataInputStream) (obj1)).close();
        throw abyte0;
        abyte0;
        if(true) goto _L12; else goto _L11
_L11:
        abyte0;
        abyte0 = ((byte []) (obj1));
          goto _L13
    }

    private static boolean isJpegFormat(byte abyte0[])
        throws IOException
    {
        for(int i = 0; i < JPEG_SIGNATURE.length; i++)
            if(abyte0[i] != JPEG_SIGNATURE[i])
                return false;

        return true;
    }

    private boolean isOrfFormat(byte abyte0[])
        throws IOException
    {
        abyte0 = new ByteOrderedDataInputStream(abyte0);
        mExifByteOrder = readByteOrder(abyte0);
        abyte0.setByteOrder(mExifByteOrder);
        short word0 = abyte0.readShort();
        return word0 == 20306 || word0 == 21330;
    }

    private boolean isRafFormat(byte abyte0[])
        throws IOException
    {
        byte abyte1[] = "FUJIFILMCCD-RAW".getBytes();
        for(int i = 0; i < abyte1.length; i++)
            if(abyte0[i] != abyte1[i])
                return false;

        return true;
    }

    private boolean isRw2Format(byte abyte0[])
        throws IOException
    {
        abyte0 = new ByteOrderedDataInputStream(abyte0);
        mExifByteOrder = readByteOrder(abyte0);
        abyte0.setByteOrder(mExifByteOrder);
        return abyte0.readShort() == 85;
    }

    private static boolean isSeekableFD(FileDescriptor filedescriptor)
        throws IOException
    {
        try
        {
            Os.lseek(filedescriptor, 0L, OsConstants.SEEK_CUR);
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            return false;
        }
        return true;
    }

    private boolean isSupportedDataType(HashMap hashmap)
        throws IOException
    {
        ExifAttribute exifattribute = (ExifAttribute)hashmap.get("BitsPerSample");
        if(exifattribute != null)
        {
            int ai[] = (int[])ExifAttribute._2D_wrap0(exifattribute, mExifByteOrder);
            if(Arrays.equals(BITS_PER_SAMPLE_RGB, ai))
                return true;
            if(mMimeType == 3)
            {
                hashmap = (ExifAttribute)hashmap.get("PhotometricInterpretation");
                if(hashmap != null)
                {
                    int i = hashmap.getIntValue(mExifByteOrder);
                    if(i == 1 && Arrays.equals(ai, BITS_PER_SAMPLE_GREYSCALE_2) || i == 6 && Arrays.equals(ai, BITS_PER_SAMPLE_RGB))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean isThumbnail(HashMap hashmap)
        throws IOException
    {
        ExifAttribute exifattribute = (ExifAttribute)hashmap.get("ImageLength");
        hashmap = (ExifAttribute)hashmap.get("ImageWidth");
        if(exifattribute != null && hashmap != null)
        {
            int i = exifattribute.getIntValue(mExifByteOrder);
            int j = hashmap.getIntValue(mExifByteOrder);
            if(i <= 512 && j <= 512)
                return true;
        }
        return false;
    }

    private void loadAttributes(InputStream inputstream)
        throws IOException
    {
        int i = 0;
_L2:
        if(i >= EXIF_TAGS.length)
            break; /* Loop/switch isn't completed */
        mAttributes[i] = new HashMap();
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream, 5000);
        mMimeType = getMimeType((BufferedInputStream)bufferedinputstream);
        inputstream = JVM INSTR new #8   <Class ExifInterface$ByteOrderedDataInputStream>;
        inputstream.ByteOrderedDataInputStream(bufferedinputstream);
        mMimeType;
        JVM INSTR tableswitch 0 12: default 132
    //                   0 209
    //                   1 209
    //                   2 209
    //                   3 209
    //                   4 147
    //                   5 209
    //                   6 209
    //                   7 193
    //                   8 209
    //                   9 170
    //                   10 201
    //                   11 209
    //                   12 185;
           goto _L3 _L4 _L4 _L4 _L4 _L5 _L4 _L4 _L6 _L4 _L7 _L8 _L4 _L9
_L3:
        setThumbnailData(inputstream);
        mIsSupportedFile = true;
        addDefaultValuesForCompatibility();
_L10:
        return;
_L5:
        getJpegAttributes(inputstream, 0, 0);
          goto _L3
        inputstream;
_L12:
        mIsSupportedFile = false;
        addDefaultValuesForCompatibility();
          goto _L10
_L7:
        getRafAttributes(inputstream);
          goto _L3
        inputstream;
_L11:
        addDefaultValuesForCompatibility();
        throw inputstream;
_L9:
        getHeifAttributes(inputstream);
          goto _L3
_L6:
        getOrfAttributes(inputstream);
          goto _L3
_L8:
        getRw2Attributes(inputstream);
          goto _L3
_L4:
        getRawAttributes(inputstream);
          goto _L3
        inputstream;
          goto _L11
        inputstream;
          goto _L12
    }

    private void parseTiffHeaders(ByteOrderedDataInputStream byteordereddatainputstream, int i)
        throws IOException
    {
        mExifByteOrder = readByteOrder(byteordereddatainputstream);
        byteordereddatainputstream.setByteOrder(mExifByteOrder);
        int j = byteordereddatainputstream.readUnsignedShort();
        if(mMimeType != 7 && mMimeType != 10 && j != 42)
            throw new IOException((new StringBuilder()).append("Invalid start code: ").append(Integer.toHexString(j)).toString());
        j = byteordereddatainputstream.readInt();
        if(j < 8 || j >= i)
            throw new IOException((new StringBuilder()).append("Invalid first Ifd offset: ").append(j).toString());
        i = j - 8;
        if(i > 0 && byteordereddatainputstream.skipBytes(i) != i)
            throw new IOException((new StringBuilder()).append("Couldn't jump to first Ifd: ").append(i).toString());
        else
            return;
    }

    private void printAttributes()
    {
        for(int i = 0; i < mAttributes.length; i++)
        {
            Log.d("ExifInterface", (new StringBuilder()).append("The size of tag group[").append(i).append("]: ").append(mAttributes[i].size()).toString());
            java.util.Map.Entry entry;
            ExifAttribute exifattribute;
            for(Iterator iterator = mAttributes[i].entrySet().iterator(); iterator.hasNext(); Log.d("ExifInterface", (new StringBuilder()).append("tagName: ").append(entry.getKey()).append(", tagType: ").append(exifattribute.toString()).append(", tagValue: '").append(exifattribute.getStringValue(mExifByteOrder)).append("'").toString()))
            {
                entry = (java.util.Map.Entry)iterator.next();
                exifattribute = (ExifAttribute)entry.getValue();
            }

        }

    }

    private ByteOrder readByteOrder(ByteOrderedDataInputStream byteordereddatainputstream)
        throws IOException
    {
        short word0 = byteordereddatainputstream.readShort();
        switch(word0)
        {
        default:
            throw new IOException((new StringBuilder()).append("Invalid byte order: ").append(Integer.toHexString(word0)).toString());

        case 18761: 
            return ByteOrder.LITTLE_ENDIAN;

        case 19789: 
            return ByteOrder.BIG_ENDIAN;
        }
    }

    private void readExifSegment(byte abyte0[], int i)
        throws IOException
    {
        ByteOrderedDataInputStream byteordereddatainputstream = new ByteOrderedDataInputStream(abyte0);
        parseTiffHeaders(byteordereddatainputstream, abyte0.length);
        readImageFileDirectory(byteordereddatainputstream, i);
    }

    private void readImageFileDirectory(ByteOrderedDataInputStream byteordereddatainputstream, int i)
        throws IOException
    {
        short word0;
        short word1;
        if(ByteOrderedDataInputStream._2D_get1(byteordereddatainputstream) + 2 > ByteOrderedDataInputStream._2D_get0(byteordereddatainputstream))
            return;
        word0 = byteordereddatainputstream.readShort();
        if(ByteOrderedDataInputStream._2D_get1(byteordereddatainputstream) + word0 * 12 > ByteOrderedDataInputStream._2D_get0(byteordereddatainputstream))
            return;
        word1 = 0;
_L3:
        int j;
        int k;
        int l;
        long l1;
        ExifTag exiftag;
        long l2;
        if(word1 >= word0)
            break MISSING_BLOCK_LABEL_859;
        j = byteordereddatainputstream.readUnsignedShort();
        k = byteordereddatainputstream.readUnsignedShort();
        l = byteordereddatainputstream.readInt();
        l1 = byteordereddatainputstream.peek() + 4;
        exiftag = (ExifTag)sExifTagMapsForReading[i].get(Integer.valueOf(j));
        l2 = 0L;
        boolean flag = false;
        if(exiftag == null)
            Log.w("ExifInterface", (new StringBuilder()).append("Skip the tag entry since tag number is not defined: ").append(j).toString());
        else
        if(k <= 0 || k >= IFD_FORMAT_BYTES_PER_FORMAT.length)
        {
            Log.w("ExifInterface", (new StringBuilder()).append("Skip the tag entry since data format is invalid: ").append(k).toString());
        } else
        {
            l2 = (long)l * (long)IFD_FORMAT_BYTES_PER_FORMAT[k];
            if(l2 < 0L || l2 > 0x7fffffffL)
                Log.w("ExifInterface", (new StringBuilder()).append("Skip the tag entry since the number of components is invalid: ").append(l).toString());
            else
                flag = true;
        }
        if(flag) goto _L2; else goto _L1
_L1:
        byteordereddatainputstream.seek(l1);
_L14:
        word1++;
          goto _L3
_L2:
        if(l2 <= 4L) goto _L5; else goto _L4
_L4:
        int i1;
        i1 = byteordereddatainputstream.readInt();
        Object obj;
        long l3;
        if(mMimeType == 7)
        {
            if(exiftag.name == "MakerNote")
                mOrfMakerNoteOffset = i1;
            else
            if(i == 6 && exiftag.name == "ThumbnailImage")
            {
                mOrfThumbnailOffset = i1;
                mOrfThumbnailLength = l;
                ExifAttribute exifattribute2 = ExifAttribute.createUShort(6, mExifByteOrder);
                ExifAttribute exifattribute3 = ExifAttribute.createULong(mOrfThumbnailOffset, mExifByteOrder);
                ExifAttribute exifattribute = ExifAttribute.createULong(mOrfThumbnailLength, mExifByteOrder);
                mAttributes[4].put("Compression", exifattribute2);
                mAttributes[4].put("JPEGInterchangeFormat", exifattribute3);
                mAttributes[4].put("JPEGInterchangeFormatLength", exifattribute);
            }
        } else
        if(mMimeType == 10 && exiftag.name == "JpgFromRaw")
            mRw2JpgFromRawOffset = i1;
        if((long)i1 + l2 > (long)ByteOrderedDataInputStream._2D_get0(byteordereddatainputstream)) goto _L7; else goto _L6
_L6:
        byteordereddatainputstream.seek(i1);
_L5:
        obj = sExifPointerTagMap.get(Integer.valueOf(j));
        if(obj == null)
            break MISSING_BLOCK_LABEL_707;
        l3 = -1L;
        l2 = l3;
        k;
        JVM INSTR tableswitch 3 13: default 408
    //                   3 638
    //                   4 658
    //                   5 412
    //                   6 412
    //                   7 412
    //                   8 648
    //                   9 667
    //                   10 412
    //                   11 412
    //                   12 412
    //                   13 667;
           goto _L8 _L9 _L10 _L11 _L11 _L11 _L12 _L13 _L11 _L11 _L11 _L13
_L13:
        break MISSING_BLOCK_LABEL_667;
_L11:
        break; /* Loop/switch isn't completed */
_L8:
        l2 = l3;
_L15:
        if(l2 > 0L && l2 < (long)ByteOrderedDataInputStream._2D_get0(byteordereddatainputstream))
        {
            byteordereddatainputstream.seek(l2);
            readImageFileDirectory(byteordereddatainputstream, ((Integer)obj).intValue());
        } else
        {
            Log.w("ExifInterface", (new StringBuilder()).append("Skip jump into the IFD since its offset is invalid: ").append(l2).toString());
        }
        byteordereddatainputstream.seek(l1);
          goto _L14
_L7:
        Log.w("ExifInterface", (new StringBuilder()).append("Skip the tag entry since data offset is invalid: ").append(i1).toString());
        byteordereddatainputstream.seek(l1);
          goto _L14
_L9:
        l2 = byteordereddatainputstream.readUnsignedShort();
          goto _L15
_L12:
        l2 = byteordereddatainputstream.readShort();
          goto _L15
_L10:
        l2 = byteordereddatainputstream.readUnsignedInt();
          goto _L15
        l2 = byteordereddatainputstream.readInt();
          goto _L15
        byte abyte0[] = new byte[(int)l2];
        byteordereddatainputstream.readFully(abyte0);
        ExifAttribute exifattribute1 = new ExifAttribute(k, l, abyte0, null);
        mAttributes[i].put(exiftag.name, exifattribute1);
        if(exiftag.name == "DNGVersion")
            mMimeType = 3;
        if((exiftag.name == "Make" || exiftag.name == "Model") && exifattribute1.getStringValue(mExifByteOrder).contains("PENTAX") || exiftag.name == "Compression" && exifattribute1.getIntValue(mExifByteOrder) == 65535)
            mMimeType = 8;
        if((long)byteordereddatainputstream.peek() != l1)
            byteordereddatainputstream.seek(l1);
          goto _L14
        if(byteordereddatainputstream.peek() + 4 > ByteOrderedDataInputStream._2D_get0(byteordereddatainputstream)) goto _L17; else goto _L16
_L16:
        i = byteordereddatainputstream.readInt();
        if(i <= 8 || i >= ByteOrderedDataInputStream._2D_get0(byteordereddatainputstream)) goto _L17; else goto _L18
_L18:
        byteordereddatainputstream.seek(i);
        if(!mAttributes[4].isEmpty()) goto _L20; else goto _L19
_L19:
        readImageFileDirectory(byteordereddatainputstream, 4);
_L17:
        return;
_L20:
        if(mAttributes[5].isEmpty())
            readImageFileDirectory(byteordereddatainputstream, 5);
        if(true) goto _L17; else goto _L21
_L21:
    }

    private void removeAttribute(String s)
    {
        for(int i = 0; i < EXIF_TAGS.length; i++)
            mAttributes[i].remove(s);

    }

    private void retrieveJpegImageSize(ByteOrderedDataInputStream byteordereddatainputstream, int i)
        throws IOException
    {
        ExifAttribute exifattribute = (ExifAttribute)mAttributes[i].get("ImageLength");
        ExifAttribute exifattribute2 = (ExifAttribute)mAttributes[i].get("ImageWidth");
        if(exifattribute == null || exifattribute2 == null)
        {
            ExifAttribute exifattribute1 = (ExifAttribute)mAttributes[i].get("JPEGInterchangeFormat");
            if(exifattribute1 != null)
                getJpegAttributes(byteordereddatainputstream, exifattribute1.getIntValue(mExifByteOrder), i);
        }
    }

    private void saveJpegAttributes(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        byte abyte0[];
        inputstream = new DataInputStream(inputstream);
        outputstream = new ByteOrderedDataOutputStream(outputstream, ByteOrder.BIG_ENDIAN);
        if(inputstream.readByte() != -1)
            throw new IOException("Invalid marker");
        outputstream.writeByte(-1);
        if(inputstream.readByte() != -40)
            throw new IOException("Invalid marker");
        outputstream.writeByte(-40);
        outputstream.writeByte(-1);
        outputstream.writeByte(-31);
        writeExifSegment(outputstream, 6);
        abyte0 = new byte[4096];
_L6:
        int i;
        if(inputstream.readByte() != -1)
            throw new IOException("Invalid marker");
        i = inputstream.readByte();
        i;
        JVM INSTR lookupswitch 3: default 156
    //                   -39: 389
    //                   -38: 389
    //                   -31: 205;
           goto _L1 _L2 _L2 _L3
_L1:
        outputstream.writeByte(-1);
        outputstream.writeByte(i);
        i = inputstream.readUnsignedShort();
        outputstream.writeUnsignedShort(i);
        int j = i - 2;
        i = j;
        if(j < 0)
            throw new IOException("Invalid length");
          goto _L4
_L3:
        int k;
        byte abyte1[];
        k = inputstream.readUnsignedShort() - 2;
        if(k < 0)
            throw new IOException("Invalid length");
        abyte1 = new byte[6];
        if(k < 6)
            break; /* Loop/switch isn't completed */
        if(inputstream.read(abyte1) != 6)
            throw new IOException("Invalid exif");
        if(!Arrays.equals(abyte1, IDENTIFIER_EXIF_APP1))
            break; /* Loop/switch isn't completed */
        if(inputstream.skipBytes(k - 6) != k - 6)
            throw new IOException("Invalid length");
        if(true) goto _L6; else goto _L5
_L5:
        outputstream.writeByte(-1);
        outputstream.writeByte(i);
        outputstream.writeUnsignedShort(k + 2);
        i = k;
        if(k >= 6)
        {
            i = k - 6;
            outputstream.write(abyte1);
        }
_L9:
        if(i <= 0) goto _L6; else goto _L7
_L7:
        k = inputstream.read(abyte0, 0, Math.min(i, abyte0.length));
        if(k < 0) goto _L6; else goto _L8
_L8:
        outputstream.write(abyte0, 0, k);
        i -= k;
          goto _L9
_L2:
        outputstream.writeByte(-1);
        outputstream.writeByte(i);
        Streams.copy(inputstream, outputstream);
        return;
_L4:
        if(i <= 0) goto _L6; else goto _L10
_L10:
        k = inputstream.read(abyte0, 0, Math.min(i, abyte0.length));
        if(k < 0) goto _L6; else goto _L11
_L11:
        outputstream.write(abyte0, 0, k);
        i -= k;
          goto _L4
    }

    private void setThumbnailData(ByteOrderedDataInputStream byteordereddatainputstream)
        throws IOException
    {
        HashMap hashmap;
        ExifAttribute exifattribute;
        hashmap = mAttributes[4];
        exifattribute = (ExifAttribute)hashmap.get("Compression");
        if(exifattribute == null) goto _L2; else goto _L1
_L1:
        mThumbnailCompression = exifattribute.getIntValue(mExifByteOrder);
        mThumbnailCompression;
        JVM INSTR lookupswitch 3: default 72
    //                   1: 82
    //                   6: 73
    //                   7: 82;
           goto _L3 _L4 _L5 _L4
_L3:
        return;
_L5:
        handleThumbnailFromJfif(byteordereddatainputstream, hashmap);
        continue; /* Loop/switch isn't completed */
_L4:
        if(isSupportedDataType(hashmap))
            handleThumbnailFromStrips(byteordereddatainputstream, hashmap);
        continue; /* Loop/switch isn't completed */
_L2:
        handleThumbnailFromJfif(byteordereddatainputstream, hashmap);
        if(true) goto _L3; else goto _L6
_L6:
    }

    private void swapBasedOnImageSize(int i, int j)
        throws IOException
    {
        ExifAttribute exifattribute;
        ExifAttribute exifattribute1;
        ExifAttribute exifattribute2;
        ExifAttribute exifattribute3;
        if(mAttributes[i].isEmpty() || mAttributes[j].isEmpty())
            return;
        exifattribute = (ExifAttribute)mAttributes[i].get("ImageLength");
        exifattribute1 = (ExifAttribute)mAttributes[i].get("ImageWidth");
        exifattribute2 = (ExifAttribute)mAttributes[j].get("ImageLength");
        exifattribute3 = (ExifAttribute)mAttributes[j].get("ImageWidth");
        break MISSING_BLOCK_LABEL_92;
        while(true) 
        {
            do
                return;
            while(exifattribute == null || exifattribute1 == null || exifattribute2 == null || exifattribute3 == null);
            int k = exifattribute.getIntValue(mExifByteOrder);
            int l = exifattribute1.getIntValue(mExifByteOrder);
            int i1 = exifattribute2.getIntValue(mExifByteOrder);
            int j1 = exifattribute3.getIntValue(mExifByteOrder);
            if(k < i1 && l < j1)
            {
                HashMap hashmap = mAttributes[i];
                mAttributes[i] = mAttributes[j];
                mAttributes[j] = hashmap;
            }
        }
    }

    private boolean updateAttribute(String s, ExifAttribute exifattribute)
    {
        boolean flag = false;
        for(int i = 0; i < EXIF_TAGS.length; i++)
            if(mAttributes[i].containsKey(s))
            {
                mAttributes[i].put(s, exifattribute);
                flag = true;
            }

        return flag;
    }

    private void updateImageSizeValues(ByteOrderedDataInputStream byteordereddatainputstream, int i)
        throws IOException
    {
        ExifAttribute exifattribute;
        ExifAttribute exifattribute1;
        ExifAttribute exifattribute2;
        Object obj;
        ExifAttribute exifattribute4;
        exifattribute = (ExifAttribute)mAttributes[i].get("DefaultCropSize");
        exifattribute1 = (ExifAttribute)mAttributes[i].get("SensorTopBorder");
        exifattribute2 = (ExifAttribute)mAttributes[i].get("SensorLeftBorder");
        obj = (ExifAttribute)mAttributes[i].get("SensorBottomBorder");
        exifattribute4 = (ExifAttribute)mAttributes[i].get("SensorRightBorder");
        if(exifattribute == null) goto _L2; else goto _L1
_L1:
        if(exifattribute.format == 5)
        {
            obj = (Rational[])ExifAttribute._2D_wrap0(exifattribute, mExifByteOrder);
            byteordereddatainputstream = ExifAttribute.createURational(obj[0], mExifByteOrder);
            obj = ExifAttribute.createURational(obj[1], mExifByteOrder);
        } else
        {
            obj = (int[])ExifAttribute._2D_wrap0(exifattribute, mExifByteOrder);
            byteordereddatainputstream = ExifAttribute.createUShort(obj[0], mExifByteOrder);
            obj = ExifAttribute.createUShort(obj[1], mExifByteOrder);
        }
        mAttributes[i].put("ImageWidth", byteordereddatainputstream);
        mAttributes[i].put("ImageLength", obj);
_L4:
        return;
_L2:
        if(exifattribute1 != null && exifattribute2 != null && obj != null && exifattribute4 != null)
        {
            int j = exifattribute1.getIntValue(mExifByteOrder);
            int k = ((ExifAttribute) (obj)).getIntValue(mExifByteOrder);
            int l = exifattribute4.getIntValue(mExifByteOrder);
            int i1 = exifattribute2.getIntValue(mExifByteOrder);
            if(k > j && l > i1)
            {
                ExifAttribute exifattribute3 = ExifAttribute.createUShort(k - j, mExifByteOrder);
                byteordereddatainputstream = ExifAttribute.createUShort(l - i1, mExifByteOrder);
                mAttributes[i].put("ImageLength", exifattribute3);
                mAttributes[i].put("ImageWidth", byteordereddatainputstream);
            }
        } else
        {
            retrieveJpegImageSize(byteordereddatainputstream, i);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void validateImages(InputStream inputstream)
        throws IOException
    {
        swapBasedOnImageSize(0, 5);
        swapBasedOnImageSize(0, 4);
        swapBasedOnImageSize(5, 4);
        inputstream = (ExifAttribute)mAttributes[1].get("PixelXDimension");
        ExifAttribute exifattribute = (ExifAttribute)mAttributes[1].get("PixelYDimension");
        if(inputstream != null && exifattribute != null)
        {
            mAttributes[0].put("ImageWidth", inputstream);
            mAttributes[0].put("ImageLength", exifattribute);
        }
        if(mAttributes[4].isEmpty() && isThumbnail(mAttributes[5]))
        {
            mAttributes[4] = mAttributes[5];
            mAttributes[5] = new HashMap();
        }
        if(!isThumbnail(mAttributes[4]))
            Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
    }

    private int writeExifSegment(ByteOrderedDataOutputStream byteordereddataoutputstream, int i)
        throws IOException
    {
        int ai[] = new int[EXIF_TAGS.length];
        int ai1[] = new int[EXIF_TAGS.length];
        ExifTag aexiftag[] = EXIF_POINTER_TAGS;
        int j = 0;
        for(int l = aexiftag.length; j < l; j++)
            removeAttribute(aexiftag[j].name);

        removeAttribute(JPEG_INTERCHANGE_FORMAT_TAG.name);
        removeAttribute(JPEG_INTERCHANGE_FORMAT_LENGTH_TAG.name);
        for(j = 0; j < EXIF_TAGS.length; j++)
        {
            Object aobj[] = mAttributes[j].entrySet().toArray();
            int i1 = 0;
            for(int j2 = aobj.length; i1 < j2; i1++)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)aobj[i1];
                if(entry.getValue() == null)
                    mAttributes[j].remove(entry.getKey());
            }

        }

        if(!mAttributes[1].isEmpty())
            mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(0L, mExifByteOrder));
        if(!mAttributes[2].isEmpty())
            mAttributes[0].put(EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(0L, mExifByteOrder));
        if(!mAttributes[3].isEmpty())
            mAttributes[1].put(EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(0L, mExifByteOrder));
        if(mHasThumbnail)
        {
            mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_TAG.name, ExifAttribute.createULong(0L, mExifByteOrder));
            mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_LENGTH_TAG.name, ExifAttribute.createULong(mThumbnailLength, mExifByteOrder));
        }
        for(j = 0; j < EXIF_TAGS.length; j++)
        {
            int j1 = 0;
            Iterator iterator1 = mAttributes[j].entrySet().iterator();
            do
            {
                if(!iterator1.hasNext())
                    break;
                int k2 = ((ExifAttribute)((java.util.Map.Entry)iterator1.next()).getValue()).size();
                if(k2 > 4)
                    j1 += k2;
            } while(true);
            ai1[j] = ai1[j] + j1;
        }

        j = 8;
        for(int l2 = 0; l2 < EXIF_TAGS.length;)
        {
            int k1 = j;
            if(!mAttributes[l2].isEmpty())
            {
                ai[l2] = j;
                k1 = j + (mAttributes[l2].size() * 12 + 2 + 4 + ai1[l2]);
            }
            l2++;
            j = k1;
        }

        int l1 = j;
        if(mHasThumbnail)
        {
            mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_TAG.name, ExifAttribute.createULong(j, mExifByteOrder));
            mThumbnailOffset = i + j;
            l1 = j + mThumbnailLength;
        }
        int i3 = l1 + 8;
        if(!mAttributes[1].isEmpty())
            mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(ai[1], mExifByteOrder));
        if(!mAttributes[2].isEmpty())
            mAttributes[0].put(EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(ai[2], mExifByteOrder));
        if(!mAttributes[3].isEmpty())
            mAttributes[1].put(EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(ai[3], mExifByteOrder));
        byteordereddataoutputstream.writeUnsignedShort(i3);
        byteordereddataoutputstream.write(IDENTIFIER_EXIF_APP1);
        int j3;
        if(mExifByteOrder == ByteOrder.BIG_ENDIAN)
        {
            i = 19789;
            j3 = i;
        } else
        {
            i = 18761;
            j3 = i;
        }
        byteordereddataoutputstream.writeShort(j3);
        byteordereddataoutputstream.setByteOrder(mExifByteOrder);
        byteordereddataoutputstream.writeUnsignedShort(42);
        byteordereddataoutputstream.writeUnsignedInt(8L);
label0:
        for(i = 0; i < EXIF_TAGS.length; i++)
        {
            if(mAttributes[i].isEmpty())
                continue;
            byteordereddataoutputstream.writeUnsignedShort(mAttributes[i].size());
            int k = ai[i] + 2 + mAttributes[i].size() * 12 + 4;
            Iterator iterator = mAttributes[i].entrySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Object obj = (java.util.Map.Entry)iterator.next();
                int k3 = ((ExifTag)sExifTagMapsForWriting[i].get(((java.util.Map.Entry) (obj)).getKey())).number;
                obj = (ExifAttribute)((java.util.Map.Entry) (obj)).getValue();
                int i2 = ((ExifAttribute) (obj)).size();
                byteordereddataoutputstream.writeUnsignedShort(k3);
                byteordereddataoutputstream.writeUnsignedShort(((ExifAttribute) (obj)).format);
                byteordereddataoutputstream.writeInt(((ExifAttribute) (obj)).numberOfComponents);
                if(i2 > 4)
                {
                    byteordereddataoutputstream.writeUnsignedInt(k);
                    k += i2;
                } else
                {
                    byteordereddataoutputstream.write(((ExifAttribute) (obj)).bytes);
                    if(i2 < 4)
                        while(i2 < 4) 
                        {
                            byteordereddataoutputstream.writeByte(0);
                            i2++;
                        }
                }
            } while(true);
            Iterator iterator2;
            if(i == 0 && mAttributes[4].isEmpty() ^ true)
                byteordereddataoutputstream.writeUnsignedInt(ai[4]);
            else
                byteordereddataoutputstream.writeUnsignedInt(0L);
            iterator2 = mAttributes[i].entrySet().iterator();
            do
            {
                ExifAttribute exifattribute;
                do
                {
                    if(!iterator2.hasNext())
                        continue label0;
                    exifattribute = (ExifAttribute)((java.util.Map.Entry)iterator2.next()).getValue();
                } while(exifattribute.bytes.length <= 4);
                byteordereddataoutputstream.write(exifattribute.bytes, 0, exifattribute.bytes.length);
            } while(true);
        }

        if(mHasThumbnail)
            byteordereddataoutputstream.write(getThumbnailBytes());
        byteordereddataoutputstream.setByteOrder(ByteOrder.BIG_ENDIAN);
        return i3;
    }

    public double getAltitude(double d)
    {
        int i = -1;
        double d1 = getAttributeDouble("GPSAltitude", -1D);
        int j = getAttributeInt("GPSAltitudeRef", -1);
        if(d1 >= 0.0D && j >= 0)
        {
            if(j != 1)
                i = 1;
            return (double)i * d1;
        } else
        {
            return d;
        }
    }

    public String getAttribute(String s)
    {
        ExifAttribute exifattribute = getExifAttribute(s);
        if(exifattribute != null)
        {
            if(!sTagSetForCompatibility.contains(s))
                return exifattribute.getStringValue(mExifByteOrder);
            if(s.equals("GPSTimeStamp"))
            {
                if(exifattribute.format != 5 && exifattribute.format != 10)
                    return null;
                s = (Rational[])ExifAttribute._2D_wrap0(exifattribute, mExifByteOrder);
                if(s.length != 3)
                    return null;
                else
                    return String.format("%02d:%02d:%02d", new Object[] {
                        Integer.valueOf((int)((float)((Rational) (s[0])).numerator / (float)((Rational) (s[0])).denominator)), Integer.valueOf((int)((float)((Rational) (s[1])).numerator / (float)((Rational) (s[1])).denominator)), Integer.valueOf((int)((float)((Rational) (s[2])).numerator / (float)((Rational) (s[2])).denominator))
                    });
            }
            try
            {
                s = Double.toString(exifattribute.getDoubleValue(mExifByteOrder));
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return null;
            }
            return s;
        } else
        {
            return null;
        }
    }

    public double getAttributeDouble(String s, double d)
    {
        s = getExifAttribute(s);
        if(s == null)
            return d;
        double d1;
        try
        {
            d1 = s.getDoubleValue(mExifByteOrder);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return d;
        }
        return d1;
    }

    public int getAttributeInt(String s, int i)
    {
        s = getExifAttribute(s);
        if(s == null)
            return i;
        int j;
        try
        {
            j = s.getIntValue(mExifByteOrder);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return i;
        }
        return j;
    }

    public long getDateTime()
    {
        long l;
        long l1;
        Object obj = getAttribute("DateTime");
        if(obj == null || sNonZeroTimePattern.matcher(((CharSequence) (obj))).matches() ^ true)
            return -1L;
        ParsePosition parseposition = new ParsePosition(0);
        try
        {
            obj = sFormatter.parse(((String) (obj)), parseposition);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            return -1L;
        }
        if(obj == null)
            return -1L;
        l = ((Date) (obj)).getTime();
        obj = getAttribute("SubSecTime");
        l1 = l;
        if(obj == null)
            break MISSING_BLOCK_LABEL_108;
        l1 = Long.parseLong(((String) (obj)));
_L2:
        if(l1 <= 1000L)
            break; /* Loop/switch isn't completed */
        l1 /= 10L;
        if(true) goto _L2; else goto _L1
_L1:
        l1 = l + l1;
_L4:
        return l1;
        NumberFormatException numberformatexception;
        numberformatexception;
        l1 = l;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public long getGpsDateTime()
    {
        String s = getAttribute("GPSDateStamp");
        Object obj;
        for(obj = getAttribute("GPSTimeStamp"); s == null || obj == null || !sNonZeroTimePattern.matcher(s).matches() && sNonZeroTimePattern.matcher(((CharSequence) (obj))).matches() ^ true;)
            return -1L;

        s = (new StringBuilder()).append(s).append(' ').append(((String) (obj))).toString();
        obj = new ParsePosition(0);
        long l;
        try
        {
            obj = sFormatter.parse(s, ((ParsePosition) (obj)));
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            return -1L;
        }
        if(obj == null)
            return -1L;
        l = ((Date) (obj)).getTime();
        return l;
    }

    public boolean getLatLong(float af[])
    {
        String s;
        String s1;
        String s2;
        String s3;
        s = getAttribute("GPSLatitude");
        s1 = getAttribute("GPSLatitudeRef");
        s2 = getAttribute("GPSLongitude");
        s3 = getAttribute("GPSLongitudeRef");
        if(s == null || s1 == null || s2 == null || s3 == null)
            break MISSING_BLOCK_LABEL_73;
        af[0] = convertRationalLatLonToFloat(s, s1);
        af[1] = convertRationalLatLonToFloat(s2, s3);
        return true;
        af;
        return false;
    }

    public byte[] getThumbnail()
    {
        if(mThumbnailCompression == 6 || mThumbnailCompression == 7)
            return getThumbnailBytes();
        else
            return null;
    }

    public Bitmap getThumbnailBitmap()
    {
        if(!mHasThumbnail)
            return null;
        if(mThumbnailBytes == null)
            mThumbnailBytes = getThumbnailBytes();
        if(mThumbnailCompression == 6 || mThumbnailCompression == 7)
            return BitmapFactory.decodeByteArray(mThumbnailBytes, 0, mThumbnailLength);
        if(mThumbnailCompression == 1)
        {
            int ai[] = new int[mThumbnailBytes.length / 3];
            for(int i = 0; i < ai.length; i++)
                ai[i] = (mThumbnailBytes[i * 3] << 16) + 0 + (mThumbnailBytes[i * 3 + 1] << 8) + mThumbnailBytes[i * 3 + 2];

            ExifAttribute exifattribute = (ExifAttribute)mAttributes[4].get("ImageLength");
            ExifAttribute exifattribute1 = (ExifAttribute)mAttributes[4].get("ImageWidth");
            if(exifattribute != null && exifattribute1 != null)
            {
                int j = exifattribute.getIntValue(mExifByteOrder);
                return Bitmap.createBitmap(ai, exifattribute1.getIntValue(mExifByteOrder), j, android.graphics.Bitmap.Config.ARGB_8888);
            }
        }
        return null;
    }

    public byte[] getThumbnailBytes()
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        if(!mHasThumbnail)
            return null;
        if(mThumbnailBytes != null)
            return mThumbnailBytes;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = obj;
        obj4 = obj1;
        if(mAssetInputStream == null) goto _L2; else goto _L1
_L1:
        obj3 = obj;
        obj4 = obj1;
        obj2 = mAssetInputStream;
        obj3 = obj2;
        obj4 = obj2;
        if(!((InputStream) (obj2)).markSupported()) goto _L4; else goto _L3
_L3:
        obj3 = obj2;
        obj4 = obj2;
        ((InputStream) (obj2)).reset();
_L6:
        if(obj2 != null)
            break; /* Loop/switch isn't completed */
        obj3 = obj2;
        obj4 = obj2;
        obj = JVM INSTR new #1715 <Class FileNotFoundException>;
        obj3 = obj2;
        obj4 = obj2;
        ((FileNotFoundException) (obj)).FileNotFoundException();
        obj3 = obj2;
        obj4 = obj2;
        try
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj2)
        {
            obj4 = obj3;
        }
        Log.d("ExifInterface", "Encountered exception while getting thumbnail", ((Throwable) (obj2)));
        IoUtils.closeQuietly(((AutoCloseable) (obj3)));
        return null;
_L4:
        obj3 = obj2;
        obj4 = obj2;
        Log.d("ExifInterface", "Cannot read thumbnail from inputstream without mark/reset support");
        IoUtils.closeQuietly(((AutoCloseable) (obj2)));
        return null;
_L2:
        obj3 = obj;
        obj4 = obj1;
        if(mFilename == null)
            break MISSING_BLOCK_LABEL_183;
        obj3 = obj;
        obj4 = obj1;
        obj2 = new FileInputStream(mFilename);
        continue; /* Loop/switch isn't completed */
        obj3 = obj;
        obj4 = obj1;
        if(mSeekableFileDescriptor == null)
            continue; /* Loop/switch isn't completed */
        obj3 = obj;
        obj4 = obj1;
        obj2 = Os.dup(mSeekableFileDescriptor);
        obj3 = obj;
        obj4 = obj1;
        Os.lseek(((FileDescriptor) (obj2)), 0L, OsConstants.SEEK_SET);
        obj3 = obj;
        obj4 = obj1;
        obj2 = new FileInputStream(((FileDescriptor) (obj2)));
        if(true) goto _L6; else goto _L5
_L5:
        obj3 = obj2;
        obj4 = obj2;
        if(((InputStream) (obj2)).skip(mThumbnailOffset) == (long)mThumbnailOffset)
            break MISSING_BLOCK_LABEL_306;
        obj3 = obj2;
        obj4 = obj2;
        obj = JVM INSTR new #956 <Class IOException>;
        obj3 = obj2;
        obj4 = obj2;
        ((IOException) (obj)).IOException("Corrupted image");
        obj3 = obj2;
        obj4 = obj2;
        throw obj;
        obj2;
        IoUtils.closeQuietly(((AutoCloseable) (obj4)));
        throw obj2;
        obj3 = obj2;
        obj4 = obj2;
        byte abyte0[] = new byte[mThumbnailLength];
        obj3 = obj2;
        obj4 = obj2;
        if(((InputStream) (obj2)).read(abyte0) == mThumbnailLength)
            break MISSING_BLOCK_LABEL_368;
        obj3 = obj2;
        obj4 = obj2;
        abyte0 = JVM INSTR new #956 <Class IOException>;
        obj3 = obj2;
        obj4 = obj2;
        abyte0.IOException("Corrupted image");
        obj3 = obj2;
        obj4 = obj2;
        throw abyte0;
        obj3 = obj2;
        obj4 = obj2;
        mThumbnailBytes = abyte0;
        IoUtils.closeQuietly(((AutoCloseable) (obj2)));
        return abyte0;
    }

    public long[] getThumbnailRange()
    {
        if(!mHasThumbnail)
            return null;
        else
            return (new long[] {
                (long)mThumbnailOffset, (long)mThumbnailLength
            });
    }

    public boolean hasThumbnail()
    {
        return mHasThumbnail;
    }

    public boolean isThumbnailCompressed()
    {
        if(!mHasThumbnail)
            return false;
        return mThumbnailCompression == 6 || mThumbnailCompression == 7;
    }

    public void saveAttributes()
        throws IOException
    {
        Object obj;
        Object obj1;
        File file;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj11;
        if(!mIsSupportedFile || mMimeType != 4)
            throw new IOException("ExifInterface only supports saving attributes on JPEG formats.");
        if(mIsInputStream || mSeekableFileDescriptor == null && mFilename == null)
            throw new IOException("ExifInterface does not support saving attributes for the current input.");
        mThumbnailBytes = getThumbnail();
        obj = null;
        obj1 = null;
        file = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = null;
        obj6 = null;
        obj7 = null;
        obj8 = obj;
        obj11 = obj3;
        if(mFilename == null) goto _L2; else goto _L1
_L1:
        obj8 = obj;
        obj11 = obj3;
        obj7 = JVM INSTR new #1746 <Class File>;
        obj8 = obj;
        obj11 = obj3;
        StringBuilder stringbuilder = JVM INSTR new #1124 <Class StringBuilder>;
        obj8 = obj;
        obj11 = obj3;
        stringbuilder.StringBuilder();
        obj8 = obj;
        obj11 = obj3;
        ((File) (obj7)).File(stringbuilder.append(mFilename).append(".tmp").toString());
        try
        {
            obj8 = JVM INSTR new #1746 <Class File>;
            ((File) (obj8)).File(mFilename);
            if(!((File) (obj8)).renameTo(((File) (obj7))))
            {
                obj11 = JVM INSTR new #956 <Class IOException>;
                obj8 = JVM INSTR new #1124 <Class StringBuilder>;
                ((StringBuilder) (obj8)).StringBuilder();
                ((IOException) (obj11)).IOException(((StringBuilder) (obj8)).append("Could'nt rename to ").append(((File) (obj7)).getAbsolutePath()).toString());
                throw obj11;
            }
            break MISSING_BLOCK_LABEL_691;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            obj11 = obj6;
            obj7 = obj2;
        }
_L12:
        obj8 = obj7;
        throw ((ErrnoException) (obj1)).rethrowAsIOException();
        obj7;
        obj1 = obj8;
_L11:
        IoUtils.closeQuietly(((AutoCloseable) (obj1)));
        IoUtils.closeQuietly(((AutoCloseable) (obj11)));
        throw obj7;
_L2:
        obj8 = obj;
        obj11 = obj3;
        obj1 = file;
        if(mSeekableFileDescriptor == null)
            break MISSING_BLOCK_LABEL_367;
        obj8 = obj;
        obj11 = obj3;
        file = File.createTempFile("temp", "jpg");
        obj8 = obj;
        obj11 = obj3;
        Os.lseek(mSeekableFileDescriptor, 0L, OsConstants.SEEK_SET);
        obj8 = obj;
        obj11 = obj3;
        obj1 = new FileInputStream(mSeekableFileDescriptor);
        obj7 = JVM INSTR new #1768 <Class FileOutputStream>;
        ((FileOutputStream) (obj7)).FileOutputStream(file);
        Streams.copy(((InputStream) (obj1)), ((OutputStream) (obj7)));
        obj5 = obj7;
        obj7 = file;
_L13:
        IoUtils.closeQuietly(((AutoCloseable) (obj1)));
        IoUtils.closeQuietly(((AutoCloseable) (obj5)));
        obj11 = null;
        obj2 = null;
        obj3 = null;
        file = null;
        obj6 = null;
        obj4 = null;
        obj = null;
        obj1 = obj11;
        obj8 = file;
        obj5 = JVM INSTR new #993 <Class FileInputStream>;
        obj1 = obj11;
        obj8 = file;
        ((FileInputStream) (obj5)).FileInputStream(((File) (obj7)));
        obj11 = obj6;
        obj8 = obj4;
        if(mFilename == null) goto _L4; else goto _L3
_L3:
        obj11 = obj6;
        obj8 = obj4;
        obj1 = JVM INSTR new #1768 <Class FileOutputStream>;
        obj11 = obj6;
        obj8 = obj4;
        ((FileOutputStream) (obj1)).FileOutputStream(mFilename);
_L6:
        obj11 = obj1;
        obj8 = obj1;
        saveJpegAttributes(((InputStream) (obj5)), ((OutputStream) (obj1)));
        IoUtils.closeQuietly(((AutoCloseable) (obj5)));
        IoUtils.closeQuietly(((AutoCloseable) (obj1)));
        ((File) (obj7)).delete();
        mThumbnailBytes = null;
        return;
_L4:
        obj1 = obj;
        obj11 = obj6;
        obj8 = obj4;
        if(mSeekableFileDescriptor == null)
            continue; /* Loop/switch isn't completed */
        obj11 = obj6;
        obj8 = obj4;
        Os.lseek(mSeekableFileDescriptor, 0L, OsConstants.SEEK_SET);
        obj11 = obj6;
        obj8 = obj4;
        obj1 = new FileOutputStream(mSeekableFileDescriptor);
        if(true) goto _L6; else goto _L5
_L5:
        obj11;
        obj8 = obj3;
        obj1 = obj2;
_L10:
        throw ((ErrnoException) (obj11)).rethrowAsIOException();
        obj11;
        obj5 = obj1;
_L8:
        IoUtils.closeQuietly(((AutoCloseable) (obj5)));
        IoUtils.closeQuietly(((AutoCloseable) (obj8)));
        ((File) (obj7)).delete();
        throw obj11;
        obj1;
        obj8 = obj11;
        obj11 = obj1;
        if(true) goto _L8; else goto _L7
_L7:
        obj11;
        obj1 = obj5;
        if(true) goto _L10; else goto _L9
_L9:
        obj7;
        obj11 = obj4;
          goto _L11
        obj7;
        obj11 = obj4;
          goto _L11
        Object obj9;
        obj9;
        obj11 = obj7;
        obj7 = obj9;
          goto _L11
        obj1;
        obj7 = obj2;
        obj11 = obj6;
          goto _L12
        obj7;
        Object obj10 = obj1;
        obj1 = obj7;
        obj7 = obj10;
        obj11 = obj6;
          goto _L12
        obj10;
        obj11 = obj7;
        obj7 = obj1;
        obj1 = obj10;
          goto _L12
        obj1 = file;
          goto _L13
    }

    public void setAttribute(String s, String s1)
    {
        Object obj;
        int i;
        obj = s1;
        if(s1 != null)
        {
            obj = s1;
            if(sTagSetForCompatibility.contains(s))
                if(s.equals("GPSTimeStamp"))
                {
                    obj = sGpsTimestampPattern.matcher(s1);
                    if(!((Matcher) (obj)).find())
                    {
                        Log.w("ExifInterface", (new StringBuilder()).append("Invalid value for ").append(s).append(" : ").append(s1).toString());
                        return;
                    }
                    obj = (new StringBuilder()).append(Integer.parseInt(((Matcher) (obj)).group(1))).append("/1,").append(Integer.parseInt(((Matcher) (obj)).group(2))).append("/1,").append(Integer.parseInt(((Matcher) (obj)).group(3))).append("/1").toString();
                } else
                {
                    try
                    {
                        double d = Double.parseDouble(s1);
                        obj = JVM INSTR new #1124 <Class StringBuilder>;
                        ((StringBuilder) (obj)).StringBuilder();
                        obj = ((StringBuilder) (obj)).append((long)(10000D * d)).append("/10000").toString();
                    }
                    // Misplaced declaration of an exception variable
                    catch(Object obj)
                    {
                        Log.w("ExifInterface", (new StringBuilder()).append("Invalid value for ").append(s).append(" : ").append(s1).toString());
                        return;
                    }
                }
        }
_L13:
        i = 0;
_L2:
        if(i >= EXIF_TAGS.length)
            break MISSING_BLOCK_LABEL_1218;
        if(i != 4 || !(mHasThumbnail ^ true))
            break; /* Loop/switch isn't completed */
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        s1 = ((String) (sExifTagMapsForWriting[i].get(s)));
        if(s1 != null)
        {
label0:
            {
                if(obj != null)
                    break label0;
                mAttributes[i].remove(s);
            }
        }
          goto _L3
          goto _L2
        Pair pair;
        s1 = (ExifTag)s1;
        pair = guessDataFormat(((String) (obj)));
        if(((ExifTag) (s1)).primaryFormat != ((Integer)pair.first).intValue() && ((ExifTag) (s1)).primaryFormat != ((Integer)pair.second).intValue()) goto _L5; else goto _L4
_L4:
        int j = ((ExifTag) (s1)).primaryFormat;
_L8:
        StringBuilder stringbuilder;
        switch(j)
        {
        case 6: // '\006'
        case 8: // '\b'
        case 11: // '\013'
        default:
            Log.w("ExifInterface", (new StringBuilder()).append("Data format isn't one of expected formats: ").append(j).toString());
            break;

        case 1: // '\001'
            mAttributes[i].put(s, ExifAttribute.createByte(((String) (obj))));
            break;

        case 2: // '\002'
        case 7: // '\007'
            mAttributes[i].put(s, ExifAttribute.createString(((String) (obj))));
            break;

        case 3: // '\003'
            String as[] = ((String) (obj)).split(",");
            s1 = new int[as.length];
            for(int k = 0; k < as.length; k++)
                s1[k] = Integer.parseInt(as[k]);

            mAttributes[i].put(s, ExifAttribute.createUShort(s1, mExifByteOrder));
            break;

        case 9: // '\t'
            String as1[] = ((String) (obj)).split(",");
            s1 = new int[as1.length];
            for(int l = 0; l < as1.length; l++)
                s1[l] = Integer.parseInt(as1[l]);

            mAttributes[i].put(s, ExifAttribute.createSLong(s1, mExifByteOrder));
            break;

        case 4: // '\004'
            String as2[] = ((String) (obj)).split(",");
            s1 = new long[as2.length];
            for(int i1 = 0; i1 < as2.length; i1++)
                s1[i1] = Long.parseLong(as2[i1]);

            mAttributes[i].put(s, ExifAttribute.createULong(s1, mExifByteOrder));
            break;

        case 5: // '\005'
            s1 = ((String) (obj)).split(",");
            Rational arational[] = new Rational[s1.length];
            for(int j1 = 0; j1 < s1.length; j1++)
            {
                String as5[] = s1[j1].split("/");
                arational[j1] = new Rational((long)Double.parseDouble(as5[0]), (long)Double.parseDouble(as5[1]), null);
            }

            mAttributes[i].put(s, ExifAttribute.createURational(arational, mExifByteOrder));
            break;

        case 10: // '\n'
            String as3[] = ((String) (obj)).split(",");
            Rational arational1[] = new Rational[as3.length];
            for(int k1 = 0; k1 < as3.length; k1++)
            {
                s1 = as3[k1].split("/");
                arational1[k1] = new Rational((long)Double.parseDouble(s1[0]), (long)Double.parseDouble(s1[1]), null);
            }

            mAttributes[i].put(s, ExifAttribute.createSRational(arational1, mExifByteOrder));
            break;

        case 12: // '\f'
            String as4[] = ((String) (obj)).split(",");
            s1 = new double[as4.length];
            for(int l1 = 0; l1 < as4.length; l1++)
                s1[l1] = Double.parseDouble(as4[l1]);

            mAttributes[i].put(s, ExifAttribute.createDouble(s1, mExifByteOrder));
            break;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if(((ExifTag) (s1)).secondaryFormat == -1 || ((ExifTag) (s1)).secondaryFormat != ((Integer)pair.first).intValue() && ((ExifTag) (s1)).secondaryFormat != ((Integer)pair.second).intValue()) goto _L7; else goto _L6
_L6:
        j = ((ExifTag) (s1)).secondaryFormat;
          goto _L8
_L10:
        j = ((ExifTag) (s1)).primaryFormat;
          goto _L8
_L7:
        if(((ExifTag) (s1)).primaryFormat == 1 || ((ExifTag) (s1)).primaryFormat == 7 || ((ExifTag) (s1)).primaryFormat == 2) goto _L10; else goto _L9
_L9:
        stringbuilder = (new StringBuilder()).append("Given tag (").append(s).append(") value didn't match with one of expected ").append("formats: ").append(IFD_FORMAT_NAMES[((ExifTag) (s1)).primaryFormat]);
        if(((ExifTag) (s1)).secondaryFormat == -1)
            s1 = "";
        else
            s1 = (new StringBuilder()).append(", ").append(IFD_FORMAT_NAMES[((ExifTag) (s1)).secondaryFormat]).toString();
        stringbuilder = stringbuilder.append(s1).append(" (guess: ").append(IFD_FORMAT_NAMES[((Integer)pair.first).intValue()]);
        if(((Integer)pair.second).intValue() == -1)
            s1 = "";
        else
            s1 = (new StringBuilder()).append(", ").append(IFD_FORMAT_NAMES[((Integer)pair.second).intValue()]).toString();
        Log.w("ExifInterface", stringbuilder.append(s1).append(")").toString());
        if(true) goto _L3; else goto _L11
_L11:
        return;
        if(true) goto _L13; else goto _L12
_L12:
    }

    private static final Charset ASCII;
    private static final int BITS_PER_SAMPLE_GREYSCALE_1[] = {
        4
    };
    private static final int BITS_PER_SAMPLE_GREYSCALE_2[] = {
        8
    };
    private static final int BITS_PER_SAMPLE_RGB[] = {
        8, 8, 8
    };
    private static final short BYTE_ALIGN_II = 18761;
    private static final short BYTE_ALIGN_MM = 19789;
    private static final int DATA_DEFLATE_ZIP = 8;
    private static final int DATA_HUFFMAN_COMPRESSED = 2;
    private static final int DATA_JPEG = 6;
    private static final int DATA_JPEG_COMPRESSED = 7;
    private static final int DATA_LOSSY_JPEG = 34892;
    private static final int DATA_PACK_BITS_COMPRESSED = 32773;
    private static final int DATA_UNCOMPRESSED = 1;
    private static final boolean DEBUG = false;
    private static final byte EXIF_ASCII_PREFIX[] = {
        65, 83, 67, 73, 73, 0, 0, 0
    };
    private static final ExifTag EXIF_POINTER_TAGS[] = {
        new ExifTag("SubIFDPointer", 330, 4, null), new ExifTag("ExifIFDPointer", 34665, 4, null), new ExifTag("GPSInfoIFDPointer", 34853, 4, null), new ExifTag("InteroperabilityIFDPointer", 40965, 4, null), new ExifTag("CameraSettingsIFDPointer", 8224, 1, null), new ExifTag("ImageProcessingIFDPointer", 8256, 1, null)
    };
    private static final ExifTag EXIF_TAGS[][] = {
        IFD_TIFF_TAGS, IFD_EXIF_TAGS, IFD_GPS_TAGS, IFD_INTEROPERABILITY_TAGS, IFD_THUMBNAIL_TAGS, IFD_TIFF_TAGS, ORF_MAKER_NOTE_TAGS, ORF_CAMERA_SETTINGS_TAGS, ORF_IMAGE_PROCESSING_TAGS, PEF_TAGS
    };
    private static final byte HEIF_BRAND_HEIC[] = {
        104, 101, 105, 99
    };
    private static final byte HEIF_BRAND_MIF1[] = {
        109, 105, 102, 49
    };
    private static final byte HEIF_TYPE_FTYP[] = {
        102, 116, 121, 112
    };
    private static final byte IDENTIFIER_EXIF_APP1[];
    private static final ExifTag IFD_EXIF_TAGS[] = {
        new ExifTag("ExposureTime", 33434, 5, null), new ExifTag("FNumber", 33437, 5, null), new ExifTag("ExposureProgram", 34850, 3, null), new ExifTag("SpectralSensitivity", 34852, 2, null), new ExifTag("ISOSpeedRatings", 34855, 3, null), new ExifTag("OECF", 34856, 7, null), new ExifTag("ExifVersion", 36864, 2, null), new ExifTag("DateTimeOriginal", 36867, 2, null), new ExifTag("DateTimeDigitized", 36868, 2, null), new ExifTag("ComponentsConfiguration", 37121, 7, null), 
        new ExifTag("CompressedBitsPerPixel", 37122, 5, null), new ExifTag("ShutterSpeedValue", 37377, 10, null), new ExifTag("ApertureValue", 37378, 5, null), new ExifTag("BrightnessValue", 37379, 10, null), new ExifTag("ExposureBiasValue", 37380, 10, null), new ExifTag("MaxApertureValue", 37381, 5, null), new ExifTag("SubjectDistance", 37382, 5, null), new ExifTag("MeteringMode", 37383, 3, null), new ExifTag("LightSource", 37384, 3, null), new ExifTag("Flash", 37385, 3, null), 
        new ExifTag("FocalLength", 37386, 5, null), new ExifTag("SubjectArea", 37396, 3, null), new ExifTag("MakerNote", 37500, 7, null), new ExifTag("UserComment", 37510, 7, null), new ExifTag("SubSecTime", 37520, 2, null), new ExifTag("SubSecTimeOriginal", 37521, 2, null), new ExifTag("SubSecTimeDigitized", 37522, 2, null), new ExifTag("FlashpixVersion", 40960, 7, null), new ExifTag("ColorSpace", 40961, 3, null), new ExifTag("PixelXDimension", 40962, 3, 4, null), 
        new ExifTag("PixelYDimension", 40963, 3, 4, null), new ExifTag("RelatedSoundFile", 40964, 2, null), new ExifTag("InteroperabilityIFDPointer", 40965, 4, null), new ExifTag("FlashEnergy", 41483, 5, null), new ExifTag("SpatialFrequencyResponse", 41484, 7, null), new ExifTag("FocalPlaneXResolution", 41486, 5, null), new ExifTag("FocalPlaneYResolution", 41487, 5, null), new ExifTag("FocalPlaneResolutionUnit", 41488, 3, null), new ExifTag("SubjectLocation", 41492, 3, null), new ExifTag("ExposureIndex", 41493, 5, null), 
        new ExifTag("SensingMethod", 41495, 3, null), new ExifTag("FileSource", 41728, 7, null), new ExifTag("SceneType", 41729, 7, null), new ExifTag("CFAPattern", 41730, 7, null), new ExifTag("CustomRendered", 41985, 3, null), new ExifTag("ExposureMode", 41986, 3, null), new ExifTag("WhiteBalance", 41987, 3, null), new ExifTag("DigitalZoomRatio", 41988, 5, null), new ExifTag("FocalLengthIn35mmFilm", 41989, 3, null), new ExifTag("SceneCaptureType", 41990, 3, null), 
        new ExifTag("GainControl", 41991, 3, null), new ExifTag("Contrast", 41992, 3, null), new ExifTag("Saturation", 41993, 3, null), new ExifTag("Sharpness", 41994, 3, null), new ExifTag("DeviceSettingDescription", 41995, 7, null), new ExifTag("SubjectDistanceRange", 41996, 3, null), new ExifTag("ImageUniqueID", 42016, 2, null), new ExifTag("DNGVersion", 50706, 1, null), new ExifTag("DefaultCropSize", 50720, 3, 4, null)
    };
    private static final int IFD_FORMAT_BYTE = 1;
    private static final int IFD_FORMAT_BYTES_PER_FORMAT[] = {
        0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 
        8, 4, 8, 1
    };
    private static final int IFD_FORMAT_DOUBLE = 12;
    private static final int IFD_FORMAT_IFD = 13;
    private static final String IFD_FORMAT_NAMES[] = {
        "", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", 
        "SRATIONAL", "SINGLE", "DOUBLE"
    };
    private static final int IFD_FORMAT_SBYTE = 6;
    private static final int IFD_FORMAT_SINGLE = 11;
    private static final int IFD_FORMAT_SLONG = 9;
    private static final int IFD_FORMAT_SRATIONAL = 10;
    private static final int IFD_FORMAT_SSHORT = 8;
    private static final int IFD_FORMAT_STRING = 2;
    private static final int IFD_FORMAT_ULONG = 4;
    private static final int IFD_FORMAT_UNDEFINED = 7;
    private static final int IFD_FORMAT_URATIONAL = 5;
    private static final int IFD_FORMAT_USHORT = 3;
    private static final ExifTag IFD_GPS_TAGS[] = {
        new ExifTag("GPSVersionID", 0, 1, null), new ExifTag("GPSLatitudeRef", 1, 2, null), new ExifTag("GPSLatitude", 2, 5, null), new ExifTag("GPSLongitudeRef", 3, 2, null), new ExifTag("GPSLongitude", 4, 5, null), new ExifTag("GPSAltitudeRef", 5, 1, null), new ExifTag("GPSAltitude", 6, 5, null), new ExifTag("GPSTimeStamp", 7, 5, null), new ExifTag("GPSSatellites", 8, 2, null), new ExifTag("GPSStatus", 9, 2, null), 
        new ExifTag("GPSMeasureMode", 10, 2, null), new ExifTag("GPSDOP", 11, 5, null), new ExifTag("GPSSpeedRef", 12, 2, null), new ExifTag("GPSSpeed", 13, 5, null), new ExifTag("GPSTrackRef", 14, 2, null), new ExifTag("GPSTrack", 15, 5, null), new ExifTag("GPSImgDirectionRef", 16, 2, null), new ExifTag("GPSImgDirection", 17, 5, null), new ExifTag("GPSMapDatum", 18, 2, null), new ExifTag("GPSDestLatitudeRef", 19, 2, null), 
        new ExifTag("GPSDestLatitude", 20, 5, null), new ExifTag("GPSDestLongitudeRef", 21, 2, null), new ExifTag("GPSDestLongitude", 22, 5, null), new ExifTag("GPSDestBearingRef", 23, 2, null), new ExifTag("GPSDestBearing", 24, 5, null), new ExifTag("GPSDestDistanceRef", 25, 2, null), new ExifTag("GPSDestDistance", 26, 5, null), new ExifTag("GPSProcessingMethod", 27, 7, null), new ExifTag("GPSAreaInformation", 28, 7, null), new ExifTag("GPSDateStamp", 29, 2, null), 
        new ExifTag("GPSDifferential", 30, 3, null)
    };
    private static final ExifTag IFD_INTEROPERABILITY_TAGS[] = {
        new ExifTag("InteroperabilityIndex", 1, 2, null)
    };
    private static final int IFD_OFFSET = 8;
    private static final ExifTag IFD_THUMBNAIL_TAGS[] = {
        new ExifTag("NewSubfileType", 254, 4, null), new ExifTag("SubfileType", 255, 4, null), new ExifTag("ThumbnailImageWidth", 256, 3, 4, null), new ExifTag("ThumbnailImageLength", 257, 3, 4, null), new ExifTag("BitsPerSample", 258, 3, null), new ExifTag("Compression", 259, 3, null), new ExifTag("PhotometricInterpretation", 262, 3, null), new ExifTag("ImageDescription", 270, 2, null), new ExifTag("Make", 271, 2, null), new ExifTag("Model", 272, 2, null), 
        new ExifTag("StripOffsets", 273, 3, 4, null), new ExifTag("Orientation", 274, 3, null), new ExifTag("SamplesPerPixel", 277, 3, null), new ExifTag("RowsPerStrip", 278, 3, 4, null), new ExifTag("StripByteCounts", 279, 3, 4, null), new ExifTag("XResolution", 282, 5, null), new ExifTag("YResolution", 283, 5, null), new ExifTag("PlanarConfiguration", 284, 3, null), new ExifTag("ResolutionUnit", 296, 3, null), new ExifTag("TransferFunction", 301, 3, null), 
        new ExifTag("Software", 305, 2, null), new ExifTag("DateTime", 306, 2, null), new ExifTag("Artist", 315, 2, null), new ExifTag("WhitePoint", 318, 5, null), new ExifTag("PrimaryChromaticities", 319, 5, null), new ExifTag("SubIFDPointer", 330, 4, null), new ExifTag("JPEGInterchangeFormat", 513, 4, null), new ExifTag("JPEGInterchangeFormatLength", 514, 4, null), new ExifTag("YCbCrCoefficients", 529, 5, null), new ExifTag("YCbCrSubSampling", 530, 3, null), 
        new ExifTag("YCbCrPositioning", 531, 3, null), new ExifTag("ReferenceBlackWhite", 532, 5, null), new ExifTag("Copyright", 33432, 2, null), new ExifTag("ExifIFDPointer", 34665, 4, null), new ExifTag("GPSInfoIFDPointer", 34853, 4, null), new ExifTag("DNGVersion", 50706, 1, null), new ExifTag("DefaultCropSize", 50720, 3, 4, null)
    };
    private static final ExifTag IFD_TIFF_TAGS[] = {
        new ExifTag("NewSubfileType", 254, 4, null), new ExifTag("SubfileType", 255, 4, null), new ExifTag("ImageWidth", 256, 3, 4, null), new ExifTag("ImageLength", 257, 3, 4, null), new ExifTag("BitsPerSample", 258, 3, null), new ExifTag("Compression", 259, 3, null), new ExifTag("PhotometricInterpretation", 262, 3, null), new ExifTag("ImageDescription", 270, 2, null), new ExifTag("Make", 271, 2, null), new ExifTag("Model", 272, 2, null), 
        new ExifTag("StripOffsets", 273, 3, 4, null), new ExifTag("Orientation", 274, 3, null), new ExifTag("SamplesPerPixel", 277, 3, null), new ExifTag("RowsPerStrip", 278, 3, 4, null), new ExifTag("StripByteCounts", 279, 3, 4, null), new ExifTag("XResolution", 282, 5, null), new ExifTag("YResolution", 283, 5, null), new ExifTag("PlanarConfiguration", 284, 3, null), new ExifTag("ResolutionUnit", 296, 3, null), new ExifTag("TransferFunction", 301, 3, null), 
        new ExifTag("Software", 305, 2, null), new ExifTag("DateTime", 306, 2, null), new ExifTag("Artist", 315, 2, null), new ExifTag("WhitePoint", 318, 5, null), new ExifTag("PrimaryChromaticities", 319, 5, null), new ExifTag("SubIFDPointer", 330, 4, null), new ExifTag("JPEGInterchangeFormat", 513, 4, null), new ExifTag("JPEGInterchangeFormatLength", 514, 4, null), new ExifTag("YCbCrCoefficients", 529, 5, null), new ExifTag("YCbCrSubSampling", 530, 3, null), 
        new ExifTag("YCbCrPositioning", 531, 3, null), new ExifTag("ReferenceBlackWhite", 532, 5, null), new ExifTag("Copyright", 33432, 2, null), new ExifTag("ExifIFDPointer", 34665, 4, null), new ExifTag("GPSInfoIFDPointer", 34853, 4, null), new ExifTag("SensorTopBorder", 4, 4, null), new ExifTag("SensorLeftBorder", 5, 4, null), new ExifTag("SensorBottomBorder", 6, 4, null), new ExifTag("SensorRightBorder", 7, 4, null), new ExifTag("ISO", 23, 3, null), 
        new ExifTag("JpgFromRaw", 46, 7, null)
    };
    private static final int IFD_TYPE_EXIF = 1;
    private static final int IFD_TYPE_GPS = 2;
    private static final int IFD_TYPE_INTEROPERABILITY = 3;
    private static final int IFD_TYPE_ORF_CAMERA_SETTINGS = 7;
    private static final int IFD_TYPE_ORF_IMAGE_PROCESSING = 8;
    private static final int IFD_TYPE_ORF_MAKER_NOTE = 6;
    private static final int IFD_TYPE_PEF = 9;
    private static final int IFD_TYPE_PREVIEW = 5;
    private static final int IFD_TYPE_PRIMARY = 0;
    private static final int IFD_TYPE_THUMBNAIL = 4;
    private static final int IMAGE_TYPE_ARW = 1;
    private static final int IMAGE_TYPE_CR2 = 2;
    private static final int IMAGE_TYPE_DNG = 3;
    private static final int IMAGE_TYPE_HEIF = 12;
    private static final int IMAGE_TYPE_JPEG = 4;
    private static final int IMAGE_TYPE_NEF = 5;
    private static final int IMAGE_TYPE_NRW = 6;
    private static final int IMAGE_TYPE_ORF = 7;
    private static final int IMAGE_TYPE_PEF = 8;
    private static final int IMAGE_TYPE_RAF = 9;
    private static final int IMAGE_TYPE_RW2 = 10;
    private static final int IMAGE_TYPE_SRW = 11;
    private static final int IMAGE_TYPE_UNKNOWN = 0;
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_LENGTH_TAG = new ExifTag("JPEGInterchangeFormatLength", 514, 4, null);
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_TAG = new ExifTag("JPEGInterchangeFormat", 513, 4, null);
    private static final byte JPEG_SIGNATURE[] = {
        -1, -40, -1
    };
    private static final byte MARKER = -1;
    private static final byte MARKER_APP1 = -31;
    private static final byte MARKER_COM = -2;
    private static final byte MARKER_EOI = -39;
    private static final byte MARKER_SOF0 = -64;
    private static final byte MARKER_SOF1 = -63;
    private static final byte MARKER_SOF10 = -54;
    private static final byte MARKER_SOF11 = -53;
    private static final byte MARKER_SOF13 = -51;
    private static final byte MARKER_SOF14 = -50;
    private static final byte MARKER_SOF15 = -49;
    private static final byte MARKER_SOF2 = -62;
    private static final byte MARKER_SOF3 = -61;
    private static final byte MARKER_SOF5 = -59;
    private static final byte MARKER_SOF6 = -58;
    private static final byte MARKER_SOF7 = -57;
    private static final byte MARKER_SOF9 = -55;
    private static final byte MARKER_SOI = -40;
    private static final byte MARKER_SOS = -38;
    private static final int MAX_THUMBNAIL_SIZE = 512;
    private static final ExifTag ORF_CAMERA_SETTINGS_TAGS[] = {
        new ExifTag("PreviewImageStart", 257, 4, null), new ExifTag("PreviewImageLength", 258, 4, null)
    };
    private static final ExifTag ORF_IMAGE_PROCESSING_TAGS[] = {
        new ExifTag("AspectFrame", 4371, 3, null)
    };
    private static final byte ORF_MAKER_NOTE_HEADER_1[] = {
        79, 76, 89, 77, 80, 0
    };
    private static final int ORF_MAKER_NOTE_HEADER_1_SIZE = 8;
    private static final byte ORF_MAKER_NOTE_HEADER_2[] = {
        79, 76, 89, 77, 80, 85, 83, 0, 73, 73
    };
    private static final int ORF_MAKER_NOTE_HEADER_2_SIZE = 12;
    private static final ExifTag ORF_MAKER_NOTE_TAGS[] = {
        new ExifTag("ThumbnailImage", 256, 7, null), new ExifTag("CameraSettingsIFDPointer", 8224, 4, null), new ExifTag("ImageProcessingIFDPointer", 8256, 4, null)
    };
    private static final short ORF_SIGNATURE_1 = 20306;
    private static final short ORF_SIGNATURE_2 = 21330;
    public static final int ORIENTATION_FLIP_HORIZONTAL = 2;
    public static final int ORIENTATION_FLIP_VERTICAL = 4;
    public static final int ORIENTATION_NORMAL = 1;
    public static final int ORIENTATION_ROTATE_180 = 3;
    public static final int ORIENTATION_ROTATE_270 = 8;
    public static final int ORIENTATION_ROTATE_90 = 6;
    public static final int ORIENTATION_TRANSPOSE = 5;
    public static final int ORIENTATION_TRANSVERSE = 7;
    public static final int ORIENTATION_UNDEFINED = 0;
    private static final int ORIGINAL_RESOLUTION_IMAGE = 0;
    private static final int PEF_MAKER_NOTE_SKIP_SIZE = 6;
    private static final String PEF_SIGNATURE = "PENTAX";
    private static final ExifTag PEF_TAGS[] = {
        new ExifTag("ColorSpace", 55, 3, null)
    };
    private static final int PHOTOMETRIC_INTERPRETATION_BLACK_IS_ZERO = 1;
    private static final int PHOTOMETRIC_INTERPRETATION_RGB = 2;
    private static final int PHOTOMETRIC_INTERPRETATION_WHITE_IS_ZERO = 0;
    private static final int PHOTOMETRIC_INTERPRETATION_YCBCR = 6;
    private static final int RAF_INFO_SIZE = 160;
    private static final int RAF_JPEG_LENGTH_VALUE_SIZE = 4;
    private static final int RAF_OFFSET_TO_JPEG_IMAGE_OFFSET = 84;
    private static final String RAF_SIGNATURE = "FUJIFILMCCD-RAW";
    private static final int REDUCED_RESOLUTION_IMAGE = 1;
    private static final short RW2_SIGNATURE = 85;
    private static final int SIGNATURE_CHECK_SIZE = 5000;
    private static final byte START_CODE = 42;
    private static final String TAG = "ExifInterface";
    public static final String TAG_APERTURE = "FNumber";
    public static final String TAG_APERTURE_VALUE = "ApertureValue";
    public static final String TAG_ARTIST = "Artist";
    public static final String TAG_BITS_PER_SAMPLE = "BitsPerSample";
    public static final String TAG_BRIGHTNESS_VALUE = "BrightnessValue";
    public static final String TAG_CFA_PATTERN = "CFAPattern";
    public static final String TAG_COLOR_SPACE = "ColorSpace";
    public static final String TAG_COMPONENTS_CONFIGURATION = "ComponentsConfiguration";
    public static final String TAG_COMPRESSED_BITS_PER_PIXEL = "CompressedBitsPerPixel";
    public static final String TAG_COMPRESSION = "Compression";
    public static final String TAG_CONTRAST = "Contrast";
    public static final String TAG_COPYRIGHT = "Copyright";
    public static final String TAG_CUSTOM_RENDERED = "CustomRendered";
    public static final String TAG_DATETIME = "DateTime";
    public static final String TAG_DATETIME_DIGITIZED = "DateTimeDigitized";
    public static final String TAG_DATETIME_ORIGINAL = "DateTimeOriginal";
    public static final String TAG_DEFAULT_CROP_SIZE = "DefaultCropSize";
    public static final String TAG_DEVICE_SETTING_DESCRIPTION = "DeviceSettingDescription";
    public static final String TAG_DIGITAL_ZOOM_RATIO = "DigitalZoomRatio";
    public static final String TAG_DNG_VERSION = "DNGVersion";
    private static final String TAG_EXIF_IFD_POINTER = "ExifIFDPointer";
    public static final String TAG_EXIF_VERSION = "ExifVersion";
    public static final String TAG_EXPOSURE_BIAS_VALUE = "ExposureBiasValue";
    public static final String TAG_EXPOSURE_INDEX = "ExposureIndex";
    public static final String TAG_EXPOSURE_MODE = "ExposureMode";
    public static final String TAG_EXPOSURE_PROGRAM = "ExposureProgram";
    public static final String TAG_EXPOSURE_TIME = "ExposureTime";
    public static final String TAG_FILE_SOURCE = "FileSource";
    public static final String TAG_FLASH = "Flash";
    public static final String TAG_FLASHPIX_VERSION = "FlashpixVersion";
    public static final String TAG_FLASH_ENERGY = "FlashEnergy";
    public static final String TAG_FOCAL_LENGTH = "FocalLength";
    public static final String TAG_FOCAL_LENGTH_IN_35MM_FILM = "FocalLengthIn35mmFilm";
    public static final String TAG_FOCAL_PLANE_RESOLUTION_UNIT = "FocalPlaneResolutionUnit";
    public static final String TAG_FOCAL_PLANE_X_RESOLUTION = "FocalPlaneXResolution";
    public static final String TAG_FOCAL_PLANE_Y_RESOLUTION = "FocalPlaneYResolution";
    public static final String TAG_F_NUMBER = "FNumber";
    public static final String TAG_GAIN_CONTROL = "GainControl";
    public static final String TAG_GPS_ALTITUDE = "GPSAltitude";
    public static final String TAG_GPS_ALTITUDE_REF = "GPSAltitudeRef";
    public static final String TAG_GPS_AREA_INFORMATION = "GPSAreaInformation";
    public static final String TAG_GPS_DATESTAMP = "GPSDateStamp";
    public static final String TAG_GPS_DEST_BEARING = "GPSDestBearing";
    public static final String TAG_GPS_DEST_BEARING_REF = "GPSDestBearingRef";
    public static final String TAG_GPS_DEST_DISTANCE = "GPSDestDistance";
    public static final String TAG_GPS_DEST_DISTANCE_REF = "GPSDestDistanceRef";
    public static final String TAG_GPS_DEST_LATITUDE = "GPSDestLatitude";
    public static final String TAG_GPS_DEST_LATITUDE_REF = "GPSDestLatitudeRef";
    public static final String TAG_GPS_DEST_LONGITUDE = "GPSDestLongitude";
    public static final String TAG_GPS_DEST_LONGITUDE_REF = "GPSDestLongitudeRef";
    public static final String TAG_GPS_DIFFERENTIAL = "GPSDifferential";
    public static final String TAG_GPS_DOP = "GPSDOP";
    public static final String TAG_GPS_IMG_DIRECTION = "GPSImgDirection";
    public static final String TAG_GPS_IMG_DIRECTION_REF = "GPSImgDirectionRef";
    private static final String TAG_GPS_INFO_IFD_POINTER = "GPSInfoIFDPointer";
    public static final String TAG_GPS_LATITUDE = "GPSLatitude";
    public static final String TAG_GPS_LATITUDE_REF = "GPSLatitudeRef";
    public static final String TAG_GPS_LONGITUDE = "GPSLongitude";
    public static final String TAG_GPS_LONGITUDE_REF = "GPSLongitudeRef";
    public static final String TAG_GPS_MAP_DATUM = "GPSMapDatum";
    public static final String TAG_GPS_MEASURE_MODE = "GPSMeasureMode";
    public static final String TAG_GPS_PROCESSING_METHOD = "GPSProcessingMethod";
    public static final String TAG_GPS_SATELLITES = "GPSSatellites";
    public static final String TAG_GPS_SPEED = "GPSSpeed";
    public static final String TAG_GPS_SPEED_REF = "GPSSpeedRef";
    public static final String TAG_GPS_STATUS = "GPSStatus";
    public static final String TAG_GPS_TIMESTAMP = "GPSTimeStamp";
    public static final String TAG_GPS_TRACK = "GPSTrack";
    public static final String TAG_GPS_TRACK_REF = "GPSTrackRef";
    public static final String TAG_GPS_VERSION_ID = "GPSVersionID";
    private static final String TAG_HAS_THUMBNAIL = "HasThumbnail";
    public static final String TAG_IMAGE_DESCRIPTION = "ImageDescription";
    public static final String TAG_IMAGE_LENGTH = "ImageLength";
    public static final String TAG_IMAGE_UNIQUE_ID = "ImageUniqueID";
    public static final String TAG_IMAGE_WIDTH = "ImageWidth";
    private static final String TAG_INTEROPERABILITY_IFD_POINTER = "InteroperabilityIFDPointer";
    public static final String TAG_INTEROPERABILITY_INDEX = "InteroperabilityIndex";
    public static final String TAG_ISO = "ISOSpeedRatings";
    public static final String TAG_ISO_SPEED_RATINGS = "ISOSpeedRatings";
    public static final String TAG_JPEG_INTERCHANGE_FORMAT = "JPEGInterchangeFormat";
    public static final String TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = "JPEGInterchangeFormatLength";
    public static final String TAG_LIGHT_SOURCE = "LightSource";
    public static final String TAG_MAKE = "Make";
    public static final String TAG_MAKER_NOTE = "MakerNote";
    public static final String TAG_MAX_APERTURE_VALUE = "MaxApertureValue";
    public static final String TAG_METERING_MODE = "MeteringMode";
    public static final String TAG_MODEL = "Model";
    public static final String TAG_NEW_SUBFILE_TYPE = "NewSubfileType";
    public static final String TAG_OECF = "OECF";
    public static final String TAG_ORF_ASPECT_FRAME = "AspectFrame";
    private static final String TAG_ORF_CAMERA_SETTINGS_IFD_POINTER = "CameraSettingsIFDPointer";
    private static final String TAG_ORF_IMAGE_PROCESSING_IFD_POINTER = "ImageProcessingIFDPointer";
    public static final String TAG_ORF_PREVIEW_IMAGE_LENGTH = "PreviewImageLength";
    public static final String TAG_ORF_PREVIEW_IMAGE_START = "PreviewImageStart";
    public static final String TAG_ORF_THUMBNAIL_IMAGE = "ThumbnailImage";
    public static final String TAG_ORIENTATION = "Orientation";
    public static final String TAG_PHOTOMETRIC_INTERPRETATION = "PhotometricInterpretation";
    public static final String TAG_PIXEL_X_DIMENSION = "PixelXDimension";
    public static final String TAG_PIXEL_Y_DIMENSION = "PixelYDimension";
    public static final String TAG_PLANAR_CONFIGURATION = "PlanarConfiguration";
    public static final String TAG_PRIMARY_CHROMATICITIES = "PrimaryChromaticities";
    private static final ExifTag TAG_RAF_IMAGE_SIZE = new ExifTag("StripOffsets", 273, 3, null);
    public static final String TAG_REFERENCE_BLACK_WHITE = "ReferenceBlackWhite";
    public static final String TAG_RELATED_SOUND_FILE = "RelatedSoundFile";
    public static final String TAG_RESOLUTION_UNIT = "ResolutionUnit";
    public static final String TAG_ROWS_PER_STRIP = "RowsPerStrip";
    public static final String TAG_RW2_ISO = "ISO";
    public static final String TAG_RW2_JPG_FROM_RAW = "JpgFromRaw";
    public static final String TAG_RW2_SENSOR_BOTTOM_BORDER = "SensorBottomBorder";
    public static final String TAG_RW2_SENSOR_LEFT_BORDER = "SensorLeftBorder";
    public static final String TAG_RW2_SENSOR_RIGHT_BORDER = "SensorRightBorder";
    public static final String TAG_RW2_SENSOR_TOP_BORDER = "SensorTopBorder";
    public static final String TAG_SAMPLES_PER_PIXEL = "SamplesPerPixel";
    public static final String TAG_SATURATION = "Saturation";
    public static final String TAG_SCENE_CAPTURE_TYPE = "SceneCaptureType";
    public static final String TAG_SCENE_TYPE = "SceneType";
    public static final String TAG_SENSING_METHOD = "SensingMethod";
    public static final String TAG_SHARPNESS = "Sharpness";
    public static final String TAG_SHUTTER_SPEED_VALUE = "ShutterSpeedValue";
    public static final String TAG_SOFTWARE = "Software";
    public static final String TAG_SPATIAL_FREQUENCY_RESPONSE = "SpatialFrequencyResponse";
    public static final String TAG_SPECTRAL_SENSITIVITY = "SpectralSensitivity";
    public static final String TAG_STRIP_BYTE_COUNTS = "StripByteCounts";
    public static final String TAG_STRIP_OFFSETS = "StripOffsets";
    public static final String TAG_SUBFILE_TYPE = "SubfileType";
    public static final String TAG_SUBJECT_AREA = "SubjectArea";
    public static final String TAG_SUBJECT_DISTANCE = "SubjectDistance";
    public static final String TAG_SUBJECT_DISTANCE_RANGE = "SubjectDistanceRange";
    public static final String TAG_SUBJECT_LOCATION = "SubjectLocation";
    public static final String TAG_SUBSEC_TIME = "SubSecTime";
    public static final String TAG_SUBSEC_TIME_DIG = "SubSecTimeDigitized";
    public static final String TAG_SUBSEC_TIME_DIGITIZED = "SubSecTimeDigitized";
    public static final String TAG_SUBSEC_TIME_ORIG = "SubSecTimeOriginal";
    public static final String TAG_SUBSEC_TIME_ORIGINAL = "SubSecTimeOriginal";
    private static final String TAG_SUB_IFD_POINTER = "SubIFDPointer";
    private static final String TAG_THUMBNAIL_DATA = "ThumbnailData";
    public static final String TAG_THUMBNAIL_IMAGE_LENGTH = "ThumbnailImageLength";
    public static final String TAG_THUMBNAIL_IMAGE_WIDTH = "ThumbnailImageWidth";
    private static final String TAG_THUMBNAIL_LENGTH = "ThumbnailLength";
    private static final String TAG_THUMBNAIL_OFFSET = "ThumbnailOffset";
    public static final String TAG_TRANSFER_FUNCTION = "TransferFunction";
    public static final String TAG_USER_COMMENT = "UserComment";
    public static final String TAG_WHITE_BALANCE = "WhiteBalance";
    public static final String TAG_WHITE_POINT = "WhitePoint";
    public static final String TAG_X_RESOLUTION = "XResolution";
    public static final String TAG_Y_CB_CR_COEFFICIENTS = "YCbCrCoefficients";
    public static final String TAG_Y_CB_CR_POSITIONING = "YCbCrPositioning";
    public static final String TAG_Y_CB_CR_SUB_SAMPLING = "YCbCrSubSampling";
    public static final String TAG_Y_RESOLUTION = "YResolution";
    public static final int WHITEBALANCE_AUTO = 0;
    public static final int WHITEBALANCE_MANUAL = 1;
    private static final HashMap sExifPointerTagMap;
    private static final HashMap sExifTagMapsForReading[];
    private static final HashMap sExifTagMapsForWriting[];
    private static SimpleDateFormat sFormatter;
    private static final Pattern sGpsTimestampPattern = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
    private static final Pattern sNonZeroTimePattern = Pattern.compile(".*[1-9].*");
    private static final HashSet sTagSetForCompatibility = new HashSet(Arrays.asList(new String[] {
        "FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"
    }));
    private final android.content.res.AssetManager.AssetInputStream mAssetInputStream;
    private final HashMap mAttributes[];
    private ByteOrder mExifByteOrder;
    private int mExifOffset;
    private final String mFilename;
    private boolean mHasThumbnail;
    private final boolean mIsInputStream;
    private boolean mIsSupportedFile;
    private int mMimeType;
    private int mOrfMakerNoteOffset;
    private int mOrfThumbnailLength;
    private int mOrfThumbnailOffset;
    private int mRw2JpgFromRawOffset;
    private final FileDescriptor mSeekableFileDescriptor;
    private byte mThumbnailBytes[];
    private int mThumbnailCompression;
    private int mThumbnailLength;
    private int mThumbnailOffset;

    static 
    {
        sExifTagMapsForReading = new HashMap[EXIF_TAGS.length];
        sExifTagMapsForWriting = new HashMap[EXIF_TAGS.length];
        sExifPointerTagMap = new HashMap();
        ASCII = Charset.forName("US-ASCII");
        IDENTIFIER_EXIF_APP1 = "Exif\000\0".getBytes(ASCII);
        sFormatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        sFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        for(int i = 0; i < EXIF_TAGS.length; i++)
        {
            sExifTagMapsForReading[i] = new HashMap();
            sExifTagMapsForWriting[i] = new HashMap();
            ExifTag aexiftag[] = EXIF_TAGS[i];
            int j = 0;
            for(int k = aexiftag.length; j < k; j++)
            {
                ExifTag exiftag = aexiftag[j];
                sExifTagMapsForReading[i].put(Integer.valueOf(exiftag.number), exiftag);
                sExifTagMapsForWriting[i].put(exiftag.name, exiftag);
            }

        }

        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[0].number), Integer.valueOf(5));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[1].number), Integer.valueOf(1));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[2].number), Integer.valueOf(2));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[3].number), Integer.valueOf(3));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[4].number), Integer.valueOf(7));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[5].number), Integer.valueOf(8));
    }

    // Unreferenced inner class android/media/ExifInterface$1

/* anonymous class */
    class _cls1 extends MediaDataSource
    {

        public void close()
            throws IOException
        {
        }

        public long getSize()
            throws IOException
        {
            return -1L;
        }

        public int readAt(long l, byte abyte0[], int i, int j)
            throws IOException
        {
            if(j == 0)
                return 0;
            if(l < 0L)
                return -1;
            if(mPosition != l)
            {
                in.seek(l);
                mPosition = l;
            }
            i = in.read(abyte0, i, j);
            if(i < 0)
            {
                mPosition = -1L;
                return -1;
            } else
            {
                mPosition = mPosition + (long)i;
                return i;
            }
        }

        long mPosition;
        final ExifInterface this$0;
        final ByteOrderedDataInputStream val$in;

            
            {
                this$0 = ExifInterface.this;
                in = byteordereddatainputstream;
                super();
            }
    }

}
