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
    public static String postgresSayHi() {
        StringBuilder builder = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");

        builder.append("Hey, we've just update the function!");
        builder.append(lineSeparator);

        builder.append("This is my environment:").append(lineSeparator);

        builder.append("    OS -> " + System.getProperty("os.name")).append(lineSeparator);
        builder.append("    OS version -> " + System.getProperty("os.version")).append(lineSeparator);
        builder.append("    OS architecture -> " + System.getProperty("os.arch")).append(lineSeparator);

        builder.append(lineSeparator);

        builder.append("    Java version -> " + System.getProperty("java.version")).append(lineSeparator);

        builder.append(lineSeparator);

        builder.append("    Total memory -> " + Runtime.getRuntime().totalMemory()).append(lineSeparator);
        builder.append("    Free memory -> " + Runtime.getRuntime().freeMemory()).append(lineSeparator);
        builder.append("    Max memory -> " + Runtime.getRuntime().maxMemory()).append(lineSeparator);

        return builder.toString();
    }
}
