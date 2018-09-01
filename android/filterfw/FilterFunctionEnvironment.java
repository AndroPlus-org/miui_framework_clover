// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterFactory;
import android.filterfw.core.FilterFunction;
import android.filterfw.core.FrameManager;

// Referenced classes of package android.filterfw:
//            MffEnvironment

public class FilterFunctionEnvironment extends MffEnvironment
{

    public FilterFunctionEnvironment()
    {
        super(null);
    }

    public FilterFunctionEnvironment(FrameManager framemanager)
    {
        super(framemanager);
    }

    public transient FilterFunction createFunction(Class class1, Object aobj[])
    {
        String s = (new StringBuilder()).append("FilterFunction(").append(class1.getSimpleName()).append(")").toString();
        class1 = FilterFactory.sharedFactory().createFilterByClass(class1, s);
        class1.initWithAssignmentList(aobj);
        return new FilterFunction(getContext(), class1);
    }
}
