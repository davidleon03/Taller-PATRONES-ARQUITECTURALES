package org.escuelaing.arep;

import spark.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.Spark.*;

public class RoundRobin {
    public static void main(String[] args) throws IOException {
        staticFiles.location("/public");
        get("/inicio", (req, res) -> {
            res.redirect("/public/index.html");
            return null;
        });
        post("/inicio", (req,res) -> getPost(req));
    }

    private static String getPost(Request req) throws MalformedURLException {
        String busqueda = req.body();
        String resultado = getAPI(busqueda, getRoundRobin());
        System.out.println(resultado);
        return "post sirve";
    }

    private static String getRoundRobin() {
        String[] ips = {"54.197.43.229", "3.235.250.144", "44.214.143.59"};
        Random r = new Random();
        return ips[r.nextInt(3)];
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    public static String getAPI(String busqueda, String RoundRobin) throws MalformedURLException, MalformedURLException {
        String uri = "https://"+ RoundRobin+ ":4567/insert/" + busqueda.substring(1, busqueda.length()-1);
        System.out.println(uri);
        URL api = new URL(uri);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(api.openStream()))) {
            String inputLine = reader.readLine();
            System.out.println(inputLine);
            return inputLine;
        } catch (IOException x) {
            System.err.println(x);
        }
        return null;
    }
}