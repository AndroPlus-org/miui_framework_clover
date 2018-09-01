// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;


// Referenced classes of package android.view:
//            MotionEvent

public final class VelocityTracker
{
    public static final class Estimator
    {

        private float estimate(float f, float af[])
        {
            float f1 = 0.0F;
            float f2 = 1.0F;
            for(int i = 0; i <= degree; i++)
            {
                f1 += af[i] * f2;
                f2 *= f;
            }

            return f1;
        }

        public float estimateX(float f)
        {
            return estimate(f, xCoeff);
        }

        public float estimateY(float f)
        {
            return estimate(f, yCoeff);
        }

        public float getXCoeff(int i)
        {
            float f;
            if(i <= degree)
                f = xCoeff[i];
            else
                f = 0.0F;
            return f;
        }

        public float getYCoeff(int i)
        {
            float f;
            if(i <= degree)
                f = yCoeff[i];
            else
                f = 0.0F;
            return f;
        }

        private static final int MAX_DEGREE = 4;
        public float confidence;
        public int degree;
        public final float xCoeff[] = new float[5];
        public final float yCoeff[] = new float[5];

        public Estimator()
        {
        }
    }


    private VelocityTracker(String s)
    {
        mPtr = nativeInitialize(s);
        mStrategy = s;
    }

    private static native void nativeAddMovement(long l, MotionEvent motionevent);

    private static native void nativeClear(long l);

    private static native void nativeComputeCurrentVelocity(long l, int i, float f);

    private static native void nativeDispose(long l);

    private static native boolean nativeGetEstimator(long l, int i, Estimator estimator);

    private static native float nativeGetXVelocity(long l, int i);

    private static native float nativeGetYVelocity(long l, int i);

    private static native long nativeInitialize(String s);

    public static VelocityTracker obtain()
    {
        VelocityTracker velocitytracker = (VelocityTracker)sPool.acquire();
        if(velocitytracker == null)
            velocitytracker = new VelocityTracker(null);
        return velocitytracker;
    }

    public static VelocityTracker obtain(String s)
    {
        if(s == null)
            return obtain();
        else
            return new VelocityTracker(s);
    }

    public void addMovement(MotionEvent motionevent)
    {
        if(motionevent == null)
        {
            throw new IllegalArgumentException("event must not be null");
        } else
        {
            nativeAddMovement(mPtr, motionevent);
            return;
        }
    }

    public void clear()
    {
        nativeClear(mPtr);
    }

    public void computeCurrentVelocity(int i)
    {
        nativeComputeCurrentVelocity(mPtr, i, 3.402823E+038F);
    }

    public void computeCurrentVelocity(int i, float f)
    {
        nativeComputeCurrentVelocity(mPtr, i, f);
    }

    protected void finalize()
        throws Throwable
    {
        if(mPtr != 0L)
        {
            nativeDispose(mPtr);
            mPtr = 0L;
        }
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public boolean getEstimator(int i, Estimator estimator)
    {
        if(estimator == null)
            throw new IllegalArgumentException("outEstimator must not be null");
        else
            return nativeGetEstimator(mPtr, i, estimator);
    }

    public float getXVelocity()
    {
        return nativeGetXVelocity(mPtr, -1);
    }

    public float getXVelocity(int i)
    {
        return nativeGetXVelocity(mPtr, i);
    }

    public float getYVelocity()
    {
        return nativeGetYVelocity(mPtr, -1);
    }

    public float getYVelocity(int i)
    {
        return nativeGetYVelocity(mPtr, i);
    }

    public void recycle()
    {
        if(mStrategy == null)
        {
            clear();
            sPool.release(this);
        }
    }

    private static final int ACTIVE_POINTER_ID = -1;
    private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(2);
    private long mPtr;
    private final String mStrategy;

}
