// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import android.util.Log;
import miui.maml.*;
import miui.maml.data.IndexedVariable;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            AnimatedScreenElement

public class CanvasDrawerElement extends AnimatedScreenElement
{

    public CanvasDrawerElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        if(mTriggers != null)
            mDrawCommands = mTriggers.find("draw");
        if(mDrawCommands == null)
            Log.e("CanvasDrawer", "no draw commands.");
        element = getVariables();
        mXVar = new IndexedVariable("__x", element, true);
        mYVar = new IndexedVariable("__y", element, true);
        mWVar = new IndexedVariable("__w", element, true);
        mHVar = new IndexedVariable("__h", element, true);
        mCanvasVar = new IndexedVariable("__objCanvas", getVariables(), false);
    }

    protected void doRender(Canvas canvas)
    {
        if(mDrawCommands != null)
        {
            float f = getWidthRaw();
            float f1 = getHeightRaw();
            float f2 = getLeft(0.0F, f);
            float f3 = getTop(0.0F, f1);
            mXVar.set(f2);
            mYVar.set(f3);
            mWVar.set(f);
            mHVar.set(f1);
            mCanvasVar.set(canvas);
            mDrawCommands.perform();
            mCanvasVar.set(null);
        }
    }

    public static final String TAG_NAME = "CanvasDrawer";
    private IndexedVariable mCanvasVar;
    private CommandTrigger mDrawCommands;
    private IndexedVariable mHVar;
    private IndexedVariable mWVar;
    private IndexedVariable mXVar;
    private IndexedVariable mYVar;
}
