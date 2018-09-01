// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.*;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.content.res:
//            ComplexColor, TypedArray, Resources, ConstantState

public class ColorStateList extends ComplexColor
    implements Parcelable
{
    private static class ColorStateListFactory extends ConstantState
    {

        public int getChangingConfigurations()
        {
            return ColorStateList._2D_get0(mSrc);
        }

        public ColorStateList newInstance()
        {
            return mSrc;
        }

        public ColorStateList newInstance(Resources resources, Resources.Theme theme)
        {
            return mSrc.obtainForTheme(theme);
        }

        public volatile Object newInstance()
        {
            return newInstance();
        }

        public volatile Object newInstance(Resources resources, Resources.Theme theme)
        {
            return newInstance(resources, theme);
        }

        private final ColorStateList mSrc;

        public ColorStateListFactory(ColorStateList colorstatelist)
        {
            mSrc = colorstatelist;
        }
    }


    static int _2D_get0(ColorStateList colorstatelist)
    {
        return colorstatelist.mChangingConfigurations;
    }

    private ColorStateList()
    {
    }

    private ColorStateList(ColorStateList colorstatelist)
    {
        if(colorstatelist != null)
        {
            mChangingConfigurations = colorstatelist.mChangingConfigurations;
            mStateSpecs = colorstatelist.mStateSpecs;
            mDefaultColor = colorstatelist.mDefaultColor;
            mIsOpaque = colorstatelist.mIsOpaque;
            mThemeAttrs = (int[][])colorstatelist.mThemeAttrs.clone();
            mColors = (int[])colorstatelist.mColors.clone();
        }
    }

    public ColorStateList(int ai[][], int ai1[])
    {
        mStateSpecs = ai;
        mColors = ai1;
        onColorsChanged();
    }

    private void applyTheme(Resources.Theme theme)
    {
        if(mThemeAttrs == null)
            return;
        int i = 0;
        int ai[][] = mThemeAttrs;
        int j = ai.length;
        int k = 0;
        while(k < j) 
        {
            int l = i;
            if(ai[k] != null)
            {
                TypedArray typedarray = theme.resolveAttributes(ai[k], com.android.internal.R.styleable.ColorStateListItem);
                float f;
                if(ai[k][0] != 0)
                    f = (float)Color.alpha(mColors[k]) / 255F;
                else
                    f = 1.0F;
                ai[k] = typedarray.extractThemeAttrs(ai[k]);
                if(ai[k] != null)
                    i = 1;
                l = typedarray.getColor(0, mColors[k]);
                f = typedarray.getFloat(1, f);
                mColors[k] = modulateColorAlpha(l, f);
                mChangingConfigurations = mChangingConfigurations | typedarray.getChangingConfigurations();
                typedarray.recycle();
                l = i;
            }
            k++;
            i = l;
        }
        if(i == 0)
            mThemeAttrs = null;
        onColorsChanged();
    }

    public static ColorStateList createFromXml(Resources resources, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        return createFromXml(resources, xmlpullparser, null);
    }

    public static ColorStateList createFromXml(Resources resources, XmlPullParser xmlpullparser, Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        AttributeSet attributeset = Xml.asAttributeSet(xmlpullparser);
        int i;
        do
            i = xmlpullparser.next();
        while(i != 2 && i != 1);
        if(i != 2)
            throw new XmlPullParserException("No start tag found");
        else
            return createFromXmlInner(resources, xmlpullparser, attributeset, theme);
    }

    static ColorStateList createFromXmlInner(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        String s = xmlpullparser.getName();
        if(!s.equals("selector"))
        {
            throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": invalid color state list tag ").append(s).toString());
        } else
        {
            ColorStateList colorstatelist = new ColorStateList();
            colorstatelist.inflate(resources, xmlpullparser, attributeset, theme);
            return colorstatelist;
        }
    }

    private void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth() + 1;
        int j = 0;
        int k = 0xffff0000;
        boolean flag = false;
        int ai[][] = (int[][])ArrayUtils.newUnpaddedArray([I, 20);
        int ai1[][] = new int[ai.length][];
        int ai2[] = new int[ai.length];
        int l = 0;
label0:
        do
        {
            int i2;
label1:
            {
                int i1 = xmlpullparser.next();
                if(i1 == 1)
                    break label0;
                int j1 = xmlpullparser.getDepth();
                if(j1 < i && i1 == 3)
                    break label0;
                if(i1 != 2 || j1 > i || xmlpullparser.getName().equals("item") ^ true)
                    continue;
                TypedArray typedarray = Resources.obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.ColorStateListItem);
                int ai4[] = typedarray.extractThemeAttrs();
                int l1 = typedarray.getColor(0, -65281);
                float f = typedarray.getFloat(1, 1.0F);
                i2 = j | typedarray.getChangingConfigurations();
                typedarray.recycle();
                int j2 = attributeset.getAttributeCount();
                int ai3[] = new int[j2];
                i1 = 0;
                j = 0;
                do
                    if(i1 < j2)
                    {
                        int k1 = attributeset.getAttributeNameResource(i1);
                        switch(k1)
                        {
                        default:
                            int k2 = j + 1;
                            if(!attributeset.getAttributeBooleanValue(i1, false))
                                k1 = -k1;
                            ai3[j] = k1;
                            j = k2;
                            // fall through

                        case 16843173: 
                        case 16843551: 
                            i1++;
                            break;
                        }
                    } else
                    {
                        ai3 = StateSet.trimStateSet(ai3, j);
                        j = modulateColorAlpha(l1, f);
                        if(l == 0 || ai3.length == 0)
                            k = j;
                        if(ai4 != null)
                            flag = true;
                        ai2 = GrowingArrayUtils.append(ai2, l, j);
                        ai1 = (int[][])GrowingArrayUtils.append(ai1, l, ai4);
                        ai = (int[][])GrowingArrayUtils.append(ai, l, ai3);
                        l++;
                        break label1;
                    }
                while(true);
            }
            j = i2;
        } while(true);
        mChangingConfigurations = j;
        mDefaultColor = k;
        if(flag)
        {
            mThemeAttrs = new int[l][];
            System.arraycopy(ai1, 0, mThemeAttrs, 0, l);
        } else
        {
            mThemeAttrs = null;
        }
        mColors = new int[l];
        mStateSpecs = new int[l][];
        System.arraycopy(ai2, 0, mColors, 0, l);
        System.arraycopy(ai, 0, mStateSpecs, 0, l);
        onColorsChanged();
    }

    private int modulateColorAlpha(int i, float f)
    {
        if(f == 1.0F)
            return i;
        else
            return 0xffffff & i | MathUtils.constrain((int)((float)Color.alpha(i) * f + 0.5F), 0, 255) << 24;
    }

    private void onColorsChanged()
    {
        int i;
        boolean flag;
        int ai[][];
        int ai1[];
        int j;
        boolean flag1;
        i = 0xffff0000;
        flag = true;
        ai = mStateSpecs;
        ai1 = mColors;
        j = ai.length;
        flag1 = flag;
        if(j <= 0) goto _L2; else goto _L1
_L1:
        int k;
        k = ai1[0];
        i = j - 1;
_L7:
        int l = k;
        if(i <= 0) goto _L4; else goto _L3
_L3:
        if(ai[i].length != 0) goto _L6; else goto _L5
_L5:
        l = ai1[i];
_L4:
        k = 0;
_L8:
        i = l;
        flag1 = flag;
        if(k < j)
        {
            if(Color.alpha(ai1[k]) == 255)
                break MISSING_BLOCK_LABEL_114;
            flag1 = false;
            i = l;
        }
_L2:
        mDefaultColor = i;
        mIsOpaque = flag1;
        return;
_L6:
        i--;
          goto _L7
        k++;
          goto _L8
    }

    public static ColorStateList valueOf(int i)
    {
        SparseArray sparsearray = sCache;
        sparsearray;
        JVM INSTR monitorenter ;
        int j = sCache.indexOfKey(i);
        if(j < 0)
            break MISSING_BLOCK_LABEL_50;
        ColorStateList colorstatelist = (ColorStateList)((WeakReference)sCache.valueAt(j)).get();
        if(colorstatelist == null)
            break MISSING_BLOCK_LABEL_43;
        sparsearray;
        JVM INSTR monitorexit ;
        return colorstatelist;
        sCache.removeAt(j);
        j = sCache.size() - 1;
_L3:
        if(j < 0) goto _L2; else goto _L1
_L1:
        if(((WeakReference)sCache.valueAt(j)).get() == null)
            sCache.removeAt(j);
        j--;
          goto _L3
_L2:
        colorstatelist = JVM INSTR new #2   <Class ColorStateList>;
        colorstatelist.ColorStateList(EMPTY, new int[] {
            i
        });
        SparseArray sparsearray1 = sCache;
        WeakReference weakreference = JVM INSTR new #263 <Class WeakReference>;
        weakreference.WeakReference(colorstatelist);
        sparsearray1.put(i, weakreference);
        sparsearray;
        JVM INSTR monitorexit ;
        return colorstatelist;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean canApplyTheme()
    {
        boolean flag;
        if(mThemeAttrs != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | mChangingConfigurations;
    }

    public int getColorForState(int ai[], int i)
    {
        int j = mStateSpecs.length;
        for(int k = 0; k < j; k++)
            if(StateSet.stateSetMatches(mStateSpecs[k], ai))
                return mColors[k];

        return i;
    }

    public int[] getColors()
    {
        return mColors;
    }

    public ConstantState getConstantState()
    {
        if(mFactory == null)
            mFactory = new ColorStateListFactory(this);
        return mFactory;
    }

    public int getDefaultColor()
    {
        return mDefaultColor;
    }

    public int[][] getStates()
    {
        return mStateSpecs;
    }

    public boolean hasFocusStateSpecified()
    {
        return StateSet.containsAttribute(mStateSpecs, 0x101009c);
    }

    public boolean hasState(int i)
    {
        int ai[][] = mStateSpecs;
        int j = ai.length;
        for(int k = 0; k < j; k++)
        {
            int ai1[] = ai[k];
            int l = ai1.length;
            for(int i1 = 0; i1 < l; i1++)
                if(ai1[i1] == i || ai1[i1] == i)
                    return true;

        }

        return false;
    }

    public boolean isOpaque()
    {
        return mIsOpaque;
    }

    public boolean isStateful()
    {
        boolean flag = true;
        if(mStateSpecs.length < 1 || mStateSpecs[0].length <= 0)
            flag = false;
        return flag;
    }

    public ColorStateList obtainForTheme(Resources.Theme theme)
    {
        if(theme == null || canApplyTheme() ^ true)
        {
            return this;
        } else
        {
            ColorStateList colorstatelist = new ColorStateList(this);
            colorstatelist.applyTheme(theme);
            return colorstatelist;
        }
    }

    public volatile ComplexColor obtainForTheme(Resources.Theme theme)
    {
        return obtainForTheme(theme);
    }

    public String toString()
    {
        return (new StringBuilder()).append("ColorStateList{mThemeAttrs=").append(Arrays.deepToString(mThemeAttrs)).append("mChangingConfigurations=").append(mChangingConfigurations).append("mStateSpecs=").append(Arrays.deepToString(mStateSpecs)).append("mColors=").append(Arrays.toString(mColors)).append("mDefaultColor=").append(mDefaultColor).append('}').toString();
    }

    public ColorStateList withAlpha(int i)
    {
        int ai[] = new int[mColors.length];
        int j = ai.length;
        for(int k = 0; k < j; k++)
            ai[k] = mColors[k] & 0xffffff | i << 24;

        return new ColorStateList(mStateSpecs, ai);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(canApplyTheme())
            Log.w("ColorStateList", "Wrote partially-resolved ColorStateList to parcel!");
        int j = mStateSpecs.length;
        parcel.writeInt(j);
        for(i = 0; i < j; i++)
            parcel.writeIntArray(mStateSpecs[i]);

        parcel.writeIntArray(mColors);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ColorStateList createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            int ai[][] = new int[i][];
            for(int j = 0; j < i; j++)
                ai[j] = parcel.createIntArray();

            return new ColorStateList(ai, parcel.createIntArray());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ColorStateList[] newArray(int i)
        {
            return new ColorStateList[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int DEFAULT_COLOR = 0xffff0000;
    private static final int EMPTY[][] = {
        new int[0]
    };
    private static final String TAG = "ColorStateList";
    private static final SparseArray sCache = new SparseArray();
    private int mChangingConfigurations;
    private int mColors[];
    private int mDefaultColor;
    private ColorStateListFactory mFactory;
    private boolean mIsOpaque;
    private int mStateSpecs[][];
    private int mThemeAttrs[][];

}
