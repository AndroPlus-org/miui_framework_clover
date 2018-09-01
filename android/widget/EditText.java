// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.widget:
//            TextView

public class EditText extends TextView
{

    public EditText(Context context)
    {
        this(context, null);
    }

    public EditText(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101006e);
    }

    public EditText(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public EditText(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    public void extendSelection(int i)
    {
        Selection.extendSelection(getText(), i);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/EditText.getName();
    }

    protected boolean getDefaultEditable()
    {
        return true;
    }

    protected MovementMethod getDefaultMovementMethod()
    {
        return ArrowKeyMovementMethod.getInstance();
    }

    public boolean getFreezesText()
    {
        return true;
    }

    public Editable getText()
    {
        return (Editable)super.getText();
    }

    public volatile CharSequence getText()
    {
        return getText();
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(isEnabled())
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_TEXT);
    }

    public void selectAll()
    {
        Selection.selectAll(getText());
    }

    public void setEllipsize(android.text.TextUtils.TruncateAt truncateat)
    {
        if(truncateat == android.text.TextUtils.TruncateAt.MARQUEE)
        {
            throw new IllegalArgumentException("EditText cannot use the ellipsize mode TextUtils.TruncateAt.MARQUEE");
        } else
        {
            super.setEllipsize(truncateat);
            return;
        }
    }

    public void setSelection(int i)
    {
        Selection.setSelection(getText(), i);
    }

    public void setSelection(int i, int j)
    {
        Selection.setSelection(getText(), i, j);
    }

    public void setText(CharSequence charsequence, TextView.BufferType buffertype)
    {
        super.setText(charsequence, TextView.BufferType.EDITABLE);
    }

    protected boolean supportsAutoSizeText()
    {
        return false;
    }
}
