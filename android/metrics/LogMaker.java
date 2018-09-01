// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.metrics;

import android.content.ComponentName;
import android.util.Log;
import android.util.SparseArray;

public class LogMaker
{

    public LogMaker(int i)
    {
        entries = new SparseArray();
        setCategory(i);
    }

    public LogMaker(Object aobj[])
    {
        entries = new SparseArray();
        if(aobj != null)
            deserialize(aobj);
        else
            setCategory(0);
    }

    public LogMaker addTaggedData(int i, Object obj)
    {
        if(obj == null)
            return clearTaggedData(i);
        if(!isValidValue(obj))
            throw new IllegalArgumentException("Value must be loggable type - int, long, float, String");
        if(obj.toString().getBytes().length > 4000)
            Log.i("LogBuilder", (new StringBuilder()).append("Log value too long, omitted: ").append(obj.toString()).toString());
        else
            entries.put(i, obj);
        return this;
    }

    public LogMaker clearCategory()
    {
        entries.remove(757);
        return this;
    }

    public LogMaker clearPackageName()
    {
        entries.remove(806);
        return this;
    }

    public LogMaker clearProcessId()
    {
        entries.remove(865);
        return this;
    }

    public LogMaker clearSubtype()
    {
        entries.remove(759);
        return this;
    }

    public LogMaker clearTaggedData(int i)
    {
        entries.delete(i);
        return this;
    }

    public LogMaker clearTimestamp()
    {
        entries.remove(805);
        return this;
    }

    public LogMaker clearType()
    {
        entries.remove(758);
        return this;
    }

    public LogMaker clearUid()
    {
        entries.remove(943);
        return this;
    }

    public void deserialize(Object aobj[])
    {
        for(int i = 0; aobj != null && i < aobj.length;)
        {
            int j = i + 1;
            Object obj = aobj[i];
            Object obj1;
            if(j < aobj.length)
            {
                obj1 = aobj[j];
                i = j + 1;
            } else
            {
                obj1 = null;
                i = j;
            }
            if(obj instanceof Integer)
            {
                entries.put(((Integer)obj).intValue(), obj1);
            } else
            {
                StringBuilder stringbuilder = (new StringBuilder()).append("Invalid key ");
                String s;
                if(obj == null)
                    s = "null";
                else
                    s = obj.toString();
                Log.i("LogBuilder", stringbuilder.append(s).toString());
            }
        }

    }

    public int getCategory()
    {
        Object obj = entries.get(757);
        if(obj instanceof Integer)
            return ((Integer)obj).intValue();
        else
            return 0;
    }

    public long getCounterBucket()
    {
        Object obj = entries.get(801);
        if(obj instanceof Number)
            return ((Number)obj).longValue();
        else
            return 0L;
    }

    public String getCounterName()
    {
        Object obj = entries.get(799);
        if(obj instanceof String)
            return (String)obj;
        else
            return null;
    }

    public int getCounterValue()
    {
        Object obj = entries.get(802);
        if(obj instanceof Integer)
            return ((Integer)obj).intValue();
        else
            return 0;
    }

    public String getPackageName()
    {
        Object obj = entries.get(806);
        if(obj instanceof String)
            return (String)obj;
        else
            return null;
    }

    public int getProcessId()
    {
        Object obj = entries.get(865);
        if(obj instanceof Integer)
            return ((Integer)obj).intValue();
        else
            return -1;
    }

    public int getSubtype()
    {
        Object obj = entries.get(759);
        if(obj instanceof Integer)
            return ((Integer)obj).intValue();
        else
            return 0;
    }

    public Object getTaggedData(int i)
    {
        return entries.get(i);
    }

    public long getTimestamp()
    {
        Object obj = entries.get(805);
        if(obj instanceof Long)
            return ((Long)obj).longValue();
        else
            return 0L;
    }

    public int getType()
    {
        Object obj = entries.get(758);
        if(obj instanceof Integer)
            return ((Integer)obj).intValue();
        else
            return 0;
    }

    public int getUid()
    {
        Object obj = entries.get(943);
        if(obj instanceof Integer)
            return ((Integer)obj).intValue();
        else
            return -1;
    }

    public boolean isLongCounterBucket()
    {
        return entries.get(801) instanceof Long;
    }

    public boolean isSubsetOf(LogMaker logmaker)
    {
        if(logmaker == null)
            return false;
        for(int i = 0; i < entries.size(); i++)
        {
            int j = entries.keyAt(i);
            Object obj = entries.valueAt(i);
            for(Object obj1 = logmaker.entries.get(j); obj == null && obj1 != null || obj.equals(obj1) ^ true;)
                return false;

        }

        return true;
    }

    public boolean isValidValue(Object obj)
    {
        boolean flag;
        if(!(obj instanceof Integer) && !(obj instanceof String) && !(obj instanceof Long))
            flag = obj instanceof Float;
        else
            flag = true;
        return flag;
    }

    public Object[] serialize()
    {
        Object aobj[] = new Object[entries.size() * 2];
        for(int i = 0; i < entries.size(); i++)
        {
            aobj[i * 2] = Integer.valueOf(entries.keyAt(i));
            aobj[i * 2 + 1] = entries.valueAt(i);
        }

        int j = ((Object) (aobj)).toString().getBytes().length;
        if(j > 4000)
        {
            Log.i("LogBuilder", (new StringBuilder()).append("Log line too long, did not emit: ").append(j).append(" bytes.").toString());
            throw new RuntimeException();
        } else
        {
            return aobj;
        }
    }

    public LogMaker setCategory(int i)
    {
        entries.put(757, Integer.valueOf(i));
        return this;
    }

    public LogMaker setComponentName(ComponentName componentname)
    {
        entries.put(806, componentname.getPackageName());
        entries.put(871, componentname.getClassName());
        return this;
    }

    public LogMaker setCounterBucket(int i)
    {
        entries.put(801, Integer.valueOf(i));
        return this;
    }

    public LogMaker setCounterBucket(long l)
    {
        entries.put(801, Long.valueOf(l));
        return this;
    }

    public LogMaker setCounterName(String s)
    {
        entries.put(799, s);
        return this;
    }

    public LogMaker setCounterValue(int i)
    {
        entries.put(802, Integer.valueOf(i));
        return this;
    }

    public LogMaker setLatency(long l)
    {
        entries.put(793, Long.valueOf(l));
        return this;
    }

    public LogMaker setPackageName(String s)
    {
        entries.put(806, s);
        return this;
    }

    public LogMaker setProcessId(int i)
    {
        entries.put(865, Integer.valueOf(i));
        return this;
    }

    public LogMaker setSubtype(int i)
    {
        entries.put(759, Integer.valueOf(i));
        return this;
    }

    public LogMaker setTimestamp(long l)
    {
        entries.put(805, Long.valueOf(l));
        return this;
    }

    public LogMaker setType(int i)
    {
        entries.put(758, Integer.valueOf(i));
        return this;
    }

    public LogMaker setUid(int i)
    {
        entries.put(943, Integer.valueOf(i));
        return this;
    }

    public static final int MAX_SERIALIZED_SIZE = 4000;
    private static final String TAG = "LogBuilder";
    private SparseArray entries;
}
