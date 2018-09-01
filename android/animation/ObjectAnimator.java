// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.graphics.Path;
import android.util.Property;
import java.lang.ref.WeakReference;
import java.util.HashMap;

// Referenced classes of package android.animation:
//            ValueAnimator, PropertyValuesHolder, ArgbEvaluator, KeyframeSet, 
//            PathKeyframes, TypeEvaluator, AnimationHandler, Animator, 
//            TypeConverter

public final class ObjectAnimator extends ValueAnimator
{

    public ObjectAnimator()
    {
        mAutoCancel = false;
    }

    private ObjectAnimator(Object obj, Property property)
    {
        mAutoCancel = false;
        setTarget(obj);
        setProperty(property);
    }

    private ObjectAnimator(Object obj, String s)
    {
        mAutoCancel = false;
        setTarget(obj);
        setPropertyName(s);
    }

    private boolean hasSameTargetAndProperties(Animator animator)
    {
        if(animator instanceof ObjectAnimator)
        {
            PropertyValuesHolder apropertyvaluesholder[] = ((ObjectAnimator)animator).getValues();
            if(((ObjectAnimator)animator).getTarget() == getTarget() && mValues.length == apropertyvaluesholder.length)
            {
                for(int i = 0; i < mValues.length; i++)
                {
                    animator = mValues[i];
                    PropertyValuesHolder propertyvaluesholder = apropertyvaluesholder[i];
                    if(animator.getPropertyName() == null || animator.getPropertyName().equals(propertyvaluesholder.getPropertyName()) ^ true)
                        return false;
                }

                return true;
            }
        }
        return false;
    }

    public static transient ObjectAnimator ofArgb(Object obj, Property property, int ai[])
    {
        obj = ofInt(obj, property, ai);
        ((ObjectAnimator) (obj)).setEvaluator(ArgbEvaluator.getInstance());
        return ((ObjectAnimator) (obj));
    }

    public static transient ObjectAnimator ofArgb(Object obj, String s, int ai[])
    {
        obj = ofInt(obj, s, ai);
        ((ObjectAnimator) (obj)).setEvaluator(ArgbEvaluator.getInstance());
        return ((ObjectAnimator) (obj));
    }

