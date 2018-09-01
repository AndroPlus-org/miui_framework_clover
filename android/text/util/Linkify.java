// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.util;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Patterns;
import android.webkit.WebView;
import android.widget.TextView;
import com.android.i18n.phonenumbers.PhoneNumberMatch;
import com.android.i18n.phonenumbers.PhoneNumberUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import libcore.util.EmptyArray;

// Referenced classes of package android.text.util:
//            LinkSpec

public class Linkify
{
    public static interface MatchFilter
    {

        public abstract boolean acceptMatch(CharSequence charsequence, int i, int j);
    }

    public static interface TransformFilter
    {

        public abstract String transformUrl(Matcher matcher, String s);
    }


    public Linkify()
    {
    }

    private static final void addLinkMovementMethod(TextView textview)
    {
        android.text.method.MovementMethod movementmethod = textview.getMovementMethod();
        if((movementmethod == null || (movementmethod instanceof LinkMovementMethod) ^ true) && textview.getLinksClickable())
            textview.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static final void addLinks(TextView textview, Pattern pattern, String s)
    {
        addLinks(textview, pattern, s, null, null, null);
    }

    public static final void addLinks(TextView textview, Pattern pattern, String s, MatchFilter matchfilter, TransformFilter transformfilter)
    {
        addLinks(textview, pattern, s, null, matchfilter, transformfilter);
    }

    public static final void addLinks(TextView textview, Pattern pattern, String s, String as[], MatchFilter matchfilter, TransformFilter transformfilter)
    {
        SpannableString spannablestring = SpannableString.valueOf(textview.getText());
        if(addLinks(((Spannable) (spannablestring)), pattern, s, as, matchfilter, transformfilter))
        {
            textview.setText(spannablestring);
            addLinkMovementMethod(textview);
        }
    }

    public static final boolean addLinks(Spannable spannable, int i)
    {
        return addLinks(spannable, i, ((Context) (null)));
    }

    private static boolean addLinks(Spannable spannable, int i, Context context)
    {
        if(i == 0)
            return false;
        URLSpan aurlspan[] = (URLSpan[])spannable.getSpans(0, spannable.length(), android/text/style/URLSpan);
        for(int j = aurlspan.length - 1; j >= 0; j--)
            spannable.removeSpan(aurlspan[j]);

        Object obj = new ArrayList();
        if((i & 1) != 0)
        {
            Pattern pattern = Patterns.AUTOLINK_WEB_URL;
            MatchFilter matchfilter = sUrlMatchFilter;
            gatherLinks(((ArrayList) (obj)), spannable, pattern, new String[] {
                "http://", "https://", "rtsp://"
            }, matchfilter, null);
        }
        if((i & 2) != 0)
            gatherLinks(((ArrayList) (obj)), spannable, Patterns.AUTOLINK_EMAIL_ADDRESS, new String[] {
                "mailto:"
            }, null, null);
        if((i & 4) != 0)
            gatherTelLinks(((ArrayList) (obj)), spannable, context);
        if((i & 8) != 0)
            gatherMapLinks(((ArrayList) (obj)), spannable);
        pruneOverlaps(((ArrayList) (obj)));
        if(((ArrayList) (obj)).size() == 0)
            return false;
        for(context = ((Iterable) (obj)).iterator(); context.hasNext(); applyLink(((LinkSpec) (obj)).url, ((LinkSpec) (obj)).start, ((LinkSpec) (obj)).end, spannable))
            obj = (LinkSpec)context.next();

        return true;
    }

    public static final boolean addLinks(Spannable spannable, Pattern pattern, String s)
    {
        return addLinks(spannable, pattern, s, null, null, null);
    }

    public static final boolean addLinks(Spannable spannable, Pattern pattern, String s, MatchFilter matchfilter, TransformFilter transformfilter)
    {
        return addLinks(spannable, pattern, s, null, matchfilter, transformfilter);
    }

    public static final boolean addLinks(Spannable spannable, Pattern pattern, String s, String as[], MatchFilter matchfilter, TransformFilter transformfilter)
    {
        String s1;
label0:
        {
            s1 = s;
            if(s == null)
                s1 = "";
            if(as != null)
            {
                s = as;
                if(as.length >= 1)
                    break label0;
            }
            s = EmptyArray.STRING;
        }
        String as1[] = new String[s.length + 1];
        as1[0] = s1.toLowerCase(Locale.ROOT);
        int i = 0;
        while(i < s.length) 
        {
            as = s[i];
            if(as == null)
                as = "";
            else
                as = as.toLowerCase(Locale.ROOT);
            as1[i + 1] = as;
            i++;
        }
        boolean flag = false;
        pattern = pattern.matcher(spannable);
        do
        {
            if(!pattern.find())
                break;
            int j = pattern.start();
            int k = pattern.end();
            boolean flag1 = true;
            if(matchfilter != null)
                flag1 = matchfilter.acceptMatch(spannable, j, k);
            if(flag1)
            {
                applyLink(makeUrl(pattern.group(0), as1, pattern, transformfilter), j, k, spannable);
                flag = true;
            }
        } while(true);
        return flag;
    }

    public static final boolean addLinks(TextView textview, int i)
    {
        if(i == 0)
            return false;
        Context context = textview.getContext();
        Object obj = textview.getText();
        if(obj instanceof Spannable)
            if(addLinks((Spannable)obj, i, context))
            {
                addLinkMovementMethod(textview);
                return true;
            } else
            {
                return false;
            }
        obj = SpannableString.valueOf(((CharSequence) (obj)));
        if(addLinks(((Spannable) (obj)), i, context))
        {
            addLinkMovementMethod(textview);
            textview.setText(((CharSequence) (obj)));
            return true;
        } else
        {
            return false;
        }
    }

    private static final void applyLink(String s, int i, int j, Spannable spannable)
    {
        spannable.setSpan(new URLSpan(s), i, j, 33);
    }

    private static final void gatherLinks(ArrayList arraylist, Spannable spannable, Pattern pattern, String as[], MatchFilter matchfilter, TransformFilter transformfilter)
    {
        Matcher matcher = pattern.matcher(spannable);
        do
        {
            if(!matcher.find())
                break;
            int i = matcher.start();
            int j = matcher.end();
            if(matchfilter == null || matchfilter.acceptMatch(spannable, i, j))
            {
                pattern = new LinkSpec();
                pattern.url = makeUrl(matcher.group(0), as, matcher, transformfilter);
                pattern.start = i;
                pattern.end = j;
                arraylist.add(pattern);
            }
        } while(true);
    }

    private static final void gatherMapLinks(ArrayList arraylist, Spannable spannable)
    {
        int i;
        spannable = spannable.toString();
        i = 0;
_L2:
        Object obj;
        int j;
        LinkSpec linkspec;
        int k;
        String s;
        try
        {
            obj = WebView.findAddress(spannable);
        }
        // Misplaced declaration of an exception variable
        catch(ArrayList arraylist)
        {
            return;
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_30;
        j = spannable.indexOf(((String) (obj)));
        if(j >= 0)
            break MISSING_BLOCK_LABEL_31;
        return;
        linkspec = JVM INSTR new #177 <Class LinkSpec>;
        linkspec.LinkSpec();
        k = j + ((String) (obj)).length();
        linkspec.start = i + j;
        linkspec.end = i + k;
        spannable = spannable.substring(k);
        i += k;
        s = URLEncoder.encode(((String) (obj)), "UTF-8");
        obj = JVM INSTR new #289 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        linkspec.url = ((StringBuilder) (obj)).append("geo:0,0?q=").append(s).toString();
        arraylist.add(linkspec);
        continue; /* Loop/switch isn't completed */
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static void gatherTelLinks(ArrayList arraylist, Spannable spannable, Context context)
    {
        Object obj = PhoneNumberUtil.getInstance();
        if(context == null)
            context = TelephonyManager.getDefault();
        else
            context = TelephonyManager.from(context);
        for(context = ((PhoneNumberUtil) (obj)).findNumbers(spannable.toString(), context.getSimCountryIso().toUpperCase(Locale.US), com.android.i18n.phonenumbers.PhoneNumberUtil.Leniency.POSSIBLE, 0x7fffffffffffffffL).iterator(); context.hasNext(); arraylist.add(obj))
        {
            spannable = (PhoneNumberMatch)context.next();
            obj = new LinkSpec();
            obj.url = (new StringBuilder()).append("tel:").append(PhoneNumberUtils.normalizeNumber(spannable.rawString())).toString();
            obj.start = spannable.start();
            obj.end = spannable.end();
        }

    }

    private static final String makeUrl(String s, String as[], Matcher matcher, TransformFilter transformfilter)
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
                {
                    matcher = s;
                    if(as.length > 0)
                        matcher = (new StringBuilder()).append(as[0]).append(s).toString();
                }
                return matcher;
            }
            i++;
        } while(true);
    }

