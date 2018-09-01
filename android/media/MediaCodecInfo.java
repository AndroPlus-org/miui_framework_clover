// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.util.*;
import java.util.*;

// Referenced classes of package android.media:
//            AudioTrack, Utils, MediaFormat, MediaCodecList

public final class MediaCodecInfo
{
    public static final class AudioCapabilities
    {

        private void applyLevelLimits()
        {
            Range range;
            int ai[] = null;
            range = null;
            Range range1 = null;
            int i = 30;
            Object obj = mParent.getMimeType();
            if(((String) (obj)).equalsIgnoreCase("audio/mpeg"))
            {
                ai = new int[9];
                ai;
                ai[0] = 8000;
                ai[1] = 11025;
                ai[2] = 12000;
                ai[3] = 16000;
                ai[4] = 22050;
                ai[5] = 24000;
                ai[6] = 32000;
                ai[7] = 44100;
                ai[8] = 48000;
                range1 = Range.create(Integer.valueOf(8000), Integer.valueOf(0x4e200));
                i = 2;
            } else
            if(((String) (obj)).equalsIgnoreCase("audio/3gpp"))
            {
                ai = new int[1];
                ai[0] = 8000;
                range1 = Range.create(Integer.valueOf(4750), Integer.valueOf(12200));
                i = 1;
            } else
            if(((String) (obj)).equalsIgnoreCase("audio/amr-wb"))
            {
                ai = new int[1];
                ai[0] = 16000;
                range1 = Range.create(Integer.valueOf(6600), Integer.valueOf(23850));
                i = 1;
            } else
            if(((String) (obj)).equalsIgnoreCase("audio/mp4a-latm"))
            {
                ai = new int[13];
                ai;
                ai[0] = 7350;
                ai[1] = 8000;
                ai[2] = 11025;
                ai[3] = 12000;
                ai[4] = 16000;
                ai[5] = 22050;
                ai[6] = 24000;
                ai[7] = 32000;
                ai[8] = 44100;
                ai[9] = 48000;
                ai[10] = 64000;
                ai[11] = 0x15888;
                ai[12] = 0x17700;
                range1 = Range.create(Integer.valueOf(8000), Integer.valueOf(0x7c830));
                i = 48;
            } else
            if(((String) (obj)).equalsIgnoreCase("audio/vorbis"))
            {
                range1 = Range.create(Integer.valueOf(32000), Integer.valueOf(0x7a120));
                range = Range.create(Integer.valueOf(8000), Integer.valueOf(0x2ee00));
                i = 255;
            } else
            if(((String) (obj)).equalsIgnoreCase("audio/opus"))
            {
                range1 = Range.create(Integer.valueOf(6000), Integer.valueOf(0x7c830));
                ai = (new int[] {
                    8000, 12000, 16000, 24000, 48000
                });
                i = 255;
            } else
            if(((String) (obj)).equalsIgnoreCase("audio/raw"))
            {
                range = Range.create(Integer.valueOf(1), Integer.valueOf(0x17700));
                range1 = Range.create(Integer.valueOf(1), Integer.valueOf(0x989680));
                i = AudioTrack.CHANNEL_COUNT_MAX;
            } else
            if(((String) (obj)).equalsIgnoreCase("audio/flac"))
            {
                range = Range.create(Integer.valueOf(1), Integer.valueOf(0x9fff6));
                i = 255;
            } else
            if(((String) (obj)).equalsIgnoreCase("audio/g711-alaw") || ((String) (obj)).equalsIgnoreCase("audio/g711-mlaw"))
            {
                ai = new int[1];
                ai[0] = 8000;
                range1 = Range.create(Integer.valueOf(64000), Integer.valueOf(64000));
            } else
            if(((String) (obj)).equalsIgnoreCase("audio/gsm"))
            {
                ai = new int[1];
                ai[0] = 8000;
                range1 = Range.create(Integer.valueOf(13000), Integer.valueOf(13000));
                i = 1;
            } else
            if(((String) (obj)).equalsIgnoreCase("audio/ac3"))
                i = 6;
            else
            if(((String) (obj)).equalsIgnoreCase("audio/eac3"))
            {
                i = 16;
            } else
            {
                Log.w("AudioCapabilities", (new StringBuilder()).append("Unsupported mime ").append(((String) (obj))).toString());
                obj = mParent;
                obj.mError = ((CodecCapabilities) (obj)).mError | 2;
            }
            if(ai == null) goto _L2; else goto _L1
_L1:
            limitSampleRates(ai);
_L4:
            applyLimits(i, range1);
            return;
_L2:
            if(range != null)
                limitSampleRates(new Range[] {
                    range
                });
            if(true) goto _L4; else goto _L3
_L3:
        }

        private void applyLimits(int i, Range range)
        {
            mMaxInputChannelCount = ((Integer)Range.create(Integer.valueOf(1), Integer.valueOf(mMaxInputChannelCount)).clamp(Integer.valueOf(i))).intValue();
            if(range != null)
                mBitrateRange = mBitrateRange.intersect(range);
        }

        public static AudioCapabilities create(MediaFormat mediaformat, CodecCapabilities codeccapabilities)
        {
            AudioCapabilities audiocapabilities = new AudioCapabilities();
            audiocapabilities.init(mediaformat, codeccapabilities);
            return audiocapabilities;
        }

        private void createDiscreteSampleRates()
        {
            mSampleRates = new int[mSampleRateRanges.length];
            for(int i = 0; i < mSampleRateRanges.length; i++)
                mSampleRates[i] = ((Integer)mSampleRateRanges[i].getLower()).intValue();

        }

        private void initWithPlatformLimits()
        {
            mBitrateRange = Range.create(Integer.valueOf(0), Integer.valueOf(0x7fffffff));
            mMaxInputChannelCount = 30;
            mSampleRateRanges = (new Range[] {
                Range.create(Integer.valueOf(8000), Integer.valueOf(0x17700))
            });
            mSampleRates = null;
        }

        private void limitSampleRates(int ai[])
        {
            Arrays.sort(ai);
            ArrayList arraylist = new ArrayList();
            int i = 0;
            for(int j = ai.length; i < j; i++)
            {
                int k = ai[i];
                if(supports(Integer.valueOf(k), null))
                    arraylist.add(Range.create(Integer.valueOf(k), Integer.valueOf(k)));
            }

            mSampleRateRanges = (Range[])arraylist.toArray(new Range[arraylist.size()]);
            createDiscreteSampleRates();
        }

        private void limitSampleRates(Range arange[])
        {
            Utils.sortDistinctRanges(arange);
            mSampleRateRanges = Utils.intersectSortedDistinctRanges(mSampleRateRanges, arange);
            Range arange1[] = mSampleRateRanges;
            int i = arange1.length;
            for(int j = 0; j < i; j++)
            {
                arange = arange1[j];
                if(!((Integer)arange.getLower()).equals(arange.getUpper()))
                {
                    mSampleRates = null;
                    return;
                }
            }

            createDiscreteSampleRates();
        }

        private void parseFromInfo(MediaFormat mediaformat)
        {
            byte byte0;
            Range range;
            byte0 = 30;
            range = MediaCodecInfo._2D_get2();
            if(mediaformat.containsKey("sample-rate-ranges"))
            {
                String as[] = mediaformat.getString("sample-rate-ranges").split(",");
                Range arange[] = new Range[as.length];
                for(int i = 0; i < as.length; i++)
                    arange[i] = Utils.parseIntRange(as[i], null);

                limitSampleRates(arange);
            }
            if(!mediaformat.containsKey("max-channel-count")) goto _L2; else goto _L1
_L1:
            int j = Utils.parseIntSafely(mediaformat.getString("max-channel-count"), 30);
_L4:
            Range range1 = range;
            if(mediaformat.containsKey("bitrate-range"))
                range1 = range.intersect(Utils.parseIntRange(mediaformat.getString("bitrate-range"), range));
            applyLimits(j, range1);
            return;
_L2:
            j = byte0;
            if((mParent.mError & 2) != 0)
                j = 0;
            if(true) goto _L4; else goto _L3
_L3:
        }

        private boolean supports(Integer integer, Integer integer1)
        {
            if(integer1 != null && (integer1.intValue() < 1 || integer1.intValue() > mMaxInputChannelCount))
                return false;
            return integer == null || Utils.binarySearchDistinctRanges(mSampleRateRanges, integer) >= 0;
        }

        public Range getBitrateRange()
        {
            return mBitrateRange;
        }

        public int getMaxInputChannelCount()
        {
            return mMaxInputChannelCount;
        }

        public Range[] getSupportedSampleRateRanges()
        {
            return (Range[])Arrays.copyOf(mSampleRateRanges, mSampleRateRanges.length);
        }

        public int[] getSupportedSampleRates()
        {
            return Arrays.copyOf(mSampleRates, mSampleRates.length);
        }

        public void init(MediaFormat mediaformat, CodecCapabilities codeccapabilities)
        {
            mParent = codeccapabilities;
            initWithPlatformLimits();
            applyLevelLimits();
            parseFromInfo(mediaformat);
        }

        public boolean isSampleRateSupported(int i)
        {
            return supports(Integer.valueOf(i), null);
        }

        public void setDefaultFormat(MediaFormat mediaformat)
        {
            if(((Integer)mBitrateRange.getLower()).equals(mBitrateRange.getUpper()))
                mediaformat.setInteger("bitrate", ((Integer)mBitrateRange.getLower()).intValue());
            if(mMaxInputChannelCount == 1)
                mediaformat.setInteger("channel-count", 1);
            if(mSampleRates != null && mSampleRates.length == 1)
                mediaformat.setInteger("sample-rate", mSampleRates[0]);
        }

        public boolean supportsFormat(MediaFormat mediaformat)
        {
            Map map = mediaformat.getMap();
            if(!supports((Integer)map.get("sample-rate"), (Integer)map.get("channel-count")))
                return false;
            return CodecCapabilities._2D_wrap0(mBitrateRange, mediaformat);
        }

        private static final int MAX_INPUT_CHANNEL_COUNT = 30;
        private static final String TAG = "AudioCapabilities";
        private Range mBitrateRange;
        private int mMaxInputChannelCount;
        private CodecCapabilities mParent;
        private Range mSampleRateRanges[];
        private int mSampleRates[];

        private AudioCapabilities()
        {
        }
    }

    public static final class CodecCapabilities
    {

        static boolean _2D_wrap0(Range range, MediaFormat mediaformat)
        {
            return supportsBitrate(range, mediaformat);
        }

        private boolean checkFeature(String s, int i)
        {
            boolean flag = false;
            Feature afeature[] = getValidFeatures();
            int j = afeature.length;
            for(int k = 0; k < j; k++)
            {
                Feature feature = afeature[k];
                if(feature.mName.equals(s))
                {
                    if((feature.mValue & i) != 0)
                        flag = true;
                    return flag;
                }
            }

            return false;
        }

        public static CodecCapabilities createFromProfileLevel(String s, int i, int j)
        {
            CodecProfileLevel codecprofilelevel = new CodecProfileLevel();
            codecprofilelevel.profile = i;
            codecprofilelevel.level = j;
            MediaFormat mediaformat = new MediaFormat();
            mediaformat.setString("mime", s);
            s = new MediaFormat();
            s = new CodecCapabilities(new CodecProfileLevel[] {
                codecprofilelevel
            }, new int[0], true, 0, mediaformat, s);
            if(((CodecCapabilities) (s)).mError != 0)
                return null;
            else
                return s;
        }

        private Feature[] getValidFeatures()
        {
            if(!isEncoder())
                return decoderFeatures;
            else
                return encoderFeatures;
        }

