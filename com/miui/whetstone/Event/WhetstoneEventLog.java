// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.Event;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.nio.*;

public class WhetstoneEventLog
{

    public WhetstoneEventLog(byte abyte0[])
    {
        mBuffer = ByteBuffer.wrap(abyte0);
        mBuffer.order(ByteOrder.nativeOrder());
        mLength = 0;
        mData = getData();
        if(!(mData instanceof Object[])) goto _L2; else goto _L1
_L1:
        mLength = ((Object[])mData).length;
_L4:
        return;
_L2:
        if(mData instanceof Object)
            mLength = 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private Object decodeObject()
    {
        int i = mBuffer.get();
        byte byte0;
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown entry type: ").append(i).toString());

        case 0: // '\0'
            return Integer.valueOf(mBuffer.getInt());

        case 1: // '\001'
            return Long.valueOf(mBuffer.getLong());

        case 2: // '\002'
            String s;
            try
            {
                int j = mBuffer.getInt();
                i = mBuffer.position();
                mBuffer.position(i + j);
                s = new String(mBuffer.array(), i, j, "UTF-8");
            }
            catch(UnsupportedEncodingException unsupportedencodingexception)
            {
                Log.wtf("WhetstoneEventLog", "UTF-8 is not supported", unsupportedencodingexception);
                return null;
            }
            return s;

        case 3: // '\003'
            byte0 = mBuffer.get();
            i = byte0;
            break;
        }
        if(byte0 < 0)
            i = byte0 + 256;
        Object aobj[] = new Object[i];
        mLength = i;
        for(int k = 0; k < i; k++)
            aobj[k] = decodeObject();

        return ((Object) (aobj));
    }

    private Object getObject(int i)
    {
        if(i < 0 || i >= mLength)
        {
            Log.e("WhetstoneEventLog", (new StringBuilder()).append("invalid pos ").append(i).toString());
            return null;
        }
        Object obj;
        if(mData instanceof Object[])
            obj = ((Object[])mData)[i];
        else
            obj = mData;
        return obj;
    }

    private String getParameter(CharSequence charsequence, int i)
    {
        int j;
        int k;
        int l;
        j = -1;
        if(charsequence == null || i <= 0)
            return null;
        k = 0;
        l = 0;
_L2:
        int i1;
        int j1;
        if(l >= charsequence.length())
            break MISSING_BLOCK_LABEL_188;
        if(charsequence.charAt(l) != '[')
            break; /* Loop/switch isn't completed */
        i1 = l;
        j1 = k;
_L3:
        l++;
        k = j1;
        j = i1;
        if(true) goto _L2; else goto _L1
_L1:
        if(charsequence.charAt(l) == ']')
        {
            if(k + 1 == i && l > j + 1)
                return charsequence.subSequence(j + 1, l - 1).toString();
            break MISSING_BLOCK_LABEL_188;
        }
        j1 = k;
        i1 = j;
        if(j > 0)
        {
            j1 = k;
            i1 = j;
            if(charsequence.charAt(l) == ',')
            {
                j1 = k + 1;
                if(j1 == i && l > j + 1)
                    return charsequence.subSequence(j + 1, l - 1).toString();
                i1 = l;
            }
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
        return null;
    }

    public Object getData()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        mBuffer.limit(mBuffer.getShort(0) + 20);
        mBuffer.position(24);
        obj = decodeObject();
        this;
        JVM INSTR monitorexit ;
        return obj;
        Object obj1;
        obj1;
        StringBuilder stringbuilder = JVM INSTR new #82  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.wtf("WhetstoneEventLog", stringbuilder.append("Truncated entry payload: tag=").append(getTag()).toString(), ((Throwable) (obj1)));
        this;
        JVM INSTR monitorexit ;
        return null;
        obj1;
        StringBuilder stringbuilder1 = JVM INSTR new #82  <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Log.wtf("WhetstoneEventLog", stringbuilder1.append("Illegal entry payload: tag=").append(getTag()).toString(), ((Throwable) (obj1)));
        this;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        throw exception;
    }

    public Integer getInteger(int i)
    {
        Object obj = getObject(i);
        if(obj != null && (obj instanceof Integer))
            return (Integer)obj;
        else
            return null;
    }

    public Long getLong(int i)
    {
        Object obj = getObject(i);
        if(obj != null && (obj instanceof Long))
            return (Long)obj;
        else
            return null;
    }

    public int getProcessId()
    {
        return mBuffer.getInt(4);
    }

    public String getString(int i)
    {
        Object obj = getObject(i);
        if(obj != null && (obj instanceof String))
            return (String)obj;
        else
            return null;
    }

    public int getTag()
    {
        return mBuffer.getInt(20);
    }

    public int getThreadId()
    {
        return mBuffer.getInt(8);
    }

    public long getTimeNanos()
    {
        return (long)mBuffer.getInt(12) * 0x3b9aca00L + (long)mBuffer.getInt(16);
    }

    public void print()
    {
        Log.i("WhetstoneEventLog", (new StringBuilder()).append(getProcessId()).append(" ").append(getTimeNanos()).append(" ").append(getTag()).append(" ").append(getData()).toString());
    }

    private static final int DATA_START = 24;
    private static final byte INT_TYPE = 0;
    private static final int LENGTH_OFFSET = 0;
    private static final byte LIST_TYPE = 3;
    private static final byte LONG_TYPE = 1;
    private static final int NANOSECONDS_OFFSET = 16;
    private static final int PAYLOAD_START = 20;
    private static final int PROCESS_OFFSET = 4;
    private static final int SECONDS_OFFSET = 12;
    private static final byte STRING_TYPE = 2;
    private static final String TAG = "WhetstoneEventLog";
    private static final int TAG_OFFSET = 20;
    private static final int THREAD_OFFSET = 8;
    private final ByteBuffer mBuffer;
    private Object mData;
    private int mLength;
}
