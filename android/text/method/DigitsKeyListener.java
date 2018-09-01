// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.icu.lang.UCharacter;
import android.icu.text.DecimalFormatSymbols;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import com.android.internal.util.ArrayUtils;
import java.util.*;

// Referenced classes of package android.text.method:
//            NumberKeyListener

public class DigitsKeyListener extends NumberKeyListener
{

    public DigitsKeyListener()
    {
        this(null, false, false);
    }

    private DigitsKeyListener(String s)
    {
        mDecimalPointChars = ".";
        mSignChars = "-+";
        mSign = false;
        mDecimal = false;
        mStringMode = true;
        mLocale = null;
        mAccepted = new char[s.length()];
        s.getChars(0, s.length(), mAccepted, 0);
        mNeedsAdvancedInput = false;
    }

    public DigitsKeyListener(Locale locale)
    {
        this(locale, false, false);
    }

    public DigitsKeyListener(Locale locale, boolean flag, boolean flag1)
    {
        mDecimalPointChars = ".";
        mSignChars = "-+";
        mSign = flag;
        mDecimal = flag1;
        mStringMode = false;
        mLocale = locale;
        if(locale == null)
        {
            setToCompat();
            return;
        }
        LinkedHashSet linkedhashset = new LinkedHashSet();
        if(!NumberKeyListener.addDigits(linkedhashset, locale))
        {
            setToCompat();
            return;
        }
        if(flag || flag1)
        {
            locale = DecimalFormatSymbols.getInstance(locale);
            if(flag)
            {
                String s = stripBidiControls(locale.getMinusSignString());
                String s1 = stripBidiControls(locale.getPlusSignString());
                if(s.length() > 1 || s1.length() > 1)
                {
                    setToCompat();
                    return;
                }
                char c = s.charAt(0);
                char c1 = s1.charAt(0);
                linkedhashset.add(Character.valueOf(c));
                linkedhashset.add(Character.valueOf(c1));
                mSignChars = (new StringBuilder()).append("").append(c).append(c1).toString();
                if(c == '\u2212' || c == '\u2013')
                {
                    linkedhashset.add(Character.valueOf('-'));
                    mSignChars = (new StringBuilder()).append(mSignChars).append('-').toString();
                }
            }
            if(flag1)
            {
                locale = locale.getDecimalSeparatorString();
                if(locale.length() > 1)
                {
                    setToCompat();
                    return;
                }
                locale = Character.valueOf(locale.charAt(0));
                linkedhashset.add(locale);
                mDecimalPointChars = locale.toString();
            }
        }
        mAccepted = NumberKeyListener.collectionToArray(linkedhashset);
        calculateNeedForAdvancedInput();
    }

    public DigitsKeyListener(boolean flag, boolean flag1)
    {
        this(null, flag, flag1);
    }

    private void calculateNeedForAdvancedInput()
    {
        byte byte0 = 0;
        boolean flag;
        if(mSign)
            flag = true;
        else
            flag = false;
        if(mDecimal)
            byte0 = 2;
        mNeedsAdvancedInput = ArrayUtils.containsAll(COMPATIBILITY_CHARACTERS[flag | byte0], mAccepted) ^ true;
    }

    public static DigitsKeyListener getInstance()
    {
        return getInstance(false, false);
    }

    public static DigitsKeyListener getInstance(String s)
    {
        Object obj = sStringCacheLock;
        obj;
        JVM INSTR monitorenter ;
        DigitsKeyListener digitskeylistener = (DigitsKeyListener)sStringInstanceCache.get(s);
        DigitsKeyListener digitskeylistener1;
        digitskeylistener1 = digitskeylistener;
        if(digitskeylistener != null)
            break MISSING_BLOCK_LABEL_41;
        digitskeylistener1 = JVM INSTR new #2   <Class DigitsKeyListener>;
        digitskeylistener1.DigitsKeyListener(s);
        sStringInstanceCache.put(s, digitskeylistener1);
        obj;
        JVM INSTR monitorexit ;
        return digitskeylistener1;
        s;
        throw s;
    }

    public static DigitsKeyListener getInstance(Locale locale)
    {
        return getInstance(locale, false, false);
    }

    public static DigitsKeyListener getInstance(Locale locale, DigitsKeyListener digitskeylistener)
    {
        if(digitskeylistener.mStringMode)
            return digitskeylistener;
        else
            return getInstance(locale, digitskeylistener.mSign, digitskeylistener.mDecimal);
    }

    public static DigitsKeyListener getInstance(Locale locale, boolean flag, boolean flag1)
    {
        int i;
        Object obj;
        DigitsKeyListener adigitskeylistener[];
        byte byte0 = 0;
        if(flag)
            i = 1;
        else
            i = 0;
        if(flag1)
            byte0 = 2;
        i |= byte0;
        obj = sLocaleCacheLock;
        obj;
        JVM INSTR monitorenter ;
        adigitskeylistener = (DigitsKeyListener[])sLocaleInstanceCache.get(locale);
        DigitsKeyListener adigitskeylistener1[];
        if(adigitskeylistener != null && adigitskeylistener[i] != null)
        {
            locale = adigitskeylistener[i];
            return locale;
        }
        adigitskeylistener1 = adigitskeylistener;
        if(adigitskeylistener != null)
            break MISSING_BLOCK_LABEL_96;
        adigitskeylistener1 = new DigitsKeyListener[4];
        sLocaleInstanceCache.put(locale, adigitskeylistener1);
        locale = new DigitsKeyListener(locale, flag, flag1);
        adigitskeylistener1[i] = locale;
        obj;
        JVM INSTR monitorexit ;
        return locale;
        locale;
        throw locale;
    }

