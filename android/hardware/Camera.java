// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.app.ActivityThread;
import android.graphics.*;
import android.media.IAudioService;
import android.os.*;
import android.renderscript.*;
import android.system.OsConstants;
import android.util.Log;
import android.util.SeempLog;
import android.view.Surface;
import android.view.SurfaceHolder;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package android.hardware:
//            CameraInjector

public class Camera
{
    public static class Area
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof Area))
                return false;
            obj = (Area)obj;
            if(rect == null)
            {
                if(((Area) (obj)).rect != null)
                    return false;
            } else
            if(!rect.equals(((Area) (obj)).rect))
                return false;
            if(weight == ((Area) (obj)).weight)
                flag = true;
            return flag;
        }

        public Rect rect;
        public int weight;

        public Area(Rect rect1, int i)
        {
            rect = rect1;
            weight = i;
        }
    }

    public static interface AutoFocusCallback
    {

        public abstract void onAutoFocus(boolean flag, Camera camera);
    }

    public static interface AutoFocusMoveCallback
    {

        public abstract void onAutoFocusMoving(boolean flag, Camera camera);
    }

    public static interface CameraDataCallback
    {

        public abstract void onCameraData(int ai[], Camera camera);
    }

    public static class CameraInfo
    {

        public static final int CAMERA_FACING_BACK = 0;
        public static final int CAMERA_FACING_FRONT = 1;
        public static final int CAMERA_SUPPORT_MODE_NONZSL = 3;
        public static final int CAMERA_SUPPORT_MODE_ZSL = 2;
        public boolean canDisableShutterSound;
        public int facing;
        public int orientation;

        public CameraInfo()
        {
        }
    }

    public static interface CameraMetaDataCallback
    {

        public abstract void onCameraMetaData(byte abyte0[], Camera camera);
    }

    public class Coordinate
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof Coordinate))
                return false;
            obj = (Coordinate)obj;
            boolean flag1 = flag;
            if(xCoordinate == ((Coordinate) (obj)).xCoordinate)
            {
                flag1 = flag;
                if(yCoordinate == ((Coordinate) (obj)).yCoordinate)
                    flag1 = true;
            }
            return flag1;
        }

        final Camera this$0;
        public int xCoordinate;
        public int yCoordinate;

        public Coordinate(int i, int j)
        {
            this$0 = Camera.this;
            super();
            xCoordinate = i;
            yCoordinate = j;
        }
    }

    public static interface ErrorCallback
    {

        public abstract void onError(int i, Camera camera);
    }

    private class EventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            boolean flag;
            boolean flag2;
            flag = true;
            flag2 = false;
            message.what;
            JVM INSTR lookupswitch 12: default 116
        //                       1: 506
        //                       2: 145
        //                       4: 358
        //                       8: 420
        //                       16: 236
        //                       64: 324
        //                       128: 168
        //                       256: 202
        //                       1024: 472
        //                       2048: 565
        //                       4096: 611
        //                       8192: 683;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L1:
            Log.e("Camera", (new StringBuilder()).append("Unknown message type ").append(message.what).toString());
            return;
_L3:
            if(Camera._2D_get12(Camera.this) != null)
                Camera._2D_get12(Camera.this).onShutter();
            return;
_L8:
            if(Camera._2D_get11(Camera.this) != null)
                Camera._2D_get11(Camera.this).onPictureTaken((byte[])message.obj, mCamera);
            return;
_L9:
            if(Camera._2D_get7(Camera.this) != null)
                Camera._2D_get7(Camera.this).onPictureTaken((byte[])message.obj, mCamera);
            return;
_L6:
            PreviewCallback previewcallback = Camera._2D_get10(Camera.this);
            if(previewcallback != null)
            {
                if(Camera._2D_get8(Camera.this))
                    Camera._2D_set0(Camera.this, null);
                else
                if(!Camera._2D_get13(Camera.this))
                    Camera._2D_wrap1(Camera.this, true, false);
                CameraInjector.processPreviewFrame(mCamera, (byte[])message.obj);
                previewcallback.onPreviewFrame((byte[])message.obj, mCamera);
            }
            return;
_L7:
            if(Camera._2D_get9(Camera.this) != null)
                Camera._2D_get9(Camera.this).onPictureTaken((byte[])message.obj, mCamera);
            return;
_L4:
            Object obj = Camera._2D_get1(Camera.this);
            obj;
            JVM INSTR monitorenter ;
            AutoFocusCallback autofocuscallback = Camera._2D_get0(Camera.this);
            obj;
            JVM INSTR monitorexit ;
            if(autofocuscallback != null)
            {
                if(message.arg1 == 0)
                    flag = false;
                else
                    flag = true;
                autofocuscallback.onAutoFocus(flag, mCamera);
            }
            return;
            message;
            throw message;
_L5:
            if(Camera._2D_get14(Camera.this) != null)
            {
                OnZoomChangeListener onzoomchangelistener = Camera._2D_get14(Camera.this);
                int i = message.arg1;
                if(message.arg2 == 0)
                    flag = false;
                onzoomchangelistener.onZoomChange(i, flag, mCamera);
            }
            return;
_L10:
            if(Camera._2D_get6(Camera.this) != null)
                Camera._2D_get6(Camera.this).onFaceDetection((Face[])message.obj, mCamera);
            return;
_L2:
            Log.e("Camera", (new StringBuilder()).append("Error ").append(message.arg1).toString());
            if(Camera._2D_get5(Camera.this) != null)
                Camera._2D_get5(Camera.this).onError(message.arg1, mCamera);
            return;
_L11:
            if(Camera._2D_get2(Camera.this) != null)
            {
                AutoFocusMoveCallback autofocusmovecallback = Camera._2D_get2(Camera.this);
                boolean flag1;
                if(message.arg1 == 0)
                    flag1 = flag2;
                else
                    flag1 = true;
                autofocusmovecallback.onAutoFocusMoving(flag1, mCamera);
            }
            return;
_L12:
            int ai[] = new int[257];
            for(int j = 0; j < 257; j++)
                ai[j] = Camera._2D_wrap0((byte[])message.obj, j * 4);

            if(Camera._2D_get3(Camera.this) != null)
                Camera._2D_get3(Camera.this).onCameraData(ai, mCamera);
            return;
