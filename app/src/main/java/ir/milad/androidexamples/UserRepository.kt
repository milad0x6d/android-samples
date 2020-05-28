package ir.milad.androidexamples

class UserRepository(private val api : GithubApi) {
    fun getAllUsers() = api.getUsers()
}