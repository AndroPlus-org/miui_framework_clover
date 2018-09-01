// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.android.internal.util.XmlUtils;
import dalvik.system.VMRuntime;
import java.util.Arrays;

// Referenced classes of package android.content.res:
//            Resources, AssetManager, MiuiTypedArray, ColorStateList, 
//            ComplexColor

public class TypedArray
{

    protected TypedArray(Resources resources)
    {
        mValue = new TypedValue();
        mResources = resources;
        mMetrics = mResources.getDisplayMetrics();
        mAssets = mResources.getAssets();
    }

    private boolean getValueAt(int i, TypedValue typedvalue)
    {
        int ai[] = mData;
        int j = ai[i + 0];
        if(j == 0)
            return false;
        typedvalue.type = j;
        typedvalue.data = ai[i + 1];
        typedvalue.assetCookie = ai[i + 2];
        typedvalue.resourceId = ai[i + 3];
        typedvalue.changingConfigurations = ActivityInfo.activityInfoConfigNativeToJava(ai[i + 4]);
        typedvalue.density = ai[i + 5];
        CharSequence charsequence;
        if(j == 3)
            charsequence = loadStringValueAt(i);
        else
            charsequence = null;
        typedvalue.string = charsequence;
        return true;
    }

    private CharSequence loadStringValueAt(int i)
    {
        int ai[] = mData;
        int j = ai[i + 2];
        if(j < 0)
        {
            if(mXml != null)
                return mXml.getPooledString(ai[i + 1]);
            else
                return null;
        } else
        {
            return mAssets.getPooledStringForCookie(j, ai[i + 1]);
        }
    }

    static TypedArray obtain(Resources resources, int i)
    {
        TypedArray typedarray = (TypedArray)resources.mTypedArrayPool.acquire();
        Object obj = typedarray;
        if(typedarray == null)
            obj = new MiuiTypedArray(resources);
        obj.mRecycled = false;
        obj.mAssets = resources.getAssets();
        obj.mMetrics = resources.getDisplayMetrics();
        ((TypedArray) (obj)).resize(i);
        return ((TypedArray) (obj));
    }

    private void resize(int i)
    {
        mLength = i;
        int j = i * 6;
        VMRuntime vmruntime = VMRuntime.getRuntime();
        if(mDataAddress == 0L || mData.length < j)
        {
            mData = (int[])vmruntime.newNonMovableArray(Integer.TYPE, j);
            mDataAddress = vmruntime.addressOf(mData);
            mIndices = (int[])vmruntime.newNonMovableArray(Integer.TYPE, i + 1);
            mIndicesAddress = vmruntime.addressOf(mIndices);
        }
    }

    public int[] extractThemeAttrs()
    {
        return extractThemeAttrs(null);
    }

