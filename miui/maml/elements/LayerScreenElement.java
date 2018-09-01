// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import miui.maml.*;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ViewHolderScreenElement

public class LayerScreenElement extends ViewHolderScreenElement
{
    private class LayerView extends View
    {

        protected void onDraw(Canvas canvas)
        {
            doRender(canvas);
            mController.doneRender();
        }

        final LayerScreenElement this$0;

        public LayerView(Context context)
        {
            this$0 = LayerScreenElement.this;
            super(context);
        }
    }


    public LayerScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mView = new LayerView(screenelementroot.getContext().mContext);
    }

    protected View getView()
    {
        return mView;
    }

    public static final String TAG_NAME = "Layer";
    private LayerView mView;
}
