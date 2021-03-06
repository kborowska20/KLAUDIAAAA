import controller.EventController;
import dao.ConnectToDb;
import dao.EventDaoSQLite;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import  static spark.Spark.*;


public class Main {

    public static void main(String[] args) {
        EventDaoSQLite eventDaoSQLite = new EventDaoSQLite();
        System.out.print(eventDaoSQLite.getAll());
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Always add generic routes to the end
        get("/", EventController::renderProducts, new ThymeleafTemplateEngine());
        // Equivalent with above

        get("/index", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(EventController.renderProducts(req, res));
        });
    }
}