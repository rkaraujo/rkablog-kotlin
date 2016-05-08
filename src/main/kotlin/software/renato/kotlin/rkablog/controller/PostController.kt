package software.renato.kotlin.rkablog.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import software.renato.kotlin.rkablog.repository.PostRepository

@Controller
class PostController @Autowired constructor(val postRepository: PostRepository) {

    @RequestMapping(value = "/index.html", method = arrayOf(RequestMethod.GET))
    fun index(@RequestParam(name = "page", required = false) page: Integer?, model: Model): String {
        val pageParam = if (page == null || page < 0) 0 else page.toInt()
        val postsPage = postRepository.findByPublishedAtIsNotNull(PageRequest(pageParam, 5, Sort.Direction.DESC, "publishedAt"))
        model.addAttribute("postsPage", postsPage)
        return "index"
    }

    @RequestMapping(value = "/p/{slugTitle}.html", method = arrayOf(RequestMethod.GET))
    fun show(@PathVariable("slugTitle") slugTitle: String, model: Model): String {
        val post = postRepository.findBySlugTitleAndPublishedAtIsNotNull(slugTitle)
        model.addAttribute("post", post)
        return "post"
    }

}


