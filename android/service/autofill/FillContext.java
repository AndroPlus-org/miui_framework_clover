// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.app.assist.AssistStructure;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.SparseIntArray;
import android.view.autofill.AutofillId;
import android.view.autofill.Helper;
import java.util.LinkedList;

public final class FillContext
    implements Parcelable
{

    public FillContext(int i, AssistStructure assiststructure)
    {
        mRequestId = i;
        mStructure = assiststructure;
    }

    private FillContext(Parcel parcel)
    {
        this(parcel.readInt(), (AssistStructure)parcel.readParcelable(null));
    }

    FillContext(Parcel parcel, FillContext fillcontext)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public android.app.assist.AssistStructure.ViewNode findViewNodeByAutofillId(AutofillId autofillid)
    {
        LinkedList linkedlist = new LinkedList();
        int i = mStructure.getWindowNodeCount();
        for(int j = 0; j < i; j++)
            linkedlist.add(mStructure.getWindowNodeAt(j).getRootViewNode());

        while(!linkedlist.isEmpty()) 
        {
            android.app.assist.AssistStructure.ViewNode viewnode = (android.app.assist.AssistStructure.ViewNode)linkedlist.removeFirst();
            if(autofillid.equals(viewnode.getAutofillId()))
                return viewnode;
            int k = 0;
            while(k < viewnode.getChildCount()) 
            {
                linkedlist.addLast(viewnode.getChildAt(k));
                k++;
            }
        }
        return null;
    }

    public android.app.assist.AssistStructure.ViewNode[] findViewNodesByAutofillIds(AutofillId aautofillid[])
    {
        LinkedList linkedlist = new LinkedList();
        android.app.assist.AssistStructure.ViewNode aviewnode[] = new android.app.assist.AssistStructure.ViewNode[aautofillid.length];
        SparseIntArray sparseintarray = new SparseIntArray(aautofillid.length);
        int i = 0;
        while(i < aautofillid.length) 
        {
            if(mViewNodeLookupTable != null)
            {
                int i1 = mViewNodeLookupTable.indexOfKey(aautofillid[i]);
                if(i1 >= 0)
                    aviewnode[i] = (android.app.assist.AssistStructure.ViewNode)mViewNodeLookupTable.valueAt(i1);
                else
                    sparseintarray.put(i, 0);
            } else
            {
                sparseintarray.put(i, 0);
            }
            i++;
        }
        int j1 = mStructure.getWindowNodeCount();
        for(int j = 0; j < j1; j++)
            linkedlist.add(mStructure.getWindowNodeAt(j).getRootViewNode());

label0:
        do
        {
            if(sparseintarray.size() > 0 && linkedlist.isEmpty() ^ true)
            {
                android.app.assist.AssistStructure.ViewNode viewnode = (android.app.assist.AssistStructure.ViewNode)linkedlist.removeFirst();
                int k = 0;
                do
                {
label1:
                    {
                        if(k < sparseintarray.size())
                        {
                            int k1 = sparseintarray.keyAt(k);
                            AutofillId autofillid = aautofillid[k1];
                            if(!autofillid.equals(viewnode.getAutofillId()))
                                break label1;
                            aviewnode[k1] = viewnode;
                            if(mViewNodeLookupTable == null)
                                mViewNodeLookupTable = new ArrayMap(aautofillid.length);
                            mViewNodeLookupTable.put(autofillid, viewnode);
                            sparseintarray.removeAt(k);
                        }
                        k = 0;
                        while(k < viewnode.getChildCount()) 
                        {
                            linkedlist.addLast(viewnode.getChildAt(k));
                            k++;
                        }
                        continue label0;
                    }
                    k++;
                } while(true);
            }
            for(int l = 0; l < sparseintarray.size(); l++)
            {
                if(mViewNodeLookupTable == null)
                    mViewNodeLookupTable = new ArrayMap(sparseintarray.size());
                mViewNodeLookupTable.put(aautofillid[sparseintarray.keyAt(l)], null);
            }

            return aviewnode;
        } while(true);
    }

    public int getRequestId()
    {
        return mRequestId;
    }

    public AssistStructure getStructure()
    {
        return mStructure;
    }

    public String toString()
    {
        if(!Helper.sDebug)
            return super.toString();
        else
            return (new StringBuilder()).append("FillContext [reqId=").append(mRequestId).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRequestId);
        parcel.writeParcelable(mStructure, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FillContext createFromParcel(Parcel parcel)
        {
            return new FillContext(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FillContext[] newArray(int i)
        {
            return new FillContext[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mRequestId;
    private final AssistStructure mStructure;
    private ArrayMap mViewNodeLookupTable;

}
