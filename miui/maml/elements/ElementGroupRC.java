// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import miui.maml.RendererController;
import miui.maml.ScreenElementRoot;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ElementGroup

public abstract class ElementGroupRC extends ElementGroup
{

    public ElementGroupRC(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mFrameRate = Utils.getAttrAsFloat(element, "frameRate", 0.0F);
        if(mFrameRate >= 0.0F)
        {
            mController = new RendererController();
            onControllerCreated(mController);
            mController.init();
        }
    }

    public RendererController getRendererController()
    {
        RendererController renderercontroller;
        if(mController != null)
            renderercontroller = mController;
        else
            renderercontroller = super.getRendererController();
        return renderercontroller;
    }

    public void init()
    {
        super.init();
        requestFramerate(mFrameRate);
    }

    protected abstract void onControllerCreated(RendererController renderercontroller);

    protected RendererController mController;
    private float mFrameRate;
}
