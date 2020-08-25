/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.xml;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.Resource;

/**
 * Spring为我们提供了许多易用的BeanFactory实现，XmlBeanFactory就是常用的一个，该实现以XML方式描述组成应用的对象及对象间的依赖关系。
 * XmlBeanFactory类将持有此XML配置元数据，并用它来构建一个完全可配置的系统或应用。该类与 {@link XmlBeanDefinitionReader}
 * 的不同点在于该类使用了自定义的XML读取器XmlBeanDefinitionReader，实现了自己的BeanDefinitionReader读取。
 * <p>
 * XmlBeanFactory的功能是建立在DefaultListableBeanFactory这个基本容器的基础上的，只是在这个基本容器的基础上实现了其他诸如XML读取的附加功能。
 * <p>
 * Convenience extension of {@link DefaultListableBeanFactory} that reads bean definitions
 * from an XML document. Delegates to {@link XmlBeanDefinitionReader} underneath; effectively
 * equivalent to using an XmlBeanDefinitionReader with a DefaultListableBeanFactory.
 *
 * <p>The structure, element and attribute names of the required XML document
 * are hard-coded in this class. (Of course a transform could be run if necessary
 * to produce this format). "beans" doesn't need to be the root element of the XML
 * document: This class will parse all bean definition elements in the XML file.
 *
 * <p>This class registers each bean definition with the {@link DefaultListableBeanFactory}
 * superclass, and relies on the latter's implementation of the {@link BeanFactory} interface.
 * It supports singletons, prototypes, and references to either of these kinds of bean.
 * See {@code "spring-beans-3.x.xsd"} (or historically, {@code "spring-beans-2.0.dtd"}) for
 * details on options and configuration style.
 *
 * <p><b>For advanced needs, consider using a {@link DefaultListableBeanFactory} with
 * an {@link XmlBeanDefinitionReader}.</b> The latter allows for reading from multiple XML
 * resources and is highly configurable in its actual XML parsing behavior.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Chris Beams
 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory
 * @see XmlBeanDefinitionReader
 * @since 15 April 2001
 * @deprecated as of Spring 3.1 in favor of {@link DefaultListableBeanFactory} and
 * {@link XmlBeanDefinitionReader}
 */
@Deprecated
@SuppressWarnings({"serial", "all"})
public class XmlBeanFactory extends DefaultListableBeanFactory {
    /**
     * XmlBeanFactory加载bean的关键就在于XmlBeanDefinitionReader
     */
    private final XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(this);

    /**
     * Create a new XmlBeanFactory with the given resource,
     * which must be parsable using DOM.
     *
     * @param resource the XML resource to load bean definitions from
     * @throws BeansException in case of loading or parsing errors
     */
    public XmlBeanFactory(Resource resource) throws BeansException {
        this(resource, null);
    }

    /**
     * 使用给定的输入流创建一个新的XmlBeanFactory，必须使用DOM对其进行解析。
     * Create a new XmlBeanFactory with the given input stream, which must be parsable using DOM.
     *
     * @param resource          the XML resource to load bean definitions from
     * @param parentBeanFactory parent bean factory
     * @throws BeansException in case of loading or parsing errors
     */
    public XmlBeanFactory(Resource resource, BeanFactory parentBeanFactory) throws BeansException {
        super(parentBeanFactory);
        // 对XmlBeanDefinitionReader对象的初始化，以及使用这个对象来完成loadBeanDefinitions()的调用，就是这个
        // 调用启动了从Resource中载入BeanDefinitions的过程，loadBeanDefinitions()是IOC容器初始化的重要组成部分。
        this.reader.loadBeanDefinitions(resource);
    }
}
