// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.content.res.AssetFileDescriptor;
import android.database.*;
import android.net.Uri;
import android.os.*;
import java.util.ArrayList;

// Referenced classes of package android.content:
//            IContentProvider, ContentProviderProxy, ContentValues, ContentProviderOperation

public abstract class ContentProviderNative extends Binder
    implements IContentProvider
{

    public ContentProviderNative()
    {
        attachInterface(this, "android.content.IContentProvider");
    }

    public static IContentProvider asInterface(IBinder ibinder)
    {
        if(ibinder == null)
            return null;
        IContentProvider icontentprovider = (IContentProvider)ibinder.queryLocalInterface("android.content.IContentProvider");
        if(icontentprovider != null)
            return icontentprovider;
        else
            return new ContentProviderProxy(ibinder);
    }

    public IBinder asBinder()
    {
        return this;
    }

    public abstract String getProviderName();

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        i;
        JVM INSTR tableswitch 1 27: default 124
    //                   1 134
    //                   2 366
    //                   3 400
    //                   4 581
    //                   5 124
    //                   6 124
    //                   7 124
    //                   8 124
    //                   9 124
    //                   10 627
    //                   11 124
    //                   12 124
    //                   13 450
    //                   14 685
    //                   15 756
    //                   16 124
    //                   17 124
    //                   18 124
    //                   19 124
    //                   20 498
    //                   21 823
    //                   22 861
    //                   23 899
    //                   24 970
    //                   25 997
    //                   26 1035
    //                   27 1073;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L1 _L1 _L1 _L1 _L6 _L1 _L1 _L7 _L8 _L9 _L1 _L1 _L1 _L1 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17
_L1:
        return super.onTransact(i, parcel, parcel1, j);
_L2:
        Object obj1;
        Object obj2;
        android.database.IContentObserver icontentobserver;
        Object obj;
        Uri uri;
        String as[];
        String as1[];
        try
        {
            parcel.enforceInterface("android.content.IContentProvider");
            obj = parcel.readString();
            uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
            j = parcel.readInt();
        }
        // Misplaced declaration of an exception variable
        catch(Parcel parcel)
        {
            DatabaseUtils.writeExceptionToParcel(parcel1, parcel);
            return true;
        }
        as = null;
        if(j <= 0) goto _L19; else goto _L18
_L18:
        as1 = new String[j];
        i = 0;
_L20:
        as = as1;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        as1[i] = parcel.readString();
        i++;
        if(true) goto _L20; else goto _L19
_L19:
        obj2 = parcel.readBundle();
        icontentobserver = android.database.IContentObserver.Stub.asInterface(parcel.readStrongBinder());
        obj1 = query(((String) (obj)), uri, as, ((android.os.Bundle) (obj2)), android.os.ICancellationSignal.Stub.asInterface(parcel.readStrongBinder()));
        if(obj1 == null) goto _L22; else goto _L21
_L21:
        parcel = ((Parcel) (obj1));
        obj2 = JVM INSTR new #92  <Class CursorToBulkCursorAdaptor>;
        parcel = ((Parcel) (obj1));
        ((CursorToBulkCursorAdaptor) (obj2)).CursorToBulkCursorAdaptor(((Cursor) (obj1)), icontentobserver, getProviderName());
        icontentobserver = null;
        obj1 = null;
        obj = ((CursorToBulkCursorAdaptor) (obj2)).getBulkCursorDescriptor();
        parcel = ((Parcel) (obj1));
        parcel1.writeNoException();
        parcel = ((Parcel) (obj1));
        parcel1.writeInt(1);
        parcel = ((Parcel) (obj1));
        ((BulkCursorDescriptor) (obj)).writeToParcel(parcel1, 1);
_L27:
        return true;
        icontentobserver = null;
        obj1;
        obj2 = parcel;
        parcel = icontentobserver;
_L39:
        if(parcel == null) goto _L24; else goto _L23
_L23:
        parcel.close();
_L24:
        if(obj2 == null) goto _L26; else goto _L25
_L25:
        ((Cursor) (obj2)).close();
_L26:
        throw obj1;
_L22:
        parcel1.writeNoException();
        parcel1.writeInt(0);
          goto _L27
_L3:
        parcel.enforceInterface("android.content.IContentProvider");
        parcel = getType((Uri)Uri.CREATOR.createFromParcel(parcel));
        parcel1.writeNoException();
        parcel1.writeString(parcel);
        return true;
_L4:
        parcel.enforceInterface("android.content.IContentProvider");
        parcel = insert(parcel.readString(), (Uri)Uri.CREATOR.createFromParcel(parcel), (ContentValues)ContentValues.CREATOR.createFromParcel(parcel));
        parcel1.writeNoException();
        Uri.writeToParcel(parcel1, parcel);
        return true;
_L7:
        parcel.enforceInterface("android.content.IContentProvider");
        i = bulkInsert(parcel.readString(), (Uri)Uri.CREATOR.createFromParcel(parcel), (ContentValues[])parcel.createTypedArray(ContentValues.CREATOR));
        parcel1.writeNoException();
        parcel1.writeInt(i);
        return true;
_L10:
        parcel.enforceInterface("android.content.IContentProvider");
        obj1 = parcel.readString();
        j = parcel.readInt();
        obj2 = JVM INSTR new #155 <Class ArrayList>;
        ((ArrayList) (obj2)).ArrayList(j);
        i = 0;
_L29:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        ((ArrayList) (obj2)).add(i, (ContentProviderOperation)ContentProviderOperation.CREATOR.createFromParcel(parcel));
        i++;
        if(true) goto _L29; else goto _L28
_L28:
        parcel = applyBatch(((String) (obj1)), ((ArrayList) (obj2)));
        parcel1.writeNoException();
        parcel1.writeTypedArray(parcel, 0);
        return true;
_L5:
        parcel.enforceInterface("android.content.IContentProvider");
        i = delete(parcel.readString(), (Uri)Uri.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readStringArray());
        parcel1.writeNoException();
        parcel1.writeInt(i);
        return true;
_L6:
        parcel.enforceInterface("android.content.IContentProvider");
        i = update(parcel.readString(), (Uri)Uri.CREATOR.createFromParcel(parcel), (ContentValues)ContentValues.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readStringArray());
        parcel1.writeNoException();
        parcel1.writeInt(i);
        return true;
_L8:
        parcel.enforceInterface("android.content.IContentProvider");
        parcel = openFile(parcel.readString(), (Uri)Uri.CREATOR.createFromParcel(parcel), parcel.readString(), android.os.ICancellationSignal.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
        parcel1.writeNoException();
        if(parcel == null) goto _L31; else goto _L30
_L30:
        parcel1.writeInt(1);
        parcel.writeToParcel(parcel1, 1);
_L32:
        return true;
_L31:
        parcel1.writeInt(0);
          goto _L32
_L9:
        parcel.enforceInterface("android.content.IContentProvider");
        parcel = openAssetFile(parcel.readString(), (Uri)Uri.CREATOR.createFromParcel(parcel), parcel.readString(), android.os.ICancellationSignal.Stub.asInterface(parcel.readStrongBinder()));
        parcel1.writeNoException();
        if(parcel == null) goto _L34; else goto _L33
_L33:
        parcel1.writeInt(1);
        parcel.writeToParcel(parcel1, 1);
_L35:
        return true;
_L34:
        parcel1.writeInt(0);
          goto _L35
_L11:
        parcel.enforceInterface("android.content.IContentProvider");
        parcel = call(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readBundle());
        parcel1.writeNoException();
        parcel1.writeBundle(parcel);
        return true;
_L12:
        parcel.enforceInterface("android.content.IContentProvider");
        parcel = getStreamTypes((Uri)Uri.CREATOR.createFromParcel(parcel), parcel.readString());
        parcel1.writeNoException();
        parcel1.writeStringArray(parcel);
        return true;
_L13:
        parcel.enforceInterface("android.content.IContentProvider");
        parcel = openTypedAssetFile(parcel.readString(), (Uri)Uri.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readBundle(), android.os.ICancellationSignal.Stub.asInterface(parcel.readStrongBinder()));
        parcel1.writeNoException();
        if(parcel == null) goto _L37; else goto _L36
_L36:
        parcel1.writeInt(1);
        parcel.writeToParcel(parcel1, 1);
_L38:
        return true;
_L37:
        parcel1.writeInt(0);
          goto _L38
_L14:
        parcel.enforceInterface("android.content.IContentProvider");
        parcel = createCancellationSignal();
        parcel1.writeNoException();
        parcel1.writeStrongBinder(parcel.asBinder());
        return true;
_L15:
        parcel.enforceInterface("android.content.IContentProvider");
        parcel = canonicalize(parcel.readString(), (Uri)Uri.CREATOR.createFromParcel(parcel));
        parcel1.writeNoException();
        Uri.writeToParcel(parcel1, parcel);
        return true;
_L16:
        parcel.enforceInterface("android.content.IContentProvider");
        parcel = uncanonicalize(parcel.readString(), (Uri)Uri.CREATOR.createFromParcel(parcel));
        parcel1.writeNoException();
        Uri.writeToParcel(parcel1, parcel);
        return true;
_L17:
        boolean flag;
        parcel.enforceInterface("android.content.IContentProvider");
        flag = refresh(parcel.readString(), (Uri)Uri.CREATOR.createFromParcel(parcel), parcel.readBundle(), android.os.ICancellationSignal.Stub.asInterface(parcel.readStrongBinder()));
        parcel1.writeNoException();
        if(flag)
            i = 0;
        else
            i = -1;
        parcel1.writeInt(i);
        return true;
        obj1;
        parcel = ((Parcel) (obj2));
        obj2 = icontentobserver;
          goto _L39
    }
}
