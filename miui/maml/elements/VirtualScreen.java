// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import miui.maml.ScreenElementRoot;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ElementGroup

public class VirtualScreen extends ElementGroup
    implements BitmapProvider.IBitmapHolder
{

    public VirtualScreen(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
    }

    protected void doRender(Canvas canvas)
    {
        if(mTicked)
        {
            mTicked = false;
            mScreenCanvas.save();
            mScreenCanvas.concat(getMatrix());
            mScreenCanvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
            super.doRender(mScreenCanvas);
            mScreenCanvas.restore();
            mVersionedBitmap.updateVersion();
        }
    }

    protected void doTick(long l)
    {
        super.doTick(l);
        mTicked = true;
    }

    public void finish()
    {
        super.finish();
        mScreenBitmap.recycle();
        mScreenBitmap = null;
        mScreenCanvas = null;
    }

    public Bitmap getBitmap()
    {
        return mScreenBitmap;
    }

    public BitmapProvider.VersionedBitmap getBitmap(String s)
    {
        return mVersionedBitmap;
    }

    public void init()
    {
        super.init();
        float f = getWidth();
        float f1 = f;
        if(f < 0.0F)
            f1 = scale(Utils.getVariableNumber("screen_width", getVariables()));
        float f2 = getHeight();
        f = f2;
        if(f2 < 0.0F)
            f = scale(Utils.getVariableNumber("screen_height", getVariables()));
        mScreenBitmap = Bitmap.createBitmap(Math.round(f1), Math.round(f), android.graphics.Bitmap.Config.ARGB_8888);
        mScreenBitmap.setDensity(mRoot.getTargetDensity());
        mScreenCanvas = new Canvas(mScreenBitmap);
        mVersionedBitmap = new BitmapProvider.VersionedBitmap(mScreenBitmap);
    }

    public static final String TAG_NAME = "VirtualScreen";
    private Bitmap mScreenBitmap;
    private Canvas mScreenCanvas;
    private boolean mTicked;
    private BitmapProvider.VersionedBitmap mVersionedBitmap;
}
