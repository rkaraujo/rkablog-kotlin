package software.renato.kotlin.rkablog.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import software.renato.kotlin.rkablog.model.Post

interface PostRepository : CrudRepository<Post, Int> {

    fun findByPublishedAtIsNotNull(pageable: Pageable): Page<Post>

    fun findBySlugTitleAndPublishedAtIsNotNull(slugTitle: String): Post

}