// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.provider;


public class ExtraGuardVirusInfoEntity
{

    public ExtraGuardVirusInfoEntity()
    {
        mType = 1;
        mDescription = null;
        mAdvice = 1;
        mVirusName = null;
        mPackageName = null;
        mEngineName = null;
    }

    public int getAdvice()
    {
        return mAdvice;
    }

    public String getDescription()
    {
        return mDescription;
    }

    public String getEngineName()
    {
        return mEngineName;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public int getType()
    {
        return mType;
    }

    public String getVirusName()
    {
        return mVirusName;
    }

    public void setAdvice(int i)
    {
        mAdvice = i;
    }

    public void setDescription(String s)
    {
        mDescription = s;
    }

    public void setEngineName(String s)
    {
        mEngineName = s;
    }

    public void setPackageName(String s)
    {
        mPackageName = s;
    }

    public void setType(int i)
    {
        mType = i;
    }

    public void setVirusName(String s)
    {
        mVirusName = s;
    }

    private int mAdvice;
    private String mDescription;
    private String mEngineName;
    private String mPackageName;
    private int mType;
    private String mVirusName;
}
