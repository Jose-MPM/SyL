package fciencias.unam.SyL.service;

import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import fciencias.unam.SyL.entity.InventarioScrapped;
@Service
public class InventaryScrapperService {

    public List<InventarioScrapped> getScrapping(){
        try{
            File pageFile = new File("/home/rodrigo/Desktop/Repos/TDIaux/SyL/src/main/resources/page/Soriana.html");

            // Document doc = Jsoup.parse(pageFile, "UTF-8");
            Document doc = Jsoup.connect("https://www.soriana.com/frutas-y-verduras/")
                                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                                .header("Accept-Language", "*")
                                .get();
            // System.out.println("HTML================================");
            // System.out.println(doc.html());

            FileWriter file2 = new FileWriter("/home/rodrigo/Desktop/Repos/TDIaux/SyL/src/main/resources/page/aux.html");
            file2.write(doc.html());
            file2.close();

            

            
            // Elements prices = doc.select(".mr-0, .cart-price, .price-plp, price-not-found, .price-pdp, pr-0, .font-size-14, .font-size-14, .cart-price-option-b");
            // Elements names = doc.select(".link, .plp-link, .font-primary--medium, .product-tile--link, .ellipsis-product-name, .font-size-16");
            // Elements proof = doc.select(".link.plp-link.font-primary--medium.product-tile--link.ellipsis-product-name.font-size-16");
            
            
            String productClasses = ".tile-body.product-tile--body.w-100.p-0";
            String productClasses2 = ".product-tile--wrapper.d-flex.list-item-product.pb-1.slick-slide.slick-current.slick-active";//check
            Elements products = doc.select(productClasses2);
            for(Element e: products)
                System.out.print(e.html());

            
            // .tile-body, .product-tile--body, .w-100, .p-0 \\producto
            // tile-body product-tile--body w-100 p-0 \\producto 
            // .link, .plp-link, .font-primary--medium, .product-tile--link, .ellipsis-product-name, .font-size-16 \\name
            // link plp-link font-primary--medium product-tile--link ellipsis-product-name font-size-16 \\name
            // mr-0 cart-price price-plp price-not-found price-pdp pr-0  font-size-14   font-size-14 cart-price-option-b \\price
            // .product-tile--wrapper.d-flex.list-item-product.pb-1.slick-slide.slick-current.slick-active
            
            String priceClasses = ".mr-0.cart-price.price-plp.price-not-found.price-pdp.pr-0.font-size-14.font-size-14.cart-price-option-b";
            String nameClasses = ".link.plp-link.font-primary--medium.product-tile--link.ellipsis-product-name.font-size-16";
            String imageClasses = ".w-100.justify-content-center.is-new-plp-enabled";
            String imageClasses2 = "tile-image.content-visibility-auto.img-dimentions.is-new-plp-enabled.lazyloaded";
            // .w-100.justify-content-center.is-new-plp-enabled \\href
            // w-100 justify-content-center is-new-plp-enabled \\href
            String returnString = "";
            List<InventarioScrapped> list = new ArrayList<>();

            InventarioScrapped inventarioScrapped = new InventarioScrapped();
            for(Element e: products){
                // inventarioScrapped.setNombre(e.select(nameClasses).first().text());
                // String price = e.select(priceClasses).first().text();
                // price = price.replace("$","");
                // inventarioScrapped.setPrecio(Float.parseFloat(price));
                // inventarioScrapped.setPathImagen(e.select(imageClasses).first().select("img").first().attr("data-src"));
                // list.add(inventarioScrapped);

                // inventarioScrapped.setNombre(e.select(nameClasses).first().text());
                String price = e.select(priceClasses).first().text();
                price = price.replace("$","");
                // inventarioScrapped.setPrecio(Float.parseFloat(price));
                // inventarioScrapped.setPathImagen(e.select(imageClasses).first().select("img").first().attr("data-src"));

                System.out.print("Name:");
                System.out.println(e.select(nameClasses).first().text());
                System.out.print("Price:");
                System.out.println(Float.parseFloat(price));
                System.out.print("Path:");
                System.out.println(e.select(imageClasses).first().select("img").first().attr("data-src"));
                list.add(
                    new InventarioScrapped(
                        e.select(nameClasses).first().text(),
                        Float.parseFloat(price),
                        e.select(imageClasses).first().select("img").first().attr("data-src")
                    )
                );
            }
                // returnString = returnString+"/n("+e.select(nameClasses) + "," + e.select(priceClasses) + "," + e.select(imageClasses).first().select("img").first().attr("data-src")+ ")";

            return list;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}




// try {
//     File myObj = new File("filename.txt");
//     Scanner myReader = new Scanner(myObj);
//     while (myReader.hasNextLine()) {
//     String data = myReader.nextLine();
//     System.out.println(data);
//     }
//     myReader.close();
//     }catch (FileNotFoundException e) {
//       System.out.println("An error occurred.");
//       e.printStackTrace();
//     }