package controllers;

import play.Logger;
import play.mvc.Controller;

/**
 * Controller class to render the start page
 */
public class Start extends Controller {
    /**
     * Method to log and render the start.html view
     */
    public static void index() {
        Logger.info("Rendering Start");
        render("start.html");
    }
}
