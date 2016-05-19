package software.renato.kotlin.rkablog.controller.interceptor

import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginInterceptor : HandlerInterceptorAdapter() {

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView) {
        val logout: String? = request.getParameter("logout")
        logout?.let { modelAndView.addObject("logout", "logout") }

        val error: String? = request.getParameter("error")
        error?.let { modelAndView.addObject("error", "error") }
    }

}