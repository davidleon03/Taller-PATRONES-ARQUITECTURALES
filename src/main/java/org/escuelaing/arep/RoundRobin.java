package org.escuelaing.arep;

import spark.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.Spark.*;

public class RoundRobin {
    private static String datos;
    private static final String USER_AGENT = "Mozilla/5.0";
    public static void main(String[] args) throws IOException {
        staticFiles.location("/public");
        get("/inicio", (req, res) -> {
            res.redirect("/index.html");
            return null;
        });
        post("/inicio", (req,res) -> getPost(req));
        get("/inicio/get", (req,res) -> getDatos(req));
    }

    private static String getDatos(Request req) {
        return datos;
    }

    private static String getPost(Request req) throws IOException {
        String busqueda = req.body();
        System.out.println(busqueda);
        URL uri = new URL("http://"+ getRoundRobin() + ":4567/insert/" + busqueda.substring(1, busqueda.length()-1));
        HttpURLConnection con = (HttpURLConnection) uri.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader( con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("------------------------");
            System.out.println(response.toString());
            datos = response.toString();
            return response.toString();
        } else {
            return "GET no sirve";
        }
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
}