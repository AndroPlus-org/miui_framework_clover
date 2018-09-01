// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


// Referenced classes of package android.media:
//            AudioHandle, AudioPortConfig

public class AudioPatch
{

    AudioPatch(AudioHandle audiohandle, AudioPortConfig aaudioportconfig[], AudioPortConfig aaudioportconfig1[])
    {
        mHandle = audiohandle;
        mSources = aaudioportconfig;
        mSinks = aaudioportconfig1;
    }

    public int id()
    {
        return mHandle.id();
    }

    public AudioPortConfig[] sinks()
    {
        return mSinks;
    }

    public AudioPortConfig[] sources()
    {
        return mSources;
    }

    public String toString()
    {
        boolean flag = false;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("mHandle: ");
        stringbuilder.append(mHandle.toString());
        stringbuilder.append(" mSources: {");
        AudioPortConfig aaudioportconfig[] = mSources;
        int i = aaudioportconfig.length;
        for(int j = 0; j < i; j++)
        {
            stringbuilder.append(aaudioportconfig[j].toString());
            stringbuilder.append(", ");
        }

        stringbuilder.append("} mSinks: {");
        aaudioportconfig = mSinks;
        i = aaudioportconfig.length;
        for(int k = ((flag) ? 1 : 0); k < i; k++)
        {
            stringbuilder.append(aaudioportconfig[k].toString());
            stringbuilder.append(", ");
        }

        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private final AudioHandle mHandle;
    private final AudioPortConfig mSinks[];
    private final AudioPortConfig mSources[];
}
