package ir.milad.androidexamples

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }

}