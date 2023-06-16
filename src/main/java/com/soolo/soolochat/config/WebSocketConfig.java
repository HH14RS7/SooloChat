package com.soolo.soolochat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.soolo.soolochat.handler.MyWebSocketHandler;
import com.soolo.soolochat.handler.StompHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	private final StompHandler stompHandler; // jwt 인증
	private final MyWebSocketHandler myWebSocketHandler; // 추가한 WebSocket 핸들러

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(stompHandler);
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/sub");
		config.setApplicationDestinationPrefixes("/pub");
	}
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws-stomp").setAllowedOrigins("http://localhost:8080", "http://localhost:3000", "http://3.34.14.45:8080", "http://3.34.14.45:80", "http://localhost:8081", "http://im-soolo.shop", "https://im-soolo.shop", "http://www.im-soolo.shop", "https://www.im-soolo.shop", "http://im-soolo.com", "https://im-soolo.com", "https://www.im-soolo.com", "http://www.im-soolo.com", "https://soolo-fe.vercel.app")
			.withSockJS();
	}


}
