// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.media.PlayerBase;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.widget.RemoteViews;
import com.android.internal.util.*;
import java.lang.reflect.Constructor;
import java.util.*;

// Referenced classes of package android.app:
//            MiuiNotification, PendingIntent, ActivityManager, RemoteInput

public class Notification
    implements Parcelable
{
    public static class Action
        implements Parcelable
    {

        static Bundle _2D_get0(Action action)
        {
            return action.mExtras;
        }

        static RemoteInput[] _2D_get1(Action action)
        {
            return action.mRemoteInputs;
        }

        public Action clone()
        {
            Icon icon1 = getIcon();
            CharSequence charsequence = title;
            PendingIntent pendingintent = actionIntent;
            Bundle bundle;
            if(mExtras == null)
                bundle = new Bundle();
            else
                bundle = new Bundle(mExtras);
            return new Action(icon1, charsequence, pendingintent, bundle, getRemoteInputs(), getAllowGeneratedReplies());
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean getAllowGeneratedReplies()
        {
            return mAllowGeneratedReplies;
        }

        public RemoteInput[] getDataOnlyRemoteInputs()
        {
            return (RemoteInput[])mExtras.getParcelableArray("android.extra.DATA_ONLY_INPUTS");
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public Icon getIcon()
        {
            if(mIcon == null && icon != 0)
                mIcon = Icon.createWithResource("", icon);
            return mIcon;
        }

        public RemoteInput[] getRemoteInputs()
        {
            return mRemoteInputs;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            Icon icon1 = getIcon();
            if(icon1 != null)
            {
                parcel.writeInt(1);
                icon1.writeToParcel(parcel, 0);
            } else
            {
                parcel.writeInt(0);
            }
            TextUtils.writeToParcel(title, parcel, i);
            if(actionIntent != null)
            {
                parcel.writeInt(1);
                actionIntent.writeToParcel(parcel, i);
            } else
            {
                parcel.writeInt(0);
            }
            parcel.writeBundle(mExtras);
            parcel.writeTypedArray(mRemoteInputs, i);
            if(mAllowGeneratedReplies)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Action createFromParcel(Parcel parcel)
            {
                return new Action(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Action[] newArray(int i)
            {
                return new Action[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String EXTRA_DATA_ONLY_INPUTS = "android.extra.DATA_ONLY_INPUTS";
        public PendingIntent actionIntent;
        public int icon;
        private boolean mAllowGeneratedReplies;
        private final Bundle mExtras;
        private Icon mIcon;
        private final RemoteInput mRemoteInputs[];
        public CharSequence title;


        public Action(int i, CharSequence charsequence, PendingIntent pendingintent)
        {
            this(Icon.createWithResource("", i), charsequence, pendingintent, new Bundle(), null, true);
        }

        private Action(Icon icon1, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInput aremoteinput[], boolean flag)
        {
            mAllowGeneratedReplies = true;
            mIcon = icon1;
            if(icon1 != null && icon1.getType() == 2)
                icon = icon1.getResId();
            title = charsequence;
            actionIntent = pendingintent;
            if(bundle == null)
                bundle = new Bundle();
            mExtras = bundle;
            mRemoteInputs = aremoteinput;
            mAllowGeneratedReplies = flag;
        }

        Action(Icon icon1, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInput aremoteinput[], boolean flag, Action action)
        {
            this(icon1, charsequence, pendingintent, bundle, aremoteinput, flag);
        }

        private Action(Parcel parcel)
        {
            mAllowGeneratedReplies = true;
            if(parcel.readInt() != 0)
            {
                mIcon = (Icon)Icon.CREATOR.createFromParcel(parcel);
                if(mIcon.getType() == 2)
                    icon = mIcon.getResId();
            }
            title = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            if(parcel.readInt() == 1)
                actionIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            mExtras = Bundle.setDefusable(parcel.readBundle(), true);
            mRemoteInputs = (RemoteInput[])parcel.createTypedArray(RemoteInput.CREATOR);
            boolean flag;
            if(parcel.readInt() == 1)
                flag = true;
            else
                flag = false;
            mAllowGeneratedReplies = flag;
        }

        Action(Parcel parcel, Action action)
        {
            this(parcel);
        }
    }

    public static final class Action.Builder
    {

        public Action.Builder addExtras(Bundle bundle)
        {
            if(bundle != null)
                mExtras.putAll(bundle);
            return this;
        }

        public Action.Builder addRemoteInput(RemoteInput remoteinput)
        {
            if(mRemoteInputs == null)
                mRemoteInputs = new ArrayList();
            mRemoteInputs.add(remoteinput);
            return this;
        }

        public Action build()
        {
            ArrayList arraylist = new ArrayList();
            RemoteInput aremoteinput1[] = (RemoteInput[])mExtras.getParcelableArray("android.extra.DATA_ONLY_INPUTS");
            if(aremoteinput1 != null)
            {
                int i = 0;
                for(int j = aremoteinput1.length; i < j; i++)
                    arraylist.add(aremoteinput1[i]);

            }
            aremoteinput1 = new ArrayList();
            if(mRemoteInputs != null)
            {
                for(Iterator iterator = mRemoteInputs.iterator(); iterator.hasNext();)
                {
                    RemoteInput remoteinput = (RemoteInput)iterator.next();
                    if(remoteinput.isDataOnly())
                        arraylist.add(remoteinput);
                    else
                        aremoteinput1.add(remoteinput);
                }

            }
            if(!arraylist.isEmpty())
            {
                RemoteInput aremoteinput[] = (RemoteInput[])arraylist.toArray(new RemoteInput[arraylist.size()]);
                mExtras.putParcelableArray("android.extra.DATA_ONLY_INPUTS", aremoteinput);
            }
            if(aremoteinput1.isEmpty())
                aremoteinput1 = null;
            else
                aremoteinput1 = (RemoteInput[])aremoteinput1.toArray(new RemoteInput[aremoteinput1.size()]);
            return new Action(mIcon, mTitle, mIntent, mExtras, aremoteinput1, mAllowGeneratedReplies, null);
        }

        public Action.Builder extend(Action.Extender extender)
        {
            extender.extend(this);
            return this;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public Action.Builder setAllowGeneratedReplies(boolean flag)
        {
            mAllowGeneratedReplies = flag;
            return this;
        }

        private boolean mAllowGeneratedReplies;
        private final Bundle mExtras;
        private final Icon mIcon;
        private final PendingIntent mIntent;
        private ArrayList mRemoteInputs;
        private final CharSequence mTitle;

        public Action.Builder(int i, CharSequence charsequence, PendingIntent pendingintent)
        {
            this(Icon.createWithResource("", i), charsequence, pendingintent);
        }

        public Action.Builder(Action action)
        {
            this(action.getIcon(), action.title, action.actionIntent, new Bundle(Action._2D_get0(action)), action.getRemoteInputs(), action.getAllowGeneratedReplies());
        }

        public Action.Builder(Icon icon1, CharSequence charsequence, PendingIntent pendingintent)
        {
            this(icon1, charsequence, pendingintent, new Bundle(), null, true);
        }

        private Action.Builder(Icon icon1, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInput aremoteinput[], boolean flag)
        {
            mAllowGeneratedReplies = true;
            mIcon = icon1;
            mTitle = charsequence;
            mIntent = pendingintent;
            mExtras = bundle;
            if(aremoteinput != null)
            {
                mRemoteInputs = new ArrayList(aremoteinput.length);
                Collections.addAll(mRemoteInputs, aremoteinput);
            }
            mAllowGeneratedReplies = flag;
        }
    }

    public static interface Action.Extender
    {

        public abstract Action.Builder extend(Action.Builder builder);
    }

    public static final class Action.WearableExtender
        implements Action.Extender
    {

        private void setFlag(int i, boolean flag)
        {
            if(flag)
                mFlags = mFlags | i;
            else
                mFlags = mFlags & i;
        }

        public Action.WearableExtender clone()
        {
            Action.WearableExtender wearableextender = new Action.WearableExtender();
            wearableextender.mFlags = mFlags;
            wearableextender.mInProgressLabel = mInProgressLabel;
            wearableextender.mConfirmLabel = mConfirmLabel;
            wearableextender.mCancelLabel = mCancelLabel;
            return wearableextender;
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public Action.Builder extend(Action.Builder builder)
        {
            Bundle bundle = new Bundle();
            if(mFlags != 1)
                bundle.putInt("flags", mFlags);
            if(mInProgressLabel != null)
                bundle.putCharSequence("inProgressLabel", mInProgressLabel);
            if(mConfirmLabel != null)
                bundle.putCharSequence("confirmLabel", mConfirmLabel);
            if(mCancelLabel != null)
                bundle.putCharSequence("cancelLabel", mCancelLabel);
            builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
            return builder;
        }

        public CharSequence getCancelLabel()
        {
            return mCancelLabel;
        }

        public CharSequence getConfirmLabel()
        {
            return mConfirmLabel;
        }

        public boolean getHintDisplayActionInline()
        {
            boolean flag = false;
            if((mFlags & 4) != 0)
                flag = true;
            return flag;
        }

        public boolean getHintLaunchesActivity()
        {
            boolean flag = false;
            if((mFlags & 2) != 0)
                flag = true;
            return flag;
        }

        public CharSequence getInProgressLabel()
        {
            return mInProgressLabel;
        }

        public boolean isAvailableOffline()
        {
            boolean flag = false;
            if((mFlags & 1) != 0)
                flag = true;
            return flag;
        }

        public Action.WearableExtender setAvailableOffline(boolean flag)
        {
            setFlag(1, flag);
            return this;
        }

        public Action.WearableExtender setCancelLabel(CharSequence charsequence)
        {
            mCancelLabel = charsequence;
            return this;
        }

        public Action.WearableExtender setConfirmLabel(CharSequence charsequence)
        {
            mConfirmLabel = charsequence;
            return this;
        }

        public Action.WearableExtender setHintDisplayActionInline(boolean flag)
        {
            setFlag(4, flag);
            return this;
        }

        public Action.WearableExtender setHintLaunchesActivity(boolean flag)
        {
            setFlag(2, flag);
            return this;
        }

        public Action.WearableExtender setInProgressLabel(CharSequence charsequence)
        {
            mInProgressLabel = charsequence;
            return this;
        }

        private static final int DEFAULT_FLAGS = 1;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_DISPLAY_INLINE = 4;
        private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
        private static final String KEY_CANCEL_LABEL = "cancelLabel";
        private static final String KEY_CONFIRM_LABEL = "confirmLabel";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
        private CharSequence mCancelLabel;
        private CharSequence mConfirmLabel;
        private int mFlags;
        private CharSequence mInProgressLabel;

        public Action.WearableExtender()
        {
            mFlags = 1;
        }

        public Action.WearableExtender(Action action)
        {
            mFlags = 1;
            action = action.getExtras().getBundle("android.wearable.EXTENSIONS");
            if(action != null)
            {
                mFlags = action.getInt("flags", 1);
                mInProgressLabel = action.getCharSequence("inProgressLabel");
                mConfirmLabel = action.getCharSequence("confirmLabel");
                mCancelLabel = action.getCharSequence("cancelLabel");
            }
        }
    }

    public static class BigPictureStyle extends Style
    {

        public void addExtras(Bundle bundle)
        {
            super.addExtras(bundle);
            if(mBigLargeIconSet)
                bundle.putParcelable("android.largeIcon.big", mBigLargeIcon);
            bundle.putParcelable("android.picture", mPicture);
        }

        public BigPictureStyle bigLargeIcon(Bitmap bitmap)
        {
            Icon icon1 = null;
            if(bitmap != null)
                icon1 = Icon.createWithBitmap(bitmap);
            return bigLargeIcon(icon1);
        }

        public BigPictureStyle bigLargeIcon(Icon icon1)
        {
            mBigLargeIconSet = true;
            mBigLargeIcon = icon1;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap bitmap)
        {
            mPicture = bitmap;
            return this;
        }

        public boolean hasSummaryInHeader()
        {
            return false;
        }

        public RemoteViews makeBigContentView()
        {
            Icon icon1 = null;
            Bitmap bitmap = null;
            if(mBigLargeIconSet)
            {
                icon1 = Notification._2D_get1(Builder._2D_get2(mBuilder));
                Notification._2D_set5(Builder._2D_get2(mBuilder), mBigLargeIcon);
                bitmap = Builder._2D_get2(mBuilder).largeIcon;
                Builder._2D_get2(mBuilder).largeIcon = null;
            }
            RemoteViews remoteviews = getStandardView(Builder._2D_wrap10(mBuilder));
            if(mSummaryTextSet)
            {
                remoteviews.setTextViewText(0x1020430, Builder._2D_wrap17(mBuilder, Builder._2D_wrap16(mBuilder, mSummaryText)));
                Builder._2D_wrap18(mBuilder, remoteviews, 0x1020430);
                remoteviews.setViewVisibility(0x1020430, 0);
            }
            mBuilder.setContentMinHeight(remoteviews, Notification._2D_wrap1(Builder._2D_get2(mBuilder)));
            if(mBigLargeIconSet)
            {
                Notification._2D_set5(Builder._2D_get2(mBuilder), icon1);
                Builder._2D_get2(mBuilder).largeIcon = bitmap;
            }
            remoteviews.setImageViewBitmap(0x10201c5, mPicture);
            return remoteviews;
        }

        public void purgeResources()
        {
            super.purgeResources();
            if(mPicture != null && mPicture.isMutable() && mPicture.getAllocationByteCount() >= 0x20000)
                mPicture = mPicture.createAshmemBitmap();
            if(mBigLargeIcon != null)
                mBigLargeIcon.convertToAshmem();
        }

        public void reduceImageSizes(Context context)
        {
            super.reduceImageSizes(context);
            context = context.getResources();
            boolean flag = ActivityManager.isLowRamDeviceStatic();
            int i;
            if(mPicture != null)
            {
                int j;
                if(flag)
                    i = 0x1050104;
                else
                    i = 0x1050103;
                j = context.getDimensionPixelSize(i);
                if(flag)
                    i = 0x1050106;
                else
                    i = 0x1050105;
                i = context.getDimensionPixelSize(i);
                mPicture = Icon.scaleDownIfNecessary(mPicture, j, i);
            }
            if(mBigLargeIcon != null)
            {
                if(flag)
                    i = 0x1050134;
                else
                    i = 0x1050133;
                i = context.getDimensionPixelSize(i);
                mBigLargeIcon.scaleDownIfNecessary(i, i);
            }
        }

        protected void restoreFromExtras(Bundle bundle)
        {
            super.restoreFromExtras(bundle);
            if(bundle.containsKey("android.largeIcon.big"))
            {
                mBigLargeIconSet = true;
                mBigLargeIcon = (Icon)bundle.getParcelable("android.largeIcon.big");
            }
            mPicture = (Bitmap)bundle.getParcelable("android.picture");
        }

        public BigPictureStyle setBigContentTitle(CharSequence charsequence)
        {
            internalSetBigContentTitle(Notification.safeCharSequence(charsequence));
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence charsequence)
        {
            internalSetSummaryText(Notification.safeCharSequence(charsequence));
            return this;
        }

        public static final int MIN_ASHMEM_BITMAP_SIZE = 0x20000;
        private Icon mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private Bitmap mPicture;

        public BigPictureStyle()
        {
            mBigLargeIconSet = false;
        }

        public BigPictureStyle(Builder builder)
        {
            mBigLargeIconSet = false;
            setBuilder(builder);
        }
    }

    public static class BigTextStyle extends Style
    {

        static void applyBigTextContentView(Builder builder, RemoteViews remoteviews, CharSequence charsequence)
        {
            remoteviews.setTextViewText(0x10201c8, Builder._2D_wrap17(builder, charsequence));
            Builder._2D_wrap18(builder, remoteviews, 0x10201c8);
            byte byte0;
            if(TextUtils.isEmpty(charsequence))
                byte0 = 8;
            else
                byte0 = 0;
            remoteviews.setViewVisibility(0x10201c8, byte0);
            remoteviews.setBoolean(0x10201c8, "setHasImage", Notification._2D_wrap1(Builder._2D_get2(builder)));
        }

        public void addExtras(Bundle bundle)
        {
            super.addExtras(bundle);
            bundle.putCharSequence("android.bigText", mBigText);
        }

        public BigTextStyle bigText(CharSequence charsequence)
        {
            mBigText = Notification.safeCharSequence(charsequence);
            return this;
        }

        public RemoteViews makeBigContentView()
        {
            CharSequence charsequence = Builder._2D_wrap0(mBuilder).getCharSequence("android.text");
            Builder._2D_wrap0(mBuilder).putCharSequence("android.text", null);
            RemoteViews remoteviews = getStandardView(Builder._2D_wrap11(mBuilder));
            Builder._2D_wrap0(mBuilder).putCharSequence("android.text", charsequence);
            CharSequence charsequence1 = Builder._2D_wrap16(mBuilder, mBigText);
            CharSequence charsequence2 = charsequence1;
            if(TextUtils.isEmpty(charsequence1))
                charsequence2 = Builder._2D_wrap16(mBuilder, charsequence);
            applyBigTextContentView(mBuilder, remoteviews, charsequence2);
            return remoteviews;
        }

        public RemoteViews makeContentView(boolean flag)
        {
            if(flag)
            {
                Builder._2D_set1(mBuilder, Builder._2D_get0(mBuilder));
                Builder._2D_set0(mBuilder, new ArrayList());
                RemoteViews remoteviews = makeBigContentView();
                Builder._2D_set0(mBuilder, Builder._2D_get3(mBuilder));
                Builder._2D_set1(mBuilder, null);
                return remoteviews;
            } else
            {
                return super.makeContentView(flag);
            }
        }

        public RemoteViews makeHeadsUpContentView(boolean flag)
        {
            if(flag && Builder._2D_get0(mBuilder).size() > 0)
                return makeBigContentView();
            else
                return super.makeHeadsUpContentView(flag);
        }

        protected void restoreFromExtras(Bundle bundle)
        {
            super.restoreFromExtras(bundle);
            mBigText = bundle.getCharSequence("android.bigText");
        }

        public BigTextStyle setBigContentTitle(CharSequence charsequence)
        {
            internalSetBigContentTitle(Notification.safeCharSequence(charsequence));
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence charsequence)
        {
            internalSetSummaryText(Notification.safeCharSequence(charsequence));
            return this;
        }

        private CharSequence mBigText;

        public BigTextStyle()
        {
        }

        public BigTextStyle(Builder builder)
        {
            setBuilder(builder);
        }
    }

    public static class Builder
    {

        static ArrayList _2D_get0(Builder builder)
        {
            return builder.mActions;
        }

        static Context _2D_get1(Builder builder)
        {
            return builder.mContext;
        }

        static Notification _2D_get2(Builder builder)
        {
            return builder.mN;
        }

        static ArrayList _2D_get3(Builder builder)
        {
            return builder.mOriginalActions;
        }

        static ArrayList _2D_set0(Builder builder, ArrayList arraylist)
        {
            builder.mActions = arraylist;
            return arraylist;
        }

        static ArrayList _2D_set1(Builder builder, ArrayList arraylist)
        {
            builder.mOriginalActions = arraylist;
            return arraylist;
        }

        static Bundle _2D_wrap0(Builder builder)
        {
            return builder.getAllExtras();
        }

        static RemoteViews _2D_wrap1(Builder builder, int i, StandardTemplateParams standardtemplateparams)
        {
            return builder.applyStandardTemplateWithActions(i, standardtemplateparams);
        }

        static int _2D_wrap10(Builder builder)
        {
            return builder.getBigPictureLayoutResource();
        }

        static int _2D_wrap11(Builder builder)
        {
            return builder.getBigTextLayoutResource();
        }

        static int _2D_wrap12(Builder builder)
        {
            return builder.getInboxLayoutResource();
        }

        static int _2D_wrap13(Builder builder)
        {
            return builder.getMessagingLayoutResource();
        }

        static int _2D_wrap14(Builder builder)
        {
            return builder.getPrimaryHighlightColor();
        }

        static CharSequence _2D_wrap15(Builder builder, CharSequence charsequence, boolean flag)
        {
            return builder.processLegacyText(charsequence, flag);
        }

        static CharSequence _2D_wrap16(Builder builder, CharSequence charsequence)
        {
            return builder.processLegacyText(charsequence);
        }

        static CharSequence _2D_wrap17(Builder builder, CharSequence charsequence)
        {
            return builder.processTextSpans(charsequence);
        }

        static void _2D_wrap18(Builder builder, RemoteViews remoteviews, int i)
        {
            builder.setTextViewColorSecondary(remoteviews, i);
        }

        static RemoteViews _2D_wrap2(Builder builder, int i)
        {
            return builder.applyStandardTemplateWithActions(i);
        }

        static RemoteViews _2D_wrap3(Builder builder, int i, StandardTemplateParams standardtemplateparams)
        {
            return builder.applyStandardTemplate(i, standardtemplateparams);
        }

        static RemoteViews _2D_wrap4(Builder builder, int i, boolean flag)
        {
            return builder.applyStandardTemplate(i, flag);
        }

        static RemoteViews _2D_wrap5(Builder builder, int i)
        {
            return builder.applyStandardTemplate(i);
        }

        static boolean _2D_wrap6(Builder builder)
        {
            return builder.isColorized();
        }

        static boolean _2D_wrap7(Builder builder)
        {
            return builder.shouldTintActionButtons();
        }

        static int _2D_wrap8(Builder builder)
        {
            return builder.getBaseLayoutResource();
        }

        static int _2D_wrap9(Builder builder)
        {
            return builder.getBigBaseLayoutResource();
        }

        private RemoteViews applyStandardTemplate(int i)
        {
            return applyStandardTemplate(i, mParams.reset().fillTextsFrom(this));
        }

        private RemoteViews applyStandardTemplate(int i, StandardTemplateParams standardtemplateparams)
        {
            BuilderRemoteViews builderremoteviews = new BuilderRemoteViews(mContext.getApplicationInfo(), i);
            resetStandardTemplate(builderremoteviews);
            Bundle bundle = mN.extras;
            updateBackgroundColor(builderremoteviews);
            bindNotificationHeader(builderremoteviews, standardtemplateparams.ambient);
            bindLargeIcon(builderremoteviews);
            boolean flag = handleProgressBar(standardtemplateparams.hasProgress, builderremoteviews, bundle);
            if(standardtemplateparams.title != null)
            {
                builderremoteviews.setViewVisibility(0x1020016, 0);
                builderremoteviews.setTextViewText(0x1020016, processTextSpans(standardtemplateparams.title));
                if(!standardtemplateparams.ambient)
                    setTextViewColorPrimary(builderremoteviews, 0x1020016);
                if(flag)
                    i = -2;
                else
                    i = -1;
                builderremoteviews.setViewLayoutWidth(0x1020016, i);
            }
            if(standardtemplateparams.text != null)
            {
                if(flag)
                    i = 0x102044b;
                else
                    i = 0x1020430;
                builderremoteviews.setTextViewText(i, processTextSpans(standardtemplateparams.text));
                if(!standardtemplateparams.ambient)
                    setTextViewColorSecondary(builderremoteviews, i);
                builderremoteviews.setViewVisibility(i, 0);
            }
            if(!flag)
                flag = Notification._2D_wrap1(mN);
            else
                flag = true;
            setContentMinHeight(builderremoteviews, flag);
            return builderremoteviews;
        }

        private RemoteViews applyStandardTemplate(int i, boolean flag)
        {
            return applyStandardTemplate(i, mParams.reset().hasProgress(flag).fillTextsFrom(this));
        }

        private RemoteViews applyStandardTemplateWithActions(int i)
        {
            return applyStandardTemplateWithActions(i, mParams.reset().fillTextsFrom(this));
        }

        private RemoteViews applyStandardTemplateWithActions(int i, StandardTemplateParams standardtemplateparams)
        {
            RemoteViews remoteviews = applyStandardTemplate(i, standardtemplateparams);
            resetStandardTemplateWithActions(remoteviews);
            int j = 0;
            i = 0;
            int k = mActions.size();
            boolean flag;
            if(mN.fullScreenIntent != null)
                flag = standardtemplateparams.ambient ^ true;
            else
                flag = false;
            remoteviews.setBoolean(0x1020189, "setEmphasizedMode", flag);
            if(k > 0)
            {
                remoteviews.setViewVisibility(0x102018a, 0);
                remoteviews.setViewVisibility(0x1020189, 0);
                int l;
                if(standardtemplateparams.ambient)
                    remoteviews.setInt(0x1020189, "setBackgroundColor", 0);
                else
                if(isColorized())
                    remoteviews.setInt(0x1020189, "setBackgroundColor", getActionBarColor());
                else
                    remoteviews.setInt(0x1020189, "setBackgroundColor", mContext.getColor(0x10600e7));
                remoteviews.setViewLayoutMarginBottomDimen(0x1020332, 0x1050100);
                l = k;
                if(k > 3)
                    l = 3;
                k = 0;
                do
                {
                    j = i;
                    if(k >= l)
                        break;
                    Action action = (Action)mActions.get(k);
                    i |= hasValidRemoteInput(action);
                    boolean flag1;
                    if(k % 2 != 0)
                        flag1 = true;
                    else
                        flag1 = false;
                    remoteviews.addView(0x1020189, generateActionButton(action, flag, flag1, standardtemplateparams.ambient));
                    k++;
                } while(true);
            } else
            {
                remoteviews.setViewVisibility(0x102018a, 8);
            }
            CharSequence acharsequence[] = mN.extras.getCharSequenceArray("android.remoteInputHistory");
            if(!standardtemplateparams.ambient && j != 0 && acharsequence != null && acharsequence.length > 0 && TextUtils.isEmpty(acharsequence[0]) ^ true)
            {
                remoteviews.setViewVisibility(0x1020336, 0);
                remoteviews.setTextViewText(0x1020337, processTextSpans(acharsequence[0]));
                setTextViewColorSecondary(remoteviews, 0x1020337);
                if(acharsequence.length > 1 && TextUtils.isEmpty(acharsequence[1]) ^ true)
                {
                    remoteviews.setViewVisibility(0x1020338, 0);
                    remoteviews.setTextViewText(0x1020338, processTextSpans(acharsequence[1]));
                    setTextViewColorSecondary(remoteviews, 0x1020338);
                    if(acharsequence.length > 2 && TextUtils.isEmpty(acharsequence[2]) ^ true)
                    {
                        remoteviews.setViewVisibility(0x1020339, 0);
                        remoteviews.setTextViewText(0x1020339, processTextSpans(acharsequence[2]));
                        setTextViewColorSecondary(remoteviews, 0x1020339);
                    }
                }
            }
            return remoteviews;
        }

        private void bindExpandButton(RemoteViews remoteviews)
        {
            int i = getPrimaryHighlightColor();
            remoteviews.setDrawableParameters(0x1020238, false, -1, i, android.graphics.PorterDuff.Mode.SRC_ATOP, -1);
            remoteviews.setInt(0x1020334, "setOriginalNotificationColor", i);
        }

        private void bindHeaderAppName(RemoteViews remoteviews, boolean flag)
        {
            remoteviews.setTextViewText(0x10201ab, loadHeaderAppName());
            if(isColorized() && flag ^ true)
            {
                setTextViewColorPrimary(remoteviews, 0x10201ab);
            } else
            {
                int i;
                if(flag)
                    i = resolveAmbientColor();
                else
                    i = resolveContrastColor();
                remoteviews.setTextColor(0x10201ab, i);
            }
        }

        private void bindHeaderChronometerAndTime(RemoteViews remoteviews)
        {
            if(showsTimeOrChronometer())
            {
                remoteviews.setViewVisibility(0x1020451, 0);
                setTextViewColorSecondary(remoteviews, 0x1020451);
                if(mN.extras.getBoolean("android.showChronometer"))
                {
                    remoteviews.setViewVisibility(0x10201f6, 0);
                    remoteviews.setLong(0x10201f6, "setBase", mN.when + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
                    remoteviews.setBoolean(0x10201f6, "setStarted", true);
                    remoteviews.setChronometerCountDown(0x10201f6, mN.extras.getBoolean("android.chronometerCountDown"));
                    setTextViewColorSecondary(remoteviews, 0x10201f6);
                } else
                {
                    remoteviews.setViewVisibility(0x102044d, 0);
                    remoteviews.setLong(0x102044d, "setTime", mN.when);
                    setTextViewColorSecondary(remoteviews, 0x102044d);
                }
            } else
            {
                long l;
                if(mN.when != 0L)
                    l = mN.when;
                else
                    l = Notification._2D_get0(mN);
                remoteviews.setLong(0x102044d, "setTime", l);
            }
        }

        private void bindHeaderText(RemoteViews remoteviews)
        {
            CharSequence charsequence = mN.extras.getCharSequence("android.subText");
            CharSequence charsequence1 = charsequence;
            if(charsequence == null)
            {
                charsequence1 = charsequence;
                if(mStyle != null)
                {
                    charsequence1 = charsequence;
                    if(mStyle.mSummaryTextSet)
                    {
                        charsequence1 = charsequence;
                        if(mStyle.hasSummaryInHeader())
                            charsequence1 = mStyle.mSummaryText;
                    }
                }
            }
            charsequence = charsequence1;
            if(charsequence1 == null)
            {
                charsequence = charsequence1;
                if(mContext.getApplicationInfo().targetSdkVersion < 24)
                {
                    charsequence = charsequence1;
                    if(mN.extras.getCharSequence("android.infoText") != null)
                        charsequence = mN.extras.getCharSequence("android.infoText");
                }
            }
            if(charsequence != null)
            {
                remoteviews.setTextViewText(0x1020282, processTextSpans(processLegacyText(charsequence)));
                setTextViewColorSecondary(remoteviews, 0x1020282);
                remoteviews.setViewVisibility(0x1020282, 0);
                remoteviews.setViewVisibility(0x1020283, 0);
                setTextViewColorSecondary(remoteviews, 0x1020283);
            }
        }

        private void bindLargeIcon(RemoteViews remoteviews)
        {
            if(Notification._2D_get1(mN) == null && mN.largeIcon != null)
                Notification._2D_set5(mN, Icon.createWithBitmap(mN.largeIcon));
            int i;
            if(Notification._2D_get1(mN) != null)
            {
                remoteviews.setViewVisibility(0x10203b1, 0);
                remoteviews.setImageViewIcon(0x10203b1, Notification._2D_get1(mN));
                processLargeLegacyIcon(Notification._2D_get1(mN), remoteviews);
                remoteviews.setViewLayoutMarginEndDimen(0x10202dc, 0x105010d);
                remoteviews.setViewLayoutMarginEndDimen(0x1020430, 0x105010d);
                remoteviews.setViewLayoutMarginEndDimen(0x102000d, 0x105010d);
                Action action = findReplyAction();
                if(action != null)
                    i = 0;
                else
                    i = 8;
                remoteviews.setViewVisibility(0x10203a7, i);
                if(action != null)
                {
                    i = resolveContrastColor();
                    remoteviews.setDrawableParameters(0x10203a7, true, -1, i, android.graphics.PorterDuff.Mode.SRC_ATOP, -1);
                    if(NotificationColorUtil.isColorLight(i))
                        i = 0xff000000;
                    else
                        i = -1;
                    remoteviews.setDrawableParameters(0x10203a7, false, -1, i, android.graphics.PorterDuff.Mode.SRC_ATOP, -1);
                    remoteviews.setOnClickPendingIntent(0x10203b1, action.actionIntent);
                    remoteviews.setOnClickPendingIntent(0x10203a7, action.actionIntent);
                    remoteviews.setRemoteInputs(0x10203b1, Action._2D_get1(action));
                    remoteviews.setRemoteInputs(0x10203a7, Action._2D_get1(action));
                }
            }
            if(Notification._2D_get1(mN) != null)
                i = 0;
            else
                i = 8;
            remoteviews.setViewVisibility(0x10203b2, i);
        }

        private void bindNotificationHeader(RemoteViews remoteviews, boolean flag)
        {
            bindSmallIcon(remoteviews, flag);
            bindHeaderAppName(remoteviews, flag);
            if(!flag)
            {
                bindHeaderText(remoteviews);
                bindHeaderChronometerAndTime(remoteviews);
                bindProfileBadge(remoteviews);
            }
            bindExpandButton(remoteviews);
        }

        private void bindProfileBadge(RemoteViews remoteviews)
        {
            Bitmap bitmap = getProfileBadge();
            if(bitmap != null)
            {
                remoteviews.setImageViewBitmap(0x102038e, bitmap);
                remoteviews.setViewVisibility(0x102038e, 0);
                if(isColorized())
                    remoteviews.setDrawableParameters(0x102038e, false, -1, getPrimaryTextColor(), android.graphics.PorterDuff.Mode.SRC_ATOP, -1);
            }
        }

        private void bindSmallIcon(RemoteViews remoteviews, boolean flag)
        {
            if(Notification._2D_get2(mN) == null && mN.icon != 0)
                Notification._2D_set8(mN, Icon.createWithResource(mContext, mN.icon));
            remoteviews.setImageViewIcon(0x1020006, Notification._2D_get2(mN));
            remoteviews.setDrawableParameters(0x1020006, false, -1, -1, null, mN.iconLevel);
            processSmallIconColor(Notification._2D_get2(mN), remoteviews, flag);
        }

        private CharSequence createSummaryText()
        {
            CharSequence charsequence = mN.extras.getCharSequence("android.title");
            if(USE_ONLY_TITLE_IN_LOW_PRIORITY_SUMMARY)
                return charsequence;
            SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
            CharSequence charsequence1 = charsequence;
            if(charsequence == null)
                charsequence1 = mN.extras.getCharSequence("android.title.big");
            BidiFormatter bidiformatter = BidiFormatter.getInstance();
            if(charsequence1 != null)
                spannablestringbuilder.append(bidiformatter.unicodeWrap(charsequence1));
            charsequence = mN.extras.getCharSequence("android.text");
            if(charsequence1 != null && charsequence != null)
                spannablestringbuilder.append(bidiformatter.unicodeWrap(mContext.getText(0x10403ec)));
            if(charsequence != null)
                spannablestringbuilder.append(bidiformatter.unicodeWrap(charsequence));
            return spannablestringbuilder;
        }

        private CharSequence ensureColorSpanContrast(CharSequence charsequence, int i, ColorStateList acolorstatelist[])
        {
            if(charsequence instanceof Spanned)
            {
                Spanned spanned = (Spanned)charsequence;
                Object aobj[] = spanned.getSpans(0, spanned.length(), java/lang/Object);
                SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder(spanned.toString());
                int j = aobj.length;
                int k = 0;
                while(k < j) 
                {
                    Object obj = aobj[k];
                    Object obj1 = obj;
                    int l = spanned.getSpanStart(obj);
                    int i1 = spanned.getSpanEnd(obj);
                    boolean flag;
                    if(i1 - l == charsequence.length())
                        flag = true;
                    else
                        flag = false;
                    if(obj instanceof CharacterStyle)
                        obj1 = ((CharacterStyle)obj).getUnderlying();
                    if(obj1 instanceof TextAppearanceSpan)
                    {
                        TextAppearanceSpan textappearancespan = (TextAppearanceSpan)obj1;
                        ColorStateList colorstatelist = textappearancespan.getTextColor();
                        if(colorstatelist != null)
                        {
                            obj1 = colorstatelist.getColors();
                            int ai[] = new int[obj1.length];
                            for(int j1 = 0; j1 < ai.length; j1++)
                                ai[j1] = NotificationColorUtil.ensureLargeTextContrast(obj1[j1], i, mInNightMode);

                            colorstatelist = new ColorStateList((int[][])colorstatelist.getStates().clone(), ai);
                            textappearancespan = new TextAppearanceSpan(textappearancespan.getFamily(), textappearancespan.getTextStyle(), textappearancespan.getTextSize(), colorstatelist, textappearancespan.getLinkTextColor());
                            obj1 = textappearancespan;
                            if(flag)
                            {
                                acolorstatelist[0] = new ColorStateList((int[][])colorstatelist.getStates().clone(), ai);
                                obj1 = textappearancespan;
                            }
                        }
                    } else
                    if(obj1 instanceof ForegroundColorSpan)
                    {
                        int k1 = NotificationColorUtil.ensureLargeTextContrast(((ForegroundColorSpan)obj1).getForegroundColor(), i, mInNightMode);
                        ForegroundColorSpan foregroundcolorspan = new ForegroundColorSpan(k1);
                        obj1 = foregroundcolorspan;
                        if(flag)
                        {
                            acolorstatelist[0] = ColorStateList.valueOf(k1);
                            obj1 = foregroundcolorspan;
                        }
                    } else
                    {
                        obj1 = obj;
                    }
                    spannablestringbuilder.setSpan(obj1, l, i1, spanned.getSpanFlags(obj));
                    k++;
                }
                return spannablestringbuilder;
            } else
            {
                return charsequence;
            }
        }

        private void ensureColors()
        {
            int i;
            i = getBackgroundColor();
            break MISSING_BLOCK_LABEL_5;
            if(mPrimaryTextColor == 1 || mSecondaryTextColor == 1 || mActionBarColor == 1 || mTextColorsAreForBackground != i)
            {
                mTextColorsAreForBackground = i;
                if(!hasForegroundColor() || isColorized() ^ true)
                {
                    mPrimaryTextColor = NotificationColorUtil.resolvePrimaryColor(mContext, i);
                    mSecondaryTextColor = NotificationColorUtil.resolveSecondaryColor(mContext, i);
                    if(i != 0 && (mBackgroundColorHint != 1 || isColorized()))
                    {
                        mPrimaryTextColor = NotificationColorUtil.findAlphaToMeetContrast(mPrimaryTextColor, i, 4.5D);
                        mSecondaryTextColor = NotificationColorUtil.findAlphaToMeetContrast(mSecondaryTextColor, i, 4.5D);
                    }
                } else
                {
                    double d = NotificationColorUtil.calculateLuminance(i);
                    double d1 = NotificationColorUtil.calculateLuminance(mForegroundColor);
                    double d2 = NotificationColorUtil.calculateContrast(mForegroundColor, i);
                    boolean flag;
                    if(d <= d1 || !NotificationColorUtil.satisfiesTextContrast(i, 0xff000000))
                    {
                        if(d <= d1)
                            flag = NotificationColorUtil.satisfiesTextContrast(i, -1) ^ true;
                        else
                            flag = false;
                    } else
                    {
                        flag = true;
                    }
                    if(d2 < 4.5D)
                    {
                        if(flag)
                        {
                            mSecondaryTextColor = NotificationColorUtil.findContrastColor(mForegroundColor, i, true, 4.5D);
                            mPrimaryTextColor = NotificationColorUtil.changeColorLightness(mSecondaryTextColor, -20);
                        } else
                        {
                            mSecondaryTextColor = NotificationColorUtil.findContrastColorAgainstDark(mForegroundColor, i, true, 4.5D);
                            mPrimaryTextColor = NotificationColorUtil.changeColorLightness(mSecondaryTextColor, 10);
                        }
                    } else
                    {
                        mPrimaryTextColor = mForegroundColor;
                        int j = mPrimaryTextColor;
                        int k;
                        if(flag)
                            k = 20;
                        else
                            k = -10;
                        mSecondaryTextColor = NotificationColorUtil.changeColorLightness(j, k);
                        if(NotificationColorUtil.calculateContrast(mSecondaryTextColor, i) < 4.5D)
                        {
                            byte byte0;
                            if(flag)
                                mSecondaryTextColor = NotificationColorUtil.findContrastColor(mSecondaryTextColor, i, true, 4.5D);
                            else
                                mSecondaryTextColor = NotificationColorUtil.findContrastColorAgainstDark(mSecondaryTextColor, i, true, 4.5D);
                            k = mSecondaryTextColor;
                            if(flag)
                                byte0 = -20;
                            else
                                byte0 = 10;
                            mPrimaryTextColor = NotificationColorUtil.changeColorLightness(k, byte0);
                        }
                    }
                }
                mActionBarColor = NotificationColorUtil.resolveActionBarColor(mContext, i);
            }
            return;
        }

        private Action findReplyAction()
        {
            ArrayList arraylist = mActions;
            if(mOriginalActions != null)
                arraylist = mOriginalActions;
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
            {
                Action action = (Action)arraylist.get(j);
                if(hasValidRemoteInput(action))
                    return action;
            }

            return null;
        }

        private RemoteViews generateActionButton(Action action, boolean flag, boolean flag1, boolean flag2)
        {
            int i;
            BuilderRemoteViews builderremoteviews;
            boolean flag3;
            ApplicationInfo applicationinfo;
            if(action.actionIntent == null)
                flag3 = true;
            else
                flag3 = false;
            applicationinfo = mContext.getApplicationInfo();
            if(flag)
                i = getEmphasizedActionLayoutResource();
            else
            if(flag3)
                i = getActionTombstoneLayoutResource();
            else
                i = getActionLayoutResource();
            builderremoteviews = new BuilderRemoteViews(applicationinfo, i);
            if(!flag3)
                builderremoteviews.setOnClickPendingIntent(0x1020172, action.actionIntent);
            builderremoteviews.setContentDescription(0x1020172, action.title);
            if(Action._2D_get1(action) != null)
                builderremoteviews.setRemoteInputs(0x1020172, Action._2D_get1(action));
            if(!flag) goto _L2; else goto _L1
_L1:
            ColorStateList acolorstatelist[];
            if(isColorized())
            {
                if(flag1)
                    i = getActionBarColor();
                else
                    i = getActionBarColorDeEmphasized();
            } else
            {
                acolorstatelist = mContext;
                if(flag1)
                    i = 0x10600e7;
                else
                    i = 0x10600e8;
                i = acolorstatelist.getColor(i);
            }
            builderremoteviews.setDrawableParameters(0x10201dd, true, -1, i, android.graphics.PorterDuff.Mode.SRC_ATOP, -1);
            action = action.title;
            acolorstatelist = null;
            if(isLegacy())
            {
                action = NotificationColorUtil.clearColorSpans(action);
            } else
            {
                acolorstatelist = new ColorStateList[1];
                action = ensureColorSpanContrast(action, i, acolorstatelist);
            }
            builderremoteviews.setTextViewText(0x1020172, processTextSpans(action));
            setTextViewColorPrimary(builderremoteviews, 0x1020172);
            if(acolorstatelist == null || acolorstatelist[0] == null) goto _L4; else goto _L3
_L3:
            builderremoteviews.setTextColor(0x1020172, acolorstatelist[0]);
_L6:
            return builderremoteviews;
_L4:
            if(mN.color != 0 && isColorized() ^ true && mTintActionButtons)
                builderremoteviews.setTextColor(0x1020172, resolveContrastColor());
            continue; /* Loop/switch isn't completed */
_L2:
            builderremoteviews.setTextViewText(0x1020172, processTextSpans(processLegacyText(action.title)));
            if(isColorized() && flag2 ^ true)
                setTextViewColorPrimary(builderremoteviews, 0x1020172);
            else
            if(mN.color != 0 && mTintActionButtons)
            {
                int j;
                if(flag2)
                    j = resolveAmbientColor();
                else
                    j = resolveContrastColor();
                builderremoteviews.setTextColor(0x1020172, j);
            }
            if(true) goto _L6; else goto _L5
_L5:
        }

        private int getActionBarColor()
        {
            ensureColors();
            return mActionBarColor;
        }

        private int getActionBarColorDeEmphasized()
        {
            return NotificationColorUtil.getShiftedColor(getBackgroundColor(), 12);
        }

        private int getActionLayoutResource()
        {
            return 0x1090088;
        }

        private int getActionTombstoneLayoutResource()
        {
            return 0x109008b;
        }

        private Bundle getAllExtras()
        {
            Bundle bundle = (Bundle)mUserExtras.clone();
            bundle.putAll(mN.extras);
            return bundle;
        }

        private int getBackgroundColor()
        {
            if(isColorized())
            {
                int i;
                if(mBackgroundColor != 1)
                    i = mBackgroundColor;
                else
                    i = mN.color;
                return i;
            }
            int j;
            if(mBackgroundColorHint != 1)
                j = mBackgroundColorHint;
            else
                j = 0;
            return j;
        }

        private int getBaseLayoutResource()
        {
            return 0x109009b;
        }

        private int getBigBaseLayoutResource()
        {
            return 0x109009c;
        }

        private int getBigPictureLayoutResource()
        {
            return 0x109009e;
        }

        private int getBigTextLayoutResource()
        {
            return 0x109009f;
        }

        private NotificationColorUtil getColorUtil()
        {
            if(mColorUtil == null)
                mColorUtil = NotificationColorUtil.getInstance(mContext);
            return mColorUtil;
        }

        private int getEmphasizedActionLayoutResource()
        {
            return 0x1090089;
        }

        private int getInboxLayoutResource()
        {
            return 0x10900a0;
        }

        private int getMessagingLayoutResource()
        {
            return 0x10900a2;
        }

        private int getPrimaryHighlightColor()
        {
            int i;
            if(isColorized())
                i = getPrimaryTextColor();
            else
                i = resolveContrastColor();
            return i;
        }

        private Bitmap getProfileBadge()
        {
            Drawable drawable = getProfileBadgeDrawable();
            if(drawable == null)
            {
                return null;
            } else
            {
                int i = mContext.getResources().getDimensionPixelSize(0x1050102);
                Bitmap bitmap = Bitmap.createBitmap(i, i, android.graphics.Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                drawable.setBounds(0, 0, i, i);
                drawable.draw(canvas);
                return bitmap;
            }
        }

        private Drawable getProfileBadgeDrawable()
        {
            if(mContext.getUserId() == 0)
                return null;
            else
                return mContext.getPackageManager().getUserBadgeForDensityNoBackground(new UserHandle(mContext.getUserId()), 0);
        }

        private boolean handleProgressBar(boolean flag, RemoteViews remoteviews, Bundle bundle)
        {
            int i = bundle.getInt("android.progressMax", 0);
            int j = bundle.getInt("android.progress", 0);
            boolean flag1 = bundle.getBoolean("android.progressIndeterminate");
            if(flag && (i != 0 || flag1))
            {
                remoteviews.setViewVisibility(0x102000d, 0);
                remoteviews.setProgressBar(0x102000d, i, j, flag1);
                remoteviews.setProgressBackgroundTintList(0x102000d, ColorStateList.valueOf(mContext.getColor(0x10600f2)));
                if(mN.color != 0)
                {
                    bundle = ColorStateList.valueOf(resolveContrastColor());
                    remoteviews.setProgressTintList(0x102000d, bundle);
                    remoteviews.setProgressIndeterminateTintList(0x102000d, bundle);
                }
                return true;
            } else
            {
                remoteviews.setViewVisibility(0x102000d, 8);
                return false;
            }
        }

        private boolean hasForegroundColor()
        {
            boolean flag = true;
            if(mForegroundColor == 1)
                flag = false;
            return flag;
        }

        private boolean hasValidRemoteInput(Action action)
        {
            if(TextUtils.isEmpty(action.title) || action.actionIntent == null)
                return false;
            action = action.getRemoteInputs();
            if(action == null)
                return false;
            int i = action.length;
            for(int j = 0; j < i; j++)
            {
                RemoteInput remoteinput = action[j];
                CharSequence acharsequence[] = remoteinput.getChoices();
                if(remoteinput.getAllowFreeFormInput() || acharsequence != null && acharsequence.length != 0)
                    return true;
            }

            return false;
        }

        private void hideLine1Text(RemoteViews remoteviews)
        {
            if(remoteviews != null)
                remoteviews.setViewVisibility(0x102044b, 8);
        }

        private boolean isColorized()
        {
            return mN.isColorized();
        }

        private boolean isLegacy()
        {
            if(!mIsLegacyInitialized)
            {
                boolean flag;
                if(mContext.getApplicationInfo().targetSdkVersion < 21)
                    flag = true;
                else
                    flag = false;
                mIsLegacy = flag;
                mIsLegacyInitialized = true;
            }
            return mIsLegacy;
        }

        public static void makeHeaderExpanded(RemoteViews remoteviews)
        {
            if(remoteviews != null)
                remoteviews.setBoolean(0x1020334, "setExpanded", true);
        }

        private RemoteViews makePublicView(boolean flag)
        {
            if(mN.publicVersion != null)
            {
                Object obj = recoverBuilder(mContext, mN.publicVersion);
                if(flag)
                    obj = ((Builder) (obj)).makeAmbientNotification();
                else
                    obj = ((Builder) (obj)).createContentView();
                return ((RemoteViews) (obj));
            }
            Bundle bundle = mN.extras;
            Style style = mStyle;
            mStyle = null;
            Icon icon1 = Notification._2D_get1(mN);
            Notification._2D_set5(mN, null);
            Bitmap bitmap = mN.largeIcon;
            mN.largeIcon = null;
            ArrayList arraylist = mActions;
            mActions = new ArrayList();
            Object obj1 = new Bundle();
            ((Bundle) (obj1)).putBoolean("android.showWhen", bundle.getBoolean("android.showWhen"));
            ((Bundle) (obj1)).putBoolean("android.showChronometer", bundle.getBoolean("android.showChronometer"));
            ((Bundle) (obj1)).putBoolean("android.chronometerCountDown", bundle.getBoolean("android.chronometerCountDown"));
            mN.extras = ((Bundle) (obj1));
            if(flag)
            {
                ((Bundle) (obj1)).putCharSequence("android.title", mContext.getString(0x10403ee));
                obj1 = makeAmbientNotification();
            } else
            {
                obj1 = makeNotificationHeader(false);
                ((RemoteViews) (obj1)).setBoolean(0x1020334, "setExpandOnlyOnButton", true);
            }
            mN.extras = bundle;
            Notification._2D_set5(mN, icon1);
            mN.largeIcon = bitmap;
            mActions = arraylist;
            mStyle = style;
            return ((RemoteViews) (obj1));
        }

        public static Notification maybeCloneStrippedForDelivery(Notification notification, boolean flag)
        {
            String s = notification.extras.getString("android.template");
            if(!flag && TextUtils.isEmpty(s) ^ true && Notification.getNotificationStyleClass(s) == null)
                return notification;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            if(notification.contentView instanceof BuilderRemoteViews)
            {
                if(notification.extras.getInt("android.rebuild.contentViewActionCount", -1) == notification.contentView.getSequenceNumber())
                    flag1 = true;
                else
                    flag1 = false;
            } else
            {
                flag1 = false;
            }
            if(notification.bigContentView instanceof BuilderRemoteViews)
            {
                if(notification.extras.getInt("android.rebuild.bigViewActionCount", -1) == notification.bigContentView.getSequenceNumber())
                    flag2 = true;
                else
                    flag2 = false;
            } else
            {
                flag2 = false;
            }
            if(notification.headsUpContentView instanceof BuilderRemoteViews)
            {
                if(notification.extras.getInt("android.rebuild.hudViewActionCount", -1) == notification.headsUpContentView.getSequenceNumber())
                    flag3 = true;
                else
                    flag3 = false;
            } else
            {
                flag3 = false;
            }
            if(!flag && flag1 ^ true && flag2 ^ true && flag3 ^ true)
                return notification;
            notification = notification.clone();
            if(flag1)
            {
                notification.contentView = null;
                notification.extras.remove("android.rebuild.contentViewActionCount");
            }
            if(flag2)
            {
                notification.bigContentView = null;
                notification.extras.remove("android.rebuild.bigViewActionCount");
            }
            if(flag3)
            {
                notification.headsUpContentView = null;
                notification.extras.remove("android.rebuild.hudViewActionCount");
            }
            if(flag)
            {
                notification.extras.remove("android.tv.EXTENSIONS");
                notification.extras.remove("android.wearable.EXTENSIONS");
                notification.extras.remove("android.car.EXTENSIONS");
            }
            return notification;
        }

        private void processLargeLegacyIcon(Icon icon1, RemoteViews remoteviews)
        {
            if(icon1 != null && isLegacy() && getColorUtil().isGrayscaleIcon(mContext, icon1))
                remoteviews.setDrawableParameters(0x1020006, false, -1, resolveContrastColor(), android.graphics.PorterDuff.Mode.SRC_ATOP, -1);
        }

        private CharSequence processLegacyText(CharSequence charsequence)
        {
            return processLegacyText(charsequence, false);
        }

        private CharSequence processLegacyText(CharSequence charsequence, boolean flag)
        {
            boolean flag1;
            if(!isLegacy())
                flag1 = textColorsNeedInversion();
            else
                flag1 = true;
            if(flag1 != flag)
                return getColorUtil().invertCharSequenceColors(charsequence);
            else
                return charsequence;
        }

        private void processSmallIconColor(Icon icon1, RemoteViews remoteviews, boolean flag)
        {
            boolean flag1;
            int i;
            if(isLegacy())
                flag1 = getColorUtil().isGrayscaleIcon(mContext, icon1);
            else
                flag1 = true;
            if(flag)
                i = resolveAmbientColor();
            else
                i = getPrimaryHighlightColor();
            if(flag1)
                remoteviews.setDrawableParameters(0x1020006, false, -1, i, android.graphics.PorterDuff.Mode.SRC_ATOP, -1);
            if(!flag1)
                i = 1;
            remoteviews.setInt(0x1020334, "setOriginalIconColor", i);
        }

        private CharSequence processTextSpans(CharSequence charsequence)
        {
            if(hasForegroundColor())
                return NotificationColorUtil.clearColorSpans(charsequence);
            else
                return charsequence;
        }

        public static Builder recoverBuilder(Context context, Notification notification)
        {
            ApplicationInfo applicationinfo;
            applicationinfo = (ApplicationInfo)notification.extras.getParcelable("android.appInfo");
            if(applicationinfo == null)
                break MISSING_BLOCK_LABEL_27;
            Context context1 = context.createApplicationContext(applicationinfo, 4);
            context = context1;
_L2:
            return new Builder(context, notification);
            android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
            namenotfoundexception;
            Log.e("Notification", (new StringBuilder()).append("ApplicationInfo ").append(applicationinfo).append(" not found").toString());
            if(true) goto _L2; else goto _L1
_L1:
        }

        private void resetContentMargins(RemoteViews remoteviews)
        {
            remoteviews.setViewLayoutMarginEndDimen(0x10202dc, 0);
            remoteviews.setViewLayoutMarginEndDimen(0x1020430, 0);
        }

        private void resetNotificationHeader(RemoteViews remoteviews)
        {
            remoteviews.setBoolean(0x1020334, "setExpanded", false);
            remoteviews.setTextViewText(0x10201ab, null);
            remoteviews.setViewVisibility(0x10201f6, 8);
            remoteviews.setViewVisibility(0x1020282, 8);
            remoteviews.setTextViewText(0x1020282, null);
            remoteviews.setViewVisibility(0x1020283, 8);
            remoteviews.setViewVisibility(0x1020451, 8);
            remoteviews.setViewVisibility(0x102044d, 8);
            remoteviews.setImageViewIcon(0x102038e, null);
            remoteviews.setViewVisibility(0x102038e, 8);
        }

        private void resetStandardTemplate(RemoteViews remoteviews)
        {
            resetNotificationHeader(remoteviews);
            resetContentMargins(remoteviews);
            remoteviews.setViewVisibility(0x10203b1, 8);
            remoteviews.setViewVisibility(0x1020016, 8);
            remoteviews.setTextViewText(0x1020016, null);
            remoteviews.setViewVisibility(0x1020430, 8);
            remoteviews.setTextViewText(0x1020430, null);
            remoteviews.setViewVisibility(0x102044b, 8);
            remoteviews.setTextViewText(0x102044b, null);
        }

        private void resetStandardTemplateWithActions(RemoteViews remoteviews)
        {
            remoteviews.setViewVisibility(0x1020189, 8);
            remoteviews.removeAllViews(0x1020189);
            remoteviews.setViewVisibility(0x1020336, 8);
            remoteviews.setTextViewText(0x1020337, null);
            remoteviews.setViewVisibility(0x1020338, 8);
            remoteviews.setTextViewText(0x1020338, null);
            remoteviews.setViewVisibility(0x1020339, 8);
            remoteviews.setTextViewText(0x1020339, null);
            remoteviews.setViewLayoutMarginBottomDimen(0x1020332, 0);
        }

        private void sanitizeColor()
        {
            if(mN.color != 0)
            {
                Notification notification = mN;
                notification.color = notification.color | 0xff000000;
            }
        }

        private void setTextViewColorPrimary(RemoteViews remoteviews, int i)
        {
            ensureColors();
            remoteviews.setTextColor(i, mPrimaryTextColor);
        }

        private void setTextViewColorSecondary(RemoteViews remoteviews, int i)
        {
            ensureColors();
            remoteviews.setTextColor(i, mSecondaryTextColor);
        }

        private boolean shouldTintActionButtons()
        {
            return mTintActionButtons;
        }

        private boolean showsTimeOrChronometer()
        {
            boolean flag;
            if(!mN.showsTime())
                flag = mN.showsChronometer();
            else
                flag = true;
            return flag;
        }

        private boolean textColorsNeedInversion()
        {
            boolean flag = false;
            if(mStyle == null || android/app/Notification$MediaStyle.equals(mStyle.getClass()) ^ true)
                return false;
            int i = mContext.getApplicationInfo().targetSdkVersion;
            boolean flag1 = flag;
            if(i > 23)
            {
                flag1 = flag;
                if(i < 26)
                    flag1 = true;
            }
            return flag1;
        }

        private void updateBackgroundColor(RemoteViews remoteviews)
        {
            if(isColorized())
                remoteviews.setInt(0x102041e, "setBackgroundColor", getBackgroundColor());
            else
                remoteviews.setInt(0x102041e, "setBackgroundResource", 0);
        }

        private boolean useExistingRemoteView()
        {
            boolean flag;
            if(mStyle != null)
            {
                if(!mStyle.displayCustomViewInline())
                    flag = mRebuildStyledRemoteViews ^ true;
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            return flag;
        }

        public Builder addAction(int i, CharSequence charsequence, PendingIntent pendingintent)
        {
            mActions.add(new Action(i, Notification.safeCharSequence(charsequence), pendingintent));
            return this;
        }

        public Builder addAction(Action action)
        {
            if(action != null)
                mActions.add(action);
            return this;
        }

        public Builder addExtras(Bundle bundle)
        {
            if(bundle != null)
                mUserExtras.putAll(bundle);
            return this;
        }

        public Builder addPerson(String s)
        {
            mPersonList.add(s);
            return this;
        }

        public Notification build()
        {
            if(mUserExtras != null)
                mN.extras = getAllExtras();
            Notification._2D_set0(mN, System.currentTimeMillis());
            Notification.addFieldsFromContext(mContext, mN);
            buildUnstyled();
            if(mStyle != null)
            {
                mStyle.reduceImageSizes(mContext);
                mStyle.purgeResources();
                mStyle.buildStyled(mN);
            }
            mN.reduceImageSizes(mContext);
            if(mContext.getApplicationInfo().targetSdkVersion < 24 && useExistingRemoteView())
            {
                if(mN.contentView == null)
                {
                    mN.contentView = createContentView();
                    mN.extras.putInt("android.rebuild.contentViewActionCount", mN.contentView.getSequenceNumber());
                }
                if(mN.bigContentView == null)
                {
                    mN.bigContentView = createBigContentView();
                    if(mN.bigContentView != null)
                        mN.extras.putInt("android.rebuild.bigViewActionCount", mN.bigContentView.getSequenceNumber());
                }
                if(mN.headsUpContentView == null)
                {
                    mN.headsUpContentView = createHeadsUpContentView();
                    if(mN.headsUpContentView != null)
                        mN.extras.putInt("android.rebuild.hudViewActionCount", mN.headsUpContentView.getSequenceNumber());
                }
            }
            if((mN.defaults & 4) != 0)
            {
                Notification notification = mN;
                notification.flags = notification.flags | 1;
            }
            return mN;
        }

        public Notification buildInto(Notification notification)
        {
            build().cloneInto(notification, true);
            return notification;
        }

        public Notification buildUnstyled()
        {
            if(mActions.size() > 0)
            {
                mN.actions = new Action[mActions.size()];
                mActions.toArray(mN.actions);
            }
            if(!mPersonList.isEmpty())
                mN.extras.putStringArray("android.people", (String[])mPersonList.toArray(new String[mPersonList.size()]));
            break MISSING_BLOCK_LABEL_85;
            if(mN.bigContentView != null || mN.contentView != null || mN.headsUpContentView != null)
                mN.extras.putBoolean("android.contains.customView", true);
            return mN;
        }

        public RemoteViews createBigContentView()
        {
            RemoteViews remoteviews;
            remoteviews = null;
            if(mN.bigContentView != null && useExistingRemoteView())
                return mN.bigContentView;
            if(mStyle == null) goto _L2; else goto _L1
_L1:
            remoteviews = mStyle.makeBigContentView();
            hideLine1Text(remoteviews);
_L4:
            makeHeaderExpanded(remoteviews);
            return remoteviews;
_L2:
            if(mActions.size() != 0)
                remoteviews = applyStandardTemplateWithActions(getBigBaseLayoutResource());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public RemoteViews createContentView()
        {
            return createContentView(false);
        }

        public RemoteViews createContentView(boolean flag)
        {
            if(mN.contentView != null && useExistingRemoteView())
                return mN.contentView;
            if(mStyle != null)
            {
                RemoteViews remoteviews = mStyle.makeContentView(flag);
                if(remoteviews != null)
                    return remoteviews;
            }
            return applyStandardTemplate(getBaseLayoutResource());
        }

        public RemoteViews createHeadsUpContentView()
        {
            return createHeadsUpContentView(false);
        }

        public RemoteViews createHeadsUpContentView(boolean flag)
        {
            if(mN.headsUpContentView != null && useExistingRemoteView())
                return mN.headsUpContentView;
            if(mStyle != null)
            {
                RemoteViews remoteviews = mStyle.makeHeadsUpContentView(flag);
                if(remoteviews != null)
                    return remoteviews;
            } else
            if(mActions.size() == 0)
                return null;
            return applyStandardTemplateWithActions(getBigBaseLayoutResource());
        }

        public Builder extend(Extender extender)
        {
            extender.extend(this);
            return this;
        }

        public Bundle getExtras()
        {
            return mUserExtras;
        }

        public Notification getNotification()
        {
            return build();
        }

        public int getPrimaryTextColor()
        {
            ensureColors();
            return mPrimaryTextColor;
        }

        public int getSecondaryTextColor()
        {
            ensureColors();
            return mSecondaryTextColor;
        }

        public String loadHeaderAppName()
        {
            Object obj = null;
            PackageManager packagemanager = mContext.getPackageManager();
            Object obj1 = obj;
            if(mN.extras.containsKey("android.substName"))
            {
                String s = mContext.getPackageName();
                obj1 = mN.extras.getString("android.substName");
                if(packagemanager.checkPermission("android.permission.SUBSTITUTE_NOTIFICATION_APP_NAME", s) != 0)
                {
                    Log.w("Notification", (new StringBuilder()).append("warning: pkg ").append(s).append(" attempting to substitute app name '").append(((String) (obj1))).append("' without holding perm ").append("android.permission.SUBSTITUTE_NOTIFICATION_APP_NAME").toString());
                    obj1 = obj;
                }
            }
            obj = obj1;
            if(TextUtils.isEmpty(((CharSequence) (obj1))))
                obj = packagemanager.getApplicationLabel(mContext.getApplicationInfo());
            if(TextUtils.isEmpty(((CharSequence) (obj))))
                return null;
            else
                return String.valueOf(obj);
        }

        public RemoteViews makeAmbientNotification()
        {
            return applyStandardTemplateWithActions(0x109009a, mParams.reset().ambient(true).fillTextsFrom(this).hasProgress(false));
        }

        public RemoteViews makeLowPriorityContentView(boolean flag)
        {
            int i = mN.color;
            mN.color = 0;
            CharSequence charsequence = mN.extras.getCharSequence("android.subText");
            if(!flag || TextUtils.isEmpty(charsequence))
            {
                CharSequence charsequence1 = createSummaryText();
                if(!TextUtils.isEmpty(charsequence1))
                    mN.extras.putCharSequence("android.subText", charsequence1);
            }
            RemoteViews remoteviews = makeNotificationHeader(false);
            remoteviews.setBoolean(0x1020334, "setAcceptAllTouches", true);
            if(charsequence != null)
                mN.extras.putCharSequence("android.subText", charsequence);
            else
                mN.extras.remove("android.subText");
            mN.color = i;
            return remoteviews;
        }

        public RemoteViews makeNotificationHeader(boolean flag)
        {
            Boolean boolean1 = (Boolean)mN.extras.get("android.colorized");
            mN.extras.putBoolean("android.colorized", false);
            Object obj = mContext.getApplicationInfo();
            int i;
            if(flag)
                i = 0x109008e;
            else
                i = 0x1090097;
            obj = new BuilderRemoteViews(((ApplicationInfo) (obj)), i);
            resetNotificationHeader(((RemoteViews) (obj)));
            bindNotificationHeader(((RemoteViews) (obj)), flag);
            if(boolean1 != null)
                mN.extras.putBoolean("android.colorized", boolean1.booleanValue());
            else
                mN.extras.remove("android.colorized");
            return ((RemoteViews) (obj));
        }

        public RemoteViews makePublicAmbientNotification()
        {
            return makePublicView(true);
        }

        public RemoteViews makePublicContentView()
        {
            return makePublicView(false);
        }

        int resolveAmbientColor()
        {
            if(mCachedAmbientColorIsFor == mN.color && mCachedAmbientColorIsFor != 1)
            {
                return mCachedAmbientColor;
            } else
            {
                int i = NotificationColorUtil.resolveAmbientColor(mContext, mN.color);
                mCachedAmbientColorIsFor = mN.color;
                mCachedAmbientColor = i;
                return i;
            }
        }

        int resolveContrastColor()
        {
            if(mCachedContrastColorIsFor == mN.color && mCachedContrastColor != 1)
                return mCachedContrastColor;
            int i = mBackgroundColorHint;
            if(mBackgroundColorHint == 1)
                i = mContext.getColor(0x10600ed);
            int j;
            int k;
            if(mN.color == 0)
            {
                ensureColors();
                j = mSecondaryTextColor;
            } else
            {
                j = NotificationColorUtil.resolveContrastColor(mContext, mN.color, i, mInNightMode);
            }
            k = j;
            if(Color.alpha(j) < 255)
                k = NotificationColorUtil.compositeColors(j, i);
            mCachedContrastColorIsFor = mN.color;
            mCachedContrastColor = k;
            return k;
        }

        public transient Builder setActions(Action aaction[])
        {
            mActions.clear();
            for(int i = 0; i < aaction.length; i++)
                if(aaction[i] != null)
                    mActions.add(aaction[i]);

            return this;
        }

        public Builder setAutoCancel(boolean flag)
        {
            setFlag(16, flag);
            return this;
        }

        public void setBackgroundColorHint(int i)
        {
            mBackgroundColorHint = i;
        }

        public Builder setBadgeIconType(int i)
        {
            Notification._2D_set1(mN, i);
            return this;
        }

        public Builder setCategory(String s)
        {
            mN.category = s;
            return this;
        }

        public Builder setChannel(String s)
        {
            Notification._2D_set2(mN, s);
            return this;
        }

        public Builder setChannelId(String s)
        {
            Notification._2D_set2(mN, s);
            return this;
        }

        public Builder setChronometerCountDown(boolean flag)
        {
            mN.extras.putBoolean("android.chronometerCountDown", flag);
            return this;
        }

        public Builder setColor(int i)
        {
            mN.color = i;
            sanitizeColor();
            return this;
        }

        public void setColorPalette(int i, int j)
        {
            mBackgroundColor = i;
            mForegroundColor = j;
            mTextColorsAreForBackground = 1;
            ensureColors();
        }

        public Builder setColorized(boolean flag)
        {
            mN.extras.putBoolean("android.colorized", flag);
            return this;
        }

        public Builder setContent(RemoteViews remoteviews)
        {
            return setCustomContentView(remoteviews);
        }

        public Builder setContentInfo(CharSequence charsequence)
        {
            mN.extras.putCharSequence("android.infoText", Notification.safeCharSequence(charsequence));
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingintent)
        {
            mN.contentIntent = pendingintent;
            return this;
        }

        void setContentMinHeight(RemoteViews remoteviews, boolean flag)
        {
            int i = 0;
            if(flag)
                i = mContext.getResources().getDimensionPixelSize(0x1050129);
            remoteviews.setInt(0x1020335, "setMinimumHeight", i);
        }

        public Builder setContentText(CharSequence charsequence)
        {
            mN.extras.putCharSequence("android.text", Notification.safeCharSequence(charsequence));
            return this;
        }

        public Builder setContentTitle(CharSequence charsequence)
        {
            mN.extras.putCharSequence("android.title", Notification.safeCharSequence(charsequence));
            return this;
        }

        public Builder setCustomBigContentView(RemoteViews remoteviews)
        {
            mN.bigContentView = remoteviews;
            return this;
        }

        public Builder setCustomContentView(RemoteViews remoteviews)
        {
            mN.contentView = remoteviews;
            return this;
        }

        public Builder setCustomHeadsUpContentView(RemoteViews remoteviews)
        {
            mN.headsUpContentView = remoteviews;
            return this;
        }

        public Builder setDefaults(int i)
        {
            mN.defaults = i;
            return this;
        }

        public Builder setDeleteIntent(PendingIntent pendingintent)
        {
            mN.deleteIntent = pendingintent;
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            if(bundle != null)
                mUserExtras = bundle;
            return this;
        }

        public Builder setFlag(int i, boolean flag)
        {
            if(flag)
            {
                Notification notification = mN;
                notification.flags = notification.flags | i;
            } else
            {
                Notification notification1 = mN;
                notification1.flags = notification1.flags & i;
            }
            return this;
        }

        public Builder setFullScreenIntent(PendingIntent pendingintent, boolean flag)
        {
            mN.fullScreenIntent = pendingintent;
            setFlag(128, flag);
            return this;
        }

        public Builder setGroup(String s)
        {
            Notification._2D_set4(mN, s);
            return this;
        }

        public Builder setGroupAlertBehavior(int i)
        {
            Notification._2D_set3(mN, i);
            return this;
        }

        public Builder setGroupSummary(boolean flag)
        {
            setFlag(512, flag);
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap)
        {
            Icon icon1 = null;
            if(bitmap != null)
                icon1 = Icon.createWithBitmap(bitmap);
            return setLargeIcon(icon1);
        }

        public Builder setLargeIcon(Icon icon1)
        {
            Notification._2D_set5(mN, icon1);
            mN.extras.putParcelable("android.largeIcon", icon1);
            return this;
        }

        public Builder setLights(int i, int j, int k)
        {
            mN.ledARGB = i;
            mN.ledOnMS = j;
            mN.ledOffMS = k;
            if(j != 0 || k != 0)
            {
                Notification notification = mN;
                notification.flags = notification.flags | 1;
            }
            return this;
        }

        public Builder setLocalOnly(boolean flag)
        {
            setFlag(256, flag);
            return this;
        }

        public Builder setNumber(int i)
        {
            mN.number = i;
            return this;
        }

        public Builder setOngoing(boolean flag)
        {
            setFlag(2, flag);
            return this;
        }

        public Builder setOnlyAlertOnce(boolean flag)
        {
            setFlag(8, flag);
            return this;
        }

        public Builder setPriority(int i)
        {
            mN.priority = i;
            return this;
        }

        public Builder setProgress(int i, int j, boolean flag)
        {
            mN.extras.putInt("android.progress", j);
            mN.extras.putInt("android.progressMax", i);
            mN.extras.putBoolean("android.progressIndeterminate", flag);
            return this;
        }

        public Builder setPublicVersion(Notification notification)
        {
            if(notification != null)
            {
                mN.publicVersion = new Notification();
                notification.cloneInto(mN.publicVersion, true);
            } else
            {
                mN.publicVersion = null;
            }
            return this;
        }

        public void setRebuildStyledRemoteViews(boolean flag)
        {
            mRebuildStyledRemoteViews = flag;
        }

        public Builder setRemoteInputHistory(CharSequence acharsequence[])
        {
            if(acharsequence == null)
            {
                mN.extras.putCharSequenceArray("android.remoteInputHistory", null);
            } else
            {
                int i = Math.min(5, acharsequence.length);
                CharSequence acharsequence1[] = new CharSequence[i];
                for(int j = 0; j < i; j++)
                    acharsequence1[j] = Notification.safeCharSequence(acharsequence[j]);

                mN.extras.putCharSequenceArray("android.remoteInputHistory", acharsequence1);
            }
            return this;
        }

        public Builder setSettingsText(CharSequence charsequence)
        {
            Notification._2D_set6(mN, Notification.safeCharSequence(charsequence));
            return this;
        }

        public Builder setShortcutId(String s)
        {
            Notification._2D_set7(mN, s);
            return this;
        }

        public Builder setShowWhen(boolean flag)
        {
            mN.extras.putBoolean("android.showWhen", flag);
            return this;
        }

        public Builder setSmallIcon(int i)
        {
            Icon icon1;
            if(i != 0)
                icon1 = Icon.createWithResource(mContext, i);
            else
                icon1 = null;
            return setSmallIcon(icon1);
        }

        public Builder setSmallIcon(int i, int j)
        {
            mN.iconLevel = j;
            return setSmallIcon(i);
        }

        public Builder setSmallIcon(Icon icon1)
        {
            mN.setSmallIcon(icon1);
            if(icon1 != null && icon1.getType() == 2)
                mN.icon = icon1.getResId();
            return this;
        }

        public Builder setSortKey(String s)
        {
            Notification._2D_set9(mN, s);
            return this;
        }

        public Builder setSound(Uri uri)
        {
            mN.sound = uri;
            mN.audioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
            return this;
        }

        public Builder setSound(Uri uri, int i)
        {
            PlayerBase.deprecateStreamTypeForPlayback(i, "Notification", "setSound()");
            mN.sound = uri;
            mN.audioStreamType = i;
            return this;
        }

        public Builder setSound(Uri uri, AudioAttributes audioattributes)
        {
            mN.sound = uri;
            mN.audioAttributes = audioattributes;
            return this;
        }

        public Builder setStyle(Style style)
        {
            if(mStyle != style)
            {
                mStyle = style;
                if(mStyle != null)
                {
                    mStyle.setBuilder(this);
                    mN.extras.putString("android.template", style.getClass().getName());
                } else
                {
                    mN.extras.remove("android.template");
                }
            }
            return this;
        }

        public Builder setSubText(CharSequence charsequence)
        {
            mN.extras.putCharSequence("android.subText", Notification.safeCharSequence(charsequence));
            return this;
        }

        public Builder setTicker(CharSequence charsequence)
        {
            mN.tickerText = Notification.safeCharSequence(charsequence);
            return this;
        }

        public Builder setTicker(CharSequence charsequence, RemoteViews remoteviews)
        {
            setTicker(charsequence);
            return this;
        }

        public Builder setTimeout(long l)
        {
            Notification._2D_set10(mN, l);
            return this;
        }

        public Builder setTimeoutAfter(long l)
        {
            Notification._2D_set10(mN, l);
            return this;
        }

        public Builder setUsesChronometer(boolean flag)
        {
            mN.extras.putBoolean("android.showChronometer", flag);
            return this;
        }

        public Builder setVibrate(long al[])
        {
            mN.vibrate = al;
            return this;
        }

        public Builder setVisibility(int i)
        {
            mN.visibility = i;
            return this;
        }

        public Builder setWhen(long l)
        {
            mN.when = l;
            return this;
        }

        public static final String EXTRA_REBUILD_BIG_CONTENT_VIEW_ACTION_COUNT = "android.rebuild.bigViewActionCount";
        public static final String EXTRA_REBUILD_CONTENT_VIEW_ACTION_COUNT = "android.rebuild.contentViewActionCount";
        public static final String EXTRA_REBUILD_HEADS_UP_CONTENT_VIEW_ACTION_COUNT = "android.rebuild.hudViewActionCount";
        private static final int LIGHTNESS_TEXT_DIFFERENCE_DARK = -10;
        private static final int LIGHTNESS_TEXT_DIFFERENCE_LIGHT = 20;
        private static final int MAX_ACTION_BUTTONS = 3;
        private static final boolean USE_ONLY_TITLE_IN_LOW_PRIORITY_SUMMARY = SystemProperties.getBoolean("notifications.only_title", true);
        private int mActionBarColor;
        private ArrayList mActions;
        private int mBackgroundColor;
        private int mBackgroundColorHint;
        private int mCachedAmbientColor;
        private int mCachedAmbientColorIsFor;
        private int mCachedContrastColor;
        private int mCachedContrastColorIsFor;
        private NotificationColorUtil mColorUtil;
        private Context mContext;
        private int mForegroundColor;
        private boolean mInNightMode;
        private boolean mIsLegacy;
        private boolean mIsLegacyInitialized;
        private Notification mN;
        private ArrayList mOriginalActions;
        StandardTemplateParams mParams;
        private ArrayList mPersonList;
        private int mPrimaryTextColor;
        private boolean mRebuildStyledRemoteViews;
        private int mSecondaryTextColor;
        private Style mStyle;
        private int mTextColorsAreForBackground;
        private boolean mTintActionButtons;
        private Bundle mUserExtras;


        public Builder(Context context)
        {
            this(context, (Notification)null);
        }

        public Builder(Context context, Notification notification)
        {
            mUserExtras = new Bundle();
            mActions = new ArrayList(3);
            mPersonList = new ArrayList();
            mCachedContrastColor = 1;
            mCachedContrastColorIsFor = 1;
            mCachedAmbientColor = 1;
            mCachedAmbientColorIsFor = 1;
            mParams = new StandardTemplateParams(null);
            mTextColorsAreForBackground = 1;
            mPrimaryTextColor = 1;
            mSecondaryTextColor = 1;
            mActionBarColor = 1;
            mBackgroundColor = 1;
            mForegroundColor = 1;
            mBackgroundColorHint = 1;
            mContext = context;
            Resources resources = mContext.getResources();
            mTintActionButtons = resources.getBoolean(0x11200ba);
            if(resources.getBoolean(0x1120065))
            {
                boolean flag;
                if((resources.getConfiguration().uiMode & 0x30) == 32)
                    flag = true;
                else
                    flag = false;
                mInNightMode = flag;
            }
            if(notification != null) goto _L2; else goto _L1
_L1:
            mN = new Notification();
            if(context.getApplicationInfo().targetSdkVersion < 24)
                mN.extras.putBoolean("android.showWhen", true);
            mN.priority = 0;
            mN.visibility = 0;
_L4:
            return;
_L2:
            mN = notification;
            if(mN.actions != null)
                Collections.addAll(mActions, mN.actions);
            if(mN.extras.containsKey("android.people"))
                Collections.addAll(mPersonList, mN.extras.getStringArray("android.people"));
            if(mN.getSmallIcon() == null && mN.icon != 0)
                setSmallIcon(mN.icon);
            if(mN.getLargeIcon() == null && mN.largeIcon != null)
                setLargeIcon(mN.largeIcon);
            notification = mN.extras.getString("android.template");
            if(TextUtils.isEmpty(notification))
                continue; /* Loop/switch isn't completed */
            context = Notification.getNotificationStyleClass(notification);
            if(context == null)
            {
                Log.d("Notification", (new StringBuilder()).append("Unknown style class: ").append(notification).toString());
                continue; /* Loop/switch isn't completed */
            }
            context = context.getDeclaredConstructor(new Class[0]);
            context.setAccessible(true);
            context = (Style)context.newInstance(new Object[0]);
            context.restoreFromExtras(mN.extras);
            if(context != null)
                try
                {
                    setStyle(context);
                }
                // Misplaced declaration of an exception variable
                catch(Context context)
                {
                    Log.e("Notification", "Could not create Style", context);
                }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public Builder(Context context, String s)
        {
            this(context, (Notification)null);
            Notification._2D_set2(mN, s);
        }
    }

    private static class BuilderRemoteViews extends RemoteViews
    {

        public BuilderRemoteViews clone()
        {
            Parcel parcel = Parcel.obtain();
            writeToParcel(parcel, 0);
            parcel.setDataPosition(0);
            BuilderRemoteViews builderremoteviews = new BuilderRemoteViews(parcel);
            parcel.recycle();
            return builderremoteviews;
        }

        public volatile RemoteViews clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public BuilderRemoteViews(ApplicationInfo applicationinfo, int i)
        {
            super(applicationinfo, i);
        }

        public BuilderRemoteViews(Parcel parcel)
        {
            super(parcel);
        }
    }

    public static final class CarExtender
        implements Extender
    {

        public Builder extend(Builder builder)
        {
            Bundle bundle = new Bundle();
            if(mLargeIcon != null)
                bundle.putParcelable("large_icon", mLargeIcon);
            if(mColor != 0)
                bundle.putInt("app_color", mColor);
            if(mUnreadConversation != null)
                bundle.putBundle("car_conversation", mUnreadConversation.getBundleForUnreadConversation());
            builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
            return builder;
        }

        public int getColor()
        {
            return mColor;
        }

        public Bitmap getLargeIcon()
        {
            return mLargeIcon;
        }

        public UnreadConversation getUnreadConversation()
        {
            return mUnreadConversation;
        }

        public CarExtender setColor(int i)
        {
            mColor = i;
            return this;
        }

        public CarExtender setLargeIcon(Bitmap bitmap)
        {
            mLargeIcon = bitmap;
            return this;
        }

        public CarExtender setUnreadConversation(UnreadConversation unreadconversation)
        {
            mUnreadConversation = unreadconversation;
            return this;
        }

        private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String TAG = "CarExtender";
        private int mColor;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public CarExtender()
        {
            mColor = 0;
        }

        public CarExtender(Notification notification)
        {
            mColor = 0;
            if(notification.extras == null)
                notification = null;
            else
                notification = notification.extras.getBundle("android.car.EXTENSIONS");
            if(notification != null)
            {
                mLargeIcon = (Bitmap)notification.getParcelable("large_icon");
                mColor = notification.getInt("app_color", 0);
                mUnreadConversation = UnreadConversation.getUnreadConversationFromBundle(notification.getBundle("car_conversation"));
            }
        }
    }

    public static class CarExtender.Builder
    {

        public CarExtender.Builder addMessage(String s)
        {
            mMessages.add(s);
            return this;
        }

        public CarExtender.UnreadConversation build()
        {
            String as[] = (String[])mMessages.toArray(new String[mMessages.size()]);
            String s = mParticipant;
            RemoteInput remoteinput = mRemoteInput;
            PendingIntent pendingintent = mReplyPendingIntent;
            PendingIntent pendingintent1 = mReadPendingIntent;
            long l = mLatestTimestamp;
            return new CarExtender.UnreadConversation(as, remoteinput, pendingintent, pendingintent1, new String[] {
                s
            }, l);
        }

        public CarExtender.Builder setLatestTimestamp(long l)
        {
            mLatestTimestamp = l;
            return this;
        }

        public CarExtender.Builder setReadPendingIntent(PendingIntent pendingintent)
        {
            mReadPendingIntent = pendingintent;
            return this;
        }

        public CarExtender.Builder setReplyAction(PendingIntent pendingintent, RemoteInput remoteinput)
        {
            mRemoteInput = remoteinput;
            mReplyPendingIntent = pendingintent;
            return this;
        }

        private long mLatestTimestamp;
        private final List mMessages = new ArrayList();
        private final String mParticipant;
        private PendingIntent mReadPendingIntent;
        private RemoteInput mRemoteInput;
        private PendingIntent mReplyPendingIntent;

        public CarExtender.Builder(String s)
        {
            mParticipant = s;
        }
    }

    public static class CarExtender.UnreadConversation
    {

        static CarExtender.UnreadConversation getUnreadConversationFromBundle(Bundle bundle)
        {
            Object obj;
            String as[];
            if(bundle == null)
                return null;
            obj = bundle.getParcelableArray("messages");
            as = null;
            if(obj == null) goto _L2; else goto _L1
_L1:
            boolean flag;
            int i;
            as = new String[obj.length];
            flag = true;
            i = 0;
_L9:
            boolean flag1 = flag;
            if(i >= as.length) goto _L4; else goto _L3
_L3:
            if(obj[i] instanceof Bundle) goto _L6; else goto _L5
_L5:
            flag1 = false;
_L4:
            if(!flag1)
                break; /* Loop/switch isn't completed */
_L2:
            obj = (PendingIntent)bundle.getParcelable("on_read");
            PendingIntent pendingintent = (PendingIntent)bundle.getParcelable("on_reply");
            RemoteInput remoteinput = (RemoteInput)bundle.getParcelable("remote_input");
            String as1[] = bundle.getStringArray("participants");
            if(as1 == null || as1.length != 1)
                return null;
            else
                return new CarExtender.UnreadConversation(as, remoteinput, pendingintent, ((PendingIntent) (obj)), as1, bundle.getLong("timestamp"));
_L6:
            as[i] = ((Bundle)obj[i]).getString("text");
            if(as[i] != null)
                break; /* Loop/switch isn't completed */
            flag1 = false;
            if(true) goto _L4; else goto _L7
_L7:
            i++;
            if(true) goto _L9; else goto _L8
_L8:
            return null;
        }

        Bundle getBundleForUnreadConversation()
        {
            Bundle bundle = new Bundle();
            Parcelable aparcelable[] = null;
            String s = aparcelable;
            if(mParticipants != null)
            {
                s = aparcelable;
                if(mParticipants.length > 1)
                    s = mParticipants[0];
            }
            aparcelable = new Parcelable[mMessages.length];
            for(int i = 0; i < aparcelable.length; i++)
            {
                Bundle bundle1 = new Bundle();
                bundle1.putString("text", mMessages[i]);
                bundle1.putString("author", s);
                aparcelable[i] = bundle1;
            }

            bundle.putParcelableArray("messages", aparcelable);
            if(mRemoteInput != null)
                bundle.putParcelable("remote_input", mRemoteInput);
            bundle.putParcelable("on_reply", mReplyPendingIntent);
            bundle.putParcelable("on_read", mReadPendingIntent);
            bundle.putStringArray("participants", mParticipants);
            bundle.putLong("timestamp", mLatestTimestamp);
            return bundle;
        }

        public long getLatestTimestamp()
        {
            return mLatestTimestamp;
        }

        public String[] getMessages()
        {
            return mMessages;
        }

        public String getParticipant()
        {
            String s;
            if(mParticipants.length > 0)
                s = mParticipants[0];
            else
                s = null;
            return s;
        }

        public String[] getParticipants()
        {
            return mParticipants;
        }

        public PendingIntent getReadPendingIntent()
        {
            return mReadPendingIntent;
        }

        public RemoteInput getRemoteInput()
        {
            return mRemoteInput;
        }

        public PendingIntent getReplyPendingIntent()
        {
            return mReplyPendingIntent;
        }

        private static final String KEY_AUTHOR = "author";
        private static final String KEY_MESSAGES = "messages";
        private static final String KEY_ON_READ = "on_read";
        private static final String KEY_ON_REPLY = "on_reply";
        private static final String KEY_PARTICIPANTS = "participants";
        private static final String KEY_REMOTE_INPUT = "remote_input";
        private static final String KEY_TEXT = "text";
        private static final String KEY_TIMESTAMP = "timestamp";
        private final long mLatestTimestamp;
        private final String mMessages[];
        private final String mParticipants[];
        private final PendingIntent mReadPendingIntent;
        private final RemoteInput mRemoteInput;
        private final PendingIntent mReplyPendingIntent;

        CarExtender.UnreadConversation(String as[], RemoteInput remoteinput, PendingIntent pendingintent, PendingIntent pendingintent1, String as1[], long l)
        {
            mMessages = as;
            mRemoteInput = remoteinput;
            mReadPendingIntent = pendingintent1;
            mReplyPendingIntent = pendingintent;
            mParticipants = as1;
            mLatestTimestamp = l;
        }
    }

    public static class DecoratedCustomViewStyle extends Style
    {

        private void buildIntoRemoteViewContent(RemoteViews remoteviews, RemoteViews remoteviews1)
        {
            if(remoteviews1 != null)
            {
                remoteviews1 = remoteviews1.clone();
                remoteviews.removeAllViewsExceptId(0x1020335, 0x102000d);
                remoteviews.addView(0x1020335, remoteviews1, 0);
                remoteviews.setReapplyDisallowed();
            }
            int i = 0x1050109;
            if(Notification._2D_wrap1(Builder._2D_get2(mBuilder)))
                i = 0x105010e;
            remoteviews.setViewLayoutMarginEndDimen(0x1020335, i);
        }

        private RemoteViews makeDecoratedBigContentView()
        {
            RemoteViews remoteviews;
            if(Builder._2D_get2(mBuilder).bigContentView == null)
                remoteviews = Builder._2D_get2(mBuilder).contentView;
            else
                remoteviews = Builder._2D_get2(mBuilder).bigContentView;
            if(Builder._2D_get0(mBuilder).size() == 0)
            {
                return makeStandardTemplateWithCustomContent(remoteviews);
            } else
            {
                RemoteViews remoteviews1 = Builder._2D_wrap2(mBuilder, Builder._2D_wrap9(mBuilder));
                buildIntoRemoteViewContent(remoteviews1, remoteviews);
                return remoteviews1;
            }
        }

        private RemoteViews makeDecoratedHeadsUpContentView()
        {
            RemoteViews remoteviews;
            if(Builder._2D_get2(mBuilder).headsUpContentView == null)
                remoteviews = Builder._2D_get2(mBuilder).contentView;
            else
                remoteviews = Builder._2D_get2(mBuilder).headsUpContentView;
            if(Builder._2D_get0(mBuilder).size() == 0)
            {
                return makeStandardTemplateWithCustomContent(remoteviews);
            } else
            {
                RemoteViews remoteviews1 = Builder._2D_wrap2(mBuilder, Builder._2D_wrap9(mBuilder));
                buildIntoRemoteViewContent(remoteviews1, remoteviews);
                return remoteviews1;
            }
        }

        private RemoteViews makeStandardTemplateWithCustomContent(RemoteViews remoteviews)
        {
            RemoteViews remoteviews1 = Builder._2D_wrap5(mBuilder, Builder._2D_wrap8(mBuilder));
            buildIntoRemoteViewContent(remoteviews1, remoteviews);
            return remoteviews1;
        }

        public boolean displayCustomViewInline()
        {
            return true;
        }

        public RemoteViews makeBigContentView()
        {
            return makeDecoratedBigContentView();
        }

        public RemoteViews makeContentView(boolean flag)
        {
            return makeStandardTemplateWithCustomContent(Builder._2D_get2(mBuilder).contentView);
        }

        public RemoteViews makeHeadsUpContentView(boolean flag)
        {
            return makeDecoratedHeadsUpContentView();
        }

        public DecoratedCustomViewStyle()
        {
        }
    }

    public static class DecoratedMediaCustomViewStyle extends MediaStyle
    {

        private RemoteViews buildIntoRemoteView(RemoteViews remoteviews, int i, RemoteViews remoteviews1)
        {
            if(remoteviews1 != null)
            {
                remoteviews1 = remoteviews1.clone();
                remoteviews1.overrideTextColors(mBuilder.getPrimaryTextColor());
                remoteviews.removeAllViews(i);
                remoteviews.addView(i, remoteviews1);
                remoteviews.setReapplyDisallowed();
            }
            return remoteviews;
        }

        private RemoteViews makeBigContentViewWithCustomContent(RemoteViews remoteviews)
        {
            RemoteViews remoteviews1 = super.makeBigContentView();
            if(remoteviews1 != null)
                return buildIntoRemoteView(remoteviews1, 0x1020335, remoteviews);
            if(remoteviews != Builder._2D_get2(mBuilder).contentView)
                return buildIntoRemoteView(super.makeContentView(false), 0x1020333, remoteviews);
            else
                return null;
        }

        public boolean displayCustomViewInline()
        {
            return true;
        }

        public RemoteViews makeBigContentView()
        {
            RemoteViews remoteviews;
            if(Builder._2D_get2(mBuilder).bigContentView != null)
                remoteviews = Builder._2D_get2(mBuilder).bigContentView;
            else
                remoteviews = Builder._2D_get2(mBuilder).contentView;
            return makeBigContentViewWithCustomContent(remoteviews);
        }

        public RemoteViews makeContentView(boolean flag)
        {
            return buildIntoRemoteView(super.makeContentView(false), 0x1020333, Builder._2D_get2(mBuilder).contentView);
        }

        public RemoteViews makeHeadsUpContentView(boolean flag)
        {
            RemoteViews remoteviews;
            if(Builder._2D_get2(mBuilder).headsUpContentView != null)
                remoteviews = Builder._2D_get2(mBuilder).headsUpContentView;
            else
                remoteviews = Builder._2D_get2(mBuilder).contentView;
            return makeBigContentViewWithCustomContent(remoteviews);
        }

        public DecoratedMediaCustomViewStyle()
        {
        }
    }

    public static interface Extender
    {

        public abstract Builder extend(Builder builder);
    }

    public static class InboxStyle extends Style
    {

        private void handleInboxImageMargin(RemoteViews remoteviews, int i, boolean flag)
        {
            boolean flag1 = false;
            int j = ((flag1) ? 1 : 0);
            if(flag)
            {
                j = Builder._2D_get2(mBuilder).extras.getInt("android.progressMax", 0);
                flag = Builder._2D_get2(mBuilder).extras.getBoolean("android.progressIndeterminate");
                if(j != 0)
                    flag = true;
                j = ((flag1) ? 1 : 0);
                if(Notification._2D_wrap1(Builder._2D_get2(mBuilder)))
                {
                    j = ((flag1) ? 1 : 0);
                    if(flag ^ true)
                        j = 0x105010d;
                }
            }
            remoteviews.setViewLayoutMarginEndDimen(i, j);
        }

        public void addExtras(Bundle bundle)
        {
            super.addExtras(bundle);
            CharSequence acharsequence[] = new CharSequence[mTexts.size()];
            bundle.putCharSequenceArray("android.textLines", (CharSequence[])mTexts.toArray(acharsequence));
        }

        public InboxStyle addLine(CharSequence charsequence)
        {
            mTexts.add(Notification.safeCharSequence(charsequence));
            return this;
        }

        public RemoteViews makeBigContentView()
        {
            CharSequence charsequence = Builder._2D_get2(mBuilder).extras.getCharSequence("android.text");
            Builder._2D_wrap0(mBuilder).putCharSequence("android.text", null);
            RemoteViews remoteviews = getStandardView(Builder._2D_wrap12(mBuilder));
            Builder._2D_wrap0(mBuilder).putCharSequence("android.text", charsequence);
            int ai[] = new int[7];
            int[] _tmp = ai;
            ai[0] = 0x10202a0;
            ai[1] = 0x10202a1;
            ai[2] = 0x10202a2;
            ai[3] = 0x10202a3;
            ai[4] = 0x10202a4;
            ai[5] = 0x10202a5;
            ai[6] = 0x10202a6;
            int i = ai.length;
            for(int j = 0; j < i; j++)
                remoteviews.setViewVisibility(ai[j], 8);

            boolean flag = false;
            int l = Builder._2D_get1(mBuilder).getResources().getDimensionPixelSize(0x1050122);
            boolean flag1 = true;
            boolean flag3 = false;
            int i1 = ai.length;
            int k = ((flag3) ? 1 : 0);
            boolean flag4 = flag1;
            int j1 = ((flag) ? 1 : 0);
            i = i1;
            if(Builder._2D_get0(mBuilder).size() > 0)
            {
                i = i1 - 1;
                j1 = ((flag) ? 1 : 0);
                flag4 = flag1;
                k = ((flag3) ? 1 : 0);
            }
            while(j1 < mTexts.size() && j1 < i) 
            {
                CharSequence charsequence1 = (CharSequence)mTexts.get(j1);
                boolean flag2 = flag4;
                if(!TextUtils.isEmpty(charsequence1))
                {
                    remoteviews.setViewVisibility(ai[j1], 0);
                    remoteviews.setTextViewText(ai[j1], Builder._2D_wrap17(mBuilder, Builder._2D_wrap16(mBuilder, charsequence1)));
                    Builder._2D_wrap18(mBuilder, remoteviews, ai[j1]);
                    remoteviews.setViewPadding(ai[j1], 0, l, 0, 0);
                    handleInboxImageMargin(remoteviews, ai[j1], flag4);
                    if(flag4)
                        k = ai[j1];
                    else
                        k = 0;
                    flag2 = false;
                }
                j1++;
                flag4 = flag2;
            }
            if(k != 0)
                remoteviews.setViewPadding(k, 0, Builder._2D_get1(mBuilder).getResources().getDimensionPixelSize(0x1050136), 0, 0);
            return remoteviews;
        }

        protected void restoreFromExtras(Bundle bundle)
        {
            super.restoreFromExtras(bundle);
            mTexts.clear();
            if(bundle.containsKey("android.textLines"))
                Collections.addAll(mTexts, bundle.getCharSequenceArray("android.textLines"));
        }

        public InboxStyle setBigContentTitle(CharSequence charsequence)
        {
            internalSetBigContentTitle(Notification.safeCharSequence(charsequence));
            return this;
        }

        public InboxStyle setSummaryText(CharSequence charsequence)
        {
            internalSetSummaryText(Notification.safeCharSequence(charsequence));
            return this;
        }

        private ArrayList mTexts;

        public InboxStyle()
        {
            mTexts = new ArrayList(5);
        }

        public InboxStyle(Builder builder)
        {
            mTexts = new ArrayList(5);
            setBuilder(builder);
        }
    }

    public static class MediaStyle extends Style
    {

        private RemoteViews generateMediaActionButton(Action action, int i)
        {
            boolean flag;
            BuilderRemoteViews builderremoteviews;
            if(action.actionIntent == null)
                flag = true;
            else
                flag = false;
            builderremoteviews = new BuilderRemoteViews(Builder._2D_get1(mBuilder).getApplicationInfo(), 0x109008c);
            builderremoteviews.setImageViewIcon(0x1020172, action.getIcon());
            if(!Builder._2D_wrap7(mBuilder) && !Builder._2D_wrap6(mBuilder))
                i = NotificationColorUtil.resolveColor(Builder._2D_get1(mBuilder), 0);
            builderremoteviews.setDrawableParameters(0x1020172, false, -1, i, android.graphics.PorterDuff.Mode.SRC_ATOP, -1);
            if(!flag)
                builderremoteviews.setOnClickPendingIntent(0x1020172, action.actionIntent);
            builderremoteviews.setContentDescription(0x1020172, action.title);
            return builderremoteviews;
        }

        private int getPrimaryHighlightColor()
        {
            return Builder._2D_wrap14(mBuilder);
        }

        private void handleImage(RemoteViews remoteviews)
        {
            if(Notification._2D_wrap1(Builder._2D_get2(mBuilder)))
            {
                remoteviews.setViewLayoutMarginEndDimen(0x10202dc, 0);
                remoteviews.setViewLayoutMarginEndDimen(0x1020430, 0);
            }
        }

        private RemoteViews makeMediaBigContentView()
        {
            int i = Math.min(Builder._2D_get0(mBuilder).size(), 5);
            int j;
            if(mActionsToShowInCompact == null)
                j = 0;
            else
                j = Math.min(mActionsToShowInCompact.length, 3);
            if(!Notification._2D_wrap1(Builder._2D_get2(mBuilder)) && i <= j)
                return null;
            RemoteViews remoteviews = Builder._2D_wrap4(mBuilder, 0x109009d, false);
            if(i > 0)
            {
                remoteviews.removeAllViews(0x10202f7);
                for(int k = 0; k < i; k++)
                    remoteviews.addView(0x10202f7, generateMediaActionButton((Action)Builder._2D_get0(mBuilder).get(k), getPrimaryHighlightColor()));

            }
            handleImage(remoteviews);
            return remoteviews;
        }

        private RemoteViews makeMediaContentView()
        {
            RemoteViews remoteviews = Builder._2D_wrap4(mBuilder, 0x10900a1, false);
            int i = Builder._2D_get0(mBuilder).size();
            int j;
            if(mActionsToShowInCompact == null)
                j = 0;
            else
                j = Math.min(mActionsToShowInCompact.length, 3);
            if(j > 0)
            {
                remoteviews.removeAllViews(0x10202f7);
                for(int k = 0; k < j; k++)
                {
                    if(k >= i)
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[] {
                            Integer.valueOf(k), Integer.valueOf(i - 1)
                        }));
                    remoteviews.addView(0x10202f7, generateMediaActionButton((Action)Builder._2D_get0(mBuilder).get(mActionsToShowInCompact[k]), getPrimaryHighlightColor()));
                }

            }
            handleImage(remoteviews);
            j = 0x1050109;
            if(Notification._2D_wrap1(Builder._2D_get2(mBuilder)))
                j = 0x105010e;
            remoteviews.setViewLayoutMarginEndDimen(0x1020335, j);
            return remoteviews;
        }

        public void addExtras(Bundle bundle)
        {
            super.addExtras(bundle);
            if(mToken != null)
                bundle.putParcelable("android.mediaSession", mToken);
            if(mActionsToShowInCompact != null)
                bundle.putIntArray("android.compactActions", mActionsToShowInCompact);
        }

        public Notification buildStyled(Notification notification)
        {
            super.buildStyled(notification);
            if(notification.category == null)
                notification.category = "transport";
            return notification;
        }

        protected boolean hasProgress()
        {
            return false;
        }

        public RemoteViews makeBigContentView()
        {
            return makeMediaBigContentView();
        }

        public RemoteViews makeContentView(boolean flag)
        {
            return makeMediaContentView();
        }

        public RemoteViews makeHeadsUpContentView(boolean flag)
        {
            RemoteViews remoteviews = makeMediaBigContentView();
            if(remoteviews == null)
                remoteviews = makeMediaContentView();
            return remoteviews;
        }

        protected void restoreFromExtras(Bundle bundle)
        {
            super.restoreFromExtras(bundle);
            if(bundle.containsKey("android.mediaSession"))
                mToken = (android.media.session.MediaSession.Token)bundle.getParcelable("android.mediaSession");
            if(bundle.containsKey("android.compactActions"))
                mActionsToShowInCompact = bundle.getIntArray("android.compactActions");
        }

        public MediaStyle setMediaSession(android.media.session.MediaSession.Token token)
        {
            mToken = token;
            return this;
        }

        public transient MediaStyle setShowActionsInCompactView(int ai[])
        {
            mActionsToShowInCompact = ai;
            return this;
        }

        static final int MAX_MEDIA_BUTTONS = 5;
        static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        private int mActionsToShowInCompact[];
        private android.media.session.MediaSession.Token mToken;

        public MediaStyle()
        {
            mActionsToShowInCompact = null;
        }

        public MediaStyle(Builder builder)
        {
            mActionsToShowInCompact = null;
            setBuilder(builder);
        }
    }

    public static class MessagingStyle extends Style
    {

        private Message findLatestIncomingMessage()
        {
            for(int i = mMessages.size() - 1; i >= 0; i--)
            {
                Message message = (Message)mMessages.get(i);
                if(!TextUtils.isEmpty(Message._2D_get0(message)))
                    return message;
            }

            if(!mMessages.isEmpty())
                return (Message)mMessages.get(mMessages.size() - 1);
            else
                return null;
        }

        private void fixTitleAndTextExtras(Bundle bundle)
        {
            Message message = findLatestIncomingMessage();
            CharSequence charsequence;
            Object obj;
            if(message == null)
                charsequence = null;
            else
                charsequence = Message._2D_get1(message);
            if(message == null)
                obj = null;
            else
            if(TextUtils.isEmpty(Message._2D_get0(message)))
                obj = mUserDisplayName;
            else
                obj = Message._2D_get0(message);
            if(!TextUtils.isEmpty(mConversationTitle))
                if(!TextUtils.isEmpty(((CharSequence) (obj))))
                {
                    obj = BidiFormatter.getInstance();
                    obj = Builder._2D_get1(mBuilder).getString(0x10403f1, new Object[] {
                        ((BidiFormatter) (obj)).unicodeWrap(mConversationTitle), ((BidiFormatter) (obj)).unicodeWrap(Message._2D_get0(message))
                    });
                } else
                {
                    obj = mConversationTitle;
                }
            if(obj != null)
                bundle.putCharSequence("android.title", ((CharSequence) (obj)));
            if(charsequence != null)
                bundle.putCharSequence("android.text", charsequence);
        }

        private static TextAppearanceSpan makeFontColorSpan(int i)
        {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i), null);
        }

        private CharSequence makeMessageLine(Message message, Builder builder)
        {
            BidiFormatter bidiformatter = BidiFormatter.getInstance();
            SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
            boolean flag = Builder._2D_wrap6(builder);
            if(TextUtils.isEmpty(Message._2D_get0(message)))
            {
                Object obj;
                int i;
                if(mUserDisplayName == null)
                    obj = "";
                else
                    obj = mUserDisplayName;
                obj = bidiformatter.unicodeWrap(((CharSequence) (obj)));
                if(flag)
                    i = builder.getPrimaryTextColor();
                else
                    i = mBuilder.resolveContrastColor();
                spannablestringbuilder.append(((CharSequence) (obj)), makeFontColorSpan(i), 0);
            } else
            {
                CharSequence charsequence = bidiformatter.unicodeWrap(Message._2D_get0(message));
                int j;
                if(flag)
                    j = builder.getPrimaryTextColor();
                else
                    j = 0xff000000;
                spannablestringbuilder.append(charsequence, makeFontColorSpan(j), 0);
            }
            if(Message._2D_get1(message) == null)
                message = "";
            else
                message = Message._2D_get1(message);
            spannablestringbuilder.append("  ").append(bidiformatter.unicodeWrap(message));
            return spannablestringbuilder;
        }

        public void addExtras(Bundle bundle)
        {
            super.addExtras(bundle);
            if(mUserDisplayName != null)
                bundle.putCharSequence("android.selfDisplayName", mUserDisplayName);
            if(mConversationTitle != null)
                bundle.putCharSequence("android.conversationTitle", mConversationTitle);
            if(!mMessages.isEmpty())
                bundle.putParcelableArray("android.messages", Message.getBundleArrayForMessages(mMessages));
            if(!mHistoricMessages.isEmpty())
                bundle.putParcelableArray("android.messages.historic", Message.getBundleArrayForMessages(mHistoricMessages));
            fixTitleAndTextExtras(bundle);
        }

        public MessagingStyle addHistoricMessage(Message message)
        {
            mHistoricMessages.add(message);
            if(mHistoricMessages.size() > 25)
                mHistoricMessages.remove(0);
            return this;
        }

        public MessagingStyle addMessage(Message message)
        {
            mMessages.add(message);
            if(mMessages.size() > 25)
                mMessages.remove(0);
            return this;
        }

        public MessagingStyle addMessage(CharSequence charsequence, long l, CharSequence charsequence1)
        {
            return addMessage(new Message(charsequence, l, charsequence1));
        }

        public CharSequence getConversationTitle()
        {
            return mConversationTitle;
        }

        public List getHistoricMessages()
        {
            return mHistoricMessages;
        }

        public List getMessages()
        {
            return mMessages;
        }

        public CharSequence getUserDisplayName()
        {
            return mUserDisplayName;
        }

        public RemoteViews makeBigContentView()
        {
            Object obj;
            int i;
            if(!TextUtils.isEmpty(Style._2D_get0(this)))
                obj = Style._2D_get0(this);
            else
                obj = mConversationTitle;
            i = TextUtils.isEmpty(((CharSequence) (obj))) ^ true;
            if(mMessages.size() == 1)
            {
                Object obj1;
                if(i != 0)
                {
                    obj1 = obj;
                    obj = makeMessageLine((Message)mMessages.get(0), mBuilder);
                } else
                {
                    obj1 = Message._2D_get0((Message)mMessages.get(0));
                    obj = Message._2D_get1((Message)mMessages.get(0));
                }
                obj1 = Builder._2D_wrap1(mBuilder, Builder._2D_wrap11(mBuilder), mBuilder.mParams.reset().hasProgress(false).title(((CharSequence) (obj1))).text(null));
                BigTextStyle.applyBigTextContentView(mBuilder, ((RemoteViews) (obj1)), ((CharSequence) (obj)));
                return ((RemoteViews) (obj1));
            }
            obj = Builder._2D_wrap1(mBuilder, Builder._2D_wrap13(mBuilder), mBuilder.mParams.reset().hasProgress(false).title(((CharSequence) (obj))).text(null));
            int ai[] = new int[7];
            int[] _tmp = ai;
            ai[0] = 0x10202a0;
            ai[1] = 0x10202a1;
            ai[2] = 0x10202a2;
            ai[3] = 0x10202a3;
            ai[4] = 0x10202a4;
            ai[5] = 0x10202a5;
            ai[6] = 0x10202a6;
            int j = 0;
            for(int k = ai.length; j < k; j++)
                ((RemoteViews) (obj)).setViewVisibility(ai[j], 8);

            int l = 0;
            Message message;
            int i1;
            if(i != 0)
                j = 0x1050128;
            else
                j = 0;
            ((RemoteViews) (obj)).setViewLayoutMarginBottomDimen(0x10202dc, j);
            if(!Notification._2D_wrap1(Builder._2D_get2(mBuilder)))
                j = 0;
            else
            if(i != 0)
                j = 1;
            else
                j = 2;
            ((RemoteViews) (obj)).setInt(0x102033a, "setNumIndentLines", j);
            i = -1;
            message = findLatestIncomingMessage();
            i1 = Math.max(0, mHistoricMessages.size() - (ai.length - mMessages.size()));
            j = l;
            l = i;
            for(; i1 + j < mHistoricMessages.size() && j < ai.length; j++)
            {
                Message message1 = (Message)mHistoricMessages.get(i1 + j);
                i = ai[j];
                ((RemoteViews) (obj)).setTextViewText(i, makeMessageLine(message1, mBuilder));
                if(message == message1)
                    l = i;
            }

            i1 = Math.max(0, mMessages.size() - ai.length);
            do
            {
                i = j;
                if(i1 + j >= mMessages.size())
                    break;
                i = j;
                if(j >= ai.length)
                    break;
                Message message2 = (Message)mMessages.get(i1 + j);
                i = ai[j];
                ((RemoteViews) (obj)).setViewVisibility(i, 0);
                ((RemoteViews) (obj)).setTextViewText(i, Builder._2D_wrap17(mBuilder, makeMessageLine(message2, mBuilder)));
                Builder._2D_wrap18(mBuilder, ((RemoteViews) (obj)), i);
                if(message == message2)
                    l = i;
                j++;
            } while(true);
            for(; i < ai.length; i++)
                ((RemoteViews) (obj)).setTextViewText(ai[i], null);

            ((RemoteViews) (obj)).setInt(0x102033a, "setContractedChildId", l);
            return ((RemoteViews) (obj));
        }

        public RemoteViews makeContentView(boolean flag)
        {
            if(!flag)
            {
                Object obj = findLatestIncomingMessage();
                CharSequence charsequence;
                if(mConversationTitle != null)
                    charsequence = mConversationTitle;
                else
                if(obj == null)
                    charsequence = null;
                else
                    charsequence = Message._2D_get0(((Message) (obj)));
                if(obj == null)
                    obj = null;
                else
                if(mConversationTitle != null)
                    obj = makeMessageLine(((Message) (obj)), mBuilder);
                else
                    obj = Message._2D_get1(((Message) (obj)));
                return Builder._2D_wrap3(mBuilder, Builder._2D_wrap8(mBuilder), mBuilder.mParams.reset().hasProgress(false).title(charsequence).text(((CharSequence) (obj))));
            } else
            {
                Builder._2D_set1(mBuilder, Builder._2D_get0(mBuilder));
                Builder._2D_set0(mBuilder, new ArrayList());
                RemoteViews remoteviews = makeBigContentView();
                Builder._2D_set0(mBuilder, Builder._2D_get3(mBuilder));
                Builder._2D_set1(mBuilder, null);
                return remoteviews;
            }
        }

        public RemoteViews makeHeadsUpContentView(boolean flag)
        {
            if(flag)
                return makeBigContentView();
            Object obj = findLatestIncomingMessage();
            CharSequence charsequence;
            if(mConversationTitle != null)
                charsequence = mConversationTitle;
            else
            if(obj == null)
                charsequence = null;
            else
                charsequence = Message._2D_get0(((Message) (obj)));
            if(obj == null)
                obj = null;
            else
            if(mConversationTitle != null)
                obj = makeMessageLine(((Message) (obj)), mBuilder);
            else
                obj = Message._2D_get1(((Message) (obj)));
            return Builder._2D_wrap1(mBuilder, Builder._2D_wrap9(mBuilder), mBuilder.mParams.reset().hasProgress(false).title(charsequence).text(((CharSequence) (obj))));
        }

        protected void restoreFromExtras(Bundle bundle)
        {
            super.restoreFromExtras(bundle);
            mMessages.clear();
            mHistoricMessages.clear();
            mUserDisplayName = bundle.getCharSequence("android.selfDisplayName");
            mConversationTitle = bundle.getCharSequence("android.conversationTitle");
            Parcelable aparcelable[] = bundle.getParcelableArray("android.messages");
            if(aparcelable != null && (aparcelable instanceof Parcelable[]))
                mMessages = Message.getMessagesFromBundleArray(aparcelable);
            bundle = bundle.getParcelableArray("android.messages.historic");
            if(bundle != null && (bundle instanceof Parcelable[]))
                mHistoricMessages = Message.getMessagesFromBundleArray(bundle);
        }

        public MessagingStyle setConversationTitle(CharSequence charsequence)
        {
            mConversationTitle = charsequence;
            return this;
        }

        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        CharSequence mConversationTitle;
        List mHistoricMessages;
        List mMessages;
        CharSequence mUserDisplayName;

        MessagingStyle()
        {
            mMessages = new ArrayList();
            mHistoricMessages = new ArrayList();
        }

        public MessagingStyle(CharSequence charsequence)
        {
            mMessages = new ArrayList();
            mHistoricMessages = new ArrayList();
            mUserDisplayName = charsequence;
        }
    }

    public static final class MessagingStyle.Message
    {

        static CharSequence _2D_get0(MessagingStyle.Message message)
        {
            return message.mSender;
        }

        static CharSequence _2D_get1(MessagingStyle.Message message)
        {
            return message.mText;
        }

        static Bundle[] getBundleArrayForMessages(List list)
        {
            Bundle abundle[] = new Bundle[list.size()];
            int i = list.size();
            for(int j = 0; j < i; j++)
                abundle[j] = ((MessagingStyle.Message)list.get(j)).toBundle();

            return abundle;
        }

        static MessagingStyle.Message getMessageFromBundle(Bundle bundle)
        {
            if(!bundle.containsKey("text") || bundle.containsKey("time") ^ true)
                return null;
            MessagingStyle.Message message;
            try
            {
                message = JVM INSTR new #2   <Class Notification$MessagingStyle$Message>;
                message.MessagingStyle.Message(bundle.getCharSequence("text"), bundle.getLong("time"), bundle.getCharSequence("sender"));
                if(bundle.containsKey("type") && bundle.containsKey("uri"))
                    message.setData(bundle.getString("type"), (Uri)bundle.getParcelable("uri"));
                if(bundle.containsKey("extras"))
                    message.getExtras().putAll(bundle.getBundle("extras"));
            }
            // Misplaced declaration of an exception variable
            catch(Bundle bundle)
            {
                return null;
            }
            return message;
        }

        static List getMessagesFromBundleArray(Parcelable aparcelable[])
        {
            ArrayList arraylist = new ArrayList(aparcelable.length);
            for(int i = 0; i < aparcelable.length; i++)
            {
                if(!(aparcelable[i] instanceof Bundle))
                    continue;
                MessagingStyle.Message message = getMessageFromBundle((Bundle)aparcelable[i]);
                if(message != null)
                    arraylist.add(message);
            }

            return arraylist;
        }

        private Bundle toBundle()
        {
            Bundle bundle = new Bundle();
            if(mText != null)
                bundle.putCharSequence("text", mText);
            bundle.putLong("time", mTimestamp);
            if(mSender != null)
                bundle.putCharSequence("sender", mSender);
            if(mDataMimeType != null)
                bundle.putString("type", mDataMimeType);
            if(mDataUri != null)
                bundle.putParcelable("uri", mDataUri);
            if(mExtras != null)
                bundle.putBundle("extras", mExtras);
            return bundle;
        }

        public String getDataMimeType()
        {
            return mDataMimeType;
        }

        public Uri getDataUri()
        {
            return mDataUri;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public CharSequence getSender()
        {
            return mSender;
        }

        public CharSequence getText()
        {
            return mText;
        }

        public long getTimestamp()
        {
            return mTimestamp;
        }

        public MessagingStyle.Message setData(String s, Uri uri)
        {
            mDataMimeType = s;
            mDataUri = uri;
            return this;
        }

        static final String KEY_DATA_MIME_TYPE = "type";
        static final String KEY_DATA_URI = "uri";
        static final String KEY_EXTRAS_BUNDLE = "extras";
        static final String KEY_SENDER = "sender";
        static final String KEY_TEXT = "text";
        static final String KEY_TIMESTAMP = "time";
        private String mDataMimeType;
        private Uri mDataUri;
        private Bundle mExtras;
        private final CharSequence mSender;
        private final CharSequence mText;
        private final long mTimestamp;

        public MessagingStyle.Message(CharSequence charsequence, long l, CharSequence charsequence1)
        {
            mExtras = new Bundle();
            mText = charsequence;
            mTimestamp = l;
            mSender = charsequence1;
        }
    }

    private static class StandardTemplateParams
    {

        final StandardTemplateParams ambient(boolean flag)
        {
            boolean flag1 = false;
            boolean flag2 = flag1;
            if(title == null)
            {
                flag2 = flag1;
                if(text == null)
                    flag2 = true;
            }
            Preconditions.checkState(flag2, "must set ambient before text");
            ambient = flag;
            return this;
        }

        final StandardTemplateParams fillTextsFrom(Builder builder)
        {
            CharSequence charsequence1;
label0:
            {
                Bundle bundle = Builder._2D_get2(builder).extras;
                title = Builder._2D_wrap15(builder, bundle.getCharSequence("android.title"), ambient);
                CharSequence charsequence = bundle.getCharSequence("android.bigText");
                if(ambient)
                {
                    charsequence1 = charsequence;
                    if(!TextUtils.isEmpty(charsequence))
                        break label0;
                }
                charsequence1 = bundle.getCharSequence("android.text");
            }
            text = Builder._2D_wrap15(builder, charsequence1, ambient);
            return this;
        }

        final StandardTemplateParams hasProgress(boolean flag)
        {
            hasProgress = flag;
            return this;
        }

        final StandardTemplateParams reset()
        {
            hasProgress = true;
            ambient = false;
            title = null;
            text = null;
            return this;
        }

        final StandardTemplateParams text(CharSequence charsequence)
        {
            text = charsequence;
            return this;
        }

        final StandardTemplateParams title(CharSequence charsequence)
        {
            title = charsequence;
            return this;
        }

        boolean ambient;
        boolean hasProgress;
        CharSequence text;
        CharSequence title;

        private StandardTemplateParams()
        {
            hasProgress = true;
            ambient = false;
        }

        StandardTemplateParams(StandardTemplateParams standardtemplateparams)
        {
            this();
        }
    }

    public static abstract class Style
    {

        static CharSequence _2D_get0(Style style)
        {
            return style.mBigContentTitle;
        }

        public void addExtras(Bundle bundle)
        {
            if(mSummaryTextSet)
                bundle.putCharSequence("android.summaryText", mSummaryText);
            if(mBigContentTitle != null)
                bundle.putCharSequence("android.title.big", mBigContentTitle);
            bundle.putString("android.template", getClass().getName());
        }

        public Notification build()
        {
            checkBuilder();
            return mBuilder.build();
        }

        public Notification buildStyled(Notification notification)
        {
            addExtras(notification.extras);
            return notification;
        }

        protected void checkBuilder()
        {
            if(mBuilder == null)
                throw new IllegalArgumentException("Style requires a valid Builder object");
            else
                return;
        }

        public boolean displayCustomViewInline()
        {
            return false;
        }

        protected RemoteViews getStandardView(int i)
        {
            checkBuilder();
            CharSequence charsequence = Builder._2D_wrap0(mBuilder).getCharSequence("android.title");
            if(mBigContentTitle != null)
                mBuilder.setContentTitle(mBigContentTitle);
            RemoteViews remoteviews = Builder._2D_wrap2(mBuilder, i);
            Builder._2D_wrap0(mBuilder).putCharSequence("android.title", charsequence);
            if(mBigContentTitle != null && mBigContentTitle.equals(""))
                remoteviews.setViewVisibility(0x10202dc, 8);
            else
                remoteviews.setViewVisibility(0x10202dc, 0);
            return remoteviews;
        }

        protected boolean hasProgress()
        {
            return true;
        }

        public boolean hasSummaryInHeader()
        {
            return true;
        }

        protected void internalSetBigContentTitle(CharSequence charsequence)
        {
            mBigContentTitle = charsequence;
        }

        protected void internalSetSummaryText(CharSequence charsequence)
        {
            mSummaryText = charsequence;
            mSummaryTextSet = true;
        }

        public RemoteViews makeBigContentView()
        {
            return null;
        }

        public RemoteViews makeContentView(boolean flag)
        {
            return null;
        }

        public RemoteViews makeHeadsUpContentView(boolean flag)
        {
            return null;
        }

        public void purgeResources()
        {
        }

        public void reduceImageSizes(Context context)
        {
        }

        protected void restoreFromExtras(Bundle bundle)
        {
            if(bundle.containsKey("android.summaryText"))
            {
                mSummaryText = bundle.getCharSequence("android.summaryText");
                mSummaryTextSet = true;
            }
            if(bundle.containsKey("android.title.big"))
                mBigContentTitle = bundle.getCharSequence("android.title.big");
        }

        public void setBuilder(Builder builder)
        {
            if(mBuilder != builder)
            {
                mBuilder = builder;
                if(mBuilder != null)
                    mBuilder.setStyle(this);
            }
        }

        private CharSequence mBigContentTitle;
        protected Builder mBuilder;
        protected CharSequence mSummaryText;
        protected boolean mSummaryTextSet;

        public Style()
        {
            mSummaryText = null;
            mSummaryTextSet = false;
        }
    }

    public static final class TvExtender
        implements Extender
    {

        public Builder extend(Builder builder)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("flags", mFlags);
            bundle.putString("channel_id", mChannelId);
            if(mContentIntent != null)
                bundle.putParcelable("content_intent", mContentIntent);
            if(mDeleteIntent != null)
                bundle.putParcelable("delete_intent", mDeleteIntent);
            builder.getExtras().putBundle("android.tv.EXTENSIONS", bundle);
            return builder;
        }

        public String getChannel()
        {
            return mChannelId;
        }

        public String getChannelId()
        {
            return mChannelId;
        }

        public PendingIntent getContentIntent()
        {
            return mContentIntent;
        }

        public PendingIntent getDeleteIntent()
        {
            return mDeleteIntent;
        }

        public boolean isAvailableOnTv()
        {
            boolean flag = false;
            if((mFlags & 1) != 0)
                flag = true;
            return flag;
        }

        public TvExtender setChannel(String s)
        {
            mChannelId = s;
            return this;
        }

        public TvExtender setChannelId(String s)
        {
            mChannelId = s;
            return this;
        }

        public TvExtender setContentIntent(PendingIntent pendingintent)
        {
            mContentIntent = pendingintent;
            return this;
        }

        public TvExtender setDeleteIntent(PendingIntent pendingintent)
        {
            mDeleteIntent = pendingintent;
            return this;
        }

        private static final String EXTRA_CHANNEL_ID = "channel_id";
        private static final String EXTRA_CONTENT_INTENT = "content_intent";
        private static final String EXTRA_DELETE_INTENT = "delete_intent";
        private static final String EXTRA_FLAGS = "flags";
        private static final String EXTRA_TV_EXTENDER = "android.tv.EXTENSIONS";
        private static final int FLAG_AVAILABLE_ON_TV = 1;
        private static final String TAG = "TvExtender";
        private String mChannelId;
        private PendingIntent mContentIntent;
        private PendingIntent mDeleteIntent;
        private int mFlags;

        public TvExtender()
        {
            mFlags = 1;
        }

        public TvExtender(Notification notification)
        {
            if(notification.extras == null)
                notification = null;
            else
                notification = notification.extras.getBundle("android.tv.EXTENSIONS");
            if(notification != null)
            {
                mFlags = notification.getInt("flags");
                mChannelId = notification.getString("channel_id");
                mContentIntent = (PendingIntent)notification.getParcelable("content_intent");
                mDeleteIntent = (PendingIntent)notification.getParcelable("delete_intent");
            }
        }
    }

    public static final class WearableExtender
        implements Extender
    {

        private void setFlag(int i, boolean flag)
        {
            if(flag)
                mFlags = mFlags | i;
            else
                mFlags = mFlags & i;
        }

        public WearableExtender addAction(Action action)
        {
            mActions.add(action);
            return this;
        }

        public WearableExtender addActions(List list)
        {
            mActions.addAll(list);
            return this;
        }

        public WearableExtender addPage(Notification notification)
        {
            mPages.add(notification);
            return this;
        }

        public WearableExtender addPages(List list)
        {
            mPages.addAll(list);
            return this;
        }

        public WearableExtender clearActions()
        {
            mActions.clear();
            return this;
        }

        public WearableExtender clearPages()
        {
            mPages.clear();
            return this;
        }

        public WearableExtender clone()
        {
            WearableExtender wearableextender = new WearableExtender();
            wearableextender.mActions = new ArrayList(mActions);
            wearableextender.mFlags = mFlags;
            wearableextender.mDisplayIntent = mDisplayIntent;
            wearableextender.mPages = new ArrayList(mPages);
            wearableextender.mBackground = mBackground;
            wearableextender.mContentIcon = mContentIcon;
            wearableextender.mContentIconGravity = mContentIconGravity;
            wearableextender.mContentActionIndex = mContentActionIndex;
            wearableextender.mCustomSizePreset = mCustomSizePreset;
            wearableextender.mCustomContentHeight = mCustomContentHeight;
            wearableextender.mGravity = mGravity;
            wearableextender.mHintScreenTimeout = mHintScreenTimeout;
            wearableextender.mDismissalId = mDismissalId;
            wearableextender.mBridgeTag = mBridgeTag;
            return wearableextender;
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public Builder extend(Builder builder)
        {
            Bundle bundle = new Bundle();
            if(!mActions.isEmpty())
                bundle.putParcelableArrayList("actions", mActions);
            if(mFlags != 1)
                bundle.putInt("flags", mFlags);
            if(mDisplayIntent != null)
                bundle.putParcelable("displayIntent", mDisplayIntent);
            if(!mPages.isEmpty())
                bundle.putParcelableArray("pages", (Parcelable[])mPages.toArray(new Notification[mPages.size()]));
            if(mBackground != null)
                bundle.putParcelable("background", mBackground);
            if(mContentIcon != 0)
                bundle.putInt("contentIcon", mContentIcon);
            if(mContentIconGravity != 0x800005)
                bundle.putInt("contentIconGravity", mContentIconGravity);
            if(mContentActionIndex != -1)
                bundle.putInt("contentActionIndex", mContentActionIndex);
            if(mCustomSizePreset != 0)
                bundle.putInt("customSizePreset", mCustomSizePreset);
            if(mCustomContentHeight != 0)
                bundle.putInt("customContentHeight", mCustomContentHeight);
            if(mGravity != 80)
                bundle.putInt("gravity", mGravity);
            if(mHintScreenTimeout != 0)
                bundle.putInt("hintScreenTimeout", mHintScreenTimeout);
            if(mDismissalId != null)
                bundle.putString("dismissalId", mDismissalId);
            if(mBridgeTag != null)
                bundle.putString("bridgeTag", mBridgeTag);
            builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
            return builder;
        }

        public List getActions()
        {
            return mActions;
        }

        public Bitmap getBackground()
        {
            return mBackground;
        }

        public String getBridgeTag()
        {
            return mBridgeTag;
        }

        public int getContentAction()
        {
            return mContentActionIndex;
        }

        public int getContentIcon()
        {
            return mContentIcon;
        }

        public int getContentIconGravity()
        {
            return mContentIconGravity;
        }

        public boolean getContentIntentAvailableOffline()
        {
            boolean flag = false;
            if((mFlags & 1) != 0)
                flag = true;
            return flag;
        }

        public int getCustomContentHeight()
        {
            return mCustomContentHeight;
        }

        public int getCustomSizePreset()
        {
            return mCustomSizePreset;
        }

        public String getDismissalId()
        {
            return mDismissalId;
        }

        public PendingIntent getDisplayIntent()
        {
            return mDisplayIntent;
        }

        public int getGravity()
        {
            return mGravity;
        }

        public boolean getHintAmbientBigPicture()
        {
            boolean flag = false;
            if((mFlags & 0x20) != 0)
                flag = true;
            return flag;
        }

        public boolean getHintAvoidBackgroundClipping()
        {
            boolean flag = false;
            if((mFlags & 0x10) != 0)
                flag = true;
            return flag;
        }

        public boolean getHintContentIntentLaunchesActivity()
        {
            boolean flag = false;
            if((mFlags & 0x40) != 0)
                flag = true;
            return flag;
        }

        public boolean getHintHideIcon()
        {
            boolean flag = false;
            if((mFlags & 2) != 0)
                flag = true;
            return flag;
        }

        public int getHintScreenTimeout()
        {
            return mHintScreenTimeout;
        }

        public boolean getHintShowBackgroundOnly()
        {
            boolean flag = false;
            if((mFlags & 4) != 0)
                flag = true;
            return flag;
        }

        public List getPages()
        {
            return mPages;
        }

        public boolean getStartScrollBottom()
        {
            boolean flag = false;
            if((mFlags & 8) != 0)
                flag = true;
            return flag;
        }

        public WearableExtender setBackground(Bitmap bitmap)
        {
            mBackground = bitmap;
            return this;
        }

        public WearableExtender setBridgeTag(String s)
        {
            mBridgeTag = s;
            return this;
        }

        public WearableExtender setContentAction(int i)
        {
            mContentActionIndex = i;
            return this;
        }

        public WearableExtender setContentIcon(int i)
        {
            mContentIcon = i;
            return this;
        }

        public WearableExtender setContentIconGravity(int i)
        {
            mContentIconGravity = i;
            return this;
        }

        public WearableExtender setContentIntentAvailableOffline(boolean flag)
        {
            setFlag(1, flag);
            return this;
        }

        public WearableExtender setCustomContentHeight(int i)
        {
            mCustomContentHeight = i;
            return this;
        }

        public WearableExtender setCustomSizePreset(int i)
        {
            mCustomSizePreset = i;
            return this;
        }

        public WearableExtender setDismissalId(String s)
        {
            mDismissalId = s;
            return this;
        }

        public WearableExtender setDisplayIntent(PendingIntent pendingintent)
        {
            mDisplayIntent = pendingintent;
            return this;
        }

        public WearableExtender setGravity(int i)
        {
            mGravity = i;
            return this;
        }

        public WearableExtender setHintAmbientBigPicture(boolean flag)
        {
            setFlag(32, flag);
            return this;
        }

        public WearableExtender setHintAvoidBackgroundClipping(boolean flag)
        {
            setFlag(16, flag);
            return this;
        }

        public WearableExtender setHintContentIntentLaunchesActivity(boolean flag)
        {
            setFlag(64, flag);
            return this;
        }

        public WearableExtender setHintHideIcon(boolean flag)
        {
            setFlag(2, flag);
            return this;
        }

        public WearableExtender setHintScreenTimeout(int i)
        {
            mHintScreenTimeout = i;
            return this;
        }

        public WearableExtender setHintShowBackgroundOnly(boolean flag)
        {
            setFlag(4, flag);
            return this;
        }

        public WearableExtender setStartScrollBottom(boolean flag)
        {
            setFlag(8, flag);
            return this;
        }

        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 0x800005;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        public static final int SCREEN_TIMEOUT_LONG = -1;
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList mActions;
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags;
        private int mGravity;
        private int mHintScreenTimeout;
        private ArrayList mPages;

        public WearableExtender()
        {
            mActions = new ArrayList();
            mFlags = 1;
            mPages = new ArrayList();
            mContentIconGravity = 0x800005;
            mContentActionIndex = -1;
            mCustomSizePreset = 0;
            mGravity = 80;
        }

        public WearableExtender(Notification notification)
        {
            mActions = new ArrayList();
            mFlags = 1;
            mPages = new ArrayList();
            mContentIconGravity = 0x800005;
            mContentActionIndex = -1;
            mCustomSizePreset = 0;
            mGravity = 80;
            notification = notification.extras.getBundle("android.wearable.EXTENSIONS");
            if(notification != null)
            {
                ArrayList arraylist = notification.getParcelableArrayList("actions");
                if(arraylist != null)
                    mActions.addAll(arraylist);
                mFlags = notification.getInt("flags", 1);
                mDisplayIntent = (PendingIntent)notification.getParcelable("displayIntent");
                Notification anotification[] = Notification._2D_wrap0(notification, "pages");
                if(anotification != null)
                    Collections.addAll(mPages, anotification);
                mBackground = (Bitmap)notification.getParcelable("background");
                mContentIcon = notification.getInt("contentIcon");
                mContentIconGravity = notification.getInt("contentIconGravity", 0x800005);
                mContentActionIndex = notification.getInt("contentActionIndex", -1);
                mCustomSizePreset = notification.getInt("customSizePreset", 0);
                mCustomContentHeight = notification.getInt("customContentHeight");
                mGravity = notification.getInt("gravity", 80);
                mHintScreenTimeout = notification.getInt("hintScreenTimeout");
                mDismissalId = notification.getString("dismissalId");
                mBridgeTag = notification.getString("bridgeTag");
            }
        }
    }


    static long _2D_get0(Notification notification)
    {
        return notification.creationTime;
    }

    static Icon _2D_get1(Notification notification)
    {
        return notification.mLargeIcon;
    }

    static Icon _2D_get2(Notification notification)
    {
        return notification.mSmallIcon;
    }

    static long _2D_set0(Notification notification, long l)
    {
        notification.creationTime = l;
        return l;
    }

    static int _2D_set1(Notification notification, int i)
    {
        notification.mBadgeIcon = i;
        return i;
    }

    static long _2D_set10(Notification notification, long l)
    {
        notification.mTimeout = l;
        return l;
    }

    static String _2D_set2(Notification notification, String s)
    {
        notification.mChannelId = s;
        return s;
    }

    static int _2D_set3(Notification notification, int i)
    {
        notification.mGroupAlertBehavior = i;
        return i;
    }

    static String _2D_set4(Notification notification, String s)
    {
        notification.mGroupKey = s;
        return s;
    }

    static Icon _2D_set5(Notification notification, Icon icon1)
    {
        notification.mLargeIcon = icon1;
        return icon1;
    }

    static CharSequence _2D_set6(Notification notification, CharSequence charsequence)
    {
        notification.mSettingsText = charsequence;
        return charsequence;
    }

    static String _2D_set7(Notification notification, String s)
    {
        notification.mShortcutId = s;
        return s;
    }

    static Icon _2D_set8(Notification notification, Icon icon1)
    {
        notification.mSmallIcon = icon1;
        return icon1;
    }

    static String _2D_set9(Notification notification, String s)
    {
        notification.mSortKey = s;
        return s;
    }

    static Notification[] _2D_wrap0(Bundle bundle, String s)
    {
        return getNotificationArrayFromBundle(bundle, s);
    }

    static boolean _2D_wrap1(Notification notification)
    {
        return notification.hasLargeIcon();
    }

    public Notification()
    {
        number = 0;
        audioStreamType = -1;
        audioAttributes = AUDIO_ATTRIBUTES_DEFAULT;
        color = 0;
        extras = new Bundle();
        mGroupAlertBehavior = 0;
        mBadgeIcon = 0;
        extraNotification = new MiuiNotification();
        when = System.currentTimeMillis();
        creationTime = System.currentTimeMillis();
        priority = 0;
    }

    public Notification(int i, CharSequence charsequence, long l)
    {
        number = 0;
        audioStreamType = -1;
        audioAttributes = AUDIO_ATTRIBUTES_DEFAULT;
        color = 0;
        extras = new Bundle();
        mGroupAlertBehavior = 0;
        mBadgeIcon = 0;
        extraNotification = new MiuiNotification();
        icon = i;
        tickerText = charsequence;
        when = l;
        creationTime = System.currentTimeMillis();
    }

    public Notification(Context context, int i, CharSequence charsequence, long l, CharSequence charsequence1, CharSequence charsequence2, 
            Intent intent)
    {
        number = 0;
        audioStreamType = -1;
        audioAttributes = AUDIO_ATTRIBUTES_DEFAULT;
        color = 0;
        extras = new Bundle();
        mGroupAlertBehavior = 0;
        mBadgeIcon = 0;
        extraNotification = new MiuiNotification();
        (new Builder(context)).setWhen(l).setSmallIcon(i).setTicker(charsequence).setContentTitle(charsequence1).setContentText(charsequence2).setContentIntent(PendingIntent.getActivity(context, 0, intent, 0)).buildInto(this);
    }

    public Notification(Parcel parcel)
    {
        number = 0;
        audioStreamType = -1;
        audioAttributes = AUDIO_ATTRIBUTES_DEFAULT;
        color = 0;
        extras = new Bundle();
        mGroupAlertBehavior = 0;
        mBadgeIcon = 0;
        extraNotification = new MiuiNotification();
        readFromParcelImpl(parcel);
        allPendingIntents = parcel.readArraySet(null);
    }

    public static void addFieldsFromContext(Context context, Notification notification)
    {
        addFieldsFromContext(context.getApplicationInfo(), notification);
    }

    public static void addFieldsFromContext(ApplicationInfo applicationinfo, Notification notification)
    {
        notification.extras.putParcelable("android.appInfo", applicationinfo);
    }

    private static Notification[] getNotificationArrayFromBundle(Bundle bundle, String s)
    {
        Object aobj[] = bundle.getParcelableArray(s);
        if((aobj instanceof Notification[]) || aobj == null)
        {
            return (Notification[])aobj;
        } else
        {
            aobj = (Notification[])Arrays.copyOf(aobj, aobj.length, [Landroid/app/Notification;);
            bundle.putParcelableArray(s, ((Parcelable []) (aobj)));
            return ((Notification []) (aobj));
        }
    }

    public static Class getNotificationStyleClass(String s)
    {
        int i = 0;
        Class aclass[] = new Class[7];
        aclass[0] = android/app/Notification$BigTextStyle;
        aclass[1] = android/app/Notification$BigPictureStyle;
        aclass[2] = android/app/Notification$InboxStyle;
        aclass[3] = android/app/Notification$MediaStyle;
        aclass[4] = android/app/Notification$DecoratedCustomViewStyle;
        aclass[5] = android/app/Notification$DecoratedMediaCustomViewStyle;
        aclass[6] = android/app/Notification$MessagingStyle;
        for(int j = aclass.length; i < j; i++)
        {
            Class class1 = aclass[i];
            if(s.equals(class1.getName()))
                return class1;
        }

        return null;
    }

    private boolean hasColorizedPermission()
    {
        boolean flag = false;
        if((flags & 0x800) != 0)
            flag = true;
        return flag;
    }

    private boolean hasLargeIcon()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mLargeIcon == null)
            if(largeIcon != null)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private boolean isForegroundService()
    {
        boolean flag = false;
        if((flags & 0x40) != 0)
            flag = true;
        return flag;
    }

    public static String priorityToString(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("UNKNOWN(").append(String.valueOf(i)).append(")").toString();

        case -2: 
            return "MIN";

        case -1: 
            return "LOW";

        case 0: // '\0'
            return "DEFAULT";

        case 1: // '\001'
            return "HIGH";

        case 2: // '\002'
            return "MAX";
        }
    }

    private void readFromParcelImpl(Parcel parcel)
    {
        parcel.readInt();
        whitelistToken = parcel.readStrongBinder();
        if(whitelistToken == null)
            whitelistToken = processWhitelistToken;
        parcel.setClassCookie(android/app/PendingIntent, whitelistToken);
        when = parcel.readLong();
        creationTime = parcel.readLong();
        if(parcel.readInt() != 0)
        {
            mSmallIcon = (Icon)Icon.CREATOR.createFromParcel(parcel);
            if(mSmallIcon.getType() == 2)
                icon = mSmallIcon.getResId();
        }
        number = parcel.readInt();
        if(parcel.readInt() != 0)
            contentIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
        if(parcel.readInt() != 0)
            deleteIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
        if(parcel.readInt() != 0)
            tickerText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        if(parcel.readInt() != 0)
            tickerView = (RemoteViews)RemoteViews.CREATOR.createFromParcel(parcel);
        if(parcel.readInt() != 0)
            contentView = (RemoteViews)RemoteViews.CREATOR.createFromParcel(parcel);
        if(parcel.readInt() != 0)
            mLargeIcon = (Icon)Icon.CREATOR.createFromParcel(parcel);
        defaults = parcel.readInt();
        flags = parcel.readInt();
        if(parcel.readInt() != 0)
            sound = (Uri)Uri.CREATOR.createFromParcel(parcel);
        audioStreamType = parcel.readInt();
        if(parcel.readInt() != 0)
            audioAttributes = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(parcel);
        vibrate = parcel.createLongArray();
        ledARGB = parcel.readInt();
        ledOnMS = parcel.readInt();
        ledOffMS = parcel.readInt();
        iconLevel = parcel.readInt();
        if(parcel.readInt() != 0)
            fullScreenIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
        priority = parcel.readInt();
        category = parcel.readString();
        mGroupKey = parcel.readString();
        mSortKey = parcel.readString();
        extras = Bundle.setDefusable(parcel.readBundle(), true);
        actions = (Action[])parcel.createTypedArray(Action.CREATOR);
        if(parcel.readInt() != 0)
            bigContentView = (RemoteViews)RemoteViews.CREATOR.createFromParcel(parcel);
        if(parcel.readInt() != 0)
            headsUpContentView = (RemoteViews)RemoteViews.CREATOR.createFromParcel(parcel);
        visibility = parcel.readInt();
        if(parcel.readInt() != 0)
            publicVersion = (Notification)CREATOR.createFromParcel(parcel);
        color = parcel.readInt();
        if(parcel.readInt() != 0)
            mChannelId = parcel.readString();
        mTimeout = parcel.readLong();
        if(parcel.readInt() != 0)
            mShortcutId = parcel.readString();
        mBadgeIcon = parcel.readInt();
        if(parcel.readInt() != 0)
            mSettingsText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mGroupAlertBehavior = parcel.readInt();
        extraNotification.readFromParcel(parcel);
    }

    private void reduceImageSizesForRemoteView(RemoteViews remoteviews, Context context, boolean flag)
    {
        if(remoteviews != null)
        {
            context = context.getResources();
            int i;
            int j;
            if(flag)
                i = 0x1050112;
            else
                i = 0x1050111;
            j = context.getDimensionPixelSize(i);
            if(flag)
                i = 0x1050110;
            else
                i = 0x105010f;
            remoteviews.reduceImageSizes(j, context.getDimensionPixelSize(i));
        }
    }

    private static CharSequence removeTextSizeSpans(CharSequence charsequence)
    {
        Spanned spanned;
        Object aobj[];
        SpannableStringBuilder spannablestringbuilder;
        int i;
        int j;
        Object obj;
        if(charsequence instanceof Spanned)
        {
            spanned = (Spanned)charsequence;
            aobj = spanned.getSpans(0, spanned.length(), java/lang/Object);
            spannablestringbuilder = new SpannableStringBuilder(spanned.toString());
            i = aobj.length;
            j = 0;
        } else
        {
            return charsequence;
        }
        if(j >= i) goto _L2; else goto _L1
_L1:
        charsequence = ((CharSequence) (aobj[j]));
        obj = charsequence;
        if(charsequence instanceof CharacterStyle)
            obj = ((CharacterStyle)charsequence).getUnderlying();
        if(!(obj instanceof TextAppearanceSpan)) goto _L4; else goto _L3
_L3:
        obj = (TextAppearanceSpan)obj;
        obj = new TextAppearanceSpan(((TextAppearanceSpan) (obj)).getFamily(), ((TextAppearanceSpan) (obj)).getTextStyle(), -1, ((TextAppearanceSpan) (obj)).getTextColor(), ((TextAppearanceSpan) (obj)).getLinkTextColor());
_L7:
        spannablestringbuilder.setSpan(obj, spanned.getSpanStart(charsequence), spanned.getSpanEnd(charsequence), spanned.getSpanFlags(charsequence));
_L6:
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        if((obj instanceof RelativeSizeSpan) || (obj instanceof AbsoluteSizeSpan)) goto _L6; else goto _L5
_L5:
        obj = charsequence;
        if(true) goto _L7; else goto _L2
_L2:
        return spannablestringbuilder;
        if(true) goto _L9; else goto _L8
_L9:
        break MISSING_BLOCK_LABEL_49;
_L8:
    }

    public static CharSequence safeCharSequence(CharSequence charsequence)
    {
        if(charsequence == null)
            return charsequence;
        CharSequence charsequence1 = charsequence;
        if(charsequence.length() > 5120)
            charsequence1 = charsequence.subSequence(0, 5120);
        if(charsequence1 instanceof Parcelable)
        {
            Log.e("Notification", (new StringBuilder()).append("warning: ").append(charsequence1.getClass().getCanonicalName()).append(" instance is a custom Parcelable and not allowed in Notification").toString());
            return charsequence1.toString();
        } else
        {
            return removeTextSizeSpans(charsequence1);
        }
    }

    public static String visibilityToString(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("UNKNOWN(").append(String.valueOf(i)).append(")").toString();

        case 0: // '\0'
            return "PRIVATE";

        case 1: // '\001'
            return "PUBLIC";

        case -1: 
            return "SECRET";
        }
    }

    private void writeToParcelImpl(Parcel parcel, int i)
    {
        parcel.writeInt(1);
        parcel.writeStrongBinder(whitelistToken);
        parcel.writeLong(when);
        parcel.writeLong(creationTime);
        if(mSmallIcon == null && icon != 0)
            mSmallIcon = Icon.createWithResource("", icon);
        if(mSmallIcon != null)
        {
            parcel.writeInt(1);
            mSmallIcon.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(number);
        if(contentIntent != null)
        {
            parcel.writeInt(1);
            contentIntent.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        if(deleteIntent != null)
        {
            parcel.writeInt(1);
            deleteIntent.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        if(tickerText != null)
        {
            parcel.writeInt(1);
            TextUtils.writeToParcel(tickerText, parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        if(tickerView != null)
        {
            parcel.writeInt(1);
            tickerView.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        if(contentView != null)
        {
            parcel.writeInt(1);
            contentView.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        if(mLargeIcon == null && largeIcon != null)
            mLargeIcon = Icon.createWithBitmap(largeIcon);
        if(mLargeIcon != null)
        {
            parcel.writeInt(1);
            mLargeIcon.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(defaults);
        parcel.writeInt(flags);
        if(sound != null)
        {
            parcel.writeInt(1);
            sound.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(audioStreamType);
        if(audioAttributes != null)
        {
            parcel.writeInt(1);
            audioAttributes.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeLongArray(vibrate);
        parcel.writeInt(ledARGB);
        parcel.writeInt(ledOnMS);
        parcel.writeInt(ledOffMS);
        parcel.writeInt(iconLevel);
        if(fullScreenIntent != null)
        {
            parcel.writeInt(1);
            fullScreenIntent.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(priority);
        parcel.writeString(category);
        parcel.writeString(mGroupKey);
        parcel.writeString(mSortKey);
        parcel.writeBundle(extras);
        parcel.writeTypedArray(actions, 0);
        if(bigContentView != null)
        {
            parcel.writeInt(1);
            bigContentView.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        if(headsUpContentView != null)
        {
            parcel.writeInt(1);
            headsUpContentView.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(visibility);
        if(publicVersion != null)
        {
            parcel.writeInt(1);
            publicVersion.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(color);
        if(mChannelId != null)
        {
            parcel.writeInt(1);
            parcel.writeString(mChannelId);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeLong(mTimeout);
        if(mShortcutId != null)
        {
            parcel.writeInt(1);
            parcel.writeString(mShortcutId);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(mBadgeIcon);
        if(mSettingsText != null)
        {
            parcel.writeInt(1);
            TextUtils.writeToParcel(mSettingsText, parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(mGroupAlertBehavior);
        extraNotification.writeToParcel(parcel, i);
    }

    public Notification clone()
    {
        Notification notification = new Notification();
        cloneInto(notification, true);
        return notification;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public void cloneInto(Notification notification, boolean flag)
    {
        whitelistToken = whitelistToken;
        notification.when = when;
        notification.creationTime = creationTime;
        notification.mSmallIcon = mSmallIcon;
        notification.number = number;
        notification.contentIntent = contentIntent;
        notification.deleteIntent = deleteIntent;
        notification.fullScreenIntent = fullScreenIntent;
        if(tickerText != null)
            notification.tickerText = tickerText.toString();
        if(flag && tickerView != null)
            notification.tickerView = tickerView.clone();
        if(flag && contentView != null)
            notification.contentView = contentView.clone();
        if(flag && mLargeIcon != null)
            notification.mLargeIcon = mLargeIcon;
        notification.iconLevel = iconLevel;
        notification.sound = sound;
        notification.audioStreamType = audioStreamType;
        if(audioAttributes != null)
            notification.audioAttributes = (new android.media.AudioAttributes.Builder(audioAttributes)).build();
        long al[] = vibrate;
        if(al != null)
        {
            int i = al.length;
            long al1[] = new long[i];
            notification.vibrate = al1;
            System.arraycopy(al, 0, al1, 0, i);
        }
        notification.ledARGB = ledARGB;
        notification.ledOnMS = ledOnMS;
        notification.ledOffMS = ledOffMS;
        notification.defaults = defaults;
        notification.flags = flags;
        notification.priority = priority;
        notification.category = category;
        notification.mGroupKey = mGroupKey;
        notification.mSortKey = mSortKey;
        if(extras != null)
            try
            {
                Bundle bundle = JVM INSTR new #448 <Class Bundle>;
                bundle.Bundle(extras);
                notification.extras = bundle;
                notification.extras.size();
            }
            catch(BadParcelableException badparcelableexception)
            {
                Log.e("Notification", (new StringBuilder()).append("could not unparcel extras from notification: ").append(this).toString(), badparcelableexception);
                notification.extras = null;
            }
        if(!ArrayUtils.isEmpty(allPendingIntents))
            notification.allPendingIntents = new ArraySet(allPendingIntents);
        if(actions != null)
        {
            notification.actions = new Action[actions.length];
            for(int j = 0; j < actions.length; j++)
                if(actions[j] != null)
                    notification.actions[j] = actions[j].clone();

        }
        if(flag && bigContentView != null)
            notification.bigContentView = bigContentView.clone();
        if(flag && headsUpContentView != null)
            notification.headsUpContentView = headsUpContentView.clone();
        notification.visibility = visibility;
        if(publicVersion != null)
        {
            notification.publicVersion = new Notification();
            publicVersion.cloneInto(notification.publicVersion, flag);
        }
        notification.color = color;
        notification.mChannelId = mChannelId;
        notification.mTimeout = mTimeout;
        notification.mShortcutId = mShortcutId;
        notification.mBadgeIcon = mBadgeIcon;
        notification.mSettingsText = mSettingsText;
        notification.mGroupAlertBehavior = mGroupAlertBehavior;
        if(!flag)
            notification.lightenPayload();
        notification.extraNotification.setTo(extraNotification);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getBadgeIconType()
    {
        return mBadgeIcon;
    }

    public String getChannel()
    {
        return mChannelId;
    }

    public String getChannelId()
    {
        return mChannelId;
    }

    public String getGroup()
    {
        return mGroupKey;
    }

    public int getGroupAlertBehavior()
    {
        return mGroupAlertBehavior;
    }

    public Icon getLargeIcon()
    {
        return mLargeIcon;
    }

    public Class getNotificationStyle()
    {
        String s = extras.getString("android.template");
        if(!TextUtils.isEmpty(s))
            return getNotificationStyleClass(s);
        else
            return null;
    }

    public CharSequence getSettingsText()
    {
        return mSettingsText;
    }

    public String getShortcutId()
    {
        return mShortcutId;
    }

    public Icon getSmallIcon()
    {
        return mSmallIcon;
    }

    public String getSortKey()
    {
        return mSortKey;
    }

    public long getTimeout()
    {
        return mTimeout;
    }

    public long getTimeoutAfter()
    {
        return mTimeout;
    }

    public boolean hasCompletedProgress()
    {
        boolean flag = false;
        if(!extras.containsKey("android.progress") || extras.containsKey("android.progressMax") ^ true)
            return false;
        if(extras.getInt("android.progressMax") == 0)
            return false;
        if(extras.getInt("android.progress") == extras.getInt("android.progressMax"))
            flag = true;
        return flag;
    }

    public boolean hasMediaSession()
    {
        boolean flag;
        if(extras.getParcelable("android.mediaSession") != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isColorized()
    {
        boolean flag = true;
        if(isColorizedMedia())
            return true;
        if(extras.getBoolean("android.colorized"))
        {
            if(!hasColorizedPermission())
                flag = isForegroundService();
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean isColorizedMedia()
    {
        Object obj = getNotificationStyle();
        if(android/app/Notification$MediaStyle.equals(obj))
        {
            obj = (Boolean)extras.get("android.colorized");
            if((obj == null || ((Boolean) (obj)).booleanValue()) && hasMediaSession())
                return true;
        } else
        if(android/app/Notification$DecoratedMediaCustomViewStyle.equals(obj) && extras.getBoolean("android.colorized") && hasMediaSession())
            return true;
        return false;
    }

    public boolean isGroupChild()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mGroupKey != null)
        {
            flag1 = flag;
            if((flags & 0x200) == 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isGroupSummary()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mGroupKey != null)
        {
            flag1 = flag;
            if((flags & 0x200) != 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isMediaNotification()
    {
        Class class1 = getNotificationStyle();
        if(android/app/Notification$MediaStyle.equals(class1))
            return true;
        return android/app/Notification$DecoratedMediaCustomViewStyle.equals(class1);
    }

    void lambda$_2D_android_app_Notification_84856(Parcel parcel, PendingIntent pendingintent, Parcel parcel1, int i)
    {
        if(parcel != parcel1) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if(allPendingIntents == null)
        {
            parcel = JVM INSTR new #915 <Class ArraySet>;
            parcel.ArraySet();
            allPendingIntents = parcel;
        }
        allPendingIntents.add(pendingintent);
        this;
        JVM INSTR monitorexit ;
_L2:
        return;
        parcel;
        throw parcel;
    }

    public final void lightenPayload()
    {
        tickerView = null;
        contentView = null;
        bigContentView = null;
        headsUpContentView = null;
        mLargeIcon = null;
        if(extras != null && extras.isEmpty() ^ true)
        {
            Set set = extras.keySet();
            int i = set.size();
            String as[] = (String[])set.toArray(new String[i]);
            int j = 0;
            while(j < i) 
            {
                String s = as[j];
                if(!"android.tv.EXTENSIONS".equals(s))
                {
                    Object obj = extras.get(s);
                    if(obj != null && ((obj instanceof Parcelable) || (obj instanceof Parcelable[]) || (obj instanceof SparseArray) || (obj instanceof ArrayList)))
                        extras.remove(s);
                }
                j++;
            }
        }
    }

    void reduceImageSizes(Context context)
    {
        if(extras.getBoolean("android.reduced.images"))
            return;
        boolean flag = ActivityManager.isLowRamDeviceStatic();
        if(mLargeIcon != null || largeIcon != null)
        {
            Resources resources = context.getResources();
            Class class1 = getNotificationStyle();
            int i;
            int j;
            if(flag)
                i = 0x1050134;
            else
                i = 0x1050133;
            i = resources.getDimensionPixelSize(i);
            j = i;
            if(android/app/Notification$MediaStyle.equals(class1) || android/app/Notification$DecoratedMediaCustomViewStyle.equals(class1))
            {
                if(flag)
                    i = 0x1050125;
                else
                    i = 0x1050124;
                j = resources.getDimensionPixelSize(i);
                if(flag)
                    i = 0x1050127;
                else
                    i = 0x1050126;
                i = resources.getDimensionPixelSize(i);
            }
            if(mLargeIcon != null)
                mLargeIcon.scaleDownIfNecessary(i, j);
            if(largeIcon != null)
                largeIcon = Icon.scaleDownIfNecessary(largeIcon, i, j);
        }
        reduceImageSizesForRemoteView(contentView, context, flag);
        reduceImageSizesForRemoteView(headsUpContentView, context, flag);
        reduceImageSizesForRemoteView(bigContentView, context, flag);
        extras.putBoolean("android.reduced.images", true);
    }

    public void setLatestEventInfo(Context context, CharSequence charsequence, CharSequence charsequence1, PendingIntent pendingintent)
    {
        if(context.getApplicationInfo().targetSdkVersion > 22)
            Log.e("Notification", "setLatestEventInfo() is deprecated and you should feel deprecated.", new Throwable());
        if(context.getApplicationInfo().targetSdkVersion < 24)
            extras.putBoolean("android.showWhen", true);
        context = new Builder(context, this);
        if(charsequence != null)
            context.setContentTitle(charsequence);
        if(charsequence1 != null)
            context.setContentText(charsequence1);
        context.setContentIntent(pendingintent);
        context.build();
    }

    public void setSmallIcon(Icon icon1)
    {
        mSmallIcon = icon1;
    }

    public boolean showsChronometer()
    {
        boolean flag;
        if(when != 0L)
            flag = extras.getBoolean("android.showChronometer");
        else
            flag = false;
        return flag;
    }

    public boolean showsTime()
    {
        boolean flag;
        if(when != 0L)
            flag = extras.getBoolean("android.showWhen");
        else
            flag = false;
        return flag;
    }

    public boolean suppressAlertingDueToGrouping()
    {
        if(isGroupSummary() && getGroupAlertBehavior() == 2)
            return true;
        return isGroupChild() && getGroupAlertBehavior() == 1;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Notification(channel=");
        stringbuilder.append(getChannelId());
        stringbuilder.append(" pri=");
        stringbuilder.append(priority);
        stringbuilder.append(" contentView=");
        if(contentView != null)
        {
            stringbuilder.append(contentView.getPackage());
            stringbuilder.append("/0x");
            stringbuilder.append(Integer.toHexString(contentView.getLayoutId()));
        } else
        {
            stringbuilder.append("null");
        }
        stringbuilder.append(" vibrate=");
        if((defaults & 2) != 0)
            stringbuilder.append("default");
        else
        if(vibrate != null)
        {
            int i = vibrate.length - 1;
            stringbuilder.append("[");
            for(int j = 0; j < i; j++)
            {
                stringbuilder.append(vibrate[j]);
                stringbuilder.append(',');
            }

            if(i != -1)
                stringbuilder.append(vibrate[i]);
            stringbuilder.append("]");
        } else
        {
            stringbuilder.append("null");
        }
        stringbuilder.append(" sound=");
        if((defaults & 1) != 0)
            stringbuilder.append("default");
        else
        if(sound != null)
            stringbuilder.append(sound.toString());
        else
            stringbuilder.append("null");
        if(tickerText != null)
            stringbuilder.append(" tick");
        stringbuilder.append(" defaults=0x");
        stringbuilder.append(Integer.toHexString(defaults));
        stringbuilder.append(" flags=0x");
        stringbuilder.append(Integer.toHexString(flags));
        stringbuilder.append(String.format(" color=0x%08x", new Object[] {
            Integer.valueOf(color)
        }));
        if(category != null)
        {
            stringbuilder.append(" category=");
            stringbuilder.append(category);
        }
        if(mGroupKey != null)
        {
            stringbuilder.append(" groupKey=");
            stringbuilder.append(mGroupKey);
        }
        if(mSortKey != null)
        {
            stringbuilder.append(" sortKey=");
            stringbuilder.append(mSortKey);
        }
        if(actions != null)
        {
            stringbuilder.append(" actions=");
            stringbuilder.append(actions.length);
        }
        stringbuilder.append(" vis=");
        stringbuilder.append(visibilityToString(visibility));
        if(publicVersion != null)
        {
            stringbuilder.append(" publicVersion=");
            stringbuilder.append(publicVersion.toString());
        }
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag;
        if(allPendingIntents == null)
            flag = true;
        else
            flag = false;
        if(flag)
            PendingIntent.setOnMarshaledListener(new _.Lambda.vZ1qb742P9hE4drBY_TrOZB_qKo(this, parcel));
        writeToParcelImpl(parcel, i);
        this;
        JVM INSTR monitorenter ;
        parcel.writeArraySet(allPendingIntents);
        this;
        JVM INSTR monitorexit ;
        if(flag)
            PendingIntent.setOnMarshaledListener(null);
        return;
        parcel;
        this;
        JVM INSTR monitorexit ;
        throw parcel;
        parcel;
        if(flag)
            PendingIntent.setOnMarshaledListener(null);
        throw parcel;
    }

    public static final AudioAttributes AUDIO_ATTRIBUTES_DEFAULT = (new android.media.AudioAttributes.Builder()).setContentType(4).setUsage(5).build();
    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    public static final int COLOR_DEFAULT = 0;
    public static final int COLOR_INVALID = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Notification createFromParcel(Parcel parcel)
        {
            return new Notification(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Notification[] newArray(int i)
        {
            return new Notification[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_ALLOW_DURING_SETUP = "android.allowDuringSetup";
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_BUILDER_APPLICATION_INFO = "android.appInfo";
    public static final String EXTRA_CHANNEL_ID = "android.intent.extra.CHANNEL_ID";
    public static final String EXTRA_CHRONOMETER_COUNT_DOWN = "android.chronometerCountDown";
    public static final String EXTRA_COLORIZED = "android.colorized";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_CONTAINS_CUSTOM_VIEW = "android.contains.customView";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_FOREGROUND_APPS = "android.foregroundApps";
    public static final String EXTRA_HISTORIC_MESSAGES = "android.messages.historic";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_NOTIFICATION_ID = "android.intent.extra.NOTIFICATION_ID";
    public static final String EXTRA_NOTIFICATION_TAG = "android.intent.extra.NOTIFICATION_TAG";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REDUCED_IMAGES = "android.reduced.images";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUBSTITUTE_APP_NAME = "android.substName";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTOGROUP_SUMMARY = 1024;
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_CAN_COLORIZE = 2048;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final String INTENT_CATEGORY_NOTIFICATION_PREFERENCES = "android.intent.category.NOTIFICATION_PREFERENCES";
    private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
    private static final int MAX_REPLY_HISTORY = 5;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    private static final String TAG = "Notification";
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;
    public static IBinder processWhitelistToken;
    public static IBinder whitelistToken;
    public Action actions[];
    public ArraySet allPendingIntents;
    public AudioAttributes audioAttributes;
    public int audioStreamType;
    public RemoteViews bigContentView;
    public String category;
    public int color;
    public PendingIntent contentIntent;
    public RemoteViews contentView;
    private long creationTime;
    public int defaults;
    public PendingIntent deleteIntent;
    public MiuiNotification extraNotification;
    public Bundle extras;
    public int flags;
    public PendingIntent fullScreenIntent;
    public RemoteViews headsUpContentView;
    public int icon;
    public int iconLevel;
    public Bitmap largeIcon;
    public int ledARGB;
    public int ledOffMS;
    public int ledOnMS;
    private int mBadgeIcon;
    private String mChannelId;
    private int mGroupAlertBehavior;
    private String mGroupKey;
    private Icon mLargeIcon;
    private CharSequence mSettingsText;
    private String mShortcutId;
    private Icon mSmallIcon;
    private String mSortKey;
    private long mTimeout;
    public int number;
    public int priority;
    public Notification publicVersion;
    public Uri sound;
    public CharSequence tickerText;
    public RemoteViews tickerView;
    public long vibrate[];
    public int visibility;
    public long when;

}
