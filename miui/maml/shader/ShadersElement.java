// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.shader;

import android.graphics.Shader;
import miui.maml.ScreenElementRoot;
import org.w3c.dom.*;

// Referenced classes of package miui.maml.shader:
//            LinearGradientElement, RadialGradientElement, SweepGradientElement, BitmapShaderElement, 
//            ShaderElement

public final class ShadersElement
{

    public ShadersElement(Element element, ScreenElementRoot screenelementroot)
    {
        loadShaderElements(element, screenelementroot);
    }

    private void loadShaderElements(Element element, ScreenElementRoot screenelementroot)
    {
        int i;
        element = element.getChildNodes();
        i = 0;
_L5:
        if(i >= element.getLength()) goto _L2; else goto _L1
_L1:
        Object obj = element.item(i);
        if(((Node) (obj)).getNodeType() == 1) goto _L4; else goto _L3
_L3:
        i++;
          goto _L5
_L4:
        Element element1 = (Element)obj;
        obj = element1.getTagName();
        if(((String) (obj)).equalsIgnoreCase("LinearGradient"))
            mShaderElement = new LinearGradientElement(element1, screenelementroot);
        else
        if(((String) (obj)).equalsIgnoreCase("RadialGradient"))
            mShaderElement = new RadialGradientElement(element1, screenelementroot);
        else
        if(((String) (obj)).equalsIgnoreCase("SweepGradient"))
            mShaderElement = new SweepGradientElement(element1, screenelementroot);
        else
        if(((String) (obj)).equalsIgnoreCase("BitmapShader"))
            mShaderElement = new BitmapShaderElement(element1, screenelementroot);
        if(mShaderElement == null) goto _L3; else goto _L2
_L2:
    }

    public Shader getShader()
    {
        Shader shader = null;
        if(mShaderElement != null)
            shader = mShaderElement.getShader();
        return shader;
    }

    public void updateShader()
    {
        if(mShaderElement != null)
            mShaderElement.updateShader();
    }

    public static final String FILL_TAG_NAME = "FillShaders";
    public static final String STROKE_TAG_NAME = "StrokeShaders";
    private ShaderElement mShaderElement;
}
