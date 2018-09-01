// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.Context;
import java.util.Locale;

public abstract class SearchIndexableData
{

    public SearchIndexableData()
    {
        userId = -1;
        locale = Locale.getDefault();
        enabled = true;
    }

    public SearchIndexableData(Context context1)
    {
        this();
        context = context1;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("SearchIndexableData[context: ");
        stringbuilder.append(context);
        stringbuilder.append(", ");
        stringbuilder.append("locale: ");
        stringbuilder.append(locale);
        stringbuilder.append(", ");
        stringbuilder.append("enabled: ");
        stringbuilder.append(enabled);
        stringbuilder.append(", ");
        stringbuilder.append("rank: ");
        stringbuilder.append(rank);
        stringbuilder.append(", ");
        stringbuilder.append("key: ");
        stringbuilder.append(key);
        stringbuilder.append(", ");
        stringbuilder.append("userId: ");
        stringbuilder.append(userId);
        stringbuilder.append(", ");
        stringbuilder.append("className: ");
        stringbuilder.append(className);
        stringbuilder.append(", ");
        stringbuilder.append("packageName: ");
        stringbuilder.append(packageName);
        stringbuilder.append(", ");
        stringbuilder.append("iconResId: ");
        stringbuilder.append(iconResId);
        stringbuilder.append(", ");
        stringbuilder.append("intentAction: ");
        stringbuilder.append(intentAction);
        stringbuilder.append(", ");
        stringbuilder.append("intentTargetPackage: ");
        stringbuilder.append(intentTargetPackage);
        stringbuilder.append(", ");
        stringbuilder.append("intentTargetClass: ");
        stringbuilder.append(intentTargetClass);
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public String className;
    public Context context;
    public boolean enabled;
    public int iconResId;
    public String intentAction;
    public String intentTargetClass;
    public String intentTargetPackage;
    public String key;
    public Locale locale;
    public String packageName;
    public int rank;
    public int userId;
}
