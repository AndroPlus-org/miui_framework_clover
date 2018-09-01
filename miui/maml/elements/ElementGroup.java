// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import java.util.ArrayList;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;
import miui.maml.data.IndexedVariable;
import org.w3c.dom.*;

// Referenced classes of package miui.maml.elements:
//            AnimatedScreenElement, ScreenElement, ScreenElementFactory, ScreenElementVisitor

public class ElementGroup extends AnimatedScreenElement
{
    private static final class LinearDirection extends Enum
    {

        public static LinearDirection valueOf(String s)
        {
            return (LinearDirection)Enum.valueOf(miui/maml/elements/ElementGroup$LinearDirection, s);
        }

        public static LinearDirection[] values()
        {
            return $VALUES;
        }

        private static final LinearDirection $VALUES[];
        public static final LinearDirection Horizontal;
        public static final LinearDirection None;
        public static final LinearDirection Vertical;

        static 
        {
            None = new LinearDirection("None", 0);
            Horizontal = new LinearDirection("Horizontal", 1);
            Vertical = new LinearDirection("Vertical", 2);
            $VALUES = (new LinearDirection[] {
                None, Horizontal, Vertical
            });
        }

        private LinearDirection(String s, int i)
        {
            super(s, i);
        }
    }


    private static int[] _2D_getmiui_2D_maml_2D_elements_2D_ElementGroup$LinearDirectionSwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_elements_2D_ElementGroup$LinearDirectionSwitchesValues != null)
            return _2D_miui_2D_maml_2D_elements_2D_ElementGroup$LinearDirectionSwitchesValues;
        int ai[] = new int[LinearDirection.values().length];
        try
        {
            ai[LinearDirection.Horizontal.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[LinearDirection.None.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[LinearDirection.Vertical.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_elements_2D_ElementGroup$LinearDirectionSwitchesValues = ai;
        return ai;
    }

    private ElementGroup(ScreenElementRoot screenelementroot, IndexedVariable indexedvariable)
    {
        super(null, screenelementroot);
        mElements = new ArrayList();
        mLinearDirection = LinearDirection.None;
        mIndexVar = indexedvariable;
    }

    public ElementGroup(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mElements = new ArrayList();
        mLinearDirection = LinearDirection.None;
        load(element);
    }

    public static ElementGroup createArrayGroup(ScreenElementRoot screenelementroot, IndexedVariable indexedvariable)
    {
        return new ElementGroup(screenelementroot, indexedvariable);
    }

    public static boolean isArrayGroup(ScreenElement screenelement)
    {
        boolean flag;
        if(screenelement instanceof ElementGroup)
            flag = ((ElementGroup)screenelement).isArray();
        else
            flag = false;
        return flag;
    }

    private void load(Element element)
    {
        String s;
        if(element == null)
            return;
        mClip = Boolean.parseBoolean(getAttr(element, "clip"));
        mLayered = Boolean.parseBoolean(getAttr(element, "layered"));
        s = getAttr(element, "linear");
        if(!"h".equalsIgnoreCase(s)) goto _L2; else goto _L1
_L1:
        mLinearDirection = LinearDirection.Horizontal;
_L4:
        element = element.getChildNodes();
        int i = element.getLength();
        for(int j = 0; j < i; j++)
            if(element.item(j).getNodeType() == 1)
                addElement(onCreateChild((Element)element.item(j)));

        break; /* Loop/switch isn't completed */
_L2:
        if("v".equalsIgnoreCase(s))
            mLinearDirection = LinearDirection.Vertical;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void acceptVisitor(ScreenElementVisitor screenelementvisitor)
    {
        super.acceptVisitor(screenelementvisitor);
        int i = mElements.size();
        for(int j = 0; j < i; j++)
            ((ScreenElement)mElements.get(j)).acceptVisitor(screenelementvisitor);

    }

    public void addElement(ScreenElement screenelement)
    {
        if(screenelement != null)
        {
            screenelement.setParent(this);
            mElements.add(screenelement);
        }
    }

    protected void doRender(Canvas canvas)
    {
        float f = getWidth();
        float f1 = getHeight();
        float f2 = getLeft(0.0F, f);
        float f3 = getTop(0.0F, f1);
        int i;
        if(mLayered && f > 0.0F && f1 > 0.0F)
            i = canvas.saveLayerAlpha(f2, f3, f2 + f, f3 + f1, getAlpha(), 31);
        else
            i = canvas.save();
        canvas.translate(f2, f3);
        if(f > 0.0F && f1 > 0.0F && mClip)
            canvas.clipRect(0.0F, 0.0F, f, f1);
        doRenderChildren(canvas);
        canvas.restoreToCount(i);
    }

    protected void doRenderChildren(Canvas canvas)
    {
        int i = mElements.size();
        for(int j = 0; j < i; j++)
        {
            ScreenElement screenelement = (ScreenElement)mElements.get(j);
            if(mIndexVar != null)
                mIndexVar.set(j);
            screenelement.render(canvas);
        }

    }

    protected void doTick(long l)
    {
        super.doTick(l);
        doTickChildren(l);
    }

    protected void doTickChildren(long l)
    {
        int i;
        float f;
        float f1;
        int j;
        i = mElements.size();
        f = 0.0F;
        f1 = 0.0F;
        j = 0;
_L2:
        Object obj;
        float f2;
        float f3;
        if(j >= i)
            break MISSING_BLOCK_LABEL_310;
        obj = (ScreenElement)mElements.get(j);
        if(mIndexVar != null)
            mIndexVar.set(j);
        ((ScreenElement) (obj)).tick(l);
        f2 = f;
        f3 = f1;
        if(mLinearDirection != LinearDirection.None)
        {
            f2 = f;
            f3 = f1;
            if(obj instanceof AnimatedScreenElement)
            {
                f2 = f;
                f3 = f1;
                if(((ScreenElement) (obj)).isVisible())
                {
                    obj = (AnimatedScreenElement)obj;
                    switch(_2D_getmiui_2D_maml_2D_elements_2D_ElementGroup$LinearDirectionSwitchesValues()[mLinearDirection.ordinal()])
                    {
                    default:
                        f3 = f1;
                        f2 = f;
                        break;

                    case 1: // '\001'
                        break; /* Loop/switch isn't completed */

                    case 2: // '\002'
                        break MISSING_BLOCK_LABEL_242;
                    }
                }
            }
        }
_L3:
        j++;
        f = f2;
        f1 = f3;
        if(true) goto _L2; else goto _L1
_L1:
        f2 = f + ((AnimatedScreenElement) (obj)).getMarginLeft();
        ((AnimatedScreenElement) (obj)).setX(f2);
        f = f2 + (((AnimatedScreenElement) (obj)).getWidth() + ((AnimatedScreenElement) (obj)).getMarginRight());
        float f4 = ((AnimatedScreenElement) (obj)).getHeight();
        f2 = f;
        f3 = f1;
        if(f1 < f4)
        {
            f3 = f4;
            f2 = f;
        }
          goto _L3
        f2 = f + ((AnimatedScreenElement) (obj)).getMarginTop();
        ((AnimatedScreenElement) (obj)).setY(f2);
        f = f2 + (((AnimatedScreenElement) (obj)).getHeight() + ((AnimatedScreenElement) (obj)).getMarginBottom());
        float f5 = ((AnimatedScreenElement) (obj)).getWidth();
        f2 = f;
        f3 = f1;
        if(f1 < f5)
        {
            f3 = f5;
            f2 = f;
        }
          goto _L3
        _2D_getmiui_2D_maml_2D_elements_2D_ElementGroup$LinearDirectionSwitchesValues()[mLinearDirection.ordinal()];
        JVM INSTR tableswitch 1 2: default 344
    //                   1 345
    //                   2 384;
           goto _L4 _L5 _L6
_L4:
        return;
_L5:
        setW(f);
        setH(f1);
        setActualWidth(descale(f));
        setActualHeight(descale(f1));
        continue; /* Loop/switch isn't completed */
_L6:
        setH(f);
        setW(f1);
        setActualHeight(descale(f));
        setActualWidth(descale(f1));
        if(true) goto _L4; else goto _L7
_L7:
    }

    public ScreenElement findElement(String s)
    {
        ScreenElement screenelement = super.findElement(s);
        if(screenelement != null)
            return screenelement;
        int i = mElements.size();
        for(int j = 0; j < i; j++)
        {
            ScreenElement screenelement1 = ((ScreenElement)mElements.get(j)).findElement(s);
            if(screenelement1 != null)
                return screenelement1;
        }

        return null;
    }

    public void finish()
    {
        super.finish();
        int i = mElements.size();
        int j = 0;
        do
        {
            if(j >= i)
                break;
            try
            {
                ((ScreenElement)mElements.get(j)).finish();
            }
            catch(Exception exception)
            {
                Log.e("MAML_ElementGroup", exception.toString());
                exception.printStackTrace();
            }
            j++;
        } while(true);
    }

    public ScreenElement getChild(int i)
    {
        if(i < 0 || i >= mElements.size())
            return null;
        else
            return (ScreenElement)mElements.get(i);
    }

    public ArrayList getElements()
    {
        return mElements;
    }

    protected float getParentLeft()
    {
        float f = getLeft();
        float f1;
        if(mParent == null)
            f1 = 0.0F;
        else
            f1 = mParent.getParentLeft();
        return f1 + f;
    }

    protected float getParentTop()
    {
        float f = getTop();
        float f1;
        if(mParent == null)
            f1 = 0.0F;
        else
            f1 = mParent.getParentTop();
        return f1 + f;
    }

    public int getSize()
    {
        return mElements.size();
    }

    public void init()
    {
        super.init();
        int i = mElements.size();
        for(int j = 0; j < i; j++)
        {
            if(mIndexVar != null)
                mIndexVar.set(j);
            ((ScreenElement)mElements.get(j)).init();
        }

    }

    public boolean isArray()
    {
        boolean flag;
        if(mIndexVar != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isLayered()
    {
        return mLayered;
    }

    protected ScreenElement onCreateChild(Element element)
    {
        return getContext().mFactory.createInstance(element, mRoot);
    }

    public boolean onHover(MotionEvent motionevent)
    {
        if(!isVisible())
            return false;
        boolean flag = touched(motionevent.getX(), motionevent.getY());
        if(mClip && flag ^ true)
        {
            if(!mHovered)
                return false;
            motionevent.setAction(10);
        }
        boolean flag2 = false;
        int i = mElements.size() - 1;
        do
        {
label0:
            {
                boolean flag3 = flag2;
                if(i >= 0)
                {
                    ScreenElement screenelement = (ScreenElement)mElements.get(i);
                    if(mIndexVar != null)
                        mIndexVar.set(i);
                    if(!screenelement.onHover(motionevent))
                        break label0;
                    flag3 = true;
                }
                boolean flag1;
                if(flag3)
                    flag1 = true;
                else
                    flag1 = super.onHover(motionevent);
                mHovered = flag1;
                return mHovered;
            }
            i--;
        } while(true);
    }

    public boolean onTouch(MotionEvent motionevent)
    {
        boolean flag2;
        int j;
        boolean flag3;
        int k;
        if(!isVisible())
            return false;
        int i = motionevent.getAction();
        boolean flag = touched(motionevent.getX(), motionevent.getY());
        if(mClip && flag ^ true)
        {
            if(!mTouched)
                return false;
            motionevent.setAction(3);
        }
        flag2 = false;
        j = mElements.size();
        ScreenElement screenelement;
        if(mRoot.version() >= 2)
            flag3 = true;
        else
            flag3 = false;
        if(!flag3) goto _L2; else goto _L1
_L1:
        k = j - 1;
_L7:
        flag3 = flag2;
        if(k < 0) goto _L4; else goto _L3
_L3:
        screenelement = (ScreenElement)mElements.get(k);
        if(mIndexVar != null)
            mIndexVar.set(k);
        if(!screenelement.onTouch(motionevent)) goto _L6; else goto _L5
_L5:
        flag3 = true;
_L4:
        motionevent.setAction(i);
        boolean flag1;
        ScreenElement screenelement1;
        if(flag3)
            flag1 = true;
        else
            flag1 = super.onTouch(motionevent);
        mTouched = flag1;
        return mTouched;
_L6:
        k--;
          goto _L7
_L2:
        k = 0;
_L8:
        flag3 = flag2;
        if(k < j)
        {
label0:
            {
                screenelement1 = (ScreenElement)mElements.get(k);
                if(mIndexVar != null)
                    mIndexVar.set(k);
                if(!screenelement1.onTouch(motionevent))
                    break label0;
                flag3 = true;
            }
        }
          goto _L4
        k++;
          goto _L8
    }

    protected void onVisibilityChange(boolean flag)
    {
        super.onVisibilityChange(flag);
        int i = mElements.size();
        for(int j = 0; j < i; j++)
            ((ScreenElement)mElements.get(j)).updateVisibility();

    }

    public void pause()
    {
        super.pause();
        int i = mElements.size();
        for(int j = 0; j < i; j++)
            ((ScreenElement)mElements.get(j)).pause();

    }

    public void pauseAnim(long l)
    {
        super.pauseAnim(l);
        int i = mElements.size();
        for(int j = 0; j < i; j++)
            ((ScreenElement)mElements.get(j)).pauseAnim(l);

    }

    public void playAnim(long l, long l1, long l2, boolean flag, 
            boolean flag1)
    {
        super.playAnim(l, l1, l2, flag, flag1);
        int i = mElements.size();
        for(int j = 0; j < i; j++)
        {
            if(mIndexVar != null)
                mIndexVar.set(j);
            ((ScreenElement)mElements.get(j)).playAnim(l, l1, l2, flag, flag1);
        }

    }

    public void removeAllElements()
    {
        mElements.clear();
        requestUpdate();
    }

    public void removeElement(ScreenElement screenelement)
    {
        mElements.remove(screenelement);
        requestUpdate();
    }

    public void reset(long l)
    {
        super.reset(l);
        int i = mElements.size();
        for(int j = 0; j < i; j++)
            ((ScreenElement)mElements.get(j)).reset(l);

    }

    public void resume()
    {
        super.resume();
        int i = mElements.size();
        for(int j = 0; j < i; j++)
            ((ScreenElement)mElements.get(j)).resume();

    }

    public void resumeAnim(long l)
    {
        super.resumeAnim(l);
        int i = mElements.size();
        for(int j = 0; j < i; j++)
            ((ScreenElement)mElements.get(j)).resumeAnim(l);

    }

    public void setAnim(String as[])
    {
        super.setAnim(as);
        int i = mElements.size();
        for(int j = 0; j < i; j++)
        {
            if(mIndexVar != null)
                mIndexVar.set(j);
            ((ScreenElement)mElements.get(j)).setAnim(as);
        }

    }

    public void setClip(boolean flag)
    {
        mClip = flag;
    }

    public void showCategory(String s, boolean flag)
    {
        super.showCategory(s, flag);
        int i = mElements.size();
        for(int j = 0; j < i; j++)
            ((ScreenElement)mElements.get(j)).showCategory(s, flag);

    }

    private static final int _2D_miui_2D_maml_2D_elements_2D_ElementGroup$LinearDirectionSwitchesValues[];
    private static final String LOG_TAG = "MAML_ElementGroup";
    public static final String TAG_NAME = "ElementGroup";
    public static final String TAG_NAME1 = "Group";
    protected boolean mClip;
    protected ArrayList mElements;
    private boolean mHovered;
    private IndexedVariable mIndexVar;
    private boolean mLayered;
    private LinearDirection mLinearDirection;
    private boolean mTouched;
}
