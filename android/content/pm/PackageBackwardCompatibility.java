// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import com.android.internal.util.ArrayUtils;
import java.util.ArrayList;

public class PackageBackwardCompatibility
{

    public PackageBackwardCompatibility()
    {
    }

    public static void modifySharedLibraries(PackageParser.Package package1)
    {
        ArrayList arraylist = package1.usesLibraries;
        ArrayList arraylist1 = package1.usesOptionalLibraries;
        arraylist = orgApacheHttpLegacy(arraylist);
        arraylist1 = orgApacheHttpLegacy(arraylist1);
        boolean flag;
        if(!ArrayUtils.contains(arraylist, "android.test.mock"))
            flag = ArrayUtils.contains(arraylist1, "android.test.mock");
        else
            flag = true;
        if(ArrayUtils.contains(arraylist, "android.test.runner") && flag ^ true)
            arraylist.add("android.test.mock");
        if(ArrayUtils.contains(arraylist1, "android.test.runner") && flag ^ true)
            arraylist1.add("android.test.mock");
        package1.usesLibraries = arraylist;
        package1.usesOptionalLibraries = arraylist1;
    }

    private static ArrayList orgApacheHttpLegacy(ArrayList arraylist)
    {
        return ArrayUtils.remove(arraylist, "org.apache.http.legacy");
    }

    private static final String ANDROID_TEST_MOCK = "android.test.mock";
    private static final String ANDROID_TEST_RUNNER = "android.test.runner";
}
