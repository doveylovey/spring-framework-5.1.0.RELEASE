# Spring IOC

### 相关类
- BeanDefinition：容器中每一个 bean 都有一个相对应的 BeanDefinition 实例，该实例负责保存 bean 对象的所有必要信息，包括 bean 对象的 class 类型、是否是抽象类、构造方法和参数、其它属性等。当客户端向容器请求相应对象时，容器就会通过这些信息为客户端返回一个完整可用的 bean 实例。
- BeanDefinitionRegistry：抽象出 bean 的注册逻辑，bean 对象对应的 BeanDefinition 实例会在 BeanDefinitionRegistry 中进行注册。
- BeanFactory：抽象出了 bean 的管理逻辑，而各个 BeanFactory 的实现类就具体承担了 bean 的注册以及管理工作。

> DefaultListableBeanFactory 作为一个比较通用的 BeanFactory 实现，它同时也实现了 BeanDefinitionRegistry 接口，因此它就承担了 Bean 的注册管理工作。BeanFactory 接口中主要包含 getBean()、containBean()、getType()、getAliases() 等管理 bean 的方法，而 BeanDefinitionRegistry 接口则包含 registerBeanDefinition()、removeBeanDefinition()、getBeanDefinition() 等注册管理 BeanDefinition 的方法

### IOC 容器启动阶段
这个阶段主要会进行：加载配置文件并解析，然后将解析后的数据封装为 BeanDefinition 实例，最后把这些保存了 bean 定义的 BeanDefinition 注册到相应的 BeanDefinitionRegistry，这样容器的启动工作就完成了。当然这个过程还包含了其他很多操作。

### IOC 容器实例化阶段
当某个请求通过容器的 getBean() 方法请求某个对象，或者因为依赖关系容器需要隐式的调用 getBean() 时，就会触发第二阶段的活动：容器会首先检查所请求的对象之前是否已经完成实例化。如果没有，则会根据注册的 BeanDefinition 所提供的信息实例化被请求对象，并为其注入依赖。当该对象装配完毕后，容器会立即将其返回给请求方法使用。

> 在实际中，使用更多的是 ApplicationContext 容器，它构建在 BeanFactory 之上，属于更高级的容器，除了具有 BeanFactory 的所有能力之外，还提供对事件监听机制、国际化等的支持。它管理的 bean 在容器启动时全部完成初始化和依赖注入操作。

# Spring 的扩展机制
IoC 容器负责管理容器中所有 bean 的生命周期，而在 bean 生命周期的不同阶段，Spring 提供了不同的扩展点来改变 bean 的命运。

### BeanFactoryPostProcessor(容器启动阶段)
BeanFactory 的前置处理器，允许我们在容器实例化相应对象之前，对注册到容器的 BeanDefinition 所保存的信息做一些额外的操作(如：修改 bean 定义的某些属性等)。

### BeanPostProcessor(bean 对象实例化阶段)
会处理容器内所有符合条件并且已经实例化后的对象

### Aware 接口
其作用就是在对象实例化完成以后将 Aware 接口定义中规定的依赖注入到当前实例中。比如最常见的 ApplicationContextAware 接口，实现了这个接口的类都可以获取到一个 ApplicationContext 对象。当容器中每个对象的实例化过程走到 BeanPostProcessor 前置处理这一步时，容器会检测到之前注册到容器的 ApplicationContextAwareProcessor，然后就会调用其 postProcessBeforeInitialization() 方法，检查并设置 Aware 相关依赖。
    



# 如果要将一个普通类交给 Spring 容器管理，通常有以下三种方法
- 使用 @Configuration 与 @Bean 注解
- 使用 @Component、@Repository、@Service、@Controller 等注解标注该类，然后启用 @ComponentScan 自动扫描
- 使用 @Import 方法
