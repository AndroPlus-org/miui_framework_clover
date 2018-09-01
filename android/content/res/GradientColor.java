// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.graphics.*;
import android.util.*;
import com.android.internal.util.GrowingArrayUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.content.res:
//            ComplexColor, TypedArray, XmlResourceParser, Resources, 
//            ConstantState

public class GradientColor extends ComplexColor
{
    private static class GradientColorFactory extends ConstantState
    {

        public int getChangingConfigurations()
        {
            return GradientColor._2D_get0(mSrc);
        }

        public GradientColor newInstance()
        {
            return mSrc;
        }

        public GradientColor newInstance(Resources resources, Resources.Theme theme)
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

        private final GradientColor mSrc;

        public GradientColorFactory(GradientColor gradientcolor)
        {
            mSrc = gradientcolor;
        }
    }


    static int _2D_get0(GradientColor gradientcolor)
    {
        return gradientcolor.mChangingConfigurations;
    }

    private GradientColor()
    {
        mShader = null;
        mGradientType = 0;
        mCenterX = 0.0F;
        mCenterY = 0.0F;
        mStartX = 0.0F;
        mStartY = 0.0F;
        mEndX = 0.0F;
        mEndY = 0.0F;
        mStartColor = 0;
        mCenterColor = 0;
        mEndColor = 0;
        mHasCenterColor = false;
        mTileMode = 0;
        mGradientRadius = 0.0F;
    }

    private GradientColor(GradientColor gradientcolor)
    {
        mShader = null;
        mGradientType = 0;
        mCenterX = 0.0F;
        mCenterY = 0.0F;
        mStartX = 0.0F;
        mStartY = 0.0F;
        mEndX = 0.0F;
        mEndY = 0.0F;
        mStartColor = 0;
        mCenterColor = 0;
        mEndColor = 0;
        mHasCenterColor = false;
        mTileMode = 0;
        mGradientRadius = 0.0F;
        if(gradientcolor != null)
        {
            mChangingConfigurations = gradientcolor.mChangingConfigurations;
            mDefaultColor = gradientcolor.mDefaultColor;
            mShader = gradientcolor.mShader;
            mGradientType = gradientcolor.mGradientType;
            mCenterX = gradientcolor.mCenterX;
            mCenterY = gradientcolor.mCenterY;
            mStartX = gradientcolor.mStartX;
            mStartY = gradientcolor.mStartY;
            mEndX = gradientcolor.mEndX;
            mEndY = gradientcolor.mEndY;
            mStartColor = gradientcolor.mStartColor;
            mCenterColor = gradientcolor.mCenterColor;
            mEndColor = gradientcolor.mEndColor;
            mHasCenterColor = gradientcolor.mHasCenterColor;
            mGradientRadius = gradientcolor.mGradientRadius;
            mTileMode = gradientcolor.mTileMode;
            if(gradientcolor.mItemColors != null)
                mItemColors = (int[])gradientcolor.mItemColors.clone();
            if(gradientcolor.mItemOffsets != null)
                mItemOffsets = (float[])gradientcolor.mItemOffsets.clone();
            if(gradientcolor.mThemeAttrs != null)
                mThemeAttrs = (int[])gradientcolor.mThemeAttrs.clone();
            if(gradientcolor.mItemsThemeAttrs != null)
                mItemsThemeAttrs = (int[][])gradientcolor.mItemsThemeAttrs.clone();
        }
    }

    private void applyItemsAttrsTheme(Resources.Theme theme)
    {
        if(mItemsThemeAttrs == null)
            return;
        boolean flag = false;
        int ai[][] = mItemsThemeAttrs;
        int i = ai.length;
        for(int j = 0; j < i;)
        {
            boolean flag1 = flag;
            if(ai[j] != null)
            {
                TypedArray typedarray = theme.resolveAttributes(ai[j], com.android.internal.R.styleable.GradientColorItem);
                ai[j] = typedarray.extractThemeAttrs(ai[j]);
                if(ai[j] != null)
                    flag = true;
                mItemColors[j] = typedarray.getColor(0, mItemColors[j]);
                mItemOffsets[j] = typedarray.getFloat(1, mItemOffsets[j]);
                mChangingConfigurations = mChangingConfigurations | typedarray.getChangingConfigurations();
                typedarray.recycle();
                flag1 = flag;
            }
            j++;
            flag = flag1;
        }

        if(!flag)
            mItemsThemeAttrs = null;
    }

