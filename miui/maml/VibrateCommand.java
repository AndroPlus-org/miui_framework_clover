// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import miui.maml.elements.ScreenElement;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml:
//            ActionCommand

public class VibrateCommand extends ActionCommand
{

    public VibrateCommand(ScreenElement screenelement, Element element)
    {
        super(screenelement);
    }

    protected void doPerform()
    {
    }

    private static final String LOG_TAG = "VibrateCommand";
    public static final String TAG_NAME = "VibrateCommand";
}
