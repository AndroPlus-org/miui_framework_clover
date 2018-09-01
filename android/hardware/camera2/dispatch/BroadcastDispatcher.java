// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.dispatch;

import com.android.internal.util.Preconditions;
import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package android.hardware.camera2.dispatch:
//            Dispatchable

public class BroadcastDispatcher
    implements Dispatchable
{

    public transient BroadcastDispatcher(Dispatchable adispatchable[])
    {
        mDispatchTargets = Arrays.asList((Dispatchable[])Preconditions.checkNotNull(adispatchable, "dispatchTargets must not be null"));
    }

    public Object dispatch(Method method, Object aobj[])
        throws Throwable
    {
        Object obj = null;
        boolean flag = false;
        Iterator iterator = mDispatchTargets.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Object obj1 = ((Dispatchable)iterator.next()).dispatch(method, aobj);
            if(!flag)
            {
                flag = true;
                obj = obj1;
            }
        } while(true);
        return obj;
    }

    private final List mDispatchTargets;
}
