package hello.login;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;


@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());    //우리가 만든 logfilter 넣어줌
        filterFilterRegistrationBean.setOrder(1);   //필터 순서
        filterFilterRegistrationBean.addUrlPatterns("/*"); //url 패턴

        return filterFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean logCheckFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginCheckFilter());    //우리가 만든 logfilter 넣어줌
        filterFilterRegistrationBean.setOrder(2);   //필터 순서
        filterFilterRegistrationBean.addUrlPatterns("/*"); //url 패턴

        return filterFilterRegistrationBean;
    }
}
