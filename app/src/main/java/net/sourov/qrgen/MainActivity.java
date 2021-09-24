package net.sourov.qrgen;


import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {


    private ImageView qrCodeIV;
    private EditText dataEdt;
    private Button generateQrBtn;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all variables.
        qrCodeIV = findViewById(R.id.idIVQrcode);
        dataEdt = findViewById(R.id.idEdt);
        generateQrBtn = findViewById(R.id.idBtnGenerateQR);

        // initializing onclick listener for button.
        generateQrBtn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(dataEdt.getText().toString())) {

                // if the edittext inputs are empty then execute
                // this method showing a toast message.
                Toast.makeText(MainActivity.this, "Enter some text to generate QR Code", Toast.LENGTH_SHORT).show();
            } else {

                WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();


                Point point = new Point();
                display.getSize(point);


                int width = point.x;
                int height = point.y;

                // generating dimension from width and height.
                int dimen = width < height ? width : height;
                dimen = dimen * 3 / 4;


                qrgEncoder = new QRGEncoder(dataEdt.getText().toString(), null, QRGContents.Type.TEXT, dimen);
                try {

                    bitmap = qrgEncoder.encodeAsBitmap();

                    qrCodeIV.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    Log.e("Tag", e.toString());
                }
            }
        });
    }
}
