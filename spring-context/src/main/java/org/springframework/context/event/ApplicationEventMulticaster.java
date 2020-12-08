/*
 * Copyright 2002-2017 the original author or authors.
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

package org.springframework.context.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * Interface to be implemented by objects that can manage a number of
 * {@link ApplicationListener} objects, and publish events to them.
 *
 * <p>An {@link org.springframework.context.ApplicationEventPublisher}, typically
 * a Spring {@link org.springframework.context.ApplicationContext}, can use an
 * ApplicationEventMulticaster as a delegate for actually publishing events.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Stephane Nicoll
 */
public interface ApplicationEventMulticaster {
    /**
     * 添加一个监听器以通知所有事件。
     * Add a listener to be notified of all events.
     *
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 添加一个监听器 bean，以通知所有事件。
     * Add a listener bean to be notified of all events.
     *
     * @param listenerBeanName the name of the listener bean to add
     */
    void addApplicationListenerBean(String listenerBeanName);

    /**
     * 从通知列表中删除一个监听器。
     * Remove a listener from the notification list.
     *
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 从通知列表中删除一个监听器 bean。
     * Remove a listener bean from the notification list.
     *
     * @param listenerBeanName the name of the listener bean to add
     */
    void removeApplicationListenerBean(String listenerBeanName);

    /**
     * 删除在此多播器上注册的所有监听器。删除后，多播程序将不会对事件通知执行任何操作，直到注册新的监听器为止。
     * Remove all listeners registered with this multicaster. After a remove call, the multicaster
     * will perform no action on event notification until new listeners are being registered.
     */
    void removeAllListeners();

    /**
     * 将给定的应用程序事件多播到适当的监听器。如果可能的话，请考虑使用 {@link #multicastEvent(ApplicationEvent, ResolvableType)}，因为它为基于泛型的事件提供了更好的支持。
     * Multicast the given application event to appropriate listeners.
     * Consider using {@link #multicastEvent(ApplicationEvent, ResolvableType)}
     * if possible as it provides a better support for generics-based events.
     *
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);

    /**
     * 将给定的应用程序事件多播到适当的监听器。如果 eventType 为 null，则会基于 event 实例构建默认类型。
     * Multicast the given application event to appropriate listeners. If the {@code eventType}
     * is {@code null}, a default type is built based on the {@code event} instance.
     *
     * @param event     the event to multicast
     * @param eventType the type of event (can be null)
     * @since 4.2
     */
    void multicastEvent(ApplicationEvent event, @Nullable ResolvableType eventType);
}
