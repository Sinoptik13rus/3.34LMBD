
object ChatServ {
    private val chats = mutableListOf<Chat>()
    var id = 0

    fun sendMessage(sid: Int, rid: Int, text: String) {
        val message = Message(id = 1, ownerId = sid, message = text)
        val newChat = chats.filter { chat ->
            chat.users.containsAll(listOf(sid, rid))
        }.firstOrNull().let { chat ->
            chat?.copy(messages = chat.messages + message)
        } ?: Chat(
            id + 1, listOf(sid, rid), listOf(message)
        )
        chats.removeIf { newChat.id == it.id }
        chats.add(newChat)
    }

    fun getChats(userId: Int) = chats.filter { chat -> chat.users.contains(userId) }.map { chat ->
        chat.messages.map { message ->
            message.messageRead = true
        }

    }


    fun readChat(chatId: Int): Boolean {
        chats.map { chat ->
            if (chat.id == chatId) chat.messages.map {
                it.messageRead = true
                return true
            }

        }
        return false
    }

    fun getUnreadChatsCount(userId: Int) =
        chats.count { chat ->
            chat.users.contains(userId)
        }


    fun deleteChat(chatId: Int) = chats.filter { chat -> chat.id == chatId }
        .map { chat ->
            chat.copy(
                id = null,
                users = emptyList(),
                messages = emptyList())
        }

    fun editMessage(chatId: Int, messageId: Int, text: String) = chats.filter { chat ->
        chat.id == chatId
    }.map { chat ->
        chat.messages.filter { message -> message.id == messageId }.map { message -> message.message = text }
    }

    fun deleteMessage(chatId: Int, messageId: Int) {
        chats.map { chat -> chat.messages.size }
        chats.filter { chat -> chat.id == chatId }.map { chat ->
            chat.messages.map { message ->
                if (message.id == messageId) message.message = null
            }
            if (chat.messages.isEmpty()) deleteChat(chatId)
        }
    }
}