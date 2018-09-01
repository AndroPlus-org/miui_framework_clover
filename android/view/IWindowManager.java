// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.res.Configuration;
import android.graphics.*;
import android.os.*;
import com.android.internal.app.IAssistScreenshotReceiver;
import com.android.internal.os.IResultReceiver;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.internal.policy.IShortcutService;
import com.android.internal.view.IInputContext;
import com.android.internal.view.IInputMethodClient;

// Referenced classes of package android.view:
//            InputChannel, IOnKeyguardExitResult, IGestureStubListener, WindowContentFrameStats, 
//            IWindowSessionCallback, IWindowSession, AppTransitionAnimationSpec, IAppTransitionAnimationSpecsFuture, 
//            IDockedStackListener, IPinnedStackListener, IWallpaperVisibilityListener, IRotationWatcher

public interface IWindowManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWindowManager
    {

        public static IWindowManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IWindowManager");
            if(iinterface != null && (iinterface instanceof IWindowManager))
                return (IWindowManager)iinterface;
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
                parcel1.writeString("android.view.IWindowManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag = startViewServer(parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag1 = stopViewServer();
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag2 = isViewServerRunning();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.view.IWindowManager");
                parcel = openSession(IWindowSessionCallback.Stub.asInterface(parcel.readStrongBinder()), com.android.internal.view.IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()), com.android.internal.view.IInputContext.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag3 = inputMethodClientHasFocus(com.android.internal.view.IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.view.IWindowManager");
                i = parcel.readInt();
                parcel = new Point();
                getInitialDisplaySize(i, parcel);
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
                parcel.enforceInterface("android.view.IWindowManager");
                i = parcel.readInt();
                parcel = new Point();
                getBaseDisplaySize(i, parcel);
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

            case 8: // '\b'
                parcel.enforceInterface("android.view.IWindowManager");
                setForcedDisplaySize(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.view.IWindowManager");
                clearForcedDisplaySize(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.view.IWindowManager");
                i = getInitialDisplayDensity(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.view.IWindowManager");
                i = getBaseDisplayDensity(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.view.IWindowManager");
                setForcedDisplayDensityForUser(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.view.IWindowManager");
                clearForcedDisplayDensityForUser(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.view.IWindowManager");
                setForcedDisplayScalingMode(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.view.IWindowManager");
                setOverscan(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setEventDispatching(flag4);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.view.IWindowManager");
                addWindowToken(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.view.IWindowManager");
                removeWindowToken(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.view.IWindowManager");
                IBinder ibinder = parcel.readStrongBinder();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                setFocusedApp(ibinder, flag5);
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.view.IWindowManager");
                i = parcel.readInt();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                prepareAppTransition(i, flag6);
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.view.IWindowManager");
                i = getPendingAppTransition();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.view.IWindowManager");
                overridePendingAppTransition(parcel.readString(), parcel.readInt(), parcel.readInt(), android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.view.IWindowManager");
                overridePendingAppTransitionScaleUp(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.view.IWindowManager");
                overridePendingAppTransitionClipReveal(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.view.IWindowManager");
                overridePendingAppTransitionLaunchFromHome(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.view.IWindowManager");
                if(parcel.readInt() != 0)
                    parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                overrideMiuiAnimSupportWinInset(parcel);
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.view.IWindowManager");
                cancelMiuiThumbnailAnimation();
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.view.IWindowManager");
                parcel = getGestureStubListener();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag7;
                GraphicBuffer graphicbuffer;
                IRemoteCallback iremotecallback2;
                if(parcel.readInt() != 0)
                    graphicbuffer = (GraphicBuffer)GraphicBuffer.CREATOR.createFromParcel(parcel);
                else
                    graphicbuffer = null;
                j = parcel.readInt();
                i = parcel.readInt();
                iremotecallback2 = android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                overridePendingAppTransitionThumb(graphicbuffer, j, i, iremotecallback2, flag7);
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag8;
                GraphicBuffer graphicbuffer1;
                IRemoteCallback iremotecallback3;
                int k;
                int l;
                if(parcel.readInt() != 0)
                    graphicbuffer1 = (GraphicBuffer)GraphicBuffer.CREATOR.createFromParcel(parcel);
                else
                    graphicbuffer1 = null;
                i = parcel.readInt();
                j = parcel.readInt();
                k = parcel.readInt();
                l = parcel.readInt();
                iremotecallback3 = android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                overridePendingAppTransitionAspectScaledThumb(graphicbuffer1, i, j, k, l, iremotecallback3, flag8);
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.view.IWindowManager");
                AppTransitionAnimationSpec aapptransitionanimationspec[] = (AppTransitionAnimationSpec[])parcel.createTypedArray(AppTransitionAnimationSpec.CREATOR);
                IRemoteCallback iremotecallback = android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder());
                IRemoteCallback iremotecallback4 = android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder());
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                overridePendingAppTransitionMultiThumb(aapptransitionanimationspec, iremotecallback, iremotecallback4, flag9);
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.view.IWindowManager");
                overridePendingAppTransitionInPlace(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.view.IWindowManager");
                IAppTransitionAnimationSpecsFuture iapptransitionanimationspecsfuture = IAppTransitionAnimationSpecsFuture.Stub.asInterface(parcel.readStrongBinder());
                IRemoteCallback iremotecallback1 = android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder());
                boolean flag10;
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                overridePendingAppTransitionMultiThumbFuture(iapptransitionanimationspecsfuture, iremotecallback1, flag10);
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.view.IWindowManager");
                executeAppTransition();
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.view.IWindowManager");
                endProlongedAnimations();
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.view.IWindowManager");
                Configuration configuration;
                if(parcel.readInt() != 0)
                    configuration = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    configuration = null;
                parcel = updateOrientationFromAppTokens(configuration, parcel.readStrongBinder(), parcel.readInt());
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

            case 37: // '%'
                parcel.enforceInterface("android.view.IWindowManager");
                Configuration configuration1;
                if(parcel.readInt() != 0)
                    configuration1 = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
                else
                    configuration1 = null;
                parcel = setNewDisplayOverrideConfiguration(configuration1, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.view.IWindowManager");
                startFreezingScreen(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.view.IWindowManager");
                stopFreezingScreen();
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("android.view.IWindowManager");
                disableKeyguard(parcel.readStrongBinder(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.view.IWindowManager");
                reenableKeyguard(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.view.IWindowManager");
                exitKeyguardSecurely(IOnKeyguardExitResult.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag11 = isKeyguardLocked();
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 44: // ','
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag12 = isKeyguardSecure();
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag13 = inKeyguardRestrictedInputMode();
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.view.IWindowManager");
                dismissKeyguard(com.android.internal.policy.IKeyguardDismissCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag14;
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                setSwitchingUser(flag14);
                parcel1.writeNoException();
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.view.IWindowManager");
                closeSystemDialogs(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 49: // '1'
                parcel.enforceInterface("android.view.IWindowManager");
                float f = getAnimationScale(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeFloat(f);
                return true;

            case 50: // '2'
                parcel.enforceInterface("android.view.IWindowManager");
                parcel = getAnimationScales();
                parcel1.writeNoException();
                parcel1.writeFloatArray(parcel);
                return true;

            case 51: // '3'
                parcel.enforceInterface("android.view.IWindowManager");
                setAnimationScale(parcel.readInt(), parcel.readFloat());
                parcel1.writeNoException();
                return true;

            case 52: // '4'
                parcel.enforceInterface("android.view.IWindowManager");
                setAnimationScales(parcel.createFloatArray());
                parcel1.writeNoException();
                return true;

            case 53: // '5'
                parcel.enforceInterface("android.view.IWindowManager");
                float f1 = getCurrentAnimatorScale();
                parcel1.writeNoException();
                parcel1.writeFloat(f1);
                return true;

            case 54: // '6'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag15;
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                setInTouchMode(flag15);
                parcel1.writeNoException();
                return true;

            case 55: // '7'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag16;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                showStrictModeViolation(flag16);
                parcel1.writeNoException();
                return true;

            case 56: // '8'
                parcel.enforceInterface("android.view.IWindowManager");
                setStrictModeVisualIndicatorPreference(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 57: // '9'
                parcel.enforceInterface("android.view.IWindowManager");
                i = parcel.readInt();
                boolean flag17;
                if(parcel.readInt() != 0)
                    flag17 = true;
                else
                    flag17 = false;
                setScreenCaptureDisabled(i, flag17);
                parcel1.writeNoException();
                return true;

            case 58: // ':'
                parcel.enforceInterface("android.view.IWindowManager");
                if(parcel.readInt() != 0)
                    parcel = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                enableSurfaceTrace(parcel);
                parcel1.writeNoException();
                return true;

            case 59: // ';'
                parcel.enforceInterface("android.view.IWindowManager");
                disableSurfaceTrace();
                parcel1.writeNoException();
                return true;

            case 60: // '<'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag18;
                boolean flag30;
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                if(parcel.readInt() != 0)
                    flag30 = true;
                else
                    flag30 = false;
                updateRotation(flag18, flag30);
                parcel1.writeNoException();
                return true;

            case 61: // '='
                parcel.enforceInterface("android.view.IWindowManager");
                i = getDefaultDisplayRotation();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 62: // '>'
                parcel.enforceInterface("android.view.IWindowManager");
                i = watchRotation(IRotationWatcher.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 63: // '?'
                parcel.enforceInterface("android.view.IWindowManager");
                removeRotationWatcher(IRotationWatcher.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 64: // '@'
                parcel.enforceInterface("android.view.IWindowManager");
                i = getPreferredOptionsPanelGravity();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 65: // 'A'
                parcel.enforceInterface("android.view.IWindowManager");
                freezeRotation(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 66: // 'B'
                parcel.enforceInterface("android.view.IWindowManager");
                thawRotation();
                parcel1.writeNoException();
                return true;

            case 67: // 'C'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag19 = isRotationFrozen();
                parcel1.writeNoException();
                if(flag19)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 68: // 'D'
                parcel.enforceInterface("android.view.IWindowManager");
                parcel = screenshotWallpaper();
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

            case 69: // 'E'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag20 = registerWallpaperVisibilityListener(IWallpaperVisibilityListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                if(flag20)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 70: // 'F'
                parcel.enforceInterface("android.view.IWindowManager");
                unregisterWallpaperVisibilityListener(IWallpaperVisibilityListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 71: // 'G'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag21 = requestAssistScreenshot(com.android.internal.app.IAssistScreenshotReceiver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag21)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 72: // 'H'
                parcel.enforceInterface("android.view.IWindowManager");
                statusBarVisibilityChanged(parcel.readInt());
                return true;

            case 73: // 'I'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag22;
                if(parcel.readInt() != 0)
                    flag22 = true;
                else
                    flag22 = false;
                setRecentsVisibility(flag22);
                return true;

            case 74: // 'J'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag23;
                if(parcel.readInt() != 0)
                    flag23 = true;
                else
                    flag23 = false;
                setPipVisibility(flag23);
                return true;

            case 75: // 'K'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag24 = hasNavigationBar();
                parcel1.writeNoException();
                if(flag24)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 76: // 'L'
                parcel.enforceInterface("android.view.IWindowManager");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                lockNow(parcel);
                parcel1.writeNoException();
                return true;

            case 77: // 'M'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag25 = isSafeModeEnabled();
                parcel1.writeNoException();
                if(flag25)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 78: // 'N'
                parcel.enforceInterface("android.view.IWindowManager");
                enableScreenIfNeeded();
                parcel1.writeNoException();
                return true;

            case 79: // 'O'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag26 = clearWindowContentFrameStats(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag26)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 80: // 'P'
                parcel.enforceInterface("android.view.IWindowManager");
                parcel = getWindowContentFrameStats(parcel.readStrongBinder());
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

            case 81: // 'Q'
                parcel.enforceInterface("android.view.IWindowManager");
                i = getDockedStackSide();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 82: // 'R'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag27;
                if(parcel.readInt() != 0)
                    flag27 = true;
                else
                    flag27 = false;
                setDockedStackResizing(flag27);
                parcel1.writeNoException();
                return true;

            case 83: // 'S'
                parcel.enforceInterface("android.view.IWindowManager");
                if(parcel.readInt() != 0)
                    parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setDockedStackDividerTouchRegion(parcel);
                parcel1.writeNoException();
                return true;

            case 84: // 'T'
                parcel.enforceInterface("android.view.IWindowManager");
                registerDockedStackListener(IDockedStackListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 85: // 'U'
                parcel.enforceInterface("android.view.IWindowManager");
                registerPinnedStackListener(parcel.readInt(), IPinnedStackListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 86: // 'V'
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag28;
                if(parcel.readInt() != 0)
                    flag28 = true;
                else
                    flag28 = false;
                setResizeDimLayer(flag28, parcel.readInt(), parcel.readFloat());
                parcel1.writeNoException();
                return true;

            case 87: // 'W'
                parcel.enforceInterface("android.view.IWindowManager");
                requestAppKeyboardShortcuts(com.android.internal.os.IResultReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 88: // 'X'
                parcel.enforceInterface("android.view.IWindowManager");
                i = parcel.readInt();
                parcel = new Rect();
                getStableInsets(i, parcel);
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

            case 89: // 'Y'
                parcel.enforceInterface("android.view.IWindowManager");
                registerShortcutKey(parcel.readLong(), com.android.internal.policy.IShortcutService.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 90: // 'Z'
                parcel.enforceInterface("android.view.IWindowManager");
                parcel = parcel.readString();
                InputChannel inputchannel = new InputChannel();
                createInputConsumer(parcel, inputchannel);
                parcel1.writeNoException();
                if(inputchannel != null)
                {
                    parcel1.writeInt(1);
                    inputchannel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 91: // '['
                parcel.enforceInterface("android.view.IWindowManager");
                boolean flag29 = destroyInputConsumer(parcel.readString());
                parcel1.writeNoException();
                if(flag29)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 92: // '\\'
                parcel.enforceInterface("android.view.IWindowManager");
                parcel = getCurrentImeTouchRegion();
                parcel1.writeNoException();
                break;
            }
            if(parcel != null)
            {
                parcel1.writeInt(1);
                parcel.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }

        private static final String DESCRIPTOR = "android.view.IWindowManager";
        static final int TRANSACTION_addWindowToken = 17;
        static final int TRANSACTION_cancelMiuiThumbnailAnimation = 27;
        static final int TRANSACTION_clearForcedDisplayDensityForUser = 13;
        static final int TRANSACTION_clearForcedDisplaySize = 9;
        static final int TRANSACTION_clearWindowContentFrameStats = 79;
        static final int TRANSACTION_closeSystemDialogs = 48;
        static final int TRANSACTION_createInputConsumer = 90;
        static final int TRANSACTION_destroyInputConsumer = 91;
        static final int TRANSACTION_disableKeyguard = 40;
        static final int TRANSACTION_disableSurfaceTrace = 59;
        static final int TRANSACTION_dismissKeyguard = 46;
        static final int TRANSACTION_enableScreenIfNeeded = 78;
        static final int TRANSACTION_enableSurfaceTrace = 58;
        static final int TRANSACTION_endProlongedAnimations = 35;
        static final int TRANSACTION_executeAppTransition = 34;
        static final int TRANSACTION_exitKeyguardSecurely = 42;
        static final int TRANSACTION_freezeRotation = 65;
        static final int TRANSACTION_getAnimationScale = 49;
        static final int TRANSACTION_getAnimationScales = 50;
        static final int TRANSACTION_getBaseDisplayDensity = 11;
        static final int TRANSACTION_getBaseDisplaySize = 7;
        static final int TRANSACTION_getCurrentAnimatorScale = 53;
        static final int TRANSACTION_getCurrentImeTouchRegion = 92;
        static final int TRANSACTION_getDefaultDisplayRotation = 61;
        static final int TRANSACTION_getDockedStackSide = 81;
        static final int TRANSACTION_getGestureStubListener = 28;
        static final int TRANSACTION_getInitialDisplayDensity = 10;
        static final int TRANSACTION_getInitialDisplaySize = 6;
        static final int TRANSACTION_getPendingAppTransition = 21;
        static final int TRANSACTION_getPreferredOptionsPanelGravity = 64;
        static final int TRANSACTION_getStableInsets = 88;
        static final int TRANSACTION_getWindowContentFrameStats = 80;
        static final int TRANSACTION_hasNavigationBar = 75;
        static final int TRANSACTION_inKeyguardRestrictedInputMode = 45;
        static final int TRANSACTION_inputMethodClientHasFocus = 5;
        static final int TRANSACTION_isKeyguardLocked = 43;
        static final int TRANSACTION_isKeyguardSecure = 44;
        static final int TRANSACTION_isRotationFrozen = 67;
        static final int TRANSACTION_isSafeModeEnabled = 77;
        static final int TRANSACTION_isViewServerRunning = 3;
        static final int TRANSACTION_lockNow = 76;
        static final int TRANSACTION_openSession = 4;
        static final int TRANSACTION_overrideMiuiAnimSupportWinInset = 26;
        static final int TRANSACTION_overridePendingAppTransition = 22;
        static final int TRANSACTION_overridePendingAppTransitionAspectScaledThumb = 30;
        static final int TRANSACTION_overridePendingAppTransitionClipReveal = 24;
        static final int TRANSACTION_overridePendingAppTransitionInPlace = 32;
        static final int TRANSACTION_overridePendingAppTransitionLaunchFromHome = 25;
        static final int TRANSACTION_overridePendingAppTransitionMultiThumb = 31;
        static final int TRANSACTION_overridePendingAppTransitionMultiThumbFuture = 33;
        static final int TRANSACTION_overridePendingAppTransitionScaleUp = 23;
        static final int TRANSACTION_overridePendingAppTransitionThumb = 29;
        static final int TRANSACTION_prepareAppTransition = 20;
        static final int TRANSACTION_reenableKeyguard = 41;
        static final int TRANSACTION_registerDockedStackListener = 84;
        static final int TRANSACTION_registerPinnedStackListener = 85;
        static final int TRANSACTION_registerShortcutKey = 89;
        static final int TRANSACTION_registerWallpaperVisibilityListener = 69;
        static final int TRANSACTION_removeRotationWatcher = 63;
        static final int TRANSACTION_removeWindowToken = 18;
        static final int TRANSACTION_requestAppKeyboardShortcuts = 87;
        static final int TRANSACTION_requestAssistScreenshot = 71;
        static final int TRANSACTION_screenshotWallpaper = 68;
        static final int TRANSACTION_setAnimationScale = 51;
        static final int TRANSACTION_setAnimationScales = 52;
        static final int TRANSACTION_setDockedStackDividerTouchRegion = 83;
        static final int TRANSACTION_setDockedStackResizing = 82;
        static final int TRANSACTION_setEventDispatching = 16;
        static final int TRANSACTION_setFocusedApp = 19;
        static final int TRANSACTION_setForcedDisplayDensityForUser = 12;
        static final int TRANSACTION_setForcedDisplayScalingMode = 14;
        static final int TRANSACTION_setForcedDisplaySize = 8;
        static final int TRANSACTION_setInTouchMode = 54;
        static final int TRANSACTION_setNewDisplayOverrideConfiguration = 37;
        static final int TRANSACTION_setOverscan = 15;
        static final int TRANSACTION_setPipVisibility = 74;
        static final int TRANSACTION_setRecentsVisibility = 73;
        static final int TRANSACTION_setResizeDimLayer = 86;
        static final int TRANSACTION_setScreenCaptureDisabled = 57;
        static final int TRANSACTION_setStrictModeVisualIndicatorPreference = 56;
        static final int TRANSACTION_setSwitchingUser = 47;
        static final int TRANSACTION_showStrictModeViolation = 55;
        static final int TRANSACTION_startFreezingScreen = 38;
        static final int TRANSACTION_startViewServer = 1;
        static final int TRANSACTION_statusBarVisibilityChanged = 72;
        static final int TRANSACTION_stopFreezingScreen = 39;
        static final int TRANSACTION_stopViewServer = 2;
        static final int TRANSACTION_thawRotation = 66;
        static final int TRANSACTION_unregisterWallpaperVisibilityListener = 70;
        static final int TRANSACTION_updateOrientationFromAppTokens = 36;
        static final int TRANSACTION_updateRotation = 60;
        static final int TRANSACTION_watchRotation = 62;

        public Stub()
        {
            attachInterface(this, "android.view.IWindowManager");
        }
    }

    private static class Stub.Proxy
        implements IWindowManager
    {

        public void addWindowToken(IBinder ibinder, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(17, parcel, parcel1, 0);
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

        public void cancelMiuiThumbnailAnimation()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(27, parcel, parcel1, 0);
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

        public void clearForcedDisplayDensityForUser(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public void clearForcedDisplaySize(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
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

        public boolean clearWindowContentFrameStats(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(79, parcel, parcel1, 0);
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

        public void closeSystemDialogs(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeString(s);
            mRemote.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void createInputConsumer(String s, InputChannel inputchannel)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeString(s);
            mRemote.transact(90, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() != 0)
                inputchannel.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean destroyInputConsumer(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeString(s);
            mRemote.transact(91, parcel, parcel1, 0);
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

        public void disableKeyguard(IBinder ibinder, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void disableSurfaceTrace()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
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

        public void dismissKeyguard(IKeyguardDismissCallback ikeyguarddismisscallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(ikeyguarddismisscallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ikeyguarddismisscallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(46, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ikeyguarddismisscallback;
            parcel1.recycle();
            parcel.recycle();
            throw ikeyguarddismisscallback;
        }

        public void enableScreenIfNeeded()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(78, parcel, parcel1, 0);
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

        public void enableSurfaceTrace(ParcelFileDescriptor parcelfiledescriptor)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(58, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public void endProlongedAnimations()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(35, parcel, parcel1, 0);
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

        public void executeAppTransition()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(34, parcel, parcel1, 0);
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

        public void exitKeyguardSecurely(IOnKeyguardExitResult ionkeyguardexitresult)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(ionkeyguardexitresult == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ionkeyguardexitresult.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ionkeyguardexitresult;
            parcel1.recycle();
            parcel.recycle();
            throw ionkeyguardexitresult;
        }

        public void freezeRotation(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            mRemote.transact(65, parcel, parcel1, 0);
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

        public float getAnimationScale(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            float f;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            mRemote.transact(49, parcel, parcel1, 0);
            parcel1.readException();
            f = parcel1.readFloat();
            parcel1.recycle();
            parcel.recycle();
            return f;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public float[] getAnimationScales()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            float af[];
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(50, parcel, parcel1, 0);
            parcel1.readException();
            af = parcel1.createFloatArray();
            parcel1.recycle();
            parcel.recycle();
            return af;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getBaseDisplayDensity(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public void getBaseDisplaySize(int i, Point point)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() != 0)
                point.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return;
            point;
            parcel1.recycle();
            parcel.recycle();
            throw point;
        }

        public float getCurrentAnimatorScale()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            float f;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(53, parcel, parcel1, 0);
            parcel1.readException();
            f = parcel1.readFloat();
            parcel1.recycle();
            parcel.recycle();
            return f;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Region getCurrentImeTouchRegion()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(92, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Region region = (Region)Region.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return region;
_L2:
            region = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getDefaultDisplayRotation()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(61, parcel, parcel1, 0);
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

        public int getDockedStackSide()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(81, parcel, parcel1, 0);
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

        public IGestureStubListener getGestureStubListener()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IGestureStubListener igesturestublistener;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            igesturestublistener = IGestureStubListener.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return igesturestublistener;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getInitialDisplayDensity(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
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

        public void getInitialDisplaySize(int i, Point point)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() != 0)
                point.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return;
            point;
            parcel1.recycle();
            parcel.recycle();
            throw point;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IWindowManager";
        }

        public int getPendingAppTransition()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(21, parcel, parcel1, 0);
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

        public int getPreferredOptionsPanelGravity()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(64, parcel, parcel1, 0);
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

        public void getStableInsets(int i, Rect rect)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            mRemote.transact(88, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() != 0)
                rect.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return;
            rect;
            parcel1.recycle();
            parcel.recycle();
            throw rect;
        }

        public WindowContentFrameStats getWindowContentFrameStats(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(80, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ibinder = (WindowContentFrameStats)WindowContentFrameStats.CREATOR.createFromParcel(parcel1);
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

        public boolean hasNavigationBar()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(75, parcel, parcel1, 0);
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

        public boolean inKeyguardRestrictedInputMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(45, parcel, parcel1, 0);
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

        public boolean inputMethodClientHasFocus(IInputMethodClient iinputmethodclient)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(iinputmethodclient == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iinputmethodclient.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
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
            iinputmethodclient;
            parcel1.recycle();
            parcel.recycle();
            throw iinputmethodclient;
        }

        public boolean isKeyguardLocked()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(43, parcel, parcel1, 0);
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

        public boolean isKeyguardSecure()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(44, parcel, parcel1, 0);
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

        public boolean isRotationFrozen()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(67, parcel, parcel1, 0);
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

        public boolean isSafeModeEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(77, parcel, parcel1, 0);
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

        public boolean isViewServerRunning()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(3, parcel, parcel1, 0);
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

        public void lockNow(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(76, parcel, parcel1, 0);
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

        public IWindowSession openSession(IWindowSessionCallback iwindowsessioncallback, IInputMethodClient iinputmethodclient, IInputContext iinputcontext)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(iwindowsessioncallback == null) goto _L2; else goto _L1
_L1:
            iwindowsessioncallback = iwindowsessioncallback.asBinder();
_L5:
            parcel.writeStrongBinder(iwindowsessioncallback);
            if(iinputmethodclient == null) goto _L4; else goto _L3
_L3:
            iwindowsessioncallback = iinputmethodclient.asBinder();
_L6:
            parcel.writeStrongBinder(iwindowsessioncallback);
            iwindowsessioncallback = obj;
            if(iinputcontext == null)
                break MISSING_BLOCK_LABEL_68;
            iwindowsessioncallback = iinputcontext.asBinder();
            parcel.writeStrongBinder(iwindowsessioncallback);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            iwindowsessioncallback = IWindowSession.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iwindowsessioncallback;
_L2:
            iwindowsessioncallback = null;
              goto _L5
_L4:
            iwindowsessioncallback = null;
              goto _L6
            iwindowsessioncallback;
            parcel1.recycle();
            parcel.recycle();
            throw iwindowsessioncallback;
              goto _L5
        }

        public void overrideMiuiAnimSupportWinInset(Rect rect)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(rect == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(26, parcel, parcel1, 0);
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

        public void overridePendingAppTransition(String s, int i, int j, IRemoteCallback iremotecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            s = obj;
            if(iremotecallback == null)
                break MISSING_BLOCK_LABEL_54;
            s = iremotecallback.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void overridePendingAppTransitionAspectScaledThumb(GraphicBuffer graphicbuffer, int i, int j, int k, int l, IRemoteCallback iremotecallback, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(graphicbuffer == null)
                break MISSING_BLOCK_LABEL_135;
            parcel.writeInt(1);
            graphicbuffer.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            graphicbuffer = obj;
            if(iremotecallback == null)
                break MISSING_BLOCK_LABEL_82;
            graphicbuffer = iremotecallback.asBinder();
            parcel.writeStrongBinder(graphicbuffer);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            graphicbuffer;
            parcel1.recycle();
            parcel.recycle();
            throw graphicbuffer;
        }

        public void overridePendingAppTransitionClipReveal(int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(24, parcel, parcel1, 0);
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

        public void overridePendingAppTransitionInPlace(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeString(s);
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

        public void overridePendingAppTransitionLaunchFromHome(int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(25, parcel, parcel1, 0);
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

        public void overridePendingAppTransitionMultiThumb(AppTransitionAnimationSpec aapptransitionanimationspec[], IRemoteCallback iremotecallback, IRemoteCallback iremotecallback1, boolean flag)
            throws RemoteException
        {
            int i;
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeTypedArray(aapptransitionanimationspec, 0);
            if(iremotecallback == null)
                break MISSING_BLOCK_LABEL_115;
            aapptransitionanimationspec = iremotecallback.asBinder();
_L1:
            parcel.writeStrongBinder(aapptransitionanimationspec);
            aapptransitionanimationspec = obj;
            if(iremotecallback1 == null)
                break MISSING_BLOCK_LABEL_61;
            aapptransitionanimationspec = iremotecallback1.asBinder();
            parcel.writeStrongBinder(aapptransitionanimationspec);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            aapptransitionanimationspec = null;
              goto _L1
            aapptransitionanimationspec;
            parcel1.recycle();
            parcel.recycle();
            throw aapptransitionanimationspec;
        }

        public void overridePendingAppTransitionMultiThumbFuture(IAppTransitionAnimationSpecsFuture iapptransitionanimationspecsfuture, IRemoteCallback iremotecallback, boolean flag)
            throws RemoteException
        {
            int i;
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(iapptransitionanimationspecsfuture == null)
                break MISSING_BLOCK_LABEL_107;
            iapptransitionanimationspecsfuture = iapptransitionanimationspecsfuture.asBinder();
_L1:
            parcel.writeStrongBinder(iapptransitionanimationspecsfuture);
            iapptransitionanimationspecsfuture = obj;
            if(iremotecallback == null)
                break MISSING_BLOCK_LABEL_54;
            iapptransitionanimationspecsfuture = iremotecallback.asBinder();
            parcel.writeStrongBinder(iapptransitionanimationspecsfuture);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iapptransitionanimationspecsfuture = null;
              goto _L1
            iapptransitionanimationspecsfuture;
            parcel1.recycle();
            parcel.recycle();
            throw iapptransitionanimationspecsfuture;
        }

        public void overridePendingAppTransitionScaleUp(int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(23, parcel, parcel1, 0);
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

        public void overridePendingAppTransitionThumb(GraphicBuffer graphicbuffer, int i, int j, IRemoteCallback iremotecallback, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(graphicbuffer == null)
                break MISSING_BLOCK_LABEL_121;
            parcel.writeInt(1);
            graphicbuffer.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            graphicbuffer = obj;
            if(iremotecallback == null)
                break MISSING_BLOCK_LABEL_68;
            graphicbuffer = iremotecallback.asBinder();
            parcel.writeStrongBinder(graphicbuffer);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            graphicbuffer;
            parcel1.recycle();
            parcel.recycle();
            throw graphicbuffer;
        }

        public void prepareAppTransition(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public void reenableKeyguard(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void registerDockedStackListener(IDockedStackListener idockedstacklistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(idockedstacklistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = idockedstacklistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(84, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            idockedstacklistener;
            parcel1.recycle();
            parcel.recycle();
            throw idockedstacklistener;
        }

        public void registerPinnedStackListener(int i, IPinnedStackListener ipinnedstacklistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            if(ipinnedstacklistener == null)
                break MISSING_BLOCK_LABEL_36;
            ibinder = ipinnedstacklistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(85, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ipinnedstacklistener;
            parcel1.recycle();
            parcel.recycle();
            throw ipinnedstacklistener;
        }

        public void registerShortcutKey(long l, IShortcutService ishortcutservice)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeLong(l);
            if(ishortcutservice == null)
                break MISSING_BLOCK_LABEL_38;
            ibinder = ishortcutservice.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(89, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ishortcutservice;
            parcel1.recycle();
            parcel.recycle();
            throw ishortcutservice;
        }

        public boolean registerWallpaperVisibilityListener(IWallpaperVisibilityListener iwallpapervisibilitylistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(iwallpapervisibilitylistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iwallpapervisibilitylistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(69, parcel, parcel1, 0);
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
            iwallpapervisibilitylistener;
            parcel1.recycle();
            parcel.recycle();
            throw iwallpapervisibilitylistener;
        }

        public void removeRotationWatcher(IRotationWatcher irotationwatcher)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(irotationwatcher == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = irotationwatcher.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(63, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            irotationwatcher;
            parcel1.recycle();
            parcel.recycle();
            throw irotationwatcher;
        }

        public void removeWindowToken(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void requestAppKeyboardShortcuts(IResultReceiver iresultreceiver, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(iresultreceiver == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iresultreceiver.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(87, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iresultreceiver;
            parcel1.recycle();
            parcel.recycle();
            throw iresultreceiver;
        }

        public boolean requestAssistScreenshot(IAssistScreenshotReceiver iassistscreenshotreceiver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(iassistscreenshotreceiver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iassistscreenshotreceiver.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
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
            iassistscreenshotreceiver;
            parcel1.recycle();
            parcel.recycle();
            throw iassistscreenshotreceiver;
        }

        public Bitmap screenshotWallpaper()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(68, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return bitmap;
_L2:
            bitmap = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setAnimationScale(int i, float f)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            parcel.writeFloat(f);
            mRemote.transact(51, parcel, parcel1, 0);
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

        public void setAnimationScales(float af[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeFloatArray(af);
            mRemote.transact(52, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            af;
            parcel1.recycle();
            parcel.recycle();
            throw af;
        }

        public void setDockedStackDividerTouchRegion(Rect rect)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(rect == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(83, parcel, parcel1, 0);
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

        public void setDockedStackResizing(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
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

        public void setEventDispatching(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
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

        public void setFocusedApp(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setForcedDisplayDensityForUser(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(12, parcel, parcel1, 0);
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

        public void setForcedDisplayScalingMode(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public void setForcedDisplaySize(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public void setInTouchMode(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(54, parcel, parcel1, 0);
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

        public int[] setNewDisplayOverrideConfiguration(Configuration configuration, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(configuration == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            configuration = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return configuration;
            parcel.writeInt(0);
              goto _L1
            configuration;
            parcel1.recycle();
            parcel.recycle();
            throw configuration;
        }

        public void setOverscan(int i, int j, int k, int l, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public void setPipVisibility(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(74, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setRecentsVisibility(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(73, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setResizeDimLayer(boolean flag, int i, float f)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            parcel.writeFloat(f);
            mRemote.transact(86, parcel, parcel1, 0);
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

        public void setScreenCaptureDisabled(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(57, parcel, parcel1, 0);
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

        public void setStrictModeVisualIndicatorPreference(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeString(s);
            mRemote.transact(56, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setSwitchingUser(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(47, parcel, parcel1, 0);
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

        public void showStrictModeViolation(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(55, parcel, parcel1, 0);
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

        public void startFreezingScreen(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(38, parcel, parcel1, 0);
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

        public boolean startViewServer(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void statusBarVisibilityChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            parcel.writeInt(i);
            mRemote.transact(72, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void stopFreezingScreen()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(39, parcel, parcel1, 0);
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

        public boolean stopViewServer()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(2, parcel, parcel1, 0);
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

        public void thawRotation()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            mRemote.transact(66, parcel, parcel1, 0);
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

        public void unregisterWallpaperVisibilityListener(IWallpaperVisibilityListener iwallpapervisibilitylistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(iwallpapervisibilitylistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iwallpapervisibilitylistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(70, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwallpapervisibilitylistener;
            parcel1.recycle();
            parcel.recycle();
            throw iwallpapervisibilitylistener;
        }

        public Configuration updateOrientationFromAppTokens(Configuration configuration, IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(configuration == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L3:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            configuration = (Configuration)Configuration.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return configuration;
_L2:
            parcel.writeInt(0);
              goto _L3
            configuration;
            parcel1.recycle();
            parcel.recycle();
            throw configuration;
            configuration = null;
              goto _L4
        }

        public void updateRotation(boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
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
            mRemote.transact(60, parcel, parcel1, 0);
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

        public int watchRotation(IRotationWatcher irotationwatcher, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowManager");
            if(irotationwatcher == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = irotationwatcher.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(62, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            irotationwatcher;
            parcel1.recycle();
            parcel.recycle();
            throw irotationwatcher;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addWindowToken(IBinder ibinder, int i, int j)
        throws RemoteException;

    public abstract void cancelMiuiThumbnailAnimation()
        throws RemoteException;

    public abstract void clearForcedDisplayDensityForUser(int i, int j)
        throws RemoteException;

    public abstract void clearForcedDisplaySize(int i)
        throws RemoteException;

    public abstract boolean clearWindowContentFrameStats(IBinder ibinder)
        throws RemoteException;

    public abstract void closeSystemDialogs(String s)
        throws RemoteException;

    public abstract void createInputConsumer(String s, InputChannel inputchannel)
        throws RemoteException;

    public abstract boolean destroyInputConsumer(String s)
        throws RemoteException;

    public abstract void disableKeyguard(IBinder ibinder, String s)
        throws RemoteException;

    public abstract void disableSurfaceTrace()
        throws RemoteException;

    public abstract void dismissKeyguard(IKeyguardDismissCallback ikeyguarddismisscallback)
        throws RemoteException;

    public abstract void enableScreenIfNeeded()
        throws RemoteException;

    public abstract void enableSurfaceTrace(ParcelFileDescriptor parcelfiledescriptor)
        throws RemoteException;

    public abstract void endProlongedAnimations()
        throws RemoteException;

    public abstract void executeAppTransition()
        throws RemoteException;

    public abstract void exitKeyguardSecurely(IOnKeyguardExitResult ionkeyguardexitresult)
        throws RemoteException;

    public abstract void freezeRotation(int i)
        throws RemoteException;

    public abstract float getAnimationScale(int i)
        throws RemoteException;

    public abstract float[] getAnimationScales()
        throws RemoteException;

    public abstract int getBaseDisplayDensity(int i)
        throws RemoteException;

    public abstract void getBaseDisplaySize(int i, Point point)
        throws RemoteException;

    public abstract float getCurrentAnimatorScale()
        throws RemoteException;

    public abstract Region getCurrentImeTouchRegion()
        throws RemoteException;

    public abstract int getDefaultDisplayRotation()
        throws RemoteException;

    public abstract int getDockedStackSide()
        throws RemoteException;

    public abstract IGestureStubListener getGestureStubListener()
        throws RemoteException;

    public abstract int getInitialDisplayDensity(int i)
        throws RemoteException;

    public abstract void getInitialDisplaySize(int i, Point point)
        throws RemoteException;

    public abstract int getPendingAppTransition()
        throws RemoteException;

    public abstract int getPreferredOptionsPanelGravity()
        throws RemoteException;

    public abstract void getStableInsets(int i, Rect rect)
        throws RemoteException;

    public abstract WindowContentFrameStats getWindowContentFrameStats(IBinder ibinder)
        throws RemoteException;

    public abstract boolean hasNavigationBar()
        throws RemoteException;

    public abstract boolean inKeyguardRestrictedInputMode()
        throws RemoteException;

    public abstract boolean inputMethodClientHasFocus(IInputMethodClient iinputmethodclient)
        throws RemoteException;

    public abstract boolean isKeyguardLocked()
        throws RemoteException;

    public abstract boolean isKeyguardSecure()
        throws RemoteException;

    public abstract boolean isRotationFrozen()
        throws RemoteException;

    public abstract boolean isSafeModeEnabled()
        throws RemoteException;

    public abstract boolean isViewServerRunning()
        throws RemoteException;

    public abstract void lockNow(Bundle bundle)
        throws RemoteException;

    public abstract IWindowSession openSession(IWindowSessionCallback iwindowsessioncallback, IInputMethodClient iinputmethodclient, IInputContext iinputcontext)
        throws RemoteException;

    public abstract void overrideMiuiAnimSupportWinInset(Rect rect)
        throws RemoteException;

    public abstract void overridePendingAppTransition(String s, int i, int j, IRemoteCallback iremotecallback)
        throws RemoteException;

    public abstract void overridePendingAppTransitionAspectScaledThumb(GraphicBuffer graphicbuffer, int i, int j, int k, int l, IRemoteCallback iremotecallback, boolean flag)
        throws RemoteException;

    public abstract void overridePendingAppTransitionClipReveal(int i, int j, int k, int l)
        throws RemoteException;

    public abstract void overridePendingAppTransitionInPlace(String s, int i)
        throws RemoteException;

    public abstract void overridePendingAppTransitionLaunchFromHome(int i, int j, int k, int l)
        throws RemoteException;

    public abstract void overridePendingAppTransitionMultiThumb(AppTransitionAnimationSpec aapptransitionanimationspec[], IRemoteCallback iremotecallback, IRemoteCallback iremotecallback1, boolean flag)
        throws RemoteException;

    public abstract void overridePendingAppTransitionMultiThumbFuture(IAppTransitionAnimationSpecsFuture iapptransitionanimationspecsfuture, IRemoteCallback iremotecallback, boolean flag)
        throws RemoteException;

    public abstract void overridePendingAppTransitionScaleUp(int i, int j, int k, int l)
        throws RemoteException;

    public abstract void overridePendingAppTransitionThumb(GraphicBuffer graphicbuffer, int i, int j, IRemoteCallback iremotecallback, boolean flag)
        throws RemoteException;

    public abstract void prepareAppTransition(int i, boolean flag)
        throws RemoteException;

    public abstract void reenableKeyguard(IBinder ibinder)
        throws RemoteException;

    public abstract void registerDockedStackListener(IDockedStackListener idockedstacklistener)
        throws RemoteException;

    public abstract void registerPinnedStackListener(int i, IPinnedStackListener ipinnedstacklistener)
        throws RemoteException;

    public abstract void registerShortcutKey(long l, IShortcutService ishortcutservice)
        throws RemoteException;

    public abstract boolean registerWallpaperVisibilityListener(IWallpaperVisibilityListener iwallpapervisibilitylistener, int i)
        throws RemoteException;

    public abstract void removeRotationWatcher(IRotationWatcher irotationwatcher)
        throws RemoteException;

    public abstract void removeWindowToken(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void requestAppKeyboardShortcuts(IResultReceiver iresultreceiver, int i)
        throws RemoteException;

    public abstract boolean requestAssistScreenshot(IAssistScreenshotReceiver iassistscreenshotreceiver)
        throws RemoteException;

    public abstract Bitmap screenshotWallpaper()
        throws RemoteException;

    public abstract void setAnimationScale(int i, float f)
        throws RemoteException;

    public abstract void setAnimationScales(float af[])
        throws RemoteException;

    public abstract void setDockedStackDividerTouchRegion(Rect rect)
        throws RemoteException;

    public abstract void setDockedStackResizing(boolean flag)
        throws RemoteException;

    public abstract void setEventDispatching(boolean flag)
        throws RemoteException;

    public abstract void setFocusedApp(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void setForcedDisplayDensityForUser(int i, int j, int k)
        throws RemoteException;

    public abstract void setForcedDisplayScalingMode(int i, int j)
        throws RemoteException;

    public abstract void setForcedDisplaySize(int i, int j, int k)
        throws RemoteException;

    public abstract void setInTouchMode(boolean flag)
        throws RemoteException;

    public abstract int[] setNewDisplayOverrideConfiguration(Configuration configuration, int i)
        throws RemoteException;

    public abstract void setOverscan(int i, int j, int k, int l, int i1)
        throws RemoteException;

    public abstract void setPipVisibility(boolean flag)
        throws RemoteException;

    public abstract void setRecentsVisibility(boolean flag)
        throws RemoteException;

    public abstract void setResizeDimLayer(boolean flag, int i, float f)
        throws RemoteException;

    public abstract void setScreenCaptureDisabled(int i, boolean flag)
        throws RemoteException;

    public abstract void setStrictModeVisualIndicatorPreference(String s)
        throws RemoteException;

    public abstract void setSwitchingUser(boolean flag)
        throws RemoteException;

    public abstract void showStrictModeViolation(boolean flag)
        throws RemoteException;

    public abstract void startFreezingScreen(int i, int j)
        throws RemoteException;

    public abstract boolean startViewServer(int i)
        throws RemoteException;

    public abstract void statusBarVisibilityChanged(int i)
        throws RemoteException;

    public abstract void stopFreezingScreen()
        throws RemoteException;

    public abstract boolean stopViewServer()
        throws RemoteException;

    public abstract void thawRotation()
        throws RemoteException;

    public abstract void unregisterWallpaperVisibilityListener(IWallpaperVisibilityListener iwallpapervisibilitylistener, int i)
        throws RemoteException;

    public abstract Configuration updateOrientationFromAppTokens(Configuration configuration, IBinder ibinder, int i)
        throws RemoteException;

    public abstract void updateRotation(boolean flag, boolean flag1)
        throws RemoteException;

    public abstract int watchRotation(IRotationWatcher irotationwatcher, int i)
        throws RemoteException;
}
