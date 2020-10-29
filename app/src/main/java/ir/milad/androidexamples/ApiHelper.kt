package ir.milad.androidexamples

interface ApiHelper {
    suspend fun getUsers(): List<User>
}