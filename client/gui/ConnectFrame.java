/*     */ package gui;
/*     */ 
/*     */ import guiFrame.SimpleFrame;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import socket.Client;
/*     */ 
/*     */ public class ConnectFrame
/*     */   extends SimpleFrame
/*     */ {
/*     */   public static ConnectFrame in;
/*     */   JTextField field;
/*     */   JTextField field2;
/*     */   JTextField field3;
/*     */   JButton bu;
/*     */   JLabel lab1;
/*     */   JLabel lab2;
/*     */   JLabel lab3;
/*     */   
/*     */   public ConnectFrame(String name) {
/*  27 */     super(name);
/*  28 */     MainFrame.client = new Client();
/*  29 */     setResizable(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void run(JPanel panel) {
/*  34 */     this.lab1 = new JLabel("服务机地址");
/*  35 */     this.lab2 = new JLabel("服务机端口");
/*  36 */     this.lab3 = new JLabel("设置用户名");
/*     */     
/*  38 */     this.lab1.setFont(new Font("微软雅黑", 1, 18));
/*  39 */     this.lab2.setFont(new Font("微软雅黑", 1, 18));
/*  40 */     this.lab3.setFont(new Font("微软雅黑", 1, 18));
/*     */     
/*  42 */     this.field = new JTextField();
/*  43 */     this.field2 = new JTextField();
/*  44 */     this.field3 = new JTextField();
/*     */     
/*  46 */     this.field.setFont(new Font("微软雅黑", 0, 18));
/*  47 */     this.field2.setFont(new Font("微软雅黑", 0, 18));
/*  48 */     this.field3.setFont(new Font("微软雅黑", 0, 18));
/*     */     
/*  50 */     this.field.setSize(new Dimension(450, 40));
/*  51 */     this.field2.setSize(new Dimension(450, 40));
/*  52 */     this.field3.setSize(new Dimension(450, 40));
/*     */     
/*  54 */     this.field.setText("127.0.0.1");
/*  55 */     this.field2.setText("8088");
/*     */ 
/*     */     
/*  58 */     this.bu = new JButton("连接聊天室");
/*  59 */     this.bu.setFont(new Font("微软雅黑", 1, 18));
/*     */     
/*  61 */     panel.add(this.field);
/*  62 */     panel.add(this.field2);
/*  63 */     panel.add(this.field3);
/*  64 */     panel.add(this.bu);
/*  65 */     panel.add(this.lab1);
/*  66 */     panel.add(this.lab2);
/*  67 */     panel.add(this.lab3);
/*     */     
/*  69 */     this.bu.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  73 */             String host = ConnectFrame.this.field.getText();
/*  74 */             String port = ConnectFrame.this.field2.getText();
/*  75 */             String name = ConnectFrame.this.field3.getText();
/*  76 */             if (port == null || port.equals(""))
/*     */               return; 
/*  78 */             if (host == null || host.equals(""))
/*     */               return; 
/*  80 */             if (name == null || name.equals(""))
/*     */               return; 
/*     */             try {
/*  83 */               int i = Integer.valueOf(port).intValue();
/*     */             }
/*  85 */             catch (Exception e1) {
/*     */               return;
/*     */             } 
/*     */ 
/*     */             
/*  90 */             MainFrame.client.name = name;
/*  91 */             boolean type = MainFrame.client.connect(host, Integer.valueOf(port).intValue());
/*  92 */             if (type) {
/*     */               
/*  94 */               if (MainFrame.frame == null)
/*  95 */                 MainFrame.frame = new MainFrame("聊天室"); 
/*  96 */               MainFrame.frame.setVisible(true);
/*  97 */               MainFrame.client.start();
/*  98 */               ConnectFrame.this.setVisible(false);
/*     */             }
/*     */             else {
/*     */               
/* 102 */               System.out.println("连接失败..");
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setbounds(int width, int height, int x, int y) {
/* 111 */     this.field.setBounds((width - this.field.getWidth()) / 2, 180, this.field.getWidth(), this.field.getHeight());
/* 112 */     this.field2.setBounds((width - this.field2.getWidth()) / 2, 260, this.field2.getWidth(), this.field2.getHeight());
/* 113 */     this.field3.setBounds((width - this.field2.getWidth()) / 2, 340, this.field3.getWidth(), this.field3.getHeight());
/* 114 */     this.lab1.setBounds(160, 185, 100, 30);
/* 115 */     this.lab2.setBounds(160, 265, 100, 30);
/* 116 */     this.lab3.setBounds(160, 345, 100, 30);
/* 117 */     this.bu.setBounds(400, 460, 150, 40);
/*     */   }
/*     */ }


/* Location:              C:\Users\xianzhikun\Desktop\client - 副本\client.jar!\gui\ConnectFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */