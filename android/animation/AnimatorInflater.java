// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.content.Context;
import android.content.res.*;
import android.util.*;
import android.view.InflateException;
import android.view.animation.AnimationUtils;
import android.view.animation.BaseInterpolator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.animation:
//            AnimatorSet, Animator, ValueAnimator, Keyframe, 
//            StateListAnimator, PropertyValuesHolder, ArgbEvaluator, ObjectAnimator, 
//            KeyframeSet, PathKeyframes, TypeEvaluator

public class AnimatorInflater
{
    private static class PathDataEvaluator
        implements TypeEvaluator
    {

        public android.util.PathParser.PathData evaluate(float f, android.util.PathParser.PathData pathdata, android.util.PathParser.PathData pathdata1)
        {
            if(!PathParser.interpolatePathData(mPathData, pathdata, pathdata1, f))
                throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
            else
                return mPathData;
        }

        public volatile Object evaluate(float f, Object obj, Object obj1)
        {
            return evaluate(f, (android.util.PathParser.PathData)obj, (android.util.PathParser.PathData)obj1);
        }

        private final android.util.PathParser.PathData mPathData;

        private PathDataEvaluator()
        {
            mPathData = new android.util.PathParser.PathData();
        }

        PathDataEvaluator(PathDataEvaluator pathdataevaluator)
        {
            this();
        }
    }


    public AnimatorInflater()
    {
    }

    private static Animator createAnimatorFromXml(Resources resources, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser, float f)
        throws XmlPullParserException, IOException
    {
        return createAnimatorFromXml(resources, theme, xmlpullparser, Xml.asAttributeSet(xmlpullparser), null, 0, f);
    }

