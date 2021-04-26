/*     */ package socket;
/*     */ 
/*     */ import gui.MainFrame;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Socket;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ 
/*     */ public class Client
/*     */ {
/*     */   public Socket client;
/*     */   public String name;
/*     */   short namelen;
/*  18 */   byte[] buffer = new byte[65536];
/*     */   static boolean type = false;
/*     */   InputStream input;
/*     */   OutputStream output;
/*     */   ByteBuffer btf;
/*     */   ByteBuffer recvbtf;
/*     */   
/*     */   public boolean connect(String host, int port) {
/*     */     try {
/*  27 */       this.client = new Socket();
/*  28 */       this.client.connect(new InetSocketAddress(host, port));
/*  29 */       this.input = this.client.getInputStream();
/*  30 */       this.output = this.client.getOutputStream();
/*  31 */       this.btf = ByteBuffer.allocate(65536);
/*  32 */       this.namelen = (short)this.name.getBytes().length;
/*  33 */       sendstr("nihao", (byte)0);
/*     */       
/*  35 */       int index = this.input.read(this.buffer);
/*  36 */       this.recvbtf = ByteBuffer.wrap(this.buffer, 0, index);
/*  37 */       byte cmd = this.recvbtf.get();
/*  38 */       short namelen = this.recvbtf.getShort();
/*  39 */       byte[] names = new byte[namelen];
/*  40 */       this.recvbtf.get(names);
/*  41 */       long time = this.recvbtf.getLong();
/*  42 */       short len = this.recvbtf.getShort();
/*  43 */       byte[] byt = new byte[len];
/*  44 */       this.recvbtf.get(byt);
/*  45 */       if (!(new String(byt, "utf-8")).equals("ok"))
/*     */       {
/*  47 */         this.client.close();
/*  48 */         return false;
/*     */       }
/*     */     
/*  51 */     } catch (IOException e) {
/*  52 */       System.out.println("用户连接失败..");
/*  53 */       return false;
/*     */     } 
/*  55 */     System.out.println("用户连接.." + host + ":" + port);
/*  56 */     return true;
/*     */   }
/*     */   
/*     */   public void start() {
/*  60 */     recvthread thread = new recvthread();
/*  61 */     thread.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public void close() { System.out.println("用户端关闭连接.."); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendstr(String a, byte cmd) {
/*  72 */     long time = System.currentTimeMillis();
/*  73 */     byte[] msg = null;
/*     */     try {
/*  75 */       msg = a.getBytes("utf-8");
/*  76 */     } catch (UnsupportedEncodingException e1) {
/*     */       
/*  78 */       e1.printStackTrace();
/*     */     } 
/*  80 */     short len = (short)msg.length;
/*  81 */     Msg ms = new Msg();
/*  82 */     ms.setCmd(cmd);
/*  83 */     ms.setLen(len);
/*  84 */     ms.setMsg(msg);
/*  85 */     ms.setTime(time);
/*     */     try {
/*  87 */       ms.setName(this.name.getBytes("utf-8"));
/*  88 */     } catch (UnsupportedEncodingException e) {
/*     */       
/*  90 */       e.printStackTrace();
/*     */     } 
/*  92 */     ms.setNamelen(this.namelen);
/*  93 */     sendstr(ms);
/*     */   }
/*     */   
/*     */   public void sendstr(Msg msg) {
/*  97 */     int start = this.btf.position();
/*  98 */     System.out.println("start=" + start);
/*  99 */     this.btf.put(msg.getCmd());
/* 100 */     this.btf.putShort(msg.getNamelen());
/* 101 */     this.btf.put(msg.getName());
/* 102 */     this.btf.putLong(msg.getTime());
/* 103 */     System.out.println("发送数据time=" + msg.getTime());
/* 104 */     this.btf.putShort(msg.getLen());
/* 105 */     System.out.println("发送数据length=" + msg.getLen());
/* 106 */     System.out.println("发送数据msg=" + new String(msg.getMsg()));
/* 107 */     this.btf.put(msg.getMsg());
/* 108 */     int off = this.btf.position();
/* 109 */     System.out.println("off=" + off);
/*     */     
/* 111 */     byte[] imfor = new byte[off - start];
/* 112 */     System.out.println(imfor.length);
/* 113 */     this.btf.position(start);
/* 114 */     this.btf.get(imfor);
/*     */ 
/*     */     
/*     */     try {
/* 118 */       this.output.write(imfor);
/* 119 */     } catch (IOException e) {
/*     */       
/* 121 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Msg recv() {
/* 126 */     int index = 0;
/*     */     try {
/* 128 */       index = this.input.read(this.buffer);
/* 129 */     } catch (IOException e) {
/* 130 */       e.printStackTrace();
/*     */     } 
/* 132 */     System.out.println("index=" + index);
/*     */ 
/*     */     
/* 135 */     this.recvbtf = ByteBuffer.wrap(this.buffer, 0, index);
/* 136 */     int start = this.recvbtf.position();
/* 137 */     System.out.println("recvstart=" + start);
/* 138 */     Msg msg = new Msg();
/* 139 */     byte cmd = this.recvbtf.get();
/* 140 */     short namelen = this.recvbtf.getShort();
/* 141 */     byte[] names = new byte[namelen];
/* 142 */     this.recvbtf.get(names);
/* 143 */     long time = this.recvbtf.getLong();
/* 144 */     short len = this.recvbtf.getShort();
/* 145 */     msg.setCmd(cmd);
/* 146 */     msg.setNamelen(namelen);
/* 147 */     msg.setName(names);
/* 148 */     msg.setTime(time);
/* 149 */     msg.setLen(len);
/* 150 */     byte[] byt = new byte[len];
/* 151 */     this.recvbtf.get(byt);
/* 152 */     msg.setMsg(byt);
/* 153 */     System.out.println("off=" + this.recvbtf.position());
/* 154 */     System.out.println("接收数据time=" + msg.getTime());
/* 155 */     System.out.println("接收数据length=" + msg.getLen());
/* 156 */     return msg;
/*     */   }
/*     */   
/*     */   public class recvthread
/*     */     extends Thread
/*     */   {
/*     */     public void run() {
/*     */       while (true) {
/* 164 */         Msg ms = Client.this.recv();
/* 165 */         if (ms.cmd == 1) {
/*     */           
/* 167 */           System.out.println("关闭");
/*     */           return;
/*     */         } 
/* 170 */         MainFrame.model.addElement(ms);
/*     */         try {
/* 172 */           System.out.println("接收数据" + new String(ms.getMsg(), "utf-8"));
/* 173 */         } catch (UnsupportedEncodingException e) {
/* 174 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\xianzhikun\Desktop\client - 副本\client.jar!\socket\Client.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */