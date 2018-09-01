// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.os:
//            BaseBundle, Parcelable, Parcel, PersistableBundle, 
//            IBinder

public final class Bundle extends BaseBundle
    implements Cloneable, Parcelable
{

    public Bundle()
    {
        mFlags = 1536;
    }

    public Bundle(int i)
    {
        super(i);
        mFlags = 1536;
    }

    public Bundle(Bundle bundle)
    {
        super(bundle);
        mFlags = bundle.mFlags;
    }

    public Bundle(Parcel parcel)
    {
        super(parcel);
        mFlags = 1024;
        maybePrefillHasFds();
    }

    public Bundle(Parcel parcel, int i)
    {
        super(parcel, i);
        mFlags = 1024;
        maybePrefillHasFds();
    }

    public Bundle(PersistableBundle persistablebundle)
    {
        super(persistablebundle);
        mFlags = 1536;
    }

    public Bundle(ClassLoader classloader)
    {
        super(classloader);
        mFlags = 1536;
    }

    Bundle(boolean flag)
    {
        super(flag);
    }

    public static Bundle forPair(String s, String s1)
    {
        Bundle bundle = new Bundle(1);
        bundle.putString(s, s1);
        return bundle;
    }

    private void maybePrefillHasFds()
    {
        if(mParcelledData != null)
            if(mParcelledData.hasFileDescriptors())
                mFlags = mFlags | 0x300;
            else
                mFlags = mFlags | 0x200;
    }

    public static Bundle setDefusable(Bundle bundle, boolean flag)
    {
        if(bundle != null)
            bundle.setDefusable(flag);
        return bundle;
    }

    public void clear()
    {
        super.clear();
        mFlags = 1536;
    }

    public Object clone()
    {
        return new Bundle(this);
    }

    public Bundle deepCopy()
    {
        Bundle bundle = new Bundle(false);
        bundle.copyInternal(this, true);
        return bundle;
    }

    public int describeContents()
    {
        int i = 0;
        if(hasFileDescriptors())
            i = 1;
        return i;
    }

    public Bundle filterValues()
    {
        unparcel();
        Bundle bundle = this;
        Bundle bundle1 = bundle;
        if(mMap != null)
        {
            ArrayMap arraymap = mMap;
            int i = arraymap.size() - 1;
            do
            {
                bundle1 = bundle;
                if(i < 0)
                    break;
                Object obj = arraymap.valueAt(i);
                ArrayMap arraymap1;
                if(PersistableBundle.isValidType(obj))
                {
                    arraymap1 = arraymap;
                    bundle1 = bundle;
                } else
                if(obj instanceof Bundle)
                {
                    Bundle bundle2 = ((Bundle)obj).filterValues();
                    bundle1 = bundle;
                    arraymap1 = arraymap;
                    if(bundle2 != obj)
                    {
                        arraymap1 = arraymap;
                        if(arraymap == mMap)
                        {
                            bundle = new Bundle(this);
                            arraymap1 = bundle.mMap;
                        }
                        arraymap1.setValueAt(i, bundle2);
                        bundle1 = bundle;
                    }
                } else
                {
                    bundle1 = bundle;
                    arraymap1 = arraymap;
                    if(!obj.getClass().getName().startsWith("android."))
                    {
                        arraymap1 = arraymap;
                        if(arraymap == mMap)
                        {
                            bundle = new Bundle(this);
                            arraymap1 = bundle.mMap;
                        }
                        arraymap1.removeAt(i);
                        bundle1 = bundle;
                    }
                }
                i--;
                bundle = bundle1;
                arraymap = arraymap1;
            } while(true);
        }
        mFlags = mFlags | 0x200;
        mFlags = mFlags & 0xfffffeff;
        return bundle1;
    }

    public IBinder getBinder(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        IBinder ibinder;
        try
        {
            ibinder = (IBinder)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "IBinder", classcastexception);
            return null;
        }
        return ibinder;
    }

    public Bundle getBundle(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        Bundle bundle;
        try
        {
            bundle = (Bundle)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Bundle", classcastexception);
            return null;
        }
        return bundle;
    }

    public byte getByte(String s)
    {
        return super.getByte(s);
    }

    public Byte getByte(String s, byte byte0)
    {
        return super.getByte(s, byte0);
    }

    public byte[] getByteArray(String s)
    {
        return super.getByteArray(s);
    }

    public char getChar(String s)
    {
        return super.getChar(s);
    }

    public char getChar(String s, char c)
    {
        return super.getChar(s, c);
    }

    public char[] getCharArray(String s)
    {
        return super.getCharArray(s);
    }

    public CharSequence getCharSequence(String s)
    {
        return super.getCharSequence(s);
    }

    public CharSequence getCharSequence(String s, CharSequence charsequence)
    {
        return super.getCharSequence(s, charsequence);
    }

    public CharSequence[] getCharSequenceArray(String s)
    {
        return super.getCharSequenceArray(s);
    }

    public ArrayList getCharSequenceArrayList(String s)
    {
        return super.getCharSequenceArrayList(s);
    }

    public ClassLoader getClassLoader()
    {
        return super.getClassLoader();
    }

    public float getFloat(String s)
    {
        return super.getFloat(s);
    }

    public float getFloat(String s, float f)
    {
        return super.getFloat(s, f);
    }

    public float[] getFloatArray(String s)
    {
        return super.getFloatArray(s);
    }

    public IBinder getIBinder(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        IBinder ibinder;
        try
        {
            ibinder = (IBinder)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "IBinder", classcastexception);
            return null;
        }
        return ibinder;
    }

    public ArrayList getIntegerArrayList(String s)
    {
        return super.getIntegerArrayList(s);
    }

    public Parcelable getParcelable(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        Parcelable parcelable;
        try
        {
            parcelable = (Parcelable)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Parcelable", classcastexception);
            return null;
        }
        return parcelable;
    }

    public Parcelable[] getParcelableArray(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        Parcelable aparcelable[];
        try
        {
            aparcelable = (Parcelable[])obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Parcelable[]", classcastexception);
            return null;
        }
        return aparcelable;
    }

    public ArrayList getParcelableArrayList(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        ArrayList arraylist;
        try
        {
            arraylist = (ArrayList)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "ArrayList", classcastexception);
            return null;
        }
        return arraylist;
    }

    public Serializable getSerializable(String s)
    {
        return super.getSerializable(s);
    }

    public short getShort(String s)
    {
        return super.getShort(s);
    }

    public short getShort(String s, short word0)
    {
        return super.getShort(s, word0);
    }

    public short[] getShortArray(String s)
    {
        return super.getShortArray(s);
    }

    public int getSize()
    {
        if(mParcelledData != null)
            return mParcelledData.dataSize();
        else
            return 0;
    }

    public Size getSize(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        Size size;
        try
        {
            size = (Size)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Size", classcastexception);
            return null;
        }
        return size;
    }

    public SizeF getSizeF(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        SizeF sizef;
        try
        {
            sizef = (SizeF)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "SizeF", classcastexception);
            return null;
        }
        return sizef;
    }

    public SparseArray getSparseParcelableArray(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        SparseArray sparsearray;
        try
        {
            sparsearray = (SparseArray)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "SparseArray", classcastexception);
            return null;
        }
        return sparsearray;
    }

    public ArrayList getStringArrayList(String s)
    {
        return super.getStringArrayList(s);
    }

    public boolean hasFileDescriptors()
    {
        boolean flag = false;
        if((mFlags & 0x200) != 0) goto _L2; else goto _L1
_L1:
        boolean flag1;
        boolean flag2;
        flag1 = false;
        flag2 = false;
        if(mParcelledData == null) goto _L4; else goto _L3
_L3:
        if(mParcelledData.hasFileDescriptors())
            flag2 = true;
_L6:
        int i;
        Object obj;
        int j;
        Object obj1;
        if(flag2)
            mFlags = mFlags | 0x100;
        else
            mFlags = mFlags & 0xfffffeff;
        mFlags = mFlags | 0x200;
_L2:
        if((mFlags & 0x100) != 0)
            flag = true;
        return flag;
_L4:
        i = mMap.size() - 1;
_L16:
        flag2 = flag1;
        if(i < 0) goto _L6; else goto _L5
_L5:
        obj = mMap.valueAt(i);
        if(!(obj instanceof Parcelable)) goto _L8; else goto _L7
_L7:
        flag2 = flag1;
        if((((Parcelable)obj).describeContents() & 1) == 0) goto _L10; else goto _L9
_L9:
        flag2 = true;
          goto _L6
_L8:
        if(!(obj instanceof Parcelable[])) goto _L12; else goto _L11
_L11:
        obj = (Parcelable[])obj;
        j = obj.length - 1;
_L17:
        flag2 = flag1;
        if(j < 0) goto _L10; else goto _L13
_L13:
        obj1 = obj[j];
        if(obj1 == null || (((Parcelable) (obj1)).describeContents() & 1) == 0) goto _L15; else goto _L14
_L14:
        flag2 = true;
_L10:
        i--;
        flag1 = flag2;
          goto _L16
_L15:
        j--;
          goto _L17
_L12:
        if(!(obj instanceof SparseArray))
            break MISSING_BLOCK_LABEL_278;
        obj = (SparseArray)obj;
        j = ((SparseArray) (obj)).size() - 1;
_L18:
        flag2 = flag1;
        if(j >= 0)
        {
label0:
            {
                obj1 = (Parcelable)((SparseArray) (obj)).valueAt(j);
                if(obj1 == null || (((Parcelable) (obj1)).describeContents() & 1) == 0)
                    break label0;
                flag2 = true;
            }
        }
          goto _L10
        j--;
          goto _L18
        flag2 = flag1;
        if(!(obj instanceof ArrayList)) goto _L10; else goto _L19
_L19:
        obj1 = (ArrayList)obj;
        flag2 = flag1;
        if(((ArrayList) (obj1)).isEmpty()) goto _L10; else goto _L20
_L20:
        flag2 = flag1;
        if(!(((ArrayList) (obj1)).get(0) instanceof Parcelable)) goto _L10; else goto _L21
_L21:
        j = ((ArrayList) (obj1)).size() - 1;
_L22:
        flag2 = flag1;
        if(j >= 0)
        {
label1:
            {
                obj = (Parcelable)((ArrayList) (obj1)).get(j);
                if(obj == null || (((Parcelable) (obj)).describeContents() & 1) == 0)
                    break label1;
                flag2 = true;
            }
        }
          goto _L10
        j--;
          goto _L22
    }

    public void putAll(Bundle bundle)
    {
        unparcel();
        bundle.unparcel();
        mMap.putAll(bundle.mMap);
        if((bundle.mFlags & 0x100) != 0)
            mFlags = mFlags | 0x100;
        if((bundle.mFlags & 0x200) == 0)
            mFlags = mFlags & 0xfffffdff;
    }

    public void putBinder(String s, IBinder ibinder)
    {
        unparcel();
        mMap.put(s, ibinder);
    }

    public void putBundle(String s, Bundle bundle)
    {
        unparcel();
        mMap.put(s, bundle);
    }

    public void putByte(String s, byte byte0)
    {
        super.putByte(s, byte0);
    }

    public void putByteArray(String s, byte abyte0[])
    {
        super.putByteArray(s, abyte0);
    }

    public void putChar(String s, char c)
    {
        super.putChar(s, c);
    }

    public void putCharArray(String s, char ac[])
    {
        super.putCharArray(s, ac);
    }

    public void putCharSequence(String s, CharSequence charsequence)
    {
        super.putCharSequence(s, charsequence);
    }

    public void putCharSequenceArray(String s, CharSequence acharsequence[])
    {
        super.putCharSequenceArray(s, acharsequence);
    }

    public void putCharSequenceArrayList(String s, ArrayList arraylist)
    {
        super.putCharSequenceArrayList(s, arraylist);
    }

    public void putFloat(String s, float f)
    {
        super.putFloat(s, f);
    }

    public void putFloatArray(String s, float af[])
    {
        super.putFloatArray(s, af);
    }

    public void putIBinder(String s, IBinder ibinder)
    {
        unparcel();
        mMap.put(s, ibinder);
    }

    public void putIntegerArrayList(String s, ArrayList arraylist)
    {
        super.putIntegerArrayList(s, arraylist);
    }

    public void putParcelable(String s, Parcelable parcelable)
    {
        unparcel();
        mMap.put(s, parcelable);
        mFlags = mFlags & 0xfffffdff;
    }

    public void putParcelableArray(String s, Parcelable aparcelable[])
    {
        unparcel();
        mMap.put(s, aparcelable);
        mFlags = mFlags & 0xfffffdff;
    }

    public void putParcelableArrayList(String s, ArrayList arraylist)
    {
        unparcel();
        mMap.put(s, arraylist);
        mFlags = mFlags & 0xfffffdff;
    }

    public void putParcelableList(String s, List list)
    {
        unparcel();
        mMap.put(s, list);
        mFlags = mFlags & 0xfffffdff;
    }

    public void putSerializable(String s, Serializable serializable)
    {
        super.putSerializable(s, serializable);
    }

    public void putShort(String s, short word0)
    {
        super.putShort(s, word0);
    }

    public void putShortArray(String s, short aword0[])
    {
        super.putShortArray(s, aword0);
    }

    public void putSize(String s, Size size)
    {
        unparcel();
        mMap.put(s, size);
    }

    public void putSizeF(String s, SizeF sizef)
    {
        unparcel();
        mMap.put(s, sizef);
    }

    public void putSparseParcelableArray(String s, SparseArray sparsearray)
    {
        unparcel();
        mMap.put(s, sparsearray);
        mFlags = mFlags & 0xfffffdff;
    }

    public void putStringArrayList(String s, ArrayList arraylist)
    {
        super.putStringArrayList(s, arraylist);
    }

    public void readFromParcel(Parcel parcel)
    {
        super.readFromParcelInner(parcel);
        mFlags = 1024;
        maybePrefillHasFds();
    }

    public void remove(String s)
    {
        super.remove(s);
        if((mFlags & 0x100) != 0)
            mFlags = mFlags & 0xfffffdff;
    }

    public boolean setAllowFds(boolean flag)
    {
        boolean flag1;
        if((mFlags & 0x400) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag)
            mFlags = mFlags | 0x400;
        else
            mFlags = mFlags & 0xfffffbff;
        return flag1;
    }

    public void setClassLoader(ClassLoader classloader)
    {
        super.setClassLoader(classloader);
    }

    public void setDefusable(boolean flag)
    {
        if(flag)
            mFlags = mFlags | 1;
        else
            mFlags = mFlags & -2;
    }

    public String toShortString()
    {
        this;
        JVM INSTR monitorenter ;
        if(mParcelledData == null)
            break MISSING_BLOCK_LABEL_55;
        if(!isEmptyParcel())
            break MISSING_BLOCK_LABEL_22;
        this;
        JVM INSTR monitorexit ;
        return "EMPTY_PARCEL";
        Object obj;
        obj = JVM INSTR new #416 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append("mParcelledData.dataSize=").append(mParcelledData.dataSize()).toString();
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        obj = mMap.toString();
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public String toString()
    {
        this;
        JVM INSTR monitorenter ;
        if(mParcelledData == null)
            break MISSING_BLOCK_LABEL_61;
        if(!isEmptyParcel())
            break MISSING_BLOCK_LABEL_22;
        this;
        JVM INSTR monitorexit ;
        return "Bundle[EMPTY_PARCEL]";
        Object obj;
        obj = JVM INSTR new #416 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append("Bundle[mParcelledData.dataSize=").append(mParcelledData.dataSize()).append("]").toString();
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        obj = JVM INSTR new #416 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append("Bundle[").append(mMap.toString()).append("]").toString();
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag;
        flag = false;
        if((mFlags & 0x400) != 0)
            flag = true;
        flag = parcel.pushAllowFds(flag);
        super.writeToParcelInner(parcel, i);
        parcel.restoreAllowFds(flag);
        return;
        Exception exception;
        exception;
        parcel.restoreAllowFds(flag);
        throw exception;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Bundle createFromParcel(Parcel parcel)
        {
            return parcel.readBundle();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Bundle[] newArray(int i)
        {
            return new Bundle[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final Bundle EMPTY;
    static final int FLAG_ALLOW_FDS = 1024;
    static final int FLAG_HAS_FDS = 256;
    static final int FLAG_HAS_FDS_KNOWN = 512;
    public static final Bundle STRIPPED;

    static 
    {
        EMPTY = new Bundle();
        EMPTY.mMap = ArrayMap.EMPTY;
        STRIPPED = new Bundle();
        STRIPPED.putInt("STRIPPED", 1);
    }
}
