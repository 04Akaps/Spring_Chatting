import { cookies } from "next/headers";
import { ChatLayout } from "@/components/chat/chat-layout";
import Link from "next/link";
import { useRouter } from "next/router";
import { useEffect } from "react";

export default function Home() {
  const router = useRouter();

  const layout = cookies().get("react-resizable-panels:layout");
  const defaultLayout = layout ? JSON.parse(layout.value) : undefined;

  useEffect(() => {
    const authCookie = cookies().get("auth");
    if (!authCookie) {
      router.push("/login");
    }
  }, [router]);

  if (typeof window !== "undefined" && !cookies().get("auth")) {
    return <div>Loading...</div>;
  }

  return (
    // TODO 로그인 유무 처리
    <main className="flex h-[calc(100dvh)] flex-col items-center justify-center p-4 md:px-24 py-32 gap-4">
      <div className="flex justify-between max-w-5xl w-full items-center">
        <Link href="#" className="text-4xl font-bold text-gradient">
          Spring을 활용하여 직접 구현하는 Chatting 플랫폼
        </Link>
      </div>

      <div className="z-10 border rounded-lg max-w-5xl w-full h-full text-sm lg:flex">
        <ChatLayout defaultLayout={defaultLayout} navCollapsedSize={8} />
      </div>
    </main>
  );
}
