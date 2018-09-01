// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.content.res.AssetFileDescriptor;
import android.database.*;
import android.net.Uri;
import android.os.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.content:
//            IContentProvider, OperationApplicationException, ContentProviderOperation, ContentProviderResult, 
//            ContentValues

final class ContentProviderProxy
    implements IContentProvider
{

    public ContentProviderProxy(IBinder ibinder)
    {
        mRemote = ibinder;
    }

    public ContentProviderResult[] applyBatch(String s, ArrayList arraylist)
        throws RemoteException, OperationApplicationException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        parcel.writeInt(arraylist.size());
        for(s = arraylist.iterator(); s.hasNext(); ((ContentProviderOperation)s.next()).writeToParcel(parcel, 0));
        break MISSING_BLOCK_LABEL_73;
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
        mRemote.transact(20, parcel, parcel1, 0);
        DatabaseUtils.readExceptionWithOperationApplicationExceptionFromParcel(parcel1);
        s = (ContentProviderResult[])parcel1.createTypedArray(ContentProviderResult.CREATOR);
        parcel.recycle();
        parcel1.recycle();
        return s;
    }

    public IBinder asBinder()
    {
        return mRemote;
    }

    public int bulkInsert(String s, Uri uri, ContentValues acontentvalues[])
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        int i;
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        uri.writeToParcel(parcel, 0);
        parcel.writeTypedArray(acontentvalues, 0);
        mRemote.transact(13, parcel, parcel1, 0);
        DatabaseUtils.readExceptionFromParcel(parcel1);
        i = parcel1.readInt();
        parcel.recycle();
        parcel1.recycle();
        return i;
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
    }

    public Bundle call(String s, String s1, String s2, Bundle bundle)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        parcel.writeString(s1);
        parcel.writeString(s2);
        parcel.writeBundle(bundle);
        mRemote.transact(21, parcel, parcel1, 0);
        DatabaseUtils.readExceptionFromParcel(parcel1);
        s = parcel1.readBundle();
        parcel.recycle();
        parcel1.recycle();
        return s;
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
    }

    public Uri canonicalize(String s, Uri uri)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        uri.writeToParcel(parcel, 0);
        mRemote.transact(25, parcel, parcel1, 0);
        DatabaseUtils.readExceptionFromParcel(parcel1);
        s = (Uri)Uri.CREATOR.createFromParcel(parcel1);
        parcel.recycle();
        parcel1.recycle();
        return s;
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
    }

    public ICancellationSignal createCancellationSignal()
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        ICancellationSignal icancellationsignal;
        parcel.writeInterfaceToken("android.content.IContentProvider");
        mRemote.transact(24, parcel, parcel1, 0);
        DatabaseUtils.readExceptionFromParcel(parcel1);
        icancellationsignal = android.os.ICancellationSignal.Stub.asInterface(parcel1.readStrongBinder());
        parcel.recycle();
        parcel1.recycle();
        return icancellationsignal;
        Exception exception;
        exception;
        parcel.recycle();
        parcel1.recycle();
        throw exception;
    }

    public int delete(String s, Uri uri, String s1, String as[])
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        int i;
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        uri.writeToParcel(parcel, 0);
        parcel.writeString(s1);
        parcel.writeStringArray(as);
        mRemote.transact(4, parcel, parcel1, 0);
        DatabaseUtils.readExceptionFromParcel(parcel1);
        i = parcel1.readInt();
        parcel.recycle();
        parcel1.recycle();
        return i;
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
    }

    public String[] getStreamTypes(Uri uri, String s)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.content.IContentProvider");
        uri.writeToParcel(parcel, 0);
        parcel.writeString(s);
        mRemote.transact(22, parcel, parcel1, 0);
        DatabaseUtils.readExceptionFromParcel(parcel1);
        uri = parcel1.createStringArray();
        parcel.recycle();
        parcel1.recycle();
        return uri;
        uri;
        parcel.recycle();
        parcel1.recycle();
        throw uri;
    }

    public String getType(Uri uri)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.content.IContentProvider");
        uri.writeToParcel(parcel, 0);
        mRemote.transact(2, parcel, parcel1, 0);
        DatabaseUtils.readExceptionFromParcel(parcel1);
        uri = parcel1.readString();
        parcel.recycle();
        parcel1.recycle();
        return uri;
        uri;
        parcel.recycle();
        parcel1.recycle();
        throw uri;
    }

    public Uri insert(String s, Uri uri, ContentValues contentvalues)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        uri.writeToParcel(parcel, 0);
        contentvalues.writeToParcel(parcel, 0);
        mRemote.transact(3, parcel, parcel1, 0);
        DatabaseUtils.readExceptionFromParcel(parcel1);
        s = (Uri)Uri.CREATOR.createFromParcel(parcel1);
        parcel.recycle();
        parcel1.recycle();
        return s;
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
    }

    public AssetFileDescriptor openAssetFile(String s, Uri uri, String s1, ICancellationSignal icancellationsignal)
        throws RemoteException, FileNotFoundException
    {
        Object obj;
        Parcel parcel;
        Parcel parcel1;
        obj = null;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        uri.writeToParcel(parcel, 0);
        parcel.writeString(s1);
        s = obj;
        if(icancellationsignal == null)
            break MISSING_BLOCK_LABEL_55;
        s = icancellationsignal.asBinder();
        parcel.writeStrongBinder(s);
        mRemote.transact(15, parcel, parcel1, 0);
        DatabaseUtils.readExceptionWithFileNotFoundExceptionFromParcel(parcel1);
        if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
        s = (AssetFileDescriptor)AssetFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
        parcel.recycle();
        parcel1.recycle();
        return s;
_L2:
        s = null;
        if(true) goto _L4; else goto _L3
_L3:
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
    }

    public ParcelFileDescriptor openFile(String s, Uri uri, String s1, ICancellationSignal icancellationsignal, IBinder ibinder)
        throws RemoteException, FileNotFoundException
    {
        Object obj;
        Parcel parcel;
        Parcel parcel1;
        obj = null;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        uri.writeToParcel(parcel, 0);
        parcel.writeString(s1);
        s = obj;
        if(icancellationsignal == null)
            break MISSING_BLOCK_LABEL_55;
        s = icancellationsignal.asBinder();
        parcel.writeStrongBinder(s);
        parcel.writeStrongBinder(ibinder);
        mRemote.transact(14, parcel, parcel1, 0);
        DatabaseUtils.readExceptionWithFileNotFoundExceptionFromParcel(parcel1);
        if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
        s = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
        parcel.recycle();
        parcel1.recycle();
        return s;
_L2:
        s = null;
        if(true) goto _L4; else goto _L3
_L3:
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
    }

    public AssetFileDescriptor openTypedAssetFile(String s, Uri uri, String s1, Bundle bundle, ICancellationSignal icancellationsignal)
        throws RemoteException, FileNotFoundException
    {
        Object obj;
        Parcel parcel;
        Parcel parcel1;
        obj = null;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        uri.writeToParcel(parcel, 0);
        parcel.writeString(s1);
        parcel.writeBundle(bundle);
        s = obj;
        if(icancellationsignal == null)
            break MISSING_BLOCK_LABEL_62;
        s = icancellationsignal.asBinder();
        parcel.writeStrongBinder(s);
        mRemote.transact(23, parcel, parcel1, 0);
        DatabaseUtils.readExceptionWithFileNotFoundExceptionFromParcel(parcel1);
        if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
        s = (AssetFileDescriptor)AssetFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
        parcel.recycle();
        parcel1.recycle();
        return s;
_L2:
        s = null;
        if(true) goto _L4; else goto _L3
_L3:
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
    }

    public Cursor query(String s, Uri uri, String as[], Bundle bundle, ICancellationSignal icancellationsignal)
        throws RemoteException
    {
        BulkCursorToCursorAdaptor bulkcursortocursoradaptor;
        Parcel parcel;
        Parcel parcel1;
        bulkcursortocursoradaptor = new BulkCursorToCursorAdaptor();
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        uri.writeToParcel(parcel, 0);
        int i;
        i = 0;
        if(as == null)
            break MISSING_BLOCK_LABEL_50;
        i = as.length;
        parcel.writeInt(i);
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        parcel.writeString(as[j]);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        parcel.writeBundle(bundle);
        parcel.writeStrongBinder(bulkcursortocursoradaptor.getObserver().asBinder());
        if(icancellationsignal == null) goto _L4; else goto _L3
_L3:
        s = icancellationsignal.asBinder();
_L9:
        parcel.writeStrongBinder(s);
        mRemote.transact(1, parcel, parcel1, 0);
        DatabaseUtils.readExceptionFromParcel(parcel1);
        if(parcel1.readInt() == 0) goto _L6; else goto _L5
_L5:
        as = (BulkCursorDescriptor)BulkCursorDescriptor.CREATOR.createFromParcel(parcel1);
        uri = mRemote;
        if(((BulkCursorDescriptor) (as)).cursor == null) goto _L8; else goto _L7
_L7:
        s = ((BulkCursorDescriptor) (as)).cursor.asBinder();
_L10:
        Binder.copyAllowBlocking(uri, s);
        bulkcursortocursoradaptor.initialize(as);
        s = bulkcursortocursoradaptor;
_L11:
        parcel.recycle();
        parcel1.recycle();
        return s;
_L4:
        s = null;
          goto _L9
_L8:
        s = null;
          goto _L10
_L6:
        bulkcursortocursoradaptor.close();
        s = null;
          goto _L11
        s;
        bulkcursortocursoradaptor.close();
        throw s;
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
        s;
        bulkcursortocursoradaptor.close();
        throw s;
          goto _L9
    }

    public boolean refresh(String s, Uri uri, Bundle bundle, ICancellationSignal icancellationsignal)
        throws RemoteException
    {
        Object obj;
        boolean flag;
        Parcel parcel;
        Parcel parcel1;
        obj = null;
        flag = false;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        uri.writeToParcel(parcel, 0);
        parcel.writeBundle(bundle);
        s = obj;
        if(icancellationsignal == null)
            break MISSING_BLOCK_LABEL_58;
        s = icancellationsignal.asBinder();
        int i;
        parcel.writeStrongBinder(s);
        mRemote.transact(27, parcel, parcel1, 0);
        DatabaseUtils.readExceptionFromParcel(parcel1);
        i = parcel1.readInt();
        if(i == 0)
            flag = true;
        parcel.recycle();
        parcel1.recycle();
        return flag;
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
    }

    public Uri uncanonicalize(String s, Uri uri)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        uri.writeToParcel(parcel, 0);
        mRemote.transact(26, parcel, parcel1, 0);
        DatabaseUtils.readExceptionFromParcel(parcel1);
        s = (Uri)Uri.CREATOR.createFromParcel(parcel1);
        parcel.recycle();
        parcel1.recycle();
        return s;
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
    }

    public int update(String s, Uri uri, ContentValues contentvalues, String s1, String as[])
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        int i;
        parcel.writeInterfaceToken("android.content.IContentProvider");
        parcel.writeString(s);
        uri.writeToParcel(parcel, 0);
        contentvalues.writeToParcel(parcel, 0);
        parcel.writeString(s1);
        parcel.writeStringArray(as);
        mRemote.transact(10, parcel, parcel1, 0);
        DatabaseUtils.readExceptionFromParcel(parcel1);
        i = parcel1.readInt();
        parcel.recycle();
        parcel1.recycle();
        return i;
        s;
        parcel.recycle();
        parcel1.recycle();
        throw s;
    }

    private IBinder mRemote;
}
