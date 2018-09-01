// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.dispatch;

import android.hardware.camera2.utils.UncheckedThrow;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package android.hardware.camera2.dispatch:
//            Dispatchable

public class InvokeDispatcher
    implements Dispatchable
{

    public InvokeDispatcher(Object obj)
    {
        mTarget = Preconditions.checkNotNull(obj, "target must not be null");
    }

    public Object dispatch(Method method, Object aobj[])
    {
        aobj = ((Object []) (method.invoke(mTarget, aobj)));
        return ((Object) (aobj));
        aobj;
        Log.wtf("InvocationSink", (new StringBuilder()).append("IllegalArgumentException while invoking ").append(method).toString(), ((Throwable) (aobj)));
_L2:
        return null;
        aobj;
        Log.wtf("InvocationSink", (new StringBuilder()).append("IllegalAccessException while invoking ").append(method).toString(), ((Throwable) (aobj)));
        continue; /* Loop/switch isn't completed */
        method;
        UncheckedThrow.throwAnyException(method.getTargetException());
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String TAG = "InvocationSink";
    private final Object mTarget;
}
