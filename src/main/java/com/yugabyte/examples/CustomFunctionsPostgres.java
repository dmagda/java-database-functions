package com.yugabyte.examples;

import org.postgresql.pljava.annotation.Function;

/**
 * A class with sample functions for Postgres.
 * 
 * The @Function annotation declares that the function should be available
 * from SQL, so a pljava.ddr file will be added to the jar, containing the SQL
 * commands to make that happen.
 * 
 * If you wish to add your function, then follow this guide:
 * https://tada.github.io/pljava/use/hello.html
 */
public class CustomFunctionsPostgres {
    public static void main(String[] args) {
        postgresSayHi();
    }

    /**
     * The Hello World function that returns information about the runtime.
     */
    @Function
    public static void postgresSayHi() {
        System.out.println("Hey, this Java function is executed by Postgres");

        System.out.println("This is my environment:");

        System.out.println("    OS -> " + System.getProperty("os.name"));
        System.out.println("    OS version -> " + System.getProperty("os.version"));
        System.out.println("    OS architecture -> " + System.getProperty("os.arch"));

        System.out.println();

        System.out.println("    Java version -> " + System.getProperty("java.version"));

        System.out.println();

        System.out.println("    Total memory -> " + Runtime.getRuntime().totalMemory());
        System.out.println("    Free memory -> " + Runtime.getRuntime().freeMemory());
        System.out.println("    Max memory -> " + Runtime.getRuntime().maxMemory());
    }
}
