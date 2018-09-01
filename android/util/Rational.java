// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import com.android.internal.util.Preconditions;
import java.io.*;

public final class Rational extends Number
    implements Comparable
{

    public Rational(int i, int j)
    {
        int k = i;
        int l = j;
        if(j < 0)
        {
            k = -i;
            l = -j;
        }
        if(l == 0 && k > 0)
        {
            mNumerator = 1;
            mDenominator = 0;
        } else
        if(l == 0 && k < 0)
        {
            mNumerator = -1;
            mDenominator = 0;
        } else
        if(l == 0 && k == 0)
        {
            mNumerator = 0;
            mDenominator = 0;
        } else
        if(k == 0)
        {
            mNumerator = 0;
            mDenominator = 1;
        } else
        {
            i = gcd(k, l);
            mNumerator = k / i;
            mDenominator = l / i;
        }
    }

    private boolean equals(Rational rational)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mNumerator == rational.mNumerator)
        {
            flag1 = flag;
            if(mDenominator == rational.mDenominator)
                flag1 = true;
        }
        return flag1;
    }

    public static int gcd(int i, int j)
    {
        int k = i;
        i = j;
        j = k;
        int l;
        for(; i != 0; i = l)
        {
            l = j % i;
            j = i;
        }

        return Math.abs(j);
    }

    private static NumberFormatException invalidRational(String s)
    {
        throw new NumberFormatException((new StringBuilder()).append("Invalid Rational: \"").append(s).append("\"").toString());
    }

    private boolean isNegInf()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mDenominator == 0)
        {
            flag1 = flag;
            if(mNumerator < 0)
                flag1 = true;
        }
        return flag1;
    }

    private boolean isPosInf()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mDenominator == 0)
        {
            flag1 = flag;
            if(mNumerator > 0)
                flag1 = true;
        }
        return flag1;
    }

    public static Rational parseRational(String s)
        throws NumberFormatException
    {
        Preconditions.checkNotNull(s, "string must not be null");
        if(s.equals("NaN"))
            return NaN;
        if(s.equals("Infinity"))
            return POSITIVE_INFINITY;
        if(s.equals("-Infinity"))
            return NEGATIVE_INFINITY;
        int i = s.indexOf(':');
        int j = i;
        if(i < 0)
            j = s.indexOf('/');
        if(j < 0)
            throw invalidRational(s);
        Rational rational;
        try
        {
            rational = new Rational(Integer.parseInt(s.substring(0, j)), Integer.parseInt(s.substring(j + 1)));
        }
        catch(NumberFormatException numberformatexception)
        {
            throw invalidRational(s);
        }
        return rational;
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        if(mNumerator == 0)
            if(mDenominator == 1 || mDenominator == 0)
                return;
            else
                throw new InvalidObjectException("Rational must be deserialized from a reduced form for zero values");
        if(mDenominator == 0)
            if(mNumerator == 1 || mNumerator == -1)
                return;
            else
                throw new InvalidObjectException("Rational must be deserialized from a reduced form for infinity values");
        if(gcd(mNumerator, mDenominator) > 1)
            throw new InvalidObjectException("Rational must be deserialized from a reduced form for finite values");
        else
            return;
    }

    public int compareTo(Rational rational)
    {
        Preconditions.checkNotNull(rational, "another must not be null");
        if(equals(rational))
            return 0;
        if(isNaN())
            return 1;
        if(rational.isNaN())
            return -1;
        if(isPosInf() || rational.isNegInf())
            return 1;
        if(isNegInf() || rational.isPosInf())
            return -1;
        long l = (long)mNumerator * (long)rational.mDenominator;
        long l1 = (long)rational.mNumerator * (long)mDenominator;
        if(l < l1)
            return -1;
        return l <= l1 ? 0 : 1;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Rational)obj);
    }

    public double doubleValue()
    {
        return (double)mNumerator / (double)mDenominator;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof Rational)
            flag = equals((Rational)obj);
        else
            flag = false;
        return flag;
    }

    public float floatValue()
    {
        return (float)mNumerator / (float)mDenominator;
    }

    public int getDenominator()
    {
        return mDenominator;
    }

    public int getNumerator()
    {
        return mNumerator;
    }

    public int hashCode()
    {
        int i = mNumerator;
        int j = mNumerator;
        return mDenominator ^ (i << 16 | j >>> 16);
    }

    public int intValue()
    {
        if(isPosInf())
            return 0x7fffffff;
        if(isNegInf())
            return 0x80000000;
        if(isNaN())
            return 0;
        else
            return mNumerator / mDenominator;
    }

    public boolean isFinite()
    {
        boolean flag = false;
        if(mDenominator != 0)
            flag = true;
        return flag;
    }

    public boolean isInfinite()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mNumerator != 0)
        {
            flag1 = flag;
            if(mDenominator == 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isNaN()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mDenominator == 0)
        {
            flag1 = flag;
            if(mNumerator == 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isZero()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(isFinite())
        {
            flag1 = flag;
            if(mNumerator == 0)
                flag1 = true;
        }
        return flag1;
    }

    public long longValue()
    {
        if(isPosInf())
            return 0x7fffffffffffffffL;
        if(isNegInf())
            return 0x8000000000000000L;
        if(isNaN())
            return 0L;
        else
            return (long)(mNumerator / mDenominator);
    }

    public short shortValue()
    {
        return (short)intValue();
    }

    public float toFloat()
    {
        return floatValue();
    }

    public String toString()
    {
        if(isNaN())
            return "NaN";
        if(isPosInf())
            return "Infinity";
        if(isNegInf())
            return "-Infinity";
        else
            return (new StringBuilder()).append(mNumerator).append("/").append(mDenominator).toString();
    }

    public static final Rational NEGATIVE_INFINITY = new Rational(-1, 0);
    public static final Rational NaN = new Rational(0, 0);
    public static final Rational POSITIVE_INFINITY = new Rational(1, 0);
    public static final Rational ZERO = new Rational(0, 1);
    private static final long serialVersionUID = 1L;
    private final int mDenominator;
    private final int mNumerator;

}
