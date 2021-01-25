/*
 * Copyright 2002-2014 the original author or authors.
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

package org.springframework.context;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.lang.Nullable;

/**
 * ApplicationContext在BeanFactory的基础上添加了附加功能，这些功能为ApplicationContext提供了以下BeanFactory不具备的新特性：
 * 1、支持不同的信息源。ApplicationContext继承自MessageSource接口，这些信息源的扩展功能可以支持国际化的实现，为开发多语言版本的应用提供服务。
 * 2、访问资源。体现在对ResourceLoader和Resource的支持上，这样就可以从不同地方得到Bean定义资源。这在接口关系上看不出来，
 * 一般来说，具体ApplicationContext都是继承了DefaultResourceLoader的子类，因为DefaultResourceLoader是AbstractApplicationContext的基类。
 * 3、支持应用事件。继承了ApplicationEventPublisher接口，这样在上下文中引入了事件机制，这些事件和Bean生命周期的结合为Bean的管理提供了便利。
 * 4、在ApplicationContext中提供的附加服务。这些服务使得基本IOC容器的功能更丰富。因为具备了这些丰富的附加功能，使得ApplicationContext与简单的BeanFactory相比，
 * 对它的使用是一种面向框架的风格，所以在开发工作中建议使用ApplicationContext作为IOC容器的基本形式。
 * <p>
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running,
 * but may be reloaded if the implementation supports this.
 * <p>
 * An ApplicationContext provides:
 * 1、用于访问应用程序组件的Bean工厂方法。
 * Bean factory methods for accessing application components.
 * Inherited from {@link org.springframework.beans.factory.ListableBeanFactory}.
 * <p>
 * 2、以通用方式加载文件资源的能力。
 * The ability to load file resources in a generic fashion.
 * Inherited from the {@link org.springframework.core.io.ResourceLoader} interface.
 * <p>
 * 3、将事件发布给注册的侦听器的能力。
 * The ability to publish events to registered listeners.
 * Inherited from the {@link ApplicationEventPublisher} interface.
 * <p>
 * 4、解决消息的能力，支持国际化。
 * The ability to resolve messages, supporting internationalization.
 * Inherited from the {@link MessageSource} interface.
 * <p>
 * Inheritance from a parent context. Definitions in a descendant context
 * will always take priority. This means, for example, that a single parent
 * context can be used by an entire web application, while each servlet has
 * its own child context that is independent of that of any other servlet.
 * <p>
 * In addition to standard {@link org.springframework.beans.factory.BeanFactory}
 * lifecycle capabilities, ApplicationContext implementations detect and invoke
 * {@link ApplicationContextAware} beans as well as {@link ResourceLoaderAware},
 * {@link ApplicationEventPublisherAware} and {@link MessageSourceAware} beans.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see ConfigurableApplicationContext
 * @see org.springframework.beans.factory.BeanFactory
 * @see org.springframework.core.io.ResourceLoader
 */
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory,
        MessageSource, ApplicationEventPublisher, ResourcePatternResolver {
    /**
     * Return the unique id of this application context.
     *
     * @return the unique id of the context, or {@code null} if none
     */
    @Nullable
    String getId();

    /**
     * Return a name for the deployed application that this context belongs to.
     *
     * @return a name for the deployed application, or the empty String by default
     */
    String getApplicationName();

    /**
     * Return a friendly name for this context.
     *
     * @return a display name for this context (never {@code null})
     */
    String getDisplayName();

    /**
     * Return the timestamp when this context was first loaded.
     *
     * @return the timestamp (ms) when this context was first loaded
     */
    long getStartupDate();

    /**
     * Return the parent context, or {@code null} if there is no parent
     * and this is the root of the context hierarchy.
     *
     * @return the parent context, or {@code null} if there is no parent
     */
    @Nullable
    ApplicationContext getParent();

    /**
     * Expose AutowireCapableBeanFactory functionality for this context.
     * <p>
     * This is not typically used by application code, except for the purpose of
     * initializing bean instances that live outside of the application context,
     * applying the Spring bean lifecycle (fully or partly) to them.
     * <p>
     * Alternatively, the internal BeanFactory exposed by the
     * {@link ConfigurableApplicationContext} interface offers access to the
     * {@link AutowireCapableBeanFactory} interface too. The present method mainly
     * serves as a convenient, specific facility on the ApplicationContext interface.
     * <p>
     * <b>NOTE: As of 4.2, this method will consistently throw IllegalStateException
     * after the application context has been closed.</b> In current Spring Framework
     * versions, only refreshable application contexts behave that way; as of 4.2,
     * all application context implementations will be required to comply.
     *
     * @return the AutowireCapableBeanFactory for this context
     * @throws IllegalStateException if the context does not support the {@link AutowireCapableBeanFactory} interface, or does not hold an
     *                               autowire-capable bean factory yet (e.g. if {@code refresh()} has
     *                               never been called), or if the context has been closed already
     * @see ConfigurableApplicationContext#refresh()
     * @see ConfigurableApplicationContext#getBeanFactory()
     */
    AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException;
}
