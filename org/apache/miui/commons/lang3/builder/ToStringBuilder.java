// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.builder;

import org.apache.miui.commons.lang3.ObjectUtils;

// Referenced classes of package org.apache.miui.commons.lang3.builder:
//            Builder, ToStringStyle, ReflectionToStringBuilder

public class ToStringBuilder
    implements Builder
{

    public ToStringBuilder(Object obj)
    {
        this(obj, null, null);
    }

    public ToStringBuilder(Object obj, ToStringStyle tostringstyle)
    {
        this(obj, tostringstyle, null);
    }

    public ToStringBuilder(Object obj, ToStringStyle tostringstyle, StringBuffer stringbuffer)
    {
        ToStringStyle tostringstyle1 = tostringstyle;
        if(tostringstyle == null)
            tostringstyle1 = getDefaultStyle();
        tostringstyle = stringbuffer;
        if(stringbuffer == null)
            tostringstyle = new StringBuffer(512);
        buffer = tostringstyle;
        style = tostringstyle1;
        object = obj;
        tostringstyle1.appendStart(tostringstyle, obj);
    }

    public static ToStringStyle getDefaultStyle()
    {
        return defaultStyle;
    }

    public static String reflectionToString(Object obj)
    {
        return ReflectionToStringBuilder.toString(obj);
    }

    public static String reflectionToString(Object obj, ToStringStyle tostringstyle)
    {
        return ReflectionToStringBuilder.toString(obj, tostringstyle);
    }

    public static String reflectionToString(Object obj, ToStringStyle tostringstyle, boolean flag)
    {
        return ReflectionToStringBuilder.toString(obj, tostringstyle, flag, false, null);
    }

    public static String reflectionToString(Object obj, ToStringStyle tostringstyle, boolean flag, Class class1)
    {
        return ReflectionToStringBuilder.toString(obj, tostringstyle, flag, false, class1);
    }

    public static void setDefaultStyle(ToStringStyle tostringstyle)
    {
        if(tostringstyle == null)
        {
            throw new IllegalArgumentException("The style must not be null");
        } else
        {
            defaultStyle = tostringstyle;
            return;
        }
    }

    public ToStringBuilder append(byte byte0)
    {
        style.append(buffer, null, byte0);
        return this;
    }

    public ToStringBuilder append(char c)
    {
        style.append(buffer, null, c);
        return this;
    }

    public ToStringBuilder append(double d)
    {
        style.append(buffer, null, d);
        return this;
    }

    public ToStringBuilder append(float f)
    {
        style.append(buffer, null, f);
        return this;
    }

    public ToStringBuilder append(int i)
    {
        style.append(buffer, null, i);
        return this;
    }

    public ToStringBuilder append(long l)
    {
        style.append(buffer, null, l);
        return this;
    }

    public ToStringBuilder append(Object obj)
    {
        style.append(buffer, null, obj, null);
        return this;
    }

    public ToStringBuilder append(String s, byte byte0)
    {
        style.append(buffer, s, byte0);
        return this;
    }

    public ToStringBuilder append(String s, char c)
    {
        style.append(buffer, s, c);
        return this;
    }

    public ToStringBuilder append(String s, double d)
    {
        style.append(buffer, s, d);
        return this;
    }

    public ToStringBuilder append(String s, float f)
    {
        style.append(buffer, s, f);
        return this;
    }

    public ToStringBuilder append(String s, int i)
    {
        style.append(buffer, s, i);
        return this;
    }

    public ToStringBuilder append(String s, long l)
    {
        style.append(buffer, s, l);
        return this;
    }

    public ToStringBuilder append(String s, Object obj)
    {
        style.append(buffer, s, obj, null);
        return this;
    }

    public ToStringBuilder append(String s, Object obj, boolean flag)
    {
        style.append(buffer, s, obj, Boolean.valueOf(flag));
        return this;
    }

    public ToStringBuilder append(String s, short word0)
    {
        style.append(buffer, s, word0);
        return this;
    }

    public ToStringBuilder append(String s, boolean flag)
    {
        style.append(buffer, s, flag);
        return this;
    }

    public ToStringBuilder append(String s, byte abyte0[])
    {
        style.append(buffer, s, abyte0, null);
        return this;
    }

    public ToStringBuilder append(String s, byte abyte0[], boolean flag)
    {
        style.append(buffer, s, abyte0, Boolean.valueOf(flag));
        return this;
    }

    public ToStringBuilder append(String s, char ac[])
    {
        style.append(buffer, s, ac, null);
        return this;
    }

    public ToStringBuilder append(String s, char ac[], boolean flag)
    {
        style.append(buffer, s, ac, Boolean.valueOf(flag));
        return this;
    }

    public ToStringBuilder append(String s, double ad[])
    {
        style.append(buffer, s, ad, null);
        return this;
    }

    public ToStringBuilder append(String s, double ad[], boolean flag)
    {
        style.append(buffer, s, ad, Boolean.valueOf(flag));
        return this;
    }

    public ToStringBuilder append(String s, float af[])
    {
        style.append(buffer, s, af, null);
        return this;
    }

    public ToStringBuilder append(String s, float af[], boolean flag)
    {
        style.append(buffer, s, af, Boolean.valueOf(flag));
        return this;
    }

    public ToStringBuilder append(String s, int ai[])
    {
        style.append(buffer, s, ai, null);
        return this;
    }

    public ToStringBuilder append(String s, int ai[], boolean flag)
    {
        style.append(buffer, s, ai, Boolean.valueOf(flag));
        return this;
    }

    public ToStringBuilder append(String s, long al[])
    {
        style.append(buffer, s, al, null);
        return this;
    }

    public ToStringBuilder append(String s, long al[], boolean flag)
    {
        style.append(buffer, s, al, Boolean.valueOf(flag));
        return this;
    }

    public ToStringBuilder append(String s, Object aobj[])
    {
        style.append(buffer, s, aobj, null);
        return this;
    }

    public ToStringBuilder append(String s, Object aobj[], boolean flag)
    {
        style.append(buffer, s, aobj, Boolean.valueOf(flag));
        return this;
    }

    public ToStringBuilder append(String s, short aword0[])
    {
        style.append(buffer, s, aword0, null);
        return this;
    }

    public ToStringBuilder append(String s, short aword0[], boolean flag)
    {
        style.append(buffer, s, aword0, Boolean.valueOf(flag));
        return this;
    }

    public ToStringBuilder append(String s, boolean aflag[])
    {
        style.append(buffer, s, aflag, null);
        return this;
    }

    public ToStringBuilder append(String s, boolean aflag[], boolean flag)
    {
        style.append(buffer, s, aflag, Boolean.valueOf(flag));
        return this;
    }

    public ToStringBuilder append(short word0)
    {
        style.append(buffer, null, word0);
        return this;
    }

    public ToStringBuilder append(boolean flag)
    {
        style.append(buffer, null, flag);
        return this;
    }

    public ToStringBuilder append(byte abyte0[])
    {
        style.append(buffer, null, abyte0, null);
        return this;
    }

    public ToStringBuilder append(char ac[])
    {
        style.append(buffer, null, ac, null);
        return this;
    }

    public ToStringBuilder append(double ad[])
    {
        style.append(buffer, null, ad, null);
        return this;
    }

    public ToStringBuilder append(float af[])
    {
        style.append(buffer, null, af, null);
        return this;
    }

    public ToStringBuilder append(int ai[])
    {
        style.append(buffer, null, ai, null);
        return this;
    }

    public ToStringBuilder append(long al[])
    {
        style.append(buffer, null, al, null);
        return this;
    }

    public ToStringBuilder append(Object aobj[])
    {
        style.append(buffer, null, aobj, null);
        return this;
    }

    public ToStringBuilder append(short aword0[])
    {
        style.append(buffer, null, aword0, null);
        return this;
    }

    public ToStringBuilder append(boolean aflag[])
    {
        style.append(buffer, null, aflag, null);
        return this;
    }

    public ToStringBuilder appendAsObjectToString(Object obj)
    {
        ObjectUtils.identityToString(getStringBuffer(), obj);
        return this;
    }

    public ToStringBuilder appendSuper(String s)
    {
        if(s != null)
            style.appendSuper(buffer, s);
        return this;
    }

    public ToStringBuilder appendToString(String s)
    {
        if(s != null)
            style.appendToString(buffer, s);
        return this;
    }

    public volatile Object build()
    {
        return build();
    }

    public String build()
    {
        return toString();
    }

    public Object getObject()
    {
        return object;
    }

    public StringBuffer getStringBuffer()
    {
        return buffer;
    }

    public ToStringStyle getStyle()
    {
        return style;
    }

    public String toString()
    {
        if(getObject() == null)
            getStringBuffer().append(getStyle().getNullText());
        else
            style.appendEnd(getStringBuffer(), getObject());
        return getStringBuffer().toString();
    }

    private static volatile ToStringStyle defaultStyle;
    private final StringBuffer buffer;
    private final Object object;
    private final ToStringStyle style;

    static 
    {
        defaultStyle = ToStringStyle.DEFAULT_STYLE;
    }
}
