// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import java.util.List;

public abstract class ViewStructure
{
    public static abstract class HtmlInfo
    {

        public abstract List getAttributes();

        public abstract String getTag();

        public HtmlInfo()
        {
        }
    }

    public static abstract class HtmlInfo.Builder
    {

        public abstract HtmlInfo.Builder addAttribute(String s, String s1);

        public abstract HtmlInfo build();

        public HtmlInfo.Builder()
        {
        }
    }


    public ViewStructure()
    {
    }

    public abstract int addChildCount(int i);

    public abstract void asyncCommit();

    public abstract ViewStructure asyncNewChild(int i);

    public abstract AutofillId getAutofillId();

    public abstract int getChildCount();

    public abstract Bundle getExtras();

    public abstract CharSequence getHint();

    public abstract Rect getTempRect();

    public abstract CharSequence getText();

    public abstract int getTextSelectionEnd();

    public abstract int getTextSelectionStart();

    public abstract boolean hasExtras();

    public abstract ViewStructure newChild(int i);

    public abstract HtmlInfo.Builder newHtmlInfoBuilder(String s);

    public abstract void setAccessibilityFocused(boolean flag);

    public abstract void setActivated(boolean flag);

    public abstract void setAlpha(float f);

    public abstract void setAssistBlocked(boolean flag);

    public abstract void setAutofillHints(String as[]);

    public abstract void setAutofillId(AutofillId autofillid);

    public abstract void setAutofillId(AutofillId autofillid, int i);

    public abstract void setAutofillOptions(CharSequence acharsequence[]);

    public abstract void setAutofillType(int i);

    public abstract void setAutofillValue(AutofillValue autofillvalue);

    public abstract void setCheckable(boolean flag);

    public abstract void setChecked(boolean flag);

    public abstract void setChildCount(int i);

    public abstract void setClassName(String s);

    public abstract void setClickable(boolean flag);

    public abstract void setContentDescription(CharSequence charsequence);

    public abstract void setContextClickable(boolean flag);

    public abstract void setDataIsSensitive(boolean flag);

    public abstract void setDimens(int i, int j, int k, int l, int i1, int j1);

    public abstract void setElevation(float f);

    public abstract void setEnabled(boolean flag);

    public abstract void setFocusable(boolean flag);

    public abstract void setFocused(boolean flag);

    public abstract void setHint(CharSequence charsequence);

    public abstract void setHtmlInfo(HtmlInfo htmlinfo);

    public abstract void setId(int i, String s, String s1, String s2);

    public abstract void setInputType(int i);

    public abstract void setLocaleList(LocaleList localelist);

    public abstract void setLongClickable(boolean flag);

    public abstract void setOpaque(boolean flag);

    public abstract void setSelected(boolean flag);

    public abstract void setText(CharSequence charsequence);

    public abstract void setText(CharSequence charsequence, int i, int j);

    public abstract void setTextLines(int ai[], int ai1[]);

    public abstract void setTextStyle(float f, int i, int j, int k);

    public abstract void setTransformation(Matrix matrix);

    public abstract void setVisibility(int i);

    public abstract void setWebDomain(String s);
}
