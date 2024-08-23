import { Message, User } from "@/app/data";
import ChatTopbar from "./chat-topbar";
import { ChatList } from "./chat-list";
import React from "react";

interface ChatProps {
  selectedUser: User;
  isMobile: boolean;
}

export function Chat({ selectedUser, isMobile }: ChatProps) {
  // selectedUser가 null인 경우, 빈 배열로 초기화
  const [messagesState, setMessages] = React.useState<Message[]>(
    selectedUser?.messages ?? [] // selectedUser가 null일 경우 빈 배열을 사용
  );

  const sendMessage = (newMessage: Message) => {
    setMessages((prevMessages) => [...prevMessages, newMessage]);
  };

  return (
    <div className="flex flex-col justify-between w-full h-full">
      <ChatTopbar selectedUser={selectedUser} />

      <ChatList
        messages={messagesState}
        selectedUser={selectedUser} // selectedUser가 null일 수 있음
        sendMessage={sendMessage}
        isMobile={isMobile}
      />
    </div>
  );
}
