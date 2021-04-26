/*     */ package clientport;
/*     */ 
/*     */ import gui.MainFrame;
/*     */ import gui.Msg;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.Socket;
/*     */ import java.nio.ByteBuffer;
/*     */ import server.ForwardCenter;
/*     */ import server.Server;
/*     */ 
/*     */ public class ClientPort extends Thread {
/*     */   public Socket client;
/*     */   byte[] msgdata;
/*     */   
/*     */   public ClientPort(Socket socket) {
/*  19 */     this.msgdata = new byte[65536];
/*     */ 
/*     */     
/*  22 */     this.name = "";
/*     */     
/*  24 */     this.client = socket;
/*     */     try {
/*  26 */       this.input = this.client.getInputStream();
/*  27 */       this.output = this.client.getOutputStream();
/*  28 */     } catch (IOException e) {
/*  29 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   InputStream input; OutputStream output; String name;
/*     */   public void run() {
/*     */     while (true) {
/*     */       byte cmd;
/*  36 */       int index = 0;
/*     */       
/*     */       try {
/*  39 */         index = this.input.read(this.msgdata);
/*  40 */         cmd = this.msgdata[0];
/*  41 */       } catch (IOException e) {
/*  42 */         e.printStackTrace();
/*  43 */         System.out.println("连接关闭..");
/*     */         
/*  45 */         Server.clients.remove(this);
/*  46 */         Server.num--;
/*  47 */         ForwardCenter.init();
/*  48 */         MainFrame.model.addElement(new Msg((byte)-1, System.currentTimeMillis(), "连接关闭，当前连接数量：" + Server.num));
/*     */         return;
/*     */       } 
/*  51 */       if (cmd == 0) {
/*     */         
/*     */         try {
/*  54 */           dosys(0, index); continue;
/*  55 */         } catch (UnsupportedEncodingException e) {
/*     */           
/*  57 */           e.printStackTrace();
/*     */           continue;
/*     */         } 
/*     */       }
/*  61 */       if (cmd == 1) {
/*  62 */         doclose(0, index);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/*  67 */       System.out.println("port=" + this.client.getPort());
/*  68 */       System.out.println("index=" + index);
/*  69 */       ForwardCenter.sendall(this.msgdata, 0, index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dosys(int start, int off) throws UnsupportedEncodingException {
/*  77 */     MainFrame.model.addElement(new Msg((byte)-1, System.currentTimeMillis(), "客户端尝试连接"));
/*  78 */     ByteBuffer recvbtf = ByteBuffer.wrap(this.msgdata, start, off);
/*  79 */     byte cmd = recvbtf.get();
/*  80 */     short namelen = recvbtf.getShort();
/*  81 */     byte[] names = new byte[namelen];
/*  82 */     recvbtf.get(names);
/*  83 */     String cmdstr = null;
/*  84 */     for (ClientPort cli : Server.clients) {
/*     */       
/*     */       try {
/*  87 */         if (cli.name.equals(new String(names, "utf-8"))) {
/*     */           
/*  89 */           cmdstr = "notok";
/*  90 */           System.out.println("连接失败..");
/*  91 */           Server.clients.remove(this);
/*  92 */           ForwardCenter.init();
/*  93 */           Server.num--;
/*  94 */           MainFrame.model.addElement(new Msg((byte)-1, System.currentTimeMillis(), "检测到重复用户名，拒绝连接！！当前连接数量" + Server.num));
/*     */           break;
/*     */         } 
/*  97 */       } catch (UnsupportedEncodingException e) {
/*     */         
/*  99 */         e.printStackTrace();
/*     */       } 
/* 101 */       System.out.println("当前连接数量:" + Server.num);
/* 102 */       cmdstr = "ok";
/*     */     } 
/* 104 */     if (cmdstr.equals("ok")) {
/*     */       
/* 106 */       this.name = new String(names, "utf-8");
/* 107 */       MainFrame.model.addElement(new Msg((byte)-1, System.currentTimeMillis(), "连接成功：当前连接数量：" + Server.num));
/*     */     } 
/* 109 */     long time = recvbtf.getLong();
/* 110 */     short len = recvbtf.getShort();
/* 111 */     byte[] byt = new byte[len];
/* 112 */     int msgstart = recvbtf.position();
/* 113 */     recvbtf.get(byt);
/*     */     
/* 115 */     ByteBuffer buff = ByteBuffer.allocate(1024);
/* 116 */     int on = buff.position();
/* 117 */     buff.put(cmd);
/* 118 */     buff.putShort(namelen);
/* 119 */     buff.put(names);
/* 120 */     buff.putLong(time);
/*     */     
/* 122 */     buff.putShort((short)cmdstr.getBytes("utf-8").length);
/* 123 */     buff.put(cmdstr.getBytes("utf-8"));
/* 124 */     int of = buff.position();
/*     */     
/* 126 */     byte[] data = new byte[of - on];
/* 127 */     buff.position(on);
/* 128 */     buff.get(data);
/*     */     
/*     */     try {
/* 131 */       this.client.getOutputStream().write(data);
/* 132 */     } catch (IOException e) {
/*     */       
/* 134 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doclose(int start, int off) throws UnsupportedEncodingException {
/*     */     try {
/* 140 */       this.output.write(this.msgdata, start, off);
/* 141 */     } catch (IOException e) {
/* 142 */       e.printStackTrace();
/*     */     } 
/* 144 */     System.out.println("关闭连接");
/* 145 */     Server.clients.remove(this);
/* 146 */     Server.num--;
/* 147 */     ForwardCenter.init();
/* 148 */     MainFrame.model.addElement(new Msg((byte)1, System.currentTimeMillis(), "连接关闭，当前连接数量：" + Server.num));
/*     */   }
/*     */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\server\server.jar!\clientport\ClientPort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */