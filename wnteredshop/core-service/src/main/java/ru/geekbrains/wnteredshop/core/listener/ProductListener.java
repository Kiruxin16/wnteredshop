package ru.geekbrains.wnteredshop.core.listener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ProductListener implements Listener{

    FileOutputStream out;

    public ProductListener() throws Exception{
        out=new FileOutputStream("product-log");
    }

    @Override
    public void onEventReceived(Event event) throws IOException {
        out.write(String.format("продукт с именем %S добавлен в магазин.\n",event.toString()).getBytes(StandardCharsets.UTF_8));
    }
}
