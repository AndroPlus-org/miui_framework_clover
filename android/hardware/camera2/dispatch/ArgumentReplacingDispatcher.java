// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.dispatch;

import com.android.internal.util.Preconditions;
import java.lang.reflect.Method;

// Referenced classes of package android.hardware.camera2.dispatch:
//            Dispatchable

public class ArgumentReplacingDispatcher
    implements Dispatchable
{

    public ArgumentReplacingDispatcher(Dispatchable dispatchable, int i, Object obj)
    {
        mTarget = (Dispatchable)Preconditions.checkNotNull(dispatchable, "target must not be null");
        mArgumentIndex = Preconditions.checkArgumentNonnegative(i, "argumentIndex must not be negative");
        mReplaceWith = Preconditions.checkNotNull(obj, "replaceWith must not be null");
    }

    private static Object[] arrayCopy(Object aobj[])
    {
        int i = aobj.length;
        Object aobj1[] = new Object[i];
        for(int j = 0; j < i; j++)
            aobj1[j] = aobj[j];

        return aobj1;
    }

    public Object dispatch(Method method, Object aobj[])
        throws Throwable
    {
        Object aobj1[] = aobj;
        if(aobj.length > mArgumentIndex)
        {
            aobj1 = arrayCopy(aobj);
            aobj1[mArgumentIndex] = mReplaceWith;
        }
        return mTarget.dispatch(method, aobj1);
    }

    private final int mArgumentIndex;
    private final Object mReplaceWith;
    private final Dispatchable mTarget;
}
