package org.escuelaing.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.util.Random;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.Spark.*;

public class RoundRobin {
    public static void main(String[] args) throws IOException {
        staticFiles.location("/public");
        get("/hello", (req, res) -> "Hello World");
        get("/inicio", (req, res) -> {
            res.redirect("/index.html");
            return null;
        });
    }


    private static String getRoundRobin() {
        String[] ips = {"52.207.82.140", "18.207.103.100", "100.24.98.240"};
        Random r = new Random();
        return ips[r.nextInt(3)];
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4566;
    }
}