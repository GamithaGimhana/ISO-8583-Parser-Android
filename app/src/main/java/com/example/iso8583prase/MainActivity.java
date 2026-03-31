package com.example.iso8583prase;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

public class MainActivity extends AppCompatActivity {

    EditText inputMessage;
    Button btnParse;
    LinearLayout outputContainer;

    JPosParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMessage = findViewById(R.id.inputMessage);
        btnParse = findViewById(R.id.btnParse);
        outputContainer = findViewById(R.id.outputContainer);
//        inputMessage.setText("005A600000000002007238448008C08000185365612345731002290000000000000010000328183251062893183251032859990051003132333435363738393031325445524D494430314D45524348414E5430303030303120313434");
        parser = new JPosParser();

        btnParse.setOnClickListener(v -> {
            String msg = inputMessage.getText().toString().trim();

            if (msg.isEmpty()) return;

            try {
                ISOMsg iso = parser.parse(msg);
                displayResult(msg, iso);
            } catch (Exception e) {
                showError("Parse Error: " + e.getMessage());
                Log.e("Parse Error", e.getMessage(), e);
            }
        });
    }

    private void displayResult(String rawMessage, ISOMsg iso) throws ISOException {
        outputContainer.removeAllViews();

        if (rawMessage.length() >= 14) {
            addText("Length: " + rawMessage.substring(0, 4));
            addText("TPDU: " + rawMessage.substring(4, 14));
        }

        addText("MTI: " + iso.getMTI());

        for (int i = 2; i <= iso.getMaxField(); i++) {
            if (iso.hasField(i)) {
                addText("DE" + i + ": " + iso.getString(i));
            }
        }
    }

    private void addText(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(14);
        tv.setPadding(10, 10, 10, 10);
        outputContainer.addView(tv);
    }

    private void showError(String msg) {
        outputContainer.removeAllViews();
        addText(msg);
    }
}