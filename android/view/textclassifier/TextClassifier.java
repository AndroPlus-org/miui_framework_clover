// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textclassifier;

import android.os.LocaleList;

// Referenced classes of package android.view.textclassifier:
//            LinksInfo, TextClassifierConstants, TextClassification, TextSelection

public interface TextClassifier
{

    public abstract TextClassification classifyText(CharSequence charsequence, int i, int j, LocaleList localelist);

    public LinksInfo getLinks(CharSequence charsequence, int i, LocaleList localelist)
    {
        return LinksInfo.NO_OP;
    }

    public TextClassifierConstants getSettings()
    {
        return TextClassifierConstants.DEFAULT;
    }

    public void logEvent(String s, String s1)
    {
    }

    public abstract TextSelection suggestSelection(CharSequence charsequence, int i, int j, LocaleList localelist);

    public static final String DEFAULT_LOG_TAG = "TextClassifierImpl";
    public static final TextClassifier NO_OP = new TextClassifier() {

        public TextClassification classifyText(CharSequence charsequence, int i, int j, LocaleList localelist)
        {
            return TextClassification.EMPTY;
        }

        public TextSelection suggestSelection(CharSequence charsequence, int i, int j, LocaleList localelist)
        {
            return (new TextSelection.Builder(i, j)).build();
        }

    }
;
    public static final String TYPE_ADDRESS = "address";
    public static final String TYPE_EMAIL = "email";
    public static final String TYPE_OTHER = "other";
    public static final String TYPE_PHONE = "phone";
    public static final String TYPE_UNKNOWN = "";
    public static final String TYPE_URL = "url";

}
