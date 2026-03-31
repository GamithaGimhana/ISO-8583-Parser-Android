package com.example.iso8583prase;

import android.util.Log;

import org.jpos.iso.ISOMsg;

public class JPosParser {

    public ISOMsg parse(String message) throws Exception {

        String isoPayload = extractIsoPayload(message);

        SimplePackager packager = new SimplePackager();

        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);

        Log.d("ISO_DEBUG", "RAW: " + message);
        Log.d("ISO_DEBUG", "ISO PAYLOAD: " + isoPayload);
        try {
            isoMsg.unpack(hexStringToByteArray(isoPayload));
        } catch (Exception e) {
            throw new Exception("Unpack failed. Possibly wrong header or packager: " + e.getMessage());
        }

        return isoMsg;
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if (len % 2 != 0) {
            s = "0" + s;
            len++;
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private String extractIsoPayload(String message) {
        if (message.length() < 20) return message;

        try {
            int length = Integer.parseInt(message.substring(0, 4), 16);

            String tpdu = message.substring(4, 14);

            if (tpdu.startsWith("60") || tpdu.startsWith("62") || tpdu.startsWith("68")) {
                return message.substring(14);
            }

        } catch (Exception ignored) {}
        return message;
    }
}
