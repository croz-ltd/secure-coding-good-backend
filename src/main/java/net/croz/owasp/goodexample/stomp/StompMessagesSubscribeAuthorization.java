package net.croz.owasp.goodexample.stomp;

import net.croz.owasp.goodexample.entity.AuthUser;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class StompMessagesSubscribeAuthorization implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
            final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            final AuthUser authUser = (AuthUser) auth.getPrincipal();

            final String destination = accessor.getDestination();
            // OWASP[81]
            if (!Objects.equals(String.format("/product/%d", authUser.getId()), destination)) {
                throw new IllegalArgumentException("Can not subscribe to topic");
            }
        }

        return message;
    }

}
