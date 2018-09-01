// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app.procstats;

import android.os.UserHandle;
import android.util.TimeUtils;
import java.io.PrintWriter;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.app.procstats:
//            ProcessState

public final class DumpUtils
{

    private DumpUtils()
    {
    }

    public static String collapseString(String s, String s1)
    {
        if(s1.startsWith(s))
        {
            int i = s1.length();
            int j = s.length();
            if(i == j)
                return "";
            if(i >= j && s1.charAt(j) == '.')
                return s1.substring(j);
        }
        return s1;
    }

    public static void dumpAdjTimesCheckin(PrintWriter printwriter, String s, long al[], int i, long l, long l1)
    {
        for(int j = 0; j < 8; j += 4)
        {
            for(int k = 0; k < 4; k++)
            {
                int i1 = k + j;
                long l2 = al[i1];
                long l3 = l2;
                if(i == i1)
                    l3 = l2 + (l1 - l);
                if(l3 != 0L)
                    printAdjTagAndValue(printwriter, i1, l3);
            }

        }

    }

    public static void dumpProcessListCsv(PrintWriter printwriter, ArrayList arraylist, boolean flag, int ai[], boolean flag1, int ai1[], boolean flag2, int ai2[], 
            long l)
    {
        printwriter.print("process");
        printwriter.print("\t");
        printwriter.print("uid");
        printwriter.print("\t");
        printwriter.print("vers");
        Object obj;
        int ai3[];
        int ai4[];
        if(flag)
            obj = ai;
        else
            obj = null;
        if(flag1)
            ai3 = ai1;
        else
            ai3 = null;
        if(flag2)
            ai4 = ai2;
        else
            ai4 = null;
        dumpStateHeadersCsv(printwriter, "\t", ((int []) (obj)), ai3, ai4);
        printwriter.println();
        for(int i = arraylist.size() - 1; i >= 0; i--)
        {
            obj = (ProcessState)arraylist.get(i);
            printwriter.print(((ProcessState) (obj)).getName());
            printwriter.print("\t");
            UserHandle.formatUid(printwriter, ((ProcessState) (obj)).getUid());
            printwriter.print("\t");
            printwriter.print(((ProcessState) (obj)).getVersion());
            ((ProcessState) (obj)).dumpCsv(printwriter, flag, ai, flag1, ai1, flag2, ai2, l);
            printwriter.println();
        }

    }

    public static void dumpProcessSummaryLocked(PrintWriter printwriter, String s, ArrayList arraylist, int ai[], int ai1[], int ai2[], long l, 
            long l1)
    {
        for(int i = arraylist.size() - 1; i >= 0; i--)
            ((ProcessState)arraylist.get(i)).dumpSummary(printwriter, s, ai, ai1, ai2, l, l1);

    }

    public static long dumpSingleTime(PrintWriter printwriter, String s, long al[], int i, long l, long l1)
    {
        long l2 = 0L;
        int j = -1;
        for(int k = 0; k < 8; k += 4)
        {
            int i1 = -1;
            int j1 = 0;
            while(j1 < 4) 
            {
                int k1 = j1 + k;
                long l3 = al[k1];
                String s1 = "";
                String s2 = s1;
                long l4 = l3;
                if(i == k1)
                {
                    l3 += l1 - l;
                    s2 = s1;
                    l4 = l3;
                    if(printwriter != null)
                    {
                        s2 = " (running)";
                        l4 = l3;
                    }
                }
                k1 = i1;
                int i2 = j;
                l3 = l2;
                if(l4 != 0L)
                {
                    k1 = i1;
                    i2 = j;
                    if(printwriter != null)
                    {
                        printwriter.print(s);
                        if(j != k)
                            j = k;
                        else
                            j = -1;
                        printScreenLabel(printwriter, j);
                        i2 = k;
                        if(i1 != j1)
                            i1 = j1;
                        else
                            i1 = -1;
                        printMemLabel(printwriter, i1, '\0');
                        k1 = j1;
                        printwriter.print(": ");
                        TimeUtils.formatDuration(l4, printwriter);
                        printwriter.println(s2);
                    }
                    l3 = l2 + l4;
                }
                j1++;
                i1 = k1;
                j = i2;
                l2 = l3;
            }
        }

        if(l2 != 0L && printwriter != null)
        {
            printwriter.print(s);
            printwriter.print("    TOTAL: ");
            TimeUtils.formatDuration(l2, printwriter);
            printwriter.println();
        }
        return l2;
    }

    private static void dumpStateHeadersCsv(PrintWriter printwriter, String s, int ai[], int ai1[], int ai2[])
    {
        int i;
        int j;
        int k;
        int l;
        if(ai != null)
            i = ai.length;
        else
            i = 1;
        if(ai1 != null)
            j = ai1.length;
        else
            j = 1;
        if(ai2 != null)
            k = ai2.length;
        else
            k = 1;
        for(l = 0; l < i; l++)
        {
            for(int i1 = 0; i1 < j; i1++)
            {
                for(int j1 = 0; j1 < k; j1++)
                {
                    printwriter.print(s);
                    boolean flag = false;
                    boolean flag1 = flag;
                    if(ai != null)
                    {
                        flag1 = flag;
                        if(ai.length > 1)
                        {
                            printScreenLabelCsv(printwriter, ai[l]);
                            flag1 = true;
                        }
                    }
                    flag = flag1;
                    if(ai1 != null)
                    {
                        flag = flag1;
                        if(ai1.length > 1)
                        {
                            if(flag1)
                                printwriter.print("-");
                            printMemLabelCsv(printwriter, ai1[i1]);
                            flag = true;
                        }
                    }
                    if(ai2 == null || ai2.length <= 1)
                        continue;
                    if(flag)
                        printwriter.print("-");
                    printwriter.print(STATE_NAMES_CSV[ai2[j1]]);
                }

            }

        }

    }