    private static Animator createAnimatorFromXml(Resources resources, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser, AttributeSet attributeset, AnimatorSet animatorset, int i, float f)
        throws XmlPullParserException, IOException
    {
        Object obj = null;
        ArrayList arraylist = null;
        int j = xmlpullparser.getDepth();
        do
        {
            int k = xmlpullparser.next();
            if(k == 3 && xmlpullparser.getDepth() <= j || k == 1)
                break;
            if(k == 2)
            {
                Object obj1 = xmlpullparser.getName();
                boolean flag = false;
                if(((String) (obj1)).equals("objectAnimator"))
                    obj1 = loadObjectAnimator(resources, theme, attributeset, f);
                else
                if(((String) (obj1)).equals("animator"))
                    obj1 = loadAnimator(resources, theme, attributeset, null, f);
                else
                if(((String) (obj1)).equals("set"))
                {
                    obj = new AnimatorSet();
                    int i1;
                    if(theme != null)
                        obj1 = theme.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AnimatorSet, 0, 0);
                    else
                        obj1 = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.AnimatorSet);
                    ((Animator) (obj)).appendChangingConfigurations(((TypedArray) (obj1)).getChangingConfigurations());
                    i1 = ((TypedArray) (obj1)).getInt(0, 0);
                    createAnimatorFromXml(resources, theme, xmlpullparser, attributeset, (AnimatorSet)obj, i1, f);
                    ((TypedArray) (obj1)).recycle();
                    obj1 = obj;
                } else
                if(((String) (obj1)).equals("propertyValuesHolder"))
                {
                    obj1 = loadValues(resources, theme, xmlpullparser, Xml.asAttributeSet(xmlpullparser));
                    if(obj1 != null && obj != null && (obj instanceof ValueAnimator))
                        ((ValueAnimator)obj).setValues(((PropertyValuesHolder []) (obj1)));
                    flag = true;
                    obj1 = obj;
                } else
                {
                    throw new RuntimeException((new StringBuilder()).append("Unknown animator name: ").append(xmlpullparser.getName()).toString());
                }
                obj = obj1;
                if(animatorset != null)
                {
                    obj = obj1;
                    if(flag ^ true)
                    {
                        ArrayList arraylist1 = arraylist;
                        if(arraylist == null)
                            arraylist1 = new ArrayList();
                        arraylist1.add(obj1);
                        obj = obj1;
                        arraylist = arraylist1;
                    }
                }
            }
        } while(true);
        if(animatorset != null && arraylist != null)
        {
            resources = new Animator[arraylist.size()];
            int l = 0;
            for(theme = arraylist.iterator(); theme.hasNext();)
            {
                resources[l] = (Animator)theme.next();
                l++;
            }

            if(i == 0)
                animatorset.playTogether(resources);
            else
                animatorset.playSequentially(resources);
        }
        return ((Animator) (obj));
    }

    private static Keyframe createNewKeyframe(Keyframe keyframe, float f)
    {
        if(keyframe.getType() == Float.TYPE)
            keyframe = Keyframe.ofFloat(f);
        else
        if(keyframe.getType() == Integer.TYPE)
            keyframe = Keyframe.ofInt(f);
        else
            keyframe = Keyframe.ofObject(f);
        return keyframe;
    }

    private static StateListAnimator createStateListAnimatorFromXml(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws IOException, XmlPullParserException
    {
        StateListAnimator statelistanimator = new StateListAnimator();
        do
            switch(xmlpullparser.next())
            {
            case 1: // '\001'
            case 3: // '\003'
                return statelistanimator;

            case 2: // '\002'
                Animator animator = null;
                if("item".equals(xmlpullparser.getName()))
                {
                    int i = xmlpullparser.getAttributeCount();
                    int ai[] = new int[i];
                    int j = 0;
                    int k = 0;
                    while(j < i) 
                    {
                        int l = attributeset.getAttributeNameResource(j);
                        if(l == 0x10101cd)
                        {
                            animator = loadAnimator(context, attributeset.getAttributeResourceValue(j, 0));
                        } else
                        {
                            int i1 = k + 1;
                            if(!attributeset.getAttributeBooleanValue(j, false))
                                l = -l;
                            ai[k] = l;
                            k = i1;
                        }
                        j++;
                    }
                    Animator animator1 = animator;
                    if(animator == null)
                        animator1 = createAnimatorFromXml(context.getResources(), context.getTheme(), xmlpullparser, 1.0F);
                    if(animator1 == null)
                        throw new android.content.res.Resources.NotFoundException("animation state item must have a valid animation");
                    statelistanimator.addState(StateSet.trimStateSet(ai, k), animator1);
                }
                break;
            }
        while(true);
    }

    private static void distributeKeyframes(Keyframe akeyframe[], float f, int i, int j)
    {
        f /= (j - i) + 2;
        for(; i <= j; i++)
            akeyframe[i].setFraction(akeyframe[i - 1].getFraction() + f);

    }

    private static void dumpKeyframes(Object aobj[], String s)
    {
        if(aobj == null || aobj.length == 0)
            return;
        Log.d("AnimatorInflater", s);
        int i = aobj.length;
        int j = 0;
        while(j < i) 
        {
            Keyframe keyframe = (Keyframe)aobj[j];
            StringBuilder stringbuilder = (new StringBuilder()).append("Keyframe ").append(j).append(": fraction ");
            if(keyframe.getFraction() < 0.0F)
                s = "null";
            else
                s = Float.valueOf(keyframe.getFraction());
            stringbuilder = stringbuilder.append(s).append(", ").append(", value : ");
            if(keyframe.hasValue())
                s = ((String) (keyframe.getValue()));
            else
                s = "null";
            Log.d("AnimatorInflater", stringbuilder.append(s).toString());
            j++;
        }
    }

    private static int getChangingConfigs(Resources resources, int i)
    {
        TypedValue typedvalue = sTmpTypedValue;
        typedvalue;
        JVM INSTR monitorenter ;
        resources.getValue(i, sTmpTypedValue, true);
        i = sTmpTypedValue.changingConfigurations;
        typedvalue;
        JVM INSTR monitorexit ;
        return i;
        resources;
        throw resources;
    }

    private static PropertyValuesHolder getPVH(TypedArray typedarray, int i, int j, int k, String s)
    {
        Object obj;
        boolean flag;
        int l;
        boolean flag1;
        int i1;
        int j1;
        Object obj1;
        obj = typedarray.peekValue(j);
        if(obj != null)
            flag = true;
        else
            flag = false;
        if(flag)
            l = ((TypedValue) (obj)).type;
        else
            l = 0;
        obj = typedarray.peekValue(k);
        if(obj != null)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
            i1 = ((TypedValue) (obj)).type;
        else
            i1 = 0;
        j1 = i;
        if(i == 4)
            if(flag && isColorType(l) || flag1 && isColorType(i1))
                j1 = 3;
            else
                j1 = 0;
        if(j1 == 0)
            i = 1;
        else
            i = 0;
        obj = null;
        obj1 = null;
        if(j1 != 2) goto _L2; else goto _L1
_L1:
label0:
        {
            String s1 = typedarray.getString(j);
            String s2 = typedarray.getString(k);
            android.util.PathParser.PathData pathdata;
            if(s1 == null)
                obj = null;
            else
                obj = new android.util.PathParser.PathData(s1);
            if(s2 == null)
                pathdata = null;
            else
                pathdata = new android.util.PathParser.PathData(s2);
            if(obj == null)
            {
                typedarray = obj1;
                if(pathdata == null)
                    break label0;
            }
            if(obj != null)
            {
                typedarray = new PathDataEvaluator(null);
                if(pathdata != null)
                {
                    if(!PathParser.canMorph(((android.util.PathParser.PathData) (obj)), pathdata))
                        throw new InflateException((new StringBuilder()).append(" Can't morph from ").append(s1).append(" to ").append(s2).toString());
                    typedarray = PropertyValuesHolder.ofObject(s, typedarray, new Object[] {
                        obj, pathdata
                    });
                } else
                {
                    typedarray = PropertyValuesHolder.ofObject(s, typedarray, new Object[] {
                        obj
                    });
                }
            } else
            {
                typedarray = obj1;
                if(pathdata != null)
                    typedarray = PropertyValuesHolder.ofObject(s, new PathDataEvaluator(null), new Object[] {
                        pathdata
                    });
            }
        }
_L4:
        return typedarray;
_L2:
        ArgbEvaluator argbevaluator = null;
        if(j1 == 3)
            argbevaluator = ArgbEvaluator.getInstance();
        if(i == 0)
            break; /* Loop/switch isn't completed */
        if(flag)
        {
            float f;
            if(l == 5)
                f = typedarray.getDimension(j, 0.0F);
            else
                f = typedarray.getFloat(j, 0.0F);
            if(flag1)
            {
                float f2;
                if(i1 == 5)
                    f2 = typedarray.getDimension(k, 0.0F);
                else
                    f2 = typedarray.getFloat(k, 0.0F);
                obj = PropertyValuesHolder.ofFloat(s, new float[] {
                    f, f2
                });
            } else
            {
                obj = PropertyValuesHolder.ofFloat(s, new float[] {
                    f
                });
            }
        } else
        {
            float f1;
            if(i1 == 5)
                f1 = typedarray.getDimension(k, 0.0F);
            else
                f1 = typedarray.getFloat(k, 0.0F);
            obj = PropertyValuesHolder.ofFloat(s, new float[] {
                f1
            });
        }
_L5:
        typedarray = ((TypedArray) (obj));
        if(obj != null)
        {
            typedarray = ((TypedArray) (obj));
            if(argbevaluator != null)
            {
                ((PropertyValuesHolder) (obj)).setEvaluator(argbevaluator);
                typedarray = ((TypedArray) (obj));
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        if(flag)
        {
            if(l == 5)
                i = (int)typedarray.getDimension(j, 0.0F);
            else
            if(isColorType(l))
                i = typedarray.getColor(j, 0);
            else
                i = typedarray.getInt(j, 0);
            if(flag1)
            {
                if(i1 == 5)
                    j = (int)typedarray.getDimension(k, 0.0F);
                else
                if(isColorType(i1))
                    j = typedarray.getColor(k, 0);
                else
                    j = typedarray.getInt(k, 0);
                obj = PropertyValuesHolder.ofInt(s, new int[] {
                    i, j
                });
            } else
            {
                obj = PropertyValuesHolder.ofInt(s, new int[] {
                    i
                });
            }
        } else
        if(flag1)
        {
            if(i1 == 5)
                i = (int)typedarray.getDimension(k, 0.0F);
            else
            if(isColorType(i1))
                i = typedarray.getColor(k, 0);
            else
                i = typedarray.getInt(k, 0);
            obj = PropertyValuesHolder.ofInt(s, new int[] {
                i
            });
        }
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    private static int inferValueTypeFromValues(TypedArray typedarray, int i, int j)
    {
        TypedValue typedvalue = typedarray.peekValue(i);
        int k;
        int l;
        if(typedvalue != null)
            i = 1;
        else
            i = 0;
        if(i != 0)
            k = typedvalue.type;
        else
            k = 0;
        typedarray = typedarray.peekValue(j);
        if(typedarray != null)
            j = 1;
        else
            j = 0;
        if(j != 0)
            l = ((TypedValue) (typedarray)).type;
        else
            l = 0;
        if(i != 0 && isColorType(k) || j != 0 && isColorType(l))
            i = 3;
        else
            i = 0;
        return i;
    }

    private static int inferValueTypeOfKeyframe(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset)
    {
        byte byte0;
        if(theme != null)
            resources = theme.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Keyframe, 0, 0);
        else
            resources = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.Keyframe);
        theme = resources.peekValue(0);
        if(theme != null)
            byte0 = 1;
        else
            byte0 = 0;
        if(byte0 != 0 && isColorType(((TypedValue) (theme)).type))
            byte0 = 3;
        else
            byte0 = 0;
        resources.recycle();
        return byte0;
    }

    private static boolean isColorType(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 28)
        {
            flag1 = flag;
            if(i <= 31)
                flag1 = true;
        }
        return flag1;
    }

    public static Animator loadAnimator(Context context, int i)
        throws android.content.res.Resources.NotFoundException
    {
        return loadAnimator(context.getResources(), context.getTheme(), i);
    }

    public static Animator loadAnimator(Resources resources, android.content.res.Resources.Theme theme, int i)
        throws android.content.res.Resources.NotFoundException
    {
        return loadAnimator(resources, theme, i, 1.0F);
    }

    public static Animator loadAnimator(Resources resources, android.content.res.Resources.Theme theme, int i, float f)
        throws android.content.res.Resources.NotFoundException
    {
        ConfigurationBoundResourceCache configurationboundresourcecache;
        Object obj;
        Object obj1;
        XmlResourceParser xmlresourceparser;
        configurationboundresourcecache = resources.getAnimatorCache();
        obj = (Animator)configurationboundresourcecache.getInstance(i, resources, theme);
        if(obj != null)
            return ((Animator) (obj));
        obj = null;
        obj1 = null;
        xmlresourceparser = null;
        XmlResourceParser xmlresourceparser1 = resources.getAnimation(i);
        xmlresourceparser = xmlresourceparser1;
        obj = xmlresourceparser1;
        obj1 = xmlresourceparser1;
        Animator animator = createAnimatorFromXml(resources, theme, xmlresourceparser1, f);
        obj = animator;
        if(animator == null)
            break MISSING_BLOCK_LABEL_173;
        xmlresourceparser = xmlresourceparser1;
        obj = xmlresourceparser1;
        obj1 = xmlresourceparser1;
        animator.appendChangingConfigurations(getChangingConfigs(resources, i));
        xmlresourceparser = xmlresourceparser1;
        obj = xmlresourceparser1;
        obj1 = xmlresourceparser1;
        ConstantState constantstate = animator.createConstantState();
        obj = animator;
        if(constantstate == null)
            break MISSING_BLOCK_LABEL_173;
        xmlresourceparser = xmlresourceparser1;
        obj = xmlresourceparser1;
        obj1 = xmlresourceparser1;
        configurationboundresourcecache.put(i, theme, constantstate);
        xmlresourceparser = xmlresourceparser1;
        obj = xmlresourceparser1;
        obj1 = xmlresourceparser1;
        resources = (Animator)constantstate.newInstance(resources, theme);
        obj = resources;
        if(xmlresourceparser1 != null)
            xmlresourceparser1.close();
        return ((Animator) (obj));
        resources;
        obj = xmlresourceparser;
        obj1 = JVM INSTR new #255 <Class android.content.res.Resources$NotFoundException>;
        obj = xmlresourceparser;
        theme = JVM INSTR new #149 <Class StringBuilder>;
        obj = xmlresourceparser;
        theme.StringBuilder();
        obj = xmlresourceparser;
        ((android.content.res.Resources.NotFoundException) (obj1)).android.content.res.Resources.NotFoundException(theme.append("Can't load animation resource ID #0x").append(Integer.toHexString(i)).toString());
        obj = xmlresourceparser;
        ((android.content.res.Resources.NotFoundException) (obj1)).initCause(resources);
        obj = xmlresourceparser;
        throw obj1;
        resources;
        if(obj != null)
            ((XmlResourceParser) (obj)).close();
        throw resources;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        obj = obj1;
        theme = JVM INSTR new #255 <Class android.content.res.Resources$NotFoundException>;
        obj = obj1;
        resources = JVM INSTR new #149 <Class StringBuilder>;
        obj = obj1;
        resources.StringBuilder();
        obj = obj1;
        theme.android.content.res.Resources.NotFoundException(resources.append("Can't load animation resource ID #0x").append(Integer.toHexString(i)).toString());
        obj = obj1;
        theme.initCause(xmlpullparserexception);
        obj = obj1;
        throw theme;
    }

    private static ValueAnimator loadAnimator(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset, ValueAnimator valueanimator, float f)
        throws android.content.res.Resources.NotFoundException
    {
        AttributeSet attributeset1 = null;
        TypedArray typedarray;
        if(theme != null)
            typedarray = theme.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Animator, 0, 0);
        else
            typedarray = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.Animator);
        if(valueanimator != null)
        {
            int i;
            if(theme != null)
                attributeset = theme.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.PropertyAnimator, 0, 0);
            else
                attributeset = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.PropertyAnimator);
            valueanimator.appendChangingConfigurations(attributeset.getChangingConfigurations());
            attributeset1 = attributeset;
        }
        attributeset = valueanimator;
        if(valueanimator == null)
            attributeset = new ValueAnimator();
        attributeset.appendChangingConfigurations(typedarray.getChangingConfigurations());
        parseAnimatorFromTypeArray(attributeset, typedarray, attributeset1, f);
        i = typedarray.getResourceId(0, 0);
        if(i > 0)
        {
            resources = AnimationUtils.loadInterpolator(resources, theme, i);
            if(resources instanceof BaseInterpolator)
                attributeset.appendChangingConfigurations(((BaseInterpolator)resources).getChangingConfiguration());
            attributeset.setInterpolator(resources);
        }
        typedarray.recycle();
        if(attributeset1 != null)
            attributeset1.recycle();
        return attributeset;
    }

    private static Keyframe loadKeyframe(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset, int i)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray;
        float f;
        int j;
        Object obj;
        boolean flag;
        if(theme != null)
            typedarray = theme.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Keyframe, 0, 0);
        else
            typedarray = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.Keyframe);
        obj = null;
        f = typedarray.getFloat(3, -1F);
        attributeset = typedarray.peekValue(0);
        if(attributeset != null)
            flag = true;
        else
            flag = false;
        j = i;
        if(i == 4)
            if(flag && isColorType(((TypedValue) (attributeset)).type))
                j = 3;
            else
                j = 0;
        if(!flag) goto _L2; else goto _L1
