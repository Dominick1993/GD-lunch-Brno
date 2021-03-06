package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.repository.entity.DailyMenu;
import com.labuda.gdlunch.repository.entity.MenuItem;
import com.labuda.gdlunch.repository.entity.Restaurant;
import java.time.LocalDate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses daily menu from Vivobene Gusto restaurant
 */
public class VivobeneParser extends AbstractRestaurantWebParser implements DailyParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(VivobeneParser.class);

    /**
     * Constructor
     *
     * @param restaurant restaurant details
     */
    public VivobeneParser(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public DailyMenu parse() {
        DailyMenu result = new DailyMenu();
        result.setRestaurant(restaurant);
        result.setDate(LocalDate.now());

        try {
            Document document = Jsoup.connect(restaurant.getParserUrl()).get();
            Elements menu = document.select(".page-body .mainbar .main-col .row .bd .content");
            if (menu.size() > 0) {
                Elements courses = menu.first().getElementsByTag("tr");

                for (Element course : courses) {
                    Elements td = course.getElementsByTag("td");
                    if (td.size() == 2) {
                        result.getMenu().add(new MenuItem(
                                td.get(0).text(),
                                parsePrice(td.get(1).text())
                        ));
                    } else {
                        result.getMenu().add(new MenuItem(
                                td.get(0).text(),
                                0f
                        ));
                    }
                }
            }
        } catch (Exception e) {
            log.error("Parsing failed", e);
        }

        return result;
    }
}
