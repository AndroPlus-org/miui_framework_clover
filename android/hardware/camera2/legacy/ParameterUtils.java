// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.graphics.*;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.utils.ParamsUtils;
import android.hardware.camera2.utils.SizeAreaComparator;
import android.util.*;
import com.android.internal.util.Preconditions;
import java.util.*;

public class ParameterUtils
{
    public static class MeteringData
    {

        public final android.hardware.Camera.Area meteringArea;
        public final Rect previewMetering;
        public final Rect reportedMetering;

        public MeteringData(android.hardware.Camera.Area area, Rect rect, Rect rect1)
        {
            meteringArea = area;
            previewMetering = rect;
            reportedMetering = rect1;
        }
    }

    public static class WeightedRectangle
    {

        private static int clip(int i, int j, int k, Rect rect1, String s)
        {
            if(i >= j) goto _L2; else goto _L1
_L1:
            Log.w("ParameterUtils", (new StringBuilder()).append("toMetering - Rectangle ").append(rect1).append(" ").append(s).append(" too small, clip to ").append(j).toString());
_L4:
            return j;
_L2:
            j = i;
            if(i > k)
            {
                Log.w("ParameterUtils", (new StringBuilder()).append("toMetering - Rectangle ").append(rect1).append(" ").append(s).append(" too small, clip to ").append(k).toString());
                j = k;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        private static int clipLower(int i, int j, Rect rect1, String s)
        {
            return clip(i, j, 0x7fffffff, rect1, s);
        }

        public Face toFace()
        {
            int i = clip(weight, 1, 100, rect, "score");
            return new Face(rect, i);
        }

        public Face toFace(int i, Point point, Point point1, Point point2)
        {
            int j = clipLower(i, 0, rect, "id");
            i = clip(weight, 1, 100, rect, "score");
            return new Face(rect, i, j, point, point1, point2);
        }

        public MeteringRectangle toMetering()
        {
            int i = clip(weight, 0, 1000, rect, "weight");
            return new MeteringRectangle(clipLower(rect.left, 0, rect, "left"), clipLower(rect.top, 0, rect, "top"), clipLower(rect.width(), 0, rect, "width"), clipLower(rect.height(), 0, rect, "height"), i);
        }

        public final Rect rect;
        public final int weight;

        public WeightedRectangle(Rect rect1, int i)
        {
            rect = (Rect)Preconditions.checkNotNull(rect1, "rect must not be null");
            weight = i;
        }
    }

    public static class ZoomData
    {

        public final Rect previewCrop;
        public final Rect reportedCrop;
        public final int zoomIndex;

        public ZoomData(int i, Rect rect, Rect rect1)
        {
            zoomIndex = i;
            previewCrop = rect;
            reportedCrop = rect1;
        }
    }


    private ParameterUtils()
    {
        throw new AssertionError();
    }

    public static boolean containsSize(List list, int i, int j)
    {
        Preconditions.checkNotNull(list, "sizeList must not be null");
        for(list = list.iterator(); list.hasNext();)
        {
            android.hardware.Camera.Size size = (android.hardware.Camera.Size)list.next();
            if(size.height == j && size.width == i)
                return true;
        }

        return false;
    }

    public static WeightedRectangle convertCameraAreaToActiveArrayRectangle(Rect rect, ZoomData zoomdata, android.hardware.Camera.Area area)
    {
        return convertCameraAreaToActiveArrayRectangle(rect, zoomdata, area, true);
    }

    private static WeightedRectangle convertCameraAreaToActiveArrayRectangle(Rect rect, ZoomData zoomdata, android.hardware.Camera.Area area, boolean flag)
    {
        rect = zoomdata.previewCrop;
        zoomdata = zoomdata.reportedCrop;
        float f = ((float)rect.width() * 1.0F) / 2000F;
        float f1 = ((float)rect.height() * 1.0F) / 2000F;
        Matrix matrix = new Matrix();
        matrix.setTranslate(1000F, 1000F);
        matrix.postScale(f, f1);
        matrix.postTranslate(rect.left, rect.top);
        if(!flag)
            rect = zoomdata;
        zoomdata = ParamsUtils.mapRect(matrix, area.rect);
        if(!zoomdata.intersect(rect))
            zoomdata.set(RECTANGLE_EMPTY);
        if(area.weight < 0)
            Log.w("ParameterUtils", (new StringBuilder()).append("convertCameraAreaToMeteringRectangle - rectangle ").append(stringFromArea(area)).append(" has too small weight, clip to 0").toString());
        return new WeightedRectangle(zoomdata, area.weight);
    }

    private static Point convertCameraPointToActiveArrayPoint(Rect rect, ZoomData zoomdata, Point point, boolean flag)
    {
        rect = convertCameraAreaToActiveArrayRectangle(rect, zoomdata, new android.hardware.Camera.Area(new Rect(point.x, point.y, point.x, point.y), 1), flag);
        return new Point(((WeightedRectangle) (rect)).rect.left, ((WeightedRectangle) (rect)).rect.top);
    }

    public static Face convertFaceFromLegacy(android.hardware.Camera.Face face, Rect rect, ZoomData zoomdata)
    {
        Preconditions.checkNotNull(face, "face must not be null");
        WeightedRectangle weightedrectangle = convertCameraAreaToActiveArrayRectangle(rect, zoomdata, new android.hardware.Camera.Area(face.rect, 1));
        Point point = face.leftEye;
        Point point1 = face.rightEye;
        Point point3 = face.mouth;
        if(point != null && point1 != null && point3 != null && point.x != -2000 && point.y != -2000 && point1.x != -2000 && point1.y != -2000 && point3.x != -2000 && point3.y != -2000)
        {
            Point point4 = convertCameraPointToActiveArrayPoint(rect, zoomdata, point, true);
            Point point2 = convertCameraPointToActiveArrayPoint(rect, zoomdata, point4, true);
            rect = convertCameraPointToActiveArrayPoint(rect, zoomdata, point4, true);
            face = weightedrectangle.toFace(face.id, point4, point2, rect);
        } else
        {
            face = weightedrectangle.toFace();
        }
        return face;
    }

    public static MeteringData convertMeteringRectangleToLegacy(Rect rect, MeteringRectangle meteringrectangle, ZoomData zoomdata)
    {
        Rect rect1 = zoomdata.previewCrop;
        float f = 2000F / (float)rect1.width();
        float f1 = 2000F / (float)rect1.height();
        Object obj = new Matrix();
        ((Matrix) (obj)).setTranslate(-rect1.left, -rect1.top);
        ((Matrix) (obj)).postScale(f, f1);
        ((Matrix) (obj)).postTranslate(-1000F, -1000F);
        Rect rect2 = ParamsUtils.mapRect(((Matrix) (obj)), meteringrectangle.getRect());
        obj = new Rect(rect2);
        Rect rect3;
        if(!((Rect) (obj)).intersect(NORMALIZED_RECTANGLE_DEFAULT))
        {
            Log.w("ParameterUtils", "convertMeteringRectangleToLegacy - metering rectangle too small, no metering will be done");
            ((Rect) (obj)).set(RECTANGLE_EMPTY);
            obj = new android.hardware.Camera.Area(RECTANGLE_EMPTY, 0);
        } else
        {
            obj = new android.hardware.Camera.Area(((Rect) (obj)), meteringrectangle.getMeteringWeight());
        }
        rect3 = meteringrectangle.getRect();
        if(!rect3.intersect(rect1))
            rect3.set(RECTANGLE_EMPTY);
        return new MeteringData(((android.hardware.Camera.Area) (obj)), rect3, convertCameraAreaToActiveArrayRectangle(rect, zoomdata, new android.hardware.Camera.Area(rect2, meteringrectangle.getMeteringWeight()), false).rect);
    }

    public static ZoomData convertScalerCropRegion(Rect rect, Rect rect1, Size size, android.hardware.Camera.Parameters parameters)
    {
        Rect rect2 = new Rect(0, 0, rect.width(), rect.height());
        rect = rect1;
        if(rect1 == null)
            rect = rect2;
        Rect rect3 = new Rect();
        rect1 = new Rect();
        return new ZoomData(getClosestAvailableZoomCrop(parameters, rect2, size, rect, rect3, rect1), rect1, rect3);
    }

    public static Size convertSize(android.hardware.Camera.Size size)
    {
        Preconditions.checkNotNull(size, "size must not be null");
        return new Size(size.width, size.height);
    }

    public static List convertSizeList(List list)
    {
        Preconditions.checkNotNull(list, "sizeList must not be null");
        ArrayList arraylist = new ArrayList(list.size());
        android.hardware.Camera.Size size;
        for(list = list.iterator(); list.hasNext(); arraylist.add(new Size(size.width, size.height)))
            size = (android.hardware.Camera.Size)list.next();

        return arraylist;
    }

    public static Size[] convertSizeListToArray(List list)
    {
        Preconditions.checkNotNull(list, "sizeList must not be null");
        Size asize[] = new Size[list.size()];
        int i = 0;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            list = (android.hardware.Camera.Size)iterator.next();
            asize[i] = new Size(((android.hardware.Camera.Size) (list)).width, ((android.hardware.Camera.Size) (list)).height);
            i++;
        }

        return asize;
    }

