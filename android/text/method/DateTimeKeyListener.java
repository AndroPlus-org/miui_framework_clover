// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import com.android.internal.util.ArrayUtils;
import java.util.*;

// Referenced classes of package android.text.method:
//            NumberKeyListener

public class DateTimeKeyListener extends NumberKeyListener
{

    public DateTimeKeyListener()
    {
        this(null);
    }

    public DateTimeKeyListener(Locale locale)
    {
        LinkedHashSet linkedhashset = new LinkedHashSet();
        boolean flag;
        if(NumberKeyListener.addDigits(linkedhashset, locale) && NumberKeyListener.addAmPmChars(linkedhashset, locale) && NumberKeyListener.addFormatCharsFromSkeleton(linkedhashset, locale, "yMdhms", "yMLdahHKkms"))
            flag = NumberKeyListener.addFormatCharsFromSkeleton(linkedhashset, locale, "yMdHms", "yMLdahHKkms");
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

    public static DateTimeKeyListener getInstance()
    {
        return getInstance(null);
    }

    public static DateTimeKeyListener getInstance(Locale locale)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        DateTimeKeyListener datetimekeylistener = (DateTimeKeyListener)sInstanceCache.get(locale);
        DateTimeKeyListener datetimekeylistener1;
        datetimekeylistener1 = datetimekeylistener;
        if(datetimekeylistener != null)
            break MISSING_BLOCK_LABEL_41;
        datetimekeylistener1 = JVM INSTR new #2   <Class DateTimeKeyListener>;
        datetimekeylistener1.DateTimeKeyListener(locale);
        sInstanceCache.put(locale, datetimekeylistener1);
        obj;
        JVM INSTR monitorexit ;
        return datetimekeylistener1;
        locale;
        throw locale;
    }

    protected char[] getAcceptedChars()
    {
        return mCharacters;
    }

    public int getInputType()
    {
        return !mNeedsAdvancedInput ? 4 : 1;
    }

    public static final char CHARACTERS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'm', 'p', ':', '/', '-', ' '
    };
    private static final String SKELETON_12HOUR = "yMdhms";
    private static final String SKELETON_24HOUR = "yMdHms";
    private static final String SYMBOLS_TO_IGNORE = "yMLdahHKkms";
    private static final HashMap sInstanceCache = new HashMap();
    private static final Object sLock = new Object();
    private final char mCharacters[];
    private final boolean mNeedsAdvancedInput;

}
