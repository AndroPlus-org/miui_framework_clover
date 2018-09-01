// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

// Referenced classes of package com.android.framework.protobuf.nano:
//            ExtendableMessageNano, FieldArray, CodedOutputByteBufferNano, CodedInputByteBufferNano, 
//            MessageNano, WireFormatNano

public final class InternalNano
{

    private InternalNano()
    {
    }

    public static byte[] bytesDefaultValue(String s)
    {
        return s.getBytes(ISO_8859_1);
    }

    public static void cloneUnknownFieldData(ExtendableMessageNano extendablemessagenano, ExtendableMessageNano extendablemessagenano1)
    {
        if(extendablemessagenano.unknownFieldData != null)
            extendablemessagenano1.unknownFieldData = extendablemessagenano.unknownFieldData.clone();
    }

    public static int computeMapFieldSize(Map map, int i, int j, int k)
    {
        int l = 0;
        int i1 = CodedOutputByteBufferNano.computeTagSize(i);
        map = map.entrySet().iterator();
        for(i = l; map.hasNext(); i += i1 + l + CodedOutputByteBufferNano.computeRawVarint32Size(l))
        {
            Object obj = (java.util.Map.Entry)map.next();
            Object obj1 = ((java.util.Map.Entry) (obj)).getKey();
            obj = ((java.util.Map.Entry) (obj)).getValue();
            if(obj1 == null || obj == null)
                throw new IllegalStateException("keys and values in maps cannot be null");
            l = CodedOutputByteBufferNano.computeFieldSize(1, j, obj1) + CodedOutputByteBufferNano.computeFieldSize(2, k, obj);
        }

        return i;
    }

    public static byte[] copyFromUtf8(String s)
    {
        return s.getBytes(UTF_8);
    }

    public static boolean equals(Map map, Map map1)
    {
        boolean flag = true;
        boolean flag1 = true;
        if(map == map1)
            return true;
        if(map == null)
        {
            if(map1.size() != 0)
                flag1 = false;
            return flag1;
        }
        if(map1 == null)
        {
            boolean flag2;
            if(map.size() == 0)
                flag2 = flag;
            else
                flag2 = false;
            return flag2;
        }
        if(map.size() != map1.size())
            return false;
        for(map = map.entrySet().iterator(); map.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)map.next();
            if(!map1.containsKey(entry.getKey()))
                return false;
            if(!equalsMapValue(entry.getValue(), map1.get(entry.getKey())))
                return false;
        }

