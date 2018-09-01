// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textclassifier;

import android.util.KeyValueListParser;
import android.util.Slog;

public final class TextClassifierConstants
{

    private TextClassifierConstants()
    {
        mDarkLaunch = false;
        mSuggestSelectionEnabledForEditableText = true;
    }

    private TextClassifierConstants(String s)
    {
        KeyValueListParser keyvaluelistparser = new KeyValueListParser(',');
        try
        {
            keyvaluelistparser.setString(s);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            Slog.e("TextClassifierConstants", (new StringBuilder()).append("Bad TextClassifier settings: ").append(s).toString());
        }
        mDarkLaunch = keyvaluelistparser.getBoolean("smart_selection_dark_launch", false);
        mSuggestSelectionEnabledForEditableText = keyvaluelistparser.getBoolean("smart_selection_enabled_for_edit_text", true);
    }

    static TextClassifierConstants loadFromString(String s)
    {
        return new TextClassifierConstants(s);
    }

    public boolean isDarkLaunch()
    {
        return mDarkLaunch;
    }

    public boolean isSuggestSelectionEnabledForEditableText()
    {
        return mSuggestSelectionEnabledForEditableText;
    }

    static final TextClassifierConstants DEFAULT = new TextClassifierConstants();
    private static final String LOG_TAG = "TextClassifierConstants";
    private static final String SMART_SELECTION_DARK_LAUNCH = "smart_selection_dark_launch";
    private static final boolean SMART_SELECTION_DARK_LAUNCH_DEFAULT = false;
    private static final String SMART_SELECTION_ENABLED_FOR_EDIT_TEXT = "smart_selection_enabled_for_edit_text";
    private static final boolean SMART_SELECTION_ENABLED_FOR_EDIT_TEXT_DEFAULT = true;
    private final boolean mDarkLaunch;
    private final boolean mSuggestSelectionEnabledForEditableText;

}
