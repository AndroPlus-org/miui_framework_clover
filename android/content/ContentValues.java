// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.*;

public final class ContentValues
    implements Parcelable
{

    public ContentValues()
    {
        mValues = new HashMap(8);
    }

    public ContentValues(int i)
    {
        mValues = new HashMap(i, 1.0F);
    }

    public ContentValues(ContentValues contentvalues)
    {
        mValues = new HashMap(contentvalues.mValues);
    }

    private ContentValues(HashMap hashmap)
    {
        mValues = hashmap;
    }

    ContentValues(HashMap hashmap, ContentValues contentvalues)
    {
        this(hashmap);
    }

    public void clear()
    {
        mValues.clear();
    }

    public boolean containsKey(String s)
    {
        return mValues.containsKey(s);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof ContentValues))
            return false;
        else
            return mValues.equals(((ContentValues)obj).mValues);
    }

    public Object get(String s)
    {
        return mValues.get(s);
    }

    public Boolean getAsBoolean(String s)
    {
        boolean flag = true;
        boolean flag1 = true;
        Object obj = mValues.get(s);
        Boolean boolean1;
        try
        {
            boolean1 = (Boolean)obj;
        }
        catch(ClassCastException classcastexception)
        {
            if(obj instanceof CharSequence)
            {
                if(!Boolean.valueOf(obj.toString()).booleanValue())
                    flag1 = "1".equals(obj);
                return Boolean.valueOf(flag1);
            }
            if(obj instanceof Number)
            {
                boolean flag2;
                if(((Number)obj).intValue() != 0)
                    flag2 = flag;
                else
                    flag2 = false;
                return Boolean.valueOf(flag2);
            } else
            {
                Log.e("ContentValues", (new StringBuilder()).append("Cannot cast value for ").append(s).append(" to a Boolean: ").append(obj).toString(), classcastexception);
                return null;
            }
        }
        return boolean1;
    }

    public Byte getAsByte(String s)
    {
        Object obj = mValues.get(s);
        if(obj == null) goto _L2; else goto _L1
_L1:
        byte byte0;
        try
        {
            byte0 = ((Number)obj).byteValue();
        }
        catch(Object obj1)
        {
            if(obj instanceof CharSequence)
            {
                try
                {
                    obj1 = Byte.valueOf(obj.toString());
                }
                // Misplaced declaration of an exception variable
                catch(Object obj1)
                {
                    Log.e("ContentValues", (new StringBuilder()).append("Cannot parse Byte value for ").append(obj).append(" at key ").append(s).toString());
                    return null;
                }
                return ((Byte) (obj1));
            } else
            {
                Log.e("ContentValues", (new StringBuilder()).append("Cannot cast value for ").append(s).append(" to a Byte: ").append(obj).toString(), ((Throwable) (obj1)));
                return null;
            }
        }
_L3:
        s = Byte.valueOf(byte0);
_L4:
        return s;
_L2:
        s = null;
        if(true) goto _L4; else goto _L3
    }

    public byte[] getAsByteArray(String s)
    {
        s = ((String) (mValues.get(s)));
        if(s instanceof byte[])
            return (byte[])s;
        else
            return null;
    }

    public Double getAsDouble(String s)
    {
        Object obj = mValues.get(s);
        if(obj == null) goto _L2; else goto _L1
_L1:
        double d;
        try
        {
            d = ((Number)obj).doubleValue();
        }
        catch(Object obj1)
        {
            if(obj instanceof CharSequence)
            {
                try
                {
                    obj1 = Double.valueOf(obj.toString());
                }
                // Misplaced declaration of an exception variable
                catch(Object obj1)
                {
                    Log.e("ContentValues", (new StringBuilder()).append("Cannot parse Double value for ").append(obj).append(" at key ").append(s).toString());
                    return null;
                }
                return ((Double) (obj1));
            } else
            {
                Log.e("ContentValues", (new StringBuilder()).append("Cannot cast value for ").append(s).append(" to a Double: ").append(obj).toString(), ((Throwable) (obj1)));
                return null;
            }
        }
_L3:
        s = Double.valueOf(d);
_L4:
        return s;
_L2:
        s = null;
        if(true) goto _L4; else goto _L3
    }

    public Float getAsFloat(String s)
    {
        Object obj = mValues.get(s);
        if(obj == null) goto _L2; else goto _L1
_L1:
        float f;
        try
        {
            f = ((Number)obj).floatValue();
        }
        catch(Object obj1)
        {
            if(obj instanceof CharSequence)
            {
                try
                {
                    obj1 = Float.valueOf(obj.toString());
                }
                // Misplaced declaration of an exception variable
                catch(Object obj1)
                {
                    Log.e("ContentValues", (new StringBuilder()).append("Cannot parse Float value for ").append(obj).append(" at key ").append(s).toString());
                    return null;
                }
                return ((Float) (obj1));
            } else
            {
                Log.e("ContentValues", (new StringBuilder()).append("Cannot cast value for ").append(s).append(" to a Float: ").append(obj).toString(), ((Throwable) (obj1)));
                return null;
            }
        }
_L3:
        s = Float.valueOf(f);
_L4:
        return s;
_L2:
        s = null;
        if(true) goto _L4; else goto _L3
    }

    public Integer getAsInteger(String s)
    {
        Object obj = mValues.get(s);
        if(obj == null) goto _L2; else goto _L1
_L1:
        int i;
        try
        {
            i = ((Number)obj).intValue();
        }
        catch(Object obj1)
        {
            if(obj instanceof CharSequence)
            {
                try
                {
                    obj1 = Integer.valueOf(obj.toString());
                }
                // Misplaced declaration of an exception variable
                catch(Object obj1)
                {
                    Log.e("ContentValues", (new StringBuilder()).append("Cannot parse Integer value for ").append(obj).append(" at key ").append(s).toString());
                    return null;
                }
                return ((Integer) (obj1));
            } else
            {
                Log.e("ContentValues", (new StringBuilder()).append("Cannot cast value for ").append(s).append(" to a Integer: ").append(obj).toString(), ((Throwable) (obj1)));
                return null;
            }
        }
_L3:
        s = Integer.valueOf(i);
_L4:
        return s;
_L2:
        s = null;
        if(true) goto _L4; else goto _L3
    }

    public Long getAsLong(String s)
    {
        Object obj = mValues.get(s);
        if(obj == null) goto _L2; else goto _L1
_L1:
        long l;
        try
        {
            l = ((Number)obj).longValue();
        }
        catch(Object obj1)
        {
            if(obj instanceof CharSequence)
            {
                try
                {
                    obj1 = Long.valueOf(obj.toString());
                }
                // Misplaced declaration of an exception variable
                catch(Object obj1)
                {
                    Log.e("ContentValues", (new StringBuilder()).append("Cannot parse Long value for ").append(obj).append(" at key ").append(s).toString());
                    return null;
                }
                return ((Long) (obj1));
            } else
            {
                Log.e("ContentValues", (new StringBuilder()).append("Cannot cast value for ").append(s).append(" to a Long: ").append(obj).toString(), ((Throwable) (obj1)));
                return null;
            }
        }
_L3:
        s = Long.valueOf(l);
_L4:
        return s;
_L2:
        s = null;
        if(true) goto _L4; else goto _L3
    }

    public Short getAsShort(String s)
    {
        Object obj = mValues.get(s);
        if(obj == null) goto _L2; else goto _L1
_L1:
        short word0;
        try
        {
            word0 = ((Number)obj).shortValue();
        }
        catch(Object obj1)
        {
            if(obj instanceof CharSequence)
            {
                try
                {
                    obj1 = Short.valueOf(obj.toString());
                }
                // Misplaced declaration of an exception variable
                catch(Object obj1)
                {
                    Log.e("ContentValues", (new StringBuilder()).append("Cannot parse Short value for ").append(obj).append(" at key ").append(s).toString());
                    return null;
                }
                return ((Short) (obj1));
            } else
            {
                Log.e("ContentValues", (new StringBuilder()).append("Cannot cast value for ").append(s).append(" to a Short: ").append(obj).toString(), ((Throwable) (obj1)));
                return null;
            }
        }
_L3:
        s = Short.valueOf(word0);
_L4:
        return s;
_L2:
        s = null;
        if(true) goto _L4; else goto _L3
    }

    public String getAsString(String s)
    {
        Object obj = null;
        Object obj1 = mValues.get(s);
        s = obj;
        if(obj1 != null)
            s = obj1.toString();
        return s;
    }

    public ArrayList getStringArrayList(String s)
    {
        return (ArrayList)mValues.get(s);
    }

    public int hashCode()
    {
        return mValues.hashCode();
    }

    public boolean isEmpty()
    {
        return mValues.isEmpty();
    }

    public Set keySet()
    {
        return mValues.keySet();
    }

    public void put(String s, Boolean boolean1)
    {
        mValues.put(s, boolean1);
    }

    public void put(String s, Byte byte1)
    {
        mValues.put(s, byte1);
    }

    public void put(String s, Double double1)
    {
        mValues.put(s, double1);
    }

    public void put(String s, Float float1)
    {
        mValues.put(s, float1);
    }

    public void put(String s, Integer integer)
    {
        mValues.put(s, integer);
    }

    public void put(String s, Long long1)
    {
        mValues.put(s, long1);
    }

    public void put(String s, Short short1)
    {
        mValues.put(s, short1);
    }

    public void put(String s, String s1)
    {
        mValues.put(s, s1);
    }

    public void put(String s, byte abyte0[])
    {
        mValues.put(s, abyte0);
    }

    public void putAll(ContentValues contentvalues)
    {
        mValues.putAll(contentvalues.mValues);
    }

    public void putNull(String s)
    {
        mValues.put(s, null);
    }

    public void putStringArrayList(String s, ArrayList arraylist)
    {
        mValues.put(s, arraylist);
    }

    public void remove(String s)
    {
        mValues.remove(s);
    }

    public int size()
    {
        return mValues.size();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        String s;
        String s1;
        for(Iterator iterator = mValues.keySet().iterator(); iterator.hasNext(); stringbuilder.append(s).append("=").append(s1))
        {
            s = (String)iterator.next();
            s1 = getAsString(s);
            if(stringbuilder.length() > 0)
                stringbuilder.append(" ");
        }

        return stringbuilder.toString();
    }

    public Set valueSet()
    {
        return mValues.entrySet();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeMap(mValues);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ContentValues createFromParcel(Parcel parcel)
        {
            return new ContentValues(parcel.readHashMap(null), null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ContentValues[] newArray(int i)
        {
            return new ContentValues[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String TAG = "ContentValues";
    private HashMap mValues;

}
