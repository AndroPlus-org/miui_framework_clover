// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;
import miui.maml.util.FilenameExtFilter;
import miui.maml.util.TextFormatter;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.data:
//            VariableBinder, Expression, IndexedVariable, Variables

public class FileBinder extends VariableBinder
{
    private static class Variable extends VariableBinder.Variable
    {

        protected int parseType(String s)
        {
            return 2;
        }

        public Expression mIndex;

        public Variable(Element element, Variables variables)
        {
            super(element, variables);
            mIndex = Expression.build(variables, element.getAttribute("index"));
            if(mIndex == null)
                Log.e("Variable", "fail to load file index expression");
        }
    }


    public FileBinder(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mVariables = new ArrayList();
        load(element);
    }

    private void load(Element element)
    {
        Object obj = null;
        if(element == null)
        {
            Log.e("FileBinder", "FileBinder node is null");
            return;
        }
        String s = element.getAttribute("filter").trim();
        if(!TextUtils.isEmpty(s))
            obj = s.split(",");
        mFilters = ((String []) (obj));
        obj = Expression.build(getVariables(), element.getAttribute("dirExp"));
        mDirFormatter = new TextFormatter(getVariables(), element.getAttribute("dir"), ((Expression) (obj)));
        if(!TextUtils.isEmpty(mName))
            mCountVar = new IndexedVariable((new StringBuilder()).append(mName).append(".count").toString(), getContext().mVariables, true);
        loadVariables(element);
    }

    private void updateVariables()
    {
        int i;
        Iterator iterator;
        if(mFiles == null)
            i = 0;
        else
            i = mFiles.length;
        iterator = mVariables.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Variable variable = (Variable)iterator.next();
            if(variable.mIndex != null)
            {
                int j = (int)variable.mIndex.evaluate();
                String s;
                if(i == 0)
                    s = null;
                else
                    s = mFiles[j % i];
                variable.set(s);
            }
        } while(true);
    }

    protected void addVariable(Variable variable)
    {
        mVariables.add(variable);
    }

    public void init()
    {
        super.init();
        refresh();
    }

    protected Variable onLoadVariable(Element element)
    {
        return new Variable(element, getVariables());
    }

    protected volatile VariableBinder.Variable onLoadVariable(Element element)
    {
        return onLoadVariable(element);
    }

    public void refresh()
    {
        super.refresh();
        String as[] = new File(mDirFormatter.getText());
        int i;
        if(mFilters == null)
            as = as.list();
        else
            as = as.list(new FilenameExtFilter(mFilters));
        mFiles = as;
        if(mFiles == null)
            i = 0;
        else
            i = mFiles.length;
        if(mCountVar != null)
            mCountVar.set(i);
        Log.i("FileBinder", (new StringBuilder()).append("file count: ").append(i).toString());
        updateVariables();
    }

    public void tick()
    {
        super.tick();
        updateVariables();
    }

    private static final boolean DBG = false;
    private static final String LOG_TAG = "FileBinder";
    public static final String TAG_NAME = "FileBinder";
    private IndexedVariable mCountVar;
    protected TextFormatter mDirFormatter;
    private String mFiles[];
    private String mFilters[];
    private ArrayList mVariables;
}
