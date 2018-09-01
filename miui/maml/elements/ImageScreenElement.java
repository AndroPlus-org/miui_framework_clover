// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.*;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import miui.graphics.BitmapFactory;
import miui.maml.*;
import miui.maml.animation.BaseAnimation;
import miui.maml.animation.SourcesAnimation;
import miui.maml.data.*;
import miui.maml.util.TextFormatter;
import miui.maml.util.Utils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// Referenced classes of package miui.maml.elements:
//            AnimatedScreenElement, BitmapProvider

public class ImageScreenElement extends AnimatedScreenElement
    implements BitmapProvider.IBitmapHolder
{
    private class Mask extends ImageScreenElement
    {

        protected void doRender(Canvas canvas)
        {
        }

        public final boolean isAlignAbsolute()
        {
            return mAlignAbsolute;
        }

        private boolean mAlignAbsolute;
        final ImageScreenElement this$0;

        public Mask(Element element, ScreenElementRoot screenelementroot)
        {
            this$0 = ImageScreenElement.this;
            super(element, screenelementroot);
            if(getAttr(element, "align").equalsIgnoreCase("absolute"))
                mAlignAbsolute = true;
        }
    }

    private static class pair
    {

        public Object p1;
        public Object p2;

        private pair()
        {
        }

        pair(pair pair1)
        {
            this();
        }
    }


    public ImageScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mMaskPaint = new Paint();
        mPaint = new Paint();
        mDesRect = new Rect();
        mBitmap = new BitmapProvider.VersionedBitmap(null);
        mCurrentBitmap = new BitmapProvider.VersionedBitmap(null);
        mW = -1;
        mH = -1;
        load(element);
    }

    private void load(Element element)
    {
        if(element == null)
            return;
        Variables variables = getVariables();
        mSrcFormatter = TextFormatter.fromElement(variables, element, "src", "srcFormat", "srcParas", "srcExp", "srcFormatExp");
        mSrcIdExpression = Expression.build(variables, getAttr(element, "srcid"));
        mAntiAlias = getAttr(element, "antiAlias").equals("false") ^ true;
        mPaint.setFilterBitmap(mAntiAlias);
        mMaskPaint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));
        mMaskPaint.setFilterBitmap(mAntiAlias);
        mExpSrcX = Expression.build(variables, getAttr(element, "srcX"));
        mExpSrcY = Expression.build(variables, getAttr(element, "srcY"));
        mExpSrcW = Expression.build(variables, getAttr(element, "srcW"));
        mExpSrcH = Expression.build(variables, getAttr(element, "srcH"));
        mExpW = Expression.build(variables, getAttr(element, "w"));
        mExpH = Expression.build(variables, getAttr(element, "h"));
        if(mExpSrcW != null && mExpSrcH != null)
        {
            mHasSrcRect = true;
            mSrcRect = new Rect();
        }
        if(mExpH != null && mExpW != null)
            mHasWidthAndHeight = true;
        mRawBlurRadius = getAttrAsInt(element, "blur", 0);
        loadMesh(element);
        mXfermodeNumExp = Expression.build(variables, getAttr(element, "xfermodeNum"));
        if(mXfermodeNumExp == null)
        {
            android.graphics.PorterDuff.Mode mode = Utils.getPorterDuffMode(getAttr(element, "xfermode"));
            mPaint.setXfermode(new PorterDuffXfermode(mode));
        }
        boolean flag = Boolean.parseBoolean(getAttr(element, "useVirtualScreen"));
        String s = getAttr(element, "srcType");
        if(flag)
            s = "VirtualScreen";
        setSrcType(s);
        mLoadAsync = Boolean.parseBoolean(getAttr(element, "loadAsync"));
        mRetainWhenInvisible = Boolean.parseBoolean(getAttr(element, "retainWhenInvisible"));
        if(mHasName)
        {
            mBmpSizeWidthVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("bmp_width").toString(), variables, true);
            mBmpSizeHeightVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("bmp_height").toString(), variables, true);
            mHasBitmapVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("has_bitmap").toString(), variables, true);
        }
        loadMask(element);
    }

    private void loadMask(Element element)
    {
        if(mMasks == null)
            mMasks = new ArrayList();
        mMasks.clear();
        element = element.getElementsByTagName("Mask");
        for(int i = 0; i < element.getLength(); i++)
            mMasks.add(new Mask((Element)element.item(i), mRoot));

    }

    private void renderWithMask(Canvas canvas, Mask mask, int i, int j)
    {
        Bitmap bitmap = getContext().mResourceManager.getBitmap(mask.getSrc());
        if(bitmap == null)
            return;
        canvas.save();
        double d = mask.getX();
        double d1 = mask.getY();
        float f = mask.getRotation();
        float f1 = f;
        double d2 = d;
        double d3 = d1;
        if(mask.isAlignAbsolute())
        {
            f1 = getRotation();
            int k;
            int l;
            int i1;
            int j1;
            int k1;
            if(f1 == 0.0F)
            {
                d2 = d - (double)i;
                d3 = d1 - (double)j;
            } else
            {
                f -= f1;
                d3 = ((double)f1 * 3.1415926535896999D) / 180D;
                float f2 = getPivotX();
                f1 = getPivotY();
                if(mRotateXYpair == null)
                    mRotateXYpair = new pair(null);
                rotateXY(f2, f1, d3, mRotateXYpair);
                double d4 = i;
                double d5 = ((Double)mRotateXYpair.p1).doubleValue();
                double d6 = j;
                d2 = ((Double)mRotateXYpair.p2).doubleValue();
                rotateXY(descale(mask.getPivotX()), descale(mask.getPivotY()), ((double)mask.getRotation() * 3.1415926535896999D) / 180D, mRotateXYpair);
                double d7 = scale(((Double)mRotateXYpair.p1).doubleValue());
                double d8 = scale(((Double)mRotateXYpair.p2).doubleValue());
                d = (d + d7) - (d4 + d5);
                d2 = (d1 + d8) - (d6 + d2);
                d1 = Math.sqrt(d * d + d2 * d2);
                d = Math.asin(d / d1);
                if(d2 > 0.0D)
                    d3 += d;
                else
                    d3 = (3.1415926535896999D + d3) - d;
                d2 = d1 * Math.sin(d3);
                d3 = d1 * Math.cos(d3);
            }
            d2 -= getX();
            d3 -= getY();
            f1 = f;
        }
        canvas.rotate(f1, (float)((double)mask.getPivotX() + d2 + (double)i), (float)((double)mask.getPivotY() + d3 + (double)j));
        k = (int)d2;
        l = (int)d3;
        i1 = Math.round(mask.getWidth());
        j1 = i1;
        if(i1 < 0)
            j1 = bitmap.getWidth();
        k1 = Math.round(mask.getHeight());
        i1 = k1;
        if(k1 < 0)
            i1 = bitmap.getHeight();
        mDesRect.set(k + i, l + j, k + i + j1, l + j + i1);
        mMaskPaint.setAlpha(mask.getAlpha());
        canvas.drawBitmap(bitmap, null, mDesRect, mMaskPaint);
        canvas.restore();
    }

    private void rotateXY(double d, double d1, double d2, pair pair1)
    {
        double d3 = Math.sqrt(d * d + d1 * d1);
        if(d3 > 0.0D)
        {
            d2 = 3.1415926535896999D - Math.acos(d / d3) - d2;
            pair1.p1 = Double.valueOf(Math.cos(d2) * d3 + d);
            pair1.p2 = Double.valueOf(d1 - Math.sin(d2) * d3);
        } else
        {
            pair1.p1 = Double.valueOf(0.0D);
            pair1.p2 = Double.valueOf(0.0D);
        }
    }

    protected void doRender(Canvas canvas)
    {
        Object obj;
        int i;
        float f;
        float f1;
        float f2;
        float f3;
        int j;
        int k;
label0:
        {
            Bitmap bitmap = mCurrentBitmap.getBitmap();
            if(bitmap == null)
                return;
            if(mPendingBlur)
            {
                if(mBlurBitmap == null || bitmap.getWidth() != mBlurBitmap.getWidth() || bitmap.getHeight() != mBlurBitmap.getHeight())
                    mBlurBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
                mPendingBlur = false;
                mBlurBitmap = BitmapFactory.fastBlur(bitmap, mBlurBitmap, mBlurRadius);
            }
            obj = bitmap;
            if(mBlurBitmap != null)
            {
                obj = bitmap;
                if(mBlurRadius > 0)
                    obj = mBlurBitmap;
            }
            i = getAlpha();
            mPaint.setAlpha(i);
            i = canvas.getDensity();
            canvas.setDensity(0);
            f = getWidth();
            f1 = getHeight();
            f2 = super.getWidth();
            f3 = super.getHeight();
            if(f == 0.0F || f1 == 0.0F)
                return;
            break label0;
        }
        j = (int)getLeft(0.0F, f);
        k = (int)getTop(0.0F, f1);
        canvas.save();
        if(mMasks.size() != 0) goto _L2; else goto _L1
_L1:
        if(((Bitmap) (obj)).getNinePatchChunk() != null)
        {
            obj = getContext().mResourceManager.getNinePatch(getSrc());
            if(obj != null)
            {
                mDesRect.set(j, k, (int)((float)j + f), (int)((float)k + f1));
                ((NinePatch) (obj)).draw(canvas, mDesRect, mPaint);
            } else
            {
                Log.e("ImageScreenElement", (new StringBuilder()).append("the image contains ninepatch chunk but couldn't get NinePatch object: ").append(getSrc()).toString());
            }
        } else
        if(f2 > 0.0F || f3 > 0.0F || mSrcRect != null)
        {
            mDesRect.set(j, k, (int)((float)j + f), (int)((float)k + f1));
            if(mSrcRect != null)
                mSrcRect.set(mSrcX, mSrcY, mSrcX + mSrcW, mSrcY + mSrcH);
            canvas.drawBitmap(((Bitmap) (obj)), mSrcRect, mDesRect, mPaint);
        } else
        if(mMeshWidth > 0 && mMeshHeight > 0)
            canvas.drawBitmapMesh(((Bitmap) (obj)), mMeshWidth, mMeshHeight, mMeshVerts, 0, null, 0, mPaint);
        else
            canvas.drawBitmap(((Bitmap) (obj)), j, k, mPaint);
_L4:
        canvas.restore();
        canvas.setDensity(i);
        return;
_L2:
        float f4 = getMaxWidth();
        float f5 = getMaxHeight();
        f4 = Math.max(f4, f);
        f5 = Math.max(f5, f1);
        int l = (int)Math.ceil(f4);
        int i1 = (int)Math.ceil(f5);
        canvas.saveLayer(j, k, j + l, k + i1, mPaint, 31);
        break MISSING_BLOCK_LABEL_569;
        if(f2 > 0.0F || f3 > 0.0F || mSrcRect != null)
        {
            mDesRect.set(j, k, (int)f + j, (int)f1 + k);
            if(mSrcRect != null)
                mSrcRect.set(mSrcX, mSrcY, mSrcX + mSrcW, mSrcY + mSrcH);
            canvas.drawBitmap(((Bitmap) (obj)), mSrcRect, mDesRect, mPaint);
        } else
        {
            canvas.drawBitmap(((Bitmap) (obj)), j, k, mPaint);
        }
        for(obj = mMasks.iterator(); ((Iterator) (obj)).hasNext(); renderWithMask(canvas, (Mask)((Iterator) (obj)).next(), j, k));
        break MISSING_BLOCK_LABEL_728;
        canvas.restore();
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void doTick(long l)
    {
        String s = null;
        super.doTick(l);
        if(!isVisible())
            return;
        if(mSrcFormatter != null)
            s = mSrcFormatter.getText();
        mSrc = s;
        if(mMasks != null)
        {
            for(Iterator iterator = mMasks.iterator(); iterator.hasNext(); ((Mask)iterator.next()).doTick(l));
        }
        if(mXfermodeNumExp != null)
        {
            android.graphics.PorterDuff.Mode mode = Utils.getPorterDuffMode((int)mXfermodeNumExp.evaluate());
            mPaint.setXfermode(new PorterDuffXfermode(mode));
        }
        if(mHasSrcRect)
        {
            mSrcX = (int)scale(evaluate(mExpSrcX));
            mSrcY = (int)scale(evaluate(mExpSrcY));
            mSrcW = (int)scale(evaluate(mExpSrcW));
            mSrcH = (int)scale(evaluate(mExpSrcH));
        }
        if(mHasWidthAndHeight)
        {
            mW = (int)scale(evaluate(mExpW));
            mH = (int)scale(evaluate(mExpH));
        }
        updateBitmap(mLoadAsync);
    }

    public void finish()
    {
        super.finish();
        if(mBitmapProvider != null)
            mBitmapProvider.finish();
        if(mMasks != null)
        {
            for(Iterator iterator = mMasks.iterator(); iterator.hasNext(); ((Mask)iterator.next()).finish());
        }
        mBitmap.reset();
        mCurrentBitmap.reset();
        mBlurBitmap = null;
    }

    public BitmapProvider.VersionedBitmap getBitmap(String s)
    {
        return mCurrentBitmap;
    }

    protected BitmapProvider.VersionedBitmap getBitmap(boolean flag)
    {
        if(mBitmap.getBitmap() != null)
            return mBitmap;
        if(mBitmapProvider != null)
            return mBitmapProvider.getBitmap(getSrc(), flag ^ true, mW, mH);
        else
            return null;
    }

    protected int getBitmapHeight()
    {
        Bitmap bitmap = mCurrentBitmap.getBitmap();
        int i;
        if(bitmap != null)
            i = bitmap.getHeight();
        else
            i = 0;
        return i;
    }

    protected int getBitmapWidth()
    {
        Bitmap bitmap = mCurrentBitmap.getBitmap();
        int i;
        if(bitmap != null)
            i = bitmap.getWidth();
        else
            i = 0;
        return i;
    }

    public float getHeight()
    {
        float f = super.getHeight();
        if(f >= 0.0F)
            return f;
        if(mHasSrcRect)
            return (float)mSrcH;
        else
            return (float)getBitmapHeight();
    }

    public final String getSrc()
    {
        String s;
        String s1;
        if(mSources != null)
            s = mSources.getSrc();
        else
            s = mSrc;
        s1 = s;
        if(s != null)
        {
            s1 = s;
            if(mSrcIdExpression != null)
                s1 = Utils.addFileNameSuffix(s, String.valueOf((long)mSrcIdExpression.evaluate()));
        }
        return s1;
    }

    public float getWidth()
    {
        float f = super.getWidth();
        if(f >= 0.0F)
            return f;
        if(mHasSrcRect)
            return (float)mSrcW;
        else
            return (float)getBitmapWidth();
    }

    public float getX()
    {
        float f = super.getX();
        float f1 = f;
        if(mSources != null)
            f1 = f + scale(mSources.getX());
        return f1;
    }

    public float getY()
    {
        float f = super.getY();
        float f1 = f;
        if(mSources != null)
            f1 = f + scale(mSources.getY());
        return f1;
    }

    public void init()
    {
        String s = null;
        super.init();
        if(mSrcFormatter != null)
            s = mSrcFormatter.getText();
        mSrc = s;
        mBitmap.reset();
        if(mMasks != null)
        {
            for(Iterator iterator = mMasks.iterator(); iterator.hasNext(); ((Mask)iterator.next()).init());
        }
        if(mBitmapProvider != null)
            mBitmapProvider.init(getSrc());
        if(isVisible())
            updateBitmap(mLoadAsync);
        mBlurRadius = (int)scale(mRawBlurRadius);
        if(mBlurRadius > 0)
            mPendingBlur = true;
    }

    protected void loadMesh(Element element)
    {
        String s = getAttr(element, "mesh");
        int i = s.indexOf(",");
        if(i != -1)
        {
            try
            {
                mMeshWidth = Integer.parseInt(s.substring(0, i));
                mMeshHeight = Integer.parseInt(s.substring(i + 1));
            }
            catch(NumberFormatException numberformatexception)
            {
                Log.w("ImageScreenElement", (new StringBuilder()).append("Invalid mesh format:").append(s).toString());
            }
            if(mMeshWidth != 0 && mMeshHeight != 0)
            {
                s = getAttr(element, "meshVertsArr");
                element = ((Element) (getVariables().get(s)));
                if(element != null && (element instanceof float[]))
                {
                    mMeshVerts = (float[])element;
                } else
                {
                    mMeshHeight = 0;
                    mMeshWidth = 0;
                    Log.w("ImageScreenElement", (new StringBuilder()).append("Invalid meshVertsArr:").append(s).append("  undifined or not float[] type").toString());
                }
            }
        }
    }

    protected BaseAnimation onCreateAnimation(String s, Element element)
    {
        if("SourcesAnimation".equals(s))
        {
            s = new SourcesAnimation(element, this);
            mSources = s;
            return s;
        } else
        {
            return super.onCreateAnimation(s, element);
        }
    }

    protected void onSetAnimBefore()
    {
        super.onSetAnimBefore();
        mSources = null;
    }

    protected void onSetAnimEnable(BaseAnimation baseanimation)
    {
        if(baseanimation instanceof SourcesAnimation)
            mSources = (SourcesAnimation)baseanimation;
        else
            super.onSetAnimEnable(baseanimation);
    }

    protected void onVisibilityChange(boolean flag)
    {
        super.onVisibilityChange(flag);
        if(!flag) goto _L2; else goto _L1
_L1:
        updateBitmap(mLoadAsync);
_L4:
        return;
_L2:
        if(!mRetainWhenInvisible)
        {
            if(mBitmapProvider != null)
                mBitmapProvider.finish();
            mCurrentBitmap.reset();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void pause()
    {
        super.pause();
        if(mMasks != null)
        {
            for(Iterator iterator = mMasks.iterator(); iterator.hasNext(); ((Mask)iterator.next()).pause());
        }
    }

    protected void pauseAnim(long l)
    {
        super.pauseAnim(l);
        if(mMasks != null)
        {
            for(Iterator iterator = mMasks.iterator(); iterator.hasNext(); ((Mask)iterator.next()).pauseAnim(l));
        }
    }

    protected void playAnim(long l, long l1, long l2, boolean flag, 
            boolean flag1)
    {
        super.playAnim(l, l1, l2, flag, flag1);
        if(mMasks != null)
        {
            for(Iterator iterator = mMasks.iterator(); iterator.hasNext(); ((Mask)iterator.next()).playAnim(l, l1, l2, flag, flag1));
        }
        if(mBitmapProvider != null)
            mBitmapProvider.reset();
    }

    public void reset(long l)
    {
        super.reset(l);
        if(mMasks != null)
        {
            for(Iterator iterator = mMasks.iterator(); iterator.hasNext(); ((Mask)iterator.next()).reset(l));
        }
        if(mBitmapProvider != null)
            mBitmapProvider.reset();
        if(mBlurRadius > 0)
            mPendingBlur = true;
    }

    public void resume()
    {
        super.resume();
        if(mMasks != null)
        {
            for(Iterator iterator = mMasks.iterator(); iterator.hasNext(); ((Mask)iterator.next()).resume());
        }
    }

    protected void resumeAnim(long l)
    {
        super.resumeAnim(l);
        if(mMasks != null)
        {
            for(Iterator iterator = mMasks.iterator(); iterator.hasNext(); ((Mask)iterator.next()).resumeAnim(l));
        }
    }

    public void setBitmap(Bitmap bitmap)
    {
        if(bitmap != mBitmap.getBitmap())
        {
            mBitmap.setBitmap(bitmap);
            updateBitmap(mLoadAsync);
            requestUpdate();
        }
    }

    public void setSrc(String s)
    {
        if(mSrcFormatter != null)
            mSrcFormatter.setText(s);
    }

    public void setSrcId(double d)
    {
        if(mSrcIdExpression != null && (mSrcIdExpression instanceof miui.maml.data.Expression.NumberExpression))
            ((miui.maml.data.Expression.NumberExpression)mSrcIdExpression).setValue(d);
        else
            mSrcIdExpression = new miui.maml.data.Expression.NumberExpression(String.valueOf(d));
    }

    public void setSrcType(String s)
    {
        mBitmapProvider = BitmapProvider.create(mRoot, s);
    }

    protected void updateBitmap(boolean flag)
    {
        BitmapProvider.VersionedBitmap versionedbitmap = getBitmap(flag);
        if(mBlurRadius > 0 && BitmapProvider.VersionedBitmap.equals(versionedbitmap, mCurrentBitmap) ^ true)
            mPendingBlur = true;
        mCurrentBitmap.set(versionedbitmap);
        updateBitmapVars();
    }

    protected void updateBitmapVars()
    {
        if(mHasName)
        {
            mBmpSizeWidthVar.set(descale(getBitmapWidth()));
            mBmpSizeHeightVar.set(descale(getBitmapHeight()));
            IndexedVariable indexedvariable = mHasBitmapVar;
            int i;
            if(mCurrentBitmap.getBitmap() != null)
                i = 1;
            else
                i = 0;
            indexedvariable.set(i);
        }
    }

    private static final String LOG_TAG = "ImageScreenElement";
    public static final String MASK_TAG_NAME = "Mask";
    private static final double PI = 3.1415926535896999D;
    public static final String TAG_NAME = "Image";
    private static final String VAR_BMP_HEIGHT = "bmp_height";
    private static final String VAR_BMP_WIDTH = "bmp_width";
    private static final String VAR_HAS_BITMAP = "has_bitmap";
    private boolean mAntiAlias;
    protected BitmapProvider.VersionedBitmap mBitmap;
    private BitmapProvider mBitmapProvider;
    private Bitmap mBlurBitmap;
    private int mBlurRadius;
    private IndexedVariable mBmpSizeHeightVar;
    private IndexedVariable mBmpSizeWidthVar;
    protected BitmapProvider.VersionedBitmap mCurrentBitmap;
    private Rect mDesRect;
    private Expression mExpH;
    private Expression mExpSrcH;
    private Expression mExpSrcW;
    private Expression mExpSrcX;
    private Expression mExpSrcY;
    private Expression mExpW;
    private int mH;
    private IndexedVariable mHasBitmapVar;
    private boolean mHasSrcRect;
    private boolean mHasWidthAndHeight;
    private boolean mLoadAsync;
    private Paint mMaskPaint;
    private ArrayList mMasks;
    private int mMeshHeight;
    private float mMeshVerts[];
    private int mMeshWidth;
    protected Paint mPaint;
    private boolean mPendingBlur;
    private int mRawBlurRadius;
    private boolean mRetainWhenInvisible;
    private pair mRotateXYpair;
    private SourcesAnimation mSources;
    private String mSrc;
    private TextFormatter mSrcFormatter;
    private int mSrcH;
    private Expression mSrcIdExpression;
    private Rect mSrcRect;
    private int mSrcW;
    private int mSrcX;
    private int mSrcY;
    private int mW;
    private Expression mXfermodeNumExp;
}
