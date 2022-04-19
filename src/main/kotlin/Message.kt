data class Message(
    val id: Int,
    val ownerId: Int,
    var message: String?,
    var messageRead: Boolean = false
)