package com.project2.util;

public interface EmailUtil {
	void sendSimpleMessage(String to, String subject, String text);
    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
}