    private static List getAvailableCropRectangles(android.hardware.Camera.Parameters parameters, Rect rect, Size size)
    {
        Preconditions.checkNotNull(parameters, "params must not be null");
        Preconditions.checkNotNull(rect, "activeArray must not be null");
        Preconditions.checkNotNull(size, "streamSize must not be null");
        Rect rect1 = getPreviewCropRectangleUnzoomed(rect, size);
        if(!parameters.isZoomSupported())
            return new ArrayList(Arrays.asList(new Rect[] {
                rect1
            }));
        ArrayList arraylist = new ArrayList(parameters.getMaxZoom() + 1);
        size = new Matrix();
        RectF rectf = new RectF();
        for(parameters = parameters.getZoomRatios().iterator(); parameters.hasNext(); arraylist.add(ParamsUtils.createRect(rectf)))
        {
            float f = 100F / (float)((Integer)parameters.next()).intValue();
            ParamsUtils.convertRectF(rect1, rectf);
            size.setScale(f, f, rect.exactCenterX(), rect.exactCenterY());
            size.mapRect(rectf);
        }

        return arraylist;
    }

    public static List getAvailablePreviewZoomCropRectangles(android.hardware.Camera.Parameters parameters, Rect rect, Size size)
    {
        Preconditions.checkNotNull(parameters, "params must not be null");
        Preconditions.checkNotNull(rect, "activeArray must not be null");
        Preconditions.checkNotNull(size, "previewSize must not be null");
        return getAvailableCropRectangles(parameters, rect, size);
    }

