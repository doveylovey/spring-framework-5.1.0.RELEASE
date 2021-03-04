# IOC 容器的初始化
IOC 容器的初始化包括三个过程：BeanDefinition 的 Resource 定位、载入、注册。

1、BeanDefinition 的资源定位由 ResourceLoader 通过统一的 Resource 接口来完成，这个 Resource 对各种形式的 BeanDefinition 的使用提供了统一接口，常见的 BeanDefinition 存在形式：文件系统中(使用 FileSystemResource 加载)、类路径下(使用 ClassPathResource 加载)。

2、BeanDefinition 的载入就是把用户定义好的 Bean 表示成 IOC 容器内部的数据结构，而这个数据结构就是 BeanDefinition。总的来说，BeanDefinition 实际上就是 POJO 对象在 IOC 容器中的抽象，它定义了一系列的数据来使得 IOC 容器能够方便的对 POJO 对象(也就是 Spring 的 Bean)进行管理，即 BeanDefinition 就是 Spring 的领域对象。

3、向 IOC 容器注册这些 BeanDefinition 就是通过调用 BeanDefinitionRegistry 接口的实现来完成的，这个注册过程把载入过程中解析得到的 BeanDefinition 向 IOC 容器进行注册。可以看到，在 IOC 容器内部是通过一个 HashMap 来持有这些 BeanDefinition 数据的。

【注意】IOC 容器和上下文的初始化一般不包含 Bean 依赖注入的实现。一般而言，依赖注入发生在应用第一次向容器通过 getBean() 索取 Bean 时。但在使用 IOC 容器时有一个预实例化的配置，可以通过在 Bean 定义信息中的 lazy-init 属性来设定。该特性让用户可以对容器的初始化过程作一个微小的控制，从而改变这个被设置了 lazy-init 属性的 Bean 的依赖注入的发生，使得这个 Bean 的依赖注入在 IOC 容器初始化时就预先完成了。
