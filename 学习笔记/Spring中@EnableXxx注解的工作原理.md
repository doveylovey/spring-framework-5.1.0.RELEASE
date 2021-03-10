Spring 中 @EnableXxx 注解的工作原理
=================================

## 各种常见 Enable
@EnableAspectJAutoProxy
```text
@EnableAspectJAutoProxy 注解用于激活 Aspect 自动代理。
<aop:aspectj-autoproxy/> 用于开启对 AspectJ 自动代理的支持。
在用到 AOP 的自动代理的时候用，如果你理解了 Java 的动态代理，很容易的就会熟悉 AOP 的自动代理的。
```

@EnableAsync
```text
@EnableAsync 注解用于开启异步方法的支持。
```

@EnableScheduling
```text
@EnableScheduling 注解用于开启计划任务的支持，一般需要 @Scheduled 注解的配合。
```

@EnableWebMVC
```text
@EnableWebMVC 注解用于开启 WebMVC 的配置支持。
```

@EnableConfigurationProperties
```text
@EnableConfigurationProperties 注解用于开启对 @ConfigurationProperties 注解配置 Bean 的支持。
也就是 @EnableConfigurationProperties 注解告诉 SpringBoot 使能支持 @ConfigurationProperties
```

@EnableJpaRepositories
```text
@EnableJpaRepositories 注解用于开启对 Spring Data JPA Repository 的支持。
Spring Data JPA 框架，主要针对的就是 Spring 唯一没有简化到的业务逻辑代码，至此，开发者连仅剩的实现持久层业务逻辑的工作都省了，唯一要做的，就只是声明持久层的接口，其他都交给 Spring Data JPA 来帮你完成！
```

@EnableTransactionManagement
```text
@EnableTransactionManagement 注解用于开启注解式事务的支持。
注解 @EnableTransactionManagement 通知 Spring，@Transactional 注解的类被事务的切面包围，这样 @Transactional 就可以使用了。
```

@EnableCaching
```text
@EnableCaching 注解用于开启注解式的缓存支持
```

通过这些简单的 @EnableXxx 可以开启对某一项功能的支持，从而避免自己配置大量的代码，很大程度上降低了使用难度。通过源码可以发现所有的 @EnableXxx 注解上都有一个 @Import 注解。@Import 注解是用来导入配置类的，这也就是说这些自动开启的实现其实是导入了一些自动配置的 Bean。

## @Import 注解导入配置的三种方式
- 直接导入配置类
```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SchedulingConfiguration.class})
@Documented
public @interface EnableScheduling {
    // 直接导入配置类 SchedulingConfiguration，这个类注解了 @Configuration 且注册了一个 ScheduledAnnotationBeanPostProcessor 的 Bean
}

@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class SchedulingConfiguration {
    @Bean(name = TaskManagementConfigUtils.SCHEDULED_ANNOTATION_PROCESSOR_BEAN_NAME)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public ScheduledAnnotationBeanPostProcessor scheduledAnnotationProcessor() {
        return new ScheduledAnnotationBeanPostProcessor();
    }
}
```

- 依据条件选择配置类
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AsyncConfigurationSelector.class)
public @interface EnableAsync {
    Class<? extends Annotation> annotation() default Annotation.class;
    boolean proxyTargetClass() default false;
    AdviceMode mode() default AdviceMode.PROXY;
    int order() default Ordered.LOWEST_PRECEDENCE;
}

public class AsyncConfigurationSelector extends AdviceModeImportSelector<EnableAsync> {
    // AsyncConfigurationSelector 通过条件来选择需要导入的配置类，其根接口为 ImportSelector，需要重写根接口中的 selectImports() 方法，在此方法内进行条件判断
    private static final String ASYNC_EXECUTION_ASPECT_CONFIGURATION_CLASS_NAME = "org.springframework.scheduling.aspectj.AspectJAsyncConfiguration";
    
    @Override
    public String[] selectImports(AdviceMode adviceMode) {
        switch (adviceMode) {
            case PROXY:
                // 若 adviceMode 为 PROXY，则返回 ProxyAsyncConfiguration 这个配置类
                return new String[] { ProxyAsyncConfiguration.class.getName() };
            case ASPECTJ:
                // 若 activeMode 为 ASPECTJ，则返回 AspectJAsyncConfiguration 这个配置类
                return new String[] { ASYNC_EXECUTION_ASPECT_CONFIGURATION_CLASS_NAME };
            default:
                return null;
        }
    }
}
```

- 动态注册 Bean
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AspectJAutoProxyRegistrar.class)
public @interface EnableAspectJAutoProxy {
    boolean proxyTargetClass() default false;
}

class AspectJAutoProxyRegistrar implements ImportBeanDefinitionRegistrar {
    // ImportBeanDefinitionRegistrar 接口的作用是在运行时自动添加 Bean 到已有的配置类，需重写其中的 registerBeanDefinitions() 方法

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // AnnotationMetadata 参数用来获得当前配置类上的注解，BeanDefinitionRegistry 参数用来注册Bean
        AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry);
        AnnotationAttributes enableAJAutoProxy = AnnotationConfigUtils.attributesFor(importingClassMetadata, EnableAspectJAutoProxy.class);
        if (enableAJAutoProxy.getBoolean("proxyTargetClass")) {
            AopConfigUtils.forceAutoProxyCreatorToUseClassProxying(registry);
        }
    }
}
```
