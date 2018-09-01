// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import android.os.*;
import android.printservice.PrintServiceInfo;
import android.printservice.recommendation.IRecommendationsChangeListener;
import android.printservice.recommendation.RecommendationInfo;
import java.util.List;

// Referenced classes of package android.print:
//            IPrintJobStateChangeListener, IPrintServicesChangeListener, PrintJobId, IPrinterDiscoveryObserver, 
//            PrinterId, PrintJobInfo, IPrintDocumentAdapter, PrintAttributes

public interface IPrintManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPrintManager
    {

        public static IPrintManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.print.IPrintManager");
            if(iinterface != null && (iinterface instanceof IPrintManager))
                return (IPrintManager)iinterface;
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
                parcel1.writeString("android.print.IPrintManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.print.IPrintManager");
                parcel = getPrintJobInfos(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.print.IPrintManager");
                PrintJobId printjobid;
                if(parcel.readInt() != 0)
                    printjobid = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    printjobid = null;
                parcel = getPrintJobInfo(printjobid, parcel.readInt(), parcel.readInt());
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

            case 3: // '\003'
                parcel.enforceInterface("android.print.IPrintManager");
                String s = parcel.readString();
                IPrintDocumentAdapter iprintdocumentadapter = IPrintDocumentAdapter.Stub.asInterface(parcel.readStrongBinder());
                PrintAttributes printattributes;
                if(parcel.readInt() != 0)
                    printattributes = (PrintAttributes)PrintAttributes.CREATOR.createFromParcel(parcel);
                else
                    printattributes = null;
                parcel = print(s, iprintdocumentadapter, printattributes, parcel.readString(), parcel.readInt(), parcel.readInt());
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

            case 4: // '\004'
                parcel.enforceInterface("android.print.IPrintManager");
                PrintJobId printjobid1;
                if(parcel.readInt() != 0)
                    printjobid1 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    printjobid1 = null;
                cancelPrintJob(printjobid1, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.print.IPrintManager");
                PrintJobId printjobid2;
                if(parcel.readInt() != 0)
                    printjobid2 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    printjobid2 = null;
                restartPrintJob(printjobid2, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.print.IPrintManager");
                addPrintJobStateChangeListener(IPrintJobStateChangeListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.print.IPrintManager");
                removePrintJobStateChangeListener(IPrintJobStateChangeListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.print.IPrintManager");
                addPrintServicesChangeListener(IPrintServicesChangeListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.print.IPrintManager");
                removePrintServicesChangeListener(IPrintServicesChangeListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.print.IPrintManager");
                parcel = getPrintServices(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.print.IPrintManager");
                ComponentName componentname;
                boolean flag;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setPrintServiceEnabled(componentname, flag, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.print.IPrintManager");
                addPrintServiceRecommendationsChangeListener(android.printservice.recommendation.IRecommendationsChangeListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.print.IPrintManager");
                removePrintServiceRecommendationsChangeListener(android.printservice.recommendation.IRecommendationsChangeListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.print.IPrintManager");
                parcel = getPrintServiceRecommendations(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.print.IPrintManager");
                createPrinterDiscoverySession(IPrinterDiscoveryObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.print.IPrintManager");
                startPrinterDiscovery(IPrinterDiscoveryObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.createTypedArrayList(PrinterId.CREATOR), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.print.IPrintManager");
                stopPrinterDiscovery(IPrinterDiscoveryObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.print.IPrintManager");
                validatePrinters(parcel.createTypedArrayList(PrinterId.CREATOR), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.print.IPrintManager");
                PrinterId printerid;
                if(parcel.readInt() != 0)
                    printerid = (PrinterId)PrinterId.CREATOR.createFromParcel(parcel);
                else
                    printerid = null;
                startPrinterStateTracking(printerid, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.print.IPrintManager");
                PrinterId printerid1;
                if(parcel.readInt() != 0)
                    printerid1 = (PrinterId)PrinterId.CREATOR.createFromParcel(parcel);
                else
                    printerid1 = null;
                parcel = getCustomPrinterIcon(printerid1, parcel.readInt());
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

            case 21: // '\025'
                parcel.enforceInterface("android.print.IPrintManager");
                PrinterId printerid2;
                if(parcel.readInt() != 0)
                    printerid2 = (PrinterId)PrinterId.CREATOR.createFromParcel(parcel);
                else
                    printerid2 = null;
                stopPrinterStateTracking(printerid2, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.print.IPrintManager");
                destroyPrinterDiscoverySession(IPrinterDiscoveryObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.print.IPrintManager";
        static final int TRANSACTION_addPrintJobStateChangeListener = 6;
        static final int TRANSACTION_addPrintServiceRecommendationsChangeListener = 12;
        static final int TRANSACTION_addPrintServicesChangeListener = 8;
        static final int TRANSACTION_cancelPrintJob = 4;
        static final int TRANSACTION_createPrinterDiscoverySession = 15;
        static final int TRANSACTION_destroyPrinterDiscoverySession = 22;
        static final int TRANSACTION_getCustomPrinterIcon = 20;
        static final int TRANSACTION_getPrintJobInfo = 2;
        static final int TRANSACTION_getPrintJobInfos = 1;
        static final int TRANSACTION_getPrintServiceRecommendations = 14;
        static final int TRANSACTION_getPrintServices = 10;
        static final int TRANSACTION_print = 3;
        static final int TRANSACTION_removePrintJobStateChangeListener = 7;
        static final int TRANSACTION_removePrintServiceRecommendationsChangeListener = 13;
        static final int TRANSACTION_removePrintServicesChangeListener = 9;
        static final int TRANSACTION_restartPrintJob = 5;
        static final int TRANSACTION_setPrintServiceEnabled = 11;
        static final int TRANSACTION_startPrinterDiscovery = 16;
        static final int TRANSACTION_startPrinterStateTracking = 19;
        static final int TRANSACTION_stopPrinterDiscovery = 17;
        static final int TRANSACTION_stopPrinterStateTracking = 21;
        static final int TRANSACTION_validatePrinters = 18;

        public Stub()
        {
            attachInterface(this, "android.print.IPrintManager");
        }
    }

    private static class Stub.Proxy
        implements IPrintManager
    {

        public void addPrintJobStateChangeListener(IPrintJobStateChangeListener iprintjobstatechangelistener, int i, int j)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(iprintjobstatechangelistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iprintjobstatechangelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iprintjobstatechangelistener;
            parcel1.recycle();
            parcel.recycle();
            throw iprintjobstatechangelistener;
        }

        public void addPrintServiceRecommendationsChangeListener(IRecommendationsChangeListener irecommendationschangelistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(irecommendationschangelistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = irecommendationschangelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            irecommendationschangelistener;
            parcel1.recycle();
            parcel.recycle();
            throw irecommendationschangelistener;
        }

        public void addPrintServicesChangeListener(IPrintServicesChangeListener iprintserviceschangelistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(iprintserviceschangelistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iprintserviceschangelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iprintserviceschangelistener;
            parcel1.recycle();
            parcel.recycle();
            throw iprintserviceschangelistener;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancelPrintJob(PrintJobId printjobid, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(printjobid == null)
                break MISSING_BLOCK_LABEL_78;
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobid;
            parcel1.recycle();
            parcel.recycle();
            throw printjobid;
        }

        public void createPrinterDiscoverySession(IPrinterDiscoveryObserver iprinterdiscoveryobserver, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(iprinterdiscoveryobserver == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iprinterdiscoveryobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iprinterdiscoveryobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iprinterdiscoveryobserver;
        }

        public void destroyPrinterDiscoverySession(IPrinterDiscoveryObserver iprinterdiscoveryobserver, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(iprinterdiscoveryobserver == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iprinterdiscoveryobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iprinterdiscoveryobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iprinterdiscoveryobserver;
        }

        public Icon getCustomPrinterIcon(PrinterId printerid, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(printerid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printerid.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_109;
            printerid = (Icon)Icon.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return printerid;
_L2:
            parcel.writeInt(0);
              goto _L3
            printerid;
            parcel1.recycle();
            parcel.recycle();
            throw printerid;
            printerid = null;
              goto _L4
        }

        public String getInterfaceDescriptor()
        {
            return "android.print.IPrintManager";
        }

        public PrintJobInfo getPrintJobInfo(PrintJobId printjobid, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(printjobid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_123;
            printjobid = (PrintJobInfo)PrintJobInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return printjobid;
_L2:
            parcel.writeInt(0);
              goto _L3
            printjobid;
            parcel1.recycle();
            parcel.recycle();
            throw printjobid;
            printjobid = null;
              goto _L4
        }

        public List getPrintJobInfos(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.print.IPrintManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(PrintJobInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getPrintServiceRecommendations(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.print.IPrintManager");
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(RecommendationInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getPrintServices(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.print.IPrintManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(PrintServiceInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Bundle print(String s, IPrintDocumentAdapter iprintdocumentadapter, PrintAttributes printattributes, String s1, int i, int j)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            parcel.writeString(s);
            s = obj;
            if(iprintdocumentadapter == null)
                break MISSING_BLOCK_LABEL_40;
            s = iprintdocumentadapter.asBinder();
            parcel.writeStrongBinder(s);
            if(printattributes == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printattributes.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_161;
            s = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            s = null;
              goto _L4
        }

        public void removePrintJobStateChangeListener(IPrintJobStateChangeListener iprintjobstatechangelistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(iprintjobstatechangelistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iprintjobstatechangelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iprintjobstatechangelistener;
            parcel1.recycle();
            parcel.recycle();
            throw iprintjobstatechangelistener;
        }

        public void removePrintServiceRecommendationsChangeListener(IRecommendationsChangeListener irecommendationschangelistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(irecommendationschangelistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = irecommendationschangelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            irecommendationschangelistener;
            parcel1.recycle();
            parcel.recycle();
            throw irecommendationschangelistener;
        }

        public void removePrintServicesChangeListener(IPrintServicesChangeListener iprintserviceschangelistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(iprintserviceschangelistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iprintserviceschangelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iprintserviceschangelistener;
            parcel1.recycle();
            parcel.recycle();
            throw iprintserviceschangelistener;
        }

        public void restartPrintJob(PrintJobId printjobid, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(printjobid == null)
                break MISSING_BLOCK_LABEL_78;
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobid;
            parcel1.recycle();
            parcel.recycle();
            throw printjobid;
        }

        public void setPrintServiceEnabled(ComponentName componentname, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void startPrinterDiscovery(IPrinterDiscoveryObserver iprinterdiscoveryobserver, List list, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(iprinterdiscoveryobserver == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iprinterdiscoveryobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeTypedList(list);
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iprinterdiscoveryobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iprinterdiscoveryobserver;
        }

        public void startPrinterStateTracking(PrinterId printerid, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(printerid == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            printerid.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printerid;
            parcel1.recycle();
            parcel.recycle();
            throw printerid;
        }

        public void stopPrinterDiscovery(IPrinterDiscoveryObserver iprinterdiscoveryobserver, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(iprinterdiscoveryobserver == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iprinterdiscoveryobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iprinterdiscoveryobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iprinterdiscoveryobserver;
        }

        public void stopPrinterStateTracking(PrinterId printerid, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            if(printerid == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            printerid.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printerid;
            parcel1.recycle();
            parcel.recycle();
            throw printerid;
        }

        public void validatePrinters(List list, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintManager");
            parcel.writeTypedList(list);
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addPrintJobStateChangeListener(IPrintJobStateChangeListener iprintjobstatechangelistener, int i, int j)
        throws RemoteException;

    public abstract void addPrintServiceRecommendationsChangeListener(IRecommendationsChangeListener irecommendationschangelistener, int i)
        throws RemoteException;

    public abstract void addPrintServicesChangeListener(IPrintServicesChangeListener iprintserviceschangelistener, int i)
        throws RemoteException;

    public abstract void cancelPrintJob(PrintJobId printjobid, int i, int j)
        throws RemoteException;

    public abstract void createPrinterDiscoverySession(IPrinterDiscoveryObserver iprinterdiscoveryobserver, int i)
        throws RemoteException;

    public abstract void destroyPrinterDiscoverySession(IPrinterDiscoveryObserver iprinterdiscoveryobserver, int i)
        throws RemoteException;

    public abstract Icon getCustomPrinterIcon(PrinterId printerid, int i)
        throws RemoteException;

    public abstract PrintJobInfo getPrintJobInfo(PrintJobId printjobid, int i, int j)
        throws RemoteException;

    public abstract List getPrintJobInfos(int i, int j)
        throws RemoteException;

    public abstract List getPrintServiceRecommendations(int i)
        throws RemoteException;

    public abstract List getPrintServices(int i, int j)
        throws RemoteException;

    public abstract Bundle print(String s, IPrintDocumentAdapter iprintdocumentadapter, PrintAttributes printattributes, String s1, int i, int j)
        throws RemoteException;

    public abstract void removePrintJobStateChangeListener(IPrintJobStateChangeListener iprintjobstatechangelistener, int i)
        throws RemoteException;

    public abstract void removePrintServiceRecommendationsChangeListener(IRecommendationsChangeListener irecommendationschangelistener, int i)
        throws RemoteException;

    public abstract void removePrintServicesChangeListener(IPrintServicesChangeListener iprintserviceschangelistener, int i)
        throws RemoteException;

    public abstract void restartPrintJob(PrintJobId printjobid, int i, int j)
        throws RemoteException;

    public abstract void setPrintServiceEnabled(ComponentName componentname, boolean flag, int i)
        throws RemoteException;

    public abstract void startPrinterDiscovery(IPrinterDiscoveryObserver iprinterdiscoveryobserver, List list, int i)
        throws RemoteException;

    public abstract void startPrinterStateTracking(PrinterId printerid, int i)
        throws RemoteException;

    public abstract void stopPrinterDiscovery(IPrinterDiscoveryObserver iprinterdiscoveryobserver, int i)
        throws RemoteException;

    public abstract void stopPrinterStateTracking(PrinterId printerid, int i)
        throws RemoteException;

    public abstract void validatePrinters(List list, int i)
        throws RemoteException;
}
