package com.epam.songs.activemq;

import com.epam.songs.api.SongController;
import com.epam.songs.api.clientmodels.SongClientModel;
import com.epam.songs.mappers.SongMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.Message;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.jms.JMSException;
import java.io.Serializable;

@Component
@Slf4j
public class CreateSongListener {
    @Autowired
    private SongController songController;

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    @JmsListener(destination = "${spring.activemq.queues.song-create}", containerFactory = "queueListenerFactory")
    @SendTo("${spring.activemq.queues.song-created}")
    @Retryable(value = Exception.class)
    public long receiveMessageFromQueue(Message jsonMessage) throws JMSException, JsonProcessingException {
        ActiveMQTextMessage message = (ActiveMQTextMessage) jsonMessage;
        SongActivemqModel songActivemqModel = objectMapper.readValue(message.getText(), SongActivemqModel.class);
        log.info("Received Save song: " + songActivemqModel + " from queue");
        SongClientModel songClientModel = songMapper.toClientModel(songActivemqModel);
        return songController.createSong(songClientModel);
    }
}
