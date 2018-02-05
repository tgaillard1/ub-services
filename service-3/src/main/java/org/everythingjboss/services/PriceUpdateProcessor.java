package org.everythingjboss.services;

import java.util.HashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.everythingjboss.inventory.Product;

public class PriceUpdateProcessor implements Processor {
    
    HashMap<String,Double> priceMap = new HashMap<>(); 
    
    public PriceUpdateProcessor() {
        priceMap.put("B", 11.47);
        priceMap.put("D", 62.14);
    }
    
    @Override
    public void process(Exchange exchange) throws Exception {
        Product p = exchange.getIn().getBody(Product.class);
        p.setPrice(priceMap.get(p.getName()).toString());
    }

}