_L13:
            if(Camera._2D_get4(Camera.this) != null)
                Camera._2D_get4(Camera.this).onCameraMetaData((byte[])message.obj, mCamera);
            return;
        }

        private final Camera mCamera;
        final Camera this$0;

        public EventHandler(Camera camera1, Looper looper)
        {
            this$0 = Camera.this;
            super(looper);
            mCamera = camera1;
        }
    }

    public static class Face
    {

        public float ageFemale;
        public float ageMale;
        public float beautyscore;
        public int blinkDetected;
        public int faceRecognised;
        public float gender;
        public int id;
        public Point leftEye;
        public Point mouth;
        public float prob;
        public Rect rect;
        public Point rightEye;
        public int score;
        public int smileDegree;
        public int smileScore;

        public Face()
        {
            id = -1;
            leftEye = null;
            rightEye = null;
            mouth = null;
            smileDegree = 0;
            smileScore = 0;
            blinkDetected = 0;
            faceRecognised = 0;
        }
    }

    public static interface FaceDetectionListener
    {

        public abstract void onFaceDetection(Face aface[], Camera camera);
    }

    public static interface OnZoomChangeListener
    {

        public abstract void onZoomChange(int i, boolean flag, Camera camera);
    }

    public class Parameters
    {

        static Camera _2D_wrap0(Parameters parameters)
        {
            return parameters.getOuter();
        }

        private String cameraFormatForPixelFormat(int i)
        {
            switch(i)
            {
            default:
                return null;

            case 16: // '\020'
                return "yuv422sp";

            case 17: // '\021'
                return "yuv420sp";

            case 20: // '\024'
                return "yuv422i-yuyv";

            case 842094169: 
                return "yuv420p";

            case 4: // '\004'
                return "rgb565";

            case 256: 
                return "jpeg";
            }
        }

        private float getFloat(String s, float f)
        {
            float f1;
            try
            {
                f1 = Float.parseFloat((String)mMap.get(s));
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return f;
            }
            return f1;
        }

        private int getInt(String s, int i)
        {
            int j;
            try
            {
                j = Integer.parseInt((String)mMap.get(s));
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return i;
            }
            return j;
        }

        private Camera getOuter()
        {
            return Camera.this;
        }

        private int pixelFormatForCameraFormat(String s)
        {
            if(s == null)
                return 0;
            if(s.equals("yuv422sp"))
                return 16;
            if(s.equals("yuv420sp"))
                return 17;
            if(s.equals("yuv422i-yuyv"))
                return 20;
            if(s.equals("yuv420p"))
                return 0x32315659;
            if(s.equals("rgb565"))
                return 4;
            return !s.equals("jpeg") ? 0 : 256;
        }

        private void put(String s, String s1)
        {
            mMap.remove(s);
            mMap.put(s, s1);
        }

        private boolean same(String s, String s1)
        {
            if(s == null && s1 == null)
                return true;
            return s != null && s.equals(s1);
        }

        private void set(String s, List list)
        {
            if(list == null)
            {
                set(s, "(0,0,0,0,0)");
            } else
            {
                StringBuilder stringbuilder = new StringBuilder();
                for(int i = 0; i < list.size(); i++)
                {
                    Area area = (Area)list.get(i);
                    Rect rect = area.rect;
                    stringbuilder.append('(');
                    stringbuilder.append(rect.left);
                    stringbuilder.append(',');
                    stringbuilder.append(rect.top);
                    stringbuilder.append(',');
                    stringbuilder.append(rect.right);
                    stringbuilder.append(',');
                    stringbuilder.append(rect.bottom);
                    stringbuilder.append(',');
                    stringbuilder.append(area.weight);
                    stringbuilder.append(')');
                    if(i != list.size() - 1)
                        stringbuilder.append(',');
                }

                set(s, stringbuilder.toString());
            }
        }

        private ArrayList split(String s)
        {
            if(s == null)
                return null;
            Object obj = new android.text.TextUtils.SimpleStringSplitter(',');
            ((android.text.TextUtils.StringSplitter) (obj)).setString(s);
            s = new ArrayList();
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); s.add((String)((Iterator) (obj)).next()));
            return s;
        }

        private ArrayList splitArea(String s)
        {
            while(s == null || s.charAt(0) != '(' || s.charAt(s.length() - 1) != ')') 
            {
                Log.e("Camera", (new StringBuilder()).append("Invalid area string=").append(s).toString());
                return null;
            }
            ArrayList arraylist = new ArrayList();
            int i = 1;
            int ai[] = new int[5];
            int k;
            do
            {
                int j = s.indexOf("),(", i);
                k = j;
                if(j == -1)
                    k = s.length() - 1;
                splitInt(s.substring(i, k), ai);
                arraylist.add(new Area(new Rect(ai[0], ai[1], ai[2], ai[3]), ai[4]));
                i = k + 3;
            } while(k != s.length() - 1);
            if(arraylist.size() == 0)
                return null;
            if(arraylist.size() == 1)
            {
                Area area = (Area)arraylist.get(0);
                s = area.rect;
                if(((Rect) (s)).left == 0 && ((Rect) (s)).top == 0 && ((Rect) (s)).right == 0 && ((Rect) (s)).bottom == 0 && area.weight == 0)
                    return null;
            }
            return arraylist;
        }

        private ArrayList splitCoordinate(String s)
        {
            if(s == null)
                return null;
            android.text.TextUtils.SimpleStringSplitter simplestringsplitter = new android.text.TextUtils.SimpleStringSplitter(',');
            simplestringsplitter.setString(s);
            s = new ArrayList();
            Iterator iterator = simplestringsplitter.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Coordinate coordinate = strToCoordinate((String)iterator.next());
                if(coordinate != null)
                    s.add(coordinate);
            } while(true);
            if(s.size() == 0)
                return null;
            else
                return s;
        }

        private void splitFloat(String s, float af[])
        {
            if(s == null)
                return;
            android.text.TextUtils.SimpleStringSplitter simplestringsplitter = new android.text.TextUtils.SimpleStringSplitter(',');
            simplestringsplitter.setString(s);
            int i = 0;
            for(s = simplestringsplitter.iterator(); s.hasNext();)
            {
                af[i] = Float.parseFloat((String)s.next());
                i++;
            }

        }

        private ArrayList splitInt(String s)
        {
            if(s == null)
                return null;
            Object obj = new android.text.TextUtils.SimpleStringSplitter(',');
            ((android.text.TextUtils.StringSplitter) (obj)).setString(s);
            s = new ArrayList();
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); s.add(Integer.valueOf(Integer.parseInt((String)((Iterator) (obj)).next()))));
            if(s.size() == 0)
                return null;
            else
                return s;
        }

        private void splitInt(String s, int ai[])
        {
            if(s == null)
                return;
            android.text.TextUtils.SimpleStringSplitter simplestringsplitter = new android.text.TextUtils.SimpleStringSplitter(',');
            simplestringsplitter.setString(s);
            int i = 0;
            for(s = simplestringsplitter.iterator(); s.hasNext();)
            {
                ai[i] = Integer.parseInt((String)s.next());
                i++;
            }

        }

        private ArrayList splitRange(String s)
        {
            while(s == null || s.charAt(0) != '(' || s.charAt(s.length() - 1) != ')') 
            {
                Log.e("Camera", (new StringBuilder()).append("Invalid range list string=").append(s).toString());
                return null;
            }
            ArrayList arraylist = new ArrayList();
            int i = 1;
            int k;
            do
            {
                int ai[] = new int[2];
                int j = s.indexOf("),(", i);
                k = j;
                if(j == -1)
                    k = s.length() - 1;
                splitInt(s.substring(i, k), ai);
                arraylist.add(ai);
                i = k + 3;
            } while(k != s.length() - 1);
            if(arraylist.size() == 0)
                return null;
            else
                return arraylist;
        }

        private ArrayList splitSize(String s)
        {
            if(s == null)
                return null;
            android.text.TextUtils.SimpleStringSplitter simplestringsplitter = new android.text.TextUtils.SimpleStringSplitter(',');
            simplestringsplitter.setString(s);
            s = new ArrayList();
            Iterator iterator = simplestringsplitter.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Size size = strToSize((String)iterator.next());
                if(size != null)
                    s.add(size);
            } while(true);
            if(s.size() == 0)
                return null;
            else
                return s;
        }

        private Coordinate strToCoordinate(String s)
        {
            if(s == null)
                return null;
            int i = s.indexOf('x');
            if(i != -1)
            {
                String s1 = s.substring(0, i);
                s = s.substring(i + 1);
                return new Coordinate(Integer.parseInt(s1), Integer.parseInt(s));
            } else
            {
                Log.e("Camera", (new StringBuilder()).append("Invalid Coordinate parameter string=").append(s).toString());
                return null;
            }
        }

        private Size strToSize(String s)
        {
            if(s == null)
                return null;
            int i = s.indexOf('x');
            if(i != -1)
            {
                String s1 = s.substring(0, i);
                s = s.substring(i + 1);
                return new Size(Integer.parseInt(s1), Integer.parseInt(s));
            } else
            {
                Log.e("Camera", (new StringBuilder()).append("Invalid size parameter string=").append(s).toString());
                return null;
            }
        }

        public void copyFrom(Parameters parameters)
        {
            if(parameters == null)
            {
                throw new NullPointerException("other must not be null");
            } else
            {
                mMap.putAll(parameters.mMap);
                return;
            }
        }

        public void dump()
        {
            Log.e("Camera", (new StringBuilder()).append("dump: size=").append(mMap.size()).toString());
            String s;
            for(Iterator iterator = mMap.keySet().iterator(); iterator.hasNext(); Log.e("Camera", (new StringBuilder()).append("dump: ").append(s).append("=").append((String)mMap.get(s)).toString()))
                s = (String)iterator.next();

        }

        public String flatten()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            for(Iterator iterator = mMap.keySet().iterator(); iterator.hasNext(); stringbuilder.append(";"))
            {
                String s = (String)iterator.next();
                stringbuilder.append(s);
                stringbuilder.append("=");
                stringbuilder.append((String)mMap.get(s));
            }

            stringbuilder.deleteCharAt(stringbuilder.length() - 1);
            return stringbuilder.toString();
        }

        public String get(String s)
        {
            return (String)mMap.get(s);
        }

        public String getAEBracket()
        {
            return get("ae-bracket-hdr");
        }

        public String getAntibanding()
        {
            return get("antibanding");
        }

        public String getAutoExposure()
        {
            return get("auto-exposure");
        }

        public boolean getAutoExposureLock()
        {
            return "true".equals(get("auto-exposure-lock"));
        }

        public boolean getAutoWhiteBalanceLock()
        {
            return "true".equals(get("auto-whitebalance-lock"));
        }

        public String getCameraMode()
        {
            return get("camera-mode");
        }

        public String getColorEffect()
        {
            return get("effect");
        }

        public String getContinuousAf()
        {
            return get("continuous-af");
        }

        public int getContrast()
        {
            return getInt("contrast");
        }

        public String getCurrentFocusPosition()
        {
            return get("manual-focus-position");
        }

        public String getDenoise()
        {
            return get("denoise");
        }

        public int getExposureCompensation()
        {
            return getInt("exposure-compensation", 0);
        }

        public float getExposureCompensationStep()
        {
            return getFloat("exposure-compensation-step", 0.0F);
        }

        public String getExposureTime()
        {
            return get("exposure-time");
        }

        public String getFaceDetectionMode()
        {
            return get("face-detection");
        }

        public String getFlashMode()
        {
            return get("flash-mode");
        }

        public float getFocalLength()
        {
            return Float.parseFloat(get("focal-length"));
        }

        public List getFocusAreas()
        {
            return splitArea(get("focus-areas"));
        }

        public void getFocusDistances(float af[])
        {
            if(af == null || af.length != 3)
            {
                throw new IllegalArgumentException("output must be a float array with three elements.");
            } else
            {
                splitFloat(get("focus-distances"), af);
                return;
            }
        }

        public String getFocusMode()
        {
            return get("focus-mode");
        }

        public float getHorizontalViewAngle()
        {
            return Float.parseFloat(get("horizontal-view-angle"));
        }

        public String getISOValue()
        {
            return get("iso");
        }

        public int getInt(String s)
        {
            return Integer.parseInt((String)mMap.get(s));
        }

        public int getJpegQuality()
        {
            return getInt("jpeg-quality");
        }

        public int getJpegThumbnailQuality()
        {
            return getInt("jpeg-thumbnail-quality");
        }

        public Size getJpegThumbnailSize()
        {
            return new Size(getInt("jpeg-thumbnail-width"), getInt("jpeg-thumbnail-height"));
        }

        public String getLensShade()
        {
            return get("lensshade");
        }

        public int getMaxContrast()
        {
            return getInt("max-contrast");
        }

        public int getMaxExposureCompensation()
        {
            return getInt("max-exposure-compensation", 0);
        }

        public String getMaxExposureTime()
        {
            return get("max-exposure-time");
        }

        public int getMaxNumDetectedFaces()
        {
            return getInt("max-num-detected-faces-hw", 0);
        }

        public int getMaxNumFocusAreas()
        {
            return getInt("max-num-focus-areas", 0);
        }

        public int getMaxNumMeteringAreas()
        {
            return getInt("max-num-metering-areas", 0);
        }

        public int getMaxSaturation()
        {
            return getInt("max-saturation");
        }

        public int getMaxSharpness()
        {
            return getInt("max-sharpness");
        }

        public String getMaxWBCCT()
        {
            return get("max-wb-cct");
        }

        public int getMaxZoom()
        {
            return getInt("max-zoom", 0);
        }

        public String getMemColorEnhance()
        {
            return get("mce");
        }

        public List getMeteringAreas()
        {
            return splitArea(get("metering-areas"));
        }

        public int getMinExposureCompensation()
        {
            return getInt("min-exposure-compensation", 0);
        }

        public String getMinExposureTime()
        {
            return get("min-exposure-time");
        }

        public int getPictureFormat()
        {
            return pixelFormatForCameraFormat(get("picture-format"));
        }

        public Size getPictureSize()
        {
            return strToSize(get("picture-size"));
        }

        public String getPowerMode()
        {
            return get("power-mode");
        }

        public Size getPreferredPreviewSizeForVideo()
        {
            return strToSize(get("preferred-preview-size-for-video"));
        }

        public int getPreviewFormat()
        {
            return pixelFormatForCameraFormat(get("preview-format"));
        }

        public void getPreviewFpsRange(int ai[])
        {
            if(ai == null || ai.length != 2)
            {
                throw new IllegalArgumentException("range must be an array with two elements.");
            } else
            {
                splitInt(get("preview-fps-range"), ai);
                return;
            }
        }

        public int getPreviewFrameRate()
        {
            return getInt("preview-frame-rate");
        }

        public String getPreviewFrameRateMode()
        {
            return get("preview-frame-rate-mode");
        }

        public Size getPreviewSize()
        {
            return strToSize(get("preview-size"));
        }

        public String getRedeyeReductionMode()
        {
            return get("redeye-reduction");
        }

        public int getSaturation()
        {
            return getInt("saturation");
        }

        public String getSceneDetectMode()
        {
            return get("scene-detect");
        }

        public String getSceneMode()
        {
            return get("scene-mode");
        }

        public String getSelectableZoneAf()
        {
            return get("selectable-zone-af");
        }

        public int getSharpness()
        {
            return getInt("sharpness");
        }

        public List getSupportedAntibanding()
        {
            return split(get("antibanding-values"));
        }

        public List getSupportedAutoexposure()
        {
            return split(get("auto-exposure-values"));
        }

        public List getSupportedColorEffects()
        {
            return split(get("effect-values"));
        }

        public List getSupportedContinuousAfModes()
        {
            return split(get("continuous-af-values"));
        }

        public List getSupportedDenoiseModes()
        {
            return split(get("denoise-values"));
        }

        public List getSupportedFaceDetectionModes()
        {
            return split(get("face-detection-values"));
        }

        public List getSupportedFlashModes()
        {
            return split(get("flash-mode-values"));
        }

        public List getSupportedFocusModes()
        {
            return split(get("focus-mode-values"));
        }

        public List getSupportedHfrSizes()
        {
            return splitSize(get("hfr-size-values"));
        }

        public List getSupportedHistogramModes()
        {
            return split(get("histogram-values"));
        }

        public List getSupportedIsoValues()
        {
            return split(get("iso-values"));
        }

        public List getSupportedJpegThumbnailSizes()
        {
            return splitSize(get("jpeg-thumbnail-size-values"));
        }

        public List getSupportedLensShadeModes()
        {
            return split(get("lensshade-values"));
        }

        public List getSupportedMemColorEnhanceModes()
        {
            return split(get("mce-values"));
        }

        public List getSupportedPictureFormats()
        {
            Object obj = get("picture-format-values");
            ArrayList arraylist = new ArrayList();
            obj = split(((String) (obj))).iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break;
                int i = pixelFormatForCameraFormat((String)((Iterator) (obj)).next());
                if(i != 0)
                    arraylist.add(Integer.valueOf(i));
            } while(true);
            return arraylist;
        }

        public List getSupportedPictureSizes()
        {
            return splitSize(get("picture-size-values"));
        }

        public List getSupportedPreviewFormats()
        {
            Object obj = get("preview-format-values");
            ArrayList arraylist = new ArrayList();
            obj = split(((String) (obj))).iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break;
                int i = pixelFormatForCameraFormat((String)((Iterator) (obj)).next());
                if(i != 0)
                    arraylist.add(Integer.valueOf(i));
            } while(true);
            return arraylist;
        }

        public List getSupportedPreviewFpsRange()
        {
            return splitRange(get("preview-fps-range-values"));
        }

        public List getSupportedPreviewFrameRateModes()
        {
            return split(get("preview-frame-rate-mode-values"));
        }

        public List getSupportedPreviewFrameRates()
        {
            return splitInt(get("preview-frame-rate-values"));
        }

        public List getSupportedPreviewSizes()
        {
            return splitSize(get("preview-size-values"));
        }

        public List getSupportedRedeyeReductionModes()
        {
            return split(get("redeye-reduction-values"));
        }

        public List getSupportedSceneDetectModes()
        {
            return split(get("scene-detect-values"));
        }

        public List getSupportedSceneModes()
        {
            return split(get("scene-mode-values"));
        }

        public List getSupportedSelectableZoneAf()
        {
            return split(get("selectable-zone-af-values"));
        }

        public List getSupportedSkinToneEnhancementModes()
        {
            return split(get("skinToneEnhancement-values"));
        }

        public List getSupportedTouchAfAec()
        {
            return split(get("touch-af-aec-values"));
        }

        public List getSupportedVideoHDRModes()
        {
            return split(get("video-hdr-values"));
        }

        public List getSupportedVideoHighFrameRateModes()
        {
            return split(get("video-hfr-values"));
        }

        public List getSupportedVideoRotationValues()
        {
            return split(get("video-rotation-values"));
        }

        public List getSupportedVideoSizes()
        {
            return splitSize(get("video-size-values"));
        }

        public List getSupportedWhiteBalance()
        {
            return split(get("whitebalance-values"));
        }

        public List getSupportedZSLModes()
        {
            return split(get("zsl-values"));
        }

        public String getTouchAfAec()
        {
            return get("touch-af-aec");
        }

        public Coordinate getTouchIndexAec()
        {
            return strToCoordinate(get("touch-index-aec"));
        }

        public Coordinate getTouchIndexAf()
        {
            return strToCoordinate(get("touch-index-af"));
        }

        public float getVerticalViewAngle()
        {
            return Float.parseFloat(get("vertical-view-angle"));
        }

        public String getVideoHDRMode()
        {
            return get("video-hdr");
        }

        public String getVideoHighFrameRate()
        {
            return get("video-hfr");
        }

        public String getVideoRotation()
        {
            return get("video-rotation");
        }

        public boolean getVideoStabilization()
        {
            return "true".equals(get("video-stabilization"));
        }

        public String getWBCurrentCCT()
        {
            return get("wb-manual-cct");
        }

        public String getWBMinCCT()
        {
            return get("min-wb-cct");
        }

        public String getWhiteBalance()
        {
            return get("whitebalance");
        }

        public String getZSLMode()
        {
            return get("zsl");
        }

        public int getZoom()
        {
            return getInt("zoom", 0);
        }

        public List getZoomRatios()
        {
            return splitInt(get("zoom-ratios"));
        }

        public boolean isAutoExposureLockSupported()
        {
            return "true".equals(get("auto-exposure-lock-supported"));
        }

        public boolean isAutoWhiteBalanceLockSupported()
        {
            return "true".equals(get("auto-whitebalance-lock-supported"));
        }

        public boolean isPowerModeSupported()
        {
            return "true".equals(get("power-mode-supported"));
        }

        public boolean isSmoothZoomSupported()
        {
            return "true".equals(get("smooth-zoom-supported"));
        }

        public boolean isVideoSnapshotSupported()
        {
            return "true".equals(get("video-snapshot-supported"));
        }

        public boolean isVideoStabilizationSupported()
        {
            return "true".equals(get("video-stabilization-supported"));
        }

        public boolean isZoomSupported()
        {
            return "true".equals(get("zoom-supported"));
        }

        public void remove(String s)
        {
            mMap.remove(s);
        }

        public void removeGpsData()
        {
            remove("gps-latitude-ref");
            remove("gps-latitude");
            remove("gps-longitude-ref");
            remove("gps-longitude");
            remove("gps-altitude-ref");
            remove("gps-altitude");
            remove("gps-timestamp");
            remove("gps-processing-method");
        }

        public boolean same(Parameters parameters)
        {
            if(this == parameters)
                return true;
            boolean flag;
            if(parameters != null)
                flag = mMap.equals(parameters.mMap);
            else
                flag = false;
            return flag;
        }

        public void set(String s, int i)
        {
            put(s, Integer.toString(i));
        }

        public void set(String s, String s1)
        {
            while(s.indexOf('=') != -1 || s.indexOf(';') != -1 || s.indexOf('\0') != -1) 
            {
                Log.e("Camera", (new StringBuilder()).append("Key \"").append(s).append("\" contains invalid character (= or ; or \\0)").toString());
                return;
            }
            while(s1.indexOf('=') != -1 || s1.indexOf(';') != -1 || s1.indexOf('\0') != -1) 
            {
                Log.e("Camera", (new StringBuilder()).append("Value \"").append(s1).append("\" contains invalid character (= or ; or \\0)").toString());
                return;
            }
            put(s, s1);
        }

        public void setAEBracket(String s)
        {
            set("ae-bracket-hdr", s);
        }

        public void setAntibanding(String s)
        {
            set("antibanding", s);
        }

        public void setAutoExposure(String s)
        {
            set("auto-exposure", s);
        }

        public void setAutoExposureLock(boolean flag)
        {
            String s;
            if(flag)
                s = "true";
            else
                s = "false";
            set("auto-exposure-lock", s);
        }

        public void setAutoHDRMode(String s)
        {
            set("auto-hdr-enable", s);
        }

        public void setAutoWhiteBalanceLock(boolean flag)
        {
            String s;
            if(flag)
                s = "true";
            else
                s = "false";
            set("auto-whitebalance-lock", s);
        }

        public void setCameraMode(int i)
        {
            set("camera-mode", i);
        }

        public void setColorEffect(String s)
        {
            set("effect", s);
        }

        public void setContinuousAf(String s)
        {
            set("continuous-af", s);
        }

        public void setContrast(int i)
        {
            if(i < 0 || i > getMaxContrast())
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid Contrast ").append(i).toString());
            } else
            {
                set("contrast", String.valueOf(i));
                return;
            }
        }

        public void setDenoise(String s)
        {
            set("denoise", s);
        }

        public void setExifDateTime(String s)
        {
            set("exif-datetime", s);
        }

        public void setExposureCompensation(int i)
        {
            set("exposure-compensation", i);
        }

        public void setExposureTime(int i)
        {
            set("exposure-time", Integer.toString(i));
        }

        public void setFaceDetectionMode(String s)
        {
            set("face-detection", s);
        }

        public void setFlashMode(String s)
        {
            set("flash-mode", s);
        }

        public void setFocusAreas(List list)
        {
            set("focus-areas", list);
        }

        public void setFocusMode(String s)
        {
            set("focus-mode", s);
        }

        public void setFocusPosition(int i, int j)
        {
            set("manual-focus-pos-type", Integer.toString(i));
            set("manual-focus-position", Integer.toString(j));
        }

        public void setGpsAltitude(double d)
        {
            set("gps-altitude", Double.toString(d));
        }

        public void setGpsAltitudeRef(double d)
        {
            set("gps-altitude-ref", Double.toString(d));
        }

        public void setGpsLatitude(double d)
        {
            set("gps-latitude", Double.toString(d));
        }

        public void setGpsLatitudeRef(String s)
        {
            set("gps-latitude-ref", s);
        }

        public void setGpsLongitude(double d)
        {
            set("gps-longitude", Double.toString(d));
        }

        public void setGpsLongitudeRef(String s)
        {
            set("gps-longitude-ref", s);
        }

        public void setGpsProcessingMethod(String s)
        {
            set("gps-processing-method", s);
        }

        public void setGpsStatus(double d)
        {
            set("gps-status", Double.toString(d));
        }

        public void setGpsTimestamp(long l)
        {
            set("gps-timestamp", Long.toString(l));
        }

        public void setISOValue(String s)
        {
            set("iso", s);
        }

        public void setJpegQuality(int i)
        {
            set("jpeg-quality", i);
        }

        public void setJpegThumbnailQuality(int i)
        {
            set("jpeg-thumbnail-quality", i);
        }

        public void setJpegThumbnailSize(int i, int j)
        {
            set("jpeg-thumbnail-width", i);
            set("jpeg-thumbnail-height", j);
        }

        public void setLensShade(String s)
        {
            set("lensshade", s);
        }

        public void setMemColorEnhance(String s)
        {
            set("mce", s);
        }

        public void setMeteringAreas(List list)
        {
            set("metering-areas", list);
        }

        public void setPictureFormat(int i)
        {
            String s = cameraFormatForPixelFormat(i);
            if(s == null)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid pixel_format=").append(i).toString());
            } else
            {
                set("picture-format", s);
                return;
            }
        }

        public void setPictureSize(int i, int j)
        {
            set("picture-size", (new StringBuilder()).append(Integer.toString(i)).append("x").append(Integer.toString(j)).toString());
        }

        public void setPowerMode(String s)
        {
            set("power-mode", s);
        }

        public void setPreviewFormat(int i)
        {
            String s = cameraFormatForPixelFormat(i);
            if(s == null)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid pixel_format=").append(i).toString());
            } else
            {
                set("preview-format", s);
                return;
            }
        }

        public void setPreviewFpsRange(int i, int j)
        {
            set("preview-fps-range", (new StringBuilder()).append("").append(i).append(",").append(j).toString());
        }

        public void setPreviewFrameRate(int i)
        {
            set("preview-frame-rate", i);
        }

        public void setPreviewFrameRateMode(String s)
        {
            set("preview-frame-rate-mode", s);
        }

        public void setPreviewSize(int i, int j)
        {
            set("preview-size", (new StringBuilder()).append(Integer.toString(i)).append("x").append(Integer.toString(j)).toString());
        }

        public void setRecordingHint(boolean flag)
        {
            String s;
            if(flag)
                s = "true";
            else
                s = "false";
            set("recording-hint", s);
        }

        public void setRedeyeReductionMode(String s)
        {
            set("redeye-reduction", s);
        }

        public void setRotation(int i)
        {
            while(i == 0 || i == 90 || i == 180 || i == 270) 
            {
                set("rotation", Integer.toString(i));
                return;
            }
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid rotation=").append(i).toString());
        }

        public void setSaturation(int i)
        {
            if(i < 0 || i > getMaxSaturation())
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid Saturation ").append(i).toString());
            } else
            {
                set("saturation", String.valueOf(i));
                return;
            }
        }

        public void setSceneDetectMode(String s)
        {
            set("scene-detect", s);
        }

        public void setSceneMode(String s)
        {
            set("scene-mode", s);
        }

        public void setSelectableZoneAf(String s)
        {
            set("selectable-zone-af", s);
        }

        public void setSharpness(int i)
        {
            if(i < 0 || i > getMaxSharpness())
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid Sharpness ").append(i).toString());
            } else
            {
                set("sharpness", String.valueOf(i));
                return;
            }
        }

        public void setTouchAfAec(String s)
        {
            set("touch-af-aec", s);
        }

        public void setTouchIndexAec(int i, int j)
        {
            set("touch-index-aec", (new StringBuilder()).append(Integer.toString(i)).append("x").append(Integer.toString(j)).toString());
        }

        public void setTouchIndexAf(int i, int j)
        {
            set("touch-index-af", (new StringBuilder()).append(Integer.toString(i)).append("x").append(Integer.toString(j)).toString());
        }

        public void setVideoHDRMode(String s)
        {
            set("video-hdr", s);
        }

        public void setVideoHighFrameRate(String s)
        {
            set("video-hfr", s);
        }

        public void setVideoRotation(String s)
        {
            set("video-rotation", s);
        }

        public void setVideoStabilization(boolean flag)
        {
            String s;
            if(flag)
                s = "true";
            else
                s = "false";
            set("video-stabilization", s);
        }

        public void setWBManualCCT(int i)
        {
            set("wb-manual-cct", Integer.toString(i));
        }

        public void setWhiteBalance(String s)
        {
            if(same(s, get("whitebalance")))
            {
                return;
            } else
            {
                set("whitebalance", s);
                set("auto-whitebalance-lock", "false");
                return;
            }
        }

        public void setZSLMode(String s)
        {
            set("zsl", s);
        }

        public void setZoom(int i)
        {
            set("zoom", i);
        }

        public void unflatten(String s)
        {
            mMap.clear();
            Object obj = new android.text.TextUtils.SimpleStringSplitter(';');
            ((android.text.TextUtils.StringSplitter) (obj)).setString(s);
            obj = ((Iterable) (obj)).iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break;
                String s1 = (String)((Iterator) (obj)).next();
                int i = s1.indexOf('=');
                if(i != -1)
                {
                    s = s1.substring(0, i);
                    s1 = s1.substring(i + 1);
                    mMap.put(s, s1);
                }
            } while(true);
        }

        public static final String AE_BRACKET = "AE-Bracket";
        public static final String AE_BRACKET_HDR = "HDR";
        public static final String AE_BRACKET_HDR_OFF = "Off";
        public static final String ANTIBANDING_50HZ = "50hz";
        public static final String ANTIBANDING_60HZ = "60hz";
        public static final String ANTIBANDING_AUTO = "auto";
        public static final String ANTIBANDING_OFF = "off";
        public static final String AUTO_EXPOSURE_CENTER_WEIGHTED = "center-weighted";
        public static final String AUTO_EXPOSURE_FRAME_AVG = "frame-average";
        public static final String AUTO_EXPOSURE_SPOT_METERING = "spot-metering";
        public static final String CONTINUOUS_AF_OFF = "caf-off";
        public static final String CONTINUOUS_AF_ON = "caf-on";
        public static final String DENOISE_OFF = "denoise-off";
        public static final String DENOISE_ON = "denoise-on";
        public static final String EFFECT_AQUA = "aqua";
        public static final String EFFECT_BLACKBOARD = "blackboard";
        public static final String EFFECT_MONO = "mono";
        public static final String EFFECT_NEGATIVE = "negative";
        public static final String EFFECT_NONE = "none";
        public static final String EFFECT_POSTERIZE = "posterize";
        public static final String EFFECT_SEPIA = "sepia";
        public static final String EFFECT_SOLARIZE = "solarize";
        public static final String EFFECT_WHITEBOARD = "whiteboard";
        public static final String FACE_DETECTION_OFF = "off";
        public static final String FACE_DETECTION_ON = "on";
        private static final String FALSE = "false";
        public static final String FLASH_MODE_AUTO = "auto";
        public static final String FLASH_MODE_OFF = "off";
        public static final String FLASH_MODE_ON = "on";
        public static final String FLASH_MODE_RED_EYE = "red-eye";
        public static final String FLASH_MODE_TORCH = "torch";
        public static final int FOCUS_DISTANCE_FAR_INDEX = 2;
        public static final int FOCUS_DISTANCE_NEAR_INDEX = 0;
        public static final int FOCUS_DISTANCE_OPTIMAL_INDEX = 1;
        public static final String FOCUS_MODE_AUTO = "auto";
        public static final String FOCUS_MODE_CONTINUOUS_PICTURE = "continuous-picture";
        public static final String FOCUS_MODE_CONTINUOUS_VIDEO = "continuous-video";
        public static final String FOCUS_MODE_EDOF = "edof";
        public static final String FOCUS_MODE_FIXED = "fixed";
        public static final String FOCUS_MODE_INFINITY = "infinity";
        public static final String FOCUS_MODE_MACRO = "macro";
        public static final String FOCUS_MODE_MANUAL_POSITION = "manual";
        public static final String FOCUS_MODE_NORMAL = "normal";
        public static final String HISTOGRAM_DISABLE = "disable";
        public static final String HISTOGRAM_ENABLE = "enable";
        public static final String ISO_100 = "ISO100";
        public static final String ISO_1600 = "ISO1600";
        public static final String ISO_200 = "ISO200";
        public static final String ISO_3200 = "ISO3200";
        public static final String ISO_400 = "ISO400";
        public static final String ISO_800 = "ISO800";
        public static final String ISO_AUTO = "auto";
        public static final String ISO_HJR = "ISO_HJR";
        private static final String KEY_ANTIBANDING = "antibanding";
        private static final String KEY_AUTO_EXPOSURE_LOCK = "auto-exposure-lock";
        private static final String KEY_AUTO_EXPOSURE_LOCK_SUPPORTED = "auto-exposure-lock-supported";
        private static final String KEY_AUTO_WHITEBALANCE_LOCK = "auto-whitebalance-lock";
        private static final String KEY_AUTO_WHITEBALANCE_LOCK_SUPPORTED = "auto-whitebalance-lock-supported";
        private static final String KEY_EFFECT = "effect";
        private static final String KEY_EXPOSURE_COMPENSATION = "exposure-compensation";
        private static final String KEY_EXPOSURE_COMPENSATION_STEP = "exposure-compensation-step";
        private static final String KEY_FLASH_MODE = "flash-mode";
        private static final String KEY_FOCAL_LENGTH = "focal-length";
        private static final String KEY_FOCUS_AREAS = "focus-areas";
        private static final String KEY_FOCUS_DISTANCES = "focus-distances";
        private static final String KEY_FOCUS_MODE = "focus-mode";
        private static final String KEY_GPS_ALTITUDE = "gps-altitude";
        private static final String KEY_GPS_LATITUDE = "gps-latitude";
        private static final String KEY_GPS_LONGITUDE = "gps-longitude";
        private static final String KEY_GPS_PROCESSING_METHOD = "gps-processing-method";
        private static final String KEY_GPS_TIMESTAMP = "gps-timestamp";
        private static final String KEY_HORIZONTAL_VIEW_ANGLE = "horizontal-view-angle";
        private static final String KEY_JPEG_QUALITY = "jpeg-quality";
        private static final String KEY_JPEG_THUMBNAIL_HEIGHT = "jpeg-thumbnail-height";
        private static final String KEY_JPEG_THUMBNAIL_QUALITY = "jpeg-thumbnail-quality";
        private static final String KEY_JPEG_THUMBNAIL_SIZE = "jpeg-thumbnail-size";
        private static final String KEY_JPEG_THUMBNAIL_WIDTH = "jpeg-thumbnail-width";
        private static final String KEY_MAX_EXPOSURE_COMPENSATION = "max-exposure-compensation";
        private static final String KEY_MAX_NUM_DETECTED_FACES_HW = "max-num-detected-faces-hw";
        private static final String KEY_MAX_NUM_DETECTED_FACES_SW = "max-num-detected-faces-sw";
        private static final String KEY_MAX_NUM_FOCUS_AREAS = "max-num-focus-areas";
        private static final String KEY_MAX_NUM_METERING_AREAS = "max-num-metering-areas";
        private static final String KEY_MAX_ZOOM = "max-zoom";
        private static final String KEY_METERING_AREAS = "metering-areas";
        private static final String KEY_MIN_EXPOSURE_COMPENSATION = "min-exposure-compensation";
        private static final String KEY_PICTURE_FORMAT = "picture-format";
        private static final String KEY_PICTURE_SIZE = "picture-size";
        private static final String KEY_PREFERRED_PREVIEW_SIZE_FOR_VIDEO = "preferred-preview-size-for-video";
        private static final String KEY_PREVIEW_FORMAT = "preview-format";
        private static final String KEY_PREVIEW_FPS_RANGE = "preview-fps-range";
        private static final String KEY_PREVIEW_FRAME_RATE = "preview-frame-rate";
        private static final String KEY_PREVIEW_SIZE = "preview-size";
        public static final String KEY_QC_AE_BRACKET_HDR = "ae-bracket-hdr";
        private static final String KEY_QC_AUTO_EXPOSURE = "auto-exposure";
        private static final String KEY_QC_AUTO_HDR_ENABLE = "auto-hdr-enable";
        private static final String KEY_QC_CAMERA_MODE = "camera-mode";
        private static final String KEY_QC_CONTINUOUS_AF = "continuous-af";
        private static final String KEY_QC_CONTRAST = "contrast";
        private static final String KEY_QC_DENOISE = "denoise";
        private static final String KEY_QC_EXIF_DATETIME = "exif-datetime";
        private static final String KEY_QC_EXPOSURE_TIME = "exposure-time";
        private static final String KEY_QC_FACE_DETECTION = "face-detection";
        private static final String KEY_QC_GPS_ALTITUDE_REF = "gps-altitude-ref";
        private static final String KEY_QC_GPS_LATITUDE_REF = "gps-latitude-ref";
        private static final String KEY_QC_GPS_LONGITUDE_REF = "gps-longitude-ref";
        private static final String KEY_QC_GPS_STATUS = "gps-status";
        private static final String KEY_QC_HFR_SIZE = "hfr-size";
        private static final String KEY_QC_HISTOGRAM = "histogram";
        private static final String KEY_QC_ISO_MODE = "iso";
        private static final String KEY_QC_LENSSHADE = "lensshade";
        private static final String KEY_QC_MANUAL_FOCUS_POSITION = "manual-focus-position";
        private static final String KEY_QC_MANUAL_FOCUS_POS_TYPE = "manual-focus-pos-type";
        private static final String KEY_QC_MAX_CONTRAST = "max-contrast";
        private static final String KEY_QC_MAX_EXPOSURE_TIME = "max-exposure-time";
        private static final String KEY_QC_MAX_SATURATION = "max-saturation";
        private static final String KEY_QC_MAX_SHARPNESS = "max-sharpness";
        private static final String KEY_QC_MAX_WB_CCT = "max-wb-cct";
        private static final String KEY_QC_MEMORY_COLOR_ENHANCEMENT = "mce";
        private static final String KEY_QC_MIN_EXPOSURE_TIME = "min-exposure-time";
        private static final String KEY_QC_MIN_WB_CCT = "min-wb-cct";
        private static final String KEY_QC_POWER_MODE = "power-mode";
        private static final String KEY_QC_POWER_MODE_SUPPORTED = "power-mode-supported";
        private static final String KEY_QC_PREVIEW_FRAME_RATE_AUTO_MODE = "frame-rate-auto";
        private static final String KEY_QC_PREVIEW_FRAME_RATE_FIXED_MODE = "frame-rate-fixed";
        private static final String KEY_QC_PREVIEW_FRAME_RATE_MODE = "preview-frame-rate-mode";
        private static final String KEY_QC_REDEYE_REDUCTION = "redeye-reduction";
        private static final String KEY_QC_SATURATION = "saturation";
        private static final String KEY_QC_SCENE_DETECT = "scene-detect";
        private static final String KEY_QC_SELECTABLE_ZONE_AF = "selectable-zone-af";
        private static final String KEY_QC_SHARPNESS = "sharpness";
        private static final String KEY_QC_SKIN_TONE_ENHANCEMENT = "skinToneEnhancement";
        private static final String KEY_QC_TOUCH_AF_AEC = "touch-af-aec";
        private static final String KEY_QC_TOUCH_INDEX_AEC = "touch-index-aec";
        private static final String KEY_QC_TOUCH_INDEX_AF = "touch-index-af";
        private static final String KEY_QC_VIDEO_HDR = "video-hdr";
        private static final String KEY_QC_VIDEO_HIGH_FRAME_RATE = "video-hfr";
        private static final String KEY_QC_VIDEO_ROTATION = "video-rotation";
        private static final String KEY_QC_WB_MANUAL_CCT = "wb-manual-cct";
        private static final String KEY_QC_ZSL = "zsl";
        private static final String KEY_RECORDING_HINT = "recording-hint";
        private static final String KEY_ROTATION = "rotation";
        private static final String KEY_SCENE_MODE = "scene-mode";
        private static final String KEY_SMOOTH_ZOOM_SUPPORTED = "smooth-zoom-supported";
        private static final String KEY_VERTICAL_VIEW_ANGLE = "vertical-view-angle";
        private static final String KEY_VIDEO_SIZE = "video-size";
        private static final String KEY_VIDEO_SNAPSHOT_SUPPORTED = "video-snapshot-supported";
        private static final String KEY_VIDEO_STABILIZATION = "video-stabilization";
        private static final String KEY_VIDEO_STABILIZATION_SUPPORTED = "video-stabilization-supported";
        private static final String KEY_WHITE_BALANCE = "whitebalance";
        private static final String KEY_ZOOM = "zoom";
        private static final String KEY_ZOOM_RATIOS = "zoom-ratios";
        private static final String KEY_ZOOM_SUPPORTED = "zoom-supported";
        public static final String LENSSHADE_DISABLE = "disable";
        public static final String LENSSHADE_ENABLE = "enable";
        public static final String LOW_POWER = "Low_Power";
        private static final int MANUAL_FOCUS_POS_TYPE_DAC = 1;
        private static final int MANUAL_FOCUS_POS_TYPE_INDEX = 0;
        public static final String MCE_DISABLE = "disable";
        public static final String MCE_ENABLE = "enable";
        public static final String NORMAL_POWER = "Normal_Power";
        private static final String PIXEL_FORMAT_BAYER_RGGB = "bayer-rggb";
        private static final String PIXEL_FORMAT_JPEG = "jpeg";
        private static final String PIXEL_FORMAT_NV12 = "nv12";
        private static final String PIXEL_FORMAT_RAW = "raw";
        private static final String PIXEL_FORMAT_RGB565 = "rgb565";
        private static final String PIXEL_FORMAT_YUV420P = "yuv420p";
        private static final String PIXEL_FORMAT_YUV420SP = "yuv420sp";
        private static final String PIXEL_FORMAT_YUV420SP_ADRENO = "yuv420sp-adreno";
        private static final String PIXEL_FORMAT_YUV422I = "yuv422i-yuyv";
        private static final String PIXEL_FORMAT_YUV422SP = "yuv422sp";
        private static final String PIXEL_FORMAT_YV12 = "yv12";
        public static final int PREVIEW_FPS_MAX_INDEX = 1;
        public static final int PREVIEW_FPS_MIN_INDEX = 0;
        public static final String REDEYE_REDUCTION_DISABLE = "disable";
        public static final String REDEYE_REDUCTION_ENABLE = "enable";
        public static final String SCENE_DETECT_OFF = "off";
        public static final String SCENE_DETECT_ON = "on";
        public static final String SCENE_MODE_ACTION = "action";
        public static final String SCENE_MODE_ASD = "asd";
        public static final String SCENE_MODE_AUTO = "auto";
        public static final String SCENE_MODE_BACKLIGHT = "backlight";
        public static final String SCENE_MODE_BARCODE = "barcode";
        public static final String SCENE_MODE_BEACH = "beach";
        public static final String SCENE_MODE_CANDLELIGHT = "candlelight";
        public static final String SCENE_MODE_FIREWORKS = "fireworks";
        public static final String SCENE_MODE_FLOWERS = "flowers";
        public static final String SCENE_MODE_HDR = "hdr";
        public static final String SCENE_MODE_LANDSCAPE = "landscape";
        public static final String SCENE_MODE_NIGHT = "night";
        public static final String SCENE_MODE_NIGHT_PORTRAIT = "night-portrait";
        public static final String SCENE_MODE_PARTY = "party";
        public static final String SCENE_MODE_PORTRAIT = "portrait";
        public static final String SCENE_MODE_SNOW = "snow";
        public static final String SCENE_MODE_SPORTS = "sports";
        public static final String SCENE_MODE_STEADYPHOTO = "steadyphoto";
        public static final String SCENE_MODE_SUNSET = "sunset";
        public static final String SCENE_MODE_THEATRE = "theatre";
        public static final String SELECTABLE_ZONE_AF_AUTO = "auto";
        public static final String SELECTABLE_ZONE_AF_CENTER_WEIGHTED = "center-weighted";
        public static final String SELECTABLE_ZONE_AF_FRAME_AVERAGE = "frame-average";
        public static final String SELECTABLE_ZONE_AF_SPOTMETERING = "spot-metering";
        public static final String SKIN_TONE_ENHANCEMENT_DISABLE = "disable";
        public static final String SKIN_TONE_ENHANCEMENT_ENABLE = "enable";
        private static final String SUPPORTED_VALUES_SUFFIX = "-values";
        public static final String TOUCH_AF_AEC_OFF = "touch-off";
        public static final String TOUCH_AF_AEC_ON = "touch-on";
        private static final String TRUE = "true";
        public static final String VIDEO_HFR_2X = "60";
        public static final String VIDEO_HFR_3X = "90";
        public static final String VIDEO_HFR_4X = "120";
        public static final String VIDEO_HFR_OFF = "off";
        public static final String VIDEO_ROTATION_0 = "0";
        public static final String VIDEO_ROTATION_180 = "180";
        public static final String VIDEO_ROTATION_270 = "270";
        public static final String VIDEO_ROTATION_90 = "90";
        public static final String WHITE_BALANCE_AUTO = "auto";
        public static final String WHITE_BALANCE_CLOUDY_DAYLIGHT = "cloudy-daylight";
        public static final String WHITE_BALANCE_DAYLIGHT = "daylight";
        public static final String WHITE_BALANCE_FLUORESCENT = "fluorescent";
        public static final String WHITE_BALANCE_INCANDESCENT = "incandescent";
        public static final String WHITE_BALANCE_MANUAL_CCT = "manual-cct";
        public static final String WHITE_BALANCE_SHADE = "shade";
        public static final String WHITE_BALANCE_TWILIGHT = "twilight";
        public static final String WHITE_BALANCE_WARM_FLUORESCENT = "warm-fluorescent";
        public static final String ZSL_OFF = "off";
        public static final String ZSL_ON = "on";
        private final LinkedHashMap mMap = new LinkedHashMap(64);
        final Camera this$0;

        protected Parameters()
        {
            this$0 = Camera.this;
            super();
        }
    }

    public static interface PictureCallback
    {

        public abstract void onPictureTaken(byte abyte0[], Camera camera);
    }

    public static interface PreviewCallback
    {

        public abstract void onPreviewFrame(byte abyte0[], Camera camera);
    }

    public static interface ShutterCallback
    {

        public abstract void onShutter();
    }

    public class Size
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof Size))
                return false;
            obj = (Size)obj;
            boolean flag1 = flag;
            if(width == ((Size) (obj)).width)
            {
                flag1 = flag;
                if(height == ((Size) (obj)).height)
                    flag1 = true;
            }
            return flag1;
        }

        public int hashCode()
        {
            return width * 32713 + height;
        }

        public int height;
        final Camera this$0;
        public int width;

        public Size(int i, int j)
        {
            this$0 = Camera.this;
            super();
            width = i;
            height = j;
        }
    }


    static AutoFocusCallback _2D_get0(Camera camera)
    {
        return camera.mAutoFocusCallback;
    }

    static Object _2D_get1(Camera camera)
    {
        return camera.mAutoFocusCallbackLock;
    }

    static PreviewCallback _2D_get10(Camera camera)
    {
        return camera.mPreviewCallback;
    }

    static PictureCallback _2D_get11(Camera camera)
    {
        return camera.mRawImageCallback;
    }

    static ShutterCallback _2D_get12(Camera camera)
    {
        return camera.mShutterCallback;
    }

    static boolean _2D_get13(Camera camera)
    {
        return camera.mWithBuffer;
    }

    static OnZoomChangeListener _2D_get14(Camera camera)
    {
        return camera.mZoomListener;
    }

    static AutoFocusMoveCallback _2D_get2(Camera camera)
    {
        return camera.mAutoFocusMoveCallback;
    }

    static CameraDataCallback _2D_get3(Camera camera)
    {
        return camera.mCameraDataCallback;
    }

    static CameraMetaDataCallback _2D_get4(Camera camera)
    {
        return camera.mCameraMetaDataCallback;
    }

    static ErrorCallback _2D_get5(Camera camera)
    {
        return camera.mErrorCallback;
    }

    static FaceDetectionListener _2D_get6(Camera camera)
    {
        return camera.mFaceListener;
    }

    static PictureCallback _2D_get7(Camera camera)
    {
        return camera.mJpegCallback;
    }

    static boolean _2D_get8(Camera camera)
    {
        return camera.mOneShot;
    }

    static PictureCallback _2D_get9(Camera camera)
    {
        return camera.mPostviewCallback;
    }

    static PreviewCallback _2D_set0(Camera camera, PreviewCallback previewcallback)
    {
        camera.mPreviewCallback = previewcallback;
        return previewcallback;
    }

    static int _2D_wrap0(byte abyte0[], int i)
    {
        return byteToInt(abyte0, i);
    }

    static void _2D_wrap1(Camera camera, boolean flag, boolean flag1)
    {
        camera.setHasPreviewCallback(flag, flag1);
    }

    Camera()
    {
        mFaceDetectionRunning = false;
        mAutoFocusCallbackLock = new Object();
    }

    protected Camera(int i)
    {
        mFaceDetectionRunning = false;
        mAutoFocusCallbackLock = new Object();
        if(i >= getNumberOfCameras())
            throw new RuntimeException("Unknown camera ID");
        i = cameraInitNormal(i);
        if(checkInitErrors(i))
        {
            if(i == -OsConstants.EACCES)
                throw new RuntimeException("Fail to connect to camera service");
            if(i == 19)
                throw new RuntimeException("Camera initialization failed");
            else
                throw new RuntimeException("Unknown camera error");
        } else
        {
            return;
        }
    }

    protected Camera(int i, int j)
    {
        mFaceDetectionRunning = false;
        mAutoFocusCallbackLock = new Object();
        i = cameraInitVersion(i, j);
        if(checkInitErrors(i))
        {
            if(i == -OsConstants.EACCES)
                throw new RuntimeException("Fail to connect to camera service");
            if(i == 19)
                throw new RuntimeException("Camera initialization failed");
            if(i == 38)
                throw new RuntimeException("Camera initialization failed because some methods are not implemented");
            if(i == 95)
                throw new RuntimeException("Camera initialization failed because the hal version is not supported by this device");
            if(i == 22)
                throw new RuntimeException("Camera initialization failed because the input arugments are invalid");
            if(i == 16)
                throw new RuntimeException("Camera initialization failed because the camera device was already opened");
            if(i == 87)
                throw new RuntimeException("Camera initialization failed because the max number of camera devices were already opened");
            else
                throw new RuntimeException("Unknown camera error");
        } else
        {
            return;
        }
    }

    private final native void _addCallbackBuffer(byte abyte0[], int i);

    private final native boolean _enableShutterSound(boolean flag);

    private static native void _getCameraInfo(int i, CameraInfo camerainfo);

    public static native int _getNumberOfCameras();

    private final native void _startFaceDetection(int i);

    private final native void _stopFaceDetection();

    private final native void _stopPreview();

    private final void addCallbackBuffer(byte abyte0[], int i)
    {
        if(i != 16 && i != 128)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported message type: ").append(i).toString());
        } else
        {
            _addCallbackBuffer(abyte0, i);
            return;
        }
    }

    private static int byteToInt(byte abyte0[], int i)
    {
        int j = 0;
        for(int k = 0; k < 4; k++)
            j += (abyte0[(3 - k) + i] & 0xff) << (3 - k) * 8;

        return j;
    }

    private int cameraInitNormal(int i)
    {
        return cameraInitVersion(i, -2);
    }

    private int cameraInitVersion(int i, int j)
    {
label0:
        {
            mShutterCallback = null;
            mRawImageCallback = null;
            mJpegCallback = null;
            mPreviewCallback = null;
            mPostviewCallback = null;
            mUsingPreviewAllocation = false;
            mZoomListener = null;
            mCameraDataCallback = null;
            mCameraMetaDataCallback = null;
            Object obj = Looper.myLooper();
            String s;
            int k;
            Object obj1;
            if(obj != null)
            {
                mEventHandler = new EventHandler(this, ((Looper) (obj)));
            } else
            {
                Looper looper = Looper.getMainLooper();
                if(looper != null)
                    mEventHandler = new EventHandler(this, looper);
                else
                    mEventHandler = null;
            }
            obj = ActivityThread.currentOpPackageName();
            s = SystemProperties.get("camera.hal1.packagelist", "");
            k = j;
            if(s.length() <= 0)
                break label0;
            obj1 = new android.text.TextUtils.SimpleStringSplitter(',');
            ((android.text.TextUtils.StringSplitter) (obj1)).setString(s);
            obj1 = ((Iterable) (obj1)).iterator();
            do
            {
                k = j;
                if(!((Iterator) (obj1)).hasNext())
                    break label0;
            } while(!((String) (obj)).equals((String)((Iterator) (obj1)).next()));
            k = 256;
        }
        return native_setup(new WeakReference(this), i, k, ((String) (obj)));
    }

    public static boolean checkInitErrors(int i)
    {
        boolean flag = false;
        if(i != 0)
            flag = true;
        return flag;
    }

    private native void enableFocusMoveCallback(int i);

    public static void getCameraInfo(int i, CameraInfo camerainfo)
    {
        IAudioService iaudioservice;
        if(i >= getNumberOfCameras())
            throw new RuntimeException("Unknown camera ID");
        _getCameraInfo(i, camerainfo);
        iaudioservice = android.media.IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
        if(iaudioservice.isCameraSoundForced())
            camerainfo.canDisableShutterSound = false;
_L1:
        return;
        camerainfo;
        Log.e("Camera", "Audio service is unavailable for queries");
          goto _L1
    }

    public static Parameters getEmptyParameters()
    {
        Camera camera = new Camera();
        camera.getClass();
        return camera. new Parameters();
    }

    public static int getNumberOfCameras()
    {
        boolean flag1;
label0:
        {
            boolean flag = false;
            String s = ActivityThread.currentOpPackageName();
            String s1 = SystemProperties.get("vendor.camera.aux.packagelist");
            flag1 = flag;
            if(s1.length() <= 0)
                break label0;
            Object obj = new android.text.TextUtils.SimpleStringSplitter(',');
            ((android.text.TextUtils.StringSplitter) (obj)).setString(s1);
            obj = ((Iterable) (obj)).iterator();
            do
            {
                flag1 = flag;
                if(!((Iterator) (obj)).hasNext())
                    break label0;
            } while(!s.equals((String)((Iterator) (obj)).next()));
            flag1 = true;
        }
        int j = _getNumberOfCameras();
        int i = j;
        if(!flag1)
        {
            i = j;
            if(j > 2)
                i = 2;
        }
        return i;
    }

    public static Parameters getParametersCopy(Parameters parameters)
    {
        if(parameters == null)
        {
            throw new NullPointerException("parameters must not be null");
        } else
        {
            Object obj = Parameters._2D_wrap0(parameters);
            obj.getClass();
            obj = ((Parameters) (obj)). new Parameters();
            ((Parameters) (obj)).copyFrom(parameters);
            return ((Parameters) (obj));
        }
    }

    private final native void native_autoFocus();

    private final native void native_cancelAutoFocus();

    private final native String native_getParameters();

    private final native void native_release();

    private final native void native_sendHistogramData();

    private final native void native_sendMetaData();

    private final native void native_setHistogramMode(boolean flag);

    private final native void native_setLongshot(boolean flag);

    private final native void native_setMetadataCb(boolean flag);

    private final native void native_setParameters(String s);

    private final native int native_setup(Object obj, int i, int j, String s);

    private final native void native_takePicture(int i);

    public static Camera open()
    {
        int i = getNumberOfCameras();
        CameraInfo camerainfo = new CameraInfo();
        for(int j = 0; j < i; j++)
        {
            getCameraInfo(j, camerainfo);
            if(camerainfo.facing == 0)
                return new Camera(j);
        }

        return null;
    }

    public static Camera open(int i)
    {
        return new Camera(i);
    }

    public static Camera openLegacy(int i, int j)
    {
        if(j < 256)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid HAL version ").append(j).toString());
        else
            return new Camera(i, j);
    }

    public static Camera openUninitialized()
    {
        return new Camera();
    }

    private static void postEventFromNative(Object obj, int i, int j, int k, Object obj1)
    {
        obj = (Camera)((WeakReference)obj).get();
        if(obj == null)
            return;
        if(((Camera) (obj)).mEventHandler != null)
        {
            obj1 = ((Camera) (obj)).mEventHandler.obtainMessage(i, j, k, obj1);
            ((Camera) (obj)).mEventHandler.sendMessage(((Message) (obj1)));
        }
    }

    private final native void setHasPreviewCallback(boolean flag, boolean flag1);

    private final native void setPreviewCallbackSurface(Surface surface);

    public final void addCallbackBuffer(byte abyte0[])
    {
        _addCallbackBuffer(abyte0, 16);
    }

    public final void addRawImageCallbackBuffer(byte abyte0[])
    {
        addCallbackBuffer(abyte0, 128);
    }

    public final void autoFocus(AutoFocusCallback autofocuscallback)
    {
        Object obj = mAutoFocusCallbackLock;
        obj;
        JVM INSTR monitorenter ;
        mAutoFocusCallback = autofocuscallback;
        obj;
        JVM INSTR monitorexit ;
        native_autoFocus();
        return;
        autofocuscallback;
        throw autofocuscallback;
    }

    public int cameraInitUnspecified(int i)
    {
        return cameraInitVersion(i, -1);
    }

    public final void cancelAutoFocus()
    {
        Object obj = mAutoFocusCallbackLock;
        obj;
        JVM INSTR monitorenter ;
        mAutoFocusCallback = null;
        obj;
        JVM INSTR monitorexit ;
        native_cancelAutoFocus();
        mEventHandler.removeMessages(4);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final Allocation createPreviewAllocation(RenderScript renderscript, int i)
        throws RSIllegalArgumentException
    {
        Size size = getParameters().getPreviewSize();
        android.renderscript.Type.Builder builder = new android.renderscript.Type.Builder(renderscript, Element.createPixel(renderscript, android.renderscript.Element.DataType.UNSIGNED_8, android.renderscript.Element.DataKind.PIXEL_YUV));
        builder.setYuvFormat(0x32315659);
        builder.setX(size.width);
        builder.setY(size.height);
        return Allocation.createTyped(renderscript, builder.create(), i | 0x20);
    }

    public final boolean disableShutterSound()
    {
        return _enableShutterSound(false);
    }

    public final boolean enableShutterSound(boolean flag)
    {
        IAudioService iaudioservice;
        if(flag)
            break MISSING_BLOCK_LABEL_37;
        iaudioservice = android.media.IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
        boolean flag1 = iaudioservice.isCameraSoundForced();
        if(flag1)
            return false;
        break MISSING_BLOCK_LABEL_37;
        RemoteException remoteexception;
        remoteexception;
        Log.e("Camera", "Audio service is unavailable for queries");
        return _enableShutterSound(flag);
    }

    protected void finalize()
    {
        release();
    }

    public int getCurrentFocusPosition()
    {
        Parameters parameters = new Parameters();
        parameters.unflatten(native_getParameters());
        int i = -1;
        if(parameters.getCurrentFocusPosition() != null)
            i = Integer.parseInt(parameters.getCurrentFocusPosition());
        return i;
    }

    public Parameters getParameters()
    {
        Parameters parameters = new Parameters();
        parameters.unflatten(native_getParameters());
        return parameters;
    }

    public int getWBCurrentCCT()
    {
        Parameters parameters = new Parameters();
        parameters.unflatten(native_getParameters());
        int i = 0;
        if(parameters.getWBCurrentCCT() != null)
            i = Integer.parseInt(parameters.getWBCurrentCCT());
        return i;
    }

    public final native void lock();

    public final native boolean previewEnabled();

    public final native void reconnect()
        throws IOException;

    public final void release()
    {
        native_release();
        mFaceDetectionRunning = false;
    }

    public final void sendHistogramData()
    {
        native_sendHistogramData();
    }

    public final void sendMetaData()
    {
        native_sendMetaData();
    }

    public void setAutoFocusMoveCallback(AutoFocusMoveCallback autofocusmovecallback)
    {
        mAutoFocusMoveCallback = autofocusmovecallback;
        int i;
        if(mAutoFocusMoveCallback != null)
            i = 1;
        else
            i = 0;
        enableFocusMoveCallback(i);
    }

    public final native void setDisplayOrientation(int i);

    public final void setErrorCallback(ErrorCallback errorcallback)
    {
        mErrorCallback = errorcallback;
    }

    public final void setFaceDetectionListener(FaceDetectionListener facedetectionlistener)
    {
        mFaceListener = facedetectionlistener;
    }

    public final void setHistogramMode(CameraDataCallback cameradatacallback)
    {
        mCameraDataCallback = cameradatacallback;
        boolean flag;
        if(cameradatacallback != null)
            flag = true;
        else
            flag = false;
        native_setHistogramMode(flag);
    }

    public final void setLongshot(boolean flag)
    {
        native_setLongshot(flag);
    }

    public final void setMetadataCb(CameraMetaDataCallback camerametadatacallback)
    {
        mCameraMetaDataCallback = camerametadatacallback;
        boolean flag;
        if(camerametadatacallback != null)
            flag = true;
        else
            flag = false;
        native_setMetadataCb(flag);
    }

    public final void setOneShotPreviewCallback(PreviewCallback previewcallback)
    {
        boolean flag = true;
        SeempLog.record(68);
        mPreviewCallback = previewcallback;
        mOneShot = true;
        mWithBuffer = false;
        if(previewcallback != null)
            mUsingPreviewAllocation = false;
        if(previewcallback == null)
            flag = false;
        setHasPreviewCallback(flag, false);
    }

    public void setParameters(Parameters parameters)
    {
        if(mUsingPreviewAllocation)
        {
            Size size = parameters.getPreviewSize();
            Size size1 = getParameters().getPreviewSize();
            if(size.width != size1.width || size.height != size1.height)
                throw new IllegalStateException("Cannot change preview size while a preview allocation is configured.");
        }
        native_setParameters(parameters.flatten());
    }

    public final void setPreviewCallback(PreviewCallback previewcallback)
    {
        SeempLog.record(66);
        mPreviewCallback = previewcallback;
        mOneShot = false;
        mWithBuffer = false;
        if(previewcallback != null)
            mUsingPreviewAllocation = false;
        boolean flag;
        if(previewcallback != null)
            flag = true;
        else
            flag = false;
        setHasPreviewCallback(flag, false);
    }

    public final void setPreviewCallbackAllocation(Allocation allocation)
        throws IOException
    {
        Size size = null;
        if(allocation != null)
        {
            size = getParameters().getPreviewSize();
            if(size.width != allocation.getType().getX() || size.height != allocation.getType().getY())
                throw new IllegalArgumentException((new StringBuilder()).append("Allocation dimensions don't match preview dimensions: Allocation is ").append(allocation.getType().getX()).append(", ").append(allocation.getType().getY()).append(". Preview is ").append(size.width).append(", ").append(size.height).toString());
            if((allocation.getUsage() & 0x20) == 0)
                throw new IllegalArgumentException("Allocation usage does not include USAGE_IO_INPUT");
            if(allocation.getType().getElement().getDataKind() != android.renderscript.Element.DataKind.PIXEL_YUV)
                throw new IllegalArgumentException("Allocation is not of a YUV type");
            allocation = allocation.getSurface();
            mUsingPreviewAllocation = true;
        } else
        {
            mUsingPreviewAllocation = false;
            allocation = size;
        }
        setPreviewCallbackSurface(allocation);
    }

    public final void setPreviewCallbackWithBuffer(PreviewCallback previewcallback)
    {
        boolean flag = false;
        SeempLog.record(67);
        mPreviewCallback = previewcallback;
        mOneShot = false;
        mWithBuffer = true;
        if(previewcallback != null)
            mUsingPreviewAllocation = false;
        if(previewcallback != null)
            flag = true;
        setHasPreviewCallback(flag, true);
    }

    public final void setPreviewDisplay(SurfaceHolder surfaceholder)
        throws IOException
    {
        if(surfaceholder != null)
            setPreviewSurface(surfaceholder.getSurface());
        else
            setPreviewSurface((Surface)null);
    }

    public final native void setPreviewSurface(Surface surface)
        throws IOException;

    public final native void setPreviewTexture(SurfaceTexture surfacetexture)
        throws IOException;

    public final void setZoomChangeListener(OnZoomChangeListener onzoomchangelistener)
    {
        mZoomListener = onzoomchangelistener;
    }

    public final void startFaceDetection()
    {
        if(mFaceDetectionRunning)
        {
            throw new RuntimeException("Face detection is already running");
        } else
        {
            _startFaceDetection(0);
            mFaceDetectionRunning = true;
            return;
        }
    }

    public final native void startPreview();

    public final native void startSmoothZoom(int i);

    public final void stopFaceDetection()
    {
        _stopFaceDetection();
        mFaceDetectionRunning = false;
    }

    public final void stopPreview()
    {
        _stopPreview();
        mFaceDetectionRunning = false;
        mShutterCallback = null;
        mRawImageCallback = null;
        mPostviewCallback = null;
        mJpegCallback = null;
        Object obj = mAutoFocusCallbackLock;
        obj;
        JVM INSTR monitorenter ;
        mAutoFocusCallback = null;
        obj;
        JVM INSTR monitorexit ;
        mAutoFocusMoveCallback = null;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final native void stopSmoothZoom();

    public final void takePicture(ShutterCallback shuttercallback, PictureCallback picturecallback, PictureCallback picturecallback1)
    {
        SeempLog.record(65);
        takePicture(shuttercallback, picturecallback, null, picturecallback1);
    }

    public final void takePicture(ShutterCallback shuttercallback, PictureCallback picturecallback, PictureCallback picturecallback1, PictureCallback picturecallback2)
    {
        SeempLog.record(65);
        mShutterCallback = shuttercallback;
        mRawImageCallback = picturecallback;
        mPostviewCallback = picturecallback1;
        mJpegCallback = picturecallback2;
        int i = 0;
        if(mShutterCallback != null)
            i = 2;
        int j = i;
        if(mRawImageCallback != null)
            j = i | 0x80;
        i = j;
        if(mPostviewCallback != null)
            i = j | 0x40;
        j = i;
        if(mJpegCallback != null)
            j = i | 0x100;
        native_takePicture(j);
        mFaceDetectionRunning = false;
    }

    public final native void unlock();

    public static final String ACTION_NEW_PICTURE = "android.hardware.action.NEW_PICTURE";
    public static final String ACTION_NEW_VIDEO = "android.hardware.action.NEW_VIDEO";
    public static final int CAMERA_ERROR_EVICTED = 2;
    public static final int CAMERA_ERROR_SERVER_DIED = 100;
    public static final int CAMERA_ERROR_UNKNOWN = 1;
    private static final int CAMERA_FACE_DETECTION_HW = 0;
    private static final int CAMERA_FACE_DETECTION_SW = 1;
    public static final int CAMERA_HAL_API_VERSION_1_0 = 256;
    private static final int CAMERA_HAL_API_VERSION_NORMAL_CONNECT = -2;
    private static final int CAMERA_HAL_API_VERSION_UNSPECIFIED = -1;
    private static final int CAMERA_MSG_COMPRESSED_IMAGE = 256;
    private static final int CAMERA_MSG_ERROR = 1;
    private static final int CAMERA_MSG_FOCUS = 4;
    private static final int CAMERA_MSG_FOCUS_MOVE = 2048;
    private static final int CAMERA_MSG_META_DATA = 8192;
    private static final int CAMERA_MSG_POSTVIEW_FRAME = 64;
    private static final int CAMERA_MSG_PREVIEW_FRAME = 16;
    private static final int CAMERA_MSG_PREVIEW_METADATA = 1024;
    private static final int CAMERA_MSG_RAW_IMAGE = 128;
    private static final int CAMERA_MSG_RAW_IMAGE_NOTIFY = 512;
    private static final int CAMERA_MSG_SHUTTER = 2;
    private static final int CAMERA_MSG_STATS_DATA = 4096;
    private static final int CAMERA_MSG_VIDEO_FRAME = 32;
    private static final int CAMERA_MSG_ZOOM = 8;
    private static final int EACCESS = -13;
    private static final int EBUSY = -16;
    private static final int EINVAL = -22;
    private static final int ENODEV = -19;
    private static final int ENOSYS = -38;
    private static final int EOPNOTSUPP = -95;
    private static final int EUSERS = -87;
    private static final int NO_ERROR = 0;
    private static final String TAG = "Camera";
    private AutoFocusCallback mAutoFocusCallback;
    private final Object mAutoFocusCallbackLock;
    private AutoFocusMoveCallback mAutoFocusMoveCallback;
    private CameraDataCallback mCameraDataCallback;
    private CameraMetaDataCallback mCameraMetaDataCallback;
    private ErrorCallback mErrorCallback;
    private EventHandler mEventHandler;
    private boolean mFaceDetectionRunning;
    private FaceDetectionListener mFaceListener;
    private PictureCallback mJpegCallback;
    private long mNativeContext;
    private boolean mOneShot;
    private PictureCallback mPostviewCallback;
    private PreviewCallback mPreviewCallback;
    private PictureCallback mRawImageCallback;
    private ShutterCallback mShutterCallback;
    private boolean mUsingPreviewAllocation;
    private boolean mWithBuffer;
    private OnZoomChangeListener mZoomListener;
}
