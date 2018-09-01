// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano;

import java.io.IOException;
import java.util.*;

// Referenced classes of package com.android.framework.protobuf.nano:
//            CodedOutputByteBufferNano, MessageNano, Extension, UnknownFieldData

class FieldData
    implements Cloneable
{

    FieldData()
    {
        unknownFieldData = new ArrayList();
    }

    FieldData(Extension extension, Object obj)
    {
        cachedExtension = extension;
        value = obj;
    }

    private byte[] toByteArray()
        throws IOException
    {
        byte abyte0[] = new byte[computeSerializedSize()];
        writeTo(CodedOutputByteBufferNano.newInstance(abyte0));
        return abyte0;
    }

    void addUnknownField(UnknownFieldData unknownfielddata)
    {
        unknownFieldData.add(unknownfielddata);
    }

    public final FieldData clone()
    {
        Object obj = new FieldData();
        obj.cachedExtension = cachedExtension;
        if(unknownFieldData != null) goto _L2; else goto _L1
_L1:
        obj.unknownFieldData = null;
_L5:
        if(value != null) goto _L4; else goto _L3
_L3:
        return ((FieldData) (obj));
_L2:
        try
        {
            ((FieldData) (obj)).unknownFieldData.addAll(unknownFieldData);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new AssertionError(obj);
        }
          goto _L5
_L4:
label0:
        {
            if(!(value instanceof MessageNano))
                break label0;
            obj.value = ((MessageNano)value).clone();
        }
          goto _L3
label1:
        {
            if(!(value instanceof byte[]))
                break label1;
            obj.value = ((byte[])value).clone();
        }
          goto _L3
        Object obj1;
        Object obj2;
        if(!(value instanceof byte[][]))
            break MISSING_BLOCK_LABEL_177;
        obj1 = (byte[][])value;
        obj2 = new byte[obj1.length][];
        obj.value = obj2;
        int i = 0;
_L7:
        if(i >= obj1.length) goto _L3; else goto _L6
_L6:
        obj2[i] = (byte[])obj1[i].clone();
        i++;
          goto _L7
label2:
        {
            if(!(value instanceof boolean[]))
                break label2;
            obj.value = ((boolean[])value).clone();
        }
          goto _L3
label3:
        {
            if(!(value instanceof int[]))
                break label3;
            obj.value = ((int[])value).clone();
        }
          goto _L3
label4:
        {
            if(!(value instanceof long[]))
                break label4;
            obj.value = ((long[])value).clone();
        }
          goto _L3
label5:
        {
            if(!(value instanceof float[]))
                break label5;
            obj.value = ((float[])value).clone();
        }
          goto _L3
        if(!(value instanceof double[]))
            continue; /* Loop/switch isn't completed */
        obj.value = ((double[])value).clone();
          goto _L3
        if(!(value instanceof MessageNano[])) goto _L3; else goto _L8
_L8:
        obj1 = (MessageNano[])value;
        obj2 = new MessageNano[obj1.length];
        obj.value = obj2;
        i = 0;
_L10:
        if(i >= obj1.length) goto _L3; else goto _L9
_L9:
        obj2[i] = obj1[i].clone();
        i++;
          goto _L10
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    int computeSerializedSize()
    {
        int i = 0;
        if(value == null) goto _L2; else goto _L1
_L1:
        int j = cachedExtension.computeSerializedSize(value);
_L4:
        return j;
_L2:
        Iterator iterator = unknownFieldData.iterator();
        do
        {
            j = i;
            if(!iterator.hasNext())
                continue;
            i += ((UnknownFieldData)iterator.next()).computeSerializedSize();
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(!(obj instanceof FieldData))
            return false;
        obj = (FieldData)obj;
        if(value != null && ((FieldData) (obj)).value != null)
        {
            if(cachedExtension != ((FieldData) (obj)).cachedExtension)
                return false;
            if(!cachedExtension.clazz.isArray())
                return value.equals(((FieldData) (obj)).value);
            if(value instanceof byte[])
                return Arrays.equals((byte[])value, (byte[])((FieldData) (obj)).value);
            if(value instanceof int[])
                return Arrays.equals((int[])value, (int[])((FieldData) (obj)).value);
            if(value instanceof long[])
                return Arrays.equals((long[])value, (long[])((FieldData) (obj)).value);
            if(value instanceof float[])
                return Arrays.equals((float[])value, (float[])((FieldData) (obj)).value);
            if(value instanceof double[])
                return Arrays.equals((double[])value, (double[])((FieldData) (obj)).value);
            if(value instanceof boolean[])
                return Arrays.equals((boolean[])value, (boolean[])((FieldData) (obj)).value);
            else
                return Arrays.deepEquals((Object[])value, (Object[])((FieldData) (obj)).value);
        }
        if(unknownFieldData != null && ((FieldData) (obj)).unknownFieldData != null)
            return unknownFieldData.equals(((FieldData) (obj)).unknownFieldData);
        boolean flag;
        try
        {
            flag = Arrays.equals(toByteArray(), ((FieldData) (obj)).toByteArray());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new IllegalStateException(((Throwable) (obj)));
        }
        return flag;
    }

    UnknownFieldData getUnknownField(int i)
    {
        if(unknownFieldData == null)
            return null;
        if(i < unknownFieldData.size())
            return (UnknownFieldData)unknownFieldData.get(i);
        else
            return null;
    }

    int getUnknownFieldSize()
    {
        if(unknownFieldData == null)
            return 0;
        else
            return unknownFieldData.size();
    }

    Object getValue(Extension extension)
    {
        if(value != null)
        {
            if(cachedExtension != extension)
                throw new IllegalStateException("Tried to getExtension with a differernt Extension.");
        } else
        {
            cachedExtension = extension;
            value = extension.getValueFrom(unknownFieldData);
            unknownFieldData = null;
        }
        return value;
    }

    public int hashCode()
    {
        int i;
        try
        {
            i = Arrays.hashCode(toByteArray());
        }
        catch(IOException ioexception)
        {
            throw new IllegalStateException(ioexception);
        }
        return i + 527;
    }

    void setValue(Extension extension, Object obj)
    {
        cachedExtension = extension;
        value = obj;
        unknownFieldData = null;
    }

    void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if(value != null)
        {
            cachedExtension.writeTo(value, codedoutputbytebuffernano);
        } else
        {
            Iterator iterator = unknownFieldData.iterator();
            while(iterator.hasNext()) 
                ((UnknownFieldData)iterator.next()).writeTo(codedoutputbytebuffernano);
        }
    }

    private Extension cachedExtension;
    private List unknownFieldData;
    private Object value;
}
