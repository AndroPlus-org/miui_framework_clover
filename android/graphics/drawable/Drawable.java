// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.*;
import android.graphics.*;
import android.os.Trace;
import android.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            DrawableInflater, NinePatchDrawable, BitmapDrawable

public abstract class Drawable
{
    public static interface Callback
    {

        public abstract void invalidateDrawable(Drawable drawable);

        public abstract void scheduleDrawable(Drawable drawable, Runnable runnable, long l);

        public abstract void unscheduleDrawable(Drawable drawable, Runnable runnable);
    }

    public static abstract class ConstantState
    {

        public boolean canApplyTheme()
        {
            return false;
        }

        public abstract int getChangingConfigurations();

        public abstract Drawable newDrawable();

        public Drawable newDrawable(Resources resources)
        {
            return newDrawable();
        }

        public Drawable newDrawable(Resources resources, android.content.res.Resources.Theme theme)
        {
            return newDrawable(resources);
        }

        public ConstantState()
        {
        }
    }


    public Drawable()
    {
        mStateSet = StateSet.WILD_CARD;
        mLevel = 0;
        mChangingConfigurations = 0;
        mBounds = ZERO_BOUNDS_RECT;
        mCallback = null;
        mVisible = true;
        mSrcDensityOverride = 0;
    }

    public static Drawable createFromPath(String s)
    {
        if(s == null)
            return null;
        Trace.traceBegin(8192L, s);
        Bitmap bitmap = BitmapFactory.decodeFile(s);
        if(bitmap == null)
            break MISSING_BLOCK_LABEL_40;
        s = drawableFromBitmap(null, bitmap, null, null, null, s);
        Trace.traceEnd(8192L);
        return s;
        Trace.traceEnd(8192L);
        return null;
        s;
        Trace.traceEnd(8192L);
        throw s;
    }

    public static Drawable createFromResourceStream(Resources resources, TypedValue typedvalue, InputStream inputstream, String s)
    {
        String s1;
        if(s != null)
            s1 = s;
        else
            s1 = "Unknown drawable";
        Trace.traceBegin(8192L, s1);
        resources = createFromResourceStream(resources, typedvalue, inputstream, s, null);
        Trace.traceEnd(8192L);
        return resources;
        resources;
        Trace.traceEnd(8192L);
        throw resources;
    }

    public static Drawable createFromResourceStream(Resources resources, TypedValue typedvalue, InputStream inputstream, String s, android.graphics.BitmapFactory.Options options)
    {
label0:
        {
            Object obj;
label1:
            {
                if(inputstream == null)
                    return null;
                Rect rect = new Rect();
                obj = options;
                if(options == null)
                    obj = new android.graphics.BitmapFactory.Options();
                obj.inScreenDensity = resolveDensity(resources, 0);
                obj = BitmapFactory.decodeResourceStream(resources, typedvalue, inputstream, rect, ((android.graphics.BitmapFactory.Options) (obj)));
                if(obj == null)
                    break label0;
                options = ((Bitmap) (obj)).getNinePatchChunk();
                if(options != null)
                {
                    typedvalue = options;
                    inputstream = rect;
                    if(!(NinePatch.isNinePatchChunk(options) ^ true))
                        break label1;
                }
                typedvalue = null;
                inputstream = null;
            }
            options = new Rect();
            ((Bitmap) (obj)).getOpticalInsets(options);
            return drawableFromBitmap(resources, ((Bitmap) (obj)), typedvalue, inputstream, options, s);
        }
        return null;
    }

    public static Drawable createFromStream(InputStream inputstream, String s)
    {
        String s1;
        if(s != null)
            s1 = s;
        else
            s1 = "Unknown drawable";
        Trace.traceBegin(8192L, s1);
        inputstream = createFromResourceStream(null, null, inputstream, s);
        Trace.traceEnd(8192L);
        return inputstream;
        inputstream;
        Trace.traceEnd(8192L);
        throw inputstream;
    }

