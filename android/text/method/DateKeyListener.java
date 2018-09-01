// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import com.android.internal.util.ArrayUtils;
import java.util.*;

// Referenced classes of package android.text.method:
//            NumberKeyListener

public class DateKeyListener extends NumberKeyListener
{

    public DateKeyListener()
    {
        this(null);
    }

    public DateKeyListener(Locale locale)
    {
        LinkedHashSet linkedhashset = new LinkedHashSet();
        boolean flag;
        if(NumberKeyListener.addDigits(linkedhashset, locale))
            flag = NumberKeyListener.addFormatCharsFromSkeletons(linkedhashset, locale, SKELETONS, "yMLd");
        else
            flag = false;
        if(flag)
        {
            mCharacters = NumberKeyListener.collectionToArray(linkedhashset);
            mNeedsAdvancedInput = ArrayUtils.containsAll(CHARACTERS, mCharacters) ^ true;
        } else
        {
            mCharacters = CHARACTERS;
            mNeedsAdvancedInput = false;
        }
    }

    public static DateKeyListener getInstance()
    {
        return getInstance(null);
    }

    public static DateKeyListener getInstance(Locale locale)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        DateKeyListener datekeylistener = (DateKeyListener)sInstanceCache.get(locale);
        DateKeyListener datekeylistener1;
        datekeylistener1 = datekeylistener;
        if(datekeylistener != null)
            break MISSING_BLOCK_LABEL_41;
        datekeylistener1 = JVM INSTR new #2   <Class DateKeyListener>;
        datekeylistener1.DateKeyListener(locale);
        sInstanceCache.put(locale, datekeylistener1);
        obj;
        JVM INSTR monitorexit ;
        return datekeylistener1;
        locale;
        throw locale;
    }

    protected char[] getAcceptedChars()
    {
        return mCharacters;
    }

    public int getInputType()
    {
        return !mNeedsAdvancedInput ? 20 : 1;
    }

    public static final char CHARACTERS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '/', '-', '.'
    };
    private static final String SKELETONS[] = {
        "yMd", "yM", "Md"
    };
    private static final String SYMBOLS_TO_IGNORE = "yMLd";
    private static final HashMap sInstanceCache = new HashMap();
    private static final Object sLock = new Object();
    private final char mCharacters[];
    private final boolean mNeedsAdvancedInput;

}
