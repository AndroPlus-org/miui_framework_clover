// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;


// Referenced classes of package android.view:
//            InputDevice

public class SearchEvent
{

    public SearchEvent(InputDevice inputdevice)
    {
        mInputDevice = inputdevice;
    }

    public InputDevice getInputDevice()
    {
        return mInputDevice;
    }

    private InputDevice mInputDevice;
}
