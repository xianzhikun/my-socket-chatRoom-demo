/*    */ package socket;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Msg
/*    */ {
/*    */   byte cmd;
/*    */   short namelen;
/*    */   byte[] name;
/*    */   long time;
/*    */   short len;
/*    */   byte[] msg;
/*    */   
/* 16 */   public byte getCmd() { return this.cmd; }
/*    */ 
/*    */   
/* 19 */   public void setCmd(byte cmd) { this.cmd = cmd; }
/*    */ 
/*    */   
/* 22 */   public byte[] getName() { return this.name; }
/*    */ 
/*    */   
/* 25 */   public void setName(byte[] name) { this.name = name; }
/*    */ 
/*    */   
/* 28 */   public short getNamelen() { return this.namelen; }
/*    */ 
/*    */   
/* 31 */   public void setNamelen(short namelen) { this.namelen = namelen; }
/*    */ 
/*    */   
/* 34 */   public byte[] getMsg() { return this.msg; }
/*    */ 
/*    */   
/* 37 */   public void setMsg(byte[] msg) { this.msg = msg; }
/*    */ 
/*    */   
/* 40 */   public long getTime() { return this.time; }
/*    */ 
/*    */   
/* 43 */   public void setTime(long time) { this.time = time; }
/*    */ 
/*    */   
/* 46 */   public short getLen() { return this.len; }
/*    */ 
/*    */   
/* 49 */   public void setLen(short len) { this.len = len; }
/*    */ }


/* Location:              C:\Users\xianzhikun\Desktop\client - 副本\client.jar!\socket\Msg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */