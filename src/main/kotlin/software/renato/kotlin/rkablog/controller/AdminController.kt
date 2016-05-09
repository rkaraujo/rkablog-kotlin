package software.renato.kotlin.rkablog.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import software.renato.kotlin.rkablog.model.Post
import software.renato.kotlin.rkablog.repository.PostRepository
import software.renato.kotlin.rkablog.util.slugfy
import java.util.*

@Controller
class AdminController @Autowired constructor(val postRepository: PostRepository) {

    val PAGE_SIZE = 25

    @RequestMapping(value = "/admin/index.html", method = arrayOf(RequestMethod.GET))
    fun index(@RequestParam(name = "page", required = false) page: Integer?, model: Model): String {
        val pageParam = if (page == null || page < 0) 0 else page.toInt()
        val postsPage = postRepository.findAll(PageRequest(pageParam, PAGE_SIZE, Sort.Direction.DESC, "updatedAt"))
        model.addAttribute("postsPage", postsPage)
        return "admin/index"
    }

    @RequestMapping(value = "/admin/post/new", method = arrayOf(RequestMethod.GET))
    fun new(): String {
        return "admin/edit"
    }

    @RequestMapping(value = "/admin/post/delete/{id}", method = arrayOf(RequestMethod.GET))
    fun delete(@PathVariable("id") id: Int, redirectAttributes: RedirectAttributes): String {
        postRepository.delete(id)
        redirectAttributes.addFlashAttribute("successMessage", "Post deleted")
        return "redirect:/admin/index.html"
    }

    @RequestMapping(value = "/admin/post/edit/{id}", method = arrayOf(RequestMethod.GET))
    fun edit(@PathVariable("id") id: Int, model: Model): String {
        val post = postRepository.findOne(id)
        model.addAttribute("post", post)
        return "admin/edit"
    }

    @RequestMapping(value = "/admin/post/save", method = arrayOf(RequestMethod.POST))
    fun save(@ModelAttribute("post") postForm: Post, redirectAttributes: RedirectAttributes): String {
        var post: Post
        if (postForm.id != 0) {
            post = postRepository.findOne(postForm.id)
            with(post) {
                content = postForm.content
                title = postForm.title
                pageDescription = postForm.pageDescription
            }
        } else {
            post = postForm
        }
        post.slugTitle = post.title.slugfy()

        postRepository.save(post)
        redirectAttributes.addFlashAttribute("successMessage", "Post saved")
        return "redirect:/admin/index.html"
    }

    @RequestMapping(value = "/admin/post/publish/{id}", method = arrayOf(RequestMethod.GET))
    fun publish(@PathVariable("id") id: Int, redirectAttributes: RedirectAttributes): String {
        val post = postRepository.findOne(id)
        post.publishedAt = Date()
        postRepository.save(post)
        redirectAttributes.addFlashAttribute("successMessage", "Post published")
        return "redirect:/admin/index.html"
    }

}

