// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import java.nio.BufferOverflowException;
import java.nio.ByteOrder;
import java.util.*;
import libcore.io.Memory;

public class TlvBufferUtils
{
    public static class TlvConstructor
    {

        private void addHeader(int i, int j)
        {
            if(mTypeSize == 1)
                mArray[mPosition] = (byte)i;
            else
            if(mTypeSize == 2)
                Memory.pokeShort(mArray, mPosition, (short)i, ByteOrder.BIG_ENDIAN);
            mPosition = mPosition + mTypeSize;
            if(mLengthSize == 1)
                mArray[mPosition] = (byte)j;
            else
            if(mLengthSize == 2)
                Memory.pokeShort(mArray, mPosition, (short)j, ByteOrder.BIG_ENDIAN);
            mPosition = mPosition + mLengthSize;
        }

        private void checkLength(int i)
        {
            if(mPosition + mTypeSize + mLengthSize + i > mArrayLength)
                throw new BufferOverflowException();
            else
                return;
        }

        private int getActualLength()
        {
            return mPosition;
        }

        public TlvConstructor allocate(int i)
        {
            mArray = new byte[i];
            mArrayLength = i;
            return this;
        }

        public TlvConstructor allocateAndPut(List list)
        {
            if(list != null)
            {
                int i = 0;
                Iterator iterator = list.iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    byte abyte0[] = (byte[])iterator.next();
                    int j = i + (mTypeSize + mLengthSize);
                    i = j;
                    if(abyte0 != null)
                        i = j + abyte0.length;
                } while(true);
                allocate(i);
                for(list = list.iterator(); list.hasNext(); putByteArray(0, (byte[])list.next()));
            }
            return this;
        }

        public byte[] getArray()
        {
            return Arrays.copyOf(mArray, getActualLength());
        }

        public TlvConstructor putByte(int i, byte byte0)
        {
            checkLength(1);
            addHeader(i, 1);
            byte abyte0[] = mArray;
            i = mPosition;
            mPosition = i + 1;
            abyte0[i] = byte0;
            return this;
        }

        public TlvConstructor putByteArray(int i, byte abyte0[])
        {
            int j;
            if(abyte0 == null)
                j = 0;
            else
                j = abyte0.length;
            return putByteArray(i, abyte0, 0, j);
        }

        public TlvConstructor putByteArray(int i, byte abyte0[], int j, int k)
        {
            checkLength(k);
            addHeader(i, k);
            if(k != 0)
                System.arraycopy(abyte0, j, mArray, mPosition, k);
            mPosition = mPosition + k;
            return this;
        }

        public TlvConstructor putInt(int i, int j)
        {
            checkLength(4);
            addHeader(i, 4);
            Memory.pokeInt(mArray, mPosition, j, ByteOrder.BIG_ENDIAN);
            mPosition = mPosition + 4;
            return this;
        }

        public TlvConstructor putShort(int i, short word0)
        {
            checkLength(2);
            addHeader(i, 2);
            Memory.pokeShort(mArray, mPosition, word0, ByteOrder.BIG_ENDIAN);
            mPosition = mPosition + 2;
            return this;
        }

        public TlvConstructor putString(int i, String s)
        {
            byte abyte0[] = null;
            int j = 0;
            if(s != null)
            {
                abyte0 = s.getBytes();
                j = abyte0.length;
            }
            return putByteArray(i, abyte0, 0, j);
        }

        public TlvConstructor putZeroLengthElement(int i)
        {
            checkLength(0);
            addHeader(i, 0);
            return this;
        }

        public TlvConstructor wrap(byte abyte0[])
        {
            mArray = abyte0;
            int i;
            if(abyte0 == null)
                i = 0;
            else
                i = abyte0.length;
            mArrayLength = i;
            return this;
        }

        private byte mArray[];
        private int mArrayLength;
        private int mLengthSize;
        private int mPosition;
        private int mTypeSize;

