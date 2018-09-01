// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import com.android.internal.util.ArrayUtils;
import java.util.*;

// Referenced classes of package android.text.method:
//            NumberKeyListener

public class TimeKeyListener extends NumberKeyListener
{

    public TimeKeyListener()
    {
        this(null);
    }

    public TimeKeyListener(Locale locale)
    {
        LinkedHashSet linkedhashset = new LinkedHashSet();
        boolean flag;
        if(NumberKeyListener.addDigits(linkedhashset, locale) && NumberKeyListener.addAmPmChars(linkedhashset, locale) && NumberKeyListener.addFormatCharsFromSkeleton(linkedhashset, locale, "hms", "ahHKkms"))
            flag = NumberKeyListener.addFormatCharsFromSkeleton(linkedhashset, locale, "Hms", "ahHKkms");
        else
            flag = false;
        if(flag)
        {
            mCharacters = NumberKeyListener.collectionToArray(linkedhashset);
            if(locale != null && "en".equals(locale.getLanguage()))
                mNeedsAdvancedInput = false;
            else
                mNeedsAdvancedInput = ArrayUtils.containsAll(CHARACTERS, mCharacters) ^ true;
        } else
        {
            mCharacters = CHARACTERS;
            mNeedsAdvancedInput = false;
        }
    }

    public static TimeKeyListener getInstance()
    {
        return getInstance(null);
    }

    public static TimeKeyListener getInstance(Locale locale)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        TimeKeyListener timekeylistener = (TimeKeyListener)sInstanceCache.get(locale);
        TimeKeyListener timekeylistener1;
        timekeylistener1 = timekeylistener;
        if(timekeylistener != null)
            break MISSING_BLOCK_LABEL_41;
        timekeylistener1 = JVM INSTR new #2   <Class TimeKeyListener>;
        timekeylistener1.TimeKeyListener(locale);
        sInstanceCache.put(locale, timekeylistener1);
        obj;
        JVM INSTR monitorexit ;
        return timekeylistener1;
        locale;
        throw locale;
    }

    protected char[] getAcceptedChars()
    {
        return mCharacters;
    }

    public int getInputType()
    {
        return !mNeedsAdvancedInput ? 36 : 1;
    }

    public static final char CHARACTERS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'm', 'p', ':'
    };
    private static final String SKELETON_12HOUR = "hms";
    private static final String SKELETON_24HOUR = "Hms";
    private static final String SYMBOLS_TO_IGNORE = "ahHKkms";
    private static final HashMap sInstanceCache = new HashMap();
    private static final Object sLock = new Object();
    private final char mCharacters[];
    private final boolean mNeedsAdvancedInput;

}
