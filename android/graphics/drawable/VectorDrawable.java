// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.*;
import android.graphics.*;
import android.os.Trace;
import android.util.*;
import com.android.internal.util.VirtualRefBasePtr;
import dalvik.system.VMRuntime;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            Drawable

public class VectorDrawable extends Drawable
{
    private static class VClipPath extends VPath
    {

        private void updateStateFromTypedArray(TypedArray typedarray)
        {
            mChangingConfigurations = mChangingConfigurations | typedarray.getChangingConfigurations();
            String s = typedarray.getString(0);
            if(s != null)
            {
                mPathName = s;
                VectorDrawable._2D_wrap30(mNativePtr, mPathName);
            }
            typedarray = typedarray.getString(1);
            if(typedarray != null)
            {
                mPathData = new android.util.PathParser.PathData(typedarray);
                VectorDrawable._2D_wrap32(mNativePtr, typedarray, typedarray.length());
            }
        }

        public void applyTheme(android.content.res.Resources.Theme theme)
        {
        }

        public boolean canApplyTheme()
        {
            return false;
        }

        public long getNativePtr()
        {
            return mNativePtr;
        }

        int getNativeSize()
        {
            return 120;
        }

        public boolean hasFocusStateSpecified()
        {
            return false;
        }

        public void inflate(Resources resources, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        {
            resources = VectorDrawable.obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.VectorDrawableClipPath);
            updateStateFromTypedArray(resources);
            resources.recycle();
        }

        public boolean isStateful()
        {
            return false;
        }

        public boolean onStateChange(int ai[])
        {
            return false;
        }

        private static final int NATIVE_ALLOCATION_SIZE = 120;
        private final long mNativePtr;

        public VClipPath()
        {
            mNativePtr = VectorDrawable._2D_wrap19();
        }

        public VClipPath(VClipPath vclippath)
        {
            super(vclippath);
            mNativePtr = VectorDrawable._2D_wrap20(vclippath.mNativePtr);
        }
    }

    static class VFullPath extends VPath
    {

        static Property _2D_get0()
        {
            return FILL_ALPHA;
        }

        static Property _2D_get1()
        {
            return FILL_COLOR;
        }

        static Property _2D_get2()
        {
            return STROKE_ALPHA;
        }

        static Property _2D_get3()
        {
            return STROKE_COLOR;
        }

        static Property _2D_get4()
        {
            return STROKE_WIDTH;
        }

        static Property _2D_get5()
        {
            return TRIM_PATH_END;
        }

        static Property _2D_get6()
        {
            return TRIM_PATH_OFFSET;
        }

        static Property _2D_get7()
        {
            return TRIM_PATH_START;
        }

        private boolean canComplexColorApplyTheme(ComplexColor complexcolor)
        {
            boolean flag;
            if(complexcolor != null)
                flag = complexcolor.canApplyTheme();
            else
                flag = false;
            return flag;
        }

        private void updateStateFromTypedArray(TypedArray typedarray)
        {
            if(mPropertyData == null)
                mPropertyData = new byte[48];
            if(!VectorDrawable._2D_wrap0(mNativePtr, mPropertyData, 48))
                throw new RuntimeException("Error: inconsistent property count");
            Object obj = ByteBuffer.wrap(mPropertyData);
            ((ByteBuffer) (obj)).order(ByteOrder.nativeOrder());
            float f = ((ByteBuffer) (obj)).getFloat(0);
            int i = ((ByteBuffer) (obj)).getInt(4);
            float f1 = ((ByteBuffer) (obj)).getFloat(8);
            int j = ((ByteBuffer) (obj)).getInt(12);
            float f2 = ((ByteBuffer) (obj)).getFloat(16);
            float f3 = ((ByteBuffer) (obj)).getFloat(20);
            float f4 = ((ByteBuffer) (obj)).getFloat(24);
            float f5 = ((ByteBuffer) (obj)).getFloat(28);
            int k = ((ByteBuffer) (obj)).getInt(32);
            int l = ((ByteBuffer) (obj)).getInt(36);
            float f6 = ((ByteBuffer) (obj)).getFloat(40);
            int i1 = ((ByteBuffer) (obj)).getInt(44);
            Shader shader = null;
            obj = null;
            Object obj1 = null;
            Object obj2 = null;
            mChangingConfigurations = mChangingConfigurations | typedarray.getChangingConfigurations();
            mThemeAttrs = typedarray.extractThemeAttrs();
            Object obj3 = typedarray.getString(0);
            if(obj3 != null)
            {
                mPathName = ((String) (obj3));
                VectorDrawable._2D_wrap30(mNativePtr, mPathName);
            }
            obj3 = typedarray.getString(2);
            if(obj3 != null)
            {
                mPathData = new android.util.PathParser.PathData(((String) (obj3)));
                VectorDrawable._2D_wrap32(mNativePtr, ((String) (obj3)), ((String) (obj3)).length());
            }
            obj3 = typedarray.getComplexColor(1);
            long l2;
            if(obj3 != null)
            {
                long l1;
                if(obj3 instanceof GradientColor)
                {
                    mFillColors = ((ComplexColor) (obj3));
                    obj = ((GradientColor)obj3).getShader();
                } else
                if(((ComplexColor) (obj3)).isStateful())
                    mFillColors = ((ComplexColor) (obj3));
                else
                    mFillColors = null;
                j = ((ComplexColor) (obj3)).getDefaultColor();
                shader = ((Shader) (obj));
            }
            obj3 = typedarray.getComplexColor(3);
            obj = obj1;
            if(obj3 != null)
            {
                if(obj3 instanceof GradientColor)
                {
                    mStrokeColors = ((ComplexColor) (obj3));
                    obj = ((GradientColor)obj3).getShader();
                } else
                if(((ComplexColor) (obj3)).isStateful())
                {
                    mStrokeColors = ((ComplexColor) (obj3));
                    obj = obj2;
                } else
                {
                    mStrokeColors = null;
                    obj = obj2;
                }
                i = ((ComplexColor) (obj3)).getDefaultColor();
            }
            l1 = mNativePtr;
            if(shader != null)
                l2 = shader.getNativeInstance();
            else
                l2 = 0L;
            VectorDrawable._2D_wrap47(l1, l2);
            l1 = mNativePtr;
            if(obj != null)
                l2 = ((Shader) (obj)).getNativeInstance();
            else
                l2 = 0L;
            VectorDrawable._2D_wrap49(l1, l2);
            f2 = typedarray.getFloat(12, f2);
            k = typedarray.getInt(8, k);
            l = typedarray.getInt(9, l);
            f6 = typedarray.getFloat(10, f6);
            f1 = typedarray.getFloat(11, f1);
            f = typedarray.getFloat(4, f);
            f4 = typedarray.getFloat(6, f4);
            f5 = typedarray.getFloat(7, f5);
            f3 = typedarray.getFloat(5, f3);
            i1 = typedarray.getInt(13, i1);
            VectorDrawable._2D_wrap48(mNativePtr, f, i, f1, j, f2, f3, f4, f5, f6, k, l, i1);
        }

        public void applyTheme(android.content.res.Resources.Theme theme)
        {
            if(mThemeAttrs != null)
            {
                TypedArray typedarray = theme.resolveAttributes(mThemeAttrs, com.android.internal.R.styleable.VectorDrawablePath);
                updateStateFromTypedArray(typedarray);
                typedarray.recycle();
            }
            boolean flag = canComplexColorApplyTheme(mFillColors);
            boolean flag1 = canComplexColorApplyTheme(mStrokeColors);
            if(flag)
            {
                mFillColors = mFillColors.obtainForTheme(theme);
                if(mFillColors instanceof GradientColor)
                    VectorDrawable._2D_wrap47(mNativePtr, ((GradientColor)mFillColors).getShader().getNativeInstance());
                else
                if(mFillColors instanceof ColorStateList)
                    VectorDrawable._2D_wrap29(mNativePtr, mFillColors.getDefaultColor());
            }
            if(flag1)
            {
                mStrokeColors = mStrokeColors.obtainForTheme(theme);
                if(mStrokeColors instanceof GradientColor)
                    VectorDrawable._2D_wrap49(mNativePtr, ((GradientColor)mStrokeColors).getShader().getNativeInstance());
                else
                if(mStrokeColors instanceof ColorStateList)
                    VectorDrawable._2D_wrap40(mNativePtr, mStrokeColors.getDefaultColor());
            }
        }