    private static final void pruneOverlaps(ArrayList arraylist)
    {
        int i;
        int j;
        Collections.sort(arraylist, new Comparator() {

            public final int compare(LinkSpec linkspec2, LinkSpec linkspec3)
            {
                if(linkspec2.start < linkspec3.start)
                    return -1;
                if(linkspec2.start > linkspec3.start)
                    return 1;
                if(linkspec2.end < linkspec3.end)
                    return 1;
                return linkspec2.end <= linkspec3.end ? 0 : -1;
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((LinkSpec)obj, (LinkSpec)obj1);
            }

        }
);
        i = arraylist.size();
        j = 0;
_L7:
        LinkSpec linkspec;
        LinkSpec linkspec1;
        int k;
        if(j >= i - 1)
            break; /* Loop/switch isn't completed */
        linkspec = (LinkSpec)arraylist.get(j);
        linkspec1 = (LinkSpec)arraylist.get(j + 1);
        k = -1;
        if(linkspec.start > linkspec1.start || linkspec.end <= linkspec1.start) goto _L2; else goto _L1
_L1:
        if(linkspec1.end > linkspec.end) goto _L4; else goto _L3
_L3:
        k = j + 1;
_L5:
        if(k == -1)
            break; /* Loop/switch isn't completed */
        arraylist.remove(k);
        i--;
        continue; /* Loop/switch isn't completed */
_L4:
        if(linkspec.end - linkspec.start > linkspec1.end - linkspec1.start)
            k = j + 1;
        else
        if(linkspec.end - linkspec.start < linkspec1.end - linkspec1.start)
            k = j;
        if(true) goto _L5; else goto _L2
_L2:
        j++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static final int ALL = 15;
    public static final int EMAIL_ADDRESSES = 2;
    public static final int MAP_ADDRESSES = 8;
    public static final int PHONE_NUMBERS = 4;
    private static final int PHONE_NUMBER_MINIMUM_DIGITS = 5;
    public static final int WEB_URLS = 1;
    public static final MatchFilter sPhoneNumberMatchFilter = new MatchFilter() {

        public final boolean acceptMatch(CharSequence charsequence, int i, int j)
        {
            int k = 0;
            for(int l = i; l < j;)
            {
                i = k;
                if(Character.isDigit(charsequence.charAt(l)))
                {
                    i = ++k;
                    if(k >= 5)
                        return true;
                }
                l++;
                k = i;
            }

            return false;
        }

    }
;
    public static final TransformFilter sPhoneNumberTransformFilter = new TransformFilter() {

        public final String transformUrl(Matcher matcher, String s)
        {
            return Patterns.digitsAndPlusOnly(matcher);
        }

    }
;
    public static final MatchFilter sUrlMatchFilter = new MatchFilter() {

        public final boolean acceptMatch(CharSequence charsequence, int i, int j)
        {
            if(i == 0)
                return true;
            return charsequence.charAt(i - 1) != '@';
        }

    }
;

}
