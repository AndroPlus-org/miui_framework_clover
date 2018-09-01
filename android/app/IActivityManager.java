// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.app.assist.AssistContent;
import android.app.assist.AssistStructure;
import android.content.*;
import android.content.pm.*;
import android.content.res.Configuration;
import android.graphics.*;
import android.net.Uri;
import android.os.*;
import android.service.voice.IVoiceInteractionSession;
import android.text.TextUtils;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.os.IResultReceiver;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.miui.internal.transition.IMiuiAppTransitionAnimationHelper;
import java.util.List;

// Referenced classes of package android.app:
//            IApplicationThread, IServiceConnection, PictureInPictureParams, ContentProviderHolder, 
//            PendingIntent, ProfilerInfo, IMiuiActivityObserver, IProcessObserver, 
//            ITaskStackListener, IUidObserver, IUserSwitchObserver, IActivityController, 
//            Notification, WaitResult, IInstrumentationWatcher, IUiAutomationConnection, 
//            IStopUserCallback

public interface IActivityManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IActivityManager
    {

        public static IActivityManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IActivityManager");
            if(iinterface != null && (iinterface instanceof IActivityManager))
                return (IActivityManager)iinterface;
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
                parcel1.writeString("android.app.IActivityManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = openContentUri(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel = (ApplicationErrorReport.ParcelableCrashInfo)ApplicationErrorReport.ParcelableCrashInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                handleApplicationCrash(ibinder, parcel);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread4 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                String s10 = parcel.readString();
                Intent intent;
                String s15;
                IBinder ibinder29;
                String s28;
                ProfilerInfo profilerinfo1;
                if(parcel.readInt() != 0)
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent = null;
                s15 = parcel.readString();
                ibinder29 = parcel.readStrongBinder();
                s28 = parcel.readString();
                i = parcel.readInt();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    profilerinfo1 = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(parcel);
                else
                    profilerinfo1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = startActivity(iapplicationthread4, s10, intent, s15, ibinder29, s28, i, j, profilerinfo1, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.IActivityManager");
                unhandledBack();
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder33 = parcel.readStrongBinder();
                i = parcel.readInt();
                Intent intent1;
                boolean flag;
                if(parcel.readInt() != 0)
                    intent1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent1 = null;
                flag = finishActivity(ibinder33, i, intent1, parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread14 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                String s11 = parcel.readString();
                IIntentReceiver iintentreceiver = android.content.IIntentReceiver.Stub.asInterface(parcel.readStrongBinder());
                IntentFilter intentfilter;
                if(parcel.readInt() != 0)
                    intentfilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel(parcel);
                else
                    intentfilter = null;
                parcel = registerReceiver(iapplicationthread14, s11, iintentreceiver, intentfilter, parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.app.IActivityManager");
                unregisterReceiver(android.content.IIntentReceiver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread7 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                Intent intent2;
                Bundle bundle5;
                String s16;
                String s24;
                IIntentReceiver iintentreceiver1;
                Bundle bundle11;
                boolean flag1;
                String as2[];
                boolean flag92;
                if(parcel.readInt() != 0)
                    intent2 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent2 = null;
                s16 = parcel.readString();
                iintentreceiver1 = android.content.IIntentReceiver.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                s24 = parcel.readString();
                if(parcel.readInt() != 0)
                    bundle11 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle11 = null;
                as2 = parcel.createStringArray();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle5 = null;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(parcel.readInt() != 0)
                    flag92 = true;
                else
                    flag92 = false;
                i = broadcastIntent(iapplicationthread7, intent2, s16, iintentreceiver1, i, s24, bundle11, as2, j, bundle5, flag1, flag92, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread15 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                Intent intent3;
                if(parcel.readInt() != 0)
                    intent3 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent3 = null;
                unbroadcastIntent(iapplicationthread15, intent3, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder34 = parcel.readStrongBinder();
                i = parcel.readInt();
                String s = parcel.readString();
                boolean flag2;
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                finishReceiver(ibinder34, i, s, parcel1, flag2, parcel.readInt());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.app.IActivityManager");
                attachApplication(IApplicationThread.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder1 = parcel.readStrongBinder();
                boolean flag3;
                if(parcel.readInt() != 0)
                    parcel1 = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                activityIdle(ibinder1, parcel1, flag3);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.app.IActivityManager");
                activityPaused(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder35 = parcel.readStrongBinder();
                PersistableBundle persistablebundle;
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    persistablebundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(parcel);
                else
                    persistablebundle = null;
                if(parcel.readInt() != 0)
                    parcel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                activityStopped(ibinder35, parcel1, persistablebundle, parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getCallingPackage(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getCallingActivity(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getTasks(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.app.IActivityManager");
                i = parcel.readInt();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                moveTaskToFront(i, j, parcel);
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.app.IActivityManager");
                moveTaskBackwards(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder2 = parcel.readStrongBinder();
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                i = getTaskForActivity(ibinder2, flag4);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                String s35 = parcel.readString();
                i = parcel.readInt();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                parcel = getContentProvider(iapplicationthread, s35, i, flag5);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.app.IActivityManager");
                publishContentProviders(IApplicationThread.Stub.asInterface(parcel.readStrongBinder()), parcel.createTypedArrayList(ContentProviderHolder.CREATOR));
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag6 = refContentProvider(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.app.IActivityManager");
                finishSubActivity(parcel.readStrongBinder(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.app.IActivityManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getRunningServiceControlPanel(parcel);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread16 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                Intent intent4;
                String s6;
                boolean flag7;
                if(parcel.readInt() != 0)
                    intent4 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent4 = null;
                s6 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                parcel = startService(iapplicationthread16, intent4, s6, flag7, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread17 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                Intent intent5;
                if(parcel.readInt() != 0)
                    intent5 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent5 = null;
                i = stopService(iapplicationthread17, intent5, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread18 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                IBinder ibinder20 = parcel.readStrongBinder();
                Intent intent6;
                if(parcel.readInt() != 0)
                    intent6 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent6 = null;
                i = bindService(iapplicationthread18, ibinder20, intent6, parcel.readString(), IServiceConnection.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag8 = unbindService(IServiceConnection.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder36 = parcel.readStrongBinder();
                Intent intent7;
                if(parcel.readInt() != 0)
                    intent7 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent7 = null;
                publishService(ibinder36, intent7, parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.app.IActivityManager");
                activityResumed(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.app.IActivityManager");
                String s1 = parcel.readString();
                boolean flag9;
                boolean flag93;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                if(parcel.readInt() != 0)
                    flag93 = true;
                else
                    flag93 = false;
                setDebugApp(s1, flag9, flag93);
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag10;
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                setAlwaysFinish(flag10);
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.app.IActivityManager");
                ComponentName componentname;
                String s7;
                Bundle bundle12;
                boolean flag11;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                s7 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    bundle12 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle12 = null;
                flag11 = startInstrumentation(componentname, s7, i, bundle12, IInstrumentationWatcher.Stub.asInterface(parcel.readStrongBinder()), IUiAutomationConnection.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread1 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addInstrumentationResults(iapplicationthread1, parcel);
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread2 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                finishInstrumentation(iapplicationthread2, i, parcel);
                parcel1.writeNoException();
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getConfiguration();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag12;
                if(parcel.readInt() != 0)
                    parcel = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag12 = updateConfiguration(parcel);
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.app.IActivityManager");
                ComponentName componentname1;
                boolean flag13;
                if(parcel.readInt() != 0)
                    componentname1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname1 = null;
                flag13 = stopServiceToken(componentname1, parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 40: // '('
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getActivityClassForToken(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getPackageForToken(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.app.IActivityManager");
                setProcessLimit(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.app.IActivityManager");
                i = getProcessLimit();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 44: // ','
                parcel.enforceInterface("android.app.IActivityManager");
                i = checkPermission(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.app.IActivityManager");
                Uri uri;
                if(parcel.readInt() != 0)
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri = null;
                i = checkUriPermission(uri, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread5 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                String s36 = parcel.readString();
                Uri uri1;
                if(parcel.readInt() != 0)
                    uri1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri1 = null;
                grantUriPermission(iapplicationthread5, s36, uri1, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread6 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                String s37 = parcel.readString();
                Uri uri2;
                if(parcel.readInt() != 0)
                    uri2 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri2 = null;
                revokeUriPermission(iapplicationthread6, s37, uri2, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.app.IActivityManager");
                IActivityController iactivitycontroller = IActivityController.Stub.asInterface(parcel.readStrongBinder());
                boolean flag14;
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                setActivityController(iactivitycontroller, flag14);
                parcel1.writeNoException();
                return true;

            case 49: // '1'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread3 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                boolean flag15;
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                showWaitingForDebugger(iapplicationthread3, flag15);
                parcel1.writeNoException();
                return true;

            case 50: // '2'
                parcel.enforceInterface("android.app.IActivityManager");
                signalPersistentProcesses(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 51: // '3'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getRecentTasks(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 52: // '4'
                parcel.enforceInterface("android.app.IActivityManager");
                serviceDoneExecuting(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 53: // '5'
                parcel.enforceInterface("android.app.IActivityManager");
                activityDestroyed(parcel.readStrongBinder());
                return true;

            case 54: // '6'
                parcel.enforceInterface("android.app.IActivityManager");
                j = parcel.readInt();
                String s17 = parcel.readString();
                IBinder ibinder21 = parcel.readStrongBinder();
                String s29 = parcel.readString();
                int k = parcel.readInt();
                Intent aintent[] = (Intent[])parcel.createTypedArray(Intent.CREATOR);
                String as1[] = parcel.createStringArray();
                i = parcel.readInt();
                Bundle bundle;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                parcel = getIntentSender(j, s17, ibinder21, s29, k, aintent, as1, i, bundle, parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 55: // '7'
                parcel.enforceInterface("android.app.IActivityManager");
                cancelIntentSender(android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 56: // '8'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getPackageForIntentSender(android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 57: // '9'
                parcel.enforceInterface("android.app.IActivityManager");
                registerIntentSenderCancelListener(android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder()), com.android.internal.os.IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 58: // ':'
                parcel.enforceInterface("android.app.IActivityManager");
                unregisterIntentSenderCancelListener(android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder()), com.android.internal.os.IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 59: // ';'
                parcel.enforceInterface("android.app.IActivityManager");
                enterSafeMode();
                parcel1.writeNoException();
                return true;

            case 60: // '<'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder37 = parcel.readStrongBinder();
                Intent intent8;
                boolean flag16;
                if(parcel.readInt() != 0)
                    intent8 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent8 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag16 = startNextMatchingActivity(ibinder37, intent8, parcel);
                parcel1.writeNoException();
                if(flag16)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 61: // '='
                parcel.enforceInterface("android.app.IActivityManager");
                noteWakeupAlarm(android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 62: // '>'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder3 = parcel.readStrongBinder();
                boolean flag17;
                if(parcel.readInt() != 0)
                    flag17 = true;
                else
                    flag17 = false;
                removeContentProvider(ibinder3, flag17);
                parcel1.writeNoException();
                return true;

            case 63: // '?'
                parcel.enforceInterface("android.app.IActivityManager");
                setRequestedOrientation(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 64: // '@'
                parcel.enforceInterface("android.app.IActivityManager");
                i = getRequestedOrientation(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 65: // 'A'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder38 = parcel.readStrongBinder();
                Intent intent9;
                boolean flag18;
                if(parcel.readInt() != 0)
                    intent9 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent9 = null;
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                unbindFinished(ibinder38, intent9, flag18);
                parcel1.writeNoException();
                return true;

            case 66: // 'B'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder4 = parcel.readStrongBinder();
                i = parcel.readInt();
                boolean flag19;
                if(parcel.readInt() != 0)
                    flag19 = true;
                else
                    flag19 = false;
                setProcessImportant(ibinder4, i, flag19, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 67: // 'C'
                parcel.enforceInterface("android.app.IActivityManager");
                ComponentName componentname2;
                IBinder ibinder22;
                Notification notification;
                if(parcel.readInt() != 0)
                    componentname2 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname2 = null;
                ibinder22 = parcel.readStrongBinder();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    notification = (Notification)Notification.CREATOR.createFromParcel(parcel);
                else
                    notification = null;
                setServiceForeground(componentname2, ibinder22, i, notification, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 68: // 'D'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder5 = parcel.readStrongBinder();
                boolean flag20;
                if(parcel.readInt() != 0)
                    flag20 = true;
                else
                    flag20 = false;
                flag20 = moveActivityTaskToBack(ibinder5, flag20);
                parcel1.writeNoException();
                if(flag20)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 69: // 'E'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = new ActivityManager.MemoryInfo();
                getMemoryInfo(parcel);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 70: // 'F'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getProcessesInErrorState();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 71: // 'G'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag21 = clearApplicationUserData(parcel.readString(), android.content.pm.IPackageDataObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                if(flag21)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 72: // 'H'
                parcel.enforceInterface("android.app.IActivityManager");
                forceStopPackage(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 73: // 'I'
                parcel.enforceInterface("android.app.IActivityManager");
                int ai[] = parcel.createIntArray();
                String s38 = parcel.readString();
                boolean flag22;
                if(parcel.readInt() != 0)
                    flag22 = true;
                else
                    flag22 = false;
                flag22 = killPids(ai, s38, flag22);
                parcel1.writeNoException();
                if(flag22)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 74: // 'J'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getServices(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 75: // 'K'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getTaskThumbnail(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 76: // 'L'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getTaskDescription(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 77: // 'M'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getRunningAppProcesses();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 78: // 'N'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getDeviceConfigurationInfo();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 79: // 'O'
                parcel.enforceInterface("android.app.IActivityManager");
                Intent intent10;
                if(parcel.readInt() != 0)
                    intent10 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent10 = null;
                parcel = peekService(intent10, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStrongBinder(parcel);
                return true;

            case 80: // 'P'
                parcel.enforceInterface("android.app.IActivityManager");
                String s39 = parcel.readString();
                i = parcel.readInt();
                ProfilerInfo profilerinfo;
                boolean flag23;
                if(parcel.readInt() != 0)
                    flag23 = true;
                else
                    flag23 = false;
                if(parcel.readInt() != 0)
                    profilerinfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(parcel);
                else
                    profilerinfo = null;
                flag23 = profileControl(s39, i, flag23, profilerinfo, parcel.readInt());
                parcel1.writeNoException();
                if(flag23)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 81: // 'Q'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag24 = shutdown(parcel.readInt());
                parcel1.writeNoException();
                if(flag24)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 82: // 'R'
                parcel.enforceInterface("android.app.IActivityManager");
                stopAppSwitches();
                parcel1.writeNoException();
                return true;

            case 83: // 'S'
                parcel.enforceInterface("android.app.IActivityManager");
                resumeAppSwitches();
                parcel1.writeNoException();
                return true;

            case 84: // 'T'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag25 = bindBackupAgent(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag25)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 85: // 'U'
                parcel.enforceInterface("android.app.IActivityManager");
                backupAgentCreated(parcel.readString(), parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 86: // 'V'
                parcel.enforceInterface("android.app.IActivityManager");
                if(parcel.readInt() != 0)
                    parcel = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                unbindBackupAgent(parcel);
                parcel1.writeNoException();
                return true;

            case 87: // 'W'
                parcel.enforceInterface("android.app.IActivityManager");
                i = getUidForIntentSender(android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 88: // 'X'
                parcel.enforceInterface("android.app.IActivityManager");
                i = parcel.readInt();
                j = parcel.readInt();
                int l = parcel.readInt();
                boolean flag26;
                boolean flag94;
                if(parcel.readInt() != 0)
                    flag26 = true;
                else
                    flag26 = false;
                if(parcel.readInt() != 0)
                    flag94 = true;
                else
                    flag94 = false;
                i = handleIncomingUser(i, j, l, flag26, flag94, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 89: // 'Y'
                parcel.enforceInterface("android.app.IActivityManager");
                addPackageDependency(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 90: // 'Z'
                parcel.enforceInterface("android.app.IActivityManager");
                killApplication(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 91: // '['
                parcel.enforceInterface("android.app.IActivityManager");
                closeSystemDialogs(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 92: // '\\'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getProcessMemoryInfo(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 93: // ']'
                parcel.enforceInterface("android.app.IActivityManager");
                killApplicationProcess(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 94: // '^'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread8 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                IIntentSender iintentsender = android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder());
                IBinder ibinder23 = parcel.readStrongBinder();
                Intent intent11;
                String s18;
                IBinder ibinder30;
                String s30;
                int i1;
                if(parcel.readInt() != 0)
                    intent11 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent11 = null;
                s18 = parcel.readString();
                ibinder30 = parcel.readStrongBinder();
                s30 = parcel.readString();
                i1 = parcel.readInt();
                i = parcel.readInt();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = startActivityIntentSender(iapplicationthread8, iintentsender, ibinder23, intent11, s18, ibinder30, s30, i1, i, j, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 95: // '_'
                parcel.enforceInterface("android.app.IActivityManager");
                overridePendingTransition(parcel.readStrongBinder(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 96: // '`'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder39 = parcel.readStrongBinder();
                String s2 = parcel.readString();
                boolean flag27;
                if(parcel.readInt() != 0)
                    flag27 = true;
                else
                    flag27 = false;
                if(parcel.readInt() != 0)
                    parcel = (ApplicationErrorReport.ParcelableCrashInfo)ApplicationErrorReport.ParcelableCrashInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag27 = handleApplicationWtf(ibinder39, s2, flag27, parcel);
                parcel1.writeNoException();
                if(flag27)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 97: // 'a'
                parcel.enforceInterface("android.app.IActivityManager");
                killBackgroundProcesses(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 98: // 'b'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag28 = isUserAMonkey();
                parcel1.writeNoException();
                if(flag28)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 99: // 'c'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread9 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                String s31 = parcel.readString();
                Intent intent12;
                Bundle bundle6;
                String s19;
                IBinder ibinder31;
                ProfilerInfo profilerinfo2;
                String s44;
                if(parcel.readInt() != 0)
                    intent12 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent12 = null;
                s19 = parcel.readString();
                ibinder31 = parcel.readStrongBinder();
                s44 = parcel.readString();
                i = parcel.readInt();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    profilerinfo2 = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(parcel);
                else
                    profilerinfo2 = null;
                if(parcel.readInt() != 0)
                    bundle6 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle6 = null;
                parcel = startActivityAndWait(iapplicationthread9, s31, intent12, s19, ibinder31, s44, i, j, profilerinfo2, bundle6, parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 100: // 'd'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag29 = willActivityBeVisible(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag29)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 101: // 'e'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread10 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                String s32 = parcel.readString();
                Intent intent13;
                Bundle bundle7;
                String s20;
                String s25;
                Configuration configuration1;
                IBinder ibinder43;
                if(parcel.readInt() != 0)
                    intent13 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent13 = null;
                s20 = parcel.readString();
                ibinder43 = parcel.readStrongBinder();
                s25 = parcel.readString();
                i = parcel.readInt();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    configuration1 = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    configuration1 = null;
                if(parcel.readInt() != 0)
                    bundle7 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle7 = null;
                i = startActivityWithConfig(iapplicationthread10, s32, intent13, s20, ibinder43, s25, i, j, configuration1, bundle7, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 102: // 'f'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getRunningExternalApplications();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 103: // 'g'
                parcel.enforceInterface("android.app.IActivityManager");
                finishHeavyWeightApp();
                parcel1.writeNoException();
                return true;

            case 104: // 'h'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder6 = parcel.readStrongBinder();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (android.os.StrictMode.ViolationInfo)android.os.StrictMode.ViolationInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                handleApplicationStrictModeViolation(ibinder6, i, parcel);
                parcel1.writeNoException();
                return true;

            case 105: // 'i'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag30 = isImmersive(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag30)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 106: // 'j'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder7 = parcel.readStrongBinder();
                boolean flag31;
                if(parcel.readInt() != 0)
                    flag31 = true;
                else
                    flag31 = false;
                setImmersive(ibinder7, flag31);
                parcel1.writeNoException();
                return true;

            case 107: // 'k'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag32 = isTopActivityImmersive();
                parcel1.writeNoException();
                if(flag32)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 108: // 'l'
                parcel.enforceInterface("android.app.IActivityManager");
                crashApplication(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 109: // 'm'
                parcel.enforceInterface("android.app.IActivityManager");
                Uri uri3;
                if(parcel.readInt() != 0)
                    uri3 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri3 = null;
                parcel = getProviderMimeType(uri3, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 110: // 'n'
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = newUriPermissionOwner(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStrongBinder(parcel);
                return true;

            case 111: // 'o'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder40 = parcel.readStrongBinder();
                i = parcel.readInt();
                String s8 = parcel.readString();
                Uri uri4;
                if(parcel.readInt() != 0)
                    uri4 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri4 = null;
                grantUriPermissionFromOwner(ibinder40, i, s8, uri4, parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 112: // 'p'
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder41 = parcel.readStrongBinder();
                Uri uri5;
                if(parcel.readInt() != 0)
                    uri5 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri5 = null;
                revokeUriPermissionFromOwner(ibinder41, uri5, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 113: // 'q'
                parcel.enforceInterface("android.app.IActivityManager");
                i = parcel.readInt();
                String s40 = parcel.readString();
                Uri uri6;
                if(parcel.readInt() != 0)
                    uri6 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri6 = null;
                i = checkGrantUriPermission(i, s40, uri6, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 114: // 'r'
                parcel.enforceInterface("android.app.IActivityManager");
                String s3 = parcel.readString();
                i = parcel.readInt();
                String s41;
                boolean flag33;
                boolean flag95;
                boolean flag99;
                if(parcel.readInt() != 0)
                    flag33 = true;
                else
                    flag33 = false;
                if(parcel.readInt() != 0)
                    flag95 = true;
                else
                    flag95 = false;
                if(parcel.readInt() != 0)
                    flag99 = true;
                else
                    flag99 = false;
                s41 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag33 = dumpHeap(s3, i, flag33, flag95, flag99, s41, parcel);
                parcel1.writeNoException();
                if(flag33)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 115: // 's'
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread11 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                String s42 = parcel.readString();
                Intent aintent1[] = (Intent[])parcel.createTypedArray(Intent.CREATOR);
                String as[] = parcel.createStringArray();
                IBinder ibinder24 = parcel.readStrongBinder();
                Bundle bundle1;
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                i = startActivities(iapplicationthread11, s42, aintent1, as, ibinder24, bundle1, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 116: // 't'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag34 = isUserRunning(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag34)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 117: // 'u'
                parcel.enforceInterface("android.app.IActivityManager");
                activitySlept(parcel.readStrongBinder());
                return true;

            case 118: // 'v'
                parcel.enforceInterface("android.app.IActivityManager");
                i = getFrontActivityScreenCompatMode();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 119: // 'w'
                parcel.enforceInterface("android.app.IActivityManager");
                setFrontActivityScreenCompatMode(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 120: // 'x'
                parcel.enforceInterface("android.app.IActivityManager");
                i = getPackageScreenCompatMode(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 121: // 'y'
                parcel.enforceInterface("android.app.IActivityManager");
                setPackageScreenCompatMode(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 122: // 'z'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag35 = getPackageAskScreenCompat(parcel.readString());
                parcel1.writeNoException();
                if(flag35)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 123: // '{'
                parcel.enforceInterface("android.app.IActivityManager");
                String s4 = parcel.readString();
                boolean flag36;
                if(parcel.readInt() != 0)
                    flag36 = true;
                else
                    flag36 = false;
                setPackageAskScreenCompat(s4, flag36);
                parcel1.writeNoException();
                return true;

            case 124: // '|'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag37 = switchUser(parcel.readInt());
                parcel1.writeNoException();
                if(flag37)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 125: // '}'
                parcel.enforceInterface("android.app.IActivityManager");
                setFocusedTask(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 126: // '~'
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag38 = removeTask(parcel.readInt());
                parcel1.writeNoException();
                if(flag38)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 127: // '\177'
                parcel.enforceInterface("android.app.IActivityManager");
                registerProcessObserver(IProcessObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 128: 
                parcel.enforceInterface("android.app.IActivityManager");
                unregisterProcessObserver(IProcessObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 129: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag39 = isIntentSenderTargetedToPackage(android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag39)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 130: 
                parcel.enforceInterface("android.app.IActivityManager");
                if(parcel.readInt() != 0)
                    parcel = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updatePersistentConfiguration(parcel);
                parcel1.writeNoException();
                return true;

            case 131: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getProcessPss(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeLongArray(parcel);
                return true;

            case 132: 
                parcel.enforceInterface("android.app.IActivityManager");
                CharSequence charsequence;
                boolean flag40;
                if(parcel.readInt() != 0)
                    charsequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    charsequence = null;
                if(parcel.readInt() != 0)
                    flag40 = true;
                else
                    flag40 = false;
                showBootMessage(charsequence, flag40);
                parcel1.writeNoException();
                return true;

            case 133: 
                parcel.enforceInterface("android.app.IActivityManager");
                killAllBackgroundProcesses();
                parcel1.writeNoException();
                return true;

            case 134: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getContentProviderExternal(parcel.readString(), parcel.readInt(), parcel.readStrongBinder());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 135: 
                parcel.enforceInterface("android.app.IActivityManager");
                removeContentProviderExternal(parcel.readString(), parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 136: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = new ActivityManager.RunningAppProcessInfo();
                getMyMemoryState(parcel);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 137: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag41 = killProcessesBelowForeground(parcel.readString());
                parcel1.writeNoException();
                if(flag41)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 138: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getCurrentUser();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 139: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag42 = shouldUpRecreateTask(parcel.readStrongBinder(), parcel.readString());
                parcel1.writeNoException();
                if(flag42)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 140: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder42 = parcel.readStrongBinder();
                Intent intent14;
                boolean flag43;
                if(parcel.readInt() != 0)
                    intent14 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent14 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag43 = navigateUpTo(ibinder42, intent14, i, parcel);
                parcel1.writeNoException();
                if(flag43)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 141: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag44;
                if(parcel.readInt() != 0)
                    flag44 = true;
                else
                    flag44 = false;
                setLockScreenShown(flag44, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 142: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag45 = finishActivityAffinity(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag45)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 143: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = getLaunchedFromUid(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 144: 
                parcel.enforceInterface("android.app.IActivityManager");
                unstableProviderDied(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 145: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag46 = isIntentSenderAnActivity(android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag46)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 146: 
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread12 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                String s33 = parcel.readString();
                Intent intent15;
                Bundle bundle8;
                String s21;
                String s26;
                ProfilerInfo profilerinfo3;
                IBinder ibinder44;
                if(parcel.readInt() != 0)
                    intent15 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent15 = null;
                s26 = parcel.readString();
                ibinder44 = parcel.readStrongBinder();
                s21 = parcel.readString();
                i = parcel.readInt();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    profilerinfo3 = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(parcel);
                else
                    profilerinfo3 = null;
                if(parcel.readInt() != 0)
                    bundle8 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle8 = null;
                i = startActivityAsUser(iapplicationthread12, s33, intent15, s26, ibinder44, s21, i, j, profilerinfo3, bundle8, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 147: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = parcel.readInt();
                boolean flag47;
                if(parcel.readInt() != 0)
                    flag47 = true;
                else
                    flag47 = false;
                i = stopUser(i, flag47, IStopUserCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 148: 
                parcel.enforceInterface("android.app.IActivityManager");
                registerUserSwitchObserver(IUserSwitchObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 149: 
                parcel.enforceInterface("android.app.IActivityManager");
                unregisterUserSwitchObserver(IUserSwitchObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 150: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getRunningUserIds();
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 151: 
                parcel.enforceInterface("android.app.IActivityManager");
                requestBugReport(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 152: 
                parcel.enforceInterface("android.app.IActivityManager");
                requestTelephonyBugReport(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 153: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = parcel.readInt();
                boolean flag48;
                long l1;
                if(parcel.readInt() != 0)
                    flag48 = true;
                else
                    flag48 = false;
                l1 = inputDispatchingTimedOut(i, flag48, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l1);
                return true;

            case 154: 
                parcel.enforceInterface("android.app.IActivityManager");
                clearPendingBackup();
                parcel1.writeNoException();
                return true;

            case 155: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getIntentForIntentSender(android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 156: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getAssistContextExtras(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 157: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder28 = parcel.readStrongBinder();
                Bundle bundle2;
                AssistContent assistcontent;
                AssistStructure assiststructure;
                if(parcel.readInt() != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle2 = null;
                if(parcel.readInt() != 0)
                    assiststructure = (AssistStructure)AssistStructure.CREATOR.createFromParcel(parcel);
                else
                    assiststructure = null;
                if(parcel.readInt() != 0)
                    assistcontent = (AssistContent)AssistContent.CREATOR.createFromParcel(parcel);
                else
                    assistcontent = null;
                if(parcel.readInt() != 0)
                    parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reportAssistContextExtras(ibinder28, bundle2, assiststructure, assistcontent, parcel);
                parcel1.writeNoException();
                return true;

            case 158: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getLaunchedFromPackage(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 159: 
                parcel.enforceInterface("android.app.IActivityManager");
                killUid(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 160: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag49;
                if(parcel.readInt() != 0)
                    flag49 = true;
                else
                    flag49 = false;
                setUserIsMonkey(flag49);
                parcel1.writeNoException();
                return true;

            case 161: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder8 = parcel.readStrongBinder();
                boolean flag50;
                if(parcel.readInt() != 0)
                    flag50 = true;
                else
                    flag50 = false;
                hang(ibinder8, flag50);
                parcel1.writeNoException();
                return true;

            case 162: 
                parcel.enforceInterface("android.app.IActivityManager");
                j = parcel.readInt();
                i = parcel.readInt();
                boolean flag51;
                if(parcel.readInt() != 0)
                    flag51 = true;
                else
                    flag51 = false;
                moveTaskToStack(j, i, flag51);
                parcel1.writeNoException();
                return true;

            case 163: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = parcel.readInt();
                Rect rect;
                boolean flag52;
                boolean flag96;
                boolean flag100;
                if(parcel.readInt() != 0)
                    rect = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect = null;
                if(parcel.readInt() != 0)
                    flag52 = true;
                else
                    flag52 = false;
                if(parcel.readInt() != 0)
                    flag96 = true;
                else
                    flag96 = false;
                if(parcel.readInt() != 0)
                    flag100 = true;
                else
                    flag100 = false;
                resizeStack(i, rect, flag52, flag96, flag100, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 164: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getAllStackInfos();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 165: 
                parcel.enforceInterface("android.app.IActivityManager");
                setFocusedStack(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 166: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getStackInfo(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 167: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag53 = convertFromTranslucent(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag53)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 168: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder9 = parcel.readStrongBinder();
                boolean flag54;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag54 = convertToTranslucent(ibinder9, parcel);
                parcel1.writeNoException();
                if(flag54)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 169: 
                parcel.enforceInterface("android.app.IActivityManager");
                notifyActivityDrawn(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 170: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder10 = parcel.readStrongBinder();
                boolean flag55;
                if(parcel.readInt() != 0)
                    flag55 = true;
                else
                    flag55 = false;
                reportActivityFullyDrawn(ibinder10, flag55);
                parcel1.writeNoException();
                return true;

            case 171: 
                parcel.enforceInterface("android.app.IActivityManager");
                restart();
                parcel1.writeNoException();
                return true;

            case 172: 
                parcel.enforceInterface("android.app.IActivityManager");
                performIdleMaintenance();
                parcel1.writeNoException();
                return true;

            case 173: 
                parcel.enforceInterface("android.app.IActivityManager");
                Uri uri7;
                if(parcel.readInt() != 0)
                    uri7 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri7 = null;
                takePersistableUriPermission(uri7, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 174: 
                parcel.enforceInterface("android.app.IActivityManager");
                Uri uri8;
                if(parcel.readInt() != 0)
                    uri8 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri8 = null;
                releasePersistableUriPermission(uri8, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 175: 
                parcel.enforceInterface("android.app.IActivityManager");
                String s5 = parcel.readString();
                boolean flag56;
                if(parcel.readInt() != 0)
                    flag56 = true;
                else
                    flag56 = false;
                parcel = getPersistedUriPermissions(s5, flag56);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 176: 
                parcel.enforceInterface("android.app.IActivityManager");
                appNotRespondingViaProvider(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 177: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getTaskBounds(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 178: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = getActivityDisplayId(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 179: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag57 = setProcessMemoryTrimLevel(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag57)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 180: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getTagForIntentSender(android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 181: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag58 = startUserInBackground(parcel.readInt());
                parcel1.writeNoException();
                if(flag58)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 182: 
                parcel.enforceInterface("android.app.IActivityManager");
                startLockTaskModeById(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 183: 
                parcel.enforceInterface("android.app.IActivityManager");
                startLockTaskModeByToken(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 184: 
                parcel.enforceInterface("android.app.IActivityManager");
                stopLockTaskMode();
                parcel1.writeNoException();
                return true;

            case 185: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag59 = isInLockTaskMode();
                parcel1.writeNoException();
                if(flag59)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 186: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder11 = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel = (ActivityManager.TaskDescription)ActivityManager.TaskDescription.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setTaskDescription(ibinder11, parcel);
                parcel1.writeNoException();
                return true;

            case 187: 
                parcel.enforceInterface("android.app.IActivityManager");
                String s12 = parcel.readString();
                j = parcel.readInt();
                i = parcel.readInt();
                Intent intent16;
                Bundle bundle9;
                IVoiceInteractionSession ivoiceinteractionsession1;
                String s27;
                IVoiceInteractor ivoiceinteractor;
                ProfilerInfo profilerinfo4;
                int j1;
                if(parcel.readInt() != 0)
                    intent16 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent16 = null;
                s27 = parcel.readString();
                ivoiceinteractionsession1 = android.service.voice.IVoiceInteractionSession.Stub.asInterface(parcel.readStrongBinder());
                ivoiceinteractor = com.android.internal.app.IVoiceInteractor.Stub.asInterface(parcel.readStrongBinder());
                j1 = parcel.readInt();
                if(parcel.readInt() != 0)
                    profilerinfo4 = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(parcel);
                else
                    profilerinfo4 = null;
                if(parcel.readInt() != 0)
                    bundle9 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle9 = null;
                i = startVoiceActivity(s12, j, i, intent16, s27, ivoiceinteractionsession1, ivoiceinteractor, j1, profilerinfo4, bundle9, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 188: 
                parcel.enforceInterface("android.app.IActivityManager");
                String s9 = parcel.readString();
                j = parcel.readInt();
                i = parcel.readInt();
                Intent intent17;
                String s13;
                Bundle bundle13;
                if(parcel.readInt() != 0)
                    intent17 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent17 = null;
                s13 = parcel.readString();
                if(parcel.readInt() != 0)
                    bundle13 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle13 = null;
                i = startAssistantActivity(s9, j, i, intent17, s13, bundle13, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 189: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getActivityOptions(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 190: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getAppTasks(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeBinderList(parcel);
                return true;

            case 191: 
                parcel.enforceInterface("android.app.IActivityManager");
                startSystemLockTaskMode(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 192: 
                parcel.enforceInterface("android.app.IActivityManager");
                stopSystemLockTaskMode();
                parcel1.writeNoException();
                return true;

            case 193: 
                parcel.enforceInterface("android.app.IActivityManager");
                finishVoiceTask(android.service.voice.IVoiceInteractionSession.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 194: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag60 = isTopOfTask(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag60)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 195: 
                parcel.enforceInterface("android.app.IActivityManager");
                notifyLaunchTaskBehindComplete(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 196: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = startActivityFromRecents(i, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 197: 
                parcel.enforceInterface("android.app.IActivityManager");
                notifyEnterAnimationComplete(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 198: 
                parcel.enforceInterface("android.app.IActivityManager");
                IApplicationThread iapplicationthread13 = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                String s34 = parcel.readString();
                Intent intent18;
                Bundle bundle10;
                String s22;
                IBinder ibinder32;
                ProfilerInfo profilerinfo5;
                boolean flag61;
                String s45;
                if(parcel.readInt() != 0)
                    intent18 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent18 = null;
                s22 = parcel.readString();
                ibinder32 = parcel.readStrongBinder();
                s45 = parcel.readString();
                j = parcel.readInt();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    profilerinfo5 = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(parcel);
                else
                    profilerinfo5 = null;
                if(parcel.readInt() != 0)
                    bundle10 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle10 = null;
                if(parcel.readInt() != 0)
                    flag61 = true;
                else
                    flag61 = false;
                i = startActivityAsCaller(iapplicationthread13, s34, intent18, s22, ibinder32, s45, j, i, profilerinfo5, bundle10, flag61, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 199: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder25 = parcel.readStrongBinder();
                Intent intent19;
                ActivityManager.TaskDescription taskdescription;
                if(parcel.readInt() != 0)
                    intent19 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent19 = null;
                if(parcel.readInt() != 0)
                    taskdescription = (ActivityManager.TaskDescription)ActivityManager.TaskDescription.CREATOR.createFromParcel(parcel);
                else
                    taskdescription = null;
                if(parcel.readInt() != 0)
                    parcel = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = addAppTask(ibinder25, intent19, taskdescription, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 200: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getAppTaskThumbnailSize();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 201: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag62 = releaseActivityInstance(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag62)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 202: 
                parcel.enforceInterface("android.app.IActivityManager");
                releaseSomeActivities(IApplicationThread.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 203: 
                parcel.enforceInterface("android.app.IActivityManager");
                bootAnimationComplete();
                parcel1.writeNoException();
                return true;

            case 204: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getTaskDescriptionIcon(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 205: 
                parcel.enforceInterface("android.app.IActivityManager");
                Intent intent20;
                String s43;
                boolean flag63;
                if(parcel.readInt() != 0)
                    intent20 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent20 = null;
                i = parcel.readInt();
                s43 = parcel.readString();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag63 = launchAssistIntent(intent20, i, s43, j, parcel);
                parcel1.writeNoException();
                if(flag63)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 206: 
                parcel.enforceInterface("android.app.IActivityManager");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startInPlaceAnimationOnFrontMostApplication(parcel);
                parcel1.writeNoException();
                return true;

            case 207: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = checkPermissionWithToken(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 208: 
                parcel.enforceInterface("android.app.IActivityManager");
                registerTaskStackListener(ITaskStackListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 209: 
                parcel.enforceInterface("android.app.IActivityManager");
                notifyCleartextNetwork(parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 210: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = createStackOnDisplay(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 211: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = getFocusedStackId();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 212: 
                parcel.enforceInterface("android.app.IActivityManager");
                setTaskResizeable(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 213: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = parcel.readInt();
                IResultReceiver iresultreceiver = com.android.internal.os.IResultReceiver.Stub.asInterface(parcel.readStrongBinder());
                Bundle bundle3;
                IBinder ibinder26;
                boolean flag64;
                boolean flag97;
                if(parcel.readInt() != 0)
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle3 = null;
                ibinder26 = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    flag64 = true;
                else
                    flag64 = false;
                if(parcel.readInt() != 0)
                    flag97 = true;
                else
                    flag97 = false;
                flag64 = requestAssistContextExtras(i, iresultreceiver, bundle3, ibinder26, flag64, flag97);
                parcel1.writeNoException();
                if(flag64)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 214: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = parcel.readInt();
                Rect rect1;
                if(parcel.readInt() != 0)
                    rect1 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect1 = null;
                resizeTask(i, rect1, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 215: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = getLockTaskModeState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 216: 
                parcel.enforceInterface("android.app.IActivityManager");
                setDumpHeapDebugLimit(parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 217: 
                parcel.enforceInterface("android.app.IActivityManager");
                dumpHeapFinished(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 218: 
                parcel.enforceInterface("android.app.IActivityManager");
                IVoiceInteractionSession ivoiceinteractionsession = android.service.voice.IVoiceInteractionSession.Stub.asInterface(parcel.readStrongBinder());
                boolean flag65;
                if(parcel.readInt() != 0)
                    flag65 = true;
                else
                    flag65 = false;
                setVoiceKeepAwake(ivoiceinteractionsession, flag65);
                parcel1.writeNoException();
                return true;

            case 219: 
                parcel.enforceInterface("android.app.IActivityManager");
                updateLockTaskPackages(parcel.readInt(), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 220: 
                parcel.enforceInterface("android.app.IActivityManager");
                noteAlarmStart(android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 221: 
                parcel.enforceInterface("android.app.IActivityManager");
                noteAlarmFinish(android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 222: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = getPackageProcessState(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 223: 
                parcel.enforceInterface("android.app.IActivityManager");
                showLockTaskEscapeMessage(parcel.readStrongBinder());
                return true;

            case 224: 
                parcel.enforceInterface("android.app.IActivityManager");
                updateDeviceOwner(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 225: 
                parcel.enforceInterface("android.app.IActivityManager");
                keyguardGoingAway(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 226: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = getUidProcessState(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 227: 
                parcel.enforceInterface("android.app.IActivityManager");
                registerUidObserver(IUidObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 228: 
                parcel.enforceInterface("android.app.IActivityManager");
                unregisterUidObserver(IUidObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 229: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag66 = isAssistDataAllowedOnCurrentActivity();
                parcel1.writeNoException();
                if(flag66)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 230: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder12 = parcel.readStrongBinder();
                boolean flag67;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag67 = showAssistFromActivity(ibinder12, parcel);
                parcel1.writeNoException();
                if(flag67)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 231: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag68 = isRootVoiceInteraction(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag68)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 232: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag69 = startBinderTracking();
                parcel1.writeNoException();
                if(flag69)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 233: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag70;
                if(parcel.readInt() != 0)
                    parcel = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag70 = stopBinderTrackingAndDump(parcel);
                parcel1.writeNoException();
                if(flag70)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 234: 
                parcel.enforceInterface("android.app.IActivityManager");
                positionTaskInStack(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 235: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = getActivityStackId(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 236: 
                parcel.enforceInterface("android.app.IActivityManager");
                exitFreeformMode(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 237: 
                parcel.enforceInterface("android.app.IActivityManager");
                reportSizeConfigurations(parcel.readStrongBinder(), parcel.createIntArray(), parcel.createIntArray(), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 238: 
                parcel.enforceInterface("android.app.IActivityManager");
                j = parcel.readInt();
                i = parcel.readInt();
                boolean flag71;
                boolean flag98;
                if(parcel.readInt() != 0)
                    flag71 = true;
                else
                    flag71 = false;
                if(parcel.readInt() != 0)
                    flag98 = true;
                else
                    flag98 = false;
                if(parcel.readInt() != 0)
                    parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag71 = moveTaskToDockedStack(j, i, flag71, flag98, parcel);
                parcel1.writeNoException();
                if(flag71)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 239: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag72;
                if(parcel.readInt() != 0)
                    flag72 = true;
                else
                    flag72 = false;
                suppressResizeConfigChanges(flag72);
                parcel1.writeNoException();
                return true;

            case 240: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = parcel.readInt();
                boolean flag73;
                if(parcel.readInt() != 0)
                    flag73 = true;
                else
                    flag73 = false;
                moveTasksToFullscreenStack(i, flag73);
                parcel1.writeNoException();
                return true;

            case 241: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = parcel.readInt();
                boolean flag74;
                if(parcel.readInt() != 0)
                    parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag74 = moveTopActivityToPinnedStack(i, parcel);
                parcel1.writeNoException();
                if(flag74)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 242: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag75 = isAppStartModeDisabled(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag75)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 243: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag76 = unlockUser(parcel.readInt(), parcel.createByteArray(), parcel.createByteArray(), android.os.IProgressListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag76)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 244: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag77 = isInMultiWindowMode(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag77)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 245: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag78 = isInPictureInPictureMode(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag78)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 246: 
                parcel.enforceInterface("android.app.IActivityManager");
                killPackageDependents(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 247: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder13 = parcel.readStrongBinder();
                boolean flag79;
                if(parcel.readInt() != 0)
                    parcel = (PictureInPictureParams)PictureInPictureParams.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag79 = enterPictureInPictureMode(ibinder13, parcel);
                parcel1.writeNoException();
                if(flag79)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 248: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder14 = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel = (PictureInPictureParams)PictureInPictureParams.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setPictureInPictureParams(ibinder14, parcel);
                parcel1.writeNoException();
                return true;

            case 249: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = getMaxNumPictureInPictureActions(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 250: 
                parcel.enforceInterface("android.app.IActivityManager");
                activityRelaunched(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 251: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getUriPermissionOwnerForActivity(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeStrongBinder(parcel);
                return true;

            case 252: 
                parcel.enforceInterface("android.app.IActivityManager");
                Rect rect2;
                Rect rect4;
                Rect rect5;
                Rect rect6;
                if(parcel.readInt() != 0)
                    rect2 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect2 = null;
                if(parcel.readInt() != 0)
                    rect6 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect6 = null;
                if(parcel.readInt() != 0)
                    rect4 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect4 = null;
                if(parcel.readInt() != 0)
                    rect5 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect5 = null;
                if(parcel.readInt() != 0)
                    parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                resizeDockedStack(rect2, rect6, rect4, rect5, parcel);
                parcel1.writeNoException();
                return true;

            case 253: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder15 = parcel.readStrongBinder();
                boolean flag80;
                if(parcel.readInt() != 0)
                    flag80 = true;
                else
                    flag80 = false;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = setVrMode(ibinder15, flag80, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 254: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel = getGrantedUriPermissions(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 255: 
                parcel.enforceInterface("android.app.IActivityManager");
                clearGrantedUriPermissions(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 256: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag81 = isAppForeground(parcel.readInt());
                parcel1.writeNoException();
                if(flag81)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 257: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder16 = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startLocalVoiceInteraction(ibinder16, parcel);
                parcel1.writeNoException();
                return true;

            case 258: 
                parcel.enforceInterface("android.app.IActivityManager");
                stopLocalVoiceInteraction(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 259: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag82 = supportsLocalVoiceInteraction();
                parcel1.writeNoException();
                if(flag82)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 260: 
                parcel.enforceInterface("android.app.IActivityManager");
                notifyPinnedStackAnimationStarted();
                parcel1.writeNoException();
                return true;

            case 261: 
                parcel.enforceInterface("android.app.IActivityManager");
                notifyPinnedStackAnimationEnded();
                parcel1.writeNoException();
                return true;

            case 262: 
                parcel.enforceInterface("android.app.IActivityManager");
                removeStack(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 263: 
                parcel.enforceInterface("android.app.IActivityManager");
                makePackageIdle(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 264: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = getMemoryTrimLevel();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 265: 
                parcel.enforceInterface("android.app.IActivityManager");
                Rect rect3;
                if(parcel.readInt() != 0)
                    rect3 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect3 = null;
                if(parcel.readInt() != 0)
                    parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                resizePinnedStack(rect3, parcel);
                parcel1.writeNoException();
                return true;

            case 266: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag83;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag83 = isVrModePackageEnabled(parcel);
                parcel1.writeNoException();
                if(flag83)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 267: 
                parcel.enforceInterface("android.app.IActivityManager");
                swapDockedAndFullscreenStack();
                parcel1.writeNoException();
                return true;

            case 268: 
                parcel.enforceInterface("android.app.IActivityManager");
                notifyLockedProfile(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 269: 
                parcel.enforceInterface("android.app.IActivityManager");
                Intent intent21;
                if(parcel.readInt() != 0)
                    intent21 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent21 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startConfirmDeviceCredentialIntent(intent21, parcel);
                parcel1.writeNoException();
                return true;

            case 270: 
                parcel.enforceInterface("android.app.IActivityManager");
                sendIdleJobTrigger();
                parcel1.writeNoException();
                return true;

            case 271: 
                parcel.enforceInterface("android.app.IActivityManager");
                IIntentSender iintentsender1 = android.content.IIntentSender.Stub.asInterface(parcel.readStrongBinder());
                IBinder ibinder27 = parcel.readStrongBinder();
                i = parcel.readInt();
                Intent intent22;
                String s14;
                String s23;
                IIntentReceiver iintentreceiver2;
                if(parcel.readInt() != 0)
                    intent22 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent22 = null;
                s14 = parcel.readString();
                iintentreceiver2 = android.content.IIntentReceiver.Stub.asInterface(parcel.readStrongBinder());
                s23 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = sendIntentSender(iintentsender1, ibinder27, i, intent22, s14, iintentreceiver2, s23, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 272: 
                parcel.enforceInterface("android.app.IActivityManager");
                setVrThread(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 273: 
                parcel.enforceInterface("android.app.IActivityManager");
                setRenderThread(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 274: 
                parcel.enforceInterface("android.app.IActivityManager");
                boolean flag84;
                if(parcel.readInt() != 0)
                    flag84 = true;
                else
                    flag84 = false;
                setHasTopUi(flag84);
                parcel1.writeNoException();
                return true;

            case 275: 
                parcel.enforceInterface("android.app.IActivityManager");
                requestActivityRelaunch(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 276: 
                parcel.enforceInterface("android.app.IActivityManager");
                Configuration configuration;
                boolean flag85;
                if(parcel.readInt() != 0)
                    configuration = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    configuration = null;
                flag85 = updateDisplayOverrideConfiguration(configuration, parcel.readInt());
                parcel1.writeNoException();
                if(flag85)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 277: 
                parcel.enforceInterface("android.app.IActivityManager");
                unregisterTaskStackListener(ITaskStackListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 278: 
                parcel.enforceInterface("android.app.IActivityManager");
                moveStackToDisplay(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 279: 
                parcel.enforceInterface("android.app.IActivityManager");
                IResultReceiver iresultreceiver1 = com.android.internal.os.IResultReceiver.Stub.asInterface(parcel.readStrongBinder());
                Bundle bundle4;
                boolean flag86;
                if(parcel.readInt() != 0)
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle4 = null;
                flag86 = requestAutofillData(iresultreceiver1, bundle4, parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                if(flag86)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 280: 
                parcel.enforceInterface("android.app.IActivityManager");
                dismissKeyguard(parcel.readStrongBinder(), com.android.internal.policy.IKeyguardDismissCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 281: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = restartUserInBackground(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 282: 
                parcel.enforceInterface("android.app.IActivityManager");
                cancelTaskWindowTransition(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 283: 
                parcel.enforceInterface("android.app.IActivityManager");
                cancelTaskThumbnailTransition(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 284: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = parcel.readInt();
                boolean flag87;
                if(parcel.readInt() != 0)
                    flag87 = true;
                else
                    flag87 = false;
                parcel = getTaskSnapshot(i, flag87);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 285: 
                parcel.enforceInterface("android.app.IActivityManager");
                scheduleApplicationInfoChanged(parcel.createStringArrayList(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 286: 
                parcel.enforceInterface("android.app.IActivityManager");
                setPersistentVrThread(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 287: 
                parcel.enforceInterface("android.app.IActivityManager");
                waitForNetworkStateUpdate(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 288: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder17 = parcel.readStrongBinder();
                boolean flag88;
                if(parcel.readInt() != 0)
                    flag88 = true;
                else
                    flag88 = false;
                setDisablePreviewScreenshots(ibinder17, flag88);
                parcel1.writeNoException();
                return true;

            case 289: 
                parcel.enforceInterface("android.app.IActivityManager");
                i = getLastResumedActivityUserId();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 290: 
                parcel.enforceInterface("android.app.IActivityManager");
                backgroundWhitelistUid(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 291: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder18 = parcel.readStrongBinder();
                boolean flag89;
                if(parcel.readInt() != 0)
                    flag89 = true;
                else
                    flag89 = false;
                setShowWhenLocked(ibinder18, flag89);
                parcel1.writeNoException();
                return true;

            case 292: 
                parcel.enforceInterface("android.app.IActivityManager");
                IBinder ibinder19 = parcel.readStrongBinder();
                boolean flag90;
                if(parcel.readInt() != 0)
                    flag90 = true;
                else
                    flag90 = false;
                setTurnScreenOn(ibinder19, flag90);
                parcel1.writeNoException();
                return true;

            case 293: 
                parcel.enforceInterface("android.app.IActivityManager");
                registerMiuiAppTransitionAnimationHelper(com.miui.internal.transition.IMiuiAppTransitionAnimationHelper.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 294: 
                parcel.enforceInterface("android.app.IActivityManager");
                unregisterMiuiAppTransitionAnimationHelper();
                parcel1.writeNoException();
                return true;

            case 295: 
                parcel.enforceInterface("android.app.IActivityManager");
                IMiuiActivityObserver imiuiactivityobserver = IMiuiActivityObserver.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                registerActivityObserver(imiuiactivityobserver, parcel);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 296: 
                parcel.enforceInterface("android.app.IActivityManager");
                unregisterActivityObserver(IMiuiActivityObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 297: 
                parcel.enforceInterface("android.app.IActivityManager");
                setResizeWhiteList(parcel.createStringArrayList());
                return true;

            case 298: 
                parcel.enforceInterface("android.app.IActivityManager");
                parcel1 = parcel.readStrongBinder();
                boolean flag91;
                if(parcel.readInt() != 0)
                    flag91 = true;
                else
                    flag91 = false;
                setDummyTranslucent(parcel1, flag91);
                return true;

            case 299: 
                parcel.enforceInterface("android.app.IActivityManager");
                reportKillProcessEvent(parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.IActivityManager";
        static final int TRANSACTION_activityDestroyed = 53;
        static final int TRANSACTION_activityIdle = 12;
        static final int TRANSACTION_activityPaused = 13;
        static final int TRANSACTION_activityRelaunched = 250;
        static final int TRANSACTION_activityResumed = 31;
        static final int TRANSACTION_activitySlept = 117;
        static final int TRANSACTION_activityStopped = 14;
        static final int TRANSACTION_addAppTask = 199;
        static final int TRANSACTION_addInstrumentationResults = 35;
        static final int TRANSACTION_addPackageDependency = 89;
        static final int TRANSACTION_appNotRespondingViaProvider = 176;
        static final int TRANSACTION_attachApplication = 11;
        static final int TRANSACTION_backgroundWhitelistUid = 290;
        static final int TRANSACTION_backupAgentCreated = 85;
        static final int TRANSACTION_bindBackupAgent = 84;
        static final int TRANSACTION_bindService = 28;
        static final int TRANSACTION_bootAnimationComplete = 203;
        static final int TRANSACTION_broadcastIntent = 8;
        static final int TRANSACTION_cancelIntentSender = 55;
        static final int TRANSACTION_cancelTaskThumbnailTransition = 283;
        static final int TRANSACTION_cancelTaskWindowTransition = 282;
        static final int TRANSACTION_checkGrantUriPermission = 113;
        static final int TRANSACTION_checkPermission = 44;
        static final int TRANSACTION_checkPermissionWithToken = 207;
        static final int TRANSACTION_checkUriPermission = 45;
        static final int TRANSACTION_clearApplicationUserData = 71;
        static final int TRANSACTION_clearGrantedUriPermissions = 255;
        static final int TRANSACTION_clearPendingBackup = 154;
        static final int TRANSACTION_closeSystemDialogs = 91;
        static final int TRANSACTION_convertFromTranslucent = 167;
        static final int TRANSACTION_convertToTranslucent = 168;
        static final int TRANSACTION_crashApplication = 108;
        static final int TRANSACTION_createStackOnDisplay = 210;
        static final int TRANSACTION_dismissKeyguard = 280;
        static final int TRANSACTION_dumpHeap = 114;
        static final int TRANSACTION_dumpHeapFinished = 217;
        static final int TRANSACTION_enterPictureInPictureMode = 247;
        static final int TRANSACTION_enterSafeMode = 59;
        static final int TRANSACTION_exitFreeformMode = 236;
        static final int TRANSACTION_finishActivity = 5;
        static final int TRANSACTION_finishActivityAffinity = 142;
        static final int TRANSACTION_finishHeavyWeightApp = 103;
        static final int TRANSACTION_finishInstrumentation = 36;
        static final int TRANSACTION_finishReceiver = 10;
        static final int TRANSACTION_finishSubActivity = 24;
        static final int TRANSACTION_finishVoiceTask = 193;
        static final int TRANSACTION_forceStopPackage = 72;
        static final int TRANSACTION_getActivityClassForToken = 40;
        static final int TRANSACTION_getActivityDisplayId = 178;
        static final int TRANSACTION_getActivityOptions = 189;
        static final int TRANSACTION_getActivityStackId = 235;
        static final int TRANSACTION_getAllStackInfos = 164;
        static final int TRANSACTION_getAppTaskThumbnailSize = 200;
        static final int TRANSACTION_getAppTasks = 190;
        static final int TRANSACTION_getAssistContextExtras = 156;
        static final int TRANSACTION_getCallingActivity = 16;
        static final int TRANSACTION_getCallingPackage = 15;
        static final int TRANSACTION_getConfiguration = 37;
        static final int TRANSACTION_getContentProvider = 21;
        static final int TRANSACTION_getContentProviderExternal = 134;
        static final int TRANSACTION_getCurrentUser = 138;
        static final int TRANSACTION_getDeviceConfigurationInfo = 78;
        static final int TRANSACTION_getFocusedStackId = 211;
        static final int TRANSACTION_getFrontActivityScreenCompatMode = 118;
        static final int TRANSACTION_getGrantedUriPermissions = 254;
        static final int TRANSACTION_getIntentForIntentSender = 155;
        static final int TRANSACTION_getIntentSender = 54;
        static final int TRANSACTION_getLastResumedActivityUserId = 289;
        static final int TRANSACTION_getLaunchedFromPackage = 158;
        static final int TRANSACTION_getLaunchedFromUid = 143;
        static final int TRANSACTION_getLockTaskModeState = 215;
        static final int TRANSACTION_getMaxNumPictureInPictureActions = 249;
        static final int TRANSACTION_getMemoryInfo = 69;
        static final int TRANSACTION_getMemoryTrimLevel = 264;
        static final int TRANSACTION_getMyMemoryState = 136;
        static final int TRANSACTION_getPackageAskScreenCompat = 122;
        static final int TRANSACTION_getPackageForIntentSender = 56;
        static final int TRANSACTION_getPackageForToken = 41;
        static final int TRANSACTION_getPackageProcessState = 222;
        static final int TRANSACTION_getPackageScreenCompatMode = 120;
        static final int TRANSACTION_getPersistedUriPermissions = 175;
        static final int TRANSACTION_getProcessLimit = 43;
        static final int TRANSACTION_getProcessMemoryInfo = 92;
        static final int TRANSACTION_getProcessPss = 131;
        static final int TRANSACTION_getProcessesInErrorState = 70;
        static final int TRANSACTION_getProviderMimeType = 109;
        static final int TRANSACTION_getRecentTasks = 51;
        static final int TRANSACTION_getRequestedOrientation = 64;
        static final int TRANSACTION_getRunningAppProcesses = 77;
        static final int TRANSACTION_getRunningExternalApplications = 102;
        static final int TRANSACTION_getRunningServiceControlPanel = 25;
        static final int TRANSACTION_getRunningUserIds = 150;
        static final int TRANSACTION_getServices = 74;
        static final int TRANSACTION_getStackInfo = 166;
        static final int TRANSACTION_getTagForIntentSender = 180;
        static final int TRANSACTION_getTaskBounds = 177;
        static final int TRANSACTION_getTaskDescription = 76;
        static final int TRANSACTION_getTaskDescriptionIcon = 204;
        static final int TRANSACTION_getTaskForActivity = 20;
        static final int TRANSACTION_getTaskSnapshot = 284;
        static final int TRANSACTION_getTaskThumbnail = 75;
        static final int TRANSACTION_getTasks = 17;
        static final int TRANSACTION_getUidForIntentSender = 87;
        static final int TRANSACTION_getUidProcessState = 226;
        static final int TRANSACTION_getUriPermissionOwnerForActivity = 251;
        static final int TRANSACTION_grantUriPermission = 46;
        static final int TRANSACTION_grantUriPermissionFromOwner = 111;
        static final int TRANSACTION_handleApplicationCrash = 2;
        static final int TRANSACTION_handleApplicationStrictModeViolation = 104;
        static final int TRANSACTION_handleApplicationWtf = 96;
        static final int TRANSACTION_handleIncomingUser = 88;
        static final int TRANSACTION_hang = 161;
        static final int TRANSACTION_inputDispatchingTimedOut = 153;
        static final int TRANSACTION_isAppForeground = 256;
        static final int TRANSACTION_isAppStartModeDisabled = 242;
        static final int TRANSACTION_isAssistDataAllowedOnCurrentActivity = 229;
        static final int TRANSACTION_isImmersive = 105;
        static final int TRANSACTION_isInLockTaskMode = 185;
        static final int TRANSACTION_isInMultiWindowMode = 244;
        static final int TRANSACTION_isInPictureInPictureMode = 245;
        static final int TRANSACTION_isIntentSenderAnActivity = 145;
        static final int TRANSACTION_isIntentSenderTargetedToPackage = 129;
        static final int TRANSACTION_isRootVoiceInteraction = 231;
        static final int TRANSACTION_isTopActivityImmersive = 107;
        static final int TRANSACTION_isTopOfTask = 194;
        static final int TRANSACTION_isUserAMonkey = 98;
        static final int TRANSACTION_isUserRunning = 116;
        static final int TRANSACTION_isVrModePackageEnabled = 266;
        static final int TRANSACTION_keyguardGoingAway = 225;
        static final int TRANSACTION_killAllBackgroundProcesses = 133;
        static final int TRANSACTION_killApplication = 90;
        static final int TRANSACTION_killApplicationProcess = 93;
        static final int TRANSACTION_killBackgroundProcesses = 97;
        static final int TRANSACTION_killPackageDependents = 246;
        static final int TRANSACTION_killPids = 73;
        static final int TRANSACTION_killProcessesBelowForeground = 137;
        static final int TRANSACTION_killUid = 159;
        static final int TRANSACTION_launchAssistIntent = 205;
        static final int TRANSACTION_makePackageIdle = 263;
        static final int TRANSACTION_moveActivityTaskToBack = 68;
        static final int TRANSACTION_moveStackToDisplay = 278;
        static final int TRANSACTION_moveTaskBackwards = 19;
        static final int TRANSACTION_moveTaskToDockedStack = 238;
        static final int TRANSACTION_moveTaskToFront = 18;
        static final int TRANSACTION_moveTaskToStack = 162;
        static final int TRANSACTION_moveTasksToFullscreenStack = 240;
        static final int TRANSACTION_moveTopActivityToPinnedStack = 241;
        static final int TRANSACTION_navigateUpTo = 140;
        static final int TRANSACTION_newUriPermissionOwner = 110;
        static final int TRANSACTION_noteAlarmFinish = 221;
        static final int TRANSACTION_noteAlarmStart = 220;
        static final int TRANSACTION_noteWakeupAlarm = 61;
        static final int TRANSACTION_notifyActivityDrawn = 169;
        static final int TRANSACTION_notifyCleartextNetwork = 209;
        static final int TRANSACTION_notifyEnterAnimationComplete = 197;
        static final int TRANSACTION_notifyLaunchTaskBehindComplete = 195;
        static final int TRANSACTION_notifyLockedProfile = 268;
        static final int TRANSACTION_notifyPinnedStackAnimationEnded = 261;
        static final int TRANSACTION_notifyPinnedStackAnimationStarted = 260;
        static final int TRANSACTION_openContentUri = 1;
        static final int TRANSACTION_overridePendingTransition = 95;
        static final int TRANSACTION_peekService = 79;
        static final int TRANSACTION_performIdleMaintenance = 172;
        static final int TRANSACTION_positionTaskInStack = 234;
        static final int TRANSACTION_profileControl = 80;
        static final int TRANSACTION_publishContentProviders = 22;
        static final int TRANSACTION_publishService = 30;
        static final int TRANSACTION_refContentProvider = 23;
        static final int TRANSACTION_registerActivityObserver = 295;
        static final int TRANSACTION_registerIntentSenderCancelListener = 57;
        static final int TRANSACTION_registerMiuiAppTransitionAnimationHelper = 293;
        static final int TRANSACTION_registerProcessObserver = 127;
        static final int TRANSACTION_registerReceiver = 6;
        static final int TRANSACTION_registerTaskStackListener = 208;
        static final int TRANSACTION_registerUidObserver = 227;
        static final int TRANSACTION_registerUserSwitchObserver = 148;
        static final int TRANSACTION_releaseActivityInstance = 201;
        static final int TRANSACTION_releasePersistableUriPermission = 174;
        static final int TRANSACTION_releaseSomeActivities = 202;
        static final int TRANSACTION_removeContentProvider = 62;
        static final int TRANSACTION_removeContentProviderExternal = 135;
        static final int TRANSACTION_removeStack = 262;
        static final int TRANSACTION_removeTask = 126;
        static final int TRANSACTION_reportActivityFullyDrawn = 170;
        static final int TRANSACTION_reportAssistContextExtras = 157;
        static final int TRANSACTION_reportKillProcessEvent = 299;
        static final int TRANSACTION_reportSizeConfigurations = 237;
        static final int TRANSACTION_requestActivityRelaunch = 275;
        static final int TRANSACTION_requestAssistContextExtras = 213;
        static final int TRANSACTION_requestAutofillData = 279;
        static final int TRANSACTION_requestBugReport = 151;
        static final int TRANSACTION_requestTelephonyBugReport = 152;
        static final int TRANSACTION_resizeDockedStack = 252;
        static final int TRANSACTION_resizePinnedStack = 265;
        static final int TRANSACTION_resizeStack = 163;
        static final int TRANSACTION_resizeTask = 214;
        static final int TRANSACTION_restart = 171;
        static final int TRANSACTION_restartUserInBackground = 281;
        static final int TRANSACTION_resumeAppSwitches = 83;
        static final int TRANSACTION_revokeUriPermission = 47;
        static final int TRANSACTION_revokeUriPermissionFromOwner = 112;
        static final int TRANSACTION_scheduleApplicationInfoChanged = 285;
        static final int TRANSACTION_sendIdleJobTrigger = 270;
        static final int TRANSACTION_sendIntentSender = 271;
        static final int TRANSACTION_serviceDoneExecuting = 52;
        static final int TRANSACTION_setActivityController = 48;
        static final int TRANSACTION_setAlwaysFinish = 33;
        static final int TRANSACTION_setDebugApp = 32;
        static final int TRANSACTION_setDisablePreviewScreenshots = 288;
        static final int TRANSACTION_setDummyTranslucent = 298;
        static final int TRANSACTION_setDumpHeapDebugLimit = 216;
        static final int TRANSACTION_setFocusedStack = 165;
        static final int TRANSACTION_setFocusedTask = 125;
        static final int TRANSACTION_setFrontActivityScreenCompatMode = 119;
        static final int TRANSACTION_setHasTopUi = 274;
        static final int TRANSACTION_setImmersive = 106;
        static final int TRANSACTION_setLockScreenShown = 141;
        static final int TRANSACTION_setPackageAskScreenCompat = 123;
        static final int TRANSACTION_setPackageScreenCompatMode = 121;
        static final int TRANSACTION_setPersistentVrThread = 286;
        static final int TRANSACTION_setPictureInPictureParams = 248;
        static final int TRANSACTION_setProcessImportant = 66;
        static final int TRANSACTION_setProcessLimit = 42;
        static final int TRANSACTION_setProcessMemoryTrimLevel = 179;
        static final int TRANSACTION_setRenderThread = 273;
        static final int TRANSACTION_setRequestedOrientation = 63;
        static final int TRANSACTION_setResizeWhiteList = 297;
        static final int TRANSACTION_setServiceForeground = 67;
        static final int TRANSACTION_setShowWhenLocked = 291;
        static final int TRANSACTION_setTaskDescription = 186;
        static final int TRANSACTION_setTaskResizeable = 212;
        static final int TRANSACTION_setTurnScreenOn = 292;
        static final int TRANSACTION_setUserIsMonkey = 160;
        static final int TRANSACTION_setVoiceKeepAwake = 218;
        static final int TRANSACTION_setVrMode = 253;
        static final int TRANSACTION_setVrThread = 272;
        static final int TRANSACTION_shouldUpRecreateTask = 139;
        static final int TRANSACTION_showAssistFromActivity = 230;
        static final int TRANSACTION_showBootMessage = 132;
        static final int TRANSACTION_showLockTaskEscapeMessage = 223;
        static final int TRANSACTION_showWaitingForDebugger = 49;
        static final int TRANSACTION_shutdown = 81;
        static final int TRANSACTION_signalPersistentProcesses = 50;
        static final int TRANSACTION_startActivities = 115;
        static final int TRANSACTION_startActivity = 3;
        static final int TRANSACTION_startActivityAndWait = 99;
        static final int TRANSACTION_startActivityAsCaller = 198;
        static final int TRANSACTION_startActivityAsUser = 146;
        static final int TRANSACTION_startActivityFromRecents = 196;
        static final int TRANSACTION_startActivityIntentSender = 94;
        static final int TRANSACTION_startActivityWithConfig = 101;
        static final int TRANSACTION_startAssistantActivity = 188;
        static final int TRANSACTION_startBinderTracking = 232;
        static final int TRANSACTION_startConfirmDeviceCredentialIntent = 269;
        static final int TRANSACTION_startInPlaceAnimationOnFrontMostApplication = 206;
        static final int TRANSACTION_startInstrumentation = 34;
        static final int TRANSACTION_startLocalVoiceInteraction = 257;
        static final int TRANSACTION_startLockTaskModeById = 182;
        static final int TRANSACTION_startLockTaskModeByToken = 183;
        static final int TRANSACTION_startNextMatchingActivity = 60;
        static final int TRANSACTION_startService = 26;
        static final int TRANSACTION_startSystemLockTaskMode = 191;
        static final int TRANSACTION_startUserInBackground = 181;
        static final int TRANSACTION_startVoiceActivity = 187;
        static final int TRANSACTION_stopAppSwitches = 82;
        static final int TRANSACTION_stopBinderTrackingAndDump = 233;
        static final int TRANSACTION_stopLocalVoiceInteraction = 258;
        static final int TRANSACTION_stopLockTaskMode = 184;
        static final int TRANSACTION_stopService = 27;
        static final int TRANSACTION_stopServiceToken = 39;
        static final int TRANSACTION_stopSystemLockTaskMode = 192;
        static final int TRANSACTION_stopUser = 147;
        static final int TRANSACTION_supportsLocalVoiceInteraction = 259;
        static final int TRANSACTION_suppressResizeConfigChanges = 239;
        static final int TRANSACTION_swapDockedAndFullscreenStack = 267;
        static final int TRANSACTION_switchUser = 124;
        static final int TRANSACTION_takePersistableUriPermission = 173;
        static final int TRANSACTION_unbindBackupAgent = 86;
        static final int TRANSACTION_unbindFinished = 65;
        static final int TRANSACTION_unbindService = 29;
        static final int TRANSACTION_unbroadcastIntent = 9;
        static final int TRANSACTION_unhandledBack = 4;
        static final int TRANSACTION_unlockUser = 243;
        static final int TRANSACTION_unregisterActivityObserver = 296;
        static final int TRANSACTION_unregisterIntentSenderCancelListener = 58;
        static final int TRANSACTION_unregisterMiuiAppTransitionAnimationHelper = 294;
        static final int TRANSACTION_unregisterProcessObserver = 128;
        static final int TRANSACTION_unregisterReceiver = 7;
        static final int TRANSACTION_unregisterTaskStackListener = 277;
        static final int TRANSACTION_unregisterUidObserver = 228;
        static final int TRANSACTION_unregisterUserSwitchObserver = 149;
        static final int TRANSACTION_unstableProviderDied = 144;
        static final int TRANSACTION_updateConfiguration = 38;
        static final int TRANSACTION_updateDeviceOwner = 224;
        static final int TRANSACTION_updateDisplayOverrideConfiguration = 276;
        static final int TRANSACTION_updateLockTaskPackages = 219;
        static final int TRANSACTION_updatePersistentConfiguration = 130;
        static final int TRANSACTION_waitForNetworkStateUpdate = 287;
        static final int TRANSACTION_willActivityBeVisible = 100;

        public Stub()
        {
            attachInterface(this, "android.app.IActivityManager");
        }
    }

    private static class Stub.Proxy
        implements IActivityManager
    {

        public void activityDestroyed(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(53, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void activityIdle(IBinder ibinder, Configuration configuration, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(configuration == null)
                break MISSING_BLOCK_LABEL_71;
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void activityPaused(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void activityRelaunched(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(250, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void activityResumed(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void activitySlept(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(117, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void activityStopped(IBinder ibinder, Bundle bundle, PersistableBundle persistablebundle, CharSequence charsequence)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L5:
            if(persistablebundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            persistablebundle.writeToParcel(parcel, 0);
_L6:
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_119;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L7:
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            ibinder;
            parcel.recycle();
            throw ibinder;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public int addAppTask(IBinder ibinder, Intent intent, ActivityManager.TaskDescription taskdescription, Bitmap bitmap)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L5:
            if(taskdescription == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            taskdescription.writeToParcel(parcel, 0);
_L6:
            if(bitmap == null)
                break MISSING_BLOCK_LABEL_150;
            parcel.writeInt(1);
            bitmap.writeToParcel(parcel, 0);
_L7:
            int i;
            mRemote.transact(199, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L5
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void addInstrumentationResults(IApplicationThread iapplicationthread, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
        }

        public void addPackageDependency(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            mRemote.transact(89, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void appNotRespondingViaProvider(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(176, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void attachApplication(IApplicationThread iapplicationthread)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
        }

        public void backgroundWhitelistUid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(290, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void backupAgentCreated(String s, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(85, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean bindBackupAgent(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(84, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int bindService(IApplicationThread iapplicationthread, IBinder ibinder, Intent intent, String s, IServiceConnection iserviceconnection, int i, String s1, 
                int j)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null) goto _L2; else goto _L1
_L1:
            iapplicationthread = iapplicationthread.asBinder();
_L5:
            parcel.writeStrongBinder(iapplicationthread);
            parcel.writeStrongBinder(ibinder);
            if(intent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L6:
            parcel.writeString(s);
            iapplicationthread = obj;
            if(iserviceconnection == null)
                break MISSING_BLOCK_LABEL_83;
            iapplicationthread = iserviceconnection.asBinder();
            parcel.writeStrongBinder(iapplicationthread);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeInt(j);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            iapplicationthread = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
              goto _L5
        }

        public void bootAnimationComplete()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(203, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int broadcastIntent(IApplicationThread iapplicationthread, Intent intent, String s, IIntentReceiver iintentreceiver, int i, String s1, Bundle bundle, 
                String as[], int j, Bundle bundle1, boolean flag, boolean flag1, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null) goto _L2; else goto _L1
_L1:
            iapplicationthread = iapplicationthread.asBinder();
_L9:
            parcel.writeStrongBinder(iapplicationthread);
            if(intent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L10:
            parcel.writeString(s);
            if(iintentreceiver == null) goto _L6; else goto _L5
_L5:
            iapplicationthread = iintentreceiver.asBinder();
_L11:
            parcel.writeStrongBinder(iapplicationthread);
            parcel.writeInt(i);
            parcel.writeString(s1);
            if(bundle == null) goto _L8; else goto _L7
_L7:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L12:
            parcel.writeStringArray(as);
            parcel.writeInt(j);
            if(bundle1 == null)
                break MISSING_BLOCK_LABEL_262;
            parcel.writeInt(1);
            bundle1.writeToParcel(parcel, 0);
_L13:
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
            parcel.writeInt(k);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            iapplicationthread = null;
              goto _L9
_L4:
            parcel.writeInt(0);
              goto _L10
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
_L6:
            iapplicationthread = null;
              goto _L11
_L8:
            parcel.writeInt(0);
              goto _L12
            parcel.writeInt(0);
              goto _L13
        }

        public void cancelIntentSender(IIntentSender iintentsender)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iintentsender.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(55, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
        }

        public void cancelTaskThumbnailTransition(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(283, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void cancelTaskWindowTransition(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(282, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int checkGrantUriPermission(int i, String s, Uri uri, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(uri == null)
                break MISSING_BLOCK_LABEL_100;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(113, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int checkPermission(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(44, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int checkPermissionWithToken(String s, int i, int j, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(207, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int checkUriPermission(Uri uri, int i, int j, int k, int l, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(uri == null)
                break MISSING_BLOCK_LABEL_107;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
        }

        public boolean clearApplicationUserData(String s, IPackageDataObserver ipackagedataobserver, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            s = obj;
            if(ipackagedataobserver == null)
                break MISSING_BLOCK_LABEL_40;
            s = ipackagedataobserver.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeInt(i);
            mRemote.transact(71, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearGrantedUriPermissions(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(255, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearPendingBackup()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(154, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void closeSystemDialogs(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            mRemote.transact(91, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean convertFromTranslucent(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(167, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean convertToTranslucent(IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_84;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(168, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void crashApplication(int i, int j, String s, int k, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeInt(k);
            parcel.writeString(s1);
            mRemote.transact(108, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int createStackOnDisplay(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(210, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void dismissKeyguard(IBinder ibinder, IKeyguardDismissCallback ikeyguarddismisscallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            ibinder = obj;
            if(ikeyguarddismisscallback == null)
                break MISSING_BLOCK_LABEL_38;
            ibinder = ikeyguarddismisscallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(280, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean dumpHeap(String s, int i, boolean flag, boolean flag1, boolean flag2, String s1, ParcelFileDescriptor parcelfiledescriptor)
            throws RemoteException
        {
            boolean flag3;
            Parcel parcel;
            Parcel parcel1;
            flag3 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
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
                i = ((flag3) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s1);
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_158;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(114, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void dumpHeapFinished(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            mRemote.transact(217, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean enterPictureInPictureMode(IBinder ibinder, PictureInPictureParams pictureinpictureparams)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(pictureinpictureparams == null)
                break MISSING_BLOCK_LABEL_84;
            parcel.writeInt(1);
            pictureinpictureparams.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(247, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void enterSafeMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(59, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void exitFreeformMode(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(236, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean finishActivity(IBinder ibinder, int i, Intent intent, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(intent == null)
                break MISSING_BLOCK_LABEL_100;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean finishActivityAffinity(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(142, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void finishHeavyWeightApp()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(103, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void finishInstrumentation(IApplicationThread iapplicationthread, int i, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_95;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
        }

        public void finishReceiver(IBinder ibinder, int i, String s, Bundle bundle, boolean flag, int j)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_95;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void finishSubActivity(IBinder ibinder, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void finishVoiceTask(IVoiceInteractionSession ivoiceinteractionsession)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(ivoiceinteractionsession == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ivoiceinteractionsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(193, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ivoiceinteractionsession;
            parcel1.recycle();
            parcel.recycle();
            throw ivoiceinteractionsession;
        }

        public void forceStopPackage(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(72, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ComponentName getActivityClassForToken(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ibinder = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
_L2:
            ibinder = null;
            if(true) goto _L4; else goto _L3
_L3:
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int getActivityDisplayId(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(178, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public Bundle getActivityOptions(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(189, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ibinder = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
_L2:
            ibinder = null;
            if(true) goto _L4; else goto _L3
_L3:
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int getActivityStackId(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(235, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public List getAllStackInfos()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(164, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(ActivityManager.StackInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Point getAppTaskThumbnailSize()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(200, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Point point = (Point)Point.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return point;
_L2:
            point = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getAppTasks(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            mRemote.transact(190, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createBinderArrayList();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public Bundle getAssistContextExtras(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(156, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return bundle;
_L2:
            bundle = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ComponentName getCallingActivity(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ibinder = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
_L2:
            ibinder = null;
            if(true) goto _L4; else goto _L3
_L3:
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public String getCallingPackage(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public Configuration getConfiguration()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Configuration configuration = (Configuration)Configuration.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return configuration;
_L2:
            configuration = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ContentProviderHolder getContentProvider(IApplicationThread iapplicationthread, String s, int i, boolean flag)
            throws RemoteException
        {
            IBinder ibinder;
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            iapplicationthread = (ContentProviderHolder)ContentProviderHolder.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return iapplicationthread;
_L2:
            iapplicationthread = null;
            if(true) goto _L4; else goto _L3
_L3:
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
        }

        public ContentProviderHolder getContentProviderExternal(String s, int i, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(134, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ContentProviderHolder)ContentProviderHolder.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public UserInfo getCurrentUser()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(138, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            UserInfo userinfo = (UserInfo)UserInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return userinfo;
_L2:
            userinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ConfigurationInfo getDeviceConfigurationInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(78, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ConfigurationInfo configurationinfo = (ConfigurationInfo)ConfigurationInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return configurationinfo;
_L2:
            configurationinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getFocusedStackId()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(211, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getFrontActivityScreenCompatMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(118, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParceledListSlice getGrantedUriPermissions(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(254, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public Intent getIntentForIntentSender(IIntentSender iintentsender)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iintentsender.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(155, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            iintentsender = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return iintentsender;
_L2:
            iintentsender = null;
            if(true) goto _L4; else goto _L3
_L3:
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
        }

        public IIntentSender getIntentSender(int i, String s, IBinder ibinder, String s1, int j, Intent aintent[], String as[], 
                int k, Bundle bundle, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(j);
            parcel.writeTypedArray(aintent, 0);
            parcel.writeStringArray(as);
            parcel.writeInt(k);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_140;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(l);
            mRemote.transact(54, parcel, parcel1, 0);
            parcel1.readException();
            s = android.content.IIntentSender.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IActivityManager";
        }

        public int getLastResumedActivityUserId()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(289, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getLaunchedFromPackage(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(158, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int getLaunchedFromUid(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(143, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int getLockTaskModeState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(215, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getMaxNumPictureInPictureActions(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(249, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void getMemoryInfo(ActivityManager.MemoryInfo memoryinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(69, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() != 0)
                memoryinfo.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return;
            memoryinfo;
            parcel1.recycle();
            parcel.recycle();
            throw memoryinfo;
        }

        public int getMemoryTrimLevel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(264, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void getMyMemoryState(ActivityManager.RunningAppProcessInfo runningappprocessinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(136, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() != 0)
                runningappprocessinfo.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return;
            runningappprocessinfo;
            parcel1.recycle();
            parcel.recycle();
            throw runningappprocessinfo;
        }

        public boolean getPackageAskScreenCompat(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            mRemote.transact(122, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getPackageForIntentSender(IIntentSender iintentsender)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iintentsender.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(56, parcel, parcel1, 0);
            parcel1.readException();
            iintentsender = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return iintentsender;
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
        }

        public String getPackageForToken(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int getPackageProcessState(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(222, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getPackageScreenCompatMode(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            mRemote.transact(120, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParceledListSlice getPersistedUriPermissions(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(175, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getProcessLimit()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(43, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public android.os.Debug.MemoryInfo[] getProcessMemoryInfo(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeIntArray(ai);
            mRemote.transact(92, parcel, parcel1, 0);
            parcel1.readException();
            ai = (android.os.Debug.MemoryInfo[])parcel1.createTypedArray(android.os.Debug.MemoryInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return ai;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public long[] getProcessPss(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeIntArray(ai);
            mRemote.transact(131, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createLongArray();
            parcel1.recycle();
            parcel.recycle();
            return ai;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public List getProcessesInErrorState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(70, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(ActivityManager.ProcessErrorStateInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getProviderMimeType(Uri uri, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(uri == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(109, parcel, parcel1, 0);
            parcel1.readException();
            uri = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return uri;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
        }

        public ParceledListSlice getRecentTasks(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParceledListSlice parceledlistslice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parceledlistslice;
_L2:
            parceledlistslice = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getRequestedOrientation(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(64, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public List getRunningAppProcesses()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(77, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(ActivityManager.RunningAppProcessInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getRunningExternalApplications()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(102, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(ApplicationInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public PendingIntent getRunningServiceControlPanel(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            componentname = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public int[] getRunningUserIds()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(150, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return ai;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getServices(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(74, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(ActivityManager.RunningServiceInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ActivityManager.StackInfo getStackInfo(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(166, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ActivityManager.StackInfo stackinfo = (ActivityManager.StackInfo)ActivityManager.StackInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return stackinfo;
_L2:
            stackinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getTagForIntentSender(IIntentSender iintentsender, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iintentsender.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(180, parcel, parcel1, 0);
            parcel1.readException();
            iintentsender = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return iintentsender;
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
        }

        public Rect getTaskBounds(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(177, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Rect rect = (Rect)Rect.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return rect;
_L2:
            rect = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ActivityManager.TaskDescription getTaskDescription(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(76, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ActivityManager.TaskDescription taskdescription = (ActivityManager.TaskDescription)ActivityManager.TaskDescription.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return taskdescription;
_L2:
            taskdescription = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Bitmap getTaskDescriptionIcon(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(204, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getTaskForActivity(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public ActivityManager.TaskSnapshot getTaskSnapshot(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(284, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ActivityManager.TaskSnapshot tasksnapshot = (ActivityManager.TaskSnapshot)ActivityManager.TaskSnapshot.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return tasksnapshot;
_L2:
            tasksnapshot = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ActivityManager.TaskThumbnail getTaskThumbnail(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(75, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ActivityManager.TaskThumbnail taskthumbnail = (ActivityManager.TaskThumbnail)ActivityManager.TaskThumbnail.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return taskthumbnail;
_L2:
            taskthumbnail = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getTasks(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getUidForIntentSender(IIntentSender iintentsender)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iintentsender.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(87, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
        }

        public int getUidProcessState(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(226, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder getUriPermissionOwnerForActivity(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(251, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = parcel1.readStrongBinder();
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void grantUriPermission(IApplicationThread iapplicationthread, String s, Uri uri, int i, int j)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(uri == null)
                break MISSING_BLOCK_LABEL_109;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(46, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
        }

        public void grantUriPermissionFromOwner(IBinder ibinder, int i, String s, Uri uri, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            if(uri == null)
                break MISSING_BLOCK_LABEL_108;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(111, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void handleApplicationCrash(IBinder ibinder, ApplicationErrorReport.ParcelableCrashInfo parcelablecrashinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(parcelablecrashinfo == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            parcelablecrashinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void handleApplicationStrictModeViolation(IBinder ibinder, int i, android.os.StrictMode.ViolationInfo violationinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(violationinfo == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            violationinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(104, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean handleApplicationWtf(IBinder ibinder, String s, boolean flag, ApplicationErrorReport.ParcelableCrashInfo parcelablecrashinfo)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(parcelablecrashinfo == null)
                break MISSING_BLOCK_LABEL_116;
            parcel.writeInt(1);
            parcelablecrashinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(96, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int handleIncomingUser(int i, int j, int k, boolean flag, boolean flag1, String s, String s1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
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
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(88, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void hang(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(161, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public long inputDispatchingTimedOut(int i, boolean flag, String s)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            long l;
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(153, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isAppForeground(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(256, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isAppStartModeDisabled(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(242, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isAssistDataAllowedOnCurrentActivity()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(229, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isImmersive(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(105, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean isInLockTaskMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(185, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isInMultiWindowMode(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(244, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean isInPictureInPictureMode(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(245, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean isIntentSenderAnActivity(IIntentSender iintentsender)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iintentsender.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(145, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
        }

        public boolean isIntentSenderTargetedToPackage(IIntentSender iintentsender)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iintentsender.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(129, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
        }

        public boolean isRootVoiceInteraction(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(231, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean isTopActivityImmersive()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(107, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isTopOfTask(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(194, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean isUserAMonkey()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(98, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isUserRunning(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(116, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isVrModePackageEnabled(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(266, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void keyguardGoingAway(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(225, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void killAllBackgroundProcesses()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(133, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void killApplication(String s, int i, int j, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s1);
            mRemote.transact(90, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void killApplicationProcess(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(93, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void killBackgroundProcesses(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(97, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void killPackageDependents(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(246, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean killPids(int ai[], String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeIntArray(ai);
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(73, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public boolean killProcessesBelowForeground(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            mRemote.transact(137, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void killUid(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(159, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean launchAssistIntent(Intent intent, int i, String s, int j, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_143;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(205, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
            parcel.writeInt(0);
              goto _L4
        }

        public void makePackageIdle(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(263, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean moveActivityTaskToBack(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(68, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void moveStackToDisplay(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(278, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void moveTaskBackwards(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean moveTaskToDockedStack(int i, int j, boolean flag, boolean flag1, Rect rect)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
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
            if(rect == null)
                break MISSING_BLOCK_LABEL_134;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(238, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            rect;
            parcel1.recycle();
            parcel.recycle();
            throw rect;
        }

        public void moveTaskToFront(int i, int j, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel1.recycle();
            parcel.recycle();
            throw bundle;
        }

        public void moveTaskToStack(int i, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(162, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void moveTasksToFullscreenStack(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(240, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean moveTopActivityToPinnedStack(int i, Rect rect)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            if(rect == null)
                break MISSING_BLOCK_LABEL_82;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(241, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            rect;
            parcel1.recycle();
            parcel.recycle();
            throw rect;
        }

        public boolean navigateUpTo(IBinder ibinder, Intent intent, int i, Intent intent1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(intent1 == null)
                break MISSING_BLOCK_LABEL_136;
            parcel.writeInt(1);
            intent1.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(140, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
_L2:
            parcel.writeInt(0);
              goto _L3
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
            parcel.writeInt(0);
              goto _L4
        }

        public IBinder newUriPermissionOwner(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            mRemote.transact(110, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readStrongBinder();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void noteAlarmFinish(IIntentSender iintentsender, int i, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iintentsender.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(221, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
        }

        public void noteAlarmStart(IIntentSender iintentsender, int i, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iintentsender.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(220, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
        }

        public void noteWakeupAlarm(IIntentSender iintentsender, int i, String s, String s1)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iintentsender.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(61, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
        }

        public void notifyActivityDrawn(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(169, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void notifyCleartextNetwork(int i, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(209, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void notifyEnterAnimationComplete(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(197, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void notifyLaunchTaskBehindComplete(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(195, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void notifyLockedProfile(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(268, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void notifyPinnedStackAnimationEnded()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(261, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void notifyPinnedStackAnimationStarted()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(260, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParcelFileDescriptor openContentUri(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void overridePendingTransition(IBinder ibinder, String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(95, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public IBinder peekService(Intent intent, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(intent == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(79, parcel, parcel1, 0);
            parcel1.readException();
            intent = parcel1.readStrongBinder();
            parcel1.recycle();
            parcel.recycle();
            return intent;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
        }

        public void performIdleMaintenance()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(172, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void positionTaskInStack(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(234, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean profileControl(String s, int i, boolean flag, ProfilerInfo profilerinfo, int j)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            if(profilerinfo == null)
                break MISSING_BLOCK_LABEL_122;
            parcel.writeInt(1);
            profilerinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            mRemote.transact(80, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void publishContentProviders(IApplicationThread iapplicationthread, List list)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeTypedList(list);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
        }

        public void publishService(IBinder ibinder, Intent intent, IBinder ibinder1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(intent == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder1);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean refContentProvider(IBinder ibinder, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void registerActivityObserver(IMiuiActivityObserver imiuiactivityobserver, Intent intent)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(imiuiactivityobserver == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = imiuiactivityobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(intent == null)
                break MISSING_BLOCK_LABEL_101;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(295, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() != 0)
                intent.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            imiuiactivityobserver;
            parcel1.recycle();
            parcel.recycle();
            throw imiuiactivityobserver;
        }

        public void registerIntentSenderCancelListener(IIntentSender iintentsender, IResultReceiver iresultreceiver)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null)
                break MISSING_BLOCK_LABEL_88;
            iintentsender = iintentsender.asBinder();
_L1:
            parcel.writeStrongBinder(iintentsender);
            iintentsender = obj;
            if(iresultreceiver == null)
                break MISSING_BLOCK_LABEL_49;
            iintentsender = iresultreceiver.asBinder();
            parcel.writeStrongBinder(iintentsender);
            mRemote.transact(57, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iintentsender = null;
              goto _L1
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
        }

        public void registerMiuiAppTransitionAnimationHelper(IMiuiAppTransitionAnimationHelper imiuiapptransitionanimationhelper)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(imiuiapptransitionanimationhelper == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imiuiapptransitionanimationhelper.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(293, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imiuiapptransitionanimationhelper;
            parcel1.recycle();
            parcel.recycle();
            throw imiuiapptransitionanimationhelper;
        }

        public void registerProcessObserver(IProcessObserver iprocessobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iprocessobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iprocessobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(127, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iprocessobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iprocessobserver;
        }

        public Intent registerReceiver(IApplicationThread iapplicationthread, String s, IIntentReceiver iintentreceiver, IntentFilter intentfilter, String s1, int i, int j)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null) goto _L2; else goto _L1
_L1:
            iapplicationthread = iapplicationthread.asBinder();
_L5:
            parcel.writeStrongBinder(iapplicationthread);
            parcel.writeString(s);
            iapplicationthread = obj;
            if(iintentreceiver == null)
                break MISSING_BLOCK_LABEL_57;
            iapplicationthread = iintentreceiver.asBinder();
            parcel.writeStrongBinder(iapplicationthread);
            if(intentfilter == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            intentfilter.writeToParcel(parcel, 0);
_L6:
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_186;
            iapplicationthread = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return iapplicationthread;
_L2:
            iapplicationthread = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
            iapplicationthread = null;
              goto _L7
        }

        public void registerTaskStackListener(ITaskStackListener itaskstacklistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(itaskstacklistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = itaskstacklistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(208, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            itaskstacklistener;
            parcel1.recycle();
            parcel.recycle();
            throw itaskstacklistener;
        }

        public void registerUidObserver(IUidObserver iuidobserver, int i, int j, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iuidobserver == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iuidobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(227, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iuidobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iuidobserver;
        }

        public void registerUserSwitchObserver(IUserSwitchObserver iuserswitchobserver, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iuserswitchobserver == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iuserswitchobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(148, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iuserswitchobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iuserswitchobserver;
        }

        public boolean releaseActivityInstance(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(201, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void releasePersistableUriPermission(Uri uri, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(uri == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(174, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
        }

        public void releaseSomeActivities(IApplicationThread iapplicationthread)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(202, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
        }

        public void removeContentProvider(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(62, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void removeContentProviderExternal(String s, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(135, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removeStack(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(262, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean removeTask(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(126, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void reportActivityFullyDrawn(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(170, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void reportAssistContextExtras(IBinder ibinder, Bundle bundle, AssistStructure assiststructure, AssistContent assistcontent, Uri uri)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L7:
            if(assiststructure == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            assiststructure.writeToParcel(parcel, 0);
_L8:
            if(assistcontent == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            assistcontent.writeToParcel(parcel, 0);
_L9:
            if(uri == null)
                break MISSING_BLOCK_LABEL_169;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L10:
            mRemote.transact(157, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L7
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public void reportKillProcessEvent(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(299, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void reportSizeConfigurations(IBinder ibinder, int ai[], int ai1[], int ai2[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeIntArray(ai);
            parcel.writeIntArray(ai1);
            parcel.writeIntArray(ai2);
            mRemote.transact(237, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void requestActivityRelaunch(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(275, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean requestAssistContextExtras(int i, IResultReceiver iresultreceiver, Bundle bundle, IBinder ibinder, boolean flag, boolean flag1)
            throws RemoteException
        {
            IBinder ibinder1;
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            ibinder1 = null;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            if(iresultreceiver == null)
                break MISSING_BLOCK_LABEL_41;
            ibinder1 = iresultreceiver.asBinder();
            parcel.writeStrongBinder(ibinder1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_148;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder);
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
            mRemote.transact(213, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            iresultreceiver;
            parcel1.recycle();
            parcel.recycle();
            throw iresultreceiver;
        }

        public boolean requestAutofillData(IResultReceiver iresultreceiver, Bundle bundle, IBinder ibinder, int i)
            throws RemoteException
        {
            IBinder ibinder1;
            Parcel parcel;
            Parcel parcel1;
            ibinder1 = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iresultreceiver == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder1 = iresultreceiver.asBinder();
            parcel.writeStrongBinder(ibinder1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_120;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(279, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            iresultreceiver;
            parcel1.recycle();
            parcel.recycle();
            throw iresultreceiver;
        }

        public void requestBugReport(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(151, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void requestTelephonyBugReport(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(152, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void resizeDockedStack(Rect rect, Rect rect1, Rect rect2, Rect rect3, Rect rect4)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(rect == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L9:
            if(rect1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            rect1.writeToParcel(parcel, 0);
_L10:
            if(rect2 == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            rect2.writeToParcel(parcel, 0);
_L11:
            if(rect3 == null) goto _L8; else goto _L7
_L7:
            parcel.writeInt(1);
            rect3.writeToParcel(parcel, 0);
_L12:
            if(rect4 == null)
                break MISSING_BLOCK_LABEL_189;
            parcel.writeInt(1);
            rect4.writeToParcel(parcel, 0);
_L13:
            mRemote.transact(252, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L9
            rect;
            parcel1.recycle();
            parcel.recycle();
            throw rect;
_L4:
            parcel.writeInt(0);
              goto _L10
_L6:
            parcel.writeInt(0);
              goto _L11
_L8:
            parcel.writeInt(0);
              goto _L12
            parcel.writeInt(0);
              goto _L13
        }

        public void resizePinnedStack(Rect rect, Rect rect1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(rect == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L3:
            if(rect1 == null)
                break MISSING_BLOCK_LABEL_97;
            parcel.writeInt(1);
            rect1.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(265, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            rect;
            parcel1.recycle();
            parcel.recycle();
            throw rect;
            parcel.writeInt(0);
              goto _L4
        }

        public void resizeStack(int i, Rect rect, boolean flag, boolean flag1, boolean flag2, int j)
            throws RemoteException
        {
            boolean flag3;
            Parcel parcel;
            Parcel parcel1;
            flag3 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            if(rect == null)
                break MISSING_BLOCK_LABEL_123;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
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
            parcel.writeInt(j);
            mRemote.transact(163, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            rect;
            parcel1.recycle();
            parcel.recycle();
            throw rect;
        }

        public void resizeTask(int i, Rect rect, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            if(rect == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            mRemote.transact(214, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            rect;
            parcel1.recycle();
            parcel.recycle();
            throw rect;
        }

        public void restart()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(171, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int restartUserInBackground(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(281, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void resumeAppSwitches()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(83, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void revokeUriPermission(IApplicationThread iapplicationthread, String s, Uri uri, int i, int j)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(uri == null)
                break MISSING_BLOCK_LABEL_109;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
        }

        public void revokeUriPermissionFromOwner(IBinder ibinder, Uri uri, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(uri == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(112, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void scheduleApplicationInfoChanged(List list, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStringList(list);
            parcel.writeInt(i);
            mRemote.transact(285, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public void sendIdleJobTrigger()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(270, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int sendIntentSender(IIntentSender iintentsender, IBinder ibinder, int i, Intent intent, String s, IIntentReceiver iintentreceiver, String s1, 
                Bundle bundle)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null) goto _L2; else goto _L1
_L1:
            iintentsender = iintentsender.asBinder();
_L5:
            parcel.writeStrongBinder(iintentsender);
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(intent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L6:
            parcel.writeString(s);
            iintentsender = obj;
            if(iintentreceiver == null)
                break MISSING_BLOCK_LABEL_91;
            iintentsender = iintentreceiver.asBinder();
            parcel.writeStrongBinder(iintentsender);
            parcel.writeString(s1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_191;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(271, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            iintentsender = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
            parcel.writeInt(0);
              goto _L7
        }

        public void serviceDoneExecuting(IBinder ibinder, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(52, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void setActivityController(IActivityController iactivitycontroller, boolean flag)
            throws RemoteException
        {
            IBinder ibinder;
            int i;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iactivitycontroller == null)
                break MISSING_BLOCK_LABEL_33;
            ibinder = iactivitycontroller.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iactivitycontroller;
            parcel1.recycle();
            parcel.recycle();
            throw iactivitycontroller;
        }

        public void setAlwaysFinish(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setDebugApp(String s, boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            int i;
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
            mRemote.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setDisablePreviewScreenshots(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(288, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setDummyTranslucent(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(298, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void setDumpHeapDebugLimit(String s, int i, long l, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeString(s1);
            mRemote.transact(216, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setFocusedStack(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(165, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setFocusedTask(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(125, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setFrontActivityScreenCompatMode(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(119, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setHasTopUi(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(274, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setImmersive(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(106, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setLockScreenShown(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(141, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setPackageAskScreenCompat(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(123, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setPackageScreenCompatMode(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(121, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setPersistentVrThread(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(286, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setPictureInPictureParams(IBinder ibinder, PictureInPictureParams pictureinpictureparams)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(pictureinpictureparams == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            pictureinpictureparams.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(248, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setProcessImportant(IBinder ibinder, int i, boolean flag, String s)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(66, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setProcessLimit(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean setProcessMemoryTrimLevel(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(179, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setRenderThread(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(273, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setRequestedOrientation(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(63, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setResizeWhiteList(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStringList(list);
            mRemote.transact(297, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void setServiceForeground(ComponentName componentname, IBinder ibinder, int i, Notification notification, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(notification == null)
                break MISSING_BLOCK_LABEL_127;
            parcel.writeInt(1);
            notification.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(j);
            mRemote.transact(67, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public void setShowWhenLocked(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(291, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setTaskDescription(IBinder ibinder, ActivityManager.TaskDescription taskdescription)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(taskdescription == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            taskdescription.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(186, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setTaskResizeable(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(212, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setTurnScreenOn(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(292, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setUserIsMonkey(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(160, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setVoiceKeepAwake(IVoiceInteractionSession ivoiceinteractionsession, boolean flag)
            throws RemoteException
        {
            IBinder ibinder;
            int i;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(ivoiceinteractionsession == null)
                break MISSING_BLOCK_LABEL_33;
            ibinder = ivoiceinteractionsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(218, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ivoiceinteractionsession;
            parcel1.recycle();
            parcel.recycle();
            throw ivoiceinteractionsession;
        }

        public int setVrMode(IBinder ibinder, boolean flag, ComponentName componentname)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_103;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(253, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setVrThread(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(272, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean shouldUpRecreateTask(IBinder ibinder, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(139, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean showAssistFromActivity(IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_84;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(230, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void showBootMessage(CharSequence charsequence, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(132, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            charsequence;
            parcel1.recycle();
            parcel.recycle();
            throw charsequence;
        }

        public void showLockTaskEscapeMessage(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(223, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void showWaitingForDebugger(IApplicationThread iapplicationthread, boolean flag)
            throws RemoteException
        {
            IBinder ibinder;
            int i;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_33;
            ibinder = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(49, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
        }

        public boolean shutdown(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(81, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void signalPersistentProcesses(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(50, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int startActivities(IApplicationThread iapplicationthread, String s, Intent aintent[], String as[], IBinder ibinder, Bundle bundle, int i)
            throws RemoteException
        {
            IBinder ibinder1;
            Parcel parcel;
            Parcel parcel1;
            ibinder1 = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder1 = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder1);
            parcel.writeString(s);
            parcel.writeTypedArray(aintent, 0);
            parcel.writeStringArray(as);
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_134;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(115, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
        }

        public int startActivity(IApplicationThread iapplicationthread, String s, Intent intent, String s1, IBinder ibinder, String s2, int i, 
                int j, ProfilerInfo profilerinfo, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null) goto _L2; else goto _L1
_L1:
            iapplicationthread = iapplicationthread.asBinder();
_L7:
            parcel.writeStrongBinder(iapplicationthread);
            parcel.writeString(s);
            if(intent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L8:
            parcel.writeString(s1);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s2);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(profilerinfo == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            profilerinfo.writeToParcel(parcel, 0);
_L9:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_207;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L10:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            iapplicationthread = null;
              goto _L7
_L4:
            parcel.writeInt(0);
              goto _L8
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public WaitResult startActivityAndWait(IApplicationThread iapplicationthread, String s, Intent intent, String s1, IBinder ibinder, String s2, int i, 
                int j, ProfilerInfo profilerinfo, Bundle bundle, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null) goto _L2; else goto _L1
_L1:
            iapplicationthread = iapplicationthread.asBinder();
_L9:
            parcel.writeStrongBinder(iapplicationthread);
            parcel.writeString(s);
            if(intent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L10:
            parcel.writeString(s1);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s2);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(profilerinfo == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            profilerinfo.writeToParcel(parcel, 0);
_L11:
            if(bundle == null) goto _L8; else goto _L7
_L7:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L12:
            parcel.writeInt(k);
            mRemote.transact(99, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_238;
            iapplicationthread = (WaitResult)WaitResult.CREATOR.createFromParcel(parcel1);
_L13:
            parcel1.recycle();
            parcel.recycle();
            return iapplicationthread;
_L2:
            iapplicationthread = null;
              goto _L9
_L4:
            parcel.writeInt(0);
              goto _L10
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
_L6:
            parcel.writeInt(0);
              goto _L11
_L8:
            parcel.writeInt(0);
              goto _L12
            iapplicationthread = null;
              goto _L13
        }

        public int startActivityAsCaller(IApplicationThread iapplicationthread, String s, Intent intent, String s1, IBinder ibinder, String s2, int i, 
                int j, ProfilerInfo profilerinfo, Bundle bundle, boolean flag, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null) goto _L2; else goto _L1
_L1:
            iapplicationthread = iapplicationthread.asBinder();
_L7:
            parcel.writeStrongBinder(iapplicationthread);
            parcel.writeString(s);
            if(intent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L8:
            parcel.writeString(s1);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s2);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(profilerinfo == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            profilerinfo.writeToParcel(parcel, 0);
_L9:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_231;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L10:
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(k);
            mRemote.transact(198, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            iapplicationthread = null;
              goto _L7
_L4:
            parcel.writeInt(0);
              goto _L8
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public int startActivityAsUser(IApplicationThread iapplicationthread, String s, Intent intent, String s1, IBinder ibinder, String s2, int i, 
                int j, ProfilerInfo profilerinfo, Bundle bundle, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null) goto _L2; else goto _L1
_L1:
            iapplicationthread = iapplicationthread.asBinder();
_L7:
            parcel.writeStrongBinder(iapplicationthread);
            parcel.writeString(s);
            if(intent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L8:
            parcel.writeString(s1);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s2);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(profilerinfo == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            profilerinfo.writeToParcel(parcel, 0);
_L9:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_216;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L10:
            parcel.writeInt(k);
            mRemote.transact(146, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            iapplicationthread = null;
              goto _L7
_L4:
            parcel.writeInt(0);
              goto _L8
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public int startActivityFromRecents(int i, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(196, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel1.recycle();
            parcel.recycle();
            throw bundle;
        }

        public int startActivityIntentSender(IApplicationThread iapplicationthread, IIntentSender iintentsender, IBinder ibinder, Intent intent, String s, IBinder ibinder1, String s1, 
                int i, int j, int k, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null) goto _L2; else goto _L1
_L1:
            iapplicationthread = iapplicationthread.asBinder();
_L7:
            parcel.writeStrongBinder(iapplicationthread);
            if(iintentsender == null) goto _L4; else goto _L3
_L3:
            iapplicationthread = iintentsender.asBinder();
_L8:
            parcel.writeStrongBinder(iapplicationthread);
            parcel.writeStrongBinder(ibinder);
            if(intent == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L9:
            parcel.writeString(s);
            parcel.writeStrongBinder(ibinder1);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_211;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L10:
            mRemote.transact(94, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            iapplicationthread = null;
              goto _L7
_L4:
            iapplicationthread = null;
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
            parcel.writeInt(0);
              goto _L10
        }

        public int startActivityWithConfig(IApplicationThread iapplicationthread, String s, Intent intent, String s1, IBinder ibinder, String s2, int i, 
                int j, Configuration configuration, Bundle bundle, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null) goto _L2; else goto _L1
_L1:
            iapplicationthread = iapplicationthread.asBinder();
_L7:
            parcel.writeStrongBinder(iapplicationthread);
            parcel.writeString(s);
            if(intent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L8:
            parcel.writeString(s1);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s2);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(configuration == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L9:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_215;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L10:
            parcel.writeInt(k);
            mRemote.transact(101, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            iapplicationthread = null;
              goto _L7
_L4:
            parcel.writeInt(0);
              goto _L8
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public int startAssistantActivity(String s, int i, int j, Intent intent, String s1, Bundle bundle, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_150;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(k);
            mRemote.transact(188, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public boolean startBinderTracking()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(232, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void startConfirmDeviceCredentialIntent(Intent intent, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_97;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(269, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
            parcel.writeInt(0);
              goto _L4
        }

        public void startInPlaceAnimationOnFrontMostApplication(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(206, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel1.recycle();
            parcel.recycle();
            throw bundle;
        }

        public boolean startInstrumentation(ComponentName componentname, String s, int i, Bundle bundle, IInstrumentationWatcher iinstrumentationwatcher, IUiAutomationConnection iuiautomationconnection, int j, 
                String s1)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L5:
            parcel.writeString(s);
            parcel.writeInt(i);
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            if(iinstrumentationwatcher == null)
                break MISSING_BLOCK_LABEL_202;
            componentname = iinstrumentationwatcher.asBinder();
_L7:
            parcel.writeStrongBinder(componentname);
            componentname = obj;
            if(iuiautomationconnection == null)
                break MISSING_BLOCK_LABEL_103;
            componentname = iuiautomationconnection.asBinder();
            parcel.writeStrongBinder(componentname);
            parcel.writeInt(j);
            parcel.writeString(s1);
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
_L2:
            parcel.writeInt(0);
              goto _L5
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
_L4:
            parcel.writeInt(0);
              goto _L6
            componentname = null;
              goto _L7
        }

        public void startLocalVoiceInteraction(IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(257, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void startLockTaskModeById(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(182, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void startLockTaskModeByToken(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(183, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean startNextMatchingActivity(IBinder ibinder, Intent intent, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_129;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(60, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
_L2:
            parcel.writeInt(0);
              goto _L3
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
            parcel.writeInt(0);
              goto _L4
        }

        public ComponentName startService(IApplicationThread iapplicationthread, Intent intent, String s, boolean flag, String s1, int i)
            throws RemoteException
        {
            int j;
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            j = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_175;
            iapplicationthread = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return iapplicationthread;
_L2:
            parcel.writeInt(0);
              goto _L3
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
            iapplicationthread = null;
              goto _L4
        }

        public void startSystemLockTaskMode(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(191, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean startUserInBackground(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(181, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int startVoiceActivity(String s, int i, int j, Intent intent, String s1, IVoiceInteractionSession ivoiceinteractionsession, IVoiceInteractor ivoiceinteractor, 
                int k, ProfilerInfo profilerinfo, Bundle bundle, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L9:
            parcel.writeString(s1);
            if(ivoiceinteractionsession == null) goto _L4; else goto _L3
_L3:
            s = ivoiceinteractionsession.asBinder();
_L10:
            parcel.writeStrongBinder(s);
            if(ivoiceinteractor == null) goto _L6; else goto _L5
_L5:
            s = ivoiceinteractor.asBinder();
_L11:
            parcel.writeStrongBinder(s);
            parcel.writeInt(k);
            if(profilerinfo == null) goto _L8; else goto _L7
_L7:
            parcel.writeInt(1);
            profilerinfo.writeToParcel(parcel, 0);
_L12:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_233;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L13:
            parcel.writeInt(l);
            mRemote.transact(187, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L9
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
_L4:
            s = null;
              goto _L10
_L6:
            s = null;
              goto _L11
_L8:
            parcel.writeInt(0);
              goto _L12
            parcel.writeInt(0);
              goto _L13
        }

        public void stopAppSwitches()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(82, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean stopBinderTrackingAndDump(ParcelFileDescriptor parcelfiledescriptor)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(233, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public void stopLocalVoiceInteraction(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(258, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void stopLockTaskMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(184, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int stopService(IApplicationThread iapplicationthread, Intent intent, String s, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(intent == null)
                break MISSING_BLOCK_LABEL_111;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
        }

        public boolean stopServiceToken(ComponentName componentname, IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void stopSystemLockTaskMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(192, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int stopUser(int i, boolean flag, IStopUserCallback istopusercallback)
            throws RemoteException
        {
            IBinder ibinder;
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            if(istopusercallback == null)
                break MISSING_BLOCK_LABEL_56;
            ibinder = istopusercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(147, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            istopusercallback;
            parcel1.recycle();
            parcel.recycle();
            throw istopusercallback;
        }

        public boolean supportsLocalVoiceInteraction()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(259, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void suppressResizeConfigChanges(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(239, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void swapDockedAndFullscreenStack()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(267, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean switchUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            mRemote.transact(124, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void takePersistableUriPermission(Uri uri, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(uri == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(173, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
        }

        public void unbindBackupAgent(ApplicationInfo applicationinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(applicationinfo == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            applicationinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(86, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            applicationinfo;
            parcel1.recycle();
            parcel.recycle();
            throw applicationinfo;
        }

        public void unbindFinished(IBinder ibinder, Intent intent, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            if(intent == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(65, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean unbindService(IServiceConnection iserviceconnection)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iserviceconnection == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iserviceconnection.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            iserviceconnection;
            parcel1.recycle();
            parcel.recycle();
            throw iserviceconnection;
        }

        public void unbroadcastIntent(IApplicationThread iapplicationthread, Intent intent, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iapplicationthread == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iapplicationthread.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(intent == null)
                break MISSING_BLOCK_LABEL_95;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iapplicationthread;
            parcel1.recycle();
            parcel.recycle();
            throw iapplicationthread;
        }

        public void unhandledBack()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean unlockUser(int i, byte abyte0[], byte abyte1[], IProgressListener iprogresslistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            abyte0 = obj;
            if(iprogresslistener == null)
                break MISSING_BLOCK_LABEL_54;
            abyte0 = iprogresslistener.asBinder();
            parcel.writeStrongBinder(abyte0);
            mRemote.transact(243, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void unregisterActivityObserver(IMiuiActivityObserver imiuiactivityobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(imiuiactivityobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imiuiactivityobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(296, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imiuiactivityobserver;
            parcel1.recycle();
            parcel.recycle();
            throw imiuiactivityobserver;
        }

        public void unregisterIntentSenderCancelListener(IIntentSender iintentsender, IResultReceiver iresultreceiver)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentsender == null)
                break MISSING_BLOCK_LABEL_88;
            iintentsender = iintentsender.asBinder();
_L1:
            parcel.writeStrongBinder(iintentsender);
            iintentsender = obj;
            if(iresultreceiver == null)
                break MISSING_BLOCK_LABEL_49;
            iintentsender = iresultreceiver.asBinder();
            parcel.writeStrongBinder(iintentsender);
            mRemote.transact(58, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iintentsender = null;
              goto _L1
            iintentsender;
            parcel1.recycle();
            parcel.recycle();
            throw iintentsender;
        }

        public void unregisterMiuiAppTransitionAnimationHelper()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            mRemote.transact(294, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void unregisterProcessObserver(IProcessObserver iprocessobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iprocessobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iprocessobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(128, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iprocessobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iprocessobserver;
        }

        public void unregisterReceiver(IIntentReceiver iintentreceiver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iintentreceiver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iintentreceiver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iintentreceiver;
            parcel1.recycle();
            parcel.recycle();
            throw iintentreceiver;
        }

        public void unregisterTaskStackListener(ITaskStackListener itaskstacklistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(itaskstacklistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = itaskstacklistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(277, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            itaskstacklistener;
            parcel1.recycle();
            parcel.recycle();
            throw itaskstacklistener;
        }

        public void unregisterUidObserver(IUidObserver iuidobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iuidobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iuidobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(228, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iuidobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iuidobserver;
        }

        public void unregisterUserSwitchObserver(IUserSwitchObserver iuserswitchobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(iuserswitchobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iuserswitchobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(149, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iuserswitchobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iuserswitchobserver;
        }

        public void unstableProviderDied(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(144, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean updateConfiguration(Configuration configuration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(configuration == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            configuration;
            parcel1.recycle();
            parcel.recycle();
            throw configuration;
        }

        public void updateDeviceOwner(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeString(s);
            mRemote.transact(224, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean updateDisplayOverrideConfiguration(Configuration configuration, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(configuration == null)
                break MISSING_BLOCK_LABEL_82;
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(276, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            configuration;
            parcel1.recycle();
            parcel.recycle();
            throw configuration;
        }

        public void updateLockTaskPackages(int i, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeInt(i);
            parcel.writeStringArray(as);
            mRemote.transact(219, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void updatePersistentConfiguration(Configuration configuration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            if(configuration == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(130, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            configuration;
            parcel1.recycle();
            parcel.recycle();
            throw configuration;
        }

        public void waitForNetworkStateUpdate(long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeLong(l);
            mRemote.transact(287, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean willActivityBeVisible(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IActivityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(100, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void activityDestroyed(IBinder ibinder)
        throws RemoteException;

    public abstract void activityIdle(IBinder ibinder, Configuration configuration, boolean flag)
        throws RemoteException;

    public abstract void activityPaused(IBinder ibinder)
        throws RemoteException;

    public abstract void activityRelaunched(IBinder ibinder)
        throws RemoteException;

    public abstract void activityResumed(IBinder ibinder)
        throws RemoteException;

    public abstract void activitySlept(IBinder ibinder)
        throws RemoteException;

    public abstract void activityStopped(IBinder ibinder, Bundle bundle, PersistableBundle persistablebundle, CharSequence charsequence)
        throws RemoteException;

    public abstract int addAppTask(IBinder ibinder, Intent intent, ActivityManager.TaskDescription taskdescription, Bitmap bitmap)
        throws RemoteException;

    public abstract void addInstrumentationResults(IApplicationThread iapplicationthread, Bundle bundle)
        throws RemoteException;

    public abstract void addPackageDependency(String s)
        throws RemoteException;

    public abstract void appNotRespondingViaProvider(IBinder ibinder)
        throws RemoteException;

    public abstract void attachApplication(IApplicationThread iapplicationthread)
        throws RemoteException;

    public abstract void backgroundWhitelistUid(int i)
        throws RemoteException;

    public abstract void backupAgentCreated(String s, IBinder ibinder)
        throws RemoteException;

    public abstract boolean bindBackupAgent(String s, int i, int j)
        throws RemoteException;

    public abstract int bindService(IApplicationThread iapplicationthread, IBinder ibinder, Intent intent, String s, IServiceConnection iserviceconnection, int i, String s1, 
            int j)
        throws RemoteException;

    public abstract void bootAnimationComplete()
        throws RemoteException;

    public abstract int broadcastIntent(IApplicationThread iapplicationthread, Intent intent, String s, IIntentReceiver iintentreceiver, int i, String s1, Bundle bundle, 
            String as[], int j, Bundle bundle1, boolean flag, boolean flag1, int k)
        throws RemoteException;

    public abstract void cancelIntentSender(IIntentSender iintentsender)
        throws RemoteException;

    public abstract void cancelTaskThumbnailTransition(int i)
        throws RemoteException;

    public abstract void cancelTaskWindowTransition(int i)
        throws RemoteException;

    public abstract int checkGrantUriPermission(int i, String s, Uri uri, int j, int k)
        throws RemoteException;

    public abstract int checkPermission(String s, int i, int j)
        throws RemoteException;

    public abstract int checkPermissionWithToken(String s, int i, int j, IBinder ibinder)
        throws RemoteException;

    public abstract int checkUriPermission(Uri uri, int i, int j, int k, int l, IBinder ibinder)
        throws RemoteException;

    public abstract boolean clearApplicationUserData(String s, IPackageDataObserver ipackagedataobserver, int i)
        throws RemoteException;

    public abstract void clearGrantedUriPermissions(String s, int i)
        throws RemoteException;

    public abstract void clearPendingBackup()
        throws RemoteException;

    public abstract void closeSystemDialogs(String s)
        throws RemoteException;

    public abstract boolean convertFromTranslucent(IBinder ibinder)
        throws RemoteException;

    public abstract boolean convertToTranslucent(IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void crashApplication(int i, int j, String s, int k, String s1)
        throws RemoteException;

    public abstract int createStackOnDisplay(int i)
        throws RemoteException;

    public abstract void dismissKeyguard(IBinder ibinder, IKeyguardDismissCallback ikeyguarddismisscallback)
        throws RemoteException;

    public abstract boolean dumpHeap(String s, int i, boolean flag, boolean flag1, boolean flag2, String s1, ParcelFileDescriptor parcelfiledescriptor)
        throws RemoteException;

    public abstract void dumpHeapFinished(String s)
        throws RemoteException;

    public abstract boolean enterPictureInPictureMode(IBinder ibinder, PictureInPictureParams pictureinpictureparams)
        throws RemoteException;

    public abstract void enterSafeMode()
        throws RemoteException;

    public abstract void exitFreeformMode(IBinder ibinder)
        throws RemoteException;

    public abstract boolean finishActivity(IBinder ibinder, int i, Intent intent, int j)
        throws RemoteException;

    public abstract boolean finishActivityAffinity(IBinder ibinder)
        throws RemoteException;

    public abstract void finishHeavyWeightApp()
        throws RemoteException;

    public abstract void finishInstrumentation(IApplicationThread iapplicationthread, int i, Bundle bundle)
        throws RemoteException;

    public abstract void finishReceiver(IBinder ibinder, int i, String s, Bundle bundle, boolean flag, int j)
        throws RemoteException;

    public abstract void finishSubActivity(IBinder ibinder, String s, int i)
        throws RemoteException;

    public abstract void finishVoiceTask(IVoiceInteractionSession ivoiceinteractionsession)
        throws RemoteException;

    public abstract void forceStopPackage(String s, int i)
        throws RemoteException;

    public abstract ComponentName getActivityClassForToken(IBinder ibinder)
        throws RemoteException;

    public abstract int getActivityDisplayId(IBinder ibinder)
        throws RemoteException;

    public abstract Bundle getActivityOptions(IBinder ibinder)
        throws RemoteException;

    public abstract int getActivityStackId(IBinder ibinder)
        throws RemoteException;

    public abstract List getAllStackInfos()
        throws RemoteException;

    public abstract Point getAppTaskThumbnailSize()
        throws RemoteException;

    public abstract List getAppTasks(String s)
        throws RemoteException;

    public abstract Bundle getAssistContextExtras(int i)
        throws RemoteException;

    public abstract ComponentName getCallingActivity(IBinder ibinder)
        throws RemoteException;

    public abstract String getCallingPackage(IBinder ibinder)
        throws RemoteException;

    public abstract Configuration getConfiguration()
        throws RemoteException;

    public abstract ContentProviderHolder getContentProvider(IApplicationThread iapplicationthread, String s, int i, boolean flag)
        throws RemoteException;

    public abstract ContentProviderHolder getContentProviderExternal(String s, int i, IBinder ibinder)
        throws RemoteException;

    public abstract UserInfo getCurrentUser()
        throws RemoteException;

    public abstract ConfigurationInfo getDeviceConfigurationInfo()
        throws RemoteException;

    public abstract int getFocusedStackId()
        throws RemoteException;

    public abstract int getFrontActivityScreenCompatMode()
        throws RemoteException;

    public abstract ParceledListSlice getGrantedUriPermissions(String s, int i)
        throws RemoteException;

    public abstract Intent getIntentForIntentSender(IIntentSender iintentsender)
        throws RemoteException;

    public abstract IIntentSender getIntentSender(int i, String s, IBinder ibinder, String s1, int j, Intent aintent[], String as[], 
            int k, Bundle bundle, int l)
        throws RemoteException;

    public abstract int getLastResumedActivityUserId()
        throws RemoteException;

    public abstract String getLaunchedFromPackage(IBinder ibinder)
        throws RemoteException;

    public abstract int getLaunchedFromUid(IBinder ibinder)
        throws RemoteException;

    public abstract int getLockTaskModeState()
        throws RemoteException;

    public abstract int getMaxNumPictureInPictureActions(IBinder ibinder)
        throws RemoteException;

    public abstract void getMemoryInfo(ActivityManager.MemoryInfo memoryinfo)
        throws RemoteException;

    public abstract int getMemoryTrimLevel()
        throws RemoteException;

    public abstract void getMyMemoryState(ActivityManager.RunningAppProcessInfo runningappprocessinfo)
        throws RemoteException;

    public abstract boolean getPackageAskScreenCompat(String s)
        throws RemoteException;

    public abstract String getPackageForIntentSender(IIntentSender iintentsender)
        throws RemoteException;

    public abstract String getPackageForToken(IBinder ibinder)
        throws RemoteException;

    public abstract int getPackageProcessState(String s, String s1)
        throws RemoteException;

    public abstract int getPackageScreenCompatMode(String s)
        throws RemoteException;

    public abstract ParceledListSlice getPersistedUriPermissions(String s, boolean flag)
        throws RemoteException;

    public abstract int getProcessLimit()
        throws RemoteException;

    public abstract android.os.Debug.MemoryInfo[] getProcessMemoryInfo(int ai[])
        throws RemoteException;

    public abstract long[] getProcessPss(int ai[])
        throws RemoteException;

    public abstract List getProcessesInErrorState()
        throws RemoteException;

    public abstract String getProviderMimeType(Uri uri, int i)
        throws RemoteException;

    public abstract ParceledListSlice getRecentTasks(int i, int j, int k)
        throws RemoteException;

    public abstract int getRequestedOrientation(IBinder ibinder)
        throws RemoteException;

    public abstract List getRunningAppProcesses()
        throws RemoteException;

    public abstract List getRunningExternalApplications()
        throws RemoteException;

    public abstract PendingIntent getRunningServiceControlPanel(ComponentName componentname)
        throws RemoteException;

    public abstract int[] getRunningUserIds()
        throws RemoteException;

    public abstract List getServices(int i, int j)
        throws RemoteException;

    public abstract ActivityManager.StackInfo getStackInfo(int i)
        throws RemoteException;

    public abstract String getTagForIntentSender(IIntentSender iintentsender, String s)
        throws RemoteException;

    public abstract Rect getTaskBounds(int i)
        throws RemoteException;

    public abstract ActivityManager.TaskDescription getTaskDescription(int i)
        throws RemoteException;

    public abstract Bitmap getTaskDescriptionIcon(String s, int i)
        throws RemoteException;

    public abstract int getTaskForActivity(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract ActivityManager.TaskSnapshot getTaskSnapshot(int i, boolean flag)
        throws RemoteException;

    public abstract ActivityManager.TaskThumbnail getTaskThumbnail(int i)
        throws RemoteException;

    public abstract List getTasks(int i, int j)
        throws RemoteException;

    public abstract int getUidForIntentSender(IIntentSender iintentsender)
        throws RemoteException;

    public abstract int getUidProcessState(int i, String s)
        throws RemoteException;

    public abstract IBinder getUriPermissionOwnerForActivity(IBinder ibinder)
        throws RemoteException;

    public abstract void grantUriPermission(IApplicationThread iapplicationthread, String s, Uri uri, int i, int j)
        throws RemoteException;

    public abstract void grantUriPermissionFromOwner(IBinder ibinder, int i, String s, Uri uri, int j, int k, int l)
        throws RemoteException;

    public abstract void handleApplicationCrash(IBinder ibinder, ApplicationErrorReport.ParcelableCrashInfo parcelablecrashinfo)
        throws RemoteException;

    public abstract void handleApplicationStrictModeViolation(IBinder ibinder, int i, android.os.StrictMode.ViolationInfo violationinfo)
        throws RemoteException;

    public abstract boolean handleApplicationWtf(IBinder ibinder, String s, boolean flag, ApplicationErrorReport.ParcelableCrashInfo parcelablecrashinfo)
        throws RemoteException;

    public abstract int handleIncomingUser(int i, int j, int k, boolean flag, boolean flag1, String s, String s1)
        throws RemoteException;

    public abstract void hang(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract long inputDispatchingTimedOut(int i, boolean flag, String s)
        throws RemoteException;

    public abstract boolean isAppForeground(int i)
        throws RemoteException;

    public abstract boolean isAppStartModeDisabled(int i, String s)
        throws RemoteException;

    public abstract boolean isAssistDataAllowedOnCurrentActivity()
        throws RemoteException;

    public abstract boolean isImmersive(IBinder ibinder)
        throws RemoteException;

    public abstract boolean isInLockTaskMode()
        throws RemoteException;

    public abstract boolean isInMultiWindowMode(IBinder ibinder)
        throws RemoteException;

    public abstract boolean isInPictureInPictureMode(IBinder ibinder)
        throws RemoteException;

    public abstract boolean isIntentSenderAnActivity(IIntentSender iintentsender)
        throws RemoteException;

    public abstract boolean isIntentSenderTargetedToPackage(IIntentSender iintentsender)
        throws RemoteException;

    public abstract boolean isRootVoiceInteraction(IBinder ibinder)
        throws RemoteException;

    public abstract boolean isTopActivityImmersive()
        throws RemoteException;

    public abstract boolean isTopOfTask(IBinder ibinder)
        throws RemoteException;

    public abstract boolean isUserAMonkey()
        throws RemoteException;

    public abstract boolean isUserRunning(int i, int j)
        throws RemoteException;

    public abstract boolean isVrModePackageEnabled(ComponentName componentname)
        throws RemoteException;

    public abstract void keyguardGoingAway(int i)
        throws RemoteException;

    public abstract void killAllBackgroundProcesses()
        throws RemoteException;

    public abstract void killApplication(String s, int i, int j, String s1)
        throws RemoteException;

    public abstract void killApplicationProcess(String s, int i)
        throws RemoteException;

    public abstract void killBackgroundProcesses(String s, int i)
        throws RemoteException;

    public abstract void killPackageDependents(String s, int i)
        throws RemoteException;

    public abstract boolean killPids(int ai[], String s, boolean flag)
        throws RemoteException;

    public abstract boolean killProcessesBelowForeground(String s)
        throws RemoteException;

    public abstract void killUid(int i, int j, String s)
        throws RemoteException;

    public abstract boolean launchAssistIntent(Intent intent, int i, String s, int j, Bundle bundle)
        throws RemoteException;

    public abstract void makePackageIdle(String s, int i)
        throws RemoteException;

    public abstract boolean moveActivityTaskToBack(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void moveStackToDisplay(int i, int j)
        throws RemoteException;

    public abstract void moveTaskBackwards(int i)
        throws RemoteException;

    public abstract boolean moveTaskToDockedStack(int i, int j, boolean flag, boolean flag1, Rect rect)
        throws RemoteException;

    public abstract void moveTaskToFront(int i, int j, Bundle bundle)
        throws RemoteException;

    public abstract void moveTaskToStack(int i, int j, boolean flag)
        throws RemoteException;

    public abstract void moveTasksToFullscreenStack(int i, boolean flag)
        throws RemoteException;

    public abstract boolean moveTopActivityToPinnedStack(int i, Rect rect)
        throws RemoteException;

    public abstract boolean navigateUpTo(IBinder ibinder, Intent intent, int i, Intent intent1)
        throws RemoteException;

    public abstract IBinder newUriPermissionOwner(String s)
        throws RemoteException;

    public abstract void noteAlarmFinish(IIntentSender iintentsender, int i, String s)
        throws RemoteException;

    public abstract void noteAlarmStart(IIntentSender iintentsender, int i, String s)
        throws RemoteException;

    public abstract void noteWakeupAlarm(IIntentSender iintentsender, int i, String s, String s1)
        throws RemoteException;

    public abstract void notifyActivityDrawn(IBinder ibinder)
        throws RemoteException;

    public abstract void notifyCleartextNetwork(int i, byte abyte0[])
        throws RemoteException;

    public abstract void notifyEnterAnimationComplete(IBinder ibinder)
        throws RemoteException;

    public abstract void notifyLaunchTaskBehindComplete(IBinder ibinder)
        throws RemoteException;

    public abstract void notifyLockedProfile(int i)
        throws RemoteException;

    public abstract void notifyPinnedStackAnimationEnded()
        throws RemoteException;

    public abstract void notifyPinnedStackAnimationStarted()
        throws RemoteException;

    public abstract ParcelFileDescriptor openContentUri(String s)
        throws RemoteException;

    public abstract void overridePendingTransition(IBinder ibinder, String s, int i, int j)
        throws RemoteException;

    public abstract IBinder peekService(Intent intent, String s, String s1)
        throws RemoteException;

    public abstract void performIdleMaintenance()
        throws RemoteException;

    public abstract void positionTaskInStack(int i, int j, int k)
        throws RemoteException;

    public abstract boolean profileControl(String s, int i, boolean flag, ProfilerInfo profilerinfo, int j)
        throws RemoteException;

    public abstract void publishContentProviders(IApplicationThread iapplicationthread, List list)
        throws RemoteException;

    public abstract void publishService(IBinder ibinder, Intent intent, IBinder ibinder1)
        throws RemoteException;

    public abstract boolean refContentProvider(IBinder ibinder, int i, int j)
        throws RemoteException;

    public abstract void registerActivityObserver(IMiuiActivityObserver imiuiactivityobserver, Intent intent)
        throws RemoteException;

    public abstract void registerIntentSenderCancelListener(IIntentSender iintentsender, IResultReceiver iresultreceiver)
        throws RemoteException;

    public abstract void registerMiuiAppTransitionAnimationHelper(IMiuiAppTransitionAnimationHelper imiuiapptransitionanimationhelper)
        throws RemoteException;

    public abstract void registerProcessObserver(IProcessObserver iprocessobserver)
        throws RemoteException;

    public abstract Intent registerReceiver(IApplicationThread iapplicationthread, String s, IIntentReceiver iintentreceiver, IntentFilter intentfilter, String s1, int i, int j)
        throws RemoteException;

    public abstract void registerTaskStackListener(ITaskStackListener itaskstacklistener)
        throws RemoteException;

    public abstract void registerUidObserver(IUidObserver iuidobserver, int i, int j, String s)
        throws RemoteException;

    public abstract void registerUserSwitchObserver(IUserSwitchObserver iuserswitchobserver, String s)
        throws RemoteException;

    public abstract boolean releaseActivityInstance(IBinder ibinder)
        throws RemoteException;

    public abstract void releasePersistableUriPermission(Uri uri, int i, int j)
        throws RemoteException;

    public abstract void releaseSomeActivities(IApplicationThread iapplicationthread)
        throws RemoteException;

    public abstract void removeContentProvider(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void removeContentProviderExternal(String s, IBinder ibinder)
        throws RemoteException;

    public abstract void removeStack(int i)
        throws RemoteException;

    public abstract boolean removeTask(int i)
        throws RemoteException;

    public abstract void reportActivityFullyDrawn(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void reportAssistContextExtras(IBinder ibinder, Bundle bundle, AssistStructure assiststructure, AssistContent assistcontent, Uri uri)
        throws RemoteException;

    public abstract void reportKillProcessEvent(int i, int j)
        throws RemoteException;

    public abstract void reportSizeConfigurations(IBinder ibinder, int ai[], int ai1[], int ai2[])
        throws RemoteException;

    public abstract void requestActivityRelaunch(IBinder ibinder)
        throws RemoteException;

    public abstract boolean requestAssistContextExtras(int i, IResultReceiver iresultreceiver, Bundle bundle, IBinder ibinder, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract boolean requestAutofillData(IResultReceiver iresultreceiver, Bundle bundle, IBinder ibinder, int i)
        throws RemoteException;

    public abstract void requestBugReport(int i)
        throws RemoteException;

    public abstract void requestTelephonyBugReport(String s, String s1)
        throws RemoteException;

    public abstract void resizeDockedStack(Rect rect, Rect rect1, Rect rect2, Rect rect3, Rect rect4)
        throws RemoteException;

    public abstract void resizePinnedStack(Rect rect, Rect rect1)
        throws RemoteException;

    public abstract void resizeStack(int i, Rect rect, boolean flag, boolean flag1, boolean flag2, int j)
        throws RemoteException;

    public abstract void resizeTask(int i, Rect rect, int j)
        throws RemoteException;

    public abstract void restart()
        throws RemoteException;

    public abstract int restartUserInBackground(int i)
        throws RemoteException;

    public abstract void resumeAppSwitches()
        throws RemoteException;

    public abstract void revokeUriPermission(IApplicationThread iapplicationthread, String s, Uri uri, int i, int j)
        throws RemoteException;

    public abstract void revokeUriPermissionFromOwner(IBinder ibinder, Uri uri, int i, int j)
        throws RemoteException;

    public abstract void scheduleApplicationInfoChanged(List list, int i)
        throws RemoteException;

    public abstract void sendIdleJobTrigger()
        throws RemoteException;

    public abstract int sendIntentSender(IIntentSender iintentsender, IBinder ibinder, int i, Intent intent, String s, IIntentReceiver iintentreceiver, String s1, 
            Bundle bundle)
        throws RemoteException;

    public abstract void serviceDoneExecuting(IBinder ibinder, int i, int j, int k)
        throws RemoteException;

    public abstract void setActivityController(IActivityController iactivitycontroller, boolean flag)
        throws RemoteException;

    public abstract void setAlwaysFinish(boolean flag)
        throws RemoteException;

    public abstract void setDebugApp(String s, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void setDisablePreviewScreenshots(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void setDummyTranslucent(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void setDumpHeapDebugLimit(String s, int i, long l, String s1)
        throws RemoteException;

    public abstract void setFocusedStack(int i)
        throws RemoteException;

    public abstract void setFocusedTask(int i)
        throws RemoteException;

    public abstract void setFrontActivityScreenCompatMode(int i)
        throws RemoteException;

    public abstract void setHasTopUi(boolean flag)
        throws RemoteException;

    public abstract void setImmersive(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void setLockScreenShown(boolean flag, int i)
        throws RemoteException;

    public abstract void setPackageAskScreenCompat(String s, boolean flag)
        throws RemoteException;

    public abstract void setPackageScreenCompatMode(String s, int i)
        throws RemoteException;

    public abstract void setPersistentVrThread(int i)
        throws RemoteException;

    public abstract void setPictureInPictureParams(IBinder ibinder, PictureInPictureParams pictureinpictureparams)
        throws RemoteException;

    public abstract void setProcessImportant(IBinder ibinder, int i, boolean flag, String s)
        throws RemoteException;

    public abstract void setProcessLimit(int i)
        throws RemoteException;

    public abstract boolean setProcessMemoryTrimLevel(String s, int i, int j)
        throws RemoteException;

    public abstract void setRenderThread(int i)
        throws RemoteException;

    public abstract void setRequestedOrientation(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void setResizeWhiteList(List list)
        throws RemoteException;

    public abstract void setServiceForeground(ComponentName componentname, IBinder ibinder, int i, Notification notification, int j)
        throws RemoteException;

    public abstract void setShowWhenLocked(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void setTaskDescription(IBinder ibinder, ActivityManager.TaskDescription taskdescription)
        throws RemoteException;

    public abstract void setTaskResizeable(int i, int j)
        throws RemoteException;

    public abstract void setTurnScreenOn(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void setUserIsMonkey(boolean flag)
        throws RemoteException;

    public abstract void setVoiceKeepAwake(IVoiceInteractionSession ivoiceinteractionsession, boolean flag)
        throws RemoteException;

    public abstract int setVrMode(IBinder ibinder, boolean flag, ComponentName componentname)
        throws RemoteException;

    public abstract void setVrThread(int i)
        throws RemoteException;

    public abstract boolean shouldUpRecreateTask(IBinder ibinder, String s)
        throws RemoteException;

    public abstract boolean showAssistFromActivity(IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void showBootMessage(CharSequence charsequence, boolean flag)
        throws RemoteException;

    public abstract void showLockTaskEscapeMessage(IBinder ibinder)
        throws RemoteException;

    public abstract void showWaitingForDebugger(IApplicationThread iapplicationthread, boolean flag)
        throws RemoteException;

    public abstract boolean shutdown(int i)
        throws RemoteException;

    public abstract void signalPersistentProcesses(int i)
        throws RemoteException;

    public abstract int startActivities(IApplicationThread iapplicationthread, String s, Intent aintent[], String as[], IBinder ibinder, Bundle bundle, int i)
        throws RemoteException;

    public abstract int startActivity(IApplicationThread iapplicationthread, String s, Intent intent, String s1, IBinder ibinder, String s2, int i, 
            int j, ProfilerInfo profilerinfo, Bundle bundle)
        throws RemoteException;

    public abstract WaitResult startActivityAndWait(IApplicationThread iapplicationthread, String s, Intent intent, String s1, IBinder ibinder, String s2, int i, 
            int j, ProfilerInfo profilerinfo, Bundle bundle, int k)
        throws RemoteException;

    public abstract int startActivityAsCaller(IApplicationThread iapplicationthread, String s, Intent intent, String s1, IBinder ibinder, String s2, int i, 
            int j, ProfilerInfo profilerinfo, Bundle bundle, boolean flag, int k)
        throws RemoteException;

    public abstract int startActivityAsUser(IApplicationThread iapplicationthread, String s, Intent intent, String s1, IBinder ibinder, String s2, int i, 
            int j, ProfilerInfo profilerinfo, Bundle bundle, int k)
        throws RemoteException;

    public abstract int startActivityFromRecents(int i, Bundle bundle)
        throws RemoteException;

    public abstract int startActivityIntentSender(IApplicationThread iapplicationthread, IIntentSender iintentsender, IBinder ibinder, Intent intent, String s, IBinder ibinder1, String s1, 
            int i, int j, int k, Bundle bundle)
        throws RemoteException;

    public abstract int startActivityWithConfig(IApplicationThread iapplicationthread, String s, Intent intent, String s1, IBinder ibinder, String s2, int i, 
            int j, Configuration configuration, Bundle bundle, int k)
        throws RemoteException;

    public abstract int startAssistantActivity(String s, int i, int j, Intent intent, String s1, Bundle bundle, int k)
        throws RemoteException;

    public abstract boolean startBinderTracking()
        throws RemoteException;

    public abstract void startConfirmDeviceCredentialIntent(Intent intent, Bundle bundle)
        throws RemoteException;

    public abstract void startInPlaceAnimationOnFrontMostApplication(Bundle bundle)
        throws RemoteException;

    public abstract boolean startInstrumentation(ComponentName componentname, String s, int i, Bundle bundle, IInstrumentationWatcher iinstrumentationwatcher, IUiAutomationConnection iuiautomationconnection, int j, 
            String s1)
        throws RemoteException;

    public abstract void startLocalVoiceInteraction(IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void startLockTaskModeById(int i)
        throws RemoteException;

    public abstract void startLockTaskModeByToken(IBinder ibinder)
        throws RemoteException;

    public abstract boolean startNextMatchingActivity(IBinder ibinder, Intent intent, Bundle bundle)
        throws RemoteException;

    public abstract ComponentName startService(IApplicationThread iapplicationthread, Intent intent, String s, boolean flag, String s1, int i)
        throws RemoteException;

    public abstract void startSystemLockTaskMode(int i)
        throws RemoteException;

    public abstract boolean startUserInBackground(int i)
        throws RemoteException;

    public abstract int startVoiceActivity(String s, int i, int j, Intent intent, String s1, IVoiceInteractionSession ivoiceinteractionsession, IVoiceInteractor ivoiceinteractor, 
            int k, ProfilerInfo profilerinfo, Bundle bundle, int l)
        throws RemoteException;

    public abstract void stopAppSwitches()
        throws RemoteException;

    public abstract boolean stopBinderTrackingAndDump(ParcelFileDescriptor parcelfiledescriptor)
        throws RemoteException;

    public abstract void stopLocalVoiceInteraction(IBinder ibinder)
        throws RemoteException;

    public abstract void stopLockTaskMode()
        throws RemoteException;

    public abstract int stopService(IApplicationThread iapplicationthread, Intent intent, String s, int i)
        throws RemoteException;

    public abstract boolean stopServiceToken(ComponentName componentname, IBinder ibinder, int i)
        throws RemoteException;

    public abstract void stopSystemLockTaskMode()
        throws RemoteException;

    public abstract int stopUser(int i, boolean flag, IStopUserCallback istopusercallback)
        throws RemoteException;

    public abstract boolean supportsLocalVoiceInteraction()
        throws RemoteException;

    public abstract void suppressResizeConfigChanges(boolean flag)
        throws RemoteException;

    public abstract void swapDockedAndFullscreenStack()
        throws RemoteException;

    public abstract boolean switchUser(int i)
        throws RemoteException;

    public abstract void takePersistableUriPermission(Uri uri, int i, int j)
        throws RemoteException;

    public abstract void unbindBackupAgent(ApplicationInfo applicationinfo)
        throws RemoteException;

    public abstract void unbindFinished(IBinder ibinder, Intent intent, boolean flag)
        throws RemoteException;

    public abstract boolean unbindService(IServiceConnection iserviceconnection)
        throws RemoteException;

    public abstract void unbroadcastIntent(IApplicationThread iapplicationthread, Intent intent, int i)
        throws RemoteException;

    public abstract void unhandledBack()
        throws RemoteException;

    public abstract boolean unlockUser(int i, byte abyte0[], byte abyte1[], IProgressListener iprogresslistener)
        throws RemoteException;

    public abstract void unregisterActivityObserver(IMiuiActivityObserver imiuiactivityobserver)
        throws RemoteException;

    public abstract void unregisterIntentSenderCancelListener(IIntentSender iintentsender, IResultReceiver iresultreceiver)
        throws RemoteException;

    public abstract void unregisterMiuiAppTransitionAnimationHelper()
        throws RemoteException;

    public abstract void unregisterProcessObserver(IProcessObserver iprocessobserver)
        throws RemoteException;

    public abstract void unregisterReceiver(IIntentReceiver iintentreceiver)
        throws RemoteException;

    public abstract void unregisterTaskStackListener(ITaskStackListener itaskstacklistener)
        throws RemoteException;

    public abstract void unregisterUidObserver(IUidObserver iuidobserver)
        throws RemoteException;

    public abstract void unregisterUserSwitchObserver(IUserSwitchObserver iuserswitchobserver)
        throws RemoteException;

    public abstract void unstableProviderDied(IBinder ibinder)
        throws RemoteException;

    public abstract boolean updateConfiguration(Configuration configuration)
        throws RemoteException;

    public abstract void updateDeviceOwner(String s)
        throws RemoteException;

    public abstract boolean updateDisplayOverrideConfiguration(Configuration configuration, int i)
        throws RemoteException;

    public abstract void updateLockTaskPackages(int i, String as[])
        throws RemoteException;

    public abstract void updatePersistentConfiguration(Configuration configuration)
        throws RemoteException;

    public abstract void waitForNetworkStateUpdate(long l)
        throws RemoteException;

    public abstract boolean willActivityBeVisible(IBinder ibinder)
        throws RemoteException;
}
