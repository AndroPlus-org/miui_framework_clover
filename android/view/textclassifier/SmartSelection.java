// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textclassifier;


final class SmartSelection
{
    static final class ClassificationResult
    {

        final String mCollection;
        final float mScore;

        ClassificationResult(String s, float f)
        {
            mCollection = s;
            mScore = f;
        }
    }


    SmartSelection(int i)
    {
        mCtx = nativeNew(i);
    }

    public static String getLanguage(int i)
    {
        return nativeGetLanguage(i);
    }

    public static int getVersion(int i)
    {
        return nativeGetVersion(i);
    }

    private static native ClassificationResult[] nativeClassifyText(long l, String s, int i, int j, int k);

    private static native void nativeClose(long l);

    private static native String nativeGetLanguage(int i);

    private static native int nativeGetVersion(int i);

    private static native long nativeNew(int i);

    private static native int[] nativeSuggest(long l, String s, int i, int j);

    public ClassificationResult[] classifyText(String s, int i, int j, int k)
    {
        return nativeClassifyText(mCtx, s, i, j, k);
    }

    public void close()
    {
        nativeClose(mCtx);
    }

    public int[] suggest(String s, int i, int j)
    {
        return nativeSuggest(mCtx, s, i, j);
    }

    static final int HINT_FLAG_EMAIL = 2;
    static final int HINT_FLAG_URL = 1;
    private final long mCtx;

    static 
    {
        System.loadLibrary("textclassifier");
    }
}
