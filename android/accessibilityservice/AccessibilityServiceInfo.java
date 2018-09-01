// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accessibilityservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.*;
import android.content.res.*;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.*;
import android.view.accessibility.AccessibilityEvent;
import java.io.IOException;
import java.util.*;
import org.xmlpull.v1.XmlPullParserException;

public class AccessibilityServiceInfo
    implements Parcelable
{
    public static final class CapabilityInfo
    {

        public final int capability;
        public final int descResId;
        public final int titleResId;

        public CapabilityInfo(int i, int j, int k)
        {
            capability = i;
            titleResId = j;
            descResId = k;
        }
    }


    static void _2D_wrap0(AccessibilityServiceInfo accessibilityserviceinfo, Parcel parcel)
    {
        accessibilityserviceinfo.initFromParcel(parcel);
    }

    public AccessibilityServiceInfo()
    {
    }

    public AccessibilityServiceInfo(ResolveInfo resolveinfo, Context context)
        throws XmlPullParserException, IOException
    {
        ServiceInfo serviceinfo;
        Object obj;
        Object obj1;
        Context context1;
        serviceinfo = resolveinfo.serviceInfo;
        mComponentName = new ComponentName(serviceinfo.packageName, serviceinfo.name);
        mResolveInfo = resolveinfo;
        obj = null;
        obj1 = null;
        context1 = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj));
        PackageManager packagemanager = context.getPackageManager();
        context1 = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj));
        context = serviceinfo.loadXmlMetaData(packagemanager, "android.accessibilityservice");
        int i;
        if(context == null)
        {
            if(context != null)
                context.close();
            return;
        }
        i = 0;
_L2:
        if(i == 1 || i == 2)
            break; /* Loop/switch isn't completed */
        context1 = context;
        resolveinfo = context;
        i = context.next();
        if(true) goto _L2; else goto _L1
