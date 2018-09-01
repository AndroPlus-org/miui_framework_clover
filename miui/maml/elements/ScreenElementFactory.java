// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.util.Log;
import miui.maml.ScreenElementRoot;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ImageScreenElement, TimepanelScreenElement, ImageNumberScreenElement, TextScreenElement, 
//            DateTimeScreenElement, ButtonScreenElement, MusicControlScreenElement, ElementGroup, 
//            VariableElement, VariableArrayElement, SpectrumVisualizerScreenElement, AdvancedSlider, 
//            FramerateController, VirtualScreen, LineScreenElement, RectangleScreenElement, 
//            EllipseScreenElement, CircleScreenElement, ArcScreenElement, CurveScreenElement, 
//            ListScreenElement, PaintScreenElement, MirrorScreenElement, WindowScreenElement, 
//            ScreenElementArray, WebViewScreenElement, LayerScreenElement, GLLayerScreenElement, 
//            CanvasDrawerElement, ScreenElement

public class ScreenElementFactory
{
    public static interface FactoryCallback
    {

        public abstract ScreenElement onCreateInstance(Element element, ScreenElementRoot screenelementroot);
    }


    public ScreenElementFactory()
    {
    }

    public ScreenElement createInstance(Element element, ScreenElementRoot screenelementroot)
    {
        String s = element.getTagName();
        if(s.equalsIgnoreCase("Image"))
            return new ImageScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Time"))
            return new TimepanelScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("ImageNumber") || s.equalsIgnoreCase("ImageChars"))
            return new ImageNumberScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Text"))
            return new TextScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("DateTime"))
            return new DateTimeScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Button"))
            return new ButtonScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("MusicControl"))
            return new MusicControlScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("ElementGroup") || s.equalsIgnoreCase("Group"))
            return new ElementGroup(element, screenelementroot);
        if(s.equalsIgnoreCase("Var"))
            return new VariableElement(element, screenelementroot);
        if(s.equalsIgnoreCase("VarArray"))
            return new VariableArrayElement(element, screenelementroot);
        if(s.equalsIgnoreCase("SpectrumVisualizer"))
            return new SpectrumVisualizerScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Slider"))
            return new AdvancedSlider(element, screenelementroot);
        if(s.equalsIgnoreCase("FramerateController"))
            return new FramerateController(element, screenelementroot);
        if(s.equalsIgnoreCase("VirtualScreen"))
            return new VirtualScreen(element, screenelementroot);
        if(s.equalsIgnoreCase("Line"))
            return new LineScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Rectangle"))
            return new RectangleScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Ellipse"))
            return new EllipseScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Circle"))
            return new CircleScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Arc"))
            return new ArcScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Curve"))
            return new CurveScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("List"))
            return new ListScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Paint"))
            return new PaintScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Mirror"))
            return new MirrorScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Window"))
            return new WindowScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Array"))
            return new ScreenElementArray(element, screenelementroot);
        if(s.equalsIgnoreCase("WebView"))
            return new WebViewScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("Layer"))
            return new LayerScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("GLLayer"))
            return new GLLayerScreenElement(element, screenelementroot);
        if(s.equalsIgnoreCase("CanvasDrawer"))
            return new CanvasDrawerElement(element, screenelementroot);
        if(mFactoryCallback == null)
            break MISSING_BLOCK_LABEL_627;
        element = mFactoryCallback.onCreateInstance(element, screenelementroot);
        return element;
        element;
        element.printStackTrace();
        Log.w("ScreenElementFactory", (new StringBuilder()).append("fail to create element.").append(element).toString());
        return null;
    }

    public FactoryCallback getCallback()
    {
        return mFactoryCallback;
    }

    public void setCallback(FactoryCallback factorycallback)
    {
        mFactoryCallback = factorycallback;
    }

    private FactoryCallback mFactoryCallback;
}
