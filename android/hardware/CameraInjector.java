// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.app.ActivityThread;
import android.app.Application;
import android.content.*;
import android.os.*;
import android.util.Log;
import android.view.*;
import java.util.*;
import miui.util.FeatureParser;

// Referenced classes of package android.hardware:
//            Camera

public class CameraInjector
{
    private static class CameraExInfo
    {

        static CameraOrientationEventListener _2D_get0(CameraExInfo cameraexinfo)
        {
            return cameraexinfo.mCameraOrientationEventListener;
        }

        static HashMap _2D_get1(CameraExInfo cameraexinfo)
        {
            return cameraexinfo.mCameraParameterInfo;
        }

        static long _2D_get2(CameraExInfo cameraexinfo)
        {
            return cameraexinfo.mEndTime;
        }

        static boolean _2D_get3(CameraExInfo cameraexinfo)
        {
            return cameraexinfo.mReverseFrameData;
        }

        static long _2D_get4(CameraExInfo cameraexinfo)
        {
            return cameraexinfo.mStartTime;
        }

        static CameraOrientationEventListener _2D_set0(CameraExInfo cameraexinfo, CameraOrientationEventListener cameraorientationeventlistener)
        {
            cameraexinfo.mCameraOrientationEventListener = cameraorientationeventlistener;
            return cameraorientationeventlistener;
        }

        static long _2D_set1(CameraExInfo cameraexinfo, long l)
        {
            cameraexinfo.mEndTime = l;
            return l;
        }

        static boolean _2D_set2(CameraExInfo cameraexinfo, boolean flag)
        {
            cameraexinfo.mReverseFrameData = flag;
            return flag;
        }

        private CameraOrientationEventListener mCameraOrientationEventListener;
        private HashMap mCameraParameterInfo;
        private long mEndTime;
        private boolean mReverseFrameData;
        private long mStartTime;

        private CameraExInfo(int i)
        {
            mCameraParameterInfo = new HashMap();
            mCameraParameterInfo.put("camera-id", String.valueOf(i));
            mStartTime = System.currentTimeMillis();
        }

        CameraExInfo(int i, CameraExInfo cameraexinfo)
        {
            this(i);
        }
    }

    private static class CameraOrientationEventListener extends OrientationEventListener
    {

        public void onOrientationChanged(int i)
        {
            if(i == -1)
                return;
            mOrientation = CameraInjector._2D_wrap0(i, mOrientation);
            if(mOrientation % 180 == 0)
            {
                i = mDisplay.getRotation();
                boolean flag;
                if(i == 2)
                    flag = true;
                else
                    flag = false;
                if(CameraExInfo._2D_get3((CameraExInfo)CameraInjector._2D_get0().get(mCamera)) != flag)
                {
                    Log.v("CameraInjector", (new StringBuilder()).append("mReverseFrameData change to ").append(flag).append(" current client orientation ").append(i).toString());
                    CameraExInfo._2D_set2((CameraExInfo)CameraInjector._2D_get0().get(mCamera), flag);
                }
            }
        }

        private Camera mCamera;
        private Context mContext;
        private Display mDisplay;
        private int mOrientation;

        public CameraOrientationEventListener(Camera camera, Context context)
        {
            super(context);
            mOrientation = -1;
            mCamera = camera;
            mContext = context;
            mDisplay = ((WindowManager)mContext.getSystemService("window")).getDefaultDisplay();
        }
    }

    static class UploadInfoThread extends Thread
    {

        public void run()
        {
            if(mCameraExInfo != null)
            {
                Intent intent = new Intent("action_save_camera_info");
                intent.setComponent(new ComponentName("com.miui.klo.bugreport", "com.miui.klo.bugreport.service.ReceiveCameraInfoService"));
                Bundle bundle = new Bundle();
                bundle.putLong("starttime", CameraExInfo._2D_get4(mCameraExInfo));
                if(CameraExInfo._2D_get2(mCameraExInfo) != 0L)
                    bundle.putLong("endtime", CameraExInfo._2D_get2(mCameraExInfo));
                bundle.putString("frontorback", (String)CameraExInfo._2D_get1(mCameraExInfo).get("camera-id"));
                intent.putExtras(bundle);
                ActivityThread.currentApplication().getBaseContext().startService(intent);
            }
        }

