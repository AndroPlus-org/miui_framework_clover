// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

abstract class BaseParceledListSlice
    implements Parcelable
{

    static boolean _2D_get0()
    {
        return DEBUG;
    }

    static String _2D_get1()
    {
        return TAG;
    }

    static List _2D_get2(BaseParceledListSlice baseparceledlistslice)
    {
        return baseparceledlistslice.mList;
    }

    static void _2D_wrap0(Class class1, Class class2)
    {
        verifySameType(class1, class2);
    }

    BaseParceledListSlice(Parcel parcel, ClassLoader classloader)
    {
        mInlineCountLimit = 0x7fffffff;
        int i = parcel.readInt();
        mList = new ArrayList(i);
        if(DEBUG)
            Log.d(TAG, (new StringBuilder()).append("Retrieving ").append(i).append(" items").toString());
        if(i <= 0)
            return;
        android.os.Parcelable.Creator creator = readParcelableCreator(parcel, classloader);
        Class class1 = null;
        int j = 0;
        do
        {
            if(j >= i || parcel.readInt() == 0)
            {
                if(j >= i)
                    return;
                break;
            }
            Object obj = readCreator(creator, parcel, classloader);
            if(class1 == null)
                class1 = obj.getClass();
            else
                verifySameType(class1, obj.getClass());
            mList.add(obj);
            if(DEBUG)
                Log.d(TAG, (new StringBuilder()).append("Read inline #").append(j).append(": ").append(mList.get(mList.size() - 1)).toString());
            j++;
        } while(true);
        IBinder ibinder = parcel.readStrongBinder();
        while(j < i) 
        {
            if(DEBUG)
                Log.d(TAG, (new StringBuilder()).append("Reading more @").append(j).append(" of ").append(i).append(": retriever=").append(ibinder).toString());
            Parcel parcel1 = Parcel.obtain();
            Parcel parcel2 = Parcel.obtain();
            parcel1.writeInt(j);
            try
            {
                ibinder.transact(1, parcel1, parcel2, 0);
            }
            // Misplaced declaration of an exception variable
            catch(Parcel parcel)
            {
                Log.w(TAG, (new StringBuilder()).append("Failure retrieving array; only received ").append(j).append(" of ").append(i).toString(), parcel);
                return;
            }
            for(; j < i && parcel2.readInt() != 0; j++)
            {
                parcel = ((Parcel) (readCreator(creator, parcel2, classloader)));
                verifySameType(class1, parcel.getClass());
                mList.add(parcel);
                if(DEBUG)
                    Log.d(TAG, (new StringBuilder()).append("Read extra #").append(j).append(": ").append(mList.get(mList.size() - 1)).toString());
            }

            parcel2.recycle();
            parcel1.recycle();
        }
    }

    public BaseParceledListSlice(List list)
    {
        mInlineCountLimit = 0x7fffffff;
        mList = list;
    }

    private Object readCreator(android.os.Parcelable.Creator creator, Parcel parcel, ClassLoader classloader)
    {
        if(creator instanceof android.os.Parcelable.ClassLoaderCreator)
            return ((android.os.Parcelable.ClassLoaderCreator)creator).createFromParcel(parcel, classloader);
        else
            return creator.createFromParcel(parcel);
    }

    private static void verifySameType(Class class1, Class class2)
    {
        if(!class2.equals(class1))
            throw new IllegalArgumentException((new StringBuilder()).append("Can't unparcel type ").append(class2.getName()).append(" in list of type ").append(class1.getName()).toString());
        else
            return;
    }

    public List getList()
    {
        return mList;
    }

    protected abstract android.os.Parcelable.Creator readParcelableCreator(Parcel parcel, ClassLoader classloader);

    public void setInlineCountLimit(int i)
    {
        mInlineCountLimit = i;
    }

    protected abstract void writeElement(Object obj, Parcel parcel, int i);

    protected abstract void writeParcelableCreator(Object obj, Parcel parcel);

    public void writeToParcel(Parcel parcel, final int callFlags)
    {
        final int N = mList.size();
        parcel.writeInt(N);
        if(DEBUG)
            Log.d(TAG, (new StringBuilder()).append("Writing ").append(N).append(" items").toString());
        if(N > 0)
        {
            final Class listElementClass = mList.get(0).getClass();
            writeParcelableCreator(mList.get(0), parcel);
            int i;
            for(i = 0; i < N && i < mInlineCountLimit && parcel.dataSize() < 0x10000; i++)
            {
                parcel.writeInt(1);
                Object obj = mList.get(i);
                verifySameType(listElementClass, obj.getClass());
                writeElement(obj, parcel, callFlags);
                if(DEBUG)
                    Log.d(TAG, (new StringBuilder()).append("Wrote inline #").append(i).append(": ").append(mList.get(i)).toString());
            }

            if(i < N)
            {
                parcel.writeInt(0);
                Binder binder = new Binder() {

                    protected boolean onTransact(int j, Parcel parcel1, Parcel parcel2, int k)
                        throws RemoteException
                    {
                        if(j != 1)
                            return super.onTransact(j, parcel1, parcel2, k);
                        k = parcel1.readInt();
                        j = k;
                        if(BaseParceledListSlice._2D_get0())
                        {
                            Log.d(BaseParceledListSlice._2D_get1(), (new StringBuilder()).append("Writing more @").append(k).append(" of ").append(N).toString());
                            j = k;
                        }
                        for(; j < N && parcel2.dataSize() < 0x10000; j++)
                        {
                            parcel2.writeInt(1);
                            parcel1 = ((Parcel) (BaseParceledListSlice._2D_get2(BaseParceledListSlice.this).get(j)));
                            BaseParceledListSlice._2D_wrap0(listElementClass, parcel1.getClass());
                            writeElement(parcel1, parcel2, callFlags);
                            if(BaseParceledListSlice._2D_get0())
                                Log.d(BaseParceledListSlice._2D_get1(), (new StringBuilder()).append("Wrote extra #").append(j).append(": ").append(BaseParceledListSlice._2D_get2(BaseParceledListSlice.this).get(j)).toString());
                        }

                        if(j < N)
                        {
                            if(BaseParceledListSlice._2D_get0())
                                Log.d(BaseParceledListSlice._2D_get1(), (new StringBuilder()).append("Breaking @").append(j).append(" of ").append(N).toString());
                            parcel2.writeInt(0);
                        }
                        return true;
                    }

                    final BaseParceledListSlice this$0;
                    final int val$N;
                    final int val$callFlags;
                    final Class val$listElementClass;

            
            {
                this$0 = BaseParceledListSlice.this;
                N = i;
                listElementClass = class1;
                callFlags = j;
                super();
            }
                }
;
                if(DEBUG)
                    Log.d(TAG, (new StringBuilder()).append("Breaking @").append(i).append(" of ").append(N).append(": retriever=").append(binder).toString());
                parcel.writeStrongBinder(binder);
            }
        }
    }

    private static boolean DEBUG = false;
    private static final int MAX_IPC_SIZE = 0x10000;
    private static String TAG = "ParceledListSlice";
    private int mInlineCountLimit;
    private final List mList;

    static 
    {
        DEBUG = false;
    }
}
