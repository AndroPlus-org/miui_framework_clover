// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.IntArray;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.internal.widget.ExploreByTouchHelper;
import java.util.List;
import miui.maml.ScreenElementRoot;
import miui.maml.elements.AnimatedScreenElement;

public class MamlAccessHelper extends ExploreByTouchHelper
{

    public MamlAccessHelper(ScreenElementRoot screenelementroot, View view)
    {
        super(view);
        mRoot = null;
        mRoot = screenelementroot;
    }

    protected int getVirtualViewAt(float f, float f1)
    {
        List list = mRoot.getAccessibleElements();
        for(int i = list.size() - 1; i >= 0; i--)
        {
            AnimatedScreenElement animatedscreenelement = (AnimatedScreenElement)list.get(i);
            if(animatedscreenelement.isVisible() && animatedscreenelement.touched(f, f1))
                return i;
        }

        return 0x80000000;
    }

    protected void getVisibleVirtualViews(IntArray intarray)
    {
        List list = mRoot.getAccessibleElements();
        for(int i = 0; i < list.size(); i++)
            if(((AnimatedScreenElement)list.get(i)).isVisible())
                intarray.add(i);

    }

    protected boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
    {
        j;
        JVM INSTR tableswitch 16 16: default 20
    //                   16 22;
           goto _L1 _L2
_L1:
        return false;
_L2:
        bundle = mRoot.getAccessibleElements();
        if(i >= 0 && i < bundle.size())
        {
            ((AnimatedScreenElement)bundle.get(i)).performAction("up");
            return true;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent)
    {
        List list = mRoot.getAccessibleElements();
        if(i >= 0 && i < list.size())
            accessibilityevent.setContentDescription(((AnimatedScreenElement)list.get(i)).getContentDescription());
    }

    protected void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfo accessibilitynodeinfo)
    {
        Object obj = mRoot.getAccessibleElements();
        if(i >= 0 && i < ((List) (obj)).size())
        {
            AnimatedScreenElement animatedscreenelement = (AnimatedScreenElement)((List) (obj)).get(i);
            String s = animatedscreenelement.getContentDescription();
            obj = s;
            if(s == null)
            {
                obj = "";
                Log.e("MamlAccessHelper", (new StringBuilder()).append("element.getContentDescription() == null ").append(animatedscreenelement.getName()).toString());
            }
            accessibilitynodeinfo.setContentDescription(((CharSequence) (obj)));
            accessibilitynodeinfo.addAction(16);
            accessibilitynodeinfo.setBoundsInParent(new Rect((int)animatedscreenelement.getAbsoluteLeft(), (int)animatedscreenelement.getAbsoluteTop(), (int)(animatedscreenelement.getAbsoluteLeft() + animatedscreenelement.getWidth()), (int)(animatedscreenelement.getAbsoluteTop() + animatedscreenelement.getHeight())));
        } else
        {
            Log.e("MamlAccessHelper", (new StringBuilder()).append("virtualViewId not found ").append(i).toString());
            accessibilitynodeinfo.setContentDescription("");
            accessibilitynodeinfo.setBoundsInParent(new Rect(0, 0, 0, 0));
        }
    }

    private static final String TAG = "MamlAccessHelper";
    ScreenElementRoot mRoot;
}
