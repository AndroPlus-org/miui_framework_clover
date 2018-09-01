// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.assist;

import android.app.Activity;
import android.content.ComponentName;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.*;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import java.util.*;

public class AssistStructure
    implements Parcelable
{
    public static class AutofillOverlay
    {

        public boolean focused;
        public AutofillValue value;

        public AutofillOverlay()
        {
        }
    }

    private static final class HtmlInfoNode extends android.view.ViewStructure.HtmlInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public List getAttributes()
        {
            if(mAttributes == null && mNames != null)
            {
                mAttributes = new ArrayList(mNames.length);
                for(int i = 0; i < mNames.length; i++)
                {
                    Pair pair = new Pair(mNames[i], mValues[i]);
                    mAttributes.add(i, pair);
                }

            }
            return mAttributes;
        }

        public String getTag()
        {
            return mTag;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mTag);
            parcel.writeStringArray(mNames);
            parcel.writeStringArray(mValues);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public HtmlInfoNode createFromParcel(Parcel parcel)
            {
                HtmlInfoNodeBuilder htmlinfonodebuilder = new HtmlInfoNodeBuilder(parcel.readString());
                String as[] = parcel.readStringArray();
                parcel = parcel.readStringArray();
                if(as != null && parcel != null)
                    if(as.length != parcel.length)
                    {
                        Log.w("AssistStructure", (new StringBuilder()).append("HtmlInfo attributes mismatch: names=").append(as.length).append(", values=").append(parcel.length).toString());
                    } else
                    {
                        int i = 0;
                        while(i < as.length) 
                        {
                            htmlinfonodebuilder.addAttribute(as[i], parcel[i]);
                            i++;
                        }
                    }
                return htmlinfonodebuilder.build();
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public HtmlInfoNode[] newArray(int i)
            {
                return new HtmlInfoNode[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private ArrayList mAttributes;
        private final String mNames[];
        private final String mTag;
        private final String mValues[];


        private HtmlInfoNode(HtmlInfoNodeBuilder htmlinfonodebuilder)
        {
            mTag = HtmlInfoNodeBuilder._2D_get1(htmlinfonodebuilder);
            if(HtmlInfoNodeBuilder._2D_get0(htmlinfonodebuilder) == null)
            {
                mNames = null;
                mValues = null;
            } else
            {
                mNames = new String[HtmlInfoNodeBuilder._2D_get0(htmlinfonodebuilder).size()];
                mValues = new String[HtmlInfoNodeBuilder._2D_get2(htmlinfonodebuilder).size()];
                HtmlInfoNodeBuilder._2D_get0(htmlinfonodebuilder).toArray(mNames);
                HtmlInfoNodeBuilder._2D_get2(htmlinfonodebuilder).toArray(mValues);
            }
        }

        HtmlInfoNode(HtmlInfoNodeBuilder htmlinfonodebuilder, HtmlInfoNode htmlinfonode)
        {
            this(htmlinfonodebuilder);
        }
    }

    private static final class HtmlInfoNodeBuilder extends android.view.ViewStructure.HtmlInfo.Builder
    {

        static ArrayList _2D_get0(HtmlInfoNodeBuilder htmlinfonodebuilder)
        {
            return htmlinfonodebuilder.mNames;
        }

        static String _2D_get1(HtmlInfoNodeBuilder htmlinfonodebuilder)
        {
            return htmlinfonodebuilder.mTag;
        }

        static ArrayList _2D_get2(HtmlInfoNodeBuilder htmlinfonodebuilder)
        {
            return htmlinfonodebuilder.mValues;
        }

        public android.view.ViewStructure.HtmlInfo.Builder addAttribute(String s, String s1)
        {
            if(mNames == null)
            {
                mNames = new ArrayList();
                mValues = new ArrayList();
            }
            mNames.add(s);
            mValues.add(s1);
            return this;
        }

        public HtmlInfoNode build()
        {
            return new HtmlInfoNode(this, null);
        }

        public volatile android.view.ViewStructure.HtmlInfo build()
        {
            return build();
        }

        private ArrayList mNames;
        private final String mTag;
        private ArrayList mValues;

        HtmlInfoNodeBuilder(String s)
        {
            mTag = s;
        }
    }

    final class ParcelTransferReader
    {

        private void fetchData()
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.AssistStructure");
            parcel.writeStrongBinder(mTransferToken);
            if(mCurParcel != null)
                mCurParcel.recycle();
            mCurParcel = Parcel.obtain();
            try
            {
                mChannel.transact(2, parcel, mCurParcel, 0);
            }
            catch(RemoteException remoteexception)
            {
                Log.w("AssistStructure", "Failure reading AssistStructure data", remoteexception);
                throw new IllegalStateException((new StringBuilder()).append("Failure reading AssistStructure data: ").append(remoteexception).toString());
            }
            parcel.recycle();
            mNumReadViews = 0;
            mNumReadWindows = 0;
        }

        void go()
        {
            fetchData();
            mActivityComponent = ComponentName.readFromParcel(mCurParcel);
            AssistStructure._2D_set2(AssistStructure.this, mCurParcel.readInt());
            AssistStructure._2D_set1(AssistStructure.this, mCurParcel.readLong());
            AssistStructure._2D_set0(AssistStructure.this, mCurParcel.readLong());
            int i = mCurParcel.readInt();
            if(i > 0)
            {
                mStringReader = new PooledStringReader(mCurParcel);
                for(int j = 0; j < i; j++)
                    mWindowNodes.add(new WindowNode(this));

            }
        }

        Parcel readParcel(int i, int j)
        {
            j = mCurParcel.readInt();
            if(j != 0)
                if(j != i)
                    throw new BadParcelableException((new StringBuilder()).append("Got token ").append(Integer.toHexString(j)).append(", expected token ").append(Integer.toHexString(i)).toString());
                else
                    return mCurParcel;
            mTransferToken = mCurParcel.readStrongBinder();
            if(mTransferToken == null)
            {
                throw new IllegalStateException("Reached end of partial data without transfer token");
            } else
            {
                fetchData();
                mStringReader = new PooledStringReader(mCurParcel);
                mCurParcel.readInt();
                return mCurParcel;
            }
        }

        private final IBinder mChannel;
        private Parcel mCurParcel;
        int mNumReadViews;
        int mNumReadWindows;
        PooledStringReader mStringReader;
        final float mTmpMatrix[] = new float[9];
        private IBinder mTransferToken;
        final AssistStructure this$0;

        ParcelTransferReader(IBinder ibinder)
        {
            this$0 = AssistStructure.this;
            super();
            mChannel = ibinder;
        }
    }

    static final class ParcelTransferWriter extends Binder
    {

        void pushViewStackEntry(ViewNode viewnode, int i)
        {
            ViewStackEntry viewstackentry;
            if(i >= mViewStack.size())
            {
                viewstackentry = new ViewStackEntry();
                mViewStack.add(viewstackentry);
            } else
            {
                viewstackentry = (ViewStackEntry)mViewStack.get(i);
            }
            viewstackentry.node = viewnode;
            viewstackentry.numChildren = viewnode.getChildCount();
            viewstackentry.curChild = 0;
            mCurViewStackEntry = viewstackentry;
        }

        boolean writeNextEntryToParcel(AssistStructure assiststructure, Parcel parcel, PooledStringWriter pooledstringwriter)
        {
            if(mCurViewStackEntry == null)
                break MISSING_BLOCK_LABEL_129;
            if(mCurViewStackEntry.curChild < mCurViewStackEntry.numChildren)
            {
                ViewNode viewnode = mCurViewStackEntry.node.mChildren[mCurViewStackEntry.curChild];
                assiststructure = mCurViewStackEntry;
                assiststructure.curChild = ((ViewStackEntry) (assiststructure)).curChild + 1;
                writeView(viewnode, parcel, pooledstringwriter, 1);
                return true;
            }
              goto _L1
_L3:
            int i;
            mCurViewStackEntry = (ViewStackEntry)mViewStack.get(i);
            if(mCurViewStackEntry.curChild < mCurViewStackEntry.numChildren)
                break; /* Loop/switch isn't completed */
_L1:
            i = mCurViewStackPos - 1;
            mCurViewStackPos = i;
            if(i >= 0)
                continue; /* Loop/switch isn't completed */
            mCurViewStackEntry = null;
            break; /* Loop/switch isn't completed */
            if(true) goto _L3; else goto _L2
_L2:
            return true;
            int j = mCurWindow;
            if(j < mNumWindows)
            {
                assiststructure = (WindowNode)assiststructure.mWindowNodes.get(j);
                mCurWindow = mCurWindow + 1;
                parcel.writeInt(0x11111111);
                assiststructure.writeSelfToParcel(parcel, pooledstringwriter, mTmpMatrix);
                mNumWrittenWindows = mNumWrittenWindows + 1;
                assiststructure = ((WindowNode) (assiststructure)).mRoot;
                mCurViewStackPos = 0;
                writeView(assiststructure, parcel, pooledstringwriter, 0);
                return true;
            } else
            {
                return false;
            }
        }

        void writeToParcel(AssistStructure assiststructure, Parcel parcel)
        {
            int i = parcel.dataPosition();
            mNumWrittenWindows = 0;
            mNumWrittenViews = 0;
            boolean flag = writeToParcelInner(assiststructure, parcel);
            StringBuilder stringbuilder = (new StringBuilder()).append("Flattened ");
            if(flag)
                assiststructure = "partial";
            else
                assiststructure = "final";
            Log.i("AssistStructure", stringbuilder.append(assiststructure).append(" assist data: ").append(parcel.dataPosition() - i).append(" bytes, containing ").append(mNumWrittenWindows).append(" windows, ").append(mNumWrittenViews).append(" views").toString());
        }

        boolean writeToParcelInner(AssistStructure assiststructure, Parcel parcel)
        {
            if(mNumWindows == 0)
                return false;
            PooledStringWriter pooledstringwriter;
            for(pooledstringwriter = new PooledStringWriter(parcel); writeNextEntryToParcel(assiststructure, parcel, pooledstringwriter);)
                if(parcel.dataSize() > 0x10000)
                {
                    parcel.writeInt(0);
                    parcel.writeStrongBinder(this);
                    pooledstringwriter.finish();
                    return true;
                }

            pooledstringwriter.finish();
            mViewStack.clear();
            return false;
        }

        void writeView(ViewNode viewnode, Parcel parcel, PooledStringWriter pooledstringwriter, int i)
        {
            parcel.writeInt(0x22222222);
            i = viewnode.writeSelfToParcel(parcel, pooledstringwriter, mSanitizeOnWrite, mTmpMatrix);
            mNumWrittenViews = mNumWrittenViews + 1;
            if((0x100000 & i) != 0)
            {
                parcel.writeInt(viewnode.mChildren.length);
                i = mCurViewStackPos + 1;
                mCurViewStackPos = i;
                pushViewStackEntry(viewnode, i);
            }
        }

        ViewStackEntry mCurViewStackEntry;
        int mCurViewStackPos;
        int mCurWindow;
        int mNumWindows;
        int mNumWrittenViews;
        int mNumWrittenWindows;
        final boolean mSanitizeOnWrite;
        final float mTmpMatrix[] = new float[9];
        final ArrayList mViewStack = new ArrayList();
        final boolean mWriteStructure;

        ParcelTransferWriter(AssistStructure assiststructure, Parcel parcel)
        {
            mSanitizeOnWrite = assiststructure.mSanitizeOnWrite;
            mWriteStructure = assiststructure.waitForReady();
            ComponentName.writeToParcel(assiststructure.mActivityComponent, parcel);
            parcel.writeInt(AssistStructure._2D_get2(assiststructure));
            parcel.writeLong(AssistStructure._2D_get1(assiststructure));
            parcel.writeLong(AssistStructure._2D_get0(assiststructure));
            mNumWindows = assiststructure.mWindowNodes.size();
            if(mWriteStructure && mNumWindows > 0)
                parcel.writeInt(mNumWindows);
            else
                parcel.writeInt(0);
        }
    }

    static final class SendChannel extends Binder
    {

        protected boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            if(i == 2)
            {
                AssistStructure assiststructure = mAssistStructure;
                if(assiststructure == null)
                    return true;
                parcel.enforceInterface("android.app.AssistStructure");
                parcel = parcel.readStrongBinder();
                if(parcel != null)
                {
                    if(parcel instanceof ParcelTransferWriter)
                    {
                        ((ParcelTransferWriter)parcel).writeToParcel(assiststructure, parcel1);
                        return true;
                    } else
                    {
                        Log.w("AssistStructure", (new StringBuilder()).append("Caller supplied bad token type: ").append(parcel).toString());
                        return true;
                    }
                } else
                {
                    (new ParcelTransferWriter(assiststructure, parcel1)).writeToParcel(assiststructure, parcel1);
                    return true;
                }
            } else
            {
                return super.onTransact(i, parcel, parcel1, j);
            }
        }

        volatile AssistStructure mAssistStructure;

        SendChannel(AssistStructure assiststructure)
        {
            mAssistStructure = assiststructure;
        }
    }

    public static class ViewNode
    {

        public float getAlpha()
        {
            return mAlpha;
        }

        public String[] getAutofillHints()
        {
            return mAutofillHints;
        }

        public AutofillId getAutofillId()
        {
            return mAutofillId;
        }

        public CharSequence[] getAutofillOptions()
        {
            return mAutofillOptions;
        }

        public int getAutofillType()
        {
            return mAutofillType;
        }

        public AutofillValue getAutofillValue()
        {
            return mAutofillValue;
        }

        public ViewNode getChildAt(int i)
        {
            return mChildren[i];
        }

        public int getChildCount()
        {
            int i;
            if(mChildren != null)
                i = mChildren.length;
            else
                i = 0;
            return i;
        }

        public String getClassName()
        {
            return mClassName;
        }

        public CharSequence getContentDescription()
        {
            return mContentDescription;
        }

        public float getElevation()
        {
            return mElevation;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public int getHeight()
        {
            return mHeight;
        }

        public String getHint()
        {
            String s = null;
            if(mText != null)
                s = mText.mHint;
            return s;
        }

        public android.view.ViewStructure.HtmlInfo getHtmlInfo()
        {
            return mHtmlInfo;
        }

        public int getId()
        {
            return mId;
        }

        public String getIdEntry()
        {
            return mIdEntry;
        }

        public String getIdPackage()
        {
            return mIdPackage;
        }

        public String getIdType()
        {
            return mIdType;
        }

        public int getInputType()
        {
            return mInputType;
        }

        public int getLeft()
        {
            return mX;
        }

        public LocaleList getLocaleList()
        {
            return mLocaleList;
        }

        public int getScrollX()
        {
            return mScrollX;
        }

        public int getScrollY()
        {
            return mScrollY;
        }

        public CharSequence getText()
        {
            CharSequence charsequence = null;
            if(mText != null)
                charsequence = mText.mText;
            return charsequence;
        }

        public int getTextBackgroundColor()
        {
            int i;
            if(mText != null)
                i = mText.mTextBackgroundColor;
            else
                i = 1;
            return i;
        }

        public int getTextColor()
        {
            int i;
            if(mText != null)
                i = mText.mTextColor;
            else
                i = 1;
            return i;
        }

        public int[] getTextLineBaselines()
        {
            int ai[] = null;
            if(mText != null)
                ai = mText.mLineBaselines;
            return ai;
        }

        public int[] getTextLineCharOffsets()
        {
            int ai[] = null;
            if(mText != null)
                ai = mText.mLineCharOffsets;
            return ai;
        }

        public int getTextSelectionEnd()
        {
            int i;
            if(mText != null)
                i = mText.mTextSelectionEnd;
            else
                i = -1;
            return i;
        }

        public int getTextSelectionStart()
        {
            int i;
            if(mText != null)
                i = mText.mTextSelectionStart;
            else
                i = -1;
            return i;
        }

        public float getTextSize()
        {
            float f;
            if(mText != null)
                f = mText.mTextSize;
            else
                f = 0.0F;
            return f;
        }

        public int getTextStyle()
        {
            int i;
            if(mText != null)
                i = mText.mTextStyle;
            else
                i = 0;
            return i;
        }

        public int getTop()
        {
            return mY;
        }

        public Matrix getTransformation()
        {
            return mMatrix;
        }

        public int getVisibility()
        {
            return mFlags & 0xc;
        }

        public String getWebDomain()
        {
            return mWebDomain;
        }

        public int getWidth()
        {
            return mWidth;
        }

        public boolean isAccessibilityFocused()
        {
            boolean flag = false;
            if((mFlags & 0x1000) != 0)
                flag = true;
            return flag;
        }

        public boolean isActivated()
        {
            boolean flag = false;
            if((mFlags & 0x2000) != 0)
                flag = true;
            return flag;
        }

        public boolean isAssistBlocked()
        {
            boolean flag = false;
            if((mFlags & 0x80) != 0)
                flag = true;
            return flag;
        }

        public boolean isCheckable()
        {
            boolean flag = false;
            if((mFlags & 0x100) != 0)
                flag = true;
            return flag;
        }

        public boolean isChecked()
        {
            boolean flag = false;
            if((mFlags & 0x200) != 0)
                flag = true;
            return flag;
        }

        public boolean isClickable()
        {
            boolean flag = false;
            if((mFlags & 0x400) != 0)
                flag = true;
            return flag;
        }

        public boolean isContextClickable()
        {
            boolean flag = false;
            if((mFlags & 0x4000) != 0)
                flag = true;
            return flag;
        }

        public boolean isEnabled()
        {
            boolean flag = false;
            if((mFlags & 1) == 0)
                flag = true;
            return flag;
        }

        public boolean isFocusable()
        {
            boolean flag = false;
            if((mFlags & 0x10) != 0)
                flag = true;
            return flag;
        }

        public boolean isFocused()
        {
            boolean flag = false;
            if((mFlags & 0x20) != 0)
                flag = true;
            return flag;
        }

        public boolean isLongClickable()
        {
            boolean flag = false;
            if((mFlags & 0x800) != 0)
                flag = true;
            return flag;
        }

        public boolean isOpaque()
        {
            boolean flag = false;
            if((mFlags & 0x8000) != 0)
                flag = true;
            return flag;
        }

        public boolean isSanitized()
        {
            return mSanitized;
        }

        public boolean isSelected()
        {
            boolean flag = false;
            if((mFlags & 0x40) != 0)
                flag = true;
            return flag;
        }

        public void setAutofillOverlay(AutofillOverlay autofilloverlay)
        {
            mAutofillOverlay = autofilloverlay;
        }

        public void updateAutofillValue(AutofillValue autofillvalue)
        {
            mAutofillValue = autofillvalue;
            if(autofillvalue.isText())
            {
                if(mText == null)
                    mText = new ViewNodeText();
                mText.mText = autofillvalue.getTextValue();
            }
        }

        int writeSelfToParcel(Parcel parcel, PooledStringWriter pooledstringwriter, boolean flag, float af[])
        {
            boolean flag1;
            boolean flag2;
            int i;
            flag1 = true;
            flag2 = true;
            i = mFlags & 0xfffff;
            int j = i;
            if(mId != -1)
                j = i | 0x200000;
            i = j;
            if(mAutofillId != null)
                i = j | 0x80000000;
            if((mX & 0xffff8000) == 0 && (mY & 0xffff8000) == 0) goto _L2; else goto _L1
_L1:
            int k = i | 0x4000000;
_L3:
            int l;
label0:
            {
                if(mScrollX == 0)
                {
                    l = k;
                    if(mScrollY == 0)
                        break label0;
                }
                l = k | 0x8000000;
            }
label1:
            {
                i = l;
                if(mMatrix != null)
                    i = l | 0x40000000;
                k = i;
                if(mElevation != 0.0F)
                    k = i | 0x10000000;
                i = k;
                if(mAlpha != 1.0F)
                    i = k | 0x20000000;
                k = i;
                if(mContentDescription != null)
                    k = i | 0x2000000;
                i = k;
                if(mText != null)
                {
                    k |= 0x1000000;
                    i = k;
                    if(!mText.isSimple())
                        i = k | 0x800000;
                }
                k = i;
                if(mInputType != 0)
                    k = i | 0x40000;
                i = k;
                if(mWebDomain != null)
                    i = k | 0x80000;
                l = i;
                if(mLocaleList != null)
                    l = i | 0x10000;
                k = l;
                if(mExtras != null)
                    k = l | 0x400000;
                i = k;
                if(mChildren != null)
                    i = k | 0x100000;
                pooledstringwriter.writeString(mClassName);
                l = i;
                k = l;
                if((i & 0x80000000) == 0)
                    break label1;
                if(!mSanitized)
                {
                    k = l;
                    if(!(flag ^ true))
                        break label1;
                }
                k = i & 0xfffffdff;
            }
            l = k;
            if(mAutofillOverlay != null)
                if(mAutofillOverlay.focused)
                    l = k | 0x20;
                else
                    l = k & 0xffffffdf;
            parcel.writeInt(l);
            if((0x200000 & i) != 0)
            {
                parcel.writeInt(mId);
                if(mId != 0)
                {
                    pooledstringwriter.writeString(mIdEntry);
                    if(mIdEntry != null)
                    {
                        pooledstringwriter.writeString(mIdType);
                        pooledstringwriter.writeString(mIdPackage);
                    }
                }
            }
            if((i & 0x80000000) != 0)
            {
                boolean flag3;
                if(!mSanitized)
                    flag ^= true;
                else
                    flag = true;
                if(mSanitized)
                    k = 1;
                else
                    k = 0;
                parcel.writeInt(k);
                parcel.writeParcelable(mAutofillId, 0);
                parcel.writeInt(mAutofillType);
                parcel.writeStringArray(mAutofillHints);
                if(flag)
                    pooledstringwriter = mAutofillValue;
                else
                if(mAutofillOverlay != null && mAutofillOverlay.value != null)
                    pooledstringwriter = mAutofillOverlay.value;
                else
                    pooledstringwriter = null;
                parcel.writeParcelable(pooledstringwriter, 0);
                parcel.writeCharSequenceArray(mAutofillOptions);
                if(mHtmlInfo instanceof Parcelable)
                {
                    parcel.writeParcelable((Parcelable)mHtmlInfo, 0);
                    flag2 = flag;
                } else
                {
                    parcel.writeParcelable(null, 0);
                    flag2 = flag;
                }
            }
            if((0x4000000 & i) != 0)
            {
                parcel.writeInt(mX);
                parcel.writeInt(mY);
                parcel.writeInt(mWidth);
                parcel.writeInt(mHeight);
            } else
            {
                parcel.writeInt(mY << 16 | mX);
                parcel.writeInt(mHeight << 16 | mWidth);
            }
            if((0x8000000 & i) != 0)
            {
                parcel.writeInt(mScrollX);
                parcel.writeInt(mScrollY);
            }
            if((0x40000000 & i) != 0)
            {
                mMatrix.getValues(af);
                parcel.writeFloatArray(af);
            }
            if((0x10000000 & i) != 0)
                parcel.writeFloat(mElevation);
            if((0x20000000 & i) != 0)
                parcel.writeFloat(mAlpha);
            if((0x2000000 & i) != 0)
                TextUtils.writeToParcel(mContentDescription, parcel, 0);
            if((0x1000000 & i) != 0)
            {
                pooledstringwriter = mText;
                if((0x800000 & i) == 0)
                    flag = flag1;
                else
                    flag = false;
                pooledstringwriter.writeToParcel(parcel, flag, flag2);
            }
            if((0x40000 & i) != 0)
                parcel.writeInt(mInputType);
            if((0x80000 & i) != 0)
                parcel.writeString(mWebDomain);
            if((i & 0x10000) != 0)
                parcel.writeParcelable(mLocaleList, 0);
            if((0x400000 & i) != 0)
                parcel.writeBundle(mExtras);
            return i;
_L2:
            if((mWidth & 0xffff8000) != 0)
                l = 1;
            else
                l = 0;
            if((mHeight & 0xffff8000) != 0)
                flag3 = true;
            else
                flag3 = false;
            k = i;
            if(!(l | flag3)) goto _L3; else goto _L1
        }

        static final int FLAGS_ACCESSIBILITY_FOCUSED = 4096;
        static final int FLAGS_ACTIVATED = 8192;
        static final int FLAGS_ALL_CONTROL = 0xfff00000;
        static final int FLAGS_ASSIST_BLOCKED = 128;
        static final int FLAGS_CHECKABLE = 256;
        static final int FLAGS_CHECKED = 512;
        static final int FLAGS_CLICKABLE = 1024;
        static final int FLAGS_CONTEXT_CLICKABLE = 16384;
        static final int FLAGS_DISABLED = 1;
        static final int FLAGS_FOCUSABLE = 16;
        static final int FLAGS_FOCUSED = 32;
        static final int FLAGS_HAS_ALPHA = 0x20000000;
        static final int FLAGS_HAS_AUTOFILL_DATA = 0x80000000;
        static final int FLAGS_HAS_CHILDREN = 0x100000;
        static final int FLAGS_HAS_COMPLEX_TEXT = 0x800000;
        static final int FLAGS_HAS_CONTENT_DESCRIPTION = 0x2000000;
        static final int FLAGS_HAS_ELEVATION = 0x10000000;
        static final int FLAGS_HAS_EXTRAS = 0x400000;
        static final int FLAGS_HAS_ID = 0x200000;
        static final int FLAGS_HAS_INPUT_TYPE = 0x40000;
        static final int FLAGS_HAS_LARGE_COORDS = 0x4000000;
        static final int FLAGS_HAS_LOCALE_LIST = 0x10000;
        static final int FLAGS_HAS_MATRIX = 0x40000000;
        static final int FLAGS_HAS_SCROLL = 0x8000000;
        static final int FLAGS_HAS_TEXT = 0x1000000;
        static final int FLAGS_HAS_URL = 0x80000;
        static final int FLAGS_LONG_CLICKABLE = 2048;
        static final int FLAGS_OPAQUE = 32768;
        static final int FLAGS_SELECTED = 64;
        static final int FLAGS_VISIBILITY_MASK = 12;
        public static final int TEXT_COLOR_UNDEFINED = 1;
        public static final int TEXT_STYLE_BOLD = 1;
        public static final int TEXT_STYLE_ITALIC = 2;
        public static final int TEXT_STYLE_STRIKE_THRU = 8;
        public static final int TEXT_STYLE_UNDERLINE = 4;
        float mAlpha;
        String mAutofillHints[];
        AutofillId mAutofillId;
        CharSequence mAutofillOptions[];
        AutofillOverlay mAutofillOverlay;
        int mAutofillType;
        AutofillValue mAutofillValue;
        ViewNode mChildren[];
        String mClassName;
        CharSequence mContentDescription;
        float mElevation;
        Bundle mExtras;
        int mFlags;
        int mHeight;
        android.view.ViewStructure.HtmlInfo mHtmlInfo;
        int mId;
        String mIdEntry;
        String mIdPackage;
        String mIdType;
        int mInputType;
        LocaleList mLocaleList;
        Matrix mMatrix;
        boolean mSanitized;
        int mScrollX;
        int mScrollY;
        ViewNodeText mText;
        String mWebDomain;
        int mWidth;
        int mX;
        int mY;

        ViewNode()
        {
            mId = -1;
            mAutofillType = 0;
            mAlpha = 1.0F;
        }

        ViewNode(ParcelTransferReader parceltransferreader, int i)
        {
            boolean flag = true;
            super();
            mId = -1;
            mAutofillType = 0;
            mAlpha = 1.0F;
            Parcel parcel = parceltransferreader.readParcel(0x22222222, i);
            parceltransferreader.mNumReadViews = parceltransferreader.mNumReadViews + 1;
            PooledStringReader pooledstringreader = parceltransferreader.mStringReader;
            mClassName = pooledstringreader.readString();
            mFlags = parcel.readInt();
            int j = mFlags;
            if((0x200000 & j) != 0)
            {
                mId = parcel.readInt();
                if(mId != 0)
                {
                    mIdEntry = pooledstringreader.readString();
                    if(mIdEntry != null)
                    {
                        mIdType = pooledstringreader.readString();
                        mIdPackage = pooledstringreader.readString();
                    }
                }
            }
            boolean flag1;
            if((0x80000000 & j) != 0)
            {
                Parcelable parcelable;
                int k;
                if(parcel.readInt() == 1)
                    flag1 = true;
                else
                    flag1 = false;
                mSanitized = flag1;
                mAutofillId = (AutofillId)parcel.readParcelable(null);
                mAutofillType = parcel.readInt();
                mAutofillHints = parcel.readStringArray();
                mAutofillValue = (AutofillValue)parcel.readParcelable(null);
                mAutofillOptions = parcel.readCharSequenceArray();
                parcelable = parcel.readParcelable(null);
                if(parcelable instanceof android.view.ViewStructure.HtmlInfo)
                    mHtmlInfo = (android.view.ViewStructure.HtmlInfo)parcelable;
            }
            if((0x4000000 & j) != 0)
            {
                mX = parcel.readInt();
                mY = parcel.readInt();
                mWidth = parcel.readInt();
                mHeight = parcel.readInt();
            } else
            {
                int l = parcel.readInt();
                mX = l & 0x7fff;
                mY = l >> 16 & 0x7fff;
                l = parcel.readInt();
                mWidth = l & 0x7fff;
                mHeight = l >> 16 & 0x7fff;
            }
            if((0x8000000 & j) != 0)
            {
                mScrollX = parcel.readInt();
                mScrollY = parcel.readInt();
            }
            if((0x40000000 & j) != 0)
            {
                mMatrix = new Matrix();
                parcel.readFloatArray(parceltransferreader.mTmpMatrix);
                mMatrix.setValues(parceltransferreader.mTmpMatrix);
            }
            if((0x10000000 & j) != 0)
                mElevation = parcel.readFloat();
            if((0x20000000 & j) != 0)
                mAlpha = parcel.readFloat();
            if((0x2000000 & j) != 0)
                mContentDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            if((0x1000000 & j) != 0)
            {
                if((0x800000 & j) == 0)
                    flag1 = flag;
                else
                    flag1 = false;
                mText = new ViewNodeText(parcel, flag1);
            }
            if((0x40000 & j) != 0)
                mInputType = parcel.readInt();
            if((0x80000 & j) != 0)
                mWebDomain = parcel.readString();
            if((0x10000 & j) != 0)
                mLocaleList = (LocaleList)parcel.readParcelable(null);
            if((0x400000 & j) != 0)
                mExtras = parcel.readBundle();
            if((0x100000 & j) != 0)
            {
                k = parcel.readInt();
                mChildren = new ViewNode[k];
                for(j = 0; j < k; j++)
                    mChildren[j] = new ViewNode(parceltransferreader, i + 1);

            }
        }
    }

    static class ViewNodeBuilder extends ViewStructure
    {

        private final ViewNodeText getNodeText()
        {
            if(mNode.mText != null)
            {
                return mNode.mText;
            } else
            {
                mNode.mText = new ViewNodeText();
                return mNode.mText;
            }
        }

        public int addChildCount(int i)
        {
            if(mNode.mChildren == null)
            {
                setChildCount(i);
                return 0;
            } else
            {
                int j = mNode.mChildren.length;
                ViewNode aviewnode[] = new ViewNode[j + i];
                System.arraycopy(mNode.mChildren, 0, aviewnode, 0, j);
                mNode.mChildren = aviewnode;
                return j;
            }
        }

        public void asyncCommit()
        {
            synchronized(mAssist)
            {
                if(!mAsync)
                {
                    IllegalStateException illegalstateexception = JVM INSTR new #55  <Class IllegalStateException>;
                    StringBuilder stringbuilder1 = JVM INSTR new #57  <Class StringBuilder>;
                    stringbuilder1.StringBuilder();
                    illegalstateexception.IllegalStateException(stringbuilder1.append("Child ").append(this).append(" was not created with ViewStructure.asyncNewChild").toString());
                    throw illegalstateexception;
                }
                break MISSING_BLOCK_LABEL_55;
            }
            if(!mAssist.mPendingAsyncChildren.remove(this))
            {
                IllegalStateException illegalstateexception1 = JVM INSTR new #55  <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #57  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalstateexception1.IllegalStateException(stringbuilder.append("Child ").append(this).append(" already committed").toString());
                throw illegalstateexception1;
            }
            mAssist.notifyAll();
            assiststructure;
            JVM INSTR monitorexit ;
        }

        public ViewStructure asyncNewChild(int i)
        {
            AssistStructure assiststructure = mAssist;
            assiststructure;
            JVM INSTR monitorenter ;
            ViewNodeBuilder viewnodebuilder;
            ViewNode viewnode = JVM INSTR new #29  <Class AssistStructure$ViewNode>;
            viewnode.ViewNode();
            mNode.mChildren[i] = viewnode;
            viewnodebuilder = JVM INSTR new #2   <Class AssistStructure$ViewNodeBuilder>;
            viewnodebuilder.ViewNodeBuilder(mAssist, viewnode, true);
            mAssist.mPendingAsyncChildren.add(viewnodebuilder);
            assiststructure;
            JVM INSTR monitorexit ;
            return viewnodebuilder;
            Exception exception;
            exception;
            throw exception;
        }

        public AutofillId getAutofillId()
        {
            return mNode.mAutofillId;
        }

        public int getChildCount()
        {
            int i;
            if(mNode.mChildren != null)
                i = mNode.mChildren.length;
            else
                i = 0;
            return i;
        }

        public Bundle getExtras()
        {
            if(mNode.mExtras != null)
            {
                return mNode.mExtras;
            } else
            {
                mNode.mExtras = new Bundle();
                return mNode.mExtras;
            }
        }

        public CharSequence getHint()
        {
            String s = null;
            if(mNode.mText != null)
                s = mNode.mText.mHint;
            return s;
        }

        public Rect getTempRect()
        {
            return mAssist.mTmpRect;
        }

        public CharSequence getText()
        {
            CharSequence charsequence = null;
            if(mNode.mText != null)
                charsequence = mNode.mText.mText;
            return charsequence;
        }

        public int getTextSelectionEnd()
        {
            int i;
            if(mNode.mText != null)
                i = mNode.mText.mTextSelectionEnd;
            else
                i = -1;
            return i;
        }

        public int getTextSelectionStart()
        {
            int i;
            if(mNode.mText != null)
                i = mNode.mText.mTextSelectionStart;
            else
                i = -1;
            return i;
        }

        public boolean hasExtras()
        {
            boolean flag;
            if(mNode.mExtras != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public ViewStructure newChild(int i)
        {
            ViewNode viewnode = new ViewNode();
            mNode.mChildren[i] = viewnode;
            return new ViewNodeBuilder(mAssist, viewnode, false);
        }

        public android.view.ViewStructure.HtmlInfo.Builder newHtmlInfoBuilder(String s)
        {
            return new HtmlInfoNodeBuilder(s);
        }

        public void setAccessibilityFocused(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            char c;
            if(flag)
                c = '\u1000';
            else
                c = '\0';
            viewnode.mFlags = c | i & 0xffffefff;
        }

        public void setActivated(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            char c;
            if(flag)
                c = '\u2000';
            else
                c = '\0';
            viewnode.mFlags = c | i & 0xffffdfff;
        }

        public void setAlpha(float f)
        {
            mNode.mAlpha = f;
        }

        public void setAssistBlocked(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            char c;
            if(flag)
                c = '\200';
            else
                c = '\0';
            viewnode.mFlags = c | i & 0xffffff7f;
        }

        public void setAutofillHints(String as[])
        {
            mNode.mAutofillHints = as;
        }

        public void setAutofillId(AutofillId autofillid)
        {
            mNode.mAutofillId = autofillid;
        }

        public void setAutofillId(AutofillId autofillid, int i)
        {
            mNode.mAutofillId = new AutofillId(autofillid, i);
        }

        public void setAutofillOptions(CharSequence acharsequence[])
        {
            mNode.mAutofillOptions = acharsequence;
        }

        public void setAutofillType(int i)
        {
            mNode.mAutofillType = i;
        }

        public void setAutofillValue(AutofillValue autofillvalue)
        {
            mNode.mAutofillValue = autofillvalue;
        }

        public void setCheckable(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            char c;
            if(flag)
                c = '\u0100';
            else
                c = '\0';
            viewnode.mFlags = c | i & 0xfffffeff;
        }

        public void setChecked(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            char c;
            if(flag)
                c = '\u0200';
            else
                c = '\0';
            viewnode.mFlags = c | i & 0xfffffdff;
        }

        public void setChildCount(int i)
        {
            mNode.mChildren = new ViewNode[i];
        }

        public void setClassName(String s)
        {
            mNode.mClassName = s;
        }

        public void setClickable(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            char c;
            if(flag)
                c = '\u0400';
            else
                c = '\0';
            viewnode.mFlags = c | i & 0xfffffbff;
        }

        public void setContentDescription(CharSequence charsequence)
        {
            mNode.mContentDescription = charsequence;
        }

        public void setContextClickable(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            char c;
            if(flag)
                c = '\u4000';
            else
                c = '\0';
            viewnode.mFlags = c | i & 0xffffbfff;
        }

        public void setDataIsSensitive(boolean flag)
        {
            mNode.mSanitized = flag ^ true;
        }

        public void setDimens(int i, int j, int k, int l, int i1, int j1)
        {
            mNode.mX = i;
            mNode.mY = j;
            mNode.mScrollX = k;
            mNode.mScrollY = l;
            mNode.mWidth = i1;
            mNode.mHeight = j1;
        }

        public void setElevation(float f)
        {
            mNode.mElevation = f;
        }

        public void setEnabled(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            boolean flag1;
            if(flag)
                flag1 = false;
            else
                flag1 = true;
            viewnode.mFlags = flag1 | i & -2;
        }

        public void setFocusable(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            byte byte0;
            if(flag)
                byte0 = 16;
            else
                byte0 = 0;
            viewnode.mFlags = byte0 | i & 0xffffffef;
        }

        public void setFocused(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            byte byte0;
            if(flag)
                byte0 = 32;
            else
                byte0 = 0;
            viewnode.mFlags = byte0 | i & 0xffffffdf;
        }

        public void setHint(CharSequence charsequence)
        {
            String s = null;
            ViewNodeText viewnodetext = getNodeText();
            if(charsequence != null)
                s = charsequence.toString();
            viewnodetext.mHint = s;
        }

        public void setHtmlInfo(android.view.ViewStructure.HtmlInfo htmlinfo)
        {
            mNode.mHtmlInfo = htmlinfo;
        }

        public void setId(int i, String s, String s1, String s2)
        {
            mNode.mId = i;
            mNode.mIdPackage = s;
            mNode.mIdType = s1;
            mNode.mIdEntry = s2;
        }

        public void setInputType(int i)
        {
            mNode.mInputType = i;
        }

        public void setLocaleList(LocaleList localelist)
        {
            mNode.mLocaleList = localelist;
        }

        public void setLongClickable(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            char c;
            if(flag)
                c = '\u0800';
            else
                c = '\0';
            viewnode.mFlags = c | i & 0xfffff7ff;
        }

        public void setOpaque(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            int j;
            if(flag)
                j = 32768;
            else
                j = 0;
            viewnode.mFlags = j | 0xffff7fff & i;
        }

        public void setSelected(boolean flag)
        {
            ViewNode viewnode = mNode;
            int i = mNode.mFlags;
            byte byte0;
            if(flag)
                byte0 = 64;
            else
                byte0 = 0;
            viewnode.mFlags = byte0 | i & 0xffffffbf;
        }

        public void setText(CharSequence charsequence)
        {
            ViewNodeText viewnodetext = getNodeText();
            viewnodetext.mText = TextUtils.trimNoCopySpans(charsequence);
            viewnodetext.mTextSelectionEnd = -1;
            viewnodetext.mTextSelectionStart = -1;
        }

        public void setText(CharSequence charsequence, int i, int j)
        {
            ViewNodeText viewnodetext = getNodeText();
            viewnodetext.mText = TextUtils.trimNoCopySpans(charsequence);
            viewnodetext.mTextSelectionStart = i;
            viewnodetext.mTextSelectionEnd = j;
        }

        public void setTextLines(int ai[], int ai1[])
        {
            ViewNodeText viewnodetext = getNodeText();
            viewnodetext.mLineCharOffsets = ai;
            viewnodetext.mLineBaselines = ai1;
        }

        public void setTextStyle(float f, int i, int j, int k)
        {
            ViewNodeText viewnodetext = getNodeText();
            viewnodetext.mTextColor = i;
            viewnodetext.mTextBackgroundColor = j;
            viewnodetext.mTextSize = f;
            viewnodetext.mTextStyle = k;
        }

        public void setTransformation(Matrix matrix)
        {
            if(matrix == null)
                mNode.mMatrix = null;
            else
                mNode.mMatrix = new Matrix(matrix);
        }

        public void setVisibility(int i)
        {
            mNode.mFlags = mNode.mFlags & 0xfffffff3 | i;
        }

        public void setWebDomain(String s)
        {
            if(s == null)
            {
                mNode.mWebDomain = null;
                return;
            } else
            {
                mNode.mWebDomain = Uri.parse(s).getHost();
                return;
            }
        }

        final AssistStructure mAssist;
        final boolean mAsync;
        final ViewNode mNode;

        ViewNodeBuilder(AssistStructure assiststructure, ViewNode viewnode, boolean flag)
        {
            mAssist = assiststructure;
            mNode = viewnode;
            mAsync = flag;
        }
    }

    static final class ViewNodeText
    {

        boolean isSimple()
        {
            boolean flag = true;
            if(mTextBackgroundColor == 1 && mTextSelectionStart == 0 && mTextSelectionEnd == 0 && mLineCharOffsets == null && mLineBaselines == null)
            {
                if(mHint != null)
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        void writeToParcel(Parcel parcel, boolean flag, boolean flag1)
        {
            Object obj;
            if(flag1)
                obj = mText;
            else
                obj = "";
            TextUtils.writeToParcel(((CharSequence) (obj)), parcel, 0);
            parcel.writeFloat(mTextSize);
            parcel.writeInt(mTextStyle);
            parcel.writeInt(mTextColor);
            if(!flag)
            {
                parcel.writeInt(mTextBackgroundColor);
                parcel.writeInt(mTextSelectionStart);
                parcel.writeInt(mTextSelectionEnd);
                parcel.writeIntArray(mLineCharOffsets);
                parcel.writeIntArray(mLineBaselines);
                parcel.writeString(mHint);
            }
        }

        String mHint;
        int mLineBaselines[];
        int mLineCharOffsets[];
        CharSequence mText;
        int mTextBackgroundColor;
        int mTextColor;
        int mTextSelectionEnd;
        int mTextSelectionStart;
        float mTextSize;
        int mTextStyle;

        ViewNodeText()
        {
            mTextColor = 1;
            mTextBackgroundColor = 1;
        }

        ViewNodeText(Parcel parcel, boolean flag)
        {
            mTextColor = 1;
            mTextBackgroundColor = 1;
            mText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            mTextSize = parcel.readFloat();
            mTextStyle = parcel.readInt();
            mTextColor = parcel.readInt();
            if(!flag)
            {
                mTextBackgroundColor = parcel.readInt();
                mTextSelectionStart = parcel.readInt();
                mTextSelectionEnd = parcel.readInt();
                mLineCharOffsets = parcel.createIntArray();
                mLineBaselines = parcel.createIntArray();
                mHint = parcel.readString();
            }
        }
    }

    static final class ViewStackEntry
    {

        int curChild;
        ViewNode node;
        int numChildren;

        ViewStackEntry()
        {
        }
    }

    public static class WindowNode
    {

        public int getDisplayId()
        {
            return mDisplayId;
        }

        public int getHeight()
        {
            return mHeight;
        }

        public int getLeft()
        {
            return mX;
        }

        public ViewNode getRootViewNode()
        {
            return mRoot;
        }

        public CharSequence getTitle()
        {
            return mTitle;
        }

        public int getTop()
        {
            return mY;
        }

        public int getWidth()
        {
            return mWidth;
        }

        void writeSelfToParcel(Parcel parcel, PooledStringWriter pooledstringwriter, float af[])
        {
            parcel.writeInt(mX);
            parcel.writeInt(mY);
            parcel.writeInt(mWidth);
            parcel.writeInt(mHeight);
            TextUtils.writeToParcel(mTitle, parcel, 0);
            parcel.writeInt(mDisplayId);
        }

        final int mDisplayId;
        final int mHeight;
        final ViewNode mRoot;
        final CharSequence mTitle;
        final int mWidth;
        final int mX;
        final int mY;

        WindowNode(ParcelTransferReader parceltransferreader)
        {
            Parcel parcel = parceltransferreader.readParcel(0x11111111, 0);
            parceltransferreader.mNumReadWindows = parceltransferreader.mNumReadWindows + 1;
            mX = parcel.readInt();
            mY = parcel.readInt();
            mWidth = parcel.readInt();
            mHeight = parcel.readInt();
            mTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            mDisplayId = parcel.readInt();
            mRoot = new ViewNode(parceltransferreader, 0);
        }

        WindowNode(AssistStructure assiststructure, ViewRootImpl viewrootimpl, boolean flag, int i)
        {
label0:
            {
                super();
                View view = viewrootimpl.getView();
                Rect rect = new Rect();
                view.getBoundsOnScreen(rect);
                mX = rect.left - view.getLeft();
                mY = rect.top - view.getTop();
                mWidth = rect.width();
                mHeight = rect.height();
                mTitle = viewrootimpl.getTitle();
                mDisplayId = viewrootimpl.getDisplayId();
                mRoot = new ViewNode();
                assiststructure = new ViewNodeBuilder(assiststructure, mRoot, false);
                if((viewrootimpl.getWindowFlags() & 0x2000) != 0)
                {
                    if(!flag)
                        break label0;
                    int j;
                    if((i & 1) != 0)
                        j = 1;
                    else
                        j = 0;
                    view.onProvideAutofillStructure(assiststructure, j);
                }
                if(flag)
                {
                    if((i & 1) != 0)
                        i = 1;
                    else
                        i = 0;
                    view.dispatchProvideAutofillStructure(assiststructure, i);
                } else
                {
                    view.dispatchProvideStructure(assiststructure);
                }
                return;
            }
            view.onProvideStructure(assiststructure);
            assiststructure.setAssistBlocked(true);
        }
    }


    static long _2D_get0(AssistStructure assiststructure)
    {
        return assiststructure.mAcquisitionEndTime;
    }

    static long _2D_get1(AssistStructure assiststructure)
    {
        return assiststructure.mAcquisitionStartTime;
    }

    static int _2D_get2(AssistStructure assiststructure)
    {
        return assiststructure.mFlags;
    }

    static long _2D_set0(AssistStructure assiststructure, long l)
    {
        assiststructure.mAcquisitionEndTime = l;
        return l;
    }

    static long _2D_set1(AssistStructure assiststructure, long l)
    {
        assiststructure.mAcquisitionStartTime = l;
        return l;
    }

    static int _2D_set2(AssistStructure assiststructure, int i)
    {
        assiststructure.mFlags = i;
        return i;
    }

    public AssistStructure()
    {
        mWindowNodes = new ArrayList();
        mPendingAsyncChildren = new ArrayList();
        mTmpRect = new Rect();
        mSanitizeOnWrite = false;
        mHaveData = true;
        mActivityComponent = null;
        mFlags = 0;
    }

    public AssistStructure(Activity activity, boolean flag, int i)
    {
        mWindowNodes = new ArrayList();
        mPendingAsyncChildren = new ArrayList();
        mTmpRect = new Rect();
        mSanitizeOnWrite = false;
        mHaveData = true;
        mActivityComponent = activity.getComponentName();
        mFlags = i;
        activity = WindowManagerGlobal.getInstance().getRootViews(activity.getActivityToken());
        int j = 0;
        while(j < activity.size()) 
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)activity.get(j);
            if(viewrootimpl.getView() == null)
                Log.w("AssistStructure", (new StringBuilder()).append("Skipping window with dettached view: ").append(viewrootimpl.getTitle()).toString());
            else
                mWindowNodes.add(new WindowNode(this, viewrootimpl, flag, i));
            j++;
        }
    }

    public AssistStructure(Parcel parcel)
    {
        boolean flag = true;
        super();
        mWindowNodes = new ArrayList();
        mPendingAsyncChildren = new ArrayList();
        mTmpRect = new Rect();
        mSanitizeOnWrite = false;
        if(parcel.readInt() != 1)
            flag = false;
        mIsHomeActivity = flag;
        mReceiveChannel = parcel.readStrongBinder();
    }

    public void clearSendChannel()
    {
        if(mSendChannel != null)
            mSendChannel.mAssistStructure = null;
    }

    public int describeContents()
    {
        return 0;
    }

    void dump(String s, ViewNode viewnode, boolean flag)
    {
        Log.i("AssistStructure", (new StringBuilder()).append(s).append("View [").append(viewnode.getLeft()).append(",").append(viewnode.getTop()).append(" ").append(viewnode.getWidth()).append("x").append(viewnode.getHeight()).append("]").append(" ").append(viewnode.getClassName()).toString());
        int i = viewnode.getId();
        if(i != 0)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(s);
            stringbuilder.append("  ID: #");
            stringbuilder.append(Integer.toHexString(i));
            String s1 = viewnode.getIdEntry();
            if(s1 != null)
            {
                String s2 = viewnode.getIdType();
                String s3 = viewnode.getIdPackage();
                stringbuilder.append(" ");
                stringbuilder.append(s3);
                stringbuilder.append(":");
                stringbuilder.append(s2);
                stringbuilder.append("/");
                stringbuilder.append(s1);
            }
            Log.i("AssistStructure", stringbuilder.toString());
        }
        i = viewnode.getScrollX();
        int k = viewnode.getScrollY();
        if(i != 0 || k != 0)
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Scroll: ").append(i).append(",").append(k).toString());
        Object obj = viewnode.getTransformation();
        if(obj != null)
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Transformation: ").append(obj).toString());
        float f = viewnode.getElevation();
        if(f != 0.0F)
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Elevation: ").append(f).toString());
        if(viewnode.getAlpha() != 0.0F)
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Alpha: ").append(f).toString());
        obj = viewnode.getContentDescription();
        if(obj != null)
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Content description: ").append(((CharSequence) (obj))).toString());
        obj = viewnode.getText();
        if(obj != null)
        {
            int j;
            int l;
            if(viewnode.isSanitized() || flag)
                obj = ((CharSequence) (obj)).toString();
            else
                obj = (new StringBuilder()).append("REDACTED[").append(((CharSequence) (obj)).length()).append(" chars]").toString();
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Text (sel ").append(viewnode.getTextSelectionStart()).append("-").append(viewnode.getTextSelectionEnd()).append("): ").append(((String) (obj))).toString());
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Text size: ").append(viewnode.getTextSize()).append(" , style: #").append(viewnode.getTextStyle()).toString());
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Text color fg: #").append(Integer.toHexString(viewnode.getTextColor())).append(", bg: #").append(Integer.toHexString(viewnode.getTextBackgroundColor())).toString());
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Input type: ").append(viewnode.getInputType()).toString());
        }
        obj = viewnode.getWebDomain();
        if(obj != null)
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Web domain: ").append(((String) (obj))).toString());
        obj = viewnode.getHtmlInfo();
        if(obj != null)
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  HtmlInfo: tag=").append(((android.view.ViewStructure.HtmlInfo) (obj)).getTag()).append(", attr=").append(((android.view.ViewStructure.HtmlInfo) (obj)).getAttributes()).toString());
        obj = viewnode.getLocaleList();
        if(obj != null)
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  LocaleList: ").append(obj).toString());
        obj = viewnode.getHint();
        if(obj != null)
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Hint: ").append(((String) (obj))).toString());
        obj = viewnode.getExtras();
        if(obj != null)
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Extras: ").append(obj).toString());
        if(viewnode.isAssistBlocked())
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  BLOCKED").toString());
        obj = viewnode.getAutofillId();
        if(obj == null)
            Log.i("AssistStructure", (new StringBuilder()).append(s).append(" NO autofill ID").toString());
        else
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("Autofill info: id= ").append(obj).append(", type=").append(viewnode.getAutofillType()).append(", options=").append(Arrays.toString(viewnode.getAutofillOptions())).append(", hints=").append(Arrays.toString(viewnode.getAutofillHints())).append(", value=").append(viewnode.getAutofillValue()).append(", sanitized=").append(viewnode.isSanitized()).toString());
        l = viewnode.getChildCount();
        if(l > 0)
        {
            Log.i("AssistStructure", (new StringBuilder()).append(s).append("  Children:").toString());
            s = (new StringBuilder()).append(s).append("    ").toString();
            for(j = 0; j < l; j++)
                dump(s, viewnode.getChildAt(j), flag);

        }
    }

    public void dump(boolean flag)
    {
        if(mActivityComponent == null)
        {
            Log.i("AssistStructure", "dump(): calling ensureData() first");
            ensureData();
        }
        Log.i("AssistStructure", (new StringBuilder()).append("Activity: ").append(mActivityComponent.flattenToShortString()).toString());
        Log.i("AssistStructure", (new StringBuilder()).append("Sanitize on write: ").append(mSanitizeOnWrite).toString());
        Log.i("AssistStructure", (new StringBuilder()).append("Flags: ").append(mFlags).toString());
        int i = getWindowNodeCount();
        for(int j = 0; j < i; j++)
        {
            WindowNode windownode = getWindowNodeAt(j);
            Log.i("AssistStructure", (new StringBuilder()).append("Window #").append(j).append(" [").append(windownode.getLeft()).append(",").append(windownode.getTop()).append(" ").append(windownode.getWidth()).append("x").append(windownode.getHeight()).append("]").append(" ").append(windownode.getTitle()).toString());
            dump("  ", windownode.getRootViewNode(), flag);
        }

    }

    public void ensureData()
    {
        if(mHaveData)
        {
            return;
        } else
        {
            mHaveData = true;
            (new ParcelTransferReader(mReceiveChannel)).go();
            return;
        }
    }

    public long getAcquisitionEndTime()
    {
        ensureData();
        return mAcquisitionEndTime;
    }

    public long getAcquisitionStartTime()
    {
        ensureData();
        return mAcquisitionStartTime;
    }

    public ComponentName getActivityComponent()
    {
        ensureData();
        return mActivityComponent;
    }

    public int getFlags()
    {
        return mFlags;
    }

    public WindowNode getWindowNodeAt(int i)
    {
        ensureData();
        return (WindowNode)mWindowNodes.get(i);
    }

    public int getWindowNodeCount()
    {
        ensureData();
        return mWindowNodes.size();
    }

    public boolean isHomeActivity()
    {
        return mIsHomeActivity;
    }

    public void sanitizeForParceling(boolean flag)
    {
        mSanitizeOnWrite = flag;
    }

    public void setAcquisitionEndTime(long l)
    {
        mAcquisitionEndTime = l;
    }

    public void setAcquisitionStartTime(long l)
    {
        mAcquisitionStartTime = l;
    }

    public void setActivityComponent(ComponentName componentname)
    {
        ensureData();
        mActivityComponent = componentname;
    }

    public void setHomeActivity(boolean flag)
    {
        mIsHomeActivity = flag;
    }

    boolean waitForReady()
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        long l = SystemClock.uptimeMillis() + 5000L;
_L1:
        long l1;
        if(mPendingAsyncChildren.size() <= 0)
            break MISSING_BLOCK_LABEL_50;
        l1 = SystemClock.uptimeMillis();
        if(l1 >= l)
            break MISSING_BLOCK_LABEL_50;
        try
        {
            wait(l - l1);
        }
        catch(InterruptedException interruptedexception) { }
          goto _L1
        if(mPendingAsyncChildren.size() <= 0)
            break MISSING_BLOCK_LABEL_105;
        StringBuilder stringbuilder = JVM INSTR new #165 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("AssistStructure", stringbuilder.append("Skipping assist structure, waiting too long for async children (have ").append(mPendingAsyncChildren.size()).append(" remaining").toString());
        flag = true;
        this;
        JVM INSTR monitorexit ;
        return flag ^ true;
        Exception exception;
        exception;
        throw exception;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mIsHomeActivity)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mHaveData)
        {
            if(mSendChannel == null)
                mSendChannel = new SendChannel(this);
            parcel.writeStrongBinder(mSendChannel);
        } else
        {
            parcel.writeStrongBinder(mReceiveChannel);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AssistStructure createFromParcel(Parcel parcel)
        {
            return new AssistStructure(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AssistStructure[] newArray(int i)
        {
            return new AssistStructure[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final boolean DEBUG_PARCEL = false;
    static final boolean DEBUG_PARCEL_CHILDREN = false;
    static final boolean DEBUG_PARCEL_TREE = false;
    static final String DESCRIPTOR = "android.app.AssistStructure";
    static final String TAG = "AssistStructure";
    static final int TRANSACTION_XFER = 2;
    static final int VALIDATE_VIEW_TOKEN = 0x22222222;
    static final int VALIDATE_WINDOW_TOKEN = 0x11111111;
    private long mAcquisitionEndTime;
    private long mAcquisitionStartTime;
    ComponentName mActivityComponent;
    private int mFlags;
    boolean mHaveData;
    private boolean mIsHomeActivity;
    final ArrayList mPendingAsyncChildren;
    IBinder mReceiveChannel;
    boolean mSanitizeOnWrite;
    SendChannel mSendChannel;
    Rect mTmpRect;
    final ArrayList mWindowNodes;

}