    public static ObjectAnimator ofFloat(Object obj, Property property, Property property1, Path path)
    {
        path = KeyframeSet.ofPath(path);
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofKeyframes(property, path.createXFloatKeyframes()), PropertyValuesHolder.ofKeyframes(property1, path.createYFloatKeyframes())
        });
    }

    public static transient ObjectAnimator ofFloat(Object obj, Property property, float af[])
    {
        obj = new ObjectAnimator(obj, property);
        ((ObjectAnimator) (obj)).setFloatValues(af);
        return ((ObjectAnimator) (obj));
    }

    public static ObjectAnimator ofFloat(Object obj, String s, String s1, Path path)
    {
        path = KeyframeSet.ofPath(path);
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofKeyframes(s, path.createXFloatKeyframes()), PropertyValuesHolder.ofKeyframes(s1, path.createYFloatKeyframes())
        });
    }

    public static transient ObjectAnimator ofFloat(Object obj, String s, float af[])
    {
        obj = new ObjectAnimator(obj, s);
        ((ObjectAnimator) (obj)).setFloatValues(af);
        return ((ObjectAnimator) (obj));
    }

    public static ObjectAnimator ofInt(Object obj, Property property, Property property1, Path path)
    {
        path = KeyframeSet.ofPath(path);
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofKeyframes(property, path.createXIntKeyframes()), PropertyValuesHolder.ofKeyframes(property1, path.createYIntKeyframes())
        });
    }

    public static transient ObjectAnimator ofInt(Object obj, Property property, int ai[])
    {
        obj = new ObjectAnimator(obj, property);
        ((ObjectAnimator) (obj)).setIntValues(ai);
        return ((ObjectAnimator) (obj));
    }

    public static ObjectAnimator ofInt(Object obj, String s, String s1, Path path)
    {
        path = KeyframeSet.ofPath(path);
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofKeyframes(s, path.createXIntKeyframes()), PropertyValuesHolder.ofKeyframes(s1, path.createYIntKeyframes())
        });
    }

    public static transient ObjectAnimator ofInt(Object obj, String s, int ai[])
    {
        obj = new ObjectAnimator(obj, s);
        ((ObjectAnimator) (obj)).setIntValues(ai);
        return ((ObjectAnimator) (obj));
    }

    public static transient ObjectAnimator ofMultiFloat(Object obj, String s, TypeConverter typeconverter, TypeEvaluator typeevaluator, Object aobj[])
    {
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofMultiFloat(s, typeconverter, typeevaluator, aobj)
        });
    }

    public static ObjectAnimator ofMultiFloat(Object obj, String s, Path path)
    {
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofMultiFloat(s, path)
        });
    }

    public static ObjectAnimator ofMultiFloat(Object obj, String s, float af[][])
    {
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofMultiFloat(s, af)
        });
    }

    public static transient ObjectAnimator ofMultiInt(Object obj, String s, TypeConverter typeconverter, TypeEvaluator typeevaluator, Object aobj[])
    {
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofMultiInt(s, typeconverter, typeevaluator, aobj)
        });
    }

    public static ObjectAnimator ofMultiInt(Object obj, String s, Path path)
    {
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofMultiInt(s, path)
        });
    }

    public static ObjectAnimator ofMultiInt(Object obj, String s, int ai[][])
    {
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofMultiInt(s, ai)
        });
    }

    public static transient ObjectAnimator ofObject(Object obj, Property property, TypeConverter typeconverter, TypeEvaluator typeevaluator, Object aobj[])
    {
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofObject(property, typeconverter, typeevaluator, aobj)
        });
    }

    public static ObjectAnimator ofObject(Object obj, Property property, TypeConverter typeconverter, Path path)
    {
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofObject(property, typeconverter, path)
        });
    }

    public static transient ObjectAnimator ofObject(Object obj, Property property, TypeEvaluator typeevaluator, Object aobj[])
    {
        obj = new ObjectAnimator(obj, property);
        ((ObjectAnimator) (obj)).setObjectValues(aobj);
        ((ObjectAnimator) (obj)).setEvaluator(typeevaluator);
        return ((ObjectAnimator) (obj));
    }

    public static ObjectAnimator ofObject(Object obj, String s, TypeConverter typeconverter, Path path)
    {
        return ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofObject(s, typeconverter, path)
        });
    }

    public static transient ObjectAnimator ofObject(Object obj, String s, TypeEvaluator typeevaluator, Object aobj[])
    {
        obj = new ObjectAnimator(obj, s);
        ((ObjectAnimator) (obj)).setObjectValues(aobj);
        ((ObjectAnimator) (obj)).setEvaluator(typeevaluator);
        return ((ObjectAnimator) (obj));
    }

    public static transient ObjectAnimator ofPropertyValuesHolder(Object obj, PropertyValuesHolder apropertyvaluesholder[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator();
        objectanimator.setTarget(obj);
        objectanimator.setValues(apropertyvaluesholder);
        return objectanimator;
    }

    void animateValue(float f)
    {
        Object obj = getTarget();
        if(mTarget != null && obj == null)
        {
            cancel();
            return;
        }
        super.animateValue(f);
        int i = mValues.length;
        for(int j = 0; j < i; j++)
            mValues[j].setAnimatedValue(obj);

    }

    public volatile Animator clone()
    {
        return clone();
    }

    public ObjectAnimator clone()
    {
        return (ObjectAnimator)super.clone();
    }

    public volatile ValueAnimator clone()
    {
        return clone();
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    String getNameForTrace()
    {
        return (new StringBuilder()).append("animator:").append(getPropertyName()).toString();
    }

    public String getPropertyName()
    {
        Object obj;
        String s;
        obj = null;
        s = null;
        if(mPropertyName == null) goto _L2; else goto _L1
_L1:
        String s1 = mPropertyName;
_L4:
        return s1;
_L2:
        if(mProperty == null)
            break; /* Loop/switch isn't completed */
        s1 = mProperty.getName();
        if(true) goto _L4; else goto _L3
_L3:
        s1 = s;
        if(mValues == null)
            continue;
        s1 = s;
        if(mValues.length <= 0)
            continue;
        int i = 0;
        s = obj;
        do
        {
            s1 = s;
            if(i >= mValues.length)
                continue;
            if(i == 0)
                s1 = "";
            else
                s1 = (new StringBuilder()).append(s).append(",").toString();
            s = (new StringBuilder()).append(s1).append(mValues[i].getPropertyName()).toString();
            i++;
        } while(true);
        if(true) goto _L4; else goto _L5
_L5:
    }

    public Object getTarget()
    {
        Object obj = null;
        if(mTarget != null)
            obj = mTarget.get();
        return obj;
    }

    void initAnimation()
    {
        if(!mInitialized)
        {
            Object obj = getTarget();
            if(obj != null)
            {
                int i = mValues.length;
                for(int j = 0; j < i; j++)
                    mValues[j].setupSetterAndGetter(obj);

            }
            super.initAnimation();
        }
    }

    boolean isInitialized()
    {
        return mInitialized;
    }

    public void setAutoCancel(boolean flag)
    {
        mAutoCancel = flag;
    }

    public volatile Animator setDuration(long l)
    {
        return setDuration(l);
    }

    public ObjectAnimator setDuration(long l)
    {
        super.setDuration(l);
        return this;
    }

    public volatile ValueAnimator setDuration(long l)
    {
        return setDuration(l);
    }

    public transient void setFloatValues(float af[])
    {
        if(mValues == null || mValues.length == 0)
        {
            if(mProperty != null)
                setValues(new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofFloat(mProperty, af)
                });
            else
                setValues(new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofFloat(mPropertyName, af)
                });
        } else
        {
            super.setFloatValues(af);
        }
    }

    public transient void setIntValues(int ai[])
    {
        if(mValues == null || mValues.length == 0)
        {
            if(mProperty != null)
                setValues(new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofInt(mProperty, ai)
                });
            else
                setValues(new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofInt(mPropertyName, ai)
                });
        } else
        {
            super.setIntValues(ai);
        }
    }

    public transient void setObjectValues(Object aobj[])
    {
        if(mValues == null || mValues.length == 0)
        {
            if(mProperty != null)
                setValues(new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofObject(mProperty, (TypeEvaluator)null, aobj)
                });
            else
                setValues(new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofObject(mPropertyName, (TypeEvaluator)null, aobj)
                });
        } else
        {
            super.setObjectValues(aobj);
        }
    }

    public void setProperty(Property property)
    {
        if(mValues != null)
        {
            PropertyValuesHolder propertyvaluesholder = mValues[0];
            String s = propertyvaluesholder.getPropertyName();
            propertyvaluesholder.setProperty(property);
            mValuesMap.remove(s);
            mValuesMap.put(mPropertyName, propertyvaluesholder);
        }
        if(mProperty != null)
            mPropertyName = property.getName();
        mProperty = property;
        mInitialized = false;
    }

    public void setPropertyName(String s)
    {
        if(mValues != null)
        {
            PropertyValuesHolder propertyvaluesholder = mValues[0];
            String s1 = propertyvaluesholder.getPropertyName();
            propertyvaluesholder.setPropertyName(s);
            mValuesMap.remove(s1);
            mValuesMap.put(s, propertyvaluesholder);
        }
        mPropertyName = s;
        mInitialized = false;
    }

    public void setTarget(Object obj)
    {
        Object obj1 = null;
        if(getTarget() != obj)
        {
            if(isStarted())
                cancel();
            if(obj == null)
                obj = obj1;
            else
                obj = new WeakReference(obj);
            mTarget = ((WeakReference) (obj));
            mInitialized = false;
        }
    }

    public void setupEndValues()
    {
        initAnimation();
        Object obj = getTarget();
        if(obj != null)
        {
            int i = mValues.length;
            for(int j = 0; j < i; j++)
                mValues[j].setupEndValue(obj);

        }
    }

    public void setupStartValues()
    {
        initAnimation();
        Object obj = getTarget();
        if(obj != null)
        {
            int i = mValues.length;
            for(int j = 0; j < i; j++)
                mValues[j].setupStartValue(obj);

        }
    }

    boolean shouldAutoCancel(AnimationHandler.AnimationFrameCallback animationframecallback)
    {
        if(animationframecallback == null)
            return false;
        if(animationframecallback instanceof ObjectAnimator)
        {
            animationframecallback = (ObjectAnimator)animationframecallback;
            if(((ObjectAnimator) (animationframecallback)).mAutoCancel && hasSameTargetAndProperties(animationframecallback))
                return true;
        }
        return false;
    }

    public void start()
    {
        AnimationHandler.getInstance().autoCancelBasedOn(this);
        super.start();
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("ObjectAnimator@").append(Integer.toHexString(hashCode())).append(", target ").append(getTarget()).toString();
        String s1 = s;
        if(mValues != null)
        {
            int i = 0;
            do
            {
                s1 = s;
                if(i >= mValues.length)
                    break;
                s = (new StringBuilder()).append(s).append("\n    ").append(mValues[i].toString()).toString();
                i++;
            } while(true);
        }
        return s1;
    }

    private static final boolean DBG = false;
    private static final String LOG_TAG = "ObjectAnimator";
    private boolean mAutoCancel;
    private Property mProperty;
    private String mPropertyName;
    private WeakReference mTarget;
}
