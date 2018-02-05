package org.everythingjboss.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.InvalidPayloadException;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.everythingjboss.inventory.Inventory;
import org.everythingjboss.inventory.ObjectFactory;
import org.everythingjboss.inventory.Product;

public class ProductAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldEx, Exchange newEx) {
        try {
            if(oldEx == null) {
                    Product p = newEx.getIn().getMandatoryBody(Product.class);
                    ObjectFactory of = new ObjectFactory();
                    Inventory inventory = of.createInventory();
                    List<Product> products = new ArrayList<Product>();
                    products.add(p);
                    inventory.setProduct(products);
                    newEx.getIn().setBody(inventory);
                    return newEx;
            } else {
                Inventory inventory = oldEx.getIn().getBody(Inventory.class);
                Product p = newEx.getIn().getMandatoryBody(Product.class);
                inventory.getProduct().add(p);
                return oldEx;
            }
        }
        catch(InvalidPayloadException ipe) {
            ipe.printStackTrace();
        }
        
        return null;
    }

}
