// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;

// Referenced classes of package android.media:
//            MediaFormat, Cea608CCWidget, Cea608CaptionTrack, SubtitleTrack

public class ClosedCaptionRenderer extends SubtitleController.Renderer
{

    public ClosedCaptionRenderer(Context context)
    {
        mContext = context;
    }

    public SubtitleTrack createTrack(MediaFormat mediaformat)
    {
        if("text/cea-608".equals(mediaformat.getString("mime")))
        {
            if(mCCWidget == null)
                mCCWidget = new Cea608CCWidget(mContext);
            return new Cea608CaptionTrack(mCCWidget, mediaformat);
        } else
        {
            throw new RuntimeException((new StringBuilder()).append("No matching format: ").append(mediaformat.toString()).toString());
        }
    }

    public boolean supports(MediaFormat mediaformat)
    {
        if(mediaformat.containsKey("mime"))
            return "text/cea-608".equals(mediaformat.getString("mime"));
        else
            return false;
    }

    private Cea608CCWidget mCCWidget;
    private final Context mContext;
}