_L1:
        attributeset = obj;
        j;
        JVM INSTR tableswitch 0 3: default 112
    //                   0 169
    //                   1 185
    //                   2 115
    //                   3 185;
           goto _L3 _L4 _L5 _L6 _L5
_L6:
        break; /* Loop/switch isn't completed */
_L3:
        attributeset = obj;
_L8:
        i = typedarray.getResourceId(1, 0);
        if(i > 0)
            attributeset.setInterpolator(AnimationUtils.loadInterpolator(resources, theme, i));
        typedarray.recycle();
        return attributeset;
_L4:
        attributeset = Keyframe.ofFloat(f, typedarray.getFloat(0, 0.0F));
        continue; /* Loop/switch isn't completed */
_L5:
        attributeset = Keyframe.ofInt(f, typedarray.getInt(0, 0));
        continue; /* Loop/switch isn't completed */
_L2:
        if(j == 0)
            attributeset = Keyframe.ofFloat(f);
        else
            attributeset = Keyframe.ofInt(f);
        if(true) goto _L8; else goto _L7
_L7:
    }

    private static ObjectAnimator loadObjectAnimator(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset, float f)
        throws android.content.res.Resources.NotFoundException
    {
        ObjectAnimator objectanimator = new ObjectAnimator();
        loadAnimator(resources, theme, attributeset, objectanimator, f);
        return objectanimator;
    }

    private static PropertyValuesHolder loadPvh(Resources resources, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser, String s, int i)
        throws XmlPullParserException, IOException
    {
        int j;
        int i1;
        Object obj = null;
        ArrayList arraylist = null;
        j = i;
        do
        {
            i = xmlpullparser.next();
            if(i == 3 || i == 1)
                break;
            if(xmlpullparser.getName().equals("keyframe"))
            {
                i = j;
                if(j == 4)
                    i = inferValueTypeOfKeyframe(resources, theme, Xml.asAttributeSet(xmlpullparser));
                Keyframe keyframe = loadKeyframe(resources, theme, Xml.asAttributeSet(xmlpullparser), i);
                ArrayList arraylist1 = arraylist;
                if(keyframe != null)
                {
                    arraylist1 = arraylist;
                    if(arraylist == null)
                        arraylist1 = new ArrayList();
                    arraylist1.add(keyframe);
                }
                xmlpullparser.next();
                arraylist = arraylist1;
                j = i;
            }
        } while(true);
        resources = obj;
        if(arraylist == null)
            break MISSING_BLOCK_LABEL_452;
        int k = arraylist.size();
        resources = obj;
        if(k <= 0)
            break MISSING_BLOCK_LABEL_452;
        resources = (Keyframe)arraylist.get(0);
        theme = (Keyframe)arraylist.get(k - 1);
        float f = theme.getFraction();
        i = k;
        if(f < 1.0F)
            if(f < 0.0F)
            {
                theme.setFraction(1.0F);
                i = k;
            } else
            {
                arraylist.add(arraylist.size(), createNewKeyframe(theme, 1.0F));
                i = k + 1;
            }
        f = resources.getFraction();
        i1 = i;
        if(f != 0.0F)
            if(f < 0.0F)
            {
                resources.setFraction(0.0F);
                i1 = i;
            } else
            {
                arraylist.add(0, createNewKeyframe(resources, 0.0F));
                i1 = i + 1;
            }
        resources = new Keyframe[i1];
        arraylist.toArray(resources);
        i = 0;
        if(i >= i1)
            break; /* Loop/switch isn't completed */
        theme = resources[i];
        if(theme.getFraction() < 0.0F)
            if(i == 0)
            {
                theme.setFraction(0.0F);
            } else
            {
label0:
                {
                    if(i != i1 - 1)
                        break label0;
                    theme.setFraction(1.0F);
                }
            }
_L5:
        i++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_260;
        int l;
        int j1;
        j1 = i;
        l = i + 1;
_L3:
label1:
        {
            if(l < i1 - 1 && resources[l].getFraction() < 0.0F)
                break label1;
            distributeKeyframes(resources, resources[j1 + 1].getFraction() - resources[i - 1].getFraction(), i, j1);
        }
        continue; /* Loop/switch isn't completed */
        j1 = l;
        l++;
        if(true) goto _L3; else goto _L1
_L1:
        theme = PropertyValuesHolder.ofKeyframe(s, resources);
        resources = theme;
        if(j == 3)
        {
            theme.setEvaluator(ArgbEvaluator.getInstance());
            resources = theme;
        }
        return resources;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static StateListAnimator loadStateListAnimator(Context context, int i)
        throws android.content.res.Resources.NotFoundException
    {
        Resources resources;
        ConfigurationBoundResourceCache configurationboundresourcecache;
        android.content.res.Resources.Theme theme;
        Object obj;
        Object obj1;
        XmlResourceParser xmlresourceparser;
        resources = context.getResources();
        configurationboundresourcecache = resources.getStateListAnimatorCache();
        theme = context.getTheme();
        obj = (StateListAnimator)configurationboundresourcecache.getInstance(i, resources, theme);
        if(obj != null)
            return ((StateListAnimator) (obj));
        obj = null;
        obj1 = null;
        xmlresourceparser = null;
        Object obj2 = resources.getAnimation(i);
        xmlresourceparser = ((XmlResourceParser) (obj2));
        obj = obj2;
        obj1 = obj2;
        StateListAnimator statelistanimator = createStateListAnimatorFromXml(context, ((XmlPullParser) (obj2)), Xml.asAttributeSet(((XmlPullParser) (obj2))));
        context = statelistanimator;
        if(statelistanimator == null)
            break MISSING_BLOCK_LABEL_182;
        xmlresourceparser = ((XmlResourceParser) (obj2));
        obj = obj2;
        obj1 = obj2;
        statelistanimator.appendChangingConfigurations(getChangingConfigs(resources, i));
        xmlresourceparser = ((XmlResourceParser) (obj2));
        obj = obj2;
        obj1 = obj2;
        ConstantState constantstate = statelistanimator.createConstantState();
        context = statelistanimator;
        if(constantstate == null)
            break MISSING_BLOCK_LABEL_182;
        xmlresourceparser = ((XmlResourceParser) (obj2));
        obj = obj2;
        obj1 = obj2;
        configurationboundresourcecache.put(i, theme, constantstate);
        xmlresourceparser = ((XmlResourceParser) (obj2));
        obj = obj2;
        obj1 = obj2;
        context = (StateListAnimator)constantstate.newInstance(resources, theme);
        if(obj2 != null)
            ((XmlResourceParser) (obj2)).close();
        return context;
        obj1;
        obj = xmlresourceparser;
        context = JVM INSTR new #255 <Class android.content.res.Resources$NotFoundException>;
        obj = xmlresourceparser;
        obj2 = JVM INSTR new #149 <Class StringBuilder>;
        obj = xmlresourceparser;
        ((StringBuilder) (obj2)).StringBuilder();
        obj = xmlresourceparser;
        context.android.content.res.Resources.NotFoundException(((StringBuilder) (obj2)).append("Can't load state list animator resource ID #0x").append(Integer.toHexString(i)).toString());
        obj = xmlresourceparser;
        context.initCause(((Throwable) (obj1)));
        obj = xmlresourceparser;
        throw context;
        context;
        if(obj != null)
            ((XmlResourceParser) (obj)).close();
        throw context;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        obj = obj1;
        context = JVM INSTR new #255 <Class android.content.res.Resources$NotFoundException>;
        obj = obj1;
        obj2 = JVM INSTR new #149 <Class StringBuilder>;
        obj = obj1;
        ((StringBuilder) (obj2)).StringBuilder();
        obj = obj1;
        context.android.content.res.Resources.NotFoundException(((StringBuilder) (obj2)).append("Can't load state list animator resource ID #0x").append(Integer.toHexString(i)).toString());
        obj = obj1;
        context.initCause(xmlpullparserexception);
        obj = obj1;
        throw context;
    }

    private static PropertyValuesHolder[] loadValues(Resources resources, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        Object obj = null;
        do
        {
            int i = xmlpullparser.getEventType();
            if(i != 3 && i != 1)
            {
                if(i != 2)
                {
                    xmlpullparser.next();
                } else
                {
                    Object obj1 = obj;
                    if(xmlpullparser.getName().equals("propertyValuesHolder"))
                    {
                        int j;
                        TypedArray typedarray;
                        String s;
                        Object obj2;
                        if(theme != null)
                            typedarray = theme.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.PropertyValuesHolder, 0, 0);
                        else
                            typedarray = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.PropertyValuesHolder);
                        s = typedarray.getString(3);
                        j = typedarray.getInt(2, 4);
                        obj1 = loadPvh(resources, theme, xmlpullparser, s, j);
                        obj2 = obj1;
                        if(obj1 == null)
                            obj2 = getPVH(typedarray, j, 0, 1, s);
                        obj1 = obj;
                        if(obj2 != null)
                        {
                            obj1 = obj;
                            if(obj == null)
                                obj1 = new ArrayList();
                            ((ArrayList) (obj1)).add(obj2);
                        }
                        typedarray.recycle();
                    }
                    xmlpullparser.next();
                    obj = obj1;
                }
            } else
            {
                resources = null;
                if(obj != null)
                {
                    int l = ((ArrayList) (obj)).size();
                    theme = new PropertyValuesHolder[l];
                    int k = 0;
                    do
                    {
                        resources = theme;
                        if(k >= l)
                            break;
                        theme[k] = (PropertyValuesHolder)((ArrayList) (obj)).get(k);
                        k++;
                    } while(true);
                }
                return resources;
            }
        } while(true);
    }

    private static void parseAnimatorFromTypeArray(ValueAnimator valueanimator, TypedArray typedarray, TypedArray typedarray1, float f)
    {
        long l = typedarray.getInt(1, 300);
        long l1 = typedarray.getInt(2, 0);
        int i = typedarray.getInt(7, 4);
        int j = i;
        if(i == 4)
            j = inferValueTypeFromValues(typedarray, 5, 6);
        PropertyValuesHolder propertyvaluesholder = getPVH(typedarray, j, 5, 6, "");
        if(propertyvaluesholder != null)
            valueanimator.setValues(new PropertyValuesHolder[] {
                propertyvaluesholder
            });
        valueanimator.setDuration(l);
        valueanimator.setStartDelay(l1);
        if(typedarray.hasValue(3))
            valueanimator.setRepeatCount(typedarray.getInt(3, 0));
        if(typedarray.hasValue(4))
            valueanimator.setRepeatMode(typedarray.getInt(4, 1));
        if(typedarray1 != null)
            setupObjectAnimator(valueanimator, typedarray1, j, f);
    }

    private static TypeEvaluator setupAnimatorForPath(ValueAnimator valueanimator, TypedArray typedarray)
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        String s = typedarray.getString(5);
        String s1 = typedarray.getString(6);
        if(s == null)
            obj1 = null;
        else
            obj1 = new android.util.PathParser.PathData(s);
        if(s1 == null)
            obj2 = null;
        else
            obj2 = new android.util.PathParser.PathData(s1);
        if(obj1 == null) goto _L2; else goto _L1
