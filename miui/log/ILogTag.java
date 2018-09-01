// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;


public interface ILogTag
{

    public abstract boolean isOn();

    public abstract void switchOff();

    public abstract void switchOn();
}
