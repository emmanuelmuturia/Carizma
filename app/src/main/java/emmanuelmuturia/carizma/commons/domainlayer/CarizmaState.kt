package emmanuelmuturia.carizma.commons.domainlayer

sealed interface CarizmaState<out T> {

    data class Success<T>(val data: T): CarizmaState<T>

    data class Error(val message: Throwable): CarizmaState<Nothing>

    data object Loading : CarizmaState<Nothing>

}