/*    */ package server;
/*    */ 
/*    */ import clientport.ClientPort;
/*    */ import gui.MainFrame;
/*    */ import gui.Msg;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ForwardCenter
/*    */   extends Thread
/*    */ {
/*    */   static ArrayList<OutputStream> outputs;
/*    */   
/*    */   public static void init() {
/* 16 */     outputs = new ArrayList();
/* 17 */     for (ClientPort a : Server.clients) {
/*    */       
/*    */       try {
/* 20 */         outputs.add(a.client.getOutputStream());
/* 21 */       } catch (IOException e) {
/*    */         
/* 23 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void sendall(byte[] byts, int a, int off) {
/* 29 */     for (OutputStream output : outputs) {
/*    */       
/*    */       try {
/* 32 */         output.write(byts, a, off);
/* 33 */         System.out.println("转发成功");
/* 34 */         MainFrame.model.addElement(new Msg((byte)101, System.currentTimeMillis(), "转发消息成功.."));
/* 35 */       } catch (IOException e) {
/* 36 */         System.out.println("转发失败");
/* 37 */         MainFrame.model.addElement(new Msg((byte)101, System.currentTimeMillis(), "转发消息失败!!"));
/* 38 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\server\server.jar!\server\ForwardCenter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */