// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony;

import android.content.Intent;
import android.telephony.Rlog;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelephonyUtils
{

    private TelephonyUtils()
    {
    }

    public static boolean isOperatorConsideredNonRoaming(String s, String s1, String s2)
    {
        if(TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2))
            return false;
        Rlog.i("TelephonyUtils", (new StringBuilder()).append("isOperatorConsideredNonRoaming for ").append(s1).toString());
        return (s1.startsWith("404") || s1.startsWith("405")) && (s2.startsWith("404") || s2.startsWith("405"));
    }

    public static String pii(CharSequence charsequence, int i, int j)
    {
        int k;
        if(charsequence == null)
            k = 0;
        else
            k = charsequence.length();
        if(k != 0)
        {
            StringBuilder stringbuilder = new StringBuilder(k);
            int l = 0;
            while(l < k) 
            {
                char c1;
                if(l >= i && l < k - j)
                {
                    byte byte0 = 120;
                    c1 = byte0;
                } else
                {
                    char c = charsequence.charAt(l);
                    c1 = c;
                }
                stringbuilder.append(c1);
                l++;
            }
            return stringbuilder.toString();
        } else
        {
            return "";
        }
    }

    public static String pii(String s)
    {
        int i;
        if(s == null)
            i = 0;
        else
            i = s.length();
        if(i == 0)
            return "";
        if(i >= 15)
            return pii(((CharSequence) (s)), 6, 2);
        if(i >= 11)
            return pii(((CharSequence) (s)), 2, 2);
        if(i >= 6)
            return pii(((CharSequence) (s)), 0, 2);
        if(i >= 2)
            return pii(((CharSequence) (s)), 0, 1);
        else
            return pii(((CharSequence) (s)), 0, 0);
    }

    public static String piiIP(String s)
    {
        if(TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        Matcher matcher = Pattern.compile((new StringBuilder()).append("(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}").append("|").append("(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}").append("|").append("((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)").toString()).matcher(s);
_L9:
        String s1;
        if(!matcher.find())
            break MISSING_BLOCK_LABEL_189;
        s1 = matcher.group(0);
        if(s1 != null) goto _L4; else goto _L3
_L3:
        int i = 0;
_L7:
        if(i == 0)
            continue; /* Loop/switch isn't completed */
        Object obj;
        obj = JVM INSTR new #458 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        int j = 0;
_L6:
        if(j >= i - 1)
            break MISSING_BLOCK_LABEL_191;
        char c = s1.charAt(j + 1);
        if(c != '.' && c != ':')
            break; /* Loop/switch isn't completed */
        ((StringBuilder) (obj)).append('x');
_L8:
        j++;
        if(true) goto _L6; else goto _L5
_L4:
        i = s1.length();
          goto _L7
_L5:
        ((StringBuilder) (obj)).append(s1.charAt(j));
          goto _L8
        obj;
        Rlog.e("TelephonyUtils", (new StringBuilder()).append("piiIP e: ").append(obj).toString());
        return s;
        ((StringBuilder) (obj)).append('x');
        obj = s.replace(s1, ((StringBuilder) (obj)).toString());
        s = ((String) (obj));
        if(true) goto _L9; else goto _L2
_L2:
        return s;
    }

    public static void putDialConferenceExtra(Intent intent, boolean flag)
    {
        if(intent != null)
            intent.putExtra("org.codeaurora.extra.DIAL_CONFERENCE_URI", flag);
        else
            Rlog.e("TelephonyUtils", "putDialConferenceExtra intent==null");
    }

    public static void putVideoStateExtra(Intent intent, int i)
    {
        if(intent != null)
            intent.putExtra("android.telecom.extra.START_CALL_WITH_VIDEO_STATE", i);
        else
            Rlog.e("TelephonyUtils", "putVideoStateExtra intent==null");
    }

    private static final String LOG_TAG = "TelephonyUtils";
    private static final HashMap sNonRoamingMap;

    static 
    {
        sNonRoamingMap = new HashMap();
        sNonRoamingMap.put("40401", "India");
        sNonRoamingMap.put("40402", "India");
        sNonRoamingMap.put("40403", "India");
        sNonRoamingMap.put("40404", "India");
        sNonRoamingMap.put("40405", "India");
        sNonRoamingMap.put("40407", "India");
        sNonRoamingMap.put("40409", "India");
        sNonRoamingMap.put("40410", "India");
        sNonRoamingMap.put("40411", "India");
        sNonRoamingMap.put("40412", "India");
        sNonRoamingMap.put("40413", "India");
        sNonRoamingMap.put("40414", "India");
        sNonRoamingMap.put("40415", "India");
        sNonRoamingMap.put("40416", "India");
        sNonRoamingMap.put("40417", "India");
        sNonRoamingMap.put("40418", "India");
        sNonRoamingMap.put("40419", "India");
        sNonRoamingMap.put("40420", "India");
        sNonRoamingMap.put("40421", "India");
        sNonRoamingMap.put("40422", "India");
        sNonRoamingMap.put("40424", "India");
        sNonRoamingMap.put("40425", "India");
        sNonRoamingMap.put("40427", "India");
        sNonRoamingMap.put("40428", "India");
        sNonRoamingMap.put("40429", "India");
        sNonRoamingMap.put("40430", "India");
        sNonRoamingMap.put("40431", "India");
        sNonRoamingMap.put("40434", "India");
        sNonRoamingMap.put("40436", "India");
        sNonRoamingMap.put("40437", "India");
        sNonRoamingMap.put("40438", "India");
        sNonRoamingMap.put("40440", "India");
        sNonRoamingMap.put("40441", "India");
        sNonRoamingMap.put("40442", "India");
        sNonRoamingMap.put("40443", "India");
        sNonRoamingMap.put("40444", "India");
        sNonRoamingMap.put("40445", "India");
        sNonRoamingMap.put("40446", "India");
        sNonRoamingMap.put("40448", "India");
        sNonRoamingMap.put("40449", "India");
        sNonRoamingMap.put("40450", "India");
        sNonRoamingMap.put("40451", "India");
        sNonRoamingMap.put("40452", "India");
        sNonRoamingMap.put("40453", "India");
        sNonRoamingMap.put("40454", "India");
        sNonRoamingMap.put("40455", "India");
        sNonRoamingMap.put("40456", "India");
        sNonRoamingMap.put("40457", "India");
        sNonRoamingMap.put("40458", "India");
        sNonRoamingMap.put("40459", "India");
        sNonRoamingMap.put("40460", "India");
        sNonRoamingMap.put("40462", "India");
        sNonRoamingMap.put("40464", "India");
        sNonRoamingMap.put("40466", "India");
        sNonRoamingMap.put("40467", "India");
        sNonRoamingMap.put("40468", "India");
        sNonRoamingMap.put("40469", "India");
        sNonRoamingMap.put("40470", "India");
        sNonRoamingMap.put("40471", "India");
        sNonRoamingMap.put("40472", "India");
        sNonRoamingMap.put("40473", "India");
        sNonRoamingMap.put("40474", "India");
        sNonRoamingMap.put("40475", "India");
        sNonRoamingMap.put("40476", "India");
        sNonRoamingMap.put("40477", "India");
        sNonRoamingMap.put("40478", "India");
        sNonRoamingMap.put("40479", "India");
        sNonRoamingMap.put("40480", "India");
        sNonRoamingMap.put("40481", "India");
        sNonRoamingMap.put("40482", "India");
        sNonRoamingMap.put("40483", "India");
        sNonRoamingMap.put("40484", "India");
        sNonRoamingMap.put("40485", "India");
        sNonRoamingMap.put("40486", "India");
        sNonRoamingMap.put("40487", "India");
        sNonRoamingMap.put("40488", "India");
        sNonRoamingMap.put("40489", "India");
        sNonRoamingMap.put("40490", "India");
        sNonRoamingMap.put("40491", "India");
        sNonRoamingMap.put("40492", "India");
        sNonRoamingMap.put("40493", "India");
        sNonRoamingMap.put("40494", "India");
        sNonRoamingMap.put("40495", "India");
        sNonRoamingMap.put("40496", "India");
        sNonRoamingMap.put("40497", "India");
        sNonRoamingMap.put("40498", "India");
        sNonRoamingMap.put("40501", "India");
        sNonRoamingMap.put("40503", "India");
        sNonRoamingMap.put("40504", "India");
        sNonRoamingMap.put("40505", "India");
        sNonRoamingMap.put("40506", "India");
        sNonRoamingMap.put("40507", "India");
        sNonRoamingMap.put("40508", "India");
        sNonRoamingMap.put("40509", "India");
        sNonRoamingMap.put("40510", "India");
        sNonRoamingMap.put("40511", "India");
        sNonRoamingMap.put("40512", "India");
        sNonRoamingMap.put("40513", "India");
        sNonRoamingMap.put("40514", "India");
        sNonRoamingMap.put("40515", "India");
        sNonRoamingMap.put("40517", "India");
        sNonRoamingMap.put("40518", "India");
        sNonRoamingMap.put("40519", "India");
        sNonRoamingMap.put("40520", "India");
        sNonRoamingMap.put("40521", "India");
        sNonRoamingMap.put("40522", "India");
        sNonRoamingMap.put("40523", "India");
        sNonRoamingMap.put("40525", "India");
        sNonRoamingMap.put("40526", "India");
        sNonRoamingMap.put("40527", "India");
        sNonRoamingMap.put("40528", "India");
        sNonRoamingMap.put("40529", "India");
        sNonRoamingMap.put("40530", "India");
        sNonRoamingMap.put("40531", "India");
        sNonRoamingMap.put("40532", "India");
        sNonRoamingMap.put("40533", "India");
        sNonRoamingMap.put("40534", "India");
        sNonRoamingMap.put("40535", "India");
        sNonRoamingMap.put("40536", "India");
        sNonRoamingMap.put("40537", "India");
        sNonRoamingMap.put("40538", "India");
        sNonRoamingMap.put("40539", "India");
        sNonRoamingMap.put("40541", "India");
        sNonRoamingMap.put("40542", "India");
        sNonRoamingMap.put("40543", "India");
        sNonRoamingMap.put("40544", "India");
        sNonRoamingMap.put("40545", "India");
        sNonRoamingMap.put("40546", "India");
        sNonRoamingMap.put("40547", "India");
        sNonRoamingMap.put("40551", "India");
        sNonRoamingMap.put("40552", "India");
        sNonRoamingMap.put("40553", "India");
        sNonRoamingMap.put("40554", "India");
        sNonRoamingMap.put("40555", "India");
        sNonRoamingMap.put("40556", "India");
        sNonRoamingMap.put("40566", "India");
        sNonRoamingMap.put("40570", "India");
        sNonRoamingMap.put("405750", "India");
        sNonRoamingMap.put("405751", "India");
        sNonRoamingMap.put("405752", "India");
        sNonRoamingMap.put("405753", "India");
        sNonRoamingMap.put("405754", "India");
        sNonRoamingMap.put("405755", "India");
        sNonRoamingMap.put("405756", "India");
        sNonRoamingMap.put("405799", "India");
        sNonRoamingMap.put("405800", "India");
        sNonRoamingMap.put("405801", "India");
        sNonRoamingMap.put("405802", "India");
        sNonRoamingMap.put("405803", "India");
        sNonRoamingMap.put("405804", "India");
        sNonRoamingMap.put("405805", "India");
        sNonRoamingMap.put("405806", "India");
        sNonRoamingMap.put("405807", "India");
        sNonRoamingMap.put("405808", "India");
        sNonRoamingMap.put("405809", "India");
        sNonRoamingMap.put("405810", "India");
        sNonRoamingMap.put("405811", "India");
        sNonRoamingMap.put("405812", "India");
        sNonRoamingMap.put("405819", "India");
        sNonRoamingMap.put("405818", "India");
        sNonRoamingMap.put("405820", "India");
        sNonRoamingMap.put("405821", "India");
        sNonRoamingMap.put("405822", "India");
        sNonRoamingMap.put("405824", "India");
        sNonRoamingMap.put("405827", "India");
        sNonRoamingMap.put("405834", "India");
        sNonRoamingMap.put("405844", "India");
        sNonRoamingMap.put("405845", "India");
        sNonRoamingMap.put("405846", "India");
        sNonRoamingMap.put("405847", "India");
        sNonRoamingMap.put("405848", "India");
        sNonRoamingMap.put("405849", "India");
        sNonRoamingMap.put("405850", "India");
        sNonRoamingMap.put("405851", "India");
        sNonRoamingMap.put("405852", "India");
        sNonRoamingMap.put("405853", "India");
        sNonRoamingMap.put("405854", "India");
        sNonRoamingMap.put("405855", "India");
        sNonRoamingMap.put("405856", "India");
        sNonRoamingMap.put("405857", "India");
        sNonRoamingMap.put("405858", "India");
        sNonRoamingMap.put("405859", "India");
        sNonRoamingMap.put("405860", "India");
        sNonRoamingMap.put("405861", "India");
        sNonRoamingMap.put("405862", "India");
        sNonRoamingMap.put("405863", "India");
        sNonRoamingMap.put("405864", "India");
        sNonRoamingMap.put("405865", "India");
        sNonRoamingMap.put("405866", "India");
        sNonRoamingMap.put("405867", "India");
        sNonRoamingMap.put("405868", "India");
        sNonRoamingMap.put("405869", "India");
        sNonRoamingMap.put("405870", "India");
        sNonRoamingMap.put("405871", "India");
        sNonRoamingMap.put("405872", "India");
        sNonRoamingMap.put("405873", "India");
        sNonRoamingMap.put("405874", "India");
        sNonRoamingMap.put("405875", "India");
        sNonRoamingMap.put("405880", "India");
        sNonRoamingMap.put("405881", "India");
        sNonRoamingMap.put("405908", "India");
        sNonRoamingMap.put("405909", "India");
        sNonRoamingMap.put("405910", "India");
        sNonRoamingMap.put("405911", "India");
        sNonRoamingMap.put("405912", "India");
        sNonRoamingMap.put("405913", "India");
        sNonRoamingMap.put("405914", "India");
        sNonRoamingMap.put("405917", "India");
        sNonRoamingMap.put("405927", "India");
        sNonRoamingMap.put("405929", "India");
        sNonRoamingMap.put("40475", "India");
        sNonRoamingMap.put("40451", "India");
        sNonRoamingMap.put("40458", "India");
        sNonRoamingMap.put("40481", "India");
        sNonRoamingMap.put("40474", "India");
        sNonRoamingMap.put("40438", "India");
        sNonRoamingMap.put("40457", "India");
        sNonRoamingMap.put("40480", "India");
        sNonRoamingMap.put("40473", "India");
        sNonRoamingMap.put("40434", "India");
        sNonRoamingMap.put("40466", "India");
        sNonRoamingMap.put("40455", "India");
        sNonRoamingMap.put("40472", "India");
        sNonRoamingMap.put("40477", "India");
        sNonRoamingMap.put("40464", "India");
        sNonRoamingMap.put("40454", "India");
        sNonRoamingMap.put("40471", "India");
        sNonRoamingMap.put("40476", "India");
        sNonRoamingMap.put("40462", "India");
        sNonRoamingMap.put("40453", "India");
        sNonRoamingMap.put("40459", "India");
    }
}
