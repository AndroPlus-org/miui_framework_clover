// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.storage;


public abstract class StorageManagerInternal
{
    public static interface ExternalStorageMountPolicy
    {

        public abstract int getMountMode(int i, String s);

        public abstract boolean hasExternalStorage(int i, String s);
    }


    public StorageManagerInternal()
    {
    }

    public abstract void addExternalStoragePolicy(ExternalStorageMountPolicy externalstoragemountpolicy);

    public abstract int getExternalStorageMountMode(int i, String s);

    public abstract void onExternalStoragePolicyChanged(int i, String s);
}