        CameraExInfo mCameraExInfo;

        private UploadInfoThread(CameraExInfo cameraexinfo)
        {
            mCameraExInfo = cameraexinfo;
        }

        UploadInfoThread(CameraExInfo cameraexinfo, UploadInfoThread uploadinfothread)
        {
            this(cameraexinfo);
        }
    }


    static WeakHashMap _2D_get0()
    {
        return sCameraInfoMap;
    }

    static int _2D_wrap0(int i, int j)
    {
        return roundOrientation(i, j);
    }

    public CameraInjector()
    {
    }

    protected static String convertSizeToString(Camera.Size size)
    {
        return (new StringBuilder()).append(size.width).append("x").append(size.height).toString();
    }

    private static void createOrientationListener(Camera camera)
    {
        CameraExInfo cameraexinfo = (CameraExInfo)sCameraInfoMap.get(camera);
        if(isInRotateWhiteList() && cameraexinfo != null)
        {
            Log.v("CameraInjector", "Listener orientation");
            camera = new CameraOrientationEventListener(camera, ActivityThread.currentApplication().getBaseContext());
            camera.enable();
            CameraExInfo._2D_set0(cameraexinfo, camera);
        }
    }

    private static void destoryOrientationListener(Camera camera)
    {
        CameraExInfo cameraexinfo = (CameraExInfo)sCameraInfoMap.get(camera);
        if(cameraexinfo != null)
        {
            camera = CameraExInfo._2D_get0(cameraexinfo);
            if(camera != null)
            {
                Log.v("CameraInjector", "release orientation listener");
                camera.disable();
                CameraExInfo._2D_set0(cameraexinfo, null);
            }
        }
    }

    private static HashMap getCameraParameterInfo(Camera camera)
    {
        camera = (CameraExInfo)sCameraInfoMap.get(camera);
        if(camera != null)
            return CameraExInfo._2D_get1(camera);
        else
            return null;
    }

    public static int getNumberOfCameras(int i)
    {
        int j = i;
        if(!isExposeAuxCamera())
        {
            j = i;
            if(i > 2)
                j = 2;
        }
        i = j;
        if(limitCamera())
        {
            i = j;
            if(j > 1)
                i = 1;
        }
        return i;
    }

    public static void getParametersEx(Camera.Parameters parameters)
    {
        if(isInWhiteList("remove_lower_perview_size_list"))
        {
            List list = parameters.getSupportedPreviewSizes();
            ArrayList arraylist = new ArrayList(list.size());
            int i = FeatureParser.getInteger("extra_min_preview_size", 0xe1000);
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Camera.Size size = (Camera.Size)iterator.next();
                if(i <= size.width * size.height)
                    arraylist.add(size);
            } while(true);
            if(arraylist.size() != list.size())
                parameters.set("preview-size-values", getValueString(arraylist));
        }
    }

    private static String getValueString(List list)
    {
        if(list != null)
        {
            StringBuilder stringbuilder = new StringBuilder();
            for(int i = 0; i < list.size(); i++)
            {
                Camera.Size size = (Camera.Size)list.get(i);
                stringbuilder.append(size.width);
                stringbuilder.append('x');
                stringbuilder.append(size.height);
                if(i != list.size() - 1)
                    stringbuilder.append(',');
            }

            return stringbuilder.toString();
        } else
        {
            return "";
        }
    }

    public static void initCamera(Camera camera, int i)
    {
        CameraExInfo cameraexinfo = new CameraExInfo(i, null);
        sCameraInfoMap.put(camera, cameraexinfo);
        onCameraStateChange(camera, true);
        uploadCameraUseInfo(camera, false);
    }

    public static boolean isExposeAuxCamera()
    {
        if(isInWhiteList("camera_aux_package_list"))
            return true;
        else
            return limitByPackageName(SystemProperties.get("camera.aux.packagelist"));
    }

    private static boolean isInRotateWhiteList()
    {
        String s;
        Object obj;
label0:
        {
            s = ActivityThread.currentPackageName();
            String s1 = SystemProperties.get("camera.rotate.packagelist");
            if(s1 != null)
            {
                obj = s1;
                if(s1.length() != 0)
                    break label0;
            }
            obj = SystemProperties.get("vendor.camera.rotate.packagelist");
        }
label1:
        {
            Log.v("CameraInjector", (new StringBuilder()).append("isInRotateWhiteList whiteList=").append(((String) (obj))).append(" processName=").append(s).toString());
            if(((String) (obj)).length() <= 0)
                break label1;
            android.text.TextUtils.SimpleStringSplitter simplestringsplitter = new android.text.TextUtils.SimpleStringSplitter(',');
            simplestringsplitter.setString(((String) (obj)));
            obj = simplestringsplitter.iterator();
            do
                if(!((Iterator) (obj)).hasNext())
                    break label1;
            while(!s.equals((String)((Iterator) (obj)).next()));
            return true;
        }
        return false;
    }

    private static boolean isInWhiteList(String s)
    {
        String s1 = ActivityThread.currentPackageName();
        s = FeatureParser.getStringArray(s);
        if(s != null && s.length > 0)
        {
            int i = s.length;
            for(int j = 0; j < i; j++)
                if(s1.equals(s[j]))
                    return true;

        }
        return false;
    }

    public static boolean limitByCameraId(String s)
    {
        boolean flag = false;
        if(s == null)
            flag = true;
        boolean flag1 = flag;
        if(!isExposeAuxCamera())
        {
            flag1 = flag;
            if(Integer.parseInt(s) >= 2)
                flag1 = true;
        }
        flag = flag1;
        if(limitCamera())
        {
            flag = flag1;
            if(Integer.parseInt(s) >= 1)
                flag = true;
        }
        return flag;
    }

    private static boolean limitByPackageName(String s)
    {
        boolean flag1;
label0:
        {
            String s1 = ActivityThread.currentPackageName();
            boolean flag = false;
            flag1 = flag;
            if(s == null)
                break label0;
            flag1 = flag;
            if(s.length() <= 0)
                break label0;
            flag1 = flag;
            if(s1 == null)
                break label0;
            flag1 = flag;
            if(s1.length() <= 0)
                break label0;
            android.text.TextUtils.SimpleStringSplitter simplestringsplitter = new android.text.TextUtils.SimpleStringSplitter(',');
            simplestringsplitter.setString(s);
            s = simplestringsplitter.iterator();
            do
            {
                flag1 = flag;
                if(!s.hasNext())
                    break label0;
            } while(!s1.equals((String)s.next()));
            flag1 = true;
        }
        return flag1;
    }

    public static boolean limitCamera()
    {
        return limitByPackageName(SystemProperties.get("camera.limit.packagelist"));
    }

    private static void onCameraStateChange(Camera camera, boolean flag)
    {
        if("chiron".equals(Build.DEVICE) || "lithium".equals(Build.DEVICE) || "polaris".equals(Build.DEVICE))
            if(flag)
                createOrientationListener(camera);
            else
                destoryOrientationListener(camera);
    }

    public static void processPreviewFrame(Camera camera, byte abyte0[])
    {
        camera = (CameraExInfo)sCameraInfoMap.get(camera);
        if(camera != null && CameraExInfo._2D_get3(camera))
            reversePreviewFrame(abyte0);
    }

    public static void releaseCamera(Camera camera)
    {
        onCameraStateChange(camera, false);
        uploadCameraUseInfo(camera, true);
        sCameraInfoMap.remove(camera);
    }

    private static void reverse(byte abyte0[], int i, int j)
    {
        while(abyte0 == null || i > abyte0.length - 1 || j > abyte0.length - 1 || i < 0 || j < 0) 
            return;
        for(; j > i; i++)
        {
            int k = abyte0[j];
            abyte0[j] = abyte0[i];
            abyte0[i] = (byte)k;
            j--;
        }

    }

    private static void reversePreviewFrame(byte abyte0[])
    {
        if(abyte0 == null)
        {
            return;
        } else
        {
            int i = (abyte0.length * 2) / 3;
            reverse(abyte0, 0, i);
            reverseUV(abyte0, i, abyte0.length - 1);
            return;
        }
    }

    private static void reverseUV(byte abyte0[], int i, int j)
    {
        while(abyte0 == null || i > abyte0.length - 1 || j > abyte0.length - 1 || i < 0 || j < 0) 
            return;
        for(; j > i; i += 2)
        {
            int k = abyte0[j - 1];
            int l = abyte0[j];
            abyte0[j - 1] = abyte0[i];
            abyte0[j] = abyte0[i + 1];
            abyte0[i] = (byte)k;
            abyte0[i + 1] = (byte)l;
            j -= 2;
        }

    }

    private static int roundOrientation(int i, int j)
    {
        int k;
        if(j == -1)
        {
            k = 1;
        } else
        {
            k = Math.abs(i - j);
            if(Math.min(k, 360 - k) >= 50)
                k = 1;
            else
                k = 0;
        }
        if(k != 0)
            return (((i + 45) / 90) * 90) % 360;
        else
            return j;
    }

    public static void setParametersEx(Camera camera, Camera.Parameters parameters)
    {
        if(isInWhiteList("add_still_beautify_list") && parameters.get("xiaomi-still-beautify-values") == null)
        {
            Camera.Size size = parameters.getPreviewSize();
            if(size != null && (size.height <= 720 || size.width <= 720))
                parameters.set("xiaomi-still-beautify-values", FeatureParser.getString("extra_still_beautify_value"));
        }
        HashMap hashmap = getCameraParameterInfo(camera);
        if(hashmap != null)
        {
            hashmap.put("preview-size", convertSizeToString(parameters.getPreviewSize()));
            hashmap.put("picture-size", convertSizeToString(parameters.getPictureSize()));
        } else
        {
            Log.w("CameraInjector", (new StringBuilder()).append("setParametersEx: Lost camera info ").append(camera).toString());
        }
    }

    public static void setTorchMode(String s)
    {
        if(limitByCameraId(s))
            throw new IllegalArgumentException("invalid cameraId");
        else
            return;
    }

    public static void startPreview(Camera camera)
    {
        HashMap hashmap = getCameraParameterInfo(camera);
        if(hashmap != null)
        {
            camera = (String)hashmap.get("camera-id");
            camera = (String)hashmap.get("preview-size");
        } else
        {
            Log.w("CameraInjector", (new StringBuilder()).append("startPreview: Lost camera info ").append(camera).toString());
        }
    }

    public static void takePicture(Camera camera)
    {
        HashMap hashmap = getCameraParameterInfo(camera);
        if(hashmap != null)
        {
            camera = (String)hashmap.get("camera-id");
            camera = (String)hashmap.get("picture-size");
        } else
        {
            Log.w("CameraInjector", (new StringBuilder()).append("takePicture: Lost camera info ").append(camera).toString());
        }
    }

    private static void uploadCameraUseInfo(Camera camera, boolean flag)
    {
        camera = (CameraExInfo)sCameraInfoMap.get(camera);
        if(camera != null)
        {
            if(flag)
                CameraExInfo._2D_set1(camera, System.currentTimeMillis());
            (new UploadInfoThread(camera, null)).start();
        }
    }

    private static final String ACTION_SAVE_CAMERA_INFO = "action_save_camera_info";
    private static final String CAMERA_CONFIG_KEY = "camera";
    private static final String ENDTTIME = "endtime";
    private static final String EXTRA_BEAUTIFY_VALUE = "extra_still_beautify_value";
    private static final String EXTRA_MIN_PREVIEW_SIZE = "extra_min_preview_size";
    private static final String FRONTORBACK = "frontorback";
    private static final String KEY_BEAUTIFY = "xiaomi-still-beautify-values";
    private static final String KEY_CAMERA_ID = "camera-id";
    private static final String KEY_PICTURE_SIZE = "picture-size";
    private static final String KEY_PREVIEW_SIZE = "preview-size";
    private static final String SAVE_CAMERA_INFO_PACKAGE_NAME = "com.miui.klo.bugreport";
    private static final String SAVE_CAMERA_INFO_SERVICE_NAME = "com.miui.klo.bugreport.service.ReceiveCameraInfoService";
    private static final String STARTTIME = "starttime";
    private static final String SUPPORTED_VALUES_SUFFIX = "-values";
    private static final String TAG = "CameraInjector";
    private static WeakHashMap sCameraInfoMap = new WeakHashMap();

}
