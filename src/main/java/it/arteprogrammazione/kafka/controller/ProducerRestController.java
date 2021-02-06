package it.arteprogrammazione.kafka.controller;

import it.arteprogrammazione.kafka.model.Person;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerRestController {

    private static final String TOPIC = "test-topic";
    private final KafkaTemplate<String, Person> kafkaTemplate;

    @Autowired
    public ProducerRestController(KafkaTemplate<String, Person> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody final Person person) {
        kafkaTemplate.send(new ProducerRecord<String, Person>(TOPIC, person.getLastName(), person));
        return "Published successfully";
    }

}
