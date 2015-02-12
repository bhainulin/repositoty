package org.shop.context.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ShopContextListener implements ApplicationListener<ContextRefreshedEvent> {

    //@Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(event);
    }
}
