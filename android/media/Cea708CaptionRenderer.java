// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;

// Referenced classes of package android.media:
//            MediaFormat, Cea708CCWidget, Cea708CaptionTrack, SubtitleTrack

public class Cea708CaptionRenderer extends SubtitleController.Renderer
{

    public Cea708CaptionRenderer(Context context)
    {
        mContext = context;
    }

    public SubtitleTrack createTrack(MediaFormat mediaformat)
    {
        if("text/cea-708".equals(mediaformat.getString("mime")))
        {
            if(mCCWidget == null)
                mCCWidget = new Cea708CCWidget(mContext);
            return new Cea708CaptionTrack(mCCWidget, mediaformat);
        } else
        {
            throw new RuntimeException((new StringBuilder()).append("No matching format: ").append(mediaformat.toString()).toString());
        }
    }

    public boolean supports(MediaFormat mediaformat)
    {
        if(mediaformat.containsKey("mime"))
            return "text/cea-708".equals(mediaformat.getString("mime"));
        else
            return false;
    }

    private Cea708CCWidget mCCWidget;
    private final Context mContext;
}
