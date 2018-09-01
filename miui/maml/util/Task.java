// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import org.w3c.dom.Element;

public class Task
{

    public Task()
    {
    }

    public static Task load(Element element)
    {
        if(element == null)
        {
            return null;
        } else
        {
            Task task = new Task();
            task.id = element.getAttribute("id");
            task.action = element.getAttribute("action");
            task.type = element.getAttribute("type");
            task.category = element.getAttribute("category");
            task.packageName = element.getAttribute("package");
            task.className = element.getAttribute("class");
            task.name = element.getAttribute("name");
            return task;
        }
    }

    public static String TAG_ACTION = "action";
    public static String TAG_CATEGORY = "category";
    public static String TAG_CLASS = "class";
    public static String TAG_ID = "id";
    public static String TAG_NAME = "name";
    public static String TAG_PACKAGE = "package";
    public static String TAG_TYPE = "type";
    public String action;
    public String category;
    public String className;
    public String id;
    public String name;
    public String packageName;
    public String type;

}
