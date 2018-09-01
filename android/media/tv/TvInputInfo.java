// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.content.*;
import android.content.pm.*;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.hardware.hdmi.HdmiDeviceInfo;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.media.tv:
//            TvInputHardwareInfo, TvInputManager

public final class TvInputInfo
    implements Parcelable
{
    public static final class Builder
    {

        private static String generateInputId(ComponentName componentname)
        {
            return componentname.flattenToShortString();
        }

        private static String generateInputId(ComponentName componentname, HdmiDeviceInfo hdmideviceinfo)
        {
            return (new StringBuilder()).append(componentname.flattenToShortString()).append(String.format(Locale.ENGLISH, "/HDMI%04X%02X", new Object[] {
                Integer.valueOf(hdmideviceinfo.getPhysicalAddress()), Integer.valueOf(hdmideviceinfo.getId())
            })).toString();
        }

        private static String generateInputId(ComponentName componentname, TvInputHardwareInfo tvinputhardwareinfo)
        {
            return (new StringBuilder()).append(componentname.flattenToShortString()).append("/").append("HW").append(tvinputhardwareinfo.getDeviceId()).toString();
        }

        private void parseServiceMetadata(int i)
        {
            ServiceInfo serviceinfo;
            Object obj;
            Object obj1;
            Object obj2;
            Object obj3;
            Object obj4;
            serviceinfo = mResolveInfo.serviceInfo;
            obj = mContext.getPackageManager();
            obj1 = null;
            obj2 = null;
            obj3 = null;
            obj4 = null;
            Object obj5 = serviceinfo.loadXmlMetaData(((PackageManager) (obj)), "android.media.tv.input");
            if(obj5 != null) goto _L2; else goto _L1
_L1:
            obj4 = obj5;
            obj3 = obj5;
            obj1 = JVM INSTR new #188 <Class IllegalStateException>;
            obj4 = obj5;
            obj3 = obj5;
            obj = JVM INSTR new #116 <Class StringBuilder>;
            obj4 = obj5;
            obj3 = obj5;
            ((StringBuilder) (obj)).StringBuilder();
            obj4 = obj5;
            obj3 = obj5;
            ((IllegalStateException) (obj1)).IllegalStateException(((StringBuilder) (obj)).append("No android.media.tv.input meta-data found for ").append(serviceinfo.name).toString());
            obj4 = obj5;
            obj3 = obj5;
            try
            {
                throw obj1;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj3) { }
            throw obj3;
            obj5;
_L10:
            obj2 = obj3;
            if(obj4 == null)
                break MISSING_BLOCK_LABEL_146;
            ((XmlResourceParser) (obj4)).close();
            obj2 = obj3;
_L8:
            if(obj2 == null)
                break; /* Loop/switch isn't completed */
            Resources resources;
            int j;
            try
            {
                throw obj2;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj4)
            {
                throw new IllegalStateException((new StringBuilder()).append("Failed reading meta-data for ").append(serviceinfo.packageName).toString(), ((Throwable) (obj4)));
            }
            catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                throw new IllegalStateException((new StringBuilder()).append("No resources found for ").append(serviceinfo.packageName).toString(), namenotfoundexception);
            }
_L2:
            obj4 = obj5;
            obj3 = obj5;
            resources = ((PackageManager) (obj)).getResourcesForApplication(serviceinfo.applicationInfo);
            obj4 = obj5;
            obj3 = obj5;
            obj = Xml.asAttributeSet(((org.xmlpull.v1.XmlPullParser) (obj5)));
_L4:
            obj4 = obj5;
            obj3 = obj5;
            j = ((XmlResourceParser) (obj5)).next();
            if(j != 1 && j != 2) goto _L4; else goto _L3
_L3:
            obj4 = obj5;
            obj3 = obj5;
            if("tv-input".equals(((XmlResourceParser) (obj5)).getName()))
                break MISSING_BLOCK_LABEL_362;
            obj4 = obj5;
            obj3 = obj5;
            obj = JVM INSTR new #188 <Class IllegalStateException>;
            obj4 = obj5;
            obj3 = obj5;
            obj1 = JVM INSTR new #116 <Class StringBuilder>;
            obj4 = obj5;
            obj3 = obj5;
            ((StringBuilder) (obj1)).StringBuilder();
            obj4 = obj5;
            obj3 = obj5;
            ((IllegalStateException) (obj)).IllegalStateException(((StringBuilder) (obj1)).append("Meta-data does not start with tv-input tag for ").append(serviceinfo.name).toString());
            obj4 = obj5;
            obj3 = obj5;
            throw obj;
            obj5;
            obj4 = obj3;
            obj3 = obj2;
            continue; /* Loop/switch isn't completed */
            obj4 = obj5;
            obj3 = obj5;
            obj = resources.obtainAttributes(((android.util.AttributeSet) (obj)), com.android.internal.R.styleable.TvInputService);
            obj4 = obj5;
            obj3 = obj5;
            mSetupActivity = ((TypedArray) (obj)).getString(1);
            obj4 = obj5;
            obj3 = obj5;
            if(mCanRecord != null)
                break MISSING_BLOCK_LABEL_433;
            obj4 = obj5;
            obj3 = obj5;
            mCanRecord = Boolean.valueOf(((TypedArray) (obj)).getBoolean(2, false));
            obj4 = obj5;
            obj3 = obj5;
            if(mTunerCount != null || i != 0)
                break MISSING_BLOCK_LABEL_473;
            obj4 = obj5;
            obj3 = obj5;
            mTunerCount = Integer.valueOf(((TypedArray) (obj)).getInt(3, 1));
            obj4 = obj5;
            obj3 = obj5;
            ((TypedArray) (obj)).recycle();
            obj4 = obj1;
            if(obj5 == null)
                break MISSING_BLOCK_LABEL_505;
            ((XmlResourceParser) (obj5)).close();
            obj4 = obj1;
_L6:
            if(obj4 == null)
                break; /* Loop/switch isn't completed */
            throw obj4;
            namenotfoundexception;
            if(true) goto _L6; else goto _L5
            Throwable throwable;
            throwable;
label0:
            {
                if(obj3 != null)
                    break label0;
                obj2 = throwable;
            }
            continue; /* Loop/switch isn't completed */
            for(obj2 = obj3; obj3 == throwable; obj2 = obj3)
                continue; /* Loop/switch isn't completed */

            ((Throwable) (obj3)).addSuppressed(throwable);
            obj2 = obj3;
            if(true) goto _L8; else goto _L7
_L7:
            throw obj5;
_L5:
            return;
            if(true) goto _L10; else goto _L9
_L9:
        }

        public TvInputInfo build()
        {
            Object obj = new ComponentName(mResolveInfo.serviceInfo.packageName, mResolveInfo.serviceInfo.name);
            boolean flag = false;
            boolean flag1 = false;
            int i;
            boolean flag2;
            int k;
            if(mHdmiDeviceInfo != null)
            {
                obj = generateInputId(((ComponentName) (obj)), mHdmiDeviceInfo);
                i = 1007;
                flag = true;
                ResolveInfo resolveinfo;
                CharSequence charsequence;
                int j;
                Icon icon;
                Icon icon1;
                Icon icon2;
                String s;
                if((mHdmiDeviceInfo.getPhysicalAddress() & 0xfff) != 0)
                    flag1 = true;
                else
                    flag1 = false;
            } else
            if(mTvInputHardwareInfo != null)
            {
                obj = generateInputId(((ComponentName) (obj)), mTvInputHardwareInfo);
                i = sHardwareTypeToTvInputType.get(mTvInputHardwareInfo.getType(), 0);
                flag = true;
            } else
            {
                obj = generateInputId(((ComponentName) (obj)));
                i = 0;
            }
            parseServiceMetadata(i);
            resolveinfo = mResolveInfo;
            charsequence = mLabel;
            j = mLabelResId;
            icon = mIcon;
            icon1 = mIconStandby;
            icon2 = mIconDisconnected;
            s = mSetupActivity;
            if(mCanRecord == null)
                flag2 = false;
            else
                flag2 = mCanRecord.booleanValue();
            if(mTunerCount == null)
                k = 0;
            else
                k = mTunerCount.intValue();
            return new TvInputInfo(resolveinfo, ((String) (obj)), i, flag, charsequence, j, icon, icon1, icon2, s, flag2, k, mHdmiDeviceInfo, flag1, mParentId, mExtras, null);
        }

        public Builder setCanRecord(boolean flag)
        {
            mCanRecord = Boolean.valueOf(flag);
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            mExtras = bundle;
            return this;
        }

        public Builder setHdmiDeviceInfo(HdmiDeviceInfo hdmideviceinfo)
        {
            if(mTvInputHardwareInfo != null)
            {
                Log.w("TvInputInfo", "TvInputHardwareInfo will not be used to build this TvInputInfo");
                mTvInputHardwareInfo = null;
            }
            mHdmiDeviceInfo = hdmideviceinfo;
            return this;
        }

        public Builder setIcon(Icon icon)
        {
            mIcon = icon;
            return this;
        }

        public Builder setIcon(Icon icon, int i)
        {
            if(i == 0)
                mIcon = icon;
            else
            if(i == 1)
                mIconStandby = icon;
            else
            if(i == 2)
                mIconDisconnected = icon;
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown state: ").append(i).toString());
            return this;
        }

        public Builder setLabel(int i)
        {
            if(mLabel != null)
            {
                throw new IllegalStateException("Label text is already set.");
            } else
            {
                mLabelResId = i;
                return this;
            }
        }

        public Builder setLabel(CharSequence charsequence)
        {
            if(mLabelResId != 0)
            {
                throw new IllegalStateException("Resource ID for label is already set.");
            } else
            {
                mLabel = charsequence;
                return this;
            }
        }

        public Builder setParentId(String s)
        {
            mParentId = s;
            return this;
        }

        public Builder setTunerCount(int i)
        {
            mTunerCount = Integer.valueOf(i);
            return this;
        }

        public Builder setTvInputHardwareInfo(TvInputHardwareInfo tvinputhardwareinfo)
        {
            if(mHdmiDeviceInfo != null)
            {
                Log.w("TvInputInfo", "mHdmiDeviceInfo will not be used to build this TvInputInfo");
                mHdmiDeviceInfo = null;
            }
            mTvInputHardwareInfo = tvinputhardwareinfo;
            return this;
        }

        private static final String DELIMITER_INFO_IN_ID = "/";
        private static final int LENGTH_HDMI_DEVICE_ID = 2;
        private static final int LENGTH_HDMI_PHYSICAL_ADDRESS = 4;
        private static final String PREFIX_HARDWARE_DEVICE = "HW";
        private static final String PREFIX_HDMI_DEVICE = "HDMI";
        private static final String XML_START_TAG_NAME = "tv-input";
        private static final SparseIntArray sHardwareTypeToTvInputType;
        private Boolean mCanRecord;
        private final Context mContext;
        private Bundle mExtras;
        private HdmiDeviceInfo mHdmiDeviceInfo;
        private Icon mIcon;
        private Icon mIconDisconnected;
        private Icon mIconStandby;
        private CharSequence mLabel;
        private int mLabelResId;
        private String mParentId;
        private final ResolveInfo mResolveInfo;
        private String mSetupActivity;
        private Integer mTunerCount;
        private TvInputHardwareInfo mTvInputHardwareInfo;

        static 
        {
            sHardwareTypeToTvInputType = new SparseIntArray();
            sHardwareTypeToTvInputType.put(1, 1000);
            sHardwareTypeToTvInputType.put(2, 0);
            sHardwareTypeToTvInputType.put(3, 1001);
            sHardwareTypeToTvInputType.put(4, 1002);
            sHardwareTypeToTvInputType.put(5, 1003);
            sHardwareTypeToTvInputType.put(6, 1004);
            sHardwareTypeToTvInputType.put(7, 1005);
            sHardwareTypeToTvInputType.put(8, 1006);
            sHardwareTypeToTvInputType.put(9, 1007);
            sHardwareTypeToTvInputType.put(10, 1008);
        }

        public Builder(Context context, ComponentName componentname)
        {
            if(context == null)
                throw new IllegalArgumentException("context cannot be null.");
            componentname = (new Intent("android.media.tv.TvInputService")).setComponent(componentname);
            mResolveInfo = context.getPackageManager().resolveService(componentname, 132);
            if(mResolveInfo == null)
            {
                throw new IllegalArgumentException("Invalid component. Can't find the service.");
            } else
            {
                mContext = context;
                return;
            }
        }

        public Builder(Context context, ResolveInfo resolveinfo)
        {
            if(context == null)
                throw new IllegalArgumentException("context cannot be null");
            if(resolveinfo == null)
            {
                throw new IllegalArgumentException("resolveInfo cannot be null");
            } else
            {
                mContext = context;
                mResolveInfo = resolveinfo;
                return;
            }
        }
    }

    public static final class TvInputSettings
    {

        static boolean _2D_wrap0(Context context, String s, int i)
        {
            return isHidden(context, s, i);
        }

        static String _2D_wrap1(Context context, String s, int i)
        {
            return getCustomLabel(context, s, i);
        }

        private static void ensureValidField(String s)
        {
            if(TextUtils.isEmpty(s))
                throw new IllegalArgumentException((new StringBuilder()).append(s).append(" should not empty ").toString());
            else
                return;
        }

        private static String getCustomLabel(Context context, String s, int i)
        {
            return (String)getCustomLabels(context, i).get(s);
        }

        public static Map getCustomLabels(Context context, int i)
        {
            String s = android.provider.Settings.Secure.getStringForUser(context.getContentResolver(), "tv_input_custom_labels", i);
            context = new HashMap();
            if(TextUtils.isEmpty(s))
                return context;
            String as[] = s.split(":");
            int j = as.length;
            for(i = 0; i < j; i++)
            {
                String as1[] = as[i].split(",");
                context.put(Uri.decode(as1[0]), Uri.decode(as1[1]));
            }

            return context;
        }

        public static Set getHiddenTvInputIds(Context context, int i)
        {
            String s = android.provider.Settings.Secure.getStringForUser(context.getContentResolver(), "tv_input_hidden_inputs", i);
            context = new HashSet();
            if(TextUtils.isEmpty(s))
                return context;
            String as[] = s.split(":");
            i = 0;
            for(int j = as.length; i < j; i++)
                context.add(Uri.decode(as[i]));

            return context;
        }

        private static boolean isHidden(Context context, String s, int i)
        {
            return getHiddenTvInputIds(context, i).contains(s);
        }

        public static void putCustomLabels(Context context, Map map, int i)
        {
            StringBuilder stringbuilder = new StringBuilder();
            boolean flag = true;
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                ensureValidField((String)entry.getKey());
                ensureValidField((String)entry.getValue());
                if(flag)
                    flag = false;
                else
                    stringbuilder.append(":");
                stringbuilder.append(Uri.encode((String)entry.getKey()));
                stringbuilder.append(",");
                stringbuilder.append(Uri.encode((String)entry.getValue()));
            }
            android.provider.Settings.Secure.putStringForUser(context.getContentResolver(), "tv_input_custom_labels", stringbuilder.toString(), i);
            context = (TvInputManager)context.getSystemService("tv_input");
            iterator = map.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                map = context.getTvInputInfo((String)iterator.next());
                if(map != null)
                    context.updateTvInputInfo(map);
            } while(true);
        }

        public static void putHiddenTvInputs(Context context, Set set, int i)
        {
            Object obj = new StringBuilder();
            boolean flag = true;
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) 
            {
                String s = (String)iterator.next();
                ensureValidField(s);
                if(flag)
                    flag = false;
                else
                    ((StringBuilder) (obj)).append(":");
                ((StringBuilder) (obj)).append(Uri.encode(s));
            }
            android.provider.Settings.Secure.putStringForUser(context.getContentResolver(), "tv_input_hidden_inputs", ((StringBuilder) (obj)).toString(), i);
            context = (TvInputManager)context.getSystemService("tv_input");
            obj = set.iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break;
                set = context.getTvInputInfo((String)((Iterator) (obj)).next());
                if(set != null)
                    context.updateTvInputInfo(set);
            } while(true);
        }

        private static final String CUSTOM_NAME_SEPARATOR = ",";
        private static final String TV_INPUT_SEPARATOR = ":";

        private TvInputSettings()
        {
        }
    }


    private TvInputInfo(ResolveInfo resolveinfo, String s, int i, boolean flag, CharSequence charsequence, int j, Icon icon, 
            Icon icon1, Icon icon2, String s1, boolean flag1, int k, HdmiDeviceInfo hdmideviceinfo, boolean flag2, 
            String s2, Bundle bundle)
    {
        mService = resolveinfo;
        mId = s;
        mType = i;
        mIsHardwareInput = flag;
        mLabel = charsequence;
        mLabelResId = j;
        mIcon = icon;
        mIconStandby = icon1;
        mIconDisconnected = icon2;
        mSetupActivity = s1;
        mCanRecord = flag1;
        mTunerCount = k;
        mHdmiDeviceInfo = hdmideviceinfo;
        mIsConnectedToHdmiSwitch = flag2;
        mParentId = s2;
        mExtras = bundle;
    }

    TvInputInfo(ResolveInfo resolveinfo, String s, int i, boolean flag, CharSequence charsequence, int j, Icon icon, 
            Icon icon1, Icon icon2, String s1, boolean flag1, int k, HdmiDeviceInfo hdmideviceinfo, boolean flag2, 
            String s2, Bundle bundle, TvInputInfo tvinputinfo)
    {
        this(resolveinfo, s, i, flag, charsequence, j, icon, icon1, icon2, s1, flag1, k, hdmideviceinfo, flag2, s2, bundle);
    }

    private TvInputInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        mService = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel);
        mId = parcel.readString();
        mType = parcel.readInt();
        boolean flag1;
        if(parcel.readByte() == 1)
            flag1 = true;
        else
            flag1 = false;
        mIsHardwareInput = flag1;
        mLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mIconUri = (Uri)parcel.readParcelable(null);
        mLabelResId = parcel.readInt();
        mIcon = (Icon)parcel.readParcelable(null);
        mIconStandby = (Icon)parcel.readParcelable(null);
        mIconDisconnected = (Icon)parcel.readParcelable(null);
        mSetupActivity = parcel.readString();
        if(parcel.readByte() == 1)
            flag1 = true;
        else
            flag1 = false;
        mCanRecord = flag1;
        mTunerCount = parcel.readInt();
        mHdmiDeviceInfo = (HdmiDeviceInfo)parcel.readParcelable(null);
        if(parcel.readByte() == 1)
            flag1 = flag;
        else
            flag1 = false;
        mIsConnectedToHdmiSwitch = flag1;
        mParentId = parcel.readString();
        mExtras = parcel.readBundle();
    }

    TvInputInfo(Parcel parcel, TvInputInfo tvinputinfo)
    {
        this(parcel);
    }

    public static TvInputInfo createTvInputInfo(Context context, ResolveInfo resolveinfo, HdmiDeviceInfo hdmideviceinfo, String s, int i, Icon icon)
        throws XmlPullParserException, IOException
    {
        return (new Builder(context, resolveinfo)).setHdmiDeviceInfo(hdmideviceinfo).setParentId(s).setLabel(i).setIcon(icon).build();
    }

    public static TvInputInfo createTvInputInfo(Context context, ResolveInfo resolveinfo, HdmiDeviceInfo hdmideviceinfo, String s, String s1, Uri uri)
        throws XmlPullParserException, IOException
    {
        context = (new Builder(context, resolveinfo)).setHdmiDeviceInfo(hdmideviceinfo).setParentId(s).setLabel(s1).build();
        context.mIconUri = uri;
        return context;
    }

    public static TvInputInfo createTvInputInfo(Context context, ResolveInfo resolveinfo, TvInputHardwareInfo tvinputhardwareinfo, int i, Icon icon)
        throws XmlPullParserException, IOException
    {
        return (new Builder(context, resolveinfo)).setTvInputHardwareInfo(tvinputhardwareinfo).setLabel(i).setIcon(icon).build();
    }

    public static TvInputInfo createTvInputInfo(Context context, ResolveInfo resolveinfo, TvInputHardwareInfo tvinputhardwareinfo, String s, Uri uri)
        throws XmlPullParserException, IOException
    {
        context = (new Builder(context, resolveinfo)).setTvInputHardwareInfo(tvinputhardwareinfo).setLabel(s).build();
        context.mIconUri = uri;
        return context;
    }

    private Drawable loadServiceIcon(Context context)
    {
        if(mService.serviceInfo.icon == 0 && mService.serviceInfo.applicationInfo.icon == 0)
            return null;
        else
            return mService.serviceInfo.loadIcon(context.getPackageManager());
    }

    public boolean canRecord()
    {
        return mCanRecord;
    }

    public Intent createSettingsIntent()
    {
        return null;
    }

    public Intent createSetupIntent()
    {
        if(!TextUtils.isEmpty(mSetupActivity))
        {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setClassName(mService.serviceInfo.packageName, mSetupActivity);
            intent.putExtra("android.media.tv.extra.INPUT_ID", getId());
            return intent;
        } else
        {
            return null;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == this)
            return true;
        if(!(obj instanceof TvInputInfo))
            return false;
        obj = (TvInputInfo)obj;
        boolean flag1 = flag;
        if(Objects.equals(mService, ((TvInputInfo) (obj)).mService))
        {
            flag1 = flag;
            if(TextUtils.equals(mId, ((TvInputInfo) (obj)).mId))
            {
                flag1 = flag;
                if(mType == ((TvInputInfo) (obj)).mType)
                {
                    flag1 = flag;
                    if(mIsHardwareInput == ((TvInputInfo) (obj)).mIsHardwareInput)
                    {
                        flag1 = flag;
                        if(TextUtils.equals(mLabel, ((TvInputInfo) (obj)).mLabel))
                        {
                            flag1 = flag;
                            if(Objects.equals(mIconUri, ((TvInputInfo) (obj)).mIconUri))
                            {
                                flag1 = flag;
                                if(mLabelResId == ((TvInputInfo) (obj)).mLabelResId)
                                {
                                    flag1 = flag;
                                    if(Objects.equals(mIcon, ((TvInputInfo) (obj)).mIcon))
                                    {
                                        flag1 = flag;
                                        if(Objects.equals(mIconStandby, ((TvInputInfo) (obj)).mIconStandby))
                                        {
                                            flag1 = flag;
                                            if(Objects.equals(mIconDisconnected, ((TvInputInfo) (obj)).mIconDisconnected))
                                            {
                                                flag1 = flag;
                                                if(TextUtils.equals(mSetupActivity, ((TvInputInfo) (obj)).mSetupActivity))
                                                {
                                                    flag1 = flag;
                                                    if(mCanRecord == ((TvInputInfo) (obj)).mCanRecord)
                                                    {
                                                        flag1 = flag;
                                                        if(mTunerCount == ((TvInputInfo) (obj)).mTunerCount)
                                                        {
                                                            flag1 = flag;
                                                            if(Objects.equals(mHdmiDeviceInfo, ((TvInputInfo) (obj)).mHdmiDeviceInfo))
                                                            {
                                                                flag1 = flag;
                                                                if(mIsConnectedToHdmiSwitch == ((TvInputInfo) (obj)).mIsConnectedToHdmiSwitch)
                                                                {
                                                                    flag1 = flag;
                                                                    if(TextUtils.equals(mParentId, ((TvInputInfo) (obj)).mParentId))
                                                                        flag1 = Objects.equals(mExtras, ((TvInputInfo) (obj)).mExtras);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public ComponentName getComponent()
    {
        return new ComponentName(mService.serviceInfo.packageName, mService.serviceInfo.name);
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public HdmiDeviceInfo getHdmiDeviceInfo()
    {
        if(mType == 1007)
            return mHdmiDeviceInfo;
        else
            return null;
    }

    public String getId()
    {
        return mId;
    }

    public String getParentId()
    {
        return mParentId;
    }

    public ServiceInfo getServiceInfo()
    {
        return mService.serviceInfo;
    }

    public int getTunerCount()
    {
        return mTunerCount;
    }

    public int getType()
    {
        return mType;
    }

    public int hashCode()
    {
        return mId.hashCode();
    }

    public boolean isConnectedToHdmiSwitch()
    {
        return mIsConnectedToHdmiSwitch;
    }

    public boolean isHardwareInput()
    {
        return mIsHardwareInput;
    }

    public boolean isHidden(Context context)
    {
        return TvInputSettings._2D_wrap0(context, mId, UserHandle.myUserId());
    }

    public boolean isPassthroughInput()
    {
        boolean flag = false;
        if(mType != 0)
            flag = true;
        return flag;
    }

    public CharSequence loadCustomLabel(Context context)
    {
        return TvInputSettings._2D_wrap1(context, mId, UserHandle.myUserId());
    }

    public Drawable loadIcon(Context context)
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        obj1 = null;
        obj2 = null;
        if(mIcon != null)
            return mIcon.loadDrawable(context);
        if(mIconUri == null) goto _L2; else goto _L1
_L1:
        Object obj3;
        Object obj4;
        obj3 = null;
        obj4 = null;
        InputStream inputstream = context.getContentResolver().openInputStream(mIconUri);
        obj4 = inputstream;
        obj3 = inputstream;
        Drawable drawable = Drawable.createFromStream(inputstream, null);
        if(drawable == null) goto _L4; else goto _L3
_L3:
        obj4 = obj2;
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_88;
        inputstream.close();
        obj4 = obj2;
_L15:
        if(obj4 == null) goto _L6; else goto _L5
_L5:
        try
        {
            throw obj4;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4)
        {
            Log.w("TvInputInfo", (new StringBuilder()).append("Loading the default icon due to a failure on loading ").append(mIconUri).toString(), ((Throwable) (obj4)));
        }
_L2:
        return loadServiceIcon(context);
        obj4;
        continue; /* Loop/switch isn't completed */
_L6:
        return drawable;
_L4:
        obj4 = obj;
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_159;
        inputstream.close();
        obj4 = obj;
_L8:
        if(obj4 == null)
            break; /* Loop/switch isn't completed */
        throw obj4;
        obj4;
        if(true) goto _L8; else goto _L7
_L7:
        if(true) goto _L2; else goto _L9
_L9:
        obj3;
        throw obj3;
        Exception exception;
        exception;
_L13:
        obj1 = obj3;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_195;
        ((InputStream) (obj4)).close();
        obj1 = obj3;
_L11:
        if(obj1 == null)
            break; /* Loop/switch isn't completed */
        throw obj1;
        obj4;
        if(obj3 == null)
        {
            obj1 = obj4;
            continue; /* Loop/switch isn't completed */
        }
        obj1 = obj3;
        if(obj3 == obj4)
            continue; /* Loop/switch isn't completed */
        ((Throwable) (obj3)).addSuppressed(((Throwable) (obj4)));
        obj1 = obj3;
        if(true) goto _L11; else goto _L10
_L10:
        throw exception;
        exception;
        obj4 = obj3;
        obj3 = obj1;
        if(true) goto _L13; else goto _L12
_L12:
        if(true) goto _L15; else goto _L14
_L14:
    }

    public Drawable loadIcon(Context context, int i)
    {
        if(i == 0)
            return loadIcon(context);
        if(i == 1)
        {
            if(mIconStandby != null)
                return mIconStandby.loadDrawable(context);
        } else
        if(i == 2)
        {
            if(mIconDisconnected != null)
                return mIconDisconnected.loadDrawable(context);
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown state: ").append(i).toString());
        }
        return null;
    }

    public CharSequence loadLabel(Context context)
    {
        if(mLabelResId != 0)
            return context.getPackageManager().getText(mService.serviceInfo.packageName, mLabelResId, null);
        if(!TextUtils.isEmpty(mLabel))
            return mLabel;
        else
            return mService.loadLabel(context.getPackageManager());
    }

    public String toString()
    {
        return (new StringBuilder()).append("TvInputInfo{id=").append(mId).append(", pkg=").append(mService.serviceInfo.packageName).append(", service=").append(mService.serviceInfo.name).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        mService.writeToParcel(parcel, i);
        parcel.writeString(mId);
        parcel.writeInt(mType);
        int j;
        if(mIsHardwareInput)
        {
            boolean flag1 = true;
            j = ((flag1) ? 1 : 0);
        } else
        {
            boolean flag3 = false;
            j = ((flag3) ? 1 : 0);
        }
        parcel.writeByte(j);
        TextUtils.writeToParcel(mLabel, parcel, i);
        parcel.writeParcelable(mIconUri, i);
        parcel.writeInt(mLabelResId);
        parcel.writeParcelable(mIcon, i);
        parcel.writeParcelable(mIconStandby, i);
        parcel.writeParcelable(mIconDisconnected, i);
        parcel.writeString(mSetupActivity);
        if(mCanRecord)
        {
            boolean flag2 = true;
            j = ((flag2) ? 1 : 0);
        } else
        {
            boolean flag4 = false;
            j = ((flag4) ? 1 : 0);
        }
        parcel.writeByte(j);
        parcel.writeInt(mTunerCount);
        parcel.writeParcelable(mHdmiDeviceInfo, i);
        if(mIsConnectedToHdmiSwitch)
        {
            j = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
            j = i;
        }
        parcel.writeByte(j);
        parcel.writeString(mParentId);
        parcel.writeBundle(mExtras);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public TvInputInfo createFromParcel(Parcel parcel)
        {
            return new TvInputInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public TvInputInfo[] newArray(int i)
        {
            return new TvInputInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DEBUG = false;
    public static final String EXTRA_INPUT_ID = "android.media.tv.extra.INPUT_ID";
    private static final String TAG = "TvInputInfo";
    public static final int TYPE_COMPONENT = 1004;
    public static final int TYPE_COMPOSITE = 1001;
    public static final int TYPE_DISPLAY_PORT = 1008;
    public static final int TYPE_DVI = 1006;
    public static final int TYPE_HDMI = 1007;
    public static final int TYPE_OTHER = 1000;
    public static final int TYPE_SCART = 1003;
    public static final int TYPE_SVIDEO = 1002;
    public static final int TYPE_TUNER = 0;
    public static final int TYPE_VGA = 1005;
    private final boolean mCanRecord;
    private final Bundle mExtras;
    private final HdmiDeviceInfo mHdmiDeviceInfo;
    private final Icon mIcon;
    private final Icon mIconDisconnected;
    private final Icon mIconStandby;
    private Uri mIconUri;
    private final String mId;
    private final boolean mIsConnectedToHdmiSwitch;
    private final boolean mIsHardwareInput;
    private final CharSequence mLabel;
    private final int mLabelResId;
    private final String mParentId;
    private final ResolveInfo mService;
    private final String mSetupActivity;
    private final int mTunerCount;
    private final int mType;

}
