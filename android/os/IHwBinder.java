// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            RemoteException, IHwInterface, HwParcel

public interface IHwBinder
{
    public static interface DeathRecipient
    {

        public abstract void serviceDied(long l);
    }


    public abstract boolean linkToDeath(DeathRecipient deathrecipient, long l);

    public abstract IHwInterface queryLocalInterface(String s);

    public abstract void transact(int i, HwParcel hwparcel, HwParcel hwparcel1, int j)
        throws RemoteException;

    public abstract boolean unlinkToDeath(DeathRecipient deathrecipient);

    public static final int FIRST_CALL_TRANSACTION = 1;
    public static final int FLAG_ONEWAY = 1;
}
