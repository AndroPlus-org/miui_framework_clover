// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import miui.maml.data.Expression;
import miui.maml.elements.ScreenElement;
import miui.maml.util.Utils;
import org.w3c.dom.*;

// Referenced classes of package miui.maml:
//            ActionCommand

public class CommandTrigger
{

    public CommandTrigger(Element element, ScreenElement screenelement)
    {
        mCommands = new ArrayList();
        if(element != null)
            load(element, screenelement);
    }

    public static CommandTrigger fromElement(Element element, ScreenElement screenelement)
    {
        Object obj = null;
        if(element == null)
            element = obj;
        else
            element = new CommandTrigger(element, screenelement);
        return element;
    }

    public static CommandTrigger fromParentElement(Element element, ScreenElement screenelement)
    {
        return fromElement(Utils.getChild(element, "Trigger"), screenelement);
    }

    private void load(Element element, ScreenElement screenelement)
    {
        mScreenElement = screenelement;
        String s = element.getAttribute("target");
        String s1 = element.getAttribute("property");
        String s2 = element.getAttribute("value");
        if(!TextUtils.isEmpty(s1) && TextUtils.isEmpty(s) ^ true && TextUtils.isEmpty(s2) ^ true)
            mPropertyCommand = ActionCommand.PropertyCommand.create(screenelement, (new StringBuilder()).append(s).append(".").append(s1).toString(), s2);
        mActionStrings = element.getAttribute("action").split(",");
        mCondition = Expression.build(mScreenElement.getVariables(), element.getAttribute("condition"));
        element = element.getChildNodes();
        for(int i = 0; i < element.getLength(); i++)
        {
            if(element.item(i).getNodeType() != 1)
                continue;
            ActionCommand actioncommand = ActionCommand.create((Element)element.item(i), screenelement);
            if(actioncommand != null)
                mCommands.add(actioncommand);
        }

    }

    public void finish()
    {
        if(mPropertyCommand != null)
            mPropertyCommand.finish();
        for(Iterator iterator = mCommands.iterator(); iterator.hasNext(); ((ActionCommand)iterator.next()).finish());
    }

    public void init()
    {
        if(mPropertyCommand != null)
            mPropertyCommand.init();
        for(Iterator iterator = mCommands.iterator(); iterator.hasNext(); ((ActionCommand)iterator.next()).init());
    }

    public boolean isAction(String s)
    {
        String as[] = mActionStrings;
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(as[j].equals(s))
                return true;

        return false;
    }

    public void onAction(String s)
    {
        if(isAction(s))
            perform();
    }

    public void pause()
    {
        if(mPropertyCommand != null)
            mPropertyCommand.pause();
        for(Iterator iterator = mCommands.iterator(); iterator.hasNext(); ((ActionCommand)iterator.next()).pause());
    }

    public void perform()
    {
        if(mCondition != null && mCondition.evaluate() <= 0.0D)
            return;
        if(mPropertyCommand != null)
            mPropertyCommand.perform();
        for(Iterator iterator = mCommands.iterator(); iterator.hasNext(); ((ActionCommand)iterator.next()).perform());
    }

    public void resume()
    {
        if(mPropertyCommand != null)
            mPropertyCommand.resume();
        for(Iterator iterator = mCommands.iterator(); iterator.hasNext(); ((ActionCommand)iterator.next()).resume());
    }

    public static final String TAG_NAME = "Trigger";
    private String mActionStrings[];
    private ArrayList mCommands;
    private Expression mCondition;
    private ActionCommand.PropertyCommand mPropertyCommand;
    private ScreenElement mScreenElement;
}
