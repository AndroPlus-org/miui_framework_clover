// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.*;
import android.os.SystemClock;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import java.lang.ref.WeakReference;
import java.util.*;
import miui.maml.data.DateTimeVariableUpdater;
import miui.maml.data.IndexedVariable;
import miui.maml.data.VariableBinder;
import miui.maml.data.VariableBinderManager;
import miui.maml.data.VariableUpdaterManager;
import miui.maml.data.Variables;
import miui.maml.elements.AnimatedScreenElement;
import miui.maml.elements.ElementGroup;
import miui.maml.elements.ElementGroupRC;
import miui.maml.elements.FramerateController;
import miui.maml.elements.ITicker;
import miui.maml.elements.ScreenElement;
import miui.maml.elements.ScreenElementVisitor;
import miui.maml.util.ConfigFile;
import miui.maml.util.Task;
import miui.maml.util.Utils;
import miui.os.SystemProperties;
import org.w3c.dom.*;

// Referenced classes of package miui.maml:
//            InteractiveListener, ScreenContext, SoundManager, SystemCommandListener, 
//            ResourceManager, RendererController, RenderThread, CommandTriggers, 
//            StylesManager, LanguageHelper, SingleRootListener

public class ScreenElementRoot extends ScreenElement
    implements InteractiveListener
{
    private static class ExtraResource
    {

        static int _2D_wrap0(String s)
        {
            return parseSizeType(s);
        }

        private void inflateMetrics(ArrayList arraylist, String s, MetricsType metricstype)
        {
            String as[];
            int i;
            int j;
            if(TextUtils.isEmpty(s))
                break MISSING_BLOCK_LABEL_140;
            as = s.split(",");
            i = 0;
            j = as.length;
_L2:
            if(i >= j)
                break MISSING_BLOCK_LABEL_140;
            String s1 = as[i];
            ScaleMetrics scalemetrics;
            try
            {
                if(arraylist != mResources)
                    break; /* Loop/switch isn't completed */
                Resource resource = JVM INSTR new #12  <Class ScreenElementRoot$ExtraResource$Resource>;
                resource.this. Resource(s1.trim(), metricstype);
                arraylist.add(resource);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                Log.w("ScreenElementRoot", (new StringBuilder()).append("format error of attribute: ").append(s).toString());
            }
_L4:
            i++;
            if(true) goto _L2; else goto _L1
_L1:
            if(arraylist != mScales) goto _L4; else goto _L3
_L3:
            scalemetrics = JVM INSTR new #15  <Class ScreenElementRoot$ExtraResource$ScaleMetrics>;
            scalemetrics.this. ScaleMetrics(s1.trim(), metricstype);
            arraylist.add(scalemetrics);
              goto _L4
        }

        private static int parseSizeType(String s)
        {
            int i = 0;
            if(!"small".equals(s)) goto _L2; else goto _L1
_L1:
            i = 1;
_L4:
            return i;
_L2:
            if("normal".equals(s))
                i = 2;
            else
            if("large".equals(s))
                i = 3;
            else
            if("xlarge".equals(s))
                i = 4;
            if(true) goto _L4; else goto _L3
_L3:
        }

        ScaleMetrics findMetrics(int i, int j, int k, ArrayList arraylist)
        {
            Object obj = null;
            int l = 0x7fffffff;
            int i1 = 0x7fffffff;
            Object obj2 = new ArrayList();
            arraylist = arraylist.iterator();
            do
            {
                if(!arraylist.hasNext())
                    break;
                ScaleMetrics scalemetrics = (ScaleMetrics)arraylist.next();
                if(scalemetrics.mSizeType == 0 || scalemetrics.mSizeType == k)
                {
                    int j1 = Math.abs(i - scalemetrics.mDensity);
                    if(j1 < l)
                    {
                        l = j1;
                        i1 = Math.abs(j - scalemetrics.mScreenWidth);
                        ((ArrayList) (obj2)).clear();
                        ((ArrayList) (obj2)).add(scalemetrics);
                    } else
                    if(j1 == l)
                    {
                        int k1 = Math.abs(j - scalemetrics.mScreenWidth);
                        if(k1 < i1)
                        {
                            i1 = k1;
                            ((ArrayList) (obj2)).clear();
                            ((ArrayList) (obj2)).add(scalemetrics);
                        } else
                        if(k1 == i1)
                            ((ArrayList) (obj2)).add(scalemetrics);
                    }
                }
            } while(true);
            obj2 = ((Iterable) (obj2)).iterator();
            arraylist = obj;
            do
            {
                Object obj1;
label0:
                {
                    obj1 = arraylist;
                    if(((Iterator) (obj2)).hasNext())
                    {
                        obj1 = (ScaleMetrics)((Iterator) (obj2)).next();
                        if(((ScaleMetrics) (obj1)).mSizeType != k)
                            break label0;
                    }
                    return ((ScaleMetrics) (obj1));
                }
                if(((ScaleMetrics) (obj1)).mSizeType == 0)
                    arraylist = ((ArrayList) (obj1));
            } while(true);
        }

        public Resource findResource(int i, int j, int k)
        {
            return (Resource)findMetrics(i, j, k, mResources);
        }

        public ScaleMetrics findScale(int i, int j, int k)
        {
            return findMetrics(i, j, k, mScales);
        }

        private ArrayList mResources;
        private ArrayList mScales;

        public ExtraResource(Element element, int i)
        {
            mResources = new ArrayList();
            mScales = new ArrayList();
            Object obj = new Resource();
            obj.mDensity = i;
            obj.mScreenWidth = (ResourceManager.translateDensity(i) * 480) / 240;
            obj.mSizeType = 0;
            obj.mPath = null;
            obj.mScale = 1.0F;
            mResources.add(obj);
            inflateMetrics(mResources, element.getAttribute("extraResourcesDensity"), MetricsType.DEN);
            inflateMetrics(mResources, element.getAttribute("extraResourcesScreenWidth"), MetricsType.SW);
            inflateMetrics(mResources, element.getAttribute("extraResources"), MetricsType.SW_DEN);
            obj = new ScaleMetrics();
            obj.mDensity = i;
            obj.mScreenWidth = (ResourceManager.translateDensity(i) * 480) / 240;
            obj.mSizeType = 0;
            obj.mScale = -1F;
            mScales.add(obj);
            inflateMetrics(mScales, element.getAttribute("extraScaleByDensity"), MetricsType.DEN);
            inflateMetrics(mScales, element.getAttribute("extraScaleByScreenWidth"), MetricsType.SW);
            inflateMetrics(mScales, element.getAttribute("extraScales"), MetricsType.SW_DEN);
        }
    }

    static final class ExtraResource.MetricsType extends Enum
    {

        public static ExtraResource.MetricsType valueOf(String s)
        {
            return (ExtraResource.MetricsType)Enum.valueOf(miui/maml/ScreenElementRoot$ExtraResource$MetricsType, s);
        }

        public static ExtraResource.MetricsType[] values()
        {
            return $VALUES;
        }

        private static final ExtraResource.MetricsType $VALUES[];
        public static final ExtraResource.MetricsType DEN;
        public static final ExtraResource.MetricsType SW;
        public static final ExtraResource.MetricsType SW_DEN;

        static 
        {
            DEN = new ExtraResource.MetricsType("DEN", 0);
            SW = new ExtraResource.MetricsType("SW", 1);
            SW_DEN = new ExtraResource.MetricsType("SW_DEN", 2);
            $VALUES = (new ExtraResource.MetricsType[] {
                DEN, SW, SW_DEN
            });
        }

        private ExtraResource.MetricsType(String s, int i)
        {
            super(s, i);
        }
    }

    class ExtraResource.Resource extends ExtraResource.ScaleMetrics
    {

        private static int[] _2D_getmiui_2D_maml_2D_ScreenElementRoot$ExtraResource$MetricsTypeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_ScreenElementRoot$ExtraResource$MetricsTypeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_ScreenElementRoot$ExtraResource$MetricsTypeSwitchesValues;
            int ai[] = new int[ExtraResource.MetricsType.values().length];
            try
            {
                ai[ExtraResource.MetricsType.DEN.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[ExtraResource.MetricsType.SW.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[ExtraResource.MetricsType.SW_DEN.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_ScreenElementRoot$ExtraResource$MetricsTypeSwitchesValues = ai;
            return ai;
        }

        protected void onParseInfo(String as[])
        {
            int i;
            if(as.length <= 2)
                i = 0;
            else
                i = 1;
            mPath = as[i];
        }

        public String toString()
        {
            return (new StringBuilder()).append(super.toString()).append(" path:").append(mPath).toString();
        }

        private static final int _2D_miui_2D_maml_2D_ScreenElementRoot$ExtraResource$MetricsTypeSwitchesValues[];
        final int $SWITCH_TABLE$miui$maml$ScreenElementRoot$ExtraResource$MetricsType[];
        String mPath;
        final ExtraResource this$1;

        public ExtraResource.Resource()
        {
            this$1 = ExtraResource.this;
            super();
        }

        public ExtraResource.Resource(String s, ExtraResource.MetricsType metricstype)
        {
            this$1 = ExtraResource.this;
            super(s, metricstype);
            _2D_getmiui_2D_maml_2D_ScreenElementRoot$ExtraResource$MetricsTypeSwitchesValues()[metricstype.ordinal()];
            JVM INSTR tableswitch 1 2: default 44
        //                       1 45
        //                       2 74;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            mPath = (new StringBuilder()).append("den").append(mDensity).toString();
            continue; /* Loop/switch isn't completed */
_L3:
            mPath = (new StringBuilder()).append("sw").append(mScreenWidth).toString();
            if(true) goto _L1; else goto _L4
_L4:
        }
    }

    class ExtraResource.ScaleMetrics
    {

        private static int[] _2D_getmiui_2D_maml_2D_ScreenElementRoot$ExtraResource$MetricsTypeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_ScreenElementRoot$ExtraResource$MetricsTypeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_ScreenElementRoot$ExtraResource$MetricsTypeSwitchesValues;
            int ai[] = new int[ExtraResource.MetricsType.values().length];
            try
            {
                ai[ExtraResource.MetricsType.DEN.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[ExtraResource.MetricsType.SW.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[ExtraResource.MetricsType.SW_DEN.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_ScreenElementRoot$ExtraResource$MetricsTypeSwitchesValues = ai;
            return ai;
        }

        protected void onParseInfo(String as[])
        {
        }

        public String toString()
        {
            return (new StringBuilder()).append("ScaleMetrics sw:").append(mScreenWidth).append(" den:").append(mDensity).append(" sizeType:").append(mSizeType).append(" scale:").append(mScale).toString();
        }

        private static final int _2D_miui_2D_maml_2D_ScreenElementRoot$ExtraResource$MetricsTypeSwitchesValues[];
        final int $SWITCH_TABLE$miui$maml$ScreenElementRoot$ExtraResource$MetricsType[];
        int mDensity;
        float mScale;
        int mScreenWidth;
        int mSizeType;
        final ExtraResource this$1;

        public ExtraResource.ScaleMetrics()
        {
            this$1 = ExtraResource.this;
            super();
            mScale = 1.0F;
        }

        public ExtraResource.ScaleMetrics(String s, ExtraResource.MetricsType metricstype)
        {
            int i;
            i = 1;
            this$1 = ExtraResource.this;
            super();
            mScale = 1.0F;
            extraresource = s.split(":");
            _2D_getmiui_2D_maml_2D_ScreenElementRoot$ExtraResource$MetricsTypeSwitchesValues()[metricstype.ordinal()];
            JVM INSTR tableswitch 1 3: default 60
        //                       1 61
        //                       2 145
        //                       3 193;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            mDensity = Integer.parseInt(ExtraResource.this[0]);
            mScreenWidth = (ResourceManager.translateDensity(mDensity) * 480) / 240;
            if(ExtraResource.this.length > 1)
                mScale = Float.parseFloat(ExtraResource.this[1]);
              goto _L1
_L3:
            mScreenWidth = Integer.parseInt(ExtraResource.this[0]);
            mDensity = ResourceManager.retranslateDensity((mScreenWidth * 240) / 480);
            if(ExtraResource.this.length > 1)
                mScale = Float.parseFloat(ExtraResource.this[1]);
              goto _L1
_L4:
            metricstype = ExtraResource.this[0].split("-");
            mSizeType = 0;
            if(metricstype.length != 1)
                break MISSING_BLOCK_LABEL_366;
            if(!metricstype[0].startsWith("sw")) goto _L6; else goto _L5
_L5:
            mScreenWidth = Integer.parseInt(metricstype[0].substring(2));
            mDensity = ResourceManager.retranslateDensity((mScreenWidth * 240) / 480);
_L9:
            if(ExtraResource.this.length <= 1) goto _L8; else goto _L7
_L7:
            if(ExtraResource.this.length != 2)
                i = 2;
            mScale = Float.parseFloat(ExtraResource.this[i]);
_L8:
            onParseInfo(ExtraResource.this);
              goto _L1
_L6:
label0:
            {
                if(!metricstype[0].startsWith("den"))
                    break label0;
                mDensity = Integer.parseInt(metricstype[0].substring(3));
                mScreenWidth = (ResourceManager.translateDensity(mDensity) * 480) / 240;
            }
              goto _L9
            extraresource = JVM INSTR new #115 <Class IllegalArgumentException>;
            metricstype = JVM INSTR new #96  <Class StringBuilder>;
            metricstype.StringBuilder();
            super(metricstype.append("invalid format: ").append(s).toString());
            throw ExtraResource.this;
            if(metricstype.length < 2)
                break MISSING_BLOCK_LABEL_419;
            mScreenWidth = Integer.parseInt(metricstype[0].substring(2));
            mDensity = Integer.parseInt(metricstype[1].substring(3));
            if(metricstype.length == 3)
                mSizeType = ExtraResource._2D_wrap0(metricstype[2]);
              goto _L9
            try
            {
                metricstype = JVM INSTR new #115 <Class IllegalArgumentException>;
                extraresource = JVM INSTR new #96  <Class StringBuilder>;
                super();
                metricstype.IllegalArgumentException(append("invalid format: ").append(s).toString());
                throw metricstype;
            }
            // Misplaced declaration of an exception variable
            catch(ExtraResource extraresource)
            {
                Log.w("ScreenElementRoot", (new StringBuilder()).append("format error of string: ").append(s).toString());
                throw new IllegalArgumentException("invalid format");
            }
              goto _L1
        }
    }

    private static class FramerateHelper
    {

        public void draw(Canvas canvas)
        {
            if(mFramerateText == null || mShowingFramerate != mRealFrameRate)
            {
                mShowingFramerate = mRealFrameRate;
                mFramerateText = String.format("FPS %d", new Object[] {
                    Integer.valueOf(mShowingFramerate)
                });
            }
            canvas.drawText(mFramerateText, mTextX, mTextY, mPaint);
        }

        public void set(int i)
        {
            mRealFrameRate = i;
        }

        private String mFramerateText;
        private TextPaint mPaint;
        private int mRealFrameRate;
        private int mShowingFramerate;
        private int mTextX;
        private int mTextY;

        public FramerateHelper()
        {
            this(0xffff0000, 14, 10, 10);
        }

        public FramerateHelper(int i, int j, int k, int l)
        {
            mPaint = new TextPaint();
            mPaint.setColor(i);
            mPaint.setTextSize(j);
            mTextX = k;
            mTextY = l;
        }
    }

    private static class InnerGroup extends ElementGroup
    {

        public final RendererController getRendererController()
        {
            return mRoot.getRendererController();
        }

        public InnerGroup(Element element, ScreenElementRoot screenelementroot)
        {
            super(element, screenelementroot);
        }
    }

    public static interface OnExternCommandListener
    {

        public abstract void onCommand(String s, Double double1, String s1);
    }

    public static interface OnHoverChangeListener
    {

        public abstract void onHoverChange(String s);
    }


    static CommandTriggers _2D_get0(ScreenElementRoot screenelementroot)
    {
        return screenelementroot.mExternalCommandManager;
    }

    static ArrayList _2D_get1(ScreenElementRoot screenelementroot)
    {
        return screenelementroot.mRendererControllers;
    }

    public ScreenElementRoot(ScreenContext screencontext)
    {
        super(null, null);
        DEFAULT_FRAME_RATE = 30F;
        mPreTickers = new ArrayList();
        mRawAttrs = new HashMap();
        mHoverMatrix = new Matrix();
        mFramerateHelper = new FramerateHelper();
        mRendererControllers = new ArrayList();
        mCapability = -1;
        mAccessibleElements = new ArrayList();
        mRoot = this;
        mContext = screencontext;
        mVariableUpdaterManager = new VariableUpdaterManager(this);
        mTouchX = new IndexedVariable("touch_x", getContext().mVariables, true);
        mTouchY = new IndexedVariable("touch_y", getContext().mVariables, true);
        mTouchBeginX = new IndexedVariable("touch_begin_x", getContext().mVariables, true);
        mTouchBeginY = new IndexedVariable("touch_begin_y", getContext().mVariables, true);
        mTouchBeginTime = new IndexedVariable("touch_begin_time", getContext().mVariables, true);
        mNeedDisallowInterceptTouchEventVar = new IndexedVariable("intercept_sys_touch", getContext().mVariables, true);
        mSoundManager = new SoundManager(mContext);
        mSystemExternCommandListener = new SystemCommandListener(this);
    }

    private void loadConfig(String s)
    {
        if(s == null)
            return;
        mConfig = new ConfigFile();
        if(!mConfig.load(s))
        {
            s = mContext.mResourceManager.getConfigRoot();
            mConfig.loadDefaultSettings(s);
        }
        s = mConfig.getVariables().iterator();
        do
        {
            if(!s.hasNext())
                break;
            miui.maml.util.ConfigFile.Variable variable = (miui.maml.util.ConfigFile.Variable)s.next();
            if(TextUtils.equals(variable.type, "string"))
                Utils.putVariableString(variable.name, mContext.mVariables, variable.value);
            else
            if(TextUtils.equals(variable.type, "number"))
                try
                {
                    double d = Double.parseDouble(variable.value);
                    Utils.putVariableNumber(variable.name, mContext.mVariables, d);
                }
                catch(NumberFormatException numberformatexception) { }
        } while(true);
        for(Iterator iterator = mConfig.getTasks().iterator(); iterator.hasNext(); mContext.mVariables.put((new StringBuilder()).append(((Task) (s)).id).append(".class").toString(), ((Task) (s)).className))
        {
            s = (Task)iterator.next();
            mContext.mVariables.put((new StringBuilder()).append(((Task) (s)).id).append(".name").toString(), ((Task) (s)).name);
            mContext.mVariables.put((new StringBuilder()).append(((Task) (s)).id).append(".package").toString(), ((Task) (s)).packageName);
        }

    }

    private void loadRawAttrs(Element element)
    {
        element = element.getAttributes();
        for(int i = 0; i < element.getLength(); i++)
        {
            Node node = element.item(i);
            mRawAttrs.put(node.getNodeName(), node.getNodeValue());
        }

    }

    private void processUseVariableUpdater(Element element)
    {
        element = element.getAttribute("useVariableUpdater");
        if(TextUtils.isEmpty(element))
            onAddVariableUpdater(mVariableUpdaterManager);
        else
            mVariableUpdaterManager.addFromTag(element);
    }

    private void setupScale(Element element)
    {
        Object obj = element.getAttribute("scaleByDensity");
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
            mScaleByDensity = Boolean.parseBoolean(((String) (obj)));
        mDefaultScreenWidth = Utils.getAttrAsInt(element, "defaultScreenWidth", 0);
        if(mDefaultScreenWidth == 0)
            mDefaultScreenWidth = Utils.getAttrAsInt(element, "screenWidth", 0);
        mRawDefaultResourceDensity = Utils.getAttrAsInt(element, "defaultResourceDensity", 0);
        if(mRawDefaultResourceDensity == 0)
            mRawDefaultResourceDensity = Utils.getAttrAsInt(element, "resDensity", 0);
        mDefaultResourceDensity = ResourceManager.translateDensity(mRawDefaultResourceDensity);
        Object obj1;
        int i;
        int j;
        int k;
        if(mDefaultScreenWidth == 0 && mDefaultResourceDensity == 0)
        {
            mDefaultScreenWidth = 480;
            mDefaultResourceDensity = 240;
        } else
        if(mDefaultResourceDensity == 0)
            mDefaultResourceDensity = (mDefaultScreenWidth * 240) / 480;
        else
        if(mDefaultScreenWidth == 0)
            mDefaultScreenWidth = (mDefaultResourceDensity * 480) / 240;
        mContext.mResourceManager.setDefaultResourceDensity(mDefaultResourceDensity);
        obj = ((WindowManager)mContext.mContext.getSystemService("window")).getDefaultDisplay();
        obj1 = new Point();
        ((Display) (obj)).getRealSize(((Point) (obj1)));
        i = ((Display) (obj)).getRotation();
        if(i == 1 || i == 3)
            i = 1;
        else
            i = 0;
        if(i != 0)
            j = ((Point) (obj1)).y;
        else
            j = ((Point) (obj1)).x;
        mTargetScreenWidth = j;
        if(i != 0)
            i = ((Point) (obj1)).x;
        else
            i = ((Point) (obj1)).y;
        mTargetScreenHeight = i;
        obj1 = new DisplayMetrics();
        ((Display) (obj)).getMetrics(((DisplayMetrics) (obj1)));
        mRawTargetDensity = ((DisplayMetrics) (obj1)).densityDpi;
        k = mContext.mContext.getResources().getConfiguration().screenLayout & 0xf;
        j = mRawDefaultResourceDensity;
        i = j;
        if(j == 0)
            i = (mDefaultScreenWidth * 240) / 480;
        obj = new ExtraResource(element, i);
        obj1 = ((ExtraResource) (obj)).findResource(mRawTargetDensity, mTargetScreenWidth, k);
        Log.d("ScreenElementRoot", (new StringBuilder()).append("findResource: ").append(((ExtraResource.Resource) (obj1)).toString()).toString());
        i = (int)((float)ResourceManager.translateDensity(((ExtraResource.Resource) (obj1)).mDensity) / ((ExtraResource.Resource) (obj1)).mScale);
        mContext.mResourceManager.setExtraResource(((ExtraResource.Resource) (obj1)).mPath, i);
        obj = ((ExtraResource) (obj)).findScale(mRawTargetDensity, mTargetScreenWidth, k);
        Log.d("ScreenElementRoot", (new StringBuilder()).append("findScale: ").append(((ExtraResource.ScaleMetrics) (obj)).toString()).toString());
        if(mScaleByDensity)
        {
            mTargetDensity = ResourceManager.translateDensity(mRawTargetDensity);
            if(((ExtraResource.ScaleMetrics) (obj)).mScale <= 0.0F)
            {
                mScale = (float)mTargetDensity / (float)mDefaultResourceDensity;
            } else
            {
                float f = ((float)mRawTargetDensity * 1.0F) / (float)((ExtraResource.ScaleMetrics) (obj)).mDensity;
                mScale = ((ExtraResource.ScaleMetrics) (obj)).mScale * f;
            }
        } else
        {
            mScale = (float)mTargetScreenWidth / (float)mDefaultScreenWidth;
            mTargetDensity = (int)((float)mDefaultResourceDensity * mScale);
            if(((ExtraResource.ScaleMetrics) (obj)).mScale > 0.0F)
            {
                float f1 = ((float)mTargetScreenWidth * 1.0F) / (float)((ExtraResource.ScaleMetrics) (obj)).mScreenWidth;
                mScale = ((ExtraResource.ScaleMetrics) (obj)).mScale * f1;
            }
        }
        Log.i("ScreenElementRoot", (new StringBuilder()).append("set scale: ").append(mScale).toString());
        mContext.mResourceManager.setTargetDensity(mTargetDensity);
        mRawWidth = Utils.getAttrAsInt(element, "width", 0);
        mRawHeight = Utils.getAttrAsInt(element, "height", 0);
        mWidth = Math.round((float)mRawWidth * mScale);
        mHeight = Math.round((float)mRawHeight * mScale);
    }

    private void traverseElements()
    {
        mRendererControllers.clear();
        acceptVisitor(new ScreenElementVisitor() {

            public void visit(ScreenElement screenelement)
            {
                if(screenelement instanceof FramerateController)
                {
                    RendererController renderercontroller = screenelement.getRendererController();
                    if(renderercontroller != null)
                        renderercontroller.addFramerateController((FramerateController)screenelement);
                }
                if((screenelement instanceof ElementGroupRC) || (screenelement instanceof ScreenElementRoot))
                    ScreenElementRoot._2D_get1(ScreenElementRoot.this).add(screenelement.getRendererController());
            }

            final ScreenElementRoot this$0;

            
            {
                this$0 = ScreenElementRoot.this;
                super();
            }
        }
);
    }

    public void acceptVisitor(ScreenElementVisitor screenelementvisitor)
    {
        super.acceptVisitor(screenelementvisitor);
        mInnerGroup.acceptVisitor(screenelementvisitor);
    }

    public void addAccessibleElements(AnimatedScreenElement animatedscreenelement)
    {
        mAccessibleElements.add(animatedscreenelement);
    }

    public void addPreTicker(ITicker iticker)
    {
        mPreTickers.add(iticker);
    }

    public boolean allowScreenRotation()
    {
        return mAllowScreenRotation;
    }

    public void attachToRenderThread(RenderThread renderthread)
    {
        if(renderthread == null || mController == null)
            throw new NullPointerException("thread or controller is null, MUST load before attaching");
        int i = mRendererControllers.size();
        for(int j = 0; j < i; j++)
            renderthread.addRendererController((RendererController)mRendererControllers.get(j));

    }

    public FramerateTokenList.FramerateToken createFramerateToken(String s)
    {
        return createToken(s);
    }

    public void detachFromRenderThread(RenderThread renderthread)
    {
        if(renderthread == null || mController == null)
            throw new NullPointerException("thread or controller is null, MUST load before detaching");
        int i = mRendererControllers.size();
        for(int j = 0; j < i; j++)
            renderthread.removeRendererController((RendererController)mRendererControllers.get(j));

    }

    protected void doRender(Canvas canvas)
    {
        if(mFinished)
            return;
        if(mClearCanvas)
            canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        if(mBgColor != 0)
            canvas.drawColor(mBgColor);
        try
        {
            mInnerGroup.render(canvas);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        catch(OutOfMemoryError outofmemoryerror)
        {
            outofmemoryerror.printStackTrace();
            Log.e("ScreenElementRoot", outofmemoryerror.toString());
        }
        if(mShowFramerate)
            mFramerateHelper.draw(canvas);
        mFrames = mFrames + 1;
        mController.doneRender();
    }

    protected void doTick(long l)
    {
        if(mFinished)
            return;
        if(mVariableBinderManager != null)
            mVariableBinderManager.tick();
        mVariableUpdaterManager.tick(l);
        int i = mPreTickers.size();
        for(int j = 0; j < i; j++)
            ((ITicker)mPreTickers.get(j)).tick(l);

        mInnerGroup.tick(l);
        boolean flag;
        if(mNeedDisallowInterceptTouchEventVar.getDouble() > 0.0D)
            flag = true;
        else
            flag = false;
        mNeedDisallowInterceptTouchEvent = flag;
        if(mFrameRateVar == null)
        {
            mFrameRateVar = new IndexedVariable("frame_rate", mContext.mVariables, true);
            mCheckPoint = 0L;
        }
        if(mCheckPoint != 0L) goto _L2; else goto _L1
_L1:
        mCheckPoint = l;
_L4:
        return;
_L2:
        long l1 = l - mCheckPoint;
        if(l1 >= 1000L)
        {
            int k = (int)((long)(mFrames * 1000) / l1);
            mFramerateHelper.set(k);
            mFrameRateVar.set(k);
            mFrames = 0;
            mCheckPoint = l;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void doneRender()
    {
        mController.doneRender();
    }

    public VariableBinder findBinder(String s)
    {
        VariableBinder variablebinder = null;
        if(mVariableBinderManager != null)
            variablebinder = mVariableBinderManager.findBinder(s);
        return variablebinder;
    }

    public ScreenElement findElement(String s)
    {
        if("__root".equals(s))
            return this;
        else
            return mInnerGroup.findElement(s);
    }

    public Task findTask(String s)
    {
        Object obj = null;
        if(mConfig == null)
            s = obj;
        else
            s = mConfig.getTask(s);
        return s;
    }

    public void finish()
    {
        if(mFinished)
            return;
        super.finish();
        Log.d("ScreenElementRoot", "finish");
        mInnerGroup.performAction("preFinish");
        mInnerGroup.finish();
        mInnerGroup.performAction("finish");
        if(mConfig != null)
            mConfig.save(mContext.mContext.getApplicationContext());
        if(mVariableBinderManager != null)
            mVariableBinderManager.finish();
        if(mExternalCommandManager != null)
            mExternalCommandManager.finish();
        if(mVariableUpdaterManager != null)
            mVariableUpdaterManager.finish();
        mSoundManager.release();
        mContext.mResourceManager.finish(mKeepResource);
        mFinished = true;
        mKeepResource = false;
    }

    public List getAccessibleElements()
    {
        return mAccessibleElements;
    }

    public String getCacheDir()
    {
        return mCacheDir;
    }

    public boolean getCapability(int i)
    {
        boolean flag = false;
        if((mCapability & i) != 0)
            flag = true;
        return flag;
    }

    public ScreenContext getContext()
    {
        return mContext;
    }

    public int getDefaultScreenWidth()
    {
        return mDefaultScreenWidth;
    }

    public final float getFontScale()
    {
        return mFontScale;
    }

    public float getHeight()
    {
        return mHeight;
    }

    public AnimatedScreenElement getHoverElement()
    {
        return mHoverElement;
    }

    public String getRawAttr(String s)
    {
        return (String)mRawAttrs.get(s);
    }

    public RendererController getRendererController()
    {
        return mController;
    }

    public int getResourceDensity()
    {
        return mDefaultResourceDensity;
    }

    public String getRootTag()
    {
        return mRootTag;
    }

    public final float getScale()
    {
        if(mScale == 0.0F)
        {
            Log.w("ScreenElementRoot", "scale not initialized!");
            return 1.0F;
        } else
        {
            return mScale;
        }
    }

    public int getScreenHeight()
    {
        return mTargetScreenHeight;
    }

    public int getScreenWidth()
    {
        return mTargetScreenWidth;
    }

    public StylesManager.Style getStyle(String s)
    {
        StylesManager.Style style = null;
        if(TextUtils.isEmpty(s))
            return null;
        if(mStylesManager != null)
            style = mStylesManager.getStyle(s);
        return style;
    }

    public int getTargetDensity()
    {
        return mTargetDensity;
    }

    public ViewManager getViewManager()
    {
        return mViewManager;
    }

    public float getWidth()
    {
        return mWidth;
    }

    public void haptic(int i)
    {
    }

    public void init()
    {
        Variables variables;
        boolean flag;
        int i;
        variables = mContext.mVariables;
        variables.put("__objRoot", this);
        variables.put("__objContext", mContext);
        super.init();
        Log.d("ScreenElementRoot", "init");
        requestFramerate(mFrameRate);
        mCapability = -1;
        mShowDebugLayout = SystemProperties.getBoolean("debug.layout", false);
        mFinished = false;
        mContext.mResourceManager.init();
        mFontScale = getContext().mContext.getResources().getConfiguration().fontScale;
        variables.put("__fontScale", mFontScale);
        LanguageHelper.load(mContext.mContext.getResources().getConfiguration().locale, mContext.mResourceManager, mContext.mVariables);
        variables.put("raw_screen_width", mTargetScreenWidth);
        variables.put("raw_screen_height", mTargetScreenHeight);
        variables.put("screen_width", (float)mTargetScreenWidth / mScale);
        variables.put("screen_height", (float)mTargetScreenHeight / mScale);
        if(mRawWidth > 0)
            variables.put("view_width", mRawWidth);
        if(mRawHeight > 0)
            variables.put("view_height", mRawHeight);
        variables.put("view_width", (float)mTargetScreenWidth / mScale);
        variables.put("view_height", (float)mTargetScreenHeight / mScale);
        variables.put("__raw_density", mRawTargetDensity);
        variables.put("__scale_factor", mScale);
        variables.put("__maml_version", 4D);
        flag = false;
        i = ((flag) ? 1 : 0);
        if(mContext == null)
            break MISSING_BLOCK_LABEL_371;
        i = ((flag) ? 1 : 0);
        Object obj;
        if(mContext.mContext == null)
            break MISSING_BLOCK_LABEL_371;
        obj = mContext.mContext.getPackageManager();
        i = ((flag) ? 1 : 0);
        if(obj == null)
            break MISSING_BLOCK_LABEL_371;
        obj = ((PackageManager) (obj)).getPackageInfo("com.android.thememanager", 0);
        i = ((flag) ? 1 : 0);
        if(obj != null)
            try
            {
                i = ((PackageInfo) (obj)).versionCode;
            }
            catch(Exception exception)
            {
                Log.e("ScreenElementRoot", "thememanager not found");
                i = ((flag) ? 1 : 0);
            }
        variables.put("__thememanager_version", i);
        variables.put("__miui_version_name", SystemProperties.get("ro.miui.ui.version.name"));
        variables.put("__miui_version_code", SystemProperties.get("ro.miui.ui.version.code"));
        variables.put("__android_version", android.os.Build.VERSION.RELEASE);
        variables.put("__system_version", android.os.Build.VERSION.INCREMENTAL);
        loadConfig();
        if(mVariableUpdaterManager != null)
            mVariableUpdaterManager.init();
        if(mVariableBinderManager != null)
            mVariableBinderManager.init();
        if(mExternalCommandManager != null)
            mExternalCommandManager.init();
        mInnerGroup.performAction("init");
        mInnerGroup.init();
        mInnerGroup.performAction("postInit");
        mRoot.mHoverElement = null;
        mNeedReset = true;
        requestUpdate();
        return;
    }

    public void issueExternCommand(String s, Double double1, String s1)
    {
        mSystemExternCommandListener.onCommand(s, double1, s1);
        if(mExternCommandListener != null)
        {
            OnExternCommandListener onexterncommandlistener = (OnExternCommandListener)mExternCommandListener.get();
            if(onexterncommandlistener != null)
            {
                onexterncommandlistener.onCommand(s, double1, s1);
                Log.d("ScreenElementRoot", (new StringBuilder()).append("issueExternCommand: ").append(s).append(" ").append(double1).append(" ").append(s1).toString());
            }
        }
    }

    public final boolean load()
    {
        long l;
        Object obj;
        Object obj1;
        Object obj2;
        try
        {
            l = SystemClock.elapsedRealtime();
            obj = mContext.mResourceManager.getManifestRoot();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
        if(obj == null)
            return false;
        mRootTag = ((Element) (obj)).getNodeName();
        loadRawAttrs(((Element) (obj)));
        processUseVariableUpdater(((Element) (obj)));
        setupScale(((Element) (obj)));
        obj1 = Utils.getChild(((Element) (obj)), "VariableBinders");
        obj2 = JVM INSTR new #734 <Class VariableBinderManager>;
        ((VariableBinderManager) (obj2)).VariableBinderManager(((Element) (obj1)), this);
        mVariableBinderManager = ((VariableBinderManager) (obj2));
        obj2 = Utils.getChild(((Element) (obj)), "ExternalCommands");
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_106;
        obj1 = JVM INSTR new #801 <Class CommandTriggers>;
        ((CommandTriggers) (obj1)).CommandTriggers(((Element) (obj2)), this);
        mExternalCommandManager = ((CommandTriggers) (obj1));
        obj1 = Utils.getChild(((Element) (obj)), "Styles");
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_138;
        obj2 = JVM INSTR new #854 <Class StylesManager>;
        ((StylesManager) (obj2)).StylesManager(((Element) (obj1)));
        mStylesManager = ((StylesManager) (obj2));
        mFrameRate = Utils.getAttrAsFloat(((Element) (obj)), "frameRate", DEFAULT_FRAME_RATE);
        mClearCanvas = Boolean.parseBoolean(((Element) (obj)).getAttribute("clearCanvas"));
        mAllowScreenRotation = Boolean.parseBoolean(((Element) (obj)).getAttribute("allowScreenRotation"));
        obj2 = JVM INSTR new #664 <Class RendererController>;
        ((RendererController) (obj2)).RendererController();
        mController = ((RendererController) (obj2));
        obj2 = JVM INSTR new #27  <Class ScreenElementRoot$InnerGroup>;
        ((InnerGroup) (obj2)).InnerGroup(((Element) (obj)), this);
        mInnerGroup = ((ElementGroup) (obj2));
        if(mInnerGroup.getElements().size() > 0)
            break MISSING_BLOCK_LABEL_243;
        Log.e("ScreenElementRoot", "load error, no element loaded");
        return false;
        mVersion = Utils.getAttrAsInt(((Element) (obj)), "version", 1);
        if(!onLoad(((Element) (obj))))
            return false;
        traverseElements();
        obj = JVM INSTR new #365 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.d("ScreenElementRoot", ((StringBuilder) (obj)).append("load finished, spent ").append(SystemClock.elapsedRealtime() - l).append(" ms").toString());
        return true;
    }

    public void loadConfig()
    {
        loadConfig(mConfigPath);
    }

    public boolean needDisallowInterceptTouchEvent()
    {
        return mNeedDisallowInterceptTouchEvent;
    }

    protected void onAddVariableUpdater(VariableUpdaterManager variableupdatermanager)
    {
        variableupdatermanager.add(new DateTimeVariableUpdater(variableupdatermanager));
    }

    public void onCommand(final String command)
    {
        if(mExternalCommandManager != null)
            postRunnable(new Runnable() {

                public void run()
                {
                    ScreenElementRoot._2D_get0(ScreenElementRoot.this).onAction(command);
_L1:
                    return;
                    Exception exception;
                    exception;
                    Log.e("ScreenElementRoot", exception.toString());
                    exception.printStackTrace();
                      goto _L1
                }

                final ScreenElementRoot this$0;
                final String val$command;

            
            {
                this$0 = ScreenElementRoot.this;
                command = s;
                super();
            }
            }
);
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        if(mAllowScreenRotation)
        {
            setConfiguration(configuration);
            onCommand("orientationChange");
            requestUpdate();
        }
    }

    public boolean onHover(MotionEvent motionevent)
    {
        boolean flag;
        if(mFinished)
            flag = false;
        else
            flag = mInnerGroup.onHover(motionevent);
        return flag;
    }

    public void onHoverChange(AnimatedScreenElement animatedscreenelement, String s)
    {
        mHoverElement = animatedscreenelement;
        if(mHoverChangeListener != null)
            mHoverChangeListener.onHoverChange(s);
    }

    protected boolean onLoad(Element element)
    {
        return true;
    }

    public boolean onTouch(MotionEvent motionevent)
    {
        double d;
        double d1;
        if(mFinished)
            return false;
        if(mHoverElement != null)
        {
            float f = mHoverElement.getWidth();
            float f1 = mHoverElement.getHeight();
            float f2 = mHoverElement.getAbsoluteLeft();
            f /= 2.0F;
            float f3 = mHoverElement.getAbsoluteTop();
            f1 /= 2.0F;
            mHoverMatrix.setTranslate((f2 + f) - motionevent.getX(), (f3 + f1) - motionevent.getY());
            motionevent.transform(mHoverMatrix);
            mHoverElement.onTouch(motionevent);
            if(motionevent.getActionMasked() == 1 || motionevent.getActionMasked() == 3)
                mHoverElement = null;
            return true;
        }
        d = descale(motionevent.getX());
        d1 = descale(motionevent.getY());
        mTouchX.set(d);
        mTouchY.set(d1);
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 2: default 192
    //                   0 217
    //                   1 254
    //                   2 192;
           goto _L1 _L2 _L3 _L1
_L1:
        boolean flag = mInnerGroup.onTouch(motionevent);
        if(!flag)
            mController.requestUpdate();
        return flag;
_L2:
        mTouchBeginX.set(d);
        mTouchBeginY.set(d1);
        mTouchBeginTime.set(System.currentTimeMillis());
        mNeedDisallowInterceptTouchEvent = false;
        continue; /* Loop/switch isn't completed */
_L3:
        mNeedDisallowInterceptTouchEvent = false;
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onUIInteractive(ScreenElement screenelement, String s)
    {
    }

    public void pause()
    {
        super.pause();
        Log.d("ScreenElementRoot", "pause");
        mInnerGroup.performAction("pause");
        mInnerGroup.pause();
        mSoundManager.pause();
        if(mVariableBinderManager != null)
            mVariableBinderManager.pause();
        if(mExternalCommandManager != null)
            mExternalCommandManager.pause();
        if(mVariableUpdaterManager != null)
            mVariableUpdaterManager.pause();
        mContext.mResourceManager.pause();
        onHoverChange(null, null);
        if(mConfig != null)
            mConfig.save(mContext.mContext.getApplicationContext());
    }

    protected void pauseAnim(long l)
    {
        super.pauseAnim(l);
        mInnerGroup.pauseAnim(l);
    }

    protected void playAnim(long l, long l1, long l2, boolean flag, 
            boolean flag1)
    {
        super.playAnim(l, l1, l2, flag, flag1);
        mInnerGroup.playAnim(l, l1, l2, flag, flag1);
    }

    public int playSound(String s)
    {
        return playSound(s, new SoundManager.SoundOptions(false, false, 1.0F));
    }

    public int playSound(String s, SoundManager.SoundOptions soundoptions)
    {
        if(TextUtils.isEmpty(s))
            return 0;
        if(shouldPlaySound())
            return mSoundManager.playSound(s, soundoptions);
        else
            return 0;
    }

    public void playSound(int i, SoundManager.Command command)
    {
        mSoundManager.playSound(i, command);
_L1:
        return;
        command;
        Log.e("ScreenElementRoot", command.toString());
          goto _L1
    }

    public boolean postDelayed(Runnable runnable, long l)
    {
        if(mFinished)
            return false;
        else
            return mContext.postDelayed(runnable, l);
    }

    public void postMessage(MotionEvent motionevent)
    {
        mController.postMessage(motionevent);
    }

    protected String putRawAttr(String s, String s1)
    {
        return (String)mRawAttrs.put(s, s1);
    }

    public void removeCallbacks(Runnable runnable)
    {
        mContext.removeCallbacks(runnable);
    }

    public void removePreTicker(ITicker iticker)
    {
        mPreTickers.remove(iticker);
    }

    public void requestUpdate()
    {
        int i = mRendererControllers.size();
        for(int j = 0; j < i; j++)
            ((RendererController)mRendererControllers.get(j)).requestUpdate();

    }

    public void reset(long l)
    {
        super.reset(l);
        mInnerGroup.reset(l);
    }

    public void resume()
    {
        super.resume();
        Log.d("ScreenElementRoot", "resume");
        mShowDebugLayout = SystemProperties.getBoolean("debug.layout", false);
        mInnerGroup.performAction("resume");
        mInnerGroup.resume();
        if(mVariableBinderManager != null)
            mVariableBinderManager.resume();
        if(mExternalCommandManager != null)
            mExternalCommandManager.resume();
        if(mVariableUpdaterManager != null)
            mVariableUpdaterManager.resume();
        mContext.mResourceManager.resume();
    }

    protected void resumeAnim(long l)
    {
        super.resumeAnim(l);
        mInnerGroup.resumeAnim(l);
    }

    public void saveVar(String s, Double double1)
    {
        if(mConfig == null)
        {
            Log.w("ScreenElementRoot", "fail to saveVar, config file is null");
            return;
        }
        if(double1 == null)
            mConfig.putNumber(s, "null");
        else
            mConfig.putNumber(s, double1.doubleValue());
    }

    public void saveVar(String s, String s1)
    {
        if(mConfig == null)
        {
            Log.w("ScreenElementRoot", "fail to saveVar, config file is null");
            return;
        } else
        {
            mConfig.putString(s, s1);
            return;
        }
    }

    public void selfFinish()
    {
        mController.finish();
    }

    public void selfInit()
    {
        mController.init();
    }

    public void selfPause()
    {
        int i = mRendererControllers.size();
        for(int j = 0; j < i; j++)
            ((RendererController)mRendererControllers.get(j)).selfPause();

    }

    public void selfResume()
    {
        int i = mRendererControllers.size();
        for(int j = 0; j < i; j++)
            ((RendererController)mRendererControllers.get(j)).selfResume();

    }

    public void setBgColor(int i)
    {
        mBgColor = i;
    }

    public void setCacheDir(String s)
    {
        mCacheDir = s;
    }

    public void setCapability(int i, boolean flag)
    {
        if(flag)
            mCapability = mCapability | i;
        else
            mCapability = mCapability & i;
    }

    public void setClearCanvas(boolean flag)
    {
        mClearCanvas = flag;
    }

    public void setConfig(String s)
    {
        mConfigPath = s;
    }

    public void setConfiguration(Configuration configuration)
    {
        if(!mAllowScreenRotation) goto _L2; else goto _L1
_L1:
        Variables variables;
        variables = mContext.mVariables;
        Utils.putVariableNumber("orientation", mContext.mVariables, Double.valueOf(configuration.orientation));
        configuration.orientation;
        JVM INSTR tableswitch 1 2: default 64
    //                   1 128
    //                   2 65;
           goto _L2 _L3 _L4
_L2:
        return;
_L4:
        variables.put("raw_screen_width", mTargetScreenHeight);
        variables.put("raw_screen_height", mTargetScreenWidth);
        variables.put("screen_width", (float)mTargetScreenHeight / mScale);
        variables.put("screen_height", (float)mTargetScreenWidth / mScale);
        continue; /* Loop/switch isn't completed */
_L3:
        variables.put("raw_screen_width", mTargetScreenWidth);
        variables.put("raw_screen_height", mTargetScreenHeight);
        variables.put("screen_width", (float)mTargetScreenWidth / mScale);
        variables.put("screen_height", (float)mTargetScreenHeight / mScale);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void setDefaultFramerate(float f)
    {
        DEFAULT_FRAME_RATE = f;
    }

    public final void setKeepResource(boolean flag)
    {
        mKeepResource = flag;
    }

    public void setOnExternCommandListener(OnExternCommandListener onexterncommandlistener)
    {
        Object obj = null;
        if(onexterncommandlistener == null)
            onexterncommandlistener = obj;
        else
            onexterncommandlistener = new WeakReference(onexterncommandlistener);
        mExternCommandListener = onexterncommandlistener;
    }

    public void setOnHoverChangeListener(OnHoverChangeListener onhoverchangelistener)
    {
        mHoverChangeListener = onhoverchangelistener;
    }

    public void setRenderControllerListener(RendererController.Listener listener)
    {
        mController.setListener(listener);
    }

    public void setRenderControllerRenderable(RendererController.IRenderable irenderable)
    {
        setRenderControllerListener(new SingleRootListener(this, irenderable));
    }

    public void setScaleByDensity(boolean flag)
    {
        mScaleByDensity = flag;
    }

    public void setViewManager(ViewManager viewmanager)
    {
        mViewManager = viewmanager;
    }

    protected boolean shouldPlaySound()
    {
        return true;
    }

    public void showCategory(String s, boolean flag)
    {
        mInnerGroup.showCategory(s, flag);
    }

    public void showFramerate(boolean flag)
    {
        mShowFramerate = flag;
    }

    public void tick(long l)
    {
        if(mNeedReset)
        {
            reset(l);
            onCommand("init");
            mNeedReset = false;
        }
        doTick(l);
    }

    public long update(long l)
    {
        long l1 = 0x7fffffffffffffffL;
        int i = mRendererControllers.size();
        int j = 0;
        while(j < i) 
        {
            RendererController renderercontroller = (RendererController)mRendererControllers.get(j);
            long l2;
            if(renderercontroller.isSelfPaused() && renderercontroller.hasRunnable() ^ true)
            {
                l2 = l1;
            } else
            {
                long l3 = renderercontroller.update(l);
                l2 = l1;
                if(l3 < l1)
                    l2 = l3;
            }
            j++;
            l1 = l2;
        }
        return l1;
    }

    public long updateIfNeeded(long l)
    {
        long l1 = 0x7fffffffffffffffL;
        int i = mRendererControllers.size();
        int j = 0;
        while(j < i) 
        {
            RendererController renderercontroller = (RendererController)mRendererControllers.get(j);
            long l2;
            if(renderercontroller.isSelfPaused() && renderercontroller.hasRunnable() ^ true)
            {
                l2 = l1;
            } else
            {
                long l3 = renderercontroller.updateIfNeeded(l);
                l2 = l1;
                if(l3 < l1)
                    l2 = l3;
            }
            j++;
            l1 = l2;
        }
        return l1;
    }

    public final int version()
    {
        return mVersion;
    }

    private static final boolean CALCULATE_FRAME_RATE = true;
    public static final int CAPABILITY_ALL = -1;
    public static final int CAPABILITY_CREATE_OBJ = 4;
    public static final int CAPABILITY_VAR_PERSISTENCE = 2;
    public static final int CAPABILITY_WEBSERVICE = 1;
    private static final int DEFAULT_RES_DENSITY = 240;
    private static final int DEFAULT_SCREEN_WIDTH = 480;
    private static final String EXTERNAL_COMMANDS_TAG_NAME = "ExternalCommands";
    private static final String LOG_TAG = "ScreenElementRoot";
    private static final int MAML_VERSION = 4;
    private static final String MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String RAW_DENSITY = "__raw_density";
    private static final String ROOT_NAME = "__root";
    private static final String SCALE_FACTOR = "__scale_factor";
    private static final String THEMEMANAGER_PACKAGE_NAME = "com.android.thememanager";
    private static final String VARIABLE_VIEW_HEIGHT = "view_height";
    private static final String VARIABLE_VIEW_WIDTH = "view_width";
    private static final String VAR_MAML_VERSION = "__maml_version";
    protected float DEFAULT_FRAME_RATE;
    private List mAccessibleElements;
    private boolean mAllowScreenRotation;
    private int mBgColor;
    private String mCacheDir;
    private int mCapability;
    private long mCheckPoint;
    private boolean mClearCanvas;
    private ConfigFile mConfig;
    private String mConfigPath;
    protected ScreenContext mContext;
    protected RendererController mController;
    private int mDefaultResourceDensity;
    private int mDefaultScreenWidth;
    private WeakReference mExternCommandListener;
    private CommandTriggers mExternalCommandManager;
    private boolean mFinished;
    private float mFontScale;
    protected float mFrameRate;
    private IndexedVariable mFrameRateVar;
    private FramerateHelper mFramerateHelper;
    private int mFrames;
    private float mHeight;
    private OnHoverChangeListener mHoverChangeListener;
    private AnimatedScreenElement mHoverElement;
    private Matrix mHoverMatrix;
    protected ElementGroup mInnerGroup;
    private boolean mKeepResource;
    private boolean mNeedDisallowInterceptTouchEvent;
    private IndexedVariable mNeedDisallowInterceptTouchEventVar;
    private boolean mNeedReset;
    private ArrayList mPreTickers;
    protected HashMap mRawAttrs;
    private int mRawDefaultResourceDensity;
    private int mRawHeight;
    private int mRawTargetDensity;
    private int mRawWidth;
    private ArrayList mRendererControllers;
    private String mRootTag;
    private float mScale;
    private boolean mScaleByDensity;
    public boolean mShowDebugLayout;
    private boolean mShowFramerate;
    private SoundManager mSoundManager;
    private StylesManager mStylesManager;
    private OnExternCommandListener mSystemExternCommandListener;
    private int mTargetDensity;
    protected int mTargetScreenHeight;
    protected int mTargetScreenWidth;
    private IndexedVariable mTouchBeginTime;
    private IndexedVariable mTouchBeginX;
    private IndexedVariable mTouchBeginY;
    private IndexedVariable mTouchX;
    private IndexedVariable mTouchY;
    protected VariableBinderManager mVariableBinderManager;
    private VariableUpdaterManager mVariableUpdaterManager;
    private int mVersion;
    private ViewManager mViewManager;
    private float mWidth;
}
