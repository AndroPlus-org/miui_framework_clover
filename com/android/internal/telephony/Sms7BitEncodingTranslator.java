// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.telephony.*;
import android.util.SparseIntArray;
import com.android.internal.telephony.cdma.sms.UserData;
import com.android.internal.util.XmlUtils;

// Referenced classes of package com.android.internal.telephony:
//            GsmAlphabet

public class Sms7BitEncodingTranslator
{

    public Sms7BitEncodingTranslator()
    {
    }

    private static void load7BitTranslationTableFromXml()
    {
        Object obj;
        obj = Resources.getSystem();
        if(DBG)
            Rlog.d("Sms7BitEncodingTranslator", "load7BitTranslationTableFromXml: open normal file");
        obj = ((Resources) (obj)).getXml(0x1170011);
        XmlUtils.beginDocument(((org.xmlpull.v1.XmlPullParser) (obj)), "SmsEnforce7BitTranslationTable");
_L5:
        Object obj1;
        XmlUtils.nextElement(((org.xmlpull.v1.XmlPullParser) (obj)));
        obj1 = ((XmlResourceParser) (obj)).getName();
        if(DBG)
        {
            StringBuilder stringbuilder1 = JVM INSTR new #94  <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            Rlog.d("Sms7BitEncodingTranslator", stringbuilder1.append("tag: ").append(((String) (obj1))).toString());
        }
        if(!"TranslationType".equals(obj1)) goto _L2; else goto _L1
_L1:
        obj1 = ((XmlResourceParser) (obj)).getAttributeValue(null, "Type");
        if(DBG)
        {
            StringBuilder stringbuilder2 = JVM INSTR new #94  <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            Rlog.d("Sms7BitEncodingTranslator", stringbuilder2.append("type: ").append(((String) (obj1))).toString());
        }
        if(!((String) (obj1)).equals("common")) goto _L4; else goto _L3
_L3:
        mTranslationTable = mTranslationTableCommon;
          goto _L5
        obj1;
        Rlog.e("Sms7BitEncodingTranslator", "Got exception while loading 7BitTranslationTable file.", ((Throwable) (obj1)));
        if(obj instanceof XmlResourceParser)
            ((XmlResourceParser) (obj)).close();
_L6:
        return;
_L4:
        if(!((String) (obj1)).equals("gsm"))
            break MISSING_BLOCK_LABEL_203;
        mTranslationTable = mTranslationTableGSM;
          goto _L5
        obj1;
        if(obj instanceof XmlResourceParser)
            ((XmlResourceParser) (obj)).close();
        throw obj1;
label0:
        {
            if(!((String) (obj1)).equals("cdma"))
                break label0;
            mTranslationTable = mTranslationTableCDMA;
        }
          goto _L5
        StringBuilder stringbuilder3 = JVM INSTR new #94  <Class StringBuilder>;
        stringbuilder3.StringBuilder();
        Rlog.e("Sms7BitEncodingTranslator", stringbuilder3.append("Error Parsing 7BitTranslationTable: found incorrect type").append(((String) (obj1))).toString());
          goto _L5
_L2:
        int i;
        int j;
        if(!"Character".equals(obj1) || mTranslationTable == null)
            break MISSING_BLOCK_LABEL_378;
        i = ((XmlResourceParser) (obj)).getAttributeUnsignedIntValue(null, "from", -1);
        j = ((XmlResourceParser) (obj)).getAttributeUnsignedIntValue(null, "to", -1);
        if(i == -1 || j == -1)
            break MISSING_BLOCK_LABEL_367;
        if(DBG)
        {
            StringBuilder stringbuilder = JVM INSTR new #94  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Rlog.d("Sms7BitEncodingTranslator", stringbuilder.append("Loading mapping ").append(Integer.toHexString(i).toUpperCase()).append(" -> ").append(Integer.toHexString(j).toUpperCase()).toString());
        }
        mTranslationTable.put(i, j);
          goto _L5
        Rlog.d("Sms7BitEncodingTranslator", "Invalid translation table file format");
          goto _L5
        if(DBG)
            Rlog.d("Sms7BitEncodingTranslator", "load7BitTranslationTableFromXml: parsing successful, file loaded");
        if(obj instanceof XmlResourceParser)
            ((XmlResourceParser) (obj)).close();
          goto _L6
    }

