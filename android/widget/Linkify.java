// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.telephony.PhoneNumberUtils;
import com.android.i18n.phonenumbers.PhoneNumberMatch;
import com.android.i18n.phonenumbers.PhoneNumberUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import miui.util.Patterns;

// Referenced classes of package android.widget:
//            LinkSpec

public class Linkify
{

    public Linkify()
    {
    }

    private static final void gatherLinks(ArrayList arraylist, CharSequence charsequence, int i, int j, Pattern pattern, String as[], android.text.util.MatchFilter matchfilter, android.text.util.TransformFilter transformfilter)
    {
        Matcher matcher = pattern.matcher(charsequence.subSequence(i, j));
        do
        {
            if(!matcher.find())
                break;
            j = matcher.start();
            i = matcher.end();
            if(matchfilter == null || matchfilter.acceptMatch(charsequence, j, i))
            {
                pattern = new LinkSpec();
                pattern.url = makeUrl(matcher.group(0), as, matcher, transformfilter);
                pattern.start = j;
                pattern.end = i;
                arraylist.add(pattern);
            }
        } while(true);
    }

    private static final void gatherTelLinks(ArrayList arraylist, CharSequence charsequence, int i, int j, boolean flag)
    {
        int k;
        int l;
        if(flag)
        {
            k = i;
            l = i;
        } else
        {
            k = i - 1;
            l = i;
        }
        for(; k >= 0 && Character.isDigit(charsequence.charAt(k)); k--)
            l = k;

        if(flag)
            i = j - 1;
        else
            i = j;
        for(; i >= 0 && i < charsequence.length() && Character.isDigit(charsequence.charAt(i)); i++)
            j = i + 1;

        if(j - l < 5)
            return;
        for(Iterator iterator = PhoneNumberUtil.getInstance().findNumbers(charsequence.subSequence(l, j), Locale.getDefault().getCountry(), com.android.i18n.phonenumbers.PhoneNumberUtil.Leniency.POSSIBLE, 0x7fffffffffffffffL).iterator(); iterator.hasNext(); arraylist.add(charsequence))
        {
            PhoneNumberMatch phonenumbermatch = (PhoneNumberMatch)iterator.next();
            charsequence = new LinkSpec();
            charsequence.url = (new StringBuilder()).append("tel:").append(PhoneNumberUtils.normalizeNumber(phonenumbermatch.rawString())).toString();
            charsequence.start = phonenumbermatch.start();
            charsequence.end = phonenumbermatch.end();
        }

    }

    private static final void gatherWebLinks(ArrayList arraylist, CharSequence charsequence, int i, int j, boolean flag)
    {
        int k;
        int l;
        if(flag)
        {
            k = i;
            l = i;
        } else
        {
            k = i - 1;
            l = i;
        }
        for(; k >= 0 && isValidChar(charsequence.charAt(k), WEB_CHAR_PATTERN); k--)
            l = k;

        if(flag)
            i = j - 1;
        else
            i = j;
        for(; i >= 0 && i < charsequence.length() && isValidChar(charsequence.charAt(i), WEB_CHAR_PATTERN); i++)
            j = i + 1;

        Pattern pattern = Patterns.WEB_URL;
        android.text.util.MatchFilter matchfilter = sUrlMatchFilter;
        gatherLinks(arraylist, charsequence, l, j, pattern, new String[] {
            "http://", "https://", "rtsp://"
        }, matchfilter, null);
    }

    static final ArrayList getLinks(CharSequence charsequence, int i, int j, int k)
    {
        while(i == -1 || j == -1 || i > j) 
            return null;
        ArrayList arraylist = new ArrayList();
        boolean flag;
        if(i != j)
            flag = true;
        else
            flag = false;
        if((k & 1) != 0)
            gatherWebLinks(arraylist, charsequence, i, j, flag);
        if((k & 4) != 0)
            gatherTelLinks(arraylist, charsequence, i, j, flag);
        return arraylist;
    }

    private static boolean isValidChar(char c, Pattern pattern)
    {
        return pattern.matcher(String.valueOf(c)).matches();
    }

    private static final String makeUrl(String s, String as[], Matcher matcher, android.text.util.TransformFilter transformfilter)
    {
        String s1 = s;
        if(transformfilter != null)
            s1 = transformfilter.transformUrl(matcher, s);
        boolean flag = false;
        int i = 0;
        do
        {
label0:
            {
                boolean flag2 = flag;
                s = s1;
                if(i < as.length)
                {
                    if(!s1.regionMatches(true, 0, as[i], 0, as[i].length()))
                        break label0;
                    boolean flag1 = true;
                    flag2 = flag1;
                    s = s1;
                    if(!s1.regionMatches(false, 0, as[i], 0, as[i].length()))
                    {
                        s = (new StringBuilder()).append(as[i]).append(s1.substring(as[i].length())).toString();
                        flag2 = flag1;
                    }
                }
                matcher = s;
                if(!flag2)
                    matcher = (new StringBuilder()).append(as[0]).append(s).toString();
                return matcher;
            }
            i++;
        } while(true);
    }

    public static final int PHONE_NUMBERS = 4;
    private static final int PHONE_NUMBER_MINIMUM_DIGITS = 5;
    private static final Pattern WEB_CHAR_PATTERN = Pattern.compile("[a-zA-Z0-9\\.]");
    public static final int WEB_URLS = 1;
    public static final android.text.util.MatchFilter sUrlMatchFilter;

    static 
    {
        sUrlMatchFilter = android.text.util.Linkify.sUrlMatchFilter;
    }
}