    public static Drawable createFromXml(Resources resources, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        return createFromXml(resources, xmlpullparser, null);
    }

    public static Drawable createFromXml(Resources resources, XmlPullParser xmlpullparser, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        return createFromXmlForDensity(resources, xmlpullparser, 0, theme);
    }

    public static Drawable createFromXmlForDensity(Resources resources, XmlPullParser xmlpullparser, int i, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        AttributeSet attributeset = Xml.asAttributeSet(xmlpullparser);
        int j;
        do
            j = xmlpullparser.next();
        while(j != 2 && j != 1);
        if(j != 2)
            throw new XmlPullParserException("No start tag found");
        resources = createFromXmlInnerForDensity(resources, xmlpullparser, attributeset, i, theme);
        if(resources == null)
            throw new RuntimeException((new StringBuilder()).append("Unknown initial tag: ").append(xmlpullparser.getName()).toString());
        else
            return resources;
    }

    public static Drawable createFromXmlInner(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        return createFromXmlInner(resources, xmlpullparser, attributeset, null);
    }

    public static Drawable createFromXmlInner(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        return createFromXmlInnerForDensity(resources, xmlpullparser, attributeset, 0, theme);
    }

    static Drawable createFromXmlInnerForDensity(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, int i, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        return resources.getDrawableInflater().inflateFromXmlForDensity(xmlpullparser.getName(), xmlpullparser, attributeset, i, theme);
    }

    private static Drawable drawableFromBitmap(Resources resources, Bitmap bitmap, byte abyte0[], Rect rect, Rect rect1, String s)
    {
        if(abyte0 != null)
            return new NinePatchDrawable(resources, bitmap, abyte0, rect, rect1, s);
        else
            return new BitmapDrawable(resources, bitmap);
    }

    protected static TypedArray obtainAttributes(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset, int ai[])
    {
        if(theme == null)
            return resources.obtainAttributes(attributeset, ai);
        else
            return theme.obtainStyledAttributes(attributeset, ai, 0, 0);
    }

    public static android.graphics.PorterDuff.Mode parseTintMode(int i, android.graphics.PorterDuff.Mode mode)
    {
        switch(i)
        {
        case 4: // '\004'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        default:
            return mode;

        case 3: // '\003'
            return android.graphics.PorterDuff.Mode.SRC_OVER;

        case 5: // '\005'
            return android.graphics.PorterDuff.Mode.SRC_IN;

        case 9: // '\t'
            return android.graphics.PorterDuff.Mode.SRC_ATOP;

        case 14: // '\016'
            return android.graphics.PorterDuff.Mode.MULTIPLY;

        case 15: // '\017'
            return android.graphics.PorterDuff.Mode.SCREEN;

        case 16: // '\020'
            return android.graphics.PorterDuff.Mode.ADD;
        }
    }

    static int resolveDensity(Resources resources, int i)
    {
        int j;
        if(resources != null)
            i = resources.getDisplayMetrics().densityDpi;
        j = i;
        if(i == 0)
            j = 160;
        return j;
    }

    public static int resolveOpacity(int i, int j)
    {
        if(i == j)
            return i;
        if(i == 0 || j == 0)
            return 0;
        if(i == -3 || j == -3)
            return -3;
        return i != -2 && j != -2 ? -1 : -2;
    }

    static void rethrowAsRuntimeException(Exception exception)
        throws RuntimeException
    {
        exception = new RuntimeException(exception);
        exception.setStackTrace(new StackTraceElement[0]);
        throw exception;
    }

    static float scaleFromDensity(float f, int i, int j)
    {
        return ((float)j * f) / (float)i;
    }

    static int scaleFromDensity(int i, int j, int k, boolean flag)
    {
        if(i == 0 || j == k)
            return i;
        float f = (float)(i * k) / (float)j;
        if(!flag)
            return (int)f;
        j = Math.round(f);
        if(j != 0)
            return j;
        return i <= 0 ? -1 : 1;
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
    }

