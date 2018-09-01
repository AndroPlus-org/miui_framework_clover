// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.content.*;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.Rating;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import miui.maml.*;
import miui.maml.data.IndexedVariable;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ElementGroup, ButtonScreenElement, TextScreenElement, ImageScreenElement, 
//            MusicController, ScreenElement, SpectrumVisualizerScreenElement, MusicLyricParser

public class MusicControlScreenElement extends ElementGroup
    implements ButtonScreenElement.ButtonActionListener
{
    private static class AlbumInfo
    {

        boolean update(String s, String s1, String s2)
        {
            String s3 = s;
            if(s != null)
                s3 = s.trim();
            s = s1;
            if(s1 != null)
                s = s1.trim();
            s1 = s2;
            if(s2 != null)
                s1 = s2.trim();
            boolean flag;
            if(TextUtils.equals(s3, title) && !(TextUtils.equals(s, artist) ^ true))
                flag = TextUtils.equals(s1, album) ^ true;
            else
                flag = true;
            if(flag)
            {
                title = s3;
                artist = s;
                album = s1;
            }
            return flag;
        }

        String album;
        String artist;
        String title;

        private AlbumInfo()
        {
        }

        AlbumInfo(AlbumInfo albuminfo)
        {
            this();
        }
    }


    static AlbumInfo _2D_get0(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mAlbumInfo;
    }

    static Bitmap _2D_get1(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mDefaultAlbumCoverBm;
    }

    static android.media.RemoteController.MetadataEditor _2D_get10(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mMetadataEditor;
    }

    static MusicController _2D_get11(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mMusicController;
    }

    static boolean _2D_get12(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mNeedUpdateLyric;
    }

    static IndexedVariable _2D_get13(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mPlayerClassVar;
    }

    static IndexedVariable _2D_get14(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mPlayerPackageVar;
    }

    static boolean _2D_get15(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mPlaying;
    }

    static IndexedVariable _2D_get16(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mPositionVar;
    }

    static int _2D_get17(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mUpdateProgressInterval;
    }

    static boolean _2D_get2(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mDisableNext;
    }

    static IndexedVariable _2D_get3(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mDisableNextVar;
    }

    static boolean _2D_get4(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mDisablePlay;
    }

    static IndexedVariable _2D_get5(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mDisablePlayVar;
    }

    static boolean _2D_get6(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mDisablePrev;
    }

    static IndexedVariable _2D_get7(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mDisablePrevVar;
    }

    static IndexedVariable _2D_get8(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mDurationVar;
    }

    static MusicLyricParser.Lyric _2D_get9(MusicControlScreenElement musiccontrolscreenelement)
    {
        return musiccontrolscreenelement.mLyric;
    }

    static boolean _2D_set0(MusicControlScreenElement musiccontrolscreenelement, boolean flag)
    {
        musiccontrolscreenelement.mDisableNext = flag;
        return flag;
    }

    static boolean _2D_set1(MusicControlScreenElement musiccontrolscreenelement, boolean flag)
    {
        musiccontrolscreenelement.mDisablePlay = flag;
        return flag;
    }

    static boolean _2D_set2(MusicControlScreenElement musiccontrolscreenelement, boolean flag)
    {
        musiccontrolscreenelement.mDisablePrev = flag;
        return flag;
    }

    static MusicLyricParser.Lyric _2D_set3(MusicControlScreenElement musiccontrolscreenelement, MusicLyricParser.Lyric lyric)
    {
        musiccontrolscreenelement.mLyric = lyric;
        return lyric;
    }

    static android.media.RemoteController.MetadataEditor _2D_set4(MusicControlScreenElement musiccontrolscreenelement, android.media.RemoteController.MetadataEditor metadataeditor)
    {
        musiccontrolscreenelement.mMetadataEditor = metadataeditor;
        return metadataeditor;
    }

    static void _2D_wrap0(MusicControlScreenElement musiccontrolscreenelement, long l)
    {
        musiccontrolscreenelement.delayToSetDefaultArtwork(l);
    }

    static void _2D_wrap1(MusicControlScreenElement musiccontrolscreenelement, boolean flag)
    {
        musiccontrolscreenelement.onMusicStateChange(flag);
    }

    static void _2D_wrap2(MusicControlScreenElement musiccontrolscreenelement)
    {
        musiccontrolscreenelement.resetAll();
    }

    static void _2D_wrap3(MusicControlScreenElement musiccontrolscreenelement)
    {
        musiccontrolscreenelement.resetUserRating();
    }

    static void _2D_wrap4(MusicControlScreenElement musiccontrolscreenelement, String s, String s1, String s2)
    {
        musiccontrolscreenelement.updateAlbum(s, s1, s2);
    }

    static void _2D_wrap5(MusicControlScreenElement musiccontrolscreenelement, Bitmap bitmap)
    {
        musiccontrolscreenelement.updateArtwork(bitmap);
    }

    static void _2D_wrap6(MusicControlScreenElement musiccontrolscreenelement, long l)
    {
        musiccontrolscreenelement.updateLyricVar(l);
    }

    static void _2D_wrap7(MusicControlScreenElement musiccontrolscreenelement, MusicLyricParser.Lyric lyric)
    {
        musiccontrolscreenelement.updateLyric(lyric);
    }

    static void _2D_wrap8(MusicControlScreenElement musiccontrolscreenelement, long l, long l1)
    {
        musiccontrolscreenelement.updateProgress(l, l1);
    }

    static void _2D_wrap9(MusicControlScreenElement musiccontrolscreenelement, Rating rating)
    {
        musiccontrolscreenelement.updateUserRating(rating);
    }

    public MusicControlScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        boolean flag = false;
        super(element, screenelementroot);
        mAlbumInfo = new AlbumInfo(null);
        mMusicUpdateListener = new android.media.RemoteController.OnClientUpdateListener() {

            public void onClientChange(boolean flag2)
            {
                mClientChanged = true;
                readPackageName();
                StringBuilder stringbuilder = (new StringBuilder()).append("clientChange: ");
                String s;
                if(MusicControlScreenElement._2D_get14(MusicControlScreenElement.this) != null)
                    s = MusicControlScreenElement._2D_get14(MusicControlScreenElement.this).getString();
                else
                    s = "null";
                stringbuilder = stringbuilder.append(s).append("/");
                if(MusicControlScreenElement._2D_get13(MusicControlScreenElement.this) != null)
                    s = MusicControlScreenElement._2D_get13(MusicControlScreenElement.this).getString();
                else
                    s = "null";
                Log.d("MusicControlScreenElement", stringbuilder.append(s).append(" clearing:").append(flag2).toString());
                MusicControlScreenElement._2D_wrap2(MusicControlScreenElement.this);
            }

            public void onClientFolderInfoBrowsedPlayer(String s)
            {
            }

            public void onClientMetadataUpdate(android.media.RemoteController.MetadataEditor metadataeditor)
            {
                boolean flag2;
                Object obj;
                Object obj1;
                Object obj2;
                MusicControlScreenElement._2D_set4(MusicControlScreenElement.this, metadataeditor);
                flag2 = false;
                obj = metadataeditor.getString(7, null);
                obj1 = metadataeditor.getString(2, null);
                obj2 = metadataeditor.getString(1, null);
                Log.d("MusicControlScreenElement", (new StringBuilder()).append("\ntitle: ").append(((String) (obj))).append(", artist: ").append(((String) (obj1))).append(", album: ").append(((String) (obj2))).toString());
                break MISSING_BLOCK_LABEL_80;
                boolean flag3;
                int i;
                long l;
                long l1;
                if(obj != null || obj1 != null || obj2 != null)
                {
                    flag2 = MusicControlScreenElement._2D_get0(MusicControlScreenElement.this).update(((String) (obj)), ((String) (obj1)), ((String) (obj2)));
                    MusicControlScreenElement._2D_wrap4(MusicControlScreenElement.this, ((String) (obj)), ((String) (obj1)), ((String) (obj2)));
                }
                obj2 = metadataeditor.getBitmap(100, null);
                obj = (new StringBuilder()).append("artwork: ");
                if(obj2 != null)
                    obj1 = ((Bitmap) (obj2)).toString();
                else
                    obj1 = "null";
                Log.d("MusicControlScreenElement", ((StringBuilder) (obj)).append(((String) (obj1))).toString());
                if(obj2 == null)
                    flag3 = flag2;
                else
                    flag3 = true;
                if(flag3)
                    if(obj2 == null)
                        MusicControlScreenElement._2D_wrap0(MusicControlScreenElement.this, 500L);
                    else
                        MusicControlScreenElement._2D_wrap5(MusicControlScreenElement.this, ((Bitmap) (obj2)));
                obj1 = metadataeditor.getString(1000, null);
                Log.d("MusicControlScreenElement", (new StringBuilder()).append("raw lyric: ").append(((String) (obj1))).toString());
                obj1 = MusicLyricParser.parseLyric(((String) (obj1)));
                if(obj1 != null)
                    ((MusicLyricParser.Lyric) (obj1)).decorate();
                if(obj1 == null)
                    flag3 = flag2;
                else
                    flag3 = true;
                if(flag3)
                {
                    MusicControlScreenElement._2D_set3(MusicControlScreenElement.this, ((MusicLyricParser.Lyric) (obj1)));
                    MusicControlScreenElement._2D_wrap7(MusicControlScreenElement.this, ((MusicLyricParser.Lyric) (obj1)));
                }
                obj1 = MusicControlScreenElement.this;
                if(MusicControlScreenElement._2D_get9(MusicControlScreenElement.this) != null)
                    i = 30;
                else
                    i = 0;
                ((MusicControlScreenElement) (obj1)).requestFramerate(i);
                l = metadataeditor.getLong(9, -1L);
                l1 = MusicControlScreenElement._2D_get11(MusicControlScreenElement.this).getEstimatedMediaPosition();
                Log.d("MusicControlScreenElement", (new StringBuilder()).append("duration: ").append(l).append(", position: ").append(l1).toString());
                if(l >= 0L && l1 >= 0L)
                    flag2 = true;
                if(flag2)
                    MusicControlScreenElement._2D_wrap8(MusicControlScreenElement.this, l, l1);
                metadataeditor = (Rating)metadataeditor.getObject(0x10000001, null);
                Log.d("MusicControlScreenElement", (new StringBuilder()).append("rating: ").append(metadataeditor).toString());
                MusicControlScreenElement._2D_wrap9(MusicControlScreenElement.this, metadataeditor);
                if(!mClientChanged)
                    onClientChange(false);
                return;
            }

            public void onClientNowPlayingContentChange()
            {
            }

            public void onClientPlayItemResponse(boolean flag2)
            {
            }

            public void onClientPlaybackStateUpdate(int i)
            {
                onStateUpdate(i);
                Log.d("MusicControlScreenElement", (new StringBuilder()).append("stateUpdate: ").append(i).toString());
            }

            public void onClientPlaybackStateUpdate(int i, long l, long l1, float f)
            {
                onStateUpdate(i);
                Log.d("MusicControlScreenElement", (new StringBuilder()).append("stateUpdate2: ").append(i).toString());
            }

            public void onClientTransportControlUpdate(int i)
            {
                boolean flag2 = true;
                Log.d("MusicControlScreenElement", (new StringBuilder()).append("transportControlFlags: ").append(i).toString());
                boolean flag3;
                MusicControlScreenElement musiccontrolscreenelement;
                boolean flag4;
                if((i & 0x200) != 0)
                    flag3 = true;
                else
                    flag3 = false;
                if(!flag3)
                    MusicControlScreenElement._2D_wrap3(MusicControlScreenElement.this);
                musiccontrolscreenelement = MusicControlScreenElement.this;
                if(i != 0)
                {
                    if((i & 0x3c) == 0)
                        flag4 = true;
                    else
                        flag4 = false;
                } else
                {
                    flag4 = false;
                }
                MusicControlScreenElement._2D_set1(musiccontrolscreenelement, flag4);
                musiccontrolscreenelement = MusicControlScreenElement.this;
                if(i != 0)
                {
                    if((i & 1) == 0)
                        flag4 = true;
                    else
                        flag4 = false;
                } else
                {
                    flag4 = false;
                }
                MusicControlScreenElement._2D_set2(musiccontrolscreenelement, flag4);
                musiccontrolscreenelement = MusicControlScreenElement.this;
                if(i != 0)
                {
                    if((i & 0x80) == 0)
                        flag4 = true;
                    else
                        flag4 = false;
                } else
                {
                    flag4 = false;
                }
                MusicControlScreenElement._2D_set0(musiccontrolscreenelement, flag4);
                if(mHasName)
                {
                    IndexedVariable indexedvariable = MusicControlScreenElement._2D_get5(MusicControlScreenElement.this);
                    if(MusicControlScreenElement._2D_get4(MusicControlScreenElement.this))
                        i = 1;
                    else
                        i = 0;
                    indexedvariable.set(i);
                    indexedvariable = MusicControlScreenElement._2D_get7(MusicControlScreenElement.this);
                    if(MusicControlScreenElement._2D_get6(MusicControlScreenElement.this))
                        i = 1;
                    else
                        i = 0;
                    indexedvariable.set(i);
                    indexedvariable = MusicControlScreenElement._2D_get3(MusicControlScreenElement.this);
                    if(MusicControlScreenElement._2D_get2(MusicControlScreenElement.this))
                        i = ((flag2) ? 1 : 0);
                    else
                        i = 0;
                    indexedvariable.set(i);
                }
            }

            public void onClientUpdateNowPlayingEntries(long al[])
            {
            }

            protected void onStateUpdate(int i)
            {
                boolean flag2;
                boolean flag3;
                flag2 = false;
                flag3 = false;
                i;
                JVM INSTR tableswitch 0 3: default 36
            //                           0 85
            //                           1 51
            //                           2 51
            //                           3 68;
                   goto _L1 _L2 _L3 _L3 _L4
_L1:
                i = ((flag3) ? 1 : 0);
_L6:
                if(i != 0)
                    MusicControlScreenElement._2D_wrap1(MusicControlScreenElement.this, flag2);
                return;
_L3:
                flag2 = false;
                i = 1;
                performAction("state_stop");
                continue; /* Loop/switch isn't completed */
_L4:
                flag2 = true;
                i = 1;
                performAction("state_play");
                continue; /* Loop/switch isn't completed */
_L2:
                MusicControlScreenElement._2D_wrap2(MusicControlScreenElement.this);
                i = ((flag3) ? 1 : 0);
                if(true) goto _L6; else goto _L5
_L5:
            }

            private boolean mClientChanged;
            final MusicControlScreenElement this$0;

            
            {
                this$0 = MusicControlScreenElement.this;
                super();
            }
        }
;
        mDelayToSetArtworkRunnable = new Runnable() {

            public void run()
            {
                MusicControlScreenElement._2D_wrap5(MusicControlScreenElement.this, MusicControlScreenElement._2D_get1(MusicControlScreenElement.this));
            }

            final MusicControlScreenElement this$0;

            
            {
                this$0 = MusicControlScreenElement.this;
                super();
            }
        }
;
        mUpdateProgressRunnable = new Runnable() {

            public void run()
            {
                if(!MusicControlScreenElement._2D_get15(MusicControlScreenElement.this) || MusicControlScreenElement._2D_get10(MusicControlScreenElement.this) == null)
                    return;
                long l = MusicControlScreenElement._2D_get10(MusicControlScreenElement.this).getLong(9, -1L);
                long l1 = MusicControlScreenElement._2D_get11(MusicControlScreenElement.this).getEstimatedMediaPosition();
                if(l <= 0L || l1 < 0L)
                    return;
                MusicControlScreenElement._2D_get8(MusicControlScreenElement.this).set(l);
                MusicControlScreenElement._2D_get16(MusicControlScreenElement.this).set(l1);
                if(MusicControlScreenElement._2D_get12(MusicControlScreenElement.this) && MusicControlScreenElement._2D_get9(MusicControlScreenElement.this) != null)
                    MusicControlScreenElement._2D_wrap6(MusicControlScreenElement.this, l1);
                requestUpdate();
                getContext().getHandler().postDelayed(this, MusicControlScreenElement._2D_get17(MusicControlScreenElement.this));
            }

            final MusicControlScreenElement this$0;

            
            {
                this$0 = MusicControlScreenElement.this;
                super();
            }
        }
;
        mButtonPrev = (ButtonScreenElement)findElement("music_prev");
        mButtonNext = (ButtonScreenElement)findElement("music_next");
        mButtonPlay = (ButtonScreenElement)findElement("music_play");
        mButtonPause = (ButtonScreenElement)findElement("music_pause");
        mTextDisplay = (TextScreenElement)findElement("music_display");
        mImageAlbumCover = (ImageScreenElement)findElement("music_album_cover");
        mSpectrumVisualizer = findSpectrumVisualizer(this);
        setupButton(mButtonPrev);
        setupButton(mButtonNext);
        setupButton(mButtonPlay);
        setupButton(mButtonPause);
        if(mButtonPause != null)
            mButtonPause.show(false);
        if(mImageAlbumCover != null)
        {
            screenelementroot = element.getAttribute("defAlbumCover");
            if(!TextUtils.isEmpty(screenelementroot))
                mDefaultAlbumCoverBm = getContext().mResourceManager.getBitmap(screenelementroot);
            if(mDefaultAlbumCoverBm != null)
                mDefaultAlbumCoverBm.setDensity(mRoot.getResourceDensity());
        }
        mAutoShow = Boolean.parseBoolean(element.getAttribute("autoShow"));
        mEnableLyric = Boolean.parseBoolean(element.getAttribute("enableLyric"));
        boolean flag1;
        if(mEnableLyric)
            flag1 = true;
        else
            flag1 = Boolean.parseBoolean(element.getAttribute("enableProgress"));
        mEnableProgress = flag1;
        mUpdateProgressInterval = getAttrAsInt(element, "updateProgressInterval", 1000);
        if(mHasName)
        {
            element = getVariables();
            mMusicStateVar = new IndexedVariable((new StringBuilder()).append(mName).append(".music_state").toString(), element, true);
            mTitleVar = new IndexedVariable((new StringBuilder()).append(mName).append(".title").toString(), element, false);
            mArtistVar = new IndexedVariable((new StringBuilder()).append(mName).append(".artist").toString(), element, false);
            mAlbumVar = new IndexedVariable((new StringBuilder()).append(mName).append(".album").toString(), element, false);
            if(mEnableLyric)
            {
                mLyricCurrentVar = new IndexedVariable((new StringBuilder()).append(mName).append(".lyric_current").toString(), element, false);
                mLyricBeforeVar = new IndexedVariable((new StringBuilder()).append(mName).append(".lyric_before").toString(), element, false);
                mLyricAfterVar = new IndexedVariable((new StringBuilder()).append(mName).append(".lyric_after").toString(), element, false);
                mLyricLastVar = new IndexedVariable((new StringBuilder()).append(mName).append(".lyric_last").toString(), element, false);
                mLyricPrevVar = new IndexedVariable((new StringBuilder()).append(mName).append(".lyric_prev").toString(), element, false);
                mLyricNextVar = new IndexedVariable((new StringBuilder()).append(mName).append(".lyric_next").toString(), element, false);
                mLyricCurrentLineProgressVar = new IndexedVariable((new StringBuilder()).append(mName).append(".lyric_current_line_progress").toString(), element, true);
            }
            if(mEnableProgress)
            {
                mDurationVar = new IndexedVariable((new StringBuilder()).append(mName).append(".music_duration").toString(), element, true);
                mPositionVar = new IndexedVariable((new StringBuilder()).append(mName).append(".music_position").toString(), element, true);
            }
            mUserRatingStyleVar = new IndexedVariable((new StringBuilder()).append(mName).append(".user_rating_style").toString(), element, true);
            mUserRatingValueVar = new IndexedVariable((new StringBuilder()).append(mName).append(".user_rating_value").toString(), element, true);
            mDisablePlayVar = new IndexedVariable((new StringBuilder()).append(mName).append(".disable_play").toString(), element, true);
            mDisablePrevVar = new IndexedVariable((new StringBuilder()).append(mName).append(".disable_prev").toString(), element, true);
            mDisableNextVar = new IndexedVariable((new StringBuilder()).append(mName).append(".disable_next").toString(), element, true);
            mArtworkVar = new IndexedVariable((new StringBuilder()).append(mName).append(".artwork").toString(), element, false);
            mPlayerPackageVar = new IndexedVariable((new StringBuilder()).append(mName).append(".package").toString(), element, false);
            mPlayerClassVar = new IndexedVariable((new StringBuilder()).append(mName).append(".class").toString(), element, false);
        }
        if(mEnableLyric)
            flag1 = mHasName;
        else
            flag1 = false;
        mNeedUpdateLyric = flag1;
        flag1 = flag;
        if(mEnableProgress)
            flag1 = mHasName;
        mNeedUpdateProgress = flag1;
        mNeedUpdateUserRating = mHasName;
        try
        {
            mMiuiMusicContext = getContext().mContext.createPackageContext("com.miui.player", 2);
        }
        // Misplaced declaration of an exception variable
        catch(Element element)
        {
            Log.w("MusicControlScreenElement", "fail to get MiuiMusic preference", element);
        }
        mAudioManager = (AudioManager)getContext().mContext.getSystemService("audio");
        mMusicController = new MusicController(getContext().mContext);
        mMusicController.enableNotificationService();
        element = mRoot.getRootTag();
        mSender = "maml";
        if(!"gadget".equalsIgnoreCase(element)) goto _L2; else goto _L1
_L1:
        mSender = "home_widget";
_L4:
        return;
_L2:
        if("statusbar".equalsIgnoreCase(element))
            mSender = "notification_bar";
        else
        if("lockscreen".equalsIgnoreCase(element))
            mSender = "lockscreen";
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void cancelSetDefaultArtwork()
    {
        getContext().getHandler().removeCallbacks(mDelayToSetArtworkRunnable);
    }

    private void delayToSetDefaultArtwork(long l)
    {
        Handler handler = getContext().getHandler();
        handler.removeCallbacks(mDelayToSetArtworkRunnable);
        handler.postDelayed(mDelayToSetArtworkRunnable, l);
    }

    private SpectrumVisualizerScreenElement findSpectrumVisualizer(ElementGroup elementgroup)
    {
        for(elementgroup = elementgroup.getElements().iterator(); elementgroup.hasNext();)
        {
            Object obj = (ScreenElement)elementgroup.next();
            if(obj instanceof SpectrumVisualizerScreenElement)
                return (SpectrumVisualizerScreenElement)obj;
            if(obj instanceof ElementGroup)
            {
                obj = findSpectrumVisualizer((ElementGroup)obj);
                if(obj != null)
                    return ((SpectrumVisualizerScreenElement) (obj));
            }
        }

        return null;
    }

    private void onMusicStateChange(boolean flag)
    {
        boolean flag1 = false;
        mPlaying = flag;
        int i;
        if(mMusicStateVar != null)
        {
            IndexedVariable indexedvariable = mMusicStateVar;
            if(flag)
                i = 1;
            else
                i = 0;
            indexedvariable.set(i);
        }
        if(mButtonPause != null)
            mButtonPause.show(flag);
        if(mButtonPlay != null)
            mButtonPlay.show(flag ^ true);
        if(mNeedUpdateProgress)
        {
            if(flag)
                i = 100;
            else
                i = 0;
            startProgressUpdate(flag, i);
        }
        if(mSpectrumVisualizer != null)
            mSpectrumVisualizer.enableUpdate(flag);
        i = ((flag1) ? 1 : 0);
        if(flag)
        {
            i = ((flag1) ? 1 : 0);
            if(mLyric != null)
                i = 30;
        }
        requestFramerate(i);
        requestUpdate();
        Log.d("MusicControlScreenElement", (new StringBuilder()).append("music state change: playing=").append(flag).toString());
    }

    private void resetAll()
    {
        updateAlbum(null, null, null);
        resetProgress();
        resetLyric();
        resetUserRating();
        updateArtwork(mDefaultAlbumCoverBm);
        resetPackageName();
        resetMusicState();
    }

    private void resetLyric()
    {
        if(mNeedUpdateLyric)
        {
            mLyricBeforeVar.set(null);
            mLyricAfterVar.set(null);
            mLyricLastVar.set(null);
            mLyricPrevVar.set(null);
            mLyricNextVar.set(null);
            mLyricCurrentVar.set(null);
        }
    }

    private void resetMusicState()
    {
        onMusicStateChange(false);
    }

    private void resetPackageName()
    {
        if(mPlayerPackageVar != null)
            mPlayerPackageVar.set(null);
        if(mPlayerClassVar != null)
            mPlayerClassVar.set(null);
    }

    private void resetProgress()
    {
        if(mNeedUpdateProgress)
        {
            mDurationVar.set(0.0D);
            mPositionVar.set(0.0D);
        }
        if(mNeedUpdateLyric)
            mLyricCurrentLineProgressVar.set(0.0D);
    }

    private void resetUserRating()
    {
        if(mNeedUpdateUserRating)
        {
            mUserRatingStyle = 0;
            mUserRatingValue = 0.0F;
            mUserRatingStyleVar.set(0.0D);
            mUserRatingValueVar.set(0.0D);
        }
    }

    private boolean sendMediaKeyEvent(int i, String s)
    {
        byte byte0 = 0;
        if(!"music_prev".equals(s)) goto _L2; else goto _L1
_L1:
        byte0 = 88;
_L4:
        if(byte0 == 88 && mDisablePrev)
            return false;
        break; /* Loop/switch isn't completed */
_L2:
        if("music_next".equals(s))
            byte0 = 87;
        else
        if("music_play".equals(s) || "music_pause".equals(s))
            byte0 = 85;
        if(true) goto _L4; else goto _L3
_L3:
        if(byte0 == 87 && mDisableNext)
            return false;
        if(byte0 == 85 && mDisablePlay)
            return false;
        if(mMusicController.sendMediaKeyEvent(i, byte0))
            return true;
        Log.d("MusicControlScreenElement", "fail to dispatch by remote controller, send to MiuiMusic.");
        if(i == 0)
            return true;
        Intent intent = null;
        if("music_play".equals(s) || "music_pause".equals(s))
            intent = new Intent("com.miui.player.musicservicecommand.togglepause");
        else
        if("music_prev".equals(s))
            intent = new Intent("com.miui.player.musicservicecommand.previous");
        else
        if("music_next".equals(s))
            intent = new Intent("com.miui.player.musicservicecommand.next");
        if(intent != null)
        {
            intent.setPackage("com.miui.player");
            intent.putExtra("intent_sender", mSender);
            mRoot.getContext().mContext.startService(intent);
            return true;
        } else
        {
            return false;
        }
    }

    private void setupButton(ButtonScreenElement buttonscreenelement)
    {
        if(buttonscreenelement != null)
        {
            buttonscreenelement.setListener(this);
            buttonscreenelement.setParent(this);
        }
    }

    private void startProgressUpdate(boolean flag, long l)
    {
        getContext().getHandler().removeCallbacks(mUpdateProgressRunnable);
        if(flag)
            if(l > 0L)
                getContext().getHandler().postDelayed(mUpdateProgressRunnable, l);
            else
                getContext().getHandler().post(mUpdateProgressRunnable);
    }

    private void updateAlbum(String s, String s1, String s2)
    {
        if(mHasName)
        {
            mTitleVar.set(s);
            mArtistVar.set(s1);
            mAlbumVar.set(s2);
        }
        if(mTextDisplay == null) goto _L2; else goto _L1
_L1:
        if(!TextUtils.isEmpty(s)) goto _L4; else goto _L3
_L3:
        s = s1;
_L6:
        mTextDisplay.setText(s);
_L2:
        requestUpdate();
        return;
_L4:
        if(!TextUtils.isEmpty(s1))
            s = String.format("%s   %s", new Object[] {
                s, s1
            });
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void updateArtwork(Bitmap bitmap)
    {
        cancelSetDefaultArtwork();
        if(mHasName)
            mArtworkVar.set(bitmap);
        if(mImageAlbumCover != null)
            mImageAlbumCover.setBitmap(bitmap);
        requestUpdate();
    }

    private void updateLyric(MusicLyricParser.Lyric lyric)
    {
        if(!mNeedUpdateLyric)
            return;
        if(lyric == null)
        {
            resetLyric();
            return;
        }
        int ai[] = lyric.getTimeArr();
        lyric = lyric.getStringArr();
        if(mLyric != null)
            mLyric.set(ai, lyric);
    }

    private void updateLyricVar(long l)
    {
        double d = mLyric.getLyricShot(l).percent;
        mLyricCurrentLineProgressVar.set(d);
        String s = mLyric.getLine(l);
        mLyricCurrentVar.set(s);
        s = mLyric.getBeforeLines(l);
        mLyricBeforeVar.set(s);
        s = mLyric.getAfterLines(l);
        mLyricAfterVar.set(s);
        s = mLyric.getLastLine(l);
        mLyricLastVar.set(s);
        mLyricPrevVar.set(s);
        s = mLyric.getNextLine(l);
        mLyricNextVar.set(s);
    }

    private void updateProgress(long l, long l1)
    {
        if(!mNeedUpdateProgress)
            return;
        if(l <= 0L || l1 < 0L)
        {
            resetProgress();
            return;
        }
        mDurationVar.set(l);
        mPositionVar.set(l1);
        if(mNeedUpdateLyric)
            if(mLyric != null)
                updateLyricVar(l1);
            else
                resetLyric();
        requestUpdate();
        startProgressUpdate(mPlaying, 0L);
    }

    private void updateUserRating(Rating rating)
    {
        float f;
        f = 1.0F;
        if(!mNeedUpdateUserRating)
            return;
        if(rating == null)
        {
            resetUserRating();
            return;
        }
        mUserRatingStyle = rating.getRatingStyle();
        mUserRatingStyle;
        JVM INSTR tableswitch 1 6: default 68
    //                   1 102
    //                   2 122
    //                   3 142
    //                   4 142
    //                   5 142
    //                   6 153;
           goto _L1 _L2 _L3 _L4 _L4 _L4 _L5
_L1:
        mUserRatingValue = 0.0F;
_L7:
        mUserRatingStyleVar.set(mUserRatingStyle);
        mUserRatingValueVar.set(mUserRatingValue);
        requestUpdate();
        return;
_L2:
        if(!rating.hasHeart())
            f = 0.0F;
        mUserRatingValue = f;
        continue; /* Loop/switch isn't completed */
_L3:
        if(!rating.isThumbUp())
            f = 0.0F;
        mUserRatingValue = f;
        continue; /* Loop/switch isn't completed */
_L4:
        mUserRatingValue = rating.getStarRating();
        continue; /* Loop/switch isn't completed */
_L5:
        mUserRatingValue = rating.getPercentRating();
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void finish()
    {
        super.finish();
        if(mArtworkVar != null)
            mArtworkVar.set(null);
        mMusicController.unregisterListener();
        Handler handler = getContext().getHandler();
        handler.removeCallbacks(mUpdateProgressRunnable);
        handler.removeCallbacks(mDelayToSetArtworkRunnable);
    }

    public void init()
    {
        super.init();
        initByPreference();
        mMusicController.registerListener(mMusicUpdateListener);
        boolean flag = mAudioManager.isMusicActive();
        if(mAutoShow && flag)
        {
            show(true);
            onMusicStateChange(true);
        } else
        {
            onMusicStateChange(false);
        }
    }

    public void initByPreference()
    {
        if(mMiuiMusicContext != null)
        {
            SharedPreferences sharedpreferences = mMiuiMusicContext.getSharedPreferences("MiuiMusic", 4);
            if(sharedpreferences != null)
            {
                updateAlbum(sharedpreferences.getString("songName", null), sharedpreferences.getString("artistName", null), sharedpreferences.getString("albumName", null));
                updateArtwork(mDefaultAlbumCoverBm);
            }
        }
    }

    public boolean onButtonDoubleClick(String s)
    {
        return false;
    }

    public boolean onButtonDown(String s)
    {
        return sendMediaKeyEvent(0, s);
    }

    public boolean onButtonLongClick(String s)
    {
        return false;
    }

    public boolean onButtonUp(String s)
    {
        if(!sendMediaKeyEvent(1, s))
            return false;
        if("music_prev".equals(s) || "music_next".equals(s))
        {
            cancelSetDefaultArtwork();
            getContext().getHandler().removeCallbacks(mUpdateProgressRunnable);
        }
        return true;
    }

    protected void onVisibilityChange(boolean flag)
    {
        super.onVisibilityChange(flag);
        if(flag)
            requestUpdate();
        else
            requestFramerate(0.0F);
    }

    public void pause()
    {
        super.pause();
    }

    public void ratingHeart()
    {
        if(mUserRatingStyle == 1)
        {
            boolean flag;
            Rating rating;
            if(mUserRatingValue == 1.0F)
                flag = true;
            else
                flag = false;
            rating = Rating.newHeartRating(flag ^ true);
            mMusicController.rating(rating);
            updateUserRating(rating);
        }
    }

    protected void readPackageName()
    {
        if(mPlayerPackageVar == null || mPlayerClassVar == null)
            return;
        String s = mMusicController.getRemoteControlClientPackageName();
        Log.d("MusicControlScreenElement", (new StringBuilder()).append("readPackage: ").append(s).toString());
        if(s != null)
        {
            Object obj = mRoot.getContext().mContext.getPackageManager().getLaunchIntentForPackage(s);
            if(obj != null)
            {
                obj = ((Intent) (obj)).getComponent();
                mPlayerPackageVar.set(((ComponentName) (obj)).getPackageName());
                mPlayerClassVar.set(((ComponentName) (obj)).getClassName());
            } else
            {
                mPlayerPackageVar.set(s);
                mPlayerClassVar.set(null);
                Log.w("MusicControlScreenElement", "set player info fail.");
            }
        }
    }

    public void resume()
    {
        super.resume();
        requestUpdate();
    }

    public void seekTo(double d)
    {
        long l = (long)(mDurationVar.getDouble() * d);
        mMusicController.seekTo(l);
    }

    private static final String BUTTON_MUSIC_NEXT = "music_next";
    private static final String BUTTON_MUSIC_PAUSE = "music_pause";
    private static final String BUTTON_MUSIC_PLAY = "music_play";
    private static final String BUTTON_MUSIC_PREV = "music_prev";
    private static final String ELE_MUSIC_ALBUM_COVER = "music_album_cover";
    private static final String ELE_MUSIC_DISPLAY = "music_display";
    private static final int FRAMERATE_PLAYING = 30;
    private static final String LOG_TAG = "MusicControlScreenElement";
    private static final String MIUI_PLAYER_PACKAGE_NAME = "com.miui.player";
    public static final int MUSIC_STATE_PLAY = 1;
    public static final int MUSIC_STATE_STOP = 0;
    public static final String TAG_NAME = "MusicControl";
    private AlbumInfo mAlbumInfo;
    private IndexedVariable mAlbumVar;
    private IndexedVariable mArtistVar;
    private IndexedVariable mArtworkVar;
    private AudioManager mAudioManager;
    private boolean mAutoShow;
    private ButtonScreenElement mButtonNext;
    private ButtonScreenElement mButtonPause;
    private ButtonScreenElement mButtonPlay;
    private ButtonScreenElement mButtonPrev;
    private Bitmap mDefaultAlbumCoverBm;
    private Runnable mDelayToSetArtworkRunnable;
    private boolean mDisableNext;
    private IndexedVariable mDisableNextVar;
    private boolean mDisablePlay;
    private IndexedVariable mDisablePlayVar;
    private boolean mDisablePrev;
    private IndexedVariable mDisablePrevVar;
    private IndexedVariable mDurationVar;
    private boolean mEnableLyric;
    private boolean mEnableProgress;
    private ImageScreenElement mImageAlbumCover;
    private MusicLyricParser.Lyric mLyric;
    private IndexedVariable mLyricAfterVar;
    private IndexedVariable mLyricBeforeVar;
    private IndexedVariable mLyricCurrentLineProgressVar;
    private IndexedVariable mLyricCurrentVar;
    private IndexedVariable mLyricLastVar;
    private IndexedVariable mLyricNextVar;
    private IndexedVariable mLyricPrevVar;
    private android.media.RemoteController.MetadataEditor mMetadataEditor;
    private Context mMiuiMusicContext;
    private MusicController mMusicController;
    private IndexedVariable mMusicStateVar;
    private android.media.RemoteController.OnClientUpdateListener mMusicUpdateListener;
    private boolean mNeedUpdateLyric;
    private boolean mNeedUpdateProgress;
    private boolean mNeedUpdateUserRating;
    private IndexedVariable mPlayerClassVar;
    private IndexedVariable mPlayerPackageVar;
    private boolean mPlaying;
    private IndexedVariable mPositionVar;
    private String mSender;
    private SpectrumVisualizerScreenElement mSpectrumVisualizer;
    private TextScreenElement mTextDisplay;
    private IndexedVariable mTitleVar;
    private int mUpdateProgressInterval;
    private Runnable mUpdateProgressRunnable;
    private int mUserRatingStyle;
    private IndexedVariable mUserRatingStyleVar;
    private float mUserRatingValue;
    private IndexedVariable mUserRatingValueVar;
}
