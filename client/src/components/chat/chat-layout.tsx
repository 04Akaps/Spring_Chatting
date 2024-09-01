"use client";

import React, { useEffect, useState } from "react";
import {
  ResizableHandle,
  ResizablePanel,
  ResizablePanelGroup,
} from "@/components/ui/resizable";
import { cn } from "@/lib/utils";
import { Sidebar } from "../sidebar";
import { Chat } from "./chat";
import { User } from "@/app/data";
import api from "@/lib/axios";
import { redirect } from "next/navigation";

interface ChatLayoutProps {
  defaultLayout: number[] | undefined;
  defaultCollapsed?: boolean;
  navCollapsedSize: number;
}

function getCookie(name: string): string | undefined {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop()?.split(";").shift();
}

export function ChatLayout({
  defaultLayout = [320, 480],
  defaultCollapsed = false,
  navCollapsedSize,
}: ChatLayoutProps) {
  const [connectedUsers, setConnectedUsers] = React.useState<User[]>([]);
  const [isCollapsed, setIsCollapsed] = React.useState(defaultCollapsed);
  const [selectedUser, setSelectedUser] = React.useState<User | null>(null);
  const [me, setMe] = React.useState<User>();

  useEffect(() => {
    const authCookie = getCookie("auth");

    if (!authCookie) {
      redirect("/login");
    }

    const verifyAuthToken = async () => {
      const result = await api.get(`/api/v1/auth/verify-token/${authCookie}`);
      setMe(result.data);
    };

    verifyAuthToken();

    return () => {};
  }, []);

  return (
    <ResizablePanelGroup
      direction="horizontal"
      onLayout={(sizes: number[]) => {
        document.cookie = `react-resizable-panels:layout=${JSON.stringify(
          sizes
        )}`;
      }}
      className="h-full items-stretch"
    >
      <ResizablePanel
        defaultSize={defaultLayout[0]}
        collapsedSize={navCollapsedSize}
        collapsible={true}
        minSize={24}
        maxSize={30}
        onCollapse={() => {
          setIsCollapsed(true);
          document.cookie = `react-resizable-panels:collapsed=${JSON.stringify(
            true
          )}`;
        }}
        onExpand={() => {
          setIsCollapsed(false);
          document.cookie = `react-resizable-panels:collapsed=${JSON.stringify(
            false
          )}`;
        }}
        className={cn(
          isCollapsed &&
            "min-w-[50px] md:min-w-[70px] transition-all duration-300 ease-in-out"
        )}
      >
        <Sidebar
          isCollapsed={isCollapsed}
          links={connectedUsers}
          setConnectedUsers={setConnectedUsers}
          setSelectedUser={setSelectedUser}
        />
      </ResizablePanel>

      {selectedUser && (
        <>
          <ResizableHandle withHandle />

          <ResizablePanel defaultSize={defaultLayout[1]} minSize={30}>
            <Chat
              selectedUser={selectedUser}
              setSelectedUser={setSelectedUser}
            />
          </ResizablePanel>
        </>
      )}
    </ResizablePanelGroup>
  );
}
