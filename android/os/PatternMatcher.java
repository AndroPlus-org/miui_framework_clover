// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.Arrays;

// Referenced classes of package android.os:
//            Parcelable, Parcel

public class PatternMatcher
    implements Parcelable
{

    public PatternMatcher(Parcel parcel)
    {
        mPattern = parcel.readString();
        mType = parcel.readInt();
        mParsedPattern = parcel.createIntArray();
    }

    public PatternMatcher(String s, int i)
    {
        mPattern = s;
        mType = i;
        if(mType == 3)
            mParsedPattern = parseAndVerifyAdvancedPattern(s);
        else
            mParsedPattern = null;
    }

    private static boolean isParsedModifier(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i == -8) goto _L2; else goto _L1
_L1:
        if(i != -7) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != -6)
        {
            flag1 = flag;
            if(i != -5)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    static boolean matchAdvancedPattern(int ai[], String s)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        i = 0;
        j = 0;
        k = ai.length;
        l = s.length();
        i1 = 0;
        j1 = 0;
_L10:
        int k1;
        if(i >= k)
            break; /* Loop/switch isn't completed */
        k1 = ai[i];
        k1;
        JVM INSTR tableswitch -4 -1: default 64
    //                   -4 94
    //                   -3 64
    //                   -2 103
    //                   -1 103;
           goto _L1 _L2 _L1 _L3 _L3
_L1:
        byte byte0;
        i1 = i;
        byte0 = 0;
        i++;
_L6:
        if(i < k) goto _L5; else goto _L4
_L4:
        int l1;
        l1 = 1;
        k1 = 1;
_L8:
        if(k1 > l1)
            return false;
        k1 = matchChars(s, j, l, byte0, k1, l1, ai, i1, j1);
        if(k1 == -1)
            return false;
        j += k1;
        continue; /* Loop/switch isn't completed */
_L2:
        byte0 = 1;
        i++;
          goto _L6
_L3:
        if(k1 == -1)
            byte0 = 2;
        else
            byte0 = 3;
        i1 = i + 1;
        do
        {
            k1 = i + 1;
            if(k1 >= k)
                break;
            i = k1;
        } while(ai[k1] != -3);
        j1 = k1 - 1;
        i = k1 + 1;
          goto _L6
_L5:
        switch(ai[i])
        {
        case -6: 
        default:
            l1 = 1;
            k1 = 1;
            break;

        case -7: 
            k1 = 0;
            l1 = 0x7fffffff;
            i++;
            break;

        case -8: 
            k1 = 1;
            l1 = 0x7fffffff;
            i++;
            break;

        case -5: 
            i++;
            k1 = ai[i];
            i++;
            l1 = ai[i];
            i += 2;
            break;
        }
        if(true) goto _L8; else goto _L7
_L7:
        if(true) goto _L10; else goto _L9
_L9:
        boolean flag;
        if(i >= k && j >= l)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean matchChar(String s, int i, int j, int k, int ai[], int l, int i1)
    {
        boolean flag = true;
        if(i >= j)
            return false;
        switch(k)
        {
        default:
            return false;

        case 1: // '\001'
            return true;

        case 2: // '\002'
            for(j = l; j < i1; j += 2)
            {
                k = s.charAt(i);
                if(k >= ai[j] && k <= ai[j + 1])
                    return true;
            }

            return false;

        case 3: // '\003'
            for(j = l; j < i1; j += 2)
            {
                k = s.charAt(i);
                if(k >= ai[j] && k <= ai[j + 1])
                    return false;
            }

            return true;

        case 0: // '\0'
            break;
        }
        if(s.charAt(i) != ai[l])
            flag = false;
        return flag;
    }

    private static int matchChars(String s, int i, int j, int k, int l, int i1, int ai[], int j1, 
            int k1)
    {
        int l1;
        for(l1 = 0; l1 < i1 && matchChar(s, i + l1, j, k, ai, j1, k1); l1++);
        i = l1;
        if(l1 < l)
            i = -1;
        return i;
    }

    static boolean matchGlobPattern(String s, String s1)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        boolean flag = true;
        i = s.length();
        if(i <= 0)
        {
            if(s1.length() > 0)
                flag = false;
            return flag;
        }
        j = s1.length();
        k = 0;
        l = 0;
        i1 = s.charAt(0);
_L12:
        int k1;
        int l1;
        if(k >= i || l >= j)
            break; /* Loop/switch isn't completed */
        int j1 = k + 1;
        boolean flag1;
        if(j1 < i)
            k = s.charAt(j1);
        else
            k = 0;
        if(i1 == '\\')
            flag1 = true;
        else
            flag1 = false;
        k1 = i1;
        l1 = j1;
        i1 = k;
        if(flag1)
        {
            l1 = j1 + 1;
            if(l1 < i)
            {
                i1 = s.charAt(l1);
                k1 = k;
            } else
            {
                i1 = 0;
                k1 = k;
            }
        }
        if(i1 != 42)
            break MISSING_BLOCK_LABEL_392;
        i1 = l;
        if(flag1) goto _L2; else goto _L1
_L1:
        i1 = l;
        if(k1 != 46) goto _L2; else goto _L3
_L3:
        if(l1 >= i - 1)
            return true;
        k1 = l1 + 1;
        char c = s.charAt(k1);
        k = l;
        l1 = k1;
        i1 = c;
        if(c == '\\')
        {
            l1 = k1 + 1;
            if(l1 < i)
            {
                i1 = s.charAt(l1);
                k = l;
            } else
            {
                i1 = 0;
                k = l;
            }
        }
_L8:
        if(s1.charAt(k) != i1) goto _L5; else goto _L4
_L4:
        if(k == j)
            return false;
        break; /* Loop/switch isn't completed */
_L5:
        l = k + 1;
        k = l;
        if(l >= j) goto _L4; else goto _L6
_L6:
        k = l;
        if(true) goto _L8; else goto _L7
_L7:
        if(++l1 < i)
            i1 = s.charAt(l1);
        else
            i1 = 0;
        l = k + 1;
        k = l1;
        continue; /* Loop/switch isn't completed */
_L10:
        l = ++i1;
        if(i1 >= j)
            break; /* Loop/switch isn't completed */
_L2:
        if(s1.charAt(i1) == k1)
            continue; /* Loop/switch isn't completed */
        l = i1;
        break; /* Loop/switch isn't completed */
        if(true) goto _L10; else goto _L9
_L9:
        k = l1 + 1;
        if(k < i)
            i1 = s.charAt(k);
        else
            i1 = 0;
        continue; /* Loop/switch isn't completed */
        if(k1 != 46 && s1.charAt(l) != k1)
            return false;
        l++;
        k = l1;
        if(true) goto _L12; else goto _L11
_L11:
        if(k >= i && l >= j)
            return true;
        return k == i - 2 && s.charAt(k) == '.' && s.charAt(k + 1) == '*';
    }

    static boolean matchPattern(String s, String s1, int ai[], int i)
    {
        if(s == null)
            return false;
        if(i == 0)
            return s1.equals(s);
        if(i == 1)
            return s.startsWith(s1);
        if(i == 2)
            return matchGlobPattern(s1, s);
        if(i == 3)
            return matchAdvancedPattern(ai, s);
        else
            return false;
    }

    static int[] parseAndVerifyAdvancedPattern(String s)
    {
        android/os/PatternMatcher;
        JVM INSTR monitorenter ;
        int i = 0;
        int j = s.length();
        boolean flag;
        boolean flag1;
        boolean flag2;
        int k;
        flag = false;
        flag1 = false;
        flag2 = false;
        k = 0;
_L16:
        if(i >= j) goto _L2; else goto _L1
_L1:
        if(k <= 2045)
            break MISSING_BLOCK_LABEL_52;
        s = JVM INSTR new #131 <Class IllegalArgumentException>;
        s.IllegalArgumentException("Pattern is too large!");
        throw s;
        s;
        android/os/PatternMatcher;
        JVM INSTR monitorexit ;
        throw s;
        int l = s.charAt(i);
        int i1;
        int j1;
        i1 = 0;
        j1 = 0;
        l;
        JVM INSTR lookupswitch 8: default 140
    //                   42: 440
    //                   43: 500
    //                   46: 560
    //                   91: 183
    //                   92: 589
    //                   93: 262
    //                   123: 341
    //                   125: 407;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L3:
        j1 = 1;
_L19:
        if(!flag) goto _L13; else goto _L12
_L12:
        if(!flag2) goto _L15; else goto _L14
_L14:
        int ai[] = sParsedPatternScratch;
        j1 = k + 1;
        ai[k] = l;
        flag2 = false;
        k = j1;
_L43:
        i++;
          goto _L16
_L7:
        if(!flag) goto _L18; else goto _L17
_L17:
        j1 = 1;
          goto _L19
_L18:
        if(s.charAt(i + 1) != '^') goto _L21; else goto _L20
_L20:
        ai = sParsedPatternScratch;
        j1 = k + 1;
        ai[k] = -2;
        i++;
        k = j1;
_L22:
        i++;
        flag = true;
          goto _L16
_L21:
        ai = sParsedPatternScratch;
        j1 = k + 1;
        ai[k] = -1;
        k = j1;
          goto _L22
_L9:
        if(flag) goto _L24; else goto _L23
_L23:
        j1 = 1;
          goto _L19
_L24:
        j1 = sParsedPatternScratch[k - 1];
        if(j1 != -1 && j1 != -2) goto _L26; else goto _L25
_L25:
        s = JVM INSTR new #131 <Class IllegalArgumentException>;
        s.IllegalArgumentException("You must define characters in a set.");
        throw s;
_L26:
        ai = sParsedPatternScratch;
        int l1 = k + 1;
        ai[k] = -3;
        flag = false;
        flag2 = false;
        j1 = i1;
        k = l1;
          goto _L19
_L10:
        if(flag) goto _L19; else goto _L27
_L27:
        if(k == 0) goto _L29; else goto _L28
_L28:
        if(!isParsedModifier(sParsedPatternScratch[k - 1])) goto _L30; else goto _L29
_L29:
        s = JVM INSTR new #131 <Class IllegalArgumentException>;
        s.IllegalArgumentException("Modifier must follow a token.");
        throw s;
_L30:
        ai = sParsedPatternScratch;
        i1 = k + 1;
        ai[k] = -5;
        i++;
        flag1 = true;
        k = i1;
          goto _L19
_L11:
        if(!flag1) goto _L19; else goto _L31
_L31:
        ai = sParsedPatternScratch;
        i1 = k + 1;
        ai[k] = -6;
        flag1 = false;
        k = i1;
          goto _L19
_L4:
        if(flag) goto _L19; else goto _L32
_L32:
        if(k == 0) goto _L34; else goto _L33
_L33:
        if(!isParsedModifier(sParsedPatternScratch[k - 1])) goto _L35; else goto _L34
_L34:
        s = JVM INSTR new #131 <Class IllegalArgumentException>;
        s.IllegalArgumentException("Modifier must follow a token.");
        throw s;
_L35:
        ai = sParsedPatternScratch;
        i1 = k + 1;
        ai[k] = -7;
        k = i1;
          goto _L19
_L5:
        if(flag) goto _L19; else goto _L36
_L36:
        if(k == 0) goto _L38; else goto _L37
_L37:
        if(!isParsedModifier(sParsedPatternScratch[k - 1])) goto _L39; else goto _L38
_L38:
        s = JVM INSTR new #131 <Class IllegalArgumentException>;
        s.IllegalArgumentException("Modifier must follow a token.");
        throw s;
_L39:
        ai = sParsedPatternScratch;
        i1 = k + 1;
        ai[k] = -8;
        k = i1;
          goto _L19
_L6:
        if(flag) goto _L19; else goto _L40
_L40:
        ai = sParsedPatternScratch;
        i1 = k + 1;
        ai[k] = -4;
        k = i1;
          goto _L19
_L8:
        if(i + 1 < j) goto _L42; else goto _L41
_L41:
        s = JVM INSTR new #131 <Class IllegalArgumentException>;
        s.IllegalArgumentException("Escape found at end of pattern!");
        throw s;
_L42:
        i++;
        l = s.charAt(i);
        j1 = 1;
          goto _L19
_L15:
        if(i + 2 >= j)
            break MISSING_BLOCK_LABEL_686;
        if(s.charAt(i + 1) != '-' || s.charAt(i + 2) == ']')
            break MISSING_BLOCK_LABEL_686;
        flag2 = true;
        ai = sParsedPatternScratch;
        j1 = k + 1;
        ai[k] = l;
        i++;
        k = j1;
          goto _L43
        ai = sParsedPatternScratch;
        j1 = k + 1;
        ai[k] = l;
        sParsedPatternScratch[j1] = l;
        k = j1 + 1;
          goto _L43
_L13:
        if(!flag1) goto _L45; else goto _L44
_L44:
        l = s.indexOf('}', i);
        if(l >= 0) goto _L47; else goto _L46
_L46:
        s = JVM INSTR new #131 <Class IllegalArgumentException>;
        s.IllegalArgumentException("Range not ended with '}'");
        throw s;
_L47:
        Object obj;
        obj = s.substring(i, l);
        i = ((String) (obj)).indexOf(',');
        if(i >= 0) goto _L49; else goto _L48
_L48:
        i1 = k;
        j1 = Integer.parseInt(((String) (obj)));
        i = j1;
_L54:
        if(j1 <= i) goto _L51; else goto _L50
_L50:
        i1 = k;
        s = JVM INSTR new #131 <Class IllegalArgumentException>;
        i1 = k;
        s.IllegalArgumentException("Range quantifier minimum is greater than maximum");
        i1 = k;
        try
        {
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
_L58:
        obj = JVM INSTR new #131 <Class IllegalArgumentException>;
        ((IllegalArgumentException) (obj)).IllegalArgumentException("Range number format incorrect", s);
        throw obj;
_L49:
        i1 = k;
        j1 = Integer.parseInt(((String) (obj)).substring(0, i));
        i1 = k;
        if(i != ((String) (obj)).length() - 1) goto _L53; else goto _L52
_L52:
        i = 0x7fffffff;
          goto _L54
_L53:
        i1 = k;
        i = Integer.parseInt(((String) (obj)).substring(i + 1));
          goto _L54
_L51:
        i1 = k;
        obj = sParsedPatternScratch;
        i1 = k + 1;
        obj[k] = j1;
        obj = sParsedPatternScratch;
        k = i1 + 1;
        obj[i1] = i;
        i = l;
          goto _L16
_L45:
        if(j1 == 0) goto _L43; else goto _L55
_L55:
        obj = sParsedPatternScratch;
        int k1 = k + 1;
        obj[k] = l;
        k = k1;
          goto _L43
_L2:
        if(!flag) goto _L57; else goto _L56
_L56:
        s = JVM INSTR new #131 <Class IllegalArgumentException>;
        s.IllegalArgumentException("Set was not terminated!");
        throw s;
_L57:
        s = Arrays.copyOf(sParsedPatternScratch, k);
        android/os/PatternMatcher;
        JVM INSTR monitorexit ;
        return s;
        s;
          goto _L58
    }

    public int describeContents()
    {
        return 0;
    }

    public final String getPath()
    {
        return mPattern;
    }

    public final int getType()
    {
        return mType;
    }

    public boolean match(String s)
    {
        return matchPattern(s, mPattern, mParsedPattern, mType);
    }

    public String toString()
    {
        String s = "? ";
        mType;
        JVM INSTR tableswitch 0 3: default 36
    //                   0 68
    //                   1 74
    //                   2 80
    //                   3 86;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return (new StringBuilder()).append("PatternMatcher{").append(s).append(mPattern).append("}").toString();
_L2:
        s = "LITERAL: ";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "PREFIX: ";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "GLOB: ";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "ADVANCED: ";
        if(true) goto _L1; else goto _L6
_L6:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mPattern);
        parcel.writeInt(mType);
        parcel.writeIntArray(mParsedPattern);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public PatternMatcher createFromParcel(Parcel parcel)
        {
            return new PatternMatcher(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PatternMatcher[] newArray(int i)
        {
            return new PatternMatcher[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_PATTERN_STORAGE = 2048;
    private static final int NO_MATCH = -1;
    private static final int PARSED_MODIFIER_ONE_OR_MORE = -8;
    private static final int PARSED_MODIFIER_RANGE_START = -5;
    private static final int PARSED_MODIFIER_RANGE_STOP = -6;
    private static final int PARSED_MODIFIER_ZERO_OR_MORE = -7;
    private static final int PARSED_TOKEN_CHAR_ANY = -4;
    private static final int PARSED_TOKEN_CHAR_SET_INVERSE_START = -2;
    private static final int PARSED_TOKEN_CHAR_SET_START = -1;
    private static final int PARSED_TOKEN_CHAR_SET_STOP = -3;
    public static final int PATTERN_ADVANCED_GLOB = 3;
    public static final int PATTERN_LITERAL = 0;
    public static final int PATTERN_PREFIX = 1;
    public static final int PATTERN_SIMPLE_GLOB = 2;
    private static final String TAG = "PatternMatcher";
    private static final int TOKEN_TYPE_ANY = 1;
    private static final int TOKEN_TYPE_INVERSE_SET = 3;
    private static final int TOKEN_TYPE_LITERAL = 0;
    private static final int TOKEN_TYPE_SET = 2;
    private static final int sParsedPatternScratch[] = new int[2048];
    private final int mParsedPattern[];
    private final String mPattern;
    private final int mType;

}
