// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.LeakyTypefaceStorage;
import android.graphics.Typeface;
import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;

// Referenced classes of package android.text.style:
//            MetricAffectingSpan

public class TextAppearanceSpan extends MetricAffectingSpan
    implements ParcelableSpan
{

    public TextAppearanceSpan(Context context, int i)
    {
        this(context, i, -1);
    }

    public TextAppearanceSpan(Context context, int i, int j)
    {
        TypedArray typedarray;
        typedarray = context.obtainStyledAttributes(i, com.android.internal.R.styleable.TextAppearance);
        ColorStateList colorstatelist = typedarray.getColorStateList(3);
        mTextColorLink = typedarray.getColorStateList(6);
        mTextSize = typedarray.getDimensionPixelSize(0, -1);
        mStyle = typedarray.getInt(2, 0);
        if(!context.isRestricted() && context.canLoadUnsafeResources())
            mTypeface = typedarray.getFont(12);
        else
            mTypeface = null;
        if(mTypeface == null) goto _L2; else goto _L1
_L1:
        mFamilyName = null;
_L4:
        typedarray.recycle();
        if(j >= 0)
        {
            context = context.obtainStyledAttributes(0x1030005, com.android.internal.R.styleable.Theme);
            colorstatelist = context.getColorStateList(j);
            context.recycle();
        }
        mTextColor = colorstatelist;
        return;
_L2:
        String s = typedarray.getString(12);
        if(s == null)
            break; /* Loop/switch isn't completed */
        mFamilyName = s;
        if(true) goto _L4; else goto _L3
_L3:
        switch(typedarray.getInt(1, 0))
        {
        default:
            mFamilyName = null;
            break;

        case 1: // '\001'
            mFamilyName = "sans";
            break;

        case 2: // '\002'
            mFamilyName = "serif";
            break;

        case 3: // '\003'
            mFamilyName = "monospace";
            break;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    public TextAppearanceSpan(Parcel parcel)
    {
        mFamilyName = parcel.readString();
        mStyle = parcel.readInt();
        mTextSize = parcel.readInt();
        if(parcel.readInt() != 0)
            mTextColor = (ColorStateList)ColorStateList.CREATOR.createFromParcel(parcel);
        else
            mTextColor = null;
        if(parcel.readInt() != 0)
            mTextColorLink = (ColorStateList)ColorStateList.CREATOR.createFromParcel(parcel);
        else
            mTextColorLink = null;
        mTypeface = LeakyTypefaceStorage.readTypefaceFromParcel(parcel);
    }

    public TextAppearanceSpan(String s, int i, int j, ColorStateList colorstatelist, ColorStateList colorstatelist1)
    {
        mFamilyName = s;
        mStyle = i;
        mTextSize = j;
        mTextColor = colorstatelist;
        mTextColorLink = colorstatelist1;
        mTypeface = null;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getFamily()
    {
        return mFamilyName;
    }

    public ColorStateList getLinkTextColor()
    {
        return mTextColorLink;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 17;
    }

    public ColorStateList getTextColor()
    {
        return mTextColor;
    }

    public int getTextSize()
    {
        return mTextSize;
    }

    public int getTextStyle()
    {
        return mStyle;
    }

    public void updateDrawState(TextPaint textpaint)
    {
        updateMeasureState(textpaint);
        if(mTextColor != null)
            textpaint.setColor(mTextColor.getColorForState(textpaint.drawableState, 0));
        if(mTextColorLink != null)
            textpaint.linkColor = mTextColorLink.getColorForState(textpaint.drawableState, 0);
    }

    public void updateMeasureState(TextPaint textpaint)
    {
        boolean flag = false;
        int i = 0;
        Typeface typeface;
        if(mTypeface != null)
        {
            i = mStyle;
            typeface = Typeface.create(mTypeface, i);
        } else
        if(mFamilyName != null || mStyle != 0)
        {
            typeface = textpaint.getTypeface();
            i = ((flag) ? 1 : 0);
            if(typeface != null)
                i = typeface.getStyle();
            i |= mStyle;
            if(mFamilyName != null)
                typeface = Typeface.create(mFamilyName, i);
            else
            if(typeface == null)
                typeface = Typeface.defaultFromStyle(i);
            else
                typeface = Typeface.create(typeface, i);
        } else
        {
            typeface = null;
        }
        if(typeface != null)
        {
            i &= typeface.getStyle();
            if((i & 1) != 0)
                textpaint.setFakeBoldText(true);
            if((i & 2) != 0)
                textpaint.setTextSkewX(-0.25F);
            textpaint.setTypeface(typeface);
        }
        if(mTextSize > 0)
            textpaint.setTextSize(mTextSize);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeString(mFamilyName);
        parcel.writeInt(mStyle);
        parcel.writeInt(mTextSize);
        if(mTextColor != null)
        {
            parcel.writeInt(1);
            mTextColor.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        if(mTextColorLink != null)
        {
            parcel.writeInt(1);
            mTextColorLink.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        LeakyTypefaceStorage.writeTypefaceToParcel(mTypeface, parcel);
    }

    private final String mFamilyName;
    private final int mStyle;
    private final ColorStateList mTextColor;
    private final ColorStateList mTextColorLink;
    private final int mTextSize;
    private final Typeface mTypeface;
}