    public int[] extractThemeAttrs(int ai[])
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int ai1[] = null;
        int ai2[] = mData;
        int i = length();
        int j = 0;
        while(j < i) 
        {
            int k = j * 6;
            int ai3[];
            if(ai2[k + 0] != 2)
            {
                ai3 = ai1;
            } else
            {
                ai2[k + 0] = 0;
                k = ai2[k + 1];
                ai3 = ai1;
                if(k != 0)
                {
                    ai3 = ai1;
                    if(ai1 == null)
                        if(ai != null && ai.length == i)
                        {
                            ai3 = ai;
                            Arrays.fill(ai, 0);
                        } else
                        {
                            ai3 = new int[i];
                        }
                    ai3[j] = k;
                }
            }
            j++;
            ai1 = ai3;
        }
        return ai1;
    }

    public boolean getBoolean(int i, boolean flag)
    {
        boolean flag1 = false;
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int j = i * 6;
        int ai[] = mData;
        i = ai[j + 0];
        if(i == 0)
            return flag;
        if(i >= 16 && i <= 31)
        {
            flag = flag1;
            if(ai[j + 1] != 0)
                flag = true;
            return flag;
        }
        TypedValue typedvalue = mValue;
        if(getValueAt(j, typedvalue))
        {
            StrictMode.noteResourceMismatch(typedvalue);
            return XmlUtils.convertValueToBoolean(typedvalue.coerceToString(), flag);
        } else
        {
            throw new RuntimeException((new StringBuilder()).append("getBoolean of bad type: 0x").append(Integer.toHexString(i)).toString());
        }
    }

    public int getChangingConfigurations()
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int i = 0;
        int ai[] = mData;
        int j = length();
        int k = 0;
        while(k < j) 
        {
            int l = k * 6;
            if(ai[l + 0] != 0)
                i |= ActivityInfo.activityInfoConfigNativeToJava(ai[l + 4]);
            k++;
        }
        return i;
    }

    public int getColor(int i, int j)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int k = i * 6;
        int ai[] = mData;
        int l = ai[k + 0];
        if(l == 0)
            return j;
        if(l >= 16 && l <= 31)
            return ai[k + 1];
        if(l == 3)
        {
            TypedValue typedvalue = mValue;
            if(getValueAt(k, typedvalue))
                return mResources.loadColorStateList(typedvalue, typedvalue.resourceId, mTheme).getDefaultColor();
            else
                return j;
        }
        if(l == 2)
        {
            TypedValue typedvalue1 = mValue;
            getValueAt(k, typedvalue1);
            throw new UnsupportedOperationException((new StringBuilder()).append("Failed to resolve attribute at index ").append(i).append(": ").append(typedvalue1).toString());
        } else
        {
            throw new UnsupportedOperationException((new StringBuilder()).append("Can't convert value at index ").append(i).append(" to color: type=0x").append(Integer.toHexString(l)).toString());
        }
    }

    public ColorStateList getColorStateList(int i)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        TypedValue typedvalue = mValue;
        if(getValueAt(i * 6, typedvalue))
        {
            if(typedvalue.type == 2)
                throw new UnsupportedOperationException((new StringBuilder()).append("Failed to resolve attribute at index ").append(i).append(": ").append(typedvalue).toString());
            else
                return mResources.loadColorStateList(typedvalue, typedvalue.resourceId, mTheme);
        } else
        {
            return null;
        }
    }

    public ComplexColor getComplexColor(int i)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        TypedValue typedvalue = mValue;
        if(getValueAt(i * 6, typedvalue))
        {
            if(typedvalue.type == 2)
                throw new UnsupportedOperationException((new StringBuilder()).append("Failed to resolve attribute at index ").append(i).append(": ").append(typedvalue).toString());
            else
                return mResources.loadComplexColor(typedvalue, typedvalue.resourceId, mTheme);
        } else
        {
            return null;
        }
    }

    public float getDimension(int i, float f)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int j = i * 6;
        int ai[] = mData;
        int k = ai[j + 0];
        if(k == 0)
            return f;
        if(k == 5)
            return TypedValue.complexToDimension(ai[j + 1], mMetrics);
        if(k == 2)
        {
            TypedValue typedvalue = mValue;
            getValueAt(j, typedvalue);
            throw new UnsupportedOperationException((new StringBuilder()).append("Failed to resolve attribute at index ").append(i).append(": ").append(typedvalue).toString());
        } else
        {
            throw new UnsupportedOperationException((new StringBuilder()).append("Can't convert value at index ").append(i).append(" to dimension: type=0x").append(Integer.toHexString(k)).toString());
        }
    }

    public int getDimensionPixelOffset(int i, int j)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int k = i * 6;
        int ai[] = mData;
        int l = ai[k + 0];
        if(l == 0)
            return j;
        if(l == 5)
            return TypedValue.complexToDimensionPixelOffset(ai[k + 1], mMetrics);
        if(l == 2)
        {
            TypedValue typedvalue = mValue;
            getValueAt(k, typedvalue);
            throw new UnsupportedOperationException((new StringBuilder()).append("Failed to resolve attribute at index ").append(i).append(": ").append(typedvalue).toString());
        } else
        {
            throw new UnsupportedOperationException((new StringBuilder()).append("Can't convert value at index ").append(i).append(" to dimension: type=0x").append(Integer.toHexString(l)).toString());
        }
    }

    public int getDimensionPixelSize(int i, int j)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int k = i * 6;
        int ai[] = mData;
        int l = ai[k + 0];
        if(l == 0)
            return j;
        if(l == 5)
            return TypedValue.complexToDimensionPixelSize(ai[k + 1], mMetrics);
        if(l == 2)
        {
            TypedValue typedvalue = mValue;
            getValueAt(k, typedvalue);
            throw new UnsupportedOperationException((new StringBuilder()).append("Failed to resolve attribute at index ").append(i).append(": ").append(typedvalue).toString());
        } else
        {
            throw new UnsupportedOperationException((new StringBuilder()).append("Can't convert value at index ").append(i).append(" to dimension: type=0x").append(Integer.toHexString(l)).toString());
        }
    }

    public Drawable getDrawable(int i)
    {
        return getDrawableForDensity(i, 0);
    }

    public Drawable getDrawableForDensity(int i, int j)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        TypedValue typedvalue = mValue;
        if(getValueAt(i * 6, typedvalue))
        {
            if(typedvalue.type == 2)
                throw new UnsupportedOperationException((new StringBuilder()).append("Failed to resolve attribute at index ").append(i).append(": ").append(typedvalue).toString());
            if(j > 0)
                mResources.getValueForDensity(typedvalue.resourceId, j, typedvalue, true);
            return mResources.loadDrawable(typedvalue, typedvalue.resourceId, j, mTheme);
        } else
        {
            return null;
        }
    }

    public float getFloat(int i, float f)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        i *= 6;
        int ai[] = mData;
        int j = ai[i + 0];
        if(j == 0)
            return f;
        if(j == 4)
            return Float.intBitsToFloat(ai[i + 1]);
        if(j >= 16 && j <= 31)
            return (float)ai[i + 1];
        TypedValue typedvalue = mValue;
        if(getValueAt(i, typedvalue))
        {
            CharSequence charsequence = typedvalue.coerceToString();
            if(charsequence != null)
            {
                StrictMode.noteResourceMismatch(typedvalue);
                return Float.parseFloat(charsequence.toString());
            }
        }
        throw new RuntimeException((new StringBuilder()).append("getFloat of bad type: 0x").append(Integer.toHexString(j)).toString());
    }

    public Typeface getFont(int i)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        TypedValue typedvalue = mValue;
        if(getValueAt(i * 6, typedvalue))
        {
            if(typedvalue.type == 2)
                throw new UnsupportedOperationException((new StringBuilder()).append("Failed to resolve attribute at index ").append(i).append(": ").append(typedvalue).toString());
            else
                return mResources.getFont(typedvalue, typedvalue.resourceId);
        } else
        {
            return null;
        }
    }

    public float getFraction(int i, int j, int k, float f)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int l = i * 6;
        int ai[] = mData;
        int i1 = ai[l + 0];
        if(i1 == 0)
            return f;
        if(i1 == 6)
            return TypedValue.complexToFraction(ai[l + 1], j, k);
        if(i1 == 2)
        {
            TypedValue typedvalue = mValue;
            getValueAt(l, typedvalue);
            throw new UnsupportedOperationException((new StringBuilder()).append("Failed to resolve attribute at index ").append(i).append(": ").append(typedvalue).toString());
        } else
        {
            throw new UnsupportedOperationException((new StringBuilder()).append("Can't convert value at index ").append(i).append(" to fraction: type=0x").append(Integer.toHexString(i1)).toString());
        }
    }

    public int getIndex(int i)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        else
            return mIndices[i + 1];
    }

    public int getIndexCount()
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        else
            return mIndices[0];
    }

    public int getInt(int i, int j)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        i *= 6;
        int ai[] = mData;
        int k = ai[i + 0];
        if(k == 0)
            return j;
        if(k >= 16 && k <= 31)
            return ai[i + 1];
        TypedValue typedvalue = mValue;
        if(getValueAt(i, typedvalue))
        {
            StrictMode.noteResourceMismatch(typedvalue);
            return XmlUtils.convertValueToInt(typedvalue.coerceToString(), j);
        } else
        {
            throw new RuntimeException((new StringBuilder()).append("getInt of bad type: 0x").append(Integer.toHexString(k)).toString());
        }
    }

    public int getInteger(int i, int j)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int k = i * 6;
        int ai[] = mData;
        int l = ai[k + 0];
        if(l == 0)
            return j;
        if(l >= 16 && l <= 31)
            return ai[k + 1];
        if(l == 2)
        {
            TypedValue typedvalue = mValue;
            getValueAt(k, typedvalue);
            throw new UnsupportedOperationException((new StringBuilder()).append("Failed to resolve attribute at index ").append(i).append(": ").append(typedvalue).toString());
        } else
        {
            throw new UnsupportedOperationException((new StringBuilder()).append("Can't convert value at index ").append(i).append(" to integer: type=0x").append(Integer.toHexString(l)).toString());
        }
    }

    public int getLayoutDimension(int i, int j)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        i *= 6;
        int ai[] = mData;
        int k = ai[i + 0];
        if(k >= 16 && k <= 31)
            return ai[i + 1];
        if(k == 5)
            return TypedValue.complexToDimensionPixelSize(ai[i + 1], mMetrics);
        else
            return j;
    }

    public int getLayoutDimension(int i, String s)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int j = i * 6;
        int ai[] = mData;
        int k = ai[j + 0];
        if(k >= 16 && k <= 31)
            return ai[j + 1];
        if(k == 5)
            return TypedValue.complexToDimensionPixelSize(ai[j + 1], mMetrics);
        if(k == 2)
        {
            s = mValue;
            getValueAt(j, s);
            throw new UnsupportedOperationException((new StringBuilder()).append("Failed to resolve attribute at index ").append(i).append(": ").append(s).toString());
        } else
        {
            throw new UnsupportedOperationException((new StringBuilder()).append(getPositionDescription()).append(": You must supply a ").append(s).append(" attribute.").toString());
        }
    }

    public String getNonConfigurationString(int i, int j)
    {
        String s = null;
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int k = i * 6;
        int ai[] = mData;
        i = ai[k + 0];
        if((j & ActivityInfo.activityInfoConfigNativeToJava(ai[k + 4])) != 0)
            return null;
        if(i == 0)
            return null;
        if(i == 3)
            return loadStringValueAt(k).toString();
        Object obj = mValue;
        if(getValueAt(k, ((TypedValue) (obj))))
        {
            obj = ((TypedValue) (obj)).coerceToString();
            if(obj != null)
                s = ((CharSequence) (obj)).toString();
            return s;
        } else
        {
            throw new RuntimeException((new StringBuilder()).append("getNonConfigurationString of bad type: 0x").append(Integer.toHexString(i)).toString());
        }
    }

    public String getNonResourceString(int i)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        i *= 6;
        int ai[] = mData;
        if(ai[i + 0] == 3 && ai[i + 2] < 0)
            return mXml.getPooledString(ai[i + 1]).toString();
        else
            return null;
    }

    public String getPositionDescription()
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        String s;
        if(mXml != null)
            s = mXml.getPositionDescription();
        else
            s = "<internal>";
        return s;
    }

    public int getResourceId(int i, int j)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        i *= 6;
        int ai[] = mData;
        if(ai[i + 0] != 0)
        {
            i = ai[i + 3];
            if(i != 0)
                return i;
        }
        return j;
    }

    public Resources getResources()
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        else
            return mResources;
    }

    public String getString(int i)
    {
        String s = null;
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int j = i * 6;
        i = mData[j + 0];
        if(i == 0)
            return null;
        if(i == 3)
            return loadStringValueAt(j).toString();
        Object obj = mValue;
        if(getValueAt(j, ((TypedValue) (obj))))
        {
            obj = ((TypedValue) (obj)).coerceToString();
            if(obj != null)
                s = ((CharSequence) (obj)).toString();
            return s;
        } else
        {
            throw new RuntimeException((new StringBuilder()).append("getString of bad type: 0x").append(Integer.toHexString(i)).toString());
        }
    }

    public CharSequence getText(int i)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        int j = i * 6;
        i = mData[j + 0];
        if(i == 0)
            return null;
        if(i == 3)
            return loadStringValueAt(j);
        TypedValue typedvalue = mValue;
        if(getValueAt(j, typedvalue))
            return typedvalue.coerceToString();
        else
            throw new RuntimeException((new StringBuilder()).append("getText of bad type: 0x").append(Integer.toHexString(i)).toString());
    }

    public CharSequence[] getTextArray(int i)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        TypedValue typedvalue = mValue;
        if(getValueAt(i * 6, typedvalue))
            return mResources.getTextArray(typedvalue.resourceId);
        else
            return null;
    }

    public int getThemeAttributeId(int i, int j)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        i *= 6;
        int ai[] = mData;
        if(ai[i + 0] == 2)
            return ai[i + 1];
        else
            return j;
    }

    public int getType(int i)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        else
            return mData[i * 6 + 0];
    }

    public boolean getValue(int i, TypedValue typedvalue)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        else
            return getValueAt(i * 6, typedvalue);
    }

    public boolean hasValue(int i)
    {
        boolean flag = false;
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        if(mData[i * 6 + 0] != 0)
            flag = true;
        return flag;
    }

    public boolean hasValueOrEmpty(int i)
    {
        boolean flag = true;
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        i *= 6;
        int ai[] = mData;
        boolean flag1 = flag;
        if(ai[i + 0] == 0)
            if(ai[i + 1] == 1)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public int length()
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        else
            return mLength;
    }

    public TypedValue peekValue(int i)
    {
        if(mRecycled)
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        TypedValue typedvalue = mValue;
        if(getValueAt(i * 6, typedvalue))
            return typedvalue;
        else
            return null;
    }

    public void recycle()
    {
        if(mRecycled)
        {
            throw new RuntimeException((new StringBuilder()).append(toString()).append(" recycled twice!").toString());
        } else
        {
            mRecycled = true;
            mXml = null;
            mTheme = null;
            mAssets = null;
            mResources.mTypedArrayPool.release(this);
            return;
        }
    }

    public String toString()
    {
        return Arrays.toString(mData);
    }

    private AssetManager mAssets;
    int mData[];
    long mDataAddress;
    int mIndices[];
    long mIndicesAddress;
    int mLength;
    private DisplayMetrics mMetrics;
    private boolean mRecycled;
    private final Resources mResources;
    Resources.Theme mTheme;
    TypedValue mValue;
    XmlBlock.Parser mXml;
}
