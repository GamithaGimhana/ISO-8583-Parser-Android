package com.example.iso8583prase;

import org.jpos.iso.*;

public class SimplePackager extends ISOBasePackager {

    public SimplePackager() {
        ISOFieldPackager[] f = new ISOFieldPackager[129];

        // MTI and Bitmap
        f[0]  = new IFB_NUMERIC(4, "Message Type Id", true);
        f[1]  = new IFB_BITMAP(16, "Bit Map");

        // Transaction Data Elements
        f[2]  = new IFB_LLNUM(19, "Primary Acct. Num.", false);
        f[3]  = new IFB_NUMERIC(6, "Processing Code", true);
        f[4]  = new IFB_NUMERIC(12, "Amount, Trans.", true);
        f[7]  = new IFB_NUMERIC(10, "Transmission date/time", true);
        f[11] = new IFB_NUMERIC(6, "Systems Trace No", true);
        f[12] = new IFB_NUMERIC(6, "Time, Local Trans.", true);
        f[13] = new IFB_NUMERIC(4, "Date, Local Trans.", true);
        f[14] = new IFB_NUMERIC(4, "Date, Expiration", true);
        f[18] = new IFB_NUMERIC(4, "Merchant Category Code", true);
        f[22] = new IFB_NUMERIC(3, "POS Entry Mode", true);
        f[23] = new IFB_NUMERIC(3, "Card Sequence Number", true);
        f[24] = new IFB_NUMERIC(3, "NII", true);
        f[25] = new IFB_NUMERIC(2, "POS Condition Code", true);
        f[26] = new IFB_NUMERIC(2, "POS PIN capture code", true);
        f[28] = new IF_CHAR(9, "Fee Amount");
        f[35] = new IFB_LLNUM(37, "Track 2 Data", false);
        f[37] = new IF_CHAR(12, "Retrieval Ref. No.");
        f[38] = new IF_CHAR(6, "Auth. Id. Response");
        f[39] = new IF_CHAR(2, "Response Code");
        f[41] = new IF_CHAR(8, "Terminal Id");
        f[42] = new IF_CHAR(15, "Card Acq. Id");
        f[43] = new IF_CHAR(40, "Card Acq. Name");
        f[45] = new IFB_LLCHAR(76, "Track 1 Data");
        f[48] = new IFB_LLLCHAR(999, "Additional Text Data");
        f[49] = new IF_CHAR(3, "Currency Code");
        f[52] = new IFB_BINARY(8, "PIN Data");
        f[53] = new IFB_NUMERIC(16, "Security Control Info", true);
        f[54] = new IFB_LLLCHAR(120, "Additional Amounts");
        f[55] = new IFB_LLLBINARY(255, "ICC Related Data");
        f[60] = new IFB_LLLCHAR(999, "Private Use");
        f[61] = new IFB_LLLCHAR(999, "Private Use");
        f[62] = new IFB_LLLCHAR(999, "Private Use");
        f[63] = new IFB_LLLCHAR(999, "Private Use");
        f[64] = new IFB_BINARY(8, "Message Auth. Code");
        f[95] = new IFB_LLCHAR(42, "Replacement Amount");

        setFieldPackager(f);
    }
}