package emmanuelmuturia.carizma.car.domainlayer.model

data class Car(
    val carId: Int = 0,
    val carName: String = "",
    val carSound: String = "",
    val carImage: String = "",
    val carDescription: String = "",
    val carTopSpeed: Double = 0.0,
    val carAcceleration: Double = 0.0
)