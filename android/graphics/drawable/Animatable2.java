// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;


// Referenced classes of package android.graphics.drawable:
//            Animatable, Drawable

public interface Animatable2
    extends Animatable
{
    public static abstract class AnimationCallback
    {

        public void onAnimationEnd(Drawable drawable)
        {
        }

        public void onAnimationStart(Drawable drawable)
        {
        }

        public AnimationCallback()
        {
        }
    }


    public abstract void clearAnimationCallbacks();

    public abstract void registerAnimationCallback(AnimationCallback animationcallback);

    public abstract boolean unregisterAnimationCallback(AnimationCallback animationcallback);
}
