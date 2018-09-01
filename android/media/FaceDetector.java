// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.Log;

public class FaceDetector
{
    public class Face
    {

        public float confidence()
        {
            return mConfidence;
        }

        public float eyesDistance()
        {
            return mEyesDist;
        }

        public void getMidPoint(PointF pointf)
        {
            pointf.set(mMidPointX, mMidPointY);
        }

        public float pose(int i)
        {
            if(i == 0)
                return mPoseEulerX;
            if(i == 1)
                return mPoseEulerY;
            if(i == 2)
                return mPoseEulerZ;
            else
                throw new IllegalArgumentException();
        }

        public static final float CONFIDENCE_THRESHOLD = 0.4F;
        public static final int EULER_X = 0;
        public static final int EULER_Y = 1;
        public static final int EULER_Z = 2;
        private float mConfidence;
        private float mEyesDist;
        private float mMidPointX;
        private float mMidPointY;
        private float mPoseEulerX;
        private float mPoseEulerY;
        private float mPoseEulerZ;
        final FaceDetector this$0;

        private Face()
        {
            this$0 = FaceDetector.this;
            super();
        }

        Face(Face face)
        {
            this();
        }
    }


    public FaceDetector(int i, int j, int k)
    {
        if(!sInitialized)
        {
            return;
        } else
        {
            fft_initialize(i, j, k);
            mWidth = i;
            mHeight = j;
            mMaxFaces = k;
            mBWBuffer = new byte[i * j];
            return;
        }
    }

    private native void fft_destroy();

    private native int fft_detect(Bitmap bitmap);

    private native void fft_get_face(Face face, int i);

    private native int fft_initialize(int i, int j, int k);

    private static native void nativeClassInit();

    protected void finalize()
        throws Throwable
    {
        fft_destroy();
    }

    public int findFaces(Bitmap bitmap, Face aface[])
    {
        if(!sInitialized)
            return 0;
        if(bitmap.getWidth() != mWidth || bitmap.getHeight() != mHeight)
            throw new IllegalArgumentException("bitmap size doesn't match initialization");
        if(aface.length < mMaxFaces)
            throw new IllegalArgumentException("faces[] smaller than maxFaces");
        int i = fft_detect(bitmap);
        int k = i;
        if(i >= mMaxFaces)
            k = mMaxFaces;
        for(int j = 0; j < k; j++)
        {
            if(aface[j] == null)
                aface[j] = new Face(null);
            fft_get_face(aface[j], j);
        }

        return k;
    }

    private static boolean sInitialized = false;
    private byte mBWBuffer[];
    private long mDCR;
    private long mFD;
    private int mHeight;
    private int mMaxFaces;
    private long mSDK;
    private int mWidth;

    static 
    {
        System.loadLibrary("FFTEm");
        nativeClassInit();
        sInitialized = true;
_L1:
        return;
        UnsatisfiedLinkError unsatisfiedlinkerror;
        unsatisfiedlinkerror;
        Log.d("FFTEm", "face detection library not found!");
          goto _L1
    }
}
