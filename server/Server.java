/*    */ package server;
/*    */ 
/*    */ import clientport.ClientPort;
/*    */ import gui.MainFrame;
/*    */ import gui.Msg;
/*    */ import java.io.IOException;
/*    */ import java.net.ServerSocket;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class Server
/*    */ {
/*    */   public static ServerSocket server;
/*    */   public static ServerThread thread;
/* 14 */   public static int num = 0;
/*    */   
/*    */   public static boolean createconnect(int port) {
/*    */     try {
/* 18 */       server = new ServerSocket(port);
/* 19 */     } catch (IOException e) {
/*    */       
/* 21 */       e.printStackTrace();
/* 22 */       System.out.println("创建端口号：" + port + "失败");
/* 23 */       MainFrame.model.addElement(new Msg((byte)-1, System.currentTimeMillis(), "创建端口号：" + port + " 失败"));
/* 24 */       return false;
/*    */     } 
/* 26 */     System.out.println("启动端口号：" + port);
/* 27 */     MainFrame.model.addElement(new Msg((byte)-1, System.currentTimeMillis(), "创建端口号：" + port + " 成功"));
/* 28 */     return true;
/*    */   }
/* 30 */   public static ArrayList<ClientPort> clients = new ArrayList();
/*    */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\server\server.jar!\server\Server.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */