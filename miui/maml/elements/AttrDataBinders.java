// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import miui.maml.data.ContextVariables;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ElementGroup, ScreenElement, ImageScreenElement, TextScreenElement

public class AttrDataBinders
{
    public static class AttrDataBinder
    {

        private Binder createBinder(String s)
        {
            if(TextUtils.isEmpty(s))
                return null;
            if("text".equals(s))
                return new TextBinder(null);
            if("paras".equals(s) || "params".equals(s))
                return new ParamsBinder(null);
            if("name".equals(s))
                return new NameBinder(null);
            if("bitmap".equals(s))
                return new BitmapBinder(null);
            if("src".equals(s))
                return new SrcBinder(null);
            if("srcid".equals(s))
                return new SrcIdBinder(null);
            else
                return null;
        }

        public boolean bind(ElementGroup elementgroup)
        {
            elementgroup = elementgroup.findElement(mTarget);
            if(elementgroup == null)
                break MISSING_BLOCK_LABEL_28;
            mBinder.bind(elementgroup);
            return true;
            elementgroup;
            elementgroup.printStackTrace();
            return false;
        }

        protected String mAttr;
        private Binder mBinder;
        protected String mData;
        protected String mTarget;
        protected ContextVariables mVars;

        public AttrDataBinder(Element element, ContextVariables contextvariables)
        {
            mTarget = element.getAttribute("target");
            mAttr = element.getAttribute("attr");
            mData = element.getAttribute("data");
            mVars = contextvariables;
            mBinder = createBinder(mAttr);
            if(TextUtils.isEmpty(mTarget) || TextUtils.isEmpty(mAttr) || TextUtils.isEmpty(mData) || mBinder == null)
                throw new IllegalArgumentException("invalid AttrDataBinder");
            else
                return;
        }
    }

    private abstract class AttrDataBinder.Binder
    {

        public abstract void bind(ScreenElement screenelement);

        final AttrDataBinder this$1;

        private AttrDataBinder.Binder()
        {
            this$1 = AttrDataBinder.this;
            super();
        }

        AttrDataBinder.Binder(AttrDataBinder.Binder binder)
        {
            this();
        }
    }

    private class AttrDataBinder.BitmapBinder extends AttrDataBinder.Binder
    {

        public void bind(ScreenElement screenelement)
        {
            ((ImageScreenElement)screenelement).setBitmap(mVars.getBmp(mData));
        }

        final AttrDataBinder this$1;

        private AttrDataBinder.BitmapBinder()
        {
            this$1 = AttrDataBinder.this;
            super(null);
        }

        AttrDataBinder.BitmapBinder(AttrDataBinder.BitmapBinder bitmapbinder)
        {
            this();
        }
    }

    private class AttrDataBinder.NameBinder extends AttrDataBinder.Binder
    {

        public void bind(ScreenElement screenelement)
        {
            screenelement.setName(mVars.getString(mData));
        }

        final AttrDataBinder this$1;

        private AttrDataBinder.NameBinder()
        {
            this$1 = AttrDataBinder.this;
            super(null);
        }

        AttrDataBinder.NameBinder(AttrDataBinder.NameBinder namebinder)
        {
            this();
        }
    }

    private class AttrDataBinder.ParamsBinder extends AttrDataBinder.Binder
    {

        public void bind(ScreenElement screenelement)
        {
            ((TextScreenElement)screenelement).setParams(new Object[] {
                mVars.getVar(mData)
            });
        }

        final AttrDataBinder this$1;

        private AttrDataBinder.ParamsBinder()
        {
            this$1 = AttrDataBinder.this;
            super(null);
        }

        AttrDataBinder.ParamsBinder(AttrDataBinder.ParamsBinder paramsbinder)
        {
            this();
        }
    }

    private class AttrDataBinder.SrcBinder extends AttrDataBinder.Binder
    {

        public void bind(ScreenElement screenelement)
        {
            ((ImageScreenElement)screenelement).setSrc(mVars.getString(mData));
        }

        final AttrDataBinder this$1;

        private AttrDataBinder.SrcBinder()
        {
            this$1 = AttrDataBinder.this;
            super(null);
        }

        AttrDataBinder.SrcBinder(AttrDataBinder.SrcBinder srcbinder)
        {
            this();
        }
    }

    private class AttrDataBinder.SrcIdBinder extends AttrDataBinder.Binder
    {

        public void bind(ScreenElement screenelement)
        {
            Double double1 = mVars.getDouble(mData);
            screenelement = (ImageScreenElement)screenelement;
            double d;
            if(double1 == null)
                d = 0.0D;
            else
                d = double1.doubleValue();
            screenelement.setSrcId(d);
        }

        final AttrDataBinder this$1;

        private AttrDataBinder.SrcIdBinder()
        {
            this$1 = AttrDataBinder.this;
            super(null);
        }

        AttrDataBinder.SrcIdBinder(AttrDataBinder.SrcIdBinder srcidbinder)
        {
            this();
        }
    }

    private class AttrDataBinder.TextBinder extends AttrDataBinder.Binder
    {

        public void bind(ScreenElement screenelement)
        {
            ((TextScreenElement)screenelement).setText(mVars.getString(mData));
        }

        final AttrDataBinder this$1;

        private AttrDataBinder.TextBinder()
        {
            this$1 = AttrDataBinder.this;
            super(null);
        }

        AttrDataBinder.TextBinder(AttrDataBinder.TextBinder textbinder)
        {
            this();
        }
    }


    static ArrayList _2D_get0(AttrDataBinders attrdatabinders)
    {
        return attrdatabinders.mBinders;
    }

    public AttrDataBinders(Element element, ContextVariables contextvariables)
    {
        mBinders = new ArrayList();
        mVars = contextvariables;
        Utils.traverseXmlElementChildren(element, "AttrDataBinder", new miui.maml.util.Utils.XmlTraverseListener() {

            public void onChild(Element element1)
            {
                ArrayList arraylist = AttrDataBinders._2D_get0(AttrDataBinders.this);
                AttrDataBinder attrdatabinder = JVM INSTR new #30  <Class AttrDataBinders$AttrDataBinder>;
                attrdatabinder.AttrDataBinder(element1, mVars);
                arraylist.add(attrdatabinder);
_L1:
                return;
                element1;
                Log.e("AttrDataBinders", element1.toString());
                  goto _L1
            }

            final AttrDataBinders this$0;

            
            {
                this$0 = AttrDataBinders.this;
                super();
            }
        }
);
    }

    public void bind(ElementGroup elementgroup)
    {
        for(Iterator iterator = mBinders.iterator(); iterator.hasNext(); ((AttrDataBinder)iterator.next()).bind(elementgroup));
    }

    private static final String ATTR_BITMAP = "bitmap";
    private static final String ATTR_NAME = "name";
    private static final String ATTR_PARAMS = "params";
    private static final String ATTR_PARAS = "paras";
    private static final String ATTR_SRC = "src";
    private static final String ATTR_SRCID = "srcid";
    private static final String ATTR_TEXT = "text";
    private static final String LOG_TAG = "AttrDataBinders";
    public static final String TAG = "AttrDataBinders";
    private ArrayList mBinders;
    protected ContextVariables mVars;
}