_L1:
        context1 = context;
        resolveinfo = context;
        if("accessibility-service".equals(context.getName()))
            break MISSING_BLOCK_LABEL_229;
        context1 = context;
        resolveinfo = context;
        obj1 = JVM INSTR new #94  <Class XmlPullParserException>;
        context1 = context;
        resolveinfo = context;
        ((XmlPullParserException) (obj1)).XmlPullParserException("Meta-data does not start withaccessibility-service tag");
        context1 = context;
        resolveinfo = context;
        try
        {
            throw obj1;
        }
        // Misplaced declaration of an exception variable
        catch(ResolveInfo resolveinfo)
        {
            resolveinfo = context1;
        }
        obj1 = JVM INSTR new #94  <Class XmlPullParserException>;
        resolveinfo = context1;
        context = JVM INSTR new #159 <Class StringBuilder>;
        resolveinfo = context1;
        context.StringBuilder();
        resolveinfo = context1;
        ((XmlPullParserException) (obj1)).XmlPullParserException(context.append("Unable to create context for: ").append(serviceinfo.packageName).toString());
        resolveinfo = context1;
        throw obj1;
        context;
        if(resolveinfo != null)
            resolveinfo.close();
        throw context;
        context1 = context;
        resolveinfo = context;
        obj1 = Xml.asAttributeSet(context);
        context1 = context;
        resolveinfo = context;
        obj1 = packagemanager.getResourcesForApplication(serviceinfo.applicationInfo).obtainAttributes(((android.util.AttributeSet) (obj1)), com.android.internal.R.styleable.AccessibilityService);
        context1 = context;
        resolveinfo = context;
        eventTypes = ((TypedArray) (obj1)).getInt(3, 0);
        context1 = context;
        resolveinfo = context;
        obj = ((TypedArray) (obj1)).getString(4);
        if(obj == null)
            break MISSING_BLOCK_LABEL_314;
        context1 = context;
        resolveinfo = context;
        packageNames = ((String) (obj)).split("(\\s)*,(\\s)*");
        context1 = context;
        resolveinfo = context;
        feedbackType = ((TypedArray) (obj1)).getInt(5, 0);
        context1 = context;
        resolveinfo = context;
        notificationTimeout = ((TypedArray) (obj1)).getInt(6, 0);
        context1 = context;
        resolveinfo = context;
        flags = ((TypedArray) (obj1)).getInt(7, 0);
        context1 = context;
        resolveinfo = context;
        mSettingsActivityName = ((TypedArray) (obj1)).getString(2);
        context1 = context;
        resolveinfo = context;
        if(!((TypedArray) (obj1)).getBoolean(8, false))
            break MISSING_BLOCK_LABEL_411;
        context1 = context;
        resolveinfo = context;
        mCapabilities = mCapabilities | 1;
        context1 = context;
        resolveinfo = context;
        if(!((TypedArray) (obj1)).getBoolean(9, false))
            break MISSING_BLOCK_LABEL_442;
        context1 = context;
        resolveinfo = context;
        mCapabilities = mCapabilities | 2;
        context1 = context;
        resolveinfo = context;
        if(!((TypedArray) (obj1)).getBoolean(11, false))
            break MISSING_BLOCK_LABEL_474;
        context1 = context;
        resolveinfo = context;
        mCapabilities = mCapabilities | 8;
        context1 = context;
        resolveinfo = context;
        if(!((TypedArray) (obj1)).getBoolean(12, false))
            break MISSING_BLOCK_LABEL_506;
        context1 = context;
        resolveinfo = context;
        mCapabilities = mCapabilities | 0x10;
        context1 = context;
        resolveinfo = context;
        if(!((TypedArray) (obj1)).getBoolean(13, false))
            break MISSING_BLOCK_LABEL_538;
        context1 = context;
        resolveinfo = context;
        mCapabilities = mCapabilities | 0x20;
        context1 = context;
        resolveinfo = context;
        if(!((TypedArray) (obj1)).getBoolean(14, false))
            break MISSING_BLOCK_LABEL_570;
        context1 = context;
        resolveinfo = context;
        mCapabilities = mCapabilities | 0x40;
        context1 = context;
        resolveinfo = context;
        obj = ((TypedArray) (obj1)).peekValue(0);
        if(obj == null)
            break MISSING_BLOCK_LABEL_638;
        context1 = context;
        resolveinfo = context;
        mDescriptionResId = ((TypedValue) (obj)).resourceId;
        context1 = context;
        resolveinfo = context;
        obj = ((TypedValue) (obj)).coerceToString();
        if(obj == null)
            break MISSING_BLOCK_LABEL_638;
        context1 = context;
        resolveinfo = context;
        mNonLocalizedDescription = ((CharSequence) (obj)).toString().trim();
        context1 = context;
        resolveinfo = context;
        obj = ((TypedArray) (obj1)).peekValue(1);
        if(obj == null)
            break MISSING_BLOCK_LABEL_706;
        context1 = context;
        resolveinfo = context;
        mSummaryResId = ((TypedValue) (obj)).resourceId;
        context1 = context;
        resolveinfo = context;
        obj = ((TypedValue) (obj)).coerceToString();
        if(obj == null)
            break MISSING_BLOCK_LABEL_706;
        context1 = context;
        resolveinfo = context;
        mNonLocalizedSummary = ((CharSequence) (obj)).toString().trim();
        context1 = context;
        resolveinfo = context;
        ((TypedArray) (obj1)).recycle();
        if(context != null)
            context.close();
        return;
    }

    private static void appendCapabilities(StringBuilder stringbuilder, int i)
    {
        stringbuilder.append("capabilities:");
        stringbuilder.append("[");
        do
        {
            if(i == 0)
                break;
            int j = 1 << Integer.numberOfTrailingZeros(i);
            stringbuilder.append(capabilityToString(j));
            j = i & j;
            i = j;
            if(j != 0)
            {
                stringbuilder.append(", ");
                i = j;
            }
        } while(true);
        stringbuilder.append("]");
    }

    private static void appendEventTypes(StringBuilder stringbuilder, int i)
    {
        stringbuilder.append("eventTypes:");
        stringbuilder.append("[");
        do
        {
            if(i == 0)
                break;
            int j = 1 << Integer.numberOfTrailingZeros(i);
            stringbuilder.append(AccessibilityEvent.eventTypeToString(j));
            j = i & j;
            i = j;
            if(j != 0)
            {
                stringbuilder.append(", ");
                i = j;
            }
        } while(true);
        stringbuilder.append("]");
    }

    private static void appendFeedbackTypes(StringBuilder stringbuilder, int i)
    {
        stringbuilder.append("feedbackTypes:");
        stringbuilder.append("[");
        do
        {
            if(i == 0)
                break;
            int j = 1 << Integer.numberOfTrailingZeros(i);
            stringbuilder.append(feedbackTypeToString(j));
            j = i & j;
            i = j;
            if(j != 0)
            {
                stringbuilder.append(", ");
                i = j;
            }
        } while(true);
        stringbuilder.append("]");
    }

    private static void appendFlags(StringBuilder stringbuilder, int i)
    {
        stringbuilder.append("flags:");
        stringbuilder.append("[");
        do
        {
            if(i == 0)
                break;
            int j = 1 << Integer.numberOfTrailingZeros(i);
            stringbuilder.append(flagToString(j));
            j = i & j;
            i = j;
            if(j != 0)
            {
                stringbuilder.append(", ");
                i = j;
            }
        } while(true);
        stringbuilder.append("]");
    }

    private static void appendPackageNames(StringBuilder stringbuilder, String as[])
    {
        stringbuilder.append("packageNames:");
        stringbuilder.append("[");
        if(as != null)
        {
            int i = as.length;
            for(int j = 0; j < i; j++)
            {
                stringbuilder.append(as[j]);
                if(j < i - 1)
                    stringbuilder.append(", ");
            }

        }
        stringbuilder.append("]");
    }

    public static String capabilityToString(int i)
    {
        switch(i)
        {
        default:
            return "UNKNOWN";

        case 1: // '\001'
            return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";

        case 2: // '\002'
            return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";

        case 4: // '\004'
            return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";

        case 8: // '\b'
            return "CAPABILITY_CAN_REQUEST_FILTER_KEY_EVENTS";

        case 16: // '\020'
            return "CAPABILITY_CAN_CONTROL_MAGNIFICATION";

        case 32: // ' '
            return "CAPABILITY_CAN_PERFORM_GESTURES";

        case 64: // '@'
            return "CAPABILITY_CAN_REQUEST_FINGERPRINT_GESTURES";
        }
    }

    public static String feedbackTypeToString(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        do
            if(i != 0)
            {
                int j = 1 << Integer.numberOfTrailingZeros(i);
                i &= j;
                switch(j)
                {
                case 1: // '\001'
                    if(stringbuilder.length() > 1)
                        stringbuilder.append(", ");
                    stringbuilder.append("FEEDBACK_SPOKEN");
                    break;

                case 4: // '\004'
                    if(stringbuilder.length() > 1)
                        stringbuilder.append(", ");
                    stringbuilder.append("FEEDBACK_AUDIBLE");
                    break;

                case 2: // '\002'
                    if(stringbuilder.length() > 1)
                        stringbuilder.append(", ");
                    stringbuilder.append("FEEDBACK_HAPTIC");
                    break;

                case 16: // '\020'
                    if(stringbuilder.length() > 1)
                        stringbuilder.append(", ");
                    stringbuilder.append("FEEDBACK_GENERIC");
                    break;

                case 8: // '\b'
                    if(stringbuilder.length() > 1)
                        stringbuilder.append(", ");
                    stringbuilder.append("FEEDBACK_VISUAL");
                    break;

                case 32: // ' '
                    if(stringbuilder.length() > 1)
                        stringbuilder.append(", ");
                    stringbuilder.append("FEEDBACK_BRAILLE");
                    break;
                }
            } else
            {
                stringbuilder.append("]");
                return stringbuilder.toString();
            }
        while(true);
    }

    private static boolean fingerprintAvailable(Context context)
    {
        boolean flag;
        if(context.getPackageManager().hasSystemFeature("android.hardware.fingerprint"))
            flag = ((FingerprintManager)context.getSystemService(android/hardware/fingerprint/FingerprintManager)).isHardwareDetected();
        else
            flag = false;
        return flag;
    }

    public static String flagToString(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 1: // '\001'
            return "DEFAULT";

        case 2: // '\002'
            return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";

        case 4: // '\004'
            return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";

        case 8: // '\b'
            return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";

        case 16: // '\020'
            return "FLAG_REPORT_VIEW_IDS";

        case 32: // ' '
            return "FLAG_REQUEST_FILTER_KEY_EVENTS";

        case 64: // '@'
            return "FLAG_RETRIEVE_INTERACTIVE_WINDOWS";

        case 128: 
            return "FLAG_ENABLE_ACCESSIBILITY_VOLUME";

        case 256: 
            return "FLAG_REQUEST_ACCESSIBILITY_BUTTON";

        case 512: 
            return "FLAG_REQUEST_FINGERPRINT_GESTURES";
        }
    }

    private static SparseArray getCapabilityInfoSparseArray(Context context)
    {
        if(sAvailableCapabilityInfos == null)
        {
            sAvailableCapabilityInfos = new SparseArray();
            sAvailableCapabilityInfos.put(1, new CapabilityInfo(1, 0x10400fc, 0x10400f5));
            sAvailableCapabilityInfos.put(2, new CapabilityInfo(2, 0x10400fb, 0x10400f4));
            sAvailableCapabilityInfos.put(8, new CapabilityInfo(8, 0x10400fa, 0x10400f3));
            sAvailableCapabilityInfos.put(16, new CapabilityInfo(16, 0x10400f7, 0x10400f0));
            sAvailableCapabilityInfos.put(32, new CapabilityInfo(32, 0x10400f8, 0x10400f1));
            if(context == null || fingerprintAvailable(context))
                sAvailableCapabilityInfos.put(64, new CapabilityInfo(64, 0x10400f6, 0x10400ef));
        }
        return sAvailableCapabilityInfos;
    }

    private void initFromParcel(Parcel parcel)
    {
        eventTypes = parcel.readInt();
        packageNames = parcel.readStringArray();
        feedbackType = parcel.readInt();
        notificationTimeout = parcel.readLong();
        flags = parcel.readInt();
        mComponentName = (ComponentName)parcel.readParcelable(getClass().getClassLoader());
        mResolveInfo = (ResolveInfo)parcel.readParcelable(null);
        mSettingsActivityName = parcel.readString();
        mCapabilities = parcel.readInt();
        mSummaryResId = parcel.readInt();
        mNonLocalizedSummary = parcel.readString();
        mDescriptionResId = parcel.readInt();
        mNonLocalizedDescription = parcel.readString();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (AccessibilityServiceInfo)obj;
        if(mComponentName == null)
        {
            if(((AccessibilityServiceInfo) (obj)).mComponentName != null)
                return false;
        } else
        if(!mComponentName.equals(((AccessibilityServiceInfo) (obj)).mComponentName))
            return false;
        return true;
    }

    public boolean getCanRetrieveWindowContent()
    {
        boolean flag = false;
        if((mCapabilities & 1) != 0)
            flag = true;
        return flag;
    }

    public int getCapabilities()
    {
        return mCapabilities;
    }

    public List getCapabilityInfos()
    {
        return getCapabilityInfos(null);
    }

    public List getCapabilityInfos(Context context)
    {
        if(mCapabilities == 0)
            return Collections.emptyList();
        int i = mCapabilities;
        ArrayList arraylist = new ArrayList();
        context = getCapabilityInfoSparseArray(context);
        do
        {
            if(i == 0)
                break;
            int j = 1 << Integer.numberOfTrailingZeros(i);
            int k = i & j;
            CapabilityInfo capabilityinfo = (CapabilityInfo)context.get(j);
            i = k;
            if(capabilityinfo != null)
            {
                arraylist.add(capabilityinfo);
                i = k;
            }
        } while(true);
        return arraylist;
    }

    public ComponentName getComponentName()
    {
        return mComponentName;
    }

    public String getDescription()
    {
        return mNonLocalizedDescription;
    }

    public String getId()
    {
        return mComponentName.flattenToShortString();
    }

    public ResolveInfo getResolveInfo()
    {
        return mResolveInfo;
    }

    public String getSettingsActivityName()
    {
        return mSettingsActivityName;
    }

    public int hashCode()
    {
        int i;
        if(mComponentName == null)
            i = 0;
        else
            i = mComponentName.hashCode();
        return i + 31;
    }

    public boolean isDirectBootAware()
    {
        boolean flag;
        if((flags & 0x10000) == 0)
            flag = mResolveInfo.serviceInfo.directBootAware;
        else
            flag = true;
        return flag;
    }

    public String loadDescription(PackageManager packagemanager)
    {
        if(mDescriptionResId == 0)
            return mNonLocalizedDescription;
        ServiceInfo serviceinfo = mResolveInfo.serviceInfo;
        packagemanager = packagemanager.getText(serviceinfo.packageName, mDescriptionResId, serviceinfo.applicationInfo);
        if(packagemanager != null)
            return packagemanager.toString().trim();
        else
            return null;
    }

    public CharSequence loadSummary(PackageManager packagemanager)
    {
        if(mSummaryResId == 0)
            return mNonLocalizedSummary;
        ServiceInfo serviceinfo = mResolveInfo.serviceInfo;
        packagemanager = packagemanager.getText(serviceinfo.packageName, mSummaryResId, serviceinfo.applicationInfo);
        if(packagemanager != null)
            return packagemanager.toString().trim();
        else
            return null;
    }

    public void setCapabilities(int i)
    {
        mCapabilities = i;
    }

    public void setComponentName(ComponentName componentname)
    {
        mComponentName = componentname;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        appendEventTypes(stringbuilder, eventTypes);
        stringbuilder.append(", ");
        appendPackageNames(stringbuilder, packageNames);
        stringbuilder.append(", ");
        appendFeedbackTypes(stringbuilder, feedbackType);
        stringbuilder.append(", ");
        stringbuilder.append("notificationTimeout: ").append(notificationTimeout);
        stringbuilder.append(", ");
        appendFlags(stringbuilder, flags);
        stringbuilder.append(", ");
        stringbuilder.append("id: ").append(getId());
        stringbuilder.append(", ");
        stringbuilder.append("resolveInfo: ").append(mResolveInfo);
        stringbuilder.append(", ");
        stringbuilder.append("settingsActivityName: ").append(mSettingsActivityName);
        stringbuilder.append(", ");
        stringbuilder.append("summary: ").append(mNonLocalizedSummary);
        stringbuilder.append(", ");
        appendCapabilities(stringbuilder, mCapabilities);
        return stringbuilder.toString();
    }

    public void updateDynamicallyConfigurableProperties(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        eventTypes = accessibilityserviceinfo.eventTypes;
        packageNames = accessibilityserviceinfo.packageNames;
        feedbackType = accessibilityserviceinfo.feedbackType;
        notificationTimeout = accessibilityserviceinfo.notificationTimeout;
        flags = accessibilityserviceinfo.flags;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(eventTypes);
        parcel.writeStringArray(packageNames);
        parcel.writeInt(feedbackType);
        parcel.writeLong(notificationTimeout);
        parcel.writeInt(flags);
        parcel.writeParcelable(mComponentName, i);
        parcel.writeParcelable(mResolveInfo, 0);
        parcel.writeString(mSettingsActivityName);
        parcel.writeInt(mCapabilities);
        parcel.writeInt(mSummaryResId);
        parcel.writeString(mNonLocalizedSummary);
        parcel.writeInt(mDescriptionResId);
        parcel.writeString(mNonLocalizedDescription);
    }

    public static final int CAPABILITY_CAN_CONTROL_MAGNIFICATION = 16;
    public static final int CAPABILITY_CAN_PERFORM_GESTURES = 32;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_FINGERPRINT_GESTURES = 64;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AccessibilityServiceInfo createFromParcel(Parcel parcel)
        {
            AccessibilityServiceInfo accessibilityserviceinfo = new AccessibilityServiceInfo();
            AccessibilityServiceInfo._2D_wrap0(accessibilityserviceinfo, parcel);
            return accessibilityserviceinfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AccessibilityServiceInfo[] newArray(int i)
        {
            return new AccessibilityServiceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DEFAULT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_AUDIBLE = 4;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FEEDBACK_GENERIC = 16;
    public static final int FEEDBACK_HAPTIC = 2;
    public static final int FEEDBACK_SPOKEN = 1;
    public static final int FEEDBACK_VISUAL = 8;
    public static final int FLAG_ENABLE_ACCESSIBILITY_VOLUME = 128;
    public static final int FLAG_FORCE_DIRECT_BOOT_AWARE = 0x10000;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ACCESSIBILITY_BUTTON = 256;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_FINGERPRINT_GESTURES = 512;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
    public static final int FLAG_RETRIEVE_INTERACTIVE_WINDOWS = 64;
    private static final String TAG_ACCESSIBILITY_SERVICE = "accessibility-service";
    private static SparseArray sAvailableCapabilityInfos;
    public int eventTypes;
    public int feedbackType;
    public int flags;
    private int mCapabilities;
    private ComponentName mComponentName;
    private int mDescriptionResId;
    private String mNonLocalizedDescription;
    private String mNonLocalizedSummary;
    private ResolveInfo mResolveInfo;
    private String mSettingsActivityName;
    private int mSummaryResId;
    public long notificationTimeout;
    public String packageNames[];

}
