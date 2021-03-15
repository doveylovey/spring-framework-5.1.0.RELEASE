## 方法一：采取默认配置
1、在 web.xml 中配置。示例：
```xml
<listener>
    <listener-class>org.apache.logging.log4j.web.Log4jServletContextListener</listener-class>
</listener>
<filter>
    <filter-name>log4jServletFilter</filter-name>
    <filter-class>org.apache.logging.log4j.web.Log4jServletFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>log4jServletFilter</filter-name>
    <url-pattern>/*</url-pattern>
    <dispatcher>REQUEST</dispatcher>
    <dispatcher>FORWARD</dispatcher>
    <dispatcher>INCLUDE</dispatcher>
    <dispatcher>ERROR</dispatcher>
</filter-mapping>
```

2、在 resource 资源路径下加入 log4j2 的配置文件，注意文件名为 log4j2.xml。示例：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="OFF" monitorInterval="1800">
    <properties>
        <property name="LOG_HOME">/logs</property>
        <property name="FILE_NAME">web-demo</property>
    </properties>
 
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </Console>
        <RollingFile name="logFile" fileName="${LOG_HOME}/${FILE_NAME}.log" filePattern="${LOG_HOME}/$${date:yyyy-MM}/${FILE_NAME}-%d{yyyy-MM-dd}-%i.log.gz" immediateFlush="true">
            <PatternLayout pattern="%date{yyyy-MM-dd HH:mm:ss.SSS} %level [%thread][%file:%line] - %msg%n" />
            <Policies>
                <TimeBasedTriggeringPolicy />
                <SizeBasedTriggeringPolicy size="10 MB" />
            </Policies>
            <DefaultRolloverStrategy max="20" />
        </RollingFile>
    </Appenders>

    <Loggers>
        <Logger name="com.study.*" level="trace" additivity="false">
            <AppenderRef ref="logFile"/>
        </Logger>
        <Root level="info">
            <AppenderRef ref="logFile"/>
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```

## 方法二：推荐
1、在 xml 中配置，可以修改配置文件路径。例如：
```xml
<context-param>
    <param-name>log4j2ConfigLocation</param-name>
    <param-value>classpath*:log4j2.xml</param-value>
</context-param>
<listener>
    <listener-class>com.study.config.Log4j2ConfigListener</listener-class>
</listener>
```

2、实现 ServletContextListener
```java
public class Log4j2ConfigListener implements ServletContextListener {
    private static final String KEY = "log4j2ConfigLocation";
 
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }
 
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        String fileName = getContextParam(arg0);
        Configurator.initialize("Log4j2", fileName);
    }
 
    private String getContextParam(ServletContextEvent event) {
        Enumeration<String> names = event.getServletContext().getInitParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            String value = event.getServletContext().getInitParameter(name);
            if(name.trim().equals(KEY)){
                return value;
            }
        }
        return null;
    }
}
```

3、配置文件内容和方法一中的 log4j2.xml 一样。
