import com.vyykn.auth.Role
import com.vyykn.auth.User
import com.vyykn.auth.UserRole
import com.vyykn.oauth.Client

class BootStrap {

    def init = { servletContext ->
        Role roleUser = new Role(authority: 'ROLE_USER').save(flush: true)

        User user = new User(
                username: 'user',
                password: 'user',
                enabled: true,
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false
        ).save(flush: true)

        UserRole.create(user, roleUser, true)

        new Client(
                clientId: 'my-client',
                authorizedGrantTypes: ['authorization_code', 'refresh_token', 'implicit', 'password', 'client_credentials'],
                authorities: ['ROLE_CLIENT'],
                scopes: ['read', 'write'],
                redirectUris: ['http://myredirect.com']
        ).save(flush: true)


    }
    def destroy = {
    }
}
