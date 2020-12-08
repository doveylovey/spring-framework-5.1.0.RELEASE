Spring 事件监听机制
=================

案例：https://www.jianshu.com/p/79dd91b348c7

在服务器端，事件监听机制更多的用于异步通知、监控和异常处理。Java 提供了实现事件监听机制的两个基础类：
- 自定义事件类型扩展自 java.util.EventObject
- 事件的监听器扩展自 java.util.EventListener。

Spring 的 ApplicationContext 容器内部中的所有事件类型均继承自 org.springframework.context.ApplicationEvent，容器中的所有监听器都实现 org.springframework.context.ApplicationListener 接口，并且以 Bean 的形式注册在容器中。一旦在容器内发布 ApplicationEvent 及其子类型的事件，注册到容器的 ApplicationListener 就会对这些事件进行处理。
- ApplicationEvent 继承自 EventObject，Spring 提供了一些默认的实现，如：ContextClosedEvent 表示容器在即将关闭时发布的事件类型，ContextRefreshedEvent 表示容器在初始化或刷新时发布的事件类型。
- 容器内部使用 ApplicationListener 作为事件监听器接口定义，它继承自 EventListener。ApplicationContext 容器在启动时，会自动识别并加载 EventListener 类型的 Bean，一旦容器内有事件发布，将通知这些注册到容器的 EventListener。
- ApplicationContext 接口继承了 ApplicationEventPublisher 接口，该接口提供了 void publishEvent(ApplicationEvent event) 方法定义，不难看出，ApplicationContext 容器充当的就是事件发布者的角色，ApplicationContext 将事件的发布以及监听器的管理工作委托给 ApplicationEventMulticaster 接口的实现类。在容器启动时，会检查容器内是否存在名为 applicationEventMulticaster 的 ApplicationEventMulticaster 对象实例。如果有就使用其提供的实现，没有就默认初始化一个 SimpleApplicationEventMulticaster 作为实现。