    public boolean canApplyTheme()
    {
        return false;
    }

    public void clearColorFilter()
    {
        setColorFilter(null);
    }

    public void clearMutated()
    {
    }

    public final Rect copyBounds()
    {
        return new Rect(mBounds);
    }

    public final void copyBounds(Rect rect)
    {
        rect.set(mBounds);
    }

    public abstract void draw(Canvas canvas);

    public int getAlpha()
    {
        return 255;
    }

    public final Rect getBounds()
    {
        if(mBounds == ZERO_BOUNDS_RECT)
            mBounds = new Rect();
        return mBounds;
    }

    public Callback getCallback()
    {
        Callback callback = null;
        if(mCallback != null)
            callback = (Callback)mCallback.get();
        return callback;
    }

    public int getChangingConfigurations()
    {
        return mChangingConfigurations;
    }

    public ColorFilter getColorFilter()
    {
        return null;
    }

    public ConstantState getConstantState()
    {
        return null;
    }

    public Drawable getCurrent()
    {
        return this;
    }

    public Rect getDirtyBounds()
    {
        return getBounds();
    }

    public void getHotspotBounds(Rect rect)
    {
        rect.set(getBounds());
    }

    public int getIntrinsicHeight()
    {
        return -1;
    }

    public int getIntrinsicWidth()
    {
        return -1;
    }

    public int getLayoutDirection()
    {
        return mLayoutDirection;
    }

    public final int getLevel()
    {
        return mLevel;
    }

    public int getMinimumHeight()
    {
        int i = getIntrinsicHeight();
        if(i <= 0)
            i = 0;
        return i;
    }

    public int getMinimumWidth()
    {
        int i = getIntrinsicWidth();
        if(i <= 0)
            i = 0;
        return i;
    }

    public abstract int getOpacity();

    public Insets getOpticalInsets()
    {
        return Insets.NONE;
    }

    public void getOutline(Outline outline)
    {
        outline.setRect(getBounds());
        outline.setAlpha(0.0F);
    }

    public boolean getPadding(Rect rect)
    {
        rect.set(0, 0, 0, 0);
        return false;
    }

    public int[] getState()
    {
        return mStateSet;
    }

    public Region getTransparentRegion()
    {
        return null;
    }

