// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.effect;


// Referenced classes of package android.media.effect:
//            EffectUpdateListener

public abstract class Effect
{

    public Effect()
    {
    }

    public abstract void apply(int i, int j, int k, int l);

    public abstract String getName();

    public abstract void release();

    public abstract void setParameter(String s, Object obj);

    public void setUpdateListener(EffectUpdateListener effectupdatelistener)
    {
    }
}
