// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import java.util.ArrayList;
import java.util.Iterator;
import miui.maml.elements.ScreenElement;
import org.w3c.dom.*;

// Referenced classes of package miui.maml:
//            CommandTrigger

public class CommandTriggers
{

    public CommandTriggers(Element element, ScreenElement screenelement)
    {
        mTriggers = new ArrayList();
        if(element != null)
            load(element, screenelement);
    }

    private void load(Element element, ScreenElement screenelement)
    {
        element = element.getChildNodes();
        int i = 0;
        while(i < element.getLength()) 
        {
            if(element.item(i).getNodeType() == 1)
            {
                Element element1 = (Element)element.item(i);
                if(element1.getNodeName().equals("Trigger"))
                    mTriggers.add(new CommandTrigger(element1, screenelement));
            }
            i++;
        }
    }

    public void add(CommandTrigger commandtrigger)
    {
        mTriggers.add(commandtrigger);
    }

    public CommandTrigger find(String s)
    {
        for(Iterator iterator = mTriggers.iterator(); iterator.hasNext();)
        {
            CommandTrigger commandtrigger = (CommandTrigger)iterator.next();
            if(commandtrigger.isAction(s))
                return commandtrigger;
        }

        return null;
    }

    public void finish()
    {
        for(Iterator iterator = mTriggers.iterator(); iterator.hasNext(); ((CommandTrigger)iterator.next()).finish());
    }

    public void init()
    {
        for(Iterator iterator = mTriggers.iterator(); iterator.hasNext(); ((CommandTrigger)iterator.next()).init());
    }

    public void onAction(String s)
    {
        for(Iterator iterator = mTriggers.iterator(); iterator.hasNext(); ((CommandTrigger)iterator.next()).onAction(s));
    }

    public void pause()
    {
        for(Iterator iterator = mTriggers.iterator(); iterator.hasNext(); ((CommandTrigger)iterator.next()).pause());
    }

    public void resume()
    {
        for(Iterator iterator = mTriggers.iterator(); iterator.hasNext(); ((CommandTrigger)iterator.next()).resume());
    }

    private static final String LOG_TAG = "CommandTriggers";
    public static final String TAG_NAME = "Triggers";
    private ArrayList mTriggers;
}
