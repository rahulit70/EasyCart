import com.rm.easycart.cart.converter.CartListViewModelToUserEvent
import com.rm.easycart.cart.viewmodel.CartListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class CartListViewModelToUserEventTest {


    @Test
    fun testConvert() = runTest {
        // Mocking the CartListViewModel
        val cartListViewModel = mockk<CartListViewModel>(relaxed = true)

        // Stubbing the behavior of loadCart method
        coEvery { cartListViewModel.loadCart() } returns Job() // Returning a mock Job object

        // Creating an instance of the converter
        val converter = CartListViewModelToUserEvent()

        // Calling the convert method
        val result = converter.convert(cartListViewModel)

        // Asserting the result
        assertEquals(Unit::class.java, result.loadCart.invoke()::class.java)
    }
}