        public boolean canApplyTheme()
        {
            if(mThemeAttrs != null)
                return true;
            boolean flag = canComplexColorApplyTheme(mFillColors);
            boolean flag1 = canComplexColorApplyTheme(mStrokeColors);
            return flag || flag1;
        }

        float getFillAlpha()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap3(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        int getFillColor()
        {
            int i;
            if(isTreeValid())
                i = VectorDrawable._2D_wrap17(mNativePtr);
            else
                i = 0;
            return i;
        }

        public long getNativePtr()
        {
            return mNativePtr;
        }

        int getNativeSize()
        {
            return 264;
        }

        Property getProperty(String s)
        {
            Property property = super.getProperty(s);
            if(property != null)
                return property;
            if(sPropertyMap.containsKey(s))
                return (Property)sPropertyMap.get(s);
            else
                return null;
        }

        int getPropertyIndex(String s)
        {
            if(!sPropertyIndexMap.containsKey(s))
                return -1;
            else
                return ((Integer)sPropertyIndexMap.get(s)).intValue();
        }

        float getStrokeAlpha()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap10(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        int getStrokeColor()
        {
            int i;
            if(isTreeValid())
                i = VectorDrawable._2D_wrap18(mNativePtr);
            else
                i = 0;
            return i;
        }

        float getStrokeWidth()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap11(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        float getTrimPathEnd()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap14(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        float getTrimPathOffset()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap15(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        float getTrimPathStart()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap16(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        public boolean hasFocusStateSpecified()
        {
            boolean flag;
            if(mStrokeColors != null && (mStrokeColors instanceof ColorStateList) && ((ColorStateList)mStrokeColors).hasFocusStateSpecified())
            {
                if(mFillColors != null && (mFillColors instanceof ColorStateList))
                    flag = ((ColorStateList)mFillColors).hasFocusStateSpecified();
                else
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public void inflate(Resources resources, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        {
            resources = VectorDrawable.obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.VectorDrawablePath);
            updateStateFromTypedArray(resources);
            resources.recycle();
        }

        public boolean isStateful()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(mStrokeColors == null)
                if(mFillColors != null)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public boolean onStateChange(int ai[])
        {
            boolean flag = false;
            boolean flag2 = flag;
            if(mStrokeColors != null)
            {
                flag2 = flag;
                if(mStrokeColors instanceof ColorStateList)
                {
                    int i = getStrokeColor();
                    int j = ((ColorStateList)mStrokeColors).getColorForState(ai, i);
                    boolean flag1;
                    int k;
                    if(i != j)
                        flag1 = true;
                    else
                        flag1 = false;
                    flag2 = flag1;
                    if(i != j)
                    {
                        VectorDrawable._2D_wrap40(mNativePtr, j);
                        flag2 = flag1;
                    }
                }
            }
            flag1 = flag2;
            if(mFillColors != null)
            {
                flag1 = flag2;
                if(mFillColors instanceof ColorStateList)
                {
                    k = getFillColor();
                    i = ((ColorStateList)mFillColors).getColorForState(ai, k);
                    boolean flag3;
                    if(k != i)
                        flag3 = true;
                    else
                        flag3 = false;
                    flag2 |= flag3;
                    flag1 = flag2;
                    if(k != i)
                    {
                        VectorDrawable._2D_wrap29(mNativePtr, i);
                        flag1 = flag2;
                    }
                }
            }
            return flag1;
        }

        void setFillAlpha(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap28(mNativePtr, f);
        }

        void setFillColor(int i)
        {
            mFillColors = null;
            if(isTreeValid())
                VectorDrawable._2D_wrap29(mNativePtr, i);
        }

        void setStrokeAlpha(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap39(mNativePtr, f);
        }

        void setStrokeColor(int i)
        {
            mStrokeColors = null;
            if(isTreeValid())
                VectorDrawable._2D_wrap40(mNativePtr, i);
        }

        void setStrokeWidth(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap41(mNativePtr, f);
        }

        void setTrimPathEnd(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap44(mNativePtr, f);
        }

        void setTrimPathOffset(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap45(mNativePtr, f);
        }

        void setTrimPathStart(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap46(mNativePtr, f);
        }

        private static final Property FILL_ALPHA = new FloatProperty("fillAlpha") {

            public Float get(VFullPath vfullpath)
            {
                return Float.valueOf(vfullpath.getFillAlpha());
            }

            public volatile Object get(Object obj)
            {
                return get((VFullPath)obj);
            }

            public void setValue(VFullPath vfullpath, float f)
            {
                vfullpath.setFillAlpha(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VFullPath)obj, f);
            }

        }
;
        private static final int FILL_ALPHA_INDEX = 4;
        private static final Property FILL_COLOR = new IntProperty("fillColor") {

            public Integer get(VFullPath vfullpath)
            {
                return Integer.valueOf(vfullpath.getFillColor());
            }

            public volatile Object get(Object obj)
            {
                return get((VFullPath)obj);
            }

            public void setValue(VFullPath vfullpath, int i)
            {
                vfullpath.setFillColor(i);
            }

            public volatile void setValue(Object obj, int i)
            {
                setValue((VFullPath)obj, i);
            }

        }
;
        private static final int FILL_COLOR_INDEX = 3;
        private static final int FILL_TYPE_INDEX = 11;
        private static final int NATIVE_ALLOCATION_SIZE = 264;
        private static final Property STROKE_ALPHA = new FloatProperty("strokeAlpha") {

            public Float get(VFullPath vfullpath)
            {
                return Float.valueOf(vfullpath.getStrokeAlpha());
            }

            public volatile Object get(Object obj)
            {
                return get((VFullPath)obj);
            }

            public void setValue(VFullPath vfullpath, float f)
            {
                vfullpath.setStrokeAlpha(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VFullPath)obj, f);
            }

        }
;
        private static final int STROKE_ALPHA_INDEX = 2;
        private static final Property STROKE_COLOR = new IntProperty("strokeColor") {

            public Integer get(VFullPath vfullpath)
            {
                return Integer.valueOf(vfullpath.getStrokeColor());
            }

            public volatile Object get(Object obj)
            {
                return get((VFullPath)obj);
            }

            public void setValue(VFullPath vfullpath, int i)
            {
                vfullpath.setStrokeColor(i);
            }

            public volatile void setValue(Object obj, int i)
            {
                setValue((VFullPath)obj, i);
            }

        }
;
        private static final int STROKE_COLOR_INDEX = 1;
        private static final int STROKE_LINE_CAP_INDEX = 8;
        private static final int STROKE_LINE_JOIN_INDEX = 9;
        private static final int STROKE_MITER_LIMIT_INDEX = 10;
        private static final Property STROKE_WIDTH = new FloatProperty("strokeWidth") {

            public Float get(VFullPath vfullpath)
            {
                return Float.valueOf(vfullpath.getStrokeWidth());
            }

            public volatile Object get(Object obj)
            {
                return get((VFullPath)obj);
            }

            public void setValue(VFullPath vfullpath, float f)
            {
                vfullpath.setStrokeWidth(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VFullPath)obj, f);
            }

        }
;
        private static final int STROKE_WIDTH_INDEX = 0;
        private static final int TOTAL_PROPERTY_COUNT = 12;
        private static final Property TRIM_PATH_END = new FloatProperty("trimPathEnd") {

            public Float get(VFullPath vfullpath)
            {
                return Float.valueOf(vfullpath.getTrimPathEnd());
            }

            public volatile Object get(Object obj)
            {
                return get((VFullPath)obj);
            }

            public void setValue(VFullPath vfullpath, float f)
            {
                vfullpath.setTrimPathEnd(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VFullPath)obj, f);
            }

        }
;
        private static final int TRIM_PATH_END_INDEX = 6;
        private static final Property TRIM_PATH_OFFSET = new FloatProperty("trimPathOffset") {

            public Float get(VFullPath vfullpath)
            {
                return Float.valueOf(vfullpath.getTrimPathOffset());
            }

            public volatile Object get(Object obj)
            {
                return get((VFullPath)obj);
            }

            public void setValue(VFullPath vfullpath, float f)
            {
                vfullpath.setTrimPathOffset(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VFullPath)obj, f);
            }

        }
;
        private static final int TRIM_PATH_OFFSET_INDEX = 7;
        private static final Property TRIM_PATH_START = new FloatProperty("trimPathStart") {

            public Float get(VFullPath vfullpath)
            {
                return Float.valueOf(vfullpath.getTrimPathStart());
            }

            public volatile Object get(Object obj)
            {
                return get((VFullPath)obj);
            }

            public void setValue(VFullPath vfullpath, float f)
            {
                vfullpath.setTrimPathStart(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VFullPath)obj, f);
            }

        }
;
        private static final int TRIM_PATH_START_INDEX = 5;
        private static final HashMap sPropertyIndexMap = new HashMap() {

            
            {
                put("strokeWidth", Integer.valueOf(0));
                put("strokeColor", Integer.valueOf(1));
                put("strokeAlpha", Integer.valueOf(2));
                put("fillColor", Integer.valueOf(3));
                put("fillAlpha", Integer.valueOf(4));
                put("trimPathStart", Integer.valueOf(5));
                put("trimPathEnd", Integer.valueOf(6));
                put("trimPathOffset", Integer.valueOf(7));
            }
        }
;
        private static final HashMap sPropertyMap = new HashMap() {

            
            {
                put("strokeWidth", VFullPath._2D_get4());
                put("strokeColor", VFullPath._2D_get3());
                put("strokeAlpha", VFullPath._2D_get2());
                put("fillColor", VFullPath._2D_get1());
                put("fillAlpha", VFullPath._2D_get0());
                put("trimPathStart", VFullPath._2D_get7());
                put("trimPathEnd", VFullPath._2D_get5());
                put("trimPathOffset", VFullPath._2D_get6());
            }
        }
;
        ComplexColor mFillColors;
        private final long mNativePtr;
        private byte mPropertyData[];
        ComplexColor mStrokeColors;
        private int mThemeAttrs[];


        public VFullPath()
        {
            mStrokeColors = null;
            mFillColors = null;
            mNativePtr = VectorDrawable._2D_wrap21();
        }

        public VFullPath(VFullPath vfullpath)
        {
            super(vfullpath);
            mStrokeColors = null;
            mFillColors = null;
            mNativePtr = VectorDrawable._2D_wrap22(vfullpath.mNativePtr);
            mThemeAttrs = vfullpath.mThemeAttrs;
            mStrokeColors = vfullpath.mStrokeColors;
            mFillColors = vfullpath.mFillColors;
        }
    }

    static class VGroup extends VObject
    {

        static Property _2D_get0()
        {
            return PIVOT_X;
        }

        static Property _2D_get1()
        {
            return PIVOT_Y;
        }

        static Property _2D_get2()
        {
            return ROTATION;
        }

        static Property _2D_get3()
        {
            return SCALE_X;
        }

        static Property _2D_get4()
        {
            return SCALE_Y;
        }

        static Property _2D_get5()
        {
            return TRANSLATE_X;
        }

        static Property _2D_get6()
        {
            return TRANSLATE_Y;
        }

        static int _2D_get7(VGroup vgroup)
        {
            return vgroup.mChangingConfigurations;
        }

        static long _2D_get8(VGroup vgroup)
        {
            return vgroup.mNativePtr;
        }

        static int getPropertyIndex(String s)
        {
            if(sPropertyIndexMap.containsKey(s))
                return ((Integer)sPropertyIndexMap.get(s)).intValue();
            else
                return -1;
        }

        public void addChild(VObject vobject)
        {
            VectorDrawable._2D_wrap27(mNativePtr, vobject.getNativePtr());
            mChildren.add(vobject);
            mIsStateful = mIsStateful | vobject.isStateful();
        }

        public void applyTheme(android.content.res.Resources.Theme theme)
        {
            if(mThemeAttrs != null)
            {
                TypedArray typedarray = theme.resolveAttributes(mThemeAttrs, com.android.internal.R.styleable.VectorDrawableGroup);
                updateStateFromTypedArray(typedarray);
                typedarray.recycle();
            }
            ArrayList arraylist = mChildren;
            int i = 0;
            for(int j = arraylist.size(); i < j; i++)
            {
                VObject vobject = (VObject)arraylist.get(i);
                if(vobject.canApplyTheme())
                {
                    vobject.applyTheme(theme);
                    mIsStateful = mIsStateful | vobject.isStateful();
                }
            }

        }

        public boolean canApplyTheme()
        {
            if(mThemeAttrs != null)
                return true;
            ArrayList arraylist = mChildren;
            int i = 0;
            for(int j = arraylist.size(); i < j; i++)
                if(((VObject)arraylist.get(i)).canApplyTheme())
                    return true;

            return false;
        }

        public String getGroupName()
        {
            return mGroupName;
        }

        public long getNativePtr()
        {
            return mNativePtr;
        }

        int getNativeSize()
        {
            int i = 100;
            for(int j = 0; j < mChildren.size(); j++)
                i += ((VObject)mChildren.get(j)).getNativeSize();

            return i;
        }

        public float getPivotX()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap4(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        public float getPivotY()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap5(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        Property getProperty(String s)
        {
            if(sPropertyMap.containsKey(s))
                return (Property)sPropertyMap.get(s);
            else
                return null;
        }

        public float getRotation()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap7(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        public float getScaleX()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap8(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        public float getScaleY()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap9(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        public float getTranslateX()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap12(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        public float getTranslateY()
        {
            float f;
            if(isTreeValid())
                f = VectorDrawable._2D_wrap13(mNativePtr);
            else
                f = 0.0F;
            return f;
        }

        public boolean hasFocusStateSpecified()
        {
            boolean flag = false;
            ArrayList arraylist = mChildren;
            int i = 0;
            for(int j = arraylist.size(); i < j;)
            {
                VObject vobject = (VObject)arraylist.get(i);
                boolean flag1 = flag;
                if(vobject.isStateful())
                    flag1 = flag | vobject.hasFocusStateSpecified();
                i++;
                flag = flag1;
            }

            return flag;
        }

        public void inflate(Resources resources, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        {
            resources = VectorDrawable.obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.VectorDrawableGroup);
            updateStateFromTypedArray(resources);
            resources.recycle();
        }

        public boolean isStateful()
        {
            return mIsStateful;
        }

        public boolean onStateChange(int ai[])
        {
            boolean flag = false;
            ArrayList arraylist = mChildren;
            int i = 0;
            for(int j = arraylist.size(); i < j;)
            {
                VObject vobject = (VObject)arraylist.get(i);
                boolean flag1 = flag;
                if(vobject.isStateful())
                    flag1 = flag | vobject.onStateChange(ai);
                i++;
                flag = flag1;
            }

            return flag;
        }

        public void setPivotX(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap33(mNativePtr, f);
        }

        public void setPivotY(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap34(mNativePtr, f);
        }

        public void setRotation(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap36(mNativePtr, f);
        }

        public void setScaleX(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap37(mNativePtr, f);
        }

        public void setScaleY(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap38(mNativePtr, f);
        }

        public void setTranslateX(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap42(mNativePtr, f);
        }

        public void setTranslateY(float f)
        {
            if(isTreeValid())
                VectorDrawable._2D_wrap43(mNativePtr, f);
        }

        public void setTree(VirtualRefBasePtr virtualrefbaseptr)
        {
            super.setTree(virtualrefbaseptr);
            for(int i = 0; i < mChildren.size(); i++)
                ((VObject)mChildren.get(i)).setTree(virtualrefbaseptr);

        }

        void updateStateFromTypedArray(TypedArray typedarray)
        {
            mChangingConfigurations = mChangingConfigurations | typedarray.getChangingConfigurations();
            mThemeAttrs = typedarray.extractThemeAttrs();
            if(mTransform == null)
                mTransform = new float[7];
            if(!VectorDrawable._2D_wrap1(mNativePtr, mTransform, 7))
                throw new RuntimeException("Error: inconsistent property count");
            float f = typedarray.getFloat(5, mTransform[0]);
            float f1 = typedarray.getFloat(1, mTransform[1]);
            float f2 = typedarray.getFloat(2, mTransform[2]);
            float f3 = typedarray.getFloat(3, mTransform[3]);
            float f4 = typedarray.getFloat(4, mTransform[4]);
            float f5 = typedarray.getFloat(6, mTransform[5]);
            float f6 = typedarray.getFloat(7, mTransform[6]);
            typedarray = typedarray.getString(0);
            if(typedarray != null)
            {
                mGroupName = typedarray;
                VectorDrawable._2D_wrap30(mNativePtr, mGroupName);
            }
            VectorDrawable._2D_wrap50(mNativePtr, f, f1, f2, f3, f4, f5, f6);
        }

        private static final int NATIVE_ALLOCATION_SIZE = 100;
        private static final Property PIVOT_X = new FloatProperty("pivotX") {

            public Float get(VGroup vgroup)
            {
                return Float.valueOf(vgroup.getPivotX());
            }

            public volatile Object get(Object obj)
            {
                return get((VGroup)obj);
            }

            public void setValue(VGroup vgroup, float f)
            {
                vgroup.setPivotX(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VGroup)obj, f);
            }

        }
;
        private static final int PIVOT_X_INDEX = 1;
        private static final Property PIVOT_Y = new FloatProperty("pivotY") {

            public Float get(VGroup vgroup)
            {
                return Float.valueOf(vgroup.getPivotY());
            }

            public volatile Object get(Object obj)
            {
                return get((VGroup)obj);
            }

            public void setValue(VGroup vgroup, float f)
            {
                vgroup.setPivotY(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VGroup)obj, f);
            }

        }
;
        private static final int PIVOT_Y_INDEX = 2;
        private static final Property ROTATION = new FloatProperty("rotation") {

            public Float get(VGroup vgroup)
            {
                return Float.valueOf(vgroup.getRotation());
            }

            public volatile Object get(Object obj)
            {
                return get((VGroup)obj);
            }

            public void setValue(VGroup vgroup, float f)
            {
                vgroup.setRotation(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VGroup)obj, f);
            }

        }
;
        private static final int ROTATION_INDEX = 0;
        private static final Property SCALE_X = new FloatProperty("scaleX") {

            public Float get(VGroup vgroup)
            {
                return Float.valueOf(vgroup.getScaleX());
            }

            public volatile Object get(Object obj)
            {
                return get((VGroup)obj);
            }

            public void setValue(VGroup vgroup, float f)
            {
                vgroup.setScaleX(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VGroup)obj, f);
            }

        }
;
        private static final int SCALE_X_INDEX = 3;
        private static final Property SCALE_Y = new FloatProperty("scaleY") {

            public Float get(VGroup vgroup)
            {
                return Float.valueOf(vgroup.getScaleY());
            }

            public volatile Object get(Object obj)
            {
                return get((VGroup)obj);
            }

            public void setValue(VGroup vgroup, float f)
            {
                vgroup.setScaleY(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VGroup)obj, f);
            }

        }
;
        private static final int SCALE_Y_INDEX = 4;
        private static final int TRANSFORM_PROPERTY_COUNT = 7;
        private static final Property TRANSLATE_X = new FloatProperty("translateX") {

            public Float get(VGroup vgroup)
            {
                return Float.valueOf(vgroup.getTranslateX());
            }

            public volatile Object get(Object obj)
            {
                return get((VGroup)obj);
            }

            public void setValue(VGroup vgroup, float f)
            {
                vgroup.setTranslateX(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VGroup)obj, f);
            }

        }
;
        private static final int TRANSLATE_X_INDEX = 5;
        private static final Property TRANSLATE_Y = new FloatProperty("translateY") {

            public Float get(VGroup vgroup)
            {
                return Float.valueOf(vgroup.getTranslateY());
            }

            public volatile Object get(Object obj)
            {
                return get((VGroup)obj);
            }

            public void setValue(VGroup vgroup, float f)
            {
                vgroup.setTranslateY(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VGroup)obj, f);
            }

        }
;
        private static final int TRANSLATE_Y_INDEX = 6;
        private static final HashMap sPropertyIndexMap = new HashMap() {

            
            {
                put("translateX", Integer.valueOf(5));
                put("translateY", Integer.valueOf(6));
                put("scaleX", Integer.valueOf(3));
                put("scaleY", Integer.valueOf(4));
                put("pivotX", Integer.valueOf(1));
                put("pivotY", Integer.valueOf(2));
                put("rotation", Integer.valueOf(0));
            }
        }
;
        private static final HashMap sPropertyMap = new HashMap() {

            
            {
                put("translateX", VGroup._2D_get5());
                put("translateY", VGroup._2D_get6());
                put("scaleX", VGroup._2D_get3());
                put("scaleY", VGroup._2D_get4());
                put("pivotX", VGroup._2D_get0());
                put("pivotY", VGroup._2D_get1());
                put("rotation", VGroup._2D_get2());
            }
        }
;
        private int mChangingConfigurations;
        private final ArrayList mChildren;
        private String mGroupName;
        private boolean mIsStateful;
        private final long mNativePtr;
        private int mThemeAttrs[];
        private float mTransform[];


        public VGroup()
        {
            mChildren = new ArrayList();
            mGroupName = null;
            mNativePtr = VectorDrawable._2D_wrap23();
        }

        public VGroup(VGroup vgroup, ArrayMap arraymap)
        {
            mChildren = new ArrayList();
            mGroupName = null;
            mIsStateful = vgroup.mIsStateful;
            mThemeAttrs = vgroup.mThemeAttrs;
            mGroupName = vgroup.mGroupName;
            mChangingConfigurations = vgroup.mChangingConfigurations;
            if(mGroupName != null)
                arraymap.put(mGroupName, this);
            mNativePtr = VectorDrawable._2D_wrap24(vgroup.mNativePtr);
            ArrayList arraylist = vgroup.mChildren;
            int i = 0;
            do
            {
                if(i < arraylist.size())
                {
                    vgroup = (VObject)arraylist.get(i);
                    if(vgroup instanceof VGroup)
                    {
                        addChild(new VGroup((VGroup)vgroup, arraymap));
                    } else
                    {
                        if(vgroup instanceof VFullPath)
                            vgroup = new VFullPath((VFullPath)vgroup);
                        else
                        if(vgroup instanceof VClipPath)
                            vgroup = new VClipPath((VClipPath)vgroup);
                        else
                            throw new IllegalStateException("Unknown object in the tree!");
                        addChild(vgroup);
                        if(((VPath) (vgroup)).mPathName != null)
                            arraymap.put(((VPath) (vgroup)).mPathName, vgroup);
                    }
                } else
                {
                    return;
                }
                i++;
            } while(true);
        }
    }

