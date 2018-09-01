// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.dispatch;

import android.hardware.camera2.utils.UncheckedThrow;
import com.android.internal.util.Preconditions;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package android.hardware.camera2.dispatch:
//            Dispatchable

public class MethodNameInvoker
{

    public MethodNameInvoker(Dispatchable dispatchable, Class class1)
    {
        mTargetClass = class1;
        mTargetClassMethods = class1.getMethods();
        mTarget = dispatchable;
    }

    public transient Object invoke(String s, Object aobj[])
    {
        Preconditions.checkNotNull(s, "methodName must not be null");
        Method method = (Method)mMethods.get(s);
        Method method1 = method;
        if(method == null)
        {
            Method amethod[] = mTargetClassMethods;
            int i = 0;
            int j = amethod.length;
label0:
            do
            {
label1:
                {
                    Method method2 = method;
                    if(i < j)
                    {
                        method1 = amethod[i];
                        if(!method1.getName().equals(s) || aobj.length != method1.getParameterTypes().length)
                            break label1;
                        method2 = method1;
                        mMethods.put(s, method1);
                    }
                    method1 = method2;
                    if(method2 == null)
                        throw new IllegalArgumentException((new StringBuilder()).append("Method ").append(s).append(" does not exist on class ").append(mTargetClass).toString());
                    break label0;
                }
                i++;
            } while(true);
        }
        try
        {
            s = ((String) (mTarget.dispatch(method1, aobj)));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            UncheckedThrow.throwAnyException(s);
            return null;
        }
        return s;
    }

    private final ConcurrentHashMap mMethods = new ConcurrentHashMap();
    private final Dispatchable mTarget;
    private final Class mTargetClass;
    private final Method mTargetClassMethods[];
}
