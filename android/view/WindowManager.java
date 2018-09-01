// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;
import java.util.Objects;

// Referenced classes of package android.view:
//            ViewManager, Display, View, ViewHierarchyEncoder

public interface WindowManager
    extends ViewManager
{
    public static class BadTokenException extends RuntimeException
    {

        public BadTokenException()
        {
        }

        public BadTokenException(String s)
        {
            super(s);
        }
    }

    public static class InvalidDisplayException extends RuntimeException
    {

        public InvalidDisplayException()
        {
        }

        public InvalidDisplayException(String s)
        {
            super(s);
        }
    }

    public static interface KeyboardShortcutsReceiver
    {

        public abstract void onKeyboardShortcutsReceived(List list);
    }

    public static class LayoutParams extends ViewGroup.LayoutParams
        implements Parcelable
    {

        public static boolean isSystemAlertWindowType(int i)
        {
            switch(i)
            {
            default:
                return false;

            case 2002: 
            case 2003: 
            case 2006: 
            case 2007: 
            case 2010: 
            case 2038: 
                return true;
            }
        }

        public static boolean mayUseInputMethod(int i)
        {
            switch(0x20008 & i)
            {
            default:
                return false;

            case 0: // '\0'
            case 131080: 
                return true;
            }
        }

        void backup()
        {
            int ai[] = mCompatibilityParamsBackup;
            int ai1[] = ai;
            if(ai == null)
            {
                ai1 = new int[4];
                mCompatibilityParamsBackup = ai1;
            }
            ai1[0] = x;
            ai1[1] = y;
            ai1[2] = width;
            ai1[3] = height;
        }

        public final int copyFrom(LayoutParams layoutparams)
        {
            int i;
label0:
            {
                i = 0;
                if(width != layoutparams.width)
                {
                    width = layoutparams.width;
                    i = 1;
                }
                int j = i;
                if(height != layoutparams.height)
                {
                    height = layoutparams.height;
                    j = i | true;
                }
                i = j;
                if(x != layoutparams.x)
                {
                    x = layoutparams.x;
                    i = j | true;
                }
                j = i;
                if(y != layoutparams.y)
                {
                    y = layoutparams.y;
                    j = i | true;
                }
                i = j;
                if(horizontalWeight != layoutparams.horizontalWeight)
                {
                    horizontalWeight = layoutparams.horizontalWeight;
                    i = j | true;
                }
                j = i;
                if(verticalWeight != layoutparams.verticalWeight)
                {
                    verticalWeight = layoutparams.verticalWeight;
                    j = i | true;
                }
                i = j;
                if(horizontalMargin != layoutparams.horizontalMargin)
                {
                    horizontalMargin = layoutparams.horizontalMargin;
                    i = j | true;
                }
                int l = i;
                if(verticalMargin != layoutparams.verticalMargin)
                {
                    verticalMargin = layoutparams.verticalMargin;
                    l = i | true;
                }
                j = l;
                if(type != layoutparams.type)
                {
                    type = layoutparams.type;
                    j = l | 2;
                }
                i = j;
                if(flags != layoutparams.flags)
                {
                    i = j;
                    if((0xc000000 & (flags ^ layoutparams.flags)) != 0)
                        i = j | 0x80000;
                    flags = layoutparams.flags;
                    i |= 4;
                }
                j = i;
                if(extraFlags != layoutparams.extraFlags)
                {
                    extraFlags = layoutparams.extraFlags;
                    j = i | 4;
                }
                i = j;
                if(extraPrivateFlags != layoutparams.extraPrivateFlags)
                {
                    extraPrivateFlags = layoutparams.extraPrivateFlags;
                    i = j | 0x20000;
                }
                j = i;
                if(privateFlags != layoutparams.privateFlags)
                {
                    privateFlags = layoutparams.privateFlags;
                    j = i | 0x20000;
                }
                i = j;
                if(softInputMode != layoutparams.softInputMode)
                {
                    softInputMode = layoutparams.softInputMode;
                    i = j | 0x200;
                }
                j = i;
                if(gravity != layoutparams.gravity)
                {
                    gravity = layoutparams.gravity;
                    j = i | 1;
                }
                l = j;
                if(format != layoutparams.format)
                {
                    format = layoutparams.format;
                    l = j | 8;
                }
                i = l;
                if(windowAnimations != layoutparams.windowAnimations)
                {
                    windowAnimations = layoutparams.windowAnimations;
                    i = l | 0x10;
                }
                if(token == null)
                    token = layoutparams.token;
                if(packageName == null)
                    packageName = layoutparams.packageName;
                j = i;
                if(!Objects.equals(mTitle, layoutparams.mTitle))
                {
                    j = i;
                    if(layoutparams.mTitle != null)
                    {
                        mTitle = layoutparams.mTitle;
                        j = i | 0x40;
                    }
                }
                l = j;
                if(alpha != layoutparams.alpha)
                {
                    alpha = layoutparams.alpha;
                    l = j | 0x80;
                }
                i = l;
                if(dimAmount != layoutparams.dimAmount)
                {
                    dimAmount = layoutparams.dimAmount;
                    i = l | 0x20;
                }
                j = i;
                if(screenBrightness != layoutparams.screenBrightness)
                {
                    screenBrightness = layoutparams.screenBrightness;
                    j = i | 0x800;
                }
                i = j;
                if(buttonBrightness != layoutparams.buttonBrightness)
                {
                    buttonBrightness = layoutparams.buttonBrightness;
                    i = j | 0x2000;
                }
                l = i;
                if(rotationAnimation != layoutparams.rotationAnimation)
                {
                    rotationAnimation = layoutparams.rotationAnimation;
                    l = i | 0x1000;
                }
                j = l;
                if(screenOrientation != layoutparams.screenOrientation)
                {
                    screenOrientation = layoutparams.screenOrientation;
                    j = l | 0x400;
                }
                i = j;
                if(preferredRefreshRate != layoutparams.preferredRefreshRate)
                {
                    preferredRefreshRate = layoutparams.preferredRefreshRate;
                    i = j | 0x200000;
                }
                j = i;
                if(preferredDisplayModeId != layoutparams.preferredDisplayModeId)
                {
                    preferredDisplayModeId = layoutparams.preferredDisplayModeId;
                    j = i | 0x800000;
                }
                if(systemUiVisibility == layoutparams.systemUiVisibility)
                {
                    i = j;
                    if(subtreeSystemUiVisibility == layoutparams.subtreeSystemUiVisibility)
                        break label0;
                }
                systemUiVisibility = layoutparams.systemUiVisibility;
                subtreeSystemUiVisibility = layoutparams.subtreeSystemUiVisibility;
                i = j | 0x4000;
            }
            int k = i;
            if(hasSystemUiListeners != layoutparams.hasSystemUiListeners)
            {
                hasSystemUiListeners = layoutparams.hasSystemUiListeners;
                k = i | 0x8000;
            }
            int i1 = k;
            if(inputFeatures != layoutparams.inputFeatures)
            {
                inputFeatures = layoutparams.inputFeatures;
                i1 = k | 0x10000;
            }
            i = i1;
            if(userActivityTimeout != layoutparams.userActivityTimeout)
            {
                userActivityTimeout = layoutparams.userActivityTimeout;
                i = i1 | 0x40000;
            }
            k = i;
            if(!surfaceInsets.equals(layoutparams.surfaceInsets))
            {
                surfaceInsets.set(layoutparams.surfaceInsets);
                k = i | 0x100000;
            }
            i = k;
            if(hasManualSurfaceInsets != layoutparams.hasManualSurfaceInsets)
            {
                hasManualSurfaceInsets = layoutparams.hasManualSurfaceInsets;
                i = k | 0x100000;
            }
            i1 = i;
            if(preservePreviousSurfaceInsets != layoutparams.preservePreviousSurfaceInsets)
            {
                preservePreviousSurfaceInsets = layoutparams.preservePreviousSurfaceInsets;
                i1 = i | 0x100000;
            }
            k = i1;
            if(needsMenuKey != layoutparams.needsMenuKey)
            {
                needsMenuKey = layoutparams.needsMenuKey;
                k = i1 | 0x400000;
            }
            i = k;
            if(accessibilityIdOfAnchor != layoutparams.accessibilityIdOfAnchor)
            {
                accessibilityIdOfAnchor = layoutparams.accessibilityIdOfAnchor;
                i = k | 0x1000000;
            }
            k = i;
            if(!Objects.equals(accessibilityTitle, layoutparams.accessibilityTitle))
            {
                k = i;
                if(layoutparams.accessibilityTitle != null)
                {
                    accessibilityTitle = layoutparams.accessibilityTitle;
                    k = i | 0x2000000;
                }
            }
            i = k;
            if(mColorMode != layoutparams.mColorMode)
            {
                mColorMode = layoutparams.mColorMode;
                i = k | 0x4000000;
            }
            hideTimeoutMilliseconds = layoutparams.hideTimeoutMilliseconds;
            return i;
        }

        public String debug(String s)
        {
            Log.d("Debug", (new StringBuilder()).append(s).append("Contents of ").append(this).append(":").toString());
            Log.d("Debug", super.debug(""));
            Log.d("Debug", "");
            Log.d("Debug", (new StringBuilder()).append("WindowManager.LayoutParams={title=").append(mTitle).append("}").toString());
            return "";
        }

        public int describeContents()
        {
            return 0;
        }

        protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
        {
            super.encodeProperties(viewhierarchyencoder);
            viewhierarchyencoder.addProperty("x", x);
            viewhierarchyencoder.addProperty("y", y);
            viewhierarchyencoder.addProperty("horizontalWeight", horizontalWeight);
            viewhierarchyencoder.addProperty("verticalWeight", verticalWeight);
            viewhierarchyencoder.addProperty("type", type);
            viewhierarchyencoder.addProperty("flags", flags);
        }

        public int getColorMode()
        {
            return mColorMode;
        }

        public final CharSequence getTitle()
        {
            Object obj;
            if(mTitle != null)
                obj = mTitle;
            else
                obj = "";
            return ((CharSequence) (obj));
        }

        public final long getUserActivityTimeout()
        {
            return userActivityTimeout;
        }

        public boolean isFullscreen()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(x == 0)
            {
                flag1 = flag;
                if(y == 0)
                {
                    flag1 = flag;
                    if(width == -1)
                    {
                        flag1 = flag;
                        if(height == -1)
                            flag1 = true;
                    }
                }
            }
            return flag1;
        }

        void restore()
        {
            int ai[] = mCompatibilityParamsBackup;
            if(ai != null)
            {
                x = ai[0];
                y = ai[1];
                width = ai[2];
                height = ai[3];
            }
        }

        public void scale(float f)
        {
            x = (int)((float)x * f + 0.5F);
            y = (int)((float)y * f + 0.5F);
            if(width > 0)
                width = (int)((float)width * f + 0.5F);
            if(height > 0)
                height = (int)((float)height * f + 0.5F);
        }

        public void setColorMode(int i)
        {
            mColorMode = i;
        }

        public final void setSurfaceInsets(View view, boolean flag, boolean flag1)
        {
            int i = (int)Math.ceil(view.getZ() * 2.0F);
            if(i == 0)
                surfaceInsets.set(0, 0, 0, 0);
            else
                surfaceInsets.set(Math.max(i, surfaceInsets.left), Math.max(i, surfaceInsets.top), Math.max(i, surfaceInsets.right), Math.max(i, surfaceInsets.bottom));
            hasManualSurfaceInsets = flag;
            preservePreviousSurfaceInsets = flag1;
        }

        public final void setTitle(CharSequence charsequence)
        {
            Object obj = charsequence;
            if(charsequence == null)
                obj = "";
            mTitle = TextUtils.stringOrSpannedString(((CharSequence) (obj)));
        }

        public final void setUserActivityTimeout(long l)
        {
            userActivityTimeout = l;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(256);
            stringbuilder.append("WM.LayoutParams{");
            stringbuilder.append("(");
            stringbuilder.append(x);
            stringbuilder.append(',');
            stringbuilder.append(y);
            stringbuilder.append(")(");
            String s;
            if(width == -1)
                s = "fill";
            else
            if(width == -2)
                s = "wrap";
            else
                s = String.valueOf(width);
            stringbuilder.append(s);
            stringbuilder.append('x');
            if(height == -1)
                s = "fill";
            else
            if(height == -2)
                s = "wrap";
            else
                s = String.valueOf(height);
            stringbuilder.append(s);
            stringbuilder.append(")");
            if(horizontalMargin != 0.0F)
            {
                stringbuilder.append(" hm=");
                stringbuilder.append(horizontalMargin);
            }
            if(verticalMargin != 0.0F)
            {
                stringbuilder.append(" vm=");
                stringbuilder.append(verticalMargin);
            }
            if(gravity != 0)
            {
                stringbuilder.append(" gr=#");
                stringbuilder.append(Integer.toHexString(gravity));
            }
            if(softInputMode != 0)
            {
                stringbuilder.append(" sim=#");
                stringbuilder.append(Integer.toHexString(softInputMode));
            }
            stringbuilder.append(" ty=");
            stringbuilder.append(type);
            stringbuilder.append(" fl=#");
            stringbuilder.append(Integer.toHexString(flags));
            stringbuilder.append(" extfl=#");
            stringbuilder.append(Integer.toHexString(extraFlags));
            stringbuilder.append(" extpfl=");
            stringbuilder.append(Integer.toHexString(extraPrivateFlags));
            if(privateFlags != 0)
            {
                if((privateFlags & 0x80) != 0)
                    stringbuilder.append(" compatible=true");
                stringbuilder.append(" pfl=0x").append(Integer.toHexString(privateFlags));
            }
            if(format != -1)
            {
                stringbuilder.append(" fmt=");
                stringbuilder.append(format);
            }
            if(windowAnimations != 0)
            {
                stringbuilder.append(" wanim=0x");
                stringbuilder.append(Integer.toHexString(windowAnimations));
            }
            if(screenOrientation != -1)
            {
                stringbuilder.append(" or=");
                stringbuilder.append(screenOrientation);
            }
            if(alpha != 1.0F)
            {
                stringbuilder.append(" alpha=");
                stringbuilder.append(alpha);
            }
            if(screenBrightness != -1F)
            {
                stringbuilder.append(" sbrt=");
                stringbuilder.append(screenBrightness);
            }
            if(buttonBrightness != -1F)
            {
                stringbuilder.append(" bbrt=");
                stringbuilder.append(buttonBrightness);
            }
            if(rotationAnimation != 0)
            {
                stringbuilder.append(" rotAnim=");
                stringbuilder.append(rotationAnimation);
            }
            if(preferredRefreshRate != 0.0F)
            {
                stringbuilder.append(" preferredRefreshRate=");
                stringbuilder.append(preferredRefreshRate);
            }
            if(preferredDisplayModeId != 0)
            {
                stringbuilder.append(" preferredDisplayMode=");
                stringbuilder.append(preferredDisplayModeId);
            }
            if(systemUiVisibility != 0)
            {
                stringbuilder.append(" sysui=0x");
                stringbuilder.append(Integer.toHexString(systemUiVisibility));
            }
            if(subtreeSystemUiVisibility != 0)
            {
                stringbuilder.append(" vsysui=0x");
                stringbuilder.append(Integer.toHexString(subtreeSystemUiVisibility));
            }
            if(hasSystemUiListeners)
            {
                stringbuilder.append(" sysuil=");
                stringbuilder.append(hasSystemUiListeners);
            }
            if(inputFeatures != 0)
                stringbuilder.append(" if=0x").append(Integer.toHexString(inputFeatures));
            if(userActivityTimeout >= 0L)
                stringbuilder.append(" userActivityTimeout=").append(userActivityTimeout);
            break MISSING_BLOCK_LABEL_696;
            if(surfaceInsets.left != 0 || surfaceInsets.top != 0 || surfaceInsets.right != 0 || surfaceInsets.bottom != 0 || hasManualSurfaceInsets || preservePreviousSurfaceInsets ^ true)
            {
                stringbuilder.append(" surfaceInsets=").append(surfaceInsets);
                if(hasManualSurfaceInsets)
                    stringbuilder.append(" (manual)");
                if(!preservePreviousSurfaceInsets)
                    stringbuilder.append(" (!preservePreviousSurfaceInsets)");
            }
            if(needsMenuKey != 0)
            {
                stringbuilder.append(" needsMenuKey=");
                stringbuilder.append(needsMenuKey);
            }
            stringbuilder.append(" colorMode=").append(mColorMode);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            parcel.writeInt(width);
            parcel.writeInt(height);
            parcel.writeInt(x);
            parcel.writeInt(y);
            parcel.writeInt(type);
            parcel.writeInt(flags);
            parcel.writeInt(extraFlags);
            parcel.writeInt(extraPrivateFlags);
            parcel.writeInt(privateFlags);
            parcel.writeInt(softInputMode);
            parcel.writeInt(gravity);
            parcel.writeFloat(horizontalMargin);
            parcel.writeFloat(verticalMargin);
            parcel.writeInt(format);
            parcel.writeInt(windowAnimations);
            parcel.writeFloat(alpha);
            parcel.writeFloat(dimAmount);
            parcel.writeFloat(screenBrightness);
            parcel.writeFloat(buttonBrightness);
            parcel.writeInt(rotationAnimation);
            parcel.writeStrongBinder(token);
            parcel.writeString(packageName);
            TextUtils.writeToParcel(mTitle, parcel, i);
            parcel.writeInt(screenOrientation);
            parcel.writeFloat(preferredRefreshRate);
            parcel.writeInt(preferredDisplayModeId);
            parcel.writeInt(systemUiVisibility);
            parcel.writeInt(subtreeSystemUiVisibility);
            int j;
            if(hasSystemUiListeners)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(inputFeatures);
            parcel.writeLong(userActivityTimeout);
            parcel.writeInt(surfaceInsets.left);
            parcel.writeInt(surfaceInsets.top);
            parcel.writeInt(surfaceInsets.right);
            parcel.writeInt(surfaceInsets.bottom);
            if(hasManualSurfaceInsets)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            if(preservePreviousSurfaceInsets)
                j = ((flag) ? 1 : 0);
            else
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(needsMenuKey);
            parcel.writeInt(accessibilityIdOfAnchor);
            TextUtils.writeToParcel(accessibilityTitle, parcel, i);
            parcel.writeInt(mColorMode);
            parcel.writeLong(hideTimeoutMilliseconds);
        }

        public static final int ACCESSIBILITY_ANCHOR_CHANGED = 0x1000000;
        public static final int ACCESSIBILITY_TITLE_CHANGED = 0x2000000;
        public static final int ALPHA_CHANGED = 128;
        public static final int ANIMATION_CHANGED = 16;
        public static final float BRIGHTNESS_OVERRIDE_FULL = 1F;
        public static final float BRIGHTNESS_OVERRIDE_NONE = -1F;
        public static final float BRIGHTNESS_OVERRIDE_OFF = 0F;
        public static final int BUTTON_BRIGHTNESS_CHANGED = 8192;
        public static final int COLOR_MODE_CHANGED = 0x4000000;
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public LayoutParams createFromParcel(Parcel parcel)
            {
                return new LayoutParams(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public LayoutParams[] newArray(int i)
            {
                return new LayoutParams[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int DIM_AMOUNT_CHANGED = 32;
        public static final int EVERYTHING_CHANGED = -1;
        public static final int FIRST_APPLICATION_WINDOW = 1;
        public static final int FIRST_SUB_WINDOW = 1000;
        public static final int FIRST_SYSTEM_WINDOW = 2000;
        public static final int FLAGS_CHANGED = 4;
        public static final int FLAG_ALLOW_LOCK_WHILE_SCREEN_ON = 1;
        public static final int FLAG_ALT_FOCUSABLE_IM = 0x20000;
        public static final int FLAG_BLUR_BEHIND = 4;
        public static final int FLAG_DIM_BEHIND = 2;
        public static final int FLAG_DISMISS_KEYGUARD = 0x400000;
        public static final int FLAG_DITHER = 4096;
        public static final int FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS = 0x80000000;
        public static final int FLAG_FORCE_NOT_FULLSCREEN = 2048;
        public static final int FLAG_FULLSCREEN = 1024;
        public static final int FLAG_HARDWARE_ACCELERATED = 0x1000000;
        public static final int FLAG_IGNORE_CHEEK_PRESSES = 32768;
        public static final int FLAG_KEEP_SCREEN_ON = 128;
        public static final int FLAG_LAYOUT_ATTACHED_IN_DECOR = 0x40000000;
        public static final int FLAG_LAYOUT_INSET_DECOR = 0x10000;
        public static final int FLAG_LAYOUT_IN_OVERSCAN = 0x2000000;
        public static final int FLAG_LAYOUT_IN_SCREEN = 256;
        public static final int FLAG_LAYOUT_NO_LIMITS = 512;
        public static final int FLAG_LOCAL_FOCUS_MODE = 0x10000000;
        public static final int FLAG_NOT_FOCUSABLE = 8;
        public static final int FLAG_NOT_TOUCHABLE = 16;
        public static final int FLAG_NOT_TOUCH_MODAL = 32;
        public static final int FLAG_SCALED = 16384;
        public static final int FLAG_SECURE = 8192;
        public static final int FLAG_SHOW_WALLPAPER = 0x100000;
        public static final int FLAG_SHOW_WHEN_LOCKED = 0x80000;
        public static final int FLAG_SLIPPERY = 0x20000000;
        public static final int FLAG_SPLIT_TOUCH = 0x800000;
        public static final int FLAG_TOUCHABLE_WHEN_WAKING = 64;
        public static final int FLAG_TRANSLUCENT_NAVIGATION = 0x8000000;
        public static final int FLAG_TRANSLUCENT_STATUS = 0x4000000;
        public static final int FLAG_TURN_SCREEN_ON = 0x200000;
        public static final int FLAG_WATCH_OUTSIDE_TOUCH = 0x40000;
        public static final int FORMAT_CHANGED = 8;
        public static final int INPUT_FEATURES_CHANGED = 0x10000;
        public static final int INPUT_FEATURE_DISABLE_POINTER_GESTURES = 1;
        public static final int INPUT_FEATURE_DISABLE_USER_ACTIVITY = 4;
        public static final int INPUT_FEATURE_NO_INPUT_CHANNEL = 2;
        public static final int INVALID_WINDOW_TYPE = -1;
        public static final int LAST_APPLICATION_WINDOW = 99;
        public static final int LAST_SUB_WINDOW = 1999;
        public static final int LAST_SYSTEM_WINDOW = 2999;
        public static final int LAYOUT_CHANGED = 1;
        public static final int MEMORY_TYPE_CHANGED = 256;
        public static final int MEMORY_TYPE_GPU = 2;
        public static final int MEMORY_TYPE_HARDWARE = 1;
        public static final int MEMORY_TYPE_NORMAL = 0;
        public static final int MEMORY_TYPE_PUSH_BUFFERS = 3;
        public static final int NEEDS_MENU_KEY_CHANGED = 0x400000;
        public static final int NEEDS_MENU_SET_FALSE = 2;
        public static final int NEEDS_MENU_SET_TRUE = 1;
        public static final int NEEDS_MENU_UNSET = 0;
        public static final int PREFERRED_DISPLAY_MODE_ID = 0x800000;
        public static final int PREFERRED_REFRESH_RATE_CHANGED = 0x200000;
        public static final int PRIVATE_FLAGS_CHANGED = 0x20000;
        public static final int PRIVATE_FLAG_ACQUIRES_SLEEP_TOKEN = 0x200000;
        public static final int PRIVATE_FLAG_COMPATIBLE_WINDOW = 128;
        public static final int PRIVATE_FLAG_DISABLE_WALLPAPER_TOUCH_EVENTS = 2048;
        public static final int PRIVATE_FLAG_FAKE_HARDWARE_ACCELERATED = 1;
        public static final int PRIVATE_FLAG_FORCE_DECOR_VIEW_VISIBILITY = 16384;
        public static final int PRIVATE_FLAG_FORCE_DRAW_STATUS_BAR_BACKGROUND = 0x20000;
        public static final int PRIVATE_FLAG_FORCE_HARDWARE_ACCELERATED = 2;
        public static final int PRIVATE_FLAG_FORCE_STATUS_BAR_VISIBLE_TRANSPARENT = 4096;
        public static final int PRIVATE_FLAG_HIDE_NON_SYSTEM_OVERLAY_WINDOWS = 0x80000;
        public static final int PRIVATE_FLAG_INHERIT_TRANSLUCENT_DECOR = 512;
        public static final int PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY = 0x100000;
        public static final int PRIVATE_FLAG_KEYGUARD = 1024;
        public static final int PRIVATE_FLAG_LAYOUT_CHILD_WINDOW_IN_PARENT_FRAME = 0x10000;
        public static final int PRIVATE_FLAG_NO_MOVE_ANIMATION = 64;
        public static final int PRIVATE_FLAG_PRESERVE_GEOMETRY = 8192;
        public static final int PRIVATE_FLAG_SHOW_FOR_ALL_USERS = 16;
        public static final int PRIVATE_FLAG_SUSTAINED_PERFORMANCE_MODE = 0x40000;
        public static final int PRIVATE_FLAG_SYSTEM_ERROR = 256;
        public static final int PRIVATE_FLAG_WANTS_OFFSET_NOTIFICATIONS = 4;
        public static final int PRIVATE_FLAG_WILL_NOT_REPLACE_ON_RELAUNCH = 32768;
        public static final int ROTATION_ANIMATION_CHANGED = 4096;
        public static final int ROTATION_ANIMATION_CROSSFADE = 1;
        public static final int ROTATION_ANIMATION_JUMPCUT = 2;
        public static final int ROTATION_ANIMATION_ROTATE = 0;
        public static final int ROTATION_ANIMATION_SEAMLESS = 3;
        public static final int ROTATION_ANIMATION_UNSPECIFIED = -1;
        public static final int SCREEN_BRIGHTNESS_CHANGED = 2048;
        public static final int SCREEN_ORIENTATION_CHANGED = 1024;
        public static final int SOFT_INPUT_ADJUST_NOTHING = 48;
        public static final int SOFT_INPUT_ADJUST_PAN = 32;
        public static final int SOFT_INPUT_ADJUST_RESIZE = 16;
        public static final int SOFT_INPUT_ADJUST_UNSPECIFIED = 0;
        public static final int SOFT_INPUT_IS_FORWARD_NAVIGATION = 256;
        public static final int SOFT_INPUT_MASK_ADJUST = 240;
        public static final int SOFT_INPUT_MASK_STATE = 15;
        public static final int SOFT_INPUT_MODE_CHANGED = 512;
        public static final int SOFT_INPUT_STATE_ALWAYS_HIDDEN = 3;
        public static final int SOFT_INPUT_STATE_ALWAYS_VISIBLE = 5;
        public static final int SOFT_INPUT_STATE_HIDDEN = 2;
        public static final int SOFT_INPUT_STATE_UNCHANGED = 1;
        public static final int SOFT_INPUT_STATE_UNSPECIFIED = 0;
        public static final int SOFT_INPUT_STATE_VISIBLE = 4;
        public static final int SURFACE_INSETS_CHANGED = 0x100000;
        public static final int SYSTEM_UI_LISTENER_CHANGED = 32768;
        public static final int SYSTEM_UI_VISIBILITY_CHANGED = 16384;
        public static final int TITLE_CHANGED = 64;
        public static final int TRANSLUCENT_FLAGS_CHANGED = 0x80000;
        public static final int TYPE_ACCESSIBILITY_OVERLAY = 2032;
        public static final int TYPE_APPLICATION = 2;
        public static final int TYPE_APPLICATION_ABOVE_SUB_PANEL = 1005;
        public static final int TYPE_APPLICATION_ATTACHED_DIALOG = 1003;
        public static final int TYPE_APPLICATION_MEDIA = 1001;
        public static final int TYPE_APPLICATION_MEDIA_OVERLAY = 1004;
        public static final int TYPE_APPLICATION_OVERLAY = 2038;
        public static final int TYPE_APPLICATION_PANEL = 1000;
        public static final int TYPE_APPLICATION_STARTING = 3;
        public static final int TYPE_APPLICATION_SUB_PANEL = 1002;
        public static final int TYPE_BASE_APPLICATION = 1;
        public static final int TYPE_BOOT_PROGRESS = 2021;
        public static final int TYPE_CHANGED = 2;
        public static final int TYPE_DISPLAY_OVERLAY = 2026;
        public static final int TYPE_DOCK_DIVIDER = 2034;
        public static final int TYPE_DRAG = 2016;
        public static final int TYPE_DRAWN_APPLICATION = 4;
        public static final int TYPE_DREAM = 2023;
        public static final int TYPE_INPUT_CONSUMER = 2022;
        public static final int TYPE_INPUT_METHOD = 2011;
        public static final int TYPE_INPUT_METHOD_DIALOG = 2012;
        public static final int TYPE_KEYGUARD = 2004;
        public static final int TYPE_KEYGUARD_DIALOG = 2009;
        public static final int TYPE_MAGNIFICATION_OVERLAY = 2027;
        public static final int TYPE_NAVIGATION_BAR = 2019;
        public static final int TYPE_NAVIGATION_BAR_PANEL = 2024;
        public static final int TYPE_PHONE = 2002;
        public static final int TYPE_POINTER = 2018;
        public static final int TYPE_PRESENTATION = 2037;
        public static final int TYPE_PRIORITY_PHONE = 2007;
        public static final int TYPE_PRIVATE_PRESENTATION = 2030;
        public static final int TYPE_QS_DIALOG = 2035;
        public static final int TYPE_SCREENSHOT = 2036;
        public static final int TYPE_SEARCH_BAR = 2001;
        public static final int TYPE_SECURE_SYSTEM_OVERLAY = 2015;
        public static final int TYPE_STATUS_BAR = 2000;
        public static final int TYPE_STATUS_BAR_PANEL = 2014;
        public static final int TYPE_STATUS_BAR_SUB_PANEL = 2017;
        public static final int TYPE_SYSTEM_ALERT = 2003;
        public static final int TYPE_SYSTEM_DIALOG = 2008;
        public static final int TYPE_SYSTEM_ERROR = 2010;
        public static final int TYPE_SYSTEM_OVERLAY = 2006;
        public static final int TYPE_TOAST = 2005;
        public static final int TYPE_VOICE_INTERACTION = 2031;
        public static final int TYPE_VOICE_INTERACTION_STARTING = 2033;
        public static final int TYPE_VOLUME_OVERLAY = 2020;
        public static final int TYPE_WALLPAPER = 2013;
        public static final int USER_ACTIVITY_TIMEOUT_CHANGED = 0x40000;
        public int accessibilityIdOfAnchor;
        public CharSequence accessibilityTitle;
        public float alpha;
        public float buttonBrightness;
        public float dimAmount;
        public int extraFlags;
        public int extraPrivateFlags;
        public int flags;
        public int format;
        public int gravity;
        public boolean hasManualSurfaceInsets;
        public boolean hasSystemUiListeners;
        public long hideTimeoutMilliseconds;
        public float horizontalMargin;
        public float horizontalWeight;
        public int inputFeatures;
        private int mColorMode;
        private int mCompatibilityParamsBackup[];
        private CharSequence mTitle;
        public int memoryType;
        public int needsMenuKey;
        public String packageName;
        public int preferredDisplayModeId;
        public float preferredRefreshRate;
        public boolean preservePreviousSurfaceInsets;
        public int privateFlags;
        public int rotationAnimation;
        public float screenBrightness;
        public int screenOrientation;
        public int softInputMode;
        public int subtreeSystemUiVisibility;
        public final Rect surfaceInsets;
        public int systemUiVisibility;
        public IBinder token;
        public int type;
        public long userActivityTimeout;
        public float verticalMargin;
        public float verticalWeight;
        public int windowAnimations;
        public int x;
        public int y;


        public LayoutParams()
        {
            super(-1, -1);
            needsMenuKey = 0;
            surfaceInsets = new Rect();
            preservePreviousSurfaceInsets = true;
            alpha = 1.0F;
            dimAmount = 1.0F;
            screenBrightness = -1F;
            buttonBrightness = -1F;
            rotationAnimation = 0;
            token = null;
            packageName = null;
            screenOrientation = -1;
            userActivityTimeout = -1L;
            accessibilityIdOfAnchor = -1;
            hideTimeoutMilliseconds = -1L;
            mColorMode = 0;
            mCompatibilityParamsBackup = null;
            mTitle = null;
            type = 2;
            format = -1;
        }

        public LayoutParams(int i)
        {
            super(-1, -1);
            needsMenuKey = 0;
            surfaceInsets = new Rect();
            preservePreviousSurfaceInsets = true;
            alpha = 1.0F;
            dimAmount = 1.0F;
            screenBrightness = -1F;
            buttonBrightness = -1F;
            rotationAnimation = 0;
            token = null;
            packageName = null;
            screenOrientation = -1;
            userActivityTimeout = -1L;
            accessibilityIdOfAnchor = -1;
            hideTimeoutMilliseconds = -1L;
            mColorMode = 0;
            mCompatibilityParamsBackup = null;
            mTitle = null;
            type = i;
            format = -1;
        }

        public LayoutParams(int i, int j)
        {
            super(-1, -1);
            needsMenuKey = 0;
            surfaceInsets = new Rect();
            preservePreviousSurfaceInsets = true;
            alpha = 1.0F;
            dimAmount = 1.0F;
            screenBrightness = -1F;
            buttonBrightness = -1F;
            rotationAnimation = 0;
            token = null;
            packageName = null;
            screenOrientation = -1;
            userActivityTimeout = -1L;
            accessibilityIdOfAnchor = -1;
            hideTimeoutMilliseconds = -1L;
            mColorMode = 0;
            mCompatibilityParamsBackup = null;
            mTitle = null;
            type = i;
            flags = j;
            format = -1;
        }

        public LayoutParams(int i, int j, int k)
        {
            super(-1, -1);
            needsMenuKey = 0;
            surfaceInsets = new Rect();
            preservePreviousSurfaceInsets = true;
            alpha = 1.0F;
            dimAmount = 1.0F;
            screenBrightness = -1F;
            buttonBrightness = -1F;
            rotationAnimation = 0;
            token = null;
            packageName = null;
            screenOrientation = -1;
            userActivityTimeout = -1L;
            accessibilityIdOfAnchor = -1;
            hideTimeoutMilliseconds = -1L;
            mColorMode = 0;
            mCompatibilityParamsBackup = null;
            mTitle = null;
            type = i;
            flags = j;
            format = k;
        }

        public LayoutParams(int i, int j, int k, int l, int i1)
        {
            super(i, j);
            needsMenuKey = 0;
            surfaceInsets = new Rect();
            preservePreviousSurfaceInsets = true;
            alpha = 1.0F;
            dimAmount = 1.0F;
            screenBrightness = -1F;
            buttonBrightness = -1F;
            rotationAnimation = 0;
            token = null;
            packageName = null;
            screenOrientation = -1;
            userActivityTimeout = -1L;
            accessibilityIdOfAnchor = -1;
            hideTimeoutMilliseconds = -1L;
            mColorMode = 0;
            mCompatibilityParamsBackup = null;
            mTitle = null;
            type = k;
            flags = l;
            format = i1;
        }

        public LayoutParams(int i, int j, int k, int l, int i1, int j1, int k1)
        {
            super(i, j);
            needsMenuKey = 0;
            surfaceInsets = new Rect();
            preservePreviousSurfaceInsets = true;
            alpha = 1.0F;
            dimAmount = 1.0F;
            screenBrightness = -1F;
            buttonBrightness = -1F;
            rotationAnimation = 0;
            token = null;
            packageName = null;
            screenOrientation = -1;
            userActivityTimeout = -1L;
            accessibilityIdOfAnchor = -1;
            hideTimeoutMilliseconds = -1L;
            mColorMode = 0;
            mCompatibilityParamsBackup = null;
            mTitle = null;
            x = k;
            y = l;
            type = i1;
            flags = j1;
            format = k1;
        }

        public LayoutParams(Parcel parcel)
        {
            boolean flag = true;
            super();
            needsMenuKey = 0;
            surfaceInsets = new Rect();
            preservePreviousSurfaceInsets = true;
            alpha = 1.0F;
            dimAmount = 1.0F;
            screenBrightness = -1F;
            buttonBrightness = -1F;
            rotationAnimation = 0;
            token = null;
            packageName = null;
            screenOrientation = -1;
            userActivityTimeout = -1L;
            accessibilityIdOfAnchor = -1;
            hideTimeoutMilliseconds = -1L;
            mColorMode = 0;
            mCompatibilityParamsBackup = null;
            mTitle = null;
            width = parcel.readInt();
            height = parcel.readInt();
            x = parcel.readInt();
            y = parcel.readInt();
            type = parcel.readInt();
            flags = parcel.readInt();
            extraFlags = parcel.readInt();
            extraPrivateFlags = parcel.readInt();
            privateFlags = parcel.readInt();
            softInputMode = parcel.readInt();
            gravity = parcel.readInt();
            horizontalMargin = parcel.readFloat();
            verticalMargin = parcel.readFloat();
            format = parcel.readInt();
            windowAnimations = parcel.readInt();
            alpha = parcel.readFloat();
            dimAmount = parcel.readFloat();
            screenBrightness = parcel.readFloat();
            buttonBrightness = parcel.readFloat();
            rotationAnimation = parcel.readInt();
            token = parcel.readStrongBinder();
            packageName = parcel.readString();
            mTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            screenOrientation = parcel.readInt();
            preferredRefreshRate = parcel.readFloat();
            preferredDisplayModeId = parcel.readInt();
            systemUiVisibility = parcel.readInt();
            subtreeSystemUiVisibility = parcel.readInt();
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            hasSystemUiListeners = flag1;
            inputFeatures = parcel.readInt();
            userActivityTimeout = parcel.readLong();
            surfaceInsets.left = parcel.readInt();
            surfaceInsets.top = parcel.readInt();
            surfaceInsets.right = parcel.readInt();
            surfaceInsets.bottom = parcel.readInt();
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            hasManualSurfaceInsets = flag1;
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            preservePreviousSurfaceInsets = flag1;
            needsMenuKey = parcel.readInt();
            accessibilityIdOfAnchor = parcel.readInt();
            accessibilityTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            mColorMode = parcel.readInt();
            hideTimeoutMilliseconds = parcel.readLong();
        }
    }


    public abstract Region getCurrentImeTouchRegion();

    public abstract Display getDefaultDisplay();

    public abstract void removeViewImmediate(View view);

    public abstract void requestAppKeyboardShortcuts(KeyboardShortcutsReceiver keyboardshortcutsreceiver, int i);

    public static final int DOCKED_BOTTOM = 4;
    public static final int DOCKED_INVALID = -1;
    public static final int DOCKED_LEFT = 1;
    public static final int DOCKED_RIGHT = 3;
    public static final int DOCKED_TOP = 2;
    public static final String INPUT_CONSUMER_NAVIGATION = "nav_input_consumer";
    public static final String INPUT_CONSUMER_PIP = "pip_input_consumer";
    public static final String INPUT_CONSUMER_WALLPAPER = "wallpaper_input_consumer";
    public static final String PARCEL_KEY_SHORTCUTS_ARRAY = "shortcuts_array";
    public static final int TAKE_SCREENSHOT_FULLSCREEN = 1;
    public static final int TAKE_SCREENSHOT_SELECTED_REGION = 2;
}