    static abstract class VObject
    {

        abstract void applyTheme(android.content.res.Resources.Theme theme);

        abstract boolean canApplyTheme();

        abstract long getNativePtr();

        abstract int getNativeSize();

        abstract Property getProperty(String s);

        abstract boolean hasFocusStateSpecified();

        abstract void inflate(Resources resources, AttributeSet attributeset, android.content.res.Resources.Theme theme);

        abstract boolean isStateful();

        boolean isTreeValid()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mTreePtr != null)
            {
                flag1 = flag;
                if(mTreePtr.get() != 0L)
                    flag1 = true;
            }
            return flag1;
        }

        abstract boolean onStateChange(int ai[]);

        void setTree(VirtualRefBasePtr virtualrefbaseptr)
        {
            mTreePtr = virtualrefbaseptr;
        }

        VirtualRefBasePtr mTreePtr;

        VObject()
        {
            mTreePtr = null;
        }
    }

    static abstract class VPath extends VObject
    {

        public android.util.PathParser.PathData getPathData()
        {
            return mPathData;
        }

        public String getPathName()
        {
            return mPathName;
        }

        Property getProperty(String s)
        {
            if(PATH_DATA.getName().equals(s))
                return PATH_DATA;
            else
                return null;
        }

        public void setPathData(android.util.PathParser.PathData pathdata)
        {
            mPathData.setPathData(pathdata);
            if(isTreeValid())
                VectorDrawable._2D_wrap31(getNativePtr(), mPathData.getNativePtr());
        }

        private static final Property PATH_DATA = new Property(android/util/PathParser$PathData, "pathData") {

            public android.util.PathParser.PathData get(VPath vpath)
            {
                return vpath.getPathData();
            }

            public volatile Object get(Object obj)
            {
                return get((VPath)obj);
            }

            public void set(VPath vpath, android.util.PathParser.PathData pathdata)
            {
                vpath.setPathData(pathdata);
            }

            public volatile void set(Object obj, Object obj1)
            {
                set((VPath)obj, (android.util.PathParser.PathData)obj1);
            }

        }
;
        int mChangingConfigurations;
        protected android.util.PathParser.PathData mPathData;
        String mPathName;


        public VPath()
        {
            mPathData = null;
        }

        public VPath(VPath vpath)
        {
            Object obj = null;
            super();
            mPathData = null;
            mPathName = vpath.mPathName;
            mChangingConfigurations = vpath.mChangingConfigurations;
            if(vpath.mPathData == null)
                vpath = obj;
            else
                vpath = new android.util.PathParser.PathData(vpath.mPathData);
            mPathData = vpath;
        }
    }

    static class VectorDrawableState extends Drawable.ConstantState
    {

        static void _2D_wrap0(VectorDrawableState vectordrawablestate, VGroup vgroup)
        {
            vectordrawablestate.createNativeTree(vgroup);
        }

        private void applyDensityScaling(int i, int j)
        {
            mBaseWidth = Drawable.scaleFromDensity(mBaseWidth, i, j, true);
            mBaseHeight = Drawable.scaleFromDensity(mBaseHeight, i, j, true);
            mOpticalInsets = Insets.of(Drawable.scaleFromDensity(mOpticalInsets.left, i, j, false), Drawable.scaleFromDensity(mOpticalInsets.top, i, j, false), Drawable.scaleFromDensity(mOpticalInsets.right, i, j, false), Drawable.scaleFromDensity(mOpticalInsets.bottom, i, j, false));
        }

        private void createNativeTree(VGroup vgroup)
        {
            mNativeTree = new VirtualRefBasePtr(VectorDrawable._2D_wrap26(VGroup._2D_get8(vgroup)));
            VMRuntime.getRuntime().registerNativeAllocation(316);
        }

        private void createNativeTreeFromCopy(VectorDrawableState vectordrawablestate, VGroup vgroup)
        {
            mNativeTree = new VirtualRefBasePtr(VectorDrawable._2D_wrap25(vectordrawablestate.mNativeTree.get(), VGroup._2D_get8(vgroup)));
            VMRuntime.getRuntime().registerNativeAllocation(316);
        }

        public void applyTheme(android.content.res.Resources.Theme theme)
        {
            mRootGroup.applyTheme(theme);
        }

        public boolean canApplyTheme()
        {
            boolean flag;
            if(mThemeAttrs == null && (mRootGroup == null || !mRootGroup.canApplyTheme()) && (mTint == null || !mTint.canApplyTheme()))
                flag = super.canApplyTheme();
            else
                flag = true;
            return flag;
        }

        public boolean canReuseCache()
        {
            if(!mCacheDirty && mCachedThemeAttrs == mThemeAttrs && mCachedTint == mTint && mCachedTintMode == mTintMode && mCachedAutoMirrored == mAutoMirrored)
            {
                return true;
            } else
            {
                updateCacheStates();
                return false;
            }
        }

        public void finalize()
            throws Throwable
        {
            super.finalize();
            int i = mLastHWCachePixelCount;
            int j = mLastSWCachePixelCount;
            VMRuntime.getRuntime().registerNativeFree(mAllocationOfAllNodes + 316 + (i * 4 + j * 4));
        }

        public float getAlpha()
        {
            return VectorDrawable._2D_wrap6(mNativeTree.get());
        }

        public int getChangingConfigurations()
        {
            int i = mChangingConfigurations;
            int j;
            if(mTint != null)
                j = mTint.getChangingConfigurations();
            else
                j = 0;
            return j | i;
        }

        long getNativeRenderer()
        {
            if(mNativeTree == null)
                return 0L;
            else
                return mNativeTree.get();
        }

        Property getProperty(String s)
        {
            if(ALPHA.getName().equals(s))
                return ALPHA;
            else
                return null;
        }

        public boolean hasFocusStateSpecified()
        {
            boolean flag;
            if(mTint == null || !mTint.hasFocusStateSpecified())
            {
                if(mRootGroup != null)
                    flag = mRootGroup.hasFocusStateSpecified();
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            return flag;
        }

        public boolean isStateful()
        {
            boolean flag;
            if(mTint == null || !mTint.isStateful())
            {
                if(mRootGroup != null)
                    flag = mRootGroup.isStateful();
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            return flag;
        }

        public Drawable newDrawable()
        {
            return new VectorDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new VectorDrawable(this, resources, null);
        }

        public boolean onStateChange(int ai[])
        {
            return mRootGroup.onStateChange(ai);
        }

        void onTreeConstructionFinished()
        {
            mRootGroup.setTree(mNativeTree);
            mAllocationOfAllNodes = mRootGroup.getNativeSize();
            VMRuntime.getRuntime().registerNativeAllocation(mAllocationOfAllNodes);
        }

        public boolean setAlpha(float f)
        {
            return VectorDrawable._2D_wrap2(mNativeTree.get(), f);
        }

        public final boolean setDensity(int i)
        {
            if(mDensity != i)
            {
                int j = mDensity;
                mDensity = i;
                applyDensityScaling(j, i);
                return true;
            } else
            {
                return false;
            }
        }

        void setViewportSize(float f, float f1)
        {
            mViewportWidth = f;
            mViewportHeight = f1;
            VectorDrawable._2D_wrap35(getNativeRenderer(), f, f1);
        }

        public void updateCacheStates()
        {
            mCachedThemeAttrs = mThemeAttrs;
            mCachedTint = mTint;
            mCachedTintMode = mTintMode;
            mCachedAutoMirrored = mAutoMirrored;
            mCacheDirty = false;
        }

        static final Property ALPHA = new FloatProperty("alpha") {

            public Float get(VectorDrawableState vectordrawablestate)
            {
                return Float.valueOf(vectordrawablestate.getAlpha());
            }

            public volatile Object get(Object obj)
            {
                return get((VectorDrawableState)obj);
            }

            public void setValue(VectorDrawableState vectordrawablestate, float f)
            {
                vectordrawablestate.setAlpha(f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((VectorDrawableState)obj, f);
            }

        }
;
        private static final int NATIVE_ALLOCATION_SIZE = 316;
        private int mAllocationOfAllNodes;
        boolean mAutoMirrored;
        int mBaseHeight;
        int mBaseWidth;
        boolean mCacheDirty;
        boolean mCachedAutoMirrored;
        int mCachedThemeAttrs[];
        ColorStateList mCachedTint;
        android.graphics.PorterDuff.Mode mCachedTintMode;
        int mChangingConfigurations;
        int mDensity;
        int mLastHWCachePixelCount;
        int mLastSWCachePixelCount;
        VirtualRefBasePtr mNativeTree;
        Insets mOpticalInsets;
        VGroup mRootGroup;
        String mRootName;
        int mThemeAttrs[];
        ColorStateList mTint;
        android.graphics.PorterDuff.Mode mTintMode;
        final ArrayMap mVGTargetsMap = new ArrayMap();
        float mViewportHeight;
        float mViewportWidth;


        public VectorDrawableState(VectorDrawableState vectordrawablestate)
        {
            mTint = null;
            mTintMode = VectorDrawable.DEFAULT_TINT_MODE;
            mBaseWidth = 0;
            mBaseHeight = 0;
            mViewportWidth = 0.0F;
            mViewportHeight = 0.0F;
            mOpticalInsets = Insets.NONE;
            mRootName = null;
            mNativeTree = null;
            mDensity = 160;
            mLastSWCachePixelCount = 0;
            mLastHWCachePixelCount = 0;
            mAllocationOfAllNodes = 0;
            if(vectordrawablestate != null)
            {
                mThemeAttrs = vectordrawablestate.mThemeAttrs;
                mChangingConfigurations = vectordrawablestate.mChangingConfigurations;
                mTint = vectordrawablestate.mTint;
                mTintMode = vectordrawablestate.mTintMode;
                mAutoMirrored = vectordrawablestate.mAutoMirrored;
                mRootGroup = new VGroup(vectordrawablestate.mRootGroup, mVGTargetsMap);
                createNativeTreeFromCopy(vectordrawablestate, mRootGroup);
                mBaseWidth = vectordrawablestate.mBaseWidth;
                mBaseHeight = vectordrawablestate.mBaseHeight;
                setViewportSize(vectordrawablestate.mViewportWidth, vectordrawablestate.mViewportHeight);
                mOpticalInsets = vectordrawablestate.mOpticalInsets;
                mRootName = vectordrawablestate.mRootName;
                mDensity = vectordrawablestate.mDensity;
                if(vectordrawablestate.mRootName != null)
                    mVGTargetsMap.put(vectordrawablestate.mRootName, this);
            } else
            {
                mRootGroup = new VGroup();
                createNativeTree(mRootGroup);
            }
            onTreeConstructionFinished();
        }
    }


    static boolean _2D_wrap0(long l, byte abyte0[], int i)
    {
        return nGetFullPathProperties(l, abyte0, i);
    }

    static boolean _2D_wrap1(long l, float af[], int i)
    {
        return nGetGroupProperties(l, af, i);
    }

    static float _2D_wrap10(long l)
    {
        return nGetStrokeAlpha(l);
    }

    static float _2D_wrap11(long l)
    {
        return nGetStrokeWidth(l);
    }

    static float _2D_wrap12(long l)
    {
        return nGetTranslateX(l);
    }

    static float _2D_wrap13(long l)
    {
        return nGetTranslateY(l);
    }

    static float _2D_wrap14(long l)
    {
        return nGetTrimPathEnd(l);
    }

    static float _2D_wrap15(long l)
    {
        return nGetTrimPathOffset(l);
    }

    static float _2D_wrap16(long l)
    {
        return nGetTrimPathStart(l);
    }

    static int _2D_wrap17(long l)
    {
        return nGetFillColor(l);
    }

    static int _2D_wrap18(long l)
    {
        return nGetStrokeColor(l);
    }

    static long _2D_wrap19()
    {
        return nCreateClipPath();
    }

    static boolean _2D_wrap2(long l, float f)
    {
        return nSetRootAlpha(l, f);
    }

    static long _2D_wrap20(long l)
    {
        return nCreateClipPath(l);
    }

    static long _2D_wrap21()
    {
        return nCreateFullPath();
    }

    static long _2D_wrap22(long l)
    {
        return nCreateFullPath(l);
    }

    static long _2D_wrap23()
    {
        return nCreateGroup();
    }

    static long _2D_wrap24(long l)
    {
        return nCreateGroup(l);
    }

    static long _2D_wrap25(long l, long l1)
    {
        return nCreateTreeFromCopy(l, l1);
    }

    static long _2D_wrap26(long l)
    {
        return nCreateTree(l);
    }

    static void _2D_wrap27(long l, long l1)
    {
        nAddChild(l, l1);
    }

    static void _2D_wrap28(long l, float f)
    {
        nSetFillAlpha(l, f);
    }

    static void _2D_wrap29(long l, int i)
    {
        nSetFillColor(l, i);
    }

    static float _2D_wrap3(long l)
    {
        return nGetFillAlpha(l);
    }

    static void _2D_wrap30(long l, String s)
    {
        nSetName(l, s);
    }

    static void _2D_wrap31(long l, long l1)
    {
        nSetPathData(l, l1);
    }

    static void _2D_wrap32(long l, String s, int i)
    {
        nSetPathString(l, s, i);
    }

    static void _2D_wrap33(long l, float f)
    {
        nSetPivotX(l, f);
    }

    static void _2D_wrap34(long l, float f)
    {
        nSetPivotY(l, f);
    }

    static void _2D_wrap35(long l, float f, float f1)
    {
        nSetRendererViewportSize(l, f, f1);
    }

    static void _2D_wrap36(long l, float f)
    {
        nSetRotation(l, f);
    }

    static void _2D_wrap37(long l, float f)
    {
        nSetScaleX(l, f);
    }

    static void _2D_wrap38(long l, float f)
    {
        nSetScaleY(l, f);
    }

    static void _2D_wrap39(long l, float f)
    {
        nSetStrokeAlpha(l, f);
    }

    static float _2D_wrap4(long l)
    {
        return nGetPivotX(l);
    }

    static void _2D_wrap40(long l, int i)
    {
        nSetStrokeColor(l, i);
    }

    static void _2D_wrap41(long l, float f)
    {
        nSetStrokeWidth(l, f);
    }

    static void _2D_wrap42(long l, float f)
    {
        nSetTranslateX(l, f);
    }

    static void _2D_wrap43(long l, float f)
    {
        nSetTranslateY(l, f);
    }

    static void _2D_wrap44(long l, float f)
    {
        nSetTrimPathEnd(l, f);
    }

    static void _2D_wrap45(long l, float f)
    {
        nSetTrimPathOffset(l, f);
    }

    static void _2D_wrap46(long l, float f)
    {
        nSetTrimPathStart(l, f);
    }

    static void _2D_wrap47(long l, long l1)
    {
        nUpdateFullPathFillGradient(l, l1);
    }

    static void _2D_wrap48(long l, float f, int i, float f1, int j, float f2, float f3, 
            float f4, float f5, float f6, int k, int i1, int j1)
    {
        nUpdateFullPathProperties(l, f, i, f1, j, f2, f3, f4, f5, f6, k, i1, j1);
    }

    static void _2D_wrap49(long l, long l1)
    {
        nUpdateFullPathStrokeGradient(l, l1);
    }

    static float _2D_wrap5(long l)
    {
        return nGetPivotY(l);
    }

    static void _2D_wrap50(long l, float f, float f1, float f2, float f3, float f4, float f5, 
            float f6)
    {
        nUpdateGroupProperties(l, f, f1, f2, f3, f4, f5, f6);
    }

    static float _2D_wrap6(long l)
    {
        return nGetRootAlpha(l);
    }

    static float _2D_wrap7(long l)
    {
        return nGetRotation(l);
    }

    static float _2D_wrap8(long l)
    {
        return nGetScaleX(l);
    }

    static float _2D_wrap9(long l)
    {
        return nGetScaleY(l);
    }

    public VectorDrawable()
    {
        this(new VectorDrawableState(null), null);
    }

    private VectorDrawable(VectorDrawableState vectordrawablestate, Resources resources)
    {
        mDpiScaledWidth = 0;
        mDpiScaledHeight = 0;
        mDpiScaledInsets = Insets.NONE;
        mDpiScaledDirty = true;
        mTmpBounds = new Rect();
        mVectorState = vectordrawablestate;
        updateLocalState(resources);
    }

    VectorDrawable(VectorDrawableState vectordrawablestate, Resources resources, VectorDrawable vectordrawable)
    {
        this(vectordrawablestate, resources);
    }

    public static VectorDrawable create(Resources resources, int i)
    {
        android.content.res.XmlResourceParser xmlresourceparser;
        AttributeSet attributeset;
        xmlresourceparser = resources.getXml(i);
        attributeset = Xml.asAttributeSet(xmlresourceparser);
        do
            i = xmlresourceparser.next();
        while(i != 2 && i != 1);
        if(i != 2)
        {
            VectorDrawable vectordrawable;
            try
            {
                resources = JVM INSTR new #361 <Class XmlPullParserException>;
                resources.XmlPullParserException("No start tag found");
                throw resources;
            }
            // Misplaced declaration of an exception variable
            catch(Resources resources)
            {
                Log.e(LOGTAG, "parser error", resources);
            }
            // Misplaced declaration of an exception variable
            catch(Resources resources)
            {
                Log.e(LOGTAG, "parser error", resources);
            }
            return null;
        }
        vectordrawable = JVM INSTR new #2   <Class VectorDrawable>;
        vectordrawable.VectorDrawable();
        vectordrawable.inflate(resources, xmlresourceparser, attributeset);
        return vectordrawable;
    }

    private void inflateChildElements(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        VectorDrawableState vectordrawablestate = mVectorState;
        boolean flag = true;
        Stack stack = new Stack();
        stack.push(vectordrawablestate.mRootGroup);
        int i = xmlpullparser.getEventType();
        int j = xmlpullparser.getDepth();
        while(i != 1 && (xmlpullparser.getDepth() >= j + 1 || i != 3)) 
        {
            boolean flag1;
            if(i == 2)
            {
                Object obj = xmlpullparser.getName();
                VGroup vgroup1 = (VGroup)stack.peek();
                if("path".equals(obj))
                {
                    obj = new VFullPath();
                    ((VFullPath) (obj)).inflate(resources, attributeset, theme);
                    vgroup1.addChild(((VObject) (obj)));
                    if(((VFullPath) (obj)).getPathName() != null)
                        vectordrawablestate.mVGTargetsMap.put(((VFullPath) (obj)).getPathName(), obj);
                    flag1 = false;
                    vectordrawablestate.mChangingConfigurations = vectordrawablestate.mChangingConfigurations | ((VFullPath) (obj)).mChangingConfigurations;
                } else
                if("clip-path".equals(obj))
                {
                    obj = new VClipPath();
                    ((VClipPath) (obj)).inflate(resources, attributeset, theme);
                    vgroup1.addChild(((VObject) (obj)));
                    if(((VClipPath) (obj)).getPathName() != null)
                        vectordrawablestate.mVGTargetsMap.put(((VClipPath) (obj)).getPathName(), obj);
                    vectordrawablestate.mChangingConfigurations = vectordrawablestate.mChangingConfigurations | ((VClipPath) (obj)).mChangingConfigurations;
                    flag1 = flag;
                } else
                {
                    flag1 = flag;
                    if("group".equals(obj))
                    {
                        VGroup vgroup = new VGroup();
                        vgroup.inflate(resources, attributeset, theme);
                        vgroup1.addChild(vgroup);
                        stack.push(vgroup);
                        if(vgroup.getGroupName() != null)
                            vectordrawablestate.mVGTargetsMap.put(vgroup.getGroupName(), vgroup);
                        vectordrawablestate.mChangingConfigurations = vectordrawablestate.mChangingConfigurations | VGroup._2D_get7(vgroup);
                        flag1 = flag;
                    }
                }
            } else
            {
                flag1 = flag;
                if(i == 3)
                {
                    flag1 = flag;
                    if("group".equals(xmlpullparser.getName()))
                    {
                        stack.pop();
                        flag1 = flag;
                    }
                }
            }
            i = xmlpullparser.next();
            flag = flag1;
        }
        if(flag)
        {
            resources = new StringBuffer();
            if(resources.length() > 0)
                resources.append(" or ");
            resources.append("path");
            throw new XmlPullParserException((new StringBuilder()).append("no ").append(resources).append(" defined").toString());
        } else
        {
            return;
        }
    }

    private static native void nAddChild(long l, long l1);

    private static native long nCreateClipPath();

    private static native long nCreateClipPath(long l);

    private static native long nCreateFullPath();

    private static native long nCreateFullPath(long l);

    private static native long nCreateGroup();

    private static native long nCreateGroup(long l);

    private static native long nCreateTree(long l);

    private static native long nCreateTreeFromCopy(long l, long l1);

    private static native int nDraw(long l, long l1, long l2, Rect rect, boolean flag, 
            boolean flag1);

    private static native float nGetFillAlpha(long l);

    private static native int nGetFillColor(long l);

    private static native boolean nGetFullPathProperties(long l, byte abyte0[], int i);

    private static native boolean nGetGroupProperties(long l, float af[], int i);

    private static native float nGetPivotX(long l);

    private static native float nGetPivotY(long l);

    private static native float nGetRootAlpha(long l);

    private static native float nGetRotation(long l);

    private static native float nGetScaleX(long l);

    private static native float nGetScaleY(long l);

    private static native float nGetStrokeAlpha(long l);

    private static native int nGetStrokeColor(long l);

    private static native float nGetStrokeWidth(long l);

    private static native float nGetTranslateX(long l);

    private static native float nGetTranslateY(long l);

    private static native float nGetTrimPathEnd(long l);

    private static native float nGetTrimPathOffset(long l);

    private static native float nGetTrimPathStart(long l);

    private static native void nSetAllowCaching(long l, boolean flag);

    private static native void nSetFillAlpha(long l, float f);

    private static native void nSetFillColor(long l, int i);

    private static native void nSetName(long l, String s);

    private static native void nSetPathData(long l, long l1);

    private static native void nSetPathString(long l, String s, int i);

    private static native void nSetPivotX(long l, float f);

    private static native void nSetPivotY(long l, float f);

    private static native void nSetRendererViewportSize(long l, float f, float f1);

    private static native boolean nSetRootAlpha(long l, float f);

    private static native void nSetRotation(long l, float f);

    private static native void nSetScaleX(long l, float f);

    private static native void nSetScaleY(long l, float f);

    private static native void nSetStrokeAlpha(long l, float f);

    private static native void nSetStrokeColor(long l, int i);

    private static native void nSetStrokeWidth(long l, float f);

    private static native void nSetTranslateX(long l, float f);

    private static native void nSetTranslateY(long l, float f);

    private static native void nSetTrimPathEnd(long l, float f);

    private static native void nSetTrimPathOffset(long l, float f);

    private static native void nSetTrimPathStart(long l, float f);

    private static native void nUpdateFullPathFillGradient(long l, long l1);

    private static native void nUpdateFullPathProperties(long l, float f, int i, float f1, int j, float f2, float f3, 
            float f4, float f5, float f6, int k, int i1, int j1);

    private static native void nUpdateFullPathStrokeGradient(long l, long l1);

    private static native void nUpdateGroupProperties(long l, float f, float f1, float f2, float f3, float f4, float f5, 
            float f6);

    private boolean needMirroring()
    {
        boolean flag = true;
        if(!isAutoMirrored() || getLayoutDirection() != 1)
            flag = false;
        return flag;
    }

    private void updateLocalState(Resources resources)
    {
        int i = Drawable.resolveDensity(resources, mVectorState.mDensity);
        if(mTargetDensity != i)
        {
            mTargetDensity = i;
            mDpiScaledDirty = true;
        }
        mTintFilter = updateTintFilter(mTintFilter, mVectorState.mTint, mVectorState.mTintMode);
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
        throws XmlPullParserException
    {
        VectorDrawableState vectordrawablestate = mVectorState;
        vectordrawablestate.mChangingConfigurations = vectordrawablestate.mChangingConfigurations | typedarray.getChangingConfigurations();
        vectordrawablestate.mThemeAttrs = typedarray.extractThemeAttrs();
        int i = typedarray.getInt(6, -1);
        if(i != -1)
            vectordrawablestate.mTintMode = Drawable.parseTintMode(i, android.graphics.PorterDuff.Mode.SRC_IN);
        ColorStateList colorstatelist = typedarray.getColorStateList(1);
        if(colorstatelist != null)
            vectordrawablestate.mTint = colorstatelist;
        vectordrawablestate.mAutoMirrored = typedarray.getBoolean(5, vectordrawablestate.mAutoMirrored);
        vectordrawablestate.setViewportSize(typedarray.getFloat(7, vectordrawablestate.mViewportWidth), typedarray.getFloat(8, vectordrawablestate.mViewportHeight));
        if(vectordrawablestate.mViewportWidth <= 0.0F)
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<vector> tag requires viewportWidth > 0").toString());
        if(vectordrawablestate.mViewportHeight <= 0.0F)
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<vector> tag requires viewportHeight > 0").toString());
        vectordrawablestate.mBaseWidth = typedarray.getDimensionPixelSize(3, vectordrawablestate.mBaseWidth);
        vectordrawablestate.mBaseHeight = typedarray.getDimensionPixelSize(2, vectordrawablestate.mBaseHeight);
        if(vectordrawablestate.mBaseWidth <= 0)
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<vector> tag requires width > 0").toString());
        if(vectordrawablestate.mBaseHeight <= 0)
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<vector> tag requires height > 0").toString());
        vectordrawablestate.mOpticalInsets = Insets.of(typedarray.getDimensionPixelOffset(10, vectordrawablestate.mOpticalInsets.left), typedarray.getDimensionPixelOffset(12, vectordrawablestate.mOpticalInsets.top), typedarray.getDimensionPixelOffset(11, vectordrawablestate.mOpticalInsets.right), typedarray.getDimensionPixelOffset(9, vectordrawablestate.mOpticalInsets.bottom));
        vectordrawablestate.setAlpha(typedarray.getFloat(4, vectordrawablestate.getAlpha()));
        typedarray = typedarray.getString(0);
        if(typedarray != null)
        {
            vectordrawablestate.mRootName = typedarray;
            vectordrawablestate.mVGTargetsMap.put(typedarray, vectordrawablestate);
        }
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        VectorDrawableState vectordrawablestate;
        TypedArray typedarray;
        super.applyTheme(theme);
        vectordrawablestate = mVectorState;
        if(vectordrawablestate == null)
            return;
        boolean flag = mVectorState.setDensity(Drawable.resolveDensity(theme.getResources(), 0));
        mDpiScaledDirty = mDpiScaledDirty | flag;
        if(vectordrawablestate.mThemeAttrs == null)
            break MISSING_BLOCK_LABEL_82;
        typedarray = theme.resolveAttributes(vectordrawablestate.mThemeAttrs, com.android.internal.R.styleable.VectorDrawable);
        vectordrawablestate.mCacheDirty = true;
        updateStateFromTypedArray(typedarray);
        typedarray.recycle();
        mDpiScaledDirty = true;
        if(vectordrawablestate.mTint != null && vectordrawablestate.mTint.canApplyTheme())
            vectordrawablestate.mTint = vectordrawablestate.mTint.obtainForTheme(theme);
        if(mVectorState != null && mVectorState.canApplyTheme())
            mVectorState.applyTheme(theme);
        updateLocalState(theme.getResources());
        return;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        theme = JVM INSTR new #692 <Class RuntimeException>;
        theme.RuntimeException(xmlpullparserexception);
        throw theme;
        theme;
        typedarray.recycle();
        throw theme;
    }

    public boolean canApplyTheme()
    {
        boolean flag;
        if(mVectorState == null || !mVectorState.canApplyTheme())
            flag = super.canApplyTheme();
        else
            flag = true;
        return flag;
    }

    public void clearMutated()
    {
        super.clearMutated();
        mMutated = false;
    }

    void computeVectorSize()
    {
        Insets insets = mVectorState.mOpticalInsets;
        int i = mVectorState.mDensity;
        int j = mTargetDensity;
        if(j != i)
        {
            mDpiScaledWidth = Drawable.scaleFromDensity(mVectorState.mBaseWidth, i, j, true);
            mDpiScaledHeight = Drawable.scaleFromDensity(mVectorState.mBaseHeight, i, j, true);
            int k = Drawable.scaleFromDensity(insets.left, i, j, false);
            int l = Drawable.scaleFromDensity(insets.right, i, j, false);
            mDpiScaledInsets = Insets.of(k, Drawable.scaleFromDensity(insets.top, i, j, false), l, Drawable.scaleFromDensity(insets.bottom, i, j, false));
        } else
        {
            mDpiScaledWidth = mVectorState.mBaseWidth;
            mDpiScaledHeight = mVectorState.mBaseHeight;
            mDpiScaledInsets = insets;
        }
        mDpiScaledDirty = false;
    }

    public void draw(Canvas canvas)
    {
        int j;
        copyBounds(mTmpBounds);
        if(mTmpBounds.width() <= 0 || mTmpBounds.height() <= 0)
            return;
        Object obj;
        long l;
        boolean flag;
        int i;
        if(mColorFilter == null)
            obj = mTintFilter;
        else
            obj = mColorFilter;
        if(obj == null)
            l = 0L;
        else
            l = ((ColorFilter) (obj)).getNativeInstance();
        flag = mVectorState.canReuseCache();
        i = nDraw(mVectorState.getNativeRenderer(), canvas.getNativeCanvasWrapper(), l, mTmpBounds, needMirroring(), flag);
        if(i == 0)
            return;
        if(canvas.isHardwareAccelerated())
        {
            j = (i - mVectorState.mLastHWCachePixelCount) * 4;
            mVectorState.mLastHWCachePixelCount = i;
        } else
        {
            j = (i - mVectorState.mLastSWCachePixelCount) * 4;
            mVectorState.mLastSWCachePixelCount = i;
        }
        if(j <= 0) goto _L2; else goto _L1
_L1:
        VMRuntime.getRuntime().registerNativeAllocation(j);
_L4:
        return;
_L2:
        if(j < 0)
            VMRuntime.getRuntime().registerNativeFree(-j);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getAlpha()
    {
        return (int)(mVectorState.getAlpha() * 255F);
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | mVectorState.getChangingConfigurations();
    }

    public ColorFilter getColorFilter()
    {
        return mColorFilter;
    }

    public Drawable.ConstantState getConstantState()
    {
        mVectorState.mChangingConfigurations = getChangingConfigurations();
        return mVectorState;
    }

    public int getIntrinsicHeight()
    {
        if(mDpiScaledDirty)
            computeVectorSize();
        return mDpiScaledHeight;
    }

    public int getIntrinsicWidth()
    {
        if(mDpiScaledDirty)
            computeVectorSize();
        return mDpiScaledWidth;
    }

    public long getNativeTree()
    {
        return mVectorState.getNativeRenderer();
    }

    public int getOpacity()
    {
        byte byte0;
        if(getAlpha() == 0)
            byte0 = -2;
        else
            byte0 = -3;
        return byte0;
    }

    public Insets getOpticalInsets()
    {
        if(mDpiScaledDirty)
            computeVectorSize();
        return mDpiScaledInsets;
    }

    public float getPixelSize()
    {
        while(mVectorState == null || mVectorState.mBaseWidth == 0 || mVectorState.mBaseHeight == 0 || mVectorState.mViewportHeight == 0.0F || mVectorState.mViewportWidth == 0.0F) 
            return 1.0F;
        float f = mVectorState.mBaseWidth;
        float f1 = mVectorState.mBaseHeight;
        float f2 = mVectorState.mViewportWidth;
        float f3 = mVectorState.mViewportHeight;
        return Math.min(f2 / f, f3 / f1);
    }

    Object getTargetByName(String s)
    {
        return mVectorState.mVGTargetsMap.get(s);
    }

    public boolean hasFocusStateSpecified()
    {
        boolean flag;
        if(mVectorState != null)
            flag = mVectorState.hasFocusStateSpecified();
        else
            flag = false;
        return flag;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        Trace.traceBegin(8192L, "VectorDrawable#inflate");
        if(mVectorState.mRootGroup != null || mVectorState.mNativeTree != null)
        {
            if(mVectorState.mRootGroup != null)
            {
                VMRuntime.getRuntime().registerNativeFree(mVectorState.mRootGroup.getNativeSize());
                mVectorState.mRootGroup.setTree(null);
            }
            VectorDrawableState vectordrawablestate = mVectorState;
            VGroup vgroup = JVM INSTR new #32  <Class VectorDrawable$VGroup>;
            vgroup.VGroup();
            vectordrawablestate.mRootGroup = vgroup;
            if(mVectorState.mNativeTree != null)
            {
                VMRuntime.getRuntime().registerNativeFree(316);
                mVectorState.mNativeTree.release();
            }
            VectorDrawableState._2D_wrap0(mVectorState, mVectorState.mRootGroup);
        }
        VectorDrawableState vectordrawablestate1 = mVectorState;
        vectordrawablestate1.setDensity(Drawable.resolveDensity(resources, 0));
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.VectorDrawable);
        updateStateFromTypedArray(typedarray);
        typedarray.recycle();
        mDpiScaledDirty = true;
        vectordrawablestate1.mCacheDirty = true;
        inflateChildElements(resources, xmlpullparser, attributeset, theme);
        vectordrawablestate1.onTreeConstructionFinished();
        updateLocalState(resources);
        Trace.traceEnd(8192L);
        return;
        resources;
        Trace.traceEnd(8192L);
        throw resources;
    }

    public boolean isAutoMirrored()
    {
        return mVectorState.mAutoMirrored;
    }

    public boolean isStateful()
    {
        boolean flag;
        if(!super.isStateful())
        {
            if(mVectorState != null)
                flag = mVectorState.isStateful();
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            mVectorState = new VectorDrawableState(mVectorState);
            mMutated = true;
        }
        return this;
    }

    protected boolean onStateChange(int ai[])
    {
        boolean flag = false;
        if(isStateful())
            mutate();
        VectorDrawableState vectordrawablestate = mVectorState;
        if(vectordrawablestate.onStateChange(ai))
        {
            flag = true;
            vectordrawablestate.mCacheDirty = true;
        }
        boolean flag1 = flag;
        if(vectordrawablestate.mTint != null)
        {
            flag1 = flag;
            if(vectordrawablestate.mTintMode != null)
            {
                mTintFilter = updateTintFilter(mTintFilter, vectordrawablestate.mTint, vectordrawablestate.mTintMode);
                flag1 = true;
            }
        }
        return flag1;
    }

    void setAllowCaching(boolean flag)
    {
        nSetAllowCaching(mVectorState.getNativeRenderer(), flag);
    }

    public void setAlpha(int i)
    {
        if(mVectorState.setAlpha((float)i / 255F))
            invalidateSelf();
    }

    public void setAutoMirrored(boolean flag)
    {
        if(mVectorState.mAutoMirrored != flag)
        {
            mVectorState.mAutoMirrored = flag;
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mColorFilter = colorfilter;
        invalidateSelf();
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        VectorDrawableState vectordrawablestate = mVectorState;
        if(vectordrawablestate.mTint != colorstatelist)
        {
            vectordrawablestate.mTint = colorstatelist;
            mTintFilter = updateTintFilter(mTintFilter, colorstatelist, vectordrawablestate.mTintMode);
            invalidateSelf();
        }
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        VectorDrawableState vectordrawablestate = mVectorState;
        if(vectordrawablestate.mTintMode != mode)
        {
            vectordrawablestate.mTintMode = mode;
            mTintFilter = updateTintFilter(mTintFilter, vectordrawablestate.mTint, mode);
            invalidateSelf();
        }
    }

    private static final String LOGTAG = android/graphics/drawable/VectorDrawable.getSimpleName();
    private static final String SHAPE_CLIP_PATH = "clip-path";
    private static final String SHAPE_GROUP = "group";
    private static final String SHAPE_PATH = "path";
    private static final String SHAPE_VECTOR = "vector";
    private ColorFilter mColorFilter;
    private boolean mDpiScaledDirty;
    private int mDpiScaledHeight;
    private Insets mDpiScaledInsets;
    private int mDpiScaledWidth;
    private boolean mMutated;
    private int mTargetDensity;
    private PorterDuffColorFilter mTintFilter;
    private final Rect mTmpBounds;
    private VectorDrawableState mVectorState;

}
