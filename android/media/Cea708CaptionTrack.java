// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import java.util.Vector;

// Referenced classes of package android.media:
//            SubtitleTrack, Cea708CCParser, Cea708CCWidget, MediaFormat

class Cea708CaptionTrack extends SubtitleTrack
{

    Cea708CaptionTrack(Cea708CCWidget cea708ccwidget, MediaFormat mediaformat)
    {
        super(mediaformat);
        mRenderingWidget = cea708ccwidget;
        mCCParser = new Cea708CCParser(mRenderingWidget);
    }

    public SubtitleTrack.RenderingWidget getRenderingWidget()
    {
        return mRenderingWidget;
    }

    public void onData(byte abyte0[], boolean flag, long l)
    {
        mCCParser.parse(abyte0);
    }

    public void updateView(Vector vector)
    {
    }

    private final Cea708CCParser mCCParser;
    private final Cea708CCWidget mRenderingWidget;
}