        private boolean isAudio()
        {
            boolean flag;
            if(mAudioCaps != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private boolean isEncoder()
        {
            boolean flag;
            if(mEncoderCaps != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private boolean isVideo()
        {
            boolean flag;
            if(mVideoCaps != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private static boolean supportsBitrate(Range range, MediaFormat mediaformat)
        {
            mediaformat = mediaformat.getMap();
            Integer integer = (Integer)mediaformat.get("max-bitrate");
            Integer integer1 = (Integer)mediaformat.get("bitrate");
            if(integer1 == null)
            {
                mediaformat = integer;
            } else
            {
                mediaformat = integer1;
                if(integer != null)
                    mediaformat = Integer.valueOf(Math.max(integer1.intValue(), integer.intValue()));
            }
            if(mediaformat != null && mediaformat.intValue() > 0)
                return range.contains(mediaformat);
            else
                return true;
        }

        private boolean supportsProfileLevel(int i, Integer integer)
        {
            boolean flag;
            CodecProfileLevel acodecprofilelevel[];
            int j;
            int k;
            flag = true;
            acodecprofilelevel = profileLevels;
            j = acodecprofilelevel.length;
            k = 0;
_L2:
            CodecProfileLevel codecprofilelevel;
            if(k >= j)
                break MISSING_BLOCK_LABEL_267;
            codecprofilelevel = acodecprofilelevel[k];
            if(codecprofilelevel.profile == i)
                break; /* Loop/switch isn't completed */
_L4:
            k++;
            if(true) goto _L2; else goto _L1
_L1:
            if(integer == null || mMime.equalsIgnoreCase("audio/mp4a-latm"))
                return true;
            if(mMime.equalsIgnoreCase("video/3gpp") && codecprofilelevel.level != integer.intValue() && codecprofilelevel.level == 16 && integer.intValue() > 1 || mMime.equalsIgnoreCase("video/mp4v-es") && codecprofilelevel.level != integer.intValue() && codecprofilelevel.level == 4 && integer.intValue() > 1) goto _L4; else goto _L3
_L3:
            if(!mMime.equalsIgnoreCase("video/hevc"))
                break; /* Loop/switch isn't completed */
            boolean flag1;
            boolean flag2;
            if((codecprofilelevel.level & 0x2aaaaaa) != 0)
                flag1 = true;
            else
                flag1 = false;
            if((integer.intValue() & 0x2aaaaaa) != 0)
                flag2 = true;
            else
                flag2 = false;
            if(flag2 && flag1 ^ true) goto _L4; else goto _L5
_L5:
            if(codecprofilelevel.level < integer.intValue()) goto _L4; else goto _L6
_L6:
            if(createFromProfileLevel(mMime, i, codecprofilelevel.level) != null)
            {
                if(createFromProfileLevel(mMime, i, integer.intValue()) == null)
                    flag = false;
                return flag;
            } else
            {
                return true;
            }
            return false;
        }

        public CodecCapabilities dup()
        {
            return new CodecCapabilities((CodecProfileLevel[])Arrays.copyOf(profileLevels, profileLevels.length), Arrays.copyOf(colorFormats, colorFormats.length), isEncoder(), mFlagsVerified, mDefaultFormat, mCapabilitiesInfo);
        }

        public AudioCapabilities getAudioCapabilities()
        {
            return mAudioCaps;
        }

        public MediaFormat getDefaultFormat()
        {
            return mDefaultFormat;
        }

        public EncoderCapabilities getEncoderCapabilities()
        {
            return mEncoderCaps;
        }

        public int getMaxSupportedInstances()
        {
            return mMaxSupportedInstances;
        }

        public String getMimeType()
        {
            return mMime;
        }

        public VideoCapabilities getVideoCapabilities()
        {
            return mVideoCaps;
        }

        public final boolean isFeatureRequired(String s)
        {
            return checkFeature(s, mFlagsRequired);
        }

        public final boolean isFeatureSupported(String s)
        {
            return checkFeature(s, mFlagsSupported);
        }

        public final boolean isFormatSupported(MediaFormat mediaformat)
        {
            Object obj;
            Feature afeature[];
            int i;
            int k;
            obj = mediaformat.getMap();
            String s = (String)((Map) (obj)).get("mime");
            if(s != null && mMime.equalsIgnoreCase(s) ^ true)
                return false;
            afeature = getValidFeatures();
            i = 0;
            k = afeature.length;
_L3:
            Feature feature;
            Integer integer1;
            if(i >= k)
                break; /* Loop/switch isn't completed */
            feature = afeature[i];
            integer1 = (Integer)((Map) (obj)).get((new StringBuilder()).append("feature-").append(feature.mName).toString());
              goto _L1
_L5:
            i++;
            if(true) goto _L3; else goto _L2
_L1:
            if(integer1 == null || (integer1.intValue() != 1 || !(isFeatureSupported(feature.mName) ^ true)) && (integer1.intValue() != 0 || !isFeatureRequired(feature.mName))) goto _L5; else goto _L4
_L4:
            return false;
_L2:
            Object obj1 = (Integer)((Map) (obj)).get("profile");
            Integer integer = (Integer)((Map) (obj)).get("level");
            if(obj1 != null)
            {
                if(!supportsProfileLevel(((Integer) (obj1)).intValue(), integer))
                    return false;
                int l = 0;
                CodecProfileLevel acodecprofilelevel[] = profileLevels;
                int j = 0;
                for(int i1 = acodecprofilelevel.length; j < i1;)
                {
                    CodecProfileLevel codecprofilelevel = acodecprofilelevel[j];
                    int j1 = l;
                    if(codecprofilelevel.profile == ((Integer) (obj1)).intValue())
                    {
                        j1 = l;
                        if(codecprofilelevel.level > l)
                            j1 = codecprofilelevel.level;
                    }
                    j++;
                    l = j1;
                }

                obj1 = createFromProfileLevel(mMime, ((Integer) (obj1)).intValue(), l);
                obj = new HashMap(((Map) (obj)));
                ((Map) (obj)).remove("profile");
                obj = new MediaFormat(((Map) (obj)));
                if(obj1 != null && ((CodecCapabilities) (obj1)).isFormatSupported(((MediaFormat) (obj))) ^ true)
                    return false;
            }
            if(mAudioCaps != null && mAudioCaps.supportsFormat(mediaformat) ^ true)
                return false;
            if(mVideoCaps != null && mVideoCaps.supportsFormat(mediaformat) ^ true)
                return false;
            return mEncoderCaps == null || !(mEncoderCaps.supportsFormat(mediaformat) ^ true);
        }

        public boolean isRegular()
        {
            Feature afeature[] = getValidFeatures();
            int i = afeature.length;
            for(int j = 0; j < i; j++)
            {
                Feature feature = afeature[j];
                if(!feature.mDefault && isFeatureRequired(feature.mName))
                    return false;
            }

            return true;
        }

        public String[] validFeatures()
        {
            Feature afeature[] = getValidFeatures();
            String as[] = new String[afeature.length];
            for(int i = 0; i < as.length; i++)
                as[i] = afeature[i].mName;

            return as;
        }

        public static final int COLOR_Format12bitRGB444 = 3;
        public static final int COLOR_Format16bitARGB1555 = 5;
        public static final int COLOR_Format16bitARGB4444 = 4;
        public static final int COLOR_Format16bitBGR565 = 7;
        public static final int COLOR_Format16bitRGB565 = 6;
        public static final int COLOR_Format18BitBGR666 = 41;
        public static final int COLOR_Format18bitARGB1665 = 9;
        public static final int COLOR_Format18bitRGB666 = 8;
        public static final int COLOR_Format19bitARGB1666 = 10;
        public static final int COLOR_Format24BitABGR6666 = 43;
        public static final int COLOR_Format24BitARGB6666 = 42;
        public static final int COLOR_Format24bitARGB1887 = 13;
        public static final int COLOR_Format24bitBGR888 = 12;
        public static final int COLOR_Format24bitRGB888 = 11;
        public static final int COLOR_Format25bitARGB1888 = 14;
        public static final int COLOR_Format32bitABGR8888 = 0x7f00a000;
        public static final int COLOR_Format32bitARGB8888 = 16;
        public static final int COLOR_Format32bitBGRA8888 = 15;
        public static final int COLOR_Format8bitRGB332 = 2;
        public static final int COLOR_FormatCbYCrY = 27;
        public static final int COLOR_FormatCrYCbY = 28;
        public static final int COLOR_FormatL16 = 36;
        public static final int COLOR_FormatL2 = 33;
        public static final int COLOR_FormatL24 = 37;
        public static final int COLOR_FormatL32 = 38;
        public static final int COLOR_FormatL4 = 34;
        public static final int COLOR_FormatL8 = 35;
        public static final int COLOR_FormatMonochrome = 1;
        public static final int COLOR_FormatRGBAFlexible = 0x7f36a888;
        public static final int COLOR_FormatRGBFlexible = 0x7f36b888;
        public static final int COLOR_FormatRawBayer10bit = 31;
        public static final int COLOR_FormatRawBayer8bit = 30;
        public static final int COLOR_FormatRawBayer8bitcompressed = 32;
        public static final int COLOR_FormatSurface = 0x7f000789;
        public static final int COLOR_FormatYCbYCr = 25;
        public static final int COLOR_FormatYCrYCb = 26;
        public static final int COLOR_FormatYUV411PackedPlanar = 18;
        public static final int COLOR_FormatYUV411Planar = 17;
        public static final int COLOR_FormatYUV420Flexible = 0x7f420888;
        public static final int COLOR_FormatYUV420PackedPlanar = 20;
        public static final int COLOR_FormatYUV420PackedSemiPlanar = 39;
        public static final int COLOR_FormatYUV420Planar = 19;
        public static final int COLOR_FormatYUV420SemiPlanar = 21;
        public static final int COLOR_FormatYUV422Flexible = 0x7f422888;
        public static final int COLOR_FormatYUV422PackedPlanar = 23;
        public static final int COLOR_FormatYUV422PackedSemiPlanar = 40;
        public static final int COLOR_FormatYUV422Planar = 22;
        public static final int COLOR_FormatYUV422SemiPlanar = 24;
        public static final int COLOR_FormatYUV444Flexible = 0x7f444888;
        public static final int COLOR_FormatYUV444Interleaved = 29;
        public static final int COLOR_QCOM_FormatYUV420SemiPlanar = 0x7fa30c00;
        public static final int COLOR_TI_FormatYUV420PackedSemiPlanar = 0x7f000100;
        public static final String FEATURE_AdaptivePlayback = "adaptive-playback";
        public static final String FEATURE_IntraRefresh = "intra-refresh";
        public static final String FEATURE_PartialFrame = "partial-frame";
        public static final String FEATURE_SecurePlayback = "secure-playback";
        public static final String FEATURE_TunneledPlayback = "tunneled-playback";
        private static final String TAG = "CodecCapabilities";
        private static final Feature decoderFeatures[] = {
            new Feature("adaptive-playback", 1, true), new Feature("secure-playback", 2, false), new Feature("tunneled-playback", 4, false), new Feature("partial-frame", 8, false)
        };
        private static final Feature encoderFeatures[] = {
            new Feature("intra-refresh", 1, false)
        };
        public int colorFormats[];
        private AudioCapabilities mAudioCaps;
        private MediaFormat mCapabilitiesInfo;
        private MediaFormat mDefaultFormat;
        private EncoderCapabilities mEncoderCaps;
        int mError;
        private int mFlagsRequired;
        private int mFlagsSupported;
        private int mFlagsVerified;
        private int mMaxSupportedInstances;
        private String mMime;
        private VideoCapabilities mVideoCaps;
        public CodecProfileLevel profileLevels[];


        public CodecCapabilities()
        {
        }

        CodecCapabilities(CodecProfileLevel acodecprofilelevel[], int ai[], boolean flag, int i, MediaFormat mediaformat, MediaFormat mediaformat1)
        {
            Map map = mediaformat1.getMap();
            colorFormats = ai;
            mFlagsVerified = i;
            mDefaultFormat = mediaformat;
            mCapabilitiesInfo = mediaformat1;
            mMime = mDefaultFormat.getString("mime");
            ai = acodecprofilelevel;
            if(acodecprofilelevel.length == 0)
            {
                ai = acodecprofilelevel;
                if(mMime.equalsIgnoreCase("video/x-vnd.on2.vp9"))
                {
                    acodecprofilelevel = new CodecProfileLevel();
                    acodecprofilelevel.profile = 1;
                    acodecprofilelevel.level = VideoCapabilities.equivalentVP9Level(mediaformat1);
                    ai = new CodecProfileLevel[1];
                    ai[0] = acodecprofilelevel;
                }
            }
            profileLevels = ai;
            int j;
            if(mMime.toLowerCase().startsWith("audio/"))
            {
                mAudioCaps = AudioCapabilities.create(mediaformat1, this);
                mAudioCaps.setDefaultFormat(mDefaultFormat);
                break MISSING_BLOCK_LABEL_140;
            } else
            {
                if(mMime.toLowerCase().startsWith("video/"))
                    mVideoCaps = VideoCapabilities.create(mediaformat1, this);
                continue;
            }
            do
            {
                if(flag)
                {
                    mEncoderCaps = EncoderCapabilities.create(mediaformat1, this);
                    mEncoderCaps.setDefaultFormat(mDefaultFormat);
                }
                mMaxSupportedInstances = Utils.parseIntSafely(MediaCodecList.getGlobalSettings().get("max-concurrent-instances"), 32);
                i = Utils.parseIntSafely(map.get("max-concurrent-instances"), mMaxSupportedInstances);
                mMaxSupportedInstances = ((Integer)Range.create(Integer.valueOf(1), Integer.valueOf(256)).clamp(Integer.valueOf(i))).intValue();
                acodecprofilelevel = getValidFeatures();
                i = 0;
                j = acodecprofilelevel.length;
                while(i < j) 
                {
                    mediaformat = acodecprofilelevel[i];
                    mediaformat1 = (new StringBuilder()).append("feature-").append(((Feature) (mediaformat)).mName).toString();
                    ai = (Integer)map.get(mediaformat1);
                    if(ai != null)
                    {
                        if(ai.intValue() > 0)
                            mFlagsRequired = mFlagsRequired | ((Feature) (mediaformat)).mValue;
                        mFlagsSupported = mFlagsSupported | ((Feature) (mediaformat)).mValue;
                        mDefaultFormat.setInteger(mediaformat1, 1);
                    }
                    i++;
                }
                return;
            } while(true);
        }

        CodecCapabilities(CodecProfileLevel acodecprofilelevel[], int ai[], boolean flag, int i, Map map, Map map1)
        {
            this(acodecprofilelevel, ai, flag, i, new MediaFormat(map), new MediaFormat(map1));
        }
    }

    public static final class CodecProfileLevel
    {

        public static final int AACObjectELD = 39;
        public static final int AACObjectERLC = 17;
        public static final int AACObjectERScalable = 20;
        public static final int AACObjectHE = 5;
        public static final int AACObjectHE_PS = 29;
        public static final int AACObjectLC = 2;
        public static final int AACObjectLD = 23;
        public static final int AACObjectLTP = 4;
        public static final int AACObjectMain = 1;
        public static final int AACObjectSSR = 3;
        public static final int AACObjectScalable = 6;
        public static final int AVCLevel1 = 1;
        public static final int AVCLevel11 = 4;
        public static final int AVCLevel12 = 8;
        public static final int AVCLevel13 = 16;
        public static final int AVCLevel1b = 2;
        public static final int AVCLevel2 = 32;
        public static final int AVCLevel21 = 64;
        public static final int AVCLevel22 = 128;
        public static final int AVCLevel3 = 256;
        public static final int AVCLevel31 = 512;
        public static final int AVCLevel32 = 1024;
        public static final int AVCLevel4 = 2048;
        public static final int AVCLevel41 = 4096;
        public static final int AVCLevel42 = 8192;
        public static final int AVCLevel5 = 16384;
        public static final int AVCLevel51 = 32768;
        public static final int AVCLevel52 = 0x10000;
        public static final int AVCProfileBaseline = 1;
        public static final int AVCProfileConstrainedBaseline = 0x10000;
        public static final int AVCProfileConstrainedHigh = 0x80000;
        public static final int AVCProfileExtended = 4;
        public static final int AVCProfileHigh = 8;
        public static final int AVCProfileHigh10 = 16;
        public static final int AVCProfileHigh422 = 32;
        public static final int AVCProfileHigh444 = 64;
        public static final int AVCProfileMain = 2;
        public static final int DolbyVisionLevelFhd24 = 4;
        public static final int DolbyVisionLevelFhd30 = 8;
        public static final int DolbyVisionLevelFhd60 = 16;
        public static final int DolbyVisionLevelHd24 = 1;
        public static final int DolbyVisionLevelHd30 = 2;
        public static final int DolbyVisionLevelUhd24 = 32;
        public static final int DolbyVisionLevelUhd30 = 64;
        public static final int DolbyVisionLevelUhd48 = 128;
        public static final int DolbyVisionLevelUhd60 = 256;
        public static final int DolbyVisionProfileDvavPen = 2;
        public static final int DolbyVisionProfileDvavPer = 1;
        public static final int DolbyVisionProfileDvavSe = 512;
        public static final int DolbyVisionProfileDvheDen = 8;
        public static final int DolbyVisionProfileDvheDer = 4;
        public static final int DolbyVisionProfileDvheDtb = 128;
        public static final int DolbyVisionProfileDvheDth = 64;
        public static final int DolbyVisionProfileDvheDtr = 16;
        public static final int DolbyVisionProfileDvheSt = 256;
        public static final int DolbyVisionProfileDvheStn = 32;
        public static final int H263Level10 = 1;
        public static final int H263Level20 = 2;
        public static final int H263Level30 = 4;
        public static final int H263Level40 = 8;
        public static final int H263Level45 = 16;
        public static final int H263Level50 = 32;
        public static final int H263Level60 = 64;
        public static final int H263Level70 = 128;
        public static final int H263ProfileBackwardCompatible = 4;
        public static final int H263ProfileBaseline = 1;
        public static final int H263ProfileH320Coding = 2;
        public static final int H263ProfileHighCompression = 32;
        public static final int H263ProfileHighLatency = 256;
        public static final int H263ProfileISWV2 = 8;
        public static final int H263ProfileISWV3 = 16;
        public static final int H263ProfileInterlace = 128;
        public static final int H263ProfileInternet = 64;
        public static final int HEVCHighTierLevel1 = 2;
        public static final int HEVCHighTierLevel2 = 8;
        public static final int HEVCHighTierLevel21 = 32;
        public static final int HEVCHighTierLevel3 = 128;
        public static final int HEVCHighTierLevel31 = 512;
        public static final int HEVCHighTierLevel4 = 2048;
        public static final int HEVCHighTierLevel41 = 8192;
        public static final int HEVCHighTierLevel5 = 32768;
        public static final int HEVCHighTierLevel51 = 0x20000;
        public static final int HEVCHighTierLevel52 = 0x80000;
        public static final int HEVCHighTierLevel6 = 0x200000;
        public static final int HEVCHighTierLevel61 = 0x800000;
        public static final int HEVCHighTierLevel62 = 0x2000000;
        private static final int HEVCHighTierLevels = 0x2aaaaaa;
        public static final int HEVCMainTierLevel1 = 1;
        public static final int HEVCMainTierLevel2 = 4;
        public static final int HEVCMainTierLevel21 = 16;
        public static final int HEVCMainTierLevel3 = 64;
        public static final int HEVCMainTierLevel31 = 256;
        public static final int HEVCMainTierLevel4 = 1024;
        public static final int HEVCMainTierLevel41 = 4096;
        public static final int HEVCMainTierLevel5 = 16384;
        public static final int HEVCMainTierLevel51 = 0x10000;
        public static final int HEVCMainTierLevel52 = 0x40000;
        public static final int HEVCMainTierLevel6 = 0x100000;
        public static final int HEVCMainTierLevel61 = 0x400000;
        public static final int HEVCMainTierLevel62 = 0x1000000;
        public static final int HEVCProfileMain = 1;
        public static final int HEVCProfileMain10 = 2;
        public static final int HEVCProfileMain10HDR10 = 4096;
        public static final int MPEG2LevelH14 = 2;
        public static final int MPEG2LevelHL = 3;
        public static final int MPEG2LevelHP = 4;
        public static final int MPEG2LevelLL = 0;
        public static final int MPEG2LevelML = 1;
        public static final int MPEG2Profile422 = 2;
        public static final int MPEG2ProfileHigh = 5;
        public static final int MPEG2ProfileMain = 1;
        public static final int MPEG2ProfileSNR = 3;
        public static final int MPEG2ProfileSimple = 0;
        public static final int MPEG2ProfileSpatial = 4;
        public static final int MPEG4Level0 = 1;
        public static final int MPEG4Level0b = 2;
        public static final int MPEG4Level1 = 4;
        public static final int MPEG4Level2 = 8;
        public static final int MPEG4Level3 = 16;
        public static final int MPEG4Level3b = 24;
        public static final int MPEG4Level4 = 32;
        public static final int MPEG4Level4a = 64;
        public static final int MPEG4Level5 = 128;
        public static final int MPEG4Level6 = 256;
        public static final int MPEG4ProfileAdvancedCoding = 4096;
        public static final int MPEG4ProfileAdvancedCore = 8192;
        public static final int MPEG4ProfileAdvancedRealTime = 1024;
        public static final int MPEG4ProfileAdvancedScalable = 16384;
        public static final int MPEG4ProfileAdvancedSimple = 32768;
        public static final int MPEG4ProfileBasicAnimated = 256;
        public static final int MPEG4ProfileCore = 4;
        public static final int MPEG4ProfileCoreScalable = 2048;
        public static final int MPEG4ProfileHybrid = 512;
        public static final int MPEG4ProfileMain = 8;
        public static final int MPEG4ProfileNbit = 16;
        public static final int MPEG4ProfileScalableTexture = 32;
        public static final int MPEG4ProfileSimple = 1;
        public static final int MPEG4ProfileSimpleFBA = 128;
        public static final int MPEG4ProfileSimpleFace = 64;
        public static final int MPEG4ProfileSimpleScalable = 2;
        public static final int VP8Level_Version0 = 1;
        public static final int VP8Level_Version1 = 2;
        public static final int VP8Level_Version2 = 4;
        public static final int VP8Level_Version3 = 8;
        public static final int VP8ProfileMain = 1;
        public static final int VP9Level1 = 1;
        public static final int VP9Level11 = 2;
        public static final int VP9Level2 = 4;
        public static final int VP9Level21 = 8;
        public static final int VP9Level3 = 16;
        public static final int VP9Level31 = 32;
        public static final int VP9Level4 = 64;
        public static final int VP9Level41 = 128;
        public static final int VP9Level5 = 256;
        public static final int VP9Level51 = 512;
        public static final int VP9Level52 = 1024;
        public static final int VP9Level6 = 2048;
        public static final int VP9Level61 = 4096;
        public static final int VP9Level62 = 8192;
        public static final int VP9Profile0 = 1;
        public static final int VP9Profile1 = 2;
        public static final int VP9Profile2 = 4;
        public static final int VP9Profile2HDR = 4096;
        public static final int VP9Profile3 = 8;
        public static final int VP9Profile3HDR = 8192;
        public int level;
        public int profile;

        public CodecProfileLevel()
        {
        }
    }

    public static final class EncoderCapabilities
    {

        private void applyLevelLimits()
        {
            String s = mParent.getMimeType();
            if(!s.equalsIgnoreCase("audio/flac")) goto _L2; else goto _L1
_L1:
            mComplexityRange = Range.create(Integer.valueOf(0), Integer.valueOf(8));
            mBitControl = 1;
_L4:
            return;
_L2:
            if(s.equalsIgnoreCase("audio/3gpp") || s.equalsIgnoreCase("audio/amr-wb") || s.equalsIgnoreCase("audio/g711-alaw") || s.equalsIgnoreCase("audio/g711-mlaw") || s.equalsIgnoreCase("audio/gsm"))
                mBitControl = 4;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static EncoderCapabilities create(MediaFormat mediaformat, CodecCapabilities codeccapabilities)
        {
            EncoderCapabilities encodercapabilities = new EncoderCapabilities();
            encodercapabilities.init(mediaformat, codeccapabilities);
            return encodercapabilities;
        }

        private static int parseBitrateMode(String s)
        {
            Feature afeature[] = bitrates;
            int i = afeature.length;
            for(int j = 0; j < i; j++)
            {
                Feature feature = afeature[j];
                if(feature.mName.equalsIgnoreCase(s))
                    return feature.mValue;
            }

            return 0;
        }

        private void parseFromInfo(MediaFormat mediaformat)
        {
            Map map = mediaformat.getMap();
            if(mediaformat.containsKey("complexity-range"))
                mComplexityRange = Utils.parseIntRange(mediaformat.getString("complexity-range"), mComplexityRange);
            if(mediaformat.containsKey("quality-range"))
                mQualityRange = Utils.parseIntRange(mediaformat.getString("quality-range"), mQualityRange);
            if(mediaformat.containsKey("feature-bitrate-modes"))
            {
                String as[] = mediaformat.getString("feature-bitrate-modes").split(",");
                int i = 0;
                for(int j = as.length; i < j; i++)
                {
                    mediaformat = as[i];
                    mBitControl = mBitControl | parseBitrateMode(mediaformat);
                }

            }
            try
            {
                mDefaultComplexity = Integer.valueOf(Integer.parseInt((String)map.get("complexity-default")));
            }
            // Misplaced declaration of an exception variable
            catch(MediaFormat mediaformat) { }
            try
            {
                mDefaultQuality = Integer.valueOf(Integer.parseInt((String)map.get("quality-default")));
            }
            // Misplaced declaration of an exception variable
            catch(MediaFormat mediaformat) { }
            mQualityScale = (String)map.get("quality-scale");
        }

        private boolean supports(Integer integer, Integer integer1, Integer integer2)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(true)
            {
                flag1 = flag;
                if(integer != null)
                    flag1 = mComplexityRange.contains(integer);
            }
            flag = flag1;
            if(flag1)
            {
                flag = flag1;
                if(integer1 != null)
                    flag = mQualityRange.contains(integer1);
            }
            flag1 = flag;
            if(!flag) goto _L2; else goto _L1
_L1:
            flag1 = flag;
            if(integer2 == null) goto _L2; else goto _L3
_L3:
            int i;
            int j;
            integer1 = mParent.profileLevels;
            i = 0;
            j = integer1.length;
_L9:
            integer = integer2;
            if(i >= j) goto _L5; else goto _L4
_L4:
            if(((CodecProfileLevel) (integer1[i])).profile != integer2.intValue()) goto _L7; else goto _L6
_L6:
            integer = null;
_L5:
            if(integer == null)
                flag1 = true;
            else
                flag1 = false;
_L2:
            return flag1;
_L7:
            i++;
            if(true) goto _L9; else goto _L8
_L8:
        }

        public Range getComplexityRange()
        {
            return mComplexityRange;
        }

        public Range getQualityRange()
        {
            return mQualityRange;
        }

        public void init(MediaFormat mediaformat, CodecCapabilities codeccapabilities)
        {
            mParent = codeccapabilities;
            mComplexityRange = Range.create(Integer.valueOf(0), Integer.valueOf(0));
            mQualityRange = Range.create(Integer.valueOf(0), Integer.valueOf(0));
            mBitControl = 2;
            applyLevelLimits();
            parseFromInfo(mediaformat);
        }

        public boolean isBitrateModeSupported(int i)
        {
            boolean flag = true;
            Feature afeature[] = bitrates;
            int j = afeature.length;
            for(int k = 0; k < j; k++)
                if(i == afeature[k].mValue)
                {
                    if((mBitControl & 1 << i) == 0)
                        flag = false;
                    return flag;
                }

            return false;
        }

        public void setDefaultFormat(MediaFormat mediaformat)
        {
            if(!((Integer)mQualityRange.getUpper()).equals(mQualityRange.getLower()) && mDefaultQuality != null)
                mediaformat.setInteger("quality", mDefaultQuality.intValue());
            if(!((Integer)mComplexityRange.getUpper()).equals(mComplexityRange.getLower()) && mDefaultComplexity != null)
                mediaformat.setInteger("complexity", mDefaultComplexity.intValue());
            Feature afeature[] = bitrates;
            int i = afeature.length;
            int j = 0;
            do
            {
label0:
                {
                    if(j < i)
                    {
                        Feature feature = afeature[j];
                        if((mBitControl & 1 << feature.mValue) == 0)
                            break label0;
                        mediaformat.setInteger("bitrate-mode", feature.mValue);
                    }
                    return;
                }
                j++;
            } while(true);
        }

        public boolean supportsFormat(MediaFormat mediaformat)
        {
            Map map = mediaformat.getMap();
            String s = mParent.getMimeType();
            mediaformat = (Integer)map.get("bitrate-mode");
            if(mediaformat != null && isBitrateModeSupported(mediaformat.intValue()) ^ true)
                return false;
            Integer integer1 = (Integer)map.get("complexity");
            mediaformat = integer1;
            Integer integer2;
            if("audio/flac".equalsIgnoreCase(s))
            {
                integer2 = (Integer)map.get("flac-compression-level");
                if(integer1 == null)
                {
                    mediaformat = integer2;
                } else
                {
                    mediaformat = integer1;
                    if(integer2 != null)
                    {
                        mediaformat = integer1;
                        if(integer1.equals(integer2) ^ true)
                            throw new IllegalArgumentException("conflicting values for complexity and flac-compression-level");
                    }
                }
            }
            integer2 = (Integer)map.get("profile");
            integer1 = integer2;
            if("audio/mp4a-latm".equalsIgnoreCase(s))
            {
                Integer integer = (Integer)map.get("aac-profile");
                if(integer2 == null)
                {
                    integer1 = integer;
                } else
                {
                    integer1 = integer2;
                    if(integer != null)
                    {
                        integer1 = integer2;
                        if(integer.equals(integer2) ^ true)
                            throw new IllegalArgumentException("conflicting values for profile and aac-profile");
                    }
                }
            }
            return supports(mediaformat, (Integer)map.get("quality"), integer1);
        }

        public static final int BITRATE_MODE_CBR = 2;
        public static final int BITRATE_MODE_CQ = 0;
        public static final int BITRATE_MODE_VBR = 1;
        private static final Feature bitrates[] = {
            new Feature("VBR", 1, true), new Feature("CBR", 2, false), new Feature("CQ", 0, false)
        };
        private int mBitControl;
        private Range mComplexityRange;
        private Integer mDefaultComplexity;
        private Integer mDefaultQuality;
        private CodecCapabilities mParent;
        private Range mQualityRange;
        private String mQualityScale;


        private EncoderCapabilities()
        {
        }
    }

    private static class Feature
    {

        public boolean mDefault;
        public String mName;
        public int mValue;

        public Feature(String s, int i, boolean flag)
        {
            mName = s;
            mValue = i;
            mDefault = flag;
        }
    }

    public static final class VideoCapabilities
    {

        private void applyAlignment(int i, int j)
        {
            MediaCodecInfo._2D_wrap0(i, "widthAlignment must be a power of two");
            MediaCodecInfo._2D_wrap0(j, "heightAlignment must be a power of two");
            if(i > mBlockWidth || j > mBlockHeight)
                applyBlockLimits(Math.max(i, mBlockWidth), Math.max(j, mBlockHeight), MediaCodecInfo._2D_get2(), MediaCodecInfo._2D_get3(), MediaCodecInfo._2D_get4());
            mWidthAlignment = Math.max(i, mWidthAlignment);
            mHeightAlignment = Math.max(j, mHeightAlignment);
            mWidthRange = Utils.alignRange(mWidthRange, mWidthAlignment);
            mHeightRange = Utils.alignRange(mHeightRange, mHeightAlignment);
        }

        private void applyBlockLimits(int i, int j, Range range, Range range1, Range range2)
        {
            MediaCodecInfo._2D_wrap0(i, "blockWidth must be a power of two");
            MediaCodecInfo._2D_wrap0(j, "blockHeight must be a power of two");
            int k = Math.max(i, mBlockWidth);
            int l = Math.max(j, mBlockHeight);
            int i1 = (k * l) / mBlockWidth / mBlockHeight;
            if(i1 != 1)
            {
                mBlockCountRange = Utils.factorRange(mBlockCountRange, i1);
                mBlocksPerSecondRange = Utils.factorRange(mBlocksPerSecondRange, i1);
                mBlockAspectRatioRange = Utils.scaleRange(mBlockAspectRatioRange, l / mBlockHeight, k / mBlockWidth);
                mHorizontalBlockRange = Utils.factorRange(mHorizontalBlockRange, k / mBlockWidth);
                mVerticalBlockRange = Utils.factorRange(mVerticalBlockRange, l / mBlockHeight);
            }
            i1 = (k * l) / i / j;
            Range range3 = range;
            Range range4 = range1;
            Range range5 = range2;
            if(i1 != 1)
            {
                range3 = Utils.factorRange(range, i1);
                range4 = Utils.factorRange(range1, i1);
                range5 = Utils.scaleRange(range2, l / j, k / i);
            }
            mBlockCountRange = mBlockCountRange.intersect(range3);
            mBlocksPerSecondRange = mBlocksPerSecondRange.intersect(range4);
            mBlockAspectRatioRange = mBlockAspectRatioRange.intersect(range5);
            mBlockWidth = k;
            mBlockHeight = l;
        }

        private void applyLevelLimits()
        {
            int i;
            CodecProfileLevel acodecprofilelevel[];
            Object obj;
            i = 4;
            acodecprofilelevel = mParent.profileLevels;
            obj = mParent.getMimeType();
            if(!((String) (obj)).equalsIgnoreCase("video/avc")) goto _L2; else goto _L1
_L1:
            int j;
            long l;
            int k;
            int i1;
            int k1;
            int l1;
            int i2;
            j = 99;
            l = 1485L;
            k = 64000;
            i1 = 396;
            k1 = 0;
            l1 = acodecprofilelevel.length;
            i2 = i;
_L102:
            CodecProfileLevel codecprofilelevel;
            int j2;
            int k2;
            int l2;
            int i3;
            int k3;
            if(k1 >= l1)
                break MISSING_BLOCK_LABEL_927;
            codecprofilelevel = acodecprofilelevel[k1];
            j2 = 0;
            k2 = 0;
            i = 0;
            l2 = 0;
            i3 = 1;
            k3 = 1;
            codecprofilelevel.level;
            JVM INSTR lookupswitch 17: default 236
        //                       1: 488
        //                       2: 508
        //                       4: 529
        //                       8: 551
        //                       16: 573
        //                       32: 595
        //                       64: 617
        //                       128: 639
        //                       256: 661
        //                       512: 682
        //                       1024: 703
        //                       2048: 724
        //                       4096: 744
        //                       8192: 763
        //                       16384: 782
        //                       32768: 801
        //                       65536: 819;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L3:
            Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized level ").append(codecprofilelevel.level).append(" for ").append(((String) (obj))).toString());
            i2 |= 1;
_L26:
            int l3 = i2;
            codecprofilelevel.profile;
            JVM INSTR lookupswitch 9: default 372
        //                       1: 910
        //                       2: 910
        //                       4: 863
        //                       8: 837
        //                       16: 850
        //                       32: 863
        //                       64: 863
        //                       65536: 910
        //                       524288: 837;
               goto _L21 _L22 _L22 _L23 _L24 _L25 _L23 _L23 _L22 _L24
_L22:
            break MISSING_BLOCK_LABEL_910;
_L21:
            Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized profile ").append(codecprofilelevel.profile).append(" for ").append(((String) (obj))).toString());
            i2 |= 1;
            i *= 1000;
            l3 = k3;
_L27:
            i3 = i2;
            if(l3 != 0)
                i3 = i2 & -5;
            l = Math.max(j2, l);
            j = Math.max(k2, j);
            k = Math.max(i, k);
            i1 = Math.max(i1, l2);
            k1++;
            i2 = i3;
            continue; /* Loop/switch isn't completed */
_L4:
            j2 = 1485;
            k2 = 99;
            i = 64;
            l2 = 396;
              goto _L26
_L5:
            j2 = 1485;
            k2 = 99;
            i = 128;
            l2 = 396;
              goto _L26
_L6:
            j2 = 3000;
            k2 = 396;
            i = 192;
            l2 = 900;
              goto _L26
_L7:
            j2 = 6000;
            k2 = 396;
            i = 384;
            l2 = 2376;
              goto _L26
_L8:
            j2 = 11880;
            k2 = 396;
            i = 768;
            l2 = 2376;
              goto _L26
_L9:
            j2 = 11880;
            k2 = 396;
            i = 2000;
            l2 = 2376;
              goto _L26
_L10:
            j2 = 19800;
            k2 = 792;
            i = 4000;
            l2 = 4752;
              goto _L26
_L11:
            j2 = 20250;
            k2 = 1620;
            i = 4000;
            l2 = 8100;
              goto _L26
_L12:
            j2 = 40500;
            k2 = 1620;
            i = 10000;
            l2 = 8100;
              goto _L26
_L13:
            j2 = 0x1a5e0;
            k2 = 3600;
            i = 14000;
            l2 = 18000;
              goto _L26
_L14:
            j2 = 0x34bc0;
            k2 = 5120;
            i = 20000;
            l2 = 20480;
              goto _L26
_L15:
            j2 = 0x3c000;
            k2 = 8192;
            i = 20000;
            l2 = 32768;
              goto _L26
_L16:
            j2 = 0x3c000;
            k2 = 8192;
            i = 50000;
            l2 = 32768;
              goto _L26
_L17:
            j2 = 0x7f800;
            k2 = 8704;
            i = 50000;
            l2 = 34816;
              goto _L26
_L18:
            j2 = 0x90000;
            k2 = 22080;
            i = 0x20f58;
            l2 = 0x1af40;
              goto _L26
_L19:
            j2 = 0xf0000;
            k2 = 36864;
            i = 0x3a980;
            l2 = 0x2d000;
              goto _L26
_L20:
            j2 = 0x1fa400;
            k2 = 36864;
            i = 0x3a980;
            l2 = 0x2d000;
              goto _L26
_L24:
            i *= 1250;
            l3 = k3;
              goto _L27
_L25:
            i *= 3000;
            l3 = k3;
              goto _L27
_L23:
            Log.w("VideoCapabilities", (new StringBuilder()).append("Unsupported profile ").append(codecprofilelevel.profile).append(" for ").append(((String) (obj))).toString());
            l3 = i2 | 2;
            i3 = 0;
            i *= 1000;
            i2 = l3;
            l3 = i3;
              goto _L27
            i = (int)Math.sqrt(j * 8);
            applyMacroBlockLimits(i, i, j, l, 16, 16, 1, 1);
            l2 = k;
            i = i2;
_L100:
            mBitrateRange = Range.create(Integer.valueOf(1), Integer.valueOf(l2));
            obj = mParent;
            obj.mError = ((CodecCapabilities) (obj)).mError | i;
            return;
_L2:
            int i4;
            int j4;
            if(!((String) (obj)).equalsIgnoreCase("video/mpeg2"))
                break MISSING_BLOCK_LABEL_1689;
            k3 = 11;
            k1 = 9;
            j = 15;
            l1 = 99;
            l = 1485L;
            l3 = 64000;
            i4 = 0;
            j4 = acodecprofilelevel.length;
            i3 = i;
_L33:
            boolean flag2;
            if(i4 >= j4)
                break MISSING_BLOCK_LABEL_1640;
            codecprofilelevel = acodecprofilelevel[i4];
            i2 = 0;
            k2 = 0;
            i = 0;
            l2 = 0;
            k = 0;
            j2 = 0;
            flag2 = true;
            codecprofilelevel.profile;
            JVM INSTR tableswitch 0 5: default 1112
        //                       0 1240
        //                       1 1352
        //                       2 1590
        //                       3 1590
        //                       4 1590
        //                       5 1590;
               goto _L28 _L29 _L30 _L31 _L31 _L31 _L31
_L31:
            break MISSING_BLOCK_LABEL_1590;
_L29:
            break; /* Loop/switch isn't completed */
_L28:
            Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized profile ").append(codecprofilelevel.profile).append(" for ").append(((String) (obj))).toString());
            i3 |= 1;
_L35:
            i1 = i3;
            if(flag2)
                i1 = i3 & -5;
            l = Math.max(i2, l);
            l1 = Math.max(k2, l1);
            l3 = Math.max(i * 1000, l3);
            k3 = Math.max(k, k3);
            k1 = Math.max(j2, k1);
            j = Math.max(l2, j);
            i4++;
            i3 = i1;
            if(true) goto _L33; else goto _L32
_L32:
            switch(codecprofilelevel.level)
            {
            default:
                Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized profile/level ").append(codecprofilelevel.profile).append("/").append(codecprofilelevel.level).append(" for ").append(((String) (obj))).toString());
                i3 |= 1;
                break;

            case 1: // '\001'
                l2 = 30;
                k = 45;
                j2 = 36;
                i2 = 40500;
                k2 = 1620;
                i = 15000;
                break;
            }
            if(true) goto _L35; else goto _L34
_L34:
_L30:
            switch(codecprofilelevel.level)
            {
            default:
                Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized profile/level ").append(codecprofilelevel.profile).append("/").append(codecprofilelevel.level).append(" for ").append(((String) (obj))).toString());
                i3 |= 1;
                break;

            case 0: // '\0'
                l2 = 30;
                k = 22;
                j2 = 18;
                i2 = 11880;
                k2 = 396;
                i = 4000;
                break;

            case 1: // '\001'
                l2 = 30;
                k = 45;
                j2 = 36;
                i2 = 40500;
                k2 = 1620;
                i = 15000;
                break;

            case 2: // '\002'
                l2 = 60;
                k = 90;
                j2 = 68;
                i2 = 0x2cd30;
                k2 = 6120;
                i = 60000;
                break;

            case 3: // '\003'
                l2 = 60;
                k = 120;
                j2 = 68;
                i2 = 0x3bc40;
                k2 = 8160;
                i = 0x13880;
                break;

            case 4: // '\004'
                l2 = 60;
                k = 120;
                j2 = 68;
                i2 = 0x77880;
                k2 = 8160;
                i = 0x13880;
                break;
            }
            if(true) goto _L35; else goto _L36
_L36:
            Log.i("VideoCapabilities", (new StringBuilder()).append("Unsupported profile ").append(codecprofilelevel.profile).append(" for ").append(((String) (obj))).toString());
            i3 |= 2;
            flag2 = false;
              goto _L35
            applyMacroBlockLimits(k3, k1, l1, l, 16, 16, 1, 1);
            mFrameRateRange = mFrameRateRange.intersect(Integer.valueOf(12), Integer.valueOf(j));
            i = i3;
            l2 = l3;
            continue; /* Loop/switch isn't completed */
            int k4;
            int l4;
            if(!((String) (obj)).equalsIgnoreCase("video/mp4v-es"))
                break MISSING_BLOCK_LABEL_2879;
            i4 = 11;
            l1 = 9;
            k1 = 15;
            k4 = 99;
            l = 1485L;
            l3 = 64000;
            k3 = 0;
            l4 = acodecprofilelevel.length;
            i3 = i;
_L41:
            boolean flag;
            boolean flag1;
            if(k3 >= l4)
                break MISSING_BLOCK_LABEL_2830;
            codecprofilelevel = acodecprofilelevel[k3];
            i2 = 0;
            k2 = 0;
            i = 0;
            l2 = 0;
            k = 0;
            j2 = 0;
            flag = false;
            flag1 = true;
            codecprofilelevel.profile;
            JVM INSTR lookupswitch 16: default 1912
        //                       1: 2059
        //                       2: 2732
        //                       4: 2732
        //                       8: 2732
        //                       16: 2732
        //                       32: 2732
        //                       64: 2732
        //                       128: 2732
        //                       256: 2732
        //                       512: 2732
        //                       1024: 2732
        //                       2048: 2732
        //                       4096: 2732
        //                       8192: 2732
        //                       16384: 2732
        //                       32768: 2430;
               goto _L37 _L38 _L39 _L39 _L39 _L39 _L39 _L39 _L39 _L39 _L39 _L39 _L39 _L39 _L39 _L39 _L40
_L37:
            Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized profile ").append(codecprofilelevel.profile).append(" for ").append(((String) (obj))).toString());
            i3 |= 1;
_L43:
            i1 = i3;
            if(flag1)
                i1 = i3 & -5;
            l = Math.max(i2, l);
            k4 = Math.max(k2, k4);
            l3 = Math.max(i * 1000, l3);
            if(flag)
            {
                k2 = Math.max(k, i4);
                j2 = Math.max(j2, l1);
                i = Math.max(l2, k1);
                l2 = j2;
            } else
            {
                i = (int)Math.sqrt(k2 * 2);
                k2 = Math.max(i, i4);
                i = Math.max(i, l1);
                j2 = Math.max(Math.max(l2, 60), k1);
                l2 = i;
                i = j2;
            }
            k3++;
            i4 = k2;
            l1 = l2;
            i3 = i1;
            k1 = i;
            if(true) goto _L41; else goto _L38
_L38:
            switch(codecprofilelevel.level)
            {
            default:
                Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized profile/level ").append(codecprofilelevel.profile).append("/").append(codecprofilelevel.level).append(" for ").append(((String) (obj))).toString());
                i3 |= 1;
                break;

            case 1: // '\001'
                flag = true;
                l2 = 15;
                k = 11;
                j2 = 9;
                i2 = 1485;
                k2 = 99;
                i = 64;
                break;

            case 4: // '\004'
                l2 = 30;
                k = 11;
                j2 = 9;
                i2 = 1485;
                k2 = 99;
                i = 64;
                break;

            case 2: // '\002'
                flag = true;
                l2 = 15;
                k = 11;
                j2 = 9;
                i2 = 1485;
                k2 = 99;
                i = 128;
                break;

            case 8: // '\b'
                l2 = 30;
                k = 22;
                j2 = 18;
                i2 = 5940;
                k2 = 396;
                i = 128;
                break;

            case 16: // '\020'
                l2 = 30;
                k = 22;
                j2 = 18;
                i2 = 11880;
                k2 = 396;
                i = 384;
                break;

            case 64: // '@'
                l2 = 30;
                k = 40;
                j2 = 30;
                i2 = 36000;
                k2 = 1200;
                i = 4000;
                break;

            case 128: 
                l2 = 30;
                k = 45;
                j2 = 36;
                i2 = 40500;
                k2 = 1620;
                i = 8000;
                break;

            case 256: 
                l2 = 30;
                k = 80;
                j2 = 45;
                i2 = 0x1a5e0;
                k2 = 3600;
                i = 12000;
                break;
            }
            if(true) goto _L43; else goto _L42
_L42:
_L40:
            switch(codecprofilelevel.level)
            {
            default:
                Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized profile/level ").append(codecprofilelevel.profile).append("/").append(codecprofilelevel.level).append(" for ").append(((String) (obj))).toString());
                i3 |= 1;
                break;

            case 1: // '\001'
            case 4: // '\004'
                l2 = 30;
                k = 11;
                j2 = 9;
                i2 = 2970;
                k2 = 99;
                i = 128;
                break;

            case 8: // '\b'
                l2 = 30;
                k = 22;
                j2 = 18;
                i2 = 5940;
                k2 = 396;
                i = 384;
                break;

            case 16: // '\020'
                l2 = 30;
                k = 22;
                j2 = 18;
                i2 = 11880;
                k2 = 396;
                i = 768;
                break;

            case 24: // '\030'
                l2 = 30;
                k = 22;
                j2 = 18;
                i2 = 11880;
                k2 = 396;
                i = 1500;
                break;

            case 32: // ' '
                l2 = 30;
                k = 44;
                j2 = 36;
                i2 = 23760;
                k2 = 792;
                i = 3000;
                break;

            case 128: 
                l2 = 30;
                k = 45;
                j2 = 36;
                i2 = 48600;
                k2 = 1620;
                i = 8000;
                break;
            }
            if(true) goto _L43; else goto _L44
_L44:
_L39:
            Log.i("VideoCapabilities", (new StringBuilder()).append("Unsupported profile ").append(codecprofilelevel.profile).append(" for ").append(((String) (obj))).toString());
            i3 |= 2;
            flag1 = false;
              goto _L43
            applyMacroBlockLimits(i4, l1, k4, l, 16, 16, 1, 1);
            mFrameRateRange = mFrameRateRange.intersect(Integer.valueOf(12), Integer.valueOf(k1));
            i = i3;
            l2 = l3;
            continue; /* Loop/switch isn't completed */
            int i5;
            int j5;
            int k5;
            if(!((String) (obj)).equalsIgnoreCase("video/3gpp"))
                break MISSING_BLOCK_LABEL_3805;
            l4 = 11;
            flag1 = 9;
            k4 = 15;
            k3 = 11;
            k1 = 9;
            i1 = 16;
            i5 = 99;
            l = 1485L;
            flag = 64000;
            j5 = 0;
            k5 = acodecprofilelevel.length;
            k = i;
_L54:
            if(j5 >= k5)
                break MISSING_BLOCK_LABEL_3719;
            codecprofilelevel = acodecprofilelevel[j5];
            j2 = 0;
            i = 0;
            l2 = 0;
            i2 = 0;
            k2 = 0;
            i3 = k3;
            l1 = k1;
            l3 = 0;
            codecprofilelevel.level;
            JVM INSTR lookupswitch 8: default 3052
        //                       1: 3356
        //                       2: 3391
        //                       4: 3427
        //                       8: 3464
        //                       16: 3501
        //                       32: 3580
        //                       64: 3623
        //                       128: 3667;
               goto _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53
_L45:
            Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized profile/level ").append(codecprofilelevel.profile).append("/").append(codecprofilelevel.level).append(" for ").append(((String) (obj))).toString());
            i4 = k | 1;
            k = l1;
_L55:
            l1 = i4;
            switch(codecprofilelevel.profile)
            {
            default:
                Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized profile ").append(codecprofilelevel.profile).append(" for ").append(((String) (obj))).toString());
                l1 = i4 | 1;
                break;

            case 1: // '\001'
            case 2: // '\002'
            case 4: // '\004'
            case 8: // '\b'
            case 16: // '\020'
            case 32: // ' '
            case 64: // '@'
            case 128: 
            case 256: 
                break;
            }
            if(l3 != 0)
            {
                i3 = 11;
                k = 9;
            } else
            {
                mAllowMbOverride = true;
            }
            l3 = l1 & -5;
            l = Math.max(j2, l);
            i5 = Math.max(i2 * k2, i5);
            flag = Math.max(64000 * i, flag);
            l4 = Math.max(i2, l4);
            flag1 = Math.max(k2, flag1);
            k4 = Math.max(l2, k4);
            k3 = Math.min(i3, k3);
            k1 = Math.min(k, k1);
            j5++;
            k = l3;
            if(true) goto _L54; else goto _L46
_L46:
            l3 = 1;
            l2 = 15;
            i2 = 11;
            k2 = 9;
            i = 1;
            j2 = 99 * 15;
            i4 = k;
            k = l1;
              goto _L55
_L47:
            l3 = 1;
            l2 = 30;
            i2 = 22;
            k2 = 18;
            i = 2;
            j2 = 396 * 15;
            i4 = k;
            k = l1;
              goto _L55
_L48:
            l3 = 1;
            l2 = 30;
            i2 = 22;
            k2 = 18;
            i = 6;
            j2 = 396 * 30;
            i4 = k;
            k = l1;
              goto _L55
_L49:
            l3 = 1;
            l2 = 30;
            i2 = 22;
            k2 = 18;
            i = 32;
            j2 = 396 * 30;
            i4 = k;
            k = l1;
              goto _L55
_L50:
            if(codecprofilelevel.profile != 1)
            {
                if(codecprofilelevel.profile == 4)
                    l3 = 1;
                else
                    l3 = 0;
            } else
            {
                l3 = 1;
            }
            if(l3 == 0)
            {
                i3 = 1;
                l1 = 1;
                i1 = 4;
            }
            l2 = 15;
            i2 = 11;
            k2 = 9;
            i = 2;
            j2 = 99 * 15;
            i4 = k;
            k = l1;
              goto _L55
_L51:
            i3 = 1;
            l1 = 1;
            i1 = 4;
            l2 = 60;
            i2 = 22;
            k2 = 18;
            i = 64;
            j2 = 396 * 50;
            i4 = k;
            k = l1;
              goto _L55
_L52:
            i3 = 1;
            l1 = 1;
            i1 = 4;
            l2 = 60;
            i2 = 45;
            k2 = 18;
            i = 128;
            j2 = 810 * 50;
            i4 = k;
            k = l1;
              goto _L55
_L53:
            i3 = 1;
            l1 = 1;
            i1 = 4;
            l2 = 60;
            i2 = 45;
            k2 = 36;
            i = 256;
            j2 = 1620 * 50;
            i4 = k;
            k = l1;
              goto _L55
            if(!mAllowMbOverride)
                mBlockAspectRatioRange = Range.create(new Rational(11, 9), new Rational(11, 9));
            applyMacroBlockLimits(k3, k1, l4, flag1, i5, l, 16, 16, i1, i1);
            mFrameRateRange = Range.create(Integer.valueOf(1), Integer.valueOf(k4));
            i = k;
            l2 = ((flag) ? 1 : 0);
            continue; /* Loop/switch isn't completed */
            long l5;
            if(((String) (obj)).equalsIgnoreCase("video/x-vnd.on2.vp8"))
            {
                j2 = 0x5f5e100;
                k2 = 0;
                for(i2 = acodecprofilelevel.length; k2 < i2; k2++)
                {
                    codecprofilelevel = acodecprofilelevel[k2];
                    l2 = i;
                    switch(codecprofilelevel.level)
                    {
                    case 3: // '\003'
                    case 5: // '\005'
                    case 6: // '\006'
                    case 7: // '\007'
                    default:
                        Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized level ").append(codecprofilelevel.level).append(" for ").append(((String) (obj))).toString());
                        l2 = i | 1;
                        // fall through

                    case 1: // '\001'
                    case 2: // '\002'
                    case 4: // '\004'
                    case 8: // '\b'
                        i = l2;
                        break;
                    }
                    switch(codecprofilelevel.profile)
                    {
                    default:
                        Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized profile ").append(codecprofilelevel.profile).append(" for ").append(((String) (obj))).toString());
                        i = l2 | 1;
                        // fall through

                    case 1: // '\001'
                        i &= -5;
                        break;
                    }
                }

                applyMacroBlockLimits(32767, 32767, 0x7fffffff, 0x7fffffffL, 16, 16, 1, 1);
                l2 = j2;
                continue; /* Loop/switch isn't completed */
            }
            if(!((String) (obj)).equalsIgnoreCase("video/x-vnd.on2.vp9"))
                break MISSING_BLOCK_LABEL_4792;
            l5 = 0xca800L;
            i3 = 36864;
            i2 = 0x30d40;
            k = 512;
            l3 = 0;
            flag = acodecprofilelevel.length;
            j2 = i;
_L72:
            if(l3 >= flag)
                break MISSING_BLOCK_LABEL_4747;
            codecprofilelevel = acodecprofilelevel[l3];
            l = 0L;
            k2 = 0;
            i = 0;
            l2 = 0;
            codecprofilelevel.level;
            JVM INSTR lookupswitch 14: default 4236
        //                       1: 4444
        //                       2: 4465
        //                       4: 4487
        //                       8: 4509
        //                       16: 4530
        //                       32: 4552
        //                       64: 4573
        //                       128: 4595
        //                       256: 4617
        //                       512: 4638
        //                       1024: 4660
        //                       2048: 4682
        //                       4096: 4704
        //                       8192: 4725;
               goto _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67 _L68 _L69 _L70
_L70:
            break MISSING_BLOCK_LABEL_4725;
_L57:
            break; /* Loop/switch isn't completed */
_L56:
            Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized level ").append(codecprofilelevel.level).append(" for ").append(((String) (obj))).toString());
            j2 |= 1;
_L73:
            int j1 = j2;
            switch(codecprofilelevel.profile)
            {
            default:
                Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized profile ").append(codecprofilelevel.profile).append(" for ").append(((String) (obj))).toString());
                j1 = j2 | 1;
                // fall through

            case 1: // '\001'
            case 2: // '\002'
            case 4: // '\004'
            case 8: // '\b'
            case 4096: 
            case 8192: 
                j2 = j1 & -5;
                break;
            }
            l5 = Math.max(l, l5);
            i3 = Math.max(k2, i3);
            i2 = Math.max(i * 1000, i2);
            k = Math.max(l2, k);
            l3++;
            if(true) goto _L72; else goto _L71
_L71:
            l = 0xca800L;
            k2 = 36864;
            i = 200;
            l2 = 512;
              goto _L73
_L58:
            l = 0x2a3000L;
            k2 = 0x12000;
            i = 800;
            l2 = 768;
              goto _L73
_L59:
            l = 0x465000L;
            k2 = 0x1e000;
            i = 1800;
            l2 = 960;
              goto _L73
_L60:
            l = 0x8ca000L;
            k2 = 0x3c000;
            i = 3600;
            l2 = 1344;
              goto _L73
_L61:
            l = 0x13c6800L;
            k2 = 0x87000;
            i = 7200;
            l2 = 2048;
              goto _L73
_L62:
            l = 0x2328000L;
            k2 = 0xf0000;
            i = 12000;
            l2 = 2752;
              goto _L73
_L63:
            l = 0x4fb0000L;
            k2 = 0x220000;
            i = 18000;
            l2 = 4160;
              goto _L73
_L64:
            l = 0x9900000L;
            k2 = 0x220000;
            i = 30000;
            l2 = 4160;
              goto _L73
_L65:
            l = 0x12980000L;
            k2 = 0x880000;
            i = 60000;
            l2 = 8384;
              goto _L73
_L66:
            l = 0x23100000L;
            k2 = 0x880000;
            i = 0x1d4c0;
            l2 = 8384;
              goto _L73
_L67:
            l = 0x46200000L;
            k2 = 0x880000;
            i = 0x2bf20;
            l2 = 8384;
              goto _L73
_L68:
            l = 0x46200000L;
            k2 = 0x2200000;
            i = 0x2bf20;
            l2 = 16832;
              goto _L73
_L69:
            l = 0x8c400000L;
            k2 = 0x2200000;
            i = 0x3a980;
            l2 = 16832;
              goto _L73
            l = 0x118800000L;
            k2 = 0x2200000;
            i = 0x75300;
            l2 = 16832;
              goto _L73
            i = Utils.divUp(k, 8);
            applyMacroBlockLimits(i, i, Utils.divUp(i3, 64), Utils.divUp(l5, 64L), 8, 8, 1, 1);
            i = j2;
            l2 = i2;
            continue; /* Loop/switch isn't completed */
            if(!((String) (obj)).equalsIgnoreCase("video/hevc"))
                break MISSING_BLOCK_LABEL_5645;
            i2 = 576;
            l = 8640;
            j2 = 0x1f400;
            k = 0;
            l3 = acodecprofilelevel.length;
            k2 = i;
_L97:
            double d;
            if(k >= l3)
                break MISSING_BLOCK_LABEL_5608;
            codecprofilelevel = acodecprofilelevel[k];
            d = 0.0D;
            l2 = 0;
            i = 0;
            codecprofilelevel.level;
            JVM INSTR lookupswitch 26: default 5072
        //                       1: 5260
        //                       2: 5260
        //                       4: 5276
        //                       8: 5276
        //                       16: 5293
        //                       32: 5293
        //                       64: 5309
        //                       128: 5309
        //                       256: 5326
        //                       512: 5326
        //                       1024: 5342
        //                       2048: 5359
        //                       4096: 5376
        //                       8192: 5393
        //                       16384: 5409
        //                       32768: 5426
        //                       65536: 5443
        //                       131072: 5460
        //                       262144: 5477
        //                       524288: 5493
        //                       1048576: 5509
        //                       2097152: 5525
        //                       4194304: 5541
        //                       8388608: 5558
        //                       16777216: 5575
        //                       33554432: 5591;
               goto _L74 _L75 _L75 _L76 _L76 _L77 _L77 _L78 _L78 _L79 _L79 _L80 _L81 _L82 _L83 _L84 _L85 _L86 _L87 _L88 _L89 _L90 _L91 _L92 _L93 _L94 _L95
_L95:
            break MISSING_BLOCK_LABEL_5591;
_L75:
            break; /* Loop/switch isn't completed */
_L74:
            Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized level ").append(codecprofilelevel.level).append(" for ").append(((String) (obj))).toString());
            k2 |= 1;
_L98:
            int j3 = k2;
            switch(codecprofilelevel.profile)
            {
            default:
                Log.w("VideoCapabilities", (new StringBuilder()).append("Unrecognized profile ").append(codecprofilelevel.profile).append(" for ").append(((String) (obj))).toString());
                j3 = k2 | 1;
                // fall through

            case 1: // '\001'
            case 2: // '\002'
            case 4096: 
                l2 >>= 6;
                break;
            }
            k2 = j3 & -5;
            l = Math.max((int)((double)l2 * d), l);
            i2 = Math.max(l2, i2);
            j2 = Math.max(i * 1000, j2);
            k++;
            if(true) goto _L97; else goto _L96
_L96:
            d = 15D;
            l2 = 36864;
            i = 128;
              goto _L98
_L76:
            d = 30D;
            l2 = 0x1e000;
            i = 1500;
              goto _L98
_L77:
            d = 30D;
            l2 = 0x3c000;
            i = 3000;
              goto _L98
_L78:
            d = 30D;
            l2 = 0x87000;
            i = 6000;
              goto _L98
_L79:
            d = 33.75D;
            l2 = 0xf0000;
            i = 10000;
              goto _L98
_L80:
            d = 30D;
            l2 = 0x220000;
            i = 12000;
              goto _L98
_L81:
            d = 30D;
            l2 = 0x220000;
            i = 30000;
              goto _L98
_L82:
            d = 60D;
            l2 = 0x220000;
            i = 20000;
              goto _L98
_L83:
            d = 60D;
            l2 = 0x220000;
            i = 50000;
              goto _L98
_L84:
            d = 30D;
            l2 = 0x880000;
            i = 25000;
              goto _L98
_L85:
            d = 30D;
            l2 = 0x880000;
            i = 0x186a0;
              goto _L98
_L86:
            d = 60D;
            l2 = 0x880000;
            i = 40000;
              goto _L98
_L87:
            d = 60D;
            l2 = 0x880000;
            i = 0x27100;
              goto _L98
_L88:
            d = 120D;
            l2 = 0x880000;
            i = 60000;
              goto _L98
_L89:
            d = 120D;
            l2 = 0x880000;
            i = 0x3a980;
              goto _L98
_L90:
            d = 30D;
            l2 = 0x2200000;
            i = 60000;
              goto _L98
_L91:
            d = 30D;
            l2 = 0x2200000;
            i = 0x3a980;
              goto _L98
_L92:
            d = 60D;
            l2 = 0x2200000;
            i = 0x1d4c0;
              goto _L98
_L93:
            d = 60D;
            l2 = 0x2200000;
            i = 0x75300;
              goto _L98
_L94:
            d = 120D;
            l2 = 0x2200000;
            i = 0x3a980;
              goto _L98
            d = 120D;
            l2 = 0x2200000;
            i = 0xc3500;
              goto _L98
            i = (int)Math.sqrt(i2 * 8);
            applyMacroBlockLimits(i, i, i2, l, 8, 8, 1, 1);
            i = k2;
            l2 = j2;
            continue; /* Loop/switch isn't completed */
            Log.w("VideoCapabilities", (new StringBuilder()).append("Unsupported mime ").append(((String) (obj))).toString());
            l2 = 64000;
            i = 6;
            if(true) goto _L100; else goto _L99
_L99:
            if(true) goto _L102; else goto _L101
_L101:
        }

        private void applyMacroBlockLimits(int i, int j, int k, int l, int i1, long l1, 
                int j1, int k1, int i2, int j2)
        {
            applyAlignment(i2, j2);
            applyBlockLimits(j1, k1, Range.create(Integer.valueOf(1), Integer.valueOf(i1)), Range.create(Long.valueOf(1L), Long.valueOf(l1)), Range.create(new Rational(1, l), new Rational(k, 1)));
            mHorizontalBlockRange = mHorizontalBlockRange.intersect(Integer.valueOf(Utils.divUp(i, mBlockWidth / j1)), Integer.valueOf(k / (mBlockWidth / j1)));
            mVerticalBlockRange = mVerticalBlockRange.intersect(Integer.valueOf(Utils.divUp(j, mBlockHeight / k1)), Integer.valueOf(l / (mBlockHeight / k1)));
        }

        private void applyMacroBlockLimits(int i, int j, int k, long l, int i1, int j1, 
                int k1, int l1)
        {
            applyMacroBlockLimits(1, 1, i, j, k, l, i1, j1, k1, l1);
        }

        public static VideoCapabilities create(MediaFormat mediaformat, CodecCapabilities codeccapabilities)
        {
            VideoCapabilities videocapabilities = new VideoCapabilities();
            videocapabilities.init(mediaformat, codeccapabilities);
            return videocapabilities;
        }

        public static int equivalentVP9Level(MediaFormat mediaformat)
        {
            mediaformat = mediaformat.getMap();
            Object obj = Utils.parseSize(mediaformat.get("block-size"), new Size(8, 8));
            int i = ((Size) (obj)).getWidth() * ((Size) (obj)).getHeight();
            obj = Utils.parseIntRange(mediaformat.get("block-count-range"), null);
            int j;
            long l;
            int k;
            if(obj == null)
                j = 0;
            else
                j = i * ((Integer)((Range) (obj)).getUpper()).intValue();
            obj = Utils.parseLongRange(mediaformat.get("blocks-per-second-range"), null);
            if(obj == null)
                l = 0L;
            else
                l = (long)i * ((Long)((Range) (obj)).getUpper()).longValue();
            obj = parseWidthHeightRanges(mediaformat.get("size-range"));
            if(obj == null)
                i = 0;
            else
                i = Math.max(((Integer)((Range)((Pair) (obj)).first).getUpper()).intValue(), ((Integer)((Range)((Pair) (obj)).second).getUpper()).intValue());
            mediaformat = Utils.parseIntRange(mediaformat.get("bitrate-range"), null);
            if(mediaformat == null)
                k = 0;
            else
                k = Utils.divUp(((Integer)mediaformat.getUpper()).intValue(), 1000);
            if(l <= 0xca800L && j <= 36864 && k <= 200 && i <= 512)
                return 1;
            if(l <= 0x2a3000L && j <= 0x12000 && k <= 800 && i <= 768)
                return 2;
            if(l <= 0x465000L && j <= 0x1e000 && k <= 1800 && i <= 960)
                return 4;
            if(l <= 0x8ca000L && j <= 0x3c000 && k <= 3600 && i <= 1344)
                return 8;
            if(l <= 0x13c6800L && j <= 0x87000 && k <= 7200 && i <= 2048)
                return 16;
            if(l <= 0x2328000L && j <= 0xf0000 && k <= 12000 && i <= 2752)
                return 32;
            if(l <= 0x4fb0000L && j <= 0x220000 && k <= 18000 && i <= 4160)
                return 64;
            if(l <= 0x9900000L && j <= 0x220000 && k <= 30000 && i <= 4160)
                return 128;
            if(l <= 0x12980000L && j <= 0x880000 && k <= 60000 && i <= 8384)
                return 256;
            if(l <= 0x23100000L && j <= 0x880000 && k <= 0x1d4c0 && i <= 8384)
                return 512;
            if(l <= 0x46200000L && j <= 0x880000 && k <= 0x2bf20 && i <= 8384)
                return 1024;
            if(l <= 0x46200000L && j <= 0x2200000 && k <= 0x2bf20 && i <= 16832)
                return 2048;
            if(l <= 0x8c400000L && j <= 0x2200000 && k <= 0x3a980 && i <= 16832)
                return 4096;
            return l > 0x118800000L || j > 0x2200000 || k > 0x75300 || i > 16832 ? 8192 : 8192;
        }

        private Range estimateFrameRatesFor(int i, int j)
        {
            Object obj = findClosestSize(i, j);
            Range range = (Range)mMeasuredFrameRates.get(obj);
            obj = Double.valueOf((double)getBlockCount(((Size) (obj)).getWidth(), ((Size) (obj)).getHeight()) / (double)Math.max(getBlockCount(i, j), 1));
            return Range.create(Double.valueOf((double)((Long)range.getLower()).longValue() * ((Double) (obj)).doubleValue()), Double.valueOf((double)((Long)range.getUpper()).longValue() * ((Double) (obj)).doubleValue()));
        }

        private Size findClosestSize(int i, int j)
        {
            int k = getBlockCount(i, j);
            Size size = null;
            i = 0x7fffffff;
            Iterator iterator = mMeasuredFrameRates.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Size size1 = (Size)iterator.next();
                j = Math.abs(k - getBlockCount(size1.getWidth(), size1.getHeight()));
                if(j < i)
                {
                    i = j;
                    size = size1;
                }
            } while(true);
            return size;
        }

        private int getBlockCount(int i, int j)
        {
            return Utils.divUp(i, mBlockWidth) * Utils.divUp(j, mBlockHeight);
        }

        private Map getMeasuredFrameRates(Map map)
        {
            HashMap hashmap = new HashMap();
            Iterator iterator = map.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Object obj = (String)iterator.next();
                if(((String) (obj)).startsWith("measured-frame-rate-"))
                {
                    ((String) (obj)).substring("measured-frame-rate-".length());
                    String as[] = ((String) (obj)).split("-");
                    if(as.length == 5)
                    {
                        Size size = Utils.parseSize(as[3], null);
                        if(size != null && size.getWidth() * size.getHeight() > 0)
                        {
                            obj = Utils.parseLongRange(map.get(obj), null);
                            if(obj != null && ((Long)((Range) (obj)).getLower()).longValue() >= 0L && ((Long)((Range) (obj)).getUpper()).longValue() >= 0L)
                                hashmap.put(size, obj);
                        }
                    }
                }
            } while(true);
            return hashmap;
        }

        private void initWithPlatformLimits()
        {
            mBitrateRange = MediaCodecInfo._2D_get0();
            mWidthRange = MediaCodecInfo._2D_get5();
            mHeightRange = MediaCodecInfo._2D_get5();
            mFrameRateRange = MediaCodecInfo._2D_get1();
            mHorizontalBlockRange = MediaCodecInfo._2D_get5();
            mVerticalBlockRange = MediaCodecInfo._2D_get5();
            mBlockCountRange = MediaCodecInfo._2D_get2();
            mBlocksPerSecondRange = MediaCodecInfo._2D_get3();
            mBlockAspectRatioRange = MediaCodecInfo._2D_get4();
            mAspectRatioRange = MediaCodecInfo._2D_get4();
            mWidthAlignment = 2;
            mHeightAlignment = 2;
            mBlockWidth = 2;
            mBlockHeight = 2;
            mSmallerDimensionUpperLimit = ((Integer)MediaCodecInfo._2D_get5().getUpper()).intValue();
        }

        private void parseFromInfo(MediaFormat mediaformat)
        {
            Object obj1;
            Object obj2;
            Object obj3;
            Size size;
            Range range;
            Range range1;
            Range range2;
            Range range3;
            Object obj = mediaformat.getMap();
            obj1 = new Size(mBlockWidth, mBlockHeight);
            obj2 = new Size(mWidthAlignment, mHeightAlignment);
            obj3 = null;
            mediaformat = null;
            size = Utils.parseSize(((Map) (obj)).get("block-size"), ((Size) (obj1)));
            Size size1 = Utils.parseSize(((Map) (obj)).get("alignment"), ((Size) (obj2)));
            range = Utils.parseIntRange(((Map) (obj)).get("block-count-range"), null);
            range1 = Utils.parseLongRange(((Map) (obj)).get("blocks-per-second-range"), null);
            mMeasuredFrameRates = getMeasuredFrameRates(((Map) (obj)));
            obj2 = parseWidthHeightRanges(((Map) (obj)).get("size-range"));
            if(obj2 != null)
            {
                obj3 = (Range)((Pair) (obj2)).first;
                mediaformat = (Range)((Pair) (obj2)).second;
            }
            obj1 = mediaformat;
            obj2 = obj3;
            if(((Map) (obj)).containsKey("feature-can-swap-width-height"))
                if(obj3 != null)
                {
                    mSmallerDimensionUpperLimit = Math.min(((Integer)((Range) (obj3)).getUpper()).intValue(), ((Integer)mediaformat.getUpper()).intValue());
                    obj1 = ((Range) (obj3)).extend(mediaformat);
                    obj2 = obj1;
                } else
                {
                    Log.w("VideoCapabilities", "feature can-swap-width-height is best used with size-range");
                    mSmallerDimensionUpperLimit = Math.min(((Integer)mWidthRange.getUpper()).intValue(), ((Integer)mHeightRange.getUpper()).intValue());
                    obj2 = mWidthRange.extend(mHeightRange);
                    mHeightRange = ((Range) (obj2));
                    mWidthRange = ((Range) (obj2));
                    obj1 = mediaformat;
                    obj2 = obj3;
                }
            range2 = Utils.parseRationalRange(((Map) (obj)).get("block-aspect-ratio-range"), null);
            range3 = Utils.parseRationalRange(((Map) (obj)).get("pixel-aspect-ratio-range"), null);
            obj3 = Utils.parseIntRange(((Map) (obj)).get("frame-rate-range"), null);
            mediaformat = ((MediaFormat) (obj3));
            if(obj3 != null)
                try
                {
                    mediaformat = ((Range) (obj3)).intersect(MediaCodecInfo._2D_get1());
                }
                // Misplaced declaration of an exception variable
                catch(MediaFormat mediaformat)
                {
                    Log.w("VideoCapabilities", (new StringBuilder()).append("frame rate range (").append(obj3).append(") is out of limits: ").append(MediaCodecInfo._2D_get1()).toString());
                    mediaformat = null;
                }
            obj = Utils.parseIntRange(((Map) (obj)).get("bitrate-range"), null);
            obj3 = obj;
            if(obj != null)
                try
                {
                    obj3 = ((Range) (obj)).intersect(MediaCodecInfo._2D_get0());
                }
                // Misplaced declaration of an exception variable
                catch(Object obj3)
                {
                    Log.w("VideoCapabilities", (new StringBuilder()).append("bitrate range (").append(obj).append(") is out of limits: ").append(MediaCodecInfo._2D_get0()).toString());
                    obj3 = null;
                }
            MediaCodecInfo._2D_wrap0(size.getWidth(), "block-size width must be power of two");
            MediaCodecInfo._2D_wrap0(size.getHeight(), "block-size height must be power of two");
            MediaCodecInfo._2D_wrap0(size1.getWidth(), "alignment width must be power of two");
            MediaCodecInfo._2D_wrap0(size1.getHeight(), "alignment height must be power of two");
            applyMacroBlockLimits(0x7fffffff, 0x7fffffff, 0x7fffffff, 0x7fffffffffffffffL, size.getWidth(), size.getHeight(), size1.getWidth(), size1.getHeight());
            if((mParent.mError & 2) == 0 && !mAllowMbOverride) goto _L2; else goto _L1
_L1:
            if(obj2 != null)
                mWidthRange = MediaCodecInfo._2D_get5().intersect(((Range) (obj2)));
            if(obj1 != null)
                mHeightRange = MediaCodecInfo._2D_get5().intersect(((Range) (obj1)));
            if(range != null)
                mBlockCountRange = MediaCodecInfo._2D_get2().intersect(Utils.factorRange(range, (mBlockWidth * mBlockHeight) / size.getWidth() / size.getHeight()));
            if(range1 != null)
                mBlocksPerSecondRange = MediaCodecInfo._2D_get3().intersect(Utils.factorRange(range1, (mBlockWidth * mBlockHeight) / size.getWidth() / size.getHeight()));
            if(range3 != null)
                mBlockAspectRatioRange = MediaCodecInfo._2D_get4().intersect(Utils.scaleRange(range3, mBlockHeight / size.getHeight(), mBlockWidth / size.getWidth()));
            if(range2 != null)
                mAspectRatioRange = MediaCodecInfo._2D_get4().intersect(range2);
            if(mediaformat != null)
                mFrameRateRange = MediaCodecInfo._2D_get1().intersect(mediaformat);
            if(obj3 != null)
                if((mParent.mError & 2) != 0)
                    mBitrateRange = MediaCodecInfo._2D_get0().intersect(((Range) (obj3)));
                else
                    mBitrateRange = mBitrateRange.intersect(((Range) (obj3)));
_L4:
            updateLimits();
            return;
_L2:
            if(obj2 != null)
                mWidthRange = mWidthRange.intersect(((Range) (obj2)));
            if(obj1 != null)
                mHeightRange = mHeightRange.intersect(((Range) (obj1)));
            if(range != null)
                mBlockCountRange = mBlockCountRange.intersect(Utils.factorRange(range, (mBlockWidth * mBlockHeight) / size.getWidth() / size.getHeight()));
            if(range1 != null)
                mBlocksPerSecondRange = mBlocksPerSecondRange.intersect(Utils.factorRange(range1, (mBlockWidth * mBlockHeight) / size.getWidth() / size.getHeight()));
            if(range3 != null)
                mBlockAspectRatioRange = mBlockAspectRatioRange.intersect(Utils.scaleRange(range3, mBlockHeight / size.getHeight(), mBlockWidth / size.getWidth()));
            if(range2 != null)
                mAspectRatioRange = mAspectRatioRange.intersect(range2);
            if(mediaformat != null)
                mFrameRateRange = mFrameRateRange.intersect(mediaformat);
            if(obj3 != null)
                mBitrateRange = mBitrateRange.intersect(((Range) (obj3)));
            if(true) goto _L4; else goto _L3
_L3:
        }

        private static Pair parseWidthHeightRanges(Object obj)
        {
            Pair pair;
            pair = Utils.parseSizeRange(obj);
            if(pair == null)
                break MISSING_BLOCK_LABEL_106;
            pair = Pair.create(Range.create(Integer.valueOf(((Size)pair.first).getWidth()), Integer.valueOf(((Size)pair.second).getWidth())), Range.create(Integer.valueOf(((Size)pair.first).getHeight()), Integer.valueOf(((Size)pair.second).getHeight())));
            return pair;
            IllegalArgumentException illegalargumentexception;
            illegalargumentexception;
            Log.w("VideoCapabilities", (new StringBuilder()).append("could not parse size range '").append(obj).append("'").toString());
            return null;
        }

        private boolean supports(Integer integer, Integer integer1, Number number)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(true)
            {
                flag1 = flag;
                int i;
                int j;
                int k;
                double d;
                double d1;
                if(integer != null)
                    if(mWidthRange.contains(integer))
                    {
                        if(integer.intValue() % mWidthAlignment == 0)
                            flag1 = true;
                        else
                            flag1 = false;
                    } else
                    {
                        flag1 = false;
                    }
            }
            flag = flag1;
            if(flag1)
            {
                flag = flag1;
                if(integer1 != null)
                    if(mHeightRange.contains(integer1))
                    {
                        if(integer1.intValue() % mHeightAlignment == 0)
                            flag = true;
                        else
                            flag = false;
                    } else
                    {
                        flag = false;
                    }
            }
            flag1 = flag;
            if(flag)
            {
                flag1 = flag;
                if(number != null)
                    flag1 = mFrameRateRange.contains(Utils.intRangeFor(number.doubleValue()));
            }
            flag = flag1;
            if(flag1)
            {
                flag = flag1;
                if(integer1 != null)
                {
                    flag = flag1;
                    if(integer != null)
                    {
                        boolean flag2;
                        if(Math.min(integer1.intValue(), integer.intValue()) <= mSmallerDimensionUpperLimit)
                            flag2 = true;
                        else
                            flag2 = false;
                        i = Utils.divUp(integer.intValue(), mBlockWidth);
                        j = Utils.divUp(integer1.intValue(), mBlockHeight);
                        k = i * j;
                        if(flag2 && mBlockCountRange.contains(Integer.valueOf(k)) && mBlockAspectRatioRange.contains(new Rational(i, j)))
                            flag1 = mAspectRatioRange.contains(new Rational(integer.intValue(), integer1.intValue()));
                        else
                            flag1 = false;
                        flag = flag1;
                        if(flag1)
                        {
                            flag = flag1;
                            if(number != null)
                            {
                                d = k;
                                d1 = number.doubleValue();
                                flag = mBlocksPerSecondRange.contains(Utils.longRangeFor(d * d1));
                            }
                        }
                    }
                }
            }
            return flag;
        }

        private void updateLimits()
        {
            mHorizontalBlockRange = mHorizontalBlockRange.intersect(Utils.factorRange(mWidthRange, mBlockWidth));
            mHorizontalBlockRange = mHorizontalBlockRange.intersect(Range.create(Integer.valueOf(((Integer)mBlockCountRange.getLower()).intValue() / ((Integer)mVerticalBlockRange.getUpper()).intValue()), Integer.valueOf(((Integer)mBlockCountRange.getUpper()).intValue() / ((Integer)mVerticalBlockRange.getLower()).intValue())));
            mVerticalBlockRange = mVerticalBlockRange.intersect(Utils.factorRange(mHeightRange, mBlockHeight));
            mVerticalBlockRange = mVerticalBlockRange.intersect(Range.create(Integer.valueOf(((Integer)mBlockCountRange.getLower()).intValue() / ((Integer)mHorizontalBlockRange.getUpper()).intValue()), Integer.valueOf(((Integer)mBlockCountRange.getUpper()).intValue() / ((Integer)mHorizontalBlockRange.getLower()).intValue())));
            Range range = mBlockCountRange;
            int i = ((Integer)mHorizontalBlockRange.getLower()).intValue();
            int j = ((Integer)mVerticalBlockRange.getLower()).intValue();
            int k = ((Integer)mHorizontalBlockRange.getUpper()).intValue();
            mBlockCountRange = range.intersect(Range.create(Integer.valueOf(j * i), Integer.valueOf(((Integer)mVerticalBlockRange.getUpper()).intValue() * k)));
            mBlockAspectRatioRange = mBlockAspectRatioRange.intersect(new Rational(((Integer)mHorizontalBlockRange.getLower()).intValue(), ((Integer)mVerticalBlockRange.getUpper()).intValue()), new Rational(((Integer)mHorizontalBlockRange.getUpper()).intValue(), ((Integer)mVerticalBlockRange.getLower()).intValue()));
            mWidthRange = mWidthRange.intersect(Integer.valueOf((((Integer)mHorizontalBlockRange.getLower()).intValue() - 1) * mBlockWidth + mWidthAlignment), Integer.valueOf(((Integer)mHorizontalBlockRange.getUpper()).intValue() * mBlockWidth));
            mHeightRange = mHeightRange.intersect(Integer.valueOf((((Integer)mVerticalBlockRange.getLower()).intValue() - 1) * mBlockHeight + mHeightAlignment), Integer.valueOf(((Integer)mVerticalBlockRange.getUpper()).intValue() * mBlockHeight));
            mAspectRatioRange = mAspectRatioRange.intersect(new Rational(((Integer)mWidthRange.getLower()).intValue(), ((Integer)mHeightRange.getUpper()).intValue()), new Rational(((Integer)mWidthRange.getUpper()).intValue(), ((Integer)mHeightRange.getLower()).intValue()));
            mSmallerDimensionUpperLimit = Math.min(mSmallerDimensionUpperLimit, Math.min(((Integer)mWidthRange.getUpper()).intValue(), ((Integer)mHeightRange.getUpper()).intValue()));
            mBlocksPerSecondRange = mBlocksPerSecondRange.intersect(Long.valueOf((long)((Integer)mBlockCountRange.getLower()).intValue() * (long)((Integer)mFrameRateRange.getLower()).intValue()), Long.valueOf((long)((Integer)mBlockCountRange.getUpper()).intValue() * (long)((Integer)mFrameRateRange.getUpper()).intValue()));
            mFrameRateRange = mFrameRateRange.intersect(Integer.valueOf((int)(((Long)mBlocksPerSecondRange.getLower()).longValue() / (long)((Integer)mBlockCountRange.getUpper()).intValue())), Integer.valueOf((int)((double)((Long)mBlocksPerSecondRange.getUpper()).longValue() / (double)((Integer)mBlockCountRange.getLower()).intValue())));
        }

        public boolean areSizeAndRateSupported(int i, int j, double d)
        {
            return supports(Integer.valueOf(i), Integer.valueOf(j), Double.valueOf(d));
        }

        public Range getAchievableFrameRatesFor(int i, int j)
        {
            if(!supports(Integer.valueOf(i), Integer.valueOf(j), null))
                throw new IllegalArgumentException("unsupported size");
            if(mMeasuredFrameRates == null || mMeasuredFrameRates.size() <= 0)
            {
                Log.w("VideoCapabilities", "Codec did not publish any measurement data.");
                return null;
            } else
            {
                return estimateFrameRatesFor(i, j);
            }
        }

        public Range getAspectRatioRange(boolean flag)
        {
            Range range;
            if(flag)
                range = mBlockAspectRatioRange;
            else
                range = mAspectRatioRange;
            return range;
        }

        public Range getBitrateRange()
        {
            return mBitrateRange;
        }

        public Range getBlockCountRange()
        {
            return mBlockCountRange;
        }

        public Size getBlockSize()
        {
            return new Size(mBlockWidth, mBlockHeight);
        }

        public Range getBlocksPerSecondRange()
        {
            return mBlocksPerSecondRange;
        }

        public int getHeightAlignment()
        {
            return mHeightAlignment;
        }

        public int getSmallerDimensionUpperLimit()
        {
            return mSmallerDimensionUpperLimit;
        }

        public Range getSupportedFrameRates()
        {
            return mFrameRateRange;
        }

        public Range getSupportedFrameRatesFor(int i, int j)
        {
            Range range = mHeightRange;
            if(!supports(Integer.valueOf(i), Integer.valueOf(j), null))
            {
                throw new IllegalArgumentException("unsupported size");
            } else
            {
                i = Utils.divUp(i, mBlockWidth) * Utils.divUp(j, mBlockHeight);
                return Range.create(Double.valueOf(Math.max((double)((Long)mBlocksPerSecondRange.getLower()).longValue() / (double)i, ((Integer)mFrameRateRange.getLower()).intValue())), Double.valueOf(Math.min((double)((Long)mBlocksPerSecondRange.getUpper()).longValue() / (double)i, ((Integer)mFrameRateRange.getUpper()).intValue())));
            }
        }

        public Range getSupportedHeights()
        {
            return mHeightRange;
        }

        public Range getSupportedHeightsFor(int i)
        {
            Object obj;
            try
            {
                obj = mHeightRange;
                if(!mWidthRange.contains(Integer.valueOf(i)) || i % mWidthAlignment != 0)
                {
                    obj = JVM INSTR new #505 <Class IllegalArgumentException>;
                    ((IllegalArgumentException) (obj)).IllegalArgumentException("unsupported width");
                    throw obj;
                }
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.v("VideoCapabilities", (new StringBuilder()).append("could not get supported heights for ").append(i).toString());
                throw new IllegalArgumentException("unsupported width");
            }
            Range range;
            int j = Utils.divUp(i, mBlockWidth);
            int k = Math.max(Utils.divUp(((Integer)mBlockCountRange.getLower()).intValue(), j), (int)Math.ceil((double)j / ((Rational)mBlockAspectRatioRange.getUpper()).doubleValue()));
            j = Math.min(((Integer)mBlockCountRange.getUpper()).intValue() / j, (int)((double)j / ((Rational)mBlockAspectRatioRange.getLower()).doubleValue()));
            range = ((Range) (obj)).intersect(Integer.valueOf((k - 1) * mBlockHeight + mHeightAlignment), Integer.valueOf(mBlockHeight * j));
            obj = range;
            if(i > mSmallerDimensionUpperLimit)
                obj = range.intersect(Integer.valueOf(1), Integer.valueOf(mSmallerDimensionUpperLimit));
            obj = ((Range) (obj)).intersect(Integer.valueOf((int)Math.ceil((double)i / ((Rational)mAspectRatioRange.getUpper()).doubleValue())), Integer.valueOf((int)((double)i / ((Rational)mAspectRatioRange.getLower()).doubleValue())));
            return ((Range) (obj));
        }

        public Range getSupportedWidths()
        {
            return mWidthRange;
        }

        public Range getSupportedWidthsFor(int i)
        {
            Object obj;
            try
            {
                obj = mWidthRange;
                if(!mHeightRange.contains(Integer.valueOf(i)) || i % mHeightAlignment != 0)
                {
                    obj = JVM INSTR new #505 <Class IllegalArgumentException>;
                    ((IllegalArgumentException) (obj)).IllegalArgumentException("unsupported height");
                    throw obj;
                }
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.v("VideoCapabilities", (new StringBuilder()).append("could not get supported widths for ").append(i).toString());
                throw new IllegalArgumentException("unsupported height");
            }
            Range range;
            int j = Utils.divUp(i, mBlockHeight);
            int k = Math.max(Utils.divUp(((Integer)mBlockCountRange.getLower()).intValue(), j), (int)Math.ceil(((Rational)mBlockAspectRatioRange.getLower()).doubleValue() * (double)j));
            j = Math.min(((Integer)mBlockCountRange.getUpper()).intValue() / j, (int)(((Rational)mBlockAspectRatioRange.getUpper()).doubleValue() * (double)j));
            range = ((Range) (obj)).intersect(Integer.valueOf((k - 1) * mBlockWidth + mWidthAlignment), Integer.valueOf(mBlockWidth * j));
            obj = range;
            if(i > mSmallerDimensionUpperLimit)
                obj = range.intersect(Integer.valueOf(1), Integer.valueOf(mSmallerDimensionUpperLimit));
            obj = ((Range) (obj)).intersect(Integer.valueOf((int)Math.ceil(((Rational)mAspectRatioRange.getLower()).doubleValue() * (double)i)), Integer.valueOf((int)(((Rational)mAspectRatioRange.getUpper()).doubleValue() * (double)i)));
            return ((Range) (obj));
        }

        public int getWidthAlignment()
        {
            return mWidthAlignment;
        }

        public void init(MediaFormat mediaformat, CodecCapabilities codeccapabilities)
        {
            mParent = codeccapabilities;
            initWithPlatformLimits();
            applyLevelLimits();
            parseFromInfo(mediaformat);
            updateLimits();
        }

        public boolean isSizeSupported(int i, int j)
        {
            return supports(Integer.valueOf(i), Integer.valueOf(j), null);
        }

        public boolean supportsFormat(MediaFormat mediaformat)
        {
            Map map = mediaformat.getMap();
            if(!supports((Integer)map.get("width"), (Integer)map.get("height"), (Number)map.get("frame-rate")))
                return false;
            return CodecCapabilities._2D_wrap0(mBitrateRange, mediaformat);
        }

        private static final String TAG = "VideoCapabilities";
        private boolean mAllowMbOverride;
        private Range mAspectRatioRange;
        private Range mBitrateRange;
        private Range mBlockAspectRatioRange;
        private Range mBlockCountRange;
        private int mBlockHeight;
        private int mBlockWidth;
        private Range mBlocksPerSecondRange;
        private Range mFrameRateRange;
        private int mHeightAlignment;
        private Range mHeightRange;
        private Range mHorizontalBlockRange;
        private Map mMeasuredFrameRates;
        private CodecCapabilities mParent;
        private int mSmallerDimensionUpperLimit;
        private Range mVerticalBlockRange;
        private int mWidthAlignment;
        private Range mWidthRange;

        private VideoCapabilities()
        {
        }
    }


    static Range _2D_get0()
    {
        return BITRATE_RANGE;
    }

    static Range _2D_get1()
    {
        return FRAME_RATE_RANGE;
    }

    static Range _2D_get2()
    {
        return POSITIVE_INTEGERS;
    }

    static Range _2D_get3()
    {
        return POSITIVE_LONGS;
    }

    static Range _2D_get4()
    {
        return POSITIVE_RATIONALS;
    }

    static Range _2D_get5()
    {
        return SIZE_RANGE;
    }

    static int _2D_wrap0(int i, String s)
    {
        return checkPowerOfTwo(i, s);
    }

    MediaCodecInfo(String s, boolean flag, CodecCapabilities acodeccapabilities[])
    {
        mName = s;
        mIsEncoder = flag;
        mCaps = new HashMap();
        int i = 0;
        for(int j = acodeccapabilities.length; i < j; i++)
        {
            s = acodeccapabilities[i];
            mCaps.put(s.getMimeType(), s);
        }

    }

    private static int checkPowerOfTwo(int i, String s)
    {
        if((i - 1 & i) != 0)
            throw new IllegalArgumentException(s);
        else
            return i;
    }

    public final CodecCapabilities getCapabilitiesForType(String s)
    {
        s = (CodecCapabilities)mCaps.get(s);
        if(s == null)
            throw new IllegalArgumentException("codec does not support type");
        else
            return s.dup();
    }

    public final String getName()
    {
        return mName;
    }

    public final String[] getSupportedTypes()
    {
        Set set = mCaps.keySet();
        String as[] = (String[])set.toArray(new String[set.size()]);
        Arrays.sort(as);
        return as;
    }

    public final boolean isEncoder()
    {
        return mIsEncoder;
    }

    public MediaCodecInfo makeRegular()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = mCaps.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            CodecCapabilities codeccapabilities = (CodecCapabilities)iterator.next();
            if(codeccapabilities.isRegular())
                arraylist.add(codeccapabilities);
        } while(true);
        if(arraylist.size() == 0)
            return null;
        if(arraylist.size() == mCaps.size())
            return this;
        else
            return new MediaCodecInfo(mName, mIsEncoder, (CodecCapabilities[])arraylist.toArray(new CodecCapabilities[arraylist.size()]));
    }

    private static final Range BITRATE_RANGE = Range.create(Integer.valueOf(0), Integer.valueOf(0x1dcd6500));
    private static final int DEFAULT_MAX_SUPPORTED_INSTANCES = 32;
    private static final int ERROR_NONE_SUPPORTED = 4;
    private static final int ERROR_UNRECOGNIZED = 1;
    private static final int ERROR_UNSUPPORTED = 2;
    private static final Range FRAME_RATE_RANGE = Range.create(Integer.valueOf(0), Integer.valueOf(960));
    private static final int MAX_SUPPORTED_INSTANCES_LIMIT = 256;
    private static final Range POSITIVE_INTEGERS = Range.create(Integer.valueOf(1), Integer.valueOf(0x7fffffff));
    private static final Range POSITIVE_LONGS = Range.create(Long.valueOf(1L), Long.valueOf(0x7fffffffffffffffL));
    private static final Range POSITIVE_RATIONALS = Range.create(new Rational(1, 0x7fffffff), new Rational(0x7fffffff, 1));
    private static final Range SIZE_RANGE = Range.create(Integer.valueOf(1), Integer.valueOf(32768));
    private Map mCaps;
    private boolean mIsEncoder;
    private String mName;

}
