BeanFactory 和 FactoryBean 的区别
================================
本文参考 https://www.cnblogs.com/aspirant/p/9082858.html

# 1、BeanFactory 详解
### BeanFactory 源码及部分方法的作用描述
```java
package org.springframework.beans.factory;
import org.springframework.beans.BeansException;
public interface BeanFactory {
    String FACTORY_BEAN_PREFIX = "&";
    Object getBean(String name) throws BeansException;// 返回给定名称注册的 Bean 实例。根据 Bean 的配置情况，如果是 singleton 模式将返回一个共享实例，否则将返回一个新建的实例，如果没有找到指定 Bean，该方法可能会抛出异常。
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;// 返回以给定名称注册的 Bean 实例，并转换为给定的 Class 类型。
    <T> T getBean(Class<T> requiredType) throws BeansException;
    Object getBean(String name, Object... args) throws BeansException;
    boolean containsBean(String name);// 判断工厂中是否包含给定名称的 Bean 定义，若有则返回 true。
    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;// 判断给定名称的 Bean 定义是否为单例模式
    boolean isPrototype(String name) throws NoSuchBeanDefinitionException;
    boolean isTypeMatch(String name, Class<?> targetType) throws NoSuchBeanDefinitionException;
    Class<?> getType(String name) throws NoSuchBeanDefinitionException;// 返回给定名称的 Bean 的 Class，如果没有找到指定的 Bean 实例，则抛出 NoSuchBeanDefinitionException 异常。
    String[] getAliases(String name);// 返回给定名称的 Bean 的所有别名
}
```
BeanFactory 以 Factory 结尾，表示它是一个工厂类(接口)，它是负责生产和管理 Bean 的一个工厂。
在 Spring 中，BeanFactory 是 IOC 容器的核心接口，它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。
BeanFactory 只是个接口，并不是 IOC 容器的具体实现，但 Spring 给出了很多种实现，如 DefaultListableBeanFactory、XmlBeanFactory、ApplicationContext 等，其中 XmlBeanFactory 就是常用的一个，该实现以 XML 的方式描述组成应用的对象及对象间的依赖关系。XmlBeanFactory 类将持有此 XML 配置元数据，并用它来构建一个完全可配置的系统或应用。

### 实例化 BeanFactory 容器的几种方式
```xml
Resource resource = new FileSystemResource("spring-beans.xml");
BeanFactory factory = new XmlBeanFactory(resource);

ClassPathResource resource = new ClassPathResource("spring-beans.xml");
BeanFactory factory = new XmlBeanFactory(resource);

ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"application-context1.xml", "spring-context2.xml"});
BeanFactory factory = (BeanFactory) context;
```
BeanFactory 接口为其他的具体 IOC 容器提供了最基本的规范，如 DefaultListableBeanFactory、XmlBeanFactory、ApplicationContext 等容器都实现了 BeanFactory 接口，然后在其基础上附加一些其他功能。

BeanFactory 和 ApplicationContext 是 Spring 中的最为常用的两个 IOC 容器，现在一般使用 ApplicationContext，它不但包含了 BeanFactory 的作用，同时还进行了更多的扩展。
BeanFactory 是 Spring 中比较原始的 Factory，如 XMLBeanFactory 就是一种典型的 BeanFactory。原始的 BeanFactory 无法支持 Spring 的许多插件，如 AOP 功能、Web 应用等。
ApplicationContext 是由 BeanFactory 接口派生而来的，ApplicationContext 包含了 BeanFactory 的所有功能，通常建议比 BeanFactory 优先使用。

ApplicationContext 以一种更像面向框架的方式工作以及对上下文进行分层和实现继承，ApplicationContext 包还提供了以下的功能：
- MessageSource，提供国际化的消息访问
- 资源访问(如 URL 和文件)
- 事件传播
- 载入多个(有继承关系)上下文 ，使得每一个上下文都专注于一个特定的层次，如应用的 web 层

在使用 Spring 框架以前，在 Service 层中通常都要使用 Dao 层对象，就必须在 Service 层中 new 一个 Dao 对象，这样存在的问题是：层与层之间的依赖。使用 Spring 后，把 Service 层和 Dao 层对象都配置到 xml 文件中，至于对象是怎么创建的、关系是怎么组合的都交给 Spring 框架去实现。

# 2、FactoryBean 详解
### FactoryBean 源码及部分方法的作用描述
```java
package org.springframework.beans.factory;
public interface FactoryBean<T> {
    T getObject() throws Exception;// 返回由 FactoryBean 创建的 Bean 实例，如果 isSingleton() 返回 true，则该实例会放到 Spring 容器中单实例缓存池中。
    Class<?> getObjectType();// 返回 FactoryBean 创建的 Bean 类型。
    boolean isSingleton();// 返回由 FactoryBean 创建的 Bean 实例的作用域是 singleton 还是 prototype。
}
```
FactoryBean 也是一个接口，以 Bean 结尾，表示它是一个 Bean，与普通 Bean 不同于的是：当 IOC 容器中的 Bean 实现了该接口后，根据该实现类的 beanName 从 BeanFactory 中获取到的 Bean 实际上是该实现类中的 getObject() 方法返回的对象(相当于 getObject() 代理了 getBean() 方法)，而不是 FactoryBean 本身，如果要想获取 FactoryBean 的实现类本身，则需要在该 beanName 前加上 & 来获取。

一般情况下，Spring 通过反射机制利用 <bean> 标签中的 class 属性指定实现类来实例化 Bean，但在某些情况下，实例化 Bean 的过程比较复杂，如果按照传统方式，则需要在 <bean> 标签中提供大量的配置信息。
配置方式的灵活性受到限制，这时采用编码的方式反而可能会简单一点，因此 Spring 提供了 org.springframework.bean.factory.FactoryBean 工厂类接口，用户可以通过实现该接口来定制实例化 Bean 的逻辑。
FactoryBean 接口对于 Spring 框架来说占有重要的地位，Spring 自身就提供了 70 多个 FactoryBean 的实现，它们隐藏了实例化一些复杂 Bean 的细节，给上层应用带来了便利。从 Spring 3.0 开始，FactoryBean 开始支持泛型，即接口声明改为 FactoryBean<T> 的形式。

# 3、BeanFactory 和 FactoryBean 的区别
- BeanFactory 以 Factory 结尾，表示它是一个工厂类(接口)，它是负责生产和管理 Bean 的一个工厂。该接口提供了 IOC 容器最基本的形式，给具体的 IOC 容器实现提供了规范。在 Spring 中，BeanFactory 是 IOC 容器的核心接口，它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。
- FactoryBean 以 Bean 结尾，表示它是一个 Bean，与普通 Bean 不同于的是：它根据该 Bean 的 ID 从 BeanFactory 中获取的实际上是 FactoryBean 的 getObject() 方法返回的对象，而不是 FactoryBean 本身，如果要获取 FactoryBean 本身，则需要在 Bean 的 ID 前面加一个 & 符号来获取。该接口为 IOC 容器中 Bean 的实现提供了更加灵活的方式。FactoryBean 在 IOC 容器的基础上给 Bean 的实现加上了一个简单工厂模式和装饰模式，可以在 getObject() 方法中灵活配置，在 Spring 源码中就有很多 FactoryBean 的实现类。
