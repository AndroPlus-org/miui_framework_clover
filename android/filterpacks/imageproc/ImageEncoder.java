// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.graphics.Bitmap;
import java.io.OutputStream;

public class ImageEncoder extends Filter
{

    public ImageEncoder(String s)
    {
        super(s);
        mQuality = 80;
    }

    public void process(FilterContext filtercontext)
    {
        pullInput("image").getBitmap().compress(android.graphics.Bitmap.CompressFormat.JPEG, mQuality, mOutputStream);
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3, 0));
    }

    private OutputStream mOutputStream;
    private int mQuality;
}