    public boolean hasFocusStateSpecified()
    {
        return false;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        inflate(resources, xmlpullparser, attributeset, null);
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        resources = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.Drawable);
        mVisible = resources.getBoolean(0, mVisible);
        resources.recycle();
    }

    void inflateWithAttributes(Resources resources, XmlPullParser xmlpullparser, TypedArray typedarray, int i)
        throws XmlPullParserException, IOException
    {
        mVisible = typedarray.getBoolean(i, mVisible);
    }

    public void invalidateSelf()
    {
        Callback callback = getCallback();
        if(callback != null)
            callback.invalidateDrawable(this);
    }

    public boolean isAutoMirrored()
    {
        return false;
    }

    public boolean isFilterBitmap()
    {
        return false;
    }

    public boolean isProjected()
    {
        return false;
    }

    public boolean isStateful()
    {
        return false;
    }

    public final boolean isVisible()
    {
        return mVisible;
    }

    public void jumpToCurrentState()
    {
    }

    public Drawable mutate()
    {
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
    }

    public boolean onLayoutDirectionChanged(int i)
    {
        return false;
    }

    protected boolean onLevelChange(int i)
    {
        return false;
    }

    protected boolean onStateChange(int ai[])
    {
        return false;
    }

    public void scheduleSelf(Runnable runnable, long l)
    {
        Callback callback = getCallback();
        if(callback != null)
            callback.scheduleDrawable(this, runnable, l);
    }

    public abstract void setAlpha(int i);

    public void setAutoMirrored(boolean flag)
    {
    }

    public void setBounds(int i, int j, int k, int l)
    {
        Rect rect1;
        Rect rect = mBounds;
        rect1 = rect;
        if(rect == ZERO_BOUNDS_RECT)
        {
            rect1 = new Rect();
            mBounds = rect1;
        }
        break MISSING_BLOCK_LABEL_33;
        if(rect1.left != i || rect1.top != j || rect1.right != k || rect1.bottom != l)
        {
            if(!rect1.isEmpty())
                invalidateSelf();
            mBounds.set(i, j, k, l);
            onBoundsChange(mBounds);
        }
        return;
    }

    public void setBounds(Rect rect)
    {
        setBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    public final void setCallback(Callback callback)
    {
        WeakReference weakreference = null;
        if(callback != null)
            weakreference = new WeakReference(callback);
        mCallback = weakreference;
    }

    public void setChangingConfigurations(int i)
    {
        mChangingConfigurations = i;
    }

    public void setColorFilter(int i, android.graphics.PorterDuff.Mode mode)
    {
        if(getColorFilter() instanceof PorterDuffColorFilter)
        {
            PorterDuffColorFilter porterduffcolorfilter = (PorterDuffColorFilter)getColorFilter();
            if(porterduffcolorfilter.getColor() == i && porterduffcolorfilter.getMode() == mode)
                return;
        }
        setColorFilter(((ColorFilter) (new PorterDuffColorFilter(i, mode))));
    }

    public abstract void setColorFilter(ColorFilter colorfilter);

    public void setDither(boolean flag)
    {
    }

    public void setFilterBitmap(boolean flag)
    {
    }

    public void setHotspot(float f, float f1)
    {
    }

    public void setHotspotBounds(int i, int j, int k, int l)
    {
    }

    public final boolean setLayoutDirection(int i)
    {
        if(mLayoutDirection != i)
        {
            mLayoutDirection = i;
            return onLayoutDirectionChanged(i);
        } else
        {
            return false;
        }
    }

    public final boolean setLevel(int i)
    {
        if(mLevel != i)
        {
            mLevel = i;
            return onLevelChange(i);
        } else
        {
            return false;
        }
    }

    final void setSrcDensityOverride(int i)
    {
        mSrcDensityOverride = i;
    }

    public boolean setState(int ai[])
    {
        if(!Arrays.equals(mStateSet, ai))
        {
            mStateSet = ai;
            return onStateChange(ai);
        } else
        {
            return false;
        }
    }

    public void setTint(int i)
    {
        setTintList(ColorStateList.valueOf(i));
    }

    public void setTintList(ColorStateList colorstatelist)
    {
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        if(mVisible != flag)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
        {
            mVisible = flag;
            invalidateSelf();
        }
        return flag1;
    }

    public void setXfermode(Xfermode xfermode)
    {
    }

    public void unscheduleSelf(Runnable runnable)
    {
        Callback callback = getCallback();
        if(callback != null)
            callback.unscheduleDrawable(this, runnable);
    }

    PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter porterduffcolorfilter, ColorStateList colorstatelist, android.graphics.PorterDuff.Mode mode)
    {
        if(colorstatelist == null || mode == null)
            return null;
        int i = colorstatelist.getColorForState(getState(), 0);
        if(porterduffcolorfilter == null)
        {
            return new PorterDuffColorFilter(i, mode);
        } else
        {
            porterduffcolorfilter.setColor(i);
            porterduffcolorfilter.setMode(mode);
            return porterduffcolorfilter;
        }
    }

    static final android.graphics.PorterDuff.Mode DEFAULT_TINT_MODE;
    private static final Rect ZERO_BOUNDS_RECT = new Rect();
    private Rect mBounds;
    private WeakReference mCallback;
    private int mChangingConfigurations;
    private int mLayoutDirection;
    private int mLevel;
    protected int mSrcDensityOverride;
    private int mStateSet[];
    private boolean mVisible;

    static 
    {
        DEFAULT_TINT_MODE = android.graphics.PorterDuff.Mode.SRC_IN;
    }
}