        return true;
    }

    public static boolean equals(double ad[], double ad1[])
    {
        boolean flag = true;
        if(ad == null || ad.length == 0)
        {
            boolean flag1 = flag;
            if(ad1 != null)
                if(ad1.length == 0)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        } else
        {
            return Arrays.equals(ad, ad1);
        }
    }

    public static boolean equals(float af[], float af1[])
    {
        boolean flag = true;
        if(af == null || af.length == 0)
        {
            boolean flag1 = flag;
            if(af1 != null)
                if(af1.length == 0)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        } else
        {
            return Arrays.equals(af, af1);
        }
    }

    public static boolean equals(int ai[], int ai1[])
    {
        boolean flag = true;
        if(ai == null || ai.length == 0)
        {
            boolean flag1 = flag;
            if(ai1 != null)
                if(ai1.length == 0)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        } else
        {
            return Arrays.equals(ai, ai1);
        }
    }

    public static boolean equals(long al[], long al1[])
    {
        boolean flag = true;
        if(al == null || al.length == 0)
        {
            boolean flag1 = flag;
            if(al1 != null)
                if(al1.length == 0)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        } else
        {
            return Arrays.equals(al, al1);
        }
    }

    public static boolean equals(Object aobj[], Object aobj1[])
    {
        int i = 0;
        int j;
        int k;
        int l;
        if(aobj == null)
            j = 0;
        else
            j = aobj.length;
        k = 0;
        if(aobj1 == null)
            l = 0;
        else
            l = aobj1.length;
        do
        {
            int i1 = k;
            if(i < j)
            {
                i1 = k;
                if(aobj[i] == null)
                {
                    i++;
                    continue;
                }
            }
            for(; i1 < l && aobj1[i1] == null; i1++);
            boolean flag;
            if(i >= j)
                k = 1;
            else
                k = 0;
            if(i1 >= l)
                flag = true;
            else
                flag = false;
            if(k != 0 && flag)
                return true;
            if(k != flag)
                return false;
            if(!aobj[i].equals(aobj1[i1]))
                return false;
            i++;
            k = i1 + 1;
        } while(true);
    }

    public static boolean equals(boolean aflag[], boolean aflag1[])
    {
        boolean flag = true;
        if(aflag == null || aflag.length == 0)
        {
            boolean flag1 = flag;
            if(aflag1 != null)
                if(aflag1.length == 0)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        } else
        {
            return Arrays.equals(aflag, aflag1);
        }
    }

    public static boolean equals(byte abyte0[][], byte abyte1[][])
    {
        int i = 0;
        int j;
        int k;
        int l;
        if(abyte0 == null)
            j = 0;
        else
            j = abyte0.length;
        k = 0;
        if(abyte1 == null)
            l = 0;
        else
            l = abyte1.length;
        do
        {
            int i1 = k;
            if(i < j)
            {
                i1 = k;
                if(abyte0[i] == null)
                {
                    i++;
                    continue;
                }
            }
            for(; i1 < l && abyte1[i1] == null; i1++);
            boolean flag;
            if(i >= j)
                k = 1;
            else
                k = 0;
            if(i1 >= l)
                flag = true;
            else
                flag = false;
            if(k != 0 && flag)
                return true;
            if(k != flag)
                return false;
            if(!Arrays.equals(abyte0[i], abyte1[i1]))
                return false;
            i++;
            k = i1 + 1;
        } while(true);
    }

    private static boolean equalsMapValue(Object obj, Object obj1)
    {
        if(obj == null || obj1 == null)
            throw new IllegalStateException("keys and values in maps cannot be null");
        if((obj instanceof byte[]) && (obj1 instanceof byte[]))
            return Arrays.equals((byte[])obj, (byte[])obj1);
        else
            return obj.equals(obj1);
    }

    public static int hashCode(Map map)
    {
        if(map == null)
            return 0;
        int i = 0;
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            map = (java.util.Map.Entry)iterator.next();
            i += hashCodeForMap(map.getKey()) ^ hashCodeForMap(map.getValue());
        }

        return i;
    }

    public static int hashCode(double ad[])
    {
        boolean flag = false;
        int i = ((flag) ? 1 : 0);
        if(ad != null)
            if(ad.length == 0)
                i = ((flag) ? 1 : 0);
            else
                i = Arrays.hashCode(ad);
        return i;
    }

    public static int hashCode(float af[])
    {
        boolean flag = false;
        int i = ((flag) ? 1 : 0);
        if(af != null)
            if(af.length == 0)
                i = ((flag) ? 1 : 0);
            else
                i = Arrays.hashCode(af);
        return i;
    }

    public static int hashCode(int ai[])
    {
        boolean flag = false;
        int i = ((flag) ? 1 : 0);
        if(ai != null)
            if(ai.length == 0)
                i = ((flag) ? 1 : 0);
            else
                i = Arrays.hashCode(ai);
        return i;
    }

    public static int hashCode(long al[])
    {
        boolean flag = false;
        int i = ((flag) ? 1 : 0);
        if(al != null)
            if(al.length == 0)
                i = ((flag) ? 1 : 0);
            else
                i = Arrays.hashCode(al);
        return i;
    }

    public static int hashCode(Object aobj[])
    {
        int i = 0;
        int j = 0;
        int k;
        if(aobj == null)
            k = 0;
        else
            k = aobj.length;
        while(j < k) 
        {
            Object obj = aobj[j];
            int l = i;
            if(obj != null)
                l = i * 31 + obj.hashCode();
            j++;
            i = l;
        }
        return i;
    }

    public static int hashCode(boolean aflag[])
    {
        boolean flag = false;
        int i = ((flag) ? 1 : 0);
        if(aflag != null)
            if(aflag.length == 0)
                i = ((flag) ? 1 : 0);
            else
                i = Arrays.hashCode(aflag);
        return i;
    }

    public static int hashCode(byte abyte0[][])
    {
        int i = 0;
        int j = 0;
        int k;
        if(abyte0 == null)
            k = 0;
        else
            k = abyte0.length;
        while(j < k) 
        {
            byte abyte1[] = abyte0[j];
            int l = i;
            if(abyte1 != null)
                l = i * 31 + Arrays.hashCode(abyte1);
            j++;
            i = l;
        }
        return i;
    }

    private static int hashCodeForMap(Object obj)
    {
        if(obj instanceof byte[])
            return Arrays.hashCode((byte[])obj);
        else
            return obj.hashCode();
    }

    public static final Map mergeMapEntry(CodedInputByteBufferNano codedinputbytebuffernano, Map map, MapFactories.MapFactory mapfactory, int i, int j, Object obj, int k, int l)
        throws IOException
    {
        int i1;
        mapfactory = mapfactory.forMap(map);
        i1 = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
        map = null;
_L3:
        int j1 = codedinputbytebuffernano.readTag();
        if(j1 != 0) goto _L2; else goto _L1
_L1:
        codedinputbytebuffernano.checkLastTagWas(0);
        codedinputbytebuffernano.popLimit(i1);
        codedinputbytebuffernano = map;
        if(map == null)
            codedinputbytebuffernano = ((CodedInputByteBufferNano) (primitiveDefaultValue(i)));
        map = ((Map) (obj));
        if(obj == null)
            map = ((Map) (primitiveDefaultValue(j)));
        mapfactory.put(codedinputbytebuffernano, map);
        return mapfactory;
_L2:
        if(j1 == k)
        {
            map = ((Map) (codedinputbytebuffernano.readPrimitiveField(i)));
            break; /* Loop/switch isn't completed */
        }
        if(j1 != l)
            continue; /* Loop/switch isn't completed */
        if(j == 11)
            codedinputbytebuffernano.readMessage((MessageNano)obj);
        else
            obj = codedinputbytebuffernano.readPrimitiveField(j);
        break; /* Loop/switch isn't completed */
        if(codedinputbytebuffernano.skipField(j1)) goto _L3; else goto _L1
    }

    private static Object primitiveDefaultValue(int i)
    {
        switch(i)
        {
        case 10: // '\n'
        case 11: // '\013'
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Type: ").append(i).append(" is not a primitive type.").toString());

        case 8: // '\b'
            return Boolean.FALSE;

        case 12: // '\f'
            return WireFormatNano.EMPTY_BYTES;

        case 9: // '\t'
            return "";

        case 2: // '\002'
            return Float.valueOf(0.0F);

        case 1: // '\001'
            return Double.valueOf(0.0D);

        case 5: // '\005'
        case 7: // '\007'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 17: // '\021'
            return Integer.valueOf(0);

        case 3: // '\003'
        case 4: // '\004'
        case 6: // '\006'
        case 16: // '\020'
        case 18: // '\022'
            return Long.valueOf(0L);
        }
    }

    public static void serializeMapField(CodedOutputByteBufferNano codedoutputbytebuffernano, Map map, int i, int j, int k)
        throws IOException
    {
        Object obj;
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); codedoutputbytebuffernano.writeField(2, k, obj))
        {
            obj = (java.util.Map.Entry)iterator.next();
            map = ((Map) (((java.util.Map.Entry) (obj)).getKey()));
            obj = ((java.util.Map.Entry) (obj)).getValue();
            if(map == null || obj == null)
                throw new IllegalStateException("keys and values in maps cannot be null");
            int l = CodedOutputByteBufferNano.computeFieldSize(1, j, map);
            int i1 = CodedOutputByteBufferNano.computeFieldSize(2, k, obj);
            codedoutputbytebuffernano.writeTag(i, 2);
            codedoutputbytebuffernano.writeRawVarint32(l + i1);
            codedoutputbytebuffernano.writeField(1, j, map);
        }

    }

    public static String stringDefaultValue(String s)
    {
        return new String(s.getBytes(ISO_8859_1), UTF_8);
    }

    protected static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Object LAZY_INIT_LOCK = new Object();
    public static final int TYPE_BOOL = 8;
    public static final int TYPE_BYTES = 12;
    public static final int TYPE_DOUBLE = 1;
    public static final int TYPE_ENUM = 14;
    public static final int TYPE_FIXED32 = 7;
    public static final int TYPE_FIXED64 = 6;
    public static final int TYPE_FLOAT = 2;
    public static final int TYPE_GROUP = 10;
    public static final int TYPE_INT32 = 5;
    public static final int TYPE_INT64 = 3;
    public static final int TYPE_MESSAGE = 11;
    public static final int TYPE_SFIXED32 = 15;
    public static final int TYPE_SFIXED64 = 16;
    public static final int TYPE_SINT32 = 17;
    public static final int TYPE_SINT64 = 18;
    public static final int TYPE_STRING = 9;
    public static final int TYPE_UINT32 = 13;
    public static final int TYPE_UINT64 = 4;
    protected static final Charset UTF_8 = Charset.forName("UTF-8");

}
