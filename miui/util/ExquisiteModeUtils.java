// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;


// Referenced classes of package miui.util:
//            FeatureParser

public class ExquisiteModeUtils
{

    public ExquisiteModeUtils()
    {
    }

    public static final float DEFAULT_EXQUISITE_SCALE_VALUE;
    public static final float DEFAULT_MIUI_SCALE_VALUE = 1F;
    public static final String MIUI_SCALE_FIELD_NAME = "miuiScale";
    public static final boolean SUPPORT_EXQUISITE_MODE;

    static 
    {
        boolean flag = true;
        DEFAULT_EXQUISITE_SCALE_VALUE = ((float)FeatureParser.getInteger("exquisite_mode_target_density", 1) * 1.0F) / (float)FeatureParser.getInteger("exquisite_mode_origin_density", 1);
        if(DEFAULT_EXQUISITE_SCALE_VALUE == 1.0F)
            flag = false;
        SUPPORT_EXQUISITE_MODE = flag;
    }
}
