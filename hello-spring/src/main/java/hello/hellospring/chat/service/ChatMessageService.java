package hello.hellospring.chat.service;

import hello.hellospring.chat.domain.ChatMessage;
import hello.hellospring.chat.dto.ChatRequestDto;

public interface ChatMessageService {
    //수신한 채팅 저장
    public ChatMessage save(ChatRequestDto chatRequestDto);

    //안 읽은 채팅 개수 표시
    public long countNewMessages(Long senderNo, Long receiverNo);

    //채팅 status update
    public void updateStatuses(Long senderNo, Long receiverNo, ChatMessage.MessageStatus status);

    ChatMessage addChatroomId(Long id, Long chatroomId);
}
