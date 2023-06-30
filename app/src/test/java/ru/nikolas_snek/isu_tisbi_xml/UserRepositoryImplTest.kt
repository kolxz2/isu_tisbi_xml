/*import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nikolas_snek.isu_tisbi_xml.data.api.ResultRequest
import ru.nikolas_snek.isu_tisbi_xml.domain.LoginManager

class LoginManagerTest {
    @Test
    fun testLoginSuccess() {
        val username = "НСучёв"
        val password = "kolxzchek"

        val loginManager = LoginManager()

        runBlocking {
            val result = loginManager.login(username, password)

            // Проверяем, что результат успешен и содержит ожидаемый токен
            assertEquals(true, result is ResultRequest.Success)
            assertEquals("ожидаемый_токен", (result as ResultRequest.Success).data)
        }
    }
}*/
