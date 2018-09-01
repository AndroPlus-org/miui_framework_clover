// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.ClipData;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.*;
import android.util.MergedConfiguration;

// Referenced classes of package android.view:
//            IWindow, InputChannel, IWindowId, Surface

public interface IWindowSession
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWindowSession
    {

        public static IWindowSession asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IWindowSession");
            if(iinterface != null && (iinterface instanceof IWindowSession))
                return (IWindowSession)iinterface;
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
                parcel1.writeString("android.view.IWindowSession");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IWindowSession");
                IWindow iwindow = IWindow.Stub.asInterface(parcel.readStrongBinder());
                j = parcel.readInt();
                WindowManager.LayoutParams layoutparams;
                Rect rect2;
                InputChannel inputchannel;
                if(parcel.readInt() != 0)
                    layoutparams = (WindowManager.LayoutParams)WindowManager.LayoutParams.CREATOR.createFromParcel(parcel);
                else
                    layoutparams = null;
                i = parcel.readInt();
                rect2 = new Rect();
                parcel = new Rect();
                inputchannel = new InputChannel();
                i = add(iwindow, j, layoutparams, i, rect2, parcel, inputchannel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(rect2 != null)
                {
                    parcel1.writeInt(1);
                    rect2.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(inputchannel != null)
                {
                    parcel1.writeInt(1);
                    inputchannel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.IWindowSession");
                IWindow iwindow1 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                j = parcel.readInt();
                WindowManager.LayoutParams layoutparams1;
                Rect rect3;
                Rect rect5;
                int k;
                InputChannel inputchannel1;
                if(parcel.readInt() != 0)
                    layoutparams1 = (WindowManager.LayoutParams)WindowManager.LayoutParams.CREATOR.createFromParcel(parcel);
                else
                    layoutparams1 = null;
                i = parcel.readInt();
                k = parcel.readInt();
                rect5 = new Rect();
                rect3 = new Rect();
                parcel = new Rect();
                inputchannel1 = new InputChannel();
                i = addToDisplay(iwindow1, j, layoutparams1, i, k, rect5, rect3, parcel, inputchannel1);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(rect5 != null)
                {
                    parcel1.writeInt(1);
                    rect5.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(rect3 != null)
                {
                    parcel1.writeInt(1);
                    rect3.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(inputchannel1 != null)
                {
                    parcel1.writeInt(1);
                    inputchannel1.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.view.IWindowSession");
                IWindow iwindow2 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                j = parcel.readInt();
                WindowManager.LayoutParams layoutparams2;
                Rect rect6;
                if(parcel.readInt() != 0)
                    layoutparams2 = (WindowManager.LayoutParams)WindowManager.LayoutParams.CREATOR.createFromParcel(parcel);
                else
                    layoutparams2 = null;
                i = parcel.readInt();
                parcel = new Rect();
                rect6 = new Rect();
                i = addWithoutInputChannel(iwindow2, j, layoutparams2, i, parcel, rect6);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(rect6 != null)
                {
                    parcel1.writeInt(1);
                    rect6.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.view.IWindowSession");
                IWindow iwindow3 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                WindowManager.LayoutParams layoutparams3;
                Rect rect7;
                int l;
                if(parcel.readInt() != 0)
                    layoutparams3 = (WindowManager.LayoutParams)WindowManager.LayoutParams.CREATOR.createFromParcel(parcel);
                else
                    layoutparams3 = null;
                j = parcel.readInt();
                l = parcel.readInt();
                rect7 = new Rect();
                parcel = new Rect();
                i = addToDisplayWithoutInputChannel(iwindow3, i, layoutparams3, j, l, rect7, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(rect7 != null)
                {
                    parcel1.writeInt(1);
                    rect7.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
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

            case 5: // '\005'
                parcel.enforceInterface("android.view.IWindowSession");
                remove(IWindow.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.view.IWindowSession");
                IWindow iwindow4 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                int l1 = parcel.readInt();
                WindowManager.LayoutParams layoutparams4;
                Rect rect4;
                MergedConfiguration mergedconfiguration;
                int i1;
                Rect rect8;
                int i2;
                Rect rect9;
                Rect rect10;
                Rect rect11;
                Rect rect12;
                Surface surface;
                if(parcel.readInt() != 0)
                    layoutparams4 = (WindowManager.LayoutParams)WindowManager.LayoutParams.CREATOR.createFromParcel(parcel);
                else
                    layoutparams4 = null;
                i2 = parcel.readInt();
                j = parcel.readInt();
                i1 = parcel.readInt();
                i = parcel.readInt();
                rect9 = new Rect();
                rect10 = new Rect();
                parcel = new Rect();
                rect11 = new Rect();
                rect4 = new Rect();
                rect8 = new Rect();
                rect12 = new Rect();
                mergedconfiguration = new MergedConfiguration();
                surface = new Surface();
                i = relayout(iwindow4, l1, layoutparams4, i2, j, i1, i, rect9, rect10, parcel, rect11, rect4, rect8, rect12, mergedconfiguration, surface);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(rect9 != null)
                {
                    parcel1.writeInt(1);
                    rect9.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(rect10 != null)
                {
                    parcel1.writeInt(1);
                    rect10.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(rect11 != null)
                {
                    parcel1.writeInt(1);
                    rect11.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(rect4 != null)
                {
                    parcel1.writeInt(1);
                    rect4.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(rect8 != null)
                {
                    parcel1.writeInt(1);
                    rect8.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(rect12 != null)
                {
                    parcel1.writeInt(1);
                    rect12.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(mergedconfiguration != null)
                {
                    parcel1.writeInt(1);
                    mergedconfiguration.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(surface != null)
                {
                    parcel1.writeInt(1);
                    surface.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.view.IWindowSession");
                IBinder ibinder1 = parcel.readStrongBinder();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                prepareToReplaceWindows(ibinder1, flag);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.view.IWindowSession");
                boolean flag1 = outOfMemory(IWindow.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.view.IWindowSession");
                IWindow iwindow6 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (Region)Region.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setTransparentRegion(iwindow6, parcel);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.view.IWindowSession");
                IWindow iwindow10 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                Rect rect;
                Rect rect1;
                if(parcel.readInt() != 0)
                    rect1 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect1 = null;
                if(parcel.readInt() != 0)
                    rect = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect = null;
                if(parcel.readInt() != 0)
                    parcel = (Region)Region.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setInsets(iwindow10, i, rect1, rect, parcel);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.view.IWindowSession");
                IWindow iwindow7 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                parcel = new Rect();
                getDisplayFrame(iwindow7, parcel);
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

            case 12: // '\f'
                parcel.enforceInterface("android.view.IWindowSession");
                finishDrawing(IWindow.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.view.IWindowSession");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                setInTouchMode(flag2);
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.view.IWindowSession");
                boolean flag3 = getInTouchMode();
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.view.IWindowSession");
                IWindow iwindow8 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                flag4 = performHapticFeedback(iwindow8, i, flag4);
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.view.IWindowSession");
                Object obj = IWindow.Stub.asInterface(parcel.readStrongBinder());
                int j1 = parcel.readInt();
                j = parcel.readInt();
                i = parcel.readInt();
                parcel = new Surface();
                obj = prepareDrag(((IWindow) (obj)), j1, j, i, parcel);
                parcel1.writeNoException();
                parcel1.writeStrongBinder(((IBinder) (obj)));
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
                parcel.enforceInterface("android.view.IWindowSession");
                IWindow iwindow5 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                IBinder ibinder2 = parcel.readStrongBinder();
                i = parcel.readInt();
                float f = parcel.readFloat();
                float f1 = parcel.readFloat();
                float f2 = parcel.readFloat();
                float f3 = parcel.readFloat();
                boolean flag5;
                if(parcel.readInt() != 0)
                    parcel = (ClipData)ClipData.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag5 = performDrag(iwindow5, ibinder2, i, f, f1, f2, f3, parcel);
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.view.IWindowSession");
                IWindow iwindow9 = IWindow.Stub.asInterface(parcel.readStrongBinder());
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                reportDropResult(iwindow9, flag6);
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.view.IWindowSession");
                cancelDragAndDrop(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.view.IWindowSession");
                dragRecipientEntered(IWindow.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.view.IWindowSession");
                dragRecipientExited(IWindow.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.view.IWindowSession");
                setWallpaperPosition(parcel.readStrongBinder(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.view.IWindowSession");
                wallpaperOffsetsComplete(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.view.IWindowSession");
                setWallpaperDisplayOffset(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.view.IWindowSession");
                IBinder ibinder = parcel.readStrongBinder();
                String s = parcel.readString();
                int k1 = parcel.readInt();
                i = parcel.readInt();
                j = parcel.readInt();
                Bundle bundle;
                boolean flag7;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                parcel = sendWallpaperCommand(ibinder, s, k1, i, j, bundle, flag7);
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
                parcel.enforceInterface("android.view.IWindowSession");
                IBinder ibinder3 = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                wallpaperCommandComplete(ibinder3, parcel);
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.view.IWindowSession");
                IBinder ibinder4 = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onRectangleOnScreenRequested(ibinder4, parcel);
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.view.IWindowSession");
                parcel = getWindowId(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.view.IWindowSession");
                pokeDrawLock(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.view.IWindowSession");
                boolean flag8 = startMovingTask(IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.readFloat(), parcel.readFloat());
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.view.IWindowSession");
                updatePointerIcon(IWindow.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.view.IWindowSession";
        static final int TRANSACTION_add = 1;
        static final int TRANSACTION_addToDisplay = 2;
        static final int TRANSACTION_addToDisplayWithoutInputChannel = 4;
        static final int TRANSACTION_addWithoutInputChannel = 3;
        static final int TRANSACTION_cancelDragAndDrop = 19;
        static final int TRANSACTION_dragRecipientEntered = 20;
        static final int TRANSACTION_dragRecipientExited = 21;
        static final int TRANSACTION_finishDrawing = 12;
        static final int TRANSACTION_getDisplayFrame = 11;
        static final int TRANSACTION_getInTouchMode = 14;
        static final int TRANSACTION_getWindowId = 28;
        static final int TRANSACTION_onRectangleOnScreenRequested = 27;
        static final int TRANSACTION_outOfMemory = 8;
        static final int TRANSACTION_performDrag = 17;
        static final int TRANSACTION_performHapticFeedback = 15;
        static final int TRANSACTION_pokeDrawLock = 29;
        static final int TRANSACTION_prepareDrag = 16;
        static final int TRANSACTION_prepareToReplaceWindows = 7;
        static final int TRANSACTION_relayout = 6;
        static final int TRANSACTION_remove = 5;
        static final int TRANSACTION_reportDropResult = 18;
        static final int TRANSACTION_sendWallpaperCommand = 25;
        static final int TRANSACTION_setInTouchMode = 13;
        static final int TRANSACTION_setInsets = 10;
        static final int TRANSACTION_setTransparentRegion = 9;
        static final int TRANSACTION_setWallpaperDisplayOffset = 24;
        static final int TRANSACTION_setWallpaperPosition = 22;
        static final int TRANSACTION_startMovingTask = 30;
        static final int TRANSACTION_updatePointerIcon = 31;
        static final int TRANSACTION_wallpaperCommandComplete = 26;
        static final int TRANSACTION_wallpaperOffsetsComplete = 23;

        public Stub()
        {
            attachInterface(this, "android.view.IWindowSession");
        }
    }

    private static class Stub.Proxy
        implements IWindowSession
    {

        public int add(IWindow iwindow, int i, WindowManager.LayoutParams layoutparams, int j, Rect rect, Rect rect1, InputChannel inputchannel)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(layoutparams == null)
                break MISSING_BLOCK_LABEL_153;
            parcel.writeInt(1);
            layoutparams.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                rect.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                rect1.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                inputchannel.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public int addToDisplay(IWindow iwindow, int i, WindowManager.LayoutParams layoutparams, int j, int k, Rect rect, Rect rect1, 
                Rect rect2, InputChannel inputchannel)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(layoutparams == null)
                break MISSING_BLOCK_LABEL_175;
            parcel.writeInt(1);
            layoutparams.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                rect.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                rect1.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                rect2.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                inputchannel.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public int addToDisplayWithoutInputChannel(IWindow iwindow, int i, WindowManager.LayoutParams layoutparams, int j, int k, Rect rect, Rect rect1)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(layoutparams == null)
                break MISSING_BLOCK_LABEL_145;
            parcel.writeInt(1);
            layoutparams.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                rect.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                rect1.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public int addWithoutInputChannel(IWindow iwindow, int i, WindowManager.LayoutParams layoutparams, int j, Rect rect, Rect rect1)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(layoutparams == null)
                break MISSING_BLOCK_LABEL_138;
            parcel.writeInt(1);
            layoutparams.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                rect.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                rect1.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancelDragAndDrop(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            parcel.writeStrongBinder(ibinder);
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

        public void dragRecipientEntered(IWindow iwindow)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public void dragRecipientExited(IWindow iwindow)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public void finishDrawing(IWindow iwindow)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public void getDisplayFrame(IWindow iwindow, Rect rect)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() != 0)
                rect.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return;
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public boolean getInTouchMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowSession");
            mRemote.transact(14, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "android.view.IWindowSession";
        }

        public IWindowId getWindowId(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = IWindowId.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void onRectangleOnScreenRequested(IBinder ibinder, Rect rect)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            parcel.writeStrongBinder(ibinder);
            if(rect == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(27, parcel, parcel1, 0);
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

        public boolean outOfMemory(IWindow iwindow)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwindow.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(8, parcel, parcel1, 0);
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
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public boolean performDrag(IWindow iwindow, IBinder ibinder, int i, float f, float f1, float f2, float f3, 
                ClipData clipdata)
            throws RemoteException
        {
            IBinder ibinder1;
            Parcel parcel;
            Parcel parcel1;
            ibinder1 = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder1 = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder1);
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeFloat(f);
            parcel.writeFloat(f1);
            parcel.writeFloat(f2);
            parcel.writeFloat(f3);
            if(clipdata == null)
                break MISSING_BLOCK_LABEL_146;
            parcel.writeInt(1);
            clipdata.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(17, parcel, parcel1, 0);
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
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public boolean performHapticFeedback(IWindow iwindow, int i, boolean flag)
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
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public void pokeDrawLock(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public IBinder prepareDrag(IWindow iwindow, int i, int j, int k, Surface surface)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            iwindow = parcel1.readStrongBinder();
            if(parcel1.readInt() != 0)
                surface.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return iwindow;
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public void prepareToReplaceWindows(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int relayout(IWindow iwindow, int i, WindowManager.LayoutParams layoutparams, int j, int k, int l, int i1, 
                Rect rect, Rect rect1, Rect rect2, Rect rect3, Rect rect4, Rect rect5, Rect rect6, 
                MergedConfiguration mergedconfiguration, Surface surface)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null) goto _L2; else goto _L1
_L1:
            iwindow = iwindow.asBinder();
_L5:
            parcel.writeStrongBinder(iwindow);
            parcel.writeInt(i);
            if(layoutparams == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            layoutparams.writeToParcel(parcel, 0);
_L6:
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                rect.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                rect1.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                rect2.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                rect3.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                rect4.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                rect5.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                rect6.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                mergedconfiguration.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                surface.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            iwindow = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
              goto _L5
        }

        public void remove(IWindow iwindow)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public void reportDropResult(IWindow iwindow, boolean flag)
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
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_33;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public Bundle sendWallpaperCommand(IBinder ibinder, String s, int i, int j, int k, Bundle bundle, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L3:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_168;
            ibinder = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
_L2:
            parcel.writeInt(0);
              goto _L3
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
            ibinder = null;
              goto _L4
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
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(flag)
                i = 1;
            parcel.writeInt(i);
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

        public void setInsets(IWindow iwindow, int i, Rect rect, Rect rect1, Region region)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(rect == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L5:
            if(rect1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            rect1.writeToParcel(parcel, 0);
_L6:
            if(region == null)
                break MISSING_BLOCK_LABEL_164;
            parcel.writeInt(1);
            region.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void setTransparentRegion(IWindow iwindow, Region region)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(region == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            region.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public void setWallpaperDisplayOffset(IBinder ibinder, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public void setWallpaperPosition(IBinder ibinder, float f, float f1, float f2, float f3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            parcel.writeStrongBinder(ibinder);
            parcel.writeFloat(f);
            parcel.writeFloat(f1);
            parcel.writeFloat(f2);
            parcel.writeFloat(f3);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean startMovingTask(IWindow iwindow, float f, float f1)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iwindow.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            parcel.writeFloat(f);
            parcel.writeFloat(f1);
            mRemote.transact(30, parcel, parcel1, 0);
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
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public void updatePointerIcon(IWindow iwindow)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public void wallpaperCommandComplete(IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(26, parcel, parcel1, 0);
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

        public void wallpaperOffsetsComplete(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSession");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
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


    public abstract int add(IWindow iwindow, int i, WindowManager.LayoutParams layoutparams, int j, Rect rect, Rect rect1, InputChannel inputchannel)
        throws RemoteException;

    public abstract int addToDisplay(IWindow iwindow, int i, WindowManager.LayoutParams layoutparams, int j, int k, Rect rect, Rect rect1, 
            Rect rect2, InputChannel inputchannel)
        throws RemoteException;

    public abstract int addToDisplayWithoutInputChannel(IWindow iwindow, int i, WindowManager.LayoutParams layoutparams, int j, int k, Rect rect, Rect rect1)
        throws RemoteException;

    public abstract int addWithoutInputChannel(IWindow iwindow, int i, WindowManager.LayoutParams layoutparams, int j, Rect rect, Rect rect1)
        throws RemoteException;

    public abstract void cancelDragAndDrop(IBinder ibinder)
        throws RemoteException;

    public abstract void dragRecipientEntered(IWindow iwindow)
        throws RemoteException;

    public abstract void dragRecipientExited(IWindow iwindow)
        throws RemoteException;

    public abstract void finishDrawing(IWindow iwindow)
        throws RemoteException;

    public abstract void getDisplayFrame(IWindow iwindow, Rect rect)
        throws RemoteException;

    public abstract boolean getInTouchMode()
        throws RemoteException;

    public abstract IWindowId getWindowId(IBinder ibinder)
        throws RemoteException;

    public abstract void onRectangleOnScreenRequested(IBinder ibinder, Rect rect)
        throws RemoteException;

    public abstract boolean outOfMemory(IWindow iwindow)
        throws RemoteException;

    public abstract boolean performDrag(IWindow iwindow, IBinder ibinder, int i, float f, float f1, float f2, float f3, 
            ClipData clipdata)
        throws RemoteException;

    public abstract boolean performHapticFeedback(IWindow iwindow, int i, boolean flag)
        throws RemoteException;

    public abstract void pokeDrawLock(IBinder ibinder)
        throws RemoteException;

    public abstract IBinder prepareDrag(IWindow iwindow, int i, int j, int k, Surface surface)
        throws RemoteException;

    public abstract void prepareToReplaceWindows(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract int relayout(IWindow iwindow, int i, WindowManager.LayoutParams layoutparams, int j, int k, int l, int i1, 
            Rect rect, Rect rect1, Rect rect2, Rect rect3, Rect rect4, Rect rect5, Rect rect6, 
            MergedConfiguration mergedconfiguration, Surface surface)
        throws RemoteException;

    public abstract void remove(IWindow iwindow)
        throws RemoteException;

    public abstract void reportDropResult(IWindow iwindow, boolean flag)
        throws RemoteException;

    public abstract Bundle sendWallpaperCommand(IBinder ibinder, String s, int i, int j, int k, Bundle bundle, boolean flag)
        throws RemoteException;

    public abstract void setInTouchMode(boolean flag)
        throws RemoteException;

    public abstract void setInsets(IWindow iwindow, int i, Rect rect, Rect rect1, Region region)
        throws RemoteException;

    public abstract void setTransparentRegion(IWindow iwindow, Region region)
        throws RemoteException;

    public abstract void setWallpaperDisplayOffset(IBinder ibinder, int i, int j)
        throws RemoteException;

    public abstract void setWallpaperPosition(IBinder ibinder, float f, float f1, float f2, float f3)
        throws RemoteException;

    public abstract boolean startMovingTask(IWindow iwindow, float f, float f1)
        throws RemoteException;

    public abstract void updatePointerIcon(IWindow iwindow)
        throws RemoteException;

    public abstract void wallpaperCommandComplete(IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void wallpaperOffsetsComplete(IBinder ibinder)
        throws RemoteException;
}
