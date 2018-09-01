// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import miui.util.ReflectionUtils;

// Referenced classes of package miui.log:
//            ILogTag

public final class AndroidTag
    implements ILogTag
{

    private AndroidTag(Class class1, String s, String s1, boolean flag)
    {
        boolean flag1 = false;
        super();
        className = s;
        fieldName = s1;
        fieldFullPath = buildFieldFullPath(s, s1);
        defaultOn = flag;
        isTagOn = flag;
        onNumber = 0;
        flag = flag1;
        if(class1 != null)
            flag = true;
        clazzLoaded = flag;
        clazz = class1;
    }

    AndroidTag(Class class1, String s, boolean flag)
    {
        this(class1, class1.getName(), s, flag);
    }

    AndroidTag(String s, String s1, boolean flag)
    {
        this(null, s, s1, flag);
    }

    static String buildFieldFullPath(Class class1, String s)
    {
        return buildFieldFullPath(class1.getName(), s);
    }

    static String buildFieldFullPath(String s, String s1)
    {
        return (new StringBuilder()).append(s).append(".").append(s1).toString();
    }

    private void loadClass()
    {
        if(clazz == null && !clazzLoaded)
        {
            clazz = ReflectionUtils.tryFindClass(className, BOOTCLASSLOADER);
            clazzLoaded = true;
        }
    }

    public boolean isOn()
    {
        return isTagOn;
    }

    public void switchOff()
    {
        this;
        JVM INSTR monitorenter ;
        loadClass();
        onNumber = onNumber - 1;
        if(onNumber != 0) goto _L2; else goto _L1
_L1:
        isTagOn = defaultOn;
        if(clazz != null)
            ReflectionUtils.trySetStaticObjectField(clazz, fieldName, Boolean.valueOf(isTagOn));
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(onNumber != -1) goto _L4; else goto _L3
_L3:
        isTagOn = false;
        if(clazz != null)
            ReflectionUtils.trySetStaticObjectField(clazz, fieldName, Boolean.valueOf(isTagOn));
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    public void switchOn()
    {
        this;
        JVM INSTR monitorenter ;
        loadClass();
        onNumber = onNumber + 1;
        if(onNumber != 0) goto _L2; else goto _L1
_L1:
        isTagOn = defaultOn;
        if(clazz != null)
            ReflectionUtils.trySetStaticObjectField(clazz, fieldName, Boolean.valueOf(isTagOn));
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(onNumber != 1) goto _L4; else goto _L3
_L3:
        isTagOn = true;
        if(clazz != null)
            ReflectionUtils.trySetStaticObjectField(clazz, fieldName, Boolean.valueOf(isTagOn));
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    static final ClassLoader BOOTCLASSLOADER = Thread.currentThread().getContextClassLoader();
    public final String className;
    private Class clazz;
    private boolean clazzLoaded;
    public final boolean defaultOn;
    public final String fieldFullPath;
    public final String fieldName;
    private boolean isTagOn;
    private int onNumber;

}
