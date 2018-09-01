// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.content.Context;
import android.content.Intent;
import android.content.res.*;
import android.os.Parcel;
import android.os.SystemClock;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import java.util.Arrays;
import java.util.Locale;

// Referenced classes of package android.text.style:
//            CharacterStyle

public class SuggestionSpan extends CharacterStyle
    implements ParcelableSpan
{

    public SuggestionSpan(Context context, Locale locale, String as[], int i, Class class1)
    {
        mSuggestions = (String[])Arrays.copyOf(as, Math.min(5, as.length));
        mFlags = i;
        if(locale == null)
            if(context != null)
            {
                locale = context.getResources().getConfiguration().locale;
            } else
            {
                Log.e("SuggestionSpan", "No locale or context specified in SuggestionSpan constructor");
                locale = null;
            }
        if(locale == null)
            as = "";
        else
            as = locale.toString();
        mLocaleStringForCompatibility = as;
        if(locale == null)
            locale = "";
        else
            locale = locale.toLanguageTag();
        mLanguageTag = locale;
        if(context != null)
            mNotificationTargetPackageName = context.getPackageName();
        else
            mNotificationTargetPackageName = null;
        if(class1 != null)
            mNotificationTargetClassName = class1.getCanonicalName();
        else
            mNotificationTargetClassName = "";
        mHashCode = hashCodeInternal(mSuggestions, mLanguageTag, mLocaleStringForCompatibility, mNotificationTargetClassName);
        initStyle(context);
    }

    public SuggestionSpan(Context context, String as[], int i)
    {
        this(context, null, as, i, null);
    }

    public SuggestionSpan(Parcel parcel)
    {
        mSuggestions = parcel.readStringArray();
        mFlags = parcel.readInt();
        mLocaleStringForCompatibility = parcel.readString();
        mLanguageTag = parcel.readString();
        mNotificationTargetClassName = parcel.readString();
        mNotificationTargetPackageName = parcel.readString();
        mHashCode = parcel.readInt();
        mEasyCorrectUnderlineColor = parcel.readInt();
        mEasyCorrectUnderlineThickness = parcel.readFloat();
        mMisspelledUnderlineColor = parcel.readInt();
        mMisspelledUnderlineThickness = parcel.readFloat();
        mAutoCorrectionUnderlineColor = parcel.readInt();
        mAutoCorrectionUnderlineThickness = parcel.readFloat();
    }

    public SuggestionSpan(Locale locale, String as[], int i)
    {
        this(null, locale, as, i, null);
    }

    private static int hashCodeInternal(String as[], String s, String s1, String s2)
    {
        return Arrays.hashCode(new Object[] {
            Long.valueOf(SystemClock.uptimeMillis()), as, s, s1, s2
        });
    }

    private void initStyle(Context context)
    {
        if(context == null)
        {
            mMisspelledUnderlineThickness = 0.0F;
            mEasyCorrectUnderlineThickness = 0.0F;
            mAutoCorrectionUnderlineThickness = 0.0F;
            mMisspelledUnderlineColor = 0xff000000;
            mEasyCorrectUnderlineColor = 0xff000000;
            mAutoCorrectionUnderlineColor = 0xff000000;
            return;
        } else
        {
            TypedArray typedarray = context.obtainStyledAttributes(null, com.android.internal.R.styleable.SuggestionSpan, 0x11100c4, 0);
            mMisspelledUnderlineThickness = typedarray.getDimension(1, 0.0F);
            mMisspelledUnderlineColor = typedarray.getColor(0, 0xff000000);
            typedarray = context.obtainStyledAttributes(null, com.android.internal.R.styleable.SuggestionSpan, 0x11100c3, 0);
            mEasyCorrectUnderlineThickness = typedarray.getDimension(1, 0.0F);
            mEasyCorrectUnderlineColor = typedarray.getColor(0, 0xff000000);
            context = context.obtainStyledAttributes(null, com.android.internal.R.styleable.SuggestionSpan, 0x11100c2, 0);
            mAutoCorrectionUnderlineThickness = context.getDimension(1, 0.0F);
            mAutoCorrectionUnderlineColor = context.getColor(0, 0xff000000);
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof SuggestionSpan)
        {
            if(((SuggestionSpan)obj).hashCode() == mHashCode)
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public int getFlags()
    {
        return mFlags;
    }

    public String getLocale()
    {
        return mLocaleStringForCompatibility;
    }

    public Locale getLocaleObject()
    {
        Locale locale;
        if(mLanguageTag.isEmpty())
            locale = null;
        else
            locale = Locale.forLanguageTag(mLanguageTag);
        return locale;
    }

    public String getNotificationTargetClassName()
    {
        return mNotificationTargetClassName;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 19;
    }

    public String[] getSuggestions()
    {
        return mSuggestions;
    }

    public int getUnderlineColor()
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        if((mFlags & 2) != 0)
            flag = true;
        else
            flag = false;
        if((mFlags & 1) != 0)
            flag1 = true;
        else
            flag1 = false;
        if((mFlags & 4) != 0)
            flag2 = true;
        else
            flag2 = false;
        if(flag1)
            if(!flag)
                return mEasyCorrectUnderlineColor;
            else
                return mMisspelledUnderlineColor;
        if(flag2)
            return mAutoCorrectionUnderlineColor;
        else
            return 0;
    }

    public int hashCode()
    {
        return mHashCode;
    }

    public void notifySelection(Context context, String s, int i)
    {
        Intent intent;
        intent = new Intent();
        if(context == null || mNotificationTargetClassName == null)
            return;
        while(mSuggestions == null || i < 0 || i >= mSuggestions.length) 
        {
            Log.w("SuggestionSpan", (new StringBuilder()).append("Unable to notify the suggestion as the index is out of range index=").append(i).append(" length=").append(mSuggestions.length).toString());
            return;
        }
        if(mNotificationTargetPackageName == null) goto _L2; else goto _L1
_L1:
        intent.setClassName(mNotificationTargetPackageName, mNotificationTargetClassName);
        intent.setAction("android.text.style.SUGGESTION_PICKED");
        intent.putExtra("before", s);
        intent.putExtra("after", mSuggestions[i]);
        intent.putExtra("hashcode", hashCode());
        context.sendBroadcast(intent);
_L4:
        return;
_L2:
        context = InputMethodManager.peekInstance();
        if(context != null)
            context.notifySuggestionPicked(this, s, i);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setFlags(int i)
    {
        mFlags = i;
    }

    public void updateDrawState(TextPaint textpaint)
    {
        boolean flag2;
        boolean flag;
        boolean flag1;
        if((mFlags & 2) != 0)
            flag = true;
        else
            flag = false;
        if((mFlags & 1) != 0)
            flag1 = true;
        else
            flag1 = false;
        if((mFlags & 4) != 0)
            flag2 = true;
        else
            flag2 = false;
        if(!flag1) goto _L2; else goto _L1
_L1:
        if(flag) goto _L4; else goto _L3
_L3:
        textpaint.setUnderlineText(mEasyCorrectUnderlineColor, mEasyCorrectUnderlineThickness);
_L6:
        return;
_L4:
        if(textpaint.underlineColor == 0)
            textpaint.setUnderlineText(mMisspelledUnderlineColor, mMisspelledUnderlineThickness);
        continue; /* Loop/switch isn't completed */
_L2:
        if(flag2)
            textpaint.setUnderlineText(mAutoCorrectionUnderlineColor, mAutoCorrectionUnderlineThickness);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeStringArray(mSuggestions);
        parcel.writeInt(mFlags);
        parcel.writeString(mLocaleStringForCompatibility);
        parcel.writeString(mLanguageTag);
        parcel.writeString(mNotificationTargetClassName);
        parcel.writeString(mNotificationTargetPackageName);
        parcel.writeInt(mHashCode);
        parcel.writeInt(mEasyCorrectUnderlineColor);
        parcel.writeFloat(mEasyCorrectUnderlineThickness);
        parcel.writeInt(mMisspelledUnderlineColor);
        parcel.writeFloat(mMisspelledUnderlineThickness);
        parcel.writeInt(mAutoCorrectionUnderlineColor);
        parcel.writeFloat(mAutoCorrectionUnderlineThickness);
    }

    public static final String ACTION_SUGGESTION_PICKED = "android.text.style.SUGGESTION_PICKED";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SuggestionSpan createFromParcel(Parcel parcel)
        {
            return new SuggestionSpan(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SuggestionSpan[] newArray(int i)
        {
            return new SuggestionSpan[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_AUTO_CORRECTION = 4;
    public static final int FLAG_EASY_CORRECT = 1;
    public static final int FLAG_MISSPELLED = 2;
    public static final int SUGGESTIONS_MAX_SIZE = 5;
    public static final String SUGGESTION_SPAN_PICKED_AFTER = "after";
    public static final String SUGGESTION_SPAN_PICKED_BEFORE = "before";
    public static final String SUGGESTION_SPAN_PICKED_HASHCODE = "hashcode";
    private static final String TAG = "SuggestionSpan";
    private int mAutoCorrectionUnderlineColor;
    private float mAutoCorrectionUnderlineThickness;
    private int mEasyCorrectUnderlineColor;
    private float mEasyCorrectUnderlineThickness;
    private int mFlags;
    private final int mHashCode;
    private final String mLanguageTag;
    private final String mLocaleStringForCompatibility;
    private int mMisspelledUnderlineColor;
    private float mMisspelledUnderlineThickness;
    private final String mNotificationTargetClassName;
    private final String mNotificationTargetPackageName;
    private final String mSuggestions[];

}
