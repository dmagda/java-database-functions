package com.yugabyte.examples;

public class CustomFunctionsOracle {
    public static void main(String[] args) {
        oracleSayHi();
    }

    public static String oracleSayHi() {
        StringBuilder builder = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");

        builder.append("Hey, this Java function is executed by Oracle Database");
        builder.append(lineSeparator);

        builder.append("This is my environment:").append(lineSeparator);

        builder.append("    OS -> " + System.getProperty("os.name")).append(lineSeparator);
        builder.append("    OS version -> " + System.getProperty("os.version")).append(lineSeparator);
        builder.append("    OS architecture -> " + System.getProperty("os.arch")).append(lineSeparator);

        builder.append(lineSeparator);

        builder.append("    Java version -> " + System.getProperty("oracle.jserver.version")).append(lineSeparator);

        builder.append(lineSeparator);

        builder.append("    Total memory -> " + Runtime.getRuntime().totalMemory()).append(lineSeparator);
        builder.append("    Free memory -> " + Runtime.getRuntime().freeMemory()).append(lineSeparator);
        builder.append("    Max memory -> " + Runtime.getRuntime().maxMemory()).append(lineSeparator);

        return builder.toString();
    }
}
