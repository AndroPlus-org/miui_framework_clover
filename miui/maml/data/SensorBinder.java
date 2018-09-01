// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.content.Context;
import android.hardware.*;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.data:
//            VariableBinder, Expression, Variables

public class SensorBinder extends VariableBinder
{
    private static class Variable extends VariableBinder.Variable
    {

        public int mIndex;

        public Variable(Element element, Variables variables)
        {
            super(element, variables);
            mIndex = Utils.getAttrAsInt(element, "index", 0);
        }
    }


    public SensorBinder(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mType = element.getAttribute("type");
        mRate = Utils.getAttrAsInt(element, "rate", 3);
        mEnableExp = Expression.build(getVariables(), element.getAttribute("enable"));
        if(mSensorManager == null)
            mSensorManager = (SensorManager)getContext().mContext.getSystemService("sensor");
        mSensor = mSensorManager.getDefaultSensor(getSensorType(mType));
        if(mSensor == null)
        {
            Log.e("SensorBinder", (new StringBuilder()).append("Fail to get sensor! TYPE: ").append(mType).toString());
            return;
        } else
        {
            mSensorEventListener = new SensorEventListener() {

                public void onAccuracyChanged(Sensor sensor, int i)
                {
                }

                public void onSensorChanged(SensorEvent sensorevent)
                {
                    int i = sensorevent.values.length;
                    Iterator iterator = mVariables.iterator();
                    do
                    {
                        if(!iterator.hasNext())
                            break;
                        Variable variable = (Variable)(VariableBinder.Variable)iterator.next();
                        if(variable.mIndex >= 0 && variable.mIndex < i)
                            variable.set(sensorevent.values[variable.mIndex]);
                    } while(true);
                    onUpdateComplete();
                }

                final SensorBinder this$0;

            
            {
                this$0 = SensorBinder.this;
                super();
            }
            }
;
            loadVariables(element);
            return;
        }
    }

    private int getSensorType(String s)
    {
        Integer integer = (Integer)SENSOR_TYPES.get(s);
        if(integer != null)
            return integer.intValue();
        int i;
        try
        {
            i = Integer.parseInt(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return 0;
        }
        return i;
    }

    private void registerListener()
    {
        while(mRegistered || mSensor == null || mEnable ^ true) 
            return;
        mSensorManager.registerListener(mSensorEventListener, mSensor, mRate);
        mRegistered = true;
    }

    private void unregisterListener()
    {
        if(!mRegistered || mSensor == null)
        {
            return;
        } else
        {
            mSensorManager.unregisterListener(mSensorEventListener, mSensor);
            mRegistered = false;
            return;
        }
    }

    public void finish()
    {
        unregisterListener();
        super.finish();
    }

    public void init()
    {
        boolean flag = true;
        super.init();
        boolean flag1 = flag;
        if(mEnableExp != null)
            if(mEnableExp.evaluate() > 0.0D)
                flag1 = flag;
            else
                flag1 = false;
        mEnable = flag1;
        registerListener();
    }

    protected Variable onLoadVariable(Element element)
    {
        return new Variable(element, getContext().mVariables);
    }

    protected volatile VariableBinder.Variable onLoadVariable(Element element)
    {
        return onLoadVariable(element);
    }

    public void pause()
    {
        super.pause();
        unregisterListener();
    }

    public void resume()
    {
        super.resume();
        registerListener();
    }

    private static final String LOG_TAG = "SensorBinder";
    private static final HashMap SENSOR_TYPES;
    public static final String TAG_NAME = "SensorBinder";
    private static SensorManager mSensorManager;
    private boolean mEnable;
    private Expression mEnableExp;
    private int mRate;
    private boolean mRegistered;
    private Sensor mSensor;
    private SensorEventListener mSensorEventListener;
    private String mType;

    static 
    {
        SENSOR_TYPES = new HashMap();
        SENSOR_TYPES.put("orientation", Integer.valueOf(3));
        SENSOR_TYPES.put("gravity", Integer.valueOf(9));
        SENSOR_TYPES.put("accelerometer", Integer.valueOf(1));
        SENSOR_TYPES.put("linear_acceleration", Integer.valueOf(10));
        SENSOR_TYPES.put("pressure", Integer.valueOf(6));
        SENSOR_TYPES.put("proximity", Integer.valueOf(8));
        SENSOR_TYPES.put("light", Integer.valueOf(5));
        SENSOR_TYPES.put("gyroscope", Integer.valueOf(4));
    }
}
