/*    */ package server;
/*    */ 
/*    */ import clientport.ClientPort;
/*    */ import gui.MainFrame;
/*    */ import gui.Msg;
/*    */ import java.io.IOException;
/*    */ import java.net.Socket;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerThread
/*    */   extends Thread
/*    */ {
/*    */   public void run() {
/* 16 */     System.out.println("服务端启动..");
/* 17 */     MainFrame.model.addElement(new Msg((byte)-1, System.currentTimeMillis(), "服务启动.."));
/* 18 */     while (!Server.server.isClosed()) {
/*    */       
/* 20 */       Socket client = null;
/*    */       try {
/* 22 */         client = Server.server.accept();
/* 23 */         ClientPort cli = new ClientPort(client);
/* 24 */         Server.clients.add(cli);
/* 25 */         ForwardCenter.init();
/* 26 */         Server.num++;
/*    */         
/* 28 */         cli.start();
/* 29 */       } catch (IOException e) {
/* 30 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\server\server.jar!\server\ServerThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */