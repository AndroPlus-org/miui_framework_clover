// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Printer;

// Referenced classes of package android.content.pm:
//            ComponentInfo, ApplicationInfo

public class ActivityInfo extends ComponentInfo
    implements Parcelable
{
    public static final class WindowLayout
    {

        public final int gravity;
        public final int height;
        public final float heightFraction;
        public final int minHeight;
        public final int minWidth;
        public final int width;
        public final float widthFraction;

        public WindowLayout(int i, float f, int j, float f1, int k, int l, int i1)
        {
            width = i;
            widthFraction = f;
            height = j;
            heightFraction = f1;
            gravity = k;
            minWidth = l;
            minHeight = i1;
        }

        WindowLayout(Parcel parcel)
        {
            width = parcel.readInt();
            widthFraction = parcel.readFloat();
            height = parcel.readInt();
            heightFraction = parcel.readFloat();
            gravity = parcel.readInt();
            minWidth = parcel.readInt();
            minHeight = parcel.readInt();
        }
    }


    public ActivityInfo()
    {
        resizeMode = 2;
        colorMode = 0;
        screenOrientation = -1;
        uiOptions = 0;
        rotationAnimation = -1;
    }

    public ActivityInfo(ActivityInfo activityinfo)
    {
        super(activityinfo);
        resizeMode = 2;
        colorMode = 0;
        screenOrientation = -1;
        uiOptions = 0;
        rotationAnimation = -1;
        theme = activityinfo.theme;
        launchMode = activityinfo.launchMode;
        documentLaunchMode = activityinfo.documentLaunchMode;
        permission = activityinfo.permission;
        taskAffinity = activityinfo.taskAffinity;
        targetActivity = activityinfo.targetActivity;
        flags = activityinfo.flags;
        screenOrientation = activityinfo.screenOrientation;
        configChanges = activityinfo.configChanges;
        softInputMode = activityinfo.softInputMode;
        uiOptions = activityinfo.uiOptions;
        parentActivityName = activityinfo.parentActivityName;
        maxRecents = activityinfo.maxRecents;
        lockTaskLaunchMode = activityinfo.lockTaskLaunchMode;
        windowLayout = activityinfo.windowLayout;
        resizeMode = activityinfo.resizeMode;
        requestedVrComponent = activityinfo.requestedVrComponent;
        rotationAnimation = activityinfo.rotationAnimation;
        colorMode = activityinfo.colorMode;
        maxAspectRatio = activityinfo.maxAspectRatio;
    }

    private ActivityInfo(Parcel parcel)
    {
        super(parcel);
        resizeMode = 2;
        colorMode = 0;
        screenOrientation = -1;
        uiOptions = 0;
        rotationAnimation = -1;
        theme = parcel.readInt();
        launchMode = parcel.readInt();
        documentLaunchMode = parcel.readInt();
        permission = parcel.readString();
        taskAffinity = parcel.readString();
        targetActivity = parcel.readString();
        flags = parcel.readInt();
        screenOrientation = parcel.readInt();
        configChanges = parcel.readInt();
        softInputMode = parcel.readInt();
        uiOptions = parcel.readInt();
        parentActivityName = parcel.readString();
        persistableMode = parcel.readInt();
        maxRecents = parcel.readInt();
        lockTaskLaunchMode = parcel.readInt();
        if(parcel.readInt() == 1)
            windowLayout = new WindowLayout(parcel);
        resizeMode = parcel.readInt();
        requestedVrComponent = parcel.readString();
        rotationAnimation = parcel.readInt();
        colorMode = parcel.readInt();
        maxAspectRatio = parcel.readFloat();
    }

    ActivityInfo(Parcel parcel, ActivityInfo activityinfo)
    {
        this(parcel);
    }

    public static int activityInfoConfigJavaToNative(int i)
    {
        int j = 0;
        for(int k = 0; k < CONFIG_NATIVE_BITS.length;)
        {
            int l = j;
            if((1 << k & i) != 0)
                l = j | CONFIG_NATIVE_BITS[k];
            k++;
            j = l;
        }

        return j;
    }

    public static int activityInfoConfigNativeToJava(int i)
    {
        int j = 0;
        for(int k = 0; k < CONFIG_NATIVE_BITS.length;)
        {
            int l = j;
            if((CONFIG_NATIVE_BITS[k] & i) != 0)
                l = j | 1 << k;
            k++;
            j = l;
        }

        return j;
    }

    public static boolean isFixedOrientationLandscape(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i == 0) goto _L2; else goto _L1
_L1:
        if(i != 6) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 8)
        {
            flag1 = flag;
            if(i != 11)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static boolean isFixedOrientationPortrait(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i == 1) goto _L2; else goto _L1
_L1:
        if(i != 7) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 9)
        {
            flag1 = flag;
            if(i != 12)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static boolean isPreserveOrientationMode(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i == 6) goto _L2; else goto _L1
_L1:
        if(i != 5) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 7)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static boolean isResizeableMode(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i == 2) goto _L2; else goto _L1
