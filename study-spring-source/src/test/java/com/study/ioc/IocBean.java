package com.study.ioc;

import java.io.Serializable;

/**
 * 作用描述：编程式使用IOC容器
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年08月25日
 */
public class IocBean implements Serializable {
    private static final long serialVersionUID = -8946203475718965150L;

    private String className;
    private String modifier;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return "className=" + className + ", modifier=" + modifier;
    }
}
