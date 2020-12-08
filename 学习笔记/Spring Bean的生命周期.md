# Spring Bean 的完整生命周期

#### Bean 的生命周期
![Spring Bean构造过程图](img/Spring Bean构造过程图.jpg)

如上图所示，Bean 的生命周期还是比较复杂的，下面来对上图每一个步骤做文字描述：
- Spring 启动，查找并加载需要被 Spring 管理的 Bean，进行 Bean 的实例化
- Bean 实例化后对将 Bean 的引入和值注入到 Bean 的属性中
- 如果 Bean 实现了 BeanNameAware 接口，Spring 将调用它的 setBeanName() 方法，将 Bean 的 Id 传入
- 如果 Bean 实现了 BeanFactoryAware 接口，Spring 将调用它的 setBeanFactory() 方法，将 BeanFactory 容器实例传入
- 如果 Bean 实现了 ApplicationContextAware 接口，Spring 将调用它的 setApplicationContext() 方法，将 Bean 所在的应用上下文引用传入
- 如果 Bean 实现了 BeanPostProcessor 接口，Spring 将调用它的 postProcessBeforeInitialization() 方法
- 如果 Bean 实现了 InitializingBean 接口，Spring 将调用它的 afterPropertiesSet() 方法。类似的，如果 Bean 使用 init-method 声明了初始化方法，该方法也会被调用
- 如果 Bean 实现了 BeanPostProcessor 接口，Spring 将调用它的 postProcessAfterInitialization() 方法
- 此时，Bean 已经准备就绪，可以被应用程序使用了。它们将一直驻留在应用上下文中，直到应用上下文被销毁
- 如果 Bean 实现了 DisposableBean 接口，Spring 将调用它的 destroy() 接口方法，同样，如果 Bean 使用了 destroy-method 声明销毁方法，该方法也会被调用

上面是 Spring 中 Bean 的核心接口和生命周期，面试回答上述过程已经足够了。但是翻阅 Java Doc 文档发现除了以上接口外，初始化过程还涉及另外的接口：
![Spring Bean构造过程图(具体到类或接口)](img/Spring Bean构造过程图(具体到类或接口).jpg)

#### Bean 完整的生命周期文字解释(初始化、销毁)
`————————————初始化————————————`
- BeanNameAware.setBeanName()：在创建此 Bean 的 Bean 工厂中设置 Bean 的名称，在普通属性设置之后调用，在 InitializingBean.afterPropertiesSet() 方法之前调用
- BeanClassLoaderAware.setBeanClassLoader()：在普通属性设置之后，InitializingBean.afterPropertiesSet() 之前调用
- BeanFactoryAware.setBeanFactory()：回调提供了自己的 Bean 实例工厂，在普通属性设置之后，在 InitializingBean.afterPropertiesSet() 或者自定义初始化方法之前调用
- EnvironmentAware.setEnvironment()：设置 environment，在组件使用时调用
- EmbeddedValueResolverAware.setEmbeddedValueResolver()：设置 StringValueResolver，用来解决嵌入式的值域问题
- ResourceLoaderAware.setResourceLoader()：在普通 Bean 对象之后调用，在 afterPropertiesSet() 或者自定义的 init-method 之前调用，在 ApplicationContextAware 之前调用
- ApplicationEventPublisherAware.setApplicationEventPublisher()：在普通Bean属性之后调用，在初始化调用afterPropertiesSet 或者自定义初始化方法之前调用。在 ApplicationContextAware 之前调用
- MessageSourceAware.setMessageSource()：在普通 Bean 属性之后调用，在初始化调用 afterPropertiesSet() 或者自定义初始化方法之前调用，在 ApplicationContextAware 之前调用
- ApplicationContextAware.setApplicationContext()：在普通 Bean 对象生成之后调用，在InitializingBean.afterPropertiesSet() 之前调用或者用户自定义初始化方法之前。在 ResourceLoaderAware.setResourceLoader()、ApplicationEventPublisherAware.setApplicationEventPublisher()、MessageSourceAware 之后调用
- ServletContextAware.setServletContext()：运行时设置 ServletContext，在普通 Bean 初始化后调用，在 InitializingBean.afterPropertiesSet() 之前调用，在 ApplicationContextAware 之后调用。注：是在 WebApplicationContext 运行时
- BeanPostProcessor.postProcessBeforeInitialization()：将此 BeanPostProcessor 应用于给定的新 Bean 实例在任何 Bean 初始化回调方法(像是 InitializingBean.afterPropertiesSet() 或者自定义的初始化方法)之前调用。这个 Bean 将要准备填充属性的值。返回的 Bean 示例可能被普通对象包装，默认实现返回是一个 Bean
- BeanPostProcessor.postProcessAfterInitialization()：将此 BeanPostProcessor 应用于给定的新 Bean 实例在任何 Bean 初始化回调方法(像是 InitializingBean.afterPropertiesSet() 或者自定义的初始化方法)之后调用。这个 Bean 将要准备填充属性的值。返回的 Bean 示例可能被普通对象包装
- InitializingBean.afterPropertiesSet()：被 BeanFactory 在设置所有 Bean 属性之后调用(并且满足 BeanFactory 和 ApplicationContextAware)

`————————————销毁————————————`
在 BeanFactory 关闭的时候，Bean 的生命周期会调用如下方法：
- DestructionAwareBeanPostProcessor.postProcessBeforeDestruction()：在销毁之前将此 BeanPostProcessor 应用于给定的 Bean 实例。能够调用自定义回调，像是 DisposableBean 的销毁和自定义销毁方法，这个回调仅仅适用于工厂中的单例 Bean(包括内部 Bean)
- 实现了自定义的 destroy() 方法