_L1:
        if(i != 4) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 6)
        {
            flag1 = flag;
            if(i != 5)
            {
                flag1 = flag;
                if(i != 7)
                {
                    flag1 = flag;
                    if(i != 1)
                        flag1 = false;
                }
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static final String lockTaskLaunchModeToString(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("unknown=").append(i).toString();

        case 0: // '\0'
            return "LOCK_TASK_LAUNCH_MODE_DEFAULT";

        case 1: // '\001'
            return "LOCK_TASK_LAUNCH_MODE_NEVER";

        case 2: // '\002'
            return "LOCK_TASK_LAUNCH_MODE_ALWAYS";

        case 3: // '\003'
            return "LOCK_TASK_LAUNCH_MODE_IF_WHITELISTED";
        }
    }

    private String persistableModeToString()
    {
        switch(persistableMode)
        {
        default:
            return (new StringBuilder()).append("UNKNOWN=").append(persistableMode).toString();

        case 0: // '\0'
            return "PERSIST_ROOT_ONLY";

        case 1: // '\001'
            return "PERSIST_NEVER";

        case 2: // '\002'
            return "PERSIST_ACROSS_REBOOTS";
        }
    }

    public static String resizeModeToString(int i)
    {
        switch(i)
        {
        case 3: // '\003'
        default:
            return (new StringBuilder()).append("unknown=").append(i).toString();

        case 0: // '\0'
            return "RESIZE_MODE_UNRESIZEABLE";

        case 1: // '\001'
            return "RESIZE_MODE_RESIZEABLE_VIA_SDK_VERSION";

        case 2: // '\002'
            return "RESIZE_MODE_RESIZEABLE";

        case 4: // '\004'
            return "RESIZE_MODE_FORCE_RESIZEABLE";

        case 6: // '\006'
            return "RESIZE_MODE_FORCE_RESIZABLE_PORTRAIT_ONLY";

        case 5: // '\005'
            return "RESIZE_MODE_FORCE_RESIZABLE_LANDSCAPE_ONLY";

        case 7: // '\007'
            return "RESIZE_MODE_FORCE_RESIZABLE_PRESERVE_ORIENTATION";
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(Printer printer, String s)
    {
        dump(printer, s, 3);
    }

    public void dump(Printer printer, String s, int i)
    {
        super.dumpFront(printer, s);
        if(permission != null)
            printer.println((new StringBuilder()).append(s).append("permission=").append(permission).toString());
        if((i & 1) != 0)
            printer.println((new StringBuilder()).append(s).append("taskAffinity=").append(taskAffinity).append(" targetActivity=").append(targetActivity).append(" persistableMode=").append(persistableModeToString()).toString());
        break MISSING_BLOCK_LABEL_111;
        if(launchMode != 0 || flags != 0 || theme != 0)
            printer.println((new StringBuilder()).append(s).append("launchMode=").append(launchMode).append(" flags=0x").append(Integer.toHexString(flags)).append(" theme=0x").append(Integer.toHexString(theme)).toString());
        if(screenOrientation != -1 || configChanges != 0 || softInputMode != 0)
            printer.println((new StringBuilder()).append(s).append("screenOrientation=").append(screenOrientation).append(" configChanges=0x").append(Integer.toHexString(configChanges)).append(" softInputMode=0x").append(Integer.toHexString(softInputMode)).toString());
        if(uiOptions != 0)
            printer.println((new StringBuilder()).append(s).append(" uiOptions=0x").append(Integer.toHexString(uiOptions)).toString());
        if((i & 1) != 0)
            printer.println((new StringBuilder()).append(s).append("lockTaskLaunchMode=").append(lockTaskLaunchModeToString(lockTaskLaunchMode)).toString());
        if(windowLayout != null)
            printer.println((new StringBuilder()).append(s).append("windowLayout=").append(windowLayout.width).append("|").append(windowLayout.widthFraction).append(", ").append(windowLayout.height).append("|").append(windowLayout.heightFraction).append(", ").append(windowLayout.gravity).toString());
        printer.println((new StringBuilder()).append(s).append("resizeMode=").append(resizeModeToString(resizeMode)).toString());
        if(requestedVrComponent != null)
            printer.println((new StringBuilder()).append(s).append("requestedVrComponent=").append(requestedVrComponent).toString());
        if(maxAspectRatio != 0.0F)
            printer.println((new StringBuilder()).append(s).append("maxAspectRatio=").append(maxAspectRatio).toString());
        super.dumpBack(printer, s, i);
        return;
    }

    public int getRealConfigChanged()
    {
        int i;
        if(applicationInfo.targetSdkVersion < 13)
            i = configChanges | 0x400 | 0x800;
        else
            i = configChanges;
        return i;
    }

    public final int getThemeResource()
    {
        int i;
        if(theme != 0)
            i = theme;
        else
            i = applicationInfo.theme;
        return i;
    }

    boolean isFixedOrientation()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!isFixedOrientationLandscape())
        {
            flag1 = flag;
            if(!isFixedOrientationPortrait())
                if(screenOrientation == 14)
                    flag1 = flag;
                else
                    flag1 = false;
        }
        return flag1;
    }

    boolean isFixedOrientationLandscape()
    {
        return isFixedOrientationLandscape(screenOrientation);
    }

    boolean isFixedOrientationPortrait()
    {
        return isFixedOrientationPortrait(screenOrientation);
    }

    public boolean supportsPictureInPicture()
    {
        boolean flag = false;
        if((flags & 0x400000) != 0)
            flag = true;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ActivityInfo{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(name).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(theme);
        parcel.writeInt(launchMode);
        parcel.writeInt(documentLaunchMode);
        parcel.writeString(permission);
        parcel.writeString(taskAffinity);
        parcel.writeString(targetActivity);
        parcel.writeInt(flags);
        parcel.writeInt(screenOrientation);
        parcel.writeInt(configChanges);
        parcel.writeInt(softInputMode);
        parcel.writeInt(uiOptions);
        parcel.writeString(parentActivityName);
        parcel.writeInt(persistableMode);
        parcel.writeInt(maxRecents);
        parcel.writeInt(lockTaskLaunchMode);
        if(windowLayout != null)
        {
            parcel.writeInt(1);
            parcel.writeInt(windowLayout.width);
            parcel.writeFloat(windowLayout.widthFraction);
            parcel.writeInt(windowLayout.height);
            parcel.writeFloat(windowLayout.heightFraction);
            parcel.writeInt(windowLayout.gravity);
            parcel.writeInt(windowLayout.minWidth);
            parcel.writeInt(windowLayout.minHeight);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(resizeMode);
        parcel.writeString(requestedVrComponent);
        parcel.writeInt(rotationAnimation);
        parcel.writeInt(colorMode);
        parcel.writeFloat(maxAspectRatio);
    }

    public static final int COLOR_MODE_DEFAULT = 0;
    public static final int COLOR_MODE_HDR = 2;
    public static final int COLOR_MODE_WIDE_COLOR_GAMUT = 1;
    public static final int CONFIG_ASSETS_PATHS = 0x80000000;
    public static final int CONFIG_COLOR_MODE = 16384;
    public static final int CONFIG_DENSITY = 4096;
    public static final int CONFIG_FONT_SCALE = 0x40000000;
    public static final int CONFIG_KEYBOARD = 16;
    public static final int CONFIG_KEYBOARD_HIDDEN = 32;
    public static final int CONFIG_LAYOUT_DIRECTION = 8192;
    public static final int CONFIG_LOCALE = 4;
    public static final int CONFIG_MCC = 1;
    public static final int CONFIG_MNC = 2;
    public static int CONFIG_NATIVE_BITS[] = {
        2, 1, 4, 8, 16, 32, 64, 128, 2048, 4096, 
        512, 8192, 256, 16384, 0x10000
    };
    public static final int CONFIG_NAVIGATION = 64;
    public static final int CONFIG_ORIENTATION = 128;
    public static final int CONFIG_SCREEN_LAYOUT = 256;
    public static final int CONFIG_SCREEN_SIZE = 1024;
    public static final int CONFIG_SMALLEST_SCREEN_SIZE = 2048;
    public static final int CONFIG_TOUCHSCREEN = 8;
    public static final int CONFIG_UI_MODE = 512;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ActivityInfo createFromParcel(Parcel parcel)
        {
            return new ActivityInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ActivityInfo[] newArray(int i)
        {
            return new ActivityInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DOCUMENT_LAUNCH_ALWAYS = 2;
    public static final int DOCUMENT_LAUNCH_INTO_EXISTING = 1;
    public static final int DOCUMENT_LAUNCH_NEVER = 3;
    public static final int DOCUMENT_LAUNCH_NONE = 0;
    public static final int FLAG_ALLOW_EMBEDDED = 0x80000000;
    public static final int FLAG_ALLOW_TASK_REPARENTING = 64;
    public static final int FLAG_ALWAYS_FOCUSABLE = 0x40000;
    public static final int FLAG_ALWAYS_RETAIN_TASK_STATE = 8;
    public static final int FLAG_AUTO_REMOVE_FROM_RECENTS = 8192;
    public static final int FLAG_CLEAR_TASK_ON_LAUNCH = 4;
    public static final int FLAG_ENABLE_VR_MODE = 32768;
    public static final int FLAG_EXCLUDE_FROM_RECENTS = 32;
    public static final int FLAG_FINISH_ON_CLOSE_SYSTEM_DIALOGS = 256;
    public static final int FLAG_FINISH_ON_TASK_LAUNCH = 2;
    public static final int FLAG_HARDWARE_ACCELERATED = 512;
    public static final int FLAG_IMMERSIVE = 2048;
    public static final int FLAG_IMPLICITLY_VISIBLE_TO_INSTANT_APP = 0x200000;
    public static final int FLAG_MULTIPROCESS = 1;
    public static final int FLAG_NO_HISTORY = 128;
    public static final int FLAG_RELINQUISH_TASK_IDENTITY = 4096;
    public static final int FLAG_RESUME_WHILE_PAUSING = 16384;
    public static final int FLAG_SHOW_FOR_ALL_USERS = 1024;
    public static final int FLAG_SHOW_WHEN_LOCKED = 0x800000;
    public static final int FLAG_SINGLE_USER = 0x40000000;
    public static final int FLAG_STATE_NOT_NEEDED = 16;
    public static final int FLAG_SUPPORTS_PICTURE_IN_PICTURE = 0x400000;
    public static final int FLAG_SYSTEM_USER_ONLY = 0x20000000;
    public static final int FLAG_TURN_SCREEN_ON = 0x1000000;
    public static final int FLAG_VISIBLE_TO_INSTANT_APP = 0x100000;
    public static final int LAUNCH_MULTIPLE = 0;
    public static final int LAUNCH_SINGLE_INSTANCE = 3;
    public static final int LAUNCH_SINGLE_TASK = 2;
    public static final int LAUNCH_SINGLE_TOP = 1;
    public static final int LOCK_TASK_LAUNCH_MODE_ALWAYS = 2;
    public static final int LOCK_TASK_LAUNCH_MODE_DEFAULT = 0;
    public static final int LOCK_TASK_LAUNCH_MODE_IF_WHITELISTED = 3;
    public static final int LOCK_TASK_LAUNCH_MODE_NEVER = 1;
    public static final int PERSIST_ACROSS_REBOOTS = 2;
    public static final int PERSIST_NEVER = 1;
    public static final int PERSIST_ROOT_ONLY = 0;
    public static final int RESIZE_MODE_FORCE_RESIZABLE_LANDSCAPE_ONLY = 5;
    public static final int RESIZE_MODE_FORCE_RESIZABLE_PORTRAIT_ONLY = 6;
    public static final int RESIZE_MODE_FORCE_RESIZABLE_PRESERVE_ORIENTATION = 7;
    public static final int RESIZE_MODE_FORCE_RESIZEABLE = 4;
    public static final int RESIZE_MODE_RESIZEABLE = 2;
    public static final int RESIZE_MODE_RESIZEABLE_AND_PIPABLE_DEPRECATED = 3;
    public static final int RESIZE_MODE_RESIZEABLE_VIA_SDK_VERSION = 1;
    public static final int RESIZE_MODE_UNRESIZEABLE = 0;
    public static final int SCREEN_ORIENTATION_BEHIND = 3;
    public static final int SCREEN_ORIENTATION_FULL_SENSOR = 10;
    public static final int SCREEN_ORIENTATION_FULL_USER = 13;
    public static final int SCREEN_ORIENTATION_LANDSCAPE = 0;
    public static final int SCREEN_ORIENTATION_LOCKED = 14;
    public static final int SCREEN_ORIENTATION_NOSENSOR = 5;
    public static final int SCREEN_ORIENTATION_PORTRAIT = 1;
    public static final int SCREEN_ORIENTATION_REVERSE_LANDSCAPE = 8;
    public static final int SCREEN_ORIENTATION_REVERSE_PORTRAIT = 9;
    public static final int SCREEN_ORIENTATION_SENSOR = 4;
    public static final int SCREEN_ORIENTATION_SENSOR_LANDSCAPE = 6;
    public static final int SCREEN_ORIENTATION_SENSOR_PORTRAIT = 7;
    public static final int SCREEN_ORIENTATION_UNSET = -2;
    public static final int SCREEN_ORIENTATION_UNSPECIFIED = -1;
    public static final int SCREEN_ORIENTATION_USER = 2;
    public static final int SCREEN_ORIENTATION_USER_LANDSCAPE = 11;
    public static final int SCREEN_ORIENTATION_USER_PORTRAIT = 12;
    public static final int UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW = 1;
    public int colorMode;
    public int configChanges;
    public int documentLaunchMode;
    public int flags;
    public int launchMode;
    public String launchToken;
    public int lockTaskLaunchMode;
    public float maxAspectRatio;
    public int maxRecents;
    public String parentActivityName;
    public String permission;
    public int persistableMode;
    public String requestedVrComponent;
    public int resizeMode;
    public int rotationAnimation;
    public int screenOrientation;
    public int softInputMode;
    public String targetActivity;
    public String taskAffinity;
    public int theme;
    public int uiOptions;
    public WindowLayout windowLayout;

}
