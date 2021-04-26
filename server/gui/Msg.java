/*    */ package gui;
/*    */ 
/*    */ 
/*    */ public class Msg
/*    */ {
/*    */   public byte cmd;
/*    */   public String msg;
/*    */   long time;
/*    */   
/* 10 */   public byte getCmd() { return this.cmd; }
/*    */ 
/*    */   
/* 13 */   public void setCmd(byte cmd) { this.cmd = cmd; }
/*    */ 
/*    */   
/* 16 */   public String getMsg() { return this.msg; }
/*    */ 
/*    */   
/* 19 */   public void setMsg(String msg) { this.msg = msg; }
/*    */ 
/*    */   
/* 22 */   public long getTime() { return this.time; }
/*    */ 
/*    */   
/* 25 */   public void setTime(long time) { this.time = time; }
/*    */ 
/*    */   
/*    */   public Msg(byte a, long b, String c) {
/* 29 */     this.cmd = a;
/* 30 */     this.time = b;
/* 31 */     this.msg = c;
/*    */   }
/*    */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\server\server.jar!\gui\Msg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */