// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioSystem;
import android.media.audiofx.Visualizer;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import miui.util.FeatureParser;

public class SpectrumVisualizer extends ImageView
{
    class AsymmetryDotBar
        implements DotBarDrawer
    {

        public void drawDotBar(Canvas canvas, int i)
        {
            int j = (int)((double)(((float)mDotbarHeight * (1.0F - mPointData[i])) / (float)mCellSize) + 0.5D) * mCellSize;
            if(j < mDotbarHeight)
                canvas.drawBitmap(mPixels, mCellSize * j, mCellSize, mCellSize * i, j, mCellSize, mDotbarHeight - j, true, mPaint);
        }

        final SpectrumVisualizer this$0;

        AsymmetryDotBar()
        {
            this$0 = SpectrumVisualizer.this;
            super();
        }
    }

    private static interface DotBarDrawer
    {

        public abstract void drawDotBar(Canvas canvas, int i);
    }

    class SymmetryDotBar
        implements DotBarDrawer
    {

        public void drawDotBar(Canvas canvas, int i)
        {
            int j = (int)((double)(((float)mDotbarHeight * (1.0F - mPointData[i])) / (float)mCellSize) + 0.5D) * mCellSize;
            if(j < mDotbarHeight)
                canvas.drawBitmap(mPixels, mCellSize * j, mCellSize, mCellSize * i, j, mCellSize, mDotbarHeight - j, true, mPaint);
            int k = (int)((double)(((float)mShadowDotbarHeight * mPointData[i]) / (float)mCellSize) + 0.5D) * mCellSize;
            j = k;
            if(k > mShadowDotbarHeight)
                j = mShadowDotbarHeight;
            if(j > 0)
                canvas.drawBitmap(mShadowPixels, 0, mCellSize, mCellSize * i, mDotbarHeight, mCellSize, j, true, mPaint);
        }

        final SpectrumVisualizer this$0;

        SymmetryDotBar()
        {
            this$0 = SpectrumVisualizer.this;
            super();
        }
    }


    public SpectrumVisualizer(Context context)
    {
        super(context);
        mPaint = new Paint();
        mSampleBuf = new short[160];
        mSoftDrawEnabled = true;
        MAX_VALID_SAMPLE = 20;
        init(context, null);
    }

    public SpectrumVisualizer(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mPaint = new Paint();
        mSampleBuf = new short[160];
        mSoftDrawEnabled = true;
        MAX_VALID_SAMPLE = 20;
        init(context, attributeset);
    }

    public SpectrumVisualizer(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mPaint = new Paint();
        mSampleBuf = new short[160];
        mSoftDrawEnabled = true;
        MAX_VALID_SAMPLE = 20;
        init(context, attributeset);
    }

    private void drawInternal(Canvas canvas)
    {
        mPaint.setAlpha(255);
        int i = mVisualizationWidthNum;
        int j = mAlphaWidthNum;
        for(int k = mAlphaWidthNum; k < i - j; k++)
            mDrawer.drawDotBar(canvas, k);

        for(int l = mAlphaWidthNum; l > 0; l--)
        {
            mPaint.setAlpha((l * 255) / mAlphaWidthNum);
            mDrawer.drawDotBar(canvas, l - 1);
            mDrawer.drawDotBar(canvas, mVisualizationWidthNum - l);
        }

    }

