// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano;

import java.io.IOException;

// Referenced classes of package com.android.framework.protobuf.nano:
//            MessageNano, InternalNano, FieldArray, FieldData, 
//            Extension, WireFormatNano, CodedInputByteBufferNano, UnknownFieldData, 
//            CodedOutputByteBufferNano

public abstract class ExtendableMessageNano extends MessageNano
{

    public ExtendableMessageNano()
    {
    }

    public ExtendableMessageNano clone()
        throws CloneNotSupportedException
    {
        ExtendableMessageNano extendablemessagenano = (ExtendableMessageNano)super.clone();
        InternalNano.cloneUnknownFieldData(this, extendablemessagenano);
        return extendablemessagenano;
    }

    public volatile MessageNano clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    protected int computeSerializedSize()
    {
        int i = 0;
        int j = 0;
        if(unknownFieldData != null)
        {
            int k = 0;
            do
            {
                i = j;
                if(k >= unknownFieldData.size())
                    break;
                j += unknownFieldData.dataAt(k).computeSerializedSize();
                k++;
            } while(true);
        }
        return i;
    }

    public final Object getExtension(Extension extension)
    {
        Object obj = null;
        if(unknownFieldData == null)
            return null;
        FieldData fielddata = unknownFieldData.get(WireFormatNano.getTagFieldNumber(extension.tag));
        if(fielddata == null)
            extension = obj;
        else
            extension = ((Extension) (fielddata.getValue(extension)));
        return extension;
    }

    public final boolean hasExtension(Extension extension)
    {
        boolean flag = false;
        if(unknownFieldData == null)
            return false;
        if(unknownFieldData.get(WireFormatNano.getTagFieldNumber(extension.tag)) != null)
            flag = true;
        return flag;
    }

    public final ExtendableMessageNano setExtension(Extension extension, Object obj)
    {
        int i = WireFormatNano.getTagFieldNumber(extension.tag);
        if(obj == null)
        {
            if(unknownFieldData != null)
            {
                unknownFieldData.remove(i);
                if(unknownFieldData.isEmpty())
                    unknownFieldData = null;
            }
        } else
        {
            FieldData fielddata = null;
            if(unknownFieldData == null)
                unknownFieldData = new FieldArray();
            else
                fielddata = unknownFieldData.get(i);
            if(fielddata == null)
                unknownFieldData.put(i, new FieldData(extension, obj));
            else
                fielddata.setValue(extension, obj);
        }
        return this;
    }

    protected final boolean storeUnknownField(CodedInputByteBufferNano codedinputbytebuffernano, int i)
        throws IOException
    {
        int j = codedinputbytebuffernano.getPosition();
        if(!codedinputbytebuffernano.skipField(i))
            return false;
        int k = WireFormatNano.getTagFieldNumber(i);
        UnknownFieldData unknownfielddata = new UnknownFieldData(i, codedinputbytebuffernano.getData(j, codedinputbytebuffernano.getPosition() - j));
        codedinputbytebuffernano = null;
        Object obj;
        if(unknownFieldData == null)
            unknownFieldData = new FieldArray();
        else
            codedinputbytebuffernano = unknownFieldData.get(k);
        obj = codedinputbytebuffernano;
        if(codedinputbytebuffernano == null)
        {
            obj = new FieldData();
            unknownFieldData.put(k, ((FieldData) (obj)));
        }
        ((FieldData) (obj)).addUnknownField(unknownfielddata);
        return true;
    }

    public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if(unknownFieldData == null)
            return;
        for(int i = 0; i < unknownFieldData.size(); i++)
            unknownFieldData.dataAt(i).writeTo(codedoutputbytebuffernano);

    }

    protected FieldArray unknownFieldData;
}