_L1:
        if(obj2 != null)
        {
            valueanimator.setObjectValues(new Object[] {
                obj1, obj2
            });
            if(!PathParser.canMorph(((android.util.PathParser.PathData) (obj1)), ((android.util.PathParser.PathData) (obj2))))
                throw new InflateException((new StringBuilder()).append(typedarray.getPositionDescription()).append(" Can't morph from ").append(s).append(" to ").append(s1).toString());
        } else
        {
            valueanimator.setObjectValues(new Object[] {
                obj1
            });
        }
        typedarray = new PathDataEvaluator(null);
_L4:
        return typedarray;
_L2:
        typedarray = obj;
        if(obj2 != null)
        {
            valueanimator.setObjectValues(new Object[] {
                obj2
            });
            typedarray = new PathDataEvaluator(null);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static void setupObjectAnimator(ValueAnimator valueanimator, TypedArray typedarray, int i, float f)
    {
        ObjectAnimator objectanimator;
        objectanimator = (ObjectAnimator)valueanimator;
        valueanimator = typedarray.getString(1);
        if(valueanimator == null) goto _L2; else goto _L1
_L1:
        String s;
        String s1;
        int j;
label0:
        {
            s = typedarray.getString(2);
            s1 = typedarray.getString(3);
            if(i != 2)
            {
                j = i;
                if(i != 4)
                    break label0;
            }
            j = 0;
        }
        if(s == null && s1 == null)
            throw new InflateException((new StringBuilder()).append(typedarray.getPositionDescription()).append(" propertyXName or propertyYName is needed for PathData").toString());
        valueanimator = KeyframeSet.ofPath(PathParser.createPathFromPathData(valueanimator), 0.5F * f);
        PropertyValuesHolder propertyvaluesholder;
        Object obj;
        if(j == 0)
        {
            typedarray = valueanimator.createXFloatKeyframes();
            valueanimator = valueanimator.createYFloatKeyframes();
        } else
        {
            typedarray = valueanimator.createXIntKeyframes();
            valueanimator = valueanimator.createYIntKeyframes();
        }
        propertyvaluesholder = null;
        obj = null;
        if(s != null)
            propertyvaluesholder = PropertyValuesHolder.ofKeyframes(s, typedarray);
        typedarray = obj;
        if(s1 != null)
            typedarray = PropertyValuesHolder.ofKeyframes(s1, valueanimator);
        if(propertyvaluesholder == null)
            objectanimator.setValues(new PropertyValuesHolder[] {
                typedarray
            });
        else
        if(typedarray == null)
            objectanimator.setValues(new PropertyValuesHolder[] {
                propertyvaluesholder
            });
        else
            objectanimator.setValues(new PropertyValuesHolder[] {
                propertyvaluesholder, typedarray
            });
_L4:
        return;
_L2:
        objectanimator.setPropertyName(typedarray.getString(0));
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static void setupValues(ValueAnimator valueanimator, TypedArray typedarray, boolean flag, boolean flag1, int i, boolean flag2, int j)
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        if(flag1)
        {
            float f;
            if(i == 5)
                f = typedarray.getDimension(5, 0.0F);
            else
                f = typedarray.getFloat(5, 0.0F);
            if(flag2)
            {
                float f2;
                if(j == 5)
                    f2 = typedarray.getDimension(6, 0.0F);
                else
                    f2 = typedarray.getFloat(6, 0.0F);
                valueanimator.setFloatValues(new float[] {
                    f, f2
                });
            } else
            {
                valueanimator.setFloatValues(new float[] {
                    f
                });
            }
        } else
        {
            float f1;
            if(j == 5)
                f1 = typedarray.getDimension(6, 0.0F);
            else
                f1 = typedarray.getFloat(6, 0.0F);
            valueanimator.setFloatValues(new float[] {
                f1
            });
        }
_L4:
        return;
_L2:
        if(flag1)
        {
            if(i == 5)
                i = (int)typedarray.getDimension(5, 0.0F);
            else
            if(isColorType(i))
                i = typedarray.getColor(5, 0);
            else
                i = typedarray.getInt(5, 0);
            if(flag2)
            {
                if(j == 5)
                    j = (int)typedarray.getDimension(6, 0.0F);
                else
                if(isColorType(j))
                    j = typedarray.getColor(6, 0);
                else
                    j = typedarray.getInt(6, 0);
                valueanimator.setIntValues(new int[] {
                    i, j
                });
            } else
            {
                valueanimator.setIntValues(new int[] {
                    i
                });
            }
            continue; /* Loop/switch isn't completed */
        }
        if(!flag2)
            continue; /* Loop/switch isn't completed */
        if(j != 5)
            break; /* Loop/switch isn't completed */
        i = (int)typedarray.getDimension(6, 0.0F);
_L5:
        valueanimator.setIntValues(new int[] {
            i
        });
        if(true) goto _L4; else goto _L3
_L3:
        if(isColorType(j))
            i = typedarray.getColor(6, 0);
        else
            i = typedarray.getInt(6, 0);
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    private static final boolean DBG_ANIMATOR_INFLATER = false;
    private static final int SEQUENTIALLY = 1;
    private static final String TAG = "AnimatorInflater";
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_COLOR = 3;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final int VALUE_TYPE_PATH = 2;
    private static final int VALUE_TYPE_UNDEFINED = 4;
    private static final TypedValue sTmpTypedValue = new TypedValue();

}
