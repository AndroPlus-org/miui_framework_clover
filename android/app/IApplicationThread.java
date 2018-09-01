// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.*;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.*;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.content.ReferrerIntent;
import java.util.List;
import java.util.Map;

// Referenced classes of package android.app:
//            ProfilerInfo, IInstrumentationWatcher, IUiAutomationConnection, ResultInfo

public interface IApplicationThread
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IApplicationThread
    {

        public static IApplicationThread asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IApplicationThread");
            if(iinterface != null && (iinterface instanceof IApplicationThread))
                return (IApplicationThread)iinterface;
            else
                return new Proxy(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.app.IApplicationThread");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                boolean flag;
                boolean flag19;
                boolean flag26;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    flag19 = true;
                else
                    flag19 = false;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag26 = true;
                else
                    flag26 = false;
                schedulePauseActivity(parcel1, flag, flag19, i, flag26);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                scheduleStopActivity(parcel1, flag1, parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                scheduleWindowVisibility(parcel1, flag2);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                i = parcel.readInt();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                scheduleResumeActivity(parcel1, i, flag3, parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.IApplicationThread");
                scheduleSendResult(parcel.readStrongBinder(), parcel.createTypedArrayList(ResultInfo.CREATOR));
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.IApplicationThread");
                boolean flag4;
                boolean flag20;
                IBinder ibinder;
                ActivityInfo activityinfo;
                Configuration configuration1;
                Configuration configuration2;
                CompatibilityInfo compatibilityinfo3;
                String s5;
                IVoiceInteractor ivoiceinteractor;
                Bundle bundle3;
                PersistableBundle persistablebundle;
                java.util.ArrayList arraylist4;
                java.util.ArrayList arraylist5;
                if(parcel.readInt() != 0)
                    parcel1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                ibinder = parcel.readStrongBinder();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    activityinfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel);
                else
                    activityinfo = null;
                if(parcel.readInt() != 0)
                    configuration1 = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    configuration1 = null;
                if(parcel.readInt() != 0)
                    configuration2 = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    configuration2 = null;
                if(parcel.readInt() != 0)
                    compatibilityinfo3 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(parcel);
                else
                    compatibilityinfo3 = null;
                s5 = parcel.readString();
                ivoiceinteractor = com.android.internal.app.IVoiceInteractor.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle3 = null;
                if(parcel.readInt() != 0)
                    persistablebundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(parcel);
                else
                    persistablebundle = null;
                arraylist4 = parcel.createTypedArrayList(ResultInfo.CREATOR);
                arraylist5 = parcel.createTypedArrayList(ReferrerIntent.CREATOR);
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                if(parcel.readInt() != 0)
                    flag20 = true;
                else
                    flag20 = false;
                if(parcel.readInt() != 0)
                    parcel = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                scheduleLaunchActivity(parcel1, ibinder, j, activityinfo, configuration1, configuration2, compatibilityinfo3, s5, ivoiceinteractor, i, bundle3, persistablebundle, arraylist4, arraylist5, flag4, flag20, parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.app.IApplicationThread");
                java.util.ArrayList arraylist = parcel.createTypedArrayList(ReferrerIntent.CREATOR);
                parcel1 = parcel.readStrongBinder();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                scheduleNewIntent(arraylist, parcel1, flag5);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                scheduleDestroyActivity(parcel1, flag6, parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.app.IApplicationThread");
                boolean flag7;
                ActivityInfo activityinfo1;
                CompatibilityInfo compatibilityinfo2;
                Bundle bundle1;
                String s4;
                if(parcel.readInt() != 0)
                    parcel1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    activityinfo1 = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel);
                else
                    activityinfo1 = null;
                if(parcel.readInt() != 0)
                    compatibilityinfo2 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(parcel);
                else
                    compatibilityinfo2 = null;
                i = parcel.readInt();
                s4 = parcel.readString();
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                scheduleReceiver(parcel1, activityinfo1, compatibilityinfo2, i, s4, bundle1, flag7, parcel.readInt(), parcel.readInt());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.app.IApplicationThread");
                IBinder ibinder2 = parcel.readStrongBinder();
                CompatibilityInfo compatibilityinfo;
                if(parcel.readInt() != 0)
                    parcel1 = (ServiceInfo)ServiceInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    compatibilityinfo = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(parcel);
                else
                    compatibilityinfo = null;
                scheduleCreateService(ibinder2, parcel1, compatibilityinfo, parcel.readInt());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.app.IApplicationThread");
                scheduleStopService(parcel.readStrongBinder());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.app.IApplicationThread");
                String s = parcel.readString();
                boolean flag8;
                boolean flag21;
                boolean flag27;
                ComponentName componentname;
                ProfilerInfo profilerinfo;
                Bundle bundle2;
                Configuration configuration3;
                IInstrumentationWatcher iinstrumentationwatcher;
                java.util.ArrayList arraylist3;
                CompatibilityInfo compatibilityinfo4;
                Bundle bundle4;
                IUiAutomationConnection iuiautomationconnection;
                java.util.HashMap hashmap;
                boolean flag30;
                if(parcel.readInt() != 0)
                    parcel1 = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                arraylist3 = parcel.createTypedArrayList(ProviderInfo.CREATOR);
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                if(parcel.readInt() != 0)
                    profilerinfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(parcel);
                else
                    profilerinfo = null;
                if(parcel.readInt() != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle2 = null;
                iinstrumentationwatcher = IInstrumentationWatcher.Stub.asInterface(parcel.readStrongBinder());
                iuiautomationconnection = IUiAutomationConnection.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                if(parcel.readInt() != 0)
                    flag21 = true;
                else
                    flag21 = false;
                if(parcel.readInt() != 0)
                    flag27 = true;
                else
                    flag27 = false;
                if(parcel.readInt() != 0)
                    flag30 = true;
                else
                    flag30 = false;
                if(parcel.readInt() != 0)
                    configuration3 = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    configuration3 = null;
                if(parcel.readInt() != 0)
                    compatibilityinfo4 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(parcel);
                else
                    compatibilityinfo4 = null;
                hashmap = parcel.readHashMap(getClass().getClassLoader());
                if(parcel.readInt() != 0)
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle4 = null;
                bindApplication(s, parcel1, arraylist3, componentname, profilerinfo, bundle2, iinstrumentationwatcher, iuiautomationconnection, i, flag8, flag21, flag27, flag30, configuration3, compatibilityinfo4, hashmap, bundle4, parcel.readString());
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.app.IApplicationThread");
                scheduleExit();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.app.IApplicationThread");
                if(parcel.readInt() != 0)
                    parcel = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                scheduleConfigurationChanged(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                scheduleServiceArgs(parcel1, parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.app.IApplicationThread");
                updateTimeZone();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.app.IApplicationThread");
                processInBackground();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.app.IApplicationThread");
                IBinder ibinder1 = parcel.readStrongBinder();
                boolean flag9;
                if(parcel.readInt() != 0)
                    parcel1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                scheduleBindService(ibinder1, parcel1, flag9, parcel.readInt());
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                scheduleUnbindService(parcel1, parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.app.IApplicationThread");
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                dumpService(parcel1, parcel.readStrongBinder(), parcel.createStringArray());
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.app.IApplicationThread");
                IIntentReceiver iintentreceiver = android.content.IIntentReceiver.Stub.asInterface(parcel.readStrongBinder());
                boolean flag10;
                boolean flag22;
                Bundle bundle;
                String s3;
                if(parcel.readInt() != 0)
                    parcel1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                i = parcel.readInt();
                s3 = parcel.readString();
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                if(parcel.readInt() != 0)
                    flag22 = true;
                else
                    flag22 = false;
                scheduleRegisteredReceiver(iintentreceiver, parcel1, i, s3, bundle, flag10, flag22, parcel.readInt(), parcel.readInt());
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.app.IApplicationThread");
                scheduleLowMemory();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                scheduleActivityConfigurationChanged(parcel1, parcel);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                scheduleActivityMovedToDisplay(parcel1, i, parcel);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.app.IApplicationThread");
                IBinder ibinder3 = parcel.readStrongBinder();
                java.util.ArrayList arraylist2 = parcel.createTypedArrayList(ResultInfo.CREATOR);
                java.util.ArrayList arraylist1 = parcel.createTypedArrayList(ReferrerIntent.CREATOR);
                i = parcel.readInt();
                boolean flag11;
                boolean flag23;
                Configuration configuration;
                if(parcel.readInt() != 0)
                    flag11 = true;
                else
                    flag11 = false;
                if(parcel.readInt() != 0)
                    parcel1 = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    configuration = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    configuration = null;
                if(parcel.readInt() != 0)
                    flag23 = true;
                else
                    flag23 = false;
                scheduleRelaunchActivity(ibinder3, arraylist2, arraylist1, i, flag11, parcel1, configuration, flag23);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                boolean flag12;
                if(parcel.readInt() != 0)
                    flag12 = true;
                else
                    flag12 = false;
                scheduleSleeping(parcel1, flag12);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.app.IApplicationThread");
                boolean flag13;
                if(parcel.readInt() != 0)
                    flag13 = true;
                else
                    flag13 = false;
                if(parcel.readInt() != 0)
                    parcel1 = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                profilerControl(flag13, parcel1, parcel.readInt());
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.app.IApplicationThread");
                setSchedulingGroup(parcel.readInt());
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.app.IApplicationThread");
                CompatibilityInfo compatibilityinfo1;
                if(parcel.readInt() != 0)
                    parcel1 = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    compatibilityinfo1 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(parcel);
                else
                    compatibilityinfo1 = null;
                scheduleCreateBackupAgent(parcel1, compatibilityinfo1, parcel.readInt());
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.app.IApplicationThread");
                if(parcel.readInt() != 0)
                    parcel1 = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                scheduleDestroyBackupAgent(parcel1, parcel);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                scheduleOnNewActivityOptions(parcel1, parcel);
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.app.IApplicationThread");
                scheduleSuicide();
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.app.IApplicationThread");
                dispatchPackageBroadcast(parcel.readInt(), parcel.createStringArray());
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.app.IApplicationThread");
                scheduleCrash(parcel.readString());
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.app.IApplicationThread");
                boolean flag14;
                boolean flag24;
                boolean flag28;
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                if(parcel.readInt() != 0)
                    flag24 = true;
                else
                    flag24 = false;
                if(parcel.readInt() != 0)
                    flag28 = true;
                else
                    flag28 = false;
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                dumpHeap(flag14, flag24, flag28, parcel1, parcel);
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.app.IApplicationThread");
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                dumpActivity(parcel1, parcel.readStrongBinder(), parcel.readString(), parcel.createStringArray());
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.app.IApplicationThread");
                clearDnsCache();
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.app.IApplicationThread");
                String s1 = parcel.readString();
                String s2 = parcel.readString();
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setHttpProxy(s1, s2, parcel1, parcel);
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.app.IApplicationThread");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setCoreSettings(parcel);
                return true;

            case 40: // '('
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updatePackageCompatibilityInfo(parcel1, parcel);
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.app.IApplicationThread");
                scheduleTrimMemory(parcel.readInt());
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.app.IApplicationThread");
                boolean flag15;
                boolean flag25;
                boolean flag29;
                android.os.Debug.MemoryInfo memoryinfo;
                boolean flag31;
                boolean flag32;
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    memoryinfo = (android.os.Debug.MemoryInfo)android.os.Debug.MemoryInfo.CREATOR.createFromParcel(parcel);
                else
                    memoryinfo = null;
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                if(parcel.readInt() != 0)
                    flag25 = true;
                else
                    flag25 = false;
                if(parcel.readInt() != 0)
                    flag29 = true;
                else
                    flag29 = false;
                if(parcel.readInt() != 0)
                    flag31 = true;
                else
                    flag31 = false;
                if(parcel.readInt() != 0)
                    flag32 = true;
                else
                    flag32 = false;
                dumpMemInfo(parcel1, memoryinfo, flag15, flag25, flag29, flag31, flag32, parcel.createStringArray());
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.app.IApplicationThread");
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                dumpGfxInfo(parcel1, parcel.createStringArray());
                return true;

            case 44: // ','
                parcel.enforceInterface("android.app.IApplicationThread");
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                dumpProvider(parcel1, parcel.readStrongBinder(), parcel.createStringArray());
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.app.IApplicationThread");
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                dumpDbInfo(parcel1, parcel.createStringArray());
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.app.IApplicationThread");
                unstableProviderDied(parcel.readStrongBinder());
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.app.IApplicationThread");
                requestAssistContextExtras(parcel.readStrongBinder(), parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                boolean flag16;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                scheduleTranslucentConversionComplete(parcel1, flag16);
                return true;

            case 49: // '1'
                parcel.enforceInterface("android.app.IApplicationThread");
                setProcessState(parcel.readInt());
                return true;

            case 50: // '2'
                parcel.enforceInterface("android.app.IApplicationThread");
                if(parcel.readInt() != 0)
                    parcel = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                scheduleInstallProvider(parcel);
                return true;

            case 51: // '3'
                parcel.enforceInterface("android.app.IApplicationThread");
                updateTimePrefs(parcel.readInt());
                return true;

            case 52: // '4'
                parcel.enforceInterface("android.app.IApplicationThread");
                scheduleEnterAnimationComplete(parcel.readStrongBinder());
                return true;

            case 53: // '5'
                parcel.enforceInterface("android.app.IApplicationThread");
                notifyCleartextNetwork(parcel.createByteArray());
                return true;

            case 54: // '6'
                parcel.enforceInterface("android.app.IApplicationThread");
                startBinderTracking();
                return true;

            case 55: // '7'
                parcel.enforceInterface("android.app.IApplicationThread");
                if(parcel.readInt() != 0)
                    parcel = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                stopBinderTrackingAndDump(parcel);
                return true;

            case 56: // '8'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                boolean flag17;
                if(parcel.readInt() != 0)
                    flag17 = true;
                else
                    flag17 = false;
                if(parcel.readInt() != 0)
                    parcel = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                scheduleMultiWindowModeChanged(parcel1, flag17, parcel);
                return true;

            case 57: // '9'
                parcel.enforceInterface("android.app.IApplicationThread");
                parcel1 = parcel.readStrongBinder();
                boolean flag18;
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                if(parcel.readInt() != 0)
                    parcel = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                schedulePictureInPictureModeChanged(parcel1, flag18, parcel);
                return true;

            case 58: // ':'
                parcel.enforceInterface("android.app.IApplicationThread");
                scheduleLocalVoiceInteractionStarted(parcel.readStrongBinder(), com.android.internal.app.IVoiceInteractor.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 59: // ';'
                parcel.enforceInterface("android.app.IApplicationThread");
                handleTrustStorageUpdate();
                return true;

            case 60: // '<'
                parcel.enforceInterface("android.app.IApplicationThread");
                attachAgent(parcel.readString());
                return true;

            case 61: // '='
                parcel.enforceInterface("android.app.IApplicationThread");
                if(parcel.readInt() != 0)
                    parcel = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                scheduleApplicationInfoChanged(parcel);
                return true;

            case 62: // '>'
                parcel.enforceInterface("android.app.IApplicationThread");
                setNetworkBlockSeq(parcel.readLong());
                return true;

            case 63: // '?'
                parcel.enforceInterface("android.app.IApplicationThread");
                notifyPackageForeground();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.IApplicationThread";
        static final int TRANSACTION_attachAgent = 60;
        static final int TRANSACTION_bindApplication = 12;
        static final int TRANSACTION_clearDnsCache = 37;
        static final int TRANSACTION_dispatchPackageBroadcast = 33;
        static final int TRANSACTION_dumpActivity = 36;
        static final int TRANSACTION_dumpDbInfo = 45;
        static final int TRANSACTION_dumpGfxInfo = 43;
        static final int TRANSACTION_dumpHeap = 35;
        static final int TRANSACTION_dumpMemInfo = 42;
        static final int TRANSACTION_dumpProvider = 44;
        static final int TRANSACTION_dumpService = 20;
        static final int TRANSACTION_handleTrustStorageUpdate = 59;
        static final int TRANSACTION_notifyCleartextNetwork = 53;
        static final int TRANSACTION_notifyPackageForeground = 63;
        static final int TRANSACTION_processInBackground = 17;
        static final int TRANSACTION_profilerControl = 27;
        static final int TRANSACTION_requestAssistContextExtras = 47;
        static final int TRANSACTION_scheduleActivityConfigurationChanged = 23;
        static final int TRANSACTION_scheduleActivityMovedToDisplay = 24;
        static final int TRANSACTION_scheduleApplicationInfoChanged = 61;
        static final int TRANSACTION_scheduleBindService = 18;
        static final int TRANSACTION_scheduleConfigurationChanged = 14;
        static final int TRANSACTION_scheduleCrash = 34;
        static final int TRANSACTION_scheduleCreateBackupAgent = 29;
        static final int TRANSACTION_scheduleCreateService = 10;
        static final int TRANSACTION_scheduleDestroyActivity = 8;
        static final int TRANSACTION_scheduleDestroyBackupAgent = 30;
        static final int TRANSACTION_scheduleEnterAnimationComplete = 52;
        static final int TRANSACTION_scheduleExit = 13;
        static final int TRANSACTION_scheduleInstallProvider = 50;
        static final int TRANSACTION_scheduleLaunchActivity = 6;
        static final int TRANSACTION_scheduleLocalVoiceInteractionStarted = 58;
        static final int TRANSACTION_scheduleLowMemory = 22;
        static final int TRANSACTION_scheduleMultiWindowModeChanged = 56;
        static final int TRANSACTION_scheduleNewIntent = 7;
        static final int TRANSACTION_scheduleOnNewActivityOptions = 31;
        static final int TRANSACTION_schedulePauseActivity = 1;
        static final int TRANSACTION_schedulePictureInPictureModeChanged = 57;
        static final int TRANSACTION_scheduleReceiver = 9;
        static final int TRANSACTION_scheduleRegisteredReceiver = 21;
        static final int TRANSACTION_scheduleRelaunchActivity = 25;
        static final int TRANSACTION_scheduleResumeActivity = 4;
        static final int TRANSACTION_scheduleSendResult = 5;
        static final int TRANSACTION_scheduleServiceArgs = 15;
        static final int TRANSACTION_scheduleSleeping = 26;
        static final int TRANSACTION_scheduleStopActivity = 2;
        static final int TRANSACTION_scheduleStopService = 11;
        static final int TRANSACTION_scheduleSuicide = 32;
        static final int TRANSACTION_scheduleTranslucentConversionComplete = 48;
        static final int TRANSACTION_scheduleTrimMemory = 41;
        static final int TRANSACTION_scheduleUnbindService = 19;
        static final int TRANSACTION_scheduleWindowVisibility = 3;
        static final int TRANSACTION_setCoreSettings = 39;
        static final int TRANSACTION_setHttpProxy = 38;
        static final int TRANSACTION_setNetworkBlockSeq = 62;
        static final int TRANSACTION_setProcessState = 49;
        static final int TRANSACTION_setSchedulingGroup = 28;
        static final int TRANSACTION_startBinderTracking = 54;
        static final int TRANSACTION_stopBinderTrackingAndDump = 55;
        static final int TRANSACTION_unstableProviderDied = 46;
        static final int TRANSACTION_updatePackageCompatibilityInfo = 40;
        static final int TRANSACTION_updateTimePrefs = 51;
        static final int TRANSACTION_updateTimeZone = 16;

        public Stub()
        {
            attachInterface(this, "android.app.IApplicationThread");
        }
    }

    private static class Stub.Proxy
        implements IApplicationThread
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void attachAgent(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeString(s);
            mRemote.transact(60, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void bindApplication(String s, ApplicationInfo applicationinfo, List list, ComponentName componentname, ProfilerInfo profilerinfo, Bundle bundle, IInstrumentationWatcher iinstrumentationwatcher, 
                IUiAutomationConnection iuiautomationconnection, int i, boolean flag, boolean flag1, boolean flag2, boolean flag3, Configuration configuration, 
                CompatibilityInfo compatibilityinfo, Map map, Bundle bundle1, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeString(s);
            if(applicationinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            applicationinfo.writeToParcel(parcel, 0);
_L17:
            parcel.writeTypedList(list);
            if(componentname == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L18:
            if(profilerinfo == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            profilerinfo.writeToParcel(parcel, 0);
_L19:
            if(bundle == null) goto _L8; else goto _L7
_L7:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L20:
            if(iinstrumentationwatcher == null) goto _L10; else goto _L9
_L9:
            s = iinstrumentationwatcher.asBinder();
_L21:
            parcel.writeStrongBinder(s);
            if(iuiautomationconnection == null) goto _L12; else goto _L11
_L11:
            s = iuiautomationconnection.asBinder();
_L22:
            parcel.writeStrongBinder(s);
            parcel.writeInt(i);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag2)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag3)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(configuration == null) goto _L14; else goto _L13
_L13:
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L23:
            if(compatibilityinfo == null) goto _L16; else goto _L15
_L15:
            parcel.writeInt(1);
            compatibilityinfo.writeToParcel(parcel, 0);
_L24:
            parcel.writeMap(map);
            if(bundle1 == null)
                break MISSING_BLOCK_LABEL_392;
            parcel.writeInt(1);
            bundle1.writeToParcel(parcel, 0);
_L25:
            parcel.writeString(s1);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L17
            s;
            parcel.recycle();
            throw s;
_L4:
            parcel.writeInt(0);
              goto _L18
_L6:
            parcel.writeInt(0);
              goto _L19
_L8:
            parcel.writeInt(0);
              goto _L20
_L10:
            s = null;
              goto _L21
_L12:
            s = null;
              goto _L22
_L14:
            parcel.writeInt(0);
              goto _L23
_L16:
            parcel.writeInt(0);
              goto _L24
            parcel.writeInt(0);
              goto _L25
        }

        public void clearDnsCache()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            mRemote.transact(37, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void dispatchPackageBroadcast(int i, String as[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeInt(i);
            parcel.writeStringArray(as);
            mRemote.transact(33, parcel, null, 1);
            parcel.recycle();
            return;
            as;
            parcel.recycle();
            throw as;
        }

        public void dumpActivity(ParcelFileDescriptor parcelfiledescriptor, IBinder ibinder, String s, String as[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeStringArray(as);
            mRemote.transact(36, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public void dumpDbInfo(ParcelFileDescriptor parcelfiledescriptor, String as[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            mRemote.transact(45, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public void dumpGfxInfo(ParcelFileDescriptor parcelfiledescriptor, String as[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            mRemote.transact(43, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public void dumpHeap(boolean flag, boolean flag1, boolean flag2, String s, ParcelFileDescriptor parcelfiledescriptor)
            throws RemoteException
        {
            boolean flag3;
            Parcel parcel;
            flag3 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag2)
                i = ((flag3) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s);
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_124;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(35, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void dumpMemInfo(ParcelFileDescriptor parcelfiledescriptor, android.os.Debug.MemoryInfo memoryinfo, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, 
                String as[])
            throws RemoteException
        {
            boolean flag5;
            Parcel parcel;
            flag5 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L3:
            if(memoryinfo == null)
                break MISSING_BLOCK_LABEL_170;
            parcel.writeInt(1);
            memoryinfo.writeToParcel(parcel, 0);
_L4:
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag2)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag3)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag4)
                i = ((flag5) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeStringArray(as);
            mRemote.transact(42, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            parcelfiledescriptor;
            parcel.recycle();
            throw parcelfiledescriptor;
            parcel.writeInt(0);
              goto _L4
        }

        public void dumpProvider(ParcelFileDescriptor parcelfiledescriptor, IBinder ibinder, String as[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringArray(as);
            mRemote.transact(44, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public void dumpService(ParcelFileDescriptor parcelfiledescriptor, IBinder ibinder, String as[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringArray(as);
            mRemote.transact(20, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IApplicationThread";
        }

        public void handleTrustStorageUpdate()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            mRemote.transact(59, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void notifyCleartextNetwork(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeByteArray(abyte0);
            mRemote.transact(53, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public void notifyPackageForeground()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            mRemote.transact(63, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void processInBackground()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void profilerControl(boolean flag, ProfilerInfo profilerinfo, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            if(profilerinfo == null)
                break MISSING_BLOCK_LABEL_77;
            parcel.writeInt(1);
            profilerinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(27, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            profilerinfo;
            parcel.recycle();
            throw profilerinfo;
        }

        public void requestAssistContextExtras(IBinder ibinder, IBinder ibinder1, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            parcel.writeStrongBinder(ibinder1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(47, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleActivityConfigurationChanged(IBinder ibinder, Configuration configuration)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(configuration == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(23, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleActivityMovedToDisplay(IBinder ibinder, int i, Configuration configuration)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(configuration == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(24, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleApplicationInfoChanged(ApplicationInfo applicationinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(applicationinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            applicationinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(61, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            applicationinfo;
            parcel.recycle();
            throw applicationinfo;
        }

        public void scheduleBindService(IBinder ibinder, Intent intent, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(intent == null)
                break MISSING_BLOCK_LABEL_78;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleConfigurationChanged(Configuration configuration)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(configuration == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            configuration;
            parcel.recycle();
            throw configuration;
        }

        public void scheduleCrash(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeString(s);
            mRemote.transact(34, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void scheduleCreateBackupAgent(ApplicationInfo applicationinfo, CompatibilityInfo compatibilityinfo, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(applicationinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            applicationinfo.writeToParcel(parcel, 0);
_L3:
            if(compatibilityinfo == null)
                break MISSING_BLOCK_LABEL_91;
            parcel.writeInt(1);
            compatibilityinfo.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(29, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            applicationinfo;
            parcel.recycle();
            throw applicationinfo;
            parcel.writeInt(0);
              goto _L4
        }

        public void scheduleCreateService(IBinder ibinder, ServiceInfo serviceinfo, CompatibilityInfo compatibilityinfo, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(serviceinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            serviceinfo.writeToParcel(parcel, 0);
_L3:
            if(compatibilityinfo == null)
                break MISSING_BLOCK_LABEL_98;
            parcel.writeInt(1);
            compatibilityinfo.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            ibinder;
            parcel.recycle();
            throw ibinder;
            parcel.writeInt(0);
              goto _L4
        }

        public void scheduleDestroyActivity(IBinder ibinder, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleDestroyBackupAgent(ApplicationInfo applicationinfo, CompatibilityInfo compatibilityinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(applicationinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            applicationinfo.writeToParcel(parcel, 0);
_L3:
            if(compatibilityinfo == null)
                break MISSING_BLOCK_LABEL_75;
            parcel.writeInt(1);
            compatibilityinfo.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(30, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            applicationinfo;
            parcel.recycle();
            throw applicationinfo;
            parcel.writeInt(0);
              goto _L4
        }

        public void scheduleEnterAnimationComplete(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(52, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleExit()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void scheduleInstallProvider(ProviderInfo providerinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(providerinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            providerinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(50, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            providerinfo;
            parcel.recycle();
            throw providerinfo;
        }

        public void scheduleLaunchActivity(Intent intent, IBinder ibinder, int i, ActivityInfo activityinfo, Configuration configuration, Configuration configuration1, CompatibilityInfo compatibilityinfo, 
                String s, IVoiceInteractor ivoiceinteractor, int j, Bundle bundle, PersistableBundle persistablebundle, List list, List list1, 
                boolean flag, boolean flag1, ProfilerInfo profilerinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L17:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(activityinfo == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            activityinfo.writeToParcel(parcel, 0);
_L18:
            if(configuration == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L19:
            if(configuration1 == null) goto _L8; else goto _L7
_L7:
            parcel.writeInt(1);
            configuration1.writeToParcel(parcel, 0);
_L20:
            if(compatibilityinfo == null) goto _L10; else goto _L9
_L9:
            parcel.writeInt(1);
            compatibilityinfo.writeToParcel(parcel, 0);
_L21:
            parcel.writeString(s);
            if(ivoiceinteractor == null) goto _L12; else goto _L11
_L11:
            intent = ivoiceinteractor.asBinder();
_L22:
            parcel.writeStrongBinder(intent);
            parcel.writeInt(j);
            if(bundle == null) goto _L14; else goto _L13
_L13:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L23:
            if(persistablebundle == null) goto _L16; else goto _L15
_L15:
            parcel.writeInt(1);
            persistablebundle.writeToParcel(parcel, 0);
_L24:
            parcel.writeTypedList(list);
            parcel.writeTypedList(list1);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(profilerinfo == null)
                break MISSING_BLOCK_LABEL_355;
            parcel.writeInt(1);
            profilerinfo.writeToParcel(parcel, 0);
_L25:
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L17
            intent;
            parcel.recycle();
            throw intent;
_L4:
            parcel.writeInt(0);
              goto _L18
_L6:
            parcel.writeInt(0);
              goto _L19
_L8:
            parcel.writeInt(0);
              goto _L20
_L10:
            parcel.writeInt(0);
              goto _L21
_L12:
            intent = null;
              goto _L22
_L14:
            parcel.writeInt(0);
              goto _L23
_L16:
            parcel.writeInt(0);
              goto _L24
            parcel.writeInt(0);
              goto _L25
        }

        public void scheduleLocalVoiceInteractionStarted(IBinder ibinder, IVoiceInteractor ivoiceinteractor)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            ibinder = obj;
            if(ivoiceinteractor == null)
                break MISSING_BLOCK_LABEL_33;
            ibinder = ivoiceinteractor.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(58, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleLowMemory()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            mRemote.transact(22, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void scheduleMultiWindowModeChanged(IBinder ibinder, boolean flag, Configuration configuration)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(configuration == null)
                break MISSING_BLOCK_LABEL_77;
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(56, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleNewIntent(List list, IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeTypedList(list);
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void scheduleOnNewActivityOptions(IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(31, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void schedulePauseActivity(IBinder ibinder, boolean flag, boolean flag1, int i, boolean flag2)
            throws RemoteException
        {
            boolean flag3;
            Parcel parcel;
            flag3 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            if(flag1)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            if(flag2)
                i = ((flag3) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void schedulePictureInPictureModeChanged(IBinder ibinder, boolean flag, Configuration configuration)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(configuration == null)
                break MISSING_BLOCK_LABEL_77;
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(57, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleReceiver(Intent intent, ActivityInfo activityinfo, CompatibilityInfo compatibilityinfo, int i, String s, Bundle bundle, boolean flag, 
                int j, int k)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L7:
            if(activityinfo == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            activityinfo.writeToParcel(parcel, 0);
_L8:
            if(compatibilityinfo == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            compatibilityinfo.writeToParcel(parcel, 0);
_L9:
            parcel.writeInt(i);
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_186;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L10:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L7
            intent;
            parcel.recycle();
            throw intent;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public void scheduleRegisteredReceiver(IIntentReceiver iintentreceiver, Intent intent, int i, String s, Bundle bundle, boolean flag, boolean flag1, 
                int j, int k)
            throws RemoteException
        {
            IBinder ibinder;
            boolean flag2;
            Parcel parcel;
            ibinder = null;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(iintentreceiver == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iintentreceiver.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_166;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(21, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            iintentreceiver;
            parcel.recycle();
            throw iintentreceiver;
            parcel.writeInt(0);
              goto _L4
        }

        public void scheduleRelaunchActivity(IBinder ibinder, List list, List list1, int i, boolean flag, Configuration configuration, Configuration configuration1, 
                boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            parcel.writeTypedList(list);
            parcel.writeTypedList(list1);
            parcel.writeInt(i);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(configuration == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L3:
            if(configuration1 == null)
                break MISSING_BLOCK_LABEL_154;
            parcel.writeInt(1);
            configuration1.writeToParcel(parcel, 0);
_L4:
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(25, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            ibinder;
            parcel.recycle();
            throw ibinder;
            parcel.writeInt(0);
              goto _L4
        }

        public void scheduleResumeActivity(IBinder ibinder, int i, boolean flag, Bundle bundle)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_85;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleSendResult(IBinder ibinder, List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            parcel.writeTypedList(list);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleServiceArgs(IBinder ibinder, ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleSleeping(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(26, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleStopActivity(IBinder ibinder, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleStopService(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleSuicide()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            mRemote.transact(32, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void scheduleTranslucentConversionComplete(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(48, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleTrimMemory(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeInt(i);
            mRemote.transact(41, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void scheduleUnbindService(IBinder ibinder, Intent intent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(intent == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(19, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleWindowVisibility(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void setCoreSettings(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(39, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public void setHttpProxy(String s, String s1, String s2, Uri uri)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            if(uri == null)
                break MISSING_BLOCK_LABEL_71;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(38, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void setNetworkBlockSeq(long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeLong(l);
            mRemote.transact(62, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setProcessState(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeInt(i);
            mRemote.transact(49, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setSchedulingGroup(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeInt(i);
            mRemote.transact(28, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void startBinderTracking()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            mRemote.transact(54, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void stopBinderTrackingAndDump(ParcelFileDescriptor parcelfiledescriptor)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(55, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public void unstableProviderDied(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(46, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void updatePackageCompatibilityInfo(String s, CompatibilityInfo compatibilityinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeString(s);
            if(compatibilityinfo == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            compatibilityinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(40, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void updateTimePrefs(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            parcel.writeInt(i);
            mRemote.transact(51, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void updateTimeZone()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IApplicationThread");
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void attachAgent(String s)
        throws RemoteException;

    public abstract void bindApplication(String s, ApplicationInfo applicationinfo, List list, ComponentName componentname, ProfilerInfo profilerinfo, Bundle bundle, IInstrumentationWatcher iinstrumentationwatcher, 
            IUiAutomationConnection iuiautomationconnection, int i, boolean flag, boolean flag1, boolean flag2, boolean flag3, Configuration configuration, 
            CompatibilityInfo compatibilityinfo, Map map, Bundle bundle1, String s1)
        throws RemoteException;

    public abstract void clearDnsCache()
        throws RemoteException;

    public abstract void dispatchPackageBroadcast(int i, String as[])
        throws RemoteException;

    public abstract void dumpActivity(ParcelFileDescriptor parcelfiledescriptor, IBinder ibinder, String s, String as[])
        throws RemoteException;

    public abstract void dumpDbInfo(ParcelFileDescriptor parcelfiledescriptor, String as[])
        throws RemoteException;

    public abstract void dumpGfxInfo(ParcelFileDescriptor parcelfiledescriptor, String as[])
        throws RemoteException;

    public abstract void dumpHeap(boolean flag, boolean flag1, boolean flag2, String s, ParcelFileDescriptor parcelfiledescriptor)
        throws RemoteException;

    public abstract void dumpMemInfo(ParcelFileDescriptor parcelfiledescriptor, android.os.Debug.MemoryInfo memoryinfo, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, 
            String as[])
        throws RemoteException;

    public abstract void dumpProvider(ParcelFileDescriptor parcelfiledescriptor, IBinder ibinder, String as[])
        throws RemoteException;

    public abstract void dumpService(ParcelFileDescriptor parcelfiledescriptor, IBinder ibinder, String as[])
        throws RemoteException;

    public abstract void handleTrustStorageUpdate()
        throws RemoteException;

    public abstract void notifyCleartextNetwork(byte abyte0[])
        throws RemoteException;

    public abstract void notifyPackageForeground()
        throws RemoteException;

    public abstract void processInBackground()
        throws RemoteException;

    public abstract void profilerControl(boolean flag, ProfilerInfo profilerinfo, int i)
        throws RemoteException;

    public abstract void requestAssistContextExtras(IBinder ibinder, IBinder ibinder1, int i, int j, int k)
        throws RemoteException;

    public abstract void scheduleActivityConfigurationChanged(IBinder ibinder, Configuration configuration)
        throws RemoteException;

    public abstract void scheduleActivityMovedToDisplay(IBinder ibinder, int i, Configuration configuration)
        throws RemoteException;

    public abstract void scheduleApplicationInfoChanged(ApplicationInfo applicationinfo)
        throws RemoteException;

    public abstract void scheduleBindService(IBinder ibinder, Intent intent, boolean flag, int i)
        throws RemoteException;

    public abstract void scheduleConfigurationChanged(Configuration configuration)
        throws RemoteException;

    public abstract void scheduleCrash(String s)
        throws RemoteException;

    public abstract void scheduleCreateBackupAgent(ApplicationInfo applicationinfo, CompatibilityInfo compatibilityinfo, int i)
        throws RemoteException;

    public abstract void scheduleCreateService(IBinder ibinder, ServiceInfo serviceinfo, CompatibilityInfo compatibilityinfo, int i)
        throws RemoteException;

    public abstract void scheduleDestroyActivity(IBinder ibinder, boolean flag, int i)
        throws RemoteException;

    public abstract void scheduleDestroyBackupAgent(ApplicationInfo applicationinfo, CompatibilityInfo compatibilityinfo)
        throws RemoteException;

    public abstract void scheduleEnterAnimationComplete(IBinder ibinder)
        throws RemoteException;

    public abstract void scheduleExit()
        throws RemoteException;

    public abstract void scheduleInstallProvider(ProviderInfo providerinfo)
        throws RemoteException;

    public abstract void scheduleLaunchActivity(Intent intent, IBinder ibinder, int i, ActivityInfo activityinfo, Configuration configuration, Configuration configuration1, CompatibilityInfo compatibilityinfo, 
            String s, IVoiceInteractor ivoiceinteractor, int j, Bundle bundle, PersistableBundle persistablebundle, List list, List list1, 
            boolean flag, boolean flag1, ProfilerInfo profilerinfo)
        throws RemoteException;

    public abstract void scheduleLocalVoiceInteractionStarted(IBinder ibinder, IVoiceInteractor ivoiceinteractor)
        throws RemoteException;

    public abstract void scheduleLowMemory()
        throws RemoteException;

    public abstract void scheduleMultiWindowModeChanged(IBinder ibinder, boolean flag, Configuration configuration)
        throws RemoteException;

    public abstract void scheduleNewIntent(List list, IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void scheduleOnNewActivityOptions(IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void schedulePauseActivity(IBinder ibinder, boolean flag, boolean flag1, int i, boolean flag2)
        throws RemoteException;

    public abstract void schedulePictureInPictureModeChanged(IBinder ibinder, boolean flag, Configuration configuration)
        throws RemoteException;

    public abstract void scheduleReceiver(Intent intent, ActivityInfo activityinfo, CompatibilityInfo compatibilityinfo, int i, String s, Bundle bundle, boolean flag, 
            int j, int k)
        throws RemoteException;

    public abstract void scheduleRegisteredReceiver(IIntentReceiver iintentreceiver, Intent intent, int i, String s, Bundle bundle, boolean flag, boolean flag1, 
            int j, int k)
        throws RemoteException;

    public abstract void scheduleRelaunchActivity(IBinder ibinder, List list, List list1, int i, boolean flag, Configuration configuration, Configuration configuration1, 
            boolean flag1)
        throws RemoteException;

    public abstract void scheduleResumeActivity(IBinder ibinder, int i, boolean flag, Bundle bundle)
        throws RemoteException;

    public abstract void scheduleSendResult(IBinder ibinder, List list)
        throws RemoteException;

    public abstract void scheduleServiceArgs(IBinder ibinder, ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void scheduleSleeping(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void scheduleStopActivity(IBinder ibinder, boolean flag, int i)
        throws RemoteException;

    public abstract void scheduleStopService(IBinder ibinder)
        throws RemoteException;

    public abstract void scheduleSuicide()
        throws RemoteException;

    public abstract void scheduleTranslucentConversionComplete(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void scheduleTrimMemory(int i)
        throws RemoteException;

    public abstract void scheduleUnbindService(IBinder ibinder, Intent intent)
        throws RemoteException;

    public abstract void scheduleWindowVisibility(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void setCoreSettings(Bundle bundle)
        throws RemoteException;

    public abstract void setHttpProxy(String s, String s1, String s2, Uri uri)
        throws RemoteException;

    public abstract void setNetworkBlockSeq(long l)
        throws RemoteException;

    public abstract void setProcessState(int i)
        throws RemoteException;

    public abstract void setSchedulingGroup(int i)
        throws RemoteException;

    public abstract void startBinderTracking()
        throws RemoteException;

    public abstract void stopBinderTrackingAndDump(ParcelFileDescriptor parcelfiledescriptor)
        throws RemoteException;

    public abstract void unstableProviderDied(IBinder ibinder)
        throws RemoteException;

    public abstract void updatePackageCompatibilityInfo(String s, CompatibilityInfo compatibilityinfo)
        throws RemoteException;

    public abstract void updateTimePrefs(int i)
        throws RemoteException;

    public abstract void updateTimeZone()
        throws RemoteException;
}