    public static List getAvailableZoomCropRectangles(android.hardware.Camera.Parameters parameters, Rect rect)
    {
        Preconditions.checkNotNull(parameters, "params must not be null");
        Preconditions.checkNotNull(rect, "activeArray must not be null");
        return getAvailableCropRectangles(parameters, rect, ParamsUtils.createSize(rect));
    }

    public static int getClosestAvailableZoomCrop(android.hardware.Camera.Parameters parameters, Rect rect, Size size, Rect rect1, Rect rect2, Rect rect3)
    {
        Preconditions.checkNotNull(parameters, "params must not be null");
        Preconditions.checkNotNull(rect, "activeArray must not be null");
        Preconditions.checkNotNull(size, "streamSize must not be null");
        Preconditions.checkNotNull(rect2, "reportedCropRegion must not be null");
        Preconditions.checkNotNull(rect3, "previewCropRegion must not be null");
        rect1 = new Rect(rect1);
        if(!rect1.intersect(rect))
        {
            Log.w("ParameterUtils", "getClosestAvailableZoomCrop - Crop region out of range; setting to active array size");
            rect1.set(rect);
        }
        Rect rect4 = shrinkToSameAspectRatioCentered(getPreviewCropRectangleUnzoomed(rect, size), rect1);
        Object obj = null;
        rect1 = null;
        int i = -1;
        List list = getAvailableZoomCropRectangles(parameters, rect);
        List list1 = getAvailablePreviewZoomCropRectangles(parameters, rect, size);
        if(list.size() != list1.size())
            throw new AssertionError("available reported/preview crop region size mismatch");
        int j = 0;
        parameters = obj;
        rect = rect1;
        do
        {
            if(j < list.size())
            {
                rect1 = (Rect)list1.get(j);
                size = (Rect)list.get(j);
                boolean flag;
                if(i == -1)
                    flag = true;
                else
                if(rect1.width() >= rect4.width() && rect1.height() >= rect4.height())
                    flag = true;
                else
                    flag = false;
                if(flag)
                {
                    rect = rect1;
                    parameters = size;
                    i = j;
                    j++;
                    continue;
                }
            }
            if(i == -1)
                throw new AssertionError("Should've found at least one valid zoom index");
            rect2.set(parameters);
            rect3.set(rect);
            return i;
        } while(true);
    }

    public static Size getLargestSupportedJpegSizeByArea(android.hardware.Camera.Parameters parameters)
    {
        Preconditions.checkNotNull(parameters, "params must not be null");
        return SizeAreaComparator.findLargestByArea(convertSizeList(parameters.getSupportedPictureSizes()));
    }

    public static float getMaxZoomRatio(android.hardware.Camera.Parameters parameters)
    {
        if(!parameters.isZoomSupported())
        {
            return 1.0F;
        } else
        {
            parameters = parameters.getZoomRatios();
            return ((float)((Integer)parameters.get(parameters.size() - 1)).intValue() * 1.0F) / 100F;
        }
    }

