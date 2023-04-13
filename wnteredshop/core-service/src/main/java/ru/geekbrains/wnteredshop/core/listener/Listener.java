package ru.geekbrains.wnteredshop.core.listener;


import java.io.IOException;

public interface Listener {

    void onEventReceived(Event event) throws IOException;


}