    private Bitmap drawToBitmap()
    {
        Canvas canvas;
        Bitmap bitmap2;
label0:
        {
            Bitmap bitmap = mCachedBitmap;
            canvas = mCachedCanvas;
            bitmap2 = bitmap;
            if(bitmap == null)
                break label0;
            if(bitmap.getWidth() == getWidth())
            {
                bitmap2 = bitmap;
                if(bitmap.getHeight() == getHeight())
                    break label0;
            }
            bitmap.recycle();
            bitmap2 = null;
        }
        Bitmap bitmap1 = bitmap2;
        if(bitmap2 == null)
        {
            bitmap1 = Bitmap.createBitmap(getWidth(), getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
            mCachedBitmap = bitmap1;
            canvas = new Canvas(bitmap1);
            mCachedCanvas = canvas;
        }
        canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        drawInternal(canvas);
        return bitmap1;
    }

    private void init(Context context, AttributeSet attributeset)
    {
        Object obj = null;
        Object obj1 = null;
        android.graphics.drawable.Drawable drawable = null;
        boolean flag = false;
        mEnableDrawing = true;
        mIsNeedCareStreamActive = true;
        mAlphaWidthNum = 0;
        if(attributeset != null)
        {
            attributeset = context.obtainStyledAttributes(attributeset, android.miui.R.styleable.SpectrumVisualizer);
            obj = attributeset.getDrawable(3);
            obj1 = attributeset.getDrawable(1);
            drawable = attributeset.getDrawable(2);
            flag = attributeset.getBoolean(0, false);
            mAlphaWidthNum = attributeset.getInt(4, mAlphaWidthNum);
            mIsEnableUpdate = attributeset.getBoolean(5, false);
            mIsNeedCareStreamActive = attributeset.getBoolean(6, false);
            attributeset.recycle();
        }
        attributeset = ((AttributeSet) (obj));
        if(obj == null)
            attributeset = context.getResources().getDrawable(0x11020090);
        obj = ((BitmapDrawable)attributeset).getBitmap();
        attributeset = ((AttributeSet) (obj1));
        if(obj1 == null)
            attributeset = context.getResources().getDrawable(0x11020091);
        obj1 = ((BitmapDrawable)attributeset).getBitmap();
        attributeset = null;
        if(flag)
        {
            attributeset = drawable;
            if(drawable == null)
                attributeset = context.getResources().getDrawable(0x11020092);
            attributeset = ((BitmapDrawable)attributeset).getBitmap();
        }
        setBitmaps(((Bitmap) (obj)), ((Bitmap) (obj1)), attributeset);
    }

    public void enableDrawing(boolean flag)
    {
        mEnableDrawing = flag;
    }

    public void enableUpdate(boolean flag)
    {
        if(mIsEnableUpdate == flag) goto _L2; else goto _L1
_L1:
        if(!flag) goto _L4; else goto _L3
_L3:
        if(mVisualizer != null) goto _L4; else goto _L5
_L5:
        if(!IS_LPA_DECODE) goto _L7; else goto _L6
_L6:
        Log.v("SpectrumVisualizer", "lpa decode is on, can't enable");
_L8:
        mIsEnableUpdate = flag;
_L2:
        return;
_L7:
        Visualizer visualizer = JVM INSTR new #242 <Class Visualizer>;
        visualizer.Visualizer(0);
        mVisualizer = visualizer;
        if(!mVisualizer.getEnabled())
        {
            mVisualizer.setCaptureSize(512);
            mVisualizer.setDataCaptureListener(mOnDataCaptureListener, Visualizer.getMaxCaptureRate(), false, true);
            mVisualizer.setEnabled(true);
        }
          goto _L8
        Object obj;
        obj;
          goto _L2
_L4:
        if(flag) goto _L8; else goto _L9
_L9:
        if(mVisualizer == null) goto _L8; else goto _L10
_L10:
        mVisualizer.setEnabled(false);
        if(android.os.Build.VERSION.SDK_INT < 22 && !FeatureParser.getBoolean("is_xiaomi_device", false))
            break MISSING_BLOCK_LABEL_164;
_L11:
        mVisualizer.release();
        mVisualizer = null;
          goto _L8
        obj;
          goto _L2
        Thread.sleep(50L);
          goto _L11
        obj;
        ((InterruptedException) (obj)).printStackTrace();
          goto _L2
    }

    public int getVisualHeight()
    {
        return mVisualizationHeight;
    }

    public int getVisualWidth()
    {
        return mVisualizationWidth;
    }

    public boolean isUpdateEnabled()
    {
        return mIsEnableUpdate;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(!mEnableDrawing)
            return;
        if(mSoftDrawEnabled)
            canvas.drawBitmap(drawToBitmap(), 0.0F, 0.0F, null);
        else
            drawInternal(canvas);
    }

    public void setAlphaNum(int i)
    {
        if(i <= 0)
        {
            mAlphaWidthNum = 0;
            return;
        }
        int j = i;
        if(i > mVisualizationWidthNum / 2)
            j = mVisualizationWidthNum / 2;
        mAlphaWidthNum = j;
    }

    public void setBitmaps(int i, int j, Bitmap bitmap, Bitmap bitmap1)
    {
        mVisualizationWidth = i;
        mVisualizationHeight = j;
        mCellSize = bitmap.getWidth();
        mDotbarHeight = bitmap.getHeight();
        if(mDotbarHeight > mVisualizationHeight)
            mDotbarHeight = mVisualizationHeight;
        mPixels = new int[mCellSize * mDotbarHeight];
        bitmap.getPixels(mPixels, 0, mCellSize, 0, 0, mCellSize, mDotbarHeight);
        mVisualizationWidthNum = mVisualizationWidth / mCellSize;
        mVisualizationHeightNum = mDotbarHeight / mCellSize;
        SAMPLE_SCALE_FACTOR = 20F / (float)mVisualizationHeightNum;
        INDEX_SCALE_FACTOR = (float)Math.log(mVisualizationWidthNum / 3);
        VISUALIZE_DESC_HEIGHT = 1.0F / (float)mVisualizationHeightNum;
        mPointData = new float[mVisualizationWidthNum];
        if(mAlphaWidthNum == 0)
            mAlphaWidthNum = mVisualizationWidthNum / 2;
        mShadowPixels = null;
        if(bitmap1 != null)
        {
            mShadowDotbarHeight = bitmap1.getHeight();
            if(mShadowDotbarHeight + mDotbarHeight > mVisualizationHeight)
                mShadowDotbarHeight = mVisualizationHeight - mDotbarHeight;
            if(mShadowDotbarHeight < mCellSize)
            {
                mDrawer = new AsymmetryDotBar();
                return;
            }
            mShadowPixels = new int[mCellSize * mShadowDotbarHeight];
            bitmap1.getPixels(mShadowPixels, 0, mCellSize, 0, 0, mCellSize, mShadowDotbarHeight);
            mDrawer = new SymmetryDotBar();
        } else
        {
            mDrawer = new AsymmetryDotBar();
        }
    }

    public void setBitmaps(Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2)
    {
        setImageBitmap(bitmap);
        setBitmaps(bitmap.getWidth(), bitmap.getHeight(), bitmap1, bitmap2);
    }

    public void setSoftDrawEnabled(boolean flag)
    {
        mSoftDrawEnabled = flag;
        if(!flag && mCachedBitmap != null)
        {
            mCachedBitmap.recycle();
            mCachedBitmap = null;
            mCachedCanvas = null;
        }
    }

    void update(byte abyte0[])
    {
        if(mIsNeedCareStreamActive && AudioSystem.isStreamActive(3, 0) ^ true)
        {
            enableDrawing(false);
            return;
        }
        enableDrawing(true);
        if(abyte0 == null)
            return;
        short aword0[] = mSampleBuf;
        int i = aword0.length;
        int j = 0;
        while(j < i) 
        {
            int k = abyte0[j * 2];
            byte byte0 = abyte0[j * 2 + 1];
            k = (int)Math.sqrt(k * k + byte0 * byte0);
            if(k >= 32767)
                k = 32767;
            aword0[j] = (short)k;
            j++;
        }
        int i1 = 0;
        j = 0;
        int l = 0;
        while(l < mVisualizationWidthNum) 
        {
            int j1 = 0;
            for(; j < i; j += mVisualizationWidthNum)
            {
                j1 = Math.max(j1, aword0[i1]);
                i1++;
            }

            j -= i;
            float f;
            if(j1 > 1)
            {
                f = (float)(Math.log(l + 2) / (double)INDEX_SCALE_FACTOR);
                f = (float)(j1 - 1) * f * f;
            } else
            {
                f = 0.0F;
            }
            if(f > 20F)
                f = mVisualizationHeightNum;
            else
                f /= SAMPLE_SCALE_FACTOR;
            mPointData[l] = Math.max(f / (float)mVisualizationHeightNum, mPointData[l] - VISUALIZE_DESC_HEIGHT);
            l++;
        }
        invalidate();
    }

    private static final int CONSIDER_SAMPLE_LENGTH = 160;
    public static boolean IS_LPA_DECODE = false;
    private static final int RES_DEFAULT_SLIDING_DOT_BAR_ID = 0x11020091;
    private static final int RES_DEFAULT_SLIDING_PANEL_ID = 0x11020090;
    private static final int RES_DEFAULT_SLIDING_SHADOW_DOT_BAR_ID = 0x11020092;
    private static final String TAG = "SpectrumVisualizer";
    private static final int VISUALIZATION_SAMPLE_LENGTH = 256;
    private float INDEX_SCALE_FACTOR;
    private final int MAX_VALID_SAMPLE;
    private float SAMPLE_SCALE_FACTOR;
    private float VISUALIZE_DESC_HEIGHT;
    int mAlphaWidthNum;
    private Bitmap mCachedBitmap;
    private Canvas mCachedCanvas;
    int mCellSize;
    int mDotbarHeight;
    private DotBarDrawer mDrawer;
    private boolean mEnableDrawing;
    private boolean mIsEnableUpdate;
    private boolean mIsNeedCareStreamActive;
    private android.media.audiofx.Visualizer.OnDataCaptureListener mOnDataCaptureListener = new android.media.audiofx.Visualizer.OnDataCaptureListener() {

        public void onFftDataCapture(Visualizer visualizer, byte abyte0[], int i)
        {
            update(abyte0);
        }

        public void onWaveFormDataCapture(Visualizer visualizer, byte abyte0[], int i)
        {
        }

        final SpectrumVisualizer this$0;

            
            {
                this$0 = SpectrumVisualizer.this;
                super();
            }
    }
;
    Paint mPaint;
    int mPixels[];
    float mPointData[];
    private short mSampleBuf[];
    int mShadowDotbarHeight;
    int mShadowPixels[];
    private boolean mSoftDrawEnabled;
    private int mVisualizationHeight;
    int mVisualizationHeightNum;
    private int mVisualizationWidth;
    int mVisualizationWidthNum;
    private Visualizer mVisualizer;

    static 
    {
        IS_LPA_DECODE = SystemProperties.getBoolean("persist.sys.lpa.decode", false);
    }
}
