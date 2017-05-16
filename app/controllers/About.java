package controllers;

import play.*;
import play.mvc.*;

/**
 * Controller class to render the abouts page
 */
public class About extends Controller {
    /**
     * Method to log and render the about.html page
     */
    public static void index() {
        Logger.info("Rendering about");
        render("about.html");
    }
}
