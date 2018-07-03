package util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class EmailSender {

	private static final String SENDER_ADDRESS = common.Contants.SENDER_ADDRESS;
	private static final String SENDER_PASSWORD = common.Contants.SENDER_PASSWORD;

  public static void sendMail(String address, String title, String text) throws Exception {

	  SMTPTransport t = null;

	  try {
      // プロパティの設定
      Properties props = System.getProperties();
      // ホスト
      props.put("mail.smtp.host", "smtp.gmail.com");
      // 認証（する）
      props.put("mail.smpt.auth", "true");
      // ポート指定（サブミッションポート）
      props.put("mail.smtp.port", "587");
      // STARTTLSによる暗号化（する）
      props.put("mail.smtp.starttls.enable", "true");

      // セッションの取得
      Session session = Session.getInstance(props);

      // MimeMessageの取得と設定
      Message msg = new MimeMessage(session);
      // 送信者設定
      msg.setFrom(new InternetAddress(SENDER_ADDRESS));
      // 宛先設定
      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address, false));
      // タイトル設定
      msg.setSubject(title);
      // 本文設定
      msg.setText(text);
      // 送信日時設定
      msg.setSentDate(new Date());

      // 送信
      t = (SMTPTransport) session.getTransport("smtp");

      t.connect("smtp.gmail.com", SENDER_ADDRESS, SENDER_PASSWORD);
      t.sendMessage(msg, msg.getAllRecipients());

    } finally {
    	if(t!=null)t.close();
    }
}
}