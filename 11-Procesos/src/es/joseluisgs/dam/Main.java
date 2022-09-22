package es.joseluisgs.dam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String IPADDRESS_PATTERN = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])"
            + "\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])"
            + "\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])"
            + "\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])";

    private static Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
    private static Matcher matcher;

    public static void main(String[] args) {

        Main obj = new Main();

        String domainName = "as.com";
        String command = "host -t a " + domainName;

        String output = obj.executeCommand(command);

        //System.out.println(output);

        List<String> list = obj.getIpAddress(output);

        if (list.size() > 0) {
            System.out.printf("%s con dirección: %n", domainName);
            for (String ip : list) {
                System.out.println(ip);
            }
        } else {
            System.out.printf("%s no tiene dirección. %n", domainName);
        }

    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

    public List<String> getIpAddress(String msg) {

        List<String> ipList = new ArrayList<String>();

        if (msg == null || msg.equals(""))
            return ipList;

        matcher = pattern.matcher(msg);
        while (matcher.find()) {
            ipList.add(matcher.group(0));
        }

        return ipList;
    }
}

