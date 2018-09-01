// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.app.MobileDataUtils;
import android.bluetooth.BluetoothAdapter;
import android.content.*;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.*;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.*;
import java.util.*;
import miui.maml.data.Expression;
import miui.maml.data.IndexedVariable;
import miui.maml.data.VariableBinder;
import miui.maml.data.VariableType;
import miui.maml.data.Variables;
import miui.maml.elements.ElementGroup;
import miui.maml.elements.ScreenElement;
import miui.maml.util.IntentInfo;
import miui.maml.util.PreloadAppPolicyHelper;
import miui.maml.util.ReflectionHelper;
import miui.maml.util.Task;
import miui.maml.util.Utils;
import miui.maml.util.Variable;
import miui.maml.util.WifiApHelper;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml:
//            VibrateCommand, ScreenContext, ScreenElementRoot, NotifierManager, 
//            CommandTrigger

public abstract class ActionCommand
{
    private static class ActionPerformCommand extends TargetCommand
    {

        public void doPerform()
        {
            ScreenElement screenelement;
            screenelement = (ScreenElement)getTarget();
            if(screenelement == null)
                return;
            if(mAction == null) goto _L2; else goto _L1
_L1:
            screenelement.performAction(mAction);
_L4:
            return;
_L2:
            if(mActionExp != null)
            {
                String s = mActionExp.evaluateStr();
                if(s != null)
                    screenelement.performAction(s);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static final String TAG_NAME = "ActionCommand";
        private String mAction;
        private Expression mActionExp;

        public ActionPerformCommand(ScreenElement screenelement, Element element)
        {
            super(screenelement, element);
            mAction = element.getAttribute("action");
            if(TextUtils.isEmpty(mAction))
            {
                mAction = null;
                mActionExp = Expression.build(getVariables(), element.getAttribute("actionExp"));
            }
        }
    }

    private static class AnimationCommand extends TargetCommand
    {

        private static int[] _2D_getmiui_2D_maml_2D_ActionCommand$AnimationCommand$CommandTypeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_ActionCommand$AnimationCommand$CommandTypeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_ActionCommand$AnimationCommand$CommandTypeSwitchesValues;
            int ai[] = new int[CommandType.values().length];
            try
            {
                ai[CommandType.INVALID.ordinal()] = 5;
            }
            catch(NoSuchFieldError nosuchfielderror4) { }
            try
            {
                ai[CommandType.PAUSE.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[CommandType.PLAY.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[CommandType.PLAY_WITH_PARAMS.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[CommandType.RESUME.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_ActionCommand$AnimationCommand$CommandTypeSwitchesValues = ai;
            return ai;
        }

        public void doPerform()
        {
            ScreenElement screenelement;
            screenelement = (ScreenElement)getTarget();
            if(screenelement == null)
                return;
            if((mCommand == CommandType.PLAY || mCommand == CommandType.PLAY_WITH_PARAMS) && (mAllAni || mAniTags != null))
                screenelement.setAnim(mAniTags);
            _2D_getmiui_2D_maml_2D_ActionCommand$AnimationCommand$CommandTypeSwitchesValues()[mCommand.ordinal()];
            JVM INSTR tableswitch 1 4: default 96
        //                       1 104
        //                       2 97
        //                       3 118
        //                       4 111;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            return;
_L3:
            screenelement.playAnim();
            continue; /* Loop/switch isn't completed */
_L2:
            screenelement.pauseAnim();
            continue; /* Loop/switch isn't completed */
_L5:
            screenelement.resumeAnim();
            continue; /* Loop/switch isn't completed */
_L4:
            long l = 0L;
            long l1 = -1L;
            boolean flag = false;
            boolean flag1 = false;
            double d;
            if(mPlayParams.length > 0)
            {
                if(mPlayParams[0] == null)
                    d = 0.0D;
                else
                    d = mPlayParams[0].evaluate();
                l = (long)d;
            }
            if(mPlayParams.length > 1)
            {
                if(mPlayParams[1] == null)
                    d = -1D;
                else
                    d = mPlayParams[1].evaluate();
                l1 = (long)d;
            }
            if(mPlayParams.length > 2)
                if(mPlayParams[2] == null || mPlayParams[2].evaluate() <= 0.0D)
                    flag = false;
                else
                    flag = true;
            if(mPlayParams.length > 3)
                if(mPlayParams[3] == null || mPlayParams[3].evaluate() <= 0.0D)
                    flag1 = false;
                else
                    flag1 = true;
            screenelement.playAnim(l, l1, flag, flag1);
            if(true) goto _L1; else goto _L6
_L6:
        }

        private static final int _2D_miui_2D_maml_2D_ActionCommand$AnimationCommand$CommandTypeSwitchesValues[];
        public static final String TAG_NAME = "AnimationCommand";
        private boolean mAllAni;
        private String mAniTags[];
        private CommandType mCommand;
        private Expression mPlayParams[];

        public AnimationCommand(ScreenElement screenelement, Element element)
        {
            super(screenelement, element);
            screenelement = element.getAttribute("command");
            if(screenelement.equalsIgnoreCase("play"))
                mCommand = CommandType.PLAY;
            else
            if(screenelement.equalsIgnoreCase("pause"))
                mCommand = CommandType.PAUSE;
            else
            if(screenelement.equalsIgnoreCase("resume"))
                mCommand = CommandType.RESUME;
            else
            if(screenelement.toLowerCase().startsWith("play(") && screenelement.endsWith(")"))
            {
                mCommand = CommandType.PLAY_WITH_PARAMS;
                mPlayParams = Expression.buildMultiple(getVariables(), screenelement.substring(5, screenelement.length() - 1));
                if(mPlayParams != null && mPlayParams.length != 2 && mPlayParams.length != 4)
                    Log.e("ActionCommand", "bad expression format");
            } else
            {
                mCommand = CommandType.INVALID;
            }
            screenelement = element.getAttribute("tags");
            if(".".equals(screenelement))
                mAllAni = true;
            else
            if(!TextUtils.isEmpty(screenelement))
                mAniTags = screenelement.split(",");
        }
    }

    private static final class AnimationCommand.CommandType extends Enum
    {

        public static AnimationCommand.CommandType valueOf(String s)
        {
            return (AnimationCommand.CommandType)Enum.valueOf(miui/maml/ActionCommand$AnimationCommand$CommandType, s);
        }

        public static AnimationCommand.CommandType[] values()
        {
            return $VALUES;
        }

        private static final AnimationCommand.CommandType $VALUES[];
        public static final AnimationCommand.CommandType INVALID;
        public static final AnimationCommand.CommandType PAUSE;
        public static final AnimationCommand.CommandType PLAY;
        public static final AnimationCommand.CommandType PLAY_WITH_PARAMS;
        public static final AnimationCommand.CommandType RESUME;

        static 
        {
            INVALID = new AnimationCommand.CommandType("INVALID", 0);
            PLAY = new AnimationCommand.CommandType("PLAY", 1);
            PAUSE = new AnimationCommand.CommandType("PAUSE", 2);
            RESUME = new AnimationCommand.CommandType("RESUME", 3);
            PLAY_WITH_PARAMS = new AnimationCommand.CommandType("PLAY_WITH_PARAMS", 4);
            $VALUES = (new AnimationCommand.CommandType[] {
                INVALID, PLAY, PAUSE, RESUME, PLAY_WITH_PARAMS
            });
        }

        private AnimationCommand.CommandType(String s, int i)
        {
            super(s, i);
        }
    }

    private static class AnimationProperty extends PropertyCommand
    {

        private static int[] _2D_getmiui_2D_maml_2D_ActionCommand$AnimationProperty$TypeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_ActionCommand$AnimationProperty$TypeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_ActionCommand$AnimationProperty$TypeSwitchesValues;
            int ai[] = new int[Type.values().length];
            try
            {
                ai[Type.INVALID.ordinal()] = 5;
            }
            catch(NoSuchFieldError nosuchfielderror4) { }
            try
            {
                ai[Type.PAUSE.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[Type.PLAY.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[Type.PLAY_WITH_PARAMS.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[Type.RESUME.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_ActionCommand$AnimationProperty$TypeSwitchesValues = ai;
            return ai;
        }

        public void doPerform()
        {
            _2D_getmiui_2D_maml_2D_ActionCommand$AnimationProperty$TypeSwitchesValues()[mType.ordinal()];
            JVM INSTR tableswitch 1 4: default 40
        //                       1 51
        //                       2 41
        //                       3 71
        //                       4 61;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            return;
_L3:
            mTargetElement.playAnim();
            continue; /* Loop/switch isn't completed */
_L2:
            mTargetElement.pauseAnim();
            continue; /* Loop/switch isn't completed */
_L5:
            mTargetElement.resumeAnim();
            continue; /* Loop/switch isn't completed */
_L4:
            long l = 0L;
            long l1 = -1L;
            boolean flag = false;
            boolean flag1 = false;
            double d;
            if(mPlayParams.length > 0)
            {
                if(mPlayParams[0] == null)
                    d = 0.0D;
                else
                    d = mPlayParams[0].evaluate();
                l = (long)d;
            }
            if(mPlayParams.length > 1)
            {
                if(mPlayParams[1] == null)
                    d = -1D;
                else
                    d = mPlayParams[1].evaluate();
                l1 = (long)d;
            }
            if(mPlayParams.length > 2)
                if(mPlayParams[2] == null || mPlayParams[2].evaluate() <= 0.0D)
                    flag = false;
                else
                    flag = true;
            if(mPlayParams.length > 3)
                if(mPlayParams[3] == null || mPlayParams[3].evaluate() <= 0.0D)
                    flag1 = false;
                else
                    flag1 = true;
            mTargetElement.playAnim(l, l1, flag, flag1);
            if(true) goto _L1; else goto _L6
_L6:
        }

        private static final int _2D_miui_2D_maml_2D_ActionCommand$AnimationProperty$TypeSwitchesValues[];
        public static final String PROPERTY_NAME = "animation";
        private Expression mPlayParams[];
        private Type mType;

        protected AnimationProperty(ScreenElement screenelement, Variable variable, String s)
        {
            super(screenelement, variable, s);
            if(!s.equalsIgnoreCase("play")) goto _L2; else goto _L1
_L1:
            mType = Type.PLAY;
_L4:
            return;
_L2:
            if(s.equalsIgnoreCase("pause"))
                mType = Type.PAUSE;
            else
            if(s.equalsIgnoreCase("resume"))
                mType = Type.RESUME;
            else
            if(s.toLowerCase().startsWith("play(") && s.endsWith(")"))
            {
                mType = Type.PLAY_WITH_PARAMS;
                mPlayParams = Expression.buildMultiple(getVariables(), s.substring(5, s.length() - 1));
                if(mPlayParams != null && mPlayParams.length != 2 && mPlayParams.length != 4)
                    Log.e("ActionCommand", "bad expression format");
            } else
            {
                mType = Type.INVALID;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }
    }

    static final class AnimationProperty.Type extends Enum
    {

        public static AnimationProperty.Type valueOf(String s)
        {
            return (AnimationProperty.Type)Enum.valueOf(miui/maml/ActionCommand$AnimationProperty$Type, s);
        }

        public static AnimationProperty.Type[] values()
        {
            return $VALUES;
        }

        private static final AnimationProperty.Type $VALUES[];
        public static final AnimationProperty.Type INVALID;
        public static final AnimationProperty.Type PAUSE;
        public static final AnimationProperty.Type PLAY;
        public static final AnimationProperty.Type PLAY_WITH_PARAMS;
        public static final AnimationProperty.Type RESUME;

        static 
        {
            INVALID = new AnimationProperty.Type("INVALID", 0);
            PLAY = new AnimationProperty.Type("PLAY", 1);
            PAUSE = new AnimationProperty.Type("PAUSE", 2);
            RESUME = new AnimationProperty.Type("RESUME", 3);
            PLAY_WITH_PARAMS = new AnimationProperty.Type("PLAY_WITH_PARAMS", 4);
            $VALUES = (new AnimationProperty.Type[] {
                INVALID, PLAY, PAUSE, RESUME, PLAY_WITH_PARAMS
            });
        }

        private AnimationProperty.Type(String s, int i)
        {
            super(s, i);
        }
    }

    private static abstract class BaseMethodCommand extends TargetCommand
    {

        public void finish()
        {
            super.finish();
            mParamValues = null;
        }

        public void init()
        {
            super.init();
            if(mParamTypes != null)
            {
                if(mParamObjVars == null)
                    mParamObjVars = new ObjVar[mParamTypes.length];
                int i = 0;
                while(i < mParamTypes.length) 
                {
                    mParamObjVars[i] = null;
                    Class class1 = mParamTypes[i];
                    if((!class1.isPrimitive() || !(class1.isArray() ^ true)) && class1 != java/lang/String)
                    {
                        Object obj = mParams[i];
                        if(obj != null)
                        {
                            obj = ((Expression) (obj)).evaluateStr();
                            if(!TextUtils.isEmpty(((CharSequence) (obj))))
                                mParamObjVars[i] = new ObjVar(((String) (obj)), getVariables());
                        }
                    }
                    i++;
                }
            }
            if(mTargetClassName == null)
                break MISSING_BLOCK_LABEL_145;
            mTargetClass = Class.forName(mTargetClassName);
_L1:
            return;
            Exception exception;
            exception;
            Log.w("ActionCommand", (new StringBuilder()).append("target class not found, name: ").append(mTargetClassName).append("\n").append(exception.toString()).toString());
              goto _L1
        }

        protected void prepareParams()
        {
            if(mParams != null)
            {
                if(mParamValues == null)
                    mParamValues = new Object[mParams.length];
                int i = 0;
                while(i < mParams.length) 
                {
                    mParamValues[i] = null;
                    Object obj = mParamTypes[i];
                    Expression expression = mParams[i];
                    if(expression != null)
                        if(obj == java/lang/String)
                            mParamValues[i] = expression.evaluateStr();
                        else
                        if(obj == Integer.TYPE)
                            mParamValues[i] = Integer.valueOf((int)(long)expression.evaluate());
                        else
                        if(obj == Long.TYPE)
                            mParamValues[i] = Long.valueOf((long)expression.evaluate());
                        else
                        if(obj == Boolean.TYPE)
                        {
                            obj = ((Object) (mParamValues));
                            boolean flag;
                            if(expression.evaluate() > 0.0D)
                                flag = true;
                            else
                                flag = false;
                            obj[i] = Boolean.valueOf(flag);
                        } else
                        if(obj == Double.TYPE)
                            mParamValues[i] = Double.valueOf(expression.evaluate());
                        else
                        if(obj == Float.TYPE)
                            mParamValues[i] = Float.valueOf((float)expression.evaluate());
                        else
                        if(obj == Byte.TYPE)
                            mParamValues[i] = Byte.valueOf((byte)(int)(long)expression.evaluate());
                        else
                        if(obj == Short.TYPE)
                            mParamValues[i] = Short.valueOf((short)(int)(long)expression.evaluate());
                        else
                        if(obj == Character.TYPE)
                        {
                            mParamValues[i] = Character.valueOf((char)(int)(long)expression.evaluate());
                        } else
                        {
                            Object obj1 = mParamObjVars[i];
                            Object aobj[] = mParamValues;
                            if(obj1 != null)
                                obj1 = ((ObjVar) (obj1)).get();
                            else
                                obj1 = null;
                            aobj[i] = obj1;
                        }
                    i++;
                }
            }
        }

        protected static final int ERROR_EXCEPTION = -2;
        protected static final int ERROR_NO_METHOD = -1;
        protected static final int ERROR_SUCCESS = 1;
        protected IndexedVariable mErrorCodeVar;
        private ObjVar mParamObjVars[];
        protected Class mParamTypes[];
        protected Object mParamValues[];
        private Expression mParams[];
        protected IndexedVariable mReturnVar;
        protected Class mTargetClass;
        protected String mTargetClassName;

        public BaseMethodCommand(ScreenElement screenelement, Element element)
        {
            super(screenelement, element);
            mTargetClassName = element.getAttribute("class");
            if(TextUtils.isEmpty(mTargetClassName))
                mTargetClassName = null;
            screenelement = element.getAttribute("params");
            mParams = Expression.buildMultiple(getVariables(), screenelement);
            screenelement = element.getAttribute("paramTypes");
            if(mParams != null && TextUtils.isEmpty(screenelement) ^ true)
            {
                screenelement = TextUtils.split(screenelement, ",");
                String s;
                try
                {
                    mParamTypes = ReflectionHelper.strTypesToClass(screenelement);
                    if(mParams.length != mParamTypes.length)
                    {
                        screenelement = JVM INSTR new #85  <Class StringBuilder>;
                        screenelement.StringBuilder();
                        Log.e("ActionCommand", screenelement.append(mLogStr).append("different length of params and paramTypes").toString());
                        mParams = null;
                        mParamTypes = null;
                    }
                }
                // Misplaced declaration of an exception variable
                catch(ScreenElement screenelement)
                {
                    Log.e("ActionCommand", (new StringBuilder()).append(mLogStr).append("invalid method paramTypes. ").append(screenelement.toString()).toString());
                    mParams = null;
                    mParamTypes = null;
                }
            }
            s = element.getAttribute("return");
            if(!TextUtils.isEmpty(s))
            {
                screenelement = VariableType.parseType(element.getAttribute("returnType"));
                mReturnVar = new IndexedVariable(s, getVariables(), screenelement.isNumber());
            }
            screenelement = element.getAttribute("errorVar");
            if(!TextUtils.isEmpty(screenelement))
                mErrorCodeVar = new IndexedVariable(screenelement, getVariables(), true);
            mLogStr = (new StringBuilder()).append(mLogStr).append(", class=").append(mTargetClassName).append(" type=").append(mTargetType.toString()).toString();
        }
    }

    private static class BluetoothSwitchCommand extends NotificationReceiver
    {

        private boolean ensureBluetoothAdapter()
        {
            if(mBluetoothAdapter == null)
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            boolean flag;
            if(mBluetoothAdapter != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        protected void doPerform()
        {
            if(!ensureBluetoothAdapter())
                return;
            if(!mOnOffHelper.mIsToggle) goto _L2; else goto _L1
_L1:
            if(mBluetoothEnable)
            {
                mBluetoothAdapter.disable();
                mBluetoothEnabling = false;
            } else
            {
                mBluetoothAdapter.enable();
                mBluetoothEnabling = true;
            }
_L4:
            update();
            return;
_L2:
            if(!mBluetoothEnabling && mBluetoothEnable != mOnOffHelper.mIsOn)
                if(mOnOffHelper.mIsOn)
                {
                    mBluetoothAdapter.enable();
                    mBluetoothEnabling = true;
                } else
                {
                    mBluetoothAdapter.disable();
                    mBluetoothEnabling = false;
                }
            if(true) goto _L4; else goto _L3
_L3:
        }

        protected void update()
        {
            byte byte0 = 0;
            if(!ensureBluetoothAdapter())
                return;
            mBluetoothEnable = mBluetoothAdapter.isEnabled();
            if(mBluetoothEnable)
            {
                mBluetoothEnabling = false;
                updateState(1);
            } else
            {
                if(mBluetoothEnabling)
                    byte0 = 2;
                updateState(byte0);
            }
        }

        private BluetoothAdapter mBluetoothAdapter;
        private boolean mBluetoothEnable;
        private boolean mBluetoothEnabling;
        private OnOffCommandHelper mOnOffHelper;

        public BluetoothSwitchCommand(ScreenElement screenelement, String s)
        {
            super(screenelement, "bluetooth_state", "android.bluetooth.adapter.action.STATE_CHANGED");
            mOnOffHelper = new OnOffCommandHelper(s);
        }
    }

    private static class ConditionCommand extends ActionCommand
    {

        protected void doPerform()
        {
            if(mCondition.evaluate() > 0.0D)
                mCommand.perform();
        }

        public void init()
        {
            mCommand.init();
        }

        private ActionCommand mCommand;
        private Expression mCondition;

        public ConditionCommand(ActionCommand actioncommand, Expression expression)
        {
            super(actioncommand.getRoot());
            mCommand = actioncommand;
            mCondition = expression;
        }
    }

    private static class DataSwitchCommand extends NotificationReceiver
    {

        protected void doPerform()
        {
            boolean flag = mApnEnable;
            if(mOnOffHelper.mIsToggle)
                flag = mApnEnable ^ true;
            else
                flag = mOnOffHelper.mIsOn;
            if(mApnEnable != flag)
                mMobileDataUtils.enableMobileData(mScreenElement.getContext().mContext, flag);
        }

        protected void update()
        {
            mApnEnable = mMobileDataUtils.isMobileEnable(mScreenElement.getContext().mContext);
            int i;
            if(mApnEnable)
                i = 1;
            else
                i = 0;
            updateState(i);
        }

        private boolean mApnEnable;
        private MobileDataUtils mMobileDataUtils;
        private OnOffCommandHelper mOnOffHelper;

        public DataSwitchCommand(ScreenElement screenelement, String s)
        {
            super(screenelement, "data_state", NotifierManager.TYPE_MOBILE_DATA);
            mOnOffHelper = new OnOffCommandHelper(s);
            mMobileDataUtils = MobileDataUtils.getInstance();
        }
    }

    private static class DelayCommand extends ActionCommand
    {

        static ActionCommand _2D_get0(DelayCommand delaycommand)
        {
            return delaycommand.mCommand;
        }

        protected void doPerform()
        {
            getRoot().postDelayed(mCmd, mDelay);
        }

        public void finish()
        {
            getRoot().removeCallbacks(mCmd);
        }

        public void init()
        {
            mCommand.init();
        }

        private Runnable mCmd;
        private ActionCommand mCommand;
        private long mDelay;

        public DelayCommand(ActionCommand actioncommand, long l)
        {
            super(actioncommand.getRoot());
            mCommand = actioncommand;
            mDelay = l;
            mCmd = new _cls1();
        }
    }

    private static class ExternCommand extends ActionCommand
    {

        protected void doPerform()
        {
            String s = null;
            ScreenElementRoot screenelementroot = getRoot();
            String s1 = mCommand;
            Double double1;
            if(mNumParaExp == null)
                double1 = null;
            else
                double1 = Double.valueOf(mNumParaExp.evaluate());
            if(mStrParaExp != null)
                s = mStrParaExp.evaluateStr();
            screenelementroot.issueExternCommand(s1, double1, s);
        }

        public static final String TAG_NAME = "ExternCommand";
        private String mCommand;
        private Expression mNumParaExp;
        private Expression mStrParaExp;

        public ExternCommand(ScreenElement screenelement, Element element)
        {
            super(screenelement);
            mCommand = element.getAttribute("command");
            mNumParaExp = Expression.build(getVariables(), element.getAttribute("numPara"));
            mStrParaExp = Expression.build(getVariables(), element.getAttribute("strPara"));
        }
    }

    private static class FieldCommand extends BaseMethodCommand
    {

        private static int[] _2D_getmiui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues;
            int ai[] = new int[TargetCommand.TargetType.values().length];
            try
            {
                ai[TargetCommand.TargetType.CONSTRUCTOR.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[TargetCommand.TargetType.SCREEN_ELEMENT.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[TargetCommand.TargetType.VARIABLE.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues = ai;
            return ai;
        }

        protected void doPerform()
        {
            if(mField == null)
                loadField();
            if(mField == null) goto _L2; else goto _L1
_L1:
            _2D_getmiui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues()[mTargetType.ordinal()];
            JVM INSTR tableswitch 1 2: default 52
        //                       1 53
        //                       2 53;
               goto _L2 _L3 _L3
_L2:
            return;
_L3:
            if(!mIsSet) goto _L5; else goto _L4
_L4:
            prepareParams();
            if(mParamValues != null && mParamValues.length == 1)
                mField.set(getTarget(), mParamValues[0]);
              goto _L2
            Object obj;
            obj;
            Log.e("ActionCommand", ((IllegalArgumentException) (obj)).toString());
              goto _L2
_L5:
            if(mReturnVar == null) goto _L2; else goto _L6
_L6:
            Object obj1 = mField.get(getTarget());
            mReturnVar.set(obj1);
              goto _L2
            obj1;
            Log.e("ActionCommand", ((IllegalAccessException) (obj1)).toString());
              goto _L2
            obj1;
            Log.e("ActionCommand", (new StringBuilder()).append(mLogStr).append("Field target is null. ").append(((NullPointerException) (obj1)).toString()).toString());
              goto _L2
        }

        public void init()
        {
            super.init();
            _2D_getmiui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues()[mTargetType.ordinal()];
            JVM INSTR tableswitch 1 2: default 36
        //                       1 37
        //                       2 37;
               goto _L1 _L2 _L2
_L1:
            return;
_L2:
            if(mField != null)
                loadField();
            if(true) goto _L1; else goto _L3
_L3:
        }

        protected void loadField()
        {
            if(mTargetClass == null)
            {
                Object obj = getTarget();
                if(obj != null)
                    mTargetClass = obj.getClass();
            }
            if(mTargetClass == null)
                break MISSING_BLOCK_LABEL_81;
            mField = mTargetClass.getField(mFieldName);
_L1:
            return;
            NoSuchFieldException nosuchfieldexception;
            nosuchfieldexception;
            Log.e("ActionCommand", (new StringBuilder()).append(mLogStr).append(nosuchfieldexception.toString()).toString());
              goto _L1
            Log.e("ActionCommand", (new StringBuilder()).append(mLogStr).append("class is null.").toString());
              goto _L1
        }

        private static final int _2D_miui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues[];
        public static final String TAG_NAME = "FieldCommand";
        private Field mField;
        private String mFieldName;
        private boolean mIsSet;

        public FieldCommand(ScreenElement screenelement, Element element)
        {
            super(screenelement, element);
            mFieldName = element.getAttribute("field");
            mLogStr = (new StringBuilder()).append("FieldCommand, ").append(mLogStr).append(", field=").append(mFieldName).append("\n").toString();
            screenelement = element.getAttribute("method");
            if(!"get".equals(screenelement)) goto _L2; else goto _L1
_L1:
            mIsSet = false;
_L4:
            return;
_L2:
            if("set".equals(screenelement))
                mIsSet = true;
            if(true) goto _L4; else goto _L3
_L3:
        }
    }

    private static class IntentCommand extends ActionCommand
    {

        private static int[] _2D_getmiui_2D_maml_2D_ActionCommand$IntentCommand$IntentTypeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_ActionCommand$IntentCommand$IntentTypeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_ActionCommand$IntentCommand$IntentTypeSwitchesValues;
            int ai[] = new int[IntentType.values().length];
            try
            {
                ai[IntentType.Activity.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[IntentType.Broadcast.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[IntentType.Service.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[IntentType.Var.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_ActionCommand$IntentCommand$IntentTypeSwitchesValues = ai;
            return ai;
        }

        protected void doPerform()
        {
            if(mIntent == null) goto _L2; else goto _L1
_L1:
            mIntentInfo.update(mIntent);
            _2D_getmiui_2D_maml_2D_ActionCommand$IntentCommand$IntentTypeSwitchesValues()[mIntentType.ordinal()];
            JVM INSTR tableswitch 1 4: default 60
        //                       1 61
        //                       2 224
        //                       3 238
        //                       4 253;
               goto _L2 _L3 _L4 _L5 _L6
_L2:
            return;
_L3:
            Object obj;
            if(mActivityOptionsBundle == null)
                break MISSING_BLOCK_LABEL_150;
            obj = (Bundle)mActivityOptionsBundle.get();
_L7:
            Object obj1 = getContext().getPackageManager().queryIntentActivities(mIntent, 0x10000);
            if(obj1 == null)
                break MISSING_BLOCK_LABEL_155;
            if(((List) (obj1)).size() <= 0)
                break MISSING_BLOCK_LABEL_155;
            getContext().startActivity(mIntent, ((Bundle) (obj)));
              goto _L2
            obj = null;
              goto _L7
            if(TextUtils.isEmpty(mIntent.getPackage()))
                break MISSING_BLOCK_LABEL_193;
            obj1 = mIntent.getPackage();
_L8:
            PreloadAppPolicyHelper.installPreloadedDataApp(getContext(), ((String) (obj1)), mIntent, ((Bundle) (obj)));
              goto _L2
label0:
            {
                if(TextUtils.isEmpty(mIntent.getComponent().getPackageName()))
                    break label0;
                obj1 = mIntent.getComponent().getPackageName();
            }
              goto _L8
            return;
              goto _L2
_L4:
            try
            {
                getContext().sendBroadcast(mIntent);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                if(mFallbackTrigger != null)
                {
                    Log.i("ActionCommand", "fail to send Intent, fallback...");
                    mFallbackTrigger.perform();
                } else
                {
                    Log.e("ActionCommand", ((Exception) (obj)).toString());
                }
            }
              goto _L2
_L5:
            getContext().startService(mIntent);
              goto _L2
_L6:
            if(mIntentVar == null) goto _L2; else goto _L9
_L9:
            mIntentVar.set(mIntent);
              goto _L2
        }

        public void finish()
        {
            if(mFallbackTrigger != null)
                mFallbackTrigger.finish();
        }

        public void init()
        {
            Task task = getRoot().findTask(mIntentInfo.getId());
            if(task != null && TextUtils.isEmpty(task.action) ^ true)
                mIntentInfo.set(task);
            if(Utils.isProtectedIntent(mIntentInfo.getAction()))
                return;
            mIntent = new Intent();
            mIntentInfo.update(mIntent);
            if(mFlags == -1) goto _L2; else goto _L1
_L1:
            mIntent.setFlags(mFlags);
_L4:
            if(mFallbackTrigger != null)
                mFallbackTrigger.init();
            return;
_L2:
            if(mIntentType == IntentType.Activity)
                mIntent.setFlags(0x34000000);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void pause()
        {
            if(mFallbackTrigger != null)
                mFallbackTrigger.pause();
        }

        public void resume()
        {
            if(mFallbackTrigger != null)
                mFallbackTrigger.resume();
        }

        private static final int _2D_miui_2D_maml_2D_ActionCommand$IntentCommand$IntentTypeSwitchesValues[];
        private static final String TAG_FALLBACK = "Fallback";
        public static final String TAG_NAME = "IntentCommand";
        private ObjVar mActivityOptionsBundle;
        private CommandTrigger mFallbackTrigger;
        private int mFlags;
        private Intent mIntent;
        private IntentInfo mIntentInfo;
        private IntentType mIntentType;
        private IndexedVariable mIntentVar;

        public IntentCommand(ScreenElement screenelement, Element element)
        {
            ObjVar objvar = null;
            super(screenelement);
            mIntentType = IntentType.Activity;
            mIntentInfo = new IntentInfo(element, getVariables());
            boolean flag = Boolean.parseBoolean(element.getAttribute("broadcast"));
            String s = element.getAttribute("type");
            if(flag || "broadcast".equals(s))
                mIntentType = IntentType.Broadcast;
            else
            if("service".equals(s))
                mIntentType = IntentType.Service;
            else
            if("activity".equals(s))
                mIntentType = IntentType.Activity;
            else
            if("var".equals(s))
            {
                mIntentType = IntentType.Var;
                s = element.getAttribute("intentVar");
                if(!TextUtils.isEmpty(s))
                    mIntentVar = new IndexedVariable(s, getVariables(), false);
            }
            mFlags = Utils.getAttrAsInt(element, "flags", -1);
            s = element.getAttribute("activityOption");
            if(!TextUtils.isEmpty(s))
                objvar = new ObjVar(s, getVariables());
            mActivityOptionsBundle = objvar;
            element = Utils.getChild(element, "Fallback");
            if(element != null)
                mFallbackTrigger = new CommandTrigger(element, screenelement);
        }
    }

    private static final class IntentCommand.IntentType extends Enum
    {

        public static IntentCommand.IntentType valueOf(String s)
        {
            return (IntentCommand.IntentType)Enum.valueOf(miui/maml/ActionCommand$IntentCommand$IntentType, s);
        }

        public static IntentCommand.IntentType[] values()
        {
            return $VALUES;
        }

        private static final IntentCommand.IntentType $VALUES[];
        public static final IntentCommand.IntentType Activity;
        public static final IntentCommand.IntentType Broadcast;
        public static final IntentCommand.IntentType Service;
        public static final IntentCommand.IntentType Var;

        static 
        {
            Activity = new IntentCommand.IntentType("Activity", 0);
            Broadcast = new IntentCommand.IntentType("Broadcast", 1);
            Service = new IntentCommand.IntentType("Service", 2);
            Var = new IntentCommand.IntentType("Var", 3);
            $VALUES = (new IntentCommand.IntentType[] {
                Activity, Broadcast, Service, Var
            });
        }

        private IntentCommand.IntentType(String s, int i)
        {
            super(s, i);
        }
    }

    private static class LoopCommand extends MultiCommand
    {

        protected void doPerform()
        {
            int i;
            int j;
            if(mBeginExp == null)
                i = 0;
            else
                i = (int)mBeginExp.evaluate();
            if(mCountExp != null)
                j = ((int)mCountExp.evaluate() + i) - 1;
            else
            if(mEndExp == null)
                j = 0;
            else
                j = (int)mEndExp.evaluate();
            if((long)(j - i) > 10000L)
                Log.w("ActionCommand", (new StringBuilder()).append("count is too large: ").append(j - i).append(", exceeds WARNING ").append(10000L).toString());
            do
            {
                if(i > j || mConditionExp != null && mConditionExp.evaluate() <= 0.0D)
                    return;
                if(mIndexVar != null)
                    mIndexVar.set(i);
                int k = mCommands.size();
                for(int l = 0; l < k; l++)
                    ((ActionCommand)mCommands.get(l)).perform();

                i++;
            } while(true);
        }

        private static final long COUNT_WARNING = 10000L;
        public static final String TAG_NAME = "LoopCommand";
        private Expression mBeginExp;
        private Expression mConditionExp;
        private Expression mCountExp;
        private Expression mEndExp;
        private IndexedVariable mIndexVar;

        public LoopCommand(ScreenElement screenelement, Element element)
        {
            super(screenelement, element);
            String s = element.getAttribute("indexName");
            screenelement = getVariables();
            if(!TextUtils.isEmpty(s))
                mIndexVar = new IndexedVariable(s, screenelement, true);
            mBeginExp = Expression.build(screenelement, element.getAttribute("begin"));
            mCountExp = Expression.build(screenelement, element.getAttribute("count"));
            if(mCountExp == null)
                mEndExp = Expression.build(screenelement, element.getAttribute("end"));
            mConditionExp = Expression.build(screenelement, element.getAttribute("loopCondition"));
        }
    }

    private static class MethodCommand extends BaseMethodCommand
    {

        private static int[] _2D_getmiui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues;
            int ai[] = new int[TargetCommand.TargetType.values().length];
            try
            {
                ai[TargetCommand.TargetType.CONSTRUCTOR.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[TargetCommand.TargetType.SCREEN_ELEMENT.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[TargetCommand.TargetType.VARIABLE.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues = ai;
            return ai;
        }

        protected void doPerform()
        {
            boolean flag;
            boolean flag1;
            int i;
            Object obj;
            int j;
            int k;
            prepareParams();
            flag = false;
            flag1 = false;
            i = 0;
            obj = null;
            j = ((flag) ? 1 : 0);
            k = ((flag1) ? 1 : 0);
            _2D_getmiui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues()[mTargetType.ordinal()];
            JVM INSTR tableswitch 1 3: default 56
        //                       1 181
        //                       2 102
        //                       3 102;
               goto _L1 _L2 _L3 _L3
_L1:
            j = i;
            k = i;
            if(mReturnVar == null) goto _L5; else goto _L4
_L4:
            j = i;
            k = i;
            mReturnVar.set(obj);
_L5:
            if(mErrorCodeVar != null)
                mErrorCodeVar.set(i);
_L14:
            return;
_L3:
            j = ((flag) ? 1 : 0);
            k = ((flag1) ? 1 : 0);
            if(mMethod != null) goto _L7; else goto _L6
_L6:
            j = ((flag) ? 1 : 0);
            k = ((flag1) ? 1 : 0);
            loadMethod();
_L7:
            j = ((flag) ? 1 : 0);
            k = ((flag1) ? 1 : 0);
            if(mMethod == null) goto _L9; else goto _L8
_L8:
            j = ((flag) ? 1 : 0);
            k = ((flag1) ? 1 : 0);
            obj = getTarget();
            j = ((flag) ? 1 : 0);
            k = ((flag1) ? 1 : 0);
            obj = mMethod.invoke(obj, mParamValues);
            i = 1;
            continue; /* Loop/switch isn't completed */
_L9:
            i = -1;
            continue; /* Loop/switch isn't completed */
_L2:
            j = ((flag) ? 1 : 0);
            k = ((flag1) ? 1 : 0);
            if(mCtor == null) goto _L11; else goto _L10
_L10:
            j = ((flag) ? 1 : 0);
            k = ((flag1) ? 1 : 0);
            obj = mCtor.newInstance(mParamValues);
            i = 1;
            continue; /* Loop/switch isn't completed */
_L11:
            i = -1;
            continue; /* Loop/switch isn't completed */
            Exception exception1;
            exception1;
            k = j;
            obj = exception1.getCause();
            k = j;
            StringBuilder stringbuilder1 = JVM INSTR new #61  <Class StringBuilder>;
            k = j;
            stringbuilder1.StringBuilder();
            k = j;
            StringBuilder stringbuilder = stringbuilder1.append(mLogStr).append(exception1.toString());
            if(obj == null) goto _L13; else goto _L12
_L12:
            k = j;
            stringbuilder1 = JVM INSTR new #61  <Class StringBuilder>;
            k = j;
            stringbuilder1.StringBuilder();
            k = j;
            obj = stringbuilder1.append("\n cause: ").append(((Throwable) (obj)).toString()).toString();
_L15:
            k = j;
            Log.e("ActionCommand", stringbuilder.append(((String) (obj))).toString());
            if(mErrorCodeVar != null)
                mErrorCodeVar.set(-2D);
              goto _L14
_L13:
            obj = "";
              goto _L15
            Exception exception;
            exception;
            if(mErrorCodeVar != null)
                mErrorCodeVar.set(k);
            throw exception;
            if(true) goto _L1; else goto _L16
_L16:
        }

        public void init()
        {
            super.init();
            _2D_getmiui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues()[mTargetType.ordinal()];
            JVM INSTR tableswitch 1 3: default 40
        //                       1 55
        //                       2 41
        //                       3 41;
               goto _L1 _L2 _L3 _L3
_L1:
            return;
_L3:
            if(mMethod == null)
                loadMethod();
            continue; /* Loop/switch isn't completed */
_L2:
            if(!getRoot().getCapability(4))
                mCtor = null;
            else
            if(mCtor == null)
                if(mTargetClass != null)
                    try
                    {
                        mCtor = mTargetClass.getConstructor(mParamTypes);
                    }
                    catch(NoSuchMethodException nosuchmethodexception)
                    {
                        Log.e("ActionCommand", (new StringBuilder()).append(mLogStr).append("init, fail to find method. ").append(nosuchmethodexception.toString()).toString());
                    }
                else
                    Log.e("ActionCommand", (new StringBuilder()).append(mLogStr).append("init, class is null.").toString());
            if(true) goto _L1; else goto _L4
_L4:
        }

        protected void loadMethod()
        {
            if(mTargetClass == null)
            {
                Object obj = getTarget();
                if(obj != null)
                    mTargetClass = obj.getClass();
            }
            if(mTargetClass != null)
            {
                try
                {
                    mMethod = mTargetClass.getMethod(mMethodName, mParamTypes);
                }
                catch(NoSuchMethodException nosuchmethodexception)
                {
                    Log.e("ActionCommand", (new StringBuilder()).append(mLogStr).append("loadMethod(). ").append(nosuchmethodexception.toString()).toString());
                }
                Log.d("ActionCommand", (new StringBuilder()).append(mLogStr).append("loadMethod(), successful.  ").append(mMethod.toString()).toString());
            } else
            {
                Log.e("ActionCommand", (new StringBuilder()).append(mLogStr).append("loadMethod(), class is null.").toString());
            }
        }

        private static final int _2D_miui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues[];
        public static final String TAG_NAME = "MethodCommand";
        private Constructor mCtor;
        private Method mMethod;
        private String mMethodName;

        public MethodCommand(ScreenElement screenelement, Element element)
        {
            super(screenelement, element);
            mMethodName = element.getAttribute("method");
            mLogStr = (new StringBuilder()).append("MethodCommand, ").append(mLogStr).append(", method=").append(mMethodName).append("\n    ").toString();
        }
    }

    private static class ModeToggleHelper
    {

        private int findMode(String s)
        {
            for(int i = 0; i < mModeNames.size(); i++)
                if(((String)mModeNames.get(i)).equals(s))
                    return i;

            return -1;
        }

        public void addMode(String s, int i)
        {
            mModeNames.add(s);
            mModeIds.add(Integer.valueOf(i));
        }

        public boolean build(String s)
        {
            int i = findMode(s);
            if(i >= 0)
            {
                mCurModeIndex = i;
                return true;
            }
            if("toggle".equals(s))
            {
                mToggleAll = true;
                return true;
            }
            s = s.split(",");
            for(int j = 0; j < s.length; j++)
            {
                int k = findMode(s[j]);
                if(k < 0)
                    return false;
                mToggleModes.add(Integer.valueOf(k));
            }

            mToggle = true;
            return true;
        }

        public void click()
        {
            if(!mToggle) goto _L2; else goto _L1
_L1:
            int i = mCurToggleIndex + 1;
            mCurToggleIndex = i;
            mCurToggleIndex = i % mToggleModes.size();
            mCurModeIndex = ((Integer)mToggleModes.get(mCurToggleIndex)).intValue();
_L4:
            return;
_L2:
            if(mToggleAll)
            {
                int j = mCurModeIndex + 1;
                mCurModeIndex = j;
                mCurModeIndex = j % mModeNames.size();
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public int getModeId()
        {
            return ((Integer)mModeIds.get(mCurModeIndex)).intValue();
        }

        public String getModeName()
        {
            return (String)mModeNames.get(mCurModeIndex);
        }

        private int mCurModeIndex;
        private int mCurToggleIndex;
        private ArrayList mModeIds;
        private ArrayList mModeNames;
        private boolean mToggle;
        private boolean mToggleAll;
        private ArrayList mToggleModes;

        private ModeToggleHelper()
        {
            mModeNames = new ArrayList();
            mModeIds = new ArrayList();
            mToggleModes = new ArrayList();
        }

        ModeToggleHelper(ModeToggleHelper modetogglehelper)
        {
            this();
        }
    }

    private static class MultiCommand extends ActionCommand
    {

        protected void doPerform()
        {
            for(Iterator iterator = mCommands.iterator(); iterator.hasNext(); ((ActionCommand)iterator.next()).perform());
        }

        public void finish()
        {
            for(Iterator iterator = mCommands.iterator(); iterator.hasNext(); ((ActionCommand)iterator.next()).finish());
        }

        public void init()
        {
            for(Iterator iterator = mCommands.iterator(); iterator.hasNext(); ((ActionCommand)iterator.next()).init());
        }

        public void pause()
        {
            for(Iterator iterator = mCommands.iterator(); iterator.hasNext(); ((ActionCommand)iterator.next()).pause());
        }

        public void resume()
        {
            for(Iterator iterator = mCommands.iterator(); iterator.hasNext(); ((ActionCommand)iterator.next()).resume());
        }

        public static final String TAG_NAME = "MultiCommand";
        public static final String TAG_NAME1 = "GroupCommand";
        protected ArrayList mCommands;

        public MultiCommand(ScreenElement screenelement, Element element)
        {
            super(screenelement);
            mCommands = new ArrayList();
            Utils.traverseXmlElementChildren(element, null, screenelement. new _cls1());
        }
    }

    private static abstract class NotificationReceiver extends StatefulActionCommand
        implements NotifierManager.OnNotifyListener
    {

        protected void asyncUpdate()
        {
            ActionCommand._2D_get0().post(new Runnable() {

                public void run()
                {
                    update();
                }

                final NotificationReceiver this$1;

            
            {
                this$1 = NotificationReceiver.this;
                super();
            }
            }
);
        }

        public void finish()
        {
            mNotifierManager.releaseNotifier(mType, this);
        }

        public void init()
        {
            update();
            mNotifierManager.acquireNotifier(mType, this);
        }

        public void onNotify(Context context, Intent intent, Object obj)
        {
            asyncUpdate();
        }

        public void pause()
        {
            mNotifierManager.pause(mType, this);
        }

        public void resume()
        {
            update();
            mNotifierManager.resume(mType, this);
        }

        protected abstract void update();

        private NotifierManager mNotifierManager;
        private String mType;

        public NotificationReceiver(ScreenElement screenelement, String s, String s1)
        {
            super(screenelement, s);
            mType = s1;
            mNotifierManager = NotifierManager.getInstance(getContext());
        }
    }

    protected static class ObjVar
    {

        public Object get()
        {
            Object obj = mVars.get(mIndex);
            if(obj != null && mIndexArr != null && (obj instanceof Object[]))
            {
                try
                {
                    obj = ((Object[])obj)[(int)mIndexArr.evaluate()];
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    return null;
                }
                return obj;
            } else
            {
                return obj;
            }
        }

        private int mIndex;
        private Expression mIndexArr;
        private Variables mVars;

        public ObjVar(String s, Variables variables)
        {
            int i;
            String s3;
            mVars = variables;
            String s1 = s;
            i = s.indexOf('[');
            s3 = s1;
            if(i <= 0)
                break MISSING_BLOCK_LABEL_66;
            s3 = s1;
            String s2 = s.substring(0, i);
            s3 = s2;
            mIndexArr = Expression.build(variables, s.substring(i + 1, s.length() - 1));
            s3 = s2;
_L2:
            mIndex = variables.registerVariable(s3);
            return;
            s;
            if(true) goto _L2; else goto _L1
_L1:
        }
    }

    private static class OnOffCommandHelper
    {

        protected boolean mIsOn;
        protected boolean mIsToggle;

        public OnOffCommandHelper(String s)
        {
            if(!s.equalsIgnoreCase("toggle")) goto _L2; else goto _L1
_L1:
            mIsToggle = true;
_L4:
            return;
_L2:
            if(s.equalsIgnoreCase("on"))
                mIsOn = true;
            else
            if(s.equalsIgnoreCase("off"))
                mIsOn = false;
            if(true) goto _L4; else goto _L3
_L3:
        }
    }

    public static abstract class PropertyCommand extends ActionCommand
    {

        public static PropertyCommand create(ScreenElement screenelement, String s, String s1)
        {
            s = new Variable(s);
            if("visibility".equals(s.getPropertyName()))
                return new VisibilityProperty(screenelement, s, s1);
            if("animation".equals(s.getPropertyName()))
                return new AnimationProperty(screenelement, s, s1);
            else
                return null;
        }

        public void init()
        {
            init();
            if(mTargetObj == null)
                return;
            if(mTargetElement == null)
            {
                mTargetElement = getRoot().findElement(mTargetObj.getObjName());
                if(mTargetElement == null)
                {
                    Log.w("ActionCommand", (new StringBuilder()).append("could not find PropertyCommand target, name: ").append(mTargetObj.getObjName()).toString());
                    mTargetObj = null;
                    return;
                }
            }
        }

        public void perform()
        {
            if(mTargetElement == null)
            {
                return;
            } else
            {
                doPerform();
                return;
            }
        }

        protected ScreenElement mTargetElement;
        private Variable mTargetObj;

        protected PropertyCommand(ScreenElement screenelement, Variable variable, String s)
        {
            super(screenelement);
            mTargetObj = variable;
        }
    }

    private static class RingModeCommand extends NotificationReceiver
    {

        protected void doPerform()
        {
            if(mAudioManager == null)
            {
                return;
            } else
            {
                mToggleHelper.click();
                int i = mToggleHelper.getModeId();
                mAudioManager.setRingerMode(i);
                updateState(i);
                return;
            }
        }

        protected void update()
        {
            if(mAudioManager == null)
                mAudioManager = (AudioManager)mScreenElement.getContext().mContext.getSystemService("audio");
            if(mAudioManager == null)
            {
                return;
            } else
            {
                updateState(mAudioManager.getRingerMode());
                return;
            }
        }

        private AudioManager mAudioManager;
        private ModeToggleHelper mToggleHelper;

        public RingModeCommand(ScreenElement screenelement, String s)
        {
            super(screenelement, "ring_mode", "android.media.RINGER_MODE_CHANGED");
            mToggleHelper = new ModeToggleHelper(null);
            mToggleHelper.addMode("normal", 2);
            mToggleHelper.addMode("silent", 0);
            mToggleHelper.addMode("vibrate", 1);
            if(!mToggleHelper.build(s))
            {
                Log.e("ActionCommand", (new StringBuilder()).append("invalid ring mode command value: ").append(s).toString());
                return;
            } else
            {
                return;
            }
        }
    }

    private static class SoundCommand extends ActionCommand
    {

        private static int[] _2D_getmiui_2D_maml_2D_SoundManager$CommandSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_SoundManager$CommandSwitchesValues != null)
                return _2D_miui_2D_maml_2D_SoundManager$CommandSwitchesValues;
            int ai[] = new int[SoundManager.Command.values().length];
            try
            {
                ai[SoundManager.Command.Pause.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[SoundManager.Command.Play.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[SoundManager.Command.Resume.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[SoundManager.Command.Stop.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_SoundManager$CommandSwitchesValues = ai;
            return ai;
        }

        protected void doPerform()
        {
            _2D_getmiui_2D_maml_2D_SoundManager$CommandSwitchesValues()[mCommand.ordinal()];
            JVM INSTR tableswitch 1 4: default 40
        //                       1 106
        //                       2 41
        //                       3 106
        //                       4 106;
               goto _L1 _L2 _L3 _L2 _L2
_L1:
            return;
_L3:
            float f = 0.0F;
            if(mVolumeExp != null)
                f = (float)mVolumeExp.evaluate();
            int i = getRoot().playSound(mSound, new SoundManager.SoundOptions(mKeepCur, mLoop, f));
            if(mStreamIdVar != null)
                mStreamIdVar.set(i);
            continue; /* Loop/switch isn't completed */
_L2:
            if(mStreamIdExp != null)
            {
                int j = (int)mStreamIdExp.evaluate();
                getRoot().playSound(j, mCommand);
            }
            if(true) goto _L1; else goto _L4
_L4:
        }

        private static final int _2D_miui_2D_maml_2D_SoundManager$CommandSwitchesValues[];
        public static final String TAG_NAME = "SoundCommand";
        private SoundManager.Command mCommand;
        private boolean mKeepCur;
        private boolean mLoop;
        private String mSound;
        private Expression mStreamIdExp;
        private IndexedVariable mStreamIdVar;
        private Expression mVolumeExp;

        public SoundCommand(ScreenElement screenelement, Element element)
        {
            super(screenelement);
            mSound = element.getAttribute("sound");
            mKeepCur = Boolean.parseBoolean(element.getAttribute("keepCur"));
            mLoop = Boolean.parseBoolean(element.getAttribute("loop"));
            mCommand = SoundManager.Command.parse(element.getAttribute("command"));
            mVolumeExp = Expression.build(getVariables(), element.getAttribute("volume"));
            if(mVolumeExp == null)
                Log.e("ActionCommand", "invalid expression in SoundCommand");
            mStreamIdExp = Expression.build(getVariables(), element.getAttribute("streamId"));
            screenelement = element.getAttribute("streamIdVar");
            if(!TextUtils.isEmpty(screenelement))
                mStreamIdVar = new IndexedVariable(screenelement, getVariables(), true);
        }
    }

    public static abstract class StateTracker
    {

        public abstract int getActualState(Context context);

        public final int getTriState(Context context)
        {
            if(mInTransition)
                return 5;
            switch(getActualState(context))
            {
            default:
                return 5;

            case 0: // '\0'
                return 0;

            case 1: // '\001'
                return 1;
            }
        }

        public final boolean isTurningOn()
        {
            boolean flag;
            if(mIntendedState != null)
                flag = mIntendedState.booleanValue();
            else
                flag = false;
            return flag;
        }

        public abstract void onActualStateChange(Context context, Intent intent);

        protected abstract void requestStateChange(Context context, boolean flag);

        protected final void setCurrentState(Context context, int i)
        {
            boolean flag = mInTransition;
            i;
            JVM INSTR tableswitch 0 3: default 36
        //                       0 106
        //                       1 122
        //                       2 138
        //                       3 154;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            break; /* Loop/switch isn't completed */
_L5:
            break MISSING_BLOCK_LABEL_154;
_L6:
            if(flag && mInTransition ^ true && mDeferredStateChangeRequestNeeded)
            {
                Log.v("ActionCommand", "processing deferred state change");
                if(mActualState != null && mIntendedState != null && mIntendedState.equals(mActualState))
                    Log.v("ActionCommand", "... but intended state matches, so no changes.");
                else
                if(mIntendedState != null)
                {
                    mInTransition = true;
                    requestStateChange(context, mIntendedState.booleanValue());
                }
                mDeferredStateChangeRequestNeeded = false;
            }
            return;
_L2:
            mInTransition = false;
            mActualState = Boolean.valueOf(false);
              goto _L6
_L3:
            mInTransition = false;
            mActualState = Boolean.valueOf(true);
              goto _L6
_L4:
            mInTransition = true;
            mActualState = Boolean.valueOf(false);
              goto _L6
            mInTransition = true;
            mActualState = Boolean.valueOf(true);
              goto _L6
        }

        public final void toggleState(Context context)
        {
            int i;
            boolean flag;
            boolean flag1;
            i = getTriState(context);
            flag = false;
            flag1 = flag;
            i;
            JVM INSTR tableswitch 0 5: default 52
        //                       0 83
        //                       1 77
        //                       2 55
        //                       3 55
        //                       4 55
        //                       5 89;
               goto _L1 _L2 _L3 _L4 _L4 _L4 _L5
_L5:
            break MISSING_BLOCK_LABEL_89;
_L4:
            break; /* Loop/switch isn't completed */
_L1:
            flag1 = flag;
_L6:
            mIntendedState = Boolean.valueOf(flag1);
            if(mInTransition)
            {
                mDeferredStateChangeRequestNeeded = true;
            } else
            {
                mInTransition = true;
                requestStateChange(context, flag1);
            }
            return;
_L3:
            flag1 = false;
              goto _L6
_L2:
            flag1 = true;
              goto _L6
            flag1 = flag;
            if(mIntendedState != null)
                flag1 = mIntendedState.booleanValue() ^ true;
              goto _L6
        }

        private Boolean mActualState;
        private boolean mDeferredStateChangeRequestNeeded;
        private boolean mInTransition;
        private Boolean mIntendedState;

        public StateTracker()
        {
            mInTransition = false;
            mActualState = null;
            mIntendedState = null;
            mDeferredStateChangeRequestNeeded = false;
        }
    }

    private static abstract class StatefulActionCommand extends ActionCommand
    {

        protected final void updateState(int i)
        {
            if(mVar == null)
            {
                return;
            } else
            {
                mVar.set(i);
                getRoot().requestUpdate();
                return;
            }
        }

        private IndexedVariable mVar;

        public StatefulActionCommand(ScreenElement screenelement, String s)
        {
            super(screenelement);
            mVar = new IndexedVariable(s, getVariables(), true);
        }
    }

    private static abstract class TargetCommand extends ActionCommand
    {

        private static int[] _2D_getmiui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues;
            int ai[] = new int[TargetType.values().length];
            try
            {
                ai[TargetType.CONSTRUCTOR.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[TargetType.SCREEN_ELEMENT.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[TargetType.VARIABLE.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues = ai;
            return ai;
        }

        protected Object getTarget()
        {
            Object obj;
label0:
            {
                switch(_2D_getmiui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues()[mTargetType.ordinal()])
                {
                default:
                    return null;

                case 1: // '\001'
                    if(mTarget != null && mTargetIndex != null)
                        return ((ElementGroup)mTarget).getChild((int)mTargetIndex.evaluate());
                    else
                        return mTarget;

                case 2: // '\002'
                    break;
                }
                if(mTarget != null)
                {
                    obj = getVariables().get(((Integer)mTarget).intValue());
                    if(mTargetIndex == null)
                        break label0;
                    if(obj.getClass().isArray())
                        return Array.get(obj, (int)mTargetIndex.evaluate());
                    Log.e("ActionCommand", (new StringBuilder()).append("target with index is not an Array variable, name: ").append(mTargetName).toString());
                    mTargetIndex = null;
                }
                return null;
            }
            return obj;
        }

        public void init()
        {
            init();
            _2D_getmiui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues()[mTargetType.ordinal()];
            JVM INSTR tableswitch 1 2: default 36
        //                       1 37
        //                       2 146;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            if(mTarget == null)
            {
                ScreenElement screenelement = getRoot().findElement(mTargetName);
                mTarget = screenelement;
                if(screenelement == null)
                    Log.e("ActionCommand", (new StringBuilder()).append("could not find ScreenElement target, name: ").append(mTargetName).toString());
                else
                if(mTargetIndex != null && !ElementGroup.isArrayGroup(screenelement))
                {
                    Log.e("ActionCommand", (new StringBuilder()).append("target with index is not an ArrayGroup, name: ").append(mTargetName).toString());
                    mTargetIndex = null;
                }
            }
            continue; /* Loop/switch isn't completed */
_L3:
            if(mTargetName != null)
                mTarget = Integer.valueOf(getVariables().registerVariable(mTargetName));
            else
                Log.e("ActionCommand", "MethodCommand, type=var, empty target name");
            if(true) goto _L1; else goto _L4
_L4:
        }

        private static final int _2D_miui_2D_maml_2D_ActionCommand$TargetCommand$TargetTypeSwitchesValues[];
        protected String mLogStr;
        private Object mTarget;
        protected Expression mTargetIndex;
        protected String mTargetName;
        protected TargetType mTargetType;

        public TargetCommand(ScreenElement screenelement, Element element)
        {
            super(screenelement);
            mTargetName = element.getAttribute("target");
            if(TextUtils.isEmpty(mTargetName))
                mTargetName = null;
            mTargetIndex = Expression.build(getVariables(), element.getAttribute("targetIndex"));
            screenelement = element.getAttribute("targetType");
            mTargetType = TargetType.SCREEN_ELEMENT;
            if(!"element".equals(screenelement)) goto _L2; else goto _L1
_L1:
            mTargetType = TargetType.SCREEN_ELEMENT;
_L4:
            mLogStr = (new StringBuilder()).append("target=").append(mTargetName).append(" type=").append(mTargetType.toString()).toString();
            return;
_L2:
            if("var".equals(screenelement))
                mTargetType = TargetType.VARIABLE;
            else
            if("ctor".equals(screenelement))
                mTargetType = TargetType.CONSTRUCTOR;
            if(true) goto _L4; else goto _L3
_L3:
        }
    }

    protected static final class TargetCommand.TargetType extends Enum
    {

        public static TargetCommand.TargetType valueOf(String s)
        {
            return (TargetCommand.TargetType)Enum.valueOf(miui/maml/ActionCommand$TargetCommand$TargetType, s);
        }

        public static TargetCommand.TargetType[] values()
        {
            return $VALUES;
        }

        private static final TargetCommand.TargetType $VALUES[];
        public static final TargetCommand.TargetType CONSTRUCTOR;
        public static final TargetCommand.TargetType SCREEN_ELEMENT;
        public static final TargetCommand.TargetType VARIABLE;

        static 
        {
            SCREEN_ELEMENT = new TargetCommand.TargetType("SCREEN_ELEMENT", 0);
            VARIABLE = new TargetCommand.TargetType("VARIABLE", 1);
            CONSTRUCTOR = new TargetCommand.TargetType("CONSTRUCTOR", 2);
            $VALUES = (new TargetCommand.TargetType[] {
                SCREEN_ELEMENT, VARIABLE, CONSTRUCTOR
            });
        }

        private TargetCommand.TargetType(String s, int i)
        {
            super(s, i);
        }
    }

    private static class UsbStorageSwitchCommand extends NotificationReceiver
    {

        static StorageManager _2D_get0(UsbStorageSwitchCommand usbstorageswitchcommand)
        {
            return usbstorageswitchcommand.mStorageManager;
        }

        protected void doPerform()
        {
            if(mStorageManager == null)
                return;
            boolean flag = mStorageManager.isUsbMassStorageEnabled();
            if(mOnOffHelper.mIsToggle)
            {
                flag ^= true;
            } else
            {
                if(mOnOffHelper.mIsOn == flag)
                    return;
                flag = mOnOffHelper.mIsOn;
            }
            updateState(3);
            (flag. new Thread() {

                public void run()
                {
                    UsbStorageSwitchCommand usbstorageswitchcommand;
                    byte byte0;
                    if(_on)
                        UsbStorageSwitchCommand._2D_get0(UsbStorageSwitchCommand.this).enableUsbMassStorage();
                    else
                        UsbStorageSwitchCommand._2D_get0(UsbStorageSwitchCommand.this).disableUsbMassStorage();
                    usbstorageswitchcommand = UsbStorageSwitchCommand.this;
                    if(_on)
                        byte0 = 2;
                    else
                        byte0 = 1;
                    usbstorageswitchcommand.updateState(byte0);
                }

                final UsbStorageSwitchCommand this$1;
                final boolean val$_on;

            
            {
                this$1 = final_usbstorageswitchcommand;
                _on = Z.this;
                super();
            }
            }
).start();
        }

        public void onNotify(Context context, Intent intent, Object obj)
        {
            if(intent != null)
                mConnected = intent.getExtras().getBoolean("connected");
            super.onNotify(context, intent, obj);
        }

        protected void update()
        {
            if(mStorageManager == null)
            {
                mStorageManager = (StorageManager)getContext().getSystemService("storage");
                if(mStorageManager == null)
                {
                    Log.w("ActionCommand", "Failed to get StorageManager");
                    return;
                }
            }
            boolean flag = mStorageManager.isUsbMassStorageEnabled();
            byte byte0;
            if(mConnected)
            {
                if(flag)
                    byte0 = 2;
                else
                    byte0 = 1;
            } else
            {
                byte0 = 0;
            }
            updateState(byte0);
        }

        private boolean mConnected;
        private OnOffCommandHelper mOnOffHelper;
        private StorageManager mStorageManager;

        public UsbStorageSwitchCommand(ScreenElement screenelement, String s)
        {
            super(screenelement, "usb_mode", "android.hardware.usb.action.USB_STATE");
            mOnOffHelper = new OnOffCommandHelper(s);
        }
    }

    private static class VariableAssignmentCommand extends ActionCommand
    {

        private static int[] _2D_getmiui_2D_maml_2D_data_2D_VariableTypeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_data_2D_VariableTypeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_data_2D_VariableTypeSwitchesValues;
            int ai[] = new int[VariableType.values().length];
            try
            {
                ai[VariableType.BOOLEAN_ARR.ordinal()] = 5;
            }
            catch(NoSuchFieldError nosuchfielderror14) { }
            try
            {
                ai[VariableType.BYTE_ARR.ordinal()] = 6;
            }
            catch(NoSuchFieldError nosuchfielderror13) { }
            try
            {
                ai[VariableType.CHAR_ARR.ordinal()] = 7;
            }
            catch(NoSuchFieldError nosuchfielderror12) { }
            try
            {
                ai[VariableType.DOUBLE_ARR.ordinal()] = 8;
            }
            catch(NoSuchFieldError nosuchfielderror11) { }
            try
            {
                ai[VariableType.FLOAT_ARR.ordinal()] = 9;
            }
            catch(NoSuchFieldError nosuchfielderror10) { }
            try
            {
                ai[VariableType.INT_ARR.ordinal()] = 10;
            }
            catch(NoSuchFieldError nosuchfielderror9) { }
            try
            {
                ai[VariableType.INVALID.ordinal()] = 11;
            }
            catch(NoSuchFieldError nosuchfielderror8) { }
            try
            {
                ai[VariableType.LONG_ARR.ordinal()] = 12;
            }
            catch(NoSuchFieldError nosuchfielderror7) { }
            try
            {
                ai[VariableType.NUM.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror6) { }
            try
            {
                ai[VariableType.NUM_ARR.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror5) { }
            try
            {
                ai[VariableType.OBJ.ordinal()] = 13;
            }
            catch(NoSuchFieldError nosuchfielderror4) { }
            try
            {
                ai[VariableType.OBJ_ARR.ordinal()] = 14;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[VariableType.SHORT_ARR.ordinal()] = 15;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[VariableType.STR.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[VariableType.STR_ARR.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_data_2D_VariableTypeSwitchesValues = ai;
            return ai;
        }

        protected void doPerform()
        {
label0:
            {
label1:
                {
label2:
                    {
label3:
                        {
                            {
                                ScreenElementRoot screenelementroot = getRoot();
                                switch(_2D_getmiui_2D_maml_2D_data_2D_VariableTypeSwitchesValues()[mType.ordinal()])
                                {
                                default:
                                    Object obj = null;
                                    Object obj1;
                                    Variables variables;
                                    Object obj2;
                                    double d;
                                    int i;
                                    int j;
                                    if(mExpression != null)
                                        obj1 = mExpression.evaluateStr();
                                    else
                                        obj1 = null;
                                    variables = getVariables();
                                    obj2 = obj;
                                    if(!TextUtils.isEmpty(((CharSequence) (obj1))))
                                    {
                                        obj2 = obj;
                                        if(variables.existsObj(((String) (obj1))))
                                            obj2 = variables.get(((String) (obj1)));
                                    }
                                    if(mIndexExpression == null)
                                        mVar.set(obj2);
                                    else
                                        mVar.setArr((int)mIndexExpression.evaluate(), obj2);
                                    break;

                                case 1: // '\001'
                                    break label3;

                                case 2: // '\002'
                                    break label2;

                                case 3: // '\003'
                                    break label1;

                                case 4: // '\004'
                                    break label0;
                                }
                            }
                            if(mRequestUpdate)
                                screenelementroot.requestUpdate();
                            return;
                        }
                        if(mExpression != null)
                        {
                            d = mExpression.evaluate();
                            mVar.set(d);
                            if(mPersist && screenelementroot.getCapability(2))
                                screenelementroot.saveVar(mName, Double.valueOf(d));
                        }
                        continue; /* Loop/switch isn't completed */
                    }
                    if(mIndexExpression != null && mExpression != null)
                        mVar.setArr((int)mIndexExpression.evaluate(), mExpression.evaluate());
                    else
                    if(mArrayValues != null)
                    {
                        i = mArrayValues.length;
                        j = 0;
                        while(j < i) 
                        {
                            obj2 = mArrayValues[j];
                            obj1 = mVar;
                            if(obj2 == null)
                                d = 0.0D;
                            else
                                d = ((Expression) (obj2)).evaluate();
                            ((IndexedVariable) (obj1)).setArr(j, d);
                            j++;
                        }
                    }
                    continue; /* Loop/switch isn't completed */
                }
                obj2 = mExpression.evaluateStr();
                mVar.set(obj2);
                if(mPersist && screenelementroot.getCapability(2))
                    screenelementroot.saveVar(mName, ((String) (obj2)));
                continue; /* Loop/switch isn't completed */
            }
            if(mIndexExpression == null || mExpression == null)
                break; /* Loop/switch isn't completed */
            mVar.setArr((int)mIndexExpression.evaluate(), mExpression.evaluateStr());
            if(true) goto _L2; else goto _L1
_L2:
            break MISSING_BLOCK_LABEL_118;
_L1:
            if(mArrayValues != null)
            {
                i = mArrayValues.length;
                j = 0;
                while(j < i) 
                {
                    obj2 = mArrayValues[j];
                    obj1 = mVar;
                    if(obj2 == null)
                        obj2 = null;
                    else
                        obj2 = ((Expression) (obj2)).evaluateStr();
                    ((IndexedVariable) (obj1)).setArr(j, obj2);
                    j++;
                }
            }
            if(false)
                ;
            else
                break MISSING_BLOCK_LABEL_118;
        }

        private static final int _2D_miui_2D_maml_2D_data_2D_VariableTypeSwitchesValues[];
        public static final String TAG_NAME = "VariableCommand";
        private Expression mArrayValues[];
        private Expression mExpression;
        private Expression mIndexExpression;
        private String mName;
        private boolean mPersist;
        private boolean mRequestUpdate;
        private VariableType mType;
        private IndexedVariable mVar;

        public VariableAssignmentCommand(ScreenElement screenelement, Element element)
        {
            super(screenelement);
            mName = element.getAttribute("name");
            mPersist = Boolean.parseBoolean(element.getAttribute("persist"));
            mRequestUpdate = Boolean.parseBoolean(element.getAttribute("requestUpdate"));
            mType = VariableType.parseType(element.getAttribute("type"));
            if(!TextUtils.isEmpty(mName))
                mVar = new IndexedVariable(mName, getVariables(), mType.isNumber());
            else
                Log.e("ActionCommand", "empty name in VariableAssignmentCommand");
            screenelement = screenelement.getVariables();
            mExpression = Expression.build(screenelement, element.getAttribute("expression"));
            if(mType.isArray())
            {
                mIndexExpression = Expression.build(screenelement, element.getAttribute("index"));
                mArrayValues = Expression.buildMultiple(screenelement, element.getAttribute("values"));
            }
        }
    }

    private static class VariableBinderCommand extends ActionCommand
    {

        private static int[] _2D_getmiui_2D_maml_2D_ActionCommand$VariableBinderCommand$CommandSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_ActionCommand$VariableBinderCommand$CommandSwitchesValues != null)
                return _2D_miui_2D_maml_2D_ActionCommand$VariableBinderCommand$CommandSwitchesValues;
            int ai[] = new int[Command.values().length];
            try
            {
                ai[Command.Invalid.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[Command.Refresh.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_ActionCommand$VariableBinderCommand$CommandSwitchesValues = ai;
            return ai;
        }

        protected void doPerform()
        {
            if(mBinder == null) goto _L2; else goto _L1
_L1:
            _2D_getmiui_2D_maml_2D_ActionCommand$VariableBinderCommand$CommandSwitchesValues()[mCommand.ordinal()];
            JVM INSTR tableswitch 1 1: default 36
        //                       1 37;
               goto _L2 _L3
_L2:
            return;
_L3:
            mBinder.refresh();
            if(true) goto _L2; else goto _L4
_L4:
        }

        public void init()
        {
            mBinder = getRoot().findBinder(mName);
        }

        private static final int _2D_miui_2D_maml_2D_ActionCommand$VariableBinderCommand$CommandSwitchesValues[];
        public static final String TAG_NAME = "BinderCommand";
        private VariableBinder mBinder;
        private Command mCommand;
        private String mName;

        public VariableBinderCommand(ScreenElement screenelement, Element element)
        {
            super(screenelement);
            mCommand = Command.Invalid;
            mName = element.getAttribute("name");
            if(element.getAttribute("command").equals("refresh"))
                mCommand = Command.Refresh;
        }
    }

    private static final class VariableBinderCommand.Command extends Enum
    {

        public static VariableBinderCommand.Command valueOf(String s)
        {
            return (VariableBinderCommand.Command)Enum.valueOf(miui/maml/ActionCommand$VariableBinderCommand$Command, s);
        }

        public static VariableBinderCommand.Command[] values()
        {
            return $VALUES;
        }

        private static final VariableBinderCommand.Command $VALUES[];
        public static final VariableBinderCommand.Command Invalid;
        public static final VariableBinderCommand.Command Refresh;

        static 
        {
            Refresh = new VariableBinderCommand.Command("Refresh", 0);
            Invalid = new VariableBinderCommand.Command("Invalid", 1);
            $VALUES = (new VariableBinderCommand.Command[] {
                Refresh, Invalid
            });
        }

        private VariableBinderCommand.Command(String s, int i)
        {
            super(s, i);
        }
    }

    private static class VisibilityProperty extends PropertyCommand
    {

        public void doPerform()
        {
            if(mIsToggle)
                mTargetElement.show(mTargetElement.isVisible() ^ true);
            else
                mTargetElement.show(mIsShow);
        }

        public static final String PROPERTY_NAME = "visibility";
        private boolean mIsShow;
        private boolean mIsToggle;

        protected VisibilityProperty(ScreenElement screenelement, Variable variable, String s)
        {
            super(screenelement, variable, s);
            if(!s.equalsIgnoreCase("toggle")) goto _L2; else goto _L1
_L1:
            mIsToggle = true;
_L4:
            return;
_L2:
            if(s.equalsIgnoreCase("true"))
                mIsShow = true;
            else
            if(s.equalsIgnoreCase("false"))
                mIsShow = false;
            if(true) goto _L4; else goto _L3
_L3:
        }
    }

    private static final class WifiStateTracker extends StateTracker
    {

        private static int wifiStateToFiveState(int i)
        {
            switch(i)
            {
            default:
                return 4;

            case 1: // '\001'
                return 0;

            case 3: // '\003'
                return 1;

            case 0: // '\0'
                return 3;

            case 2: // '\002'
                return 2;
            }
        }

        public int getActualState(Context context)
        {
            context = (WifiManager)context.getSystemService("wifi");
            if(context != null)
                return wifiStateToFiveState(context.getWifiState());
            else
                return 4;
        }

        public void onActualStateChange(Context context, Intent intent)
        {
            boolean flag = true;
            if(!"android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction())) goto _L2; else goto _L1
_L1:
            int i = intent.getIntExtra("wifi_state", -1);
            setCurrentState(context, wifiStateToFiveState(i));
            if(3 == i)
            {
                zConnected = true;
                zScanAttempt = 0;
            }
_L4:
            return;
_L2:
            boolean flag1;
            if("android.net.wifi.SCAN_RESULTS".equals(intent.getAction()))
            {
                if(zScanAttempt < 3)
                {
                    int j = zScanAttempt + 1;
                    zScanAttempt = j;
                    if(j == 3)
                        zConnected = false;
                }
                continue; /* Loop/switch isn't completed */
            }
            if(!"android.net.wifi.STATE_CHANGE".equals(intent.getAction()))
                continue; /* Loop/switch isn't completed */
            zScanAttempt = 3;
            context = ((NetworkInfo)intent.getParcelableExtra("networkInfo")).getDetailedState();
            flag1 = flag;
            if(android.net.NetworkInfo.DetailedState.SCANNING != context)
            {
                if(android.net.NetworkInfo.DetailedState.CONNECTING != context)
                    break; /* Loop/switch isn't completed */
                flag1 = flag;
            }
_L5:
            zConnected = flag1;
            if(true) goto _L4; else goto _L3
_L3:
            flag1 = flag;
            if(android.net.NetworkInfo.DetailedState.AUTHENTICATING != context)
            {
                flag1 = flag;
                if(android.net.NetworkInfo.DetailedState.OBTAINING_IPADDR != context)
                {
                    flag1 = flag;
                    if(android.net.NetworkInfo.DetailedState.CONNECTED != context)
                        flag1 = false;
                }
            }
              goto _L5
            if(true) goto _L4; else goto _L6
_L6:
        }

        protected void requestStateChange(Context context, final boolean desiredState)
        {
            final WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
            context = new WifiApHelper(context);
            if(wifiManager == null)
            {
                Log.d("ActionCommand", "No wifiManager.");
                return;
            } else
            {
                (context. new AsyncTask() {

                    protected volatile Object doInBackground(Object aobj[])
                    {
                        return doInBackground((Void[])aobj);
                    }

                    protected transient Void doInBackground(Void avoid[])
                    {
                        int i = wifiManager.getWifiApState();
                        if(desiredState && (i == 12 || i == 13))
                            wifiApHelper.setWifiApEnabled(false);
                        wifiManager.setWifiEnabled(desiredState);
                        return null;
                    }

                    final WifiStateTracker this$1;
                    final boolean val$desiredState;
                    final WifiApHelper val$wifiApHelper;
                    final WifiManager val$wifiManager;

            
            {
                this$1 = final_wifistatetracker;
                wifiManager = wifimanager;
                desiredState = flag;
                wifiApHelper = WifiApHelper.this;
                super();
            }
                }
).execute(new Void[0]);
                return;
            }
        }

        private static final int MAX_SCAN_ATTEMPT = 3;
        public boolean zConnected;
        private int zScanAttempt;

        private WifiStateTracker()
        {
            zConnected = false;
            zScanAttempt = 0;
        }

        WifiStateTracker(WifiStateTracker wifistatetracker)
        {
            this();
        }
    }

    private static class WifiSwitchCommand extends NotificationReceiver
    {

        protected void doPerform()
        {
            Context context = getContext();
            if(!mOnOffHelper.mIsToggle) goto _L2; else goto _L1
_L1:
            mWifiState.toggleState(context);
_L8:
            update();
            return;
_L2:
            boolean flag;
            boolean flag1;
            flag = false;
            flag1 = flag;
            mWifiState.getTriState(context);
            JVM INSTR tableswitch 0 5: default 80
        //                       0 104
        //                       1 121
        //                       2 82
        //                       3 82
        //                       4 82
        //                       5 82;
               goto _L3 _L4 _L5 _L6 _L6 _L6 _L6
_L3:
            flag1 = flag;
_L6:
            if(!flag1) goto _L8; else goto _L7
_L7:
            mWifiState.requestStateChange(context, mOnOffHelper.mIsOn);
              goto _L8
_L4:
            flag1 = flag;
            if(mOnOffHelper.mIsOn)
                flag1 = true;
              goto _L6
_L5:
            flag1 = flag;
            if(!mOnOffHelper.mIsOn)
                flag1 = true;
              goto _L6
        }

        public void onNotify(Context context, Intent intent, Object obj)
        {
            mWifiState.onActualStateChange(context, intent);
            super.onNotify(context, intent, obj);
        }

        protected void update()
        {
            int i = 0;
            mWifiState.getTriState(getContext());
            JVM INSTR tableswitch 0 5: default 52
        //                       0 53
        //                       1 61
        //                       2 52
        //                       3 52
        //                       4 52
        //                       5 89;
               goto _L1 _L2 _L3 _L1 _L1 _L1 _L4
_L1:
            return;
_L2:
            updateState(0);
            continue; /* Loop/switch isn't completed */
_L3:
            if(((WifiStateTracker)mWifiState).zConnected)
                i = 1;
            else
                i = 2;
            updateState(i);
            continue; /* Loop/switch isn't completed */
_L4:
            if(mWifiState.isTurningOn())
                i = 3;
            updateState(i);
            if(true) goto _L1; else goto _L5
_L5:
        }

        private OnOffCommandHelper mOnOffHelper;
        private final StateTracker mWifiState = new WifiStateTracker(null);

        public WifiSwitchCommand(ScreenElement screenelement, String s)
        {
            super(screenelement, "wifi_state", NotifierManager.TYPE_WIFI_STATE);
            update();
            mOnOffHelper = new OnOffCommandHelper(s);
        }
    }


    static Handler _2D_get0()
    {
        return mHandler;
    }

    public ActionCommand(ScreenElement screenelement)
    {
        mScreenElement = screenelement;
    }

    protected static ActionCommand create(ScreenElement screenelement, String s, String s1)
    {
        if(TextUtils.isEmpty(s) || TextUtils.isEmpty(s1))
            return null;
        Variable variable = new Variable(s);
        if(variable.getObjName() != null)
            return PropertyCommand.create(screenelement, s, s1);
        s = variable.getPropertyName();
        if("RingMode".equals(s))
            return new RingModeCommand(screenelement, s1);
        if("Wifi".equals(s))
            return new WifiSwitchCommand(screenelement, s1);
        if("Data".equals(s))
            return new DataSwitchCommand(screenelement, s1);
        if("Bluetooth".equals(s))
            return new BluetoothSwitchCommand(screenelement, s1);
        if("UsbStorage".equals(s))
            return new UsbStorageSwitchCommand(screenelement, s1);
        else
            return null;
    }

    public static ActionCommand create(Element element, ScreenElement screenelement)
    {
        if(element == null)
            return null;
        Expression expression = Expression.build(screenelement.getVariables(), element.getAttribute("condition"));
        Expression expression1 = Expression.build(screenelement.getVariables(), element.getAttribute("delayCondition"));
        long l = Utils.getAttrAsLong(element, "delay", 0L);
        String s = element.getNodeName();
        if(s.equals("Command"))
            element = create(screenelement, element.getAttribute("target"), element.getAttribute("value"));
        else
        if(s.equals("VariableCommand"))
            element = new VariableAssignmentCommand(screenelement, element);
        else
        if(s.equals("BinderCommand"))
            element = new VariableBinderCommand(screenelement, element);
        else
        if(s.equals("IntentCommand"))
            element = new IntentCommand(screenelement, element);
        else
        if(s.equals("SoundCommand"))
            element = new SoundCommand(screenelement, element);
        else
        if(s.equals("ExternCommand"))
            element = new ExternCommand(screenelement, element);
        else
        if(s.equals("VibrateCommand"))
            element = new VibrateCommand(screenelement, element);
        else
        if(s.equals("MethodCommand"))
            element = new MethodCommand(screenelement, element);
        else
        if(s.equals("FieldCommand"))
            element = new FieldCommand(screenelement, element);
        else
        if(s.equals("MultiCommand") || s.equals("GroupCommand"))
            element = new MultiCommand(screenelement, element);
        else
        if(s.equals("LoopCommand"))
            element = new LoopCommand(screenelement, element);
        else
        if(s.equals("AnimationCommand"))
            element = new AnimationCommand(screenelement, element);
        else
        if(s.equals("ActionCommand"))
        {
            element = new ActionPerformCommand(screenelement, element);
        } else
        {
            ObjectFactory.ActionCommandFactory actioncommandfactory = (ObjectFactory.ActionCommandFactory)screenelement.getContext().getObjectFactory("ActionCommand");
            if(actioncommandfactory != null)
                element = actioncommandfactory.create(screenelement, element);
            else
                element = null;
        }
        if(element == null)
            return null;
        screenelement = element;
        if(expression1 != null)
            screenelement = new ConditionCommand(element, expression1);
        element = screenelement;
        if(l > 0L)
            element = new DelayCommand(screenelement, l);
        if(expression != null)
            element = new ConditionCommand(element, expression);
        return element;
    }

    protected abstract void doPerform();

    public void finish()
    {
    }

    protected final Context getContext()
    {
        return getScreenContext().mContext;
    }

    protected ScreenElementRoot getRoot()
    {
        return mScreenElement.getRoot();
    }

    protected final ScreenContext getScreenContext()
    {
        return mScreenElement.getContext();
    }

    protected ScreenElement getScreenElement()
    {
        return mScreenElement;
    }

    protected final Variables getVariables()
    {
        return mScreenElement.getVariables();
    }

    public void init()
    {
    }

    public void pause()
    {
    }

    public void perform()
    {
        doPerform();
    }

    public void resume()
    {
    }

    private static final String COMMAND_BLUETOOTH = "Bluetooth";
    private static final String COMMAND_DATA = "Data";
    private static final String COMMAND_RING_MODE = "RingMode";
    private static final String COMMAND_USB_STORAGE = "UsbStorage";
    private static final String COMMAND_WIFI = "Wifi";
    private static final String LOG_TAG = "ActionCommand";
    private static final int STATE_DISABLED = 0;
    private static final int STATE_ENABLED = 1;
    private static final int STATE_INTERMEDIATE = 5;
    private static final int STATE_TURNING_OFF = 3;
    private static final int STATE_TURNING_ON = 2;
    private static final int STATE_UNKNOWN = 4;
    public static final String TAG_NAME = "Command";
    private static final Handler mHandler = new Handler(Looper.getMainLooper());
    protected ScreenElement mScreenElement;


    // Unreferenced inner class miui/maml/ActionCommand$DelayCommand$1

/* anonymous class */
    class DelayCommand._cls1
        implements Runnable
    {

        public void run()
        {
            DelayCommand._2D_get0(DelayCommand.this).perform();
        }

        final DelayCommand this$1;

            
            {
                this$1 = DelayCommand.this;
                super();
            }
    }


    // Unreferenced inner class miui/maml/ActionCommand$MultiCommand$1

/* anonymous class */
    class MultiCommand._cls1
        implements miui.maml.util.Utils.XmlTraverseListener
    {

        public void onChild(Element element)
        {
            element = ActionCommand.create(element, screenElement);
            if(element != null)
                mCommands.add(element);
        }

        final MultiCommand this$1;
        final ScreenElement val$screenElement;

            
            {
                this$1 = final_multicommand;
                screenElement = ScreenElement.this;
                super();
            }
    }

}
