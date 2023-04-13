package ru.geekbrains.wnteredshop.core.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedDeque;

@Component
public class EventPool {

    private Listener listener;
    private ConcurrentLinkedDeque<Event> events;
    private Thread loop;



    @PostConstruct
    public void initial() throws Exception {
        events=new ConcurrentLinkedDeque<>();
        listener = new ProductListener();
        loop = new Thread(this::process);
        loop.setDaemon(true);
        loop.start();

    }



    public void process()  {
        while (true){
            try {
                Event event = events.poll();
                if(event!= null) {
                    listener.onEventReceived(event);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void publishEvent(Event event){
        events.add(event);
    }
}
