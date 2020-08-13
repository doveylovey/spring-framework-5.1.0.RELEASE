BeanFactory 和 FactoryBean 的区别
================================
本文参考 https://www.cnblogs.com/aspirant/p/9082858.html

# BeanFactory 和 FactoryBean 的区别
- BeanFactory 接口提供了 IOC 容器最基本的形式，给具体的 IOC 容器实现提供了规范。
- FactoryBean 接口为 IOC 容器中 Bean 的实现提供了更加灵活的方式。FactoryBean 在 IOC 容器的基础上给 Bean 的实现加上了一个简单工厂模式和装饰模式，可以在 getObject() 方法中灵活配置，在 Spring 源码中就有很多 FactoryBean 的实现类。
- 区别：BeanFactory 是个 Factory，也就是 IOC 容器或对象工厂，而 FactoryBean 是个 Bean。在 Spring 中，所有的 Bean 都是由 BeanFactory(也就是 IOC 容器)来进行管理的，但对 FactoryBean 而言，这个 Bean 不是个简单的 Bean，而是一个能生产或者修饰对象生成的工厂 Bean，它的实现与设计模式中的工厂模式和修饰器模式类似。

# BeanFactory
BeanFactory 以 Factory 结尾，表示它是一个工厂类(接口)，它是负责生产和管理 Bean 的一个工厂。在 Spring 中，BeanFactory 是 IOC 容器的核心接口，它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。

BeanFactory 只是个接口，并不是 IOC 容器的具体实现，但是 Spring 中给出了很多种实现，如 DefaultListableBeanFactory、XmlBeanFactory、ApplicationContext 等，其中 XmlBeanFactory 就是常用的一个，该实现将以 XML 方式描述组成应用的对象及对象间的依赖关系。XmlBeanFactory 类将持有此 XML 配置元数据，并用它来构建一个完全可配置的系统或应用。

BeanFactory 接口为其他具体的 IOC 容器提供了最基本的规范，如 DefaultListableBeanFactory、XmlBeanFactory、ApplicationContext 等容器都实现了 BeanFactory 接口，再在其基础之上附加上其他功能。

BeanFactory 和 ApplicationContext 就是 Spring 框架中的两个常用 IOC 容器，现在一般使用 ApplicationContext，它不但包含了 BeanFactory 的作用，同时还进行了更多的扩展。
BeanFactory 是 Spring 中比较原始的 Factory，如 XMLBeanFactory 就是一种典型的 BeanFactory。原始的 BeanFactory 无法支持 Spring 的许多插件，如 AOP 功能、Web 应用等。
ApplicationContext 接口是由 BeanFactory 接口派生而来的，ApplicationContext 包含了 BeanFactory 的所有功能，通常建议比 BeanFactory 优先使用。
ApplicationContext 以一种更向面向框架的方式工作以及对上下文进行分层和实现继承，ApplicationContext 包还提供了以下的功能：
- MessageSource，提供国际化的消息访问
- 资源访问(如 URL 和文件)
- 事件传播
- 载入多个(有继承关系)上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的 web 层

在使用 Spring 框架之前，我们的 Service 层中要使用 Dao 层的对象，就不得不在 Service 层中 new 一个 Dao 对象。存在的问题：层与层之间的依赖。
Service 层要用 Dao 层对象则需要配置到 xml 文件中，至于对象是怎么创建的、关系是怎么组合的都交给 Spring 框架去实现。 
```xml
Resource resource = new FileSystemResource("beans.xml");
BeanFactory factory = new XmlBeanFactory(resource);

ClassPathResource resource = new ClassPathResource("beans.xml");
BeanFactory factory = new XmlBeanFactory(resource);

ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml", "applicationContext-part2.xml"});
BeanFactory factory = (BeanFactory) context;
```
基本就是这些了，接着使用 getBean(String beanName) 方法就可以取得 Bean 的实例。BeanFactory 提供的方法及其简单，仅提供了六种方法供客户调用：
```java
boolean containsBean(String beanName)：判断工厂中是否包含给定名称的 Bean 定义，若有则返回 true
Object getBean(String)：返回给定名称注册的 Bean 实例。根据 Bean 的配置情况，如果是 singleton 模式则返回一个共享实例，否则返回一个新建的实例，如果没有找到指定 Bean，该方法可能会抛出异常
Object getBean(String, Class)：返回以给定名称注册的 Bean 实例，并转换为给定 Class 类型
Class getType(String name)：返回给定名称的 Bean 的 Class，如果没有找到指定的 Bean 实例，则抛出 NoSuchBeanDefinitionException 异常
boolean isSingleton(String)：判断给定名称的 Bean 定义是否为单例模式
String[] getAliases(String name)：返回给定 Bean 名称的所有别名
``` 
Spring 框架中 BeanFactory 的源码如下(已忽略注释和其他无关内容)：
```java
package org.springframework.beans.factory;  
import org.springframework.beans.BeansException;  
public interface BeanFactory {  
    String FACTORY_BEAN_PREFIX = "&";  
    Object getBean(String name) throws BeansException;  
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;  
    <T> T getBean(Class<T> requiredType) throws BeansException;  
    Object getBean(String name, Object... args) throws BeansException;  
    boolean containsBean(String name);  
    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;  
    boolean isPrototype(String name) throws NoSuchBeanDefinitionException;  
    boolean isTypeMatch(String name, Class<?> targetType) throws NoSuchBeanDefinitionException;  
    Class<?> getType(String name) throws NoSuchBeanDefinitionException;  
    String[] getAliases(String name);  
}
```

# FactoryBean
一般情况下，Spring 通过反射机制利用 <bean> 标签中的 class 属性指定实现类来实例化 Bean，但在某些情况下，实例化 Bean 过程比较复杂，如果按照传统的方式，则需要在 <bean> 标签中提供大量的配置信息。
配置方式的灵活性受到限制，这时采用编码的方式反而可能会简单一点，因此 Spring 提供了一个 org.springframework.bean.factory.FactoryBean 的工厂类接口，用户可以通过实现该接口来定制实例化 Bean 的逻辑。
FactoryBean 接口对于 Spring 框架来说占有重要的地位，Spring 自身就提供了 70 多个 FactoryBean 的实现，它们隐藏了实例化一些复杂 Bean 的细节，给上层应用带来了便利。从 Spring 3.0 开始，FactoryBean 开始支持泛型，即接口声明改为 FactoryBean<T> 的形式。

FactoryBean 以 Bean 结尾，表示它是一个 Bean，与普通 Bean 不同于的是：它根据该 Bean 的 ID 从 BeanFactory 中获取的实际上是 FactoryBean 的 getObject() 返回的对象，而不是 FactoryBean 本身，如果要获取 FactoryBean 对象本身，则需要在 Bean 的 ID 前面加一个 & 符号来获取。

我们自己实现一个 FactoryBean，其功能是：用来代理一个对象，对该对象的所有方法做一个拦截，在调用前后都输出一行 LOG，模仿 ProxyFactoryBean 的功能。  
