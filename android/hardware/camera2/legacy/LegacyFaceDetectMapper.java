// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.camera2.*;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.utils.ParamsUtils;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.hardware.camera2.legacy:
//            LegacyRequest, ParameterUtils

public class LegacyFaceDetectMapper
{

    static String _2D_get0()
    {
        return TAG;
    }

    static boolean _2D_get1(LegacyFaceDetectMapper legacyfacedetectmapper)
    {
        return legacyfacedetectmapper.mFaceDetectEnabled;
    }

    static Object _2D_get2(LegacyFaceDetectMapper legacyfacedetectmapper)
    {
        return legacyfacedetectmapper.mLock;
    }

    static android.hardware.Camera.Face[] _2D_set0(LegacyFaceDetectMapper legacyfacedetectmapper, android.hardware.Camera.Face aface[])
    {
        legacyfacedetectmapper.mFaces = aface;
        return aface;
    }

    public LegacyFaceDetectMapper(Camera camera, CameraCharacteristics cameracharacteristics)
    {
        mFaceDetectEnabled = false;
        mFaceDetectScenePriority = false;
        mFaceDetectReporting = false;
        mCamera = (Camera)Preconditions.checkNotNull(camera, "camera must not be null");
        Preconditions.checkNotNull(cameracharacteristics, "characteristics must not be null");
        mFaceDetectSupported = ArrayUtils.contains((int[])cameracharacteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES), 1);
        if(!mFaceDetectSupported)
        {
            return;
        } else
        {
            mCamera.setFaceDetectionListener(new android.hardware.Camera.FaceDetectionListener() {

                public void onFaceDetection(android.hardware.Camera.Face aface[], Camera camera1)
                {
                    int i;
                    if(aface == null)
                        i = 0;
                    else
                        i = aface.length;
                    camera1 = ((Camera) (LegacyFaceDetectMapper._2D_get2(LegacyFaceDetectMapper.this)));
                    camera1;
                    JVM INSTR monitorenter ;
                    if(!LegacyFaceDetectMapper._2D_get1(LegacyFaceDetectMapper.this)) goto _L2; else goto _L1
_L1:
                    LegacyFaceDetectMapper._2D_set0(LegacyFaceDetectMapper.this, aface);
_L4:
                    camera1;
                    JVM INSTR monitorexit ;
                    return;
_L2:
                    if(i <= 0) goto _L4; else goto _L3
_L3:
                    Log.d(LegacyFaceDetectMapper._2D_get0(), "onFaceDetection - Ignored some incoming faces sinceface detection was disabled");
                      goto _L4
                    aface;
                    throw aface;
                }

                final LegacyFaceDetectMapper this$0;

            
            {
                this$0 = LegacyFaceDetectMapper.this;
                super();
            }
            }
);
            return;
        }
    }

    public void mapResultFaces(CameraMetadataNative camerametadatanative, LegacyRequest legacyrequest)
    {
        Preconditions.checkNotNull(camerametadatanative, "result must not be null");
        Preconditions.checkNotNull(legacyrequest, "legacyRequest must not be null");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        android.hardware.Camera.Face aface[];
        boolean flag;
        Object obj1;
        if(mFaceDetectReporting)
            i = 1;
        else
            i = 0;
        if(!mFaceDetectReporting)
            break MISSING_BLOCK_LABEL_186;
        aface = mFaces;
_L1:
        flag = mFaceDetectScenePriority;
        android.hardware.Camera.Face aface1[] = mFacesPrev;
        mFacesPrev = aface;
        obj;
        JVM INSTR monitorexit ;
        CameraCharacteristics cameracharacteristics = legacyrequest.characteristics;
        obj = legacyrequest.captureRequest;
        obj1 = legacyrequest.previewSize;
        android.hardware.Camera.Parameters parameters = legacyrequest.parameters;
        legacyrequest = (Rect)cameracharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        obj = ParameterUtils.convertScalerCropRegion(legacyrequest, (Rect)((CaptureRequest) (obj)).get(CaptureRequest.SCALER_CROP_REGION), ((android.util.Size) (obj1)), parameters);
        obj1 = new ArrayList();
        if(aface != null)
        {
            int j = 0;
            int k = aface.length;
            while(j < k) 
            {
                android.hardware.Camera.Face face = aface[j];
                if(face != null)
                    ((List) (obj1)).add(ParameterUtils.convertFaceFromLegacy(face, legacyrequest, ((ParameterUtils.ZoomData) (obj))));
                else
                    Log.w(TAG, "mapResultFaces - read NULL face from camera1 device");
                j++;
            }
        }
        break MISSING_BLOCK_LABEL_209;
        aface = null;
          goto _L1
        camerametadatanative;
        throw camerametadatanative;
        camerametadatanative.set(CaptureResult.STATISTICS_FACES, (Face[])((List) (obj1)).toArray(new Face[0]));
        camerametadatanative.set(CaptureResult.STATISTICS_FACE_DETECT_MODE, Integer.valueOf(i));
        if(flag)
            camerametadatanative.set(CaptureResult.CONTROL_SCENE_MODE, Integer.valueOf(1));
        return;
    }

    public void processFaceDetectMode(CaptureRequest capturerequest, android.hardware.Camera.Parameters parameters)
    {
        boolean flag;
        Preconditions.checkNotNull(capturerequest, "captureRequest must not be null");
        int i = ((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.STATISTICS_FACE_DETECT_MODE, Integer.valueOf(0))).intValue();
        if(i != 0 && mFaceDetectSupported ^ true)
        {
            Log.w(TAG, "processFaceDetectMode - Ignoring statistics.faceDetectMode; face detection is not available");
            return;
        }
        int j = ((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.CONTROL_SCENE_MODE, Integer.valueOf(0))).intValue();
        if(j == 1 && mFaceDetectSupported ^ true)
        {
            Log.w(TAG, "processFaceDetectMode - ignoring control.sceneMode == FACE_PRIORITY; face detection is not available");
            return;
        }
        switch(i)
        {
        default:
            Log.w(TAG, (new StringBuilder()).append("processFaceDetectMode - ignoring unknown statistics.faceDetectMode = ").append(i).toString());
            return;

        case 2: // '\002'
            Log.w(TAG, "processFaceDetectMode - statistics.faceDetectMode == FULL unsupported, downgrading to SIMPLE");
            break;

        case 0: // '\0'
        case 1: // '\001'
            break;
        }
        if(i == 0)
        {
            if(j == 1)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        capturerequest = ((CaptureRequest) (mLock));
        capturerequest;
        JVM INSTR monitorenter ;
        if(flag == mFaceDetectEnabled) goto _L2; else goto _L1
_L1:
        if(!flag)
            break MISSING_BLOCK_LABEL_246;
        mCamera.startFaceDetection();
_L3:
        mFaceDetectEnabled = flag;
        if(j == 1)
            flag = true;
        else
            flag = false;
        mFaceDetectScenePriority = flag;
        if(i != 0)
            flag = true;
        else
            flag = false;
        mFaceDetectReporting = flag;
_L2:
        capturerequest;
        JVM INSTR monitorexit ;
        return;
        mCamera.stopFaceDetection();
        mFaces = null;
          goto _L3
        parameters;
        throw parameters;
    }

    private static final boolean DEBUG = false;
    private static String TAG = "LegacyFaceDetectMapper";
    private final Camera mCamera;
    private boolean mFaceDetectEnabled;
    private boolean mFaceDetectReporting;
    private boolean mFaceDetectScenePriority;
    private final boolean mFaceDetectSupported;
    private android.hardware.Camera.Face mFaces[];
    private android.hardware.Camera.Face mFacesPrev[];
    private final Object mLock = new Object();

}
