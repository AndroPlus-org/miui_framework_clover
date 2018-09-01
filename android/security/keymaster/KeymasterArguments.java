// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.Parcel;
import android.os.Parcelable;
import java.math.BigInteger;
import java.util.*;

// Referenced classes of package android.security.keymaster:
//            KeymasterArgument, KeymasterIntArgument, KeymasterLongArgument, KeymasterDefs, 
//            KeymasterBooleanArgument, KeymasterBlobArgument, KeymasterDateArgument

public class KeymasterArguments
    implements Parcelable
{

    public KeymasterArguments()
    {
        mArguments = new ArrayList();
    }

    private KeymasterArguments(Parcel parcel)
    {
        mArguments = parcel.createTypedArrayList(KeymasterArgument.CREATOR);
    }

    KeymasterArguments(Parcel parcel, KeymasterArguments keymasterarguments)
    {
        this(parcel);
    }

    private void addEnumTag(int i, int j)
    {
        mArguments.add(new KeymasterIntArgument(i, j));
    }

    private void addLongTag(int i, BigInteger biginteger)
    {
        if(biginteger.signum() == -1 || biginteger.compareTo(UINT64_MAX_VALUE) > 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Long tag value out of range: ").append(biginteger).toString());
        } else
        {
            mArguments.add(new KeymasterLongArgument(i, biginteger.longValue()));
            return;
        }
    }

    private KeymasterArgument getArgumentByTag(int i)
    {
        for(Iterator iterator = mArguments.iterator(); iterator.hasNext();)
        {
            KeymasterArgument keymasterargument = (KeymasterArgument)iterator.next();
            if(keymasterargument.tag == i)
                return keymasterargument;
        }

        return null;
    }

    private int getEnumTagValue(KeymasterArgument keymasterargument)
    {
        return ((KeymasterIntArgument)keymasterargument).value;
    }

    private BigInteger getLongTagValue(KeymasterArgument keymasterargument)
    {
        return toUint64(((KeymasterLongArgument)keymasterargument).value);
    }

    public static BigInteger toUint64(long l)
    {
        if(l >= 0L)
            return BigInteger.valueOf(l);
        else
            return BigInteger.valueOf(l).add(UINT64_RANGE);
    }

    public void addBoolean(int i)
    {
        if(KeymasterDefs.getTagType(i) != 0x70000000)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Not a boolean tag: ").append(i).toString());
        } else
        {
            mArguments.add(new KeymasterBooleanArgument(i));
            return;
        }
    }

    public void addBytes(int i, byte abyte0[])
    {
        if(KeymasterDefs.getTagType(i) != 0x90000000)
            throw new IllegalArgumentException((new StringBuilder()).append("Not a bytes tag: ").append(i).toString());
        if(abyte0 == null)
        {
            throw new NullPointerException("value == nulll");
        } else
        {
            mArguments.add(new KeymasterBlobArgument(i, abyte0));
            return;
        }
    }

    public void addDate(int i, Date date)
    {
        if(KeymasterDefs.getTagType(i) != 0x60000000)
            throw new IllegalArgumentException((new StringBuilder()).append("Not a date tag: ").append(i).toString());
        if(date == null)
            throw new NullPointerException("value == nulll");
        if(date.getTime() < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Date tag value out of range: ").append(date).toString());
        } else
        {
            mArguments.add(new KeymasterDateArgument(i, date));
            return;
        }
    }

    public void addDateIfNotNull(int i, Date date)
    {
        if(KeymasterDefs.getTagType(i) != 0x60000000)
            throw new IllegalArgumentException((new StringBuilder()).append("Not a date tag: ").append(i).toString());
        if(date != null)
            addDate(i, date);
    }

    public void addEnum(int i, int j)
    {
        int k = KeymasterDefs.getTagType(i);
        if(k != 0x10000000 && k != 0x20000000)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Not an enum or repeating enum tag: ").append(i).toString());
        } else
        {
            addEnumTag(i, j);
            return;
        }
    }

    public transient void addEnums(int i, int ai[])
    {
        if(KeymasterDefs.getTagType(i) != 0x20000000)
            throw new IllegalArgumentException((new StringBuilder()).append("Not a repeating enum tag: ").append(i).toString());
        int j = 0;
        for(int k = ai.length; j < k; j++)
            addEnumTag(i, ai[j]);

    }

    public void addUnsignedInt(int i, long l)
    {
        int j = KeymasterDefs.getTagType(i);
        if(j != 0x30000000 && j != 0x40000000)
            throw new IllegalArgumentException((new StringBuilder()).append("Not an int or repeating int tag: ").append(i).toString());
        if(l < 0L || l > 0xffffffffL)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Int tag value out of range: ").append(l).toString());
        } else
        {
            mArguments.add(new KeymasterIntArgument(i, (int)l));
            return;
        }
    }

    public void addUnsignedLong(int i, BigInteger biginteger)
    {
        int j = KeymasterDefs.getTagType(i);
        if(j != 0x50000000 && j != 0xa0000000)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Not a long or repeating long tag: ").append(i).toString());
        } else
        {
            addLongTag(i, biginteger);
            return;
        }
    }

    public boolean containsTag(int i)
    {
        boolean flag;
        if(getArgumentByTag(i) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean getBoolean(int i)
    {
        if(KeymasterDefs.getTagType(i) != 0x70000000)
            throw new IllegalArgumentException((new StringBuilder()).append("Not a boolean tag: ").append(i).toString());
        return getArgumentByTag(i) != null;
    }

    public byte[] getBytes(int i, byte abyte0[])
    {
        if(KeymasterDefs.getTagType(i) != 0x90000000)
            throw new IllegalArgumentException((new StringBuilder()).append("Not a bytes tag: ").append(i).toString());
        KeymasterArgument keymasterargument = getArgumentByTag(i);
        if(keymasterargument == null)
            return abyte0;
        else
            return ((KeymasterBlobArgument)keymasterargument).blob;
    }

    public Date getDate(int i, Date date)
    {
        if(KeymasterDefs.getTagType(i) != 0x60000000)
            throw new IllegalArgumentException((new StringBuilder()).append("Tag is not a date type: ").append(i).toString());
        KeymasterArgument keymasterargument = getArgumentByTag(i);
        if(keymasterargument == null)
            return date;
        date = ((KeymasterDateArgument)keymasterargument).date;
        if(date.getTime() < 0L)
            throw new IllegalArgumentException((new StringBuilder()).append("Tag value too large. Tag: ").append(i).toString());
        else
            return date;
    }

    public int getEnum(int i, int j)
    {
        if(KeymasterDefs.getTagType(i) != 0x10000000)
            throw new IllegalArgumentException((new StringBuilder()).append("Not an enum tag: ").append(i).toString());
        KeymasterArgument keymasterargument = getArgumentByTag(i);
        if(keymasterargument == null)
            return j;
        else
            return getEnumTagValue(keymasterargument);
    }

    public List getEnums(int i)
    {
        if(KeymasterDefs.getTagType(i) != 0x20000000)
            throw new IllegalArgumentException((new StringBuilder()).append("Not a repeating enum tag: ").append(i).toString());
        ArrayList arraylist = new ArrayList();
        Iterator iterator = mArguments.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            KeymasterArgument keymasterargument = (KeymasterArgument)iterator.next();
            if(keymasterargument.tag == i)
                arraylist.add(Integer.valueOf(getEnumTagValue(keymasterargument)));
        } while(true);
        return arraylist;
    }

    public long getUnsignedInt(int i, long l)
    {
        if(KeymasterDefs.getTagType(i) != 0x30000000)
            throw new IllegalArgumentException((new StringBuilder()).append("Not an int tag: ").append(i).toString());
        KeymasterArgument keymasterargument = getArgumentByTag(i);
        if(keymasterargument == null)
            return l;
        else
            return (long)((KeymasterIntArgument)keymasterargument).value & 0xffffffffL;
    }

    public List getUnsignedLongs(int i)
    {
        if(KeymasterDefs.getTagType(i) != 0xa0000000)
            throw new IllegalArgumentException((new StringBuilder()).append("Tag is not a repeating long: ").append(i).toString());
        ArrayList arraylist = new ArrayList();
        Iterator iterator = mArguments.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            KeymasterArgument keymasterargument = (KeymasterArgument)iterator.next();
            if(keymasterargument.tag == i)
                arraylist.add(getLongTagValue(keymasterargument));
        } while(true);
        return arraylist;
    }

    public void readFromParcel(Parcel parcel)
    {
        parcel.readTypedList(mArguments, KeymasterArgument.CREATOR);
    }

    public int size()
    {
        return mArguments.size();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(mArguments);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeymasterArguments createFromParcel(Parcel parcel)
        {
            return new KeymasterArguments(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeymasterArguments[] newArray(int i)
        {
            return new KeymasterArguments[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final long UINT32_MAX_VALUE = 0xffffffffL;
    private static final long UINT32_RANGE = 0x100000000L;
    public static final BigInteger UINT64_MAX_VALUE;
    private static final BigInteger UINT64_RANGE;
    private List mArguments;

    static 
    {
        UINT64_RANGE = BigInteger.ONE.shiftLeft(64);
        UINT64_MAX_VALUE = UINT64_RANGE.subtract(BigInteger.ONE);
    }
}
