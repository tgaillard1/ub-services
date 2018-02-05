package org.everythingjboss.services;

import java.util.HashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.everythingjboss.inventory.Product;

public class PriceUpdateProcessor implements Processor {
    
    HashMap<String,Double> priceMap = new HashMap<>(); 
    
    public PriceUpdateProcessor() {
        priceMap.put("A", 50.0);
        priceMap.put("C", 70.75);
    }
    
    @Override
    public void process(Exchange exchange) throws Exception {
        Product p = exchange.getIn().getBody(Product.class);
        p.setPrice(priceMap.get(p.getName()).toString());
    }

}
