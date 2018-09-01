// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import java.util.ArrayList;
import miui.maml.*;
import miui.maml.animation.BaseAnimation;
import miui.maml.data.*;
import miui.maml.util.StyleHelper;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ScreenElementVisitor, ElementGroup

public abstract class ScreenElement
{
    protected static final class Align extends Enum
    {

        public static Align valueOf(String s)
        {
            return (Align)Enum.valueOf(miui/maml/elements/ScreenElement$Align, s);
        }

        public static Align[] values()
        {
            return $VALUES;
        }

        private static final Align $VALUES[];
        public static final Align CENTER;
        public static final Align LEFT;
        public static final Align RIGHT;

        static 
        {
            LEFT = new Align("LEFT", 0);
            CENTER = new Align("CENTER", 1);
            RIGHT = new Align("RIGHT", 2);
            $VALUES = (new Align[] {
                LEFT, CENTER, RIGHT
            });
        }

        private Align(String s, int i)
        {
            super(s, i);
        }
    }

    protected static final class AlignV extends Enum
    {

        public static AlignV valueOf(String s)
        {
            return (AlignV)Enum.valueOf(miui/maml/elements/ScreenElement$AlignV, s);
        }

        public static AlignV[] values()
        {
            return $VALUES;
        }

        private static final AlignV $VALUES[];
        public static final AlignV BOTTOM;
        public static final AlignV CENTER;
        public static final AlignV TOP;

        static 
        {
            TOP = new AlignV("TOP", 0);
            CENTER = new AlignV("CENTER", 1);
            BOTTOM = new AlignV("BOTTOM", 2);
            $VALUES = (new AlignV[] {
                TOP, CENTER, BOTTOM
            });
        }

        private AlignV(String s, int i)
        {
            super(s, i);
        }
    }