    private void applyRootAttrsTheme(Resources.Theme theme)
    {
        theme = theme.resolveAttributes(mThemeAttrs, com.android.internal.R.styleable.GradientColor);
        mThemeAttrs = theme.extractThemeAttrs(mThemeAttrs);
        updateRootElementState(theme);
        mChangingConfigurations = mChangingConfigurations | theme.getChangingConfigurations();
        theme.recycle();
    }

    private void applyTheme(Resources.Theme theme)
    {
        if(mThemeAttrs != null)
            applyRootAttrsTheme(theme);
        if(mItemsThemeAttrs != null)
            applyItemsAttrsTheme(theme);
        onColorsChange();
    }

    public static GradientColor createFromXml(Resources resources, XmlResourceParser xmlresourceparser, Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        AttributeSet attributeset = Xml.asAttributeSet(xmlresourceparser);
        int i;
        do
            i = xmlresourceparser.next();
        while(i != 2 && i != 1);
        if(i != 2)
            throw new XmlPullParserException("No start tag found");
        else
            return createFromXmlInner(resources, xmlresourceparser, attributeset, theme);
    }

    static GradientColor createFromXmlInner(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        String s = xmlpullparser.getName();
        if(!s.equals("gradient"))
        {
            throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": invalid gradient color tag ").append(s).toString());
        } else
        {
            GradientColor gradientcolor = new GradientColor();
            gradientcolor.inflate(resources, xmlpullparser, attributeset, theme);
            return gradientcolor;
        }
    }

    private void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = Resources.obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.GradientColor);
        updateRootElementState(typedarray);
        mChangingConfigurations = mChangingConfigurations | typedarray.getChangingConfigurations();
        typedarray.recycle();
        validateXmlContent();
        inflateChildElements(resources, xmlpullparser, attributeset, theme);
        onColorsChange();
    }

    private void inflateChildElements(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth() + 1;
        float af[] = new float[20];
        int ai[] = new int[af.length];
        int ai1[][] = new int[af.length][];
        int j = 0;
        boolean flag = false;
        do
        {
            int k = xmlpullparser.next();
            if(k == 1)
                break;
            int l = xmlpullparser.getDepth();
            if(l < i && k == 3)
                break;
            if(k == 2 && l <= i && !(xmlpullparser.getName().equals("item") ^ true))
            {
                TypedArray typedarray = Resources.obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.GradientColorItem);
                boolean flag1 = typedarray.hasValue(0);
                boolean flag2 = typedarray.hasValue(1);
                if(!flag1 || flag2 ^ true)
                    throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": <item> tag requires a 'color' attribute and a 'offset' ").append("attribute!").toString());
                int ai2[] = typedarray.extractThemeAttrs();
                int i1 = typedarray.getColor(0, 0);
                float f = typedarray.getFloat(1, 0.0F);
                mChangingConfigurations = mChangingConfigurations | typedarray.getChangingConfigurations();
                typedarray.recycle();
                if(ai2 != null)
                    flag = true;
                ai = GrowingArrayUtils.append(ai, j, i1);
                af = GrowingArrayUtils.append(af, j, f);
                ai1 = (int[][])GrowingArrayUtils.append(ai1, j, ai2);
                j++;
            }
        } while(true);
        if(j > 0)
        {
            if(flag)
            {
                mItemsThemeAttrs = new int[j][];
                System.arraycopy(ai1, 0, mItemsThemeAttrs, 0, j);
            } else
            {
                mItemsThemeAttrs = null;
            }
            mItemColors = new int[j];
            mItemOffsets = new float[j];
            System.arraycopy(ai, 0, mItemColors, 0, j);
            System.arraycopy(af, 0, mItemOffsets, 0, j);
        }
    }

    private void onColorsChange()
    {
        float af[] = null;
        int ai1[];
        if(mItemColors != null)
        {
            int i = mItemColors.length;
            int ai[] = new int[i];
            float af1[] = new float[i];
            int j = 0;
            do
            {
                ai1 = ai;
                af = af1;
                if(j >= i)
                    break;
                ai[j] = mItemColors[j];
                af1[j] = mItemOffsets[j];
                j++;
            } while(true);
        } else
        if(mHasCenterColor)
        {
            ai1 = new int[3];
            ai1[0] = mStartColor;
            ai1[1] = mCenterColor;
            ai1[2] = mEndColor;
            af = new float[3];
            af[0] = 0.0F;
            af[1] = 0.5F;
            af[2] = 1.0F;
        } else
        {
            ai1 = new int[2];
            ai1[0] = mStartColor;
            ai1[1] = mEndColor;
        }
        if(ai1.length < 2)
            Log.w("GradientColor", (new StringBuilder()).append("<gradient> tag requires 2 color values specified!").append(ai1.length).append(" ").append(ai1).toString());
        if(mGradientType == 0)
            mShader = new LinearGradient(mStartX, mStartY, mEndX, mEndY, ai1, af, parseTileMode(mTileMode));
        else
        if(mGradientType == 1)
            mShader = new RadialGradient(mCenterX, mCenterY, mGradientRadius, ai1, af, parseTileMode(mTileMode));
        else
            mShader = new SweepGradient(mCenterX, mCenterY, ai1, af);
        mDefaultColor = ai1[0];
    }

    private static android.graphics.Shader.TileMode parseTileMode(int i)
    {
        switch(i)
        {
        default:
            return android.graphics.Shader.TileMode.CLAMP;

        case 0: // '\0'
            return android.graphics.Shader.TileMode.CLAMP;

        case 1: // '\001'
            return android.graphics.Shader.TileMode.REPEAT;

        case 2: // '\002'
            return android.graphics.Shader.TileMode.MIRROR;
        }
    }

    private void updateRootElementState(TypedArray typedarray)
    {
        mThemeAttrs = typedarray.extractThemeAttrs();
        mStartX = typedarray.getFloat(8, mStartX);
        mStartY = typedarray.getFloat(9, mStartY);
        mEndX = typedarray.getFloat(10, mEndX);
        mEndY = typedarray.getFloat(11, mEndY);
        mCenterX = typedarray.getFloat(3, mCenterX);
        mCenterY = typedarray.getFloat(4, mCenterY);
        mGradientType = typedarray.getInt(2, mGradientType);
        mStartColor = typedarray.getColor(0, mStartColor);
        mHasCenterColor = mHasCenterColor | typedarray.hasValue(7);
        mCenterColor = typedarray.getColor(7, mCenterColor);
        mEndColor = typedarray.getColor(1, mEndColor);
        mTileMode = typedarray.getInt(6, mTileMode);
        mGradientRadius = typedarray.getFloat(5, mGradientRadius);
    }

    private void validateXmlContent()
        throws XmlPullParserException
    {
        if(mGradientRadius <= 0.0F && mGradientType == 1)
            throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
        else
            return;
    }

    public boolean canApplyTheme()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mThemeAttrs == null)
            if(mItemsThemeAttrs != null)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | mChangingConfigurations;
    }

    public ConstantState getConstantState()
    {
        if(mFactory == null)
            mFactory = new GradientColorFactory(this);
        return mFactory;
    }

    public int getDefaultColor()
    {
        return mDefaultColor;
    }

    public Shader getShader()
    {
        return mShader;
    }

    public volatile ComplexColor obtainForTheme(Resources.Theme theme)
    {
        return obtainForTheme(theme);
    }

    public GradientColor obtainForTheme(Resources.Theme theme)
    {
        if(theme == null || canApplyTheme() ^ true)
        {
            return this;
        } else
        {
            GradientColor gradientcolor = new GradientColor(this);
            gradientcolor.applyTheme(theme);
            return gradientcolor;
        }
    }

    private static final boolean DBG_GRADIENT = false;
    private static final String TAG = "GradientColor";
    private static final int TILE_MODE_CLAMP = 0;
    private static final int TILE_MODE_MIRROR = 2;
    private static final int TILE_MODE_REPEAT = 1;
    private int mCenterColor;
    private float mCenterX;
    private float mCenterY;
    private int mChangingConfigurations;
    private int mDefaultColor;
    private int mEndColor;
    private float mEndX;
    private float mEndY;
    private GradientColorFactory mFactory;
    private float mGradientRadius;
    private int mGradientType;
    private boolean mHasCenterColor;
    private int mItemColors[];
    private float mItemOffsets[];
    private int mItemsThemeAttrs[][];
    private Shader mShader;
    private int mStartColor;
    private float mStartX;
    private float mStartY;
    private int mThemeAttrs[];
    private int mTileMode;
}
