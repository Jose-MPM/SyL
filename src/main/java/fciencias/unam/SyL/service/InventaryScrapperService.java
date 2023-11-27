package fciencias.unam.SyL.service;

import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import fciencias.unam.SyL.entity.InventarioScrapped;

@Service
public class InventaryScrapperService {

    public List<InventarioScrapped> getScrapping(){
        try{
            File pageFile = new File("/home/rodrigo/Desktop/Repos/TDIaux/SyL/src/main/resources/page/Soriana.html");

            Document doc = Jsoup.parse(pageFile, "UTF-8");
            
            String productClasses1 = ".product-tile--wrapper.d-flex.list-item-product.pb-1";//check
            Elements products = doc.select(productClasses1);
            for(Element e: products)
                System.out.print(e.html());
            
            String priceClasses = ".mr-0.cart-price.price-plp.price-not-found.price-pdp.pr-0.font-size-14.font-size-14.cart-price-option-b";
            String nameClasses = ".link.plp-link.font-primary--medium.product-tile--link.ellipsis-product-name.font-size-16";
            String imageClasses = ".w-100.justify-content-center.is-new-plp-enabled";
            String imageClasses2 = "tile-image.content-visibility-auto.img-dimentions.is-new-plp-enabled.lazyloaded";
            
            List<InventarioScrapped> list = new ArrayList<>();

            InventarioScrapped inventarioScrapped = new InventarioScrapped();
            for(Element e: products){
                String price = e.select(priceClasses).first().text();
                price = price.replace("$","");
                list.add(
                    new InventarioScrapped(
                        e.select(nameClasses).first().text(),
                        Float.parseFloat(price),
                        e.select(imageClasses).first().select("img").first().attr("data-src")
                    )
                );
            }

            return list;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}
