// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.dispatch;

import com.android.internal.util.Preconditions;
import java.lang.reflect.Method;

// Referenced classes of package android.hardware.camera2.dispatch:
//            Dispatchable, MethodNameInvoker

public class DuckTypingDispatcher
    implements Dispatchable
{

    public DuckTypingDispatcher(Dispatchable dispatchable, Class class1)
    {
        Preconditions.checkNotNull(class1, "targetClass must not be null");
        Preconditions.checkNotNull(dispatchable, "target must not be null");
        mDuck = new MethodNameInvoker(dispatchable, class1);
    }

    public Object dispatch(Method method, Object aobj[])
    {
        return mDuck.invoke(method.getName(), aobj);
    }

    private final MethodNameInvoker mDuck;
}