    private static boolean noTranslationNeeded(char c, boolean flag)
    {
        boolean flag1 = false;
        if(flag)
        {
            flag = flag1;
            if(GsmAlphabet.isGsmSeptets(c))
            {
                flag = flag1;
                if(UserData.charToAscii.get(c, -1) != -1)
                    flag = true;
            }
            return flag;
        } else
        {
            return GsmAlphabet.isGsmSeptets(c);
        }
    }

    public static String translate(CharSequence charsequence)
    {
        if(charsequence == null)
        {
            Rlog.w("Sms7BitEncodingTranslator", "Null message can not be translated");
            return null;
        }
        int i = charsequence.length();
        if(i <= 0)
            return "";
        if(!mIs7BitTranslationTableLoaded)
        {
            mTranslationTableCommon = new SparseIntArray();
            mTranslationTableGSM = new SparseIntArray();
            mTranslationTableCDMA = new SparseIntArray();
            load7BitTranslationTableFromXml();
            mIs7BitTranslationTableLoaded = true;
        }
        char ac[];
        if(mTranslationTableCommon != null && mTranslationTableCommon.size() > 0 || mTranslationTableGSM != null && mTranslationTableGSM.size() > 0 || mTranslationTableCDMA != null && mTranslationTableCDMA.size() > 0)
        {
            ac = new char[i];
            boolean flag = useCdmaFormatForMoSms();
            for(int j = 0; j < i; j++)
                ac[j] = translateIfNeeded(charsequence.charAt(j), flag);

        } else
        {
            return null;
        }
        return String.valueOf(ac);
    }

    private static char translateIfNeeded(char c, boolean flag)
    {
        int i;
        int j;
        if(noTranslationNeeded(c, flag))
        {
            if(DBG)
                Rlog.v("Sms7BitEncodingTranslator", (new StringBuilder()).append("No translation needed for ").append(Integer.toHexString(c)).toString());
            return c;
        }
        i = -1;
        if(mTranslationTableCommon != null)
            i = mTranslationTableCommon.get(c, -1);
        j = i;
        if(i != -1) goto _L2; else goto _L1
_L1:
        if(!flag) goto _L4; else goto _L3
_L3:
        j = i;
        if(mTranslationTableCDMA != null)
            j = mTranslationTableCDMA.get(c, -1);
_L2:
        if(j != -1)
        {
            if(DBG)
                Rlog.v("Sms7BitEncodingTranslator", (new StringBuilder()).append(Integer.toHexString(c)).append(" (").append(c).append(")").append(" translated to ").append(Integer.toHexString(j)).append(" (").append((char)j).append(")").toString());
            return (char)j;
        }
        break; /* Loop/switch isn't completed */
_L4:
        j = i;
        if(mTranslationTableGSM != null)
            j = mTranslationTableGSM.get(c, -1);
        if(true) goto _L2; else goto _L5
_L5:
        if(DBG)
            Rlog.w("Sms7BitEncodingTranslator", (new StringBuilder()).append("No translation found for ").append(Integer.toHexString(c)).append("! Replacing for empty space").toString());
        return ' ';
    }

    private static boolean useCdmaFormatForMoSms()
    {
        if(!SmsManager.getDefault().isImsSmsSupported())
        {
            boolean flag;
            if(TelephonyManager.getDefault().getCurrentPhoneType() == 2)
                flag = true;
            else
                flag = false;
            return flag;
        } else
        {
            return "3gpp2".equals(SmsManager.getDefault().getImsSmsFormat());
        }
    }

    private static final boolean DBG;
    private static final String TAG = "Sms7BitEncodingTranslator";
    private static final String XML_CHARACTOR_TAG = "Character";
    private static final String XML_FROM_TAG = "from";
    private static final String XML_START_TAG = "SmsEnforce7BitTranslationTable";
    private static final String XML_TO_TAG = "to";
    private static final String XML_TRANSLATION_TYPE_TAG = "TranslationType";
    private static boolean mIs7BitTranslationTableLoaded = false;
    private static SparseIntArray mTranslationTable = null;
    private static SparseIntArray mTranslationTableCDMA = null;
    private static SparseIntArray mTranslationTableCommon = null;
    private static SparseIntArray mTranslationTableGSM = null;

    static 
    {
        DBG = Build.IS_DEBUGGABLE;
    }
}
