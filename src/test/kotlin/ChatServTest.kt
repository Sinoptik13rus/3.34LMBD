import org.junit.Test

import org.junit.Assert.*

class ChatServTest {

    @Test
    fun sendMessage() {
    }

    @Test
    fun getChatsTest() {
        var chats = ChatServ
        val sid = 1
        val rid = 2
        val text = "1"

        val result = chats.sendMessage(sid, rid, text)

    }

    @Test
    fun readChat() {
    }

    @Test
    fun deleteChat() {
    }

    @Test
    fun editMessage() {
    }

    @Test
    fun deleteMessage() {
    }
}