// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.IntArray;
import java.util.ArrayList;

// Referenced classes of package android.os:
//            Parcelable, Parcel

public final class IncidentReportArgs
    implements Parcelable
{

    public IncidentReportArgs()
    {
        mSections = new IntArray();
        mHeaders = new ArrayList();
    }

    public IncidentReportArgs(Parcel parcel)
    {
        mSections = new IntArray();
        mHeaders = new ArrayList();
        readFromParcel(parcel);
    }

    public static IncidentReportArgs parseSetting(String s)
        throws IllegalArgumentException
    {
        if(s == null || s.length() == 0)
            return null;
        s = s.trim();
        if(s.length() == 0 || "disabled".equals(s))
            return null;
        IncidentReportArgs incidentreportargs = new IncidentReportArgs();
        if("all".equals(s))
        {
            incidentreportargs.setAll(true);
            return incidentreportargs;
        }
        if("none".equals(s))
            return incidentreportargs;
        String as[] = s.split(",");
        int i = as.length;
        int j = 0;
        while(j < i) 
        {
            String s1 = as[j].trim();
            if(s1.length() != 0)
            {
                int k;
                try
                {
                    k = Integer.parseInt(s1);
                }
                catch(NumberFormatException numberformatexception)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Malformed setting. Bad integer at section index ").append(j).append(": section='").append(s1).append("' setting='").append(s).append("'").toString());
                }
                if(k < 1)
                    throw new IllegalArgumentException((new StringBuilder()).append("Malformed setting. Illegal section at index ").append(j).append(": section='").append(s1).append("' setting='").append(s).append("'").toString());
                incidentreportargs.addSection(k);
            }
            j++;
        }
        return incidentreportargs;
    }

    public void addHeader(byte abyte0[])
    {
        mHeaders.add(abyte0);
    }

    public void addSection(int i)
    {
        if(!mAll)
            mSections.add(i);
    }

    public boolean containsSection(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!mAll)
            if(mSections.indexOf(i) >= 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean isAll()
    {
        return mAll;
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag = false;
        if(parcel.readInt() != 0)
            flag = true;
        mAll = flag;
        mSections.clear();
        int i = parcel.readInt();
        for(int j = 0; j < i; j++)
            mSections.add(parcel.readInt());

        mHeaders.clear();
        i = parcel.readInt();
        for(int k = 0; k < i; k++)
            mHeaders.add(parcel.createByteArray());

    }

    public int sectionCount()
    {
        return mSections.size();
    }

    public void setAll(boolean flag)
    {
        mAll = flag;
        if(flag)
            mSections.clear();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("Incident(");
        if(mAll)
        {
            stringbuilder.append("all");
        } else
        {
            int i = mSections.size();
            if(i > 0)
                stringbuilder.append(mSections.get(0));
            int j = 1;
            while(j < i) 
            {
                stringbuilder.append(" ");
                stringbuilder.append(mSections.get(j));
                j++;
            }
        }
        stringbuilder.append(", ");
        stringbuilder.append(mHeaders.size());
        stringbuilder.append(" headers)");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j;
        if(mAll)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        j = mSections.size();
        parcel.writeInt(j);
        for(i = 0; i < j; i++)
            parcel.writeInt(mSections.get(i));

        j = mHeaders.size();
        parcel.writeInt(j);
        for(i = 0; i < j; i++)
            parcel.writeByteArray((byte[])mHeaders.get(i));

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public IncidentReportArgs createFromParcel(Parcel parcel)
        {
            return new IncidentReportArgs(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IncidentReportArgs[] newArray(int i)
        {
            return new IncidentReportArgs[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private boolean mAll;
    private final ArrayList mHeaders;
    private final IntArray mSections;

}