    public static void printAdjTag(PrintWriter printwriter, int i)
    {
        i = printArrayEntry(printwriter, ADJ_SCREEN_TAGS, i, 4);
        printArrayEntry(printwriter, ADJ_MEM_TAGS, i, 1);
    }

    public static void printAdjTagAndValue(PrintWriter printwriter, int i, long l)
    {
        printwriter.print(',');
        printAdjTag(printwriter, i);
        printwriter.print(':');
        printwriter.print(l);
    }

    public static int printArrayEntry(PrintWriter printwriter, String as[], int i, int j)
    {
        int k = i / j;
        if(k >= 0 && k < as.length)
            printwriter.print(as[k]);
        else
            printwriter.print('?');
        return i - k * j;
    }

    public static void printMemLabel(PrintWriter printwriter, int i, char c)
    {
        i;
        JVM INSTR tableswitch -1 3: default 36
    //                   -1 53
    //                   0 73
    //                   1 92
    //                   2 111
    //                   3 130;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        printwriter.print("????");
        if(c != 0)
            printwriter.print(c);
_L8:
        return;
_L2:
        printwriter.print("    ");
        if(c != 0)
            printwriter.print(' ');
        continue; /* Loop/switch isn't completed */
_L3:
        printwriter.print("Norm");
        if(c != 0)
            printwriter.print(c);
        continue; /* Loop/switch isn't completed */
_L4:
        printwriter.print("Mod ");
        if(c != 0)
            printwriter.print(c);
        continue; /* Loop/switch isn't completed */
_L5:
        printwriter.print("Low ");
        if(c != 0)
            printwriter.print(c);
        continue; /* Loop/switch isn't completed */
_L6:
        printwriter.print("Crit");
        if(c != 0)
            printwriter.print(c);
        if(true) goto _L8; else goto _L7
_L7:
    }

    public static void printMemLabelCsv(PrintWriter printwriter, int i)
    {
        if(i >= 0)
            if(i <= 3)
                printwriter.print(ADJ_MEM_NAMES_CSV[i]);
            else
                printwriter.print("???");
    }

    public static void printPercent(PrintWriter printwriter, double d)
    {
        d *= 100D;
        if(d < 1.0D)
            printwriter.print(String.format("%.2f", new Object[] {
                Double.valueOf(d)
            }));
        else
        if(d < 10D)
            printwriter.print(String.format("%.1f", new Object[] {
                Double.valueOf(d)
            }));
        else
            printwriter.print(String.format("%.0f", new Object[] {
                Double.valueOf(d)
            }));
        printwriter.print("%");
    }

    public static void printProcStateTag(PrintWriter printwriter, int i)
    {
        i = printArrayEntry(printwriter, ADJ_SCREEN_TAGS, i, 56);
        i = printArrayEntry(printwriter, ADJ_MEM_TAGS, i, 14);
        printArrayEntry(printwriter, STATE_TAGS, i, 1);
    }

    public static void printProcStateTagAndValue(PrintWriter printwriter, int i, long l)
    {
        printwriter.print(',');
        printProcStateTag(printwriter, i);
        printwriter.print(':');
        printwriter.print(l);
    }

    public static void printScreenLabel(PrintWriter printwriter, int i)
    {
        i;
        JVM INSTR tableswitch -1 4: default 40
    //                   -1 48
    //                   0 58
    //                   1 40
    //                   2 40
    //                   3 40
    //                   4 68;
           goto _L1 _L2 _L3 _L1 _L1 _L1 _L4
_L1:
        printwriter.print("????/");
_L6:
        return;
_L2:
        printwriter.print("     ");
        continue; /* Loop/switch isn't completed */
_L3:
        printwriter.print("SOff/");
        continue; /* Loop/switch isn't completed */
_L4:
        printwriter.print("SOn /");
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static void printScreenLabelCsv(PrintWriter printwriter, int i)
    {
        i;
        JVM INSTR tableswitch -1 4: default 40
    //                   -1 47
    //                   0 48
    //                   1 40
    //                   2 40
    //                   3 40
    //                   4 60;
           goto _L1 _L2 _L3 _L1 _L1 _L1 _L4
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        printwriter.print("???");
_L6:
        return;
_L3:
        printwriter.print(ADJ_SCREEN_NAMES_CSV[0]);
        continue; /* Loop/switch isn't completed */
_L4:
        printwriter.print(ADJ_SCREEN_NAMES_CSV[1]);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static final String ADJ_MEM_NAMES_CSV[] = {
        "norm", "mod", "low", "crit"
    };
    static final String ADJ_MEM_TAGS[] = {
        "n", "m", "l", "c"
    };
    public static final String ADJ_SCREEN_NAMES_CSV[] = {
        "off", "on"
    };
    static final String ADJ_SCREEN_TAGS[] = {
        "0", "1"
    };
    static final String CSV_SEP = "\t";
    public static final String STATE_NAMES[] = {
        "Persist", "Top    ", "ImpFg  ", "ImpBg  ", "Backup ", "HeavyWt", "Service", "ServRst", "Receivr", "Home   ", 
        "LastAct", "CchAct ", "CchCAct", "CchEmty"
    };
    public static final String STATE_NAMES_CSV[] = {
        "pers", "top", "impfg", "impbg", "backup", "heavy", "service", "service-rs", "receiver", "home", 
        "lastact", "cch-activity", "cch-aclient", "cch-empty"
    };
    static final String STATE_TAGS[] = {
        "p", "t", "f", "b", "u", "w", "s", "x", "r", "h", 
        "l", "a", "c", "e"
    };

}
