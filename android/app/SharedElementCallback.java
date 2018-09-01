// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.transition.TransitionUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback
{
    public static interface OnSharedElementsReadyListener
    {

        public abstract void onSharedElementsReady();
    }


    public SharedElementCallback()
    {
    }

    public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectf)
    {
        if(view instanceof ImageView)
        {
            ImageView imageview = (ImageView)view;
            Object obj = imageview.getDrawable();
            Drawable drawable = imageview.getBackground();
            if(obj != null && (drawable == null || drawable.getAlpha() == 0))
            {
                obj = TransitionUtils.createDrawableBitmap(((Drawable) (obj)), imageview);
                if(obj != null)
                {
                    view = new Bundle();
                    if(((Bitmap) (obj)).getConfig() != android.graphics.Bitmap.Config.HARDWARE)
                        view.putParcelable("sharedElement:snapshot:bitmap", ((Parcelable) (obj)));
                    else
                        view.putParcelable("sharedElement:snapshot:graphicBuffer", ((Bitmap) (obj)).createGraphicBufferHandle());
                    view.putString("sharedElement:snapshot:imageScaleType", imageview.getScaleType().toString());
                    if(imageview.getScaleType() == android.widget.ImageView.ScaleType.MATRIX)
                    {
                        matrix = imageview.getImageMatrix();
                        rectf = new float[9];
                        matrix.getValues(rectf);
                        view.putFloatArray("sharedElement:snapshot:imageMatrix", rectf);
                    }
                    return view;
                }
            }
        }
        if(mTempMatrix == null)
            mTempMatrix = new Matrix(matrix);
        else
            mTempMatrix.set(matrix);
        matrix = (ViewGroup)view.getParent();
        return TransitionUtils.createViewBitmap(view, mTempMatrix, rectf, matrix);
    }

    public View onCreateSnapshotView(Context context, Parcelable parcelable)
    {
        Object obj = null;
        if(!(parcelable instanceof Bundle)) goto _L2; else goto _L1
_L1:
        Bundle bundle = (Bundle)parcelable;
        Object obj1 = (GraphicBuffer)bundle.getParcelable("sharedElement:snapshot:graphicBuffer");
        obj = (Bitmap)bundle.getParcelable("sharedElement:snapshot:bitmap");
        if(obj1 == null && obj == null)
            return null;
        parcelable = ((Parcelable) (obj));
        if(obj == null)
            parcelable = Bitmap.createHardwareBitmap(((GraphicBuffer) (obj1)));
        obj1 = new ImageView(context);
        context = ((Context) (obj1));
        ((ImageView) (obj1)).setImageBitmap(parcelable);
        ((ImageView) (obj1)).setScaleType(android.widget.ImageView.ScaleType.valueOf(bundle.getString("sharedElement:snapshot:imageScaleType")));
        obj = context;
        if(((ImageView) (obj1)).getScaleType() == android.widget.ImageView.ScaleType.MATRIX)
        {
            parcelable = bundle.getFloatArray("sharedElement:snapshot:imageMatrix");
            obj = new Matrix();
            ((Matrix) (obj)).setValues(parcelable);
            ((ImageView) (obj1)).setImageMatrix(((Matrix) (obj)));
            obj = context;
        }
_L4:
        return ((View) (obj));
_L2:
        if(parcelable instanceof Bitmap)
        {
            parcelable = (Bitmap)parcelable;
            obj = new View(context);
            ((View) (obj)).setBackground(new BitmapDrawable(context.getResources(), parcelable));
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onMapSharedElements(List list, Map map)
    {
    }

    public void onRejectSharedElements(List list)
    {
    }

    public void onSharedElementEnd(List list, List list1, List list2)
    {
    }

    public void onSharedElementStart(List list, List list1, List list2)
    {
    }

    public void onSharedElementsArrived(List list, List list1, OnSharedElementsReadyListener onsharedelementsreadylistener)
    {
        onsharedelementsreadylistener.onSharedElementsReady();
    }

    private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
    private static final String BUNDLE_SNAPSHOT_GRAPHIC_BUFFER = "sharedElement:snapshot:graphicBuffer";
    private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";
    private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
    static final SharedElementCallback NULL_CALLBACK = new SharedElementCallback() {

    }
;
    private Matrix mTempMatrix;

}
