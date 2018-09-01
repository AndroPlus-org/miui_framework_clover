// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.graphics.Path;
import android.graphics.PointF;
import android.util.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

// Referenced classes of package android.animation:
//            IntEvaluator, FloatEvaluator, BidirectionalTypeConverter, TypeConverter, 
//            KeyframeSet, Keyframes, FloatArrayEvaluator, IntArrayEvaluator, 
//            Keyframe, TypeEvaluator

public class PropertyValuesHolder
    implements Cloneable
{
    static class FloatPropertyValuesHolder extends PropertyValuesHolder
    {

        void calculateValue(float f)
        {
            mFloatAnimatedValue = mFloatKeyframes.getFloatValue(f);
        }

        public FloatPropertyValuesHolder clone()
        {
            FloatPropertyValuesHolder floatpropertyvaluesholder = (FloatPropertyValuesHolder)clone();
            floatpropertyvaluesholder.mFloatKeyframes = (Keyframes.FloatKeyframes)floatpropertyvaluesholder.mKeyframes;
            return floatpropertyvaluesholder;
        }

        public volatile PropertyValuesHolder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        Object getAnimatedValue()
        {
            return Float.valueOf(mFloatAnimatedValue);
        }

        void setAnimatedValue(Object obj)
        {
            if(mFloatProperty != null)
            {
                mFloatProperty.setValue(obj, mFloatAnimatedValue);
                return;
            }
            if(mProperty != null)
            {
                mProperty.set(obj, Float.valueOf(mFloatAnimatedValue));
                return;
            }
            if(mJniSetter != 0L)
            {
                PropertyValuesHolder._2D_wrap4(obj, mJniSetter, mFloatAnimatedValue);
                return;
            }
            if(mSetter == null)
                break MISSING_BLOCK_LABEL_98;
            mTmpValueArray[0] = Float.valueOf(mFloatAnimatedValue);
            mSetter.invoke(obj, mTmpValueArray);
_L1:
            return;
            obj;
            Log.e("PropertyValuesHolder", ((IllegalAccessException) (obj)).toString());
              goto _L1
            obj;
            Log.e("PropertyValuesHolder", ((InvocationTargetException) (obj)).toString());
              goto _L1
        }

        public transient void setFloatValues(float af[])
        {
            setFloatValues(af);
            mFloatKeyframes = (Keyframes.FloatKeyframes)mKeyframes;
        }

        public void setProperty(Property property)
        {
            if(property instanceof FloatProperty)
                mFloatProperty = (FloatProperty)property;
            else
                setProperty(property);
        }

        void setupSetter(Class class1)
        {
            if(mProperty != null)
                return;
            HashMap hashmap = sJNISetterPropertyMap;
            hashmap;
            JVM INSTR monitorenter ;
            HashMap hashmap1 = (HashMap)sJNISetterPropertyMap.get(class1);
            boolean flag;
            flag = false;
            if(hashmap1 == null)
                break MISSING_BLOCK_LABEL_86;
            boolean flag1 = hashmap1.containsKey(mPropertyName);
            flag = flag1;
            if(!flag1)
                break MISSING_BLOCK_LABEL_86;
            Object obj = (Long)hashmap1.get(mPropertyName);
            flag = flag1;
            if(obj == null)
                break MISSING_BLOCK_LABEL_86;
            mJniSetter = ((Long) (obj)).longValue();
            flag = flag1;
            if(flag)
                break MISSING_BLOCK_LABEL_156;
            obj = getMethodName("set", mPropertyName);
            try
            {
                mJniSetter = PropertyValuesHolder._2D_wrap0(class1, ((String) (obj)));
            }
            catch(NoSuchMethodError nosuchmethoderror) { }
            obj = hashmap1;
            if(hashmap1 != null)
                break MISSING_BLOCK_LABEL_139;
            obj = JVM INSTR new #20  <Class HashMap>;
            ((HashMap) (obj)).HashMap();
            sJNISetterPropertyMap.put(class1, obj);
            ((HashMap) (obj)).put(mPropertyName, Long.valueOf(mJniSetter));
            hashmap;
            JVM INSTR monitorexit ;
            if(mJniSetter == 0L)
                setupSetter(class1);
            return;
            class1;
            throw class1;
        }

        private static final HashMap sJNISetterPropertyMap = new HashMap();
        float mFloatAnimatedValue;
        Keyframes.FloatKeyframes mFloatKeyframes;
        private FloatProperty mFloatProperty;
        long mJniSetter;


        public FloatPropertyValuesHolder(Property property, Keyframes.FloatKeyframes floatkeyframes)
        {
            super(property, null);
            mValueType = Float.TYPE;
            mKeyframes = floatkeyframes;
            mFloatKeyframes = floatkeyframes;
            if(property instanceof FloatProperty)
                mFloatProperty = (FloatProperty)mProperty;
        }

        public transient FloatPropertyValuesHolder(Property property, float af[])
        {
            super(property, null);
            setFloatValues(af);
            if(property instanceof FloatProperty)
                mFloatProperty = (FloatProperty)mProperty;
        }

        public FloatPropertyValuesHolder(String s, Keyframes.FloatKeyframes floatkeyframes)
        {
            super(s, null);
            mValueType = Float.TYPE;
            mKeyframes = floatkeyframes;
            mFloatKeyframes = floatkeyframes;
        }

        public transient FloatPropertyValuesHolder(String s, float af[])
        {
            super(s, null);
            setFloatValues(af);
        }
    }

    static class IntPropertyValuesHolder extends PropertyValuesHolder
    {

        void calculateValue(float f)
        {
            mIntAnimatedValue = mIntKeyframes.getIntValue(f);
        }

        public IntPropertyValuesHolder clone()
        {
            IntPropertyValuesHolder intpropertyvaluesholder = (IntPropertyValuesHolder)clone();
            intpropertyvaluesholder.mIntKeyframes = (Keyframes.IntKeyframes)intpropertyvaluesholder.mKeyframes;
            return intpropertyvaluesholder;
        }

        public volatile PropertyValuesHolder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        Object getAnimatedValue()
        {
            return Integer.valueOf(mIntAnimatedValue);
        }

        void setAnimatedValue(Object obj)
        {
            if(mIntProperty != null)
            {
                mIntProperty.setValue(obj, mIntAnimatedValue);
                return;
            }
            if(mProperty != null)
            {
                mProperty.set(obj, Integer.valueOf(mIntAnimatedValue));
                return;
            }
            if(mJniSetter != 0L)
            {
                PropertyValuesHolder._2D_wrap7(obj, mJniSetter, mIntAnimatedValue);
                return;
            }
            if(mSetter == null)
                break MISSING_BLOCK_LABEL_98;
            mTmpValueArray[0] = Integer.valueOf(mIntAnimatedValue);
            mSetter.invoke(obj, mTmpValueArray);
_L1:
            return;
            obj;
            Log.e("PropertyValuesHolder", ((IllegalAccessException) (obj)).toString());
              goto _L1
            obj;
            Log.e("PropertyValuesHolder", ((InvocationTargetException) (obj)).toString());
              goto _L1
        }

        public transient void setIntValues(int ai[])
        {
            setIntValues(ai);
            mIntKeyframes = (Keyframes.IntKeyframes)mKeyframes;
        }

        public void setProperty(Property property)
        {
            if(property instanceof IntProperty)
                mIntProperty = (IntProperty)property;
            else
                setProperty(property);
        }

        void setupSetter(Class class1)
        {
            if(mProperty != null)
                return;
            HashMap hashmap = sJNISetterPropertyMap;
            hashmap;
            JVM INSTR monitorenter ;
            HashMap hashmap1 = (HashMap)sJNISetterPropertyMap.get(class1);
            boolean flag;
            flag = false;
            if(hashmap1 == null)
                break MISSING_BLOCK_LABEL_86;
            boolean flag1 = hashmap1.containsKey(mPropertyName);
            flag = flag1;
            if(!flag1)
                break MISSING_BLOCK_LABEL_86;
            Object obj = (Long)hashmap1.get(mPropertyName);
            flag = flag1;
            if(obj == null)
                break MISSING_BLOCK_LABEL_86;
            mJniSetter = ((Long) (obj)).longValue();
            flag = flag1;
            if(flag)
                break MISSING_BLOCK_LABEL_156;
            obj = getMethodName("set", mPropertyName);
            try
            {
                mJniSetter = PropertyValuesHolder._2D_wrap1(class1, ((String) (obj)));
            }
            catch(NoSuchMethodError nosuchmethoderror) { }
            obj = hashmap1;
            if(hashmap1 != null)
                break MISSING_BLOCK_LABEL_139;
            obj = JVM INSTR new #20  <Class HashMap>;
            ((HashMap) (obj)).HashMap();
            sJNISetterPropertyMap.put(class1, obj);
            ((HashMap) (obj)).put(mPropertyName, Long.valueOf(mJniSetter));
            hashmap;
            JVM INSTR monitorexit ;
            if(mJniSetter == 0L)
                setupSetter(class1);
            return;
            class1;
            throw class1;
        }

        private static final HashMap sJNISetterPropertyMap = new HashMap();
        int mIntAnimatedValue;
        Keyframes.IntKeyframes mIntKeyframes;
        private IntProperty mIntProperty;
        long mJniSetter;


        public IntPropertyValuesHolder(Property property, Keyframes.IntKeyframes intkeyframes)
        {
            super(property, null);
            mValueType = Integer.TYPE;
            mKeyframes = intkeyframes;
            mIntKeyframes = intkeyframes;
            if(property instanceof IntProperty)
                mIntProperty = (IntProperty)mProperty;
        }

        public transient IntPropertyValuesHolder(Property property, int ai[])
        {
            super(property, null);
            setIntValues(ai);
            if(property instanceof IntProperty)
                mIntProperty = (IntProperty)mProperty;
        }

        public IntPropertyValuesHolder(String s, Keyframes.IntKeyframes intkeyframes)
        {
            super(s, null);
            mValueType = Integer.TYPE;
            mKeyframes = intkeyframes;
            mIntKeyframes = intkeyframes;
        }

        public transient IntPropertyValuesHolder(String s, int ai[])
        {
            super(s, null);
            setIntValues(ai);
        }
    }

    static class MultiFloatValuesHolder extends PropertyValuesHolder
    {

        void setAnimatedValue(Object obj)
        {
            float af[];
            int i;
            af = (float[])getAnimatedValue();
            i = af.length;
            if(mJniSetter == 0L) goto _L2; else goto _L1
_L1:
            i;
            JVM INSTR tableswitch 1 4: default 52
        //                       1 62
        //                       2 76
        //                       3 52
        //                       4 93;
               goto _L3 _L4 _L5 _L3 _L6
_L3:
            PropertyValuesHolder._2D_wrap8(obj, mJniSetter, af);
_L2:
            return;
_L4:
            PropertyValuesHolder._2D_wrap4(obj, mJniSetter, af[0]);
            continue; /* Loop/switch isn't completed */
_L5:
            PropertyValuesHolder._2D_wrap10(obj, mJniSetter, af[0], af[1]);
            continue; /* Loop/switch isn't completed */
_L6:
            PropertyValuesHolder._2D_wrap5(obj, mJniSetter, af[0], af[1], af[2], af[3]);
            if(true) goto _L2; else goto _L7
_L7:
        }

        void setupSetter(Class class1)
        {
            if(mJniSetter != 0L)
                return;
            HashMap hashmap = sJNISetterPropertyMap;
            hashmap;
            JVM INSTR monitorenter ;
            HashMap hashmap1 = (HashMap)sJNISetterPropertyMap.get(class1);
            boolean flag;
            flag = false;
            if(hashmap1 == null)
                break MISSING_BLOCK_LABEL_88;
            boolean flag1 = hashmap1.containsKey(mPropertyName);
            flag = flag1;
            if(!flag1)
                break MISSING_BLOCK_LABEL_88;
            Object obj = (Long)hashmap1.get(mPropertyName);
            flag = flag1;
            if(obj == null)
                break MISSING_BLOCK_LABEL_88;
            mJniSetter = ((Long) (obj)).longValue();
            flag = flag1;
            if(flag) goto _L2; else goto _L1
_L1:
            int i;
            obj = getMethodName("set", mPropertyName);
            calculateValue(0.0F);
            i = ((float[])getAnimatedValue()).length;
            mJniSetter = PropertyValuesHolder._2D_wrap2(class1, ((String) (obj)), i);
_L4:
            obj = hashmap1;
            if(hashmap1 != null)
                break MISSING_BLOCK_LABEL_158;
            obj = JVM INSTR new #14  <Class HashMap>;
            ((HashMap) (obj)).HashMap();
            sJNISetterPropertyMap.put(class1, obj);
            ((HashMap) (obj)).put(mPropertyName, Long.valueOf(mJniSetter));
_L2:
            hashmap;
            JVM INSTR monitorexit ;
            return;
            NoSuchMethodError nosuchmethoderror;
            nosuchmethoderror;
            try
            {
                mJniSetter = PropertyValuesHolder._2D_wrap2(class1, mPropertyName, i);
            }
            catch(NoSuchMethodError nosuchmethoderror1) { }
            if(true) goto _L4; else goto _L3
_L3:
            class1;
            throw class1;
        }

        void setupSetterAndGetter(Object obj)
        {
            setupSetter(obj.getClass());
        }

        private static final HashMap sJNISetterPropertyMap = new HashMap();
        private long mJniSetter;


        public MultiFloatValuesHolder(String s, TypeConverter typeconverter, TypeEvaluator typeevaluator, Keyframes keyframes)
        {
            super(s, null);
            setConverter(typeconverter);
            mKeyframes = keyframes;
            setEvaluator(typeevaluator);
        }

        public transient MultiFloatValuesHolder(String s, TypeConverter typeconverter, TypeEvaluator typeevaluator, Object aobj[])
        {
            super(s, null);
            setConverter(typeconverter);
            setObjectValues(aobj);
            setEvaluator(typeevaluator);
        }
    }

    static class MultiIntValuesHolder extends PropertyValuesHolder
    {

        void setAnimatedValue(Object obj)
        {
            int ai[];
            int i;
            ai = (int[])getAnimatedValue();
            i = ai.length;
            if(mJniSetter == 0L) goto _L2; else goto _L1
_L1:
            i;
            JVM INSTR tableswitch 1 4: default 52
        //                       1 62
        //                       2 76
        //                       3 52
        //                       4 93;
               goto _L3 _L4 _L5 _L3 _L6
_L3:
            PropertyValuesHolder._2D_wrap9(obj, mJniSetter, ai);
_L2:
            return;
_L4:
            PropertyValuesHolder._2D_wrap7(obj, mJniSetter, ai[0]);
            continue; /* Loop/switch isn't completed */
_L5:
            PropertyValuesHolder._2D_wrap11(obj, mJniSetter, ai[0], ai[1]);
            continue; /* Loop/switch isn't completed */
_L6:
            PropertyValuesHolder._2D_wrap6(obj, mJniSetter, ai[0], ai[1], ai[2], ai[3]);
            if(true) goto _L2; else goto _L7
_L7:
        }

        void setupSetter(Class class1)
        {
            if(mJniSetter != 0L)
                return;
            HashMap hashmap = sJNISetterPropertyMap;
            hashmap;
            JVM INSTR monitorenter ;
            HashMap hashmap1 = (HashMap)sJNISetterPropertyMap.get(class1);
            boolean flag;
            flag = false;
            if(hashmap1 == null)
                break MISSING_BLOCK_LABEL_88;
            boolean flag1 = hashmap1.containsKey(mPropertyName);
            flag = flag1;
            if(!flag1)
                break MISSING_BLOCK_LABEL_88;
            Object obj = (Long)hashmap1.get(mPropertyName);
            flag = flag1;
            if(obj == null)
                break MISSING_BLOCK_LABEL_88;
            mJniSetter = ((Long) (obj)).longValue();
            flag = flag1;
            if(flag) goto _L2; else goto _L1
_L1:
            int i;
            obj = getMethodName("set", mPropertyName);
            calculateValue(0.0F);
            i = ((int[])getAnimatedValue()).length;
            mJniSetter = PropertyValuesHolder._2D_wrap3(class1, ((String) (obj)), i);
_L4:
            obj = hashmap1;
            if(hashmap1 != null)
                break MISSING_BLOCK_LABEL_158;
            obj = JVM INSTR new #14  <Class HashMap>;
            ((HashMap) (obj)).HashMap();
            sJNISetterPropertyMap.put(class1, obj);
            ((HashMap) (obj)).put(mPropertyName, Long.valueOf(mJniSetter));
_L2:
            hashmap;
            JVM INSTR monitorexit ;
            return;
            NoSuchMethodError nosuchmethoderror;
            nosuchmethoderror;
            try
            {
                mJniSetter = PropertyValuesHolder._2D_wrap3(class1, mPropertyName, i);
            }
            catch(NoSuchMethodError nosuchmethoderror1) { }
            if(true) goto _L4; else goto _L3
_L3:
            class1;
            throw class1;
        }

        void setupSetterAndGetter(Object obj)
        {
            setupSetter(obj.getClass());
        }

        private static final HashMap sJNISetterPropertyMap = new HashMap();
        private long mJniSetter;


        public MultiIntValuesHolder(String s, TypeConverter typeconverter, TypeEvaluator typeevaluator, Keyframes keyframes)
        {
            super(s, null);
            setConverter(typeconverter);
            mKeyframes = keyframes;
            setEvaluator(typeevaluator);
        }

        public transient MultiIntValuesHolder(String s, TypeConverter typeconverter, TypeEvaluator typeevaluator, Object aobj[])
        {
            super(s, null);
            setConverter(typeconverter);
            setObjectValues(aobj);
            setEvaluator(typeevaluator);
        }
    }

    private static class PointFToFloatArray extends TypeConverter
    {

        public volatile Object convert(Object obj)
        {
            return convert((PointF)obj);
        }

        public float[] convert(PointF pointf)
        {
            mCoordinates[0] = pointf.x;
            mCoordinates[1] = pointf.y;
            return mCoordinates;
        }

        private float mCoordinates[];

        public PointFToFloatArray()
        {
            super(android/graphics/PointF, [F);
            mCoordinates = new float[2];
        }
    }

    private static class PointFToIntArray extends TypeConverter
    {

        public volatile Object convert(Object obj)
        {
            return convert((PointF)obj);
        }

        public int[] convert(PointF pointf)
        {
            mCoordinates[0] = Math.round(pointf.x);
            mCoordinates[1] = Math.round(pointf.y);
            return mCoordinates;
        }

        private int mCoordinates[];

        public PointFToIntArray()
        {
            super(android/graphics/PointF, [I);
            mCoordinates = new int[2];
        }
    }

    public static class PropertyValues
    {

        public String toString()
        {
            return (new StringBuilder()).append("property name: ").append(propertyName).append(", type: ").append(type).append(", startValue: ").append(startValue.toString()).append(", endValue: ").append(endValue.toString()).toString();
        }

        public DataSource dataSource;
        public Object endValue;
        public String propertyName;
        public Object startValue;
        public Class type;

        public PropertyValues()
        {
            dataSource = null;
        }
    }

    public static interface PropertyValues.DataSource
    {

        public abstract Object getValueAtFraction(float f);
    }


    static long _2D_wrap0(Class class1, String s)
    {
        return nGetFloatMethod(class1, s);
    }

    static long _2D_wrap1(Class class1, String s)
    {
        return nGetIntMethod(class1, s);
    }

    static void _2D_wrap10(Object obj, long l, float f, float f1)
    {
        nCallTwoFloatMethod(obj, l, f, f1);
    }

    static void _2D_wrap11(Object obj, long l, int i, int j)
    {
        nCallTwoIntMethod(obj, l, i, j);
    }

    static long _2D_wrap2(Class class1, String s, int i)
    {
        return nGetMultipleFloatMethod(class1, s, i);
    }

    static long _2D_wrap3(Class class1, String s, int i)
    {
        return nGetMultipleIntMethod(class1, s, i);
    }

    static void _2D_wrap4(Object obj, long l, float f)
    {
        nCallFloatMethod(obj, l, f);
    }

    static void _2D_wrap5(Object obj, long l, float f, float f1, float f2, float f3)
    {
        nCallFourFloatMethod(obj, l, f, f1, f2, f3);
    }

    static void _2D_wrap6(Object obj, long l, int i, int j, int k, int i1)
    {
        nCallFourIntMethod(obj, l, i, j, k, i1);
    }

    static void _2D_wrap7(Object obj, long l, int i)
    {
        nCallIntMethod(obj, l, i);
    }

    static void _2D_wrap8(Object obj, long l, float af[])
    {
        nCallMultipleFloatMethod(obj, l, af);
    }

    static void _2D_wrap9(Object obj, long l, int ai[])
    {
        nCallMultipleIntMethod(obj, l, ai);
    }

    private PropertyValuesHolder(Property property)
    {
        mSetter = null;
        mGetter = null;
        mKeyframes = null;
        mTmpValueArray = new Object[1];
        mProperty = property;
        if(property != null)
            mPropertyName = property.getName();
    }

    PropertyValuesHolder(Property property, PropertyValuesHolder propertyvaluesholder)
    {
        this(property);
    }

    private PropertyValuesHolder(String s)
    {
        mSetter = null;
        mGetter = null;
        mKeyframes = null;
        mTmpValueArray = new Object[1];
        mPropertyName = s;
    }

    PropertyValuesHolder(String s, PropertyValuesHolder propertyvaluesholder)
    {
        this(s);
    }

    private Object convertBack(Object obj)
    {
        Object obj1 = obj;
        if(mConverter != null)
        {
            if(!(mConverter instanceof BidirectionalTypeConverter))
                throw new IllegalArgumentException((new StringBuilder()).append("Converter ").append(mConverter.getClass().getName()).append(" must be a BidirectionalTypeConverter").toString());
            obj1 = ((BidirectionalTypeConverter)mConverter).convertBack(obj);
        }
        return obj1;
    }

    static String getMethodName(String s, String s1)
    {
        if(s1 == null || s1.length() == 0)
        {
            return s;
        } else
        {
            char c = Character.toUpperCase(s1.charAt(0));
            s1 = s1.substring(1);
            return (new StringBuilder()).append(s).append(c).append(s1).toString();
        }
    }

    private Method getPropertyFunction(Class class1, String s, Class class2)
    {
        Method method;
        Method method1;
        String s1;
        method = null;
        method1 = null;
        s1 = getMethodName(s, mPropertyName);
        if(class2 != null) goto _L2; else goto _L1
_L1:
        Method method2 = class1.getMethod(s1, null);
        method1 = method2;
_L3:
        if(method1 == null)
            Log.w("PropertyValuesHolder", (new StringBuilder()).append("Method ").append(getMethodName(s, mPropertyName)).append("() with type ").append(class2).append(" not found on target class ").append(class1).toString());
        return method1;
        NoSuchMethodException nosuchmethodexception1;
        nosuchmethodexception1;
        if(true) goto _L3; else goto _L2
_L2:
        int j;
        Class aclass1[] = new Class[1];
        Class aclass[];
        int i;
        Class class3;
        if(class2.equals(java/lang/Float))
            aclass = FLOAT_VARIANTS;
        else
        if(class2.equals(java/lang/Integer))
            aclass = INTEGER_VARIANTS;
        else
        if(class2.equals(java/lang/Double))
        {
            aclass = DOUBLE_VARIANTS;
        } else
        {
            aclass = new Class[1];
            aclass[0] = class2;
        }
        i = aclass.length;
        j = 0;
_L5:
        method1 = method;
        if(j >= i) goto _L3; else goto _L4
_L4:
        class3 = aclass[j];
        aclass1[0] = class3;
        method1 = class1.getMethod(s1, aclass1);
        method = method1;
        if(mConverter != null)
            break MISSING_BLOCK_LABEL_183;
        method = method1;
        mValueType = class3;
        return method1;
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        j++;
          goto _L5
    }

    private static native void nCallFloatMethod(Object obj, long l, float f);

    private static native void nCallFourFloatMethod(Object obj, long l, float f, float f1, float f2, float f3);

    private static native void nCallFourIntMethod(Object obj, long l, int i, int j, int k, int i1);

    private static native void nCallIntMethod(Object obj, long l, int i);

    private static native void nCallMultipleFloatMethod(Object obj, long l, float af[]);

    private static native void nCallMultipleIntMethod(Object obj, long l, int ai[]);

    private static native void nCallTwoFloatMethod(Object obj, long l, float f, float f1);

    private static native void nCallTwoIntMethod(Object obj, long l, int i, int j);

    private static native long nGetFloatMethod(Class class1, String s);

    private static native long nGetIntMethod(Class class1, String s);

    private static native long nGetMultipleFloatMethod(Class class1, String s, int i);

    private static native long nGetMultipleIntMethod(Class class1, String s, int i);

    public static transient PropertyValuesHolder ofFloat(Property property, float af[])
    {
        return new FloatPropertyValuesHolder(property, af);
    }

    public static transient PropertyValuesHolder ofFloat(String s, float af[])
    {
        return new FloatPropertyValuesHolder(s, af);
    }

    public static transient PropertyValuesHolder ofInt(Property property, int ai[])
    {
        return new IntPropertyValuesHolder(property, ai);
    }

    public static transient PropertyValuesHolder ofInt(String s, int ai[])
    {
        return new IntPropertyValuesHolder(s, ai);
    }

    public static transient PropertyValuesHolder ofKeyframe(Property property, Keyframe akeyframe[])
    {
        return ofKeyframes(property, KeyframeSet.ofKeyframe(akeyframe));
    }

    public static transient PropertyValuesHolder ofKeyframe(String s, Keyframe akeyframe[])
    {
        return ofKeyframes(s, KeyframeSet.ofKeyframe(akeyframe));
    }

    static PropertyValuesHolder ofKeyframes(Property property, Keyframes keyframes)
    {
        if(keyframes instanceof Keyframes.IntKeyframes)
            return new IntPropertyValuesHolder(property, (Keyframes.IntKeyframes)keyframes);
        if(keyframes instanceof Keyframes.FloatKeyframes)
        {
            return new FloatPropertyValuesHolder(property, (Keyframes.FloatKeyframes)keyframes);
        } else
        {
            property = new PropertyValuesHolder(property);
            property.mKeyframes = keyframes;
            property.mValueType = keyframes.getType();
            return property;
        }
    }

    static PropertyValuesHolder ofKeyframes(String s, Keyframes keyframes)
    {
        if(keyframes instanceof Keyframes.IntKeyframes)
            return new IntPropertyValuesHolder(s, (Keyframes.IntKeyframes)keyframes);
        if(keyframes instanceof Keyframes.FloatKeyframes)
        {
            return new FloatPropertyValuesHolder(s, (Keyframes.FloatKeyframes)keyframes);
        } else
        {
            s = new PropertyValuesHolder(s);
            s.mKeyframes = keyframes;
            s.mValueType = keyframes.getType();
            return s;
        }
    }

    public static transient PropertyValuesHolder ofMultiFloat(String s, TypeConverter typeconverter, TypeEvaluator typeevaluator, Keyframe akeyframe[])
    {
        return new MultiFloatValuesHolder(s, typeconverter, typeevaluator, KeyframeSet.ofKeyframe(akeyframe));
    }

    public static transient PropertyValuesHolder ofMultiFloat(String s, TypeConverter typeconverter, TypeEvaluator typeevaluator, Object aobj[])
    {
        return new MultiFloatValuesHolder(s, typeconverter, typeevaluator, aobj);
    }

    public static PropertyValuesHolder ofMultiFloat(String s, Path path)
    {
        path = KeyframeSet.ofPath(path);
        return new MultiFloatValuesHolder(s, new PointFToFloatArray(), null, path);
    }

    public static PropertyValuesHolder ofMultiFloat(String s, float af[][])
    {
        int i;
        int j;
        if(af.length < 2)
            throw new IllegalArgumentException("At least 2 values must be supplied");
        i = 0;
        j = 0;
_L2:
        int k;
        int l;
        if(j >= af.length)
            break MISSING_BLOCK_LABEL_87;
        if(af[j] == null)
            throw new IllegalArgumentException("values must not be null");
        k = af[j].length;
        if(j != 0)
            break; /* Loop/switch isn't completed */
        l = k;
_L4:
        j++;
        i = l;
        if(true) goto _L2; else goto _L1
_L1:
        l = i;
        if(k == i) goto _L4; else goto _L3
_L3:
        throw new IllegalArgumentException("Values must all have the same length");
        return new MultiFloatValuesHolder(s, null, new FloatArrayEvaluator(new float[i]), af);
    }

    public static transient PropertyValuesHolder ofMultiInt(String s, TypeConverter typeconverter, TypeEvaluator typeevaluator, Keyframe akeyframe[])
    {
        return new MultiIntValuesHolder(s, typeconverter, typeevaluator, KeyframeSet.ofKeyframe(akeyframe));
    }

    public static transient PropertyValuesHolder ofMultiInt(String s, TypeConverter typeconverter, TypeEvaluator typeevaluator, Object aobj[])
    {
        return new MultiIntValuesHolder(s, typeconverter, typeevaluator, aobj);
    }

    public static PropertyValuesHolder ofMultiInt(String s, Path path)
    {
        path = KeyframeSet.ofPath(path);
        return new MultiIntValuesHolder(s, new PointFToIntArray(), null, path);
    }

    public static PropertyValuesHolder ofMultiInt(String s, int ai[][])
    {
        int i;
        int j;
        if(ai.length < 2)
            throw new IllegalArgumentException("At least 2 values must be supplied");
        i = 0;
        j = 0;
_L2:
        int k;
        int l;
        if(j >= ai.length)
            break MISSING_BLOCK_LABEL_87;
        if(ai[j] == null)
            throw new IllegalArgumentException("values must not be null");
        k = ai[j].length;
        if(j != 0)
            break; /* Loop/switch isn't completed */
        l = k;
_L4:
        j++;
        i = l;
        if(true) goto _L2; else goto _L1
_L1:
        l = i;
        if(k == i) goto _L4; else goto _L3
_L3:
        throw new IllegalArgumentException("Values must all have the same length");
        return new MultiIntValuesHolder(s, null, new IntArrayEvaluator(new int[i]), ai);
    }

    public static transient PropertyValuesHolder ofObject(Property property, TypeConverter typeconverter, TypeEvaluator typeevaluator, Object aobj[])
    {
        property = new PropertyValuesHolder(property);
        property.setConverter(typeconverter);
        property.setObjectValues(aobj);
        property.setEvaluator(typeevaluator);
        return property;
    }

    public static PropertyValuesHolder ofObject(Property property, TypeConverter typeconverter, Path path)
    {
        property = new PropertyValuesHolder(property);
        property.mKeyframes = KeyframeSet.ofPath(path);
        property.mValueType = android/graphics/PointF;
        property.setConverter(typeconverter);
        return property;
    }

    public static transient PropertyValuesHolder ofObject(Property property, TypeEvaluator typeevaluator, Object aobj[])
    {
        property = new PropertyValuesHolder(property);
        property.setObjectValues(aobj);
        property.setEvaluator(typeevaluator);
        return property;
    }

    public static PropertyValuesHolder ofObject(String s, TypeConverter typeconverter, Path path)
    {
        s = new PropertyValuesHolder(s);
        s.mKeyframes = KeyframeSet.ofPath(path);
        s.mValueType = android/graphics/PointF;
        s.setConverter(typeconverter);
        return s;
    }

    public static transient PropertyValuesHolder ofObject(String s, TypeEvaluator typeevaluator, Object aobj[])
    {
        s = new PropertyValuesHolder(s);
        s.setObjectValues(aobj);
        s.setEvaluator(typeevaluator);
        return s;
    }

    private void setupGetter(Class class1)
    {
        mGetter = setupSetterOrGetter(class1, sGetterPropertyMap, "get", null);
    }

    private Method setupSetterOrGetter(Class class1, HashMap hashmap, String s, Class class2)
    {
        Object obj = null;
        hashmap;
        JVM INSTR monitorenter ;
        HashMap hashmap1 = (HashMap)hashmap.get(class1);
        boolean flag;
        Method method;
        flag = false;
        method = obj;
        if(hashmap1 == null)
            break MISSING_BLOCK_LABEL_69;
        boolean flag1 = hashmap1.containsKey(mPropertyName);
        method = obj;
        flag = flag1;
        if(!flag1)
            break MISSING_BLOCK_LABEL_69;
        method = (Method)hashmap1.get(mPropertyName);
        flag = flag1;
        if(flag)
            break MISSING_BLOCK_LABEL_118;
        method = getPropertyFunction(class1, s, class2);
        s = hashmap1;
        if(hashmap1 != null)
            break MISSING_BLOCK_LABEL_107;
        s = JVM INSTR new #155 <Class HashMap>;
        s.HashMap();
        hashmap.put(class1, s);
        s.put(mPropertyName, method);
        hashmap;
        JVM INSTR monitorexit ;
        return method;
        class1;
        throw class1;
    }

    private void setupValue(Object obj, Keyframe keyframe)
    {
        if(mProperty == null) goto _L2; else goto _L1
_L1:
        keyframe.setValue(convertBack(mProperty.get(obj)));
_L4:
        return;
_L2:
        if(mGetter == null)
        {
            setupGetter(obj.getClass());
            if(mGetter == null)
                return;
        }
        try
        {
            keyframe.setValue(convertBack(mGetter.invoke(obj, new Object[0])));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("PropertyValuesHolder", ((InvocationTargetException) (obj)).toString());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("PropertyValuesHolder", ((IllegalAccessException) (obj)).toString());
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    void calculateValue(float f)
    {
        Object obj = mKeyframes.getValue(f);
        if(mConverter != null)
            obj = mConverter.convert(obj);
        mAnimatedValue = obj;
    }

    public PropertyValuesHolder clone()
    {
        PropertyValuesHolder propertyvaluesholder;
        try
        {
            propertyvaluesholder = (PropertyValuesHolder)super.clone();
            propertyvaluesholder.mPropertyName = mPropertyName;
            propertyvaluesholder.mProperty = mProperty;
            propertyvaluesholder.mKeyframes = mKeyframes.clone();
            propertyvaluesholder.mEvaluator = mEvaluator;
        }
        catch(CloneNotSupportedException clonenotsupportedexception)
        {
            return null;
        }
        return propertyvaluesholder;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    Object getAnimatedValue()
    {
        return mAnimatedValue;
    }

    public String getPropertyName()
    {
        return mPropertyName;
    }

    public void getPropertyValues(PropertyValues propertyvalues)
    {
        init();
        propertyvalues.propertyName = mPropertyName;
        propertyvalues.type = mValueType;
        propertyvalues.startValue = mKeyframes.getValue(0.0F);
        if(propertyvalues.startValue instanceof android.util.PathParser.PathData)
            propertyvalues.startValue = new android.util.PathParser.PathData((android.util.PathParser.PathData)propertyvalues.startValue);
        propertyvalues.endValue = mKeyframes.getValue(1.0F);
        if(propertyvalues.endValue instanceof android.util.PathParser.PathData)
            propertyvalues.endValue = new android.util.PathParser.PathData((android.util.PathParser.PathData)propertyvalues.endValue);
        if((mKeyframes instanceof PathKeyframes.FloatKeyframesBase) || (mKeyframes instanceof PathKeyframes.IntKeyframesBase) || mKeyframes.getKeyframes() != null && mKeyframes.getKeyframes().size() > 2)
            propertyvalues.dataSource = new PropertyValues.DataSource() {

                public Object getValueAtFraction(float f)
                {
                    return mKeyframes.getValue(f);
                }

                final PropertyValuesHolder this$0;

            
            {
                this$0 = PropertyValuesHolder.this;
                super();
            }
            }
;
        else
            propertyvalues.dataSource = null;
    }

    public Class getValueType()
    {
        return mValueType;
    }

    void init()
    {
        TypeEvaluator typeevaluator = null;
        if(mEvaluator != null) goto _L2; else goto _L1
_L1:
        if(mValueType != java/lang/Integer) goto _L4; else goto _L3
_L3:
        typeevaluator = sIntEvaluator;
_L6:
        mEvaluator = typeevaluator;
_L2:
        if(mEvaluator != null)
            mKeyframes.setEvaluator(mEvaluator);
        return;
_L4:
        if(mValueType == java/lang/Float)
            typeevaluator = sFloatEvaluator;
        if(true) goto _L6; else goto _L5
_L5:
    }

    void setAnimatedValue(Object obj)
    {
        if(mProperty != null)
            mProperty.set(obj, getAnimatedValue());
        if(mSetter == null)
            break MISSING_BLOCK_LABEL_49;
        mTmpValueArray[0] = getAnimatedValue();
        mSetter.invoke(obj, mTmpValueArray);
_L1:
        return;
        obj;
        Log.e("PropertyValuesHolder", ((IllegalAccessException) (obj)).toString());
          goto _L1
        obj;
        Log.e("PropertyValuesHolder", ((InvocationTargetException) (obj)).toString());
          goto _L1
    }

    public void setConverter(TypeConverter typeconverter)
    {
        mConverter = typeconverter;
    }

    public void setEvaluator(TypeEvaluator typeevaluator)
    {
        mEvaluator = typeevaluator;
        mKeyframes.setEvaluator(typeevaluator);
    }

    public transient void setFloatValues(float af[])
    {
        mValueType = Float.TYPE;
        mKeyframes = KeyframeSet.ofFloat(af);
    }

    public transient void setIntValues(int ai[])
    {
        mValueType = Integer.TYPE;
        mKeyframes = KeyframeSet.ofInt(ai);
    }

    public transient void setKeyframes(Keyframe akeyframe[])
    {
        int i = akeyframe.length;
        Keyframe akeyframe1[] = new Keyframe[Math.max(i, 2)];
        mValueType = akeyframe[0].getType();
        for(int j = 0; j < i; j++)
            akeyframe1[j] = akeyframe[j];

        mKeyframes = new KeyframeSet(akeyframe1);
    }

    public transient void setObjectValues(Object aobj[])
    {
        mValueType = aobj[0].getClass();
        mKeyframes = KeyframeSet.ofObject(aobj);
        if(mEvaluator != null)
            mKeyframes.setEvaluator(mEvaluator);
    }

    public void setProperty(Property property)
    {
        mProperty = property;
    }

    public void setPropertyName(String s)
    {
        mPropertyName = s;
    }

    void setupEndValue(Object obj)
    {
        List list = mKeyframes.getKeyframes();
        if(!list.isEmpty())
            setupValue(obj, (Keyframe)list.get(list.size() - 1));
    }

    void setupSetter(Class class1)
    {
        Class class2;
        if(mConverter == null)
            class2 = mValueType;
        else
            class2 = mConverter.getTargetType();
        mSetter = setupSetterOrGetter(class1, sSetterPropertyMap, "set", class2);
    }

    void setupSetterAndGetter(Object obj)
    {
        Object obj1;
        if(mProperty == null)
            break MISSING_BLOCK_LABEL_178;
        obj1 = null;
        List list1 = mKeyframes.getKeyframes();
        if(list1 != null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L6:
        int k = 0;
_L4:
        if(k >= i)
            break; /* Loop/switch isn't completed */
        Keyframe keyframe1;
        keyframe1 = (Keyframe)list1.get(k);
        if(!keyframe1.hasValue())
            break MISSING_BLOCK_LABEL_68;
        Object obj2 = obj1;
        if(!keyframe1.valueWasSetOnStart())
            break MISSING_BLOCK_LABEL_102;
        obj2 = obj1;
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_89;
        obj2 = convertBack(mProperty.get(obj));
        keyframe1.setValue(obj2);
        keyframe1.setValueWasSetOnStart(true);
        k++;
        obj1 = obj2;
        if(true) goto _L4; else goto _L3
_L3:
        break; /* Loop/switch isn't completed */
_L2:
        i = list1.size();
        if(true) goto _L6; else goto _L5
_L5:
        return;
        ClassCastException classcastexception;
        classcastexception;
        Log.w("PropertyValuesHolder", (new StringBuilder()).append("No such property (").append(mProperty.getName()).append(") on target object ").append(obj).append(". Trying reflection instead").toString());
        mProperty = null;
        if(mProperty == null)
        {
            Class class1 = obj.getClass();
            if(mSetter == null)
                setupSetter(class1);
            List list = mKeyframes.getKeyframes();
            int j;
            int l;
            if(list == null)
                j = 0;
            else
                j = list.size();
            l = 0;
            while(l < j) 
            {
                Keyframe keyframe = (Keyframe)list.get(l);
                if(!keyframe.hasValue() || keyframe.valueWasSetOnStart())
                {
                    if(mGetter == null)
                    {
                        setupGetter(class1);
                        if(mGetter == null)
                            return;
                    }
                    try
                    {
                        keyframe.setValue(convertBack(mGetter.invoke(obj, new Object[0])));
                        keyframe.setValueWasSetOnStart(true);
                    }
                    catch(InvocationTargetException invocationtargetexception)
                    {
                        Log.e("PropertyValuesHolder", invocationtargetexception.toString());
                    }
                    catch(IllegalAccessException illegalaccessexception)
                    {
                        Log.e("PropertyValuesHolder", illegalaccessexception.toString());
                    }
                }
                l++;
            }
        }
        return;
    }

    void setupStartValue(Object obj)
    {
        List list = mKeyframes.getKeyframes();
        if(!list.isEmpty())
            setupValue(obj, (Keyframe)list.get(0));
    }

    public String toString()
    {
        return (new StringBuilder()).append(mPropertyName).append(": ").append(mKeyframes.toString()).toString();
    }

    private static Class DOUBLE_VARIANTS[];
    private static Class FLOAT_VARIANTS[];
    private static Class INTEGER_VARIANTS[];
    private static final TypeEvaluator sFloatEvaluator = new FloatEvaluator();
    private static final HashMap sGetterPropertyMap = new HashMap();
    private static final TypeEvaluator sIntEvaluator = new IntEvaluator();
    private static final HashMap sSetterPropertyMap = new HashMap();
    private Object mAnimatedValue;
    private TypeConverter mConverter;
    private TypeEvaluator mEvaluator;
    private Method mGetter;
    Keyframes mKeyframes;
    protected Property mProperty;
    String mPropertyName;
    Method mSetter;
    final Object mTmpValueArray[];
    Class mValueType;

    static 
    {
        FLOAT_VARIANTS = (new Class[] {
            Float.TYPE, java/lang/Float, Double.TYPE, Integer.TYPE, java/lang/Double, java/lang/Integer
        });
        INTEGER_VARIANTS = (new Class[] {
            Integer.TYPE, java/lang/Integer, Float.TYPE, Double.TYPE, java/lang/Float, java/lang/Double
        });
        DOUBLE_VARIANTS = (new Class[] {
            Double.TYPE, java/lang/Double, Float.TYPE, Integer.TYPE, java/lang/Float, java/lang/Integer
        });
    }
}
