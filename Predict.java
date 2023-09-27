package com.example.farmerguide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.farmerguide.ml.TfLiteModel;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Predict extends AppCompatActivity {
    Button select_btn, predict;
    ImageView imageView;
    TextView tv, remedy_;
    Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predict_layout);
        select_btn = (Button) findViewById(R.id.select);

        imageView = (ImageView) findViewById(R.id.imageView);
        predict = (Button) findViewById((R.id.predict));
        tv = (TextView) findViewById(R.id.textview);
        remedy_ = (TextView) findViewById(R.id.textview2);


        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });


        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] class_labels = {"Bacterial spot", "Early blight",
                        "healthy", "Late_blight", "Leaf_Mold",
                        "Septoria leaf spot",
                        "TSpider_mites Two-spotted_spider_mite",
                        "Target Spot", "Mosaic_virus",
                        "Yellow Leaf Curl Virus"};
                String disease = "Detected Disease is ";
                String fungi=" ";



                img = Bitmap.createScaledBitmap(img,256,256,true);


                try {

                    TfLiteModel model = TfLiteModel.newInstance(getApplicationContext());

                    // Creates inputs for reference.

                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 256, 256, 3}, DataType.FLOAT32);

                    TensorImage tensorImage = new TensorImage(DataType.FLOAT32);
                    tensorImage.load(img);
                    ByteBuffer byteBuffer = tensorImage.getBuffer();


                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    TfLiteModel.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    // Releases model resources if no longer used.
                    model.close();

                    //tv.setText(outputFeature0.getFloatArray()[0]+"\n"+outputFeature0.getFloatArray()[1]);
                    if(outputFeature0.getFloatArray()[0]>0.5)
                    {
                        disease += class_labels[0];
                        fungi += "Recommended Pesticides :\n" +
                                "1.\tCopper Hydroxide\n" +
                                "2.\tNeem Oil\n";
                    }
                    else if(outputFeature0.getFloatArray()[1]>0.5)
                    {
                        disease += class_labels[1];
                        fungi += "Recommended Pesticides :\n" +
                                "1.\tCEASE Biological Fungicide\n" +
                                "2.\tBonide® Copper Fungicide Dust\n";
                    }
                    else if(outputFeature0.getFloatArray()[2]>0.5)
                    {
                        disease += class_labels[2];
                        fungi += "Plant is safe";
                    }
                    else if(outputFeature0.getFloatArray()[3]>0.5)
                    {
                        disease += class_labels[3];
                        fungi += "Recommended Pesticides :\n" +
                                "1.\tChlorothalonil\n" +
                                "2.\tMancozeb(Manzate)\n";

                    }
                    else if(outputFeature0.getFloatArray()[4]>0.5)
                    {
                        disease += class_labels[4];
                        fungi += "Recommended Pesticides :\n" +
                                "•\tIndofil Z-78\n" +
                                "•\tchlorothalonil\n" +
                                "•\tmancozeb \n" +
                                "•\tcopper formulations\n";
                    }
                    else if(outputFeature0.getFloatArray()[5]>0.5)
                    {
                        disease += class_labels[5];
                        fungi += "Fungicides labeled for target spot in cotton include: \n" +
                                "1.\tElatus (5-7.3 fl oz/a), \n" +
                                "2.\tHeadline (6-12 fl oz/a), \n" +
                                "3.\tPriaxor (4-8 fl oz/a), \n" +
                                "4.\tQuadris (and generic azoxystrobin products, 6-9 fl oz/a), \n" +
                                "5.\tStratego YLD (5 fl oz/a), and Twinline (7-8.5 fl oz/a)\n";
                    }
                    else if(outputFeature0.getFloatArray()[6]>0.5)
                    {
                        disease += class_labels[6];
                        fungi +="Recommended Pesticides :\n" +
                                "•\tInsecticidal soaps\n" +
                                "•\tHorticultural oils\n" +
                                "•\tNeem oil\n";
                    }
                    else if(outputFeature0.getFloatArray()[7]>0.5)
                    {
                        disease += class_labels[7];
                        fungi += "Recommended Pesticides :\n" +
                                "1.\tChlorothalonil\n" +
                                "2.\tMancozeb(Manzate)\n"+
                                "3.\tCopper oxychloride\n";

                    }
                    else if(outputFeature0.getFloatArray()[8]>0.5)
                    {
                        disease += class_labels[8];
                        fungi+="Recommended Pesticides :\n" +
                                "1.\tHarvest-Guard\n" +
                                "2.\tAllDown\n" +
                                "3.\tSafer Soap\n" +
                                "4.\tBon-Neem II\n";
                    }
                    else if(outputFeature0.getFloatArray()[9]>0.5)
                    {
                        disease += class_labels[9];
                        fungi+="Recommended Pesticides :\n" +
                                "1.\tDinotefuran(Venom) imidacloprid\n" +
                                "2.\tThiamethoxam\n" +
                                "3.\tAdmorePro\n" +
                                "4.\tNuprid\n";
                    }
                    tv.setText(disease);
                    remedy_.setText(fungi);
                } catch (IOException e) {
                    // TODO Handle the exception
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100)
        {
            imageView.setImageURI(data.getData());
            Uri uri = data.getData();
            try {
                img= MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}