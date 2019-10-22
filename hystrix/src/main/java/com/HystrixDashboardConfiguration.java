package com;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yinjianquan on 19-10-22.
 */
@Configuration
public class HystrixDashboardConfiguration {
    @Bean
    public ServletRegistrationBean getServlet() {
        //创建自己的servlet
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        //注册servlet
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        //启动顺序
        registrationBean.setLoadOnStartup(1);
        //Servlet的访问路径
        registrationBean.addUrlMappings("/hystrix.stream");
        //Servlet的名字
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}

