// TODO: this file should be removed when API is in main Ktor repo

package io.ktor.authentication

import io.ktor.http.content.*
import io.ktor.http.*
import io.ktor.http.auth.*

/**
 * Response content with `401 Unauthorized` status code and `WWW-Authenticate` header of supplied [challenges]
 * @param challenges to be passed with `WWW-Authenticate` header
 */
public class UnauthorizedResponse(public vararg val challenges: HttpAuthHeader) : OutgoingContent.NoContent() {
    override val status: HttpStatusCode?
        get() = HttpStatusCode.Unauthorized

    override val headers: Headers
        get() = if (challenges.isNotEmpty())
            headersOf(HttpHeaders.WWWAuthenticate, challenges.joinToString(", ") { it.render() })
        else
            Headers.Empty
}