    private static Rect getPreviewCropRectangleUnzoomed(Rect rect, Size size)
    {
        if(size.getWidth() > rect.width())
            throw new IllegalArgumentException("previewSize must not be wider than activeArray");
        if(size.getHeight() > rect.height())
            throw new IllegalArgumentException("previewSize must not be taller than activeArray");
        float f = ((float)rect.width() * 1.0F) / (float)rect.height();
        float f1 = ((float)size.getWidth() * 1.0F) / (float)size.getHeight();
        float f2;
        Matrix matrix;
        if((double)Math.abs(f1 - f) < 0.05000000074505806D)
        {
            f = rect.height();
            f2 = rect.width();
        } else
        if(f1 < f)
        {
            f = rect.height();
            f2 = f * f1;
        } else
        {
            f2 = rect.width();
            f = f2 / f1;
        }
        matrix = new Matrix();
        size = new RectF(0.0F, 0.0F, f2, f);
        matrix.setTranslate(rect.exactCenterX(), rect.exactCenterY());
        matrix.postTranslate(-size.centerX(), -size.centerY());
        matrix.mapRect(size);
        return ParamsUtils.createRect(size);
    }

    private static SizeF getZoomRatio(Size size, Size size1)
    {
        Preconditions.checkNotNull(size, "activeArraySize must not be null");
        Preconditions.checkNotNull(size1, "cropSize must not be null");
        Preconditions.checkArgumentPositive(size1.getWidth(), "cropSize.width must be positive");
        Preconditions.checkArgumentPositive(size1.getHeight(), "cropSize.height must be positive");
        return new SizeF(((float)size.getWidth() * 1.0F) / (float)size1.getWidth(), ((float)size.getHeight() * 1.0F) / (float)size1.getHeight());
    }

    private static Rect shrinkToSameAspectRatioCentered(Rect rect, Rect rect1)
    {
        float f = ((float)rect.width() * 1.0F) / (float)rect.height();
        float f1 = ((float)rect1.width() * 1.0F) / (float)rect1.height();
        float f2;
        Matrix matrix;
        RectF rectf;
        if(f1 < f)
        {
            f = rect.height();
            f2 = f * f1;
        } else
        {
            f2 = rect.width();
            f = f2 / f1;
        }
        matrix = new Matrix();
        rectf = new RectF(rect1);
        matrix.setScale(f2 / (float)rect.width(), f / (float)rect.height(), rect1.exactCenterX(), rect1.exactCenterY());
        matrix.mapRect(rectf);
        return ParamsUtils.createRect(rectf);
    }

    public static String stringFromArea(android.hardware.Camera.Area area)
    {
        if(area == null)
        {
            return null;
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            Rect rect = area.rect;
            stringbuilder.setLength(0);
            stringbuilder.append("([");
            stringbuilder.append(rect.left);
            stringbuilder.append(',');
            stringbuilder.append(rect.top);
            stringbuilder.append("][");
            stringbuilder.append(rect.right);
            stringbuilder.append(',');
            stringbuilder.append(rect.bottom);
            stringbuilder.append(']');
            stringbuilder.append(',');
            stringbuilder.append(area.weight);
            stringbuilder.append(')');
            return stringbuilder.toString();
        }
    }

    public static String stringFromAreaList(List list)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(list == null)
            return null;
        int i = 0;
        Iterator iterator = list.iterator();
        while(iterator.hasNext()) 
        {
            android.hardware.Camera.Area area = (android.hardware.Camera.Area)iterator.next();
            if(area == null)
                stringbuilder.append("null");
            else
                stringbuilder.append(stringFromArea(area));
            if(i != list.size() - 1)
                stringbuilder.append(", ");
            i++;
        }
        return stringbuilder.toString();
    }

    private static final double ASPECT_RATIO_TOLERANCE = 0.05000000074505806D;
    public static final android.hardware.Camera.Area CAMERA_AREA_DEFAULT;
    private static final boolean DEBUG = false;
    public static final Rect NORMALIZED_RECTANGLE_DEFAULT;
    public static final int NORMALIZED_RECTANGLE_MAX = 1000;
    public static final int NORMALIZED_RECTANGLE_MIN = -1000;
    public static final Rect RECTANGLE_EMPTY = new Rect(0, 0, 0, 0);
    private static final String TAG = "ParameterUtils";
    private static final int ZOOM_RATIO_MULTIPLIER = 100;

    static 
    {
        NORMALIZED_RECTANGLE_DEFAULT = new Rect(-1000, -1000, 1000, 1000);
        CAMERA_AREA_DEFAULT = new android.hardware.Camera.Area(new Rect(NORMALIZED_RECTANGLE_DEFAULT), 1);
    }
}
