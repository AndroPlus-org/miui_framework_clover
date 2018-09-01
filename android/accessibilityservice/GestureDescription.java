// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accessibilityservice;

import android.graphics.*;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;

public final class GestureDescription
{
    public static class Builder
    {

        public Builder addStroke(StrokeDescription strokedescription)
        {
            if(mStrokes.size() >= 10)
                throw new IllegalStateException("Attempting to add too many strokes to a gesture");
            mStrokes.add(strokedescription);
            if(GestureDescription._2D_wrap2(mStrokes) > 60000L)
            {
                mStrokes.remove(strokedescription);
                throw new IllegalStateException("Gesture would exceed maximum duration with new stroke");
            } else
            {
                return this;
            }
        }

        public GestureDescription build()
        {
            if(mStrokes.size() == 0)
                throw new IllegalStateException("Gestures must have at least one stroke");
            else
                return new GestureDescription(mStrokes, null);
        }

        private final List mStrokes = new ArrayList();

        public Builder()
        {
        }
    }

    public static class GestureStep
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeLong(timeSinceGestureStart);
            parcel.writeParcelableArray(touchPoints, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public GestureStep createFromParcel(Parcel parcel)
            {
                return new GestureStep(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public GestureStep[] newArray(int i)
            {
                return new GestureStep[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public int numTouchPoints;
        public long timeSinceGestureStart;
        public TouchPoint touchPoints[];


        public GestureStep(long l, int i, TouchPoint atouchpoint[])
        {
            timeSinceGestureStart = l;
            numTouchPoints = i;
            touchPoints = new TouchPoint[i];
            for(int j = 0; j < i; j++)
                touchPoints[j] = new TouchPoint(atouchpoint[j]);

        }

        public GestureStep(Parcel parcel)
        {
            timeSinceGestureStart = parcel.readLong();
            parcel = parcel.readParcelableArray(android/accessibilityservice/GestureDescription$TouchPoint.getClassLoader());
            int i;
            if(parcel == null)
                i = 0;
            else
                i = parcel.length;
            numTouchPoints = i;
            touchPoints = new TouchPoint[numTouchPoints];
            for(i = 0; i < numTouchPoints; i++)
                touchPoints[i] = (TouchPoint)parcel[i];

        }
    }

    public static class MotionEventGenerator
    {

        private static TouchPoint[] getCurrentTouchPoints(int i)
        {
            if(sCurrentTouchPoints == null || sCurrentTouchPoints.length < i)
            {
                sCurrentTouchPoints = new TouchPoint[i];
                for(int j = 0; j < i; j++)
                    sCurrentTouchPoints[j] = new TouchPoint();

            }
            return sCurrentTouchPoints;
        }

        public static List getGestureStepsFromGestureDescription(GestureDescription gesturedescription, int i)
        {
            ArrayList arraylist = new ArrayList();
            TouchPoint atouchpoint[] = getCurrentTouchPoints(gesturedescription.getStrokeCount());
            int j = 0;
            long l = 0L;
            long l1 = GestureDescription._2D_wrap1(gesturedescription, 0L);
            while(l1 >= 0L) 
            {
                if(j == 0)
                    l = l1;
                else
                    l = Math.min(l1, (long)i + l);
                j = GestureDescription._2D_wrap0(gesturedescription, l, atouchpoint);
                arraylist.add(new GestureStep(l, j, atouchpoint));
                l1 = GestureDescription._2D_wrap1(gesturedescription, 1L + l);
            }
            return arraylist;
        }

        private static TouchPoint sCurrentTouchPoints[];

        public MotionEventGenerator()
        {
        }
    }

    public static class StrokeDescription
    {

        public StrokeDescription continueStroke(Path path, long l, long l1, boolean flag)
        {
            if(!mContinued)
            {
                throw new IllegalStateException("Only strokes marked willContinue can be continued");
            } else
            {
                path = new StrokeDescription(path, l, l1, flag);
                path.mContinuedStrokeId = mId;
                return path;
            }
        }

        public int getContinuedStrokeId()
        {
            return mContinuedStrokeId;
        }

        public long getDuration()
        {
            return mEndTime - mStartTime;
        }

        public int getId()
        {
            return mId;
        }

        float getLength()
        {
            return mPathMeasure.getLength();
        }

        public Path getPath()
        {
            return new Path(mPath);
        }

        boolean getPosForTime(long l, float af[])
        {
            if(mTapLocation != null)
            {
                af[0] = mTapLocation[0];
                af[1] = mTapLocation[1];
                return true;
            }
            if(l == mEndTime)
            {
                return mPathMeasure.getPosTan(getLength(), af, null);
            } else
            {
                float f = mTimeToLengthConversion;
                float f1 = l - mStartTime;
                return mPathMeasure.getPosTan(f * f1, af, null);
            }
        }

        public long getStartTime()
        {
            return mStartTime;
        }

        boolean hasPointForTime(long l)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(l >= mStartTime)
            {
                flag1 = flag;
                if(l <= mEndTime)
                    flag1 = true;
            }
            return flag1;
        }

        public boolean willContinue()
        {
            return mContinued;
        }

        private static final int INVALID_STROKE_ID = -1;
        static int sIdCounter;
        boolean mContinued;
        int mContinuedStrokeId;
        long mEndTime;
        int mId;
        Path mPath;
        private PathMeasure mPathMeasure;
        long mStartTime;
        float mTapLocation[];
        private float mTimeToLengthConversion;

        public StrokeDescription(Path path, long l, long l1)
        {
            this(path, l, l1, false);
        }

        public StrokeDescription(Path path, long l, long l1, boolean flag)
        {
            mContinuedStrokeId = -1;
            mContinued = flag;
            RectF rectf;
            if(l1 > 0L)
                flag = true;
            else
                flag = false;
            Preconditions.checkArgument(flag, "Duration must be positive");
            if(l >= 0L)
                flag = true;
            else
                flag = false;
            Preconditions.checkArgument(flag, "Start time must not be negative");
            Preconditions.checkArgument(path.isEmpty() ^ true, "Path is empty");
            rectf = new RectF();
            path.computeBounds(rectf, false);
            if(rectf.bottom >= 0.0F && rectf.top >= 0.0F && rectf.right >= 0.0F)
            {
                if(rectf.left >= 0.0F)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = false;
            }
            Preconditions.checkArgument(flag, "Path bounds must not be negative");
            mPath = new Path(path);
            mPathMeasure = new PathMeasure(path, false);
            if(mPathMeasure.getLength() == 0.0F)
            {
                path = new Path(path);
                path.lineTo(-1F, -1F);
                mTapLocation = new float[2];
                (new PathMeasure(path, false)).getPosTan(0.0F, mTapLocation, null);
            }
            if(mPathMeasure.nextContour())
            {
                throw new IllegalArgumentException("Path has more than one contour");
            } else
            {
                mPathMeasure.setPath(mPath, false);
                mStartTime = l;
                mEndTime = l + l1;
                mTimeToLengthConversion = getLength() / (float)l1;
                int i = sIdCounter;
                sIdCounter = i + 1;
                mId = i;
                return;
            }
        }
    }

    public static class TouchPoint
        implements Parcelable
    {

        public void copyFrom(TouchPoint touchpoint)
        {
            mStrokeId = touchpoint.mStrokeId;
            mContinuedStrokeId = touchpoint.mContinuedStrokeId;
            mIsStartOfPath = touchpoint.mIsStartOfPath;
            mIsEndOfPath = touchpoint.mIsEndOfPath;
            mX = touchpoint.mX;
            mY = touchpoint.mY;
        }

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mStrokeId);
            parcel.writeInt(mContinuedStrokeId);
            byte byte0;
            if(mIsStartOfPath)
                i = 1;
            else
                i = 0;
            if(mIsEndOfPath)
                byte0 = 2;
            else
                byte0 = 0;
            parcel.writeInt(i | byte0);
            parcel.writeFloat(mX);
            parcel.writeFloat(mY);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public TouchPoint createFromParcel(Parcel parcel)
            {
                return new TouchPoint(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public TouchPoint[] newArray(int i)
            {
                return new TouchPoint[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final int FLAG_IS_END_OF_PATH = 2;
        private static final int FLAG_IS_START_OF_PATH = 1;
        public int mContinuedStrokeId;
        public boolean mIsEndOfPath;
        public boolean mIsStartOfPath;
        public int mStrokeId;
        public float mX;
        public float mY;


        public TouchPoint()
        {
        }

        public TouchPoint(TouchPoint touchpoint)
        {
            copyFrom(touchpoint);
        }

        public TouchPoint(Parcel parcel)
        {
            boolean flag = true;
            super();
            mStrokeId = parcel.readInt();
            mContinuedStrokeId = parcel.readInt();
            int i = parcel.readInt();
            boolean flag1;
            if((i & 1) != 0)
                flag1 = true;
            else
                flag1 = false;
            mIsStartOfPath = flag1;
            if((i & 2) != 0)
                flag1 = flag;
            else
                flag1 = false;
            mIsEndOfPath = flag1;
            mX = parcel.readFloat();
            mY = parcel.readFloat();
        }
    }


    static int _2D_wrap0(GestureDescription gesturedescription, long l, TouchPoint atouchpoint[])
    {
        return gesturedescription.getPointsForTime(l, atouchpoint);
    }

    static long _2D_wrap1(GestureDescription gesturedescription, long l)
    {
        return gesturedescription.getNextKeyPointAtLeast(l);
    }

    static long _2D_wrap2(List list)
    {
        return getTotalDuration(list);
    }

    private GestureDescription()
    {
        mStrokes = new ArrayList();
        mTempPos = new float[2];
    }

    private GestureDescription(List list)
    {
        mStrokes = new ArrayList();
        mTempPos = new float[2];
        mStrokes.addAll(list);
    }

    GestureDescription(List list, GestureDescription gesturedescription)
    {
        this(list);
    }

    public static long getMaxGestureDuration()
    {
        return 60000L;
    }

    public static int getMaxStrokeCount()
    {
        return 10;
    }

    private long getNextKeyPointAtLeast(long l)
    {
        long l1 = 0x7fffffffffffffffL;
        for(int i = 0; i < mStrokes.size(); i++)
        {
            long l2 = ((StrokeDescription)mStrokes.get(i)).mStartTime;
            long l3 = l1;
            if(l2 < l1)
            {
                l3 = l1;
                if(l2 >= l)
                    l3 = l2;
            }
            l2 = ((StrokeDescription)mStrokes.get(i)).mEndTime;
            l1 = l3;
            if(l2 >= l3)
                continue;
            l1 = l3;
            if(l2 >= l)
                l1 = l2;
        }

        l = l1;
        if(l1 == 0x7fffffffffffffffL)
            l = -1L;
        return l;
    }

    private int getPointsForTime(long l, TouchPoint atouchpoint[])
    {
        int i = 0;
        int j = 0;
        while(j < mStrokes.size()) 
        {
            StrokeDescription strokedescription = (StrokeDescription)mStrokes.get(j);
            int k = i;
            if(strokedescription.hasPointForTime(l))
            {
                atouchpoint[i].mStrokeId = strokedescription.getId();
                atouchpoint[i].mContinuedStrokeId = strokedescription.getContinuedStrokeId();
                TouchPoint touchpoint = atouchpoint[i];
                boolean flag;
                if(strokedescription.getContinuedStrokeId() < 0)
                {
                    if(l == strokedescription.mStartTime)
                        flag = true;
                    else
                        flag = false;
                } else
                {
                    flag = false;
                }
                touchpoint.mIsStartOfPath = flag;
                touchpoint = atouchpoint[i];
                if(!strokedescription.willContinue())
                {
                    if(l == strokedescription.mEndTime)
                        flag = true;
                    else
                        flag = false;
                } else
                {
                    flag = false;
                }
                touchpoint.mIsEndOfPath = flag;
                strokedescription.getPosForTime(l, mTempPos);
                atouchpoint[i].mX = Math.round(mTempPos[0]);
                atouchpoint[i].mY = Math.round(mTempPos[1]);
                k = i + 1;
            }
            j++;
            i = k;
        }
        return i;
    }

    private static long getTotalDuration(List list)
    {
        long l = 0x8000000000000000L;
        for(int i = 0; i < list.size(); i++)
            l = Math.max(l, ((StrokeDescription)list.get(i)).mEndTime);

        return Math.max(l, 0L);
    }

    public StrokeDescription getStroke(int i)
    {
        return (StrokeDescription)mStrokes.get(i);
    }

    public int getStrokeCount()
    {
        return mStrokes.size();
    }

    private static final long MAX_GESTURE_DURATION_MS = 60000L;
    private static final int MAX_STROKE_COUNT = 10;
    private final List mStrokes;
    private final float mTempPos[];
}
