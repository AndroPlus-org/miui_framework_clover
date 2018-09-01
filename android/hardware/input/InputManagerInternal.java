// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.input;

import android.hardware.display.DisplayViewport;
import android.view.InputEvent;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import java.util.List;

public abstract class InputManagerInternal
{

    public InputManagerInternal()
    {
    }

    public abstract boolean injectInputEvent(InputEvent inputevent, int i, int j);

    public abstract void onInputMethodSubtypeChanged(int i, InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype);

    public abstract void setDisplayViewports(DisplayViewport displayviewport, DisplayViewport displayviewport1, List list);

    public abstract void setInteractive(boolean flag);

    public abstract void setPulseGestureEnabled(boolean flag);

    public abstract void toggleCapsLock(int i);
}