    private static int[] _2D_getmiui_2D_maml_2D_elements_2D_ScreenElement$AlignSwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_elements_2D_ScreenElement$AlignSwitchesValues != null)
            return _2D_miui_2D_maml_2D_elements_2D_ScreenElement$AlignSwitchesValues;
        int ai[] = new int[Align.values().length];
        try
        {
            ai[Align.CENTER.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Align.LEFT.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Align.RIGHT.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_elements_2D_ScreenElement$AlignSwitchesValues = ai;
        return ai;
    }

    private static int[] _2D_getmiui_2D_maml_2D_elements_2D_ScreenElement$AlignVSwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_elements_2D_ScreenElement$AlignVSwitchesValues != null)
            return _2D_miui_2D_maml_2D_elements_2D_ScreenElement$AlignVSwitchesValues;
        int ai[] = new int[AlignV.values().length];
        try
        {
            ai[AlignV.BOTTOM.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[AlignV.CENTER.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[AlignV.TOP.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_elements_2D_ScreenElement$AlignVSwitchesValues = ai;
        return ai;
    }

    public ScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        mInitShow = true;
        mShow = true;
        mIsVisible = true;
        mRoot = screenelementroot;
        if(element != null && screenelementroot != null)
            mStyle = screenelementroot.getStyle(element.getAttribute("style"));
        load(element);
    }

    protected static boolean isTagEnable(String as[], String s)
    {
        if(as == null)
        {
            if(TextUtils.isEmpty(s))
                return true;
        } else
        if(Utils.arrayContains(as, s))
            return true;
        return false;
    }

    private void load(Element element)
    {
        if(element == null)
            return;
        mCategory = getAttr(element, "category");
        mName = getAttr(element, "name");
        mHasName = TextUtils.isEmpty(mName) ^ true;
        String s = getAttr(element, "visibility");
        String s1;
        if(!TextUtils.isEmpty(s))
            if(s.equalsIgnoreCase("false"))
                mInitShow = false;
            else
            if(s.equalsIgnoreCase("true"))
                mInitShow = true;
            else
                mVisibilityExpression = Expression.build(getVariables(), s);
        mAlign = Align.LEFT;
        s1 = getAttr(element, "align");
        s = s1;
        if(TextUtils.isEmpty(s1))
            s = getAttr(element, "alignH");
        if(s.equalsIgnoreCase("right"))
            mAlign = Align.RIGHT;
        else
        if(s.equalsIgnoreCase("left"))
            mAlign = Align.LEFT;
        else
        if(s.equalsIgnoreCase("center"))
            mAlign = Align.CENTER;
        mAlignV = AlignV.TOP;
        s = getAttr(element, "alignV");
        if(s.equalsIgnoreCase("bottom"))
            mAlignV = AlignV.BOTTOM;
        else
        if(s.equalsIgnoreCase("top"))
            mAlignV = AlignV.TOP;
        else
        if(s.equalsIgnoreCase("center"))
            mAlignV = AlignV.CENTER;
        loadTriggers(element);
        loadAnimations(element);
    }

    private void loadAnimations(Element element)
    {
        Utils.traverseXmlElementChildren(element, null, new miui.maml.util.Utils.XmlTraverseListener() {

            public void onChild(Element element1)
            {
                String s = element1.getNodeName();
                if(s.endsWith("Animation"))
                {
                    element1 = onCreateAnimation(s, element1);
                    if(element1 != null)
                    {
                        if(mAnimations == null)
                            mAnimations = new ArrayList();
                        mAnimations.add(element1);
                    }
                }
            }

            final ScreenElement this$0;

            
            {
                this$0 = ScreenElement.this;
                super();
            }
        }
);
    }

    private void setVisibilityVar(boolean flag)
    {
        int i = 1;
        if(mHasName)
        {
            if(mVisibilityVar == null)
                mVisibilityVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("visibility").toString(), getContext().mVariables, true);
            IndexedVariable indexedvariable = mVisibilityVar;
            if(!flag)
                i = 0;
            indexedvariable.set(i);
        }
    }

    public void acceptVisitor(ScreenElementVisitor screenelementvisitor)
    {
        screenelementvisitor.visit(this);
    }

    public miui.maml.FramerateTokenList.FramerateToken createToken(String s)
    {
        RendererController renderercontroller = getRendererController();
        if(renderercontroller == null)
            return null;
        else
            return renderercontroller.createToken(s);
    }

    protected final double descale(double d)
    {
        return d / (double)mRoot.getScale();
    }

    protected abstract void doRender(Canvas canvas);

    protected void doTick(long l)
    {
        if(mAnimations != null)
        {
            int i = mAnimations.size();
            for(int j = 0; j < i; j++)
                ((BaseAnimation)mAnimations.get(j)).tick(l);

        }
    }

    protected final double evaluate(Expression expression)
    {
        double d;
        if(expression == null)
            d = 0.0D;
        else
            d = expression.evaluate();
        return d;
    }

    protected final String evaluateStr(Expression expression)
    {
        Object obj = null;
        if(expression == null)
            expression = obj;
        else
            expression = expression.evaluateStr();
        return expression;
    }

    public ScreenElement findElement(String s)
    {
        if(mName != null && mName.equals(s))
            s = this;
        else
            s = null;
        return s;
    }

    public void finish()
    {
        if(mTriggers != null)
            mTriggers.finish();
        if(mAnimations != null)
        {
            int i = mAnimations.size();
            for(int j = 0; j < i; j++)
                ((BaseAnimation)mAnimations.get(j)).finish();

        }
    }

    protected String getAttr(Element element, String s)
    {
        return StyleHelper.getAttr(element, s, mStyle);
    }

    protected float getAttrAsFloat(Element element, String s, float f)
    {
        float f1;
        try
        {
            f1 = Float.parseFloat(getAttr(element, s));
        }
        // Misplaced declaration of an exception variable
        catch(Element element)
        {
            return f;
        }
        return f1;
    }

    protected int getAttrAsInt(Element element, String s, int i)
    {
        int j;
        try
        {
            j = Integer.parseInt(getAttr(element, s));
        }
        // Misplaced declaration of an exception variable
        catch(Element element)
        {
            return i;
        }
        return j;
    }

    protected long getAttrAsLong(Element element, String s, long l)
    {
        long l1;
        try
        {
            l1 = Long.parseLong(getAttr(element, s));
        }
        // Misplaced declaration of an exception variable
        catch(Element element)
        {
            return l;
        }
        return l1;
    }

    public ScreenContext getContext()
    {
        return mRoot.getContext();
    }

    protected final float getFramerate()
    {
        float f;
        if(mFramerateToken == null)
            f = 0.0F;
        else
            f = mFramerateToken.getFramerate();
        return f;
    }

    protected float getLeft(float f, float f1)
    {
        float f2;
        if(f1 <= 0.0F)
            return f;
        f2 = f;
        _2D_getmiui_2D_maml_2D_elements_2D_ScreenElement$AlignSwitchesValues()[mAlign.ordinal()];
        JVM INSTR tableswitch 1 2: default 44
    //                   1 48
    //                   2 57;
           goto _L1 _L2 _L3
_L1:
        f = f2;
_L5:
        return f;
_L2:
        f -= f1 / 2.0F;
        continue; /* Loop/switch isn't completed */
_L3:
        f -= f1;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public String getName()
    {
        return mName;
    }

    protected final NotifierManager getNotifierManager()
    {
        return NotifierManager.getInstance(getContext().mContext);
    }

    public ElementGroup getParent()
    {
        return mParent;
    }

    public RendererController getRendererController()
    {
        RendererController renderercontroller = null;
        if(mParent != null)
            renderercontroller = mParent.getRendererController();
        return renderercontroller;
    }

    public ScreenElementRoot getRoot()
    {
        return mRoot;
    }

    protected float getTop(float f, float f1)
    {
        float f2;
        if(f1 <= 0.0F)
            return f;
        f2 = f;
        _2D_getmiui_2D_maml_2D_elements_2D_ScreenElement$AlignVSwitchesValues()[mAlignV.ordinal()];
        JVM INSTR tableswitch 1 2: default 44
    //                   1 57
    //                   2 48;
           goto _L1 _L2 _L3
_L1:
        f = f2;
_L5:
        return f;
_L3:
        f -= f1 / 2.0F;
        continue; /* Loop/switch isn't completed */
_L2:
        f -= f1;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public final Variables getVariables()
    {
        return getContext().mVariables;
    }

    public void init()
    {
        mShow = mInitShow;
        mFramerateToken = null;
        if(mTriggers != null)
            mTriggers.init();
        setAnim(null);
        if(mAnimations != null)
        {
            int i = mAnimations.size();
            for(int j = 0; j < i; j++)
                ((BaseAnimation)mAnimations.get(j)).init();

        }
        updateVisibility();
        setVisibilityVar(isVisible());
        performAction("init");
    }

    public boolean isVisible()
    {
        return mIsVisible;
    }

    protected boolean isVisibleInner()
    {
        boolean flag;
        if(mShow && (mVisibilityExpression == null || mVisibilityExpression.evaluate() > 0.0D))
        {
            if(mParent == null)
                flag = true;
            else
                flag = mParent.isVisible();
        } else
        {
            flag = false;
        }
        return flag;
    }

    protected void loadTriggers(Element element)
    {
        element = Utils.getChild(element, "Triggers");
        if(element != null)
            mTriggers = new CommandTriggers(element, this);
    }

    protected BaseAnimation onCreateAnimation(String s, Element element)
    {
        return null;
    }

    public boolean onHover(MotionEvent motionevent)
    {
        return false;
    }

    protected void onSetAnimBefore()
    {
    }

    protected void onSetAnimEnable(BaseAnimation baseanimation)
    {
    }

    public boolean onTouch(MotionEvent motionevent)
    {
        return false;
    }

    protected void onVisibilityChange(boolean flag)
    {
        setVisibilityVar(flag);
        if(!flag)
        {
            mCurFramerate = getFramerate();
            requestFramerate(0.0F);
        } else
        {
            requestFramerate(mCurFramerate);
        }
    }

    public void pause()
    {
        if(mTriggers != null)
            mTriggers.pause();
        if(mAnimations != null)
        {
            int i = mAnimations.size();
            for(int j = 0; j < i; j++)
                ((BaseAnimation)mAnimations.get(j)).pause();

        }
    }

    public final void pauseAnim()
    {
        long l = SystemClock.elapsedRealtime();
        pauseAnim(l);
        doTick(l);
    }

    protected void pauseAnim(long l)
    {
        if(mAnimations != null)
        {
            int i = mAnimations.size();
            for(int j = 0; j < i; j++)
                ((BaseAnimation)mAnimations.get(j)).pauseAnim(l);

        }
    }

    public void performAction(String s)
    {
        if(mTriggers != null && s != null)
        {
            mTriggers.onAction(s);
            requestUpdate();
        }
    }

    public final void playAnim()
    {
        playAnim(0L, -1L, true, true);
    }

    protected void playAnim(long l, long l1, long l2, boolean flag, 
            boolean flag1)
    {
        if(mAnimations != null)
        {
            int i = mAnimations.size();
            for(int j = 0; j < i; j++)
                ((BaseAnimation)mAnimations.get(j)).playAnim(l, l1, l2, flag, flag1);

        }
    }

    public final void playAnim(long l, long l1, boolean flag, boolean flag1)
    {
        long l2 = SystemClock.elapsedRealtime();
        playAnim(l2, l, l1, flag, flag1);
        doTick(l2);
    }

    protected final void postInMainThread(Runnable runnable)
    {
        getContext().getHandler().post(runnable);
    }

    public void postRunnable(Runnable runnable)
    {
        RendererController renderercontroller = mRoot.getRendererController();
        if(renderercontroller != null)
            renderercontroller.postRunnable(runnable);
    }

    public void render(Canvas canvas)
    {
        updateVisibility();
        if(!isVisible())
        {
            return;
        } else
        {
            doRender(canvas);
            return;
        }
    }

    protected final void requestFramerate(float f)
    {
        if(f < 0.0F)
            return;
        if(mFramerateToken == null)
        {
            if(f == 0.0F)
                return;
            mFramerateToken = createToken(toString());
        }
        if(mFramerateToken != null)
            mFramerateToken.requestFramerate(f);
    }

    public void requestUpdate()
    {
        RendererController renderercontroller = getRendererController();
        if(renderercontroller != null)
            renderercontroller.requestUpdate();
    }

    public final void reset()
    {
        long l = SystemClock.elapsedRealtime();
        reset(l);
        doTick(l);
    }

    public void reset(long l)
    {
        if(mAnimations != null)
        {
            int i = mAnimations.size();
            for(int j = 0; j < i; j++)
                ((BaseAnimation)mAnimations.get(j)).reset(l);

        }
    }

    public void resume()
    {
        if(mTriggers != null)
            mTriggers.resume();
        if(mAnimations != null)
        {
            int i = mAnimations.size();
            for(int j = 0; j < i; j++)
                ((BaseAnimation)mAnimations.get(j)).resume();

        }
    }

    public final void resumeAnim()
    {
        long l = SystemClock.elapsedRealtime();
        resumeAnim(l);
        doTick(l);
    }

    protected void resumeAnim(long l)
    {
        if(mAnimations != null)
        {
            int i = mAnimations.size();
            for(int j = 0; j < i; j++)
                ((BaseAnimation)mAnimations.get(j)).resumeAnim(l);

        }
    }

    protected final float scale(double d)
    {
        return (float)((double)mRoot.getScale() * d);
    }

    protected void setActualHeight(double d)
    {
        if(!mHasName)
            return;
        if(mActualHeightVar == null)
            mActualHeightVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("actual_h").toString(), getVariables(), true);
        mActualHeightVar.set(d);
    }

    protected void setActualWidth(double d)
    {
        if(!mHasName)
            return;
        if(mActualWidthVar == null)
            mActualWidthVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("actual_w").toString(), getVariables(), true);
        mActualWidthVar.set(d);
    }

    public void setAnim(String as[])
    {
        if(mAnimations != null)
        {
            onSetAnimBefore();
            int i = mAnimations.size();
            for(int j = 0; j < i; j++)
            {
                BaseAnimation baseanimation = (BaseAnimation)mAnimations.get(j);
                boolean flag = isTagEnable(as, baseanimation.getTag());
                baseanimation.setDisable(flag ^ true);
                if(flag)
                    onSetAnimEnable(baseanimation);
            }

        }
    }

    public final void setAnimations(String s)
    {
        if(TextUtils.isEmpty(s) || ".".equals(s))
            s = null;
        else
            s = s.split(",");
        setAnim(s);
    }

    public void setName(String s)
    {
        mName = s;
    }

    public void setParent(ElementGroup elementgroup)
    {
        mParent = elementgroup;
    }

    public void show(boolean flag)
    {
        mShow = flag;
        updateVisibility();
        requestUpdate();
    }

    public void showCategory(String s, boolean flag)
    {
        if(mCategory != null && mCategory.equals(s))
            show(flag);
    }

    public void tick(long l)
    {
        updateVisibility();
        if(!isVisible())
        {
            return;
        } else
        {
            doTick(l);
            return;
        }
    }

    protected void updateVisibility()
    {
        boolean flag = isVisibleInner();
        if(mIsVisible != flag)
        {
            mIsVisible = flag;
            onVisibilityChange(mIsVisible);
            if(flag)
                doTick(SystemClock.elapsedRealtime());
        }
    }

    private static final int _2D_miui_2D_maml_2D_elements_2D_ScreenElement$AlignSwitchesValues[];
    private static final int _2D_miui_2D_maml_2D_elements_2D_ScreenElement$AlignVSwitchesValues[];
    public static final String ACTUAL_H = "actual_h";
    public static final String ACTUAL_W = "actual_w";
    public static final String ACTUAL_X = "actual_x";
    public static final String ACTUAL_Y = "actual_y";
    private static final String LOG_TAG = "MAML ScreenElement";
    public static final String VISIBILITY = "visibility";
    public static final int VISIBILITY_FALSE = 0;
    public static final int VISIBILITY_TRUE = 1;
    private IndexedVariable mActualHeightVar;
    private IndexedVariable mActualWidthVar;
    protected Align mAlign;
    protected AlignV mAlignV;
    protected ArrayList mAnimations;
    protected RendererController mAvailableController;
    protected String mCategory;
    private float mCurFramerate;
    private miui.maml.FramerateTokenList.FramerateToken mFramerateToken;
    protected boolean mHasName;
    private boolean mInitShow;
    private boolean mIsVisible;
    protected String mName;
    protected ElementGroup mParent;
    protected ScreenElementRoot mRoot;
    private boolean mShow;
    protected miui.maml.StylesManager.Style mStyle;
    protected CommandTriggers mTriggers;
    private Expression mVisibilityExpression;
    private IndexedVariable mVisibilityVar;
}
