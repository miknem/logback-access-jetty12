# logback-access-jetty12

## About
An addon for logback-access to get access logs working with Jetty12/ Spring Boot 3.2.
Basically a workaround for the problems described here https://github.com/qos-ch/logback/issues/719. 

Because of Jetty 12 being incompatible with previous versions (e.g. Request and Response objects do not implement servlet spec) we did not find a good way to do a pull request to merge this into the real logback framework https://github.com/qos-ch/logback without breaking backwards compatibility.   

Package contains a custom implementation of copy-pasted logback access classes that has been adapted to support Jetty 12. 

## Purpose
The purpose of publishing this code is to just share what we have done ourselves in order to hopefully inspire someone to get support for Jetty 12 into the actual logback framework.
Hopefully this could be helpful for someone.

Use at own risk, we do not take responsibility for any bugs or problems this may cause, nor do we plan to support this code in the future, this repository merely exists for code-sharing purposes.

## Usage
The way we currently run this is that we package this code as a jar file ourselves. Then we include this in our Spring Boot 3.2 applications alongside the actual logback access framework.
When customizing Jetty for Spring Boot we instead use `Jetty12RequestLogImpl` instead of `RequestLogImpl`. Example below
```
public void customize(JettyServletWebServerFactory factory) {
    Jetty12RequestLogImpl requestLog = new Jetty12RequestLogImpl();
    ...
    requestLog.start();
    factory.addServerCustomizers(server -> server.setRequestLog(requestLog);
}
```