    public static DigitsKeyListener getInstance(boolean flag, boolean flag1)
    {
        return getInstance(null, flag, flag1);
    }

    private boolean isDecimalPointChar(char c)
    {
        boolean flag;
        if(mDecimalPointChars.indexOf(c) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean isSignChar(char c)
    {
        boolean flag;
        if(mSignChars.indexOf(c) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void setToCompat()
    {
        mDecimalPointChars = ".";
        mSignChars = "-+";
        boolean flag;
        byte byte0;
        if(mSign)
            flag = true;
        else
            flag = false;
        if(mDecimal)
            byte0 = 2;
        else
            byte0 = 0;
        mAccepted = COMPATIBILITY_CHARACTERS[flag | byte0];
        mNeedsAdvancedInput = false;
    }

    private static String stripBidiControls(String s)
    {
        String s1 = "";
        int i = 0;
        while(i < s.length()) 
        {
            char c = s.charAt(i);
            String s2 = s1;
            if(!UCharacter.hasBinaryProperty(c, 2))
                if(s1.isEmpty())
                    s2 = String.valueOf(c);
                else
                    s2 = (new StringBuilder()).append(s1).append(c).toString();
            i++;
            s1 = s2;
        }
        return s1;
    }

    public CharSequence filter(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l)
    {
        CharSequence charsequence1 = super.filter(charsequence, i, j, spanned, k, l);
        if(!mSign && !mDecimal)
            return charsequence1;
        CharSequence charsequence2 = charsequence;
        int i1 = i;
        int j1 = j;
        if(charsequence1 != null)
        {
            charsequence2 = charsequence1;
            i1 = 0;
            j1 = charsequence1.length();
        }
        j = -1;
        int k1 = -1;
        int l1 = spanned.length();
        i = 0;
        while(i < k) 
        {
            char c = spanned.charAt(i);
            int j2;
            if(isSignChar(c))
            {
                j2 = i;
            } else
            {
                j2 = j;
                if(isDecimalPointChar(c))
                {
                    k1 = i;
                    j2 = j;
                }
            }
            i++;
            j = j2;
        }
        for(i = l; i < l1; i++)
        {
            char c1 = spanned.charAt(i);
            if(isSignChar(c1))
                return "";
            if(isDecimalPointChar(c1))
                k1 = i;
        }

        charsequence = null;
        i = j1 - 1;
        l = j;
        while(i >= i1) 
        {
            char c2 = charsequence2.charAt(i);
            boolean flag = false;
            int i2;
            int k2;
            if(isSignChar(c2))
            {
                if(i != i1 || k != 0)
                {
                    j = 1;
                    i2 = l;
                    k2 = k1;
                } else
                if(l >= 0)
                {
                    j = 1;
                    k2 = k1;
                    i2 = l;
                } else
                {
                    i2 = i;
                    k2 = k1;
                    j = ((flag) ? 1 : 0);
                }
            } else
            {
                k2 = k1;
                i2 = l;
                j = ((flag) ? 1 : 0);
                if(isDecimalPointChar(c2))
                    if(k1 >= 0)
                    {
                        j = 1;
                        k2 = k1;
                        i2 = l;
                    } else
                    {
                        k2 = i;
                        i2 = l;
                        j = ((flag) ? 1 : 0);
                    }
            }
            spanned = charsequence;
            if(j != 0)
            {
                if(j1 == i1 + 1)
                    return "";
                spanned = charsequence;
                if(charsequence == null)
                    spanned = new SpannableStringBuilder(charsequence2, i1, j1);
                spanned.delete(i - i1, (i + 1) - i1);
            }
            i--;
            k1 = k2;
            l = i2;
            charsequence = spanned;
        }
        if(charsequence != null)
            return charsequence;
        if(charsequence1 != null)
            return charsequence1;
        else
            return null;
    }

    protected char[] getAcceptedChars()
    {
        return mAccepted;
    }

    public int getInputType()
    {
        if(!mNeedsAdvancedInput) goto _L2; else goto _L1
_L1:
        int i = 1;
_L4:
        return i;
_L2:
        char c = '\002';
        if(mSign)
            c = '\u1002';
        i = c;
        if(mDecimal)
            i = c | 0x2000;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final char COMPATIBILITY_CHARACTERS[][] = {
        {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        }, {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
            '-', '+'
        }, {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
            '.'
        }, {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
            '-', '+', '.'
        }
    };
    private static final int DECIMAL = 2;
    private static final String DEFAULT_DECIMAL_POINT_CHARS = ".";
    private static final String DEFAULT_SIGN_CHARS = "-+";
    private static final char EN_DASH = 8211;
    private static final char HYPHEN_MINUS = 45;
    private static final char MINUS_SIGN = 8722;
    private static final int SIGN = 1;
    private static final Object sLocaleCacheLock = new Object();
    private static final HashMap sLocaleInstanceCache = new HashMap();
    private static final Object sStringCacheLock = new Object();
    private static final HashMap sStringInstanceCache = new HashMap();
    private char mAccepted[];
    private final boolean mDecimal;
    private String mDecimalPointChars;
    private final Locale mLocale;
    private boolean mNeedsAdvancedInput;
    private final boolean mSign;
    private String mSignChars;
    private final boolean mStringMode;

}