        public TlvConstructor(int i, int j)
        {
            while(i < 0 || i > 2 || j <= 0 || j > 2) 
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid sizes - typeSize=").append(i).append(", lengthSize=").append(j).toString());
            mTypeSize = i;
            mLengthSize = j;
        }
    }

    public static class TlvElement
    {

        public byte getByte()
        {
            if(length != 1)
                throw new IllegalArgumentException((new StringBuilder()).append("Accesing a byte from a TLV element of length ").append(length).toString());
            else
                return refArray[offset];
        }

        public int getInt()
        {
            if(length != 4)
                throw new IllegalArgumentException((new StringBuilder()).append("Accesing an int from a TLV element of length ").append(length).toString());
            else
                return Memory.peekInt(refArray, offset, ByteOrder.BIG_ENDIAN);
        }

        public short getShort()
        {
            if(length != 2)
                throw new IllegalArgumentException((new StringBuilder()).append("Accesing a short from a TLV element of length ").append(length).toString());
            else
                return Memory.peekShort(refArray, offset, ByteOrder.BIG_ENDIAN);
        }

        public String getString()
        {
            return new String(refArray, offset, length);
        }

        public int length;
        public int offset;
        public byte refArray[];
        public int type;

        private TlvElement(int i, int j, byte abyte0[], int k)
        {
            type = i;
            length = j;
            refArray = abyte0;
            offset = k;
            if(k + j > abyte0.length)
                throw new BufferOverflowException();
            else
                return;
        }

        TlvElement(int i, int j, byte abyte0[], int k, TlvElement tlvelement)
        {
            this(i, j, abyte0, k);
        }
    }

    public static class TlvIterable
        implements Iterable
    {

        static byte[] _2D_get0(TlvIterable tlviterable)
        {
            return tlviterable.mArray;
        }

        static int _2D_get1(TlvIterable tlviterable)
        {
            return tlviterable.mArrayLength;
        }

        static int _2D_get2(TlvIterable tlviterable)
        {
            return tlviterable.mLengthSize;
        }

        static int _2D_get3(TlvIterable tlviterable)
        {
            return tlviterable.mTypeSize;
        }

        public Iterator iterator()
        {
            return new Iterator() {

                public boolean hasNext()
                {
                    boolean flag;
                    if(mOffset < TlvIterable._2D_get1(TlvIterable.this))
                        flag = true;
                    else
                        flag = false;
                    return flag;
                }

                public TlvElement next()
                {
                    if(!hasNext())
                        throw new NoSuchElementException();
                    short word0 = 0;
                    short word1;
                    TlvElement tlvelement;
                    if(TlvIterable._2D_get3(TlvIterable.this) == 1)
                        word0 = TlvIterable._2D_get0(TlvIterable.this)[mOffset];
                    else
                    if(TlvIterable._2D_get3(TlvIterable.this) == 2)
                        word0 = Memory.peekShort(TlvIterable._2D_get0(TlvIterable.this), mOffset, ByteOrder.BIG_ENDIAN);
                    mOffset = mOffset + TlvIterable._2D_get3(TlvIterable.this);
                    word1 = 0;
                    if(TlvIterable._2D_get2(TlvIterable.this) == 1)
                        word1 = TlvIterable._2D_get0(TlvIterable.this)[mOffset];
                    else
                    if(TlvIterable._2D_get2(TlvIterable.this) == 2)
                        word1 = Memory.peekShort(TlvIterable._2D_get0(TlvIterable.this), mOffset, ByteOrder.BIG_ENDIAN);
                    mOffset = mOffset + TlvIterable._2D_get2(TlvIterable.this);
                    tlvelement = new TlvElement(word0, word1, TlvIterable._2D_get0(TlvIterable.this), mOffset, null);
                    mOffset = mOffset + word1;
                    return tlvelement;
                }

                public volatile Object next()
                {
                    return next();
                }

                public void remove()
                {
                    throw new UnsupportedOperationException();
                }

                private int mOffset;
                final TlvIterable this$1;

            
            {
                this$1 = TlvIterable.this;
                super();
                mOffset = 0;
            }
            }
;
        }

        public List toList()
        {
            ArrayList arraylist = new ArrayList();
            TlvElement tlvelement;
            for(Iterator iterator1 = iterator(); iterator1.hasNext(); arraylist.add(Arrays.copyOfRange(tlvelement.refArray, tlvelement.offset, tlvelement.offset + tlvelement.length)))
                tlvelement = (TlvElement)iterator1.next();

            return arraylist;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("[");
            boolean flag = true;
            Iterator iterator1 = iterator();
            do
                if(iterator1.hasNext())
                {
                    TlvElement tlvelement = (TlvElement)iterator1.next();
                    if(!flag)
                        stringbuilder.append(",");
                    boolean flag1 = false;
                    stringbuilder.append(" (");
                    if(mTypeSize != 0)
                        stringbuilder.append("T=").append(tlvelement.type).append(",");
                    stringbuilder.append("L=").append(tlvelement.length).append(") ");
                    if(tlvelement.length == 0)
                        stringbuilder.append("<null>");
                    else
                    if(tlvelement.length == 1)
                        stringbuilder.append(tlvelement.getByte());
                    else
                    if(tlvelement.length == 2)
                        stringbuilder.append(tlvelement.getShort());
                    else
                    if(tlvelement.length == 4)
                        stringbuilder.append(tlvelement.getInt());
                    else
                        stringbuilder.append("<bytes>");
                    flag = flag1;
                    if(tlvelement.length != 0)
                    {
                        stringbuilder.append(" (S='").append(tlvelement.getString()).append("')");
                        flag = flag1;
                    }
                } else
                {
                    stringbuilder.append("]");
                    return stringbuilder.toString();
                }
            while(true);
        }

        private byte mArray[];
        private int mArrayLength;
        private int mLengthSize;
        private int mTypeSize;

        public TlvIterable(int i, int j, byte abyte0[])
        {
            boolean flag = false;
            super();
            while(i < 0 || i > 2 || j <= 0 || j > 2) 
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid sizes - typeSize=").append(i).append(", lengthSize=").append(j).toString());
            mTypeSize = i;
            mLengthSize = j;
            mArray = abyte0;
            if(abyte0 == null)
                i = ((flag) ? 1 : 0);
            else
                i = abyte0.length;
            mArrayLength = i;
        }
    }


    private TlvBufferUtils()
    {
    }

    public static boolean isValid(byte abyte0[], int i, int j)
    {
        boolean flag = true;
        if(i < 0 || i > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid arguments - typeSize must be 0, 1, or 2: typeSize=").append(i).toString());
        if(j <= 0 || j > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid arguments - lengthSize must be 1 or 2: lengthSize=").append(j).toString());
        if(abyte0 == null)
            return true;
        int k;
        for(k = 0; k + i + j <= abyte0.length;)
        {
            k += i;
            if(j == 1)
                k += abyte0[k] + j;
            else
                k += Memory.peekShort(abyte0, k, ByteOrder.BIG_ENDIAN) + j;
        }

        if(k != abyte0.length)
            flag = false;
        return flag;
    }
}
