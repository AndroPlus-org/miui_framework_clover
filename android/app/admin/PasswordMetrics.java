// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;

public class PasswordMetrics
    implements Parcelable
{

    public PasswordMetrics()
    {
        quality = 0;
        length = 0;
        letters = 0;
        upperCase = 0;
        lowerCase = 0;
        numeric = 0;
        symbols = 0;
        nonLetter = 0;
    }

    public PasswordMetrics(int i, int j)
    {
        quality = 0;
        length = 0;
        letters = 0;
        upperCase = 0;
        lowerCase = 0;
        numeric = 0;
        symbols = 0;
        nonLetter = 0;
        quality = i;
        length = j;
    }

    public PasswordMetrics(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        this(i, j);
        letters = k;
        upperCase = l;
        lowerCase = i1;
        numeric = j1;
        symbols = k1;
        nonLetter = l1;
    }

    private PasswordMetrics(Parcel parcel)
    {
        quality = 0;
        length = 0;
        letters = 0;
        upperCase = 0;
        lowerCase = 0;
        numeric = 0;
        symbols = 0;
        nonLetter = 0;
        quality = parcel.readInt();
        length = parcel.readInt();
        letters = parcel.readInt();
        upperCase = parcel.readInt();
        lowerCase = parcel.readInt();
        numeric = parcel.readInt();
        symbols = parcel.readInt();
        nonLetter = parcel.readInt();
    }

    PasswordMetrics(Parcel parcel, PasswordMetrics passwordmetrics)
    {
        this(parcel);
    }

    private static int categoryChar(char c)
    {
        if('a' <= c && c <= 'z')
            return 0;
        if('A' <= c && c <= 'Z')
            return 1;
        return '0' > c || c > '9' ? 3 : 2;
    }

    public static PasswordMetrics computeForPassword(String s)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        i = 0;
        j = 0;
        k = 0;
        l = 0;
        i1 = 0;
        j1 = 0;
        k1 = s.length();
        l1 = 0;
_L7:
        if(l1 >= k1)
            break MISSING_BLOCK_LABEL_130;
        categoryChar(s.charAt(l1));
        JVM INSTR tableswitch 0 3: default 72
    //                   0 84
    //                   1 95
    //                   2 106
    //                   3 118;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_118;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        int i2 = i;
_L8:
        l1++;
        i = i2;
        if(true) goto _L7; else goto _L6
_L6:
        i2 = i + 1;
        k++;
          goto _L8
_L3:
        i2 = i + 1;
        j++;
          goto _L8
_L4:
        l++;
        j1++;
        i2 = i;
          goto _L8
        i1++;
        j1++;
        i2 = i;
          goto _L8
        boolean flag;
        int j2;
        if(l > 0)
            j2 = 1;
        else
            j2 = 0;
        if(i + i1 > 0)
            flag = true;
        else
            flag = false;
        if(flag && j2 != 0)
            j2 = 0x50000;
        else
        if(flag)
            j2 = 0x40000;
        else
        if(j2 != 0)
        {
            if(maxLengthSequence(s) > 3)
                j2 = 0x20000;
            else
                j2 = 0x30000;
        } else
        {
            j2 = 0;
        }
        return new PasswordMetrics(j2, k1, i, j, k, l, i1, j1);
    }

    private static int maxDiffCategory(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 0: // '\0'
        case 1: // '\001'
            return 1;

        case 2: // '\002'
            return 10;
        }
    }

    public static int maxLengthSequence(String s)
    {
        if(s.length() == 0)
            return 0;
        char c = s.charAt(0);
        int i = categoryChar(c);
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        int j1 = 1;
        char c2 = c;
        while(j1 < s.length()) 
        {
            char c1 = s.charAt(j1);
            int l1 = categoryChar(c1);
            int i2 = c1 - c2;
            int j2;
            if(l1 != i || Math.abs(i2) > maxDiffCategory(i))
            {
                l = Math.max(l, j1 - i1);
                j2 = j1;
                i1 = 0;
                i = l1;
            } else
            {
                int k1 = l;
                j2 = i1;
                if(k != 0)
                {
                    k1 = l;
                    j2 = i1;
                    if(i2 != j)
                    {
                        k1 = Math.max(l, j1 - i1);
                        j2 = j1 - 1;
                    }
                }
                j = i2;
                i1 = 1;
                l = k1;
            }
            c2 = c1;
            j1++;
            k = i1;
            i1 = j2;
        }
        return Math.max(l, s.length() - i1);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof PasswordMetrics))
            return false;
        obj = (PasswordMetrics)obj;
        boolean flag1 = flag;
        if(quality == ((PasswordMetrics) (obj)).quality)
        {
            flag1 = flag;
            if(length == ((PasswordMetrics) (obj)).length)
            {
                flag1 = flag;
                if(letters == ((PasswordMetrics) (obj)).letters)
                {
                    flag1 = flag;
                    if(upperCase == ((PasswordMetrics) (obj)).upperCase)
                    {
                        flag1 = flag;
                        if(lowerCase == ((PasswordMetrics) (obj)).lowerCase)
                        {
                            flag1 = flag;
                            if(numeric == ((PasswordMetrics) (obj)).numeric)
                            {
                                flag1 = flag;
                                if(symbols == ((PasswordMetrics) (obj)).symbols)
                                {
                                    flag1 = flag;
                                    if(nonLetter == ((PasswordMetrics) (obj)).nonLetter)
                                        flag1 = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public boolean isDefault()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(quality == 0)
        {
            flag1 = flag;
            if(length == 0)
            {
                flag1 = flag;
                if(letters == 0)
                {
                    flag1 = flag;
                    if(upperCase == 0)
                    {
                        flag1 = flag;
                        if(lowerCase == 0)
                        {
                            flag1 = flag;
                            if(numeric == 0)
                            {
                                flag1 = flag;
                                if(symbols == 0)
                                {
                                    flag1 = flag;
                                    if(nonLetter == 0)
                                        flag1 = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(quality);
        parcel.writeInt(length);
        parcel.writeInt(letters);
        parcel.writeInt(upperCase);
        parcel.writeInt(lowerCase);
        parcel.writeInt(numeric);
        parcel.writeInt(symbols);
        parcel.writeInt(nonLetter);
    }

    private static final int CHAR_DIGIT = 2;
    private static final int CHAR_LOWER_CASE = 0;
    private static final int CHAR_SYMBOL = 3;
    private static final int CHAR_UPPER_CASE = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PasswordMetrics createFromParcel(Parcel parcel)
        {
            return new PasswordMetrics(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PasswordMetrics[] newArray(int i)
        {
            return new PasswordMetrics[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int MAX_ALLOWED_SEQUENCE = 3;
    public int length;
    public int letters;
    public int lowerCase;
    public int nonLetter;
    public int numeric;
    public int quality;
    public int symbols;
    public int upperCase;

}
