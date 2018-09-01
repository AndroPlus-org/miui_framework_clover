// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.*;
import android.text.TextUtils;
import android.util.Log;
import miui.maml.*;
import miui.maml.util.Utils;
import miui.widget.SpectrumVisualizer;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ImageScreenElement

public class SpectrumVisualizerScreenElement extends ImageScreenElement
{

    public SpectrumVisualizerScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        load(element);
    }

    private void load(Element element)
    {
        if(element == null)
        {
            return;
        } else
        {
            mPanelSrc = element.getAttribute("panelSrc");
            mDotbar = element.getAttribute("dotbarSrc");
            mShadow = element.getAttribute("shadowSrc");
            mSpectrumVisualizer = new SpectrumVisualizer(getContext().mContext);
            mSpectrumVisualizer.setSoftDrawEnabled(false);
            mSpectrumVisualizer.enableUpdate(false);
            mAlphaWidthNum = Utils.getAttrAsInt(element, "alphaWidthNum", -1);
            return;
        }
    }

    protected void doRender(Canvas canvas)
    {
        if(mPanel != null)
        {
            mPaint.setAlpha(getAlpha());
            canvas.drawBitmap(mPanel, getLeft(), getTop(), mPaint);
        }
        super.doRender(canvas);
    }

    public void enableUpdate(boolean flag)
    {
        mSpectrumVisualizer.enableUpdate(flag);
    }

    protected BitmapProvider.VersionedBitmap getBitmap(boolean flag)
    {
        if(mCanvas == null)
        {
            return null;
        } else
        {
            mCanvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
            mCanvas.setDensity(0);
            mSpectrumVisualizer.draw(mCanvas);
            mCanvas.setDensity(mResDensity);
            mBitmap.updateVersion();
            return mBitmap;
        }
    }

    public void init()
    {
        Bitmap bitmap;
        Bitmap bitmap1;
        int i;
        int k;
label0:
        {
label1:
            {
label2:
                {
                    bitmap = null;
                    super.init();
                    int j;
                    if(!TextUtils.isEmpty(mPanelSrc))
                        bitmap = getContext().mResourceManager.getBitmap(mPanelSrc);
                    mPanel = bitmap;
                    if(TextUtils.isEmpty(mDotbar))
                        bitmap = null;
                    else
                        bitmap = getContext().mResourceManager.getBitmap(mDotbar);
                    if(TextUtils.isEmpty(mShadow))
                        bitmap1 = null;
                    else
                        bitmap1 = getContext().mResourceManager.getBitmap(mShadow);
                    i = (int)getWidth();
                    j = (int)getHeight();
                    if(i > 0)
                    {
                        k = j;
                        if(j > 0)
                            break label2;
                    }
                    if(mPanel == null)
                        break label1;
                    i = mPanel.getWidth();
                    k = mPanel.getHeight();
                }
                if(bitmap == null)
                {
                    Log.e("SpectrumVisualizerScreenElement", "no dotbar");
                    return;
                }
                break label0;
            }
            Log.e("SpectrumVisualizerScreenElement", "no panel or size");
            return;
        }
        mSpectrumVisualizer.setBitmaps(i, k, bitmap, bitmap1);
        if(mAlphaWidthNum >= 0)
            mSpectrumVisualizer.setAlphaNum(mAlphaWidthNum);
        mResDensity = bitmap.getDensity();
        mSpectrumVisualizer.layout(0, 0, i, k);
        bitmap = Bitmap.createBitmap(i, k, android.graphics.Bitmap.Config.ARGB_8888);
        bitmap.setDensity(mResDensity);
        mCanvas = new Canvas(bitmap);
        mBitmap.setBitmap(bitmap);
    }

    public static final String TAG_NAME = "SpectrumVisualizer";
    private int mAlphaWidthNum;
    private Canvas mCanvas;
    private String mDotbar;
    private Bitmap mPanel;
    private String mPanelSrc;
    private int mResDensity;
    private String mShadow;
    private SpectrumVisualizer mSpectrumVisualizer;
}
