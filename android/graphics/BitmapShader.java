// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Shader, Bitmap

public class BitmapShader extends Shader
{

    private BitmapShader(Bitmap bitmap, int i, int j)
    {
        if(bitmap == null)
            throw new IllegalArgumentException("Bitmap must be non-null");
        if(bitmap == mBitmap && i == mTileX && j == mTileY)
        {
            return;
        } else
        {
            mBitmap = bitmap;
            mTileX = i;
            mTileY = j;
            return;
        }
    }

    public BitmapShader(Bitmap bitmap, Shader.TileMode tilemode, Shader.TileMode tilemode1)
    {
        this(bitmap, tilemode.nativeInt, tilemode1.nativeInt);
    }

    private static native long nativeCreate(long l, Bitmap bitmap, int i, int j);

    protected Shader copy()
    {
        BitmapShader bitmapshader = new BitmapShader(mBitmap, mTileX, mTileY);
        copyLocalMatrix(bitmapshader);
        return bitmapshader;
    }

    long createNativeInstance(long l)
    {
        return nativeCreate(l, mBitmap, mTileX, mTileY);
    }

    public Bitmap mBitmap;
    private int mTileX;
    private int mTileY;
}
