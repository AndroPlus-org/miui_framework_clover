// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import android.util.Log;
import miui.maml.ScreenElementRoot;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            AnimatedScreenElement, ScreenElement

public class MirrorScreenElement extends AnimatedScreenElement
{

    public MirrorScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mTargetName = element.getAttribute("target");
        mMirrorTranslation = Boolean.parseBoolean(element.getAttribute("mirrorTranslation"));
    }

    protected void doRender(Canvas canvas)
    {
        if(mTarget != null)
            if(mMirrorTranslation && (mTarget instanceof AnimatedScreenElement))
                ((AnimatedScreenElement)mTarget).doRenderWithTranslation(canvas);
            else
                mTarget.doRender(canvas);
    }

    public void init()
    {
        super.init();
        mTarget = mRoot.findElement(mTargetName);
        if(mTarget == null)
            Log.e("MirrorScreenElement", (new StringBuilder()).append("the target does not exist: ").append(mTargetName).toString());
    }

    private static final String LOG_TAG = "MirrorScreenElement";
    public static final String TAG_NAME = "Mirror";
    private boolean mMirrorTranslation;
    private ScreenElement mTarget;
    private String mTargetName;
}
