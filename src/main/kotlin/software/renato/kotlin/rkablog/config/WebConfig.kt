package software.renato.kotlin.rkablog.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import software.renato.kotlin.rkablog.controller.interceptor.LoginInterceptor

@Configuration
open class WebConfig : WebMvcConfigurerAdapter() {

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/login.html").setViewName("login")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(LoginInterceptor()).addPathPatterns("/login.html")
    }
}
