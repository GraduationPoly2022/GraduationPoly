package com.shop.helper;

import org.springframework.stereotype.Service;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.io.*;
import java.net.Socket;
import java.util.*;


@Service
public class CheckMail {
    private static int hear(BufferedReader in) throws IOException {
        String line;
        int res = 0;
        while ((line = in.readLine()) != null) {
            String pfx = line.substring(0, 3);
            try {
                res = Integer.parseInt(pfx);
            } catch (Exception ex) {
                res = -1;
            }
            if (line.charAt(3) != '-') break;
        }
        return res;
    }

    private static void say(BufferedWriter wr, String text)
            throws IOException {
        wr.write(text + "\r\n");
        wr.flush();
    }

    private static ArrayList<?> getMX(String hostName)
            throws NamingException {
        // Perform a DNS lookup for MX records in the domain
        Hashtable<String, String> env = new Hashtable<>();
        env.put("java.naming.factory.initial",
                "com.sun.jndi.dns.DnsContextFactory");
        DirContext dirContext = new InitialDirContext(env);
        Attributes attrs = dirContext.getAttributes
                (hostName, new String[]{"MX"});
        Attribute attr = attrs.get("MX");
        // if we don't have an MX record, try the machine itself
        if ((attr == null) || (attr.size() == 0)) {
            attrs = dirContext.getAttributes(hostName, new String[]{"A"});
            attr = attrs.get("A");
            if (attr == null)
                throw new NamingException
                        ("No match for name '" + hostName + "'");
        }
        // Huzzah! we have machines to try. Return them as an array list
        // NOTE: We SHOULD take the preference into account to be absolutely
        //   correct. This is left as an exercise for anyone who cares.
        ArrayList<String> res = new ArrayList<>();
        NamingEnumeration<?> en = attr.getAll();
        while (en.hasMore()) {
            String x = (String) en.next();
            String[] f = x.split(" ");
            if (f[1].endsWith("."))
                f[1] = f[1].substring(0, (f[1].length() - 1));
            res.add(f[1]);
        }
        return res;
    }

    private static boolean isAddressValid(String address) {
        // Find the separator for the domain name
        int pos = address.indexOf('@');
        // If the address does not contain an '@', it's not valid
        if (pos == -1) return false;
        // Isolate the domain/machine name and get a list of mail exchangers
        String domain = address.substring(++pos);
        ArrayList<?> mxList = null;
        try {
            mxList = getMX(domain);
        } catch (NamingException ex) {
            return false;
        }
        // Just because we can send mail to the domain, doesn't mean that the
        // address is valid, but if we can't, it's a sure sign that it isn't
        if (mxList.size() == 0) return false;
        // Now, do the SMTP validation, try each mail exchanger until we get
        // a positive acceptance. It *MAY* be possible for one MX to allow
        // a message [store and forwarder for example] and another [like
        // the actual mail server] to reject it. This is why we REALLY ought
        // to take the preference into account.
        boolean valid = false;
        Socket skt = null;
        BufferedReader rdr = null;
        BufferedWriter wtr = null;
        for (Object o : mxList) {

            try {
                int res;
                skt = new Socket((String) o, 25);
                rdr = new BufferedReader
                        (new InputStreamReader(skt.getInputStream()));
                wtr = new BufferedWriter
                        (new OutputStreamWriter(skt.getOutputStream()));
                res = hear(rdr);
                if (res != 220) throw new Exception("Invalid header");
                say(wtr, "EHLO orbaker.com");
                res = hear(rdr);
                if (res != 250) throw new Exception("Not ESMTP");
                // validate the sender address
                say(wtr, "MAIL FROM: <tim@orbaker.com>");
                res = hear(rdr);
                if (res != 250) throw new Exception("Sender rejected");
                say(wtr, "RCPT TO: <" + address + ">");
                res = hear(rdr);
                // be polite
                say(wtr, "RSET");
                hear(rdr);
                say(wtr, "QUIT");
                hear(rdr);
                if (res != 250)
                    throw new Exception("Address is not valid!");
                valid = true;
            } catch (Exception ex) {
                // Do nothing but try next host
                valid = false;
            } finally {
                try {
                    if (rdr != null) {
                        rdr.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (wtr != null) {
                        wtr.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (skt != null) {
                        skt.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return valid;
    }

    public static synchronized Map<String, Boolean> emailExists(List<String> data) {
        Map<String, Boolean> booleanMap = new HashMap<>();
        List<String> distinctArray = new ArrayList<>();
        for (String datum : data) {
            if (!distinctArray.contains(datum)) {
                distinctArray.add(datum);
            }
        }
        for (String testDatum : distinctArray) {
            booleanMap.put(testDatum, isAddressValid(testDatum));
        }
        return booleanMap;
    }

    public static synchronized Boolean emailExists(String data) {
        return isAddressValid(data);
    }
}
