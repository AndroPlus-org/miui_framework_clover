// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.shader;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import miui.maml.*;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.shader:
//            ShaderElement

public class BitmapShaderElement extends ShaderElement
{

    public BitmapShaderElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mBitmap = mRoot.getContext().mResourceManager.getBitmap(element.getAttribute("src"));
        resolveTileMode(element);
        mShader = new BitmapShader(mBitmap, mTileModeX, mTileModeY);
    }

    private void resolveTileMode(Element element)
    {
        element = element.getAttribute("tile").split(",");
        if(element.length <= 1)
        {
            element = mTileMode;
            mTileModeY = element;
            mTileModeX = element;
            return;
        } else
        {
            mTileModeX = getTileMode(element[0]);
            mTileModeY = getTileMode(element[1]);
            return;
        }
    }

    public void onGradientStopsChanged()
    {
    }

    public void updateShader()
    {
    }

    public boolean updateShaderMatrix()
    {
        return false;
    }

    public static final String TAG_NAME = "BitmapShader";
    private Bitmap mBitmap;
    private android.graphics.Shader.TileMode mTileModeX;
    private android.graphics.Shader.TileMode mTileModeY;
}
