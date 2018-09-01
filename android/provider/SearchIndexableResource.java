// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.Context;

// Referenced classes of package android.provider:
//            SearchIndexableData

public class SearchIndexableResource extends SearchIndexableData
{

    public SearchIndexableResource(int i, int j, String s, int k)
    {
        rank = i;
        xmlResId = j;
        className = s;
        iconResId = k;
    }

    public SearchIndexableResource(Context context)
    {
        super(context);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("SearchIndexableResource[");
        stringbuilder.append(super.toString());
        stringbuilder.append(", ");
        stringbuilder.append("xmlResId: ");
        stringbuilder.append(xmlResId);
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public int xmlResId;
}
