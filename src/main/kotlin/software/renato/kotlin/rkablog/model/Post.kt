package software.renato.kotlin.rkablog.model

import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "posts")
data class Post(
        var content: String = "",

        @Column(name = "created_at")
        var createdAt: Date = Date(),

        @Column(name = "published_at")
        var publishedAt: Date? = Date(),

        @Column(name = "slug_title")
        var slugTitle: String = "",

        var title: String = "",

        @Column(name = "updated_at")
        var updatedAt: Date = Date(),

        @Column(name = "page_description")
        var pageDescription: String = "",

        @Id
        @GeneratedValue
        var id: Int = 0
) {

    fun getFormattedPublishedAt(): String {
        val sdf = SimpleDateFormat("MMM dd, yyyy")
        return if (publishedAt != null) sdf.format(publishedAt) else ""
    }

    fun getSummaryContent(): String {
        return content.substringBefore("</p>").substringAfter("<p>")
    }

    fun getUrl(): String {
        return "/p/${slugTitle}.html"
    }
}


