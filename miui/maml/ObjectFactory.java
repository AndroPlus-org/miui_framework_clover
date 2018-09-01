// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import miui.maml.elements.BitmapProvider;
import miui.maml.elements.ScreenElement;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml:
//            ActionCommand, ScreenElementRoot

public abstract class ObjectFactory
{
    public static abstract class ActionCommandFactory extends ObjectFactoryBase
    {

        public final ActionCommand create(ScreenElement screenelement, Element element)
        {
            Object obj = null;
            ActionCommand actioncommand = doCreate(screenelement, element);
            if(actioncommand != null)
                return actioncommand;
            if(mOld == null)
                screenelement = obj;
            else
                screenelement = ((ActionCommandFactory)mOld).create(screenelement, element);
            return screenelement;
        }

        protected abstract ActionCommand doCreate(ScreenElement screenelement, Element element);

        public static final String NAME = "ActionCommand";

        protected ActionCommandFactory()
        {
            super("ActionCommand");
        }
    }

    public static abstract class BitmapProviderFactory extends ObjectFactoryBase
    {

        public final BitmapProvider create(ScreenElementRoot screenelementroot, String s)
        {
            Object obj = null;
            BitmapProvider bitmapprovider = doCreate(screenelementroot, s);
            if(bitmapprovider != null)
                return bitmapprovider;
            if(mOld == null)
                screenelementroot = obj;
            else
                screenelementroot = ((BitmapProviderFactory)mOld).create(screenelementroot, s);
            return screenelementroot;
        }

        protected abstract BitmapProvider doCreate(ScreenElementRoot screenelementroot, String s);

        public static final String NAME = "BitmapProvider";

        protected BitmapProviderFactory()
        {
            super("BitmapProvider");
        }
    }

    public static abstract class ObjectFactoryBase extends ObjectFactory
    {

        public String getName()
        {
            return mName;
        }

        public ObjectFactory getOld()
        {
            return mOld;
        }

        public void setOld(ObjectFactory objectfactory)
        {
            mOld = objectfactory;
        }

        private String mName;
        protected ObjectFactory mOld;

        protected ObjectFactoryBase(String s)
        {
            mName = s;
        }
    }


    public ObjectFactory()
    {
    }

    public abstract String getName();

    public abstract ObjectFactory getOld();

    public abstract void setOld(ObjectFactory objectfactory);
}
